package diceMaster;

import diceMaster.controller.DiceMasterOverviewController;
import diceMaster.mockaps.FakeServer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private DiceMasterOverviewController diceMasterOverviewController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.diceMasterOverviewController = new DiceMasterOverviewController(primaryStage, new FakeServer());
    }

    public static void main(String[] args) {
        launch(args);
    }
}