package classfiles;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FormController {
    @FXML
    private Button addPartBtn, modifyPartBtn, deletePartBtn, addProductBtn, modifyProductBtn, deleteProductBtn, exitBtn;

    @FXML
    private TextField partSearchbar, productSearchbar;

    public void closeApp(ActionEvent e) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void switchSceneTo(String form, Button btn) throws IOException{
        Parent page;
        Stage stage = (Stage) btn.getScene().getWindow();

        switch(form) {
            case "addpartform":
                page = (Pane) FXMLLoader.load(getClass().getResource("/UI/partform.fxml"));
                stage.setScene(new Scene(page));
                break;
            case "addproductform":
                page = (Pane) FXMLLoader.load(getClass().getResource("/UI/productform.fxml"));
                stage.setScene(new Scene(page));
                break;
            case "modifypartform":
                page = (Pane) FXMLLoader.load(getClass().getResource("/UI/partform.fxml"));
                stage.setScene(new Scene(page));
                break;
            case "modifyproductform":
                page = (Pane) FXMLLoader.load(getClass().getResource("/UI/productform.fxml"));
                stage.setScene(new Scene(page));
                break;
        }
    }

    public void handleAddPartBtn(ActionEvent e) throws IOException{
        switchSceneTo("addpartform", addPartBtn);
    }

    public void handleAddProductBtn(ActionEvent e) throws IOException{
        switchSceneTo("addproductform", addPartBtn);
    }

    public void handleModifyPartBtn(ActionEvent e) throws IOException{
        switchSceneTo("modifypartform", modifyPartBtn);
    }

    public void handleModifyProductBtn(ActionEvent e) throws IOException{
        switchSceneTo("modifyproductform", modifyProductBtn);
    }

}
