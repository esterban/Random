package org.srwcrw.poker.texasholdem.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.Hand5Ordinal;
import org.srwcrw.poker.texasholdem.collections.Hand5OrdinalFactory;
import org.srwcrw.poker.texasholdem.entities.ICardFactory;

import java.util.Arrays;
import java.util.Comparator;

@Component
public class Hand5OrdinalFactoryFast implements Hand5OrdinalFactory {

    @Autowired
    private ICardFactory cardFactory;

    private long value;

    private Comparator<CardOrdinal> cardOrdinalComparator = new Comparator<CardOrdinal>() {
        @Override
        public int compare(CardOrdinal o1, CardOrdinal o2) {
            return Byte.compare(o1.getValue(), o2.getValue());
        }
    };

    @Override
    public Hand5Ordinal create(Card card1, Card card2, Card card3, Card card4, Card card5) {
        CardOrdinal[] ordinalArray = new CardOrdinal[5];
        ordinalArray[0] = card1.getCardOrdinal();
        ordinalArray[1] = card2.getCardOrdinal();
        ordinalArray[2] = card3.getCardOrdinal();
        ordinalArray[3] = card4.getCardOrdinal();
        ordinalArray[4] = card5.getCardOrdinal();

        Arrays.sort(ordinalArray, cardOrdinalComparator);

        Hand5Ordinal hand5Ordinal = new Hand5Ordinal(ordinalArray);
        return hand5Ordinal;
    }

    private long calculateOrdinalValue(Card card1, Card card2, Card card3, Card card4, Card card5) {
        return 0l;
    }
}
