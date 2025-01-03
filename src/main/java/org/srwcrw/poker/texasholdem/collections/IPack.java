package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.components.Card;

import java.util.List;
import java.util.SortedSet;

public interface IPack {
    Card getCardAtRandom();

    List<Card> getCardListAtRandom(int cardCount);

    IPack removeCard(Card card);

    SortedSet<Card> getCards();
    Card[] getCardsArray();

    Card getNthCard(int index);

    int getSize();
}
