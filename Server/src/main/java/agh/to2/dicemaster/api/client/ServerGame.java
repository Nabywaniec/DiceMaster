package agh.to2.dicemaster.api.client;

import agh.to2.dicemaster.api.common.GameDTO;
import agh.to2.dicemaster.api.common.MoveDTO;

public abstract class ServerGame {

    private GameDTO gameDTO;

    public ServerGame(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    abstract void makeMove(MoveDTO moveDTO);
    abstract void leaveGame();
}
