package org.srwcrw.poker.texasholdem.components.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.HandFactory;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.components.IHand5Card;

@Component
public class ConverterHand5Card implements Converter<IHand5Card> {
    @Autowired
    @Qualifier("handFactoryHand5Mutable")
    private HandFactory<? extends IHand5Card> handFactoryHand5;

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
