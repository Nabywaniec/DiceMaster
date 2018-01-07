package agh.to2.dicemaster.client;

import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.client.api.Server;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.common.UserType;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;

import java.util.List;

public class DiceMasterServer implements Server {

    private String serverAddress;

    public DiceMasterServer(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public ServerGame createGame(GameConfigDTO gameConfigDTO, GameEventHandler gameEventHandler, UserType userType) {
        return null;
    }

    @Override
    public ServerGame requestJoinGame(GameDTO gameDTO, GameEventHandler gameEventHandler, UserType userType) {
        return null;
    }

    @Override
    public List<GameDTO> getAvailableGames() {
        return null;
    }

    @Override
    public boolean registerClient(String username) {
        return false;
    }
}
