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

import io.github.colindj1120.materialdesignui.controls.base.EnhancedTextBase;
import io.github.colindj1120.materialdesignui.controls.base.innertext.InnerTextField;
import io.github.colindj1120.materialdesignui.enums.controls.enhancedtextfield.FloatMode;
import io.github.colindj1120.materialdesignui.enums.controls.enhancedtextfield.TextFieldMode;
import io.github.colindj1120.materialdesignui.factories.styling.CssFactory;
import io.github.colindj1120.materialdesignui.factories.styling.StyleableObjectPropertyFactory;
import io.github.colindj1120.materialdesignui.skins.EnhancedTextFieldSkin;
import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesManager;
import io.github.colindj1120.materialdesignui.styling.Stylesheets;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.EnumConverter;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.util.List;
import java.util.function.Consumer;

import static io.github.colindj1120.materialdesignui.utils.PropertyUtils.checkProperty;

/**
 * EnhancedTextField extends {@link EnhancedTextBase} to provide a text field with additional features such as floating labels, leading and trailing icons, and custom style modes, as defined in the
 * Material Design UI guidelines.
 *
 * <p>EnhancedTextField implements {@link InnerTextField<TextField>} which provides the control with all the functionality for the user to access the inner text field
 * without having to get the inner text field first.</p>
 *
 * <p>This class integrates closely with CSS for styling and supports dynamic updates to its appearance through pseudo-classes.</p>
 *
 * <p>
 * Features include:
 * <ul>
 *     <li>Floating labels for text fields with an option to always float.</li>
 *     <li>Support for a supporting text label underneath the field</li>
 *     <li>Support for character limitations displayed above or below the field</li>
 *     <li>Support for leading and trailing icons within the text field.</li>
 *     <li>Customizable float modes and text field modes to alter the appearance.</li>
 *     <li>Boolean bindings and pseudo-class states dynamically reflect the control's state and mode.</li>
 *     <li>Extensive CSS support for customizing the look and feel, including text fill and prompt text fill.</li>
 * </ul>
 * </p>
 *
 * <p>The class initializes and binds several styleable properties related to the float mode, text field mode,
 * and icons. It sets up listeners for property changes to dynamically update the UI in response to state changes.
 * This control is designed to be easily styled and customized through CSS, providing a flexible component
 * for user interfaces.</p>
 *
 * <p>
 * <pre>
 * Usage example:
 * EnhancedTextField textField = new EnhancedTextField();
 * textField.setFloatMode(FloatMode.BORDER);
 * textField.setTextFieldMode(TextFieldMode.OUTLINED);
 * </pre>
 * </p>
 *
 * <p>This class also overrides the {@code createDefaultSkin} method to supply a custom skin tailored to the
 * enhanced features, ensuring a consistent Material Design look and feel.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InnerTextField
 * @see EnhancedTextBase
 */
public class EnhancedTextField extends EnhancedTextBase implements InnerTextField<TextField> {
    protected static final StyleablePropertiesManager STYLES_MANAGER                      = new StyleablePropertiesManager(EnhancedTextBase.getClassCssMetaData());
    protected static final PseudoClass                FLOAT_BORDER_MODE_PSEUDO_CLASS      = PseudoClass.getPseudoClass("float-border-mode");
    protected static final PseudoClass                FLOAT_ABOVE_MODE_PSEUDO_CLASS       = PseudoClass.getPseudoClass("float-above-mode");
    protected static final PseudoClass                FLOAT_INSIDE_MODE_PSEUDO_CLASS      = PseudoClass.getPseudoClass("float-above-mode");
    protected static final PseudoClass                TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS = PseudoClass.getPseudoClass("filled-mode");

    protected final TextField             innerField     = new TextField();
    protected final ObjectProperty<Node>  leadingIcon    = new SimpleObjectProperty<>(this, "leadingIcon");
    protected final ObjectProperty<Node>  trailingIcon   = new SimpleObjectProperty<>(this, "trailingIcon");
    protected final SimpleStringProperty  floatingText   = new SimpleStringProperty(this, "floatingText", "");
    protected final SimpleBooleanProperty alwaysFloating = new SimpleBooleanProperty(this, "alwaysFloating", false);

    protected StyleableObjectProperty<FloatMode>     floatMode;
    protected StyleableObjectProperty<TextFieldMode> textFieldMode;

    protected BooleanBinding isTextModeFilled;
    protected BooleanBinding isFloatModeBorder;
    protected BooleanBinding isFloatModeInside;
    protected BooleanBinding isFloatModeAbove;
    protected BooleanBinding isFloatModeDisabled;
    protected BooleanBinding isFloatAnimationEnabled;

    protected BooleanBinding isEmpty;

    static {
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
    }

    /**
     * Constructs an instance of EnhancedTextField. This constructor initializes all necessary styleable properties, sets up boolean bindings for dynamic UI updates, and configures listeners for
     * property changes. It also applies initial CSS styling and updates pseudo-class states to reflect the initial configuration.
     */
    public EnhancedTextField() {
        super();
        setupStyle();
        initializeStyleableProperties();
        setupBooleanBindings();
        setupListeners();
        updatePseudoClassStates();
    }

    /**
     * Sets up the style for the enhanced text field.
     * <p>Adds the "enhanced-text-field" CSS class to the style class list of the text field. </p>
     * <p>Adds the stylesheet defined by the Stylesheets.ENHANCED_TEXT_FIELD enumeration to the stylesheets list of the text field.</p>
     */
    private void setupStyle() {
        getStyleClass().add("enhanced-text-field");
        getStylesheets().add(Stylesheets.ENHANCED_TEXT_FIELD.getStyleSheet());
    }

    /**
     * Initializes the styleable properties for the current object. These properties define the behavior and appearance of the object when styled using CSS.
     */
    private void initializeStyleableProperties() {
        floatMode = StyleableObjectPropertyFactory.<FloatMode>builder()
                                                  .bean(this)
                                                  .name("floatMode")
                                                  .cssMetaData(STYLES_MANAGER.findCssMetaData("-float-mode"))
                                                  .defaultValue(FloatMode.DISABLED)
                                                  .invalidatedCachedCallback(this::floatModeInvalidated)
                                                  .build();

        textFieldMode = StyleableObjectPropertyFactory.<TextFieldMode>builder()
                                                      .bean(this)
                                                      .name("textFieldMode")
                                                      .defaultValue(TextFieldMode.OUTLINED)
                                                      .cssMetaData(STYLES_MANAGER.findCssMetaData("-text-field-mode"))
                                                      .invalidatedCachedCallback(this::textFieldModeInvalidated)
                                                      .build();
    }

    /**
     * Sets up the boolean bindings for various properties.
     *
     * <p>
     * This method creates a series of boolean bindings based on different conditions. These bindings are useful for dynamically updating the state of certain properties based on the changes in other
     * related properties.
     * </p>
     *
     * <p>
     * The following boolean bindings are created:
     * <ul>
     *     <li><em>isTextModeFilled</em>: A binding that determines if the TextFieldMode is set to FILLED. It is bound to the textFieldMode property.</li>
     *     <li><em>isFloatModeBorder</em>: A binding that determines if the FloatMode is set to BORDER. It is bound to the floatMode property.</li>
     *     <li><em>isFloatModeInside</em>: A binding that determines if the FloatMode is set to INSIDE. It is bound to the floatMode property.</li>
     *     <li><em>isFloatModeAbove</em>: A binding that determines if the FloatMode is set to ABOVE. It is bound to the floatMode property.</li>
     *     <li><em>isFloatModeDisabled</em>: A binding that determines if the FloatMode is set to DISABLED. It is bound to the floatMode property.</li>
     *     <li><em>isEmpty</em>: A binding that determines if the text of the control is empty. It is bound to the textProperty of the control.</li>
     *     <li><em>isFloatAnimationEnabled</em>: A binding that determines if the float animation should be enabled. It depends on the following conditions: floatMode is not DISABLED,
     *     alwaysFloating is not true, text of the control is empty, and prompt text is empty. It is bound to the floatMode, alwaysFloating, isEmpty, and promptTextProperty properties.</li>
     *     <li><em></em></li>
     *     <li><em></em></li>
     * </ul>
     *
     * <p>
     * These boolean bindings are created using the Bindings.createBooleanBinding() method, with the help of lambda expressions
     * to define the conditions. The resulting boolean bindings can be used to automatically update the state of other properties,
     * such as CSS styles or visibility.
     * </p>
     */
    private void setupBooleanBindings() {
        isTextModeFilled        = Bindings.createBooleanBinding(() -> getTextFieldMode() == TextFieldMode.FILLED, textFieldMode);
        isFloatModeBorder       = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.BORDER, floatMode);
        isFloatModeInside       = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.INSIDE, floatMode);
        isFloatModeAbove        = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.ABOVE, floatMode);
        isFloatModeDisabled     = Bindings.createBooleanBinding(() -> getFloatMode() == FloatMode.DISABLED, floatMode);
        isEmpty                 = Bindings.createBooleanBinding(() -> getText().isEmpty(), textProperty());
        isFloatAnimationEnabled = Bindings.createBooleanBinding(() -> !isFloatModeDisabled() && !isAlwaysFloating() && isEmpty.get() && getPromptText().isEmpty(), floatMode, alwaysFloating, isEmpty,
                                                                promptTextProperty());
    }

    /**
     * Sets up the listeners for the EnhancedTextField.
     *
     * <p>When the 'alwaysFloating' property is invalidated, it requests a layout.</p>
     * <p>When the 'focused' property is changed, it sets the focus on the innerField if it is focused.</p>
     */
    protected void setupListeners() {
        alwaysFloating.addListener(invalidated -> requestLayout());
        this.focusedProperty()
            .addListener((obs, oldFocus, isFocused) -> {
                if (isFocused) {
                    innerField.requestFocus();
                }
            });
    }

    //region Override Methods
    //*****************************************************************
    // Override Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EnhancedTextFieldSkin(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField getTextField() {
        return innerField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControl getTextControl() {
        return innerField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEmpty() {
        return isEmpty.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooleanBinding isEmptyProperty() {
        return isEmpty;
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

    /**
     * Retrieves the leading icon property.
     *
     * @return The object property representing the leading icon.
     */
    public ObjectProperty<Node> leadingIconProperty() {
        return leadingIcon;
    }

    /**
     * Sets the leading icon for this component.
     *
     * @param leadingIcon
     *         the node representing the leading icon
     */
    public void setLeadingIcon(Node leadingIcon) {
        this.leadingIcon.set(leadingIcon);
    }

    /**
     * Returns the trailing icon of the Node.
     *
     * @return the trailing icon of the Node
     */
    public Node getTrailingIcon() {
        return trailingIcon.get();
    }

    /**
     * Retrieves the ObjectProperty representing the trailing icon of this element.
     *
     * @return the ObjectProperty representing the trailing icon
     */
    public ObjectProperty<Node> trailingIconProperty() {
        return trailingIcon;
    }

    /**
     * Sets the trailing icon for this component. The trailing icon is the icon displayed at the end of the component.
     *
     * @param trailingIcon
     *         the trailing icon to be set
     */
    public void setTrailingIcon(Node trailingIcon) {
        this.trailingIcon.set(trailingIcon);
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

    //endregion Getters and Setters

    //region StyleableProperties Invalidate Functions
    //*****************************************************************
    // StyleableProperties Invalidate Functions
    //*****************************************************************

    /**
     * Called when the 'floatMode' property is invalidated. Changes the pseudo-class state according to the new value of the 'floatMode' property.
     *
     * @param prop
     *         The StyleableObjectProperty representing the 'floatMode' property.
     */
    private void floatModeInvalidated(StyleableObjectProperty<FloatMode> prop, FloatMode oldValue, Consumer<FloatMode> oldValueSetter) {
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
    private void textFieldModeInvalidated(StyleableObjectProperty<TextFieldMode> prop, TextFieldMode oldValue, Consumer<TextFieldMode> oldValueSetter) {
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
    private void updatePseudoClassStates() {
        pseudoClassStateChanged(TEXT_FIELD_FILLED_MODE_PSEUDO_CLASS, isTextModeFilled.get());
        pseudoClassStateChanged(FLOAT_BORDER_MODE_PSEUDO_CLASS, isFloatModeBorder.get());
        pseudoClassStateChanged(FLOAT_INSIDE_MODE_PSEUDO_CLASS, isFloatModeInside.get());
        pseudoClassStateChanged(FLOAT_ABOVE_MODE_PSEUDO_CLASS, isFloatModeAbove.get());
    }

    //endregion StyleableProperties Invalidate Functions

    /**
     * Retrieves the CSS metadata associated with the class.
     *
     * @return An unmodifiable list of CssMetaData objects representing the CSS properties that can be applied to this class.
     */
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
