package agh.to2.dicemaster.game.model;


import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

import java.util.ArrayList;

public class Player extends GameParticipant{

    private String id;

    private ArrayList<Dice.Value> dices = new ArrayList<>();

    public Player(String id) {
        this.id = id;
        this.dices.clear();
    }

    public void createDices(){
        this.dices.addAll(DiceManager.getDices(5));

    }

    public void changeDices(Dice.Value t[]){

        for(int i=0;i<t.length;i++){
            this.dices.remove(t[i]);
        }

        this.dices.addAll(DiceManager.getDices(t.length));
    }

    public ArrayList<Dice.Value> getDices() {

        ArrayList<Dice.Value> new_ = new ArrayList<>();
        for(Dice.Value d : dices){
            new_.add(d);
        }
        return new_;
    }

    public String  getId() {
        return id;
    }


    public  void notifyGameStateChange(GameDTO gameDTO){

    }
    public  void registerPlayerEventHandler(PlayerEventHandler playerEventHandler){

    }
}
