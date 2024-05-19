package org.srwcrw.poker.texasholdem.utils;

import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.Comparator;
import java.util.SortedSet;

public interface IHandUtils {
    SortedSet<Value> getValueSetSorted(IPack hand, Comparator<Value> valueComparator);
}
