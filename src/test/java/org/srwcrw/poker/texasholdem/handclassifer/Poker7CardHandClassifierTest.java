package org.srwcrw.poker.texasholdem.handclassifer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.srwcrw.poker.texasholdem.collections.Hand7Card;
import org.srwcrw.poker.texasholdem.components.Card;
import org.srwcrw.poker.texasholdem.components.CardFactoryImmutable;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.test.TestUtils;
import org.srwcrw.poker.texasholdem.test.TestUtils7Card;
import org.srwcrw.poker.texasholdem.test.TestUtilsTexasHoldem;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CardFactoryImmutable.class, TestUtilsTexasHoldem.class, TestUtils.class, PackGenerator.class, TestUtils7Card.class})
class Poker7CardHandClassifierTest {
    public static final Logger LOGGER = LogManager.getLogger();

    private Poker7CardHandClassifier poker7CardHandClassifier = new Poker7CardHandClassifier();

    @Autowired
    private TestUtils7Card testUtils7Card;

    @Test
    public void testClassifyOnePair() {
        for (int counter = 1; counter <= 1e6; ++counter) {
            Hand7Card onePairHand = testUtils7Card.createOnePairA();

            HandType5Cards handType5CardsA = poker7CardHandClassifier.classify(onePairHand);

            assertThat(handType5CardsA).isEqualTo(HandType5Cards.OnePair);
        }

        LOGGER.info("Finished Card constructor count = {} ", Card.constructorCount);
    }

    @Test
    public void testClassifyNotOnePair() {
        Hand7Card highCardHand = testUtils7Card.createHighCardA();

        HandType5Cards handType5CardsA = poker7CardHandClassifier.classify(highCardHand);

        assertThat(handType5CardsA).isNotEqualTo(HandType5Cards.OnePair);
    }
}