package unistore.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;

public class Borrow {
    private Employee employee;
    private Item item;
    private IntegerProperty quantity;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Borrow(Employee employee, Item item, int quantity, LocalDate borrowDate, LocalDate returnDate) {
        this.employee = employee;
        this.item = item;
        this.quantity = new SimpleIntegerProperty(quantity);
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters and setters
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public IntegerProperty quantityProperty() { return quantity; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
