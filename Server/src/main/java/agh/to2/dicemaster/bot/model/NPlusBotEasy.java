package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPlusBotEasy extends Bot{
    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input){

        result.setScoreToWin(input.getScoreToWin());

        int dicesAvailabletoThrow = input.getMyInput().size();

        Random r = new Random();
        int dicesToPutAside = r.nextInt(dicesAvailabletoThrow);

        List<Integer> dicesToThrow = new ArrayList<>(input.getMyInput());

        int to_Remove;
        for (int i=0;i<dicesToPutAside;i++){
            to_Remove = r.nextInt(dicesToThrow.size());
            if ( (dicesToThrow.size()-1)*6 >= result.getScoreToWin()-dicesToThrow.get(to_Remove) &&
                     (dicesToThrow.size()-1)*1 <= result.getScoreToWin()-dicesToThrow.get(to_Remove) ) {   //*1 to show it clearly
                result.subtractNumToFinishGame(dicesToThrow.get(to_Remove));
                dicesToThrow.remove(to_Remove);
            }
        }

        result.setDicesToThrow(dicesToThrow);

        return result;
    }

}





