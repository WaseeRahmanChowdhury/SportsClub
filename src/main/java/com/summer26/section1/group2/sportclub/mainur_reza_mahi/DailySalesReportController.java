package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DailySalesReportController
{
    @javafx.fxml.FXML
    private Label totalRevenueLabel;
    @javafx.fxml.FXML
    private TableView categoryBreakdownTC;
    @javafx.fxml.FXML
    private TableColumn itemSoldTC;
    @javafx.fxml.FXML
    private TableColumn revenueTC;
    @javafx.fxml.FXML
    private Label totalOrderServedLabel;
    @javafx.fxml.FXML
    private Label totalItemSoldLabel;
    @javafx.fxml.FXML
    private Label bestSellingItemLabel;
    @javafx.fxml.FXML
    private TableColumn categoryTC;
    @javafx.fxml.FXML
    private DatePicker reportDateDP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void generateReportButtonOA(ActionEvent actionEvent) {
    }
}