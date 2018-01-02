package agh.to2.dicemaster.api.client;

import agh.to2.dicemaster.api.common.GameConfigDTO;
import agh.to2.dicemaster.api.common.GameDTO;

import java.util.List;

public interface Server {

    ServerGame createGame(GameConfigDTO gameConfigDTO, GameEventHandler gameEventHandler, UserType userType);
    ServerGame requestJoinGame(GameDTO gameDTO, GameEventHandler gameEventHandler, UserType userType);

    List<GameDTO> getAvailableGames();
    boolean registerClient(String username);
}
