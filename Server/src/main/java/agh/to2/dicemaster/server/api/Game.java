package agh.to2.dicemaster.server.api;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;

import java.util.List;

public abstract class Game {
    private int id;
    private GameConfigDTO gameConfigDTO;

    public abstract void addObserver(GameParticipant gameParticipant);
    public abstract void addPlayer(GameParticipant gameParticipant);
    public abstract GameDTO getGameDTO();

    public abstract List<GameParticipant> getPlayers();
    public abstract List<GameParticipant> getObservers();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
