package model;
/**
 * Outsourced Class Outsourced.java
 * This is a subclass of Part and
 * is the structure behind all Outsourced objects.
 */

/**
 * @author Axel Cornelly
 */

public class Outsourced extends Part{
    private String companyName;

    /**
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is the constructor for Outsourced objects. It takes the values
     * of its parameters and calls the superclass's constructor to set its
     * values. However, it sets the variable 'companyName' here, instead of 
     * in the superclass constructor.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There were issues with this method and missing values.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I can improve this constructor.
     * 
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    
    /** 
     * @param companyName
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a setter method used by Outsourced objects. It takes in
     * a String value as a parameter and sets its companyName value to
     * the String value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There were issues where this method would set invalid entries.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I can improve the functionality of a setter method.
     * 
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
    /** 
     * @return String
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a getter method that returns a String value.
     * It returns the Outsourced Part's companyName value.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue where the company name would not be returned.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I can improve this method's functionality.
     * 
     */
    public String getCompanyName() {
        return companyName;
    }
    
}
