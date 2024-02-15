package io.github.colindj1120.materialdesignui;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.colindj1120.materialdesignui.controls.ToggleNavigationBar;
import io.github.colindj1120.materialdesignui.controls.EnhancedTextField;
import io.github.colindj1120.materialdesignui.testing.controllers.EnhancedTextFieldControlsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        CSSFX.start();

        VBox  vBox  = getMDToggleNavigationBarVBox();
//        VBox  vBox  = getCustomTextFieldVBox();
        Scene scene = new Scene(vBox, 800, 600);


        vBox.requestFocus();
        stage.setTitle("MDTextField Test");
        stage.setScene(scene);
        stage.show();
    }

    private VBox getMDToggleNavigationBarVBox() {
        ToggleNavigationBar toggleNavigationBar = new ToggleNavigationBar();

        HBox hBox = new HBox(toggleNavigationBar);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMaxWidth(250);

        VBox vBox = new VBox(20, hBox);
        vBox.setStyle("-fx-background-color: lightblue");
        vBox.setAlignment(Pos.CENTER);
        return vBox;

    }

    @NotNull
    private VBox getCustomTextFieldVBox() {
        EnhancedTextField textField = new EnhancedTextField();

        HBox hBox = new HBox(textField);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMaxWidth(250);

        GridPane textFieldControls = createTextFieldControls(textField);
        textFieldControls.setStyle("-fx-background-color: blue");
        textFieldControls.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20, hBox, textFieldControls);
        vBox.setStyle("-fx-background-color: lightblue");
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    @SuppressWarnings("ThrowablePrintedToSystemOut")
    private GridPane createTextFieldControls(EnhancedTextField textField) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/testing/fxml/EnhancedTextFieldControls.fxml"));
            loader.load();

            EnhancedTextFieldControlsController controller = loader.getController();
            controller.setEnhancedTextField(textField);

            return controller.getTextFieldControls();
        } catch (IOException e) {
            System.out.println(e);
            return new GridPane();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}