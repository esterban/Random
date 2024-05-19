package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.entities.Card;

import java.util.SortedSet;

public interface IPack {
    Card getCardAtRandom();
    IPack removeCard(Card card);
    SortedSet<Card> getCards();
}
