package controller;

import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

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

    private int autoID;
    //AtomicInteger autoID = new AtomicInteger(1);

    public void updateLabel(String text) {
        partFormTitleLabel.setText(text);
    }

    public void parsePartData(Part part) {
        partIDField.setText(String.valueOf(part.getId()));
        partNameField.setText(part.getName());
        partInvField.setText(String.valueOf(part.getStock()));
        partPriceField.setText(String.valueOf(part.getPrice()));
        partMaxField.setText(String.valueOf(part.getMax()));
        partMinField.setText(String.valueOf(part.getMin()));
        if(inHouseRadioBtn.isSelected()) {
            // TODO: How to handle extended class as parameter
        }
        else if(outsourcedRadioBtn.isSelected()) {
            // TODO: How to handle extended class as parameter
        }
    }

    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Stage stage = (Stage) partCancelBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }

    @FXML
    public void inHouseChecked(ActionEvent e) {
        outsourcedRadioBtn.setSelected(false); // unselects other radio button
        partChangingLabel.setText("Machine ID");
    }

    public void setPartID(int newID) {
        autoID = newID;
    }

    @FXML
    public void outsourcedChecked(ActionEvent e) {
        inHouseRadioBtn.setSelected(false); // unselects other radio button
        partChangingLabel.setText("Company Name");
    }

    @FXML
    public void savePart(ActionEvent e) throws IOException {
        //int id = autoID.getAndIncrement();
        int id = autoID;
        String name = partNameField.getText();
        int stock = Integer.parseInt(partInvField.getText());
        double price = Double.parseDouble(partPriceField.getText());
        int max = Integer.parseInt(partMaxField.getText());
        int min = Integer.parseInt(partMinField.getText());
        
        if(inHouseRadioBtn.isSelected()) {
            int machineId = Integer.parseInt(partChangingField.getText());
            InHouse inHousePart = new InHouse(id, name, price, stock, min, max, machineId);
            Inventory.addPart(inHousePart);
        }
        else if(outsourcedRadioBtn.isSelected()) {
            String companyName = partChangingField.getText();
            Outsourced outsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.addPart(outsourcedPart);
        }

        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Stage stage = (Stage) partSaveBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }
}
