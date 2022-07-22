package model;
/**
 * Main Class Main.java
 * This is the main class for the Inventory Program.
 */

/**
 * @author Axel Cornelly
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.stage.Stage;

public class Main extends Application {
    
    /** 
     * @param appStage
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This start method takes in a Stage object and throws an IOException when the parameter isn't met.
     * This method is overriden from the parent class, Application and initializes the program's window.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * When first using this method, I had issues with getting the Pane object to load the correct fxml file.
     * Starting out, my program was not a Java package and was simply a collection of folders. I had to restructure
     * my files in order to fix this.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think this method needs its functionality to be extended.
     */
    @Override
    public void start(Stage appStage) throws IOException{
        Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Scene scene = new Scene(mainPane);
        appStage.setScene(scene);
        appStage.setTitle("Inventory Management System");
        appStage.show();
    }

    
    /** 
     * @param args
     * 
     * The location of the Javadoc files are in InventoryProgram/src
     */
    public static void main(String[] args) {
        launch(args);
    }
}
