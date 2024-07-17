package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.components.Card;

import java.util.Arrays;

public class Hand5OrdinalFactoryFast implements Hand5OrdinalFactory {
    @Override
    public Hand5Ordinal create(Card card1, Card card2, Card card3, Card card4, Card card5) {
        long[] ordinalArray = new long[5];
        ordinalArray[0] = card1.getCardOrdinal().getValue();
        ordinalArray[1] = card2.getCardOrdinal().getValue();
        ordinalArray[2] = card3.getCardOrdinal().getValue();
        ordinalArray[3] = card4.getCardOrdinal().getValue();
        ordinalArray[4] = card5.getCardOrdinal().getValue();

        Arrays.sort(ordinalArray);

        long hand5OrdinalLong =
                ordinalArray[0] +
                ordinalArray[1] * (1 << 8) +
                ordinalArray[2] * (1 << 16) +
                ordinalArray[3] * (1 << 24) +
                ordinalArray[4] * (1 << 32);

        Hand5Ordinal hand5Ordinal = new Hand5Ordinal(hand5OrdinalLong);
        return hand5Ordinal;
    }
}
