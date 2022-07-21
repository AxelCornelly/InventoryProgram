package model;
/**
 * Inventory class.
 * Main application file
 */

import javafx.collections.FXCollections;

/**
 * @author Axel Cornelly
 */

import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static Part lookupPart(int partID) {
        for(Part p : allParts) {
            if(p.getId() == partID) {
                return p;
            }
        }

        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> queriedParts = FXCollections.observableArrayList();

        for(Part p : allParts) {
            if(p.getName().toLowerCase().contains(partName.toLowerCase())) {
                queriedParts.add(p);
            }
        }

        return queriedParts;
    }

    public static Product lookupProduct(int productID) {
        for(Product p : allProducts) {
            if(p.getId() == productID) {
                return p;
            }
        }

        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> queriedProducts = FXCollections.observableArrayList();

        for(Product p : allProducts) {
            if(p.getName().toLowerCase().contains(productName.toLowerCase())) {
                queriedProducts.add(p);
            }
        }

        return queriedProducts;
    }

    public static void updatePart(int index, Part selectedPart) {
        if(selectedPart.getClass().getSimpleName().equalsIgnoreCase("inhouse")) {
            InHouse partToUpdate = (InHouse) Inventory.getAllParts().get(index);
            partToUpdate.setId(selectedPart.getId());
            partToUpdate.setName(selectedPart.getName());
            partToUpdate.setPrice(selectedPart.getPrice());
            partToUpdate.setStock(selectedPart.getStock());
            partToUpdate.setMin(selectedPart.getMin());
            partToUpdate.setMax(selectedPart.getMax());
            partToUpdate.setMachineId(((InHouse)selectedPart).getMachineId());
        }
        else if(selectedPart.getClass().getSimpleName().equalsIgnoreCase("outsourced")) {
            Outsourced partToUpdate = (Outsourced) Inventory.getAllParts().get(index);
            partToUpdate.setId(selectedPart.getId());
            partToUpdate.setName(selectedPart.getName());
            partToUpdate.setPrice(selectedPart.getPrice());
            partToUpdate.setStock(selectedPart.getStock());
            partToUpdate.setMin(selectedPart.getMin());
            partToUpdate.setMax(selectedPart.getMax());
            partToUpdate.setCompanyName(((Outsourced)selectedPart).getCompanyName());
        }
    }

    public static void updateProduct(int index, Product newProduct) {

    }

    public static boolean deletePart(Part selectedPart) {
        if(allParts.remove(selectedPart)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean deleteProduct(Product selectedProduct) {

    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
