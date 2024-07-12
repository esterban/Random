package org.srwcrw.poker.texasholdem.collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtils.class, PackGenerator.class})
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