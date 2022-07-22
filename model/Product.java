package model;
/**
 * Product class Product.java.
 * Serves as the structur for all
 * Product objects.
 */

 /**
 * @author Axel Cornelly
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    
    /** 
     * @param id
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a setter method that sets the Product's
     * id value to the value passed in through the parameter.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Issue where this method would try setting a non-integer value.
     * Resolved with a validity check in another class.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I dont think this method needs its functionality to be extended.
     * 
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * @param name
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a setter method that sets the Product's
     * name. This class takes in the String parameter
     * and sets the value of the input to the Product's name.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Issue where this method would try to set a numerical value.
     * Resolved with validity check in another class.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended.
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * @param price
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a setter method that sets the Product's
     * price value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Issue where method would set an int instead.
     * Resolved with specifying type of input.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality extended.
     * 
     */
    public void setPrice(double price) {
        this.price = price;
    }

    
    /** 
     * @param stock
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a setter method that takes in an integer
     * parameter and sets the Product's stock value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Issue where the method would set negative values.
     * Resolved with a validity check in another class.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended.
     * 
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    
    /** 
     * @param min
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a setter method that takes in an integer as a parameter
     * and sets the Product's min value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Issue where the min value could be set to a negative number.
     * Resolved with a validity check in another class.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended
     * 
     */
    public void setMin(int min) {
        this.min = min;
    }

    
    /** 
     * @param max
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a setter method that takes in an integer as a parameter
     * and sets the Product's max value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Issue where the max value could be set to a negative number.
     * Resolved with validity checks in another class.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended.
     * 
     */
    public void setMax(int max) {
        this.max = max;
    }

    
    /** 
     * @return int
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a getter method which returns the Product
     * object's id value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would sometimes not return an integer when called.
     * Resolved with a correct call to this method in another
     * class.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended.
     * 
     */
    public int getId() {
        return id;
    }

    
    /** 
     * @return String
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a getter method that returns the Product
     * object's name value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Method would be called incorrectly.
     * Resolved with correct usage in other classes.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality extended.
     * 
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @return double
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a getter method that returns a double value
     * of the Product object's price.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would return an integer.
     * Resolved with a correct type cast.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended.
     * 
     */
    public double getPrice() {
        return price;
    }

    
    /** 
     * @return int
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a getter method that returns an integer value
     * correspondant to the Product object's stock value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would return a negative number when not checked.
     * Resolved with validity checks in other classes.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended
     * 
     */
    public int getStock() {
        return stock;
    }

    
    /** 
     * @return int
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a getter method that returns an integer value
     * correspondant to the Product object's min value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would return a negative value if unchecked.
     * Resolved with validity checks in other classes.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I dont think this method needs its functionality to be extended.
     * 
     */
    public int getMin() {
        return min;
    }
    
    
    /** 
     * @return int
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a getter method that returns an integer value
     * correspondant to the Product object's max value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would return a negative number if unchecked.
     * Resolved with validity checks in other classes.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality extended.
     * 
     */
    public int getMax() {
        return max;
    }

    
    /** 
     * @param part
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a Part as a parameter and
     * adds it to the current Product object's associatedParts list.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would not add Part object to associatedParts list.
     * Resolved by initializing the ObservableList correctly.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would initialize the ObservableList outside of the method
     * itself and include it at the beginning of the class structure.
     * 
     */
    public void addAssociatedPart(Part part) {
        associatedParts = FXCollections.observableArrayList();
        associatedParts.add(part);
    }

    
    /** 
     * @param selectedPart
     * @return boolean
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method takes in a Part object as a parameter
     * and removes it from the Product object's associatedParts
     * list.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would not remove the part that was fed in as the
     * parameter.
     * Resolved by ensuring the Part selected exists and can be found.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended.
     * 
     */
    public boolean deleteAssociatedPart(Part selectedPart) {
        return associatedParts.remove(selectedPart);
    }

    
    /** 
     * @return ObservableList<Part>
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method returns a Product object's associatedParts list.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Would throw an error when trying to return the list.
     * Resolved by initializing the list correctly.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I don't think this method needs its functionality to be extended.
     * 
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
