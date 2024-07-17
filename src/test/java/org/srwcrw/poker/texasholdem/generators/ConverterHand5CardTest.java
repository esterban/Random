package org.srwcrw.poker.texasholdem.generators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.collections.Hand;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
class ConverterHand5CardTest {

    @Autowired
    TestUtils testUtils;

    @Test
    public void testConvertA() {
        ConverterHand5Card converterHand5Card = new ConverterHand5Card();

        SortedSet<Card> cardSortedSet = convertHandToSortedSet(testUtils.createStraight());
        Hand hand = new Hand(cardSortedSet);

        Hand5Card hand5CardConverted = converterHand5Card.convert(hand);
        SortedSet<Card> cardSortedSetConverted = convertHandToSortedSet(hand5CardConverted);

        assertThat(cardSortedSetConverted).containsOnly(cardSortedSet.toArray(new Card[]{}));
    }

    private SortedSet<Card> convertHandToSortedSet(Hand5Card hand5Card) {
        SortedSet<Card> cardSortedSetConverted = IntStream.range(0, 5).boxed().map(e -> hand5Card.getNthCard(e)).collect(Collectors.toCollection(TreeSet::new));

        return cardSortedSetConverted;
    }
}