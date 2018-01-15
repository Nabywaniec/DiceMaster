package agh.to2.dicemaster.common.api;

public abstract class GameConfigDTO {

    //I can't see any subclass of this class. This code is needed undoubtedly.
    private int numToFinishGame;

    public void setNumToFinishGame(int numToFinishGame) {
        this.numToFinishGame = numToFinishGame;
    }

    public int getNumToFinishGame() {
        return numToFinishGame;
    }
}
