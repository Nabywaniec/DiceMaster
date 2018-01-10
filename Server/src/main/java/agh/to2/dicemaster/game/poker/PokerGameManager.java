package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.game.model.Timer;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.Comparator;
import java.util.HashMap;

public class PokerGameManager {
    private final PokerGame game;
    private Timer timer;

    public PokerGameManager(PokerGame game) {
        this.game = game;
    }

    public void performMove(MoveDTO moveDTO) {
    }

    public Player findRoundWinner() {
        Player winner = game.getPlayerList()
                .stream()
                .max(Comparator.comparingInt(Player::getRoundScore))
                .orElseThrow(IllegalStateException::new);
        winner.incereaseGameScore(1);
        return winner;
    }

    public Player findGameWinner() {
        return game.getPlayerList()
                .stream()
                .max(Comparator.comparingInt(Player::getGameScore))
                .orElseThrow(IllegalStateException::new);
    }

}
