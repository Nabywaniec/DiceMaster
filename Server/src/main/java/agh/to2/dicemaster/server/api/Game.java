package agh.to2.dicemaster.server.api;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.game.model.Observer;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.game.poker.GameManager;

import java.util.List;

public abstract class Game {
    private int id;

    private GameConfigDTO gameConfigDTO;

    private GameManager gameManager;

    private List<GameParticipant> players;

    private List<GameParticipant> observers;

    public abstract void addObserver(GameParticipant gameParticipant);

    public abstract void addPlayer(GameParticipant gameParticipant);

    public List<GameParticipant> getPlayers() {
        return players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public void setGameConfigDTO(GameConfigDTO configDTO){
        this.gameConfigDTO=configDTO;
    }
}
