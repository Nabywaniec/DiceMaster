package agh.to2.dicemaster.game.model;

import agh.to2.dicemaster.game.poker.GameParticipant;

import java.util.ArrayList;

public class Gamer extends GameParticipant{

    private int id;
    private ArrayList<Dice.Value> bones = new ArrayList<>();

    public Gamer(int id) {
        super(id);
        this.bones.clear();
    }

    public void changeBones(Dice.Value t[], int n){

        for(int i=0;i<n;i++){
            this.bones.remove(t[i]);
        }

        this.bones.addAll(this.getBones());
    }

    public ArrayList<Dice.Value> getBones() {
        return bones;
    }

    public int getId() {
        return id;
    }
}
