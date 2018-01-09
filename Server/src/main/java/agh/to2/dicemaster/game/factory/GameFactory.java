package agh.to2.dicemaster.game.factory;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameType;
import agh.to2.dicemaster.game.poker.PokerGame;
import agh.to2.dicemaster.server.api.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GameFactory {
    public Game createGame(GameConfigDTO gameConfigDTO) {
        if(gameConfigDTO.getGameType().equals(GameType.POKER)){
            return new PokerGame();
        }

        throw new NotImplementedException();
    }
}
