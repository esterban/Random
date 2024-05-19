package org.srwcrw.poker.texasholdem.entities;

public enum HandType5Cards {
    HighestCard(1),
    OnePair(2),
    TwoPair(3),
    ThreeOfAKind(4),
    Straight(5),
    Flush(6),
    FullHouse(7),
    FourOfAKind(8),
    StraightFlush(9),
    RoyalFlush(10);

    private int ranking;

    HandType5Cards(int ranking) {
        this.ranking = ranking;
    }
}
