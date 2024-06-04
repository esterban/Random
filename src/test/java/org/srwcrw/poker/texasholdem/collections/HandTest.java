package org.srwcrw.poker.texasholdem.collections;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    private final TestUtils testUtils = new TestUtils();
    @Test
    void testGetCardAtRandom() {
        Hand hand = (Hand) testUtils.createPack();

        List<Card> cardList = hand.getCardListAtRandom(5);

        assertThat(cardList).hasSize(5);
    }

}