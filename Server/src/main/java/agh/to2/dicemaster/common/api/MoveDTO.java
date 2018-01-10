package agh.to2.dicemaster.common.api;


public class MoveDTO implements DTO {
    private String playerId;
    private boolean[] dicesToReRoll;

    public MoveDTO(String playerId, boolean[] dicesToReRoll) {
        this.playerId = playerId;
        this.dicesToReRoll = dicesToReRoll;
    }

    public boolean[] getDicesToReRoll() {
        return dicesToReRoll;
    }

    public void setDicesToReRoll(boolean[] dicesToReRoll) {
        this.dicesToReRoll = dicesToReRoll;
    }

    @Override
    public void fromJSON() {

    }

    @Override
    public String toJSON() {
        return null;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}