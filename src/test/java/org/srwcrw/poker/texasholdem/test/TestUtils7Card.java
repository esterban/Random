package org.srwcrw.poker.texasholdem.test;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.Hand7Card;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtils7Card {
    public static Hand7Card createHighCardA() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card3 = new Card(Suit.Hearts, Value.Four);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card4 = new Card(Suit.Hearts, Value.Queen);
        Card card5 = new Card(Suit.Hearts, Value.King);
        Card card6 = new Card(Suit.Hearts, Value.Two);
        Card card7 = new Card(Suit.Hearts, Value.Three);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5, card6, card7).collect(Collectors.toCollection(TreeSet::new));

        Hand7Card hand7Card = new Hand7Card(handSet);

        return hand7Card;
    }


    public static Hand7Card createOnePairA() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card3 = new Card(Suit.Hearts, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card4 = new Card(Suit.Hearts, Value.Queen);
        Card card5 = new Card(Suit.Hearts, Value.King);
        Card card6 = new Card(Suit.Hearts, Value.Two);
        Card card7 = new Card(Suit.Hearts, Value.Three);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5, card6, card7).collect(Collectors.toCollection(TreeSet::new));

        Hand7Card hand7Card = new Hand7Card(handSet);

        return hand7Card;
    }
}
