package agh.to2.dicemaster.server.managers;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GamesManager {
    public void createGame(GameConfigDTO gameConfigDTO) { }

    public Game getGameById(int gameId) {
        return null;
    }

    public Collection<GameDTO> getAll() {
        return null;
    }

    public Game removeGame(int gameId) { return null; }
}
