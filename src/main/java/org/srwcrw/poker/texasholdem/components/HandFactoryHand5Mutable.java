package org.srwcrw.poker.texasholdem.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.Hand5OrdinalFactory;
import org.srwcrw.poker.texasholdem.collections.HandFactory;

@Component
public class HandFactoryHand5Mutable implements HandFactory<IHand5Card> {
    @Autowired
    private Hand5OrdinalFactory hand5OrdinalFactory; // Renamed to follow Java conventions

    // Initialize ThreadLocal with a new Hand5CardMutable for each thread
    private ThreadLocal<Hand5CardMutable> hand5CardArrayThreadLocal =
            ThreadLocal.withInitial(() -> new Hand5CardMutable(hand5OrdinalFactory));

    @Override
    public Hand5CardMutable create(Card... cardArray) {
        for (int i = 0; i < cardArray.length; ++i) {
            Card card = cardArray[i];
            hand5CardArrayThreadLocal.get().setCard(i, card);
        }
        return hand5CardArrayThreadLocal.get();
    }
}