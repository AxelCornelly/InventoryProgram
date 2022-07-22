package controller;
/**
 * ProductFormsController Class ProductFormsController.java
 * This class serves as the controller for the add and modify
 * Product forms.
 */

 /**
 * @author Axel Cornelly
 */

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

public class ProductFormsController implements Initializable{
    @FXML
    private Label productTitleLabel;

    @FXML
    private Button productAddPartBtn, removeBtn, productSaveBtn, productCancelBtn;

    @FXML
    private TextField productIDField, productNameField, productInvField, productPriceField,
                      productMaxField, productMinField, productPartSearchbar;
    
    @FXML
    private TableView<Part> productTopTableView, productBottomTableView;

    @FXML
    private TableColumn<Part, Integer> topIDCol, topInvCol, botIDCol, botInvCol;

    @FXML
    private TableColumn<Part, String> topNameCol, botNameCol;

    @FXML
    private TableColumn<Part, Double> topPriceCol, botPriceCol;

    public static int id = 1;
    private static boolean modifying = false;
    private Product currProduct;
    private static ObservableList<Part> assocParts = FXCollections.observableArrayList();

    
    /** 
     * @param text Text to update Label to
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a String parameter and updated the 
     * text of the productTitleLabel
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Was updating the wrong label at one point.
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
        productTitleLabel.setText(text);
    }

    
    /** 
     * @param product Product to read data from
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a Product object and reads its information.
     * It takes the information and fills the corresponding text fields.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Had issues populating the bottom table view of the modify product
     * form because of an uninitialized ObservableList.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * No improvements to this method. It is a simple read and write
     * method.
     * 
     */
    public void parseProductData(Product product) {
        modifying = true;
        currProduct = product;

        productIDField.setText(String.valueOf(product.getId()));
        productNameField.setText(product.getName());
        productInvField.setText(String.valueOf(product.getStock()));
        productPriceField.setText(String.valueOf(product.getPrice()));
        productMaxField.setText(String.valueOf(product.getMax()));
        productMinField.setText(String.valueOf(product.getMin()));

        assocParts = product.getAllAssociatedParts();
        productBottomTableView.setItems(assocParts);
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
     * When triggered, it takes the selected Part object within the
     * top tableview and add it to the bottom table view as well as
     * add the part to the Product's list of associated parts.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would add selected Part to the bottom table view but not to 
     * the Product's associated parts list.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * Add a dialog box to confirm with the user that they want to
     * add the selected Part.
     * 
     */
    @FXML
    public void handleProductAddPartBtn(ActionEvent e) throws IOException {
        int selected = productTopTableView.getSelectionModel().getSelectedIndex();
                if(selected >= 0) {
                    Part selectedPart = productTopTableView.getSelectionModel().getSelectedItem();
                    assocParts.add(selectedPart);
                    productTopTableView.getSelectionModel().clearSelection();
                }
                else {
                    Alert modifyAlert = new Alert(Alert.AlertType.ERROR);
                    modifyAlert.setTitle("Modify Error");
                    modifyAlert.setHeaderText("Error Associating a Part");
                    modifyAlert.setContentText("No valid part selected.");

                    modifyAlert.showAndWait();
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
     * This method is an event listener that is binded to a button.
     * When triggered, the method takes the selected Part from the
     * bottom table view and removes it from the table view as well as
     * the Product's associated parts list.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Had an issue where removing the associated Part would remove the
     * Part from the top table instead.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not know how I would extend the functionality
     * of this method. It does not need to do any more than remove
     * the associated part, and do confirmation checks with the user.
     * 
     */
    @FXML
    public void handleRemoveBtn(ActionEvent e) throws IOException {
        int selected = productBottomTableView.getSelectionModel().getSelectedIndex();
                if(selected >= 0) {
                    Alert modifyAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    modifyAlert.setTitle("Removing Associated Part");
                    modifyAlert.setHeaderText("You're about to remove an associated part");
                    modifyAlert.setContentText("Are you sure you want this part removed?");

                    ButtonType yesButton = new ButtonType("Yes", ButtonData.OK_DONE);
                    ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                    modifyAlert.getButtonTypes().setAll(yesButton, cancelButton);
                    Optional<ButtonType> result = modifyAlert.showAndWait();
                    
                    if(result.get() == yesButton) {
                        assocParts.remove(productBottomTableView.getSelectionModel().getSelectedItem());
                        productBottomTableView.getSelectionModel().clearSelection();
                    }
                    else {
                        // close dialog
                    }
                    
                }
                else {
                    Alert modifyAlert = new Alert(Alert.AlertType.ERROR);
                    modifyAlert.setTitle("Modify Error");
                    modifyAlert.setHeaderText("Error Removing an Associated Part");
                    modifyAlert.setContentText("No valid part selected.");

                    modifyAlert.showAndWait();
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
     * This method is an event listener that is binded to a button.
     * This method reads the information entered from the user through
     * text fields and either creates or changes a Product.
     * There are validity checks for data entered to ensure the 
     * Product objects can be modified or created.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There were issues with validity checks and making sure
     * the Product was not created or modified if any of the checks
     * failed. 
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would create a single Alert object and change the content
     * based on the error to reduce redundancy. I would also add
     * a Dialog box to pop up to show the user the information of
     * the Product they created. If the user was modifying, the 
     * Dialog would show the differences instead.
     * 
     */
    @FXML
    public void saveProduct(ActionEvent e) throws IOException {
        boolean stockValid = false;
        boolean priceValid = false;
        boolean maxValid = false;
        boolean minValid = false;
        String name = productNameField.getText();
        int stock = 0; Integer.parseInt(productInvField.getText());
        double price = 0.00; Double.parseDouble(productPriceField.getText());
        int max = 0; Integer.parseInt(productMaxField.getText());
        int min = 0; Integer.parseInt(productMinField.getText());

        try {
            stock = Integer.parseInt(productInvField.getText());
            try {
                price = Double.parseDouble(productPriceField.getText());
                try {
                    max = Integer.parseInt(productMaxField.getText());
                    try {
                        min = Integer.parseInt(productMinField.getText());
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
            if(max < 0){
                Alert dataAlert = new Alert(Alert.AlertType.ERROR);
                dataAlert.setTitle("Error!");
                dataAlert.setHeaderText("Invalid Entry");
                dataAlert.setContentText("Part max cannot be negative.");
                dataAlert.showAndWait();
            }
            else if(min > max || min < 0) {
                Alert dataAlert = new Alert(Alert.AlertType.ERROR);
                dataAlert.setTitle("Error!");
                dataAlert.setHeaderText("Invalid Entry");
                dataAlert.setContentText("Product min cannot be greater than max and must be positive.");
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
            else if(modifying){
                currProduct.setId(Integer.parseInt(productIDField.getText()));
                currProduct.setName(name);
                currProduct.setStock(stock);
                currProduct.setPrice(price);
                currProduct.setMax(max);
                currProduct.setMin(min);
    
                for(Part p : assocParts) {
                    if(currProduct.getAllAssociatedParts().contains(p)) {
                        continue;
                    }
                    else {
                        currProduct.addAssociatedPart(p);
                    }
                }
    
                modifying = false;
                Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
                Stage stage = (Stage) productSaveBtn.getScene().getWindow();
    
                stage.setScene(new Scene(main));
            }
            else {
                Product newProduct = new Product(id++, name, price, stock, min, max);
                if(!assocParts.isEmpty()){
                    for(Part p : assocParts) {
                        newProduct.addAssociatedPart(p);
                    }
                }
                Inventory.addProduct(newProduct);
                Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
                Stage stage = (Stage) productSaveBtn.getScene().getWindow();
    
                stage.setScene(new Scene(main));
            }
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
     * This method is an event listener that is binded to a button.
     * This method acts the same as the handCancelBtn() method found
     * in the PartFormsController class.
     * When triggered, it will return to the main form without saving
     * any information entered.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would continue to display a Product's information when cancelling
     * and adding a new Product. 
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would add a Dialog box to let the user know that the data
     * they entered would not be saved.
     * 
     */
    @FXML
    public void handleCancelBtn(ActionEvent e) throws IOException{
        if(modifying) {
            modifying = false;
        }

        Pane main = (Pane) FXMLLoader.load(getClass().getResource("/view/mainform.fxml"));
        Stage stage = (Stage) productCancelBtn.getScene().getWindow();

        stage.setScene(new Scene(main));
    }

    
    /** 
     * @param location
     * @param resources
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an overridden method from the superclass
     * Initializable. It initializes the window upon loading and
     * sets the data to be visible. This method also holds lambda
     * methods that provide search bar functionality.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There were issues when setting the cell value factories because
     * the columns weren't initialized in the FXML file.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think the functionality of this method could be
     * extended as it is purely for initializing the table views
     * in the product form page and creating the functionality
     * of the Part search bar.
     * 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns to values
        topIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        topNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        topInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        botIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        botNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        botInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        botPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Part Searchbar functionality
        productPartSearchbar.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                String query = productPartSearchbar.getText();

                // Search by name
                ObservableList<Part> parts = Inventory.lookupPart(query);
                
                // If first call results in empty list, assume an ID is passed.
                try {
                    if(parts.isEmpty()) {
                        int id = Integer.parseInt(query);
                        Part p = Inventory.lookupPart(id);
                        if(p != null) {
                            parts.add(p);
                        }
                    }
                }
                catch (NumberFormatException err) {
                    // Ignore
                }
                
                productTopTableView.setItems(parts);

                // If after both searches used and list still empty, display error.
                if(parts.isEmpty()) {
                    Alert partSearchAlert = new Alert(Alert.AlertType.WARNING);
                    partSearchAlert.setTitle("Search Error");
                    partSearchAlert.setHeaderText("Part not found!");
                    partSearchAlert.setContentText("Check ID or spelling.");
                    partSearchAlert.showAndWait();
                }
            }
        });

        productTopTableView.setItems(Inventory.getAllParts());
        productBottomTableView.setItems(assocParts);
    }
}
