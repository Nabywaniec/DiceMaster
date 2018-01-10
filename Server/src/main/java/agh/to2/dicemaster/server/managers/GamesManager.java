package agh.to2.dicemaster.server.managers;

import agh.to2.dicemaster.common.UserType;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.User;
import agh.to2.dicemaster.server.api.Game;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GamesManager {

    private Map<Integer, Game> games = new HashMap<>();

    public synchronized Game createGame(GameConfigDTO gameConfigDTO) {
//        TODO: GameFactory.createGame(gameConfigDTO)
        Game game = null;//GameFactory.createGame(gameConfigDTO)
        games.put(game.getId(), game);
        return game;
    }

    public synchronized Optional<Game> getGameById(int gameId) {
        return Optional.ofNullable(games.get(gameId));
    }

    public synchronized Collection<GameDTO> getAll() {
        return games.values().stream().map(Game::getGameDTO).collect(Collectors.toSet());
    }

    public synchronized Game removeGame(int gameId) {
        return games.remove(gameId);
    }

    public synchronized void addUserToGame(UserType userType, User user, int gameId) {
        Optional<Game> game = this.getGameById(gameId);
        if(game.isPresent()){
            if(userType == UserType.PLAYER){
                game.get().addPlayer(user);
            } else if(userType == UserType.OBSERVER){
                game.get().addObserver(user);
            }
        }
    }

    @Scheduled(fixedRate = 20000) //every 20 sec
    public synchronized void removeIdleGames(){
        this.games = games.entrySet()
                .stream()
                .filter(entry ->
                        Stream.concat(entry.getValue().getPlayers().stream(), entry.getValue().getObservers().stream())
                        .anyMatch(player -> player instanceof User)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
