package classfiles;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PartFormsController {
    @FXML
    private Button addPartSaveBtn, addPartCancelBtn, modifyPartSaveBtn, modifyPartCancelBtn;

    @FXML
    public TextField modifyPartIDField, modifyPartNameField, modifyPartInvField, modifyPartPriceField, 
                      modifyPartMaxField, modifyPartChangingField, modifyPartMinField, addPartIDField, 
                      addPartNameField, addPartInvField, addPartPriceField, addPartMaxField, addPartChangingField, addPartMinField;
    
    @FXML
    public RadioButton addPartInHouseRadioBtn, addPartOutsourcedRadioBtn, modifyPartInHouseRadioBtn, modifyPartOutsourcedRadioBtn;

    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        Parent main = FXMLLoader.load(getClass().getResource("/UI/mainform.fxml"));
        
    }
}
