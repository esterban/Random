package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.collections.Hand;
import org.srwcrw.poker.texasholdem.entities.Card;

import java.util.AbstractMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class HandGenerator implements IHandGenerator {
    @Override
    public AbstractMap.SimpleEntry<IPack, IPack> generateHandAndRemoveImmutable(IPack pack, int numberOfCards) {
        IPack newPack = new Hand(pack.getCards());

        SortedSet<Card> handCardSet = new TreeSet<>();

        for (int cardCounter = 1; cardCounter <= numberOfCards; ++cardCounter) {
            Card card = newPack.getCardAtRandom();
            handCardSet.add(card);
            newPack = newPack.removeCard(card);
        }

        return new AbstractMap.SimpleEntry<>(newPack, new Hand(handCardSet));
    }

//    @Override
//    public IPack generateFullPack() {
//        return null;
//    }
}
