package org.srwcrw.poker.texasholdem.components;

import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.Objects;

public final class Card implements Comparable<Card> {
    private final Suit suit;
    private final Value value;
    private final CardOrdinal cardOrdinal;

    Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;

        cardOrdinal = new CardOrdinal(suit, value);
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }

    @Override
    public int compareTo(Card o) {
        if (this.value.compareTo(o.getValue()) != 0) {
            return this.value.compareTo(o.getValue());
        }

        return this.suit.compareTo(o.getSuit());
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }

    public CardOrdinal getCardOrdinal() {
        return cardOrdinal;
    }
}
