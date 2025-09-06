package org.srwcrw.poker.texasholdem.components;


import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.HandFactory;
import org.srwcrw.poker.texasholdem.comparator.Poker5CardAceHighLowComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PokerTexasHoldemUtils {
    private static final Poker5CardAceHighLowComparator poker5CardAceHighLowComparator = new Poker5CardAceHighLowComparator();

    @Autowired
    @Qualifier("handFactoryHand5Mutable")
    private HandFactory<? extends IHand5Card> handFactoryHand5;

    private PokerTexasHoldemUtils() {
    }

    public List<IHand5Card> createHand5From3CommunityCards(IHand5Card communityCards, Hand2Card hand2Card) {
        List<IHand5Card> possibleHandList = new ArrayList<>();

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

                IHand5Card permutationHand = handFactoryHand5.create(
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

    public List<IHand5Card> generateAllPossibleHands(IHand5Card communityCards, Hand2Card hand2Card) {
        List<IHand5Card> allPossibleHandList = new ArrayList<>();

        allPossibleHandList.add(communityCards);

        List<IHand5Card> firstCardHand5Permutations = createHand5From4CommunityCards(communityCards, IteratorUtils.get(hand2Card.getCards().iterator(), 0));
        List<IHand5Card> secondCardHand5Permutations = createHand5From4CommunityCards(communityCards, IteratorUtils.get(hand2Card.getCards().iterator(), 1));

        allPossibleHandList.addAll(firstCardHand5Permutations);
        allPossibleHandList.addAll(secondCardHand5Permutations);

        List<IHand5Card> twoHoleCardsPermutations = createHand5From3CommunityCards(communityCards, hand2Card);

        allPossibleHandList.addAll(twoHoleCardsPermutations);

        return allPossibleHandList;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public IHand5Card findBestHandWithCommunityCards(IHand5Card communityCards, Hand2Card hand2Card) {
        List<IHand5Card> possibleHands = generateAllPossibleHands(communityCards, hand2Card);
        IHand5Card bestHand = findBestHand(possibleHands);

        return bestHand;
    }

    public IHand5Card findBestHand(List<IHand5Card> hand5CardList) {
        IHand5Card bestHand = null;

        for (IHand5Card hand5Card : hand5CardList) {
            if (bestHand == null) {
                bestHand = hand5Card;
            } else if (poker5CardAceHighLowComparator.compare(hand5Card, bestHand) > 0) {
                bestHand = hand5Card;
            }
        }

        return bestHand;
    }

    public List<IHand5Card> createHand5From4CommunityCards(IHand5Card communityCards, Card otherCard) {
        List<IHand5Card> possibleHandList = new ArrayList<>();

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

            IHand5Card permutationHand = handFactoryHand5.create(
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
