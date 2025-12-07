package unistore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent; 
import java.io.IOException;
import javafx.scene.control.Button;

public class MainController {

    @FXML private StackPane contentStack;  
    
    private static MainController instance;
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button usersBtn;
    @FXML
    private Button itemsBtn;
    @FXML
    private Button employeesBtn;
    @FXML
    private Button borrowBtn;

    @FXML
    public void initialize() { 
        instance = this;
        usersBtn.setOnAction(e->loadPage("users/index.fxml"));
        itemsBtn.setOnAction(e->loadPage("items/list.fxml"));
        borrowBtn.setOnAction(e->loadPage("borrow/list.fxml"));

    } 

 public static MainController getInstance() {
        return instance;
    }

    public StackPane getContentStack() {
        return contentStack;
    }

    public void loadPage(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/unistore/view/pages/" + fxmlPath));
            contentStack.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }  
}  
