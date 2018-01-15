package agh.to2.dicemaster.bot;

import java.util.List;

public class DiceOutputDTO {
    private List<Integer> dicesToThrow;

    public void subtractNumToFinishGame(int numOnDiceToPutAside) {
        this.numToFinishGame -= numOnDiceToPutAside;
    }

    public void divideNumToFinishGame(int numOnDiceToPutAside) {
        this.numToFinishGame /= numOnDiceToPutAside;
    }

    public int getNumToFinishGame() {
        return numToFinishGame;
    }

    public void setNumToFinishGame(int numToFinishGame) {

        this.numToFinishGame = numToFinishGame;
    }

    private int numToFinishGame;

    public List<Integer> getDicesToThrow() {
        return dicesToThrow;
    }

    public void setDicesToThrow(List<Integer> dicesToThrow) {
        this.dicesToThrow = dicesToThrow;
    }
}
