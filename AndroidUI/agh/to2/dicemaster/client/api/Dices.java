package agh.to2.dicemaster.client.api;

public class Dices {
    public final DiceNumbers[] dicesScore = new DiceNumbers[5];


    public Dices(){
        for(int i = 0; i < 5; i++){
            this.dicesScore[i] = DiceNumbers.UNKNOWN;
        }
    }
    public Dices(DiceNumbers d1, DiceNumbers d2, DiceNumbers d3, DiceNumbers d4, DiceNumbers d5){
        this.dicesScore[0] = d1;
        this.dicesScore[1] = d2;
        this.dicesScore[2] = d3;
        this.dicesScore[3] = d4;
        this.dicesScore[4] = d5;
    }
}
