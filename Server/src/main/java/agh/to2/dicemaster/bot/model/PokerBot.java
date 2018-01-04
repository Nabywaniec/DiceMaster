package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kamkalet on 04.01.2018.
 */
public class PokerBot extends Bot {
    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input) {
        DiceOutputDTO result = new DiceOutputDTO();


        //FIXME mocked result prepared
        List<Integer> tmp = Arrays.asList(4,5,6);
        result.setDicesToThrow(tmp);


        return result;
    }
}
