package model;
/**
 * Inventory class.
 * Main application file
 */

/**
 * @author Axel Cornelly
 */

import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static Part lookupPart(int partID) {

    }

    public static ObservableList<Part> lookupPart(String partName) {

    }

    public static Product lookupProduct(int productID) {

    }

    public static ObservableList<Product> lookupProduct(String productName) {

    }

    public static void updatePart(int index, Part selectedPart) {

    }

    public static void updateProduct(int index, Product newProduct) {

    }

    public static boolean deletePart(Part selectedPart) {

    }

    public static boolean deleteProduct(Product selectedProduct) {

    }

    public static ObservableList<Part> getAllParts() {

    }

    public static ObservableList<Product> getAllProducts() {

    }

}
