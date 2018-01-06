package agh.to2.dicemaster.game.model;

import agh.to2.dicemaster.game.poker.GameParticipant;

import java.util.ArrayList;

public class Gamer extends GameParticipant{

    private int id;
    private ArrayList<Dice.Value> dices = new ArrayList<>();

    public Gamer(int id) {
        super(id);
        this.dices.clear();
    }

    public void createDices(){

    }

    public void changeBones(Dice.Value t[], int n){

        for(int i=0;i<n;i++){
            this.dices.remove(t[i]);
        }

        this.dices.addAll(this.getBones());
    }

    public ArrayList<Dice.Value> getBones() {
        return dices;
    }

    public int getId() {
        return id;
    }
}
