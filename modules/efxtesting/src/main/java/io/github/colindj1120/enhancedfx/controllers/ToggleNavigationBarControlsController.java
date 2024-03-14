package io.github.colindj1120.enhancedfx.controllers;

import io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.EFXToggleButton;
import io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.EFXToggleNavigationBar;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class ToggleNavigationBarControlsController {
    @FXML
    private GridPane toggleNavigationBarControls;

    @FXML
    private Button addToggleButton, orientationHorizontalButton, orientationVerticalButton, deleteSelected;

    private EFXToggleNavigationBar toggleNavigationBar;

    @FXML
    private void handleAddToggleButton() {
        checkToggleNavigationBarLoaded();
        // Generating random key using UUID
        String randomKey = UUID.randomUUID()
                               .toString();

        // Generating random button text
        String        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random        rnd      = new Random();
        int           length   = rnd.nextInt(1, 10);  // Generates a random integer between 7 and 12
        StringBuilder sb       = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
        }
        String randomButtonText = sb.toString();

        toggleNavigationBar.addToggleButton(randomButtonText, (isSelected) -> {});
    }

    @FXML
    private void handleOrientHorizontal() {
        checkToggleNavigationBarLoaded();
        toggleNavigationBar.setOrientation(Orientation.HORIZONTAL);
    }

    @FXML
    private void handleOrientVertical() {
        checkToggleNavigationBarLoaded();
        toggleNavigationBar.setOrientation(Orientation.VERTICAL);
    }

    @FXML
    private void handleDeleteSelectedButton() {
        checkToggleNavigationBarLoaded();
        Optional<EFXToggleButton> btn = toggleNavigationBar.getSelected();
        btn.ifPresent(b -> toggleNavigationBar.removeToggleButton(b));
    }

    private void checkToggleNavigationBarLoaded() {
        if (Objects.isNull(this.toggleNavigationBar)) {
            throw new IllegalStateException("Toggle Navigation Bar Variable Has Not Been Set");
        }
    }

    public void setToggleNavigationBar(EFXToggleNavigationBar toggleNavigationBar) {
        this.toggleNavigationBar = toggleNavigationBar;
    }

    public GridPane getToggleNavigationBarControls() {
        return toggleNavigationBarControls;
    }
}
