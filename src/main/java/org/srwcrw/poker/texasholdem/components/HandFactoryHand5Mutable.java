package org.srwcrw.poker.texasholdem.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.Hand5OrdinalFactory;
import org.srwcrw.poker.texasholdem.collections.HandFactory;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("handFactoryHand5Mutable")
public class HandFactoryHand5Mutable implements HandFactory<IHand5Card> {
    @Autowired
    private Hand5OrdinalFactory hand5OrdinalFactory;

    private List<Hand5CardMutable> hand5CardList = new ArrayList<>();

    private int cardIndex = 0;

    @Override
    public Hand5CardMutable create(Card... cardArray) {
        Hand5CardMutable hand5CardMutable;
        if (cardIndex >= hand5CardList.size()) {
            hand5CardMutable = new Hand5CardMutable(hand5OrdinalFactory);

            hand5CardList.add(hand5CardMutable);
        } else {
            hand5CardMutable = hand5CardList.get(cardIndex);
        }

        for (int i = 0; i < cardArray.length; ++i) {
            Card card = cardArray[i];
            hand5CardMutable.setCard(i, card);
        }

        ++cardIndex;

        return hand5CardMutable;
    }

    @Override
    public void markAllHandsAsProcessed() {
        cardIndex = 0;
    }
}