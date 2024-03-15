package io.github.colindj1120.enhancedfx;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.colindj1120.enhancedfx.controllers.EnhancedTextFieldControlsController;
import io.github.colindj1120.enhancedfx.controllers.ToggleNavigationBarControlsController;
import io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.EFXButton;
import io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.EFXToggleButton;
import io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.EFXToggleNavigationBar;
import io.github.colindj1120.enhancedfx.controls.control.efxtext.EFXTextField;
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

//        VBox vBox = getMDToggleNavigationBarVBox();
        VBox  vBox  = getCustomTextFieldVBox();
//        VBox  vBox  = getEnhancedButtonVBox();
        Scene scene = new Scene(vBox, 800, 600);

        vBox.requestFocus();
        stage.setTitle("MDTextField Test");
        stage.setScene(scene);
        stage.show();
    }

    private VBox getMDToggleNavigationBarVBox() {
        EFXToggleNavigationBar toggleNavigationBar = new EFXToggleNavigationBar();
        EFXToggleButton        btn                 = toggleNavigationBar.addToggleButton("LADfadfadf", isSelected -> {});
        btn.setPrefSize(400, 400);
        //btn.setStyle("-fx-background-radius: 10 20 30 40; -fx-border-radius: 10 20 30 40;");
        //btn.setRippleClipShape(EFXRippleShape.ASYMMETRIC_ROUNDED_RECTANGLE);
        //btn.setRippleShape(EFXRippleShape.ASYMMETRIC_ROUNDED_RECTANGLE);
        //btn.setRippleRadius(10);
        return getvBox(new HBox(toggleNavigationBar), 600, createToggleNavigationBarControls(toggleNavigationBar));
    }

    @NotNull
    private VBox getCustomTextFieldVBox() {
        EFXTextField textField = new EFXTextField();

        return getvBox(new HBox(textField), 250, createTextFieldControls(textField));
    }

    @NotNull
    private VBox getEnhancedButtonVBox() {
        EFXButton button = new EFXButton("Click me!");
        button.setPrefSize(200, 200);

        //ETF etf = new ETF();
//        etf.setPadding(EFXInsetUtils.vertical(20));
//        etf.setPadding(EFXInsetUtils.all(20, 30, 20, 30));

        return getvBox(new HBox(button), 600, new GridPane());
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
    private GridPane createTextFieldControls(EFXTextField textField) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EnhancedTextFieldControls.fxml"));
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
    private GridPane createToggleNavigationBarControls(EFXToggleNavigationBar toggleNavigationBar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ToggleNavigationBarControls.fxml"));
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