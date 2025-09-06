package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.components.Card;

public interface HandFactory<T extends IPack> {
    T create(Card... cards);
    void markAllHandsAsProcessed();
}
