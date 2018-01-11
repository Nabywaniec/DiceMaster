package agh.to2.dicemaster.common.api;

public abstract class GameDTO {
    //needs verification
    private int id;
    private GameConfigDTO gameConfigDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GameConfigDTO getGameConfigDTO() {
        return gameConfigDTO;
    }

    public void setGameConfigDTO(GameConfigDTO gameConfigDTO) {
        this.gameConfigDTO = gameConfigDTO;
    }

    public abstract void fromJSON(String JSON);
    public abstract void toJSON(String JSON);
}
