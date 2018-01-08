package agh.to2.dicemaster.server.mocks;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;

public class GameFactory {
    public Game CreateGame(GameConfigDTO gameConfigDTO) {
        return new Game() {
            @Override
            public void addObserver(GameParticipant gameParticipant) {
                System.out.printf("Adding observer %s to Game ID = + %d\n", gameParticipant, super.getId());
            }

            @Override
            public void addPlayer(GameParticipant gameParticipant) {
                System.out.printf("Adding player %s to Game ID = + %d\n", gameParticipant, super.getId());
            }

            @Override
            public GameDTO getGameDTO() {
                return null;
            }
        };
    }
}
