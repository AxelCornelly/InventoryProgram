package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage appStage) throws IOException{
        Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Scene scene = new Scene(mainPane);
        appStage.setScene(scene);
        appStage.setTitle("Inventory Management System");
        appStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
