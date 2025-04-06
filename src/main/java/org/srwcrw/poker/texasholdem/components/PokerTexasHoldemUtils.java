package org.srwcrw.poker.texasholdem.components;


import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.comparator.Poker5CardAceHighLowComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PokerTexasHoldemUtils {
    private static final Poker5CardAceHighLowComparator poker5CardAceHighLowComparator = new Poker5CardAceHighLowComparator();

    @Autowired
    private HandFactoryHand5 handFactoryHand5;

    private PokerTexasHoldemUtils() {
    }

    public List<Hand5Card> createHand5From3CommunityCards(Hand5Card communityCards, Hand2Card hand2Card) {
        List<Hand5Card> possibleHandList = new ArrayList<>();

        for (int communityIgnoreIndexA = 0; communityIgnoreIndexA < 4; ++communityIgnoreIndexA) {
            for (int communityIgnoreIndexB = communityIgnoreIndexA + 1; communityIgnoreIndexB < 5; ++communityIgnoreIndexB) {
                List<Card> permutationCardSet = new ArrayList<>();

                Iterator<Card> hand2CardIterator = hand2Card.getCards().iterator();

                permutationCardSet.add(hand2CardIterator.next());
                permutationCardSet.add(hand2CardIterator.next());

                for (int communityCardIndex = 0; communityCardIndex < 5; ++communityCardIndex) {
                    if (communityIgnoreIndexA == communityCardIndex || communityIgnoreIndexB == communityCardIndex) {
                        continue;
                    }

                    Card cardToAdd = communityCards.getNthCard(communityCardIndex);
                    permutationCardSet.add(cardToAdd);
                }

                Iterator<Card> permutationCardSetIterator = permutationCardSet.iterator();

                Hand5Card permutationHand = handFactoryHand5.create(
                        permutationCardSetIterator.next(),
                        permutationCardSetIterator.next(),
                        permutationCardSetIterator.next(),
                        permutationCardSetIterator.next(),
                        permutationCardSetIterator.next());

                possibleHandList.add(permutationHand);
            }
        }

        return possibleHandList;
    }

    public List<Hand5Card> generateAllPossibleHands(Hand5Card communityCards, Hand2Card hand2Card) {
        List<Hand5Card> allPossibleHandList = new ArrayList<>();

        allPossibleHandList.add(communityCards);

        List<Hand5Card> firstCardHand5Permutations = createHand5From4CommunityCards(communityCards, IteratorUtils.get(hand2Card.getCards().iterator(), 0));
        List<Hand5Card> secondCardHand5Permutations = createHand5From4CommunityCards(communityCards, IteratorUtils.get(hand2Card.getCards().iterator(), 1));

        allPossibleHandList.addAll(firstCardHand5Permutations);
        allPossibleHandList.addAll(secondCardHand5Permutations);

        List<Hand5Card> twoHoleCardsPermutations = createHand5From3CommunityCards(communityCards, hand2Card);

        allPossibleHandList.addAll(twoHoleCardsPermutations);

        return allPossibleHandList;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public Hand5Card findBestHandWithCommunityCards(Hand5Card communityCards, Hand2Card hand2Card) {
        List<Hand5Card> possibleHands = generateAllPossibleHands(communityCards, hand2Card);
        Hand5Card bestHand = findBestHand(possibleHands);

        return bestHand;
    }

    public Hand5Card findBestHand(List<Hand5Card> hand5CardList) {
        Hand5Card bestHand = null;

        for (Hand5Card hand5Card : hand5CardList) {
            if (bestHand == null) {
                bestHand = hand5Card;
            } else if (poker5CardAceHighLowComparator.compare(hand5Card, bestHand) > 0) {
                bestHand = hand5Card;
            }
        }

        return bestHand;
    }

    public List<Hand5Card> createHand5From4CommunityCards(Hand5Card communityCards, Card otherCard) {
        List<Hand5Card> possibleHandList = new ArrayList<>();

        for (int communityIgnoreIndex = 0; communityIgnoreIndex < 5; ++communityIgnoreIndex) {
            List<Card> permutationCardSet = new ArrayList<>();

            permutationCardSet.add(otherCard);

            for (int communityCardIndex = 0; communityCardIndex < 5; ++communityCardIndex) {
                if (communityIgnoreIndex == communityCardIndex) {
                    continue;
                }

                Card cardToAdd = communityCards.getNthCard(communityCardIndex);
                permutationCardSet.add(cardToAdd);
            }

            Iterator<Card> permutationCardSetIterator = permutationCardSet.iterator();

            Hand5Card permutationHand = handFactoryHand5.create(
                    permutationCardSetIterator.next(),
                    permutationCardSetIterator.next(),
                    permutationCardSetIterator.next(),
                    permutationCardSetIterator.next(),
                    permutationCardSetIterator.next());

            possibleHandList.add(permutationHand);
        }

        return possibleHandList;
    }
}
