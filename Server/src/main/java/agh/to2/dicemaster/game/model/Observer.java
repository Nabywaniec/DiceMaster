package agh.to2.dicemaster.game.model;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Observer extends GameParticipant {


    private ArrayList<GameDTO> gamesToObserve;

    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {

    }

    @Override
    public void registerPlayerEventHandler(PlayerEventHandler playerEventHandler) {

    }

    public HashMap<Player, Integer> getRoundResult(GameDTO game){
        for(GameDTO gameDTO:gamesToObserve){
            if(gameDTO.getId() == game.getId()){
                return game.getGameManager().getRound_results();
            }
        }
        return null;
    }

    public HashMap<Player, Integer> getGameResult(GameDTO game){
        for(GameDTO gameDTO:gamesToObserve){
            if(gameDTO.getId() == game.getId()){
                return game.getGameManager().getGame_results();
            }
        }
        return null;
    }
}

