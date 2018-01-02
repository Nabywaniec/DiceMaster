package agh.to2.dicemaster.api.server;

import agh.to2.dicemaster.api.common.GameDTO;

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
