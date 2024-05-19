package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.Hand7Card;
import org.srwcrw.poker.texasholdem.collections.IPack;

public class ConverterHand7Card implements Converter<Hand7Card> {
    @Override
    public Hand7Card convert(IPack from) {
        return new Hand7Card(from.getCards());
    }
}
