package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.game.model.Observer;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;

public class PokerGame extends Game {

    public PokerGame(int id, GameConfigDTO gameConfigDTO) {
        setId(id);
        setGameConfigDTO(gameConfigDTO);
        setGameManager(new GameManager());
    }

    @Override
    public void addObserver(GameParticipant gameParticipant) {

        this.getGameManager().addObserver(gameParticipant);
    }

    @Override
    public void addPlayer(GameParticipant player) {
        if (this.getGameConfigDTO().getMaxPlayers() >= this.getPlayers().size() + 1) {
            this.getGameManager().addGamer((Player) player);
        }
    }
}
