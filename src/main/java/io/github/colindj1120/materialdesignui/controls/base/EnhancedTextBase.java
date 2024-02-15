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
package io.github.colindj1120.materialdesignui.controls.base;

import io.github.colindj1120.materialdesignui.controls.EnhancedTextField;
import io.github.colindj1120.materialdesignui.enums.Status;
import io.github.colindj1120.materialdesignui.enums.controls.StyleMode;
import io.github.colindj1120.materialdesignui.enums.controls.enhancedtextfield.MaxCharacterCountPosition;
import io.github.colindj1120.materialdesignui.factories.styling.CssFactory;
import io.github.colindj1120.materialdesignui.factories.styling.StyleableIntegerPropertyFactory;
import io.github.colindj1120.materialdesignui.factories.styling.StyleableObjectPropertyFactory;
import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesManager;
import io.github.colindj1120.materialdesignui.styling.Stylesheets;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.*;
import javafx.css.converter.ColorConverter;
import javafx.css.converter.EnumConverter;
import javafx.css.converter.SizeConverter;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.function.Consumer;

import static io.github.colindj1120.materialdesignui.utils.PropertyUtils.checkProperty;

/**
 * Abstract base class for enhanced text input controls within a Material Design UI framework. This class extends {@link Control} and introduces additional properties and styling capabilities to
 * support features such as supporting text, maximum character count, custom text and prompt text colors, and various style modes. It leverages JavaFX CSS and pseudo-classes to allow for dynamic
 * styling and theming.
 *
 * <p>
 * Key features include:
 * <ul>
 *     <li>Support for dark and custom style modes through pseudo-classes.</li>
 *     <li>Ability to define maximum character count with position customization.</li>
 *     <li>Dynamic styling of text and prompt text colors based on CSS properties.</li>
 *     <li>Integration of supporting text with enabled/disabled states for additional information display.</li>
 *     <li>Use of BooleanBindings to reactively enable or disable features based on the component's state.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Subclasses are expected to provide implementations for specific text input behaviors, such as retrieving
 * the {@link TextInputControl} associated with this control and managing the empty state of the text input.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 * @see TextInputControl
 */
public abstract class EnhancedTextBase extends Control {
    private static final   StyleablePropertiesManager STYLES_MANAGER                             = new StyleablePropertiesManager(Control.getClassCssMetaData());
    protected static final PseudoClass                STYLE_MODE_DARK_PSEUDO_CLASS               = PseudoClass.getPseudoClass("style-mode-dark");
    protected static final PseudoClass                STYLE_MODE_CUSTOM_PSEUDO_CLASS             = PseudoClass.getPseudoClass("style-mode-custom");
    protected static final PseudoClass                MAX_CHARACTER_COUNT_POS_BELOW_PSEUDO_CLASS = PseudoClass.getPseudoClass("max-character-count-pos-below");
    protected static final PseudoClass                SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS       = PseudoClass.getPseudoClass("supporting-text-enabled");

    protected final SimpleStringProperty supportingText = new SimpleStringProperty(this, "supportingText", "");

    protected StyleableObjectProperty<Status>                    supportingTextState;
    protected StyleableIntegerProperty                           maxCharacterCount;
    protected StyleableObjectProperty<Status>                    maxCharCountState;
    protected StyleableObjectProperty<MaxCharacterCountPosition> maxCharacterCountPosition;
    protected StyleableObjectProperty<StyleMode>                 styleMode;
    protected StyleableObjectProperty<Color>                     textFill;
    protected StyleableObjectProperty<Color>                     promptTextFill;

    protected BooleanBinding isStyleModeDark;
    protected BooleanBinding isStyleModeCustom;
    protected BooleanBinding isSupportingTextEnabled;
    protected BooleanBinding isMaxCharacterCountEnabled;
    protected BooleanBinding isMaxCharacterCountPosBelow;

    static {
        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, Color>().property("-fx-text-fill")
                                                                                .converter(ColorConverter.getInstance())
                                                                                .initialValue(Color.valueOf("#000000"))
                                                                                .isSettableFunction(node -> checkProperty(node.textFill))
                                                                                .propertyGetterFunction(node -> node.textFill));

        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, Color>().property("-fx-prompt-text-fill")
                                                                                .converter(ColorConverter.getInstance())
                                                                                .initialValue(Color.valueOf("#000000"))
                                                                                .isSettableFunction(node -> checkProperty(node.promptTextFill))
                                                                                .propertyGetterFunction(node -> node.promptTextFill));

        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, Status>().property("-max-char-count-state")
                                                                                 .converter(EnumConverter.getEnumConverter(Status.class))
                                                                                 .initialValue(Status.DISABLED)
                                                                                 .isSettableFunction(node -> checkProperty(node.maxCharCountState))
                                                                                 .propertyGetterFunction(node -> node.maxCharCountState));

        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, Number>().property("-max-char-count")
                                                                                 .converter(SizeConverter.getInstance())
                                                                                 .initialValue(50)
                                                                                 .isSettableFunction(node -> checkProperty(node.maxCharacterCount) && node.isMaxCharacterCountEnabled())
                                                                                 .propertyGetterFunction(node -> node.maxCharacterCount));

        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, MaxCharacterCountPosition>().property("-max-char-count-pos")
                                                                                                    .converter(EnumConverter.getEnumConverter(MaxCharacterCountPosition.class))
                                                                                                    .initialValue(MaxCharacterCountPosition.ABOVE)
                                                                                                    .isSettableFunction(
                                                                                                            node -> checkProperty(node.maxCharacterCountPosition) && node.isMaxCharacterCountEnabled())
                                                                                                    .propertyGetterFunction(node -> node.maxCharacterCountPosition));

        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, Status>().property("-supporting-text-state")
                                                                                 .converter(EnumConverter.getEnumConverter(Status.class))
                                                                                 .initialValue(Status.DISABLED)
                                                                                 .isSettableFunction(node -> checkProperty(node.supportingTextState))
                                                                                 .propertyGetterFunction(node -> node.supportingTextState));

        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, StyleMode>().property("-style-mode")
                                                                                    .converter(EnumConverter.getEnumConverter(StyleMode.class))
                                                                                    .initialValue(StyleMode.LIGHT)
                                                                                    .isSettableFunction(node -> checkProperty(node.styleMode))
                                                                                    .propertyGetterFunction(node -> node.styleMode));

    }

    /**
     * Constructs an instance of {@code EnhancedTextBase}, initializing the control with enhanced styling, property bindings, and listeners specific to Material Design UI requirements. This
     * constructor orchestrates several setup methods to fully prepare the control for use.
     * <p>
     * The initialization process involves:
     * <ul>
     *     <li>Applying the base style class and loading the associated stylesheet.</li>
     *     <li>Initializing styleable properties with default values and CSS metadata.</li>
     *     <li>Setting up Boolean bindings to dynamically respond to property changes.</li>
     *     <li>Registering listeners for property changes to update the UI accordingly.</li>
     *     <li>Updating the pseudo-class states based on the initial properties to correctly reflect
     *         the control's visual state.</li>
     * </ul>
     * </p>
     *
     * <p>
     * This comprehensive initialization ensures that the control is fully configured and ready to
     * display with the appropriate Material Design styling and behavior.
     * </p>
     */
    public EnhancedTextBase() {
        super();
        setupStyle();
        initializeStyleableProperties();
        setupBooleanBindings();
        setupListeners();
        updatePseudoClassStates();
    }

    /**
     * Returns the TextInputControl associated with this object.
     *
     * @return the TextInputControl
     */
    public abstract TextInputControl getTextControl();

    /**
     * Checks if the given object is empty.
     *
     * @return true if the object is empty, false otherwise
     */
    public abstract Boolean isEmpty();

    /**
     * Checks if the property is empty.
     *
     * @return A BooleanBinding that represents whether the property is empty or not.
     */
    public abstract BooleanBinding isEmptyProperty();

    /**
     * Sets up the style for the EnhancedTextBase class. The method adds the "enhanced-text-base" style class to the CSS classes of the text field, and adds the "enhanced-text-base.css" stylesheet to
     * the list of stylesheets.
     */
    private void setupStyle() {
        getStyleClass().add("enhanced-text-base");
        getStylesheets().add(Stylesheets.ENHANCED_TEXT_BASE.getStyleSheet());
    }

    /**
     * Sets up the boolean bindings for various properties. These bindings depend on the state of certain properties and are used to dynamically update the boolean values.
     * <p>
     * For the 'isMaxCharacterCountEnabled' property: The binding is created with a lambda expression that checks if the 'maxCharCountState' property is set to 'Status.ENABLED'. The
     * 'maxCharCountState' property represents the operational status of the maximum character count feature.
     * <p>
     * For the 'isSupportingTextEnabled' property: The binding is created with a lambda expression that checks if the 'supportingTextState' property is set to 'Status.ENABLED'. The
     * 'supportingTextState' property represents the operational status of the supporting text feature.
     * <p>
     * For the 'isMaxCharacterCountPosBelow' property: The binding is created with a lambda expression that checks if the 'maxCharacterCountPosition' property is set to
     * 'MaxCharacterCountPosition.BELOW'. The 'maxCharacterCountPosition' property represents the position of the maximum character count feature (either below or above the text).
     * <p>
     * For the 'isStyleModeDark' property: The binding is created with a lambda expression that checks if the 'styleMode' property is set to 'StyleMode.DARK'. The 'styleMode' property represents the
     * current style mode of the component.
     * <p>
     * For the 'isStyleModeCustom' property: The binding is created with a lambda expression that checks if the 'styleMode' property is set to 'StyleMode.CUSTOM'. The 'styleMode' property represents
     * the current style mode of the component.
     * <p>
     * These bindings are used to dynamically update the boolean values of corresponding properties in the class 'EnhancedTextBase'.
     */
    private void setupBooleanBindings() {
        isMaxCharacterCountEnabled  = Bindings.createBooleanBinding(() -> getMaxCharCountState() == Status.ENABLED, maxCharCountState);
        isSupportingTextEnabled     = Bindings.createBooleanBinding(() -> getSupportingTextState() == Status.ENABLED, supportingTextState);
        isMaxCharacterCountPosBelow = Bindings.createBooleanBinding(() -> getMaxCharacterCountPosition() == MaxCharacterCountPosition.BELOW, maxCharacterCountPosition);
        isStyleModeDark             = Bindings.createBooleanBinding(() -> getStyleMode() == StyleMode.DARK, styleMode);
        isStyleModeCustom           = Bindings.createBooleanBinding(() -> getStyleMode() == StyleMode.CUSTOM, styleMode);
    }

    /**
     * Sets up the listeners for the properties related to text and prompt text colors.
     */
    private void setupListeners() {
        textFill.addListener((obs, oldColor, newColor) -> updateTextWithNewColor(newColor));
        promptTextFill.addListener((obs, oldColor, newColor) -> updatePromptTextWithNewColor(newColor));
    }

    /**
     * Updates the style of a text field by changing the text fill color to a new color.
     *
     * @param newColor
     *         the new color to be applied to the text field
     */
    private void updateTextWithNewColor(Color newColor) {
        Node node = getTextControl();
        String baseStyle = node.getStyle()
                               .replaceAll("-fx-text-fill: #[0-9a-fA-F]+;", ""); //remove old color
        String newStyle = String.format("%s -fx-text-fill: #%s;", baseStyle, newColor.toString()
                                                                                     .substring(2)); //append new color
        node.setStyle(newStyle); //set the new style
    }

    /**
     * Updates the prompt text color of a text field with a new color.
     *
     * @param newColor
     *         The new color to be applied to the prompt text
     */
    private void updatePromptTextWithNewColor(Color newColor) {
        Node node = getTextControl();
        String baseStyle = node.getStyle()
                               .replaceAll("-fx-prompt-text-fill: #[0-9a-fA-F]+;", ""); //remove old color
        String newStyle = String.format("%s -fx-prompt-text-fill: #%s;", baseStyle, newColor.toString()
                                                                                            .substring(2)); //append new color
        node.setStyle(newStyle); //set the new style
    }

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Determines whether the style mode is dark.
     *
     * @return true if the style mode is dark, false otherwise.
     */
    public Boolean isStyleModeDark() {
        return isStyleModeDark.get();
    }

    /**
     * Returns the BooleanBinding that represents the style mode dark property.
     *
     * @return the BooleanBinding that represents the style mode dark property
     */
    public BooleanBinding isStyleModeDarkProperty() {
        return isStyleModeDark;
    }

    /**
     * Checks if the style mode is set to custom.
     *
     * @return True if the style mode is set to custom, False otherwise.
     */
    public Boolean isStyleModeCustom() {
        return isStyleModeCustom.get();
    }

    /**
     * Returns a BooleanBinding indicating whether the style mode is a custom property.
     *
     * @return a BooleanBinding indicating whether the style mode is a custom property
     */
    public BooleanBinding isStyleModeCustomProperty() {
        return isStyleModeCustom;
    }

    /**
     * Retrieves the current style mode.
     *
     * @return The current StyleMode.
     */
    public StyleMode getStyleMode() {
        return styleMode.get();
    }

    /**
     * Returns the style mode property of this object.
     *
     * @return the StyleableObjectProperty representing the style mode
     */
    public StyleableObjectProperty<StyleMode> styleModeProperty() {
        return styleMode;
    }

    /**
     * Sets the style mode for the object.
     *
     * @param styleMode the style mode to set
     */
    public void setStyleMode(StyleMode styleMode) {
        this.styleMode.set(styleMode);
    }

    /**
     * Returns whether the supporting text is enabled.
     *
     * @return true if the supporting text is enabled, false otherwise
     */
    public Boolean isSupportingTextEnabled() {
        return isSupportingTextEnabled.get();
    }

    /**
     * Checks if the Supporting Text Enabled property is currently enabled or disabled.
     *
     * @return {@code true} if the Supporting Text Enabled property is enabled, {@code false} otherwise.
     */
    public BooleanBinding isSupportingTextEnabledProperty() {
        return isSupportingTextEnabled;
    }

    /**
     * Retrieves the state of the supporting text.
     *
     * @return the state of the supporting text
     */
    public Status getSupportingTextState() {
        return supportingTextState.get();
    }

    /**
     * Retrieves the supporting text state property.
     *
     * @return the supporting text state property
     */
    public StyleableObjectProperty<Status> supportingTextStateProperty() {
        return supportingTextState;
    }

    /**
     * Sets the state of the supporting text.
     *
     * @param supportingTextState the state of the supporting text
     */
    public void setSupportingTextState(Status supportingTextState) {
        this.supportingTextState.set(supportingTextState);
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
     * Checks if the max character count is enabled.
     *
     * @return {@code true} if the max character count is enabled, {@code false} otherwise.
     */
    public Boolean isMaxCharacterCountEnabled() {
        return isMaxCharacterCountEnabled.get();
    }

    /**
     * Returns a BooleanBinding indicating whether the max character count is enabled.
     *
     * @return a BooleanBinding representing the max character count enabled property.
     */
    public BooleanBinding isMaxCharacterCountEnabledProperty() {
        return isMaxCharacterCountEnabled;
    }

    /**
     * Determines if the maximum character count position is below a certain value.
     *
     * @return true if the maximum character count position is below the value, otherwise false.
     */
    public Boolean isMaxCharacterCountPosBelow() {
        return isMaxCharacterCountPosBelow.get();
    }

    /**
     * Returns a BooleanBinding indicating whether the maximum character count position is below the specified property.
     *
     * @return a BooleanBinding that represents the state of the maximum character count position relative to the specified property.
     */
    public BooleanBinding isMaxCharacterCountPosBelowProperty() {
        return isMaxCharacterCountPosBelow;
    }

    /**
     * Retrieves the maximum character count.
     *
     * @return the maximum character count
     */
    public int getMaxCharacterCount() {
        return maxCharacterCount.get();
    }

    /**
     * Retrieves the maximum character count property.
     *
     * @return the maximum character count property
     */
    public StyleableIntegerProperty maxCharacterCountProperty() {
        return maxCharacterCount;
    }

    /**
     * Sets the maximum character count.
     *
     * @param maxCharacterCount the maximum character count to be set
     */
    public void setMaxCharacterCount(int maxCharacterCount) {
        this.maxCharacterCount.set(maxCharacterCount);
    }

    /**
     * Retrieves the maximum character count state.
     *
     * @return the maximum character count state
     */
    public Status getMaxCharCountState() {
        return maxCharCountState.get();
    }

    /**
     * Returns the maxCharCountState property.
     *
     * @return the maxCharCountState property.
     */
    public StyleableObjectProperty<Status> maxCharCountStateProperty() {
        return maxCharCountState;
    }

    /**
     * Sets the state of the maximum character count.
     *
     * @param maxCharCountState the state of the maximum character count
     */
    public void setMaxCharCountState(Status maxCharCountState) {
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
     * Retrieves the styleable object property for the maximum character count position.
     *
     * @return The styleable object property for the maximum character count position.
     */
    public StyleableObjectProperty<MaxCharacterCountPosition> maxCharacterCountPositionProperty() {
        return maxCharacterCountPosition;
    }

    /**
     * Sets the position of the maximum character count.
     *
     * @param maxCharacterCountPosition the position of the maximum character count
     */
    public void setMaxCharacterCountPosition(MaxCharacterCountPosition maxCharacterCountPosition) {
        this.maxCharacterCountPosition.set(maxCharacterCountPosition);
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
     * Returns the text fills property of this object.
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
     * Returns the prompt text fill color.
     *
     * @return The prompt text fill color.
     */
    public Color getPromptTextFill() {
        return promptTextFill.get();
    }

    /**
     * Returns the styleable property object that represents the fill color of the prompt text.
     *
     * @return The styleable property object that represents the fill color of the prompt text.
     */
    public StyleableObjectProperty<Color> promptTextFillProperty() {
        return promptTextFill;
    }

    /**
     * Sets the fill color of the prompt text in this TextField.
     *
     * @param promptTextFill
     *         the color to set as the fill color of the prompt text
     */
    public void setPromptTextFill(Color promptTextFill) {
        this.promptTextFill.set(promptTextFill);
    }

    //endregion Getters and Setters

    //region StyleableProperties and PseudoClass
    //*****************************************************************
    // StyleableProperties and PseudoClass
    //*****************************************************************

    /**
     * Updates the pseudo-class states of the text field based on the current state properties.
     *
     * <p>This method is used internally to dynamically update the appearance of the text field based on changes in its state properties.</p>
     *
     * <p>Note: This method does not return any value.</p>
     *
     * <p>It updates the following pseudo-classes:</p>
     * <ul>
     *     <li>TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isTextModeFilled' property.</li>
     *     <li>FLOAT_BORDER_MODE_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isFloatModeBorder' property.</li>
     *     <li>FLOAT_INSIDE_MODE_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isFloatModeInside' property.</li>
     *     <li>FLOAT_ABOVE_MODE_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isFloatModeAbove' property.</li>
     * </ul>
     */
    private void updatePseudoClassStates() {
        pseudoClassStateChanged(STYLE_MODE_DARK_PSEUDO_CLASS, isStyleModeDark.get());
        pseudoClassStateChanged(STYLE_MODE_CUSTOM_PSEUDO_CLASS, isStyleModeCustom.get());
        pseudoClassStateChanged(MAX_CHARACTER_COUNT_POS_BELOW_PSEUDO_CLASS, isMaxCharacterCountPosBelow.get());
        pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS, isSupportingTextEnabled.get());
    }

    private void initializeStyleableProperties() {
        supportingTextState = StyleableObjectPropertyFactory.<Status>builder()
                                                            .bean(this)
                                                            .name("supportingTextState")
                                                            .cssMetaData(STYLES_MANAGER.findCssMetaData("-supporting-text-state"))
                                                            .defaultValue(Status.DISABLED)
                                                            .invalidatedPropCallback(this::supportingTextStateInvalidated)
                                                            .build();

        maxCharacterCount = StyleableIntegerPropertyFactory.builder()
                                                           .bean(this)
                                                           .name("maxCharacterCount")
                                                           .cssMetaData(STYLES_MANAGER.findCssMetaData("-max-char-count"))
                                                           .defaultValue(50)
                                                           .invalidatedCachedCallback(this::maxCharacterCountInvalidated)
                                                           .build();

        maxCharCountState = StyleableObjectPropertyFactory.<Status>builder()
                                                          .bean(this)
                                                          .name("maxCharacterCountEnabled")
                                                          .defaultValue(Status.DISABLED)
                                                          .build();

        maxCharacterCountPosition = StyleableObjectPropertyFactory.<MaxCharacterCountPosition>builder()
                                                                  .bean(this)
                                                                  .name("maxCharacterCountPosition")
                                                                  .cssMetaData(STYLES_MANAGER.findCssMetaData("-max-char-count-pos"))
                                                                  .defaultValue(MaxCharacterCountPosition.ABOVE)
                                                                  .invalidatedCachedCallback(this::maxCharacterCountPositionInvalidated)
                                                                  .build();

        styleMode = StyleableObjectPropertyFactory.<StyleMode>builder()
                                                  .bean(this)
                                                  .name("styleMode")
                                                  .cssMetaData(STYLES_MANAGER.findCssMetaData("-style-mode"))
                                                  .defaultValue(StyleMode.LIGHT)
                                                  .invalidatedPropCallback(this::styleModeInvalidated)
                                                  .build();

        textFill = StyleableObjectPropertyFactory.<Color>builder()
                                                 .bean(this)
                                                 .name("textFill")
                                                 .cssMetaData(STYLES_MANAGER.findCssMetaData("-fx-text-fill"))
                                                 .defaultValue(Color.valueOf("#000000"))
                                                 .build();

        promptTextFill = StyleableObjectPropertyFactory.<Color>builder()
                                                       .bean(this)
                                                       .name("promptTextFill")
                                                       .cssMetaData(STYLES_MANAGER.findCssMetaData("-fx-prompt-text-fill"))
                                                       .defaultValue(Color.valueOf("#000000"))
                                                       .build();
    }

    /**
     * Invalidates the supporting text state of a styleable object property and updates the pseudo-class states of the text field accordingly.
     *
     * @param prop
     *         the styleable object property representing the supporting text state
     */
    public void supportingTextStateInvalidated(StyleableObjectProperty<Status> prop) {
        Status status = prop.get();
        pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS, status == Status.ENABLED);
    }

    /**
     * Invalidates the maximum character count position of a styleable object property.
     *
     * @param prop
     *         the styleable object property representing the maximum character count position
     * @param oldValue
     *         the old value of the maximum character count position
     * @param oldValueSetter
     *         the consumer to accept the old value of the property
     *
     * @throws IllegalArgumentException
     *         if the maximum character count is disabled and the position is attempted to be set
     */
    protected void maxCharacterCountPositionInvalidated(StyleableObjectProperty<MaxCharacterCountPosition> prop, MaxCharacterCountPosition oldValue,
                                                        Consumer<MaxCharacterCountPosition> oldValueSetter) {
        if (!isMaxCharacterCountEnabled()) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.set(oldValue);
            throw new IllegalArgumentException("Cannot set character count position if max character count is disabled");
        }
        oldValueSetter.accept(prop.get());
    }

    protected void maxCharacterCountInvalidated(StyleableIntegerProperty prop, Integer oldValue, Consumer<Integer> oldValueSetter) {
        int value = prop.get();
        if (value < 0 || !isMaxCharacterCountEnabled()) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.set(oldValue);
            if (!isMaxCharacterCountEnabled()) {
                throw new IllegalArgumentException("Cannot set character count if max character count is disabled");
            }
            throw new IllegalArgumentException("value %d cannot be negative.".formatted(value));
        }
        oldValueSetter.accept(value);  // update the old value
    }

    protected void styleModeInvalidated(StyleableObjectProperty<StyleMode> prop) {
        StyleMode mode = prop.get();

        pseudoClassStateChanged(STYLE_MODE_DARK_PSEUDO_CLASS, mode == StyleMode.DARK);
        pseudoClassStateChanged(STYLE_MODE_CUSTOM_PSEUDO_CLASS, mode == StyleMode.CUSTOM);
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return STYLES_MANAGER.getCssMetaDataList();
    }

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {return getClassCssMetaData();}

    //endregion StyleableProperties and PseudoClass
}
