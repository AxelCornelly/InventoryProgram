package controller;
/**
 * FormController Class FormController.java
 * This is the controller class for the main
 * form fxml file.
 */

/**
 * @author Axel Cornelly
 */

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class FormController implements Initializable{
    @FXML
    private Button addPartBtn, modifyPartBtn, deletePartBtn, addProductBtn, modifyProductBtn, deleteProductBtn, exitBtn;

    @FXML
    private TextField partSearchbar, productSearchbar;

    @FXML
    private TableView<Part> partsTableView;
    
    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Part, Integer> partIDColumn, partInvColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    
    @FXML
    private TableColumn<Product, Integer> productIDColumn, productInvColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    
    /** 
     * @param e
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * The closeApp() function is an event listener that takes in an ActionEvent object e.
     * The method closes out of the program as if the close window button was pressed.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * With this method I had issues with the window closing because I had initially forgot to cast
     * exitBtn.getScene().getwindow as a Stage object.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * Initialize a Stage object instead of casting it in order to clean up code.
     * 
     */
    @FXML
    public void closeApp(ActionEvent e) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    
    /** 
     * @param form
     * @param btn
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method was a helper method I created to avoid the redundancy of multiple screen
     * switching methods. It takes in a String object which is the command to determine the
     * path of the desired fxml file, and a Button object which determines which button called it.
     * The method utilizes a switch block that takes in the String parameter and sets the stage
     * scene dependent on the desired form.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * I had many issues with this method. At first, the FXMLLoaders could not find the path that
     * led to the correct fxml files due to my project not being a Java package. I also had problems
     * figuring out how to change form widgets from another form until I referred to the webinar that
     * explained how. When it came to switching to the modifying forms for both parts and products,
     * it took some time for me to figure out how to use the tableview selection methods.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would improve this method by removing it altogether. Even though it is redundant, I would have
     * had less problems if I created multiple event listener methods binded to the corresponding buttons
     * that would need to use them. This would remove the need for the switch block and result in easier to read code.
     * 
     */
    public void switchSceneTo(String form, Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        
        FXMLLoader partLoader = new FXMLLoader(getClass().getResource("/view/partform.fxml"));
        Pane partPane = (Pane) partLoader.load();
        PartFormsController partFormsController = partLoader.getController();
        
        FXMLLoader productLoader = new FXMLLoader(getClass().getResource("/view/productform.fxml"));
        Pane productPane = (Pane) productLoader.load();
        ProductFormsController productFormsController = productLoader.getController();

        switch(form) {
            case "addpartform":
                partFormsController.updateLabel("Add Part");
                stage.setScene(new Scene(partPane));
                break;
            case "addproductform":
                productFormsController.updateLabel("Add Product");
                stage.setScene(new Scene(productPane));
                break;
            case "modifypartform":
                int selected = partsTableView.getSelectionModel().getSelectedIndex();
                if(selected >= 0) {
                    Object selectedPart = partsTableView.getSelectionModel().getSelectedItem();
                    partFormsController.parsePartData(selectedPart);
                    partFormsController.updateLabel("Modify Part");
                    stage.setScene(new Scene(partPane));
                }
                else {
                    Alert modifyAlert = new Alert(Alert.AlertType.ERROR);
                    modifyAlert.setTitle("Modify Error");
                    modifyAlert.setHeaderText("Error Modifying Part");
                    modifyAlert.setContentText("No valid part selected.");

                    modifyAlert.showAndWait();
                }
                break;
            case "modifyproductform":
                int selectedProductRow = productTableView.getSelectionModel().getSelectedIndex();
                if(selectedProductRow >= 0) {
                    productFormsController.parseProductData(productTableView.getSelectionModel().getSelectedItem());
                    productFormsController.updateLabel("Modify Product");
                    stage.setScene(new Scene(productPane));
                }
                else {
                    Alert modifyAlert = new Alert(Alert.AlertType.ERROR);
                    modifyAlert.setTitle("Modify Error");
                    modifyAlert.setHeaderText("Error Modifying Product");
                    modifyAlert.setContentText("No valid product selected.");

                    modifyAlert.showAndWait();
                }
                break;
        }
    }

    
    /** 
     * @param e
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an event listener for its corresponding button. When triggered,
     * it will call the helper method switchSceneTo() with parameters "addpartform" and addPartBtn.
     * This is sending that helper method the correlated command to open the part form window as well as
     * send the button that was used to find out which window the command came from.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * I had issues with calling the helper method in this method. I had to play around with what parameters 
     * would needed to be passed and how to handle them in the helper method.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * Instead of calling a helper method, I would just write out the functionality of switching scenes into this
     * method. It would remove the layering of method calls and result in easier bug fixing.
     * 
     */
    @FXML
    public void handleAddPartBtn(ActionEvent e) throws IOException {
        switchSceneTo("addpartform", addPartBtn);
    }

    
    /** 
     * @param e
     * @throws IOException
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method functions the same as handleAddPartBtn(). It is an event listener for a binded button.
     * It calls a helper method with a String and Button as parameters.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * This method had the same issues as the method above. I also had issues with passing in the wrong
     * String command into the helper method which resulted in opening the wrong form/window. I also had
     * an issue binding it to the right button because the method names are so similar.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would also remove the helper method call here as well. It adds an extra step and isn't necessary.
     * I would have just put the logic for switching scenes here as well.
     * 
     */
    @FXML
    public void handleAddProductBtn(ActionEvent e) throws IOException {
        switchSceneTo("addproductform", addProductBtn);
    }

    
    /** 
     * @param e
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an event listener that is binded to a Button object.
     * This method allows for the user to delete a selected part after a
     * quick confirmation from a popup Dialog. Upon deleting the selected
     * part, the part is removed from the table view as well as the allParts
     * ObservableList found in the Inventory class.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * With this method, I had issues with initializing the confirmation dialog box.
     * The yesButton object I created wouldn't show sometimes and was replaced with the
     * default "Ok" button. This method also had issues with deleting the part regardless
     * of saying yes or cancel.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would improve this method by adding an additional Dialog box that would appear
     * upon a successful delete that showed the information about the deleted Part.
     * 
     */
    @FXML
    public void handleDeletePartBtn(ActionEvent e) throws IOException {
        int selected = partsTableView.getSelectionModel().getSelectedIndex();
        if(selected >= 0) {
            Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDeleteAlert.setTitle("Confirm Delete");
            confirmDeleteAlert.setHeaderText("You're about to delete a part!");
            confirmDeleteAlert.setContentText("Are you sure you want to delete this part?");

            ButtonType yesButton = new ButtonType("Yes", ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            confirmDeleteAlert.getButtonTypes().setAll(yesButton, cancelButton);
            Optional<ButtonType> result = confirmDeleteAlert.showAndWait();
            
            if(result.get() == yesButton) {
                Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());
                partsTableView.getSelectionModel().clearSelection();
            }
            else {
                // close dialog
            }
        }
        else {
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("Delete Error");
            deleteAlert.setHeaderText("Error Deleting Part");
            deleteAlert.setContentText("No valid part selected.");

            deleteAlert.showAndWait();
        }
    }

    
    /** 
     * @param e
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an event listener that is binded to a button.
     * When called, it checks to see if a Product within the Product
     * Table View is selected and tries to delete it. If there is no 
     * selected Product, an error Dialog will appear warning the user.
     * If the Product has an associated part, it will also show an
     * error Dialog to the user. If the Product does not have an
     * associated part, then it prompts the user for confirmation and
     * removes the Product from the Table View and ObservableList if 
     * the user presses yes.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * I had a small issue with this method where the Product would be
     * deleted regardless of if the Product had associated parts with it.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would add an additional dialog box after a successful delete
     * that showed the information of the Product deleted.
     * 
     */
    @FXML
    public void handleDeleteProductBtn(ActionEvent e) throws IOException {
        int selected = productTableView.getSelectionModel().getSelectedIndex();
        if(selected >= 0) {
            Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
            
            if(selectedProduct.getAllAssociatedParts().size() == 0){
                Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmDeleteAlert.setTitle("Confirm Delete");
                confirmDeleteAlert.setHeaderText("You're about to delete a product!");
                confirmDeleteAlert.setContentText("Are you sure you want to delete this product?");

                ButtonType yesButton = new ButtonType("Yes", ButtonData.OK_DONE);
                ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
                confirmDeleteAlert.getButtonTypes().setAll(yesButton, cancelButton);
                Optional<ButtonType> result = confirmDeleteAlert.showAndWait();
                
                if(result.get() == yesButton) {
                    Inventory.deleteProduct(productTableView.getSelectionModel().getSelectedItem());
                    partsTableView.getSelectionModel().clearSelection();
                }
                else {
                    // close dialog
                }
            }
            else {
                Alert confirmDeleteAlert = new Alert(Alert.AlertType.ERROR);
                confirmDeleteAlert.setTitle("Error Deleting");
                confirmDeleteAlert.setHeaderText("Unable to Delete Product");
                confirmDeleteAlert.setContentText("This product has parts associated with it.");
                confirmDeleteAlert.showAndWait();
            }
        }
        else {
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("Delete Error");
            deleteAlert.setHeaderText("Error Deleting Part");
            deleteAlert.setContentText("No valid part selected.");

            deleteAlert.showAndWait();
        }
    }

    
    /** 
     * @param e
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an event listener that is binded to a button.
     * It calls the helper method switchSceneTo() with parameters
     * that will allow the window to change to the modify part form.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * This method had issues with passing the correct button from which
     * it was called from.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would remove the helper method call and put all functionality within
     * this method. I would also add a Dialog box to appear and notify the user
     * that they are about to switch windows.
     * 
     */
    @FXML
    public void handleModifyPartBtn(ActionEvent e) throws IOException {
        switchSceneTo("modifypartform", modifyPartBtn);
    }

    
    /** 
     * @param e
     * @throws IOException
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method is an event listener that is binded to a button.
     * It called the helper method switchSceneTo() with parameters
     * that allow the window to switch to the modify product form.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * An issue I had with this method was passing the correct parameters
     * to the helper method.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would remove the helper method call and build the funcionality here
     * instead. I would also add a Dialog box to confirm with the user that 
     * they are about to modify a product.
     * 
     */
    @FXML
    public void handleModifyProductBtn(ActionEvent e) throws IOException {
        switchSceneTo("modifyproductform", modifyProductBtn);
    }

    
    /** 
     * @param location
     * @param resources
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is an overrided method from the superclass Initializable. It 
     * initializes a window and all its data as it loads. This method is
     * used to populate the table views on the main form. It also includes
     * lambda method calls for search bar functionality.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * I had an issue displaying the data in the table views. At first I
     * was not pulling data from the ObservableLists in the Inventory class.
     * I also had problems with setting the cell value factories for the 
     * table columns and had mismatching column value types.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would add a welcome Dialog to appear on the first call of this method
     * so that the user is greeted when they run the program for the first time.
     * 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize table columns to values
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Part Searchbar functionality
        partSearchbar.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                String query = partSearchbar.getText();

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
                
                partsTableView.setItems(parts);

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

        // Product Searchbar functionality
        productSearchbar.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                String query = productSearchbar.getText();

                ObservableList<Product> products = Inventory.lookupProduct(query);
                
                // If first call results in empty list, assume an ID is passed.
                try {
                    if(products.isEmpty()) {
                        int id = Integer.parseInt(query);
                        Product p = Inventory.lookupProduct(id);
                        if(p != null) {
                            products.add(p);
                        }
                    }
                }
                catch (NumberFormatException err) {
                    // Ignore
                }
                
                productTableView.setItems(products);

                // If after both searches used and list still empty, display error.
                if(products.isEmpty()) {
                    Alert productSearchAlert = new Alert(Alert.AlertType.WARNING);
                    productSearchAlert.setTitle("Search Error");
                    productSearchAlert.setHeaderText("Product not found!");
                    productSearchAlert.setContentText("Check ID or spelling.");
                    productSearchAlert.showAndWait();
                }
            }
        });

        partsTableView.setItems(Inventory.getAllParts());
        productTableView.setItems(Inventory.getAllProducts());
    }
}
