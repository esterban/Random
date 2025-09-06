package org.srwcrw.poker.texasholdem.handclassifer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.IHand5Card;
import org.srwcrw.poker.texasholdem.components.Hand5OrdinalFactoryFast;
import org.srwcrw.poker.texasholdem.components.HandFactoryHand5;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.CardOrdinalFactory;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardOrdinalFactory.class, CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class, HandFactoryHand5.class, Hand5OrdinalFactoryFast.class})
class Poker5CardHandClassifierTest {
    private Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();

    @Autowired
    private TestUtils testUtils;

    @Test
    public void testClassifyStraightFlush() {
        IHand5Card straightFlush = testUtils.createStraightFlushAceLow();

        HandType5Cards handType5Cards = poker5CardHandClassifier.classify(straightFlush);

        assertThat(handType5Cards).isEqualTo(HandType5Cards.StraightFlush);
    }

    @Test
    public void testClassifyRoyalFlush() {
        IHand5Card royalFlush = testUtils.createStraightFlushAceHigh();

        HandType5Cards handType5Cards = poker5CardHandClassifier.classify(royalFlush);

        assertThat(handType5Cards).isEqualTo(HandType5Cards.RoyalFlush);
    }

    @Test
    public void testClassifyFourOfAKind() {
        IHand5Card fourOfAKind = testUtils.createFourOfAKindA();

        HandType5Cards handType5Cards = poker5CardHandClassifier.classify(fourOfAKind);

        assertThat(handType5Cards).isEqualTo(HandType5Cards.FourOfAKind);
    }

    @Test
    public void testClassifyFlush() {
        IHand5Card flushHandA = testUtils.createFlushA();
        IHand5Card flushHandB = testUtils.createFlushB();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(flushHandA);
        HandType5Cards handType5CardsB = poker5CardHandClassifier.classify(flushHandB);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.Flush);
        assertThat(handType5CardsB).isEqualTo(HandType5Cards.Flush);
    }


    @Test
    public void testClassifyFullHouse() {
        IHand5Card fullHouseHand = testUtils.createFullHouseA();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(fullHouseHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.FullHouse);
    }

    @Test
    public void testClassifyStraight() {
        IHand5Card straightHand = testUtils.createStraight();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(straightHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.Straight);
    }

    @Test
    public void testClassifyStraightAceLow() {
        IHand5Card hand5CardStraightAceLow = testUtils.createStraightAceLow();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(hand5CardStraightAceLow);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.Straight);
    }

    @Test
    public void testClassifyThreeOfAKind() {
        IHand5Card threeOfAKindHand = testUtils.createThreeOfAKind();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(threeOfAKindHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.ThreeOfAKind);
    }

    @Test
    public void testClassifyTwoPair() {
        IHand5Card twoPairHand = testUtils.createTwoPairA();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(twoPairHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.TwoPair);
    }

    @Test
    public void testClassifyTwoPairB() {
        IHand5Card twoPairHand = testUtils.createTwoPairBKickerTen();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(twoPairHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.TwoPair);
    }

    @Test
    public void testClassifyOnePair() {
        IHand5Card onePairHand = testUtils.createOnePair();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(onePairHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.OnePair);
    }

    @Test
    public void testClassifyHighestCard() {
        IHand5Card highestCardHand = testUtils.createHighestCardA();

        HandType5Cards handType5CardsA = poker5CardHandClassifier.classify(highestCardHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.HighestCard);
    }
}