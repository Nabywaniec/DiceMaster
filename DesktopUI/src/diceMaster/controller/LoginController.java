package diceMaster.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class LoginController {
    private DiceMasterOverviewController diceMasterOverviewController;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField userNameTextField;
    @FXML
    private Text loginText;
    @FXML
    private Button loginButton;

    public void setDiceMasterOverviewController(DiceMasterOverviewController diceMasterOverviewController) {
        this.diceMasterOverviewController = diceMasterOverviewController;
        this.bindSizeProperties();
    }

    private void bindSizeProperties() {
    }

    public void handleLoginEvent(MouseEvent mouseEvent) {
        if(userNameTextField.getText().isEmpty()){
            showAlert("Type your nick first!");
            return;
        }
        if(userNameTextField.getText().startsWith("bot#")){
            showAlert("Your nick can't start with \"bot#\"!");
            return;
        }
        if(userNameTextField.getText().startsWith(" ")){
            showAlert("Your nick can't start with white character!");
            return;
        }

        // if server will add some exceptions put here try catch
        if (!diceMasterOverviewController.getServer().registerClient(loginText.getText())) {
            showAlert("Couldn't login!");
            return;
        }
        this.diceMasterOverviewController.setUserNickName(loginText.getText());
        this.diceMasterOverviewController.showGamesTable();
    }

    public void showAlert(String alertMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("DiceMaster - Login");
        alert.setHeaderText("DiceMaster - Login");
        alert.setContentText(alertMessage);
        alert.show();
    }
}

