package agh.to2.dicemaster.game.model;


import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

public class Player extends GameParticipant {
    private static final int NUMBER_OF_DICES = 5;
    private final GameParticipant gameParticipant;
    private final Dice[] dices = DiceManager.getDices(NUMBER_OF_DICES);
    private int score = 0;

    public Player(GameParticipant gameParticipant, Game game) {
        this.gameParticipant = gameParticipant;
    }

    public void rollDices(Iterable<Integer> dicesToRoll) {

        for (Integer diceNumber : dicesToRoll) {
            dices[diceNumber].roll();
        }
    }

    public Dice[] getDices() {
        return dices.clone();
    }

    public Dice.Value[] getDicesValues() {
        Dice.Value[] values = new Dice.Value[dices.length];

        for (int i = 0; i < values.length; i++) {
            values[i] = dices[i].getValue();
        }

        return values;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {
        gameParticipant.notifyGameStateChange(gameDTO);
    }

    @Override
    public void registerPlayerEventHandler(PlayerEventHandler playerEventHandler) {
        gameParticipant.registerPlayerEventHandler(playerEventHandler);
    }
}
