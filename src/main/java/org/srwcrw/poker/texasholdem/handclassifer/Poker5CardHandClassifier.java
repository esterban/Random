package org.srwcrw.poker.texasholdem.handclassifer;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.utils.HandUtils;

public class Poker5CardHandClassifier implements PokerCardClassifier<Hand5Card> {
    private final HandUtils handUtils = new HandUtils();

    public HandType5Cards classify(Hand5Card hand5Card) {
        int pairsCount = handUtils.countPairs(hand5Card);
        int threesCount = handUtils.countThrees(hand5Card);
        int foursCount = handUtils.countFours(hand5Card);
        boolean isStraight = isStraight(hand5Card);
        boolean isFlush = isFlush(hand5Card);

        if (isFlush && isStraight) {
//            Set<Value> handValues = handUtils.getValueSetSorted(hand5Card, new ValueComparatorAceHigh());
//
//            if (handValues.contains(Value.Ace) && handValues.contains(Value.King)) {
//                return HandType5Cards.RoyalFlush;
//            }

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

    private boolean isFlush(Hand5Card hand5Card) {
//        Set<Suit> uniqueSuits = new HashSet<>();

        Suit firstSuit = null;

        for (int index =0; index < 5; ++index) {
            Card card = hand5Card.getNthCard(index);

//        for (Card card : hand5Card.getCards()) {
            if (firstSuit == null) {
                firstSuit = card.getSuit();
            } else {
                if (firstSuit != card.getSuit()) {
                    return false;
                }


            }
        }

//        return uniqueSuits.size() == 1;
        return true;
    }

    private boolean isStraight(Hand5Card hand5Card) {
//        SortedSet<Value> cards = handUtils.getValueSetSorted(hand5Card, new ValueComparatorAceLow());
//
//        if (cards.size() < 5) {
//            return false;
//        }

        if (handUtils.areValuesConsecutive(hand5Card)) {
            return true;
        }

//        cards = handUtils.getValueSetSorted(hand5Card, new ValueComparatorAceHigh());

        return handUtils.areValuesConsecutiveAceHigh(hand5Card);
    }


}
