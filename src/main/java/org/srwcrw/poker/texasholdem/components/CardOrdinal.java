package org.srwcrw.poker.texasholdem.components;

import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

public final class CardOrdinal {
    private final byte value;

    public CardOrdinal(Suit suit, Value value) {
        this.value = (byte) (suit.ordinal() + value.ordinal() * Suit.values().length);
    }

    public byte getValue() {
        return value;
    }
}
