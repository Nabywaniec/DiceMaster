package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;
import agh.to2.dicemaster.bot.IllegalHandException;
import agh.to2.dicemaster.bot.helpers.Hand;
import agh.to2.dicemaster.bot.helpers.HandChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kamkalet on 04.01.2018.
 */
public class PokerBotEasy extends Bot {
    @Override
    DiceOutputDTO getDicesToThrow(DiceInputDTO input) {
        DiceOutputDTO result = new DiceOutputDTO();
        // TODO implement this method

        List<Integer> dicesToThrow = new ArrayList<>();

        if (!myHandIsTheHighestOnTable(input) && myHandUsesAllDices(input.getMyInput())) {
            // throw all dices
            dicesToThrow.addAll(input.getMyInput());
        } else {
            try {
                dicesToThrow.addAll(HandChecker.getDicesNotFromHand(input.getMyInput()));
            } catch (IllegalHandException e) {
                e.printStackTrace();
            }
        }

        result.setDicesToThrow(dicesToThrow);


        return result;
    }

    private boolean myHandIsTheHighestOnTable(DiceInputDTO input) {
        for (List<Integer> someonesInput :
                input.getOtherPlayersInputs()) {
            // if someone's hand is better or equal than mine return false
            if (HandChecker.whatTheHandIsThis(someonesInput).compareTo(
                    HandChecker.whatTheHandIsThis(input.getMyInput())) >= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean myHandUsesAllDices(List<Integer> inputHand) {
        Hand hand = HandChecker.whatTheHandIsThis(inputHand);
        if (hand.equals(Hand.Bust) || hand.equals(Hand.Five) ||
                hand.equals(Hand.Full) || hand.equals(Hand.Straight)) {
            return true;
        }
        return false;
    }


}
