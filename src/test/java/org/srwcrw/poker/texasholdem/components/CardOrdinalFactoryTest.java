package org.srwcrw.poker.texasholdem.components;

import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import static org.assertj.core.api.Assertions.assertThat;

@PrepareForTest(CardFactoryImmutable.class)
@SpringBootTest(classes = {CardOrdinalFactory.class})
class CardOrdinalFactoryTest {
    @Autowired
    private CardOrdinalFactory cardOrdinalFactory;

    @Test
    public void testCreate() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                CardOrdinal cardOrdinal = cardOrdinalFactory.create(suit, value);

                assertThat(cardOrdinal).isNotNull();
            }
        }
    }
}