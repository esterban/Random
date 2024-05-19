package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.Hand;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;

public class ConverterHand5Card implements Converter<Hand5Card> {

    @Override
    public Hand5Card convert(IPack from) {
        return new Hand5Card(from.getCards());
    }
}
