package com.example.javafx_practice;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ForexController implements Initializable {

    public TextField txtDateInput;
    public TextField txtDateOutput;
    public Button btnDateOk;
    public Button btnMonth;
    public Button btnYear;
    public TextField txtAverageOutput;
    public TextField txtStandardDeviationOutput;
    public Button txtForeignExchange;
    public LineChart chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtForeignExchange.setText("usd");
    }

    public void searchForex(ActionEvent actionEvent) {
        String DateInput="";
        if (txtDateInput.getText()!=null){
            DateInput = txtDateInput.getText();
        } else {
            return; // null 값이라서 종료함.
        }

        // 서버에 DateInput를 넘겨준다. send()함수 사용.

        // receive()함수를 통해서 해당 날짜의 환율을 가져온다.
        txtDateOutput.setText("1100");



    }

    public void viewGraphMonth(ActionEvent actionEvent) {
    }

    public void viewGraphYear(ActionEvent actionEvent) {
    }
}