package diceMaster.controller;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.common.api.UserInGame;
import diceMaster.model.gui.GameEventHandler;
import diceMaster.model.server.ServerGame;
import diceMaster.view.DicesField;
import diceMaster.view.UserInGameFilled;
import diceMaster.view.UserInGameListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;

import java.util.LinkedList;
import java.util.List;


public class InGameController implements GameEventHandler {
    private DiceMasterOverviewController appController;
    private ServerGame serverGame;

    @FXML
    BorderPane borderPane;

    @FXML
    Button reRollButton;

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

    public void setAppController(DiceMasterOverviewController appController) {
        this.appController = appController;
        this.bindSizeProperties();
        this.dicesField.setDicesFiledScale(1);
    }

    private void bindSizeProperties(){
        this.splitGameWindowLine.endXProperty().bind(borderPane.widthProperty());
    }


    public void handleReRoll(ActionEvent event) {

        boolean [] dicesToReroll = new boolean[5];
        for(int i=0; i<5; i++)
            if(dicesField.getDiceViews().get(i).isSelected())
                dicesToReroll[i] = true;
            else
                dicesToReroll[i] = false;
        MoveDTO moveDTO = new MoveDTO(dicesToReroll);

        for(int i=0; i<5; i++)
            if(dicesField.getDiceViews().get(i).isSelected())
                dicesField.getDiceViews().get(i).setSelected(false);
        reRollButton.setDisable(true);

        serverGame.makeMove(moveDTO);
    }

    public void handleDicesFieldMouseClicked(MouseEvent mouseEvent) {
        boolean flag = false;
        for(int i=0; i<5; i++)
            if(dicesField.getDiceViews().get(i).isSelected())
                flag=true;
        reRollButton.setDisable(!flag);
    }


    @Override
    public void refreshGame(GameDTO game) {
        this.setPlayersListToView(game.getPlayers());
    }

    public void setServerGame(ServerGame serverGame) {
        this.serverGame = serverGame;
        this.refreshGame(serverGame.getGameDTO());
    }

    private void setPlayersListToView(List<UserInGame> players){
        List<UserInGame> beforeMove = new LinkedList<>();
        List<UserInGame> afterMove = new LinkedList<>();
        boolean foundCurrentPlayer = false;

        for(UserInGame player: players){
            if(player.isHisTurn()){
                foundCurrentPlayer = true;
                this.currentUser.init(player);
                continue;
            }

            if(foundCurrentPlayer){
                afterMove.add(player);
            } else {
                beforeMove.add(player);
            }
        }
        this.playersMoved.init(afterMove);
        this.playersWaitingForMove.init(beforeMove);
    }
}
