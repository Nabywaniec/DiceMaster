package agh.to2.dicemaster.bot;

import java.util.List;

public class DiceInputDTO {

    private int numToFinishGame;

    public int getNumToFinishGame() {
        return numToFinishGame;
    }

    private List<Integer> myInput;
    private List<List<Integer>> otherPlayersInputs;

    public List<Integer> getMyInput() {
        return myInput;
    }

    public List<List<Integer>> getOtherPlayersInputs() {
        return otherPlayersInputs;
    }

    public DiceInputDTO(int numToFinishGame) {
        this.numToFinishGame = numToFinishGame;
    }
}
