package org.srwcrw.poker.texasholdem.test;

import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.AbstractMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

@SuppressWarnings("UnnecessaryLocalVariable")
public class TestUtilsTexasHoldem {
    public Map.Entry<Hand5Card, Hand2Card> createOnePairA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(new Card(Suit.Hearts, Value.Ace));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Three));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Six));
        hand5CardSet.add(new Card(Suit.Clubs, Value.Ten));
        hand5CardSet.add(new Card(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(new Card(Suit.Clubs, Value.Ace));
        hand2CardSet.add(new Card(Suit.Diamonds, Value.Five));

        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createTwoPairA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(new Card(Suit.Hearts, Value.Ace));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Five));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Six));
        hand5CardSet.add(new Card(Suit.Clubs, Value.Ten));
        hand5CardSet.add(new Card(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(new Card(Suit.Clubs, Value.Ace));
        hand2CardSet.add(new Card(Suit.Diamonds, Value.Five));

        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createThreeAcesA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(new Card(Suit.Hearts, Value.Ace));
        hand5CardSet.add(new Card(Suit.Diamonds, Value.Ace));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Six));
        hand5CardSet.add(new Card(Suit.Clubs, Value.Ten));
        hand5CardSet.add(new Card(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(new Card(Suit.Clubs, Value.Ace));
        hand2CardSet.add(new Card(Suit.Diamonds, Value.Five));

        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createStraightA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(new Card(Suit.Hearts, Value.Ace));
        hand5CardSet.add(new Card(Suit.Diamonds, Value.Jack));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Six));
        hand5CardSet.add(new Card(Suit.Clubs, Value.Ten));
        hand5CardSet.add(new Card(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(new Card(Suit.Clubs, Value.King));
        hand2CardSet.add(new Card(Suit.Diamonds, Value.Six));

        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createFlushA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(new Card(Suit.Hearts, Value.Ace));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Six));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Ten));
        hand5CardSet.add(new Card(Suit.Spades, Value.Jack));
        hand5CardSet.add(new Card(Suit.Spades, Value.Ten));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(new Card(Suit.Hearts, Value.Queen));
        hand2CardSet.add(new Card(Suit.Hearts, Value.King));

        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createFullhouseA() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(new Card(Suit.Hearts, Value.Six));
        hand5CardSet.add(new Card(Suit.Diamonds, Value.Six));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Ten));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Jack));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Two));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(new Card(Suit.Clubs, Value.Six));
        hand2CardSet.add(new Card(Suit.Clubs, Value.Ten));

        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createFourKings() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(new Card(Suit.Hearts, Value.King));
        hand5CardSet.add(new Card(Suit.Clubs, Value.King));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Six));
        hand5CardSet.add(new Card(Suit.Clubs, Value.Ten));
        hand5CardSet.add(new Card(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(new Card(Suit.Diamonds, Value.King));
        hand2CardSet.add(new Card(Suit.Spades, Value.King));

        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

    public Map.Entry<Hand5Card, Hand2Card> createStraightFlush() {
        SortedSet<Card> hand5CardSet = new TreeSet<>();

        hand5CardSet.add(new Card(Suit.Hearts, Value.Ace));
        hand5CardSet.add(new Card(Suit.Hearts, Value.King));
        hand5CardSet.add(new Card(Suit.Hearts, Value.Queen));
        hand5CardSet.add(new Card(Suit.Clubs, Value.Ten));
        hand5CardSet.add(new Card(Suit.Spades, Value.Queen));

        SortedSet<Card> hand2CardSet = new TreeSet<>();

        hand2CardSet.add(new Card(Suit.Hearts, Value.Jack));
        hand2CardSet.add(new Card(Suit.Hearts, Value.Ten));

        Hand5Card hand5Card = new Hand5Card(hand5CardSet);
        Hand2Card hand2Card = new Hand2Card(hand2CardSet);

        Map.Entry<Hand5Card, Hand2Card> result = new AbstractMap.SimpleEntry<>(hand5Card, hand2Card);

        return result;
    }

}
