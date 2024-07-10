package org.srwcrw.poker.texasholdem.executables;

import org.srwcrw.poker.texasholdem.collections.Hand5Card;
import org.srwcrw.poker.texasholdem.collections.IPack;
import org.srwcrw.poker.texasholdem.entities.HandType5Cards;
import org.srwcrw.poker.texasholdem.generators.ConverterHand2Card;
import org.srwcrw.poker.texasholdem.generators.ConverterHand5Card;
import org.srwcrw.poker.texasholdem.generators.HandGenerator;
import org.srwcrw.poker.texasholdem.generators.PackGenerator;
import org.srwcrw.poker.texasholdem.handclassifer.Poker5CardHandClassifier;
import org.srwcrw.poker.texasholdem.handclassifer.PokerTexasHoldemClassifier;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class TexasHoldemMain {
    private static final PackGenerator packGenerator = new PackGenerator();
    private static final HandGenerator handGenerator = new HandGenerator();
    private static final Poker5CardHandClassifier poker5CardHandClassifier = new Poker5CardHandClassifier();
    private static final ConverterHand5Card converterHand5Card = new ConverterHand5Card();
    private static final ConverterHand2Card converterHand2Card = new ConverterHand2Card();

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
        }

//        System.out.println("Player hands are = " + playerHandList);

        IPack communityCards = handGenerator.generateHandAndRemoveImmutable(fullPack, 5).getValue();
        Hand5Card communityCardsHand = converterHand5Card.convert(communityCards);

        System.out.println("Player 1 cards = " + playerHandList.get(0));
        System.out.println("Community cards = " + communityCards);

        PokerTexasHoldemClassifier pokerTexasHoldemClassifier = new PokerTexasHoldemClassifier(communityCardsHand);

        HandType5Cards handType5Cards1 = pokerTexasHoldemClassifier.classify(converterHand2Card.convert(playerHandList.get(0)));

        System.out.println("handType5Cards1 = " + handType5Cards1);


    }
}
