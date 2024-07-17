package org.srwcrw.poker.texasholdem.components;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import static org.assertj.core.api.Assertions.assertThat;

class CardOrdinalUtilsTest {
    @Test
    public void testOrdinalToSuitA() {
        Card card = new Card(Suit.Hearts, Value.Ace);

        CardOrdinalUtils cardOrdinalUtils = new CardOrdinalUtils();
        Suit suit = cardOrdinalUtils.ordinalToSuit(card.getCardOrdinal());

        assertThat(suit).isEqualTo(Suit.Hearts);
    }

    @Test
    public void testOrdinalToSuitB() {
        Card card = new Card(Suit.Spades, Value.Two);

        CardOrdinalUtils cardOrdinalUtils = new CardOrdinalUtils();
        Suit suit = cardOrdinalUtils.ordinalToSuit(card.getCardOrdinal());

        assertThat(suit).isEqualTo(Suit.Spades);
    }

    @Test
    public void testOrdinalToValueA() {
        Card card = new Card(Suit.Spades, Value.Two);

        CardOrdinalUtils cardOrdinalUtils = new CardOrdinalUtils();
        Value value = cardOrdinalUtils.ordinalToValue(card.getCardOrdinal());

        assertThat(value).isEqualTo(Value.Two);
    }

    @Test
    public void testOrdinalToValueB() {
        Card card = new Card(Suit.Diamonds, Value.Jack);

        CardOrdinalUtils cardOrdinalUtils = new CardOrdinalUtils();
        Value value = cardOrdinalUtils.ordinalToValue(card.getCardOrdinal());

        assertThat(value).isEqualTo(Value.Jack);
    }
}