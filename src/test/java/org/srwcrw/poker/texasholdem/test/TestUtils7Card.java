package org.srwcrw.poker.texasholdem.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.collections.Hand7Card;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.entities.ICardFactory;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
public class TestUtils7Card {
    @Autowired
    private ICardFactory cardFactory;

    public Hand7Card createHighCardA() {
        Card card1 = cardFactory.createCard(Suit.Clubs, Value.Ace);
        Card card3 = cardFactory.createCard(Suit.Hearts, Value.Four);
        Card card2 = cardFactory.createCard(Suit.Clubs, Value.Ten);
        Card card4 = cardFactory.createCard(Suit.Hearts, Value.Queen);
        Card card5 = cardFactory.createCard(Suit.Hearts, Value.King);
        Card card6 = cardFactory.createCard(Suit.Hearts, Value.Two);
        Card card7 = cardFactory.createCard(Suit.Hearts, Value.Three);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5, card6, card7).collect(Collectors.toCollection(TreeSet::new));

        Hand7Card hand7Card = new Hand7Card(handSet);

        return hand7Card;
    }


    public Hand7Card createOnePairA() {
        Card card1 = cardFactory.createCard(Suit.Clubs, Value.Ace);
        Card card3 = cardFactory.createCard(Suit.Hearts, Value.Ace);
        Card card2 = cardFactory.createCard(Suit.Clubs, Value.Ten);
        Card card4 = cardFactory.createCard(Suit.Hearts, Value.Queen);
        Card card5 = cardFactory.createCard(Suit.Hearts, Value.King);
        Card card6 = cardFactory.createCard(Suit.Hearts, Value.Two);
        Card card7 = cardFactory.createCard(Suit.Hearts, Value.Three);
        SortedSet handSet = Stream.of(card1, card2, card3, card4, card5, card6, card7).collect(Collectors.toCollection(TreeSet::new));

        Hand7Card hand7Card = new Hand7Card(handSet);

        return hand7Card;
    }
}
