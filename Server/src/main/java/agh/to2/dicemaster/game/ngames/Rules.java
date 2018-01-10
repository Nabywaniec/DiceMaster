package agh.to2.dicemaster.game.ngames;

import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.game.nmodel.Player;

import java.util.List;

public interface Rules {
    Integer getresult(Player player);

    void initializeDices(Player player);

    void drawDices(Player player, MoveDTO move);

    Integer getAim();


    void initializeRound(List<Player> players);
}
