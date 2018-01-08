package agh.to2.dicemaster.server.mocks;

import agh.to2.dicemaster.common.api.GameDTO;

public class PokerGameDTO extends GameDTO {
    private String type = "poker";

    public PokerGameDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
