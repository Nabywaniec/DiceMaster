package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.game.model.Observer;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.HashMap;
import java.util.List;

public class GameManager {

    private HashMap<Player, Integer> game_results;
    private HashMap<Player, Integer> round_results;
    private Algo algo;
    private List<GameParticipant> observers;
    private GameDTO game;

    public GameManager(GameDTO game){
        this.game_results.clear();
        this.round_results.clear();
        this.algo = algo;
        this.game = game;
    }

    public HashMap<Player, Integer> initialize(HashMap<Player, Integer> results){

        for(Player key : results.keySet()){
            results.put(key, new Integer(0));
        }
        return results;
    }

    public void addGamer(Player gamer){
        this.game_results.put(gamer, new Integer(0));
        this.round_results.put(gamer, new Integer(0));
        this.observers.add(gamer);
    }

    public void addObserver(GameParticipant observer){
        this.observers.add(observer);
    }

    public void findWinnerRound(){

        int maxy =-1;
        String maxy_owner ="";
        Player maxy_owner2 = null;
        for(Player gamer:round_results.keySet()){
            int r;
            round_results.put(gamer, r = algo.getResult(gamer.getDices()));
            if(r > maxy){
                maxy = algo.getResult(gamer.getDices());
                maxy_owner = gamer.getId();

            }
        }
        System.out.println("Player " + maxy_owner + " win round!");
        int r = this.game_results.get(maxy_owner2);
        game_results.put(maxy_owner2, r +1);
    }

    public void findWinnerGame(){
        int maxy =-1;
        String maxy_owner = "";
        int r;
        for(Player gamer : game_results.keySet()){
            if( (r = game_results.get(gamer)) > maxy){
                maxy = r;
                maxy_owner = gamer.getId();
            }
        }
        System.out.println("Player " + maxy_owner + " win game !");
    }

    public void deleteGamer(Player gamerToDelete){
        this.game_results.remove(gamerToDelete);
        this.round_results.remove(gamerToDelete);
    }

    public List<GameParticipant> getObservers(){
        return this.observers;
    }

    public HashMap<Player, Integer> getGame_results() {
        return game_results;
    }

    public HashMap<Player, Integer> getRound_results() {
        return round_results;
    }
}
