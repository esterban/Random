package org.srwcrw.poker.texasholdem.utils;

import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.Comparator;

public interface IHandUtils {
    Value[] getValueSetSorted(IPack hand, Comparator<Value> valueComparator);
}
