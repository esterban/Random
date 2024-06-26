package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.collections.Hand;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.*;

public class PackGenerator {
    public IPack generateFullPack() {
        SortedSet<Card> cardSet = new TreeSet<>();

        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                Card card = new Card(suit, value);
                cardSet.add(card);
            }
        }

        return new Hand(cardSet);
    }
}
