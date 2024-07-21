package org.srwcrw.poker.texasholdem.utils;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HandUtils implements IHandUtils {
    private final Map<Value, Integer> valueCounts = new HashMap<>();

    @Override
    public SortedSet<Value> getValueSetSorted(IPack hand, Comparator<Value> valueComparator) {
        Supplier<SortedSet<Value>> treeSetSupplier = () -> new TreeSet<>(valueComparator);

        @SuppressWarnings("UnnecessaryLocalVariable")
        SortedSet<Value> values = IntStream.range(0, 5).boxed().map(e -> hand.getNthCard(e)).map(e -> e.getValue()).collect(Collectors.toCollection(treeSetSupplier));
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

    public boolean areValuesConsecutive(Hand5Card hand5Card) {
        SortedSet<Value> sortedSet = IntStream.range(0,5).boxed()
                .map(e -> hand5Card.getNthCard(e))
                .map(e -> e.getValue())
                .collect(Collectors.toCollection(TreeSet::new));

        return areValuesConsecutive(sortedSet);
    }

    public boolean areValuesConsecutiveAceHigh(Hand5Card hand5Card) {
        SortedSet<Value> valueSortedSet = getValueSetSorted(hand5Card, new ValueComparatorAceHigh());

        return areValuesConsecutive(valueSortedSet);
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

    public int countPairs(Hand5Card hand) {
        int pairCount = 0;
        int[] valueCounts = countMatchingValuesArray(hand);

        for (int cardCount : valueCounts) {
            if (cardCount == 2) {
                ++pairCount;
            }
        }

        return pairCount;
    }


//    public int countThrees(IPack hand5Card) {
//        int threesCount = 0;
//        int[] valueCounts = countMatchingValuesArray(hand5Card);
//
//        for (int cardCount : valueCounts) {
//            if (cardCount == 3) {
//                ++threesCount;
//            }
//        }
//
//        return threesCount;
//    }

    public int countThrees(Hand5Card hand5Card) {
        int threesCount = 0;
        int[] valueCounts = countMatchingValuesArray(hand5Card);

        for (int cardCount : valueCounts) {
            if (cardCount == 3) {
                ++threesCount;
            }
        }

        return threesCount;
    }


//    public int countFours(IPack pack) {
//        int foursCount = 0;
//        int[] fourCountArray = countMatchingValuesArray(pack);
//
//        for (int count : fourCountArray) {
//            if (count == 4) {
//                ++foursCount;
//            }
//        }
//
//        return foursCount;
//    }

    public int countFours(Hand5Card pack) {
        int foursCount = 0;
        int[] fourCountArray = countMatchingValuesArray(pack);

        for (int count : fourCountArray) {
            if (count == 4) {
                ++foursCount;
            }
        }

        return foursCount;
    }


//    public Map<Value, Integer> countMatchingValues(IPack hand) {
//        zeroValueCounts();
//
//        for (Card card : hand.getCards()) {
//            valueCounts.compute(card.getValue(), (k, v) -> v == null ? 1 : v + 1);
//        }
//
//        return valueCounts;
//    }

    public Map<Value, Integer> countMatchingValues(Hand5Card hand) {
        zeroValueCounts();

//        for (Card card : hand.getCards()) {
        for (int index = 0; index < 5; ++index) {
            Card card = hand.getNthCard(index);
            valueCounts.compute(card.getValue(), (k, v) -> v == null ? 1 : v + 1);
        }

        return valueCounts;
    }

    public int[] countMatchingValuesArray(IPack hand) {
        int[] counts = new int[Value.values().length];

//        for (Card card : hand.getCards()) {
        for (int index = 0; index < hand.getSize(); ++index) {
            Card card = hand.getNthCard(index);
            ++counts[card.getValue().ordinal()];
        }

        return counts;
    }

    public int[] countMatchingValuesArray(Hand5Card hand) {
        int[] counts = new int[Value.values().length];

//        for (Card card : hand.getCards()) {
//            ++counts[card.getValue().ordinal()];
//        }

        ++counts[hand.getNthCard(0).getValue().ordinal()];
        ++counts[hand.getNthCard(1).getValue().ordinal()];
        ++counts[hand.getNthCard(2).getValue().ordinal()];
        ++counts[hand.getNthCard(3).getValue().ordinal()];
        ++counts[hand.getNthCard(4).getValue().ordinal()];

        return counts;

//        return null;
    }

//    private int[] createValuesArray(Hand5Card hand5Card) {
//        int[] counts = new int[Value.values().length];
//    }


    private void zeroValueCounts() {
        valueCounts.replaceAll((k, v) -> 0);
    }

    public Set<Value> getPairValues(IPack hand) {
//        Map<Value, Integer> valueMap = countMatchingValues(hand);
        int[] valueCounts = countMatchingValuesArray(hand);
        return getPairValuesArray(valueCounts);
    }

    //    public Set<Value> getThreeOfAKindValues(IPack hand) {
    public Set<Value> getThreeOfAKindValues(Hand5Card hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);
        return getThreeOfAKindValues(valueMap);
    }

//    public Set<Value> getFourOfAKindValues(IPack hand) {
//        Map<Value, Integer> valueMap = countMatchingValues(hand);
//        return getFourOfAKindValues(valueMap);
//    }

    public Set<Value> getFourOfAKindValues(Hand5Card hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);
        return getFourOfAKindValues(valueMap);
    }

//    public Set<Value> getPairValues(Map<Value, Integer> valueMap) {
//        @SuppressWarnings("UnnecessaryLocalVariable")
//        var pairValues = valueMap.entrySet().stream().filter(e -> e.getValue() == 2).map(Map.Entry::getKey).collect(Collectors.toSet());
//        return pairValues;
//    }
    public Set<Value> getPairValuesArray(int[] valueCounts) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        var pairValues = IntStream.range(0, 13).boxed()
                .filter(e -> valueCounts[e] == 2)
                .map(e -> Value.values()[e])
                    .collect(Collectors.toCollection(TreeSet::new));

        return pairValues;
    }

    public Set<Value> getThreeOfAKindValues(Map<Value, Integer> valueMap) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        Set<Value> pairValues = valueMap.entrySet().stream()
                .filter(e -> e.getValue() == 3)
                .map(Map.Entry::getKey)
                            .collect(Collectors.toCollection(TreeSet::new));

        return pairValues;
    }

    public Set<Value> getFourOfAKindValues(Map<Value, Integer> valueMap) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        Set<Value> pairValues = valueMap.entrySet().stream()
                .filter(e -> e.getValue() == 4)
                .map(Map.Entry::getKey)
                    .collect(Collectors.toCollection(TreeSet::new));
        return pairValues;
    }


    //    public Value getHighestSingleCardAceHigh(IPack hand) {
    public Value getHighestSingleCardAceHigh(Hand5Card hand) {
        Value highestValue = Value.Two;

        Map<Value, Integer> valueMap = countMatchingValues(hand);

        ValueComparatorAceHigh valueComparatorAceHigh = new ValueComparatorAceHigh();

        for (Map.Entry<Value, Integer> entry : valueMap.entrySet()) {
            if (entry.getValue() == 1 && valueComparatorAceHigh.compare(entry.getKey(), highestValue) > 0) {
                highestValue = entry.getKey();
            }
        }

        return highestValue;
    }

    //    public SortedSet<Value> getSingleCards(IPack hand) {
    public SortedSet<Value> getSingleCards(Hand5Card hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);

        Set<Value> singleCards = valueMap.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                            .collect(Collectors.toCollection(TreeSet::new));

        SortedSet<Value> sortedSingleCards = new TreeSet<>(new ValueComparatorAceHigh());
        sortedSingleCards.addAll(singleCards);

        return sortedSingleCards;
    }

}
