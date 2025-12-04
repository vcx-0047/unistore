package unistore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class LoginController {

    @FXML private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, cButton, dotButton, convertButton;
    @FXML private TextField dim;
    @FXML private Label resault ;

    private String dimValue = ""; 

    @FXML
    public void initialize() {

        button1.setOnAction(e -> appendDigit("1"));
        button2.setOnAction(e -> appendDigit("2"));
        button3.setOnAction(e -> appendDigit("3"));
        button4.setOnAction(e -> appendDigit("4"));
        button5.setOnAction(e -> appendDigit("5"));
        button6.setOnAction(e -> appendDigit("6"));
        button7.setOnAction(e -> appendDigit("7"));
        button8.setOnAction(e -> appendDigit("8"));
        button9.setOnAction(e -> appendDigit("9"));
        button0.setOnAction(e -> appendDigit("0")); 
        dotButton.setOnAction(e -> appendDigit(".")); 
        cButton.setOnAction(e -> clearInput());
        convertButton.setOnAction(e -> convert());
    }

    private void appendDigit(String digit) {
        dimValue = dimValue + digit;
        dim.setText(String.valueOf(dimValue));
        
    }

    private void clearInput() {
        dimValue = "";
        dim.setText("");
    }

    private void convert() { 
        double newDimValue = Double.parseDouble(dimValue);
        double km = newDimValue * 1.60934;
        resault.setText(dimValue + " miles = " + km + " km") ;
        dim.setText("");
    }
}
