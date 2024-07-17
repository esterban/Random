package org.srwcrw.poker.texasholdem.components;

import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

public class CardOrdinalUtils {
    Suit ordinalToSuit(CardOrdinal cardOrdinal) {
        Suit suit = Suit.values()[(int) (cardOrdinal.getValue() / 13)];
        return suit;
    }

    Value ordinalToValue(CardOrdinal cardOrdinal) {
        Value value = Value.values()[(int) (cardOrdinal.getValue() % 13)];
        return value;
    }
}
