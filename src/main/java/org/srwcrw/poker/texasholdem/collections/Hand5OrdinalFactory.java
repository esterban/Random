package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.components.Card;

public interface Hand5OrdinalFactory {
    Hand5Ordinal create(Card card1, Card card2, Card card3, Card card4, Card card5);

    int createIndex(Card card1, Card card2, Card card3, Card card4, Card card5);

}
