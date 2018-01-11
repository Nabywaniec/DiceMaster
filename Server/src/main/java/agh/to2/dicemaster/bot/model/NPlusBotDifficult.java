package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;
import agh.to2.dicemaster.bot.IllegalHandException;
import agh.to2.dicemaster.bot.helpers.Hand;
import agh.to2.dicemaster.bot.helpers.HandChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPlusBotDifficult extends Bot{
    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input){


        //FIXME mocked result prepared
        List<Integer> tmp = Arrays.asList(4,5,6);
        result.setDicesToThrow(tmp);


        return result;
    }
}

