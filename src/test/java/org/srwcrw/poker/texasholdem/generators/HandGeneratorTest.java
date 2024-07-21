package org.srwcrw.poker.texasholdem.generators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import java.util.AbstractMap;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
class HandGeneratorTest {
    @Autowired
    private TestUtils testUtils;

    @Test
    void testGenerateHandAndRemoveImmutable() {
        HandGenerator handGenerator = new HandGenerator();
        IPack fullPack = testUtils.createPack();

        AbstractMap.SimpleEntry<IPack, IPack> packHandPair = handGenerator.generateHandAndRemoveImmutable(fullPack, 5);

        assertThat(packHandPair.getValue().getCards()).hasSize(5);
    }

    @Test
    void testGenerateHandAndRemoveMutable() {
        HandGenerator handGenerator = new HandGenerator();
        IPack fullPack = testUtils.createPack();

        IPack hand = handGenerator.generateHandAndRemoveMutable(fullPack, 5);

        assertThat(hand.getCards()).hasSize(5);
        assertThat(fullPack.getCards()).hasSize(47);

        Set<Card> handCardSetCopy = new TreeSet<>(hand.getCards());
        handCardSetCopy.retainAll(fullPack.getCards());

        assertThat(handCardSetCopy).isEmpty();
    }
}