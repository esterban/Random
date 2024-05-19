package org.srwcrw.poker.texasholdem.utils;

import org.apache.commons.collections4.IteratorUtils;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.entities.Card;

import java.util.*;

public class PokerTexasHoldemUtils {
    public List<Hand5Card> createHand5From3CommunityCards(Hand5Card communityCards, Hand2Card hand2Card) {
        List<Hand5Card> possibleHandList = new ArrayList<>();

        for (int communityIgnoreIndexA = 0; communityIgnoreIndexA < 5; ++communityIgnoreIndexA) {
            for (int communityIgnoreIndexB = communityIgnoreIndexA + 1; communityIgnoreIndexB < 5; ++communityIgnoreIndexB) {
                SortedSet<Card> permutationCardSet = new TreeSet<>();

                Iterator<Card> hand2CardIterator = hand2Card.getCards().iterator();

                permutationCardSet.add(hand2CardIterator.next());
                permutationCardSet.add(hand2CardIterator.next());

                for (int communityCardIndex = 0; communityCardIndex < 5; ++communityCardIndex) {
                    if (communityIgnoreIndexA == communityCardIndex || communityIgnoreIndexB == communityCardIndex) {
                        continue;
                    }

                    Iterator<Card> communityCardIterator = communityCards.getCards().iterator();
                    Card cardToAdd = IteratorUtils.get(communityCardIterator, communityCardIndex);
                    permutationCardSet.add(cardToAdd);
                }

                Hand5Card permutationHand = new Hand5Card(permutationCardSet);
                possibleHandList.add(permutationHand);
            }
        }

        return possibleHandList;
    }

}
