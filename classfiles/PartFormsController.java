package classfiles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PartFormsController {
    @FXML
    private Button partSaveBtn, partCancelBtn;

    @FXML
    private TextField partIDField, partNameField, partInvField, partPriceField, 
                      partMaxField, partChangingField, partMinField;
    
    @FXML
    private RadioButton inHouseRadioBtn, outsourcedRadioBtn;

    @FXML
    private Label partChangingLabel, partFormTitleLabel;

    public void updateLabel(String text) {
        partFormTitleLabel.setText(text);
    }

    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/UI/mainform.fxml"));
        Stage stage = (Stage) partCancelBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }

    @FXML
    public void inHouseChecked(ActionEvent e) {
        outsourcedRadioBtn.setSelected(false);
        partChangingLabel.setText("Machine ID");
    }

    @FXML
    public void outsourcedChecked(ActionEvent e) {
        inHouseRadioBtn.setSelected(false);
        partChangingLabel.setText("Company Name");
    }
}
