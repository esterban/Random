package org.srwcrw.poker.texasholdem.components;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.srwcrw.poker.texasholdem.utils.HandUtils.areEqual;

@SpringBootTest(classes = {HandFactoryHand5Mutable.class, HandFactoryHand5.class, CardOrdinalFactory.class, TestUtils.class, PackGenerator.class, CardFactoryImmutable.class, Hand5OrdinalFactoryFast.class})
class HandFactoryHand5MutableTest {

    @Autowired
    private HandFactoryHand5Mutable handFactoryHand5Mutable;

    @Autowired
    private TestUtils testUtils;

    @Test
    public void testCreate() {
        IHand5Card hand5Card = testUtils.createOnePair();

        Hand5CardMutable hand5CardMutable = handFactoryHand5Mutable.create(hand5Card.getCardsArray());

        assertThat(areEqual(hand5Card, hand5CardMutable)).isTrue();

        assertThat(hand5CardMutable).isNotNull();
    }

    @Test
    public void testCreate2Hand() {
        IHand5Card hand5CardA = testUtils.createOnePair();
        IHand5Card hand5CardB = testUtils.createOnePair();

        Hand5CardMutable hand5CardMutableA = handFactoryHand5Mutable.create(hand5CardA.getCardsArray());
        Hand5CardMutable hand5CardMutableB = handFactoryHand5Mutable.create(hand5CardB.getCardsArray());

        assertThat(hand5CardMutableA).isNotNull();
        assertThat(hand5CardMutableB).isNotNull();

        assertThat(areEqual(hand5CardA, hand5CardMutableA)).isTrue();
        assertThat(areEqual(hand5CardB, hand5CardMutableB)).isTrue();
        assertThat(hand5CardMutableA).isEqualTo(hand5CardMutableB);
        assertThat(hand5CardMutableB).isEqualTo(hand5CardMutableA);
        assertThat(hand5CardMutableA).isNotSameAs(hand5CardMutableB);


    }

}