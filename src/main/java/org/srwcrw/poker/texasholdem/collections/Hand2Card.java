package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.components.Card;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Hand2Card implements IPack {
    private final CollectionUtils collectionUtils = new CollectionUtils();

    private final SortedSet<Card> cards;

    public Hand2Card(SortedSet<Card> cards) {
        if (cards.size() != 2) {
            throw  new RuntimeException("Hand5Card can only be constructed from 5 cards");
        }

        this.cards = cards;
    }

    public Hand2Card(Card card1, Card card2) {
        this.cards = new TreeSet<>(List.of(card1, card2));
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
    public List<Card> getCardListAtRandom(int cardCount) {
        return collectionUtils.generateCardListFromPack(this, cardCount);
    }

    @Override
    public IPack removeCard(Card card) {
        throw new RuntimeException("Immutable hand cannot change");
    }

    @Override
    public SortedSet<Card> getCards() {
        return Collections.unmodifiableSortedSet(cards);
    }

    @Override
    public String toString() {
        return "Hand2Card{" +
                "cards=" + cards +
                '}';
    }

    @Override
    public Card getNthCard(int index) {
        if (index >= 2) {
            throw new RuntimeException("index out of bounds " + index);
        }

        Iterator<Card> cardIterator = cards.iterator();

        if (index == 0) {
            return cardIterator.next();
        }

        cardIterator.next();

        return cardIterator.next();
    }

    @Override
    public int getSize() {
        return cards.size();
    }
}
