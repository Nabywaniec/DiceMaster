package agh.to2.dicemaster.common;

public class Dices {
    private DiceNumbers dicesScore[];

    public Dices() {
        dicesScore = new DiceNumbers[5];
    }

    public Dices(DiceNumbers[] dicesNumbers) {
        this.dicesScore = dicesNumbers;
    }

    public DiceNumbers[] getDicesScore() {
        return dicesScore;
    }
}
