package diceMaster.controller;

import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.common.api.*;
import diceMaster.view.DicesField;
import diceMaster.view.UserInGameFilled;
import diceMaster.view.UserInGameListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.List;


public class InGameController implements GameEventHandler {
    private DiceMasterOverviewController appController;
    private ServerGame serverGame;
    private Text timerText;

    @FXML
    BorderPane borderPane;

    @FXML
    Button reRollButton;

    @FXML
    Button exitButton;

    @FXML
    Button skipTurnButton;

    @FXML
    DicesField dicesField;

    @FXML
    Line splitGameWindowLine;

    @FXML
    UserInGameListView playersWaitingForMove;

    @FXML
    UserInGameListView playersMoved;

    @FXML
    UserInGameFilled currentUser;

    @FXML
    Text tablesTypeText;

    @FXML
    Text roundsToWin;

    @FXML
    Text scoreInRound;

    @FXML
    Group mainGroup;

    public void setAppController(DiceMasterOverviewController appController) {
        this.appController = appController;
        this.bindSizeProperties();
        this.reRollButton.setDisable(true);
        this.dicesField.setDicesFiledScale(1);
    }

    private void bindSizeProperties() {
        this.splitGameWindowLine.endXProperty().bind(borderPane.widthProperty());
    }

    public void onGameChange(GameDTO game) {
        if (!isStillInGame(game.getPlayers())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kicked");
            alert.setHeaderText("");
            alert.setContentText("");
            alert.show();
            this.appController.showGamesTable();
        }
        this.playersWaitingForMove.getChildren().clear();
        this.playersMoved.getChildren().clear();
        this.currentUser.getChildren().clear();

        for (UserInGame u : game.getPlayers()) {
            if (u.getUserName().equals(this.appController.getUserNickName())) {
                this.dicesField.setDicesDots(u.getDices().getDicesScore());
                if (u.isHisTurn()) {
                    this.reRollButton.setDisable(false);
                    this.skipTurnButton.setDisable(false);
                    this.dicesField.setCanBeSelected();
                    this.startTimer();
                }
            }
        }

        this.setPlayersListToView(game.getPlayers());

        if (serverGame.getGameDTO().getGameConfig().getGameType() != GameType.POKER)
            scoreInRound.setText("Score to win round: " + String.valueOf(serverGame.getGameDTO().getScoreToWin()));
    }

    private void startTimer(){
        this.timerText = new Text();
        timerText.setText("30");
        timerText.setFont(Font.font(30));
        timerText.setLayoutX(870);
        timerText.setLayoutY(490);
        this.mainGroup.getChildren().add(timerText);
        Thread timeThread = new Thread() {
            @Override
            public void run() {
                while(Integer.valueOf(timerText.getText()) > 0) {
                    try {
                        sleep(1000);
                        timerText.setText(String.valueOf(Integer.valueOf(timerText.getText()) - 1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        showAlert("Something went wrong with timer!!!");
                    }
                }
            }
        };
        timeThread.start();
    }

    private void stopTimer(){
        this.mainGroup.getChildren().remove(this.timerText);
    }

    public void setServerGame(ServerGame serverGame) {
        this.serverGame = serverGame;
        this.tablesTypeText.setText("Table's type: " + serverGame.getGameDTO().getGameConfig().getGameType().toString());
        this.roundsToWin.setText("Rounds to win: " + String.valueOf(serverGame.getGameDTO().getGameConfig().getRoundsToWin()));
        this.onGameChange(serverGame.getGameDTO());
    }

    private void setPlayersListToView(List<UserInGame> players) {
        List<UserInGame> beforeMove = new LinkedList<>();
        List<UserInGame> afterMove = new LinkedList<>();
        boolean foundCurrentPlayer = false;

        for (UserInGame player : players) {
            if (player.isHisTurn()) {
                foundCurrentPlayer = true;
                this.currentUser.init(player);
                System.out.println(player);
                continue;
            }
            if (foundCurrentPlayer) {
                beforeMove.add(player);
            } else {
                afterMove.add(player);
            }
        }
        this.playersMoved.init(afterMove);
        this.playersWaitingForMove.init(beforeMove);
    }

    public void handleExit(ActionEvent actionEvent) {
        this.serverGame.leaveGame();
        this.appController.showGamesTable();
    }

    public void handleReRoll(ActionEvent event) {
        boolean[] dicesToReroll = new boolean[5];
        for (int i = 0; i < 5; i++)
            if (this.dicesField.getDiceViews().get(i).isSelected())
                dicesToReroll[i] = true;
            else
                dicesToReroll[i] = false;
        MoveDTO moveDTO = new MoveDTO(dicesToReroll);

        for (int i = 0; i < 5; i++)
            if (dicesField.getDiceViews().get(i).isSelected())
                dicesField.getDiceViews().get(i).setSelected(false);
        this.reRollButton.setDisable(true);
        this.skipTurnButton.setDisable(true);

        this.serverGame.makeMove(moveDTO);
        this.stopTimer();
    }

    public void handleSkipTurn(ActionEvent actionEvent) {
        boolean[] dicesToReroll = new boolean[5];
        for (int i = 0; i < 5; i++)
            dicesToReroll[i] = false;

        MoveDTO moveDTO = new MoveDTO(dicesToReroll);

        for (int i = 0; i < 5; i++)
            if (dicesField.getDiceViews().get(i).isSelected())
                dicesField.getDiceViews().get(i).setSelected(false);

        this.reRollButton.setDisable(true);
        this.skipTurnButton.setDisable(true);

        this.serverGame.makeMove(moveDTO);
        this.stopTimer();
    }


    public void showAlert(String alertMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("DiceMaster - Game in progress");
        alert.setHeaderText("DiceMaster - Game in progress");
        alert.setContentText(alertMessage);
        alert.show();
    }

    private boolean isStillInGame(List<UserInGame> players) {
        for (UserInGame userInGame : players) {
            if (userInGame.getUserName().equals(this.appController.getUserNickName()))
                return true;
        }
        return false;
    }
}
