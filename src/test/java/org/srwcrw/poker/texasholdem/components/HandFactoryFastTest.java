//package org.srwcrw.poker.texasholdem.components;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.srwcrw.poker.texasholdem.test.TestUtils;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = {HandFactoryFast.class, Hand5OrdinalFactoryFast.class, TestUtils.class, CardOrdinalFactory.class, CardFactoryImmutable.class, PackGenerator.class, HandFactoryHand5.class,})
//class HandFactoryFastTest {
//    @Autowired
//    private HandFactoryFast handFactoryFast;
//
//    @Autowired
//    private Hand5OrdinalFactoryFast hand5OrdinalFactoryFast;
//
//    @Autowired
//    private TestUtils testUtils;
//
//    @Test
//    public void testCreate() {
//        Hand5Card hand5CardA = testUtils.createOnePair();
//
//        Hand5Card hand5Card = handFactoryFast.create(hand5CardA.getCardsArray()[0],
//                hand5CardA.getCardsArray()[1],
//                hand5CardA.getCardsArray()[2],
//                hand5CardA.getCardsArray()[3],
//                hand5CardA.getCardsArray()[4] );
//
//        assertThat(hand5Card).isNotNull();
//
//    }
//
//}