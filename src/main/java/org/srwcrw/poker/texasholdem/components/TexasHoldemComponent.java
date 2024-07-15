package org.srwcrw.poker.texasholdem.components;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.comparator.Poker5CardAceHighLowComparator;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.generators.ConverterHand2Card;
import org.srwcrw.poker.texasholdem.generators.ConverterHand5Card;
import org.srwcrw.poker.texasholdem.generators.HandGenerator;
import org.srwcrw.poker.texasholdem.utils.PokerTexasHoldemUtils;

import java.util.*;

@Aspect
@Component
public class TexasHoldemComponent {
    @Autowired
    private CardFactoryImmutable cardFactoryImmutable;

    private static final Poker5CardAceHighLowComparator POKER_5_CARD_ACE_HIGH_LOW_COMPARATOR = new Poker5CardAceHighLowComparator();

    @Autowired
    private PackGenerator packGenerator;

    private static final HandGenerator HAND_GENERATOR = new HandGenerator();
    //    private static final Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();
    private static final PokerTexasHoldemUtils POKER_TEXAS_HOLDEM_UTILS = new PokerTexasHoldemUtils();
    private static final ConverterHand5Card CONVERTER_HAND_5_CARD = new ConverterHand5Card();
    private static final ConverterHand2Card CONVERTER_HAND_2_CARD = new ConverterHand2Card();


    @SuppressWarnings("unused")
    public void monteCarloOneOpponent() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        AbstractMap.SimpleEntry<IPack, IPack> handPair;

        final int opponentCount = 1;

//        final int handCount = 1000 * 1000;
//        final int handCount = 300 * 1000;
        final int handCount = 100 * 1000;
//        final int handCount = 10 * 1000;
//        final int handCount = 100;

        int handsWonCount = 0;
        int handsDrawnCount = 0;

        IPack fullPack = packGenerator.generateFullPack();
        Map.Entry<IPack, Hand2Card> packPlayerHandPair = createPlayerHand2(fullPack);
        IPack pack50 = packPlayerHandPair.getKey();

        for (int handCounter = 1; handCounter <= handCount; ++handCounter) {
            List<IPack> opponentHandList = new ArrayList<>();

            Hand2Card playerHand2Card = packPlayerHandPair.getValue();
            IPack pack48 = null;

            for (int counter = 1; counter <= opponentCount; ++counter) {
                handPair = HAND_GENERATOR.generateHandAndRemoveImmutable(pack50, 2);
                pack48 = handPair.getKey();

                opponentHandList.add(handPair.getValue());
            }

            IPack communityCards = HAND_GENERATOR.generateHandAndRemoveImmutable(pack48, 5).getValue();
            Hand5Card communityCardsHand = CONVERTER_HAND_5_CARD.convert(communityCards);

            Hand5Card playerBestHand = POKER_TEXAS_HOLDEM_UTILS.findBestHandWithCommunityCards(communityCardsHand, playerHand2Card);
            Hand5Card opponentBestHand = POKER_TEXAS_HOLDEM_UTILS.findBestHandWithCommunityCards(communityCardsHand, CONVERTER_HAND_2_CARD.convert(opponentHandList.get(0)));

            int winLoseComparison = POKER_5_CARD_ACE_HIGH_LOW_COMPARATOR.compare(playerBestHand, opponentBestHand);

            if (winLoseComparison > 0) {
                ++handsWonCount;
            } else if (winLoseComparison == 0) {
                ++handsDrawnCount;
            }
        }

        stopWatch.stop();
        System.out.printf("Execution time = %4.3f \n", stopWatch.getTotalTimeSeconds());

        double handsWonRatio = (double) handsWonCount / handCount;
        double handsDrawnRatio = (double) handsDrawnCount / handCount;
        double handsLostRatio = 1.0 - handsDrawnRatio - handsWonRatio;

        double handsWonPercentage = handsWonRatio * 100;
        double handsDrawnPercentage = handsDrawnRatio * 100;
        double handsLostPercentage = handsLostRatio * 100;

        System.out.printf("Player win/draw/lose percentages = %2.1f%% / %2.1f%% / %2.1f%%  \n", handsWonPercentage, handsDrawnPercentage, handsLostPercentage);
    }


    private Map.Entry<IPack, Hand2Card> createPlayerHand2(IPack fullPack) {
//        Player win/draw/lose percentages = 53.046% / 6.486% / 40.468%
//        Card playerCard1 = new Card(Suit.Hearts, Value.Jack);
//        Card playerCard2 = new Card(Suit.Clubs, Value.Queen);

//        Player win/draw/lose percentages = 49.211% / 7.367% / 43.422%
//        Card playerCard1 = new Card(Suit.Hearts, Value.Six);
//        Card playerCard2 = new Card(Suit.Clubs, Value.King);

//        Player win/draw/lose percentages = 55.032% / 5.899% / 39.069%
//        Card playerCard1 = new Card(Suit.Hearts, Value.Ten);
//        Card playerCard2 = new Card(Suit.Clubs, Value.King);

//        Player win/draw/lose percentages = 57.777% / 5.464% / 36.759%
//        Card playerCard1 = new Card(Suit.Hearts, Value.Ten);
//        Card playerCard2 = new Card(Suit.Hearts, Value.King);

        // *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS ***
        // *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS ***
        // *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS ***
//        Player win/draw/lose percentages = 50.188% / 1.800% / 48.012%
//        Card playerCard1 = new Card(Suit.Hearts, Value.Two);
//        Card playerCard2 = new Card(Suit.Clubs, Value.Two);

        // SWright - 2024/07/11 - @1e6 iterations - error is still too high, execution time =  416.105s :/ NOT GOOD
//        Player win/draw/lose percentages = 53.9% / 1.7% / 44.4%
//        Player win/draw/lose percentages = 53.2% / 1.7% / 45.0%
        Card playerCard1 = cardFactoryImmutable.createCard(Suit.Hearts, Value.Three);
        Card playerCard2 = cardFactoryImmutable.createCard(Suit.Clubs, Value.Three);


//        Player win/draw/lose percentages = 81.973% / 0.671% / 17.356%
//        Card playerCard1 = new Card(Suit.Hearts, Value.King);
//        Card playerCard2 = new Card(Suit.Clubs, Value.King);

        fullPack = fullPack.removeCard(playerCard1);
        fullPack = fullPack.removeCard(playerCard2);

        SortedSet<Card> playerCardSortedSet = new TreeSet<>();
        playerCardSortedSet.add(playerCard1);
        playerCardSortedSet.add(playerCard2);

        Hand2Card playerHand2Card = new Hand2Card(playerCardSortedSet);

        return new AbstractMap.SimpleEntry<>(fullPack, playerHand2Card);
    }
}
