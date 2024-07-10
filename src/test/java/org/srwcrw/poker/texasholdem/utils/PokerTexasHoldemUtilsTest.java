package org.srwcrw.poker.texasholdem.utils;

import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// 2024-05-19 SWright - This is an experiment for testing private methods, although at the time of writing I have moved
// the private method from PokerTexasHoldemClassifier to PokerTexasHoldemUtils as public utility method??!? (I don't like moving the implementation out.
// Will leave for the time being.
@PrepareForTest(PokerTexasHoldemUtils.class)
class PokerTexasHoldemUtilsTest {
    private TestUtilsTexasHoldem testUtilsTexasHoldem = new TestUtilsTexasHoldem();

    @Test
    public void testCreateHand5From3CommunityCards() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();

        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        Method method =  pokerTexasHoldemUtils.getClass().getDeclaredMethod("createHand5From3CommunityCards",Hand5Card.class, Hand2Card.class);
        method.setAccessible(true);

        List<Hand5Card> hand5Card = (List<Hand5Card>) method.invoke(pokerTexasHoldemUtils, onePairAllCards.getKey(), onePairAllCards.getValue());

        assertThat(hand5Card).hasSize(10);
    }

    @Test
    public void testFindBestHandA() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(onePairAllCards.getKey(), onePairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertThat(bestHand.getCards()).hasSize(5);
        assertThat(bestHand.getCards()).containsExactly(
                new Card(Suit.Hearts, Value.Six),
                new Card(Suit.Clubs, Value.Ten),
                new Card(Suit.Spades, Value.Queen),
                new Card(Suit.Hearts, Value.Ace),
                new Card(Suit.Clubs, Value.Ace));
    }

    @Test
    public void testFindBestHandB() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createTwoPairA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertThat(bestHand.getCards()).hasSize(5);
        assertThat(bestHand.getCards()).containsOnly(
                new Card(Suit.Spades, Value.Queen),
                new Card(Suit.Hearts, Value.Five),
                new Card(Suit.Diamonds, Value.Five),
                new Card(Suit.Hearts, Value.Ace),
                new Card(Suit.Clubs, Value.Ace));
    }

    @Test
    void testCreateHand5From4CommunityCards() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), onePairAllCards.getValue().getCards().first());

        assertThat(hand5CardList).hasSize(5);
    }
}