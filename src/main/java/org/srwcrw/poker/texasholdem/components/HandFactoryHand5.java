package org.srwcrw.poker.texasholdem.components;

import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.HandFactory;

@Component
public class HandFactoryHand5 implements HandFactory<IHand5Card> {
    @Override
    public IHand5Card create(Card... cardArray) {
        Hand5Card hand5Card = new Hand5Card(cardArray[0], cardArray[1], cardArray[2], cardArray[3], cardArray[4]);
        return hand5Card;
    }
}
