package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class NMultiplyBotEasy extends Bot{

    private boolean isPrime(int number) {
        return number > 2
                && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(n -> (number % n == 0));
    }


    DiceOutputDTO getDicesToThrow(DiceInputDTO input) {
        DiceOutputDTO result = new DiceOutputDTO();
        result.setNumToFinishGame(input.getNumToFinishGame());

        int dicesAvailabletoThrow = input.getMyInput().size();

        Random r = new Random();
        int dicesToPutAside = r.nextInt(dicesAvailabletoThrow);

        List<Integer> dicesToThrow = new ArrayList<>(input.getMyInput());

        int to_Remove;
        for (int i = 0; i < dicesToPutAside; i++) {
            to_Remove = r.nextInt(dicesToThrow.size());
            if (result.getNumToFinishGame()%dicesToThrow.get(to_Remove)==0 &&

                    ((!isPrime(result.getNumToFinishGame()/dicesToThrow.get(to_Remove)) && result.getNumToFinishGame()/dicesToThrow.get(to_Remove)>7)
                            || result.getNumToFinishGame()/dicesToThrow.get(to_Remove)<7) &&

                    (Math.pow(6,dicesToThrow.size() - 1) >= result.getNumToFinishGame() / dicesToThrow.get(to_Remove))){
                        result.divideNumToFinishGame(dicesToThrow.get(to_Remove));
                        dicesToThrow.remove(to_Remove);
                    }
        }

        result.setDicesToThrow(dicesToThrow);


        return result;
    }

}
