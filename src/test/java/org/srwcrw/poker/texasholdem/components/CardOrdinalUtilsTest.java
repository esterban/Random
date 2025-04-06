package org.srwcrw.poker.texasholdem.components;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardOrdinalFactory.class, CardFactoryImmutable.class, CardOrdinalUtils.class, })
public class CardOrdinalUtilsTest {
    @Autowired
    private CardOrdinalUtils cardOrdinalUtils;

    @Autowired
    private CardFactoryImmutable cardFactoryImmutable;

    @Test
    public void testOrdinalToSuitA() {
        Card card = cardFactoryImmutable.createCard(Suit.Hearts, Value.Ace);

        Suit suit = cardOrdinalUtils.ordinalToSuit(card.getCardOrdinal());

        assertThat(suit).isEqualTo(Suit.Hearts);
    }

    @Test
    public void testOrdinalToSuitB() {
        Card card = cardFactoryImmutable.createCard(Suit.Spades, Value.Two);
         
        Suit suit = cardOrdinalUtils.ordinalToSuit(card.getCardOrdinal());

        assertThat(suit).isEqualTo(Suit.Spades);
    }

    @Test
    public void testOrdinalToValueA() {
        Card card = cardFactoryImmutable.createCard(Suit.Spades, Value.Two);

        Value value = cardOrdinalUtils.ordinalToValue(card.getCardOrdinal());

        assertThat(value).isEqualTo(Value.Two);
    }

    @Test
    public void testOrdinalToValueB() {
        Card card = cardFactoryImmutable.createCard(Suit.Diamonds, Value.Jack);
         
        Value value = cardOrdinalUtils.ordinalToValue(card.getCardOrdinal());

        assertThat(value).isEqualTo(Value.Jack);
    }
}