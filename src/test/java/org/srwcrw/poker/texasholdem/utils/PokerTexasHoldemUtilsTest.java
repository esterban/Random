package org.srwcrw.poker.texasholdem.utils;

import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

// 2024-05-19 SWright - This is an experiment for testing private methods, although at the time of writing I have moved
// the private method from PokerTexasHoldemClassifier to PokerTexasHoldemUtils as public utility method??!? (I don't like moving the implementation out.
// Will leave for the time being.
@PrepareForTest(PokerTexasHoldemUtils.class)
@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
class PokerTexasHoldemUtilsTest {
    @Autowired
    private TestUtilsTexasHoldem testUtilsTexasHoldem;

    @Autowired
    private CardFactoryImmutable cardFactoryImmutable;

    @Test
    public void testCreateHand5From3CommunityCards() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();

        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        Method method = pokerTexasHoldemUtils.getClass().getDeclaredMethod("createHand5From3CommunityCards", Hand5Card.class, Hand2Card.class);
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

//        assertThat(bestHand.getCards()).hasSize(5);
//        Set<Card> cardSet = new HashSet<>();
//
//        for (int index = 0; index < 5; ++index) {
//            cardSet.add(bestHand.getNthCard(index));
//        }
//
//        assertThat(cardSet).contains(
//                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
//                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
//                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
//                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
//                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace)
//        );

        assertHandMatchesExpectedOutput( bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace)
        );
    }

    @Test
    public void testFindBestHandB() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createTwoPairA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

//        assertThat(bestHand.getCards()).hasSize(5);
//        assertThat(bestHand.getCards()).containsOnly(
        assertHandMatchesExpectedOutput( bestHand,
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Five),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace));
    }

    @Test
    public void testFindBestHandC() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createThreeAcesA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace));


//        assertThat(bestHand.getCards()).hasSize(5);
//        assertThat(bestHand.getCards()).containsOnly(
//                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
//                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
//                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ace),
//                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
//                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace));
    }

    @Test
    public void testFindBestHandD() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createStraightA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten));

//
//        assertThat(bestHand.getCards()).hasSize(5);
//        assertThat(bestHand.getCards()).containsOnly(
//                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
//                cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
//                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
//                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack),
//                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten));

//        List<Value> valueList = bestHand.getCards().stream().map(e -> e.getValue()).collect(Collectors.toUnmodifiableList());
//
//        assertThat(valueList).containsOnly(Value.Ace, Value.King, Value.Queen, Value.Jack, Value.Ten);
    }

    @Test
    public void testFindBestHandE() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFlushA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

//        assertThat(bestHand.getCards()).hasSize(5);
//        assertThat(bestHand.getCards()).containsOnly(
        assertHandMatchesExpectedOutput(bestHand,

                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six));

//        List<Suit> suitList = bestHand.getCards().stream().map(e -> e.getSuit()).collect(Collectors.toUnmodifiableList());
//
//        assertThat(suitList).containsOnly(Suit.Hearts, Suit.Hearts, Suit.Hearts, Suit.Hearts, Suit.Hearts);
    }

    @Test
    public void testFindBestHandF() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFullhouseA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

//        assertThat(bestHand.getCards()).hasSize(5);
//        assertThat(bestHand.getCards()).containsOnly(
        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Six),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Six),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten));

//        List<Value> valueList = bestHand.getCards().stream().map(e -> e.getValue()).collect(Collectors.toUnmodifiableList());
//
//        assertThat(valueList).containsOnly(Value.Six, Value.Six, Value.Six, Value.Ten, Value.Ten);
    }

    @Test
    public void testFindBestHandG() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFourKings();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

//        assertThat(bestHand.getCards()).hasSize(5);
//        assertThat(bestHand.getCards()).containsOnly(
        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.King),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen));

//        List<Value> valueList = bestHand.getCards().stream().map(e -> e.getValue()).collect(Collectors.toUnmodifiableList());
//
//        assertThat(valueList).containsOnly(Value.King, Value.King, Value.King, Value.King, Value.Queen);
    }

    @Test
    public void testFindBestHandH() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createStraightFlush();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

//        assertThat(bestHand.getCards()).hasSize(5);
//        assertThat(bestHand.getCards()).containsOnly(
        assertHandMatchesExpectedOutput(bestHand,

                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Jack),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten));

//        List<Suit> suitList = bestHand.getCards().stream().map(e -> e.getSuit()).collect(Collectors.toUnmodifiableList());
//        assertThat(suitList).containsOnly(Suit.Hearts, Suit.Hearts, Suit.Hearts, Suit.Hearts, Suit.Hearts);
//
//
//        List<Value> valueList = bestHand.getCards().stream().map(e -> e.getValue()).collect(Collectors.toUnmodifiableList());
//        assertThat(valueList).containsOnly(Value.Ace, Value.King, Value.Queen, Value.Jack, Value.Ten);
    }

    @Test
    void testCreateHand5From4CommunityCards() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), onePairAllCards.getValue().getCards().first());

        assertThat(hand5CardList).hasSize(5);
    }

    private void assertHandMatchesExpectedOutput(Hand5Card hand5Card, Card expectedCard1,Card expectedCard2,Card expectedCard3,Card expectedCard4,Card expectedCard5) {
        Set<Card> cardSet = new HashSet<>();

        for (int index = 0; index < 5; ++index) {
            cardSet.add(hand5Card.getNthCard(index));
        }

//        assertThat(cardSet).contains(
//                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
//                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
//                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
//                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
//                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace)
//        );

        assertThat(cardSet).contains(
                expectedCard1,
                expectedCard2,
                expectedCard3,
                expectedCard4,
                expectedCard5
        );
    }
}