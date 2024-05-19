package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.entities.Card;

import java.util.AbstractMap;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public final class Hand7Card implements IPack {
    private final SortedSet<Card> cards;

    public Hand7Card(SortedSet<Card> cards) {
        if (cards.size() != 7) {
            throw  new RuntimeException("Hand7Card can only be constructed from 7 cards");
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

    public AbstractMap.SimpleEntry<Card, IPack> removeRandomCardImmutable() {
        Hand newHand = new Hand(cards);
        Card card = getCardAtRandom();

        newHand.removeCard(card);

        return new AbstractMap.SimpleEntry<>(card, newHand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand7Card hand = (Hand7Card) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
