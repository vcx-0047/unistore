package unistore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class LoginController {

    @FXML private Button loginBtn;
    @FXML private TextField TextField;
    @FXML private PasswordField PasswordField;

    @FXML
    public void initialize() {
        // Pass the ActionEvent from the button to the method
        loginBtn.setOnAction(this::switchToSceneTwo);
    }

    public void switchToSceneTwo(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/unistore/view/pages/Main.fxml")); 
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
