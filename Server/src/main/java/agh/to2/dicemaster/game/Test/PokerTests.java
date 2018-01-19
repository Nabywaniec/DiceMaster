package agh.to2.dicemaster.game.Test;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.game.model.Dice;
import agh.to2.dicemaster.game.model.Player;
import agh.to2.dicemaster.game.model.Timer;
import agh.to2.dicemaster.game.poker.PokerGame;
import agh.to2.dicemaster.game.poker.PokerGameManager;
import agh.to2.dicemaster.game.poker.PokerScore;
import agh.to2.dicemaster.server.User;
import agh.to2.dicemaster.server.api.GameParticipant;
import org.junit.Test;
//import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
//import static org.testng.AssertJUnit.assertEquals;


public class PokerTests extends Thread {

    private static final int N = 5;

    @Test
    public void pokerAlgoTest() {

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

        set1[0].setValue(Dice.Value.ONE);
        set1[1].setValue(Dice.Value.ONE);
        set1[2].setValue(Dice.Value.THREE);
        set1[3].setValue(Dice.Value.THREE);
        set1[4].setValue(Dice.Value.ONE);
        assertEquals(PokerScore.getScore(set1), 200 + 9);

        set1[0].setValue(Dice.Value.ONE);
        set1[1].setValue(Dice.Value.TWO);
        set1[2].setValue(Dice.Value.TWO);
        set1[3].setValue(Dice.Value.THREE);
        set1[4].setValue(Dice.Value.ONE);
        assertEquals(PokerScore.getScore(set1), 50 + 6);

        set1[0].setValue(Dice.Value.ONE);
        set1[1].setValue(Dice.Value.TWO);
        set1[2].setValue(Dice.Value.THREE);
        set1[3].setValue(Dice.Value.FIVE);
        set1[4].setValue(Dice.Value.SIX);
        assertEquals(PokerScore.getScore(set1), 17);

        set1[0].setValue(Dice.Value.ONE);
        set1[1].setValue(Dice.Value.TWO);
        set1[2].setValue(Dice.Value.THREE);
        set1[3].setValue(Dice.Value.THREE);
        set1[4].setValue(Dice.Value.THREE);
        assertEquals(PokerScore.getScore(set1), 100 + 9);

    }

     @Test
     public void pokerGameTest() {

            // testy działanie Timera

            GameConfigDTO gameConfigDTO = new GameConfigDTO();
            gameConfigDTO.setMaxPlayers(3);
            PokerGame pokerGame = new PokerGame(1, gameConfigDTO);
            PokerGameManager pokerGameManager = new PokerGameManager(pokerGame);
            Timer timer = new Timer(pokerGameManager, 30);

            GameParticipant gameParticipant1 = new GameParticipant("1");
            GameParticipant gameParticipant2 = new GameParticipant("2");
            GameParticipant gameParticipant3 = new GameParticipant("3");

            Player player1 = new Player(gameParticipant1);
            Player player2 = new Player(gameParticipant2);
            Player player3 = new Player(gameParticipant3);

            pokerGame.addPlayer(player1);
            pokerGame.addPlayer(player2);
            pokerGame.addPlayer(player3);
            assertEquals(pokerGame.getObservers().contains(player1), true);

            GameParticipant[] gameParticipants = new GameParticipant[3];
            gameParticipants[0] = player1;
            gameParticipants[1] = player2;
            assertEquals(pokerGame.getPokerGameManager().hasStarted(), false);
            gameParticipants[2] = player3;

            assertEquals(pokerGame.getPlayerList().size(), 3);

            pokerGame.addPlayer(new Player(new GameParticipant("4")));
            assertEquals(pokerGame.getPokerGameManager().hasStarted(), true);

            pokerGameManager.onGameStart();
            timer.run();
            try {
                sleep(35000);
            } catch (InterruptedException ie) {
            }
            assertEquals(timer.getDelay(), true);

            try {
                sleep(28000);
            } catch (InterruptedException ie) {
            }
            assertEquals(timer.getDelay(), false);


            // test spr wygranego w rundzie

            PokerGameManager pokerGameManager1 = pokerGame.getPokerGameManager();
            for (int i = 0; i < 27; i++) {
                pokerGameManager1.onTurnEnd();
            }

            assertEquals(pokerGameManager1, false);
            assertEquals(pokerGameManager1.getRoundNumber(), 4);
            assertEquals(pokerGameManager1.getCurrentPlayer(), 0);
            assertEquals(pokerGameManager1.getTurnNumber(), 0);
            assertEquals(pokerGameManager1.hasEnded(), false);

            for(int i=0;i<18;i++){
                pokerGameManager1.onTurnEnd();
            }
            assertEquals(pokerGameManager1.hasEnded(), true);


        }

      @Test
      public void testAddingPlayer(){

          GameConfigDTO gameConfigDTO = new GameConfigDTO();
          gameConfigDTO.setMaxPlayers(3);
          PokerGame pokerGame = new PokerGame(1, gameConfigDTO);
          PokerGameManager pokerGameManager = new PokerGameManager(pokerGame);
          Timer timer = new Timer(pokerGameManager, 30);

          GameParticipant gameParticipant1 = new GameParticipant("1");
          GameParticipant gameParticipant2 = new GameParticipant("2");
          GameParticipant gameParticipant3 = new GameParticipant("3");

          Player player1 = new Player(gameParticipant1);
          Player player2 = new Player(gameParticipant2);

          pokerGame.addPlayer(player1);
          pokerGame.addPlayer(player2);
          pokerGame.addPlayer(player1);
          pokerGame.addObserver(gameParticipant3);
          assertEquals(pokerGame.getPlayerList().size(), 2);
          assertEquals(pokerGame.getObservers().size(), 1);

          PokerGameManager pokerGameManager1 = pokerGame.getPokerGameManager();
          pokerGameManager1.onPlayerLeft(player1);
          assertEquals(pokerGameManager1.getParticipantsToRemove().size(), 1);
          pokerGameManager1.onTurnEnd();
          assertEquals(pokerGame.getPlayerList().size(), 1);





      }




    }

