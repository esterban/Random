package org.srwcrw.poker.texasholdem;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;

import static org.assertj.core.api.Assertions.assertThat;

class HandType5CardsTest {

    @Test
    public void testCompare() {
        HandType5Cards highestCard = HandType5Cards.HighestCard;
        HandType5Cards twoPair = HandType5Cards.TwoPair;

        assertThat(twoPair.compareTo(highestCard)).isGreaterThan(0);
    }
}