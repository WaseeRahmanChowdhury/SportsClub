module com.summer26.section1.group2.sportclub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.summer26.section1.group2.sportclub to javafx.fxml;
    exports com.summer26.section1.group2.sportclub;
}