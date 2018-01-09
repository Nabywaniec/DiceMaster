package agh.to2.dicemaster.common.api;

import agh.to2.dicemaster.game.poker.GameManager;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.LinkedList;
import java.util.List;

public abstract class GameDTO {
    private int id;

    private GameManager gameManager;

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

    public void setGameManager(GameManager gameManager){this.gameManager = gameManager;}

    public GameManager getGameManager() {
        return gameManager;
    }
}
