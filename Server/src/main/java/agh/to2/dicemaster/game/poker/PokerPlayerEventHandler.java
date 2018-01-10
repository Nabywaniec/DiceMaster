package agh.to2.dicemaster.game.poker;

import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

public class PokerPlayerEventHandler implements PlayerEventHandler {
    private PokerGameManager manager;

    public PokerPlayerEventHandler(PokerGameManager manager) {
        this.manager = manager;
    }


    @Override
    public void onMoveRequest(MoveDTO moveDTO) {
        manager.performMove(moveDTO);
    }

    @Override
    public void onPlayerLeft() {

    }
}
