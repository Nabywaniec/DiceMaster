package agh.to2.dicemaster.model;

import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.client.api.Server;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.common.api.*;
import agh.to2.dicemaster.activity.MainActivity;

public class AppState implements GameEventHandler {
//    private LoginActivity loginActivity;// = new LoginActivity();
//    private LobbyActivity lobbyActivity;// = new LobbyActivity();
//    private TableCreatorActivity tableCreatorActivity;// = new TableCreatorActivity();
    private GameEventHandler gameEventHandler = new AndroidUIGameEventHandler();
    public static AppState appstate;

    private Server server;
    private String userName;
    private ServerGame serverGame;
    private UserInGame userInGame;

    private UserType userType;

    public AppState(MainActivity mainActivity) {
        //main activity launches when the app launches
        this.appstate = this;
    }

    @Override
    public void onGameChange(GameDTO gameDTO) {
        ;
    }


    public void initServer(Server server){
        this.server = server;
    }

    public Server getServer(){
        return this.server;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setServerGame(ServerGame serverGame){
        this.serverGame = serverGame;
    }

    public ServerGame getServerGame(){
        return this.serverGame;
    }

    public GameEventHandler getGameEventHandler() {return this.gameEventHandler;}

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType(){
        return this.userType;
    }
}
