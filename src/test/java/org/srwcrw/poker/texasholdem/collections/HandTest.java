package org.srwcrw.poker.texasholdem.collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.*;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardOrdinalFactory.class, CardFactoryImmutable.class, TestUtils.class, PackGenerator.class, HandFactoryHand5.class, Hand5OrdinalFactoryFast.class})
class HandTest {
    @Autowired
    private TestUtils testUtils;

    @Test
    void testGetCardAtRandom() {
        Hand hand = (Hand) testUtils.createPack();

        List<Card> cardList = hand.getCardListAtRandom(5);

        assertThat(cardList).hasSize(5);
    }

}