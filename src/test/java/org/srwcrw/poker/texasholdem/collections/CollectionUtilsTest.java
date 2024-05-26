package org.srwcrw.poker.texasholdem.collections;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.entities.Card;
import org.srwcrw.poker.texasholdem.test.TestUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionUtilsTest {
    @Test
    void testGenerateCardListFromPack() {
        CollectionUtils collectionUtils = new CollectionUtils();

        IPack pack = TestUtils.createPack();

        List<Card> handCardList = collectionUtils.generateCardListFromPack(pack, 2);
        Set<Card> handCardSet = new HashSet<>(handCardList);

        assertThat(handCardList).hasSize(2);
        assertThat(handCardSet).hasSize(2);
        assertThat(pack.getCards()).hasSize(52);
    }
}