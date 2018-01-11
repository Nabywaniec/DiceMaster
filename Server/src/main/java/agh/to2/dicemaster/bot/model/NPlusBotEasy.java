package agh.to2.dicemaster.bot.model;


import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NPlusBotEasy extends Bot{
    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input){

        int dicesAvailabletoThrow = input.getMyInput().size();

        Random r = new Random();
        int dicesToPutAside = r.nextInt(dicesAvailabletoThrow);

        List<Integer> dicesToThrow = new ArrayList<>(input.getMyInput());

        int to_Remove;
        for (int i=0;i<dicesToPutAside;i++){
            to_Remove = r.nextInt(dicesToThrow.size());
            dicesToThrow.remove(to_Remove);
        }

//        int sum = 0;
//        for (int i: dicesToThrow) {
//            sum += i;
//        }
//        if (sum+dicesToThrow.size()<suma_do_wyrzucenia)
//            dicesToThrow=input.getMyInput();


        result.setDicesToThrow(dicesToThrow);

        //FIXME mocked result prepared
//        List<Integer> tmp = Arrays.asList(4,5,6);
//        result.setDicesToThrow(tmp);



        return result;
    }
}





