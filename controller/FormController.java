package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Action;

import com.apple.dnssd.QueryListener;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

public class FormController implements Initializable{
    @FXML
    private Button addPartBtn, modifyPartBtn, deletePartBtn, addProductBtn, modifyProductBtn, deleteProductBtn, exitBtn;

    @FXML
    private TextField partSearchbar, productSearchbar;

    @FXML
    private TableView<Part> partsTableView;
    
    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Part, Integer> partIDColumn, partInvColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    
    @FXML
    private TableColumn<Product, Integer> productIDColumn, productInvColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    SortedList<Part> sortedParts;
    SortedList<Product> sortedProducts;
    AtomicInteger partAutoID = new AtomicInteger(1);
    AtomicInteger productAutoID = new AtomicInteger(1);
    int partID;
    int productID;

    @FXML
    public void closeApp(ActionEvent e) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void switchSceneTo(String form, Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        
        FXMLLoader partLoader = new FXMLLoader(getClass().getResource("/view/partform.fxml"));
        Pane partPane = (Pane) partLoader.load();
        PartFormsController partFormsController = partLoader.getController();
        
        FXMLLoader productLoader = new FXMLLoader(getClass().getResource("/view/productform.fxml"));
        Pane productPane = (Pane) productLoader.load();
        ProductFormsController productFormsController = productLoader.getController();

        switch(form) {
            case "addpartform":
                partFormsController.updateLabel("Add Part");
                partID = partAutoID.getAndIncrement();
                partFormsController.setPartID(partID);
                stage.setScene(new Scene(partPane));
                break;
            case "addproductform":
                productFormsController.updateLabel("Add Product");
                stage.setScene(new Scene(productPane));
                break;
            case "modifypartform":
                int selected = partsTableView.getSelectionModel().getSelectedIndex();
                if(selected >= 0) {
                    partFormsController.parsePartData(partsTableView.getSelectionModel().getSelectedItem());
                    partFormsController.updateLabel("Modify Part");
                    stage.setScene(new Scene(partPane));
                }
                else {
                    Alert modifyAlert = new Alert(Alert.AlertType.ERROR);
                    modifyAlert.setTitle("Modify Error");
                    modifyAlert.setHeaderText("Error Modifying Part");
                    modifyAlert.setContentText("No valid part selected.");

                    modifyAlert.showAndWait();
                }
                break;
            case "modifyproductform":
                int selectedProductRow = productTableView.getSelectionModel().getSelectedIndex();
                if(selectedProductRow >= 0) {
                    productFormsController.parseProductData(productTableView.getSelectionModel().getSelectedItem());
                    productFormsController.updateLabel("Modify Product");
                    stage.setScene(new Scene(productPane));
                }
                break;
        }
    }

    @FXML
    public void handleAddPartBtn(ActionEvent e) throws IOException {
        switchSceneTo("addpartform", addPartBtn);
    }

    @FXML
    public void handleAddProductBtn(ActionEvent e) throws IOException {
        switchSceneTo("addproductform", addProductBtn);
    }

    @FXML
    public void handleDeletePartBtn(ActionEvent e) throws IOException {
        int selected = partsTableView.getSelectionModel().getSelectedIndex();
        if(selected >= 0) {
            int source = sortedParts.getSourceIndexFor(Inventory.getAllParts(), selected);
            if(source >= 0) {
                Inventory.getAllParts().remove(source);
                partsTableView.getSelectionModel().clearSelection();
            }
        }
        else {
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("Delete Error");
            deleteAlert.setHeaderText("Error Deleting Part");
            deleteAlert.setContentText("No valid part selected.");

            deleteAlert.showAndWait();
        }
    }

    @FXML
    public void handleDeleteProductBtn(ActionEvent e) throws IOException {
        int selected = productTableView.getSelectionModel().getSelectedIndex();
        if(selected >= 0) {
            int source = sortedProducts.getSourceIndexFor(Inventory.getAllProducts(), selected);
            if(source >= 0) {
                Inventory.getAllProducts().remove(source);
                productTableView.getSelectionModel().clearSelection();
            }
        }
        else {
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("Delete Error");
            deleteAlert.setHeaderText("Error Deleting Part");
            deleteAlert.setContentText("No valid part selected.");

            deleteAlert.showAndWait();
        }
    }

    @FXML
    public void handleModifyPartBtn(ActionEvent e) throws IOException {
        switchSceneTo("modifypartform", modifyPartBtn);
    }

    @FXML
    public void handleModifyProductBtn(ActionEvent e) throws IOException {
        switchSceneTo("modifyproductform", modifyProductBtn);
    }

    private static boolean firstTime = true;
    private void addTestData() {
        if(!firstTime) {
            return;
        }

        firstTime = false;
        InHouse wheel = new InHouse(1, "Wheel", 2.99, 5, 1, 10, 1);
        Outsourced handlebar = new Outsourced(2, "Handlebar", 7.50, 3, 1, 15, "Handle Co.");
        InHouse brake = new InHouse(3, "Brake", 10, 5, 1, 10, 4);
        Inventory.addPart(wheel);
        Inventory.addPart(handlebar);
        Inventory.addPart(brake);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add test values
        addTestData();
        
        // Initialize table columns to values
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Search bar functionality (Part)
        // TODO: Add ability to show warning dialog on enter key press as well
        // FilteredList<Part> filteredParts = new FilteredList<>(Inventory.getAllParts(), p -> true);
        // partSearchbar.textProperty().addListener((observable, oldVal, newVal) -> {
        //     filteredParts.setPredicate(part -> {
        //         if(newVal == null || newVal.isEmpty()) {
        //             return true;
        //         }

        //         String filter = newVal.toLowerCase();
        //         if(part.getName().toLowerCase().contains(filter)) { 
        //             return true; 
        //         }
        //         return false;
        //     });
        // });
        
        // sortedParts = new SortedList<>(filteredParts);
        // sortedParts.comparatorProperty().bind(partsTableView.comparatorProperty());
        // partsTableView.setItems(sortedParts);

        // // Searchbar Functionality (Product)
        // FilteredList<Product> filteredProducts = new FilteredList<>(Inventory.getAllProducts(), p -> true);
        // productSearchbar.textProperty().addListener((observable, oldVal, newVal) -> {
        //     filteredProducts.setPredicate(product -> {
        //         if(newVal == null || newVal.isEmpty()) {
        //             return true;
        //         }

        //         String filter = newVal.toLowerCase();
        //         if(product.getName().toLowerCase().contains(filter)) { 
        //             return true; 
        //         }
        //         return false;
        //     });
        // });
        
        // sortedProducts = new SortedList<>(filteredProducts);
        // sortedProducts.comparatorProperty().bind(productTableView.comparatorProperty());
        // productTableView.setItems(Inventory.getAllProducts());
        partSearchbar.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                String query = partSearchbar.getText();

                ObservableList<Part> parts = searchPartByName(query);

                if(parts.isEmpty() || parts == null) {
                    Alert partSearchAlert = new Alert(Alert.AlertType.WARNING);
                    partSearchAlert.setTitle("Search Error");
                    partSearchAlert.setContentText("Part not found!");
                }
                partsTableView.setItems(parts);
            }
        });

        partsTableView.setItems(Inventory.getAllParts());
        productTableView.setItems(Inventory.getAllProducts());
    }

    private ObservableList<Part> searchPartByName(String q) {
        ObservableList<Part> queriedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part p : allParts) {
            if(p.getName().toLowerCase().contains(q.toLowerCase())) {
                queriedParts.add(p);
            }
        }

        return queriedParts;
    }
}
