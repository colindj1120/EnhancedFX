package io.github.colindj1120.materialdesignui.controls;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * CustomControl with a title, label, and textfield, ensuring alignment. Version: 1.5 Author: Colin Jokisch
 */
public class CustomControl extends Pane {

    private Label     title;
    private Label     label;
    private TextField textField;
    private Button btn;
    private boolean isOn = false;

    /**
     * Constructs the CustomControl with title, label, and textfield.
     */
    public CustomControl() {
        initializeComponents();
        setupLayout();
    }

    /**
     * Initializes the title, label, and textfield components.
     */
    private void initializeComponents() {
        title     = new Label("First Name");
        label     = new Label("Your Label:");
        textField = new TextField();
        btn = new Button("Switch");

        label.setStyle("-fx-font-size: 15;");

        btn.setOnAction(e -> {
            if(isOn) {
                isOn = false;
                label.setStyle("-fx-font-size: 30");
            } else {
                isOn = true;
                label.setStyle("-fx-font-size: 15");
            }
        });
    }

    /**
     * Sets up the layout with proper alignment.
     */
    private void setupLayout() {
        setStyle("-fx-background-color: lightblue");
        title.setPadding(new Insets(0, 3, 0, 3));
        title.setStyle("-fx-background-color: lightblue; -fx-text-fill: black;");
        textField.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-style: solid; -fx-border-width: 1");
        getChildren().addAll(textField, label, title, btn);

        textField.layoutYProperty().bind(label.heightProperty().add(title.heightProperty().divide(2)));
        title.layoutYProperty().bind(textField.layoutYProperty().subtract(title.heightProperty().divide(2)));
        title.layoutXProperty().bind(textField.layoutXProperty().add(5));
        btn.layoutYProperty().bind(textField.layoutYProperty().add(textField.heightProperty()).add(10));
    }
}

