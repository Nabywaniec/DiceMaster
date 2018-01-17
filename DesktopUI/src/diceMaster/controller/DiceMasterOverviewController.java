package diceMaster.controller;

import diceMaster.Main;
import diceMaster.mockaps.FakeServer;
import diceMaster.model.server.Server;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;

public class DiceMasterOverviewController {

    private Stage primaryStage;

    private Server server;

    private AvailableGamesChecker availableGamesChecker;

    private InGameController inGameController;

    private Timer timer;

    public DiceMasterOverviewController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initGameLayout() {
        try {
            this.primaryStage.setTitle("DiceMasterPrototype");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/LoginPane.fxml"));
            BorderPane rootLayout = loader.load();
            LoginController loginController = loader.getController();
            this.server = new FakeServer();
            loginController.setAppController(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void showGamesTable() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("view/GamesTablePane.fxml"));
            BorderPane rootLayout = loader.load();
            GamesTableController gamesTableController = loader.getController();
            gamesTableController.setDiceMasterOverviewController(this);

            this.availableGamesChecker = new AvailableGamesChecker(gamesTableController);
            this.timer = new Timer(true);
            this.timer.schedule(availableGamesChecker,0,1000);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InGameController initInGameController(){
        return this.inGameController;
    }

    public InGameController showGame() {
        try {
            this.timer.cancel();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/InGamePane.fxml"));
            BorderPane rootLayout = loader.load();
            this.inGameController = loader.getController();
            inGameController.setAppController(this);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.inGameController;
    }

    public Server getServer() {
        return server;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
