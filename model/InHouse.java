package model;
/**
 * InHouse Class InHouse.java
 * This is a subclass of Part and
 * serves as the structure for all
 * InHouse objects.
 */

/**
 * @author Axel Cornelly
 */

public class InHouse extends Part{
    private int machineId;
    /**
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is the constructor for InHouse objects. Takes the
     * parameters fed into it and uses the superclass constructor
     * to set them as well as sets the variable machineId.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * Had issues with missing values.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I do not think I can improve this constructor. It is short
     * and to the point.
     * 
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    
    /** 
     * @param machineId
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This method sets the variable machineId to the value
     * of the parameter.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * One issue I had with this method was forgetting to
     * use the 'this' keyword to set the value.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * If the UML Class diagram allowed it, I would have made
     * this method static so that it could be called anywhere.
     * 
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    
    /** 
     * @return int
     * 
     * <p><b>
     * Functionality
     * </b></p>
     * 
     * This is a getter method. It returns an int value,
     * machineId.
     * 
     * <p><b>
     * Issues
     * </b></p>
     * 
     * There was an issue when machineId did not have a value.
     * 
     * <p><b>
     * Improvements
     * </b></p>
     * 
     * I would probably use the 'this' keyword here as well. 
     * 
     */
    public int getMachineId() {
        return machineId;
    }
    
}
