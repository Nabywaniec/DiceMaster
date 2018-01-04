package agh.to2.dicemaster.game.poker;

public class Information {

    private String message;
    private int type;


    public Information(String message, int type){
        this.message = message;
        this.type = type;
    }

    public String getMessage(){
        return message;
    }

    public int getType(){
        return this.type;
    }

    public String format(){

        //TODO
        return "";

    }




}
