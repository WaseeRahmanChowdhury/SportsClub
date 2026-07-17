module com.summer26.section1.group2.sportclub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;



    opens com.summer26.section1.group2.sportclub to javafx.fxml;
    opens com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury to javafx.fxml;
    opens com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid to javafx.fxml;
    exports com.summer26.section1.group2.sportclub;
    opens com.summer26.section1.group2.sportclub.general to javafx.fxml;
    exports com.summer26.section1.group2.sportclub.Mahidul;
    opens com.summer26.section1.group2.sportclub.Mahidul to javafx.fxml;
    opens com.summer26.section1.group2.sportclub.mainur_reza_mahi to javafx.fxml;
    exports com.summer26.section1.group2.sportclub.mainur_reza_mahi;
}