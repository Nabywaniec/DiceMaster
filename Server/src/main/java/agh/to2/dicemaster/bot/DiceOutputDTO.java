package agh.to2.dicemaster.bot;

import java.util.List;

public class DiceOutputDTO {

    private List<Integer> dicesToThrow;

    public static void subtractNumToFinishGame(int numOnDiceToPutAside) {
        numToFinishGame -= numOnDiceToPutAside;
    }

    public static void divideNumToFinishGame(int numOnDiceToPutAside) {
        numToFinishGame /= numOnDiceToPutAside;
    }

    public static int getNumToFinishGame() {
        return numToFinishGame;
    }

    public static void setNumToFinishGame(int num) {

        numToFinishGame = num;
    }

    private static int numToFinishGame;

    public List<Integer> getDicesToThrow() {
        return dicesToThrow;
    }

    public void setDicesToThrow(List<Integer> dicesToThrow) {
        this.dicesToThrow = dicesToThrow;
    }
}
