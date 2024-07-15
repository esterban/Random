package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.components.Card;

import java.util.ArrayList;
import java.util.List;

public class ConverterHand5Card implements Converter<Hand5Card> {
    private final List<Card> allHandsLookup = new ArrayList<>();

    public ConverterHand5Card() {
    }

    @Override
    public Hand5Card convert(IPack from) {
        return new Hand5Card(
                from.getNthCard(0),
                from.getNthCard(1),
                from.getNthCard(2),
                from.getNthCard(3),
                from.getNthCard(4)
                );
    }
}
