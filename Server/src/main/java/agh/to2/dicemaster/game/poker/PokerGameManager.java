package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.game.model.Timer;

import java.util.Comparator;

public class PokerGameManager {

    private final static int ROUND_DURATION = 20;
    private final static int ROUND_COUNT = 5;

    private final PokerGame game;
    private int currentPlayer;
    private int roundNumber = 1;
    private Thread timerThread;

    public PokerGameManager(PokerGame game) {
        this.game = game;
    }


    public synchronized void onTurnStart() {
        if (currentPlayer >= game.getPlayerList().size()) {
            onRoundEnd();
        }
        timerThread = new Thread(new Timer(this, ROUND_DURATION));
        timerThread.start();
    }

    public synchronized void onTurnEnd() {
        currentPlayer++;
        notifyAllGameParticipants();
        onTurnStart();
    }

    public synchronized void onRoundStart() {
        if (roundNumber > ROUND_COUNT) {
            onGameEnd();
        } else {
            currentPlayer = 0;
            onTurnStart();
        }
    }

    public synchronized void onRoundEnd() {
        findRoundWinner();
        roundNumber++;
        notifyAllGameParticipants();
        onRoundStart();
    }

    public synchronized void onGameStart() {
        onRoundStart();
    }

    public synchronized void onGameEnd() {
        notifyAllGameParticipants();
    }

    private void notifyAllGameParticipants() {
        GameDTO gameDTO = game.getGameDTO();
        game.getPlayers().forEach(player -> player.notifyGameStateChange(gameDTO));
        game.getObservers().forEach(observer -> observer.notifyGameStateChange(gameDTO));
    }

    public synchronized void performMove(MoveDTO moveDTO) {
        Player player = game.getPlayerList()
                .stream()
                .filter(p -> p.getId().equals(moveDTO.getPlayerId()))
                .findAny()
                .orElseThrow(IllegalStateException::new);

        if (!game.getPlayerList().get(currentPlayer).equals(player)) return;
        player.rollDices(moveDTO.getDicesToReRoll());
        timerThread.interrupt();
    }

    private Player findRoundWinner() {
        Player winner = game.getPlayerList()
                .stream()
                .max(Comparator.comparingInt(Player::getRoundScore))
                .orElseThrow(IllegalStateException::new);
        winner.incereaseGameScore(1);
        return winner;
    }

    private Player findGameWinner() {
        return game.getPlayerList()
                .stream()
                .max(Comparator.comparingInt(Player::getGameScore))
                .orElseThrow(IllegalStateException::new);
    }

}
