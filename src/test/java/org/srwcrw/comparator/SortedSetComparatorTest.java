package org.srwcrw.comparator;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceLow;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class SortedSetComparatorTest {

    @Test
    void testCompareA() {
        SortedSetComparator<Card> setComparator = new SortedSetComparator<>();

        SortedSet<Card> set1 = TestUtils.createStraightFlushAceHigh().getCards();
        SortedSet<Card> set2 = TestUtils.createStraightFlushAceLow().getCards();

        assertThat(setComparator.compare(set1, set2)).isGreaterThan(0);
        assertThat(setComparator.compare(set2, set1)).isLessThan(0);
    }

    @Test
    void testCompareB() {
        ValueComparatorAceLow valueComparatorAceLow = new ValueComparatorAceLow();

        SortedSetComparator<Value> setComparator = new SortedSetComparator<>();

        SortedSet<Value> values1 = new TreeSet<>(valueComparatorAceLow);
        values1.add(Value.Ace);
        values1.add(Value.Three);

        SortedSet<Value> values2 = new TreeSet<>(valueComparatorAceLow);
        values2.add(Value.Ace);
        values2.add(Value.Five);

        assertThat(setComparator.compare(values1, values2)).isLessThan(0);
    }

    @Test
    void testCompareC() {
        Comparator<Value> valueComparatorAceHigh = new ValueComparatorAceHigh();

        SortedSetComparator<Value> setComparator = new SortedSetComparator<>();

        SortedSet<Value> values1 = new TreeSet<>(valueComparatorAceHigh);
        values1.add(Value.Ace);

        SortedSet<Value> values2 = new TreeSet<>(valueComparatorAceHigh);
        values2.add(Value.Five);

        assertThat(setComparator.compare(values1, values2)).isGreaterThan(0);
    }

}