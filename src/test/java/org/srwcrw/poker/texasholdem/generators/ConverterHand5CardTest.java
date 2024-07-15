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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
class ConverterHand5CardTest {

    @Autowired
    TestUtils testUtils;

    @Test
    public void testConvertA() {
        ConverterHand5Card converterHand5Card = new ConverterHand5Card();

        SortedSet<Card> cardSortedSet = testUtils.createStraight().getCards();
        Hand hand = new Hand(cardSortedSet);

        Hand5Card hand5Card = converterHand5Card.convert(hand);

        assertThat(hand5Card.getCards()).containsOnly(cardSortedSet.toArray(new Card[]{}));
    }
}