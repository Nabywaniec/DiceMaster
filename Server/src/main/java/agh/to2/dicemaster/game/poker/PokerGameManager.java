package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.game.model.Timer;
import sun.security.util.PendingException;

import java.util.Comparator;

public class PokerGameManager {

    private final static int ROUND_DURATION = 20;
    private final static int ROUND_COUNT = 5;
    private final static int TURNS_PER_ROUND = 3;

    private final PokerGame game;
    private int currentPlayer;
    private int roundNumber = 1;
    private int turnNumber = 1;
    private Thread timerThread;
    private GameState gameState = GameState.PENDING;

    private enum GameState {PENDING, STARTED, ENDED}


    public PokerGameManager(PokerGame game) {
        this.game = game;
    }


    public synchronized void onTurnEnd() {
        currentPlayer++;
        turnNumber++;
        if (currentPlayer >= game.getPlayerList().size()) {
            currentPlayer = 0;
        }
        if (turnNumber > TURNS_PER_ROUND) {
            turnNumber = 1;
            onRoundEnd();
        }
        if (roundNumber > ROUND_COUNT) {
            onGameEnd();
        }
        notifyAllGameParticipants();
    }

    private synchronized void onRoundEnd() {
        findRoundWinner();
        roundNumber++;
    }

    public synchronized void onGameStart() {
        gameState = GameState.STARTED;
        timerThread = new Thread(new Timer(this, ROUND_DURATION));
        timerThread.start();
    }

    public synchronized void onGameEnd() {
        gameState = GameState.ENDED;
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

    public boolean hasStarted() {
        return gameState.equals(GameState.STARTED);
    }

    public boolean hasEnded() {
        return gameState.equals(GameState.ENDED);
    }

}
