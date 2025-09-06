package org.srwcrw.poker.texasholdem.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.Hand5CardMutable;
import org.srwcrw.poker.texasholdem.components.IHand5Card;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.*;
import java.util.stream.Collectors;

public class HandUtils implements IHandUtils {
    private final Map<Value, Integer> valueCounts = new HashMap<>();

    @Override
    public Value[] getValueSetSorted(IPack hand, Comparator<Value> valueComparator) {
//        Supplier<SortedSet<Value>> treeSetSupplier = () -> new TreeSet<>(valueComparator);
//
//        @SuppressWarnings("UnnecessaryLocalVariable")
//        SortedSet<Value> values = IntStream.range(0, 5).boxed().map(e -> hand.getNthCard(e)).map(e -> e.getValue()).collect(Collectors.toCollection(treeSetSupplier));

//        SortedSet<Value> values = new TreeSet<>(valueComparator);

        Value[] values = new Value[5];

        for (int index = 0; index < 5; ++index) {
//            values.add(hand.getNthCard(index).getValue());
            values[index] = hand.getNthCard(index).getValue();
        }

        Arrays.sort(values, valueComparator);

        return values;
    }

    public boolean areValuesConsecutive(Value[] values) {
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

    public boolean areValuesConsecutive(IHand5Card hand5Card) {
//        SortedSet<Value> sortedSet = IntStream.range(0, 5).boxed()
//                .map(e -> hand5Card.getNthCard(e))
//                .map(e -> e.getValue())
//                .collect(Collectors.toCollection(TreeSet::new));

//        return areValuesConsecutive(sortedSet);

        Value[] valueArray = new Value[5];

        for (int index = 0; index < 5; index++) {
            valueArray[index] = hand5Card.getCardsArray()[index].getValue();
        }

        return areValuesConsecutive(valueArray);
    }

    public boolean areValuesConsecutiveAceHigh(IHand5Card hand5Card) {
//        SortedSet<Value> valueSortedSet = getValueSetSorted(hand5Card, new ValueComparatorAceHigh());
        Value[] valueSortedSet = getValueSetSorted(hand5Card, new ValueComparatorAceHigh());

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

    public int countPairs(IHand5Card hand) {
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

//    public int countThrees(IHand5Card hand5Card) {
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

    public int countThrees(IHand5Card hand5Card) {
        Value value1 = hand5Card.getCardsArray()[0].getValue();
        Value value2 = hand5Card.getCardsArray()[1].getValue();
        Value value3 = hand5Card.getCardsArray()[2].getValue();
        Value value4 = hand5Card.getCardsArray()[3].getValue();
        Value value5 = hand5Card.getCardsArray()[4].getValue();

        if (value1.equals(value2) && value1.equals(value3) && value1.equals(value4)) {
            return 0;
        }

        if (value2.equals(value3) && value3.equals(value4) && value4.equals(value5)) {
            return 0;
        }

        if (value1.equals(value2)) {
            if (value2.equals(value3)) {
                return 1;
            } else if (value2.equals(value4)) {
                return 1;
            } else if (value2.equals(value5)) {
                return 1;
            }
        }

        if (value1.equals(value3)) {
            if (value1.equals(value4)) {
                return 1;
            } else if (value1.equals(value5)) {
                return 1;
            }
        }

        if (value1.equals(value4) && value1.equals(value5)) {
            return 1;
        }

        if (value2.equals(value3) && (value3.equals(value4) || value3.equals(value5))) {
            return 1;
        }

        if (value3.equals(value4) && value4.equals(value5)) {
            return 1;
        }

        return 0;
    }

    // SWright 2024-12-29 Note : The implementation of countThrees below DOES allocate memory on heap, not sure why
    // as the allocated memory doesn't escape
//    public int countThrees(IHand5Card hand5Card) {
//        Card[] cardArray = hand5Card.getCardsArray();
//
//        int[] valueCounts = new int[Value.values().length];
//
//        for (Card card : cardArray) {
//            ++valueCounts[card.getValue().ordinal()];
//        }
//
//        for (int valueCount : valueCounts) {
//            if (valueCount == 3) {
//                return 1;
//            }
//        }
//
//        return 0;
//    }


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

//    public int countFours(IHand5Card pack) {
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

    public int countFours(IHand5Card pack) {
        Value firstValue = pack.getCardsArray()[0].getValue();
        int firstValueCount = 1;
        Value nextValue = null;
        int nextValueCount = 0;

        for (int index = 1; index < 5; ++index) {
            Value value = pack.getCardsArray()[index].getValue();

            if (!firstValue.equals(value)) {
                if (nextValue == null) {
                    nextValue = value;
                    ++nextValueCount;
                }
            } else if (value.equals(nextValue)) {
                ++nextValueCount;
            } else {
                ++firstValueCount;
            }
        }

        return (firstValueCount == 4 || nextValueCount == 4) ? 1 : 0;
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

    public Map<Value, Integer> countMatchingValues(IHand5Card hand) {
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

    public int[] countMatchingValuesArray(IHand5Card hand) {
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

//    private int[] createValuesArray(IHand5Card hand5Card) {
//        int[] counts = new int[Value.values().length];
//    }


    private void zeroValueCounts() {
        valueCounts.replaceAll((k, v) -> 0);
    }

    public Value[] getPairValues(IPack hand) {
        int[] valueCounts = countMatchingValuesArray(hand);
        return getPairValuesArray(valueCounts);
    }

    public Value[] getPairValues(IHand5Card hand) {
        int[] valueCounts = countMatchingValuesArray(hand);
        return getPairValuesArray(valueCounts, 5);
    }

    public Set<Value> getThreeOfAKindValues(IHand5Card hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);
        return getThreeOfAKindValues(valueMap);
    }

    public Set<Value> getFourOfAKindValues(IHand5Card hand) {
        Map<Value, Integer> valueMap = countMatchingValues(hand);
        return getFourOfAKindValues(valueMap);
    }

    public Value[] getPairValuesArray(int[] valueCounts) {
        return getPairValuesArray(valueCounts, Value.values().length);
    }

    public Value[] getPairValuesArray(int[] valueCounts, int numberOfCards) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        Value[] pairValues = new Value[numberOfCards];
        int pairIndex = 0;

        int valueIndex = 0;

        for (int count : valueCounts) {
            if (count == 2) {
                Value cardValue = Value.values()[valueIndex];
                pairValues[pairIndex] = cardValue;
                ++pairIndex;
            }

            ++valueIndex;
        }

        Arrays.sort(pairValues, Comparator.nullsLast(Comparator.reverseOrder()));

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

    public Value getHighestSingleCardAceHigh(IHand5Card hand) {
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

    public Value[] getSingleCards(IHand5Card hand) {
        Value[] singleValues = new Value[5];
        Value[] nonSingleValues = new Value[5];

        int singleIndex = 0;
        int nonSingleIndex = 0;

        for (int cardIndex = 0; cardIndex < 5; ++cardIndex) {
            Card card = hand.getNthCard(cardIndex);

            if (!ArrayUtils.contains(singleValues, card.getValue())) {
                if (!ArrayUtils.contains(nonSingleValues, card.getValue())) {
                    singleValues[singleIndex] = card.getValue();
                    ++singleIndex;
                }
            } else {
                int findSingleIndex = ArrayUtils.indexOf(singleValues, card.getValue());
                singleValues[findSingleIndex] = null;

                nonSingleValues[nonSingleIndex] = card.getValue();
            }
        }

        Arrays.sort(singleValues, Comparator.nullsLast(Comparator.reverseOrder()));

        return singleValues;
    }

    public static boolean areEqual(IHand5Card hand5Card, Hand5CardMutable hand5CardMutable) {
        return Arrays.equals(hand5Card.getCardsArray(), hand5CardMutable.getCardsArray());
    }

    public static boolean areEqual(Hand5CardMutable hand5CardMutable, IHand5Card hand5Card) {
        return Arrays.equals(hand5Card.getCardsArray(), hand5CardMutable.getCardsArray());
    }

}
