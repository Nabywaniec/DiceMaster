package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.server.api.Game;

import java.util.HashMap;

public class GameManager {

    private HashMap<Player, Integer> game_results;
    private HashMap<Player, Integer> round_results;
    private final Game game;

    public GameManager(Game game) {
        this.game = game;
    }

    public void performMove(MoveDTO moveDTO) {

    }
    public HashMap<Player, Integer> initialize(HashMap<Player, Integer> results) {

        for (Player key : results.keySet()) {
            results.put(key, 0);
        }
        return results;
    }

    public void addGamer(Player gamer) {
        this.game_results.put(gamer, 0);
        this.round_results.put(gamer, 0);
    }

    public void findRoundWinner() {
        int maxy = -1;
        String maxy_owner = "";
        Player maxy_owner2 = null;
        for (Player gamer : round_results.keySet()) {
            int r = PokerScore.getScore(gamer.getDices());
            round_results.put(gamer, r);
            if (r > maxy) {
                maxy = PokerScore.getScore(gamer.getDices());
                maxy_owner = gamer.getId();

            }
        }
        System.out.println("Player " + maxy_owner + " win round!");
        int r = this.game_results.get(maxy_owner2);
        game_results.put(maxy_owner2, r + 1);
    }

    public void findGameWinner() {
        int maxy = -1;
        String maxy_owner = "";
        for (Player gamer : game_results.keySet()) {
            int r = game_results.get(gamer);
            if (r > maxy) {
                maxy = r;
                maxy_owner = gamer.getId();
            }
        }
        System.out.println("Player " + maxy_owner + " win game !");
    }

    public void deleteGamer(Player gamerToDelete) {
        this.game_results.remove(gamerToDelete);
        this.round_results.remove(gamerToDelete);
    }


}
