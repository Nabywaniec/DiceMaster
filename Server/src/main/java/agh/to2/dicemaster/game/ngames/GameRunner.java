package agh.to2.dicemaster.game.ngames;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.game.nmodel.Player;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.List;

/**
 * Created by werka on 10.01.18.
 */
public class GameRunner {
    NGame nGame;
    Rules rules;
    List<Player> players;
    Integer turn;
    public GameRunner(Rules rules, List<Player> players, NGame nGame) {
        this.nGame=nGame;
        this.rules=rules;
        this.players=players;
        this.turn=rules.initializeRound(players);
        for (GameParticipant p:players) {
            rules.initializeDices((Player) p);
        }
        notifyAllGameParticipants();
        runGame();
    }

    private void notifyAllGameParticipants() {
        GameDTO gameDTO = nGame.getGameDTO();
        nGame.getPlayers().forEach(player -> player.notifyGameStateChange(gameDTO));
        nGame.getObservers().forEach(observer -> observer.notifyGameStateChange(gameDTO));
    }

    public void runGame(){
        for (Player p:players) {
            if(rules.countPoints( p)){ //roundWon by player
                p.addRoundWon();
                if(p.getScore()==5){
                    notifyAllGameParticipants();
                    endGame();
                }

                this.turn=rules.initializeRound(players);
                for (Player pl :players) {
                    rules.initializeDices(pl);
                }
            }
        }
        while(true){
            Player player=  players.get(turn);
            notifyAllGameParticipants();
            if(rules.countPoints(player)){ //roundWon by player
                player.addRoundWon();
                if(player.getScore()==5){
                    notifyAllGameParticipants();
                    endGame();
                }

                this.turn=rules.initializeRound(players);
                for (GameParticipant p:players) {
                    rules.initializeDices((Player) p);
                }
            }
            turn=turn+1;
        }

    }
    public void endGame(){}

}
