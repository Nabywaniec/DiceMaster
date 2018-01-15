package agh.to2.dicemaster.client.api;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;

public abstract class ServerGame {

    private GameDTO gameDTO;

    public ServerGame(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    abstract void makeMove(MoveDTO moveDTO);
    abstract void leaveGame();
}
