package unistore.model;

import javafx.beans.property.*;

public class Item {

    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty quantity;

    public Item(int id, String name, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int qty) { this.quantity.set(qty); }
    public IntegerProperty quantityProperty() { return quantity; }
}
