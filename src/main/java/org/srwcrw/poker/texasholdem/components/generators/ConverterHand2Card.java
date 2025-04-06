package org.srwcrw.poker.texasholdem.components.generators;

import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.IPack;

public class ConverterHand2Card implements Converter<Hand2Card> {
    @Override
    public Hand2Card convert(IPack from) {
        return new Hand2Card(from.getCards());
    }
}
