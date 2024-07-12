package org.srwcrw.poker.texasholdem.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class})
class CardFactoryImmutableTest {
    @Autowired
    private CardFactoryImmutable cardFactoryImmutable;

    @Test
    public void testCreateCardA() {
        Card card = cardFactoryImmutable.createCard(Suit.Clubs, Value.Queen);

        assertThat(card).isEqualTo(new Card(Suit.Clubs, Value.Queen));
    }
}