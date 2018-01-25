package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.BotType;
import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;
import agh.to2.dicemaster.bot.factory.NPlusBotFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kamkalet on 18.01.2018.
 */
class NPlusBotDifficultTest {

    private NPlusBotFactory factory = new NPlusBotFactory();

    @org.junit.jupiter.api.Test
    void getDicesToThrow() {

        NPlusBotDifficult bot=null;
        try {
            bot = (NPlusBotDifficult)
                    factory.createBot(BotType.DIFFICULT);
        } catch (Exception e) {
            System.exit(1);
        }

        DiceOutputDTO output = new DiceOutputDTO(30);

        HashMap<Integer, Integer> dicesToThrow = new HashMap<>();
        dicesToThrow.put(4, 1);

        bot.setResult(output);
        output.setMyInput(Arrays.asList(6, 6, 6, 6, 1));
        output.setDicesToThrow(dicesToThrow);
        output.setScoreLeft(5);

        DiceInputDTO input = new DiceInputDTO(30);
        input.setMyInput(Arrays.asList(6, 6, 6, 6, 1));

        testOutputs(dicesToThrow, bot.getDicesToThrow(input).getDicesToThrow());

        HashMap<Integer, Integer> dicesToThrow2 = new HashMap<>();
        dicesToThrow2.put(0, 1);
        dicesToThrow2.put(1, 1);
        dicesToThrow2.put(2, 1);
        dicesToThrow2.put(3, 1);
        dicesToThrow2.put(4, 1);
        input.setMyInput(Arrays.asList(4, 3, 2, 5, 1));
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