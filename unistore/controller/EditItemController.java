package unistore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import unistore.model.Item;

public class EditItemController {

    @FXML private TextField nameField;
    @FXML private TextField qtyField;
    @FXML private Button saveBtn;

    private Item item;

    public void setItem(Item item) {
        this.item = item;
        nameField.setText(item.getName());
        qtyField.setText(String.valueOf(item.getQuantity()));
    }

    @FXML
    public void initialize() {
        saveBtn.setOnAction(e -> {
            item.setName(nameField.getText());
            item.setQuantity(Integer.parseInt(qtyField.getText())); 
            MainController.getInstance().loadPage("items/list.fxml");
        });
    }
}
