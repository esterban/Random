package org.srwcrw.poker.texasholdem.collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.*;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardOrdinalFactory.class, CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class, HandFactoryHand5.class, Hand5OrdinalFactoryFast.class})
class Hand5OrdinalFactoryFastTest {
    @Autowired
    private TestUtils testUtils;

    @Autowired
    private HandFactoryHand5 handFactoryHand5;

    @Test
    public void testCreate() {
        Hand5OrdinalFactoryFast hand5OrdinalFactoryFast = new Hand5OrdinalFactoryFast();
        IHand5Card hand5CardA = testUtils.createOnePair();

        IHand5Card hand5CardB = handFactoryHand5.create(
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