package agh.to2.dicemaster.server.DTO;

import agh.to2.dicemaster.common.UserType;
import agh.to2.dicemaster.common.api.GameConfigDTO;

public class CreateGameRequestDTO {
    private GameConfigDTO gameConfigDTO;
    private UserType userType;

    public CreateGameRequestDTO() {
    }

    public CreateGameRequestDTO(GameConfigDTO gameConfigDTO, UserType userType) {
        this.gameConfigDTO = gameConfigDTO;
        this.userType = userType;
    }

    public GameConfigDTO getGameConfigDTO() {
        return gameConfigDTO;
    }

    public void setGameConfigDTO(GameConfigDTO gameConfigDTO) {
        this.gameConfigDTO = gameConfigDTO;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
