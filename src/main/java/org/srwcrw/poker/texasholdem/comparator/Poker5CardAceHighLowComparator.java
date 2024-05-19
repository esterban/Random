package org.srwcrw.poker.texasholdem.comparator;

import org.srwcrw.comparator.SortedSetComparator;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.handclassifer.Poker5CardHandClassifier;
import org.srwcrw.poker.texasholdem.utils.HandUtils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

public class Poker5CardAceHighLowComparator implements Comparator<Hand5Card> {
    private static Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();
    private static HandUtils handUtils = new HandUtils();
    private static SortedSetComparator<Value> valueSortedSetComparator = new SortedSetComparator<>();

    @Override
    public int compare(Hand5Card o1, Hand5Card o2) {
        HandType5Cards handType1 = poker5CardHandClassifier.classify(o1);
        HandType5Cards handType2 = poker5CardHandClassifier.classify(o2);

        if (handType1.compareTo(handType2) != 0) {
            return handType1.compareTo(handType2);
        }

        switch (handType1) {
            case OnePair:
                return compareOnePair(o1, o2);

            case TwoPair:
                return compareTwoPair(o1, o2);

            case ThreeOfAKind:
                return compareThreeOfAKind(o1, o2);

            case Straight:
                return compareStraight(o1, o2);

            case Flush:
                return compareFlush(o1, o2);
        }

        return 0;
    }

    private int compareOnePair(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.OnePair || poker5CardHandClassifier.classify(hand2) != HandType5Cards.OnePair) {
            throw new RuntimeException("Hand is not of one pair, A = " + hand1 + " , B = " + hand2);
        }

        Set<Value> hand1Values = handUtils.getPairValues(hand1);
        Set<Value> hand2Values = handUtils.getPairValues(hand2);

        Value pair1Value = hand1Values.iterator().next();
        Value pair2Value = hand2Values.iterator().next();

        if (pair1Value != pair2Value) {
            return pair1Value.compareTo(pair2Value);
        }

        SortedSet<Value> highestValue1 = handUtils.getSingleCards(hand1);
        SortedSet<Value> highestValue2 = handUtils.getSingleCards(hand2);

        return valueSortedSetComparator.compare(highestValue1, highestValue2);
    }

    private int compareTwoPair(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.TwoPair || poker5CardHandClassifier.classify(hand2) != HandType5Cards.TwoPair) {
            throw new RuntimeException("Hand is not of one pair, A = " + hand1 + " , B = " + hand2);
        }

        Set<Value> hand1Values = handUtils.getPairValues(hand1);
        Set<Value> hand2Values = handUtils.getPairValues(hand2);

        Iterator<Value> hand1Iterator = hand1Values.iterator();
        Iterator<Value> hand2Iterator = hand2Values.iterator();

        Value pair1Value = hand1Iterator.next();
        Value pair2Value = hand2Iterator.next();

        if (pair1Value != pair2Value) {
            return pair1Value.compareTo(pair2Value);
        }

        Value secondPair1Value = hand1Iterator.next();
        Value secondPair2Value = hand2Iterator.next();

        if (secondPair1Value != secondPair2Value) {
            return secondPair1Value.compareTo(secondPair2Value);
        }

        Value highestValue1 = handUtils.getHighestSingleCard(hand1);
        Value highestValue2 = handUtils.getHighestSingleCard(hand2);

        return highestValue1.compareTo(highestValue2);
    }

    private int compareThreeOfAKind(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.ThreeOfAKind || poker5CardHandClassifier.classify(hand2) != HandType5Cards.ThreeOfAKind) {
            throw new RuntimeException("Hand is not of three of a kind, A = " + hand1 + " , B = " + hand2);
        }

        Set<Value> hand1Values = handUtils.getThreeOfKindValues(hand1);
        Set<Value> hand2Values = handUtils.getThreeOfKindValues(hand2);

        Iterator<Value> hand1Iterator = hand1Values.iterator();
        Iterator<Value> hand2Iterator = hand2Values.iterator();

        Value threeOfAKindValue1 = hand1Iterator.next();
        Value threeOfAKindValue2 = hand2Iterator.next();

        if (threeOfAKindValue1 != threeOfAKindValue2) {
            return threeOfAKindValue1.compareTo(threeOfAKindValue2);
        }

        SortedSet<Value> highCardValues1 = handUtils.getSingleCards(hand1);
        SortedSet<Value> highCardValues2 = handUtils.getSingleCards(hand2);

        return valueSortedSetComparator.compare(highCardValues1, highCardValues2);
    }

    private int compareStraight(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.Straight || poker5CardHandClassifier.classify(hand2) != HandType5Cards.Straight) {
            throw new RuntimeException("Hand is not of three of a kind, A = " + hand1 + " , B = " + hand2);
        }

        SortedSet<Value> hand1Values = handUtils.getSingleCards(hand1);
        Iterator<Value> hand1Iterator = hand1Values.iterator();
        hand1Iterator.next();

        SortedSet<Value> hand2Values = handUtils.getSingleCards(hand2);
        Iterator<Value> hand2Iterator = hand2Values.iterator();
        hand2Iterator.next();

        return hand1Iterator.next().compareTo(hand2Iterator.next());
    }

    private int compareFlush(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.Flush || poker5CardHandClassifier.classify(hand2) != HandType5Cards.Flush) {
            throw new RuntimeException("Hand is not of three of a kind, A = " + hand1 + " , B = " + hand2);
        }

        SortedSet<Value> hand1Values = handUtils.getSingleCards(hand1);
        SortedSet<Value> hand2Values = handUtils.getSingleCards(hand2);

        return valueSortedSetComparator.compare(hand1Values, hand2Values);
    }

    private int compareFullHouse(Hand5Card hand1, Hand5Card hand2) {

        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.FullHouse || poker5CardHandClassifier.classify(hand2) != HandType5Cards.FullHouse) {
            throw new RuntimeException("Hand is not of three of a kind, A = " + hand1 + " , B = " + hand2);
        }

        SortedSet<Value> hand1Values = handUtils.getSingleCards(hand1);

        SortedSet<Value> hand2Values = handUtils.getSingleCards(hand2);

        return valueSortedSetComparator.compare(hand1Values, hand2Values);
    }
}
