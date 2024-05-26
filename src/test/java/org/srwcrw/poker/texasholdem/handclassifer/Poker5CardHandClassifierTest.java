package org.srwcrw.poker.texasholdem.handclassifer;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class Poker5CardHandClassifierTest {
    private Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();


    @Test
    public void testClassifyStraightFlush() {
        Hand5Card straightFlush = TestUtils.createStraightFlushAceLow();

        HandType5Cards handType5Cards = poker5CardHandClassifier.classify(straightFlush);

        assertThat(handType5Cards).isEqualTo(HandType5Cards.StraightFlush);
    }

    @Test
    public void testClassifyRoyalFlush() {
        Hand5Card royalFlush = TestUtils.createStraightFlushAceHigh();

        HandType5Cards handType5Cards = poker5CardHandClassifier.classify(royalFlush);

        assertThat(handType5Cards).isEqualTo(HandType5Cards.RoyalFlush);
    }

    @Test
    public void testClassifyFourOfAKind() {
        Hand5Card fourOfAKind = TestUtils.createFourOfAKindA();

        HandType5Cards handType5Cards = poker5CardHandClassifier.classify(fourOfAKind);

        assertThat(handType5Cards).isEqualTo(HandType5Cards.FourOfAKind);
    }

    @Test
    public void testClassifyFlush() {
        Hand5Card flushHandA = TestUtils.createFlushA();
        Hand5Card flushHandB = TestUtils.createFlushB();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(flushHandA);
        HandType5Cards handType5CardsB = poker5CardHandClassifier.classify(flushHandB);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.Flush);
        assertThat(handType5CardsB).isEqualTo(HandType5Cards.Flush);
    }


    @Test
    public void testClassifyFullHouse() {
        Hand5Card fullHouseHand = TestUtils.createFullHouseA();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(fullHouseHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.FullHouse);
    }

    @Test
    public void testClassifyStraight() {
        Hand5Card straightHand = TestUtils.createStraight();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(straightHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.Straight);
    }

    @Test
    public void testClassifyThreeOfAKind() {
        Hand5Card threeOfAKindHand = TestUtils.createThreeOfAKind();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(threeOfAKindHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.ThreeOfAKind);
    }

    @Test
    public void testClassifyTwoPair() {
        Hand5Card twoPairHand = TestUtils.createTwoPairA();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(twoPairHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.TwoPair);
    }

    @Test
    public void testClassifyTwoPairB() {
//        Hand5Card twoPairHand = TestUtils.createTwoPairBKickerSeven();
        Hand5Card twoPairHand = TestUtils.createTwoPairBKickerTen();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(twoPairHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.TwoPair);
    }

    @Test
    public void testClassifyOnePair() {
        Hand5Card onePairHand = TestUtils.createOnePair();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(onePairHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.OnePair);
    }

    @Test
    public void testClassifyHighestCard() {
        Hand5Card highestCardHand = TestUtils.createHighestCard();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(highestCardHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.HighestCard);
    }
}