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
import javafx.scene.control.Alert;
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

    public static int id = 1;
    private static boolean modifying = false;
    private static InHouse tempInHouse;
    private static Outsourced tempOutsourced;

    public void updateLabel(String text) {
        partFormTitleLabel.setText(text);
    }

    public void modifyPart(Object part) {
        modifying = true;

        if(part.getClass().getSimpleName().equalsIgnoreCase("inhouse")) {
            inHouseRadioBtn.setSelected(true);
            outsourcedRadioBtn.setSelected(false);

            tempInHouse = (InHouse) part;
            partIDField.setText(String.valueOf(tempInHouse.getId()));
            partNameField.setText(tempInHouse.getName());
            partInvField.setText(String.valueOf(tempInHouse.getStock()));
            partPriceField.setText(String.valueOf(tempInHouse.getPrice()));
            partMaxField.setText(String.valueOf(tempInHouse.getMax()));
            partMinField.setText(String.valueOf(tempInHouse.getMin()));
            partChangingField.setText(String.valueOf(tempInHouse.getMachineId()));
        }
        else if(part.getClass().getSimpleName().equalsIgnoreCase("outsourced")) {
            inHouseRadioBtn.setSelected(false);
            outsourcedRadioBtn.setSelected(true);

            tempOutsourced = (Outsourced) part;
            partIDField.setText(String.valueOf(tempOutsourced.getId()));
            partNameField.setText(tempOutsourced.getName());
            partInvField.setText(String.valueOf(tempOutsourced.getStock()));
            partPriceField.setText(String.valueOf(tempOutsourced.getPrice()));
            partMaxField.setText(String.valueOf(tempOutsourced.getMax()));
            partMinField.setText(String.valueOf(tempOutsourced.getMin()));
            partChangingField.setText(tempOutsourced.getCompanyName());
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

    @FXML
    public void outsourcedChecked(ActionEvent e) {
        inHouseRadioBtn.setSelected(false); // unselects other radio button
        partChangingLabel.setText("Company Name");
    }

    @FXML
    public void savePart(ActionEvent e) throws IOException {
        String name = partNameField.getText();
        int stock = Integer.parseInt(partInvField.getText());
        double price = Double.parseDouble(partPriceField.getText());
        int max = Integer.parseInt(partMaxField.getText());
        int min = Integer.parseInt(partMinField.getText());
        
        // Exception control
        if(stock > max || stock < min || stock < 0) {
            Alert dataAlert = new Alert(Alert.AlertType.ERROR);
            dataAlert.setTitle("Error!");
            dataAlert.setHeaderText("Invalid Entry");
            dataAlert.setContentText("Inventory cannot be greater than max, less than min, or negative.");
            dataAlert.showAndWait();
        }
        else {
            if(inHouseRadioBtn.isSelected()) {
                int machineId = Integer.parseInt(partChangingField.getText());

                if(modifying) {
                    InHouse inHousePart = new InHouse(Integer.parseInt(partIDField.getText()), name, price, stock, min, max, machineId);
                    Inventory.updatePart(Inventory.getAllParts().indexOf(tempInHouse), inHousePart);
                    modifying = false;
                }
                else {
                    InHouse inHousePart = new InHouse(id++, name, price, stock, min, max, machineId);
                    Inventory.addPart(inHousePart);
                    modifying = false;
                } 
            }
            else if(outsourcedRadioBtn.isSelected()) {
                String companyName = partChangingField.getText();
                // TO DO: Fix line 133. Index out of bound error for modifying a part.
                if(modifying) {
                    Outsourced outsourcedPart = new Outsourced(Integer.parseInt(partIDField.getText()), name, price, stock, min, max, companyName);
                    Inventory.updatePart(Inventory.getAllParts().indexOf(tempOutsourced), outsourcedPart);
                    modifying = false;
                }
                else {
                    Outsourced outsourcedPart = new Outsourced(id++, name, price, stock, min, max, companyName);
                    Inventory.addPart(outsourcedPart);
                    modifying = false;
                }
            }
            
            Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
            Stage stage = (Stage) partSaveBtn.getScene().getWindow();

            stage.setScene(new Scene(main));
        }
    }
}
