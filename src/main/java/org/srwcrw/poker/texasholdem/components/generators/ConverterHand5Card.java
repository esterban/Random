package org.srwcrw.poker.texasholdem.components.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.components.Hand5Card;
import org.srwcrw.poker.texasholdem.components.HandFactoryHand5;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.components.Card;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterHand5Card implements Converter<Hand5Card> {
    private final List<Card> allHandsLookup = new ArrayList<>();

    @Autowired
    private HandFactoryHand5 handFactoryHand5;

    private ConverterHand5Card() {
    }

    @Override
    public Hand5Card convert(IPack from) {
        return handFactoryHand5.create(
                from.getNthCard(0),
                from.getNthCard(1),
                from.getNthCard(2),
                from.getNthCard(3),
                from.getNthCard(4)
        );
    }
}
