package org.srwcrw.poker.texasholdem.collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
class Hand5OrdinalFactoryFastTest {
    @Autowired
    private TestUtils testUtils;


    @Test
    public void testCreate() {
//    Card card1, Card card2, Card card3, Card card4, Card card5) {
        Hand5OrdinalFactoryFast hand5OrdinalFactoryFast = new Hand5OrdinalFactoryFast();
        Hand5Card hand5CardA = testUtils.createOnePair();

        Hand5Card hand5CardB = new Hand5Card(
                hand5CardA.getNthCard(1),
                hand5CardA.getNthCard(2),
                hand5CardA.getNthCard(3),
                hand5CardA.getNthCard(4),
                hand5CardA.getNthCard(0) );

        Hand5Ordinal hand5OrdinalA = hand5OrdinalFactoryFast.create(
                hand5CardA.getNthCard(0),
                hand5CardA.getNthCard(1),
                hand5CardA.getNthCard(2),
                hand5CardA.getNthCard(3),
                hand5CardA.getNthCard(4) );

        Hand5Ordinal hand5OrdinalB = hand5OrdinalFactoryFast.create(
                hand5CardB.getNthCard(0),
                hand5CardB.getNthCard(1),
                hand5CardB.getNthCard(2),
                hand5CardB.getNthCard(3),
                hand5CardB.getNthCard(4) );

        assertThat(hand5OrdinalA.getOrdinal()).isEqualTo(hand5OrdinalB.getOrdinal());

    }
}