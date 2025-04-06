package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.components.CardOrdinal;
import org.srwcrw.poker.texasholdem.components.Hand5Card;

import java.util.Arrays;
import java.util.Comparator;

public final class Hand5Ordinal implements IHandOrdinal<Hand5Card> {
    private final long ordinal;


//    Hand5Ordinal(Card... cardArray) {
//        CardOrdinal[] cardOrdinalArray = new CardOrdinal[5];
//
//        for (int index = 0; index < 5; ++index) {
//            cardOrdinalArray[index] = cardArray[index].getCardOrdinal();
//        }
//
//        Arrays.sort(cardOrdinalArray);
//
//        ordinal = 0;
//    }

    public Hand5Ordinal(CardOrdinal... cardOrdinalArray) {
        Comparator<CardOrdinal> cardOrdinalComparator = new Comparator<CardOrdinal>() {
            @Override
            public int compare(CardOrdinal o1, CardOrdinal o2) {
                return 0;
            }
        };
        Arrays.sort(cardOrdinalArray, cardOrdinalComparator);

        ordinal = 0;
    }

    @Override
    public long getOrdinal() {
        return ordinal;
    }
}
