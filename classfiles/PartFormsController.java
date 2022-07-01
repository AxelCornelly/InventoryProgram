package classfiles;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PartFormsController {
    @FXML
    private Button partSaveBtn, partCancelBtn;

    @FXML
    public TextField partIDField, partNameField, partInvField, partPriceField, 
                      partMaxField, partChangingField, partMinField;
    
    @FXML
    public RadioButton inHouseRadioBtn, outsourcedRadioBtn;

    @FXML
    public Label partFormTitleLabel;
    
    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        Parent main = FXMLLoader.load(getClass().getResource("/UI/mainform.fxml"));
        Stage stage = 
    }
}
