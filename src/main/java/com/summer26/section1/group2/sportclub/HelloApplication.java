package com.summer26.section1.group2.sportclub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/summer26/section1/group2/sportclub/general/loginview.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/summer26/section1/group2/sportclub/Abdullah_Abuzor_Sajid/admin-dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }
}
