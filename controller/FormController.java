package controller;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Inventory;
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
                stage.setScene(new Scene(partPane));
                break;
            case "addproductform":
                productFormsController.updateLabel("Add Product");
                stage.setScene(new Scene(productPane));
                break;
            case "modifypartform":
                int selected = partsTableView.getSelectionModel().getSelectedIndex();
                if(selected >= 0) {
                    Object selectedPart = partsTableView.getSelectionModel().getSelectedItem();
                    partFormsController.parsePartData(selectedPart);
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
                else {
                    Alert modifyAlert = new Alert(Alert.AlertType.ERROR);
                    modifyAlert.setTitle("Modify Error");
                    modifyAlert.setHeaderText("Error Modifying Product");
                    modifyAlert.setContentText("No valid product selected.");

                    modifyAlert.showAndWait();
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
            Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDeleteAlert.setTitle("Confirm Delete");
            confirmDeleteAlert.setHeaderText("You're about to delete a part!");
            confirmDeleteAlert.setContentText("Are you sure you want to delete this part?");

            ButtonType yesButton = new ButtonType("Yes", ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            confirmDeleteAlert.getButtonTypes().setAll(yesButton, cancelButton);
            Optional<ButtonType> result = confirmDeleteAlert.showAndWait();
            
            if(result.get() == yesButton) {
                Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());
                partsTableView.getSelectionModel().clearSelection();
            }
            else {
                // close dialog
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
            Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
            
            if(selectedProduct.getAllAssociatedParts().size() == 0){
                Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmDeleteAlert.setTitle("Confirm Delete");
                confirmDeleteAlert.setHeaderText("You're about to delete a product!");
                confirmDeleteAlert.setContentText("Are you sure you want to delete this product?");

                ButtonType yesButton = new ButtonType("Yes", ButtonData.OK_DONE);
                ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                confirmDeleteAlert.getButtonTypes().setAll(yesButton, cancelButton);
                Optional<ButtonType> result = confirmDeleteAlert.showAndWait();
                
                if(result.get() == yesButton) {
                    Inventory.deleteProduct(productTableView.getSelectionModel().getSelectedItem());
                    partsTableView.getSelectionModel().clearSelection();
                }
                else {
                    // close dialog
                }
            }
            else {
                Alert confirmDeleteAlert = new Alert(Alert.AlertType.ERROR);
                confirmDeleteAlert.setTitle("Error Deleting");
                confirmDeleteAlert.setHeaderText("Unable to Delete Product");
                confirmDeleteAlert.setContentText("This product has parts associated with it.");
                confirmDeleteAlert.showAndWait();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns to values
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Part Searchbar functionality
        partSearchbar.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                String query = partSearchbar.getText();

                // Search by name
                ObservableList<Part> parts = Inventory.lookupPart(query);
                
                // If first call results in empty list, assume an ID is passed.
                try {
                    if(parts.isEmpty()) {
                        int id = Integer.parseInt(query);
                        Part p = Inventory.lookupPart(id);
                        if(p != null) {
                            parts.add(p);
                        }
                    }
                }
                catch (NumberFormatException err) {
                    // Ignore
                }
                
                partsTableView.setItems(parts);

                // If after both searches used and list still empty, display error.
                if(parts.isEmpty()) {
                    Alert partSearchAlert = new Alert(Alert.AlertType.WARNING);
                    partSearchAlert.setTitle("Search Error");
                    partSearchAlert.setHeaderText("Part not found!");
                    partSearchAlert.setContentText("Check ID or spelling.");
                    partSearchAlert.showAndWait();
                }
            }
        });

        // Product Searchbar functionality
        productSearchbar.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                String query = productSearchbar.getText();

                ObservableList<Product> products = Inventory.lookupProduct(query);
                
                // If first call results in empty list, assume an ID is passed.
                try {
                    if(products.isEmpty()) {
                        int id = Integer.parseInt(query);
                        Product p = Inventory.lookupProduct(id);
                        if(p != null) {
                            products.add(p);
                        }
                    }
                }
                catch (NumberFormatException err) {
                    // Ignore
                }
                
                productTableView.setItems(products);

                // If after both searches used and list still empty, display error.
                if(products.isEmpty()) {
                    Alert productSearchAlert = new Alert(Alert.AlertType.WARNING);
                    productSearchAlert.setTitle("Search Error");
                    productSearchAlert.setHeaderText("Product not found!");
                    productSearchAlert.setContentText("Check ID or spelling.");
                    productSearchAlert.showAndWait();
                }
            }
        });

        partsTableView.setItems(Inventory.getAllParts());
        productTableView.setItems(Inventory.getAllProducts());
    }
}
