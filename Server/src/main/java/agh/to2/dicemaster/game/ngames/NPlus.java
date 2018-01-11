package agh.to2.dicemaster.game.ngames;

import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.game.nmodel.Dice;
import agh.to2.dicemaster.game.nmodel.Player;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.List;
import java.util.Random;

public class NPlus implements Rules {
    public Integer aim;

    public NPlus() {

    }


    @Override
    public  boolean countPoints(Player player) {
       if(player.getScore()==aim){
           return true;
       }
       else return false;
    }

    @Override
    public void initializeDices(Player player) {
        for (int i = 0; i < 5; i++) {
           player.setDice(i, Dice.randomDice());
        }
    }


    @Override
    public void drawDices(Player player, MoveDTO move) {
        for (int i = 0; i < 5; i++) {

        }
    }


    @Override
    public Integer getAim() {
        return aim;
    }

    @Override
    public int initializeRound(List<Player> players) {
        int[] dices = new int[5];
        Integer aim = 1;
        Random generator = new Random();
        for (int i = 0; i < 5; i++) {
            dices[i] = generator.nextInt(6) + 1;
            aim += dices[i];
        }
        return generator.nextInt(players.size());
    }


}
