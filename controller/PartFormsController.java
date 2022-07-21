package controller;

import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;

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
    private static boolean modifying, wasInHouse, wasOutsourced = false;
    private static InHouse tempInHouse;
    private static Outsourced tempOutsourced;

    public void updateLabel(String text) {
        partFormTitleLabel.setText(text);
    }

    public void parsePartData(Object part) {
        modifying = true;

        // Filling in entry fields for corresponding Part types
        if(part.getClass().getSimpleName().equalsIgnoreCase("inhouse")) {
            inHouseRadioBtn.setSelected(true);
            outsourcedRadioBtn.setSelected(false);
            wasInHouse = true;
            wasOutsourced = false;

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
            wasInHouse = false;
            wasOutsourced = true;

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
        if(modifying) {
            modifying = false;
        }

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
        boolean stockValid = false;
        boolean priceValid = false;
        boolean maxValid = false;
        boolean minValid = false;
        String name = partNameField.getText();
        int stock = 0;
        double price = 0.00;
        int max = 0;
        int min = 0;

        try {
            stock = Integer.parseInt(partInvField.getText());
            try {
                price = Double.parseDouble(partPriceField.getText());
                try {
                    max = Integer.parseInt(partMaxField.getText());
                    try {
                        min = Integer.parseInt(partMinField.getText());
                        minValid = true;
                    }
                    catch (NumberFormatException err) {
                        minValid = false;

                        Alert minAlert = new Alert(Alert.AlertType.ERROR);
                        minAlert.setTitle("Error!");
                        minAlert.setHeaderText("Invalid Entry");
                        minAlert.setContentText("Min value must be a positive integer.");
                        minAlert.showAndWait();
                    }

                    maxValid = true;
                }
                catch (NumberFormatException err) {
                    maxValid = false;

                    Alert maxAlert = new Alert(Alert.AlertType.ERROR);
                    maxAlert.setTitle("Error!");
                    maxAlert.setHeaderText("Invalid Entry");
                    maxAlert.setContentText("Max value must be a positive integer.");
                    maxAlert.showAndWait();
                }

                priceValid = true;
            }
            catch (NumberFormatException err) {
                priceValid = false;

                Alert priceAlert = new Alert(Alert.AlertType.ERROR);
                priceAlert.setTitle("Error!");
                priceAlert.setHeaderText("Invalid Entry");
                priceAlert.setContentText("Price must be a positive decimal not exceeding the hundreths place.");
                priceAlert.showAndWait();
            }

            stockValid = true;
        }
        catch (NumberFormatException err) {
            stockValid = false;
            Alert stockAlert = new Alert(Alert.AlertType.ERROR);
            stockAlert.setTitle("Error!");
            stockAlert.setHeaderText("Invalid Entry");
            stockAlert.setContentText("Inventory value must be a positive integer.");
            stockAlert.showAndWait();
        }

        if(stockValid && priceValid && maxValid && minValid) {
            if(min > max || min < 0) {
                Alert dataAlert = new Alert(Alert.AlertType.ERROR);
                dataAlert.setTitle("Error!");
                dataAlert.setHeaderText("Invalid Entry");
                dataAlert.setContentText("Part min cannot be greater than max and must be positive.");
                dataAlert.showAndWait();
            }
            else if(max < 0) {
                Alert dataAlert = new Alert(Alert.AlertType.ERROR);
                dataAlert.setTitle("Error!");
                dataAlert.setHeaderText("Invalid Entry");
                dataAlert.setContentText("Max must cannot be negative");
                dataAlert.showAndWait();
            }
            else if(stock > max || stock < min || stock < 0) {
                Alert dataAlert = new Alert(Alert.AlertType.ERROR);
                dataAlert.setTitle("Error!");
                dataAlert.setHeaderText("Invalid Entry");
                dataAlert.setContentText("Inventory must be within range of max and min and must be positive.");
                dataAlert.showAndWait();
            }
            else if(price < 0) {
                Alert dataAlert = new Alert(Alert.AlertType.ERROR);
                dataAlert.setTitle("Error!");
                dataAlert.setHeaderText("Invalid Entry");
                dataAlert.setContentText("Price cannot be negative.");
                dataAlert.showAndWait();
            }
            else {
                if(inHouseRadioBtn.isSelected()) {
                    try {
                        int machineId = Integer.parseInt(partChangingField.getText());
                        if(modifying) {
                            InHouse inHousePart = new InHouse(Integer.parseInt(partIDField.getText()), name, price, stock, min, max, machineId);
        
                            if(wasOutsourced) {
                                Inventory.updatePart(Inventory.getAllParts().indexOf(tempOutsourced), inHousePart);
                                modifying = false;
                            }
                            else {
                                Inventory.updatePart(Inventory.getAllParts().indexOf(tempInHouse), inHousePart);
                                modifying = false;
                            }
                        }
                        else {
                            InHouse inHousePart = new InHouse(id++, name, price, stock, min, max, machineId);
                            Inventory.addPart(inHousePart);
                            modifying = false;
                        } 
    
                        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
                        Stage stage = (Stage) partSaveBtn.getScene().getWindow();
    
                        stage.setScene(new Scene(main));
                    }
                    catch (NumberFormatException err) {
                        Alert specialAlert = new Alert(Alert.AlertType.ERROR);
                        specialAlert.setTitle("Error!");
                        specialAlert.setHeaderText("Invalid Entry");
                        specialAlert.setContentText("Machine ID must be a positive integer.");
                        specialAlert.showAndWait();
                    }
                }
                else if(outsourcedRadioBtn.isSelected()) {
                    String companyName = partChangingField.getText();
                    if(modifying) {
                        Outsourced outsourcedPart = new Outsourced(Integer.parseInt(partIDField.getText()), name, price, stock, min, max, companyName);
                        
                        if(wasInHouse) {
                            Inventory.updatePart(Inventory.getAllParts().indexOf(tempInHouse), outsourcedPart);
                            modifying = false;
                        }
                        else {
                            Inventory.updatePart(Inventory.getAllParts().indexOf(tempOutsourced), outsourcedPart);
                            modifying = false;
                        }
                    }
                    else {
                        Outsourced outsourcedPart = new Outsourced(id++, name, price, stock, min, max, companyName);
                        Inventory.addPart(outsourcedPart);
                        modifying = false;
                    }
    
                    Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
                    Stage stage = (Stage) partSaveBtn.getScene().getWindow();
    
                    stage.setScene(new Scene(main));
                }
            }
        }
    }
}
