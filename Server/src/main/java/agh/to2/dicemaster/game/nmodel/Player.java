package agh.to2.dicemaster.game.nmodel;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.User;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

/**
 * Created by werka on 10.01.18.
 */
public class Player extends GameParticipant{

    Dice[] dices = new Dice[5];
    Integer score;
    GameParticipant gameParticipant;
    public Player(GameParticipant gameParticipant) {
        this.gameParticipant = gameParticipant;
        this.score=0;
    }


    public Dice[] getDices() {
        return dices;
    }

    public void setDice(int  i, Dice dice){
        dices[i] = dice;
    }


    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {

    }

    @Override
    public void registerPlayerEventHandler(PlayerEventHandler playerEventHandler) {

    }
}