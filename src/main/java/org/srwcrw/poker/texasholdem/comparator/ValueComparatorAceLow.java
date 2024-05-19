package org.srwcrw.poker.texasholdem.comparator;

import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.Comparator;

public class ValueComparatorAceLow implements Comparator<Value> {
    @Override
    public int compare(Value o1, Value o2) {
        if (o1 == Value.Ace) {
            if (o2 == Value.Ace) {
                return 0;
            }

            return -1;
        }

        if (o2 == Value.Ace) {
            return 1;
        }

        return o1.compareTo(o2);
    }
}
