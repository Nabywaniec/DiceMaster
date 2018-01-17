package diceMaster.controller;


import agh.to2.dicemaster.common.api.GameDTO;
import diceMaster.Main;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class GamesTableController {
    private DiceMasterOverviewController diceMasterOverviewController;
    private ObservableList<GameDTO> listOfGames;

    @FXML
    BorderPane borderPane;

    @FXML
    Button createGameButton;

    @FXML
    Button joinGameAsPlayerButton;

    @FXML
    Button joinGameAsObserverButton;

    @FXML
    TableView<GameDTO> gamesTable;

    @FXML
    TableColumn<GameDTO, String> tableNameColumn;

    @FXML
    TableColumn<GameDTO, Integer> playersOnTableColumn;

    @FXML
    TableColumn<GameDTO, Integer> easyBotsNumberColumn;

    @FXML
    TableColumn<GameDTO, Integer> hardBotsNumberColumn;


    public void setDiceMasterOverviewController(DiceMasterOverviewController appController) {
        this.diceMasterOverviewController = appController;
        this.listOfGames = FXCollections.observableArrayList();
        this.bindSizeProperties();
        this.init();
    }

    public void init() {
        this.gamesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.tableNameColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getGameConfig().getTableName()));
        this.playersOnTableColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<Integer>(dataValue.getValue().getPlayers().size()));
        this.easyBotsNumberColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<Integer>(dataValue.getValue().getGameConfig().getEasyBotsCount()));
        this.hardBotsNumberColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<Integer>(dataValue.getValue().getGameConfig().getHardBotsCount()));
        this.listOfGames.addAll(this.diceMasterOverviewController.getServer().getAvailableGames());
        this.gamesTable.setItems(listOfGames);
    }

    private void bindSizeProperties() {

    }

    public boolean showCreateGameDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CreateGameDialog.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create game");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.diceMasterOverviewController.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            CreateGameController presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            presenter.setDiceMasterOverviewController(this.diceMasterOverviewController);
            presenter.init();
            dialogStage.showAndWait();
            return presenter.isApproved();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createGameActionHandler(MouseEvent mouseEvent) {
        showCreateGameDialog();
    }

    public void joinAsPlayerGameActionHandler(MouseEvent mouseEvent) {

        //server.joinGame(null,null, null);
        System.out.println(gamesTable.getSelectionModel().getSelectedItem());
        diceMasterOverviewController.showGame();
    }

    public void refreshGamesTable() {
        int selectedGame = -1;
        if(!gamesTable.getSelectionModel().isEmpty()){
            selectedGame = gamesTable.getSelectionModel().getSelectedItem().getId();
        }
        this.listOfGames.clear();
        this.listOfGames.addAll(this.diceMasterOverviewController.getServer().getAvailableGames());
        if (selectedGame != -1) {
            for (GameDTO gameDTO : listOfGames) {
                if (gameDTO.getId() == selectedGame) {
                    gamesTable.getSelectionModel().select(gameDTO);
                }
            }
        }
    }

    public void joinAsObserverGameActionHandler(MouseEvent mouseEvent) {
        diceMasterOverviewController.getServer().requestJoinGame(null, null, null);
    }

}