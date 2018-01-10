package agh.to2.dicemaster.game.model;


public class DiceManager {

    public static Dice[] getDices(int numberOfDices) {
        Dice[] dices = new Dice[numberOfDices];

        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
        }
        return dices;
    }


}
