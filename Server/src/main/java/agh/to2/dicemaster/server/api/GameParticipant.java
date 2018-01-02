package agh.to2.dicemaster.server.api;

import agh.to2.dicemaster.common.api.GameDTO;

public abstract class GameParticipant {
    abstract void notifyGameStateChange(GameDTO gameDTO);
    abstract void registerPlayerEventHandler(PlayerEventHandler playerEventHandler);

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
