package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.entities.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CollectionUtils {

    public List<Card> generateCardListFromPack(IPack cards, int cardCount) {
        if (cards == null || cards.getCards().size() < cardCount) {
            return null;
        }

        List<Card> cardList = new ArrayList<>();

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        Set<Integer> cardIndexSet = new HashSet<>();
        int cardFoundCount = 0;

        while (cardFoundCount < cardCount) {
            int randomIndex = threadLocalRandom.nextInt(cards.getCards().size());

            cardIndexSet.add(randomIndex);
            cardFoundCount = cardIndexSet.size();
        }

        int counter = 0;

        for (Card card : cards.getCards()) {
            if (cardIndexSet.contains(counter)) {
                cardList.add(card);
            }

            ++counter;
        }

        return cardList;
    }
}
