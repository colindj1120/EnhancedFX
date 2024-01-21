/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.materialdesignui.controls;

import io.github.colindj1120.materialdesignui.enums.*;
import io.github.colindj1120.materialdesignui.styling.Stylesheets;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesCreator.SimpleStyleableObjectPropertyCreator;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesCreator.StyleableIntegerPropertyCreator;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesCreator.StyleableObjectPropertyCreator;
import io.github.colindj1120.materialdesignui.utils.styleutils.StyleablePropertiesManager;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.css.*;
import javafx.css.converter.ColorConverter;
import javafx.css.converter.EnumConverter;
import javafx.css.converter.SizeConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The MDTextField class extends the JavaFX Pane class to create a custom, feature-rich TextField UI component for use within the MaterialDesignUI application.
 *
 * <p>The MDTextField class incorporates a variety of features that go above and beyond the
 * functionality provided by the typical JavaFX TextField. This includes an array of pseudo CSS classes, used to represent the various states that the MDTextField can be in, such as having a floating
 * label, a border, or a filled mode.
 *
 * <p>The pseudo CSS classes utilized to facilitate this include:
 * <ul>
 *   <li>MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS</li>
 *   <li>MAX_CHAR_COUNT_POS_BELOW_PSEUDO_CLASS</li>
 *   <li>SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS</li>
 *   <li>FLOAT_BORDER_MODE_PSEUDO_CLASS</li>
 *   <li>FLOAT_ABOVE_MODE_PSEUDO_CLASS</li>
 *   <li>FLOAT_INSIDE_MODE_PSEUDO_CLASS</li>
 *   <li>FLOAT_ANIMATION_DISABLED_PSEUDO_CLASS</li>
 *   <li>TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS</li>
 *   <li>STYLE_MODE_DARK_PSEUDO_CLASS</li>
 *   <li>STYLE_MODE_CUSTOM_PSEUDO_CLASS</li>
 * </ul>
 *
 * <p>These classes dynamically modify the appearance and behavior of the TextField, altering
 * aesthetics such as colors, and functional aspects like positioning and visibility of selected
 * elements.
 *
 * <p>In order to control and manage these changes in response to the state of the TextField,
 * a series of Booleans and bindings are utilized. These observe properties such as text length,
 * float mode, style mode and more, and update the TextField as necessary when these values change.
 *
 * <p>A variety of properties such as maxCharacterCount, supportingTextState, and
 * maxCharacterCountPosition are made styleable using a StyleablePropertiesManager, which provides
 * more extensive flexibility in customizing the appearance of the TextField.
 *
 * <p>The class includes a TextField, characterCountLabel, supportingTextLabel, and floatingTextLabel
 * from JavaFX. These components are styled and configured in the MDTextField constructor and
 * private helper methods.
 *
 * <p>The `SuppressWarnings("unused")` annotation is used to prevent compiler warnings for
 * unused methods or properties. These properties, while not currently in use, are intended to
 * be utilized in future developments of this class.
 *
 * <p>The MDTextField class should be used wherever a Text Field UI component is required within
 * the MaterialDesignUI application, providing a user interface that is consistent, aesthetically
 * pleasing, and follows the Material Design guidelines.
 * <p>
 * This class is part of the {@code io.github.colindj1120.materialdesignui.controls} package.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
@SuppressWarnings("unused")
public class MDTextField extends Pane {
    private static final BackgroundFill             backgroundFill                        = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY);
    private static final ObjectProperty<Background> transparentBackgroundProperty         = new SimpleObjectProperty<>(new Background(backgroundFill));
    private static final StyleablePropertiesManager STYLEABLE_PROPERTIES_MANAGER          = new StyleablePropertiesManager(Pane.getClassCssMetaData());
    private static final String                     STYLE                                 = "md-text-field";
    private static final PseudoClass                MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS   = PseudoClass.getPseudoClass("max-char-count-enabled");
    private static final PseudoClass                MAX_CHAR_COUNT_POS_BELOW_PSEUDO_CLASS = PseudoClass.getPseudoClass("max-char-count-pos-below");
    private static final PseudoClass                SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS  = PseudoClass.getPseudoClass("supporting-text-enabled");
    private static final PseudoClass                FLOAT_BORDER_MODE_PSEUDO_CLASS        = PseudoClass.getPseudoClass("float-border-mode");
    private static final PseudoClass                FLOAT_ABOVE_MODE_PSEUDO_CLASS         = PseudoClass.getPseudoClass("float-above-mode");
    private static final PseudoClass                FLOAT_INSIDE_MODE_PSEUDO_CLASS        = PseudoClass.getPseudoClass("float-above-mode");
    private static final PseudoClass                FLOAT_ANIMATION_DISABLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("float-animation-disabled");
    private static final PseudoClass                TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS   = PseudoClass.getPseudoClass("filled-mode");
    private static final PseudoClass                STYLE_MODE_DARK_PSEUDO_CLASS          = PseudoClass.getPseudoClass("style-mode-dark");
    private static final PseudoClass                STYLE_MODE_CUSTOM_PSEUDO_CLASS        = PseudoClass.getPseudoClass("style-mode-custom");

    private final TextField                           textField             = new TextField();
    private final Label                               characterCountLabel   = new Label();
    private final Label                               supportingTextLabel   = new Label();
    private final Label                               floatingTextLabel     = new Label();
    private final SimpleIntegerProperty               textLength            = new SimpleIntegerProperty(this, "textLength", 0);
    private final SimpleObjectProperty<TextFieldSkin> currentSkin           = new SimpleObjectProperty<>(this, "textFieldSkin");
    private final SimpleStringProperty                floatingText          = new SimpleStringProperty(this, "floatingText", "");
    private final SimpleStringProperty                supportingText        = new SimpleStringProperty(this, "supportingText", "");
    private final Map<FloatMode, ParallelTransition>  FLOAT_MODE_ANIMATIONS = new HashMap<>();
    private final double                              scaleFactor           = .75;
    private final Scale                               scale                 = Transform.scale(1, 1, 0, 0);
    private final Translate                           translate             = Transform.translate(1, 1);

    private StyleableIntegerProperty                           maxCharacterCount;
    private StyleableObjectProperty<EnabledStatus>             maxCharCountState;
    private StyleableObjectProperty<MaxCharacterCountPosition> maxCharacterCountPosition;
    private StyleableObjectProperty<EnabledStatus>             supportingTextState;
    private StyleableObjectProperty<EnabledStatus>             floatAnimationState;
    private StyleableObjectProperty<FloatMode>                 floatMode;
    private StyleableObjectProperty<TextFieldMode>             textFieldMode;
    private StyleableObjectProperty<StyleMode>                 styleMode;
    private StyleableObjectProperty<Color>                     textFill;
    private boolean                                            isTextFieldEnlarged;

    private BooleanBinding isTextModeFilled;
    private BooleanBinding isFloatModeBorder;
    private BooleanBinding isFloatModeInside;
    private BooleanBinding isFloatModeAbove;
    private BooleanBinding isFloatModeDisabled;
    private BooleanBinding isMaxCharacterCountEnabled;
    private BooleanBinding isSupportingTextEnabled;
    private BooleanBinding isFloatAnimationEnabled;
    private BooleanBinding isMaxCharacterCountPosBelow;
    private BooleanBinding isStyleModeDark;
    private BooleanBinding isStyleModeCustom;
    private BooleanBinding isEmpty;

    /**
     * Checks if a property is non-null and not bound.
     *
     * @param property
     *         the property object to check
     * @param <T>
     *         the type of the property object, must extend Property
     *
     * @return true if the property is non-null and not bound, false otherwise
     */
    private static <T extends Property<?>> boolean checkProperty(T property) {
        return Objects.nonNull(property) && !property.isBound();
    }

    static {
        //@formatter:off
        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, Number>addCssMetaData("-max-char-count",
                                                                         SizeConverter.getInstance(),
                                                                         50,
                                                                         node -> checkProperty(node.maxCharacterCount) && node.isMaxCharacterCountEnabled.get(),
                                                                         node -> node.maxCharacterCount);

        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, EnabledStatus>addCssMetaData("-max-char-count-state",
                                                                                EnumConverter.getEnumConverter(EnabledStatus.class),
                                                                                EnabledStatus.DISABLED,
                                                                                node -> checkProperty(node.maxCharCountState),
                                                                                node -> node.maxCharCountState);

        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, MaxCharacterCountPosition>addCssMetaData("-max-char-count-pos",
                                                                                            EnumConverter.getEnumConverter(MaxCharacterCountPosition.class),
                                                                                            MaxCharacterCountPosition.ABOVE,
                                                                                            node -> checkProperty(node.maxCharacterCountPosition) && node.isMaxCharacterCountEnabled.get(),
                                                                                            node -> node.maxCharacterCountPosition);

        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, EnabledStatus>addCssMetaData("-supporting-text-state",
                                                                                EnumConverter.getEnumConverter(EnabledStatus.class),
                                                                                EnabledStatus.DISABLED,
                                                                                node -> checkProperty(node.supportingTextState),
                                                                                node -> node.supportingTextState);

        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, FloatMode>addCssMetaData("-float-mode",
                                                                            EnumConverter.getEnumConverter(FloatMode.class),
                                                                            FloatMode.DISABLED,
                                                                            node -> checkProperty(node.floatMode),
                                                                            node -> node.floatMode);

        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, EnabledStatus>addCssMetaData("-float-animation-state",
                                                                                EnumConverter.getEnumConverter(EnabledStatus.class),
                                                                                EnabledStatus.ENABLED,
                                                                                node -> checkProperty(node.floatAnimationState) && node.isFloatModeDisabled.not().get(),
                                                                                node -> node.floatAnimationState);

        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, TextFieldMode>addCssMetaData("-text-field-mode",
                                                                                EnumConverter.getEnumConverter(TextFieldMode.class), TextFieldMode.OUTLINED,
                                                                                node -> checkProperty(node.textFieldMode) && (node.isTextModeFilled.and(node.isFloatModeBorder)).not().get(),
                                                                                node -> node.textFieldMode);

        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, StyleMode>addCssMetaData("-style-mode",
                                                                            EnumConverter.getEnumConverter(StyleMode.class),
                                                                            StyleMode.LIGHT,
                                                                            node -> checkProperty(node.styleMode),
                                                                            node -> node.styleMode);

        STYLEABLE_PROPERTIES_MANAGER.<MDTextField, Color>addCssMetaData("-fx-text-fill",
                                                                        ColorConverter.getInstance(),
                                                                        Color.valueOf("#000000"),
                                                                        node -> checkProperty(node.textFill),
                                                                        node -> node.textFill);
        //@formatter:on
    }

    /**
     * Creates a new instance of the MDTextField class. It sets up the style, styleable properties, boolean bindings, text field, count label, supporting text label, and floating text label. It also
     * sets up the mouse clicked event to request focus. It adds the text field, character count label, and floating text label as children. Finally, it sets the layout and updates the pseudo classes
     * based on the property values.
     */
    public MDTextField() {
        super();
        setupStyle();
        setupStyleableProperties();
        setupBooleanBindings();
        setupTextField();
        setupCountLabel();
        setupSupportingTextLabel();
        setupFloatingTextLabel();

        setOnMouseClicked(e -> {
            requestFocus();
            e.consume();
        });

        this.getChildren()
            .addAll(textField, characterCountLabel, floatingTextLabel);
        setLayout();

        pseudoClassStateChanged(TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS, isTextModeFilled.get());
        pseudoClassStateChanged(MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS, isMaxCharacterCountEnabled.get());
        pseudoClassStateChanged(MAX_CHAR_COUNT_POS_BELOW_PSEUDO_CLASS, isMaxCharacterCountPosBelow.get());
        pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS, isSupportingTextEnabled.get());
        pseudoClassStateChanged(FLOAT_BORDER_MODE_PSEUDO_CLASS, isFloatModeBorder.get());
        pseudoClassStateChanged(FLOAT_INSIDE_MODE_PSEUDO_CLASS, isFloatModeInside.get());
        pseudoClassStateChanged(FLOAT_ABOVE_MODE_PSEUDO_CLASS, isFloatModeAbove.get());
        pseudoClassStateChanged(FLOAT_ANIMATION_DISABLED_PSEUDO_CLASS, isFloatAnimationEnabled.not()
                                                                                              .get());
        pseudoClassStateChanged(STYLE_MODE_DARK_PSEUDO_CLASS, isStyleModeDark.get());
        pseudoClassStateChanged(STYLE_MODE_CUSTOM_PSEUDO_CLASS, isStyleModeCustom.get());
    }

    /**
     * Sets up the boolean bindings for various properties. These bindings are used to dynamically update the boolean values based on the changes in the associated properties.
     */
    private void setupBooleanBindings() {
        isTextModeFilled            = Bindings.createBooleanBinding(() -> getTextFieldMode() == TextFieldMode.FILLED, textFieldMode);
        isFloatModeBorder           = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.BORDER, floatMode);
        isFloatModeInside           = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.INSIDE, floatMode);
        isFloatModeAbove            = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.ABOVE, floatMode);
        isFloatModeDisabled         = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.DISABLED, floatMode);
        isMaxCharacterCountEnabled  = Bindings.createBooleanBinding(() -> getMaxCharCountState() == EnabledStatus.ENABLED, maxCharCountState);
        isSupportingTextEnabled     = Bindings.createBooleanBinding(() -> getSupportingTextState() == EnabledStatus.ENABLED, supportingTextState);
        isFloatAnimationEnabled     = Bindings.createBooleanBinding(() -> getFloatAnimationState() == EnabledStatus.ENABLED && isFloatModeDisabled.not()
                                                                                                                                                  .get() && getPromptText().isEmpty() &&
                                                                          getText().isEmpty(), floatAnimationState, floatMode, promptTextProperty(), textProperty());
        isMaxCharacterCountPosBelow = Bindings.createBooleanBinding(() -> getMaxCharacterCountPosition() == MaxCharacterCountPosition.BELOW, maxCharacterCountPosition);
        isStyleModeDark             = Bindings.createBooleanBinding(() -> getStyleMode() == StyleMode.DARK, styleMode);
        isStyleModeCustom           = Bindings.createBooleanBinding(() -> getStyleMode() == StyleMode.CUSTOM, styleMode);
        isEmpty                     = Bindings.createBooleanBinding(() -> getText().isEmpty(), textProperty());
    }

    /**
     * Sets up the text field with necessary configurations and listeners.
     */
    private void setupTextField() {
        textField.getStyleClass()
                 .remove("text-field");
        textField.getStyleClass()
                 .add("field");

        textFill.addListener((obs, oldColor, newColor) -> updateStyleWithNewColor(newColor));

        textField.focusedProperty()
                 .addListener((observable, oldValue, newValue) -> {
                     if (isFloatAnimationEnabled.get()) {
                         animateFloatingText(newValue);
                     }
                 });

        textField.fontProperty()
                 .addListener((observable, oldFont, newFont) -> {
                     Font floatingLabelFont   = floatingTextLabel.getFont();
                     Font supportingLabelFont = supportingTextLabel.getFont();

                     setLabelFont(floatingTextLabel, floatingLabelFont, newFont);
                     setLabelFont(supportingTextLabel, supportingLabelFont, newFont);
                 });

        DoubleBinding heightBinding = Bindings.createDoubleBinding(this::calculateAdjustedTextFieldHeight, floatingTextLabel.visibleProperty(), floatMode);

        textField.minHeightProperty()
                 .bind(heightBinding);

        textField.alignmentProperty()
                 .bind(Bindings.when(floatingTextLabel.visibleProperty())
                               .then(Pos.BASELINE_LEFT)
                               .otherwise(Pos.CENTER_LEFT));

        textField.textProperty()
                 .addListener((observable, oldValue, newValue) -> {
                     if (newValue.length() > maxCharacterCount.get() && isMaxCharacterCountEnabled.get()) {
                         textField.setText(oldValue);
                     }
                 });
    }

    /**
     * Calculates the adjusted height of a text field based on the visibility of a floating text label and the float mode.
     *
     * @return The adjusted height of the text field. If the floating text label is visible and the float mode is inside, the height of the text field plus the height of the floating text label is
     *         returned. If the floating text label is not visible or the float mode is not inside, the height of the text field is returned. If the text field was previously enlarged and is now not
     *         enlarged, the height of the text field minus the height of the floating text label is returned.
     */
    private double calculateAdjustedTextFieldHeight() {
        if (floatingTextLabel.visibleProperty()
                             .and(isFloatModeInside)
                             .get()) {
            isTextFieldEnlarged = true;
            return textField.getHeight() + floatingTextLabel.getHeight();
        } else {
            if (isTextFieldEnlarged) {
                isTextFieldEnlarged = false;
                return textField.getHeight() - floatingTextLabel.getHeight();
            }
            return textField.getHeight();
        }
    }

    /**
     * Sets up the count label for character count in a text field.
     * <p>
     * This method binds the length of the text in the text field to the text length property, sets up the binding for displaying the character count in the count label, and configures the visibility
     * and background of the count label.
     * </p>
     * <p>
     * It also adds a listener to the max character count property, and truncates the text in the text field if it exceeds the new max character count.
     * </p>
     * <p>
     * Additionally, it sets up the mouse click event to request focus when the count label is clicked, and applies a CSS style class to the count label.
     * </p>
     */
    private void setupCountLabel() {
        textLength.bind(Bindings.length(textField.textProperty()));

        characterCountLabel.textProperty()
                           .bind(Bindings.concat(textLength.asString(), "/", maxCharacterCount.asString()));

        characterCountLabel.visibleProperty()
                           .bind(isMaxCharacterCountEnabled);
        characterCountLabel.backgroundProperty()
                           .bind(transparentBackgroundProperty);

        maxCharacterCount.addListener((obs, oldMax, newMax) -> {
            if (isMaxCharacterCountEnabled.get() && textField.getLength() > newMax.intValue()) {
                textField.setText(textField.getText(0, getMaxCharacterCount()));
            }
        });

        characterCountLabel.setOnMouseClicked(e -> requestFocus());

        characterCountLabel.getStyleClass()
                           .setAll("count-label");
    }

    /**
     * Sets up the supporting text label.
     * <p>
     * This method is responsible for configuring the supporting text label by setting its style class, text, visibility, background, and mouse event handler. The supporting text label will be added
     * to or removed from the parent container based on its visibility.
     * </p>
     *
     * @see #isSupportingTextEnabled()
     */
    private void setupSupportingTextLabel() {
        supportingTextLabel.getStyleClass()
                           .setAll("supporting-text-label");

        supportingTextLabel.setText("Supporting Text");

        supportingText.bind(supportingTextLabel.textProperty());

        supportingTextLabel.visibleProperty()
                           .bind(isSupportingTextEnabled);

        supportingTextLabel.visibleProperty()
                           .addListener((obs, oldVisible, isVisible) -> {
                               if (isVisible) {
                                   getChildren().add(supportingTextLabel);
                               } else {
                                   getChildren().remove(supportingTextLabel);
                                   autosize();
                               }
                           });
        supportingTextLabel.backgroundProperty()
                           .bind(transparentBackgroundProperty);

        supportingTextLabel.setOnMouseClicked(e -> requestFocus());
    }

    /**
     * Sets up the floating text label for a text field. The floating text label is displayed above the text field when it is not empty. It is used to provide a hint or description of the expected
     * input in the text field. This method configures the visual appearance and behavior of the floating text label.
     */
    private void setupFloatingTextLabel() {
        floatingTextLabel.setText("Floating Text");
        floatingTextLabel.getStyleClass()
                         .setAll("floating-label");

        floatingTextLabel.getTransforms()
                         .addAll(scale, translate);

        floatingTextLabel.backgroundProperty()
                         .bind(Bindings.when(isTextModeFilled.or(isFloatModeBorder.not()))
                                       .then(transparentBackgroundProperty)
                                       .otherwise(textField.backgroundProperty()));

        floatingTextLabel.setOnMouseClicked(e -> {
            if (isFloatAnimationEnabled.get() && scale.getX() == 1.0) {
                textField.requestFocus();
                e.consume();
            }
        });

        floatingTextLabel.visibleProperty()
                         .bind(isFloatModeDisabled.not());

        floatingText.bind(floatingTextLabel.textProperty());

        Platform.runLater(() -> floatingTextLabel.layoutXProperty()
                                                 .bind(Bindings.createDoubleBinding(this::calculateFloatingLabelLayoutXPosition, currentSkin, textField.layoutXProperty(),
                                                                                    floatingTextLabel.paddingProperty())));

        floatAnimationState.addListener((obs) -> {
            if (isFloatAnimationEnabled.get()) {
                applyAnimationState(scale, translate, 1, 1, 1, 1);
            } else {
                double translateX = floatingTextLabel.getPadding()
                                                     .getLeft() * scaleFactor - 1;
                double translateY = -1 * getFloatingLabelTargetY();
                applyAnimationState(scale, translate, scaleFactor, scaleFactor, translateX, translateY);
            }
        });
    }

    /**
     * Sets the layout for various UI elements:
     * <ul>
     *    <li>Binds the layoutY property of the textField to textFieldLayoutYBinding</li>
     *    <li>Binds the layoutX property of the characterCountLabel to textField.widthProperty() subtracted by characterCountLabel.widthProperty()</li>
     *    <li>Creates a binding for the layoutY property of characterCountLabel based on whether the maximum character count position is below or above the textField</li>
     *    <li>Binds the layoutY property of supportingTextLabel to textField.layoutYProperty() added by textField.heightProperty() plus 1</li>
     *    <li>Binds the layoutX property of supportingTextLabel to textField.layoutXProperty() added by 8</li>
     *    <li>Binds the layoutY property of floatingTextLabel to textField.layoutYProperty() added by textField.heightProperty() divided by 2 and subtracted by floatingTextLabel.heightProperty()
     *    divided by 2</li>
     *    <li>Adds a listener to the skinProperty of textField to set the currentSkin to the newSkin when it changes</li>
     *    <li>Binds the minWidth and maxWidth properties to textField.widthProperty()</li>
     *    <li>Creates a binding for the minHeight and maxHeight properties based on the maximum Y position of the textField, characterCountLabel, and supportingTextLabel</li>
     * </ul>
     */
    private void setLayout() {
        NumberBinding textFieldLayoutYBinding = getTextFieldLayoutYBinding();
        textField.layoutYProperty()
                 .bind(textFieldLayoutYBinding);

        characterCountLabel.layoutXProperty()
                           .bind(textField.widthProperty()
                                          .subtract(characterCountLabel.widthProperty()));

        DoubleBinding aboveBinding = Bindings.createDoubleBinding(() -> {
            double textFieldMinY = textField.getBoundsInParent()
                                            .getMinY();
            return textFieldMinY - characterCountLabel.getHeight() - 1;
        }, textField.layoutYProperty());

        DoubleBinding belowBinding = Bindings.createDoubleBinding(() -> {
            double textFieldMaxY = textField.getBoundsInParent()
                                            .getMaxY();
            return textFieldMaxY + 1;
        }, textField.layoutYProperty());

        characterCountLabel.layoutYProperty()
                           .bind(Bindings.when(isMaxCharacterCountPosBelow)
                                         .then(belowBinding)
                                         .otherwise(aboveBinding));

        supportingTextLabel.layoutYProperty()
                           .bind(textField.layoutYProperty()
                                          .add(textField.heightProperty()
                                                        .add(1)));
        supportingTextLabel.layoutXProperty()
                           .bind(textField.layoutXProperty()
                                          .add(8));

        floatingTextLabel.layoutYProperty()
                         .bind(textField.layoutYProperty()
                                        .add(textField.heightProperty()
                                                      .divide(2))
                                        .subtract(floatingTextLabel.heightProperty()
                                                                   .divide(2)));

        textField.skinProperty()
                 .addListener((obs, oldSkin, newSkin) -> currentSkin.set((TextFieldSkin) newSkin));

        minWidthProperty().bind(textField.widthProperty());
        maxWidthProperty().bind(textField.widthProperty());
        DoubleBinding heightBinding = Bindings.createDoubleBinding(this::calculateMaxY, textField.layoutYProperty(), characterCountLabel.layoutYProperty(), supportingTextLabel.layoutYProperty(),
                                                                   supportingTextLabel.visibleProperty(), characterCountLabel.visibleProperty());
        minHeightProperty().bind(heightBinding);
        maxHeightProperty().bind(heightBinding);
    }

    /**
     * Calculates the maximum Y position among the children of this element.
     *
     * @return The maximum Y position among the children.
     *
     * @throws IllegalArgumentException
     *         If the maximum Y position cannot be found for any child.
     */
    private double calculateMaxY() {
        return getChildren().stream()
                            .mapToDouble(c -> c.getBoundsInParent()
                                               .getMaxY())
                            .max()
                            .orElseThrow(() -> new IllegalArgumentException("MDTextField Max Y Position Not Found"));
    }

    /**
     * Returns a binding for the layout Y position of the text field.
     * <p>
     * The binding is determined based on the following conditions:
     * <ul>
     *     <li>If the height of the character count label is greater than the height of the floating text label, the binding will be the height of the floating text label.</li>
     *     <li>If the float mode is set to "above" or "border" and the height of the character count label is not greater than the height of the floating text label, the binding will be the height
     *     of the floating text label.</li>
     *     <li>If the maximum character count is enabled, the binding will be the height of the character count label.</li>
     *     <li>Otherwise, the binding will be 0.</li>
     * </ul>
     *
     * @return a NumberBinding for the layout Y position of the text field
     */
    private NumberBinding getTextFieldLayoutYBinding() {
        BooleanBinding charLabelHeightGreaterThanFloatLabel = Bindings.createBooleanBinding(this::isCharLabelHeightGreaterThanFloatLabel, characterCountLabel.heightProperty(),
                                                                                            floatingTextLabel.heightProperty());

        When.NumberConditionBuilder whenFloatAboveOrBorder = Bindings.when(isFloatModeAbove.or(isFloatModeBorder)
                                                                                           .and(charLabelHeightGreaterThanFloatLabel.not()))
                                                                     .then(floatingTextLabel.heightProperty());

        When.NumberConditionBuilder whenMaxCharCountEnabled = Bindings.when(isMaxCharacterCountEnabled)
                                                                      .then(characterCountLabel.heightProperty());
        return whenFloatAboveOrBorder.otherwise(whenMaxCharCountEnabled.otherwise(0));
    }

    /**
     * Sets up the style for the component. Adds the specified style class and stylesheet to the component's style class and stylesheets.
     */
    private void setupStyle() {
        this.getStyleClass()
            .add(STYLE);
        this.getStylesheets()
            .add(Stylesheets.MD_TEXT_FIELD.getStyleSheet());
    }

    /**
     * Updates the style of a text field by changing the text fill color to a new color.
     *
     * @param newColor
     *         the new color to be applied to the text field
     */
    private void updateStyleWithNewColor(Color newColor) {
        String baseStyle = textField.getStyle()
                                    .replaceAll("-fx-text-fill: #[0-9a-fA-F]+;", ""); //remove old color
        String newStyle = String.format("%s -fx-text-fill: #%s;", baseStyle, newColor.toString()
                                                                                     .substring(2)); //append new color
        textField.setStyle(newStyle); //set the new style
    }

    /**
     * Sets the font of a given label to a new font based on the old font.
     *
     * @param label
     *         The label whose font needs to be set.
     * @param oldFont
     *         The old font from which the new font should be derived.
     * @param newFont
     *         The new font to be set for the label.
     */
    private void setLabelFont(Label label, Font oldFont, Font newFont) {
        //@formatter:off
        label.setFont(Font.font(newFont.getFamily(),
                                FontWeight.findByName(oldFont.getStyle()),
                                FontPosture.findByName(oldFont.getStyle()),
                                newFont.getSize()));
        //@formatter:on
    }

    /**
     * Calculates the X position for the floating label layout.
     *
     * @return the X position as a Double value
     */
    private Double calculateFloatingLabelLayoutXPosition() {
        double minX = 0;
        if (Objects.nonNull(currentSkin.get())) {
            minX = currentSkin.get()
                              .getCharacterBounds(0)
                              .getMinX();
        }
        return minX + textField.getLayoutX() - floatingTextLabel.getPadding()
                                                                .getLeft();
    }

    /**
     * Calculates the target Y position for the floating label based on the current float mode.
     *
     * @return The target Y position for the floating label.
     */
    private double getFloatingLabelTargetY() {
        double textFieldMinY = textField.getBoundsInParent()
                                        .getMinY();
        double floatingTextLabelMinY = floatingTextLabel.getBoundsInParent()
                                                        .getMinY();
        double floatingLabelYOffset = floatingTextLabelMinY - textFieldMinY;

        return switch (getFloatMode()) {
            case ABOVE -> floatingLabelYOffset + floatingTextLabel.getHeight() + 1;
            case BORDER -> floatingLabelYOffset + floatingTextLabel.getHeight() / 2;
            case INSIDE -> floatingLabelYOffset;
            case DISABLED -> 0;
        };
    }

    /**
     * Applies animation state to the given Scale and Translate objects.
     *
     * @param scale
     *         the Scale object to apply animation state to.
     * @param translate
     *         the Translate object to apply animation state to.
     * @param scaleX
     *         the value to set for the X scale.
     * @param scaleY
     *         the value to set for the Y scale.
     * @param translateX
     *         the value to set for the X translation.
     * @param translateY
     *         the value to set for the Y translation.
     */
    private void applyAnimationState(Scale scale, Translate translate, double scaleX, double scaleY, double translateX, double translateY) {
        scale.setX(scaleX);
        scale.setY(scaleY);
        translate.setX(translateX);
        translate.setY(translateY);
    }

    /**
     * Sets up the styleable properties for the class. Each styleable property is created and initialized with default values. The method also defines the behaviors and restrictions associated with
     * each property.
     */
    private void setupStyleableProperties() {
        //@formatter:off
        maxCharacterCount = StyleableIntegerPropertyCreator.createStyleableIntegerProperty(50, "maxCharacterCount", this, STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-max-char-count"),
                                                                                           (prop, oldValue, oldValueSetter) -> {
                                                                                               int value = prop.get();
                                                                                               if (value < 0 || isMaxCharacterCountEnabled.not().get()) {
                                                                                                   if (prop.isBound()) {
                                                                                                       prop.unbind();
                                                                                                   }
                                                                                                   prop.set(oldValue);
                                                                                                   if(isMaxCharacterCountEnabled.not().get()) {
                                                                                                       throw new IllegalArgumentException("Cannot set character count if max character count is disabled");
                                                                                                   }
                                                                                                   throw new IllegalArgumentException("value %d cannot be negative.".formatted(value));
                                                                                               }
                                                                                               oldValueSetter.accept(value);  // update the old value
                                                                                           });

        maxCharCountState = SimpleStyleableObjectPropertyCreator.createSimpleStyleableObjectProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-max-char-count-state"), this,
                                                                                                     "maxCharacterCountEnabled", EnabledStatus.DISABLED,
                                                                                                     (prop) -> pseudoClassStateChanged(MAX_CHAR_COUNT_ENABLED_PSEUDO_CLASS,
                                                                                                                                       isMaxCharacterCountEnabled.get()));

        maxCharacterCountPosition = StyleableObjectPropertyCreator.createStyleableObjectProperty(MaxCharacterCountPosition.ABOVE, "maxCharacterCountPosition", this,
                                                                                                 STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-max-char-count-pos"),
                                                                                                 (prop, oldValue, oldValueSetter) -> {
                                                                                                    if(isMaxCharacterCountEnabled.not().get()) {
                                                                                                        if(prop.isBound()) {
                                                                                                            prop.unbind();
                                                                                                        }
                                                                                                        prop.set(oldValue);
                                                                                                        throw new IllegalArgumentException("Cannot set character count position if max character count is disabled");
                                                                                                    }
                                                                                                    pseudoClassStateChanged(MAX_CHAR_COUNT_POS_BELOW_PSEUDO_CLASS,
                                                                                                                            isMaxCharacterCountPosBelow.get());
                                                                                                 });

        supportingTextState = SimpleStyleableObjectPropertyCreator.createSimpleStyleableObjectProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-supporting-text-state"), this,
                                                                                                       "supportingTextState", EnabledStatus.DISABLED,
                                                                                                       (prop) -> pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS,
                                                                                                                                         isSupportingTextEnabled.get()));

        floatMode = SimpleStyleableObjectPropertyCreator.createSimpleStyleableObjectProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-float-mode"), this, "floatMode", FloatMode.DISABLED,
                                                                                             (prop) -> {
                                                                                                 FloatMode mode = prop.get();
                                                                                                 pseudoClassStateChanged(FLOAT_BORDER_MODE_PSEUDO_CLASS, isFloatModeBorder.get());
                                                                                                 pseudoClassStateChanged(FLOAT_INSIDE_MODE_PSEUDO_CLASS, isFloatModeInside.get());
                                                                                                 pseudoClassStateChanged(FLOAT_ABOVE_MODE_PSEUDO_CLASS, isFloatModeAbove.get());
                                                                                             });

        floatAnimationState = StyleableObjectPropertyCreator.createStyleableObjectProperty(EnabledStatus.ENABLED, "floatAnimationState", this,
                                                                                           STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-float-animation-state"),
                                                                                           (prop, oldValue, oldValueSetter) -> {
                                                                                               if(isFloatModeDisabled.get() || isTextModeFilled.and(isFloatModeBorder).get()) {
                                                                                                   if(prop.isBound()) {
                                                                                                       prop.unbind();
                                                                                                   }
                                                                                                   prop.set(oldValue);
                                                                                                   if(isFloatModeDisabled.get()) {
                                                                                                       throw new IllegalArgumentException("Float Mode Animation State cannot be set when Float Mode is Disabled");
                                                                                                   }
                                                                                                   throw new IllegalArgumentException("Float Mode cannot be set to border mode when the text field mode is filled");
                                                                                               }
                                                                                               pseudoClassStateChanged(FLOAT_ANIMATION_DISABLED_PSEUDO_CLASS, isFloatAnimationEnabled.not().get());
                                                                                               if(isFloatAnimationEnabled.get()) {
                                                                                                   applyCss();
                                                                                               }
                                                                                           });

        textFieldMode = StyleableObjectPropertyCreator.createStyleableObjectProperty(TextFieldMode.OUTLINED, "textFieldMode", this,
                                                                                     STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-text-field-mode"),
                                                                                     (prop, oldValue, oldValueSetter) -> {
                                                                                         TextFieldMode mode = prop.get();
                                                                                         if (isTextModeFilled.and(isFloatModeBorder).get()) {
                                                                                             if (prop.isBound()) {
                                                                                                 prop.unbind();
                                                                                             }
                                                                                             prop.setValue(oldValue);
                                                                                             throw new IllegalArgumentException("Text Field Mode cannot be set to filled mode when the float mode is border");
                                                                                         }
                                                                                         pseudoClassStateChanged(TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS, isTextModeFilled.get());
                                                                                         if (oldValue != mode) {
                                                                                             oldValueSetter.accept(mode);
                                                                                         }
                                                                                     });

        styleMode = SimpleStyleableObjectPropertyCreator.createSimpleStyleableObjectProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-style-mode"), this, "styleMode",
                                                                                             StyleMode.LIGHT,
                                                                                             (prop) -> {
                                                                                                 StyleMode mode = prop.get();
                                                                                                 pseudoClassStateChanged(STYLE_MODE_DARK_PSEUDO_CLASS, isStyleModeDark.get());
                                                                                                 pseudoClassStateChanged(STYLE_MODE_CUSTOM_PSEUDO_CLASS, isStyleModeCustom.get());
                                                                                             });

        textFill = SimpleStyleableObjectPropertyCreator.createSimpleStyleableObjectProperty(STYLEABLE_PROPERTIES_MANAGER.findCssMetaData("-fx-text-fill"), this, "textFill",
                                                                                            Color.valueOf("#000000"));

        //@formatter:on
    }

    //@formatter:off
    /**
     * Animates the floating text based on the given boolean value.
     *
     * <p>
     * @param shouldFloat a boolean value indicating whether the text should float or not
     *                    <br/>true - animate the text to float
     *                    <br/>false - animate the text to return to its original position
     * </p>
     */
    //@formatter:on
    public void animateFloatingText(boolean shouldFloat) {
        double targetY = getFloatingLabelTargetY();

        Timeline     timeline;
        Interpolator spline = Interpolator.SPLINE(0.0, 0.1, 0.25, 1.0);

        if (shouldFloat) {
            double translateX = floatingTextLabel.getPadding()
                                                 .getLeft() * scaleFactor - 1;
            timeline = new Timeline(new KeyFrame(Duration.millis(150), new KeyValue(scale.xProperty(), scaleFactor, spline)),
                                    new KeyFrame(Duration.millis(150), new KeyValue(scale.yProperty(), scaleFactor, spline)),
                                    new KeyFrame(Duration.millis(150), new KeyValue(translate.xProperty(), translateX, spline)),
                                    new KeyFrame(Duration.millis(150), new KeyValue(translate.yProperty(), -1 * targetY, spline)));

        } else {
            timeline = new Timeline(new KeyFrame(Duration.millis(150), new KeyValue(scale.xProperty(), 1, spline)), new KeyFrame(Duration.millis(150), new KeyValue(scale.yProperty(), 1, spline)),
                                    new KeyFrame(Duration.millis(150), new KeyValue(translate.xProperty(), 1, spline)),
                                    new KeyFrame(Duration.millis(150), new KeyValue(translate.yProperty(), 1, spline)));

        }
        timeline.play();
    }

    /**
     * Retrieves the maximum character count.
     *
     * @return the maximum character count.
     */
    public int getMaxCharacterCount() {
        return maxCharacterCount.get();
    }

    /**
     * Retrieves the maxCharacterCount property.
     *
     * @return the maxCharacterCount property as a StyleableIntegerProperty object.
     */
    public StyleableIntegerProperty maxCharacterCountProperty() {
        return maxCharacterCount;
    }

    /**
     * Sets the maximum character count for the object.
     *
     * @param maxCharacterCount the maximum character count to be set
     */
    public void setMaxCharacterCount(int maxCharacterCount) {
        this.maxCharacterCount.set(maxCharacterCount);
    }

    /**
     * This method checks if the height of a character count label is greater than the height of a floating text label.
     *
     * @return true if the height of the character count label is greater than the height of the floating text label, false otherwise.
     */
    private boolean isCharLabelHeightGreaterThanFloatLabel() {
        return characterCountLabel.getHeight() > floatingTextLabel.getHeight();
    }

    /**
     * Returns the maximum character count state.
     *
     * @return the maximum character count state.
     */
    public EnabledStatus getMaxCharCountState() {
        return maxCharCountState.get();
    }

    /**
     * Gets the maxCharCountStateProperty of this object.
     *
     * @return the maxCharCountStateProperty of this object.
     */
    public StyleableObjectProperty<EnabledStatus> maxCharCountStateProperty() {
        return maxCharCountState;
    }

    /**
     * Sets the maximum character count state.
     *
     * @param maxCharCountState the new maximum character count state
     */
    public void setMaxCharCountState(EnabledStatus maxCharCountState) {
        this.maxCharCountState.set(maxCharCountState);
    }

    /**
     * Retrieves the position of the maximum character count.
     *
     * @return The position of the maximum character count.
     */
    public MaxCharacterCountPosition getMaxCharacterCountPosition() {
        return maxCharacterCountPosition.get();
    }

    /**
     * Retrieves the maxCharacterCountPosition property.
     *
     * @return The maxCharacterCountPosition property.
     */
    public StyleableObjectProperty<MaxCharacterCountPosition> maxCharacterCountPositionProperty() {
        return maxCharacterCountPosition;
    }

    /**
     * Sets the maximum character count position.
     *
     * @param maxCharacterCountPosition the new maximum character count position
     */
    public void setMaxCharacterCountPosition(MaxCharacterCountPosition maxCharacterCountPosition) {
        this.maxCharacterCountPosition.set(maxCharacterCountPosition);
    }

    /**
     * Retrieves the current state of the supporting text.
     *
     * @return the current state of the supporting text
     */
    public EnabledStatus getSupportingTextState() {
        return supportingTextState.get();
    }

    /**
     * Gets the supporting text state property.
     *
     * @return the supporting text state property
     */
    public StyleableObjectProperty<EnabledStatus> supportingTextStateProperty() {
        return supportingTextState;
    }

    /**
     * Sets the state of the supporting text.
     *
     * @param supportingTextState The state of the supporting text.
     */
    public void setSupportingTextState(EnabledStatus supportingTextState) {
        this.supportingTextState.set(supportingTextState);
    }

    /**
     * Retrieves the float mode of the object.
     *
     * @return The FloatMode value representing the float mode.
     */
    public FloatMode getFloatMode() {
        return floatMode.get();
    }

    /**
     * Returns the StyleableObjectProperty representing the float mode.
     *
     * @return the StyleableObjectProperty<FloatMode> representing the float mode.
     */
    public StyleableObjectProperty<FloatMode> floatModeProperty() {
        return floatMode;
    }

    /**
     * Sets the float mode.
     *
     * @param floatMode the float mode to be set
     */
    public void setFloatMode(FloatMode floatMode) {
        this.floatMode.set(floatMode);
    }

    /**
     * Gets the state of the float animation.
     *
     * @return The state of the float animation.
     */
    public EnabledStatus getFloatAnimationState() {
        return floatAnimationState.get();
    }

    /**
     * Returns the styleable object property representing the float animation state.
     *
     * @return the styleable object property representing the float animation state
     */
    public StyleableObjectProperty<EnabledStatus> floatAnimationStateProperty() {
        return floatAnimationState;
    }

    /**
     * Sets the float animation state.
     *
     * @param floatAnimationState
     *         the enabled status of the float animation
     */
    public void setFloatAnimationState(EnabledStatus floatAnimationState) {
        this.floatAnimationState.set(floatAnimationState);
    }

    /**
     * Retrieves the current mode of the text field.
     *
     * @return The TextFieldMode representing the current mode of the text field.
     */
    public TextFieldMode getTextFieldMode() {
        return textFieldMode.get();
    }

    /**
     * Gets the text field mode property.
     *
     * @return the text field mode property
     */
    public StyleableObjectProperty<TextFieldMode> textFieldModeProperty() {
        return textFieldMode;
    }

    /**
     * Sets the mode of a text field.
     *
     * @param textFieldMode
     *         the mode to set the text field to
     */
    public void setTextFieldMode(TextFieldMode textFieldMode) {
        this.textFieldMode.set(textFieldMode);
    }

    /**
     * Retrieves the style mode.
     *
     * @return the style mode
     */
    public StyleMode getStyleMode() {
        return styleMode.get();
    }

    /**
     * Returns the property representing the style mode of the object.
     *
     * @return the style mode property
     */
    public StyleableObjectProperty<StyleMode> styleModeProperty() {
        return styleMode;
    }

    /**
     * Sets the style mode for the application.
     *
     * @param styleMode
     *         the style mode to be set
     */
    public void setStyleMode(StyleMode styleMode) {
        this.styleMode.set(styleMode);
    }

    /**
     * Retrieves the floating text associated with this object.
     *
     * @return the floating text
     */
    public String getFloatingText() {
        return floatingText.get();
    }

    /**
     * Returns the floating text property.
     *
     * @return the floating text property
     */
    public SimpleStringProperty floatingTextProperty() {
        return floatingText;
    }

    /**
     * Sets the floating text for this object.
     *
     * @param floatingText
     *         the text to set as floating text
     */
    public void setFloatingText(String floatingText) {
        this.floatingText.set(floatingText);
    }

    /**
     * Returns the supporting text.
     *
     * @return the supporting text
     */
    public String getSupportingText() {
        return supportingText.get();
    }

    /**
     * Returns the supporting text property.
     *
     * @return the supporting text property
     */
    public SimpleStringProperty supportingTextProperty() {
        return supportingText;
    }

    /**
     * Sets the supporting text for the object.
     *
     * @param supportingText
     *         the supporting text to be set
     */
    public void setSupportingText(String supportingText) {
        this.supportingText.set(supportingText);
    }

    /**
     * Gets the color of the text fill.
     *
     * @return the color of the text fill
     */
    public Color getTextFill() {
        return textFill.get();
    }

    /**
     * Returns the text fill property of this object.
     *
     * @return the text fill property
     */
    public StyleableObjectProperty<Color> textFillProperty() {
        return textFill;
    }

    /**
     * Sets the fill color of the text.
     *
     * @param textFill
     *         the color to set as the fill color of the text
     */
    public void setTextFill(Color textFill) {
        this.textFill.set(textFill);
    }

    /**
     * Retrieves the length of the text.
     *
     * @return The length of the text.
     */
    public int getTextLength() {
        return textLength.get();
    }

    /**
     * Returns a read-only integer property representing the length of the text.
     *
     * @return The read-only integer property representing the length of the text.
     */
    public ReadOnlyIntegerProperty textLengthProperty() {
        return textLength;
    }

    /**
     * Checks if the text field is enlarged.
     *
     * @return true if the text field is enlarged, false otherwise
     */
    public boolean isTextFieldEnlarged() {
        return isTextFieldEnlarged;
    }

    /**
     * Returns whether the text mode is filled or not.
     *
     * @return true if the text mode is filled, false otherwise.
     */
    public Boolean isTextModeFilled() {
        return isTextModeFilled.get();
    }

    /**
     * Returns the {@link BooleanBinding} property indicating whether the text mode is filled.
     *
     * @return the BooleanBinding property indicating whether the text mode is filled.
     */
    public BooleanBinding isTextModeFilledProperty() {
        return isTextModeFilled;
    }

    /**
     * Retrieves the value of the isFloatModeBorder property.
     *
     * @return the value of the isFloatModeBorder property, indicating whether the float mode border is enabled or disabled.
     */
    public Boolean isFloatModeBorder() {
        return isFloatModeBorder.get();
    }

    /**
     * Checks whether the float mode border property is enabled.
     *
     * @return {@code true} if the float mode border property is enabled, {@code false} otherwise.
     */
    public BooleanBinding isFloatModeBorderProperty() {
        return isFloatModeBorder;
    }

    /**
     * Determines if the float mode is currently inside.
     *
     * @return {@code true} if the float mode is inside, {@code false} otherwise.
     */
    public Boolean isFloatModeInside() {
        return isFloatModeInside.get();
    }

    /**
     * Returns the value of the isFloatModeInside property as a BooleanBinding.
     *
     * @return the value of the isFloatModeInside property as a BooleanBinding
     */
    public BooleanBinding isFloatModeInsideProperty() {
        return isFloatModeInside;
    }

    /**
     * Returns whether the float mode is above a certain value.
     *
     * @return true if the float mode is above the specified value, false otherwise.
     */
    public Boolean isFloatModeAbove() {
        return isFloatModeAbove.get();
    }

    /**
     * Returns the value of the {@code isFloatModeAbove} property.
     *
     * @return the value of the {@code isFloatModeAbove} property
     */
    public BooleanBinding isFloatModeAboveProperty() {
        return isFloatModeAbove;
    }

    /**
     * Returns the value indicating whether the float mode is disabled.
     *
     * @return {@code true} if the float mode is disabled, otherwise {@code false}.
     */
    public Boolean isFloatModeDisabled() {
        return isFloatModeDisabled.get();
    }

    /**
     * Returns a BooleanBinding object that represents the property for determining if float mode is disabled.
     *
     * @return a BooleanBinding object representing the property for determining if float mode is disabled
     */
    public BooleanBinding isFloatModeDisabledProperty() {
        return isFloatModeDisabled;
    }

    /**
     * Retrieves the value of the isMaxCharacterCountEnabled property.
     *
     * @return true if the maximum character count is enabled, false otherwise
     */
    public Boolean isMaxCharacterCountEnabled() {
        return isMaxCharacterCountEnabled.get();
    }

    /**
     * Returns a BooleanBinding representing the property indicating whether the maximum character count is enabled.
     *
     * @return the BooleanBinding for the max character count enabled property
     */
    public BooleanBinding isMaxCharacterCountEnabledProperty() {
        return isMaxCharacterCountEnabled;
    }

    /**
     * Returns the value of the isSupportingTextEnabled property.
     *
     * @return {@code true} if supporting text is enabled, {@code false} otherwise.
     */
    public Boolean isSupportingTextEnabled() {
        return isSupportingTextEnabled.get();
    }

    /**
     * Returns the property indicating whether the text is enabled or not.
     *
     * @return the property indicating whether the text is enabled or not
     */
    public BooleanBinding isSupportingTextEnabledProperty() {
        return isSupportingTextEnabled;
    }

    /**
     * Returns whether float animation is enabled.
     *
     * @return true if float animation is enabled, false otherwise.
     */
    public Boolean isFloatAnimationEnabled() {
        return isFloatAnimationEnabled.get();
    }

    /**
     * Retrieves the property that indicates whether the float animation is enabled.
     *
     * @return The BooleanBinding property representing the state of float animation enabled.
     */
    public BooleanBinding isFloatAnimationEnabledProperty() {
        return isFloatAnimationEnabled;
    }

    /**
     * Returns the value of the boolean property indicating whether the maximum character count position is below.
     *
     * @return {@code true} if the maximum character count position is below, otherwise {@code false}
     */
    public Boolean isMaxCharacterCountPosBelow() {
        return isMaxCharacterCountPosBelow.get();
    }

    /**
     * Returns a BooleanBinding indicating whether the maximum character count is positive and below a certain property value.
     *
     * @return a BooleanBinding representing whether the maximum character count is positive and below a certain property value.
     */
    public BooleanBinding isMaxCharacterCountPosBelowProperty() {
        return isMaxCharacterCountPosBelow;
    }

    /**
     * Returns the value of the isStyleModeDark property.
     *
     * @return true if the style mode is dark, otherwise false
     */
    public Boolean isStyleModeDark() {
        return isStyleModeDark.get();
    }

    /**
     * Returns a BooleanBinding indicating whether the style mode is set to dark.
     *
     * @return a BooleanBinding representing the style mode darkness: - {@code true} if the style mode is set to dark, - {@code false} otherwise.
     */
    public BooleanBinding isStyleModeDarkProperty() {
        return isStyleModeDark;
    }

    /**
     * Retrieves the value of the 'isStyleModeCustom' property.
     *
     * @return True if the style mode is custom, False otherwise.
     */
    public Boolean isStyleModeCustom() {
        return isStyleModeCustom.get();
    }

    /**
     * Returns a BooleanBinding representing the property for the style mode custom.
     *
     * @return a BooleanBinding representing the property for the style mode custom.
     */
    public BooleanBinding isStyleModeCustomProperty() {
        return isStyleModeCustom;
    }

    /**
     * Returns a boolean value indicating whether the text field is empty or not.
     *
     * @return true if the text field is empty, false otherwise
     */
    public Boolean isEmpty() {
        return isEmpty.get();
    }

    /**
     * Checks if the property isEmpty is true or false.
     *
     * @return The boolean binding that represents the value of the isEmpty property.
     */
    public BooleanBinding isEmptyProperty() {
        return isEmpty;
    }

    /* *************************************************************************
     *                                                                         *
     * TextField Methods                                                       *
     *                                                                         *
     **************************************************************************/

    /**
     * Returns the property for the preferred column count of the text field.
     *
     * @return the property for the preferred column count
     */
    public final IntegerProperty prefColumnCountProperty() {return textField.prefColumnCountProperty();}

    /**
     * Returns the preferred number of columns for the text field.
     *
     * @return The preferred number of columns for the text field.
     */
    public final int getPrefColumnCount() {return textField.getPrefColumnCount();}

    /**
     * Sets the preferred column count for the text field.
     *
     * @param value
     *         the number of columns to set
     */
    public final void setPrefColumnCount(int value) {textField.setPrefColumnCount(value);}

    /**
     * Returns the property for the onAction event handler.
     *
     * @return The ObjectProperty for the onAction event handler.
     */
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {return textField.onActionProperty();}

    /**
     * Returns the event handler for the "onAction" event.
     *
     * @return the event handler for the "onAction" event
     */
    public final EventHandler<ActionEvent> getOnAction() {return textField.getOnAction();}

    /**
     * Sets the handler to be invoked when the text field's action event occurs.
     *
     * @param value
     *         the event handler
     */
    public final void setOnAction(EventHandler<ActionEvent> value) {textField.setOnAction(value);}

    /**
     * Returns the property representing the alignment of the text in the MDTextField.
     *
     * @return the property representing the alignment of the text
     */
    public final ObjectProperty<Pos> alignmentProperty() {return textField.alignmentProperty();}

    /**
     * Sets the alignment of the text within the MDTextField.
     *
     * @param value
     *         the alignment to set for the text
     */
    public final void setAlignment(Pos value) {textField.setAlignment(value);}

    /**
     * Returns the alignment property of the text field.
     *
     * @return the alignment property of the text field
     */
    public final Pos getAlignment() {return textField.getAlignment();}

    /**
     * Retrieves the font property associated with the text field.
     *
     * @return the font property of the text field.
     */
    public final ObjectProperty<Font> fontProperty() {return textField.fontProperty();}

    /**
     * Sets the font of the text field.
     *
     * @param value
     *         the new font to set for the text field
     */
    public final void setFont(Font value) {textField.setFont(value);}

    /**
     * Gets the font of the text field.
     *
     * @return the font of the text field
     */
    public final Font getFont() {return textField.getFont();}

    /**
     * Returns the prompt text property of the text field.
     *
     * @return the prompt text property of the text field
     */
    public final StringProperty promptTextProperty() {return textField.promptTextProperty();}

    /**
     * Returns the prompt text of the text input field.
     *
     * @return the prompt text of the text input field
     */
    public final String getPromptText() {return textField.getPromptText();}

    /**
     * Sets the prompt text to be displayed in the text field.
     *
     * @param value
     *         the prompt text to be displayed
     */
    public final void setPromptText(String value) {textField.setPromptText(value);}

    /**
     * Gets the textFormatter property of the TextField control. The textFormatter property is used to format and validate the text entered into the TextField.
     *
     * @return The textFormatter property of the TextField control.
     */
    public final ObjectProperty<TextFormatter<?>> textFormatterProperty() {return textField.textFormatterProperty();}

    /**
     * Retrieves the text formatter used by the text field.
     *
     * @return the text formatter being used
     */
    public final TextFormatter<?> getTextFormatter() {return textField.getTextFormatter();}

    /**
     * Sets the text formatter for the text field.
     *
     * @param value
     *         the text formatter to set
     */
    public final void setTextFormatter(TextFormatter<?> value) {textField.setTextFormatter(value);}

    /**
     * Retrieves the text value from the text field.
     *
     * @return The text value from the text field.
     */
    public final String getText() {return textField.getText();}

    /**
     * Sets the text of the text field.
     *
     * @param value
     *         the text to be set for the text field
     */
    public final void setText(String value) {textField.setText(value);}

    /**
     * Returns the StringProperty associated with the text of the TextField.
     *
     * @return the StringProperty object representing the text of the TextField.
     */
    public final StringProperty textProperty() {return textField.textProperty();}

    /**
     * Gets the length of the text in the TextField.
     *
     * @return the length of the text
     */
    public final int getLength() {return textField.getLength();}

    /**
     * Retrieves the read-only integer property representing the length of the text in the textField.
     *
     * @return The read-only integer property representing the length of the text in the textField.
     */
    public final ReadOnlyIntegerProperty lengthProperty() {return textField.lengthProperty();}

    /**
     * Returns whether the text field is editable or not.
     *
     * @return true if the text field is editable, false otherwise
     */
    public final boolean isEditable() {return textField.isEditable();}

    /**
     * Sets whether the text field is editable or not.
     *
     * @param value
     *         true if the text field should be editable, false otherwise
     */
    public final void setEditable(boolean value) {textField.setEditable(value);}

    /**
     * Returns the editable property of the text field.
     *
     * @return the editable property of the text field
     */
    public final BooleanProperty editableProperty() {return textField.editableProperty();}

    /**
     * Returns the range of characters that are currently selected in the text field.
     *
     * @return The range of characters that are currently selected in the text field.
     */
    public final IndexRange getSelection() {return textField.getSelection();}

    /**
     * Returns the read-only object property representing the current selection range within the text field.
     *
     * @return the read-only object property representing the selection range
     */
    public final ReadOnlyObjectProperty<IndexRange> selectionProperty() {return textField.selectionProperty();}

    /**
     * Retrieves the selected text from a text field.
     *
     * @return The selected text in the text field.
     */
    public final String getSelectedText() {return textField.getSelectedText();}

    /**
     * Returns the read-only property that represents the currently selected text of the text field.
     *
     * @return the read-only property representing the currently selected text of the text field
     */
    public final ReadOnlyStringProperty selectedTextProperty() {return textField.selectedTextProperty();}

    /**
     * Returns the anchor value of the textField. The anchor value determines the position within the text field where new text is inserted.
     *
     * @return The anchor value of the textField, which represents the insertion position.
     */
    public final int getAnchor() {return textField.getAnchor();}

    /**
     * Returns a {@link ReadOnlyIntegerProperty} representing the anchor value of a text field.
     *
     * @return the anchor property of the text field
     */
    public final ReadOnlyIntegerProperty anchorProperty() {return textField.anchorProperty();}

    /**
     * Returns the current caret position in the text field.
     *
     * @return The current caret position.
     */
    public final int getCaretPosition() {return textField.getCaretPosition();}

    /**
     * Returns the read-only integer property representing the caret position in the text field. The caret position specifies the index of the character where the caret is placed.
     *
     * @return the read-only integer property representing the caret position
     */
    public final ReadOnlyIntegerProperty caretPositionProperty() {return textField.caretPositionProperty();}

    /**
     * Returns whether the current text field is undoable.
     *
     * @return {@code true} if the text field is undoable, {@code false} otherwise
     */
    public final boolean isUndoable() {return textField.isUndoable();}

    /**
     * Retrieves the read-only boolean property indicating whether an undo operation can be performed.
     *
     * @return The read-only boolean property indicating whether an undo operation can be performed.
     */
    public final ReadOnlyBooleanProperty undoableProperty() {return textField.undoableProperty();}

    /**
     * Checks if the text field supports redo operation.
     *
     * @return {@code true} if the text field supports redo operation, {@code false} otherwise.
     */
    public final boolean isRedoable() {return textField.isRedoable();}

    /**
     * Returns the read-only boolean property representing the redoable state of the underlying text field.
     *
     * @return the redoable property
     */
    public final ReadOnlyBooleanProperty redoableProperty() {return textField.redoableProperty();}

    /**
     * Retrieves the tooltip property of the text field.
     *
     * @return The ObjectProperty representing the tooltip.
     */
    public final ObjectProperty<Tooltip> tooltipProperty() {return textField.tooltipProperty();}

    /**
     * Sets the tooltip for the TextField.
     *
     * @param value
     *         the Tooltip to be set
     */
    public final void setTooltip(Tooltip value) {textField.setTooltip(value);}

    /**
     * Retrieves the tooltip associated with this method.
     *
     * @return the tooltip of the current text field
     */
    public final Tooltip getTooltip() {return textField.getTooltip();}

    /**
     * Returns the {@code ObjectProperty} representing the context menu associated with this method.
     *
     * @return the {@code ObjectProperty} representing the context menu associated with this method.
     */
    public final ObjectProperty<ContextMenu> contextMenuProperty() {return textField.contextMenuProperty();}

    /**
     * Sets the context menu for the text field.
     *
     * @param value
     *         the context menu to be set for the text field
     */
    public final void setContextMenu(ContextMenu value) {textField.setContextMenu(value);}

    /**
     * Returns the context menu associated with this method.
     *
     * @return the context menu
     */
    public final ContextMenu getContextMenu() {return textField.getContextMenu();}

    /**
     * Retrieves the text within the specified range from the text field.
     *
     * @param start
     *         the index of the first character to retrieve (inclusive)
     * @param end
     *         the index of the last character to retrieve (exclusive)
     *
     * @return the text within the specified range
     */
    public String getText(int start, int end) {return textField.getText(start, end);}

    /**
     * Appends the given text to the existing text in the text field.
     *
     * @param text
     *         the text to be appended to the text field
     */
    public void appendText(String text) {textField.appendText(text);}

    /**
     * Inserts the specified text at the given index in the text field.
     *
     * @param index
     *         the index at which the text is to be inserted
     * @param text
     *         the text to be inserted
     */
    public void insertText(int index, String text) {textField.insertText(index, text);}

    /**
     * Deletes the text within the specified range in a text field.
     *
     * @param range
     *         the range of text to be deleted
     */
    public void deleteText(IndexRange range) {textField.deleteText(range);}

    /**
     * Deletes the text in the specified range from the textField.
     *
     * @param start
     *         the starting index of the range (inclusive)
     * @param end
     *         the ending index of the range (exclusive)
     */
    public void deleteText(int start, int end) {textField.deleteText(start, end);}

    /**
     * Replaces the text within the specified range of the text field with the given text.
     *
     * @param range
     *         the range of text to be replaced
     * @param text
     *         the text to replace with
     */
    public void replaceText(IndexRange range, String text) {textField.replaceText(range, text);}

    /**
     * Replaces the text in the TextField from the specified start index to the specified end index with the given text.
     *
     * @param start
     *         the index of the first character to be replaced
     * @param end
     *         the index of the last character to be replaced (exclusive)
     * @param text
     *         the text to be inserted at the specified position
     */
    public void replaceText(final int start, final int end, final String text) {textField.replaceText(start, end, text);}

    /**
     * Cuts the currently selected text from the text field.
     */
    public void cut() {textField.cut();}

    /**
     * Copies the selected text in the text field to the system clipboard.
     */
    public void copy() {textField.copy();}

    /**
     * Pastes the current contents of the clipboard into the text field.
     */
    public void paste() {textField.paste();}

    /**
     * Selects the text in the text field starting from the current caret position and moving backwards. The selected text will be in the highlighted or highlighted in reverse order depending on the
     * selected theme. Calling this method will update the highlight of the text field.
     *
     * @throws UnsupportedOperationException
     *         if the text field does not support selecting text in reverse order.
     */
    public void selectBackward() {textField.selectBackward();}

    /**
     * Selects text in the forward direction from the current cursor position.
     */
    public void selectForward() {textField.selectForward();}

    /**
     * Moves the cursor to the previous word in the text field.
     */
    public void previousWord() {textField.previousWord();}

    /**
     * Moves the cursor to the next word in the text field.
     */
    public void nextWord() {textField.nextWord();}

    /**
     * Moves the cursor to the end of the next word in the text field. This method should be called when the user wants to move the cursor to the end of the next word in the text field. After calling
     * this method, the cursor will be positioned at the end of the next word.
     */
    public void endOfNextWord() {textField.endOfNextWord();}

    /**
     * Selects the previous word in the text field.
     */
    public void selectPreviousWord() {textField.selectPreviousWord();}

    /**
     * Selects the next word in the text field.
     */
    public void selectNextWord() {textField.selectNextWord();}

    /**
     * Selects the end of the next word in the text field.
     */
    public void selectEndOfNextWord() {textField.selectEndOfNextWord();}

    /**
     * Selects all the text in the text field.
     */
    public void selectAll() {textField.selectAll();}

    /**
     * Method to navigate back to the home page.
     */
    public void home() {textField.home();}

    /**
     * Ends the text input by moving the cursor to the end of the input field.
     */
    public void end() {textField.end();}

    /**
     * Selects the home position in the text field.
     */
    public void selectHome() {textField.selectHome();}

    /**
     * Selects the end of the text in the text field.
     */
    public void selectEnd() {textField.selectEnd();}

    /**
     * Deletes the previous character in the text field.
     *
     * @return true if the previous character was deleted, false otherwise.
     */
    public boolean deletePreviousChar() {return textField.deletePreviousChar();}

    /**
     * Deletes the next character in the text field.
     *
     * @return {@code true} if the next character was successfully deleted, {@code false} otherwise.
     */
    public boolean deleteNextChar() {return textField.deleteNextChar();}

    /**
     * This method moves the caret position of the associated text field one character forward. If the caret is already at the end of the text, this method has no effect.
     */
    public void forward() {textField.forward();}

    /**
     * Moves the cursor in the text field one position backwards.
     */
    public void backward() {textField.backward();}

    /**
     * Positions the caret at the specified position in the text field.
     *
     * @param pos
     *         the position to place the caret at
     */
    public void positionCaret(int pos) {textField.positionCaret(pos);}

    /**
     * Selects the position caret in the text field.
     *
     * @param pos
     *         the desired position to set the caret to
     */
    public void selectPositionCaret(int pos) {textField.selectPositionCaret(pos);}

    /**
     * Method to select range of text in the text field.
     *
     * @param anchor
     *         the starting position of the text range to be selected
     * @param caretPosition
     *         the ending position of the text range to be selected
     */
    public void selectRange(int anchor, int caretPosition) {textField.selectRange(anchor, caretPosition);}

    /**
     * Extend the selection in the text field from the current caret position to the specified position.
     *
     * @param pos
     *         the position to extend the selection to
     */
    public void extendSelection(int pos) {textField.extendSelection(pos);}

    /**
     * Clears the contents of the text field.
     */
    public void clear() {textField.clear();}

    /**
     * Deselects any selected text in the text field.
     */
    public void deselect() {textField.deselect();}

    /**
     * Replaces the currently selected text in the text field with the given replacement text.
     *
     * @param replacement
     *         the text to replace the currently selected text with
     */
    public void replaceSelection(String replacement) {textField.replaceSelection(replacement);}

    /**
     * Undoes the last action performed in the text field. This method calls the `undo()` method of the `textField` object to reverse the last action made by the user.
     * <p>
     * Note: The `undo()` method is specific to the `textField` object used in the implementation and may behave differently depending on the context and implementation details.
     */
    public final void undo() {textField.undo();}

    /**
     * Redoes the last action undone in the text field.
     */
    public final void redo() {textField.redo();}

    /**
     * Commits the value entered in the text field.
     */
    public final void commitValue() {textField.commitValue();}

    /**
     * Cancels the current edit operation in the text field.
     */
    public final void cancelEdit() {textField.cancelEdit();}

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {return STYLEABLE_PROPERTIES_MANAGER.getCssMetaDataList();}
}
