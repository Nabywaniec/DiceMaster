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
    List<Player> players = new ArrayList<>();
    List<GameParticipant> observers=new LinkedList<GameParticipant>();

    public NGame(int id, GameConfigDTO gameConfigDTO) {
        super(id,gameConfigDTO);
        this.gameConfigDTO=gameConfigDTO;
         /* for (int i=0; i< gameConfigDTO.getEasyBotsCount();i++) {

        }
        for (int i=0; i< gameConfigDTO.getHardBotsCount();i++) {

        }*/
        if (gameConfigDTO.getGameType() == GameType.NPLUS) {
            this.rules = new NPlus();
        }
        if (gameConfigDTO.getGameType() == GameType.NTIMES) {
            this.rules = new NTimes();
        }
        GameRunner gameRunner=new GameRunner(rules,players,this);


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
        return Collections.unmodifiableList(players);
    }

    @Override
    public List<GameParticipant> getObservers() {
        return observers;
    }

    @Override
    public GameDTO getGameDTO() {
        return null;
    }


}


