package org.srwcrw.poker.texasholdem.utils;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PokerTexasHoldemUtilsTest {
    private TestUtilsTexasHoldem testUtilsTexasHoldem = new TestUtilsTexasHoldem();

    @Test
    public void testCreateHand5From3CommunityCards() {
        PokerTexasHoldemUtils pokerTexasHoldemUtils = new PokerTexasHoldemUtils();

        Map.Entry<Hand5Card, Hand2Card> onePairAllCards = testUtilsTexasHoldem.createOnePairA();

        List<Hand5Card> hand5Card = pokerTexasHoldemUtils.createHand5From3CommunityCards(onePairAllCards.getKey(), onePairAllCards.getValue());

        assertThat(hand5Card).hasSize(10);
    }
}