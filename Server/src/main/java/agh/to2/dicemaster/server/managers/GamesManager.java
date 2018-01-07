package agh.to2.dicemaster.server.managers;

import agh.to2.dicemaster.common.UserType;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.User;
import agh.to2.dicemaster.server.api.Game;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GamesManager {

    private HashMap<Integer, Game> games = new HashMap<>();

    public Game createGame(GameConfigDTO gameConfigDTO) {
//        TODO: GameFactory.createGame(gameConfigDTO)
        Game game = null;//GameFactory.createGame(gameConfigDTO)
        games.put(game.getId(), game);
        return null;
    }

    public Optional<Game> getGameById(int gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    public Collection<GameDTO> getAll() {
        return games.values().stream().map(Game::getGameDTO).collect(Collectors.toSet());
    }

    public Game removeGame(int gameId) {
        return games.remove(gameId);
    }

    public void addUserToGame(UserType userType, User user, int gameId) {
        Optional<Game> game = this.getGameById(gameId);
        if(game.isPresent()){
            if(userType == UserType.PLAYER){
                game.get().addPlayer(user);
            } else if(userType == UserType.OBSERVER){
                game.get().addObserver(user);
            }
        }
    }
}
