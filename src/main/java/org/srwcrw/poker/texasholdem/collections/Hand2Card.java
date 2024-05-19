package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.entities.Card;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class Hand2Card implements IPack {
    private final SortedSet<Card> cards;

    public Hand2Card(SortedSet<Card> cards) {
        if (cards.size() != 2) {
            throw  new RuntimeException("Hand5Card can only be constructed from 5 cards");
        }

        this.cards = cards;
    }

    @Override
    public Card getCardAtRandom() {
        if (cards == null || cards.isEmpty()) {
            return null;
        }

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        int randomIndex = threadLocalRandom.nextInt(cards.size());

        int counter = 0;

        for (Card card : cards) {
            if (counter == randomIndex) {
                return card;
            }

            ++counter;
        }

        return cards.iterator().next();
    }

    @Override
    public IPack removeCard(Card card) {
        throw new RuntimeException("Immutable hand cannot change");
    }

    @Override
    public SortedSet<Card> getCards() {
        SortedSet<Card> newTreeSet = new TreeSet<>(cards.comparator());
        newTreeSet.addAll(cards);
        return newTreeSet;
    }

    @Override
    public String toString() {
        return "Hand2Card{" +
                "cards=" + cards +
                '}';
    }
}
