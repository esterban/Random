package org.srwcrw.poker.texasholdem.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.components.*;
import org.srwcrw.poker.texasholdem.comparator.Poker5CardAceHighLowComparator;
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
@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class, CardOrdinalFactory.class, HandFactoryHand5.class, Hand5OrdinalFactoryFast.class, PokerTexasHoldemUtils.class})
class PokerTexasHoldemUtilsTest {
    @Autowired
    private PokerTexasHoldemUtils pokerTexasHoldemUtils;

    private final Poker5CardAceHighLowComparator poker5CardAceHighLowComparator = new Poker5CardAceHighLowComparator();

    @Autowired
    private TestUtilsTexasHoldem testUtilsTexasHoldem;

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private CardFactoryImmutable cardFactoryImmutable;

    @Autowired
    private HandFactoryHand5 handFactoryHand5;

    @Test
    public void testCreateHand5From3CommunityCardsA() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map.Entry<IHand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        Method method = pokerTexasHoldemUtils.getClass().getDeclaredMethod("createHand5From3CommunityCards", IHand5Card.class, Hand2Card.class);
        method.setAccessible(true);

        List<IHand5Card> hand5CardList = (List<IHand5Card>) method.invoke(pokerTexasHoldemUtils, onePairAllCards.getKey(), onePairAllCards.getValue());

        assertThat(hand5CardList).hasSize(10);

        IHand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

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
        Map.Entry<IHand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairB();

        Method method = pokerTexasHoldemUtils.getClass().getDeclaredMethod("createHand5From3CommunityCards", IHand5Card.class, Hand2Card.class);
        method.setAccessible(true);

        List<IHand5Card> hand5CardList = (List<IHand5Card>) method.invoke(pokerTexasHoldemUtils, onePairAllCards.getKey(), onePairAllCards.getValue());

        assertThat(hand5CardList).hasSize(10);

        IHand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

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
        Map.Entry<IHand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(onePairAllCards.getKey(), onePairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

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
        Map.Entry<IHand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createTwoPairA();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Five),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace));
    }

    @Test
    public void testFindBestHandC() {
        Map.Entry<IHand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createThreeAcesA();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace));
    }

    @Test
    public void testFindBestHandD() {
        Map.Entry<IHand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createStraightA();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten));
    }

    @Test
    public void testFindBestHandE() {
        Map.Entry<IHand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFlushA();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,

                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six));
    }

    @Test
    public void testFindBestHandF() {
        Map.Entry<IHand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFullhouseA();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.Six),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Six),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten));
    }

    @Test
    public void testFindBestHandG() {
        Map.Entry<IHand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createFourKings();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Diamonds, Value.King),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.King),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen));
    }

    @Test
    public void testFindBestHandH() {
        Map.Entry<IHand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createStraightFlush();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.King),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Jack),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten));
    }


//        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ace));
//        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Three));
//        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
//        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
//        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));
//
//        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ace));
//        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Five));

    @Test
    public void testFindBestTwoPairA() {
        Map.Entry<IHand5Card, Hand2Card> twoPairAllCards = testUtilsTexasHoldem.createOnePairA();

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.generateAllPossibleHands(twoPairAllCards.getKey(), twoPairAllCards.getValue());

        IHand5Card bestHand = pokerTexasHoldemUtils.findBestHand(hand5CardList);

        assertHandMatchesExpectedOutput(bestHand,
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Queen),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Six));
    }


    @Test
    void testCreateHand5From4CommunityCardsA() {
        Map.Entry<IHand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        Card handCard = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five);

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), handCard);

        IHand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

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
        Map.Entry<IHand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairC();

        Card handCard = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Two);

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), handCard);

        IHand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

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
        Map.Entry<IHand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairD();

        Card handCard = cardFactoryImmutable.createCard(Suit.Clubs, Value.Three);

        List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), handCard);

        IHand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

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
        Map.Entry<IHand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairD();

        for (Value value : Value.values()) {

            Card handCard = cardFactoryImmutable.createCard(Suit.Diamonds, value);
            List<IHand5Card> hand5CardList = pokerTexasHoldemUtils.createHand5From4CommunityCards(onePairAllCards.getKey(), handCard);

            IHand5Card bestHand5Card = testUtilsTexasHoldem.getBestHand(hand5CardList);

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

//    Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
//    Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);
//    Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack);
//    Card card4 = cardFactoryImmutable.createCard(Suit.Spades, Value.Jack);
//    Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);

    @Test
    void testTwoPlayersTwoPairKickerDrawA() {
        IHand5Card twoPairCommunityCards = testUtils.createTwoPairBKickerTen();

        Hand2Card playerHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Clubs, Value.Two),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Three));

        Hand2Card opponentHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Clubs, Value.Four),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Five));

        IHand5Card bestPlayerHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(twoPairCommunityCards, playerHand2Card);
        IHand5Card bestOppenentHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(twoPairCommunityCards, opponentHand2Card);

        int bestHandsComparison = poker5CardAceHighLowComparator.compare(bestPlayerHand5Card, bestOppenentHand5Card);

        assertThat(bestHandsComparison).isEqualTo(0);
    }

    @Test
    void testTwoPlayersTwoPairKickerDrawB() {
        IHand5Card twoPairCommunityCards = testUtils.createTwoPairBKickerTen();

        Hand2Card playerHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Three));

        Hand2Card opponentHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Clubs, Value.Four),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Five));

        IHand5Card bestPlayerHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(twoPairCommunityCards, playerHand2Card);
        IHand5Card bestOppenentHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(twoPairCommunityCards, opponentHand2Card);

        int bestHandsComparison = poker5CardAceHighLowComparator.compare(bestPlayerHand5Card, bestOppenentHand5Card);

        assertThat(bestHandsComparison).isPositive();
    }

//    Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Two);
//    Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Three);
//    Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Five);
//    Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Six);
//    Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Seven);

    @Test
    void testTwoPlayersTwoPairKickerDrawC() {
        IHand5Card communityHighestCard = testUtils.createHighestCardC();

        Hand2Card playerHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Diamonds, Value.Two),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Jack));

        Hand2Card opponentHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Clubs, Value.King),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Five));

        IHand5Card bestPlayerHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityHighestCard, playerHand2Card);
        IHand5Card bestOppenentHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityHighestCard, opponentHand2Card);

        int bestHandsComparison = poker5CardAceHighLowComparator.compare(bestPlayerHand5Card, bestOppenentHand5Card);

        assertThat(bestHandsComparison).isNegative();
    }

    @Test
    void testTwoPlayersTwoPairKickerDrawD() {
        IHand5Card communityHighestCard = testUtils.createHighestCardC();

        Hand2Card playerHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Diamonds, Value.Three),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Jack));

        Hand2Card opponentHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Clubs, Value.Three),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Eight));

        IHand5Card bestPlayerHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityHighestCard, playerHand2Card);
        IHand5Card bestOppenentHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityHighestCard, opponentHand2Card);

        int bestHandsComparison = poker5CardAceHighLowComparator.compare(bestPlayerHand5Card, bestOppenentHand5Card);

        assertThat(bestHandsComparison).isPositive();
    }

    @Test
    void testTwoPlayersTwoPairKickerDrawE() {
        IHand5Card communityHighestCard = testUtils.createHighestCardC();

        Hand2Card playerHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Diamonds, Value.Three),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Jack));

        Hand2Card opponentHand2Card = new Hand2Card(cardFactoryImmutable.createCard(Suit.Clubs, Value.Three),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Eight));

        IHand5Card bestPlayerHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityHighestCard, playerHand2Card);
        IHand5Card bestOppenentHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityHighestCard, opponentHand2Card);

        int bestHandsComparison = poker5CardAceHighLowComparator.compare(bestPlayerHand5Card, bestOppenentHand5Card);

        assertThat(bestHandsComparison).isPositive();
    }

//*** DRAWING HAND *** DRAWING HAND *** - Player / Opponent hole cards are : Hand2Card{cards=[Card{suit=Clubs, value=Two}, Card{suit=Spades, value=King}]} -> Hand{cards=[Card{suit=Hearts, value=Two}, Card{suit=Hearts, value=Three}]}
//*** DRAWING HAND *** DRAWING HAND *** - Community cards are : Hand{cards=[Card{suit=Hearts, value=Five}, Card{suit=Clubs, value=Six}, Card{suit=Hearts, value=Nine}, Card{suit=Spades, value=Ten}, Card{suit=Clubs, value=Ace}]}
//*** DRAWING HAND *** DRAWING HAND *** - Hand type = HighestCard

    @Test
    void testTwoPlayersHighestCard() {
        IHand5Card communityHighestCard = handFactoryHand5.create(
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Five),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Six),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Nine),
                cardFactoryImmutable.createCard(Suit.Spades, Value.Ten),
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace)
        );

        Hand2Card playerHand2Card = new Hand2Card(
                cardFactoryImmutable.createCard(Suit.Clubs, Value.Two),
                cardFactoryImmutable.createCard(Suit.Spades, Value.King));

        Hand2Card opponentHand2Card = new Hand2Card(
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Two),
                cardFactoryImmutable.createCard(Suit.Hearts, Value.Three));

        IHand5Card bestPlayerHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityHighestCard, playerHand2Card);
        IHand5Card bestOppenentHand5Card = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityHighestCard, opponentHand2Card);

        int bestHandsComparison = poker5CardAceHighLowComparator.compare(bestPlayerHand5Card, bestOppenentHand5Card);

        assertThat(bestHandsComparison).isPositive();
    }


    private void assertHandMatchesExpectedOutput(IHand5Card hand5Card, Card expectedCard1, Card expectedCard2, Card expectedCard3, Card expectedCard4, Card expectedCard5) {
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