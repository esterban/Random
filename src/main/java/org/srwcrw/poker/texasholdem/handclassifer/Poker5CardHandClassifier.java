package org.srwcrw.poker.texasholdem.handclassifer;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.Hand7Card;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceHigh;
import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceLow;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.utils.HandUtils;

import java.util.*;

public class Poker5CardHandClassifier implements PokerCardClassifier<Hand5Card> {
    private HandUtils handUtils = new HandUtils();

    public HandType5Cards classify(Hand5Card hand5Card) {
        int pairsCount = handUtils.countPairs(hand5Card);
        int threesCount = countThrees(hand5Card);
        int foursCount = countFours(hand5Card);
        boolean isStraight = isStraight(hand5Card);
        boolean isFlush = isFlush(hand5Card);

        if (isFlush && isStraight) {
            Set<Value> handValues = handUtils.getValueSetSorted(hand5Card, new ValueComparatorAceHigh());

            if (handValues.contains(Value.Ace) && handValues.contains(Value.King)) {
                return HandType5Cards.RoyalFlush;
            }

            return HandType5Cards.StraightFlush;
        }

        if (foursCount == 1) {
            return HandType5Cards.FourOfAKind;
        }

        if (pairsCount == 1 && threesCount == 1) {
            return HandType5Cards.FullHouse;
        }

        if (isFlush) {
            return HandType5Cards.Flush;
        }

        if (isStraight) {
            return HandType5Cards.Straight;
        }

        if (threesCount == 1) {
            return HandType5Cards.ThreeOfAKind;
        }

        if (pairsCount == 2) {
            return HandType5Cards.TwoPair;
        }

        if (pairsCount == 1) {
            return HandType5Cards.OnePair;
        }

        return HandType5Cards.HighestCard;
    }

    private int countThrees(Hand5Card hand5Card) {
        int threesCount = 0;

        Map<Value, Integer> valueCounts = new HashMap<>();

        for (Card card : hand5Card.getCards()) {
            valueCounts.compute(card.getValue(), (k, v) -> v == null ? 1 : v + 1);
        }

        for (Map.Entry<Value, Integer> cardCount : valueCounts.entrySet()) {
            if (cardCount.getValue() == 3) {
                ++threesCount;
            }
        }

        return threesCount;
    }

    private int countFours(Hand5Card hand5Card) {
        int foursCount = 0;

        Map<Value, Integer> valueCounts = new HashMap<>();

        for (Card card : hand5Card.getCards()) {
            valueCounts.compute(card.getValue(), (k, v) -> v == null ? 1 : v + 1);
        }

        for (Map.Entry<Value, Integer> cardCount : valueCounts.entrySet()) {
            if (cardCount.getValue() == 4) {
                ++foursCount;
            }
        }

        return foursCount;
    }

    private boolean isFlush(Hand5Card hand5Card) {
        Set<Suit> uniqueSuits = new HashSet<>();

        for (Card card : hand5Card.getCards()) {
            uniqueSuits.add(card.getSuit());
        }

        return uniqueSuits.size() == 1;
    }

    private boolean isStraight(Hand5Card hand5Card) {
        SortedSet<Value> cards = handUtils.getValueSetSorted(hand5Card, new ValueComparatorAceLow());

        if (cards.size() < 5) {
            return false;
        }

        if (handUtils.areValuesConsecutive(cards)) {
            return true;
        }

        cards = handUtils.getValueSetSorted(hand5Card, new ValueComparatorAceHigh());

        if (handUtils.areValuesConsecutive(cards)) {
            return true;
        }

        return false;
    }


}
