package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NMultiplyBotDifficult extends Bot {

    // recursion to check all dice sets
    private int getCounterOfSatisfactorySets(int[] dices, int toBeThrown, int sumFin, int counter, int size) {

        if (size == toBeThrown) {
            boolean divisible = true;
            for (Integer dice : dices) {
                if (sumFin % dice != 0) divisible = false;
                sumFin /= dice;
            }
            if (sumFin == 1 && divisible) return ++counter;
            else return counter;
        }

        for (int i = 1; i <= 6; i++) {
            dices[size] = i;
            counter = getCounterOfSatisfactorySets(dices, toBeThrown, sumFin, counter, size + 1);
        }

        return counter;

    }

    private double calculateProbability(int toBeThrown, int sumFin) {

        int counter = getCounterOfSatisfactorySets(new int[toBeThrown], toBeThrown, sumFin, 0, 0);
        return counter / Math.pow(6, toBeThrown);

    }

    private Map<Integer, Integer> getBestDiceToReroll(List<Integer> dices, int valueToRoll) {

        HashMap<Integer, Integer> dicesToBeThrown = new HashMap<>();
        double highestProbability = 0.0;
        int bestMask = 0;

        //checking all 32 possibilities
        for (int i = 0; i <= 31; i++) {
            boolean isDivisible = true;
            double currentProbability;
            int maskInt = i;
            int currentFactor = valueToRoll;
            int toBeThrown = 5;
            for (int j = 0; j < 5 && isDivisible; j++) {
                //if i want dont want to reroll current dice
                if (maskInt % 2 == 0) {
                    toBeThrown--;
                    if (currentFactor % dices.get(4 - j) == 0)
                        currentFactor = currentFactor / dices.get(4 - j);
                    else isDivisible = false;
                }
                maskInt = maskInt / 2;
            }

            if (currentFactor >= 1 && isDivisible) {
                currentProbability = calculateProbability(toBeThrown, currentFactor);
//                System.out.println("i = " + i + "tobeT =" + toBeThrown + " F = " + currentFactor + "prob = " + currentProbability);
                if (currentProbability > highestProbability) {
                    highestProbability = currentProbability;
                    bestMask = i;
                }
            }

        }

        for (int j = 0; j < 5; j++) {
            if (bestMask % 2 == 1) {
                dicesToBeThrown.put(4 - j, dices.get(4 - j));
            }
            bestMask = bestMask / 2;
        }
        return dicesToBeThrown;

    }

    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input) {

        List<Integer> allDices = input.getMyInput();
        result.setMyInput(allDices);
        HashMap<Integer, Integer> dicesToThrow
                = (HashMap<Integer, Integer>)
                this.getBestDiceToReroll(allDices, input.getScoreToWin());
        result.setDicesToThrow(dicesToThrow);
        return result;

    }
}
