package controller;
/**
 * PartFormsController Class PartFormsController.java
 * This class is the controller file for the Part forms
 * fxml files.
 */

/**
 * @author Axel Cornelly
 */

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

    
    /** 
     * @param text Text to update Label to
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method changes the text of the Title Label in the
     * Part Form. It is called by from the FormController class
     * which utilizes an instance of this class.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Initially was updating one of the labels that were describing
     * an entry field.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think there could be any improvements to functionality here.
     * It is just a simple method to change the text on a widget.
     * 
     */
    public void updateLabel(String text) {
        partFormTitleLabel.setText(text);
    }

    
    /** 
     * @param part Part to read data from
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is called in the FormController class to send
     * a selected part over here to be parsed. It is used when a 
     * part is being modified. It retrieves information about
     * the Part object and updates the text fields in the form
     * to have those values.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * This method was particularly interesting because I had an
     * issue passing along a Part while making sure data was parsed
     * correctly for it being either an InHouse or Outsourced object.
     * The workaround I devised was to just pass in the Part as an 
     * Object class, check its actual class with .getClass.getSimpleName(),
     * and then cast it to whatever class it is.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * An improvement I would make for this method is to take in a 
     * parameter of Part instead of Object because I am realizing now
     * that an instance of Part can be cast to its subclasses without
     * a problem.
     * 
     */
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

    
    /** 
     * @param e Event to listen to
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an action listener that is binded to a button.
     * This method, when called, will close out of the Part Form.
     * It makes sure that any entered data is not saved.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * This method had an issue of displaying incorrect information of
     * a part. This was because the static variable, modifying, was not
     * being reset to false after the cancel button would trigger, resulting
     * in the data carrying over to the next selected part.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would add a warning Dialog here to pop up to let the user know that no data 
     * will be saved.
     * 
     */
    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        if(modifying) {
            modifying = false;
        }

        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Stage stage = (Stage) partCancelBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }

    
    /** 
     * @param e Event to listen to
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is an event listener that is binded to a button.
     * This method checks if the inHouseRadioBtn is selected,
     * and if it is, will de-select the other radio button, and
     * change the last entry field label to say "Machine ID" to
     * correlate to the corresponding InHouse data member.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * At first, this was just to change the label defining the text
     * field for Machine ID but I soon noticed that both radio buttons
     * could be selected. So I had to make sure only one was selected at
     * a time.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would add a quick Dialog box here pop up and inform the user
     * that they are now Adding/Modifying an InHouse Part.
     * 
     */
    @FXML
    public void inHouseChecked(ActionEvent e) {
        outsourcedRadioBtn.setSelected(false); // unselects other radio button
        partChangingLabel.setText("Machine ID");
    }

    
    /** 
     * @param e Event to listen to
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an event listener that is binded to a button.
     * This method acts the same as inHouseChecked() and changes
     * the text on the partChangingLabel to "Company Name"
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * This method also had an issue with not deselecting the other
     * radio button so I made sure to implement the deselect here as well.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would also add a Dialog box here to let the user know
     * that they are now Adding/Modifying an Outsourced Part.
     * 
     */
    @FXML
    public void outsourcedChecked(ActionEvent e) {
        inHouseRadioBtn.setSelected(false); // unselects other radio button
        partChangingLabel.setText("Company Name");
    }

    
    /** 
     * @param e Event to listen to
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an event listener that is binded to a button.
     * This method retrieves all the information entered into the 
     * text fields, validates the inputs, creates or modifies an 
     * instance of either an InHouse or Outsourced Part, and adds
     * it to the allParts ObservableList in the Inventory class.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There were lots of issues in this method as it is one of the
     * more in-depth methods in this program. I had lots of issues 
     * verifying user input and making sure that the data does not
     * save after an error was found. There were also issues on
     * checking whether the user is modifying or creating a new Part,
     * as well as how to parse data dependant on if the Part was of
     * the InHouse or Outsourced class.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * One way I would improve this method is by creating one singular
     * Dialog object and changing its contents based on the error found.
     * It would reduce the amount of lines of code and remove redundancy.
     * I would also add another Dialog object to show the user the
     * information they typed and ask for confirmation before saving
     * the part.
     * 
     */
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

        // Validity Check
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

        // Once all Validity checks pass, part can be potentially saved
        if(stockValid && priceValid && maxValid && minValid) {
            // Data checks
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
                // Determine if Part is of the InHouse or Outsourced class
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
