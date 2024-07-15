package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.Hand;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.components.Card;

import java.util.AbstractMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class HandGenerator implements IHandGenerator {

    @Override
    public AbstractMap.SimpleEntry<IPack, IPack> generateHandAndRemoveImmutable(IPack pack, int numberOfCards) {
        IPack newPack = new Hand(new TreeSet<>(pack.getCards()));

        SortedSet<Card> handCardSet = new TreeSet<>();

        for (int cardCounter = 1; cardCounter <= numberOfCards; ++cardCounter) {
            Card card = newPack.getCardAtRandom();
            handCardSet.add(card);
            newPack = newPack.removeCard(card);
        }

        return new AbstractMap.SimpleEntry<>(newPack, new Hand(handCardSet));
    }

    @Override
    public IPack generateHandAndRemoveMutable(IPack pack, int numberOfCards) {
        SortedSet<Card> handCardSet = new TreeSet<>();

        for (int cardCounter = 1; cardCounter <= numberOfCards; ++cardCounter) {
            Card card = pack.getCardAtRandom();
            handCardSet.add(card);
            pack.removeCard(card);
        }

        return new Hand(handCardSet);
    }

    @Override
    public IPack generateHandPackNoModify(IPack pack, int numberOfCards) {
        List<Card> cardList = pack.getCardListAtRandom(numberOfCards);

        return new Hand(new TreeSet<>(cardList));
    }
}
