package agh.to2.dicemaster.game.poker;

public class Observer extends GameParticipant {

    private int id;


    public Observer(int id){
        super(id);
    }


    public int getId(){
        return this.id;
    }

}
