package agh.to2.dicemaster.game.model;

import java.util.concurrent.TimeUnit;

public class Timer {

    private int id;

    public Timer(int id){
        this.id = id;
    }

    public int countTime(long seconds){

        try {
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        return 0;
    }

    public int getId(){
        return  id;
    }



}
