package classfiles;

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

    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/UI/mainform.fxml"));
        Stage stage = (Stage) productCancelBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }
}
