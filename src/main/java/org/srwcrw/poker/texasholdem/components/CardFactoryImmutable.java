package org.srwcrw.poker.texasholdem.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.entities.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class CardFactoryImmutable implements ICardFactory {
    Map<Suit, Map<Value, Card>> cardLookupMap = new HashMap<>();

    private CardFactoryImmutable() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                Card card = new Card(suit, value);
                cardLookupMap.putIfAbsent(suit, new HashMap<>());
                cardLookupMap.get(suit).put(value, card);
            }
        }
    }

    @Override
    public Card createCard(Suit suit, Value value) {
        return cardLookupMap.get(suit).get(value);
    }
}
