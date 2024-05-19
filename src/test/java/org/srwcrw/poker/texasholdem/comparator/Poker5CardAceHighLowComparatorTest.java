package org.srwcrw.poker.texasholdem.comparator;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;

import static org.assertj.core.api.Assertions.assertThat;

class Poker5CardAceHighLowComparatorTest {
    private TestUtils testUtils = new TestUtils();
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
    }

    @Test
    void testCompareFlushA() {
        Hand5Card hand5CardFlushA = testUtils.createFlushA();
        Hand5Card hand5CardFlushB = testUtils.createFlushB();

        Hand5Card hand5CardOnePair = testUtils.createOnePair();

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardOnePair)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardOnePair)).isGreaterThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardFlushB)).isGreaterThan(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardFlushA)).isLessThan(0);

        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushA, hand5CardFlushA)).isEqualTo(0);
        assertThat(poker5CardAceHighLowComparator.compare(hand5CardFlushB, hand5CardFlushB)).isEqualTo(0);
    }

}