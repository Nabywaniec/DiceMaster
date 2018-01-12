package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;
import agh.to2.dicemaster.bot.helpers.HandUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dawid Tomasiewicz on 06.01.2018.
 */
public class PokerBotDifficult extends Bot {
    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO inputDice) {

        DiceOutputDTO diceOutputResult = new DiceOutputDTO();

        JSONParser parser = new JSONParser();
        Object object = null;
        List<Integer> myDices = inputDice.getMyInput();
        Collections.sort(myDices);

        try {
            object = parser
                    .parse(new FileReader(HandUtils.getFileName(myDices)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONArray array = (JSONArray) object;

//        array.forEach(System.out::println);

        Long myHand = HandUtils.getHandNumber(HandUtils.whatTheHandIsThis(myDices)).longValue();
        Long tmpHand;
        Integer whichMask = 0;
        Double result = 0.0;
        ArrayList<Double> results = new ArrayList<>();
        JSONArray tmpRow;
        for (int i = 0; i < array.size()/8; i++) {
            result = 0.0;
            for (int j = 0; j < 8; j++) {
                tmpRow = (JSONArray) array.get(8 * i + j);
                tmpHand = (Long) tmpRow.get(2);
                if (myHand < tmpHand) {
                    result += (Double) tmpRow.get(3) * HandUtils.getHandValue(tmpHand);
                }
            }
            results.add(result);
        }
        Integer index = HandUtils.getMaxIndex(results);


        JSONArray maskRow= (JSONArray) array.get(8 * index);
        JSONArray resultMask = (JSONArray) maskRow.get(1);

        List<Integer> resultOfResults = null;
        if (results.get(index) > HandUtils.getHandValue(myHand)) {
            resultOfResults = HandUtils.integerListFromLongList(resultMask);
        } else {
            resultOfResults = new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);add(0);}};
        }

        diceOutputResult.setDicesToThrow(resultOfResults);

        //FIXME mocked result prepared
//        List<Integer> tmp = Arrays.asList(4,5,6);
//        result.setDicesToThrow(tmp);


        return diceOutputResult;
    }
}
