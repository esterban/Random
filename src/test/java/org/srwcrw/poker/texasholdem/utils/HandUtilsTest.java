package org.srwcrw.poker.texasholdem.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceLow;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
class HandUtilsTest {
    @Autowired
    private TestUtils testUtils;

    private final ValueComparatorAceLow valueComparatorAceLow = new ValueComparatorAceLow();
    private final Comparator<Value> valueComparatorAceHigh = new ValueComparatorAceHigh();
    private final HandUtils handUtils = new HandUtils();

    @Test
    void areValuesConsecutiveAceLowA() {
        Hand5Card straightFlushAceLow = testUtils.createStraightFlushAceLow();
        SortedSet<Value> straightFlushAceLowValues = handUtils.getValueSetSorted(straightFlushAceLow, valueComparatorAceLow);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceLow));
        assertThat(handUtils.areValuesConsecutive(straightFlushAceLowValues)).isTrue();
    }

    @Test
    void areValuesConsecutiveAceLowB() {
        Hand5Card straightFlushAceLow = testUtils.createStraightFlushAceHigh();
        SortedSet<Value> straightFlushAceLowValues = handUtils.getValueSetSorted(straightFlushAceLow, valueComparatorAceLow);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceLowValues)).isFalse();
    }

    @Test
    void areValuesConsecutiveAceHighA() {
        Hand5Card straightFlushAceHigh = testUtils.createStraightFlushAceLow();
        SortedSet<Value> straightFlushAceHighValues = handUtils.getValueSetSorted(straightFlushAceHigh, valueComparatorAceHigh);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceHighValues)).isFalse();
    }

    @Test
    void areValuesConsecutiveAceHighB() {
        Hand5Card straightFlushAceHigh = testUtils.createStraightFlushAceHigh();
        SortedSet<Value> straightFlushAceHighValues = handUtils.getValueSetSorted(straightFlushAceHigh, valueComparatorAceHigh);

        assertThat(handUtils.areValuesConsecutive(straightFlushAceHighValues)).isTrue();
    }

    @Test
    void areValuesNotConsecutive() {
        Hand5Card onePairHand = testUtils.createOnePair();
        SortedSet<Value> onePairValues = handUtils.getValueSetSorted(onePairHand, valueComparatorAceLow);

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

        Set<Value> valueSet = handUtils.getPairValuesArray(valueCounts);

        assertThat(valueSet.size()).isEqualTo(1);
        assertThat(valueSet).containsOnly(Value.Ace);
    }

}