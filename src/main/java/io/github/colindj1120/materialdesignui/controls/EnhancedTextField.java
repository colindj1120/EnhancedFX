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

import io.github.colindj1120.materialdesignui.controls.base.innertext.InnerTextField;
import io.github.colindj1120.materialdesignui.factories.styling.StyleableIntegerPropertyFactory;
import io.github.colindj1120.materialdesignui.factories.styling.StyleableObjectPropertyFactory;
import io.github.colindj1120.materialdesignui.skins.EnhancedTextFieldSkin;
import io.github.colindj1120.materialdesignui.enums.Status;
import io.github.colindj1120.materialdesignui.enums.controls.StyleMode;
import io.github.colindj1120.materialdesignui.enums.controls.enhancedtextfield.FloatMode;
import io.github.colindj1120.materialdesignui.enums.controls.enhancedtextfield.MaxCharacterCountPosition;
import io.github.colindj1120.materialdesignui.enums.controls.enhancedtextfield.TextFieldMode;
import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesManager;
import io.github.colindj1120.materialdesignui.factories.styling.CssFactory;
import io.github.colindj1120.materialdesignui.styling.Stylesheets;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.*;
import javafx.css.converter.ColorConverter;
import javafx.css.converter.EnumConverter;
import javafx.css.converter.SizeConverter;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.function.Consumer;

import static io.github.colindj1120.materialdesignui.utils.PropertyUtils.checkProperty;

public class EnhancedTextField extends Control implements InnerTextField<TextField> {
    protected static final StyleablePropertiesManager STYLES_MANAGER                      = new StyleablePropertiesManager(Control.getClassCssMetaData());
    protected static final PseudoClass                STYLE_MODE_DARK_PSEUDO_CLASS        = PseudoClass.getPseudoClass("style-mode-dark");
    protected static final PseudoClass                STYLE_MODE_CUSTOM_PSEUDO_CLASS      = PseudoClass.getPseudoClass("style-mode-custom");
    protected static final PseudoClass                FLOAT_BORDER_MODE_PSEUDO_CLASS      = PseudoClass.getPseudoClass("float-border-mode");
    protected static final PseudoClass                FLOAT_ABOVE_MODE_PSEUDO_CLASS       = PseudoClass.getPseudoClass("float-above-mode");
    protected static final PseudoClass                FLOAT_INSIDE_MODE_PSEUDO_CLASS      = PseudoClass.getPseudoClass("float-above-mode");
    protected static final PseudoClass                TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS = PseudoClass.getPseudoClass("filled-mode");

    protected final TextField             innerField     = new TextField();
    protected final ObjectProperty<Node>  leadingIcon    = new SimpleObjectProperty<>(this, "leadingIcon");
    protected final ObjectProperty<Node>  trailingIcon   = new SimpleObjectProperty<>(this, "trailingIcon");
    protected final SimpleStringProperty  supportingText = new SimpleStringProperty(this, "supportingText", "");
    protected final SimpleStringProperty  floatingText   = new SimpleStringProperty(this, "floatingText", "");
    protected final SimpleBooleanProperty alwaysFloating = new SimpleBooleanProperty(this, "alwaysFloating", false);

    protected BooleanBinding isMaxCharacterCountEnabled;
    protected BooleanBinding isMaxCharacterCountPosBelow;
    protected BooleanBinding isStyleModeDark;
    protected BooleanBinding isStyleModeCustom;
    protected BooleanBinding isSupportingTextEnabled;
    protected BooleanBinding isTextModeFilled;
    protected BooleanBinding isFloatModeBorder;
    protected BooleanBinding isFloatModeInside;
    protected BooleanBinding isFloatModeAbove;
    protected BooleanBinding isFloatModeDisabled;
    protected BooleanBinding isFloatAnimationEnabled;
    protected BooleanBinding isEmpty;

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

        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, FloatMode>().property("-float-mode")
                                                                                    .converter(EnumConverter.getEnumConverter(FloatMode.class))
                                                                                    .initialValue(FloatMode.DISABLED)
                                                                                    .isSettableFunction(node -> checkProperty(node.floatMode) && !(node.isTextModeFilled() && node.isFloatModeBorder()))
                                                                                    .propertyGetterFunction(node -> node.floatMode));

        STYLES_MANAGER.addCssMetaData(new CssFactory<EnhancedTextField, TextFieldMode>().property("-text-field-mode")
                                                                                        .converter(EnumConverter.getEnumConverter(TextFieldMode.class))
                                                                                        .initialValue(TextFieldMode.OUTLINED)
                                                                                        .isSettableFunction(
                                                                                              node -> checkProperty(node.textFieldMode) && !(node.isTextModeFilled() && node.isFloatModeBorder()))
                                                                                        .propertyGetterFunction(node -> node.textFieldMode));

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

    public EnhancedTextField() {
        super();
        getStylesheets().add(Stylesheets.ENHANCED_TEXT_FIELD.getStyleSheet());
        setupBooleanBindings();
        setupThis();
        setupInnerField();
        setupListeners();

        updatePseudoClassStates();
    }

    protected void setupThis() {
        getStyleClass().add("enhanced-text-field");
    }

    protected void setupInnerField() {
        this.focusedProperty()
            .addListener((obs, oldFocus, isFocused) -> {
                if (isFocused) {
                    innerField.requestFocus();
                }
            });
    }

    protected void setupBooleanBindings() {
        isMaxCharacterCountEnabled  = Bindings.createBooleanBinding(() -> getMaxCharCountState() == Status.ENABLED, maxCharCountState);
        isSupportingTextEnabled     = Bindings.createBooleanBinding(() -> getSupportingTextState() == Status.ENABLED, supportingTextState);
        isMaxCharacterCountPosBelow = Bindings.createBooleanBinding(() -> getMaxCharacterCountPosition() == MaxCharacterCountPosition.BELOW, maxCharacterCountPosition);
        isStyleModeDark             = Bindings.createBooleanBinding(() -> getStyleMode() == StyleMode.DARK, styleMode);
        isStyleModeCustom           = Bindings.createBooleanBinding(() -> getStyleMode() == StyleMode.CUSTOM, styleMode);
        isTextModeFilled            = Bindings.createBooleanBinding(() -> getTextFieldMode() == TextFieldMode.FILLED, textFieldMode);
        isFloatModeBorder           = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.BORDER, floatMode);
        isFloatModeInside           = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.INSIDE, floatMode);
        isFloatModeAbove            = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.ABOVE, floatMode);
        isFloatModeDisabled         = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.DISABLED, floatMode);
        isEmpty                     = Bindings.createBooleanBinding(() -> getText().isEmpty(), textProperty());

        isFloatAnimationEnabled = Bindings.createBooleanBinding(() -> !isFloatModeDisabled() && !isAlwaysFloating() && isEmpty.get() && getPromptText().isEmpty(), floatMode, alwaysFloating, isEmpty,
                                                                promptTextProperty());
    }

    protected void setupListeners() {
        //floatMode.addListener(invalidated -> requestLayout());
        alwaysFloating.addListener(invalidated -> requestLayout());
        textFill.addListener((obs, oldColor, newColor) -> updateTextWithNewColor(newColor));
        promptTextFill.addListener((obs, oldColor, newColor) -> updatePromptTextWithNewColor(newColor));

        textFieldMode.addListener(invalidated -> {
            System.out.println(textFieldMode);
        });
    }

    /**
     * Updates the style of a text field by changing the text fill color to a new color.
     *
     * @param newColor
     *         the new color to be applied to the text field
     */
    protected void updateTextWithNewColor(Color newColor) {
        Node node = getInnerField();
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
    protected void updatePromptTextWithNewColor(Color newColor) {
        Node node = getInnerField();
        String baseStyle = node.getStyle()
                               .replaceAll("-fx-prompt-text-fill: #[0-9a-fA-F]+;", ""); //remove old color
        String newStyle = String.format("%s -fx-prompt-text-fill: #%s;", baseStyle, newColor.toString()
                                                                                            .substring(2)); //append new color
        node.setStyle(newStyle); //set the new style
    }

    //region Override Methods
    //*****************************************************************
    // Override Methods
    //*****************************************************************

    @Override
    protected Skin<?> createDefaultSkin() {
        return new EnhancedTextFieldSkin(this);
    }

    @Override
    public TextField getInnerField() {
        return innerField;
    }

    //endregion Override Methods

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Retrieves the leading icon associated with this CustomTextField.
     *
     * @return the leading icon as a Node
     */
    public Node getLeadingIcon() {
        return leadingIcon.get();
    }

    public ObjectProperty<Node> leadingIconProperty() {
        return leadingIcon;
    }

    public void setLeadingIcon(Node leadingIcon) {
        this.leadingIcon.set(leadingIcon);
    }

    public Node getTrailingIcon() {
        return trailingIcon.get();
    }

    public ObjectProperty<Node> trailingIconProperty() {
        return trailingIcon;
    }

    public void setTrailingIcon(Node trailingIcon) {
        this.trailingIcon.set(trailingIcon);
    }

    public Boolean isMaxCharacterCountEnabled() {
        return isMaxCharacterCountEnabled.get();
    }

    public BooleanBinding isMaxCharacterCountEnabledProperty() {
        return isMaxCharacterCountEnabled;
    }

    public Boolean isMaxCharacterCountPosBelow() {
        return isMaxCharacterCountPosBelow.get();
    }

    public BooleanBinding isMaxCharacterCountPosBelowProperty() {
        return isMaxCharacterCountPosBelow;
    }

    public Boolean isStyleModeDark() {
        return isStyleModeDark.get();
    }

    public BooleanBinding isStyleModeDarkProperty() {
        return isStyleModeDark;
    }

    public Boolean isStyleModeCustom() {
        return isStyleModeCustom.get();
    }

    public BooleanBinding isStyleModeCustomProperty() {
        return isStyleModeCustom;
    }

    public StyleMode getStyleMode() {
        return styleMode.get();
    }

    public StyleableObjectProperty<StyleMode> styleModeProperty() {
        return styleMode;
    }

    public void setStyleMode(StyleMode styleMode) {
        this.styleMode.set(styleMode);
    }

    public Boolean isSupportingTextEnabled() {
        return isSupportingTextEnabled.get();
    }

    public BooleanBinding isSupportingTextEnabledProperty() {
        return isSupportingTextEnabled;
    }

    public Boolean isEmpty() {
        return isEmpty.get();
    }

    public BooleanBinding isEmptyProperty() {
        return isEmpty;
    }

    public Status getSupportingTextState() {
        return supportingTextState.get();
    }

    public StyleableObjectProperty<Status> supportingTextStateProperty() {
        return supportingTextState;
    }

    public void setSupportingTextState(Status supportingTextState) {
        this.supportingTextState.set(supportingTextState);
    }

    public int getMaxCharacterCount() {
        return maxCharacterCount.get();
    }

    public StyleableIntegerProperty maxCharacterCountProperty() {
        return maxCharacterCount;
    }

    public void setMaxCharacterCount(int maxCharacterCount) {
        this.maxCharacterCount.set(maxCharacterCount);
    }

    public Status getMaxCharCountState() {
        return maxCharCountState.get();
    }

    public StyleableObjectProperty<Status> maxCharCountStateProperty() {
        return maxCharCountState;
    }

    public void setMaxCharCountState(Status maxCharCountState) {
        this.maxCharCountState.set(maxCharCountState);
    }

    public MaxCharacterCountPosition getMaxCharacterCountPosition() {
        return maxCharacterCountPosition.get();
    }

    public StyleableObjectProperty<MaxCharacterCountPosition> maxCharacterCountPositionProperty() {
        return maxCharacterCountPosition;
    }

    public void setMaxCharacterCountPosition(MaxCharacterCountPosition maxCharacterCountPosition) {
        this.maxCharacterCountPosition.set(maxCharacterCountPosition);
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
     * @param floatMode
     *         the float mode to be set
     */
    public void setFloatMode(FloatMode floatMode) {
        this.floatMode.set(floatMode);
    }

    /**
     * Checks if the object is always floating.
     *
     * @return {@code true} if the object is always floating, {@code false} otherwise.
     */
    public boolean isAlwaysFloating() {
        return alwaysFloating.get();
    }

    /**
     * Returns a SimpleBooleanProperty representing the "always floating" property.
     *
     * @return a SimpleBooleanProperty object representing the "always floating" property
     */
    public SimpleBooleanProperty alwaysFloatingProperty() {
        return alwaysFloating;
    }

    /**
     * Sets whether the object should always be floating.
     *
     * @param alwaysFloating
     *         true if the object should always be floating, false otherwise
     */
    public void setAlwaysFloating(boolean alwaysFloating) {
        this.alwaysFloating.set(alwaysFloating);
    }

    /**
     * Returns whether the float animation is enabled or not.
     *
     * @return true if float animation is enabled, false otherwise.
     */
    public boolean isFloatAnimationEnabled() {return this.isFloatAnimationEnabled.get();}

    /**
     * Returns a BooleanBinding representing the property that indicates whether float animation is enabled.
     *
     * @return a BooleanBinding indicating whether float animation is enabled
     */
    public BooleanBinding isFloatAnimationEnabledProperty() {
        return isFloatAnimationEnabled;
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

    //region StyleableProperties
    //*****************************************************************
    // StyleableProperties
    //*****************************************************************

    protected final StyleableObjectProperty<Status> supportingTextState = StyleableObjectPropertyFactory.<Status>builder()
                                                                                                        .bean(this)
                                                                                                        .name("supportingTextState")
                                                                                                        .cssMetaData(STYLES_MANAGER.findCssMetaData("-supporting-text-state"))
                                                                                                        .defaultValue(Status.DISABLED)
                                                                                                        .build();

    protected final StyleableIntegerProperty maxCharacterCount = StyleableIntegerPropertyFactory.builder()
                                                                                                .bean(this)
                                                                                                .name("maxCharacterCount")
                                                                                                .cssMetaData(STYLES_MANAGER.findCssMetaData("-max-char-count"))
                                                                                                .defaultValue(50)
                                                                                                .invalidatedCachedCallback(this::maxCharacterCountInvalidated)
                                                                                                .build();

    protected final StyleableObjectProperty<Status> maxCharCountState = StyleableObjectPropertyFactory.<Status>builder()
                                                                                                      .bean(this)
                                                                                                      .name("maxCharacterCountEnabled")
                                                                                                      .defaultValue(Status.DISABLED)
                                                                                                      .build();

    protected final StyleableObjectProperty<MaxCharacterCountPosition> maxCharacterCountPosition = StyleableObjectPropertyFactory.<MaxCharacterCountPosition>builder()
                                                                                                                                 .bean(this)
                                                                                                                                 .name("maxCharacterCountPosition")
                                                                                                                                 .cssMetaData(STYLES_MANAGER.findCssMetaData("-max-char-count-pos"))
                                                                                                                                 .defaultValue(MaxCharacterCountPosition.ABOVE)
                                                                                                                                 .invalidatedCachedCallback(this::maxCharacterCountPositionInvalidated)
                                                                                                                                 .build();

    protected final StyleableObjectProperty<StyleMode> styleMode = StyleableObjectPropertyFactory.<StyleMode>builder()
                                                                                                 .bean(this)
                                                                                                 .name("styleMode")
                                                                                                 .cssMetaData(STYLES_MANAGER.findCssMetaData("-style-mode"))
                                                                                                 .defaultValue(StyleMode.LIGHT)
                                                                                                 .invalidatedPropCallback(this::styleModeInvalidated)
                                                                                                 .build();

    protected final StyleableObjectProperty<FloatMode> floatMode = StyleableObjectPropertyFactory.<FloatMode>builder()
                                                                                                 .bean(this)
                                                                                                 .name("floatMode")
                                                                                                 .cssMetaData(STYLES_MANAGER.findCssMetaData("-float-mode"))
                                                                                                 .defaultValue(FloatMode.DISABLED)
                                                                                                 .invalidatedCachedCallback(this::floatModeInvalidated)
                                                                                                 .build();

    protected final StyleableObjectProperty<TextFieldMode> textFieldMode = StyleableObjectPropertyFactory.<TextFieldMode>builder()
                                                                                                         .bean(this)
                                                                                                         .name("textFieldMode")
                                                                                                         .defaultValue(TextFieldMode.OUTLINED)
                                                                                                         .cssMetaData(STYLES_MANAGER.findCssMetaData("-text-field-mode"))
                                                                                                         .invalidatedCachedCallback(this::textFieldModeInvalidated)
                                                                                                         .build();

    protected final StyleableObjectProperty<Color> textFill = StyleableObjectPropertyFactory.<Color>builder()
                                                                                            .bean(this)
                                                                                            .name("textFill")
                                                                                            .cssMetaData(STYLES_MANAGER.findCssMetaData("-fx-text-fill"))
                                                                                            .defaultValue(Color.valueOf("#000000"))
                                                                                            .build();

    protected final StyleableObjectProperty<Color> promptTextFill = StyleableObjectPropertyFactory.<Color>builder()
                                                                                                  .bean(this)
                                                                                                  .name("promptTextFill")
                                                                                                  .cssMetaData(STYLES_MANAGER.findCssMetaData("-fx-prompt-text-fill"))
                                                                                                  .defaultValue(Color.valueOf("#000000"))
                                                                                                  .build();

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
        if (isMaxCharacterCountEnabled.not()
                                      .get()) {
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
        if (value < 0 || isMaxCharacterCountEnabled.not()
                                                   .get()) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.set(oldValue);
            if (isMaxCharacterCountEnabled.not()
                                          .get()) {
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

    /**
     * Called when the 'floatMode' property is invalidated. Changes the pseudo-class state according to the new value of the 'floatMode' property.
     *
     * @param prop
     *         The StyleableObjectProperty representing the 'floatMode' property.
     */
    protected void floatModeInvalidated(StyleableObjectProperty<FloatMode> prop, FloatMode oldValue, Consumer<FloatMode> oldValueSetter) {
        FloatMode mode = prop.get();
        if (isTextModeFilled.get() && mode == FloatMode.BORDER) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.setValue(oldValue);
            throw new IllegalArgumentException("Float Mode cannot be set to Border mode when the Text Field Mode is Filled");
        }

        pseudoClassStateChanged(FLOAT_BORDER_MODE_PSEUDO_CLASS, mode == FloatMode.BORDER);
        pseudoClassStateChanged(FLOAT_INSIDE_MODE_PSEUDO_CLASS, mode == FloatMode.INSIDE);
        pseudoClassStateChanged(FLOAT_ABOVE_MODE_PSEUDO_CLASS, mode == FloatMode.ABOVE);

        if (oldValue != mode) {
            oldValueSetter.accept(mode);
        }
    }

    /**
     * Handles the invalidation of the {@link StyleableObjectProperty} representing the {@link TextFieldMode} of the text field.
     *
     * @param prop
     *         the styleable object property representing the text field mode
     * @param oldValue
     *         the previous value of the text field mode
     * @param oldValueSetter
     *         a consumer to accept the previous value of the text field mode
     */
    protected void textFieldModeInvalidated(StyleableObjectProperty<TextFieldMode> prop, TextFieldMode oldValue, Consumer<TextFieldMode> oldValueSetter) {
        TextFieldMode mode = prop.get();
        if (isTextModeFilled.and(isFloatModeBorder)
                            .get()) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.setValue(oldValue);
            throw new IllegalArgumentException("Text Field Mode cannot be set to filled mode when the float mode is border");
        }
        pseudoClassStateChanged(TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS, mode == TextFieldMode.FILLED);
        if (oldValue != mode) {
            oldValueSetter.accept(mode);
        }
    }

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
    protected void updatePseudoClassStates() {
        pseudoClassStateChanged(TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS, isTextModeFilled.get());
        pseudoClassStateChanged(FLOAT_BORDER_MODE_PSEUDO_CLASS, isFloatModeBorder.get());
        pseudoClassStateChanged(FLOAT_INSIDE_MODE_PSEUDO_CLASS, isFloatModeInside.get());
        pseudoClassStateChanged(FLOAT_ABOVE_MODE_PSEUDO_CLASS, isFloatModeAbove.get());
        pseudoClassStateChanged(STYLE_MODE_DARK_PSEUDO_CLASS, isStyleModeDark.get());
        pseudoClassStateChanged(STYLE_MODE_CUSTOM_PSEUDO_CLASS, isStyleModeCustom.get());
    }

    //endregion StyleableProperties

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return EnhancedTextField.STYLES_MANAGER.getCssMetaDataList();
    }

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {return getClassCssMetaData();}

}
