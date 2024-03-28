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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext;

import io.github.colindj1120.enhancedfx.base.beans.binding.EFXBooleanBinding;
import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol.base.EFXControlBase;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base.EFXTextBase;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base.FloatMode;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base.InnerTextField;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base.TextMode;
import io.github.colindj1120.enhancedfx.controls.skins.EFXTextFieldSkin;
import io.github.colindj1120.enhancedfx.utils.EFXPropertyUtils;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.converter.EnumConverter;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * An extended version of {@link TextField} that incorporates additional styling and functionality based on the Material Design principles for JavaFX applications. This class is part of the EnhancedFX library,
 * which aims to provide a richer set of UI components and utilities.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Floating labels: Labels that transition above the text field when it is focused or contains text.</li>
 *     <li>Customizable floating modes: Allows the floating label to be configured as always floating, floating inside, or floating above the text field.</li>
 *     <li>Extended styling capabilities: Offers more CSS properties for fine-grained control over the appearance.</li>
 *     <li>Animation support: Provides options to enable or disable animations for the floating label.</li>
 *     <li><b>Enhanced Skinning:</b> Utilizes a custom skin, {@link EFXTextFieldSkin}, to apply the enhanced features and styling, ensuring a cohesive user experience across different EnhancedFX
 *     components.</li>
 * </ul>
 *
 * <p>To customize the appearance and behavior of the {@code EFXTextField}, CSS can be used in conjunction with the exposed styleable properties. This allows developers to create a consistent look and feel
 * across their application while adhering to Material Design guidelines.</p>
 *
 * <p>The implementation ensures backward compatibility with existing {@link TextField} usage patterns, making it easy to integrate into existing projects. It extends {@code EFXTextBase}, inheriting its rich
 * set of features and further extending them to meet the specific needs of a text field component.</p>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 *     EFXTextField textField = new EFXTextField();
 *     textField.setPromptText("Username");
 *     textField.setFloatingText("Username");
 *     textField.setFloatMode(FloatMode.ABOVE);
 *     textField.setAlwaysFloating(true);
 *     // Apply custom CSS if needed
 *     textField.getStyleClass().add("custom-text-field");
 * }
 * </pre>
 *
 * <p>For styling and additional configuration, refer to the EnhancedFX CSS documentation. This class supports several pseudo-classes for state management and offers a comprehensive set of CSS properties for
 * customization.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextField
 * @see EFXTextBase
 * @see FloatMode
 * @see EFXStylesheets
 * @see CustomControlConfigurator
 * @see EFXTextFieldSkin
 */
public class EFXTextField extends EFXTextBase<TextField> implements InnerTextField<TextField> {
    private static final String ENHANCED_TEXT_FIELD_STYLE = "enhanced-text-field";

    protected static final StyleablePropertiesManager STYLES_MANAGER                   = new StyleablePropertiesManager(EFXTextBase.getClassCssMetaData());
    protected static final PseudoClass                FLOAT_MODE_BORDER_PSEUDO_CLASS   = PseudoClass.getPseudoClass("float-mode-border");
    protected static final PseudoClass                FLOAT_MODE_DISABLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("float-mode-border");
    protected static final PseudoClass                FLOAT_MODE_ABOVE_PSEUDO_CLASS    = PseudoClass.getPseudoClass("float-mode-above");
    protected static final PseudoClass                FLOAT_MODE_INSIDE_PSEUDO_CLASS   = PseudoClass.getPseudoClass("float-mode-inside");

    protected TextField             innerControl = new TextField();
    private   SimpleStringProperty  floatingText;
    private   SimpleBooleanProperty alwaysFloating;

    protected EFXStyleableObjectProperty<FloatMode> floatMode;

    protected EFXBooleanBinding isFloatAnimationEnabled;

    static {
        //region Float Mode
        //*****************************************************************
        // Float Mode
        //*****************************************************************

        CssFactory<EFXTextField, FloatMode> floatModeCssFactory;
        floatModeCssFactory = CssFactory.<EFXTextField, FloatMode>create()
                                        .property("-efx-float-mode")
                                        .converter(EnumConverter.getEnumConverter(FloatMode.class))
                                        .initialValue(FloatMode.DISABLED)
                                        .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.floatMode) && !(node.isTextModeFilled() && node.isFloatModeBorder()))
                                        .propertyGetterFunction(node -> node.floatMode);
        STYLES_MANAGER.addCssMetaData(floatModeCssFactory);

        //endregion Float Mode
    }

    /**
     * Factory method to create a new instance of {@code EFXTextField} with default text.
     *
     * @return A new instance of {@code EFXTextField} with no initial text.
     */
    public static EFXTextField create() {
        return EFXTextField.create("");
    }

    /**
     * Creates a new instance of {@code EFXTextField} with specified initial text.
     *
     * <p>This method serves as a factory method for creating {@code EFXTextField} objects, allowing for customization of the text field's initial text content.</p>
     *
     * <p>During the creation process, this method initializes the text field with the provided text and configures essential properties and bindings that dictate the control's behavior, such as floating label
     * animation and always floating label state.</p>
     *
     * <p>The method leverages {@code CustomControlConfigurator} to fluently apply configurations such as initializing boolean bindings and adding property invalidation listeners. These configurations enable
     * dynamic responses to property changes, enhancing the control's interactivity and visual feedback.</p>
     *
     * @param text
     *         The initial text to set in the {@code EFXTextField}.
     *
     * @return A newly created instance of {@code EFXTextField} initialized with the specified text.
     */
    public static EFXTextField create(String text) {
        EFXTextField efxTextField = EFXControlBase.create(EFXTextField.class);

        efxTextField.setText(text);

        CustomControlConfigurator.create(efxTextField)
                                 .initializeEFXBooleanBinding(efxTextField.getFloatAnimationEnabledBinding(), efxTextField, association -> efxTextField.isFloatAnimationEnabled = association)
                                 .addBooleanPropertyInvalidationListener(efxTextField.alwaysFloating, ignored -> efxTextField.requestLayout());

        return efxTextField;
    }

    /**
     * Constructs an instance of EFXTextField.
     *
     * <p>This constructor calls the `super()` method to initialize the parent class constructor. </p>
     */
    protected EFXTextField() {
        super();
    }

    /**
     * Initializes the {@code EFXTextField}, setting up default properties and behaviors.
     *
     * <p>This override of the {@code initialize} method provides specific initializations for the {@code EFXTextField}, like setting up default values for the floating text and the always floating state.</p>
     *
     * <p>The {@code floatingText} property is initialized with an empty string, indicating that no floating text is displayed by default. The {@code alwaysFloating} property is initialized to {@code false},
     * meaning that the floating label behavior (if enabled) will activate only when the text field is focused or contains text.</p>
     *
     * <p>This method is called during the construction process of the {@code EFXTextField} and ensures that the text field is appropriately configured with its unique features upon instantiation.</p>
     */
    @Override
    protected void initialize() {
        super.initialize();
        this.floatingText = new SimpleStringProperty(this, "floatingText", "");
        this.alwaysFloating = new SimpleBooleanProperty(this, "alwaysFloating", false);
    }

    //region EFXControlBase Functions
    //*****************************************************************
    // EFXControlBase Functions
    //*****************************************************************

    /**
     * Retrieves the current instance of {@code EFXTextField}. This overridden method ensures that the specific implementation of the control provided by this class is returned, adhering to the contract
     * specified in the superclass. The method is pivotal for obtaining the correct instance of the enhanced text field, especially when polymorphic behavior is expected in a hierarchy of controls.
     *
     * @return The {@code EFXTextField} instance represented by {@code this}, confirming the current object as the operative control. This design facilitates direct access to the enhanced text field's features
     *         and properties within its own context.
     */
    @Override
    protected EFXTextField getControl() {
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @param obs
     *         The observable object that triggered the theme change. This parameter allows for the implementation of observer patterns and can be used to react to specific changes in theme-related properties.
     * @param oldEFXTheme
     *         The previous theme that was applied to the control.
     * @param newEFXTheme
     *         The new theme that is being applied to the control.
     */
    @Override
    protected void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
        loadNewThemeHelper(EFXStylesheets.ENHANCED_TEXT_FIELD, oldEFXTheme, newEFXTheme);
    }

    /**
     * This method builds upon {@code setupStyleableProperties} from the superclass, and sets up the styleable properties for the {@code EFXTextField} control. It is responsible for initializing the
     * {@code floatMode} property.
     *
     * <h2>Properties:</h2>
     * <ul>
     *     <li>{@code floatMode}: Represents the floating state of the text field. It holds a value of the {@code FloatMode} enumeration.</li>
     * </ul>
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
     * <p>This method adds styleable properties specifically relevant to the EFXTextField control, supplementing and complementing those set up via the superclass method.</p>
     */
    @Override
    protected void setupStyleableProperties() {
        super.setupStyleableProperties();
        floatMode = EFXStyleableObjectProperty.<FloatMode>create()
                                              .bean(this)
                                              .name("floatMode")
                                              .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-float-mode"))
                                              .initialValue(FloatMode.DISABLED)
                                              .invalidatedCachedCallback(this::floatModeInvalidated)
                                              .build();
    }

    /**
     * This method builds upon {@code setupControl} from the superclass, and configures the initial setup for the custom text field control.
     *
     * <p>
     * <em>The method performs the following actions to ensure the control is correctly initialized:</em>
     * <ul>
     *     <li>Verifies the existence of a stylesheet specific to {@code ENHANCED_TEXT_FIELD} and retrieves its path.</li>
     *     <li>Creates a {@link CustomControlConfigurator} instance for this control, allowing for fluent configuration and application of styles and behaviors.</li>
     *     <li>Sets a predefined style class {@code ENHANCED_TEXT_FIELD_STYLE} to apply base styling rules from the stylesheet.</li>
     *     <li>Adds the stylesheet located at the verified path, ensuring the control adheres to the visual design specified in the CSS file.</li>
     * </ul>
     * </p>
     *
     * <p>This setup method is vital for ensuring that the enhanced text field is not only visually appealing but also functionally rich, supporting features like float animation and responsive styling
     * adjustments.</p>
     *
     * @implNote The method relies on {@code checkStylesheetPathExists} to ensure the stylesheet path is valid and accessible. This is a critical step to prevent runtime errors due to missing resources.
     *         The {@code CustomNodeConfigurator} is used to apply configuration settings succinctly, showcasing an efficient and readable way to set up custom controls.
     */
    @Override
    protected void setupControl() {
        super.setupControl();
        String stylesheetPath = checkStylesheetPathExists(EFXStylesheets.ENHANCED_TEXT_FIELD, this.getClass());

        CustomControlConfigurator.create(this)
                                 .addStyleClass(ENHANCED_TEXT_FIELD_STYLE)
                                 .addStylesheet(stylesheetPath);
    }

    /**
     * This method builds upon {@code updatePseudoClassStates} from the superclass, and updates the pseudo class states of the component based on the current text mode states and float mode states. The pseudo
     * class states are updated for floating modes including disabled, border, inside, and above modes.
     *
     * @see EFXTextField#FLOAT_MODE_DISABLED_PSEUDO_CLASS
     * @see EFXTextField#FLOAT_MODE_BORDER_PSEUDO_CLASS
     * @see EFXTextField#FLOAT_MODE_INSIDE_PSEUDO_CLASS
     * @see EFXTextField#FLOAT_MODE_ABOVE_PSEUDO_CLASS
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
        return EFXTextFieldSkin.create(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField getInnerControl() {
        return innerControl;
    }

    //endregion EFXControlBase Functions

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
     *      <li>The {@code floatMode} of this text field is not disabled. The Floating label is a pattern where the placeholder text is transformed into a title for the form field when a field is selected
     *          or filled out.</li>
     *      <li>The {@code alwaysFloating} property is not true. This property specifies whether the label of this field should always be showing as if it's floating, regardless of where the field is filled
     *          or focused.</li>
     *      <li>Both {@code getText()} and {@code getPromptText()} methods return an empty string.</li>
     * </ul>
     * </p>
     *
     * <p>The returned BooleanBinding is built by the {@code Bindings.createBooleanBinding()} method. The binding depends on the {@code floatMode}, {@code alwaysFloating} properties, as well as the text and
     * promptText properties of this text field. Thus, any changes to these properties will cause the binding to be recalculated.</p>
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
     * Returns the EFXStyleableObjectProperty representing the float mode.
     *
     * @return the EFXStyleableObjectProperty<FloatMode> representing the float mode.
     */
    public EFXStyleableObjectProperty<FloatMode> floatModeProperty() {
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
     * Returns an EFXBooleanBinding representing the property that indicates whether float animation is enabled.
     *
     * @return a EFXBooleanBinding indicating whether float animation is enabled
     */
    public EFXBooleanBinding isFloatAnimationEnabledProperty() {
        return isFloatAnimationEnabled;
    }

    /**
     * Returns the EFXBooleanBinding object for determining if float animation is enabled.
     *
     * @return the EFXBooleanBinding object representing the state of float animation.
     */
    public EFXBooleanBinding isFloatAnimationEnabledAssociation() {
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
     * Handles the invalidation of the float mode property for an EFXTextField.
     *
     * <p>This method ensures that the float mode is compatible with the current text mode of the field. Specifically, it prevents setting the float mode to {@code FloatMode.BORDER} when the text mode is
     * filled, enforcing consistent visual styling.</p>
     *
     * <p>When an incompatible mode change is attempted, the method reverts the float mode to its previous value and throws an {@code IllegalArgumentException} to signal the error. Additionally, this method
     * updates the pseudo-class states associated with the float mode to reflect the current mode accurately.</p>
     *
     * <p>
     * <em>Pseudo-class states changed:</em>
     * <ul>
     *     <li>{@code FLOAT_MODE_DISABLED_PSEUDO_CLASS} - Applied when float mode is disabled.</li>
     *     <li>{@code FLOAT_MODE_BORDER_PSEUDO_CLASS} - Applied when float mode is set to border.</li>
     *     <li>{@code FLOAT_MODE_INSIDE_PSEUDO_CLASS} - Applied when float mode is set to inside.</li>
     *     <li>{@code FLOAT_MODE_ABOVE_PSEUDO_CLASS} - Applied when float mode is set to above.</li>
     * </ul>
     * </p>
     *
     * <p>This method is crucial for maintaining the visual coherence of the text field by preventing incompatible configurations between text and float modes.</p>
     *
     * @param prop
     *         The {@code EFXStyleableObjectProperty} representing the float mode.
     * @param oldValue
     *         The previous value of the float mode property.
     * @param oldValueSetter
     *         A {@code Consumer} that accepts the previous float mode value, allowing it to be restored if needed.
     *
     * @throws IllegalArgumentException
     *         if the float mode is set to {@code FloatMode.BORDER} when the text mode is filled.
     */
    private void floatModeInvalidated(EFXStyleableObjectProperty<FloatMode> prop, FloatMode oldValue, Consumer<FloatMode> oldValueSetter) {
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
     * Overrides the {@code textModeInvalidated} method to include validation against the current float mode.
     *
     * <p>Specifically, it prevents setting the text mode to filled if the float mode is currently set to border.</p>
     *
     * <p>This method enforces that the text mode and float mode are compatible, maintaining the visual integrity of the text field. If an attempt is made to set an incompatible text mode, the method reverts
     * the text mode to its previous value and throws an {@code IllegalArgumentException}.</p>
     *
     * <p>The compatibility check is essential for ensuring that the text field's appearance remains coherent and as expected according to the design choices made via properties.</p>
     *
     * @param prop
     *         The {@code EFXStyleableObjectProperty} representing the text mode.
     * @param oldValue
     *         The previous value of the text mode property.
     * @param oldValueSetter
     *         A {@code Consumer} that accepts the previous text mode value, enabling it to be reverted if necessary.
     *
     * @throws IllegalArgumentException
     *         if the text mode is set to filled when the float mode is border.
     */
    @Override
    protected void textModeInvalidated(EFXStyleableObjectProperty<TextMode> prop, TextMode oldValue, Consumer<TextMode> oldValueSetter) {
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
        return EFXTextField.STYLES_MANAGER.getCssMetaDataList();
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
