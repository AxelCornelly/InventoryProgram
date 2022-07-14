package controller;

import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    AtomicInteger autoID = new AtomicInteger(1);
    private boolean inHouse;
    private boolean outsourced;

    public void updateLabel(String text) {
        partFormTitleLabel.setText(text);
    }

    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Stage stage = (Stage) partCancelBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }

    @FXML
    public void inHouseChecked(ActionEvent e) {
        inHouse = inHouseRadioBtn.isSelected();
        
        outsourcedRadioBtn.setSelected(false); // unselects other radio button
        outsourced = outsourcedRadioBtn.isSelected();

        partChangingLabel.setText("Machine ID");
    }

    @FXML
    public void outsourcedChecked(ActionEvent e) {
        outsourced = outsourcedRadioBtn.isSelected();
        inHouseRadioBtn.setSelected(false); // unselects other radio button
        inHouse = inHouseRadioBtn.isSelected();
        partChangingLabel.setText("Company Name");
    }

    @FXML
    public void savePart(ActionEvent e) throws IOException {
        int id = autoID.getAndIncrement();
        String name = partNameField.getText();
        int stock = Integer.parseInt(partInvField.getText());
        double price = Double.parseDouble(partPriceField.getText());
        int max = Integer.parseInt(partMaxField.getText());
        int min = Integer.parseInt(partMinField.getText());

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/view/mainform.fxml"));
        FormController mainController = mainLoader.getController();
        
        if(inHouse) {
            int machineId = Integer.parseInt(partChangingField.getText());
            InHouse inHousePart = new InHouse(id, name, price, stock, min, max, machineId);
            mainController.addToPartTable(inHousePart);
        }
        else if(outsourced) {
            String companyName = partChangingField.getText();
            Outsourced outsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
            mainController.addToPartTable(outsourcedPart);
        }

        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Stage stage = (Stage) partSaveBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }
}
