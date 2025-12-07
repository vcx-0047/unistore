package unistore.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import unistore.model.Borrow;
import unistore.model.Employee;
import unistore.model.Item;

import java.time.LocalDate;

public class BorrowController {

    @FXML private ComboBox<Employee> employeeCombo;
    @FXML private TableView<Borrow> borrowTable;
    @FXML private TableColumn<Borrow, Item> colItem;
    @FXML private TableColumn<Borrow, Integer> colQuantity;
    @FXML private TableColumn<Borrow, Void> colAction;
    @FXML private Button addRowBtn;
    @FXML private Button btnBorrow;

    private ObservableList<Employee> employees;
    private ObservableList<Item> items;
    private ObservableList<Borrow> borrowList;

    @FXML
    public void initialize() { 
        employees = FXCollections.observableArrayList(
                new Employee(1, "Alice"),
                new Employee(2, "Bob"),
                new Employee(3, "Charlie")
        );

        items = FXCollections.observableArrayList(
                new Item(1, "Mouse", 10),
                new Item(2, "Keyboard", 5),
                new Item(3, "Monitor", 2)
        );
 
        employeeCombo.setItems(employees);
        employeeCombo.setEditable(true);
        employeeCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Employee e) { return e == null ? "" : e.getName(); }
            @Override
            public Employee fromString(String s) {
                return employees.stream().filter(emp -> emp.getName().equalsIgnoreCase(s)).findFirst().orElse(null);
            }
        });
 
        employeeCombo.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            employeeCombo.setItems(employees.filtered(emp ->
                    emp.getName().toLowerCase().contains(newVal.toLowerCase())
            ));
            employeeCombo.show();
        });
 
        borrowList = FXCollections.observableArrayList();
        borrowTable.setItems(borrowList);

        setupTableColumns();

        addRowBtn.setOnAction(e -> addRow());
    }

    private void setupTableColumns() { 
        colItem.setCellFactory(col -> new TableCell<>() {
            private final ComboBox<Item> combo = new ComboBox<>(items);

            {
                combo.setEditable(true);
                combo.setConverter(new StringConverter<>() {
                    @Override public String toString(Item i) { return i == null ? "" : i.getName(); }
                    @Override public Item fromString(String s) {
                        return items.stream().filter(it -> it.getName().equalsIgnoreCase(s)).findFirst().orElse(null);
                    }
                });
 
                combo.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
                    combo.setItems(items.filtered(it ->
                            it.getName().toLowerCase().contains(newVal.toLowerCase())
                    ));
                    combo.show();
                });

                combo.valueProperty().addListener((obs, oldVal, newVal) -> {
                    Borrow borrow = getTableRow().getItem();
                    if (borrow != null) borrow.setItem(newVal);
                });
            }

            @Override
            protected void updateItem(Item i, boolean empty) {
                super.updateItem(i, empty);
                if (empty) setGraphic(null);
                else {
                    combo.setValue(i);
                    setGraphic(combo);
                }
            }
        });
 
        colQuantity.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());
        colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override public String toString(Integer value) { return value.toString(); }
            @Override public Integer fromString(String s) { return Integer.parseInt(s); }
        }));
        colQuantity.setOnEditCommit(e -> e.getRowValue().setQuantity(e.getNewValue()));
 
        colAction.setCellFactory(col -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");
            {
                deleteBtn.setOnAction(e -> borrowList.remove(getTableRow().getItem()));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });

        borrowTable.setEditable(true);
    }

    private void addRow() {
        borrowList.add(new Borrow(
                employeeCombo.getValue(), 
                null, 
                1, 
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        ));
    }
}
