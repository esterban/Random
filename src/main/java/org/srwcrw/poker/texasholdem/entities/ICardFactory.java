package org.srwcrw.poker.texasholdem.entities;

public interface ICardFactory {
    Card createCard(Suit suit, Value value);
}
