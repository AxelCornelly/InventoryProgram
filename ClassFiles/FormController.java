import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FormController {
    @FXML
    private Pane mainPane;
    
    @FXML
    private Button addPartBtn;

    @FXML
    private Button modifyPartBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private Button addProductBtn;

    @FXML
    private Button modifyProductBtn;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField partSearchbar;

    @FXML
    private TextField productSearchbar;

    Stage stage;

    public void closeApp(ActionEvent e) {
        stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }
}
