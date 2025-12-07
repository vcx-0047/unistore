package unistore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

import unistore.model.Item;

public class ItemController {

    @FXML private TableView<Item> itemsTable;
    @FXML private TableColumn<Item, Integer> colId;
    @FXML private TableColumn<Item, String> colName;
    @FXML private TableColumn<Item, Integer> colQty;
    @FXML private TableColumn<Item, Void> colAction;
    @FXML private Button createItemBtn;

    private ObservableList<Item> itemList;

    @FXML
    public void initialize() {
        setupTable();
        loadItems();

        createItemBtn.setOnAction(e -> openCreateItemPage());
    }

    private void setupTable() {
        colId.setCellValueFactory(cell -> cell.getValue().idProperty().asObject());
        colName.setCellValueFactory(cell -> cell.getValue().nameProperty());
        colQty.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());
 
        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = column -> {
            return new TableCell<Item, Void>() {
                private final Button btn = new Button("Edit");
                {
                    btn.setOnAction(e -> {
                        Item item = getTableView().getItems().get(getIndex());
                        openEditItemPage(item);
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : btn);
                }
            };
        };
        colAction.setCellFactory(cellFactory);
    }

    private void loadItems() {
        itemList = FXCollections.observableArrayList(
            new Item(1, "Mouse", 10),
            new Item(2, "Keyboard", 5)
        );
        itemsTable.setItems(itemList);
    }

    private void openEditItemPage(Item item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unistore/view/pages/items/edit.fxml"));
            Parent view = loader.load();
            EditItemController controller = loader.getController();
            controller.setItem(item);
 
            MainController.getInstance().getContentStack().getChildren().setAll(view);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void openCreateItemPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unistore/view/pages/items/create.fxml"));
            Parent view = loader.load();
            MainController.getInstance().getContentStack().getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
