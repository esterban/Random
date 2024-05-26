package org.srwcrw.poker.texasholdem.test;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.generators.PackGenerator;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtils {
    public static Hand5Card createStraightFlushAceLow() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Two);
        Card card3 = new Card(Suit.Clubs, Value.Three);
        Card card4 = new Card(Suit.Clubs, Value.Four);
        Card card5 = new Card(Suit.Clubs, Value.Five);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createStraightFlushAceHigh() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card3 = new Card(Suit.Clubs, Value.Jack);
        Card card4 = new Card(Suit.Clubs, Value.Queen);
        Card card5 = new Card(Suit.Clubs, Value.King);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createStraightFlushAceA() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card3 = new Card(Suit.Clubs, Value.Jack);
        Card card4 = new Card(Suit.Clubs, Value.Queen);
        Card card5 = new Card(Suit.Clubs, Value.King);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createStraightFlushAceB() {
        Card card1 = new Card(Suit.Clubs, Value.Nine);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card3 = new Card(Suit.Clubs, Value.Jack);
        Card card4 = new Card(Suit.Clubs, Value.Queen);
        Card card5 = new Card(Suit.Clubs, Value.King);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createFourOfAKindA() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Hearts, Value.Ace);
        Card card3 = new Card(Suit.Spades, Value.Ace);
        Card card4 = new Card(Suit.Diamonds, Value.Ace);
        Card card5 = new Card(Suit.Clubs, Value.Seven);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createFourOfAKindB() {
        Card card1 = new Card(Suit.Clubs, Value.Two);
        Card card2 = new Card(Suit.Hearts, Value.Two);
        Card card3 = new Card(Suit.Spades, Value.Two);
        Card card4 = new Card(Suit.Diamonds, Value.Two);
        Card card5 = new Card(Suit.Clubs, Value.Seven);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createFullHouseA() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Hearts, Value.Ace);
        Card card3 = new Card(Suit.Spades, Value.Six);
        Card card4 = new Card(Suit.Clubs, Value.Six);
        Card card5 = new Card(Suit.Diamonds, Value.Six);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createFullHouseB() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Hearts, Value.Ace);
        Card card3 = new Card(Suit.Spades, Value.Ace);
        Card card4 = new Card(Suit.Clubs, Value.Six);
        Card card5 = new Card(Suit.Diamonds, Value.Six);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createFlushA() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Three);
        Card card3 = new Card(Suit.Clubs, Value.Five);
        Card card4 = new Card(Suit.Clubs, Value.Six);
        Card card5 = new Card(Suit.Clubs, Value.Seven);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createFlushB() {
        Card card1 = new Card(Suit.Hearts, Value.Ten);
        Card card2 = new Card(Suit.Hearts, Value.Three);
        Card card3 = new Card(Suit.Hearts, Value.Jack);
        Card card4 = new Card(Suit.Hearts, Value.Six);
        Card card5 = new Card(Suit.Hearts, Value.Seven);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createStraight() {
        Card card1 = new Card(Suit.Clubs, Value.Four);
        Card card2 = new Card(Suit.Hearts, Value.Three);
        Card card3 = new Card(Suit.Hearts, Value.Five);
        Card card4 = new Card(Suit.Hearts, Value.Six);
        Card card5 = new Card(Suit.Hearts, Value.Seven);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createStraightAceLow() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Hearts, Value.Two);
        Card card3 = new Card(Suit.Hearts, Value.Three);
        Card card4 = new Card(Suit.Hearts, Value.Four);
        Card card5 = new Card(Suit.Hearts, Value.Five);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createStraightAceHigh() {
        Card card1 = new Card(Suit.Clubs, Value.Ten);
        Card card2 = new Card(Suit.Hearts, Value.Jack);
        Card card3 = new Card(Suit.Hearts, Value.Queen);
        Card card4 = new Card(Suit.Hearts, Value.King);
        Card card5 = new Card(Suit.Hearts, Value.Ace);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }



    public static Hand5Card createThreeOfAKind() {
        Card card1 = new Card(Suit.Hearts, Value.Ten);
        Card card2 = new Card(Suit.Diamonds, Value.Ten);
        Card card3 = new Card(Suit.Clubs, Value.Ten);
        Card card4 = new Card(Suit.Hearts, Value.King);
        Card card5 = new Card(Suit.Hearts, Value.Two);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createThreeOfAKindA() {
        Card card1 = new Card(Suit.Hearts, Value.Ten);
        Card card2 = new Card(Suit.Diamonds, Value.Ten);
        Card card3 = new Card(Suit.Clubs, Value.Ten);
        Card card4 = new Card(Suit.Hearts, Value.Ace);
        Card card5 = new Card(Suit.Hearts, Value.Two);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createThreeOfAKindB() {
        Card card1 = new Card(Suit.Hearts, Value.Ten);
        Card card2 = new Card(Suit.Diamonds, Value.Ten);
        Card card3 = new Card(Suit.Clubs, Value.Ten);
        Card card4 = new Card(Suit.Hearts, Value.King);
        Card card5 = new Card(Suit.Hearts, Value.Two);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }


    public static Hand5Card createThreeOfAKindC() {
        Card card1 = new Card(Suit.Hearts, Value.Five);
        Card card2 = new Card(Suit.Diamonds, Value.Five);
        Card card3 = new Card(Suit.Clubs, Value.Five);
        Card card4 = new Card(Suit.Hearts, Value.King);
        Card card5 = new Card(Suit.Hearts, Value.Two);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }


    public static Hand5Card createTwoPairA() {
        Card card1 = new Card(Suit.Hearts, Value.Ten);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card3 = new Card(Suit.Diamonds, Value.Three);
        Card card4 = new Card(Suit.Spades, Value.Three);
        Card card5 = new Card(Suit.Hearts, Value.Seven);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createTwoPairBKickerTen() {
        Card card1 = new Card(Suit.Hearts, Value.Queen);
        Card card2 = new Card(Suit.Clubs, Value.Queen);
        Card card3 = new Card(Suit.Diamonds, Value.Jack);
        Card card4 = new Card(Suit.Spades, Value.Jack);
        Card card5 = new Card(Suit.Hearts, Value.Ten);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createTwoPairBKickerSeven() {
        Card card1 = new Card(Suit.Hearts, Value.Queen);
        Card card2 = new Card(Suit.Clubs, Value.Queen);
        Card card3 = new Card(Suit.Diamonds, Value.Jack);
        Card card4 = new Card(Suit.Spades, Value.Jack);
        Card card5 = new Card(Suit.Hearts, Value.Seven);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }


    public static Hand5Card createOnePair() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card3 = new Card(Suit.Hearts, Value.Ace);
        Card card4 = new Card(Suit.Hearts, Value.Queen);
        Card card5 = new Card(Suit.Hearts, Value.King);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createOnePairRank1KickerTen() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card3 = new Card(Suit.Hearts, Value.Ace);
        Card card5 = new Card(Suit.Hearts, Value.King);
        Card card4 = new Card(Suit.Hearts, Value.Ten);
        Card card2 = new Card(Suit.Clubs, Value.Four);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createOnePairRank1KickerNine() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card3 = new Card(Suit.Hearts, Value.Ace);
        Card card4 = new Card(Suit.Hearts, Value.King);
        Card card2 = new Card(Suit.Clubs, Value.Nine);
        Card card5 = new Card(Suit.Hearts, Value.Two);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createHighestCard() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card3 = new Card(Suit.Hearts, Value.Nine);
        Card card4 = new Card(Suit.Hearts, Value.Queen);
        Card card5 = new Card(Suit.Hearts, Value.King);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }


    public static Hand5Card createOnePairA() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card3 = new Card(Suit.Hearts, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card4 = new Card(Suit.Hearts, Value.Queen);
        Card card5 = new Card(Suit.Hearts, Value.King);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static Hand5Card createOnePairB() {
        Card card1 = new Card(Suit.Clubs, Value.Ace);
        Card card3 = new Card(Suit.Hearts, Value.Ace);
        Card card2 = new Card(Suit.Clubs, Value.Ten);
        Card card4 = new Card(Suit.Hearts, Value.Queen);
        Card card5 = new Card(Suit.Hearts, Value.Two);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5).collect(Collectors.toCollection(TreeSet::new));

        Hand5Card hand5Card = new Hand5Card(handSet);

        return hand5Card;
    }

    public static IPack createPack() {
        PackGenerator packGenerator = new PackGenerator();
        return packGenerator.generateFullPack();
    }

}
