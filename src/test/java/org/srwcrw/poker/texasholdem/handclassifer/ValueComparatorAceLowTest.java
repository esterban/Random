package org.srwcrw.poker.texasholdem.handclassifer;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceLow;
import org.srwcrw.poker.texasholdem.entities.Value;

import static org.assertj.core.api.Assertions.assertThat;

class ValueComparatorAceLowTest {
    @Test
    public void testCompareA() {
        assertThat(Value.Ace).usingComparator(new ValueComparatorAceLow()).isLessThan(Value.Two);
        assertThat(Value.Ace).usingComparator(new ValueComparatorAceLow()).isLessThan(Value.King);
        assertThat(Value.Ace).usingComparator(new ValueComparatorAceLow()).isEqualTo(Value.Ace);
    }
}