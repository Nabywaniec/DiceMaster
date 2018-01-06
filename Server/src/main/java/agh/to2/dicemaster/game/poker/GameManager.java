package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.game.model.Gamer;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.HashMap;
import java.util.List;

public class GameManager {

    private HashMap<Gamer, Integer> game_results;
    private HashMap<Gamer, Integer> round_results;
    private Algo algo;
    private List<GameParticipant> observers;

    public GameManager(){
        this.game_results.clear();
        this.round_results.clear();
        this.algo = algo;
    }

    public HashMap<Gamer, Integer> initialize(HashMap<Gamer, Integer> results){

        for(Gamer key : results.keySet()){
            results.put(key, new Integer(0));
        }
        return results;
    }

    public void addGamer(Gamer gamer){
        this.game_results.put(gamer, new Integer(0));
        this.round_results.put(gamer, new Integer(0));
        this.observers.add(gamer);
    }


    public void findWinnerRound(){

        int maxy =-1;
        String maxy_owner ="";
        Gamer maxy_owner2 = null;
        for(Gamer gamer:round_results.keySet()){
            int r;
            round_results.put(gamer, r = algo.getResult(gamer.getBones()));
            if(r > maxy){
                maxy = algo.getResult(gamer.getBones());
                maxy_owner = gamer.getId();
                maxy_owner2 = gamer;
            }
        }
        System.out.println("Gamer " + maxy_owner + " win round!");
        int r = this.game_results.get(maxy_owner2);
        game_results.put(maxy_owner2, r +1);
    }

    public void findWinnerGame(){
        int maxy =-1;
        String maxy_owner = "";
        int r;
        for(Gamer gamer : game_results.keySet()){
            game_results.put(gamer, r = algo.getResult(gamer.getBones()));
            if(r > maxy){
                maxy = algo.getResult(gamer.getBones());
                maxy_owner = gamer.getId();
            }
        }
        System.out.println("Gamer " + maxy_owner + " win game !");
    }

    public void deleteGamer(Gamer gamerToDelete){
        this.game_results.remove(gamerToDelete);
        this.round_results.remove(gamerToDelete);
    }

    public List<GameParticipant> getObservers(){
        return this.observers;
    }



}
