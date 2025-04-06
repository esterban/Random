package org.srwcrw.poker.texasholdem.collections;

import org.srwcrw.poker.texasholdem.components.Card;

import java.util.Set;

public class HandProbability {
    private Set<Card> playerCards;
    private int numberOfOpponents;
    private double winProbability;
    private double drawProbability;
    private double loseProbability;
    private long numberOfIterations;

    public HandProbability(Set<Card> playerCards, int numberOfOpponents, double winProbability, double drawProbability, double loseProbability, long numberOfIterations) {
        this.playerCards = playerCards;
        this.numberOfOpponents = numberOfOpponents;
        this.winProbability = winProbability;
        this.drawProbability = drawProbability;
        this.loseProbability = loseProbability;
        this.numberOfIterations = numberOfIterations;
    }

    public Set<Card> getPlayerCards() {
        return playerCards;
    }

    public int getNumberOfOpponents() {
        return numberOfOpponents;
    }

    public double getWinProbability() {
        return winProbability;
    }

    public double getDrawProbability() {
        return drawProbability;
    }

    public double getLoseProbability() {
        return loseProbability;
    }

    public long getNumberOfIterations() {
        return numberOfIterations;
    }
}
