package org.srwcrw.poker.texasholdem.utils;

import org.apache.commons.collections4.IteratorUtils;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.comparator.Poker5CardAceHighLowComparator;
import org.srwcrw.poker.texasholdem.components.Card;

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

//                    for (int index = 0; index < 5; ++index) {
                    Card cardToAdd = communityCards.getNthCard(communityCardIndex);
                    permutationCardSet.add(cardToAdd);
//                    }

                }

                Iterator<Card> permutationCardSetIterator = permutationCardSet.iterator();

                Hand5Card permutationHand = new Hand5Card(
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

//        Iterator<Card> communityCardIterator = communityCards.getCards().iterator();
//
//        Hand5Card hand5Card = new Hand5Card(
//                communityCardIterator.next(),
//                communityCardIterator.next(),
//                communityCardIterator.next(),
//                communityCardIterator.next(),
//                communityCardIterator.next());

//        allPossibleHandList.add(hand5Card);

        allPossibleHandList.add(communityCards);

        List<Hand5Card> firstCardHand5Permutations = createHand5From4CommunityCards(communityCards, hand2Card.getCards().first());
        List<Hand5Card> secondCardHand5Permutations = createHand5From4CommunityCards(communityCards, IteratorUtils.get(hand2Card.getCards().iterator(), 1));

        allPossibleHandList.addAll(firstCardHand5Permutations);
        allPossibleHandList.addAll(secondCardHand5Permutations);

        List<Hand5Card> twoHoleCardsPermutations = createHand5From3CommunityCards(communityCards, hand2Card);

        allPossibleHandList.addAll(twoHoleCardsPermutations);

        return allPossibleHandList;
    }

    public Hand5Card findBestHandWithCommunityCards(Hand5Card communityCards, Hand2Card hand2Card) {
        List<Hand5Card> possibleHands = generateAllPossibleHands(communityCards, hand2Card);
        Hand5Card bestHand = findBestHand(possibleHands);

        return bestHand;
    }


    public Hand5Card findBestHand(List<Hand5Card> hand5CardList) {
        SortedSet<Hand5Card> hand5CardSet = new TreeSet<>(new Poker5CardAceHighLowComparator());
        hand5CardSet.addAll(hand5CardList);

        return hand5CardSet.last();
    }

    public List<Hand5Card> createHand5From4CommunityCards(Hand5Card communityCards, Card otherCard) {
        List<Hand5Card> possibleHandList = new ArrayList<>();

        for (int communityIgnoreIndex = 0; communityIgnoreIndex < 5; ++communityIgnoreIndex) {
            SortedSet<Card> permutationCardSet = new TreeSet<>();

            permutationCardSet.add(otherCard);

            for (int communityCardIndex = 0; communityCardIndex < 5; ++communityCardIndex) {
                if (communityIgnoreIndex == communityCardIndex) {
                    continue;
                }

                Card cardToAdd = communityCards.getNthCard(communityCardIndex);
                permutationCardSet.add(cardToAdd);
            }

            Iterator<Card> permutationCardSetIterator = permutationCardSet.iterator();

            Hand5Card permutationHand = new Hand5Card(
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
