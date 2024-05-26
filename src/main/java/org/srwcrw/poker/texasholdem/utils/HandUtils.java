package org.srwcrw.poker.texasholdem.utils;

import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HandUtils implements IHandUtils {
    private final Map<Value, Integer> valueCounts = new HashMap<>();

    @Override
    public SortedSet<Value> getValueSetSorted(IPack hand, Comparator<Value> valueComparator) {
        Supplier<SortedSet<Value>> treeSetSupplier = new Supplier<SortedSet<Value>>() {
            @Override
            public SortedSet<Value> get() {
                return new TreeSet<>(valueComparator);
            }
        };

        SortedSet<Value> values = hand.getCards().stream().map(c -> c.getValue()).collect(Collectors.toCollection(treeSetSupplier));
        return values;
    }

    public boolean areValuesConsecutive(SortedSet<Value> values) {
        boolean firstValueIsAnAce = false;
        boolean haveCheckedFirstCard = false;
        Value lastValue = null;

        for (Value value : values) {
            if (lastValue == null) {
                lastValue = value;

                if (value == Value.Ace) {
                    firstValueIsAnAce = true;
                }

                continue;
            }

            if (firstValueIsAnAce && !haveCheckedFirstCard) {
                if (value != Value.Two) {
                    return false;
                }
            } else if (Math.abs(value.ordinal() - lastValue.ordinal()) != 1) {
                return false;
            }

            haveCheckedFirstCard = true;
            lastValue = value;
        }

        return true;
    }

    public int countPairs(IPack hand) {
        int pairCount = 0;
        int[] valueCounts = countMatchingValuesArray(hand);

        for (int cardCount : valueCounts) {
            if (cardCount == 2) {
                ++pairCount;
            }
        }

        return pairCount;
    }

    public int countThrees(IPack hand5Card) {
        int threesCount = 0;
        int[] valueCounts = countMatchingValuesArray(hand5Card);

        for (int cardCount : valueCounts) {
            if (cardCount == 3) {
                ++threesCount;
            }
        }

        return threesCount;
    }

    public int countFours(IPack pack) {
        int foursCount = 0;
        int[] fourCountArray = countMatchingValuesArray(pack);

        for (int count : fourCountArray) {
            if (count == 4) {
                ++foursCount;
            }
        }

        return foursCount;
    }

    public Map<Value, Integer> countMatchingValues(IPack hand) {
        zeroValueCounts();

        for (Card card : hand.getCards()) {
            valueCounts.compute(card.getValue(), (k, v) -> v == null ? 1 : v + 1);
        }

        return valueCounts;
    }

    public int[] countMatchingValuesArray(IPack hand) {
        int[] counts = new int[Value.values().length];

        for (Card card : hand.getCards()) {
            ++counts[card.getValue().ordinal()];
        }

        return counts;
    }

    private void zeroValueCounts() {
        for (Value value : valueCounts.keySet()) {
            valueCounts.put(value, Integer.valueOf(0));
        }
    }

    public Set<Value> getPairValues(IPack hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);
        return getPairValues(valueMap);
    }

    public Set<Value> getThreeOfAKindValues(IPack hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);
        return getThreeOfAKindValues(valueMap);
    }

    public Set<Value> getFourOfAKindValues(IPack hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);
        return getFourOfAKindValues(valueMap);
    }

    public Set<Value> getPairValues(Map<Value, Integer> valueMap) {
        Set<Value> pairValues = valueMap.entrySet().stream().filter(e -> e.getValue() == 2).map(e -> e.getKey()).collect(Collectors.toSet());
        return pairValues;
    }

    public Set<Value> getThreeOfAKindValues(Map<Value, Integer> valueMap) {
        Set<Value> pairValues = valueMap.entrySet().stream().filter(e -> e.getValue() == 3).map(e -> e.getKey()).collect(Collectors.toSet());
        return pairValues;
    }

    public Set<Value> getFourOfAKindValues(Map<Value, Integer> valueMap) {
        Set<Value> pairValues = valueMap.entrySet().stream().filter(e -> e.getValue() == 4).map(e -> e.getKey()).collect(Collectors.toSet());
        return pairValues;
    }


    public Value getHighestSingleCard(IPack hand) {
        Value highestValue = Value.Two;

        Map<Value, Integer> valueMap = countMatchingValues(hand);

        for (Map.Entry<Value, Integer> entry : valueMap.entrySet()) {
            if (entry.getValue() == 1 && entry.getKey().compareTo(highestValue) > 1) {
                highestValue = entry.getKey();
            }
        }

        return highestValue;
    }

    public SortedSet<Value> getSingleCards(IPack hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);

        Set<Value> singleCards = valueMap.entrySet().stream().filter(e -> e.getValue() == 1).map(e -> e.getKey()).collect(Collectors.toSet());
        SortedSet<Value> sortedSingleCards = new TreeSet<>(new ValueComparatorAceHigh());
        sortedSingleCards.addAll(singleCards);

        return sortedSingleCards;
    }

}
