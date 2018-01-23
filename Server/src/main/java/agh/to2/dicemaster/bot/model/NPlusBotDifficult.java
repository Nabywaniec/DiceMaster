package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class NPlusBotDifficult extends Bot {

    private double probability = 0.0;

    private int getCounterOfSatisfactorySets(List<Integer> dices ,int toBeThrown, int sumFin, int counter) {

        if(dices.size()==toBeThrown){
            for (Integer dice : dices) {
                sumFin -= dice;
            }
            if(sumFin==0) return ++counter;
        }

        List<Integer> dicesNext = new ArrayList<>();
        for (int i = 1; i <=6 ; i++) {
            dicesNext.add(i);
            counter += getCounterOfSatisfactorySets(dicesNext ,toBeThrown, counter, sumFin);
        }

        return counter;

    }

    private double calculateProbability(List<Integer> dices, int toBeThrown, int sumFin, int counter) {

        return getCounterOfSatisfactorySets(dices, toBeThrown, sumFin, 0) / Math.pow(6,toBeThrown);

    }

    private boolean[] getBestThrowMask(List<Integer> dices, int sumFin) {

        int bestMask = 0;
        double highestProbability = 0.0;


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
                currentProbability = calculateProbability(toBeThrown, 0, currentSum);
            if(currentProbability > highestProbability){
                highestProbability = currentProbability;
            }

        }

    }

    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input) {

        List<Integer> allDices = input.getMyInput();
        result.setMyInput(allDices);
        HashMap<Integer, Integer> dicesToThrow = input.getDicesRerolled();


        result.setDicesToThrow(dicesToThrow);

        return result;
    }


}

