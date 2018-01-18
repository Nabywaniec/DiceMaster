package diceMaster.model.gui;

import agh.to2.dicemaster.common.api.GameDTO;

public interface GameEventHandler {
    void refreshGame(GameDTO game);
}
