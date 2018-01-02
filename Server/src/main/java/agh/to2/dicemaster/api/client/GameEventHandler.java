package agh.to2.dicemaster.api.client;

import agh.to2.dicemaster.api.common.GameDTO;

public interface GameEventHandler {
    void onGameChange(GameDTO gameDTO);
}
