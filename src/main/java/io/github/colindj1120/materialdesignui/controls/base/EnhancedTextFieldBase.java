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
import io.github.colindj1120.materialdesignui.enums.*;
import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesCreator;
import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesManager;
import io.github.colindj1120.materialdesignui.styling.Stylesheets;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.css.PseudoClass;
import javafx.css.StyleableIntegerProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.EnumConverter;
import javafx.css.converter.SizeConverter;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@code EnhancedTextFieldBase} is an abstract class extending {@code TextBase} and implementing {@code InnerTextField} and {@code TextFieldIcons} interfaces. It provides a comprehensive foundation
 * for creating sophisticated text field controls in JavaFX with enhanced functionalities and aesthetic customization options. This class is specifically designed to integrate seamlessly with JavaFX's
 * styling and event handling mechanisms, making it suitable for building modern and interactive user interfaces.
 *
 * <p><b>Core Features:</b></p>
 * <ul>
 *   <li><b>Icon Management:</b> Facilitates the addition and manipulation of leading and trailing icons, enhancing the text field's interactive capabilities.</li>
 *   <li><b>Character Count and Supporting Text:</b> Supports character count limitations and display along with additional supporting text for user guidance and input validation.</li>
 *   <li><b>Style Customization:</b> Offers extensive style customization through CSS, including different modes (e.g., filled, outlined) and pseudo-class states for dynamic styling.</li>
 *   <li><b>Animation and Floating Label Support:</b> Provides capabilities for label animations and floating label behaviors, enhancing the user experience and interface aesthetics.</li>
 *   <li><b>Advanced Property Binding:</b> Utilizes JavaFX property bindings and BooleanBindings for reactive UI updates based on the state of the text field and its content.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * {@code EnhancedTextFieldBase} is intended to be subclassed for creating custom text field controls
 * that require advanced features beyond the standard {@code TextField} component. Subclasses can
 * leverage the provided functionality while adding specific behaviors and styles as needed.
 *
 * <pre>
 * public class MyCustomTextField extends EnhancedTextFieldBase {
 *     // Implementation of abstract methods and additional functionalities
 * }
 * </pre>
 *
 * <p>This class is part of the {@code io.github.colindj1120.materialdesignui.controls.base} package,
 * contributing to a Material Design UI framework for JavaFX applications.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see javafx.scene.control.TextField
 * @see javafx.css.PseudoClass
 * @see javafx.scene.Node
 * @see javafx.beans.property.ObjectProperty
 * @see javafx.scene.control.Label
 * @see javafx.scene.transform.Scale
 * @see javafx.scene.transform.Translate
 */
public abstract class EnhancedTextFieldBase extends TextBase implements InnerTextField, TextFieldIcons {
    protected static final String STYLE = "enhanced-text-field";

    protected static final PseudoClass FLOAT_BORDER_MODE_PSEUDO_CLASS      = PseudoClass.getPseudoClass("float-border-mode");
    protected static final PseudoClass FLOAT_ABOVE_MODE_PSEUDO_CLASS       = PseudoClass.getPseudoClass("float-above-mode");
    protected static final PseudoClass FLOAT_INSIDE_MODE_PSEUDO_CLASS      = PseudoClass.getPseudoClass("float-above-mode");
    protected static final PseudoClass TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS = PseudoClass.getPseudoClass("filled-mode");
    protected static final PseudoClass STYLE_MODE_DARK_PSEUDO_CLASS        = PseudoClass.getPseudoClass("style-mode-dark");
    protected static final PseudoClass STYLE_MODE_CUSTOM_PSEUDO_CLASS      = PseudoClass.getPseudoClass("style-mode-custom");

    protected final TextField                           textField              = new TextField();
    protected final ObjectProperty<Node>                leadingIcon            = new SimpleObjectProperty<>(this, "leadingIcon");
    protected final ObjectProperty<Node>                trailingIcon           = new SimpleObjectProperty<>(this, "trailingIcon");
    protected final Label                               leadingIconLabel       = new Label();
    protected final Label                               trailingIconLabel      = new Label();
    protected final Label                               characterCountLabel    = new Label();
    protected final Label                               supportingTextLabel    = new Label();
    protected final Label                               floatingTextLabel      = new Label();
    protected final StackPane                           textFieldContainer     = new StackPane(textField, floatingTextLabel);
    protected final double                              scaleFactor            = .75;
    protected final Scale                               scale                  = Transform.scale(1, 1, 0, 0);
    protected final Translate                           translate              = Transform.translate(1, 1);
    protected final SimpleStringProperty                floatingText           = new SimpleStringProperty(this, "floatingText", "");
    protected final SimpleStringProperty                supportingText         = new SimpleStringProperty(this, "supportingText", "");
    protected final SimpleObjectProperty<EnabledStatus> floatAnimationState    = new SimpleObjectProperty<>(this, "floatAnimateState", EnabledStatus.DISABLED);
    protected final SimpleBooleanProperty               alwaysFloating         = new SimpleBooleanProperty(this, "alwaysFloating", false);
    protected final SimpleObjectProperty<Pos>           textFieldAlignmentBase = new SimpleObjectProperty<>(this, "textFieldAlignmentBase");

    protected StyleableIntegerProperty                           maxCharacterCount;
    protected StyleableObjectProperty<EnabledStatus>             maxCharCountState;
    protected StyleableObjectProperty<MaxCharacterCountPosition> maxCharacterCountPosition;
    protected StyleableObjectProperty<EnabledStatus>             supportingTextState;
    protected StyleableObjectProperty<FloatMode>                 floatMode;
    protected StyleableObjectProperty<TextFieldMode>             textFieldMode;
    protected StyleableObjectProperty<StyleMode>                 styleMode;

    protected BooleanBinding isTextModeFilled;
    protected BooleanBinding isFloatModeBorder;
    protected BooleanBinding isFloatModeInside;
    protected BooleanBinding isFloatModeAbove;
    protected BooleanBinding isFloatModeDisabled;
    protected BooleanBinding isFloatAnimationEnabled;
    protected BooleanBinding floatTextLabelVisibleAndModeInside;
    protected BooleanBinding isMaxCharacterCountEnabled;
    protected BooleanBinding isSupportingTextEnabled;
    protected BooleanBinding isMaxCharacterCountPosBelow;
    protected BooleanBinding isStyleModeDark;
    protected BooleanBinding isStyleModeCustom;

    static {
        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<EnhancedTextField, EnabledStatus>().property("-max-char-count-state")
                                                                                                                                 .converter(EnumConverter.getEnumConverter(EnabledStatus.class))
                                                                                                                                 .initialValue(EnabledStatus.DISABLED)
                                                                                                                                 .isSettableFunction(node -> checkProperty(node.maxCharCountState))
                                                                                                                                 .propertyGetterFunction(node -> node.maxCharCountState));

        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<EnhancedTextField, Number>().property("-max-char-count")
                                                                                                                          .converter(SizeConverter.getInstance())
                                                                                                                          .initialValue(50)
                                                                                                                          .isSettableFunction(node -> checkProperty(node.maxCharacterCount) &&
                                                                                                                                                      node.isMaxCharacterCountEnabled())
                                                                                                                          .propertyGetterFunction(node -> node.maxCharacterCount));

        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<EnhancedTextField, MaxCharacterCountPosition>().property("-max-char-count-pos")
                                                                                                                                             .converter(EnumConverter.getEnumConverter(
                                                                                                                                                     MaxCharacterCountPosition.class))
                                                                                                                                             .initialValue(MaxCharacterCountPosition.ABOVE)
                                                                                                                                             .isSettableFunction(node -> checkProperty(
                                                                                                                                                     node.maxCharacterCountPosition) &&
                                                                                                                                                                         node.isMaxCharacterCountEnabled())
                                                                                                                                             .propertyGetterFunction(
                                                                                                                                                     node -> node.maxCharacterCountPosition));

        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<EnhancedTextField, EnabledStatus>().property("-supporting-text-state")
                                                                                                                                 .converter(EnumConverter.getEnumConverter(EnabledStatus.class))
                                                                                                                                 .initialValue(EnabledStatus.DISABLED)
                                                                                                                                 .isSettableFunction(node -> checkProperty(node.supportingTextState))
                                                                                                                                 .propertyGetterFunction(node -> node.supportingTextState));

        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<EnhancedTextField, FloatMode>().property("-float-mode")
                                                                                                                             .converter(EnumConverter.getEnumConverter(FloatMode.class))
                                                                                                                             .initialValue(FloatMode.DISABLED)
                                                                                                                             .isSettableFunction(node -> checkProperty(node.floatMode) &&
                                                                                                                                                         !(node.isTextModeFilled() &&
                                                                                                                                                           node.isFloatModeBorder()))
                                                                                                                             .propertyGetterFunction(node -> node.floatMode));

        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<EnhancedTextField, TextFieldMode>().property("-text-field-mode")
                                                                                                                                 .converter(EnumConverter.getEnumConverter(TextFieldMode.class))
                                                                                                                                 .initialValue(TextFieldMode.OUTLINED)
                                                                                                                                 .isSettableFunction(node -> checkProperty(node.textFieldMode) &&
                                                                                                                                                             !(node.isTextModeFilled() &&
                                                                                                                                                               node.isFloatModeBorder()))
                                                                                                                                 .propertyGetterFunction(node -> node.textFieldMode));

        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<EnhancedTextField, StyleMode>().property("-style-mode")
                                                                                                                             .converter(EnumConverter.getEnumConverter(StyleMode.class))
                                                                                                                             .initialValue(StyleMode.LIGHT)
                                                                                                                             .isSettableFunction(node -> checkProperty(node.styleMode))
                                                                                                                             .propertyGetterFunction(node -> node.styleMode));
    }

    /**
     * Initializes a new instance of the EnhancedTextFieldBase class. This constructor sets up the style, styleable properties, and boolean bindings for the text field.
     */
    protected EnhancedTextFieldBase() {
        setupStyle();
        setupStyleableProperties();
        setupBooleanBindings();
    }

    /**
     * Sets up the style for the component. Adds the specified style class and stylesheet to the component's style class and stylesheets.
     */
    protected void setupStyle() {
        this.getStyleClass()
            .add(STYLE);
        this.getStylesheets()
            .add(Stylesheets.MD_TEXT_FIELD.getStyleSheet());
    }

    /**
     * Sets up the styleable properties for the class. Each styleable property is created and initialized with default values. The method also defines the behaviors and restrictions associated with
     * each property.
     */
    protected void setupStyleableProperties() {
        maxCharCountState = StyleablePropertiesCreator.StyleableObjectPropertyCreator.<EnabledStatus>builder()
                                                                                     .bean(this)
                                                                                     .name("maxCharacterCountEnabled")
                                                                                     .defaultValue(EnabledStatus.DISABLED)
                                                                                     .build();

        maxCharacterCount = StyleablePropertiesCreator.StyleableIntegerPropertyCreator.builder()
                                                                                      .bean(this)
                                                                                      .name("maxCharacterCount")
                                                                                      .cssMetaData(findCssMetaData("-max-char-count"))
                                                                                      .defaultValue(50)
                                                                                      .invalidatedCachedCallback(this::maxCharacterCountInvalidated)
                                                                                      .build();

        maxCharacterCountPosition = StyleablePropertiesCreator.StyleableObjectPropertyCreator.<MaxCharacterCountPosition>builder()
                                                                                             .bean(this)
                                                                                             .name("maxCharacterCountPosition")
                                                                                             .cssMetaData(findCssMetaData("-max-char-count-pos"))
                                                                                             .defaultValue(MaxCharacterCountPosition.ABOVE)
                                                                                             .invalidatedCachedCallback(this::maxCharacterCountPositionInvalidated)
                                                                                             .build();

        supportingTextState = StyleablePropertiesCreator.StyleableObjectPropertyCreator.<EnabledStatus>builder()
                                                                                       .bean(this)
                                                                                       .name("supportingTextState")
                                                                                       .cssMetaData(findCssMetaData("-supporting-text-state"))
                                                                                       .defaultValue(EnabledStatus.DISABLED)
                                                                                       .build();

        floatMode = StyleablePropertiesCreator.StyleableObjectPropertyCreator.<FloatMode>builder()
                                                                             .bean(this)
                                                                             .name("floatMode")
                                                                             .cssMetaData(findCssMetaData("-float-mode"))
                                                                             .defaultValue(FloatMode.DISABLED)
                                                                             .invalidatedCachedCallback(this::floatModeInvalidated)
                                                                             .build();

        textFieldMode = StyleablePropertiesCreator.StyleableObjectPropertyCreator.<TextFieldMode>builder()
                                                                                 .bean(this)
                                                                                 .name("textFieldMode")
                                                                                 .defaultValue(TextFieldMode.OUTLINED)
                                                                                 .cssMetaData(findCssMetaData("-text-field-mode"))
                                                                                 .invalidatedCachedCallback(this::textFieldModeInvalidated)
                                                                                 .build();

        styleMode = StyleablePropertiesCreator.StyleableObjectPropertyCreator.<StyleMode>builder()
                                                                             .bean(this)
                                                                             .name("styleMode")
                                                                             .cssMetaData(findCssMetaData("-style-mode"))
                                                                             .defaultValue(StyleMode.LIGHT)
                                                                             .invalidatedPropCallback(this::styleModeInvalidated)
                                                                             .build();
    }

    /**
     * Sets up the boolean bindings for various properties. These bindings are used to dynamically update the boolean values based on the changes in the associated properties.
     */
    protected void setupBooleanBindings() {
        isTextModeFilled    = Bindings.createBooleanBinding(() -> getTextFieldMode() == TextFieldMode.FILLED, textFieldMode);
        isFloatModeBorder   = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.BORDER, floatMode);
        isFloatModeInside   = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.INSIDE, floatMode);
        isFloatModeAbove    = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.ABOVE, floatMode);
        isFloatModeDisabled = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.DISABLED, floatMode);

        isMaxCharacterCountEnabled         = Bindings.createBooleanBinding(() -> getMaxCharCountState() == EnabledStatus.ENABLED, maxCharCountState);
        isSupportingTextEnabled            = Bindings.createBooleanBinding(() -> getSupportingTextState() == EnabledStatus.ENABLED, supportingTextState);
        floatTextLabelVisibleAndModeInside = floatingTextLabel.visibleProperty()
                                                              .and(isFloatModeInside);
        isMaxCharacterCountPosBelow        = Bindings.createBooleanBinding(() -> getMaxCharacterCountPosition() == MaxCharacterCountPosition.BELOW, maxCharacterCountPosition);
        isStyleModeDark                    = Bindings.createBooleanBinding(() -> getStyleMode() == StyleMode.DARK, styleMode);
        isStyleModeCustom                  = Bindings.createBooleanBinding(() -> getStyleMode() == StyleMode.CUSTOM, styleMode);
        isEmpty                            = Bindings.createBooleanBinding(() -> getText().isEmpty(), textProperty());

        isFloatAnimationEnabled = Bindings.createBooleanBinding(() -> !isFloatModeDisabled() && !isAlwaysFloating() && isEmpty.get() && getPromptText().isEmpty(), floatMode, alwaysFloating, isEmpty,
                                                                promptTextProperty(), textFieldFocusedProperty());
    }

    /**
     * Invalidates the maximum character count for a StyleableIntegerProperty.
     *
     * @param prop
     *         The StyleableIntegerProperty whose maximum character count is invalidated.
     * @param oldValue
     *         The previous value of the StyleableIntegerProperty.
     * @param oldValueSetter
     *         A Consumer that accepts the new value of the StyleableIntegerProperty.
     *
     * @throws IllegalArgumentException
     *         If the new value is negative, or if the maximum character count is disabled.
     */
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
     * Method to handle the invalidation of the "style mode" property.
     *
     * @param prop
     *         The StyleableObjectProperty representing the "style mode" property.
     */
    protected void styleModeInvalidated(StyleableObjectProperty<StyleMode> prop) {
        StyleMode mode = prop.get();

        pseudoClassStateChanged(STYLE_MODE_DARK_PSEUDO_CLASS, mode == StyleMode.DARK);
        pseudoClassStateChanged(STYLE_MODE_CUSTOM_PSEUDO_CLASS, mode == StyleMode.LIGHT);
    }

    /**
     * Updates the pseudo-class states of the text field based on the current state properties. This method is used internally to dynamically update the appearance of the text field based on changes
     * in its state properties.
     *
     * <p>Note: This method does not return any value.</p>
     *
     * <p>It updates the following pseudo-classes:</p>
     * <ul>
     *     <li>TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isTextModeFilled' property.</li>
     *     <li>FLOAT_BORDER_MODE_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isFloatModeBorder' property.</li>
     *     <li>FLOAT_INSIDE_MODE_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isFloatModeInside' property.</li>
     *     <li>FLOAT_ABOVE_MODE_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isFloatModeAbove' property.</li>
     *     <li>STYLE_MODE_DARK_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isStyleModeDark' property.</li>
     *     <li>STYLE_MODE_CUSTOM_PSEUDO_CLASS: Sets the pseudo-class state based on the 'isStyleModeCustom' property.</li>
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

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField getField() {
        return textField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Label getLeadingIconLabel() {
        return leadingIconLabel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Label getTrailingIconLabel() {
        return trailingIconLabel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestFocus() {
        textFieldFocus();
    }

    @Override
    public void setTextFieldAlignment(Pos value) {
        textField.setAlignment(value);
        textFieldAlignmentBase.set(value);

        if (floatTextLabelVisibleAndModeInside.get()) {
            floatTextLabelVisibleAndModeInside.invalidate();
        }
    }

    @Override
    public Pos getTextFieldAlignment() {
        if (floatTextLabelVisibleAndModeInside.get()) {
            return textFieldAlignmentBase.get();
        } else {
            return textField.getAlignment();
        }
    }

    /**
     * Converts the given alignment position to its corresponding baseline alignment position.
     *
     * @param alignment
     *         the alignment position to be converted
     *
     * @return the corresponding baseline alignment position
     */
    protected static Pos convertToBaseline(Pos alignment) {
        if (Objects.isNull(alignment)) {
            return Pos.BASELINE_LEFT;
        }

        return switch (alignment) {
            case TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT, BASELINE_LEFT -> Pos.BASELINE_LEFT;
            case TOP_CENTER, CENTER, BOTTOM_CENTER, BASELINE_CENTER -> Pos.BASELINE_CENTER;
            case TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT, BASELINE_RIGHT -> Pos.BASELINE_RIGHT;
        };
    }

    /**
     * Sets the focus to the text field and deselects any selected text.
     */
    protected void textFieldFocus() {
        textField.requestFocus();
        textField.deselect();
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
     * Retrieves the value of the floatTextLabelVisibleAndModeInside property.
     *
     * @return the value of the floatTextLabelVisibleAndModeInside property, indicating whether the float text label is visible and in mode inside.
     */
    public Boolean getFloatTextLabelVisibleAndModeInside() {
        return floatTextLabelVisibleAndModeInside.get();
    }

    /**
     * Returns the value of the floatTextLabelVisibleAndModeInside property.
     *
     * @return the value of the floatTextLabelVisibleAndModeInside property as a BooleanBinding.
     */
    public BooleanBinding floatTextLabelVisibleAndModeInsideProperty() {
        return floatTextLabelVisibleAndModeInside;
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
     * Retrieves the leading icon associated with this node.
     *
     * @return The leading icon of this node, or null if no leading icon is set.
     */
    public Node getLeadingIcon() {
        return leadingIcon.get();
    }

    /**
     * Returns the ObjectProperty representing the leading icon of this component.
     *
     * @return the ObjectProperty representing the leading icon
     */
    public ObjectProperty<Node> leadingIconProperty() {
        return leadingIcon;
    }

    /**
     * Sets the leading icon for the node.
     *
     * @param leadingIcon
     *         the new leading icon to be set
     */
    public void setLeadingIcon(Node leadingIcon) {
        if (this.leadingIcon.get() instanceof Label label) {
            label.setGraphic(leadingIcon);
        } else {
            Label label = new Label();
            label.setGraphic(leadingIcon);
            this.leadingIcon.set(label);
        }
    }

    /**
     * Removes the leading icon from the current object.
     */
    public void removeLeadingIcon() {
        this.leadingIcon.set(null);
    }

    /**
     * Retrieves the trailing icon of the node.
     *
     * @return The trailing icon of the node.
     */
    public Node getTrailingIcon() {
        return trailingIcon.get();
    }

    /**
     * Returns the ObjectProperty representing the trailing icon of this object.
     *
     * @return the ObjectProperty representing the trailing icon of this object.
     */
    public ObjectProperty<Node> trailingIconProperty() {
        return trailingIcon;
    }

    /**
     * Sets the trailing icon for the control.
     *
     * @param trailingIcon
     *         The node representing the trailing icon.
     */
    public void setTrailingIcon(Node trailingIcon) {
        this.trailingIcon.set(trailingIcon);
    }

    /**
     * Removes the trailing icon from the current instance.
     */
    public void removeTrailingIcon() {
        this.trailingIcon.set(null);
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
     * @param maxCharacterCount
     *         the maximum character count to be set
     */
    public void setMaxCharacterCount(int maxCharacterCount) {
        this.maxCharacterCount.set(maxCharacterCount);
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
     * @param maxCharCountState
     *         the new maximum character count state
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
     * @param maxCharacterCountPosition
     *         the new maximum character count position
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
     * @param supportingTextState
     *         The state of the supporting text.
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
     * @param floatMode
     *         the float mode to be set
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
     * Returns the SimpleObjectProperty representing the float animation state.
     *
     * @return the SimpleObjectProperty representing the float animation state
     */
    public ReadOnlyObjectProperty<EnabledStatus> floatAnimationStateProperty() {
        return floatAnimationState;
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
}
