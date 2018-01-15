package agh.to2.dicemaster.server.api;

import agh.to2.dicemaster.common.api.GameConfigDTO;

import java.util.List;

public abstract class Game {
    private int id;
    private GameConfigDTO gameConfigDTO;

    private List<GameParticipant> players;
    abstract void addObserver(GameParticipant gameParticipant);
    abstract void addPlayer(GameParticipant gameParticipant);

    public List<GameParticipant> getPlayers() {
        return players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
