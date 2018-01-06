package agh.to2.dicemaster.game.model;

import java.util.ArrayList;

public class DiceManager {



    public static ArrayList<Dice.Value> getDices(int n){
        ArrayList<Dice.Value> newDices = new ArrayList<>();
        for(int i=0;i<n;i++){
            newDices.add(new Dice().getV());
        }
        return newDices;
    }

}
