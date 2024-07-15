package org.srwcrw.poker.texasholdem.collections;

import org.apache.commons.collections4.IteratorUtils;
import org.srwcrw.poker.texasholdem.components.Card;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Hand implements IPack {

        private final SortedSet<Card> cards;
//    private final Card card1;
//    private final Card card2;
//    private final Card card3;
//    private final Card card4;
//    private final Card card5;

    private final CollectionUtils collectionUtils = new CollectionUtils();

    public Hand(SortedSet<Card> cards) {
        this.cards = cards;
    }

//    public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
//        this.card1 = card1;
//        this.card2 = card2;
//        this.card3 = card3;
//        this.card4 = card4;
//        this.card5 = card5;
//    }

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
        cards.remove(card);
        return this;
    }

    @Override
    public SortedSet<Card> getCards() {
        return Collections.unmodifiableSortedSet(cards);
    }

    @SuppressWarnings("unused")
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
        Hand hand = (Hand) o;
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

    @Override
    public Card getNthCard(int index) {
        if (index >= cards.size()) {
            throw new RuntimeException("size = " + cards.size() +" , index out of bounds " + index);
        }

        return IteratorUtils.get(cards.iterator(), index);
    }
}
