package agh.to2.dicemaster.server;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

import java.util.Collection;

public class User extends GameParticipant {

    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {

    }

    @Override
    public void registerPlayerEventHandler(PlayerEventHandler playerEventHandler) {

    }

    public void sendGames(Collection<Game> games) {

    }
}
