package org.srwcrw.poker.texasholdem.handclassifer;

import org.junit.jupiter.api.Test;
import org.srwcrw.poker.texasholdem.test.TestUtils7Card;
import org.srwcrw.poker.texasholdem.collections.Hand7Card;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;

import static org.assertj.core.api.Assertions.assertThat;

class Poker7CardHandClassifierTest {
    private Poker7CardHandClassifier poker7CardHandClassifier = new Poker7CardHandClassifier();

    @Test
    public void testClassifyOnePair() {
        Hand7Card onePairHand = TestUtils7Card.createOnePairA();

        HandType5Cards handType5CardsA = poker7CardHandClassifier.classify(onePairHand);

        assertThat(handType5CardsA).isEqualTo(HandType5Cards.OnePair);
    }

    @Test
    public void testClassifyNotOnePair() {
        Hand7Card highCardHand = TestUtils7Card.createHighCardA();

        HandType5Cards handType5CardsA = poker7CardHandClassifier.classify(highCardHand);

        assertThat(handType5CardsA).isNotEqualTo(HandType5Cards.OnePair);
    }
}