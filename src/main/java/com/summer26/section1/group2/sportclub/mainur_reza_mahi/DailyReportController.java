package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DailyReportController
{
    @javafx.fxml.FXML
    private TableColumn percentageTC;
    @javafx.fxml.FXML
    private Label commonPurposeLabel;
    @javafx.fxml.FXML
    private TableView purposeBreakdownTC;
    @javafx.fxml.FXML
    private Label currentlyInsideLabel;
    @javafx.fxml.FXML
    private TableColumn countTC;
    @javafx.fxml.FXML
    private Label totalLeftLabel;
    @javafx.fxml.FXML
    private Label totalVisitorsLabel;
    @javafx.fxml.FXML
    private TableColumn purposeTC;
    @javafx.fxml.FXML
    private DatePicker reportDateDP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void generateReportButtonOA(ActionEvent actionEvent) {
    }
}