package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NMultiplyBotDifficult extends Bot{

    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input){

        DiceOutputDTO result = new DiceOutputDTO();
        result.setNumToFinishGame(input.getNumToFinishGame());

        int dicesAvailabletoThrow = input.getMyInput().size();

        Random r = new Random();
        int dicesToPutAside = r.nextInt(dicesAvailabletoThrow);

        List<Integer> dicesToThrow = new ArrayList<>(input.getMyInput());



        result.setDicesToThrow(dicesToThrow);


        return result;

    }

}
