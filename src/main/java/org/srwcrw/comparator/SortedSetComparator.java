package org.srwcrw.comparator;

import java.util.*;

public class SortedSetComparator<T extends Comparable<T>> implements Comparator<SortedSet<T>> {
    @Override
    public int compare(SortedSet<T> o1, SortedSet<T> o2) {
        if (o1.size() != o2.size()) {
            return Integer.compare(o1.size(), o2.size());
        }

        Comparator<? super T> comparator = o1.comparator();

        if (comparator == null) {
            comparator = Comparator.naturalOrder();
        }

        TreeSet<T> reversed1 = new TreeSet<>(Collections.reverseOrder(o1.comparator()));
        TreeSet<T> reversed2 = new TreeSet<>(Collections.reverseOrder(o2.comparator()));

        reversed1.addAll(o1);
        reversed2.addAll(o2);

        Iterator<T> iterator1 = reversed1.iterator();
        Iterator<T> iterator2 = reversed2.iterator();

        while (iterator1.hasNext()) {
            T value1 = iterator1.next();
            T value2 = iterator2.next();

            if (comparator.compare(value1, value2) != 0) {
                return comparator.compare(value1, value2);
            }
        }

        return 0;
    }
}
