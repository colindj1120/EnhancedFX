package io.github.colindj1120.materialdesignui;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.colindj1120.materialdesignui.controls.EnhancedTextField;
import io.github.colindj1120.materialdesignui.controls.EnhancedToggleButton;
import io.github.colindj1120.materialdesignui.controls.ToggleNavigationBar;
import io.github.colindj1120.materialdesignui.enums.effects.RippleShape;
import io.github.colindj1120.materialdesignui.testing.controllers.EnhancedTextFieldControlsController;
import io.github.colindj1120.materialdesignui.testing.controllers.ToggleNavigationBarControlsController;
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

        VBox vBox = getMDToggleNavigationBarVBox();
//        VBox  vBox  = getCustomTextFieldVBox();
        Scene scene = new Scene(vBox, 800, 600);

        vBox.requestFocus();
        stage.setTitle("MDTextField Test");
        stage.setScene(scene);
        stage.show();
    }

    private VBox getMDToggleNavigationBarVBox() {
        ToggleNavigationBar  toggleNavigationBar = new ToggleNavigationBar();
        EnhancedToggleButton btn                 = toggleNavigationBar.addToggleButton("LADfadfadf", isSelected -> {});
        btn.setPrefSize(400, 400);
        btn.setStyle("-fx-background-radius: 10 20 30 40; -fx-border-radius: 10 20 30 40;");
        btn.setRippleClipShape(RippleShape.ASYMMETRIC_ROUNDED_RECTANGLE);
        btn.setRippleShape(RippleShape.ASYMMETRIC_ROUNDED_RECTANGLE);
        btn.setRippleRadius(10);
        return getvBox(new HBox(toggleNavigationBar), 600, createToggleNavigationBarControls(toggleNavigationBar));
    }

    @NotNull
    private VBox getCustomTextFieldVBox() {
        EnhancedTextField textField = new EnhancedTextField();

        return getvBox(new HBox(textField), 250, createTextFieldControls(textField));
    }

    @NotNull
    private VBox getvBox(HBox hBox, int maxWidth, GridPane gridPane) {
        hBox.setAlignment(Pos.CENTER);
        hBox.setMaxWidth(maxWidth);

        gridPane.setStyle("-fx-background-color: blue");
        gridPane.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20, hBox, gridPane);
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
        }
        catch (IOException e) {
            System.out.println(e);
            return new GridPane();
        }
    }

    @SuppressWarnings("ThrowablePrintedToSystemOut")
    private GridPane createToggleNavigationBarControls(ToggleNavigationBar toggleNavigationBar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/testing/fxml/ToggleNavigationBarControls.fxml"));
            loader.load();

            ToggleNavigationBarControlsController controller = loader.getController();
            controller.setToggleNavigationBar(toggleNavigationBar);

            return controller.getToggleNavigationBarControls();
        }
        catch (IOException e) {
            System.out.println(e);
            return new GridPane();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}