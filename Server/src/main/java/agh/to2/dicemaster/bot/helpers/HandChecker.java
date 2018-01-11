package agh.to2.dicemaster.bot.helpers;

import agh.to2.dicemaster.bot.IllegalBotTypeException;
import agh.to2.dicemaster.bot.IllegalHandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandChecker {

    public static List<Integer> getDicesNotFromHand(List<Integer> inputHand) throws IllegalHandException {
        Collections.sort(inputHand);

        if (sortedContainsFour(inputHand)) {
            if (inputHand.get(0).equals(inputHand.get(3))) {
                return inputHand.subList(4, 5);
            } else {
                return inputHand.subList(0, 1);
            }
        }

        if (sortedContainsThree(inputHand)) {
            if (inputHand.get(0).equals(inputHand.get(2)))
                return inputHand.subList(3, 5);
            if (inputHand.get(1).equals(inputHand.get(3))){
                List<Integer> result = new ArrayList<>();
                result.add(inputHand.get(0));
                result.add(inputHand.get(4));
                return result;
            }

            if (inputHand.get(2).equals(inputHand.get(4)))
                return inputHand.subList(0, 2);
        }

        if (sortedContainsTwoPairs(inputHand)) {
            if (inputHand.get(0).equals(inputHand.get(1))
                    && inputHand.get(2).equals(inputHand.get(3))) {
                return inputHand.subList(4, 5);
            }
            if (inputHand.get(0).equals(inputHand.get(1))
                    && inputHand.get(3).equals(inputHand.get(4))) {
                return inputHand.subList(2, 3);
            }
            if (inputHand.get(1).equals(inputHand.get(2))
                    && inputHand.get(3).equals(inputHand.get(4))) {
                return inputHand.subList(0,1);
            }
        }

        if (sortedContainsOnePair(inputHand)) {
            List<Integer> result = new ArrayList<>();
            if (inputHand.get(0).equals(inputHand.get(1))) {
                return inputHand.subList(2, 5);
            }
            if (inputHand.get(1).equals(inputHand.get(2))) {
                result.add(inputHand.get(0));
                result.addAll(inputHand.subList(3, 5));
                return result;
            }
            if (inputHand.get(2).equals(inputHand.get(3))) {
                result.addAll(inputHand.subList(0, 2));
                result.add(inputHand.get(4));
                return result;
            }
            if (inputHand.get(3).equals(inputHand.get(4))) {
                return inputHand.subList(0, 3);
            }
        }
        throw new IllegalHandException();
    }


    public static Hand whatTheHandIsThis(List<Integer> inputHand) {
        Collections.sort(inputHand);

        if (sortedContainsFive(inputHand)) {
            return Hand.Five;
        }
        if (sortedContainsFour(inputHand)) {
            return Hand.Four;
        }
        if (sortedContainsFull(inputHand)) {
            return Hand.Full;
        }
        if (sortedContainsStraight(inputHand)) {
            return Hand.Straight;
        }
        if (sortedContainsThree(inputHand)) {
            return Hand.Three;
        }
        if (sortedContainsTwoPairs(inputHand)) {
            return Hand.TwoPairs;
        }
        if (sortedContainsOnePair(inputHand)) {
            return Hand.OnePair;
        }
        return Hand.Bust;

    }

    private static boolean sortedContainsFive(List<Integer> inputHand){
        return inputHand.get(0).equals(inputHand.get(4));
    }
    private static boolean sortedContainsFour(List<Integer> inputHand){
        return (inputHand.get(0).equals(inputHand.get(3))) &&
                inputHand.get(1).equals(inputHand.get(4));
    }
    private static boolean sortedContainsFull(List<Integer> inputHand){
        return ((inputHand.get(0).equals(inputHand.get(2))) &&
                (inputHand.get(3).equals(inputHand.get(4)))) ||
                ((inputHand.get(0).equals(inputHand.get(1))) &&
                        (inputHand.get(2).equals(inputHand.get(4))));
    }
    private static boolean sortedContainsStraight(List<Integer> inputHand){
        return inputHand.get(0).equals(inputHand.get(1) - 1) &&
                inputHand.get(0).equals(inputHand.get(2) - 2) &&
                inputHand.get(0).equals(inputHand.get(3) - 3) &&
                inputHand.get(0).equals(inputHand.get(4) - 4);
    }
    private static boolean sortedContainsThree(List<Integer> inputHand){
        return inputHand.get(0).equals(inputHand.get(2)) ||
                inputHand.get(1).equals(inputHand.get(3)) ||
                inputHand.get(2).equals(inputHand.get(4));
    }
    private static boolean sortedContainsTwoPairs(List<Integer> inputHand){
        return ((inputHand.get(0).equals(inputHand.get(1)))
                && (inputHand.get(2).equals(inputHand.get(3)))) ||
                ((inputHand.get(1).equals(inputHand.get(2))) &&
                        (inputHand.get(3).equals(inputHand.get(4)))) ||
                ((inputHand.get(0).equals(inputHand.get(1))) &&
                        (inputHand.get(3).equals(inputHand.get(4))));
    }
    private static boolean sortedContainsOnePair(List<Integer> inputHand){
        for (int i = 0; i < 4; i++) {
            if (inputHand.get(i).equals(inputHand.get(i+1)))
                return true;
        }
        return false;
    }
    private static boolean sortedContainsBust(List<Integer> inputHand){
        for (int i = 0; i < 4; i++) {
            if (inputHand.get(i).equals(inputHand.get(i+1)))
                return false;
        }
        return inputHand.get(0).equals(inputHand.get(4) - 5);
    }
}
