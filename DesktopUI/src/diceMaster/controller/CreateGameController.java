package diceMaster.controller;

import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameType;
import agh.to2.dicemaster.common.api.UserType;
import diceMaster.model.server.ServerGame;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public class CreateGameController extends Pane {
    private boolean approved = false;
    private GameConfigDTO gameConfigDTO;
    private Stage dialogStage;
    private DiceMasterOverviewController diceMasterOverviewController;

    @FXML
    private TextField tableNameTextFiled;

    @FXML
    private CheckBox joinAsPlayerCheckBox;

    @FXML
    private CheckBox joinAsObserverCheckBox;

    @FXML
    private ComboBox<String> gameTypeComboBox;

    @FXML
    private Spinner<Integer> maxPlayersSpinner;

    @FXML
    private Spinner<Integer> easyBotsSpinner;

    @FXML
    private Spinner<Integer> hardBotsSpinner;

    @FXML
    private Spinner<Integer> roundsToWinSpinner;


    public CreateGameController() {
    }

    public void init(){
        makeSpinnerEditableOnlyForNumbers(maxPlayersSpinner);
        makeSpinnerEditableOnlyForNumbers(easyBotsSpinner);
        makeSpinnerEditableOnlyForNumbers(hardBotsSpinner);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage =  dialogStage;
    }

    public boolean isApproved() {
        return approved;
    }

    public void handleJoinAsPlayerCheckBox(MouseEvent mouseEvent) {
        joinAsPlayerCheckBox.setSelected(true);
        joinAsObserverCheckBox.setSelected(false);
    }

    public void handleJoinAsObserverCheckBox(MouseEvent mouseEvent) {
        joinAsPlayerCheckBox.setSelected(false);
        joinAsObserverCheckBox.setSelected(true);
    }

    public void handleCancelClicked(MouseEvent mouseEvent) {
        dialogStage.close();
    }

    public void handleCreateClicked(MouseEvent mouseEvent) {
        String tableName = tableNameTextFiled.getText();
        int roundsToWin = roundsToWinSpinner.getValue();
        int maxPlayers = maxPlayersSpinner.getValue();
        GameType gameType =  fromStringToGameType(gameTypeComboBox.getValue());
        int hardBotsCount = hardBotsSpinner.getValue();
        int easyBotsCount = easyBotsSpinner.getValue();

        UserType userType = UserType.OBSERVER;
        if(joinAsPlayerCheckBox.isSelected())
            userType = UserType.PLAYER;

        if(tableName.isEmpty()) {
            showAlert("Table's name field cannot be empty!");
            return;
        }
        if(roundsToWin < 1) {
            showAlert("Rounds to win must be bigger than 0!");
            return;
        }
        if(tableName.startsWith(" ")) {
            showAlert("Table's name cannot start with white char!");
            return;
        }
        if(maxPlayers + easyBotsCount + hardBotsCount <= 1) {
            showAlert("There has to be at least 2 game participants (bots/players) !");
            return;
        }

        this.gameConfigDTO = new GameConfigDTO(
                tableName,
                maxPlayers,
                gameType,
                hardBotsCount,
                easyBotsCount,
                roundsToWin);

        ServerGame serverGame = this.diceMasterOverviewController.getServer().createGame(
                gameConfigDTO,
                this.diceMasterOverviewController.showGame(),
                userType);
        this.diceMasterOverviewController.initInGameController().setServerGame(serverGame);
        dialogStage.close();
    }

    private <T> void commitEditorText(Spinner<T> spinner) {
        if (!spinner.isEditable()) return;
        String text = spinner.getEditor().getText();
        SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                T value = converter.fromString(text);
                valueFactory.setValue(value);
            }
        }
    }

    private GameType fromStringToGameType(String gameTypeString){
        if(gameTypeString == "Poker")
            return GameType.POKER;
        if(gameTypeString == "N+")
            return GameType.NPLUS;
        if(gameTypeString == "N*")
            return GameType.NTIMES;
        // to prevent nulls
        return GameType.POKER;
    }

    private void makeSpinnerEditableOnlyForNumbers(Spinner spinner){
        spinner.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            commitEditorText(spinner);
        });
        NumberFormat format = NumberFormat.getIntegerInstance();
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.isContentChange()) {
                ParsePosition parsePosition = new ParsePosition(0);
                format.parse(c.getControlNewText(), parsePosition);
                if (parsePosition.getIndex() == 0 ||
                        parsePosition.getIndex() < c.getControlNewText().length()) {
                    return null;
                }
            }
            return c;
        };
        TextFormatter<Integer> priceFormatter = new TextFormatter<Integer>(
                new IntegerStringConverter(), 0, filter);
       spinner.getEditor().setTextFormatter(priceFormatter);
    }


    public void showAlert(String alertMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("DiceMaster - Create game");
        alert.setHeaderText("DiceMaster - Create game");
        alert.setContentText(alertMessage);
        alert.show();
    }

    public void setDiceMasterOverviewController(DiceMasterOverviewController diceMasterOverviewController) {
        this.diceMasterOverviewController = diceMasterOverviewController;
    }
}
