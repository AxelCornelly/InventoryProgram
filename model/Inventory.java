package model;
/**
 * Inventory class.
 * This class serves to handle the inventory
 * of Parts and Products
 */

 /**
 * @author Axel Cornelly
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    
    /** 
     * @param part Part object to be added
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a Part object as a parameter
     * and adds the part into the allParts ObservableList.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue where the method could not add to allParts
     * because it was not initialized.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not know how to extend the functionality
     * of this method. Nothing else should be done here except add
     * the part that was fed in through the parameter.
     * 
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    
    /** 
     * @param product Product Object to be added
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a Product object as a parameter
     * and adds it to the allProducts ObservableList.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue with the method not adding
     * to the list because the list was not initialized.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think the functionality of this method
     * should be extended. Nothing else should be done here
     * except add the product to the list.
     * 
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    
    /** 
     * @param partID The ID of the Part Object
     * @return Part
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in an int as a parameter and uses it to find
     * the corresponding part within the ObservableList, allParts.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There were issues comparing ids.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would improve this method by implementing a binary search instead
     * of a linear one. It would increase the speed of which the item is
     * found.
     * 
     */
    public static Part lookupPart(int partID) {
        for(Part p : allParts) {
            if(p.getId() == partID) {
                return p;
            }
        }

        return null;
    }

    
    /** 
     * @param partName The name of the Part to be found
     * @return ObservableList<Part>
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a String as a parameter and uses it
     * to search the ObservableList allParts for Parts that have
     * the same String or part of it. Returns an ObservableList<Part> object.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There were issues with finding parts that had partially matching
     * strings. Resolved by using the .toLowerCase().contains() methods.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would use a binary search algorithm here instead of linear.
     * 
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> queriedParts = FXCollections.observableArrayList();

        for(Part p : allParts) {
            if(p.getName().toLowerCase().contains(partName.toLowerCase())) {
                queriedParts.add(p);
            }
        }

        return queriedParts;
    }

    
    /** 
     * @param productID The ID of the Product Object to be found
     * @return Product
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in an int parameter and uses it to
     * search the allProducts ObservableList for a Product with
     * matching id.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue with comparing the ids.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would implement a binary search algorithm here to
     * speed up the searching process.
     * 
     */
    public static Product lookupProduct(int productID) {
        for(Product p : allProducts) {
            if(p.getId() == productID) {
                return p;
            }
        }

        return null;
    }

    
    /** 
     * @param productName The name of the Product to be found
     * @return ObservableList<Product>
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a String parameter and uses it to
     * check for matching String values within the Products in
     * the allProducts list.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue comparing strings and making sure that
     * partial strings were considered as well.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would implement a binary search algorithm here to speed up
     * the searching process.
     * 
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> queriedProducts = FXCollections.observableArrayList();

        for(Product p : allProducts) {
            if(p.getName().toLowerCase().contains(productName.toLowerCase())) {
                queriedProducts.add(p);
            }
        }

        return queriedProducts;
    }

    
    /** 
     * @param index The index to focus on in the list
     * @param selectedPart The Part object to update to
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in two parameters, an int and a Part object.
     * This method updates the Part object located at the index of
     * int by removing it and inserting the passed in Part object.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There were issues where updating an InHouse Part into an
     * Outsourced Part would throw type mismatches.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * Since a Part object is being passed in, I would change it 
     * so that there are two versions of this method. One for
     * InHouse parts, one for Outsourced ones.
     * 
     */
    public static void updatePart(int index, Part selectedPart) {
        if(selectedPart.getClass().getSimpleName().equalsIgnoreCase("inhouse")) {
            getAllParts().remove(getAllParts().get(index));
            getAllParts().add(index, (InHouse)selectedPart);
        }
        else if(selectedPart.getClass().getSimpleName().equalsIgnoreCase("outsourced")) {
            getAllParts().remove(getAllParts().get(index));
            getAllParts().add(index, (Outsourced)selectedPart);
        }
    }

    
    /** 
     * @param index The index to focus on in the list
     * @param newProduct The Product object to update to
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in two parameters, an int and a Product object.
     * This method updates the Product located at the index of int by
     * removing it and inserting the passed in Product.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue of removing the existing Product from the
     * index provided.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I could extend the functionality of this method
     * any further.
     * 
     */
    public static void updateProduct(int index, Product newProduct) {
        getAllProducts().remove(getAllProducts().get(index));
        getAllProducts().add(index, newProduct);

    }

    
    /** 
     * @param selectedPart The Part to be deleted
     * @return boolean
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a Part object as a parameter and 
     * returns a boolean value dependant on if the delete
     * operation was successful.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue with passing a part into this
     * method.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I can extend the functionality of this
     * method any further.
     * 
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    
    /** 
     * @param selectedProduct The Product to be deleted.
     * @return boolean
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a Product object as a parameter
     * and removes it from the allProducts ObservableList.
     * This method also returns a boolean value if the delete
     * operation was successful.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue with passing in the correct Product
     * object.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I can extend the functionality of this
     * method any further.
     * 
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    
    /** 
     * @return ObservableList<Part>
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method returns the ObservableList allParts.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue with the list not being initialized
     * so it was not being returned.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I can extend the functionality of this
     * method any further.
     * 
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    
    /** 
     * @return ObservableList<Product>
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method returns the ObservableList allProducts
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue where the list was not being returned
     * because it was never initialized.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I can extend the functionality of this
     * method any further.
     * 
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
