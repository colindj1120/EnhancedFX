package io.github.colindj1120.materialdesignui;

import io.github.colindj1120.database.DatabaseUtility;
import io.github.colindj1120.materialdesignui.controls.CustomControl;
import io.github.colindj1120.materialdesignui.controls.MDTextField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final String     JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String     connStr     = "jdbc:derby://localhost:1527/C:/Derby/WorkoutDB";
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseUtility.getInstance(JDBC_DRIVER, connStr); //Sets up the database utility

//        CSSFX.start();


        MDTextField textField = new MDTextField();
        BorderPane borderPane = new BorderPane(textField);
        borderPane.setPadding(new Insets(10));

        CustomControl control = new CustomControl();
        Scene scene = new Scene(borderPane, 300, 300);

        stage.setTitle("MDTextField Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}