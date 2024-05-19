package org.srwcrw.poker.texasholdem.utils;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceLow;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import java.util.Comparator;
import java.util.SortedSet;

import static org.assertj.core.api.Assertions.assertThat;

class HandUtilsTest {

    private ValueComparatorAceLow valueComparatorAceLow = new ValueComparatorAceLow();
    private Comparator<Value> valueComparatorAceHigh = new ValueComparatorAceHigh();
    private HandUtils handUtils = new HandUtils();

    @Test
    void areValuesConsecutiveAceLowA() {
        Hand5Card straightFlushAceLow = TestUtils.createStraightFlushAceLow();
        SortedSet<Value> straightFlushAceLowValues = handUtils.getValueSetSorted(straightFlushAceLow, valueComparatorAceLow);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceLowValues)).isTrue();
    }

    @Test
    void areValuesConsecutiveAceLowB() {
        Hand5Card straightFlushAceLow = TestUtils.createStraightFlushAceHigh();
        SortedSet<Value> straightFlushAceLowValues = handUtils.getValueSetSorted(straightFlushAceLow, valueComparatorAceLow);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceLowValues)).isFalse();
    }

    @Test
    void areValuesConsecutiveAceHighA() {
        Hand5Card straightFlushAceHigh = TestUtils.createStraightFlushAceLow();
        SortedSet<Value> straightFlushAceHighValues = handUtils.getValueSetSorted(straightFlushAceHigh, valueComparatorAceHigh);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceHighValues)).isFalse();
    }

    @Test
    void areValuesConsecutiveAceHighB() {
        Hand5Card straightFlushAceHigh = TestUtils.createStraightFlushAceHigh();
        SortedSet<Value> straightFlushAceHighValues = handUtils.getValueSetSorted(straightFlushAceHigh, valueComparatorAceHigh);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceHighValues)).isTrue();
    }

    @Test
    void areValuesNotConsecutive() {
        Hand5Card onePairHand = TestUtils.createOnePair();
        SortedSet<Value> onePairValues = handUtils.getValueSetSorted(onePairHand, valueComparatorAceLow);

        assertThat(handUtils.areValuesConsecutive(onePairValues)).isFalse();
    }
}