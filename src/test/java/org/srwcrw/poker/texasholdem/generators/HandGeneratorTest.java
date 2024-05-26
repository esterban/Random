package org.srwcrw.poker.texasholdem.generators;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HandGeneratorTest {
    private final TestUtils testUtils = new TestUtils();

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

        Set<Card> handCardSetCopy = new HashSet<>(hand.getCards());
        handCardSetCopy.retainAll(fullPack.getCards());

        assertThat(handCardSetCopy).isEmpty();
    }
}