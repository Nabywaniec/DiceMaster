package agh.to2.dicemaster.game.ngames;


import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.GameType;
import agh.to2.dicemaster.game.nmodel.*;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;

import java.util.*;

public class NGame extends Game {
    GameConfigDTO gameConfigDTO;
    Rules rules;
    List<Player> players = new ArrayList<Player>();
    List<GameParticipant> observers=new LinkedList<GameParticipant>();

    public NGame(int id, GameConfigDTO gameConfigDTO) {
        super(id,gameConfigDTO);
        this.gameConfigDTO=gameConfigDTO;
        if (gameConfigDTO.getGameType() == GameType.NPLUS) {
            rules = new NPlus();
        }
        if (gameConfigDTO.getGameType() == GameType.NTIMES) {
            rules = new NTimes();
        }
      /* for (int i=0; i< gameConfigDTO.getEasyBotsCount();i++) {

       }
        for (int i=0; i< gameConfigDTO.getHardBotsCount();i++) {

        }*/
      rules.initializeRound(players);
        for (Player p:players) {
            rules.initializeDices(p);
        }
    }

    @Override
    public void addObserver(GameParticipant gameParticipant) {
        observers.add(gameParticipant);
    }

    @Override
    public void addPlayer(GameParticipant gameParticipant) {
        Player player = new Player(gameParticipant);
        players.add(player);
    }



    @Override
    public List<GameParticipant> getPlayers() {
        return null;
    }

    @Override
    public List<GameParticipant> getObservers() {
        return null;
    }

    @Override
    public GameDTO getGameDTO() {
        return null;
    }


}


