package agh.to2.dicemaster.server.api;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Game {

    private final int id;
    private final GameConfigDTO gameConfigDTO;

    public Game(int id, GameConfigDTO gameConfigDTO) {
        this.id = id;
        this.gameConfigDTO = gameConfigDTO;
    }

    public abstract void addObserver(GameParticipant gameParticipant);

    public abstract void addPlayer(GameParticipant gameParticipant);

    public abstract List<GameParticipant> getPlayers();

    public abstract List<GameParticipant> getObservers();

    public abstract GameDTO getGameDTO();

    public int getId() {
        return id;
    }

    public GameConfigDTO getGameConfigDTO() {
        return gameConfigDTO;
    }

}
