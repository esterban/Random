package org.srwcrw.poker.texasholdem.entities;

import org.srwcrw.poker.texasholdem.components.Card;

public interface ICardFactory {
    Card createCard(Suit suit, Value value);
}
