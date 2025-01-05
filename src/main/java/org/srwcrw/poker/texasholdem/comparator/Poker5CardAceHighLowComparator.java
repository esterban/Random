package org.srwcrw.poker.texasholdem.comparator;

import org.apache.commons.lang3.ArrayUtils;
import org.srwcrw.comparator.SortedSetComparator;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.handclassifer.Poker5CardHandClassifier;
import org.srwcrw.poker.texasholdem.utils.HandUtils;

import java.util.*;

public class Poker5CardAceHighLowComparator implements Comparator<Hand5Card> {
    private static final Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();
    private static final HandUtils handUtils = new HandUtils();
    private static final SortedSetComparator<Value> valueSortedSetComparator = new SortedSetComparator<>();

    @Override
    public int compare(Hand5Card o1, Hand5Card o2) {
        HandType5Cards handType1 = poker5CardHandClassifier.classify(o1);
        HandType5Cards handType2 = poker5CardHandClassifier.classify(o2);

        if (handType1.compareTo(handType2) != 0) {
            return handType1.compareTo(handType2);
        }

        switch (handType1) {
            case HighestCard:
                return compareHighestCard(o1, o2);

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

            case FullHouse:
                return compareFullHouse(o1, o2);

            case FourOfAKind:
                return compareFourOfAKind(o1, o2);

            case StraightFlush:
            case RoyalFlush:
                return compareStraightFlush(o1, o2);

            default:
                throw new RuntimeException("Internal error - Hand5CardsType unrecognised = " + handType1);
        }
    }

    private int compareHighestCard(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.HighestCard || poker5CardHandClassifier.classify(hand2) != HandType5Cards.HighestCard) {
            throw new RuntimeException("Hand is highest card, A = " + hand1 + " , B = " + hand2);
        }

        Value[] highestValue1 = handUtils.getSingleCards(hand1);
        Value[] highestValue2 = handUtils.getSingleCards(hand2);

        return Arrays.compare(highestValue1, highestValue2);
    }

    private int compareOnePair(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.OnePair || poker5CardHandClassifier.classify(hand2) != HandType5Cards.OnePair) {
            throw new RuntimeException("Hand is not of one pair, A = " + hand1 + " , B = " + hand2);
        }

        Value[] hand1Values = handUtils.getPairValues(hand1);
        Value[] hand2Values = handUtils.getPairValues(hand2);

        Value pair1Value = hand1Values[0];
        Value pair2Value = hand2Values[0];

        if (pair1Value != pair2Value) {
            return pair1Value.compareTo(pair2Value);
        }

        Value[] highestValue1 = handUtils.getSingleCards(hand1);
        Value[] highestValue2 = handUtils.getSingleCards(hand2);

        return Arrays.compare(highestValue1, highestValue2);
    }

    private int compareTwoPair(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.TwoPair || poker5CardHandClassifier.classify(hand2) != HandType5Cards.TwoPair) {
            throw new RuntimeException("Hand is not of one pair, A = " + hand1 + " , B = " + hand2);
        }

        Value[] hand1Values = handUtils.getPairValues(hand1);
        Value[] hand2Values = handUtils.getPairValues(hand2);

        Value pair1Value = hand1Values[0];
        Value pair2Value = hand2Values[0];

        if (pair1Value != pair2Value) {
            return pair1Value.compareTo(pair2Value);
        }

        Value secondPair1Value = hand1Values[1];
        Value secondPair2Value = hand2Values[1];

        if (secondPair1Value != secondPair2Value) {
            return secondPair1Value.compareTo(secondPair2Value);
        }

        Value highestValue1 = handUtils.getHighestSingleCardAceHigh(hand1);
        Value highestValue2 = handUtils.getHighestSingleCardAceHigh(hand2);

        return highestValue1.compareTo(highestValue2);
    }

    private int compareThreeOfAKind(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.ThreeOfAKind || poker5CardHandClassifier.classify(hand2) != HandType5Cards.ThreeOfAKind) {
            throw new RuntimeException("Hand is not of three of a kind, A = " + hand1 + " , B = " + hand2);
        }

        Set<Value> hand1Values = handUtils.getThreeOfAKindValues(hand1);
        Set<Value> hand2Values = handUtils.getThreeOfAKindValues(hand2);

        Iterator<Value> hand1Iterator = hand1Values.iterator();
        Iterator<Value> hand2Iterator = hand2Values.iterator();

        Value threeOfAKindValue1 = hand1Iterator.next();
        Value threeOfAKindValue2 = hand2Iterator.next();

        if (threeOfAKindValue1 != threeOfAKindValue2) {
            return threeOfAKindValue1.compareTo(threeOfAKindValue2);
        }

        Value[] highestValue1 = handUtils.getSingleCards(hand1);
        Value[] highestValue2 = handUtils.getSingleCards(hand2);

        return Arrays.compare(highestValue1, highestValue2);
    }

    private int compareStraight(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.Straight || poker5CardHandClassifier.classify(hand2) != HandType5Cards.Straight) {
            throw new RuntimeException("Hand is not a straight, A = " + hand1 + " , B = " + hand2);
        }

        Value[] highestValue1 = handUtils.getSingleCards(hand1);
        Value[] highestValue2 = handUtils.getSingleCards(hand2);

        return highestValue1[1].compareTo(highestValue2[1]);
    }

    private int compareFlush(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.Flush || poker5CardHandClassifier.classify(hand2) != HandType5Cards.Flush) {
            throw new RuntimeException("Hand is not a flush, A = " + hand1 + " , B = " + hand2);
        }

        Value[] highestValue1 = handUtils.getSingleCards(hand1);
        Value[] highestValue2 = handUtils.getSingleCards(hand2);

        return Arrays.compare(highestValue1, highestValue2);
    }

    private int compareFullHouse(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.FullHouse || poker5CardHandClassifier.classify(hand2) != HandType5Cards.FullHouse) {
            throw new RuntimeException("Hand is not a full house, A = " + hand1 + " , B = " + hand2);
        }

        Value[] hand1Values = handUtils.getPairValues(hand1);
        Value[] hand2Values = handUtils.getPairValues(hand2);

        if (ArrayUtils.indexesOf(hand1Values, hand1Values[0]).cardinality() != 1 || ArrayUtils.indexesOf(hand2Values, hand2Values[0]).cardinality() != 1){
            throw new RuntimeException("Internal Error - either hand does not contain a single pair, A = " + hand1 + " , B = " + hand2);
        }

        return hand1Values[0].compareTo(hand2Values[0]);
    }

    private int compareFourOfAKind(Hand5Card hand1, Hand5Card hand2) {
        if (poker5CardHandClassifier.classify(hand1) != HandType5Cards.FourOfAKind || poker5CardHandClassifier.classify(hand2) != HandType5Cards.FourOfAKind) {
            throw new RuntimeException("Hand is not four of a kind, A = " + hand1 + " , B = " + hand2);
        }

        Set<Value> hand1Values = handUtils.getFourOfAKindValues(hand1);
        Set<Value> hand2Values = handUtils.getFourOfAKindValues(hand2);

        if (hand1Values.size() != 1 || hand2Values.size() != 1) {
            throw new RuntimeException("Internal Error - either hand does not contain four of a kind, A = " + hand1 + " , B = " + hand2);
        }

        return hand1Values.iterator().next().compareTo(hand2Values.iterator().next());
    }

    private int compareStraightFlush(Hand5Card hand1, Hand5Card hand2) {
        HandType5Cards hand1Type = poker5CardHandClassifier.classify(hand1);
        HandType5Cards hand2Type = poker5CardHandClassifier.classify(hand2);

        if (!(hand1Type == HandType5Cards.StraightFlush || hand1Type == HandType5Cards.RoyalFlush) ||
                !(hand2Type == HandType5Cards.StraightFlush || hand2Type == HandType5Cards.RoyalFlush)) {
            throw new RuntimeException("Hand is not a straight flush, A = " + hand1 + " , B = " + hand2);
        }

        Value[] highestValue1 = handUtils.getSingleCards(hand1);
        Value[] highestValue2 = handUtils.getSingleCards(hand2);

        return highestValue1[1].compareTo(highestValue2[1]);
    }
}
