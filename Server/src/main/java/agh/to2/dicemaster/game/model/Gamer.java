package agh.to2.dicemaster.game.model;


import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

import java.util.ArrayList;

public class Gamer extends GameParticipant{

    private String id;

    private ArrayList<Dice.Value> dices = new ArrayList<>();

    public Gamer(String id) {
        this.id = id;
        this.dices.clear();
    }

    public void createDices(){
        this.dices.addAll(DiceManager.getDices(5));

    }

    public void changeBones(Dice.Value t[], int n){

        for(int i=0;i<n;i++){
            this.dices.remove(t[i]);
        }

        this.dices.addAll(DiceManager.getDices(n));
    }

    public ArrayList<Dice.Value> getBones() {
        return dices;
    }

    public String  getId() {
        return id;
    }


    public  void notifyGameStateChange(GameDTO gameDTO){

    }
    public  void registerPlayerEventHandler(PlayerEventHandler playerEventHandler){

    }
}
