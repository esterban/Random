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
import org.srwcrw.poker.texasholdem.collections.HandProbability;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.comparator.Poker5CardAceHighLowComparator;
import org.srwcrw.poker.texasholdem.components.generators.ConverterHand2Card;
import org.srwcrw.poker.texasholdem.components.generators.ConverterHand5Card;
import org.srwcrw.poker.texasholdem.components.generators.HandGenerator;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Aspect
@Component
public class TexasHoldemComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(TexasHoldemComponent.class);

    private static final Poker5CardAceHighLowComparator POKER_5_CARD_ACE_HIGH_LOW_COMPARATOR = new Poker5CardAceHighLowComparator();

    @org.springframework.beans.factory.annotation.Value("#{${texasholdemcomponent.matchingSuit}}")
    private boolean matchingSuit;

    @org.springframework.beans.factory.annotation.Value("#{${texasholdemcomponent.handCount}}")
    private Integer handCount;

    @org.springframework.beans.factory.annotation.Value("#{${texasholdemcomponent.opponentCount}}")
    private Integer opponentCount;

    @Autowired
    private PackGenerator packGenerator;

    @Autowired
    private CardFactoryImmutable cardFactoryImmutable;

    @Autowired
    private ConverterHand5Card converterHand5Card;

    private static final HandGenerator HAND_GENERATOR = new HandGenerator();

    @Autowired
    private PokerTexasHoldemUtils pokerTexasHoldemUtils;
    private static final ConverterHand2Card CONVERTER_HAND_2_CARD = new ConverterHand2Card();

    private final List<HandProbability> handProbabilityList = new ArrayList<>();

    private final int handLoopCounter = 1;

    public List<HandProbability> getHandProbabilityList() {
        return handProbabilityList;
    }

    @SuppressWarnings("unused")
    public double monteCarloOneOpponent() {
        double durationMs;

        AbstractMap.SimpleEntry<IPack, IPack> handPair;

//        Value firstPlayerCardValue = Value.Two;
        Value firstPlayerCardValue = Value.Ace;

        List<Value> valueList = List.of(Value.values());

        List<Hand5Card> twoPairDrawCommunityList = new ArrayList<>();
        List<List<Hand2Card>> twoPairDrawPlayerList = new ArrayList<>();
        List<List<Hand5Card>> twoPairDrawList = new ArrayList<>();

        LOGGER.info("Number of opponents = {}", opponentCount);

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (Value kickerValue : valueList) {
            for (int loopCounter = 1; loopCounter <= handLoopCounter; ++loopCounter) {

                int handsWonCount = 0;
                int handsDrawnCount = 0;
                int handsLostCount = 0;


                IPack fullPack = packGenerator.generateFullPack();
//                Map.Entry<IPack, Hand2Card> packPlayerHandPair = createPlayerHand2(fullPack, firstPlayerCardValue, kickerValue, matchingSuit);
                Map.Entry<IPack, Hand2Card> packPlayerHandPair = createPlayerHand2Pair(fullPack, kickerValue);

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
                    }

                    if (pack48.getSize() != 50 - (opponentCount * 2)) {
                        throw new RuntimeException("EEEKKKK!! remaining pack size is not " + (50 - (opponentCount * 2)));
                    }

                    IPack communityCards = HAND_GENERATOR.generateHandAndRemoveImmutable(pack48, 5).getValue();
                    Hand5Card communityCardsHand = converterHand5Card.convert(communityCards);

                    List<Hand5Card> playerAllPossibleHands = pokerTexasHoldemUtils.generateAllPossibleHands(communityCardsHand, playerHand2Card);

                    Hand5Card playerBestHand = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityCardsHand, playerHand2Card);

                    int winLoseComparison = 0;

                    for (int opponentIndex = 0; opponentIndex < opponentCount; ++opponentIndex) {
                        Hand5Card opponentBestHand = pokerTexasHoldemUtils.findBestHandWithCommunityCards(communityCardsHand, CONVERTER_HAND_2_CARD.convert(opponentHandList.get(opponentIndex)));
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

                double handsWonRatio = (double) handsWonCount / ((double) handCount);
                double handsDrawnRatio = (double) handsDrawnCount / ((double) handCount);
                double handsLostRatio = (double) handsLostCount / ((double) handCount);

                if (handsWonCount + handsDrawnCount + handsLostCount != handCount) {
                    throw new RuntimeException("EEEEKKEKEKKEK!!!!");
                }

                double handsWonPercentage = handsWonRatio * 100;
                double handsDrawnPercentage = handsDrawnRatio * 100;
                double handsLostPercentage = handsLostRatio * 100;

                LOGGER.info("{}", String.format("Player win/draw/lose percentages = %2.1f%% / %2.1f%% / %2.1f%% (%d iterations)", handsWonPercentage, handsDrawnPercentage, handsLostPercentage, handCount));

                LOGGER.info("Card constructor count = {} ", Card.constructorCount);


                Set<Card> playerCards = Set.of(cardFactoryImmutable.createCard(Suit.Spades, firstPlayerCardValue),
                        cardFactoryImmutable.createCard(matchingSuit ? Suit.Spades : Suit.Clubs, kickerValue));

                HandProbability handProbability = new HandProbability(playerCards, opponentCount, handsWonPercentage / 100.0, handsDrawnPercentage / 100.0, handsLostPercentage / 100.0, handCount);
                handProbabilityList.add(handProbability);
            }
        }

        stopWatch.stop();
        durationMs = stopWatch.getTotalTimeSeconds();

        return durationMs;
    }


    private Map.Entry<IPack, Hand2Card> createPlayerHand2(IPack fullPack, Value firstPlayerCardValue, Value kickerValue, boolean matchingSuit) {
        if (matchingSuit && firstPlayerCardValue.equals(kickerValue)) {
            return null;
        }

        Card playerCard1 = cardFactoryImmutable.createCard(Suit.Spades, firstPlayerCardValue);
        Card playerCard2 = cardFactoryImmutable.createCard(Suit.Clubs, kickerValue);

        if (matchingSuit) {
            playerCard2 = cardFactoryImmutable.createCard(Suit.Spades, kickerValue);
        }

        LOGGER.info("Player hand = {} , {}", playerCard1, playerCard2);

        fullPack = fullPack.removeCard(playerCard1);
        fullPack = fullPack.removeCard(playerCard2);

        SortedSet<Card> playerCardSortedSet = new TreeSet<>();
        playerCardSortedSet.add(playerCard1);
        playerCardSortedSet.add(playerCard2);

        Hand2Card playerHand2Card = new Hand2Card(playerCardSortedSet);

        return new AbstractMap.SimpleEntry<>(fullPack, playerHand2Card);
    }

    private Map.Entry<IPack, Hand2Card> createPlayerHand2Pair(IPack fullPack, Value firstPlayerCardValue) {
        Card playerCard1 = cardFactoryImmutable.createCard(Suit.Spades, firstPlayerCardValue);
        Card playerCard2 = cardFactoryImmutable.createCard(Suit.Clubs, firstPlayerCardValue);

        LOGGER.info("Player hand = {} , {}", playerCard1, playerCard2);

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
        String[] HEADERS = {"Your card", "Opponent Card 1", "Win", "Draw", "Lose"};

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
                    LOGGER.error("", e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
