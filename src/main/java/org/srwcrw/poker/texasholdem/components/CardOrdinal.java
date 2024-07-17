package org.srwcrw.poker.texasholdem.components;

import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

public class CardOrdinal {
    private final long value;

    public CardOrdinal(Card card) {
        this(card.getSuit(), card.getValue());
    }

    public CardOrdinal(Suit suit, Value value) {
        this.value = (long)suit.ordinal() * 4l + (long)value.ordinal();
    }
}
