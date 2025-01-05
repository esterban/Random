package org.srwcrw.poker.texasholdem.chatgpt.counterfactualregretminimisation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PokerAI {

    private static final int SIMULATION_COUNT = 1000;
    private Map<String, double[]> regretSum = new HashMap<>();
    private Map<String, double[]> strategySum = new HashMap<>();

    public static void main(String[] args) {
        PokerAI bot = new PokerAI();
        String[] playerHand = {"AS", "KH"}; // Example hand
        String[] communityCards = {"10D", "JC", "QD"}; // Example community cards


        String decision = bot.makeDecision(playerHand, communityCards, 100, 200);
        System.out.println("Bot's decision: " + decision);
    }

    public String makeDecision(String[] hand, String[] communityCards, int pot, int bet) {
        String infoSet = generateInfoSet(hand, communityCards, pot, bet);
        double[] strategy = getStrategy(infoSet);
        double randomValue = new Random().nextDouble();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < strategy.length; i++) {
            cumulativeProbability += strategy[i];
            if (randomValue < cumulativeProbability) {
                return getActionName(i);
            }
        }
        return "FOLD"; // Default fallback
    }

    private String generateInfoSet(String[] hand, String[] communityCards, int pot, int bet) {
        return Arrays.toString(hand) + "|" + Arrays.toString(communityCards) + "|Pot:" + pot + "|Bet:" + bet;
    }

    private double[] getStrategy(String infoSet) {
        double[] regret = regretSum.getOrDefault(infoSet, new double[]{0.0, 0.0, 0.0}); // FOLD, CALL, RAISE
        double[] strategy = new double[3];
        double normalizingSum = 0;

        for (int i = 0; i < regret.length; i++) {
            strategy[i] = Math.max(regret[i], 0);
            normalizingSum += strategy[i];
        }

        for (int i = 0; i < strategy.length; i++) {
            if (normalizingSum > 0) {
                strategy[i] /= normalizingSum;
            } else {
                strategy[i] = 1.0 / strategy.length;
            }
        }

        strategySum.put(infoSet, addArrays(strategySum.getOrDefault(infoSet, new double[3]), strategy));
        return strategy;
    }

    public void trainCFR(int iterations) {
        for (int i = 0; i < iterations; i++) {
            playIteration();
        }
    }

    private void playIteration() {
        // Simulate a game iteration and update regret
        // This is a placeholder; full CFR implementation would explore all decision nodes
    }

    private String getActionName(int index) {
        switch (index) {
            case 0:
                return "FOLD";
            case 1:
                return "CALL";
            case 2:
                return "RAISE";
            default:
                return "FOLD";
        }
    }

    private double[] addArrays(double[] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }
}
