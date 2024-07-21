package org.srwcrw.poker.texasholdem.collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class})
class CollectionUtilsTest {
    @Autowired
    private TestUtils testUtils;

    @Test
    void testGenerateCardListFromPack() {
        CollectionUtils collectionUtils = new CollectionUtils();

        IPack pack = testUtils.createPack();

        List<Card> handCardList = collectionUtils.generateCardListFromPack(pack, 2);
        Set<Card> handCardSet = new TreeSet<>(handCardList);

        assertThat(handCardList).hasSize(2);
        assertThat(handCardSet).hasSize(2);
        assertThat(pack.getCards()).hasSize(52);
    }
}