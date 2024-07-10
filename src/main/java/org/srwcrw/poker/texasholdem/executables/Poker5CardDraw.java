package org.srwcrw.poker.texasholdem.executables;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.generators.ConverterHand5Card;
import org.srwcrw.poker.texasholdem.generators.HandGenerator;
import org.srwcrw.poker.texasholdem.generators.PackGenerator;
import org.srwcrw.poker.texasholdem.handclassifer.Poker5CardHandClassifier;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.srwcrw.poker.texasholdem.entities.HandType5Cards.OnePair;

public class Poker5CardDraw {
    public final PackGenerator packGenerator = new PackGenerator();
    public final HandGenerator handGenerator = new HandGenerator();

    public final Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();

    public final ConverterHand5Card converterHand5Card = new ConverterHand5Card();

    public int onePairCount = 0;

//    private static final int iterationCount = 100 * 1000 * 1000;
    private static final int iterationCount = 10 * 1000 * 1000;
//    private static final int iterationCount = 1 * 1000 * 1000;
//    private static final int iterationCount = 100 * 1000;
//    private static final int iterationCount = 10 * 1000;
//    private static final int iterationCount = 1000;


    public static void main(String[] args) {
        long nanoStart = System.nanoTime();

        Map<HandType5Cards, Integer> handType5CardsCount = new HashMap<>();

        Poker5CardDraw poker5CardDraw = new Poker5CardDraw();

        IPack fullPack = poker5CardDraw.packGenerator.generateFullPack();

        for (int counter = 1; counter <= iterationCount; ++counter) {
            IPack iPack5Card = poker5CardDraw.handGenerator.generateHandPackNoModify(fullPack, 5);
            Hand5Card hand5Card = poker5CardDraw.converterHand5Card.convert(iPack5Card);
            HandType5Cards handType5Cards = poker5CardDraw.poker5CardHandClassifier.classify(hand5Card);

            handType5CardsCount.compute(handType5Cards, (k, v) -> v == null ? 1 : v + 1);
        }

        long nanoEnd = System.nanoTime();

        double durationNanos = nanoEnd - nanoStart;
        double durationSeconds = durationNanos / 1e9;

        for (HandType5Cards handType5Cards : handType5CardsCount.keySet().stream().sorted().collect(Collectors.toList())) {
            Integer count = handType5CardsCount.get(handType5Cards);
            double ratio = (double) count / iterationCount * 100;

            System.out.printf(handType5Cards + " percentage = %10.5f\n", ratio);
        }

        System.out.printf("Duration = %10.3f seconds", durationSeconds);

        // 2024-05-22 12:16 - SWright - Duration (Laptop) - 1e7 iterations Duration = 54.6492517 , 55.0566367 , 58.1787818 , 54.3512108 seconds
        // 2024-05-26 20:50 - SWright - Sample size = 4, Average = 40.32240485

        double onePairRatio = (double) handType5CardsCount.get(OnePair) / (double) iterationCount;

        if (Math.abs(onePairRatio - 0.4225) > 1e-3) {
            throw new RuntimeException("One Pair percentage = " + onePairRatio * 100.0 + " expected to be approximately 42.25%");
        }
    }
}
