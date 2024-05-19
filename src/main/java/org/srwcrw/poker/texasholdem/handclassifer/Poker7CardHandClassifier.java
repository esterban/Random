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

public class Poker7CardHandClassifier implements PokerCardClassifier<Hand7Card> {
    private HandUtils handUtils = new HandUtils();

    // TODO 2024-05-15 swright create a HandType7Cards
    public HandType5Cards classify(Hand7Card hand7Card) {
        int pairsCount = handUtils.countPairs(hand7Card);
        int tripleCount = countThrees(hand7Card);
        int fourCount = countFours(hand7Card);
        boolean isFlush = isFlush(hand7Card);

        if (pairsCount == 0 && tripleCount == 0 && fourCount == 0 && !isFlush) {
            return HandType5Cards.HighestCard;
        }

        if (pairsCount == 1) {
            return HandType5Cards.OnePair;
        }

        return null;
    }

    private int countThrees(Hand7Card hand7Card) {
        int threesCount = 0;

        Map<Value, Integer> valueCounts = new HashMap<>();

        for (Card card : hand7Card.getCards()) {
            valueCounts.compute(card.getValue(), (k, v) -> v == null ? 1 : v + 1);
        }

        for (Map.Entry<Value, Integer> cardCount : valueCounts.entrySet()) {
            if (cardCount.getValue() == 3) {
                ++threesCount;
            }
        }

        return threesCount;
    }

    private int countFours(Hand7Card hand7Card) {
        int foursCount = 0;

        Map<Value, Integer> valueCounts = new HashMap<>();

        for (Card card : hand7Card.getCards()) {
            valueCounts.compute(card.getValue(), (k, v) -> v == null ? 1 : v + 1);
        }

        for (Map.Entry<Value, Integer> cardCount : valueCounts.entrySet()) {
            if (cardCount.getValue() == 4) {
                ++foursCount;
            }
        }

        return foursCount;
    }

    private boolean isFlush(Hand7Card hand5Card) {
        Map<Suit, Integer> uniqueSuits = new HashMap<>();

        for (Card card : hand5Card.getCards()) {
            uniqueSuits.compute(card.getSuit(), (k, v) -> v == null ? 1 : v + 1);
        }

        return uniqueSuits.entrySet().stream().filter(e -> e.getValue() >= 5).count() > 0;
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
