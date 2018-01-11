package agh.to2.dicemaster.client.gui;

import agh.to2.dicemaster.client.api.*;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;

public class PlayerPresenter {
    private LoginView loginView = new LoginView();
    private LobbyView lobbyView = new LobbyView();
    private TableCreatorView tableCreatorView = new TableCreatorView();
    private TableView tableView = new TableView();

    private Server server;
    private ServerGame serverGame;
    private Player player;
    private String userName;

    public PlayerPresenter() {
        loginView.show();
    }

    public void onLoginButtonPress(String userName) {
        if (!server.registerClient(userName));      //TODO: komunikat o błędzie
        else {
            this.userName = userName;
            lobbyView.refresh(userName, server.getAvailableGames());
            loginView.hide();
            lobbyView.show();
        }
    }

    public void onTableListClick() {
        //TODO
    }

    public void onJoinTable(GameDTO gameDTO, UserType userType) {
        if (server.requestJoinGame(gameDTO, null, userType) == null);    //TODO: GameEventHandler, komunikat o błędzie
        else {
            tableView.refresh(gameDTO, userType);
            lobbyView.hide();
            tableView.show();
        }

    }

    public void onLobbyCreateTableButtonClick() {
        lobbyView.hide();
        tableCreatorView.show();
    }

    public void onCreateTableButtonClick(GameConfigDTO gameConfigDTO, UserType userType) {
        serverGame = server.createGame(gameConfigDTO, null, userType);

        if (serverGame == null);      //TODO: GameEventHandler, komunikat o błędzie
        else {
            tableView.refresh(serverGame.getGameDTO(), userType);
            tableCreatorView.hide();
            tableView.show();
        }
    }

    public void onDiceClick(MoveDTO moveDTO) {

    }

    public void onRollButtonClick(MoveDTO moveDTO) {
        serverGame.makeMove(moveDTO);
    }

    public void onLeaveTableButtonClick() {
        serverGame.leaveGame();
        tableView.hide();
        lobbyView.show();
    }

    public void onGameStatusRefresh(GameDTO gameDTO) {
        tableView.refresh(gameDTO, null);   //TODO: userType
    }
}
