package agh.to2.dicemaster.server.api;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.game.model.Observer;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.game.poker.GameManager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Game {

    private final int id;
    private final GameConfigDTO gameConfigDTO;
    private final List<GameParticipant> players = new LinkedList<>();
    private final List<GameParticipant> observers = new LinkedList<>();

    public Game(int id, GameConfigDTO gameConfigDTO) {
        this.id = id;
        this.gameConfigDTO = gameConfigDTO;
    }

    public abstract void addObserver(GameParticipant gameParticipant);

    public abstract void addPlayer(GameParticipant gameParticipant);

    public abstract GameDTO getGameDTO();

    public int getId() {
        return id;
    }

    public List<GameParticipant> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<GameParticipant> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    public GameConfigDTO getGameConfigDTO() {
        return gameConfigDTO;
    }

}
