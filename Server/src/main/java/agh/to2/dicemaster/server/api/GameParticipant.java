package agh.to2.dicemaster.server.api;

import agh.to2.dicemaster.common.api.GameDTO;

import java.util.Objects;

public abstract class GameParticipant {
    private String id;

    public GameParticipant() {}

    public GameParticipant(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void notifyGameStateChange(GameDTO gameDTO);

    public abstract void registerPlayerEventHandler(PlayerEventHandler playerEventHandler);

}
