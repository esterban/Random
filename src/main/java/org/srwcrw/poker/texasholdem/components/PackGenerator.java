package org.srwcrw.poker.texasholdem.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.collections.Hand;
import org.srwcrw.poker.texasholdem.entities.*;

import java.util.*;

@Component
public class PackGenerator {
    @Autowired
    private ICardFactory iCardFactory;

    private PackGenerator() {}

    public IPack generateFullPack() {
        SortedSet<Card> cardSet = new TreeSet<>();

        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                Card card = iCardFactory.createCard(suit, value);
                cardSet.add(card);
            }
        }

        return new Hand(cardSet);
    }
}
