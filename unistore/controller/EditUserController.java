package unistore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import unistore.model.User;

public class EditUserController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField roleField;

    private User user;

    public void setUser(User user) {
        this.user = user;
        if(user != null) {
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            roleField.setText(user.getRole());
        }
    }

    @FXML
    private void saveUser() {
        if(user != null) {
            user.setUsername(usernameField.getText());
            user.setPassword(passwordField.getText());
            user.setRole(roleField.getText());
        }
        System.out.println("User saved: " + user.getUsername());
    }
}
