package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.List;

public class NMultiplyBotEasy extends Bot{

    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input){
        DiceOutputDTO result = new DiceOutputDTO();


        //FIXME mocked result prepared
        List<Integer> tmp = input.getMyInput();
        result.setDicesToThrow(tmp);


        return result;
    }

}
