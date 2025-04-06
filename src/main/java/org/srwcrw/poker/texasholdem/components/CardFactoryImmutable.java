package org.srwcrw.poker.texasholdem.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.entities.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class CardFactoryImmutable implements ICardFactory {
    @Autowired
    private CardOrdinalFactory cardOrdinalFactory;

    private Card[] cardLookUp = new Card[52];

    private CardFactoryImmutable() {
    }

    @Override
    public Card createCard(Suit suit, Value value) {
        CardOrdinal cardOrdinal = cardOrdinalFactory.create(suit, value);

        if (cardLookUp[cardOrdinal.getValue()] == null) {
            cardLookUp[cardOrdinal.getValue()] = new Card(suit, value, cardOrdinal);
        }

        return cardLookUp[cardOrdinal.getValue()];
    }
}
