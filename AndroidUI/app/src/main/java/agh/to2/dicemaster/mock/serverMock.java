package agh.to2.dicemaster.mock;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.client.api.Server;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.common.api.Dices;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.GameType;
import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.common.api.UserInGame;
import agh.to2.dicemaster.common.api.UserType;

/**
 * Created by Artur on 20.01.2018.
 */

public class serverMock implements Server{
    private String userName;
    private Dices mockDices = new Dices();
    private UserInGame p1 = new UserInGame("mock player 1",mockDices,0,false);
    private ArrayList<UserInGame> list1 = new ArrayList<UserInGame>();
    private ArrayList<String> list2 = new ArrayList<String>();
    private String o1 = "mock observer 1";
    private GameConfigDTO mockGameConfigDTO = new GameConfigDTO("mock table",3, GameType.NPLUS,0,0,10);
    private GameDTO mockGameDTO;
    private ArrayList<GameDTO> mockGameList;
    private int id = 1;

    public serverMock(){
        list1.add(p1);
        list2.add(o1);
        mockGameDTO = new GameDTO(1,mockGameConfigDTO,list1,list2);
        mockGameList = new ArrayList<>();
        mockGameList.add(mockGameDTO);
    }

    @Override
    public ServerGame createGame(GameConfigDTO gameConfigDTO, GameEventHandler gameEventHandler, UserType userType) {
        UserInGame userInGame = new UserInGame(this.userName,new Dices(),0,false);
        ArrayList<UserInGame> list = new ArrayList<UserInGame>();
        list.add(userInGame);
        GameDTO mockGameDTO2 = new GameDTO(id,gameConfigDTO,list,list2);
        id++;
        ServerGame mockServerGame = new ServerGame(mockGameDTO2) {
            @Override
            public void makeMove(MoveDTO moveDTO) {

            }

            @Override
            public void leaveGame() {

            }
        };
        this.mockGameList.add(mockGameDTO2);
        return mockServerGame;
    }

    @Override
    public ServerGame requestJoinGame(GameDTO gameDTO, GameEventHandler gameEventHandler, UserType userType) {
        ServerGame mockServerGame = new ServerGame(gameDTO) {
            @Override
            public void makeMove(MoveDTO moveDTO) {

            }

            @Override
            public void leaveGame() {

            }
        };
        return mockServerGame;
    }

    @Override
    public List<GameDTO> getAvailableGames() {
        return this.mockGameList;
    }

    @Override
    public boolean registerClient(String s) {
        this.userName = s;
        return true;
    }
}
