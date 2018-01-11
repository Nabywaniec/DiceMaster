package diceMaster.model.server;

import diceMaster.model.common.GameConfigDTO;
import diceMaster.model.common.GameDTO;
import diceMaster.model.common.UserType;
import diceMaster.model.gui.GameEventHandler;


import java.util.List;

public interface Server {
    ServerGame createGame(GameConfigDTO gameConfigDTO, GameEventHandler gameEventHandler, UserType userType);
    ServerGame requestJoinGame(GameDTO gameDTO, GameEventHandler gameEventHandler, UserType userType);

    List<GameDTO> getAvailableGames();
    boolean registerClient(String username);
}
