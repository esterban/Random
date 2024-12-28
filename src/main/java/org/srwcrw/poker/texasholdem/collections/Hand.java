package org.srwcrw.poker.texasholdem.collections;

import org.apache.commons.collections4.IteratorUtils;
import org.srwcrw.poker.texasholdem.components.Card;

import java.util.*;

public final class Hand implements IPack {

//    private static final Random random = new Random(0L);
    private static final Random random = new Random();

    private final SortedSet<Card> cards;

    private final CollectionUtils collectionUtils = new CollectionUtils();

    public Hand(SortedSet<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Card getCardAtRandom() {
        if (cards == null || cards.isEmpty()) {
            return null;
        }

        int randomIndex = random.nextInt(cards.size());

        return getNthCard(randomIndex);
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

    @Override
    public Card[] getCardsArray() {
        throw new RuntimeException("Not implemented");
//        return new Card[0];
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

    @Override
    public int getSize() {
        return cards.size();
    }
}
