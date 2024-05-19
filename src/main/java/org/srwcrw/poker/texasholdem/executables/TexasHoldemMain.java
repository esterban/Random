package org.srwcrw.poker.texasholdem.executables;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.generators.ConverterHand5Card;
import org.srwcrw.poker.texasholdem.generators.HandGenerator;
import org.srwcrw.poker.texasholdem.generators.IHandGenerator;
import org.srwcrw.poker.texasholdem.generators.PackGenerator;
import org.srwcrw.poker.texasholdem.handclassifer.Poker5CardHandClassifier;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class TexasHoldemMain {
    private static PackGenerator packGenerator = new PackGenerator();
    private static HandGenerator handGenerator = new HandGenerator();
    private static Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();
    private static ConverterHand5Card converterHand5Card = new ConverterHand5Card();

    public static void main(String[] args) {
        IPack fullPack = packGenerator.generateFullPack();

        AbstractMap.SimpleEntry<IPack, IPack> handPair;
//        System.out.println("Full pack size = " + fullPack.getCards().size());
//        System.out.println("Hand = " + handPair.getValue());

        final int numberOfPlayers = 5;

        List<IPack> playerHandList = new ArrayList<>();

        for (int counter = 1; counter < numberOfPlayers; ++counter) {
            handPair = handGenerator.generateHandAndRemoveImmutable(fullPack, 2);
            fullPack = handPair.getKey();

            playerHandList.add(handPair.getValue());

//            Hand5Card hand5Card = converterHand5Card.convert(handPair.getValue());
//            HandType5Cards handType5Cards = poker5CardHandClassifier.classify(hand5Card);

//            System.out.println("Hand of " + handPair.getValue() + " , -> type is = " + handType5Cards);
        }

        System.out.println("Player hands are = " + playerHandList);

        IPack communityCards = handGenerator.generateHandAndRemoveImmutable(fullPack, 5).getValue();

    }
}
