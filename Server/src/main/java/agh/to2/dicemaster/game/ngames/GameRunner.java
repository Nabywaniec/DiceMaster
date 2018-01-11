package agh.to2.dicemaster.game.ngames;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.game.nmodel.Player;
import agh.to2.dicemaster.game.nmodel.Timer;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.List;

import static sun.audio.AudioPlayer.player;

/**
 * Created by werka on 10.01.18.
 */
public class GameRunner {
    NGame nGame;
    Rules rules;
    List<Player> players;
    Integer turn;
    private Thread timerThread;
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
        timerThread = new Thread(new Timer(this, 30));
        timerThread.start();
    }

    public void runGame(){
        for (Player p:players) {
            checkIfWon(p);
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
            turn = turn + 1 % players.size();
        }

    }

    public void kickPlayer(){
        players.remove(turn);
        notifyAllGameParticipants();

    }

    void checkIfWon(Player p){
        if (rules.countPoints(p)) { //roundWon by player
            p.addRoundWon();
            if (p.getScore() == 5) {
                notifyAllGameParticipants();
                endGame();
            }
        }
    }
    public synchronized void performMove(MoveDTO moveDTO) {
        for (Player p :players){
            if(moveDTO.getPlayerId().equals(p.getId())&& players.get(turn) ==(p));
           //TODO
            // rules.drawDices(p,);
        }
        timerThread.interrupt();
    }
    public void endGame(){}

}
