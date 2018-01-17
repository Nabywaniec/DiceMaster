package agh.to2.dicemaster.game.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import agh.to2.dicemaster.game.model.Dice;
import agh.to2.dicemaster.game.poker.PokerScore;
import org.junit.jupiter.api.Test;


public class PokerTests {

    private static final int N = 5;

    @Test
    public void pokerTest() {

        Dice[] set1 = new Dice[N];
        set1[0] = new Dice();
        set1[0].setValue(Dice.Value.ONE);
        set1[1] = new Dice();
        set1[1].setValue(Dice.Value.TWO);
        set1[2] = new Dice();
        set1[2].setValue(Dice.Value.THREE);
        set1[3] = new Dice();
        set1[3].setValue(Dice.Value.FOUR);
        set1[4] = new Dice();
        set1[4].setValue(Dice.Value.FIVE);
        assertEquals(PokerScore.getScore(set1), 130);
        


    }
}
