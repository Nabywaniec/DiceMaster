package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.game.model.Observer;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;

public class PokerGame extends Game {
    @Override
    public void addObserver(GameParticipant gameParticipant) {
        this.getGameManager().addObserver(gameParticipant);
    }

    @Override
    public void addPlayer(GameParticipant player) {
        this.getGameManager().addGamer((Player) player);
    }
}
