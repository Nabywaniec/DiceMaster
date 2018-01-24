package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.*;

public class NPlusBotDifficult extends Bot {

    private double probability = 0.0;

    private int getCounterOfSatisfactorySets(int[] dices, int toBeThrown, int sumFin, int counter, int size) {

        if (size == toBeThrown) {
            for (Integer dice : dices) {

                sumFin -= dice;
            }
            if (sumFin == 0) return ++counter;
            else return counter;
        }

        for (int i = 1; i <= 6; i++) {
            dices[size] = i;
            counter = getCounterOfSatisfactorySets(dices, toBeThrown, sumFin, counter, size + 1);
        }

        return counter;

    }

    private double calculateProbability(int toBeThrown, int sumFin) {

        return getCounterOfSatisfactorySets(new int[toBeThrown], toBeThrown, sumFin, 0, 0) / Math.pow(6, toBeThrown);

    }

    private Map<Integer, Integer> getBestThrowMask(List<Integer> dices, int sumFin) {

        HashMap<Integer, Integer> dicesToBeThrown = new HashMap<>();
        double highestProbability = 0.0;
        int bestMask = 0;

        for (int i = 0; i <= 32; i++) {
            double currentProbability = 0.0;
            int maskInt = i;
            int currentSum = sumFin;
            int toBeThrown = 0;
            for (int j = 0; j < 5; j++) {
                if (maskInt % 2 == 1) {
                    toBeThrown++;
                    currentSum = currentSum - dices.get(j);
                }
                maskInt = maskInt / 2;
            }

            if (currentSum > 0)
                currentProbability = calculateProbability(toBeThrown, currentSum);
            if (currentProbability > highestProbability) {
                highestProbability = currentProbability;
                bestMask = i;
            }

        }

        for (int j = 0; j < 5; j++) {
            if (bestMask % 2 == 1) {
                dicesToBeThrown.put(4-j, dices.get(4-j));
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
                this.getBestThrowMask(allDices, input.getScoreToWin());
        result.setDicesToThrow(dicesToThrow);
        return result;

    }


}

