package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.Card;

import java.util.ArrayList;
import java.util.List;

public class ConverterHand5Card implements Converter<Hand5Card> {
    private final List<Card> allHandsLookup = new ArrayList<>();

    public ConverterHand5Card() {
//        for (int indexA = 0;)
    }


    @Override
    public Hand5Card convert(IPack from) {
        return new Hand5Card(from.getCards());
    }
}
