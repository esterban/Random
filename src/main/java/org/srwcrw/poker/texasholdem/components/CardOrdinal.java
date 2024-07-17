package org.srwcrw.poker.texasholdem.components;

import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

public class CardOrdinal {
    private final long value;

    public CardOrdinal(Suit suit, Value value) {
        this.value = (long)suit.ordinal() * 13l + (long)value.ordinal();
    }

    public long getValue() {
        return value;
    }
}
