package agh.to2.dicemaster.game.model;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

public class Observer extends GameParticipant {
    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {

    }

    @Override
    public void registerPlayerEventHandler(PlayerEventHandler playerEventHandler) {

    }
}
