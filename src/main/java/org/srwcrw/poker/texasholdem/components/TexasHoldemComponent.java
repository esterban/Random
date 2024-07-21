package org.srwcrw.poker.texasholdem.components;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.srwcrw.poker.texasholdem.collections.Hand2Card;
import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.comparator.Poker5CardAceHighLowComparator;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;
import org.srwcrw.poker.texasholdem.generators.ConverterHand2Card;
import org.srwcrw.poker.texasholdem.generators.ConverterHand5Card;
import org.srwcrw.poker.texasholdem.generators.HandGenerator;
import org.srwcrw.poker.texasholdem.handclassifer.Poker5CardHandClassifier;
import org.srwcrw.poker.texasholdem.utils.PokerTexasHoldemUtils;

import java.util.*;

@Aspect
@Component
public class TexasHoldemComponent {
    private static final Poker5CardAceHighLowComparator POKER_5_CARD_ACE_HIGH_LOW_COMPARATOR = new Poker5CardAceHighLowComparator();

    @org.springframework.beans.factory.annotation.Value("#{${texasholdemcomponent.handCount}}")
    private Integer handCount;

    @Autowired
    private PackGenerator packGenerator;

    private static final HandGenerator HAND_GENERATOR = new HandGenerator();
    private static final Poker5CardHandClassifier POKER_5_CARD_HAND_CLASSIFIER = new Poker5CardHandClassifier();
    private static final PokerTexasHoldemUtils POKER_TEXAS_HOLDEM_UTILS = new PokerTexasHoldemUtils();
    private static final ConverterHand5Card CONVERTER_HAND_5_CARD = new ConverterHand5Card();
    private static final ConverterHand2Card CONVERTER_HAND_2_CARD = new ConverterHand2Card();


    @SuppressWarnings("unused")
    public void monteCarloOneOpponent() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        AbstractMap.SimpleEntry<IPack, IPack> handPair;

        final int opponentCount = 1;

        int handsWonCount = 0;
        int handsDrawnCount = 0;
        int handsLostCount = 0;

        IPack fullPack = packGenerator.generateFullPack();
        Map.Entry<IPack, Hand2Card> packPlayerHandPair = createPlayerHand2(fullPack);
        IPack pack50 = packPlayerHandPair.getKey();

        Map<HandType5Cards, Integer> playerHandTypeCounts = new TreeMap<>();
        Map<HandType5Cards, Integer> opponentHandTypeCounts = new TreeMap<>();

        Map<HandType5Cards, Integer> playerDrawnHandTypeCounts = new TreeMap<>();
        Map<HandType5Cards, Integer> opponentDrawnHandTypeCounts = new TreeMap<>();

        Map<Value, Integer> playerHighCardCount = new TreeMap<>();
        Map<Value, Integer> opponentHighCardCount = new TreeMap<>();

        for (int handCounter = 1; handCounter <= handCount; ++handCounter) {
            List<IPack> opponentHandList = new ArrayList<>();

            Hand2Card playerHand2Card = packPlayerHandPair.getValue();
            IPack pack48 = null;

            for (int counter = 1; counter <= opponentCount; ++counter) {
                handPair = HAND_GENERATOR.generateHandAndRemoveImmutable(pack50, 2);

                if (pack50.getSize() != 50) {
                    throw new RuntimeException("EEEKKKK!! remaining pack size is not 50");
                }

                pack48 = handPair.getKey();

                opponentHandList.add(handPair.getValue());

                if (pack48.getSize() != 48) {
                    throw new RuntimeException("EEEKKKK!! remaining pack size is not 48");
                }
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
            } else {
                ++handsLostCount;
            }
        }

        stopWatch.stop();
        System.out.printf("Execution time = %4.3f \n", stopWatch.getTotalTimeSeconds());

        double handsWonRatio = (double) handsWonCount / ((double) handCount);
        double handsDrawnRatio = (double) handsDrawnCount / ((double) handCount);
        double handsLostRatio = (double) handsLostCount / ((double) handCount);

        if (handsWonCount + handsDrawnCount + handsLostCount != handCount) {
            throw new RuntimeException("EEEEKKEKEKKEK!!!!");
        }

        double handsWonPercentage = handsWonRatio * 100;
        double handsDrawnPercentage = handsDrawnRatio * 100;
        double handsLostPercentage = handsLostRatio * 100;

        System.out.println();
        System.out.println();

        System.out.printf("Player win/draw/lose percentages = %2.2f%% / %2.2f%% / %2.2f%% (%d iterators) \n", handsWonPercentage, handsDrawnPercentage, handsLostPercentage, handCount);
        System.out.printf("Player win/draw/lose count = %d / %d / %d  \n", handsWonCount, handsDrawnCount, handsLostCount);

        System.out.println();
        System.out.println();
    }


    private Map.Entry<IPack, Hand2Card> createPlayerHand2(IPack fullPack) {
        // *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS ***
        // *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS ***
        // *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS *** POCKET PAIRS ***
        //        Player win/draw/lose percentages = 49.44% / 1.95% / 48.60% (3e6 iterations)
//        Card playerCard2 = new Card(Suit.Spades, Value.Two);
//        Card playerCard1 = new Card(Suit.Hearts, Value.Two);

//        Player win/draw/lose percentages = 52.94% / 1.50% / 45.56% (10000 iterators)
//        Player win/draw/lose count = 5294 / 150 / 4556
//        Player win/draw/lose percentages = 52.95% / 1.76% / 45.29% (100000 iterators)
//        Player win/draw/lose count = 52947 / 1759 / 45294
//        Player win/draw/lose percentages = 52.98% / 1.78% / 45.24% (300000 iterators)
//        Player win/draw/lose count = 158950 / 5330 / 135720
        Card playerCard2 = new Card(Suit.Spades, Value.Three);
        Card playerCard1 = new Card(Suit.Hearts, Value.Three);

        fullPack = fullPack.removeCard(playerCard1);
        fullPack = fullPack.removeCard(playerCard2);

        SortedSet<Card> playerCardSortedSet = new TreeSet<>();
        playerCardSortedSet.add(playerCard1);
        playerCardSortedSet.add(playerCard2);

        Hand2Card playerHand2Card = new Hand2Card(playerCardSortedSet);

        return new AbstractMap.SimpleEntry<>(fullPack, playerHand2Card);
    }

    private Value getBestCardFromHand5(Hand5Card hand5Card) {
        Value highestValue = Value.Two;

        for (int index = 0; index < 5; ++index) {
            Card card = hand5Card.getNthCard(index);

            if (card.getValue().ordinal() > highestValue.ordinal()) {
                highestValue = card.getValue();
            }
        }

        return highestValue;
    }

    private String formatHandTypePercentageString(double playerPercentage, Double opponentPercentage, HandType5Cards handType5Card) {
        Formatter formatter = new Formatter();
        String playerPercentageString = formatter.format("%2.2f", playerPercentage).toString();

        String opponentPercentageString = "";

        if (opponentPercentage != null) {
            formatter = new Formatter();
            String opponentPercentageStringTemp = formatter.format("%2.2f", opponentPercentage).toString();
            opponentPercentageString = StringUtils.leftPad(opponentPercentageStringTemp, 5);

            formatter = new Formatter();
            opponentPercentageString = formatter.format("Opponent %s%% ", opponentPercentageString).toString();
        }

        formatter = new Formatter();
        String result = formatter.format("%20s Player = %s%% %5s %s",
                handType5Card,
                StringUtils.leftPad(playerPercentageString, 5),
                " ",
                opponentPercentageString
        ).toString();

        return result;
    }
}
