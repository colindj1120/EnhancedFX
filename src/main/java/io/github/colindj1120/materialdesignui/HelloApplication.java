package io.github.colindj1120.materialdesignui;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.colindj1120.materialdesignui.controls.MDTextField;
import io.github.colindj1120.materialdesignui.enums.EnabledStatus;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final String     JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String     connStr     = "jdbc:derby://localhost:1527/C:/Derby/WorkoutDB";
    @Override
    public void start(Stage stage) throws IOException {
        //DatabaseUtility.getInstance(JDBC_DRIVER, connStr); //Sets up the database utility

        CSSFX.start();

        MDTextField textField = new MDTextField();
        BorderPane borderPane = new BorderPane(textField);
        borderPane.setPadding(new Insets(10));
//
        Button newButton = new Button("Test");
//        newButton.setOnAction(e -> textField.setSupportingTextState(EnabledStatus.DISABLED));

//        textField.setMaxCharacterCount(-1);
        VBox vBox = new VBox(10, borderPane, newButton);
        vBox.setStyle("-fx-background-color: lightblue");
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 600, 600);
        vBox.requestFocus();
        stage.setTitle("MDTextField Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}