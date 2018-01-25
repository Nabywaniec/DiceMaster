package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.BotType;
import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;
import agh.to2.dicemaster.bot.factory.NMultiplyBotFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by AD on 25.01.2018.
 */
class NMultiplyBotDifficultTest {

    private NMultiplyBotFactory factory = new NMultiplyBotFactory();
    @Test
    void getDicesToThrow() {
        NMultiplyBotDifficult bot=null;
        try {
            bot = (NMultiplyBotDifficult)
                    factory.createBot(BotType.DIFFICULT);
        } catch (Exception e) {
            System.exit(1);
        }

        DiceOutputDTO output = new DiceOutputDTO(30);
        bot.setResult(output);

        HashMap<Integer, Integer> dicesToThrow = new HashMap<>();
        dicesToThrow.put(4, 1);


        DiceInputDTO input = new DiceInputDTO(30);
        input.setMyInput(Arrays.asList(2, 3, 1, 1, 4));

        testOutputs(dicesToThrow, bot.getDicesToThrow(input).getDicesToThrow());

        HashMap<Integer, Integer> dicesToThrow2 = new HashMap<>();
        dicesToThrow2.put(1, 1);
        dicesToThrow2.put(4, 1);
        input.setMyInput(Arrays.asList(1, 1, 4, 6, 1));
        input.setScoreToWin(360);
        testOutputs(dicesToThrow2, bot.getDicesToThrow(input).getDicesToThrow());

    }

    private void testOutputs(HashMap<Integer, Integer> expected ,HashMap<Integer, Integer> result){

        Iterator it = result.entrySet().iterator();
        Iterator it2 = expected.entrySet().iterator();

        assertEquals(result.size(),  expected.size());
        Map.Entry pair;
        Map.Entry pair2;
        for (int j = 0; j < result.size(); j++) {
            pair = (Map.Entry) it.next();
            pair2 = (Map.Entry) it2.next();
            assertEquals((Integer)pair.getKey(), (Integer)pair2.getKey());
        }


    }

}