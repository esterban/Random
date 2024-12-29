package org.srwcrw.poker.texasholdem.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class TestUtils {
    @Autowired
    private PackGenerator packGenerator;

    @Autowired
    private CardFactoryImmutable cardFactoryImmutable;

    private TestUtils() {}

    public Hand5Card createRoyalFlushA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack);
        Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Queen);
        Card card4 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.King);
        Card card5 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ace);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createRoyalFlushB() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Jack);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.King);
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createStraightFlushAceLow() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Two);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Three);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Four);
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Five);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createStraightFlushAceHigh() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Jack);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.King);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createStraightFlushA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Nine);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Jack);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.King);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createStraightFlushB() {
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Eight);
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Nine);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Jack);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createFourOfAKindA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card3 = cardFactoryImmutable.createCard(Suit.Spades, Value.Ace);
        Card card4 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ace);
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createFourOfAKindB() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Two);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);
        Card card3 = cardFactoryImmutable.createCard(Suit.Spades, Value.Two);
        Card card4 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Two);
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createFullHouseA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card3 = cardFactoryImmutable.createCard(Suit.Spades, Value.Six);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Six);
        Card card5 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Six);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createFullHouseB() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card3 = cardFactoryImmutable.createCard(Suit.Spades, Value.Ace);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Six);
        Card card5 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Six);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createFlushA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Three);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Five);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Six);
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createFlushAA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Three);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Four);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Six);
        Card card5 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }


    public Hand5Card createFlushB() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Three);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Jack);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Six);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createStraight() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Four);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Three);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Five);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Six);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createStraightAceLow() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Three);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Four);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Five);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createStraightAceHigh() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Jack);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createThreeOfAKind() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createThreeOfAKindA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Three);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createThreeOfAKindAA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ten);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createThreeOfAKindB() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createThreeOfAKindC() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Five);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);
        Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Five);
        Card card4 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Five);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createTwoPairA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Three);
        Card card4 = cardFactoryImmutable.createCard(Suit.Spades, Value.Three);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createTwoPairBKickerTwo() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);
        Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack);
        Card card4 = cardFactoryImmutable.createCard(Suit.Spades, Value.Jack);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }


    public Hand5Card createTwoPairBKickerAce() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);
        Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack);
        Card card4 = cardFactoryImmutable.createCard(Suit.Spades, Value.Jack);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createTwoPairBKickerTen() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);
        Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack);
        Card card4 = cardFactoryImmutable.createCard(Suit.Spades, Value.Jack);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createTwoPairBKickerSeven() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);
        Card card3 = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack);
        Card card4 = cardFactoryImmutable.createCard(Suit.Spades, Value.Jack);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createOnePair() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createOnePairRank1KickerTen() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ten);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Four);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createOnePairRank1KickerNine() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Nine);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createHighestCardA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Nine);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createHighestCardAA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Nine);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }


    public Hand5Card createHighestCardB() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Two);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Nine);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createHighestCardC() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Two);
        Card card2 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Three);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Five);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Six);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Seven);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createOnePairA() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.King);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public Hand5Card createOnePairB() {
        Card card1 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ace);
        Card card3 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);
        Card card2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Ten);
        Card card4 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Queen);
        Card card5 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Two);

        Hand5Card hand5Card = new Hand5Card(card1, card2, card3, card4, card5);

        return hand5Card;
    }

    public IPack createPack() {
        return packGenerator.generateFullPack();
    }

    public SortedSet<Card> convertHand5ToSortedSet(Hand5Card hand5Card) {
        SortedSet<Card> sortedSet = new TreeSet<>();

        for (int index = 0; index < 5; ++index) {
            sortedSet.add(hand5Card.getNthCard(index));
        }

        return sortedSet;
    }

}
