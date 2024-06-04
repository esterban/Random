package org.srwcrw.poker.texasholdem.executables;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.generators.ConverterHand5Card;
import org.srwcrw.poker.texasholdem.generators.HandGenerator;
import org.srwcrw.poker.texasholdem.generators.PackGenerator;
import org.srwcrw.poker.texasholdem.handclassifer.Poker5CardHandClassifier;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Poker5CardDraw {
    public final PackGenerator packGenerator = new PackGenerator();
    public final HandGenerator handGenerator = new HandGenerator();

    public final Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();

    public final ConverterHand5Card converterHand5Card = new ConverterHand5Card();

    public int onePairCount = 0;

    private static final int iterationCount = 100 * 1000 * 1000;
//    private static final int iterationCount = 10 * 1000 * 1000;
//    private static final int iterationCount = 1 * 1000 * 1000;
//    private static final int iterationCount = 1000;


    public static void main(String[] args) {
        long nanoStart = System.nanoTime();

        Map<HandType5Cards, Integer> handType5CardsCount = new HashMap<>();

        Poker5CardDraw poker5CardDraw = new Poker5CardDraw();

        IPack fullPack = poker5CardDraw.packGenerator.generateFullPack();

        for (int counter = 1; counter <= iterationCount; ++counter) {
            AbstractMap.SimpleEntry<IPack, IPack> packAndHand5 = poker5CardDraw.handGenerator.generateHandAndRemoveImmutable(fullPack, 5);
            Hand5Card hand5Card = poker5CardDraw.converterHand5Card.convert(packAndHand5.getValue());
            HandType5Cards handType5Cards = poker5CardDraw.poker5CardHandClassifier.classify(hand5Card);

            handType5CardsCount.compute(handType5Cards, (k, v) -> v == null ? 1 : v + 1);
        }

        double onePairRatio = (double)poker5CardDraw.onePairCount / (double) iterationCount * 100.0;

        long nanoEnd = System.nanoTime();

        double durationNanos = nanoEnd - nanoStart;
        double durationSeconds = durationNanos / 1e9;

        for (HandType5Cards handType5Cards : handType5CardsCount.keySet().stream().sorted().collect(Collectors.toList())) {
            Integer count = handType5CardsCount.get(handType5Cards);
            double ratio = (double) count / iterationCount * 100;

            System.out.printf(handType5Cards + " percentage = %10.3f\n" , ratio);
        }

        System.out.println("Duration = " + durationSeconds + " seconds");
    }
}
