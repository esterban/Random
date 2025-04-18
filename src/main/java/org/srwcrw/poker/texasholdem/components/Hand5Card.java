package org.srwcrw.poker.texasholdem.components;

import org.srwcrw.poker.texasholdem.collections.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Hand5Card implements IPack {
    private static final CollectionUtils collectionUtils = new CollectionUtils();

    private Hand5OrdinalFactory hand5OrdinalFactory;

    private final Card card1;
    private final Card card2;
    private final Card card3;
    private final Card card4;
    private final Card card5;
    private final Card[] cardArray = new Card[5];

    private Hand5Ordinal hand5Ordinal;

    public Hand5Card(Hand5OrdinalFactory hand5OrdinalFactory, Card card1, Card card2, Card card3, Card card4, Card card5) {
        this.hand5OrdinalFactory = hand5OrdinalFactory;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.card4 = card4;
        this.card5 = card5;

        cardArray[0] = card1;
        cardArray[1] = card2;
        cardArray[2] = card3;
        cardArray[3] = card4;
        cardArray[4] = card5;
    }

    @Override
    public Card getCardAtRandom() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int randomCounter = threadLocalRandom.nextInt(5) + 1;

        return switch (randomCounter) {
            case 1 -> card1;
            case 2 -> card2;
            case 3 -> card3;
            case 4 -> card4;
            case 5 -> card5;
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

//        SortedSet<Card> cardsCopy = new TreeSet<>(List.of(card1, card2, card3, card4, card5));
//        return Collections.unmodifiableSortedSet(cardsCopy);
    }

    @Override
    public Card[] getCardsArray() {
//        return new Card[0];
        return cardArray;
    }

    @SuppressWarnings("unused")
    public AbstractMap.SimpleEntry<Card, IPack> removeRandomCardImmutable() {
        SortedSet<Card> cardSortedSet = new TreeSet<>(List.of(card1, card2, card3, card4, card5));
        Hand newHand = new Hand(cardSortedSet);
        Card card = getCardAtRandom();

        newHand.removeCard(card);

        return new AbstractMap.SimpleEntry<>(card, newHand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand5Card hand5Card = (Hand5Card) o;

        return Objects.equals(card1, hand5Card.card1) &&
                Objects.equals(card2, hand5Card.card2) &&
                Objects.equals(card3, hand5Card.card3) &&
                Objects.equals(card4, hand5Card.card4) &&
                Objects.equals(card5, hand5Card.card5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card1, card2, card3, card4, card5);
    }

    @Override
    public String toString() {
        return "Hand5Card{" +
                "card1=" + card1 +
                ", card2=" + card2 +
                ", card3=" + card3 +
                ", card4=" + card4 +
                ", card5=" + card5 +
                '}';
    }

    @Override
    public Card getNthCard(int index) {
        return switch (index) {
            case 0 -> card1;
            case 1 -> card2;
            case 2 -> card3;
            case 3 -> card4;
            case 4 -> card5;
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
            hand5Ordinal = hand5OrdinalFactory.create(card1, card2, card3, card4, card5);
        }

        return hand5Ordinal;
    }
}
