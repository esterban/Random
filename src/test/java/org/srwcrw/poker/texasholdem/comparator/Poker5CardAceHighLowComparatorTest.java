package org.srwcrw.poker.texasholdem.comparator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.Hand5Card;
import org.srwcrw.poker.texasholdem.components.Hand5OrdinalFactoryFast;
import org.srwcrw.poker.texasholdem.components.HandFactoryHand5;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.CardOrdinalFactory;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardOrdinalFactory.class, CardFactoryImmutable.class, PackGenerator.class, TestUtils.class, HandFactoryHand5.class, Hand5OrdinalFactoryFast.class})
class Poker5CardAceHighLowComparatorTest {
    @Autowired
    private TestUtils testUtils;

    private Poker5CardAceHighLowComparator poker5CardAceHighLowComparator = new Poker5CardAceHighLowComparator();

    @Test
    void testCompareOnePairA() {
        Hand5Card hand5CardA = testUtils.createOnePairRank1KickerTen();
        Hand5Card hand5CardB = testUtils.createOnePairRank1KickerNine();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardB)).isEqualTo(0);
    }

    @Test
    void testCompareTwoPairA() {
        Hand5Card hand5CardA = testUtils.createTwoPairBKickerTen();
        Hand5Card hand5CardB = testUtils.createTwoPairBKickerSeven();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardB)).isEqualTo(0);
    }

    @Test
    void testCompareTwoPairB() {
        Hand5Card hand5CardA = testUtils.createTwoPairBKickerTwo();
        Hand5Card hand5CardB = testUtils.createTwoPairBKickerAce();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardA)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardB)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardB)).isEqualTo(0);
    }

    @Test
    void testCompareThreeOfAKindA() {
        Hand5Card hand5CardA = testUtils.createThreeOfAKindA();
        Hand5Card hand5CardB = testUtils.createThreeOfAKindB();
        Hand5Card hand5CardC = testUtils.createThreeOfAKindC();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardC)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardC, hand5CardA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardC)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardC, hand5CardB)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardC, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardOnePair, hand5CardA)).isLessThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardOnePair, hand5CardB)).isLessThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardOnePair, hand5CardC)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardA, hand5CardA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardB, hand5CardB)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardC, hand5CardC)).isEqualTo(0);
    }

    @Test
    void testCompareStraightA() {
        Hand5Card hand5CardStraight = testUtils.createStraight();
        Hand5Card hand5CardStraightAceLow = testUtils.createStraightAceLow();
        Hand5Card hand5CardStraightAceHigh = testUtils.createStraightAceHigh();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraight, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightAceLow, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightAceHigh, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightAceHigh, hand5CardStraightAceLow)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightAceLow, hand5CardStraightAceHigh)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightAceHigh, hand5CardStraight)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraight, hand5CardStraightAceLow)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraight, hand5CardStraightAceHigh)).isLessThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightAceLow, hand5CardStraight)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraight, hand5CardStraight)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightAceLow, hand5CardStraightAceLow)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightAceHigh, hand5CardStraightAceHigh)).isEqualTo(0);
    }

    @Test
    void testCompareFlushA() {
        Hand5Card hand5CardFlushA = testUtils.createFlushA();
        Hand5Card hand5CardFlushB = testUtils.createFlushB();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardFlushB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardFlushA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardFlushA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardFlushB)).isEqualTo(0);
    }

    @Test
    void testCompareFlushB() {
        Hand5Card hand5CardFlushA = testUtils.createFlushA();
        Hand5Card hand5CardFlushB = testUtils.createFlushAA();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardFlushB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardFlushA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardFlushA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardFlushB)).isEqualTo(0);
    }


//*** DRAWING HAND *** DRAWING HAND *** - Player / Opponent hole cards are : Hand2Card{cards=[Card{suit=Clubs, value=Two}, Card{suit=Spades, value=King}]} -> Hand{cards=[Card{suit=Hearts, value=Six}, Card{suit=Hearts, value=Ace}]}
//*** DRAWING HAND *** DRAWING HAND *** - Community cards are : Hand{cards=[Card{suit=Clubs, value=Three}, Card{suit=Clubs, value=Four}, Card{suit=Clubs, value=Ten}, Card{suit=Clubs, value=Queen}, Card{suit=Clubs, value=King}]}
//*** DRAWING HAND *** DRAWING HAND *** - Hand type = Flush
//

//    @Test
//    void testCompareFlushC() {
//        Hand5Card hand5CardFlushA = new Hand5Card(
//                cardFactoryImmutable.createCard(Clubs, ),
//                cardFactoryImmutable.createCard(Clubs, ),
//                cardFactoryImmutable.createCard(Clubs, ),
//                cardFactoryImmutable.createCard(Clubs, ),
//                cardFactoryImmutable.createCard(Clubs,)
//        );
//        Hand5Card hand5CardFlushB  = new Hand5Card(
//                cardFactoryImmutable.createCard(Clubs, ),
//                cardFactoryImmutable.createCard(Clubs, ),
//                cardFactoryImmutable.createCard(Clubs, ),
//                cardFactoryImmutable.createCard(Clubs, ),
//                cardFactoryImmutable.createCard(Clubs,)
//        );
//
//        Hand5Card hand5CardOnePair = testUtils.createOnePair();
//
//        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardOnePair)).isGreaterThan(0);
//        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardOnePair)).isGreaterThan(0);
//
//        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardFlushB)).isGreaterThan(0);
//        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardFlushA)).isLessThan(0);
//
//        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardFlushA)).isEqualTo(0);
//        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardFlushB)).isEqualTo(0);
//    }


    @Test
    void testCompareFullHouse() {
        Hand5Card hand5CardFullHouseA = testUtils.createFullHouseA();
        Hand5Card hand5CardFullHouseB = testUtils.createFullHouseB();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFullHouseA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFullHouseA, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFullHouseA, hand5CardFullHouseB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFullHouseB, hand5CardFullHouseA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFullHouseA, hand5CardFullHouseA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFullHouseB, hand5CardFullHouseB)).isEqualTo(0);
    }

    @Test
    void testCompareFourOfAKind() {
        Hand5Card hand5CardFourOfAKindA = testUtils.createFourOfAKindA();
        Hand5Card hand5CardFourOfAKindB = testUtils.createFourOfAKindB();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFourOfAKindA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFourOfAKindA, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFourOfAKindA, hand5CardFourOfAKindB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFourOfAKindB, hand5CardFourOfAKindA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFourOfAKindA, hand5CardFourOfAKindA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFourOfAKindB, hand5CardFourOfAKindB)).isEqualTo(0);
    }

    @Test
    void testCompareStraightFlush() {
        Hand5Card hand5CardStraightFlushA = testUtils.createStraightFlushA();
        Hand5Card hand5CardStraightFlushB = testUtils.createStraightFlushB();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushA, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushA, hand5CardStraightFlushB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushB, hand5CardStraightFlushA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushA, hand5CardStraightFlushA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushB, hand5CardStraightFlushB)).isEqualTo(0);
    }

    @Test
    void testCompareRoyalFlush() {
        Hand5Card hand5CardStraightFlushA = testUtils.createRoyalFlushA();
        Hand5Card hand5CardStraightFlushB = testUtils.createRoyalFlushB();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushA, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushA, hand5CardStraightFlushB)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushB, hand5CardStraightFlushA)).isEqualTo(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushA, hand5CardStraightFlushA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardStraightFlushB, hand5CardStraightFlushB)).isEqualTo(0);
    }

    @Test
    void testCompareHighestCardA() {
        Hand5Card hand5CardHighestCardA = testUtils.createHighestCardA();
        Hand5Card hand5CardHighestCardB = testUtils.createHighestCardB();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardA, hand5CardOnePair)).isLessThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardB, hand5CardOnePair)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardA, hand5CardHighestCardB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardB, hand5CardHighestCardA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardA, hand5CardHighestCardA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardB, hand5CardHighestCardB)).isEqualTo(0);
    }

    @Test
    void testCompareHighestCardB() {
        Hand5Card hand5CardHighestCardA = testUtils.createHighestCardA();
        Hand5Card hand5CardHighestCardAA = testUtils.createHighestCardAA();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardA, hand5CardOnePair)).isLessThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardAA, hand5CardOnePair)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardA, hand5CardHighestCardAA)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardAA, hand5CardHighestCardA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardA, hand5CardHighestCardA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardHighestCardAA, hand5CardHighestCardAA)).isEqualTo(0);
    }

    @Test
    void testCompareThreeOfAKindB() {
        Hand5Card threeOfAKindA = testUtils.createThreeOfAKindA();
        Hand5Card threeOfAKindAA = testUtils.createThreeOfAKindAA();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(threeOfAKindA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardOnePair, threeOfAKindA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(threeOfAKindAA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardOnePair, threeOfAKindAA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(threeOfAKindA, threeOfAKindAA)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(threeOfAKindAA, threeOfAKindA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(threeOfAKindA, threeOfAKindA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(threeOfAKindAA, threeOfAKindAA)).isEqualTo(0);
    }


}