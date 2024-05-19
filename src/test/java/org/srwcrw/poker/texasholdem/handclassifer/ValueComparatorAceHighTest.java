package org.srwcrw.poker.texasholdem.handclassifer;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.entities.Value;

import static org.assertj.core.api.Assertions.assertThat;

class ValueComparatorAceHighTest {

    @Test
    public void testCompareToA() {
        assertThat(Value.Ace).usingComparator(new ValueComparatorAceHigh()).isGreaterThan(Value.Two);
        assertThat(Value.Two).usingComparator(new ValueComparatorAceHigh()).isLessThan(Value.Ace);
        assertThat(Value.Ace).usingComparator(new ValueComparatorAceHigh()).isGreaterThan(Value.King);
        assertThat(Value.Ace).usingComparator(new ValueComparatorAceHigh()).isEqualTo(Value.Ace);
    }
}