package unistore.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import unistore.model.Borrow;

import java.io.IOException;
import java.time.LocalDate;

import unistore.model.Borrow;
import unistore.model.Employee;
import unistore.model.Item;

import java.time.LocalDate;

public class BorrowListController {

    @FXML private TableView<Borrow> borrowListTable;
    @FXML private TableColumn<Borrow, Integer> colIndex;
    @FXML private TableColumn<Borrow, String> colItem;
    @FXML private TableColumn<Borrow, String> colEmployee;
    @FXML private TableColumn<Borrow, LocalDate> colDate;
    @FXML private TableColumn<Borrow, Void> colReturn;

    @FXML private Button btnNewBorrow;

    private ObservableList<Borrow> borrowList;

    @FXML
    public void initialize() { 
        borrowList = FXCollections.observableArrayList(
        new Borrow(
            new Employee(1, "Alice"),
            new Item(1, "Mouse", 10),
            1,
            LocalDate.now().minusDays(2),
            LocalDate.now().plusDays(5)
        ),
        new Borrow(
            new Employee(2, "Bob"),
            new Item(2, "Keyboard", 5),
            1,
            LocalDate.now().minusDays(1),
            LocalDate.now().plusDays(7)
        )
);

        borrowListTable.setItems(borrowList);

        setupColumns();

        btnNewBorrow.setOnAction(e -> openBorrowPage());
    }

    private void openBorrowPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unistore/view/pages/borrow/borrow.fxml"));
            Parent view = loader.load();

            MainController.getInstance().getContentStack().getChildren().setAll(view);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setupColumns() { 
        colIndex.setCellValueFactory(cell ->
                new SimpleIntegerProperty(borrowList.indexOf(cell.getValue()) + 1).asObject()
        );
 
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
 
        colEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));
 
        colDate.setCellValueFactory(cell ->
                new SimpleObjectProperty<>(cell.getValue().getBorrowDate())
        );
 
        Callback<TableColumn<Borrow, Void>, TableCell<Borrow, Void>> cellFactory = col -> new TableCell<>() {
            private final Button btn = new Button("Return");
            {
                btn.setOnAction(e -> {
                    Borrow record = getTableView().getItems().get(getIndex());
                    borrowList.remove(record);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        };
        colReturn.setCellFactory(cellFactory);
    }
}
