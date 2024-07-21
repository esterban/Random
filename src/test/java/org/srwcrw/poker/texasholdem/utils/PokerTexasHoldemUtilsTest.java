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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

// 2024-05-19 SWright - This is an experiment for testing private methods, although at the time of writing I have moved
// the private method from PokerTexasHoldemClassifier to PokerTexasHoldemUtils as public utility method??!? (I don't like moving the implementation out.
// Will leave for the time being.
@PrepareForTest(PokerTexasHoldemUtils.class)
@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
class PokerTexasHoldemUtilsTest {
    private final PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();

    @Autowired
    private TestUtilsTexasHoldem testUtilsTexasHoldem;

    @Autowired
    private CardFactoryImmutable cardFactoryImmutable;

    @Test
    public void testCreateHand5From3CommunityCardsA() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        Method method = pokerTexasHoldemUtils.getClass().getDeclaredMethod("createHand5From3CommunityCards", Hand5Card.class, Hand2Card.class);
        method.setAccessible(true);

        List<Hand5Card> hand5CardList = (List<Hand5Card>) method.invoke(pokerTexasHoldemUtils, onePairAllCards.getKey(), onePairAllCards.getValue());

        assertThat(hand5CardList).hasSize(10);

        Hand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand5Card,
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace)
        );

    }

    @Test
    public void testCreateHand5From3CommunityCardsB() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairB();

        Method method = pokerTexasHoldemUtils.getClass().getDeclaredMethod("createHand5From3CommunityCards", Hand5Card.class, Hand2Card.class);
        method.setAccessible(true);

        List<Hand5Card> hand5CardList = (List<Hand5Card>) method.invoke(pokerTexasHoldemUtils, onePairAllCards.getKey(), onePairAllCards.getValue());

        assertThat(hand5CardList).hasSize(10);

        Hand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand5Card,
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Six),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace)
        );
    }

    @Test
    public void testFindBestHandA() {
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(onePairAllCards.getKey(), onePairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace)
        );
    }

    @Test
    public void testFindBestHandB() {
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createTwoPairA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Five),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace));
    }

    @Test
    public void testFindBestHandC() {
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createThreeAcesA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace));
    }

    @Test
    public void testFindBestHandD() {
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createStraightA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten));
    }

    @Test
    public void testFindBestHandE() {
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFlushA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,

                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six));
    }

    @Test
    public void testFindBestHandF() {
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFullhouseA();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Six),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Six),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten));
    }

    @Test
    public void testFindBestHandG() {
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFourKings();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.King),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen));
    }

    @Test
    public void testFindBestHandH() {
        Map.Entry<Hand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createStraightFlush();

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        Hand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Jack),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten));
    }

    @Test
    void testCreateHand5From4CommunityCardsA() {
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        Card handCard = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five);

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), handCard);

        Hand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

        assertThat(hand5CardList).hasSize(5);

        assertHandMatchesExpectedOutput(bestHand5Card,
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace));
    }

    @Test
    void testCreateHand5From4CommunityCardsB() {
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairC();

        Card handCard = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Two);

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), handCard);

        Hand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

        assertThat(hand5CardList).hasSize(5);

        assertHandMatchesExpectedOutput(bestHand5Card,
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Two),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Two),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen));
    }

    @Test
    void testCreateHand5From4CommunityCardsTwoPairA() {
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createTwoPairB();

        Card handCard = cardFactoryImmutable.createCard(Suit.Clubs, Value.Three);

        List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), handCard);

        Hand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

        assertThat(hand5CardList).hasSize(5);

        assertHandMatchesExpectedOutput(bestHand5Card,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Three),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Three),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen));
    }

    @Test
    void testCreateHand5From4CommunityCardsTwoPairMultiA() {
        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createTwoPairB();


        for (Value value : Value.values()) {

            Card handCard = cardFactoryImmutable.createCard(Suit.Diamonds, value);
            List<Hand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), handCard);

            Hand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

            assertThat(hand5CardList).hasSize(5);

            if (value == Value.Queen) {
                assertHandMatchesExpectedOutput(bestHand5Card,
                        cardFactoryImmutable.createCard(Suit.Hearts, Value.Three),
                        cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                        cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen),
                        cardFactoryImmutable.createCard(Suit.Diamonds, Value.Queen),
                        cardFactoryImmutable.createCard(Suit.Spades, Value.Queen));

            } else if (value == Value.Two) {
                assertHandMatchesExpectedOutput(bestHand5Card,
                        cardFactoryImmutable.createCard(Suit.Hearts, Value.Two),
                        cardFactoryImmutable.createCard(Suit.Diamonds, Value.Two),
                        cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                        cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen),
                        cardFactoryImmutable.createCard(Suit.Spades, Value.Queen));

            } else {
                assertHandMatchesExpectedOutput(bestHand5Card,
                        cardFactoryImmutable.createCard(Suit.Diamonds, value),
                        cardFactoryImmutable.createCard(Suit.Hearts, Value.Three),
                        cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                        cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen),
                        cardFactoryImmutable.createCard(Suit.Spades, Value.Queen));
            }
        }
    }

    private void assertHandMatchesExpectedOutput(Hand5Card hand5Card, Card expectedCard1, Card expectedCard2, Card expectedCard3, Card expectedCard4, Card expectedCard5) {
        Set<Card> cardSet = new TreeSet<>();

        for (int index = 0; index < 5; ++index) {
            cardSet.add(hand5Card.getNthCard(index));
        }

        assertThat(cardSet).contains(
                expectedCard1,
                expectedCard2,
                expectedCard3,
                expectedCard4,
                expectedCard5
        );
    }
}