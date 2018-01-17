package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.game.model.Observer;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PokerGame extends Game {

    private PokerGameManager pokerGameManager;
    private List<Player> players = new LinkedList<>();
    private List<GameParticipant> observers = new LinkedList<>();

    public PokerGame(int id, GameConfigDTO gameConfigDTO) {
        super(id, gameConfigDTO);
    }

    List<Player> getPlayerList() {
        return players;
    }

    @Override
    public void addObserver(GameParticipant gameParticipant) {
        PokerPlayerEventHandler pokerPlayerEventHandler = new PokerPlayerEventHandler(pokerGameManager);
        observers.add(gameParticipant);
        gameParticipant.registerPlayerEventHandler(pokerPlayerEventHandler);
    }

    @Override
    public void addPlayer(GameParticipant gameParticipant) {
        PokerPlayerEventHandler pokerPlayerEventHandler = new PokerPlayerEventHandler(pokerGameManager);
        Player player = new Player(gameParticipant);
        players.add(player);
        player.registerPlayerEventHandler(pokerPlayerEventHandler);
        if (getGameConfigDTO().getMaxPlayers() == players.size()) {
            pokerGameManager.onGameStart();
        }
    }

    @Override
    public List<GameParticipant> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    @Override
    public List<GameParticipant> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    @Override
    public GameDTO getGameDTO() {
        return null;
    }
}
