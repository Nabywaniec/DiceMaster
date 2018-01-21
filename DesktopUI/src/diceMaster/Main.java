package diceMaster;

import agh.to2.dicemaster.client.api.Server;
import diceMaster.controller.DiceMasterOverviewController;
import diceMaster.mockaps.FakeServer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main extends Application {
    private Stage primaryStage;
    private DiceMasterOverviewController diceMasterOverviewController;

    @Override
    public void start(Stage primaryStage) {
        this.startRealServerConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void startRealServerConnection() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("connection.properties");
            prop.load(input);

            this.primaryStage = primaryStage;
            this.diceMasterOverviewController = new DiceMasterOverviewController(primaryStage,
                    Server.createDiceMasterServer(prop.getProperty("serverAddress"),
                            prop.getProperty("serverUsername"),
                            prop.getProperty("password"))
            );

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startFakeServerConnection() {
        this.primaryStage = primaryStage;
        this.diceMasterOverviewController = new DiceMasterOverviewController(primaryStage, new FakeServer());
    }
}