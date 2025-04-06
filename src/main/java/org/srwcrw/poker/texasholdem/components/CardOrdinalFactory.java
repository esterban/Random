package org.srwcrw.poker.texasholdem.components;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class CardOrdinalFactory {
    private final CardOrdinal[] cardOrdinals = new CardOrdinal[Suit.values().length * Value.values().length];

    private CardOrdinalFactory() {

    }

    public CardOrdinal create(Suit suit, Value value) {
        int index = suit.ordinal() + Suit.values().length * value.ordinal();

        if (cardOrdinals[index] == null) {
            CardOrdinal cardOrdinal = new CardOrdinal(suit, value);
            cardOrdinals[index] = cardOrdinal;
        }

        return cardOrdinals[index];
    }

    Value cardOrdinalToValue(CardOrdinal cardOrdinal) {
        Value value = Value.values()[(cardOrdinal.getValue() / Suit.values().length)];
        return value;
    }

    Suit cardOrdinalToSuit(CardOrdinal cardOrdinal) {
        Suit suit = Suit.values()[(cardOrdinal.getValue() % Suit.values().length)];
        return suit;
    }
}
