package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.BotType;
import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;
import agh.to2.dicemaster.bot.factory.NMultiplyBotFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by AD on 18.01.2018.
 */
class NPlusBotDifficultTest {

    NMultiplyBotFactory factory = new NMultiplyBotFactory();

    @org.junit.jupiter.api.Test
    void getDicesToThrow() {

        NMultiplyBotDifficult bot=null;
        try {
            bot = (NMultiplyBotDifficult)
                    factory.createBot(BotType.DIFFICULT);
        } catch (Exception e) {

        }

        DiceOutputDTO output = new DiceOutputDTO(30);

        HashMap<Integer, Integer> dicesToThrow = new HashMap<>();
        dicesToThrow.put(5, 1);

        output.setMyInput(Arrays.asList(6, 6, 6, 6, 1));
        output.setDicesToThrow(dicesToThrow);
        output.setScoreLeft(5);

        DiceInputDTO input = new DiceInputDTO(30);
        input.setMyInput(Arrays.asList(6, 6, 6, 6, 1));


        HashMap<Integer, Integer> map2 = bot.getDicesToThrow(input).getDicesToThrow();

        Iterator it = map2.entrySet().iterator();
        Iterator it2 = dicesToThrow.entrySet().iterator();

        assertEquals(map2.size(), dicesToThrow.size());
        Map.Entry pair;
        Map.Entry pair2;
        for (int j = 0; j < map2.size(); j++) {
            pair = (Map.Entry) it.next();
            pair2 = (Map.Entry) it2.next();
            assertEquals((Integer)pair.getKey(), (Integer)pair2.getKey());
        }


    }

}