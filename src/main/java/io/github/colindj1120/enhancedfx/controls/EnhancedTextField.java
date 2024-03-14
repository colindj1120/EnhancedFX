/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/EnhancedFX).
 *
 * EnhancedFX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EnhancedFX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with EnhancedFX.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.controls;

import io.github.colindj1120.enhancedfx.beans.property.extendedstyleableproperty.ExtendedStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.binding.associations.BooleanBindingAssociation;
import io.github.colindj1120.enhancedfx.controls.base.EnhancedTextBase;
import io.github.colindj1120.enhancedfx.controls.base.inner.innertext.InnerTextField;
import io.github.colindj1120.enhancedfx.enums.controls.enhancedtextbase.TextMode;
import io.github.colindj1120.enhancedfx.enums.controls.enhancedtextfield.FloatMode;
import io.github.colindj1120.enhancedfx.enums.styling.Stylesheets;
import io.github.colindj1120.enhancedfx.enums.styling.Theme;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.factories.styling.CssFactory;
import io.github.colindj1120.enhancedfx.factories.styling.ExtendedStyleableObjectPropertyFactory;
import io.github.colindj1120.enhancedfx.skins.EnhancedTextFieldSkin;
import io.github.colindj1120.enhancedfx.styling.StyleablePropertiesManager;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.EnumConverter;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import static io.github.colindj1120.enhancedfx.utils.PropertyUtils.checkProperty;

/**
 * An extended version of {@link TextField} that incorporates additional styling and functionality based on the Material Design principles for JavaFX applications. This class is part of the EnhancedFX library,
 * which aims to provide a richer set of UI components and utilities.
 *
 * <p>
 * <em>The {@code EnhancedTextField} enhances the standard {@link TextField} by introducing features such as:</em>
 * <ul>
 *     <li>Floating labels: Labels that transition above the text field when it is focused or contains text.</li>
 *     <li>Customizable floating modes: Allows the floating label to be configured as always floating, floating inside, or floating above the text field.</li>
 *     <li>Extended styling capabilities: Offers more CSS properties for fine-grained control over the appearance.</li>
 *     <li>Animation support: Provides options to enable or disable animations for the floating label.</li>
 *     <li><b>Enhanced Skinning:</b> Utilizes a custom skin, {@link EnhancedTextFieldSkin}, to apply the enhanced features and styling, ensuring a cohesive user experience across different EnhancedFX
 *     components.</li>
 * </ul>
 * </p>
 *
 * <p>
 * To customize the appearance and behavior of the {@code EnhancedTextField}, CSS can be used in conjunction with the exposed styleable properties. This allows developers to create a consistent look and feel
 * across their application while adhering to Material Design guidelines.
 * </p>
 *
 * <p>
 * The implementation ensures backward compatibility with existing {@link TextField} usage patterns, making it easy to integrate into existing projects. It extends {@code EnhancedTextBase}, inheriting its
 * rich set of features and further extending them to meet the specific needs of a text field component.
 * </p>
 *
 * <h2>Usage Example</h2>
 * <p>
 * <pre>
 *     EnhancedTextField textField = new EnhancedTextField();
 *     textField.setPromptText("Username");
 *     textField.setFloatingText("Username");
 *     textField.setFloatMode(FloatMode.ABOVE);
 *     textField.setAlwaysFloating(true);
 *     // Apply custom CSS if needed
 *     textField.getStyleClass().add("custom-text-field");
 * </pre>
 * </p>
 *
 * <p>
 * For styling and additional configuration, refer to the EnhancedFX CSS documentation. This class supports several pseudo-classes for state management and offers a comprehensive set of CSS properties for
 * customization.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextField
 * @see EnhancedTextBase
 * @see FloatMode
 * @see Stylesheets
 * @see CustomControlConfigurator
 * @see EnhancedTextFieldSkin
 */
public class EnhancedTextField extends EnhancedTextBase<TextField> implements InnerTextField<TextField> {
    private static final String ENHANCED_TEXT_FIELD_STYLE = "enhanced-text-field";

    protected static final StyleablePropertiesManager STYLES_MANAGER                   = new StyleablePropertiesManager(io.github.colindj1120.enhancedfx.controls.base.EnhancedTextBase.getClassCssMetaData());
    protected static final PseudoClass                FLOAT_MODE_BORDER_PSEUDO_CLASS   = PseudoClass.getPseudoClass("float-mode-border");
    protected static final PseudoClass                FLOAT_MODE_DISABLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("float-mode-border");
    protected static final PseudoClass                FLOAT_MODE_ABOVE_PSEUDO_CLASS    = PseudoClass.getPseudoClass("float-mode-above");
    protected static final PseudoClass                FLOAT_MODE_INSIDE_PSEUDO_CLASS   = PseudoClass.getPseudoClass("float-mode-inside");

    protected       TextField             innerControl   = new TextField();
    protected final SimpleStringProperty  floatingText   = new SimpleStringProperty(this, "floatingText", "");
    protected final SimpleBooleanProperty alwaysFloating = new SimpleBooleanProperty(this, "alwaysFloating", false);

    protected ExtendedStyleableObjectProperty<FloatMode> floatMode;

    protected BooleanBindingAssociation isFloatAnimationEnabled;

    static {
        //region Float Mode
        //*****************************************************************
        // Float Mode
        //*****************************************************************

        CssFactory<EnhancedTextField, FloatMode> floatModeCssFactory;
        floatModeCssFactory = CssFactory.<EnhancedTextField, FloatMode>create()
                                        .property("-efx-float-mode")
                                        .converter(EnumConverter.getEnumConverter(FloatMode.class))
                                        .initialValue(FloatMode.DISABLED)
                                        .isSettableFunction(node -> checkProperty(node.floatMode) && !(node.isTextModeFilled() && node.isFloatModeBorder()))
                                        .propertyGetterFunction(node -> node.floatMode);
        STYLES_MANAGER.addCssMetaData(floatModeCssFactory);

        //endregion Float Mode
    }

    /**
     * Constructs a new {@code EnhancedTextField} with no initial text.
     *
     * <p>
     * This constructor initializes a blank {@code EnhancedTextField} component. It performs any necessary setup inherited from the superclass and prepares the text field for use, with no initial text set. This
     * is suitable for scenarios where the text field's content will be determined post-construction, or when you wish to start with an empty text field.
     * </p>
     */
    public EnhancedTextField() {
        super();
        init();
    }

    /**
     * Constructs a new {@code EnhancedTextField} with the specified initial text.
     *
     * <p>
     * This constructor allows for the creation of an {@code EnhancedTextField} with preset text, facilitating scenarios where the initial content is known at the time of the text field's instantiation. This
     * can be particularly useful for forms or UI components that are pre-populated with data for the user to edit or review.
     * </p>
     *
     * <p>
     * After calling the superclass constructor to perform any necessary initialization inherited from the superclass, the provided text is set as the current content of the text field.
     * </p>
     *
     * @param text
     *         The initial text to set in the text field; if {@code null}, the text field will be initialized empty but will not throw an exception. Subsequent attempts to set null may be handled differently
     *         based on the text field's configuration.
     */
    public EnhancedTextField(String text) {
        super();
        innerControl.setText(text);
        init();
    }

    private void init() {
        CustomControlConfigurator.create(this)
                                 .setBooleanAssociationBinding(getFloatAnimationEnabledBinding(), this, association -> isFloatAnimationEnabled = association)
                                 .addBooleanPropertyInvalidationListener(alwaysFloating, invalidated -> requestLayout());
    }

    //region EnhancedControlBase Functions
    //*****************************************************************
    // EnhancedControlBase Functions
    //*****************************************************************

    /**
     * Retrieves the current instance of {@code EnhancedTextField}. This overridden method ensures that the specific implementation of the control provided by this class is returned, adhering to the contract
     * specified in the superclass. The method is pivotal for obtaining the correct instance of the enhanced text field, especially when polymorphic behavior is expected in a hierarchy of controls.
     *
     * @return The {@code EnhancedTextField} instance represented by {@code this}, confirming the current object as the operative control. This design facilitates direct access to the enhanced text field's
     *         features and properties within its own context.
     */
    @Override
    protected EnhancedTextField getControl() {
        return this;
    }

    @Override
    protected void loadNewTheme(Observable obs, Theme oldTheme, Theme newTheme) {
        loadNewThemeHelper(Stylesheets.ENHANCED_TEXT_FIELD, oldTheme, newTheme);
    }

    /**
     * This method builds upon {@code setupStyleableProperties} from the superclass, and sets up the styleable properties for the {@code EnhancedTextField} control. It is responsible for initializing the
     * {@code floatMode} property.
     *
     * <p>
     * The {@code floatMode} property represents the floating state of the text field, like being disabled or bordered on the graphical interface. It holds a value of the {@code FloatMode} enumeration.
     * </p>
     *
     * <p>
     * <em>The properties are initialized with:</em>
     * <ul>
     *     <li><b>Bean</b>: This instance serving as the bean in which the property resides.</li>
     *     <li><b>Name</b>: The name of the property, set to either "floatMode" or "textMode".</li>
     *     <li><b>CssMetaData</b>: The CSS metadata linked to the property, obtained by searching for "-float-mode" or "-text-field-mode".</li>
     *     <li><b>DefaultValue</b>: The initial value of the property, set to `FloatMode.DISABLED` or `TextMode.OUTLINED` respectively.</li>
     *     <li><b>InvalidatedCachedCallback</b>: The callback method that executes when the property becomes invalid.</li>
     * </ul>
     * </p>
     *
     * <p>
     * This (floatMode) process uses the {@code ExtendedStyleableObjectPropertyFactory.builder()} to configure and initialize the property.
     * </p>
     *
     * <p>
     * This method adds styleable properties specifically relevant to the EnhancedTextField control, supplementing and complementing those set up via the superclass method.
     * </p>
     */
    @Override
    protected void setupStyleableProperties() {
        super.setupStyleableProperties();
        floatMode = ExtendedStyleableObjectPropertyFactory.<FloatMode>builder()
                                                          .bean(this)
                                                          .name("floatMode")
                                                          .type(FloatMode.class)
                                                          .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-float-mode"))
                                                          .defaultValue(FloatMode.DISABLED)
                                                          .invalidatedCachedCallback(this::floatModeInvalidated)
                                                          .build();
    }

    /**
     * This method builds upon {@code setupControl} from the superclass, and configures the initial setup for the custom text field control. This method extends the setup process defined in its parent class,
     * focusing on applying a specific stylesheet and setting initial style properties and bindings for the control.
     *
     * <p>
     * <em>The method performs the following actions to ensure the control is correctly initialized:</em>
     * <ul>
     *     <li>Verifies the existence of a stylesheet specific to {@code ENHANCED_TEXT_FIELD} and retrieves its path. This stylesheet is crucial for applying the custom appearance and behaviors defined for
     *     the enhanced text field.</li>
     *     <li>Creates a {@link CustomControlConfigurator} instance for this control, allowing for fluent configuration and application of styles and behaviors.</li>
     *     <li>Sets a predefined style class {@code ENHANCED_TEXT_FIELD_STYLE} to apply base styling rules from the stylesheet.</li>
     *     <li>Adds the stylesheet located at the verified path, ensuring the control adheres to the visual design specified in the CSS file.</li>
     *     <li>Binds a boolean property to enable or disable float animation through a custom association binding, allowing for dynamic updates based on control state or global settings.</li>
     *     <li>Adds an invalidation listener to the {@code alwaysFloating} boolean property, ensuring the layout is requested whenever this property's value changes. This is critical for maintaining the
     *     correct appearance and behavior of the floating label feature.</li>
     * </ul>
     * </p>
     *
     * <p>
     * This setup method is vital for ensuring that the enhanced text field is not only visually appealing but also functionally rich, supporting features like float animation and responsive styling
     * adjustments.
     * </p>
     *
     * @implNote The method relies on {@code checkStylesheetPathExists} to ensure the stylesheet path is valid and accessible. This is a critical step to prevent runtime errors due to missing resources.
     *         The {@code CustomControlConfigurator} is used to apply configuration settings succinctly, showcasing an efficient and readable way to set up custom controls.
     */
    @Override
    protected void setupControl() {
        super.setupControl();
        String stylesheetPath = checkStylesheetPathExists(Stylesheets.ENHANCED_TEXT_FIELD, this.getClass());

        CustomControlConfigurator.create(this)
                                 .addStyleClass(ENHANCED_TEXT_FIELD_STYLE)
                                 .addStylesheet(stylesheetPath);
    }

    /**
     * This method builds upon {@code updatePseudoClassStates} from the superclass, and updates the pseudo class states of the component based on the current text mode states and float mode states. The pseudo
     * class states are updated for floating modes including disabled, border, inside, and above modes.
     *
     * @see EnhancedTextField#FLOAT_MODE_DISABLED_PSEUDO_CLASS
     * @see EnhancedTextField#FLOAT_MODE_BORDER_PSEUDO_CLASS
     * @see EnhancedTextField#FLOAT_MODE_INSIDE_PSEUDO_CLASS
     * @see EnhancedTextField#FLOAT_MODE_ABOVE_PSEUDO_CLASS
     */
    @Override
    protected void updatePseudoClassStates() {
        super.updatePseudoClassStates();
        pseudoClassStateChanged(FLOAT_MODE_DISABLED_PSEUDO_CLASS, isFloatModeDisabled());
        pseudoClassStateChanged(FLOAT_MODE_BORDER_PSEUDO_CLASS, isFloatModeBorder());
        pseudoClassStateChanged(FLOAT_MODE_INSIDE_PSEUDO_CLASS, isFloatModeInside());
        pseudoClassStateChanged(FLOAT_MODE_ABOVE_PSEUDO_CLASS, isFloatModeAbove());
    }

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
    public TextField getInnerControl() {
        return innerControl;
    }

    //endregion EnhancedControlBase Functions

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Creates a BooleanBinding that indicates whether the float animation should be enabled for this text field.
     *
     * <p>
     * <em>The float animation is considered to be enabled in the following scenario:</em>
     * <ul>
     *      <li> The {@code floatMode} of this text field is not disabled. The Floating label is a pattern where the placeholder text is transformed into a title for the form field when a field is selected
     *      or filled out.</li>
     *      <li> The {@code alwaysFloating} property is not true. This property specifies whether the label of this field should always be showing as if it's floating, regardless of where the field is filled
     *      or focused.</li>
     *      <li> Both {@code getText()} and {@code getPromptText()} methods return an empty string.</li>
     * </ul>
     * </p>
     *
     * <p>
     * The returned BooleanBinding is built by the {@code Bindings.createBooleanBinding()} method. The binding depends on the {@code floatMode}, {@code alwaysFloating} properties, as well as the text and
     * promptText properties of this text field. Thus, any changes to these properties will cause the binding to be recalculated.
     * </p>
     *
     * @return A BooleanBinding indicating whether the float animation should be enabled.
     */
    private BooleanBinding getFloatAnimationEnabledBinding() {
        Callable<Boolean> initialFloatAnimEnabled    = () -> !isFloatModeDisabled() && !isAlwaysFloating() && getText().isEmpty() && getPromptText().isEmpty();
        Observable[]      floatAnimationDependencies = {floatMode, alwaysFloating, textProperty(), promptTextProperty()};

        return Bindings.createBooleanBinding(initialFloatAnimEnabled, floatAnimationDependencies);
    }

    /**
     * Retrieves the value of the isFloatModeBorder property.
     *
     * @return the value of the isFloatModeBorder property, indicating whether the float mode border is enabled or disabled.
     */
    public Boolean isFloatModeBorder() {
        return floatMode.get() == FloatMode.BORDER;
    }

    /**
     * Determines if the float mode is currently inside.
     *
     * @return {@code true} if the float mode is inside, {@code false} otherwise.
     */
    public Boolean isFloatModeInside() {
        return floatMode.valueEquals(FloatMode.INSIDE);
    }

    /**
     * Returns whether the float mode is above a certain value.
     *
     * @return true if the float mode is above the specified value, false otherwise.
     */
    public Boolean isFloatModeAbove() {
        return floatMode.get() == FloatMode.ABOVE;
    }

    /**
     * Returns the value indicating whether the float mode is disabled.
     *
     * @return {@code true} if the float mode is disabled, otherwise {@code false}.
     */
    public Boolean isFloatModeDisabled() {
        return floatMode.valueEquals(FloatMode.DISABLED);
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
     * Returns a BooleanBindingAssociation representing the property that indicates whether float animation is enabled.
     *
     * @return a BooleanBindingAssociation indicating whether float animation is enabled
     */
    public BooleanBindingAssociation isFloatAnimationEnabledProperty() {
        return isFloatAnimationEnabled;
    }

    /**
     * Returns the BooleanBindingAssociation object for determining if float animation is enabled.
     *
     * @return the BooleanBindingAssociation object representing the state of float animation.
     */
    public BooleanBindingAssociation isFloatAnimationEnabledAssociation() {
        return isFloatAnimationEnabled;
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
        if (isTextModeFilled() && mode.equals(FloatMode.BORDER)) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.setValue(oldValue);
            throw new IllegalArgumentException("Float Mode cannot be set to Border mode when the Text Field Mode is Filled");
        }

        pseudoClassStateChanged(FLOAT_MODE_DISABLED_PSEUDO_CLASS, mode.equals(FloatMode.DISABLED));
        pseudoClassStateChanged(FLOAT_MODE_BORDER_PSEUDO_CLASS, mode.equals(FloatMode.BORDER));
        pseudoClassStateChanged(FLOAT_MODE_INSIDE_PSEUDO_CLASS, mode.equals(FloatMode.INSIDE));
        pseudoClassStateChanged(FLOAT_MODE_ABOVE_PSEUDO_CLASS, mode.equals(FloatMode.ABOVE));

        if (!oldValue.equals(mode)) {
            oldValueSetter.accept(mode);
        }
    }

    /**
     * This method is invoked when the text mode property is invalidated, and performs additional validation and actions based on the invalidated value of the property before calling its parent method.
     *
     * @param prop
     *         The styleable object property representing the text mode.
     * @param oldValue
     *         The previous value of the text mode property.
     * @param oldValueSetter
     *         A consumer that can be used to set the old value back.
     *
     * @throws IllegalArgumentException
     *         If the text mode is set to "filled" mode and the float mode is "border".
     */
    @Override
    protected void textModeInvalidated(StyleableObjectProperty<TextMode> prop, TextMode oldValue, Consumer<TextMode> oldValueSetter) {
        if (isTextModeFilled() && isFloatModeBorder()) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.setValue(oldValue);
            throw new IllegalArgumentException("Text Mode cannot be set to filled mode when the float mode is border");
        }

        super.textModeInvalidated(prop, oldValue, oldValueSetter);
    }

    //endregion StyleableProperties Invalidate Functions

    //region Css Metadata
    //*****************************************************************
    // Css Metadata
    //*****************************************************************

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

    //endregion Css Metadata
}
