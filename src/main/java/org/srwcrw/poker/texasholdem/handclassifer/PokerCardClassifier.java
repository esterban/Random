package org.srwcrw.poker.texasholdem.handclassifer;

import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;

public interface PokerCardClassifier<T extends IPack> {

    // TODO 2024-05-15 swright create a HandType7Cards
    HandType5Cards classify(T hand7Card);
}
