package org.srwcrw.poker.texasholdem.executables;

import org.springframework.beans.factory.annotation.Autowired;
import org.srwcrw.poker.texasholdem.Statistics;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.components.PackGenerator;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.generators.Converter;
import org.srwcrw.poker.texasholdem.generators.ConverterHand5Card;
import org.srwcrw.poker.texasholdem.generators.ConverterHand7Card;
import org.srwcrw.poker.texasholdem.generators.HandGenerator;
import org.srwcrw.poker.texasholdem.handclassifer.Poker5CardHandClassifier;
import org.srwcrw.poker.texasholdem.handclassifer.Poker7CardHandClassifier;
import org.srwcrw.poker.texasholdem.handclassifer.PokerCardClassifier;

import java.util.AbstractMap;

import static org.srwcrw.poker.texasholdem.entities.HandType5Cards.HighestCard;
import static org.srwcrw.poker.texasholdem.entities.HandType5Cards.OnePair;

public class Main {
    @Autowired
    private PackGenerator packGenerator;

    private static final HandGenerator handGenerator = new HandGenerator();

    private static final Poker7CardHandClassifier poker7CardHandClassifier = new Poker7CardHandClassifier();
    private static final ConverterHand7Card converterHand7Card = new ConverterHand7Card();

    private static final Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();
    private static final ConverterHand5Card converterHand5Card = new ConverterHand5Card();

    private final int iterationCount = 1000;
//    private final int iterationCount = 1000 * 1000;
//    private final int iterationCount = 5 * 1000 * 1000;


    public static void main(String[] args) {
        Main main = new Main();

        double startNanos = System.nanoTime();

        IPack fullPack = main.packGenerator.generateFullPack();

        AbstractMap.SimpleEntry<IPack, IPack> handPair = handGenerator.generateHandAndRemoveImmutable(fullPack, 2);

        System.out.println("Full pack size = " + fullPack.getCards().size());
        System.out.println("Hand = " + handPair.getValue());

        Statistics hand5CardsStatistics = main.runTest(fullPack, converterHand5Card, poker5CardHandClassifier, 5);
        Statistics hand7CardsStatistics = main.runTest(fullPack, converterHand7Card, poker7CardHandClassifier, 7);

        main.calculateProbabilities(hand7CardsStatistics.noPairCount, hand7CardsStatistics.onePairCount, hand5CardsStatistics.noPairCount, hand5CardsStatistics.onePairCount, startNanos);
    }

    private <T extends IPack> Statistics runTest(IPack fullPack, Converter<T> converter, PokerCardClassifier<T> cardClassifier, int numberOfCards) {
        IPack handPair;

        long noPairCount = 0;
        long onePairCount = 0;

        for (int counter = 1; counter < iterationCount; ++counter) {
            handPair = handGenerator.generateHandPackNoModify(fullPack, numberOfCards);

            T hands = converter.convert(handPair);
            HandType5Cards handType5Cards = cardClassifier.classify(hands);

            if (OnePair.equals(handType5Cards)) {
                ++onePairCount;
            } else if (HighestCard.equals(handType5Cards)) {
                ++noPairCount;
            }
        }

        return new Statistics(noPairCount, onePairCount);
    }

    private void calculateProbabilities(double noPairCount, double onePairCount, double hand5noPairCount, double hand5onePairCount, double startNanos) {
        double noPairPercentage = noPairCount / (double) iterationCount * 100.0;
        double onePairPercentage = onePairCount / (double) iterationCount * 100.0;

        double hand5noPairPercentage = hand5noPairCount / (double) iterationCount * 100.0;
        double hand5onePairPercentage = hand5onePairCount / (double) iterationCount * 100.0;

        double endNanos = System.nanoTime();

        double durationNanos = endNanos - startNanos;
        double durationMillis = durationNanos / 1e6;

        outputResults(noPairPercentage, onePairPercentage, hand5noPairPercentage, hand5onePairPercentage, durationMillis);
    }

    private void outputResults(double noPairPercentage, double onePairPercentage, double hand5noPairPercentage,
                               double hand5onePairPercentage, double durationMillis) {
        System.out.printf("7 Cards No pair percentage = %10.3f%%", noPairPercentage);
        System.out.println();
        System.out.printf("7 Cards One pair percentage = %10.3f%%", onePairPercentage);
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------------------------------------");
        System.out.println();

        System.out.printf("5 Cards No pair percentage = %10.3f%%", hand5noPairPercentage);
        System.out.println();
        System.out.printf("5 Cards One pair percentage = %10.3f%%", hand5onePairPercentage);
        System.out.println();

        System.out.printf("Duration seconds = %10.3f", durationMillis / 1e3);
        System.out.println();
    }
}
