package org.srwcrw.poker.texasholdem.components.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.components.*;
import org.srwcrw.poker.texasholdem.collections.IPack;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterHand5Card implements Converter<IHand5Card> {
    @Autowired
    private HandFactoryHand5 handFactoryHand5;

    private ConverterHand5Card() {
    }

    @Override
    public IHand5Card convert(IPack from) {
        return handFactoryHand5.create(
                from.getNthCard(0),
                from.getNthCard(1),
                from.getNthCard(2),
                from.getNthCard(3),
                from.getNthCard(4)
        );
    }
}
