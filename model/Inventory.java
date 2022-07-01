package model;
/**
 * Inventory class.
 * Main application file
 */

/**
 * @author Axel Cornelly
 */

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class Inventory extends Application{
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public static void addPart(Part part) {

    }

    public static void addProduct(Product product) {

    }

    public static Part lookupPart(int partID) {

    }

    public static ObservableList<Part> lookupPart(String partName) {

    }

    public static Product lookupProduct(int productID) {

    }

    public static ObservableList<Product> lookupProduct(String productName) {

    }

    public static void updatePart(int index, Part selectedPart) {

    }

    public static void updateProduct(int index, Product newProduct) {

    }

    public static boolean deletePart(Part selectedPart) {

    }

    public static boolean deleteProduct(Product selectedProduct) {

    }

    public static ObservableList<Part> getAllParts() {

    }

    public static ObservableList<Product> getAllProducts() {

    }

    @Override
    public void start(Stage appStage) throws IOException{
        Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/UI/mainform.fxml"));
        Scene scene = new Scene(mainPane);
        appStage.setScene(scene);
        appStage.setTitle("Inventory Management System");
        appStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
