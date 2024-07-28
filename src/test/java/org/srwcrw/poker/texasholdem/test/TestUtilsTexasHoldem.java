package org.srwcrw.poker.texasholdem.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.comparator.Poker5CardAceHighLowComparator;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.entities.ICardFactory;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.*;

@Component
public class TestUtilsTexasHoldem {
    @Autowired
    private ICardFactory cardFactory;

    public Map.Entry<Hand5Card, Hand2Card> createOnePairA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ace));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Three));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ace));
        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Five));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createOnePairB() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ace));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Three));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Six));
        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Five));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createOnePairC() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Two));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Three));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Six));
        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Five));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createTwoPairA() {
        // SWright TODO - Remove using a SortedSet in Hand5Card constructor,
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ace));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Five));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());


        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ace));
        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Five));

//        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createOnePairD() {
        // SWright TODO - Remove using a SortedSet in Hand5Card constructor,
        List<Card> hand5CardSet = new ArrayList<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Queen));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Two));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Three));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ace));
        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Five));

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createThreeAcesA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ace));
        hand5CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Ace));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ace));
        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Five));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

//        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createStraightA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ace));
        hand5CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Jack));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.King));
        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Six));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

//        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createFlushA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ace));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Jack));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Ten));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Queen));
        hand2CardSet.add(cardFactory.createCard(Suit.Hearts, Value.King));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

//        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createFullhouseA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Jack));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Two));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Six));
        hand2CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

//        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createFourKings() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.King));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.King));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Six));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Diamonds, Value.King));
        hand2CardSet.add(cardFactory.createCard(Suit.Spades, Value.King));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

//        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createStraightFlush() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ace));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.King));
        hand5CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Queen));
        hand5CardSet.add(cardFactory.createCard(Suit.Clubs, Value.Ten));
        hand5CardSet.add(cardFactory.createCard(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Jack));
        hand2CardSet.add(cardFactory.createCard(Suit.Hearts, Value.Ten));

        Iterator<Card> hand5Iterator = hand5CardSet.iterator();

//        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand5Card hand5Card = new Hand5Card(
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next(),
                hand5Iterator.next());

        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Hand5Card getBestHand(List<Hand5Card> hand5CardList) {
        Poker5CardAceHighLowComparator poker5CardAceHighLowComparator = new Poker5CardAceHighLowComparator();
        Hand5Card bestHand5Card = null;

        for (Hand5Card hand5Card : hand5CardList) {
            if (bestHand5Card == null) {
                bestHand5Card = hand5Card;
            } else if (poker5CardAceHighLowComparator.compare(hand5Card, bestHand5Card) > 0) {
                bestHand5Card = hand5Card;
            }
        }

        return bestHand5Card;
    }

}
