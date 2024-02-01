package io.github.colindj1120.materialdesignui.testing.controllers;

import io.github.colindj1120.materialdesignui.controls.EnhancedTextField;
import io.github.colindj1120.materialdesignui.enums.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.coreui.CoreUiFree;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Objects;

public class EnhancedTextFieldControlsController {
    @FXML
    protected GridPane textFieldControls;
    @FXML
    protected Button   addLeadingIcon, removeLeadingIcon, addTrailingIcon, removeTrailingIcon, setPromptText, removePromptText, setWidthTo225, setWidthToDefault, setHeightTo225, setHeightToDefault,
            disableFloatMode, aboveFloatMode, borderFloatMode, insideFloatMode, enableSupportingText, disableSupportingText, enableMaxChar, disableMaxChar, setMaxChar10, setMaxChar100,
            setMaxCharDefault, focusHere, setMaxCharAbove, setMaxCharBelow, setTextModeFilled, setTextModeOutline, setStyleDark, setStyleLight, setAlignmentTopLeft, setAlignmentCenterLeft,
            setAlignmentBottomLeft, setAlignmentBaseLineLeft, setAlignmentTopCenter, setAlignmentCenter, setAlignmentBottomCenter, setAlignmentBaseLineCenter, setAlignmentTopRight,
            setAlignmentCenterRight, setAlignmentBottomRight, setAlignmentBaseLineRight, alwaysFloatingOn, alwaysFloatingOff;

    protected final FontIcon          leadingIcon  = FontIcon.of(CoreUiFree.MAGNIFYING_GLASS, 20);
    protected final FontIcon          trailingIcon = FontIcon.of(CoreUiFree.X_CIRCLE, 20);
    protected       EnhancedTextField textField;

    @FXML
    protected void handleAddLeadingIcon() {
        checkEnhancedTextFieldLoaded();
        textField.setLeadingIcon(leadingIcon);
    }

    @FXML
    protected void handleRemoveLeadingIcon() {
        checkEnhancedTextFieldLoaded();
        textField.removeLeadingIcon();
    }

    @FXML
    protected void handleAddTrailingIcon() {
        checkEnhancedTextFieldLoaded();
        textField.setTrailingIcon(trailingIcon);
    }

    @FXML
    protected void handleRemoveTrailingIcon() {
        checkEnhancedTextFieldLoaded();
        textField.removeTrailingIcon();
    }

    @FXML
    protected void handleSetPromptText() {
        checkEnhancedTextFieldLoaded();
        textField.setPromptText("Prompt Text");
    }

    @FXML
    protected void handleRemovePromptText() {
        checkEnhancedTextFieldLoaded();
        textField.setPromptText("");
    }

    @FXML
    protected void handleSetWidthTo225() {
        checkEnhancedTextFieldLoaded();
        textField.setPrefWidth(225);
    }

    @FXML
    protected void handleSetWidthToDefault() {
        checkEnhancedTextFieldLoaded();
        textField.setPrefWidth(180);
    }

    @FXML
    protected void handleSetHeightTo225() {
        checkEnhancedTextFieldLoaded();
        textField.setPrefHeight(225);
    }

    @FXML
    protected void handleSetHeightToDefault() {
        checkEnhancedTextFieldLoaded();
        textField.setPrefHeight(27.6);
    }

    @FXML
    protected void handleDisableFloatMode() {
        checkEnhancedTextFieldLoaded();
        textField.setFloatMode(FloatMode.DISABLED);
        textField.setFloatingText("");
    }

    @FXML
    protected void handleAboveFloatMode() {
        checkEnhancedTextFieldLoaded();
        textField.setFloatMode(FloatMode.ABOVE);
        textField.setFloatingText("Floating Text");
    }

    @FXML
    protected void handleBorderFloatMode() {
        checkEnhancedTextFieldLoaded();
        textField.setFloatMode(FloatMode.BORDER);
        textField.setFloatingText("Floating Text");
    }

    @FXML
    protected void handleInsideFloatMode() {
        checkEnhancedTextFieldLoaded();
        textField.setFloatMode(FloatMode.INSIDE);
        textField.setFloatingText("Floating Text");
    }

    @FXML
    protected void handleEnableSupportingText() {
        checkEnhancedTextFieldLoaded();
        textField.setSupportingTextState(EnabledStatus.ENABLED);
        textField.setSupportingText("Supporting Text");
    }

    @FXML
    protected void handleDisableSupportingText() {
        checkEnhancedTextFieldLoaded();
        textField.setSupportingTextState(EnabledStatus.DISABLED);
        textField.setSupportingText("");
    }

    @FXML
    protected void handleEnableMaxChar() {
        checkEnhancedTextFieldLoaded();
        textField.setMaxCharCountState(EnabledStatus.ENABLED);
    }

    @FXML
    protected void handleDisableMaxChar() {
        checkEnhancedTextFieldLoaded();
        textField.setMaxCharCountState(EnabledStatus.DISABLED);
    }

    @FXML
    protected void handleSetMaxChar10() {
        checkEnhancedTextFieldLoaded();
        textField.setMaxCharacterCount(10);
    }

    @FXML
    protected void handleSetMaxChar100() {
        checkEnhancedTextFieldLoaded();
        textField.setMaxCharacterCount(100);
    }

    @FXML
    protected void handleSetMaxCharDefault() {
        checkEnhancedTextFieldLoaded();
        textField.setMaxCharacterCount(50);
    }

    @FXML
    protected void handleFocusHere() {
        focusHere.requestFocus();
    }

    @FXML
    protected void handleSetMaxCharAbove() {
        checkEnhancedTextFieldLoaded();
        textField.setMaxCharacterCountPosition(MaxCharacterCountPosition.ABOVE);
    }

    @FXML
    protected void handleSetMaxCharBelow() {
        checkEnhancedTextFieldLoaded();
        textField.setMaxCharacterCountPosition(MaxCharacterCountPosition.BELOW);
    }

    @FXML
    protected void handleSetTextModeFilled() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldMode(TextFieldMode.FILLED);
    }

    @FXML
    protected void handleSetTextModeOutline() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldMode(TextFieldMode.OUTLINED);
    }

    @FXML
    protected void handleSetStyleDark() {
        checkEnhancedTextFieldLoaded();
        textField.setStyleMode(StyleMode.DARK);
    }

    @FXML
    protected void handleSetStyleLight() {
        checkEnhancedTextFieldLoaded();
        textField.setStyleMode(StyleMode.LIGHT);
    }

    @FXML
    private void handleSetAlignmentTopLeft() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.TOP_LEFT);
    }

    @FXML
    private void handleSetAlignmentCenterLeft() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.CENTER_LEFT);
    }

    @FXML
    private void handleSetAlignmentBottomLeft() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.BOTTOM_LEFT);
    }

    @FXML
    private void handleSetAlignmentBaseLineLeft() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.BASELINE_LEFT);
    }

    @FXML
    private void handleSetAlignmentTopCenter() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.TOP_CENTER);
    }

    @FXML
    private void handleSetAlignmentCenter() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.CENTER);
    }

    @FXML
    private void handleSetAlignmentBottomCenter() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.BOTTOM_CENTER);
    }

    @FXML
    private void handleSetAlignmentBaseLineCenter() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.BASELINE_CENTER);
    }

    @FXML
    private void handleSetAlignmentTopRight() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.TOP_RIGHT);
    }

    @FXML
    private void handleSetAlignmentCenterRight() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.CENTER_RIGHT);
    }

    @FXML
    private void handleSetAlignmentBottomRight() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.BOTTOM_RIGHT);
    }

    @FXML
    private void handleSetAlignmentBaseLineRight() {
        checkEnhancedTextFieldLoaded();
        textField.setTextFieldAlignment(Pos.BASELINE_RIGHT);
    }

    @FXML
    private void handleAlwaysFloatingOn() {
        checkEnhancedTextFieldLoaded();
        textField.setAlwaysFloating(true);
    }

    @FXML
    private void handleAlwaysFloatingOff() {
        checkEnhancedTextFieldLoaded();
        textField.setAlwaysFloating(false);
    }

    private void checkEnhancedTextFieldLoaded() {
        if (Objects.isNull(this.textField)) {
            throw new IllegalStateException("Enhanced Text Field Variable Has Not Been Set");
        }
    }

    public void setEnhancedTextField(EnhancedTextField textField) {
        this.textField = textField;
    }

    public GridPane getTextFieldControls() {
        return textFieldControls;
    }
}
