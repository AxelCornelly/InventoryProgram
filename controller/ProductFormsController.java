package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Product;

public class ProductFormsController {
    @FXML
    private Label productTitleLabel;

    @FXML
    private Button productAddPartBtn, removeBtn, productSaveBtn, productCancelBtn;

    @FXML
    private TextField productIDField, productNameField, productInvField, productPriceField,
                      productMaxField, productMinField, productPartSearchbar;
    
    @FXML
    private TableView productTopTableView, productBottomTableView;

    public void updateLabel(String text) {
        productTitleLabel.setText(text);
    }

    public void parseProductData(Product product) {
        productIDField.setText(String.valueOf(product.getId()));
        productNameField.setText(product.getName());
        productInvField.setText(String.valueOf(product.getStock()));
        productPriceField.setText(String.valueOf(product.getPrice()));
        productMaxField.setText(String.valueOf(product.getMax()));
        productMinField.setText(String.valueOf(product.getMin()));
    }

    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Stage stage = (Stage) productCancelBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }
}
