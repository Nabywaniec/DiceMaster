package agh.to2.dicemaster.api.server;

import agh.to2.dicemaster.api.common.MoveDTO;

public interface PlayerEventHandler {
    void onMoveRequest(MoveDTO moveDTO);
    void onPlayerLeft();
}
