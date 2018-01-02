package agh.to2.dicemaster.client.api;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;

import java.util.List;

public interface Server {

    ServerGame createGame(GameConfigDTO gameConfigDTO, GameEventHandler gameEventHandler, UserType userType);
    ServerGame requestJoinGame(GameDTO gameDTO, GameEventHandler gameEventHandler, UserType userType);

    List<GameDTO> getAvailableGames();
    boolean registerClient(String username);
}
