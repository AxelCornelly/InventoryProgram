import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Inventory class.
 * Main application file
 */

/**
 * @author Axel Cornelly
 */

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
    public void start(Stage appStage) {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);
        Label formName = new Label("Inventory Management System");
        Insets gridPadding = new Insets(10, 10, 20, 10);

        Pane partsPane = new Pane();
        partsPane.setStyle("-fx-background-fill: black, white;");
        partsPane.setStyle("-fx-background-insets: 0, 3;");

        Pane productsPane = new Pane();
        productsPane.setStyle("-fx-background-fill: black, white;");
        productsPane.setStyle("-fx-background-insets: 0, 3;");
        
        TableView<Part> partTable = new TableView<Part>();
        partTable.relocate(0, 15);
        TableView<Product> productTable = new TableView<Product>();
        productTable.relocate(0, 15);

        Label partPaneLabel = new Label("Parts");
        Label productPaneLabel = new Label("Products");

        gridPane.add(formName, 0, 0);
        gridPane.add(partsPane, 0, 1);
        gridPane.add(productsPane, 1, 1);
        partsPane.getChildren().add(partPaneLabel);
        partsPane.getChildren().add(partTable);
        productsPane.getChildren().add(productPaneLabel);
        productsPane.getChildren().add(productTable);
        gridPane.setPadding(gridPadding);

        appStage.setScene(scene);
        appStage.setTitle("Inventory Management System");
        appStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
