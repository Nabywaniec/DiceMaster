package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.game.model.Observer;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;

public class PokerGame extends Game {

    public PokerGame(int id, GameConfigDTO gameConfigDTO) {
        super(id, gameConfigDTO);
    }

    @Override
    public void addObserver(GameParticipant gameParticipant) {
    }

    @Override
    public void addPlayer(GameParticipant player) {
    }

    @Override
    public GameDTO getGameDTO() {
        return null;
    }
}
