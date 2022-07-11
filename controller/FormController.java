package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn, partInvColumn, partPriceColumn;
    
    @FXML
    private TableColumn<Part, Integer> productIDColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn, productInvColumn, productPriceColumn;

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
        ObservableList<Part> parts = Inventory.getAllParts();
        System.out.println(parts);
        ObservableList<Product> products = Inventory.getAllProducts();
        System.out.println(products);

        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("Part ID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("Part Name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("Inventory Level"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price/Cost per Unit"));

        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("Product ID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("Product Name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("Inventory Level"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price/Cost per Unit"));
        
        partsTableView.setItems(parts);
        productTableView.setItems(products);
        
    }

}
