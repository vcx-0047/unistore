package unistore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback; 
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent; 
import java.io.IOException; 
import javafx.scene.Scene;
import javafx.stage.Stage;

import unistore.model.User;

public class UsersController {

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Integer> colId;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, Void> colAction;

    private ObservableList<User> userList;

    @FXML private Button createUser ;

    @FXML
    public void initialize() {
        setupTable();
        loadUsers();
        createUser.setOnAction(e->{ 
            try { 
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/unistore/view/pages/users/edit.fxml"));
                Parent view = loader.load(); 
                EditUserController controller = loader.getController(); 
                MainController mainController = MainController.getInstance();
                mainController.getContentStack().getChildren().setAll(view);
            } catch (IOException ex) {
                ex.printStackTrace();
            } 

         }); 
    }

    // ------------------------


    //  Setup Table Columns
    // ------------------------
    private void setupTable() {

        // map the User class fields
        colId.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        colName.setCellValueFactory(cell -> cell.getValue().usernameProperty());

        // Create "edit" button in each row
        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = column -> {
            return new TableCell<User, Void>() {
                private final Button btn = new Button("Edit");

                {
                    btn.setOnAction(e -> {
                        try {
                            User user = getTableView().getItems().get(getIndex());

                            // Load the FXML inside the main content stack
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unistore/view/pages/users/edit.fxml"));
                            Parent view = loader.load();

                            // Get the EditUserController and pass the user
                            EditUserController controller = loader.getController();
                            controller.setUser(user);

                            // Use MainController's contentStack to set the new page
                            MainController mainController = MainController.getInstance(); // singleton or reference
                            mainController.getContentStack().getChildren().setAll(view);

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });


                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
        };

        colAction.setCellFactory(cellFactory);
    }

    // ------------------------
    //  Load dummy data (simulating DB)
    // ------------------------
    private void loadUsers() {
        userList = FXCollections.observableArrayList(
            new User(1, "walid", "123", "admin"),
            new User(2, "john", "pass", "user")
        );

        usersTable.setItems(userList);
    }
}
