package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NMultiplyBotDifficult extends Bot {

    List<Integer> dicesToThrow = new ArrayList<>();

    private boolean isPrime(int number) {
        return number > 2
                && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(n -> (number % n == 0));
    }

    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input) {

        // result.setNumToFinishGame(input.getNumToFinishGame());
        List<Integer> allDices = input.getMyInput();

        int numToTarget = result.getNumToFinishGame();
        int to_Remove;
        for (int i = 0; i < dicesToThrow.size(); i++) {

            if (numToTarget % allDices.get(i) == 0 &&

                    ((!isPrime(numToTarget / dicesToThrow.get(i)) && numToTarget / dicesToThrow.get(i) > 7)
                            || numToTarget / dicesToThrow.get(i) < 7) &&

                    (Math.pow(6, dicesToThrow.size() - 1) >= numToTarget / dicesToThrow.get(i))) {

                result.divideNumToFinishGame(dicesToThrow.get(i));
                dicesToThrow.remove(i);

            }
        }

        result.setDicesToThrow(dicesToThrow);

        return result;
    }

}
