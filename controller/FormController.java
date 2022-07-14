package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ObservableList<Part> partsList = Inventory.getAllParts();
    private ObservableList<Product> productsList = Inventory.getAllProducts();

    @FXML
    public void closeApp(ActionEvent e) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void switchSceneTo(String form, Button btn) throws IOException{
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
                partFormsController.updateLabel("Modify Part");
                stage.setScene(new Scene(partPane));
                break;
            case "modifyproductform":
                productFormsController.updateLabel("Modify Product");
                stage.setScene(new Scene(productPane));
                break;
        }
    }

    @FXML
    public void handleAddPartBtn(ActionEvent e) throws IOException{
        switchSceneTo("addpartform", addPartBtn);
    }

    @FXML
    public void handleAddProductBtn(ActionEvent e) throws IOException{
        switchSceneTo("addproductform", addProductBtn);
    }

    @FXML
    public void handleModifyPartBtn(ActionEvent e) throws IOException{
        switchSceneTo("modifypartform", modifyPartBtn);
    }

    @FXML
    public void handleModifyProductBtn(ActionEvent e) throws IOException{
        switchSceneTo("modifyproductform", modifyProductBtn);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        productIDColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        
        partsTableView.setItems(partsList);
        productTableView.setItems(productsList);
        
    }
    /*
     * TO DO: 
     * Fix adding part function. So far, table is not being populated and
     * adding part does nothing. The methods below dont behave correctly.
     * Might want to check the savePart method in PartformsController Class
     */
    public void addToPartTable(Part part) {
        partsTableView.getItems().add(part);
        System.out.println(partsTableView.getItems());
    }

    public void addToProductTable(Product product) {
        productTableView.getItems().add(product);
        System.out.println(productTableView.getItems());
    }
}
