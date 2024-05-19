package org.srwcrw.poker.texasholdem.handclassifer;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.math3.util.MultidimensionalCounter;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.utils.HandUtils;

import java.util.*;

public class PokerTexasHoldemClassifier implements PokerCardClassifier<Hand2Card> {
    private final Hand5Card communityCards;
    private final HandUtils handUtils;

    public PokerTexasHoldemClassifier(Hand5Card communityCards) {
        this.communityCards = communityCards;
        this.handUtils = new HandUtils();
    }

    @Override
    public HandType5Cards classify(Hand2Card hand2Card) {
        int pairsCount = handUtils.countPairs(hand2Card);
        int tripleCount = handUtils.getThreeOfKindValues(hand2Card).size();
        int fourCount = handUtils.getFourOfKindValues(hand2Card).size();

        if (pairsCount == 0 && tripleCount == 0 && fourCount == 0) {
            return HandType5Cards.HighestCard;
        }

        if (pairsCount == 1) {
            return HandType5Cards.OnePair;
        }

        return null;

    }

    private List<Hand5Card> generateAllPossibleHands(Hand2Card hand2Card) {
        List<Hand5Card> allPossibleHandList = new ArrayList<>();

        Hand5Card hand5Card = new Hand5Card(communityCards.getCards());
        allPossibleHandList.add(hand5Card);

        List<Hand5Card> firstCardHand5Permutations = createHand5From4CommunityCards(hand2Card.getCards().first());
        List<Hand5Card> secondCardHand5Permutations = createHand5From4CommunityCards(IteratorUtils.get(hand2Card.getCards().iterator(), 1));

        allPossibleHandList.addAll(firstCardHand5Permutations);
        allPossibleHandList.addAll(secondCardHand5Permutations);

        return allPossibleHandList;
    }

    private List<Hand5Card> createHand5From4CommunityCards(Card otherCard) {
        List<Hand5Card> possibleHandList = new ArrayList<>();

        Iterator<Card> communityCardIterator = communityCards.getCards().iterator();

        for (int communityIgnoreIndex = 0; communityIgnoreIndex < 5; ++communityIgnoreIndex) {
            SortedSet<Card> permutationCardSet = new TreeSet<>();

            permutationCardSet.add(otherCard);

            for (int communityCardIndex = 0; communityCardIndex < 5; ++communityCardIndex) {
                if (communityIgnoreIndex == communityCardIndex) {
                    continue;
                }

                Card cardToAdd = IteratorUtils.get(communityCardIterator, communityCardIndex);
                permutationCardSet.add(cardToAdd);
            }

            Hand5Card permutationHand = new Hand5Card(permutationCardSet);
            possibleHandList.add(permutationHand);
        }

        return possibleHandList;
    }

//    private List<Hand5Card> createHand5From3CommunityCards(Hand2Card hand2Card) {
//        List<Hand5Card> possibleHandList = new ArrayList<>();
//
//        Iterator<Card> communityCardIterator = communityCards.getCards().iterator();
//
//        for (int communityIgnoreIndexA = 0; communityIgnoreIndexA < 5; ++communityIgnoreIndexA) {
//            for (int communityIgnoreIndexB = 0; communityIgnoreIndexB < 5; ++communityIgnoreIndexB) {
//                SortedSet<Card> permutationCardSet = new TreeSet<>();
//
//                Iterator<Card> hand2CardIterator = hand2Card.getCards().iterator();
//
//                permutationCardSet.add(hand2CardIterator.next());
//                permutationCardSet.add(hand2CardIterator.next());
//
//                for (int communityCardIndex = 0; communityCardIndex < 5; ++communityCardIndex) {
//                    if (communityIgnoreIndexA == communityCardIndex || communityIgnoreIndexB == communityCardIndex) {
//                        continue;
//                    }
//
//                    Card cardToAdd = IteratorUtils.get(communityCardIterator, communityCardIndex);
//                    permutationCardSet.add(cardToAdd);
//                }
//
//                Hand5Card permutationHand = new Hand5Card(permutationCardSet);
//                possibleHandList.add(permutationHand);
//            }
//        }
//
//        return possibleHandList;
//    }
}
