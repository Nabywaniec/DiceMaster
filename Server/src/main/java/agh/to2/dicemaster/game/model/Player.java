package agh.to2.dicemaster.game.model;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

import java.util.Collection;

public class Player extends GameParticipant {
    private static final int NUMBER_OF_DICES = 5;

    private final Dice[] dices = createDices();
    private int score;

    private static Dice[] createDices() {
        Dice[] dices = new Dice[NUMBER_OF_DICES];
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Dice();
        }
        return dices;
    }


    public void rollOne(int diceToRoll) {
        dices[diceToRoll].roll();
    }

    public void rollMany(Collection<Integer> dicesToRoll) {
        for (Integer diceToRoll : dicesToRoll) {
            rollOne(diceToRoll);
        }
    }

    public Dice[] getDices() {
        return dices.clone();
    }

    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {

    }

    @Override
    public void registerPlayerEventHandler(PlayerEventHandler playerEventHandler) {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
