package org.srwcrw.poker.texasholdem.components;

import org.srwcrw.poker.texasholdem.collections.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public final class Hand5CardMutable implements IHand5Card {
    private static final CollectionUtils collectionUtils = new CollectionUtils();

    private Hand5OrdinalFactory hand5OrdinalFactory;

    private final Card[] cardArray = new Card[5];

    private Hand5Ordinal hand5Ordinal;

    private final AtomicLong constructorCallCount = new AtomicLong(0);

    private final Hand5Card hand5CardView = new Hand5Card(cardArray[0], cardArray[1], cardArray[2], cardArray[3], cardArray[4]);

    public Hand5CardMutable(Hand5OrdinalFactory hand5OrdinalFactory) {
        this.hand5OrdinalFactory = hand5OrdinalFactory;
    }

    public Hand5CardMutable(Hand5OrdinalFactory hand5OrdinalFactory, Card card1, Card card2, Card card3, Card card4, Card card5) {
        this.hand5OrdinalFactory = hand5OrdinalFactory;

        cardArray[0] = card1;
        cardArray[1] = card2;
        cardArray[2] = card3;
        cardArray[3] = card4;
        cardArray[4] = card5;

        constructorCallCount.incrementAndGet();
    }

    public void setCard(int index, Card card) {
        cardArray[index] = card;
    }

    @Override
    public Card getCardAtRandom() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int randomCounter = threadLocalRandom.nextInt(5) + 1;

        return switch (randomCounter) {
            case 1 -> cardArray[0];
            case 2 -> cardArray[1];
            case 3 -> cardArray[2];
            case 4 -> cardArray[3];
            case 5 -> cardArray[4];
            default ->
                    throw new RuntimeException("Should not be reached, should always return a card --- INTERNAL ERROR");
        };
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
        throw new RuntimeException("Not supported");

//        SortedSet<Card> cardsCopy = new TreeSet<>(List.of(cardArray[0], cardArray[1], cardArray[2], cardArray[3], cardArray[4]));
//        return Collections.unmodifiableSortedSet(cardsCopy);
    }

    @Override
    public Card[] getCardsArray() {
//        return new Card[0];
        return cardArray;
    }

    @SuppressWarnings("unused")
    public AbstractMap.SimpleEntry<Card, IPack> removeRandomCardImmutable() {
        SortedSet<Card> cardSortedSet = new TreeSet<>(List.of(cardArray[0], cardArray[1], cardArray[2], cardArray[3], cardArray[4]));
        Hand newHand = new Hand(cardSortedSet);
        Card card = getCardAtRandom();

        newHand.removeCard(card);

        return new AbstractMap.SimpleEntry<>(card, newHand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand5CardMutable hand5Card = (Hand5CardMutable) o;

        return Objects.equals(cardArray[0], hand5Card.cardArray[0]) &&
                Objects.equals(cardArray[1], hand5Card.cardArray[1]) &&
                Objects.equals(cardArray[2], hand5Card.cardArray[2]) &&
                Objects.equals(cardArray[3], hand5Card.cardArray[3]) &&
                Objects.equals(cardArray[4], hand5Card.cardArray[4]);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardArray[0], cardArray[1], cardArray[2], cardArray[3], cardArray[4]);
    }

    @Override
    public String toString() {
        return "Hand5Card{" +
                "cardArray[0]=" + cardArray[0] +
                ", cardArray[1]=" + cardArray[1] +
                ", cardArray[2]=" + cardArray[2] +
                ", cardArray[3]=" + cardArray[3] +
                ", cardArray[4]=" + cardArray[4] +
                '}';
    }

    @Override
    public Card getNthCard(int index) {
        return switch (index) {
            case 0 -> cardArray[0];
            case 1 -> cardArray[1];
            case 2 -> cardArray[2];
            case 3 -> cardArray[3];
            case 4 -> cardArray[4];
            default ->
                    throw new RuntimeException("Should not be reached, should always return a card --- INTERNAL ERROR");
        };
    }

    @Override
    public int getSize() {
        return 5;
    }

    public Hand5Ordinal getHand5Ordinal() {
        if (hand5Ordinal == null) {
            hand5Ordinal = hand5OrdinalFactory.create(cardArray[0], cardArray[1], cardArray[2], cardArray[3], cardArray[4]);
        }

        return hand5Ordinal;
    }

    public Hand5Card getHand5CardView() {
        return hand5CardView;
    }
}
