package org.srwcrw.poker.texasholdem.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.HandFactory;

@Component
public class HandFactoryFast implements HandFactory<Hand5Card> {
    private final Hand5OrdinalFactoryFast hand5OrdinalFactoryFast;

    private Hand5Card[] cardRepository = new Hand5Card[52];

    @Autowired
    public HandFactoryFast(Hand5OrdinalFactoryFast hand5OrdinalFactoryFast) {
        this.hand5OrdinalFactoryFast = hand5OrdinalFactoryFast;
    }

    @Override
    public Hand5Card create(Card... cards) {
        hand5OrdinalFactoryFast.create(cards[0], cards[1], cards[2], cards[3], cards[4]);

        int cardIndex = hand5OrdinalFactoryFast.createIndex(cards[0], cards[1], cards[2], cards[3], cards[4]);

        Hand5Card hand5Card = null;

        if (cardRepository[cardIndex] == null) {
            hand5Card = new Hand5Card(cards[0], cards[1], cards[2], cards[3], cards[4]);
            cardRepository[cardIndex] = hand5Card;
        } else {
            hand5Card = cardRepository[cardIndex];
        }

        return hand5Card;
    }

    @Override
    public void markAllHandsAsProcessed() {

    }
}
