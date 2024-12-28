package org.srwcrw.poker.texasholdem.components;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Aspect
@Component
public class TexasHoldemComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(TexasHoldemComponent.class);

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

    private static final int countByHandValues = 5;
    private final int handLoopCounter = 0;


    @SuppressWarnings("unused")
    public double monteCarloOneOpponent() {
        Double durationMs = null;

        AbstractMap.SimpleEntry<IPack, IPack> handPair;

        final int opponentCount = 1;

        Value firstPlayerCard = Value.Four;

        boolean matchingSuit = false;
        List<Value> valueList = List.of(Value.values());

        List<Hand5Card> twoPairDrawCommunityList = new ArrayList<>();
        List<List<Hand2Card>> twoPairDrawPlayerList = new ArrayList<>();
        List<List<Hand5Card>> twoPairDrawList = new ArrayList<>();

        LOGGER.info("Number of opponents = " + opponentCount);

        for (Value kickerValue : valueList) {
            for (int loopCounter = 1; loopCounter <= 1; ++loopCounter) {

                int handsWonCount = 0;
                int handsDrawnCount = 0;
                int handsLostCount = 0;

                final StopWatch stopWatch = new StopWatch();
                stopWatch.start();

                IPack fullPack = packGenerator.generateFullPack();
                Map.Entry<IPack, Hand2Card> packPlayerHandPair = createPlayerHand2(fullPack, firstPlayerCard, kickerValue, matchingSuit);

                if (packPlayerHandPair == null) {
                    continue;
                }

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

                    handPair = HAND_GENERATOR.generateHandAndRemoveImmutable(pack50, 2);
                    opponentHandList.add(handPair.getValue());
                    IPack pack48 = handPair.getKey();

                    for (int counter = 2; counter <= opponentCount; ++counter) {
                        handPair = HAND_GENERATOR.generateHandAndRemoveImmutable(pack48, 2);
                        pack48 = handPair.getKey();

                        opponentHandList.add(handPair.getValue());

//                    if (pack50.getSize() != 50) {
//                        throw new RuntimeException("EEEKKKK!! remaining pack size is not 50");
//                    }

//                    pack48 = handPair.getKey();
//                    if (pack48.getSize() != 48) {
//                        throw new RuntimeException("EEEKKKK!! remaining pack size is not 48");
//                    }
                    }

                    if (pack48.getSize() != 50 - (opponentCount * 2)) {
                        throw new RuntimeException("EEEKKKK!! remaining pack size is not " + (50 - (opponentCount * 2)));
                    }

                    IPack communityCards = HAND_GENERATOR.generateHandAndRemoveImmutable(pack48, 5).getValue();
                    Hand5Card communityCardsHand = CONVERTER_HAND_5_CARD.convert(communityCards);

                    List<Hand5Card> playerAllPossibleHands = POKER_TEXAS_HOLDEM_UTILS.generateAllPossibleHands(communityCardsHand, playerHand2Card);

                    Hand5Card playerBestHand = POKER_TEXAS_HOLDEM_UTILS.findBestHandWithCommunityCards(communityCardsHand, playerHand2Card);

                    int winLoseComparison = 0;

                    for (int opponentIndex = 0; opponentIndex < opponentCount; ++opponentIndex) {
                        Hand5Card opponentBestHand = POKER_TEXAS_HOLDEM_UTILS.findBestHandWithCommunityCards(communityCardsHand, CONVERTER_HAND_2_CARD.convert(opponentHandList.get(opponentIndex)));
                        winLoseComparison = POKER_5_CARD_ACE_HIGH_LOW_COMPARATOR.compare(playerBestHand, opponentBestHand);

                        if (winLoseComparison < 0) {
                            break;
                        }
                    }

                    if (winLoseComparison > 0) {
                        ++handsWonCount;
                    } else if (winLoseComparison == 0) {
                        ++handsDrawnCount;

                        debugInfo();
                    } else {
                        ++handsLostCount;
                    }
                }

                stopWatch.stop();

                durationMs = stopWatch.getTotalTimeSeconds();
//                LOGGER.info(String.format("Execution time = %4.3f", stopWatch.getTotalTimeSeconds()));
//                LOGGER.warn(String.format("Execution time = %4.3f", durationMs));

                double handsWonRatio = (double) handsWonCount / ((double) handCount);
                double handsDrawnRatio = (double) handsDrawnCount / ((double) handCount);
                double handsLostRatio = (double) handsLostCount / ((double) handCount);

                if (handsWonCount + handsDrawnCount + handsLostCount != handCount) {
                    throw new RuntimeException("EEEEKKEKEKKEK!!!!");
                }

                Double handsWonPercentage = handsWonRatio * 100;
                Double handsDrawnPercentage = handsDrawnRatio * 100;
                Double handsLostPercentage = handsLostRatio * 100;

                LOGGER.info(String.format("Player win/draw/lose percentages = %2.1f%% / %2.1f%% / %2.1f%% (%d iterations)", handsWonPercentage, handsDrawnPercentage, handsLostPercentage, handCount));

                LOGGER.info("Card constructor count = {} ", Card.constructorCount);
            }
        }

        return durationMs;
    }


    private Map.Entry<IPack, Hand2Card> createPlayerHand2(IPack fullPack, Value firstPlayerCardValue, Value kickerValue, boolean matchingSuit) {
        if (matchingSuit && firstPlayerCardValue.equals(kickerValue)) {
            return null;
        }

        Card playerCard1 = new Card(Suit.Spades, firstPlayerCardValue);
        Card playerCard2 = new Card(Suit.Clubs, kickerValue);

        if (matchingSuit) {
            playerCard2 = new Card(Suit.Spades, kickerValue);
        }

        LOGGER.info("Player hand = " + playerCard1 + " , " + playerCard2);

        fullPack = fullPack.removeCard(playerCard1);
        fullPack = fullPack.removeCard(playerCard2);

        SortedSet<Card> playerCardSortedSet = new TreeSet<>();
        playerCardSortedSet.add(playerCard1);
        playerCardSortedSet.add(playerCard2);

        Hand2Card playerHand2Card = new Hand2Card(playerCardSortedSet);

        return new AbstractMap.SimpleEntry<>(fullPack, playerHand2Card);
    }

    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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
        //noinspection UnnecessaryLocalVariable
        String result = formatter.format("%20s Player = %s%% %5s %s",
                handType5Card,
                StringUtils.leftPad(playerPercentageString, 5),
                " ",
                opponentPercentageString
        ).toString();

        return result;
    }

    private void debugInfo() {
        //                    HandType5Cards playerHandType5Cards = POKER_5_CARD_HAND_CLASSIFIER.classify(playerBestHand);
//
//                    if (playerHandType5Cards == HandType5Cards.ThreeOfAKind && opponentHandList.get(0).getNthCard(0).getValue() != Value.King && opponentHandList.get(0).getNthCard(1).getValue() != Value.King) {
//                    if (playerHandType5Cards == HandType5Cards.Flush && playerBestHand.getNthCard (0).getSuit() == Suit.Clubs && (opponentHandList.get(0).getNthCard(0).getSuit() == Suit.Clubs || opponentHandList.get(0).getNthCard(1).getSuit() == Suit.Clubs)) {
//                    if (playerHandType5Cards == HandType5Cards.Flush && playerBestHand.getNthCard(0).getSuit() == Suit.Spades && (opponentHandList.get(0).getNthCard(0).getSuit() == Suit.Spades || opponentHandList.get(0).getNthCard(1).getSuit() == Suit.Spades)) {
//                    if (playerHandType5Cards == HandType5Cards.Flush && playerBestHand.getNthCard(0).getSuit() == Suit.Spades) {
//                        LOGGER.info("*** DRAWING HAND *** DRAWING HAND *** - Player / Opponent hole cards are : " + playerHand2Card + " -> " + opponentHandList.get(0));
//                        LOGGER.info("*** DRAWING HAND *** DRAWING HAND *** - Community cards are : " + communityCards);
//                        LOGGER.info("*** DRAWING HAND *** DRAWING HAND *** - Hand type = " + playerHandType5Cards);
//                        LOGGER.info();
//                    }
//
//                    if (playerHandType5Cards == HandType5Cards.ThreeOfAKind) {
//                        List<Hand5Card> handsPair = new ArrayList<>();
//                        handsPair.add(playerBestHand);
//                        handsPair.add(opponentBestHand);
//
//                        twoPairDrawList.add(handsPair);
//
//                        List<Hand2Card> playerHoleCards = new ArrayList<>();
//                        playerHoleCards.add(playerHand2Card);
//                        playerHoleCards.add(CONVERTER_HAND_2_CARD.convert(opponentHandList.get(0)));
//
//                        twoPairDrawPlayerList.add(playerHoleCards);
//
//                        twoPairDrawCommunityList.add(communityCardsHand);
//                    }
    }

    private void outputCSV() {
        Map<String, String> AUTHOR_BOOK_MAP = new HashMap<>() {
            {
                put("Dan Simmons", "Hyperion");
                put("Douglas Adams", "The Hitchhiker's Guide to the Galaxy");
            }
        };

//        String[] HEADERS = { "author", "title"};
        String[] HEADERS = { "Your card", "Opponent Card 1", "Win", "Draw", "Lose"};

        StringWriter sw = new StringWriter();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .build();

        try (final CSVPrinter printer = new CSVPrinter(sw, csvFormat)) {
            AUTHOR_BOOK_MAP.forEach((author, title) -> {
                try {
                    printer.printRecord(author, title);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
