package agh.to2.dicemaster.game.server.api;

import agh.to2.dicemaster.common.api.GameDTO;

public class GameParticipant {
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

    public  void notifyGameStateChange(GameDTO gameDTO){}

    public  void registerPlayerEventHandler(PlayerEventHandler playerEventHandler){}

}
