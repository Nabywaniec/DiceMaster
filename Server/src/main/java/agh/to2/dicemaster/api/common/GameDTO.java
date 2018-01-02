package agh.to2.dicemaster.api.common;

public abstract class GameDTO implements DTO {
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
}
