package org.srwcrw.poker.texasholdem.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.Hand5Card;
import org.srwcrw.poker.texasholdem.components.Hand5OrdinalFactoryFast;
import org.srwcrw.poker.texasholdem.components.HandFactoryHand5;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.CardOrdinalFactory;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceLow;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardOrdinalFactory.class, CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class, HandFactoryHand5.class, Hand5OrdinalFactoryFast.class})
class HandUtilsTest {
    @Autowired
    private TestUtils testUtils;

    private final ValueComparatorAceLow valueComparatorAceLow = new ValueComparatorAceLow();
    private final Comparator<Value> valueComparatorAceHigh = new ValueComparatorAceHigh();
    private final HandUtils handUtils = new HandUtils();

    @Test
    void areValuesConsecutiveAceLowA() {
        Hand5Card straightFlushAceLow = testUtils.createStraightFlushAceLow();
        Value[] straightFlushAceLowValues = handUtils.getValueSetSorted(straightFlushAceLow, valueComparatorAceLow);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceLow));
        assertThat(handUtils.areValuesConsecutive(straightFlushAceLowValues)).isTrue();
    }

    @Test
    void areValuesConsecutiveAceLowB() {
        Hand5Card straightFlushAceLow = testUtils.createStraightFlushAceHigh();
        Value[] straightFlushAceLowValues = handUtils.getValueSetSorted(straightFlushAceLow, valueComparatorAceLow);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceLowValues)).isFalse();
    }

    @Test
    void areValuesConsecutiveAceHighA() {
        Hand5Card straightFlushAceHigh = testUtils.createStraightFlushAceLow();
        Value[] straightFlushAceHighValues = handUtils.getValueSetSorted(straightFlushAceHigh, valueComparatorAceHigh);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceHighValues)).isFalse();
    }

    @Test
    void areValuesConsecutiveAceHighB() {
        Hand5Card straightFlushAceHigh = testUtils.createStraightFlushAceHigh();
        Value[] straightFlushAceLowValues = handUtils.getValueSetSorted(straightFlushAceHigh, valueComparatorAceHigh);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceLowValues)).isTrue();
    }

    @Test
    void areValuesNotConsecutive() {
        Hand5Card onePairHand = testUtils.createOnePair();
        Value[] onePairValues = handUtils.getValueSetSorted(onePairHand, valueComparatorAceLow);

        assertThat(handUtils.areValuesConsecutive(onePairValues)).isFalse();
    }

    @Test
    void testGetHighestSingleCardA() {
        Hand5Card hand = testUtils.createOnePair();

        Value highestValue = handUtils.getHighestSingleCardAceHigh(hand);

        assertThat(highestValue).isEqualTo(Value.King);
    }

    @Test
    void testGetHighestSingleCardB() {
        Hand5Card hand = testUtils.createHighestCardA();

        Value highestValue = handUtils.getHighestSingleCardAceHigh(hand);

        assertThat(highestValue).isEqualTo(Value.Ace);
    }

    @Test
    void testGetPairValuesArray() {
        int[] valueCounts = {
                0,0,0,0,
                3,0,0,0,
                0,0,0,0,
                2};

        Value[] valueSet = handUtils.getPairValuesArray(valueCounts);

        assertThat(ArrayUtils.indexesOf(valueSet, null).cardinality()).isEqualTo(valueSet.length - 1);
        assertThat(valueSet).contains(Value.Ace);
    }

    @Test
    void testCountThrees() {
        Hand5Card handA = testUtils.createThreeOfAKindA();
        Hand5Card handAA = testUtils.createThreeOfAKindAA();
        Hand5Card handB = testUtils.createThreeOfAKindB();
        Hand5Card handC = testUtils.createThreeOfAKindC();
        Hand5Card hand4OfKind = testUtils.createFourOfAKindA();

        int threeOfKindCountA = handUtils.countThrees(handA);
        int threeOfKindCountAA = handUtils.countThrees(handAA);
        int threeOfKindCountB = handUtils.countThrees(handB);
        int threeOfKindCountC = handUtils.countThrees(handC);
        int fourOfAKingCount = handUtils.countThrees(hand4OfKind);

        assertThat(threeOfKindCountA).isEqualTo(1);
        assertThat(threeOfKindCountAA).isEqualTo(1);
        assertThat(threeOfKindCountB).isEqualTo(1);
        assertThat(threeOfKindCountC).isEqualTo(1);
        assertThat(fourOfAKingCount).isEqualTo(0);
    }
}