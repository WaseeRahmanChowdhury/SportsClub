package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class StaffDirectoryListController {

    @FXML
    private TextField searchField;
    @FXML
    private TableView<StaffMember> staffTable;
    @FXML
    private TableColumn<StaffMember, String> idColumn;
    @FXML
    private TableColumn<StaffMember, String> nameColumn;
    @FXML
    private TableColumn<StaffMember, String> roleColumn;
    @FXML
    private TableColumn<StaffMember, String> departmentColumn;
    @FXML
    private TableColumn<StaffMember, String> phoneColumn;

    @FXML
    private Label selectedStaffIdLabel;
    @FXML
    private TextField editNameField;
    @FXML
    private TextField editRoleField;
    @FXML
    private TextField editDepartmentField;
    @FXML
    private TextField editPhoneField;
    @FXML
    private Label statusLabel;

    private final ObservableList<StaffMember> staffRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        staffTable.setItems(staffRows);

        staffTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StaffMember>() {
            @Override
            public void changed(ObservableValue<? extends StaffMember> observable, StaffMember oldValue,
                                StaffMember newValue) {
                populateEditFields(newValue);
            }
        });

        // event-4: load all staff records from the staff directory
        staffRows.setAll(StaffMember.getStaffMembers());
    }

    // event-6: Admin types a name or ID in the search box to filter
    @FXML
    private void onSearch() {
        staffRows.setAll(StaffMember.searchStaff(searchField.getText()));
    }

    @FXML
    private void exportToPdf() {
        FileChooser chooser = new FileChooser();
        chooser.setInitialFileName("StaffDirectory.pdf");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
        File file = chooser.showSaveDialog(staffTable.getScene().getWindow());
        if (file == null) {
            return;
        }

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            Paragraph title = new Paragraph("Staff Directory");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("Generated: " + LocalDate.now()));
            document.add(new Paragraph(" "));

            Table table = new Table(5);
            table.addCell("Staff ID");
            table.addCell("Name");
            table.addCell("Role");
            table.addCell("Department");
            table.addCell("Phone");

            for (StaffMember member : staffTable.getItems()) {
                table.addCell(member.getStaffId());
                table.addCell(member.getFullName());
                table.addCell(member.getRole());
                table.addCell(member.getDepartment());
                table.addCell(member.getPhoneNumber());
            }
            document.add(table);

            document.close();
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Exported staff directory to " + file.getName());
        } catch (DocumentException e) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Could not generate the PDF document.");
        } catch (IOException e) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Could not write the PDF file.");
        }
    }

    private void populateEditFields(StaffMember member) {
        if (member == null) {
            selectedStaffIdLabel.setText("");
            editNameField.clear();
            editRoleField.clear();
            editDepartmentField.clear();
            editPhoneField.clear();
            return;
        }
        selectedStaffIdLabel.setText(member.getStaffId());
        editNameField.setText(member.getFullName());
        editRoleField.setText(member.getRole());
        editDepartmentField.setText(member.getDepartment());
        editPhoneField.setText(member.getPhoneNumber());
    }

    // event-7/8: Admin clicks 'Edit', updates fields, saves the updated record
    @FXML
    private void saveChanges() {
        statusLabel.setTextFill(Color.RED);

        String staffId = selectedStaffIdLabel.getText();
        if (staffId == null || staffId.isBlank()) {
            statusLabel.setText("Select a staff entry from the table first.");
            return;
        }

        if (editNameField.getText().isBlank() || editRoleField.getText().isBlank()
                || editDepartmentField.getText().isBlank() || editPhoneField.getText().isBlank()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        boolean updated = StaffMember.updateStaffMember(
                staffId,
                editNameField.getText().trim(),
                editRoleField.getText().trim(),
                editDepartmentField.getText().trim(),
                editPhoneField.getText().trim()
        );

        if (!updated) {
            statusLabel.setText("Could not find staff record to update.");
            return;
        }

        ActivityLog.log(ActivityLog.TYPE_STAFF,
                "Updated staff record " + staffId, "Admin");

        staffTable.refresh();
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Staff record updated successfully");
    }
}
