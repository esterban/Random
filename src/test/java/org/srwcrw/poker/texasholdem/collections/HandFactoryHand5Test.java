package org.srwcrw.poker.texasholdem.collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.*;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {HandFactoryHand5.class, CardOrdinalFactory.class, TestUtils.class, PackGenerator.class, CardFactoryImmutable.class, Hand5OrdinalFactoryFast.class})
class HandFactoryHand5Test {
    @Autowired
    private HandFactoryHand5 handFactoryHand5;

    @Autowired
    private TestUtils testUtils;

    @Test
    public void testCreate() {
        Hand5Card hand5Card = testUtils.createOnePair();

        Hand5Card hand5 = handFactoryHand5.create(hand5Card.getCardsArray());
        assertThat(hand5).isNotNull();
    }
}