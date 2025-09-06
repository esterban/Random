package org.srwcrw.poker.texasholdem.handclassifer;

import org.srwcrw.poker.texasholdem.comparator.ValueComparatorAceLow;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.IHand5Card;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.utils.HandUtils;

public class Poker5CardHandClassifier implements PokerCardClassifier<IHand5Card> {
    private final HandUtils handUtils = new HandUtils();

    public HandType5Cards classify(IHand5Card hand5Card) {
        int pairsCount = handUtils.countPairs(hand5Card);
        int threesCount = handUtils.countThrees(hand5Card);
        int foursCount = handUtils.countFours(hand5Card);
        boolean isStraight = isStraight(hand5Card);
        boolean isFlush = isFlush(hand5Card);

        if (isFlush && isStraight) {
            boolean hasAce = false;
            boolean hasKing = false;

            for (int index = 0; index < 5; ++index) {
                Card card = hand5Card.getNthCard(index);

                if (card.getValue().equals(Value.Ace)) {
                    hasAce = true;
                } else if (card.getValue().equals(Value.King)) {
                    hasKing = true;
                }
            }

            return hasAce && hasKing ? HandType5Cards.RoyalFlush : HandType5Cards.StraightFlush;
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

    private boolean isFlush(IHand5Card hand5Card) {
        Suit firstSuit = null;

        for (int index =0; index < 5; ++index) {
            Card card = hand5Card.getNthCard(index);

            if (firstSuit == null) {
                firstSuit = card.getSuit();
            } else {
                if (firstSuit != card.getSuit()) {
                    return false;
                }


            }
        }

        return true;
    }

    private boolean isStraight(IHand5Card hand5Card) {
        Value[] cards = handUtils.getValueSetSorted(hand5Card, new ValueComparatorAceLow());

        if (cards.length < 5) {
            return false;
        }

        if (handUtils.areValuesConsecutive(cards)) {
            return true;
        }

        return handUtils.areValuesConsecutiveAceHigh(hand5Card);
    }
}
