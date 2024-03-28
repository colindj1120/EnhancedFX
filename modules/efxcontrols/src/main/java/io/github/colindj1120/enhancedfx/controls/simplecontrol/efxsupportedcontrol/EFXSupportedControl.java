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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxsupportedcontrol;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableDoubleProperty;
import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.enums.EFXState;
import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol.EFXControl;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxsupportedcontrol.base.SupportingTextPosition;
import io.github.colindj1120.enhancedfx.utils.EFXPropertyUtils;
import io.github.colindj1120.enhancedfx.utils.converters.styleconverters.DoubleStyleConverter;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.converter.EnumConverter;
import javafx.scene.control.Control;

import java.util.List;
import java.util.function.Consumer;

/**
 * The {@code EFXSupportedControl} abstract class extends {@code EFXControl} to introduce additional features and behaviors tailored for controls that support additional textual content and customizable
 * positioning.
 *
 * <p>It leverages CSS and pseudo-classes to provide a versatile and dynamic approach to styling and behavior customization.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><strong>Supporting Text:</strong> Allows embedding additional text within the control, enhancing its informational capacity.</li>
 *     <li><strong>Customizable Text Position:</strong> Offers the ability to customize the position of the supporting text relative to the control, with predefined positions (top, bottom, left, right).</li>
 *     <li><strong>Dynamic Theming Support:</strong> Seamlessly integrates with the EnhancedFX theming system, ensuring that the control and its supporting text adhere to the current theme.</li>
 *     <li><strong>State-driven Styling:</strong> Utilizes pseudo-classes to reflect the state of the control, including whether supporting text is enabled or disabled, and its position.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * public class MyCustomEFXControl extends EFXSupportedControl<Button> {
 *     public MyCustomEFXControl() {
 *         super();
 *         setSupportingText("Optional Info");
 *         setSupportingTextPosition(SupportingTextPosition.BOTTOM);
 *         setSupportingTextState(EFXState.ENABLED);
 *     }
 *
 *     @Override
 *     protected EFXSupportedControl<?> getControl() {
 *         return this;
 *     }
 *
 *     @Override
 *     protected void setupStyleableProperties() {
 *         super.setupStyleableProperties();
 *         // Additional styleable property setup can be performed here.
 *     }
 *
 *     @Override
 *     protected void setupControl() {
 *         super.setupControl();
 *         // Control-specific setup including supporting text configuration.
 *     }
 *
 *     @Override
 *     public Button getInnerControl() {
 *         // Return the Button control wrapped by this EFXSupportedControl
 *         return new Button("My Custom Button");
 *     }
 * }
 * }
 * </pre>
 *
 * <p>This example demonstrates how to extend {@code EFXSupportedControl} to create a custom control that uses the additional capabilities provided by this class. Developers can further customize the
 * control's appearance and behavior by overriding and implementing the abstract methods defined by {@code EFXControl} and {@code EFXSupportedControl}.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXControl
 */
public abstract class EFXSupportedControl<T extends Control> extends EFXControl<T> {
    private static final String ENHANCED_SUPPORTING_TEXT_STYLE = "enhanced-supporting-text";

    private static final   StyleablePropertiesManager STYLES_MANAGER                          = new StyleablePropertiesManager(EFXControl.getClassCssMetaData());
    protected static final PseudoClass                SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS    = PseudoClass.getPseudoClass("supporting-text-enabled");
    protected static final PseudoClass                SUPPORTING_TEXT_DISABLED_PSEUDO_CLASS   = PseudoClass.getPseudoClass("supporting-text-disabled");
    protected static final PseudoClass                SUPPORTING_TEXT_POS_TOP_PSEUDO_CLASS    = PseudoClass.getPseudoClass("supporting-text-pos-top");
    protected static final PseudoClass                SUPPORTING_TEXT_POS_BOTTOM_PSEUDO_CLASS = PseudoClass.getPseudoClass("supporting-text-pos-bottom");
    protected static final PseudoClass                SUPPORTING_TEXT_POS_LEFT_PSEUDO_CLASS   = PseudoClass.getPseudoClass("supporting-text-pos-left");
    protected static final PseudoClass                SUPPORTING_TEXT_POS_RIGHT_PSEUDO_CLASS  = PseudoClass.getPseudoClass("supporting-text-pos-right");

    protected SimpleStringProperty                               supportingText;
    protected EFXStyleableObjectProperty<EFXState>               supportingTextState;
    protected EFXStyleableObjectProperty<SupportingTextPosition> supportingTextPosition;
    protected EFXStyleableDoubleProperty                         supportingTextXOffset;
    protected EFXStyleableDoubleProperty                         supportingTextYOffset;

    static {
        //region Supporting Text Position
        //*****************************************************************
        // Supporting Text Position
        //*****************************************************************

        CssFactory<EFXSupportedControl<?>, SupportingTextPosition> supportingTextPositionCssFactory;
        supportingTextPositionCssFactory = CssFactory.<EFXSupportedControl<?>, SupportingTextPosition>create()
                                                     .property("-efx-supporting-text-position")
                                                     .converter(EnumConverter.getEnumConverter(SupportingTextPosition.class))
                                                     .initialValue(SupportingTextPosition.LEFT)
                                                     .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.supportingTextPosition) && node.isSupportingTextEnabled())
                                                     .propertyGetterFunction(node -> node.supportingTextPosition);
        STYLES_MANAGER.addCssMetaData(supportingTextPositionCssFactory);

        //endregion Supporting Text Position

        //region Supporting Text EFXState
        //*****************************************************************
        // Supporting Text EFXState
        //*****************************************************************

        CssFactory<EFXSupportedControl<?>, EFXState> supportingTextStateCssFactory;
        supportingTextStateCssFactory = CssFactory.<EFXSupportedControl<?>, EFXState>create()
                                                  .property("-efx-supporting-text-state")
                                                  .converter(EnumConverter.getEnumConverter(EFXState.class))
                                                  .initialValue(EFXState.DISABLED)
                                                  .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.supportingTextState))
                                                  .propertyGetterFunction(node -> node.supportingTextState);
        STYLES_MANAGER.addCssMetaData(supportingTextStateCssFactory);

        //endregion Supporting Text EFXState

        //region Supporting Text X Offset
        //*****************************************************************
        // Supporting Text X Offset
        //*****************************************************************

        CssFactory<EFXSupportedControl<?>, Double> supportingTextXOffsetCssFactory;
        supportingTextXOffsetCssFactory = CssFactory.<EFXSupportedControl<?>, Double>create()
                                                    .property("-efx-supporting-text-x-offset")
                                                    .converter(DoubleStyleConverter.getInstance())
                                                    .initialValue(0.0)
                                                    .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.supportingTextXOffset))
                                                    .propertyGetterFunction(node -> node.supportingTextXOffset);
        STYLES_MANAGER.addCssMetaData(supportingTextXOffsetCssFactory);

        //endregion Supporting Text X Offset

        //region Supporting Text Y Offset
        //*****************************************************************
        // Supporting Text Y Offset
        //*****************************************************************

        CssFactory<EFXSupportedControl<?>, Double> supportingTextYOffsetCssFactory;
        supportingTextYOffsetCssFactory = CssFactory.<EFXSupportedControl<?>, Double>create()
                                                    .property("-efx-supporting-text-y-offset")
                                                    .converter(DoubleStyleConverter.getInstance())
                                                    .initialValue(0.0)
                                                    .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.supportingTextYOffset))
                                                    .propertyGetterFunction(node -> node.supportingTextYOffset);
        STYLES_MANAGER.addCssMetaData(supportingTextYOffsetCssFactory);

        //endregion Supporting Text Y Offset

    }

    /**
     * Constructs an instance of EFXSupportedControl.
     *
     * <p>This constructor calls the `super()` method to initialize the parent class constructor. </p>
     */
    protected EFXSupportedControl() {
        super();
    }

    /**
     * Initializes the custom control by invoking the initialization routine of the parent class and setting up additional properties specific to this control.
     *
     * <p>This overridden method ensures that the control is ready for use immediately after instantiation, with all necessary setup and configuration in place.</p>
     *
     * <h2>Key aspects include:</h2>
     * <ul>
     *     <li><strong>Parent Initialization:</strong> Calls the {@code initialize()} method of the superclass to ensure that any setup required by the base class is performed. This may include setting up
     *     default styles, event listeners, or other foundational aspects of the control.</li>
     *     <li><strong>Supporting Text Property Initialization:</strong> Initializes the {@code supportingText} property, a {@link SimpleStringProperty}, which is intended to hold additional information or
     *     context about the control's current state. The property is initialized with an empty string, indicating no supporting text by default.</li>
     * </ul>
     *
     * <p>This method is part of the control's lifecycle and is automatically called during the control's construction process. It is not intended to be called directly from outside the control's
     * implementation.</p>
     */
    @Override
    protected void initialize() {
        super.initialize();
        this.supportingText = new SimpleStringProperty(this, "supportingText", "");
    }

    //region EFXControlBase Functions
    //*****************************************************************
    // EFXControlBase Functions
    //*****************************************************************

    /**
     * Retrieves the current instance of {@link EFXSupportedControl}. This method serves as a contract for subclasses, requiring them to provide their specific implementation of the control. The control is
     * expected to be enhanced and supported, adhering to the specific needs of the implementing UI component.
     *
     * @return The {@link EFXSupportedControl} instance associated with this UI component. This control is customized and configured to meet the UI requirements.
     */
    @Override
    protected abstract EFXSupportedControl<?> getControl();

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
        loadNewThemeHelper(EFXStylesheets.ENHANCED_SUPPORTED_CONTROL, oldEFXTheme, newEFXTheme);
    }

    /**
     * This method builds upon {@code setupStyleableProperties} from the superclass, and sets up the styleable properties for this subclass instance. It is responsible for initializing the
     * {@code supportingTextEFXState} and {@code supportingTextPosition} properties of this object's state.
     *
     * <h2>Properties:</h2>
     * <ul>
     *     <li>{@code supportingTextEFXState}: Represents the position of supporting text in the interface. It holds a value of the {@code SupportingTextPosition} enumeration.</li>
     *     <li>{@code supportingTextPosition}: Represents the position of supporting text in the interface. It holds a value of the {@code SupportingTextPosition} enumeration.</li>
     * </ul>
     *
     * <p>
     * <em>Both properties are initialized with:</em>
     * <ul>
     *     <li><b>Bean</b>: This instance serving as the bean in which the property resides.</li>
     *     <li><b>Name</b>: The name of the property, set to either "supportingTextEFXState" or "supportingTextPosition".</li>
     *     <li><b>CssMetaData</b>: The CSS metadata linked to the property, obtained by searching for "-supporting-text-state" or "-supporting-text-position".</li>
     *     <li><b>DefaultValue</b>: The initial value of the property, set to `EFXState.DISABLED` or `SupportingTextPosition.LEFT` respectively.</li>
     *     <li><b>InvalidatedPropCallback/InvalidatedCachedCallback</b>: The callback method that executes when the property becomes invalid.</li>
     * </ul>
     * </p>
     *
     * <p>This process uses the {@code ExtendedStyleableObjectPropertyFactory.builder()} to configure and initialize the property.</p>
     *
     * <p>This subclass method adds styleable properties relevant to the specifics of this subclass, supplementing and complementing those set up via the superclass method.</p>
     */
    protected void setupStyleableProperties() {
        supportingTextState = EFXStyleableObjectProperty.<EFXState>create()
                                                        .bean(this)
                                                        .name("supportingTextEFXState")
                                                        .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-supporting-text-state"))
                                                        .initialValue(EFXState.DISABLED)
                                                        .invalidatedPropCallback(this::supportingTextStateInvalidated)
                                                        .build();

        supportingTextPosition = EFXStyleableObjectProperty.<SupportingTextPosition>create()
                                                           .bean(this)
                                                           .name("supportingTextPosition")
                                                           .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-supporting-text-position"))
                                                           .initialValue(SupportingTextPosition.LEFT)
                                                           .invalidatedCachedCallback(this::supportingTextPositionInvalidated)
                                                           .build();

        supportingTextXOffset = EFXStyleableDoubleProperty.create()
                                                          .bean(this)
                                                          .name("supportingTextXOffset")
                                                          .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-supporting-text-x-offset"))
                                                          .initialValue(0.0)
                                                          .build();

        supportingTextYOffset = EFXStyleableDoubleProperty.create()
                                                          .bean(this)
                                                          .name("supportingTextYOffset")
                                                          .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-supporting-text-y-offset"))
                                                          .initialValue(0.0)
                                                          .build();
    }

    /**
     * This method builds upon {@code setupControl} from the superclass, and sets up the control with necessary styling configurations. This method is designed to be overridden by subclasses, hence it's marked
     * as {@code protected}. It performs a series of operations to ensure the control is configured with the appropriate stylesheet and style class.
     *
     * <p>
     * <em>Actions:</em>
     * <ul>
     *     <li>Verifies the existence of the stylesheet path for the {@code ENHANCED_SUPPORTED_CONTROL} stylesheet, relevant to the current class's context.</li>
     *     <li>Creates a {@link CustomControlConfigurator} instance for the current control.</li>
     *     <li>Adds a predefined style class {@code ENHANCED_SUPPORTING_TEXT_STYLE} to the control.</li>
     *     <li>Adds the stylesheet, located at the verified path, to the control.</li>
     * </ul>
     * <br>
     * This setup process is essential for ensuring that the control adheres to a specific appearance and behavior defined by the {@code ENHANCED_SUPPORTED_CONTROL} and {@code ENHANCED_SUPPORTING_TEXT_STYLE}.
     * </p>
     *
     * @implNote This method relies on {@code checkStylesheetPathExists} to validate the stylesheet path's existence. It's crucial that this validation step is accurate to prevent runtime errors related
     *         to missing resources. The {@code CustomNodeConfigurator} is used to fluently apply configuration settings to the control, showcasing an example of the Builder pattern for setup tasks.
     */
    @Override
    protected void setupControl() {
        String stylesheetPath = checkStylesheetPathExists(EFXStylesheets.ENHANCED_SUPPORTED_CONTROL, this.getClass());

        CustomControlConfigurator.create(getControl())
                                 .addStyleClass(ENHANCED_SUPPORTING_TEXT_STYLE)
                                 .addStylesheet(stylesheetPath);
    }

    /**
     * This method builds upon {@code updatePseudoClassStates} from the superclass, and updates the pseudo class states of the component based on the current supporting text state and the current supporting
     * text position. The pseudo class states are updated for the enabled, disabled, position top, position bottom, position left, and position right modes.
     *
     * @see EFXSupportedControl#SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS
     * @see EFXSupportedControl#SUPPORTING_TEXT_DISABLED_PSEUDO_CLASS
     * @see EFXSupportedControl#SUPPORTING_TEXT_POS_TOP_PSEUDO_CLASS
     * @see EFXSupportedControl#SUPPORTING_TEXT_POS_BOTTOM_PSEUDO_CLASS
     * @see EFXSupportedControl#SUPPORTING_TEXT_POS_LEFT_PSEUDO_CLASS
     * @see EFXSupportedControl#SUPPORTING_TEXT_POS_RIGHT_PSEUDO_CLASS
     */
    @Override
    protected void updatePseudoClassStates() {
        pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS, isSupportingTextEnabled());
        pseudoClassStateChanged(SUPPORTING_TEXT_DISABLED_PSEUDO_CLASS, isSupportingTextDisabled());
        pseudoClassStateChanged(SUPPORTING_TEXT_POS_TOP_PSEUDO_CLASS, isSupportingTextPosTop());
        pseudoClassStateChanged(SUPPORTING_TEXT_POS_BOTTOM_PSEUDO_CLASS, isSupportingTextPosBottom());
        pseudoClassStateChanged(SUPPORTING_TEXT_POS_LEFT_PSEUDO_CLASS, isSupportingTextPosLeft());
        pseudoClassStateChanged(SUPPORTING_TEXT_POS_RIGHT_PSEUDO_CLASS, isSupportingTextPosRight());
    }

    //endregion EFXControlBase Functions

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Retrieves the supporting text of the EFXSupportedControl.
     *
     * @return The supporting text as a String.
     */
    public String getSupportingText() {
        return supportingText.get();
    }

    /**
     * Returns the supporting text property.
     *
     * @return The SimpleStringProperty representing the supporting text property.
     */
    public SimpleStringProperty supportingTextProperty() {
        return supportingText;
    }

    /**
     * Sets the supporting text for the control.
     *
     * @param supportingText
     *         the supporting text to set
     */
    public void setSupportingText(String supportingText) {
        this.supportingText.set(supportingText);
    }

    /**
     * Retrieves the current state of the supporting text.
     *
     * @return The current state of the supporting text as an EFXState object. Possible values are ENABLED and DISABLED.
     */
    public EFXState getSupportingTextState() {
        return supportingTextState.get();
    }

    /**
     * Retrieves the supporting text state property of the object. The supporting text state property is an instance of `EFXStyleableObjectProperty<EFXState>`.
     *
     * @return The supporting text state property of the object.
     */
    public EFXStyleableObjectProperty<EFXState> supportingTextStateProperty() {
        return supportingTextState;
    }

    /**
     * Sets the supporting text state of the EFXSupportedControl.
     * <p>
     * The supporting text state indicates whether the supporting text of the control is currently active and can interact with the user or inactive and cannot interact with the user .
     * </p>
     *
     * @param supportingTextEFXState
     *         The supporting text state to set (ENABLED or DISABLED) as a EFXState enum.
     */
    public void setSupportingTextState(EFXState supportingTextEFXState) {
        this.supportingTextState.set(supportingTextEFXState);
    }

    /**
     * Checks if the supporting text of the EFXSupportedControl is currently enabled.
     *
     * @return {@code true} if the supporting text is enabled, {@code false} otherwise.
     */
    public Boolean isSupportingTextEnabled() {
        return supportingTextState.valueEquals(EFXState.ENABLED);
    }

    /**
     * Checks if the supporting text is currently disabled.
     *
     * @return {@code true} if the supporting text is disabled, {@code false} otherwise
     */
    public Boolean isSupportingTextDisabled() {
        return supportingTextState.valueEquals(EFXState.DISABLED);
    }

    /**
     * Retrieves the position of the supporting text within the control.
     *
     * @return The SupportingTextPosition enum representing the position of the supporting text.
     */
    public SupportingTextPosition getSupportingTextPosition() {
        return supportingTextPosition.get();
    }

    /**
     * Retrieves the supporting text position property of the object. The supporting text position property is an instance of `EFXStyleableObjectProperty<SupportingTextPosition>`.
     *
     * @return The supporting text position property of the object.
     */
    public EFXStyleableObjectProperty<SupportingTextPosition> supportingTextPositionProperty() {
        return supportingTextPosition;
    }

    /**
     * Sets the position of the supporting text.
     *
     * @param supportingTextPosition
     *         The position of the supporting text.
     */
    public void setSupportingTextPosition(SupportingTextPosition supportingTextPosition) {
        this.supportingTextPosition.set(supportingTextPosition);
    }

    /**
     * Checks if the supporting text position is set to LEFT.
     *
     * @return {@code true} if the supporting text position is set to LEFT, {@code false} otherwise.
     */
    public Boolean isSupportingTextPosLeft() {
        return supportingTextPosition.valueEquals(SupportingTextPosition.LEFT);
    }

    /**
     * Checks if the supporting text position is set to RIGHT.
     *
     * @return {@code true} if the supporting text position is set to RIGHT, {@code false} otherwise.
     */
    public Boolean isSupportingTextPosRight() {
        return supportingTextPosition.valueEquals(SupportingTextPosition.RIGHT);
    }

    /**
     * Checks if the supporting text position is set to TOP.
     *
     * @return {@code true} if the supporting text position is set to TOP, {@code false} otherwise.
     */
    public Boolean isSupportingTextPosTop() {
        return supportingTextPosition.valueEquals(SupportingTextPosition.TOP);
    }

    /**
     * Checks if the supporting text position is set to BOTTOM.
     *
     * @return {@code true} if the supporting text position is set BOTTOM, {@code false} otherwise.
     */
    public Boolean isSupportingTextPosBottom() {
        return supportingTextPosition.valueEquals(SupportingTextPosition.BOTTOM);
    }

    /**
     * Returns the x-offset of the supporting text.
     *
     * @return the x-offset of the supporting text
     */
    public double getSupportingTextXOffset() {
        return supportingTextXOffset.get();
    }

    /**
     * Gets the supporting text x-offset property.
     *
     * @return the supporting text x-offset property
     */
    public EFXStyleableDoubleProperty supportingTextXOffsetProperty() {
        return supportingTextXOffset;
    }

    /**
     * Sets the x offset for the supporting text.
     *
     * @param supportingTextXOffset
     *         the x offset value to set
     */
    public void setSupportingTextXOffset(double supportingTextXOffset) {
        this.supportingTextXOffset.set(supportingTextXOffset);
    }

    /**
     * Returns the y-offset of the supporting text.
     *
     * @return the y-offset of the supporting text
     */
    public double getSupportingTextYOffset() {
        return supportingTextYOffset.get();
    }

    /**
     * Returns the supportingTextYOffset property.
     *
     * @return the supportingTextYOffset property
     */
    public EFXStyleableDoubleProperty supportingTextYOffsetProperty() {
        return supportingTextYOffset;
    }

    /**
     * Sets the y offset for supporting text.
     *
     * @param supportingTextYOffset
     *         The y offset value for supporting text.
     */
    public void setSupportingTextYOffset(double supportingTextYOffset) {
        this.supportingTextYOffset.set(supportingTextYOffset);
    }

    //endregion Getters and Setters

    //region Styleable Properties
    //*****************************************************************
    // Styleable Properties
    //*****************************************************************

    /**
     * Handles invalidation of the supporting text state for the control.
     *
     * <p>This method updates the pseudo-class state based on the current state of the supporting text, enabling or disabling the supporting text visually.</p>
     *
     * <p>When the supporting text state changes, this method checks the new state against {@code EFXState.ENABLED}. If the state matches, it implies that supporting text should be visible and accordingly
     * updates the pseudo-class state to true, making the supporting text visible. Conversely, if the state does not match, the pseudo-class state is set to false, hiding the supporting text.</p>
     *
     * <p>This method ensures that the visual representation of the control is consistent with its internal state, particularly in regard to the visibility of supporting text.</p>
     *
     * @param prop
     *         The {@code EFXStyleableObjectProperty} representing the supporting text state. This property holds the current state and triggers this method upon invalidation.
     */
    private void supportingTextStateInvalidated(EFXStyleableObjectProperty<EFXState> prop) {
        EFXState efxState = prop.get();
        pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS, efxState.equals(EFXState.ENABLED));
    }

    /**
     * Validates and potentially updates the position of the supporting text based on the given property.
     *
     * <p>This method enforces the rule that supporting text position can only be set when supporting text is enabled.</p>
     *
     * <p>If supporting text is disabled ({@code isSupportingTextEnabled()} returns false), and an attempt is made to set its position, this method will revert the change and throw an
     * {@code IllegalArgumentException}. This ensures that the control's state remains consistent and that supporting text position changes are only made when supporting text is actually enabled and
     * visible.</p>
     *
     * <p>In cases where the position can be legally set (i.e., supporting text is enabled), this method delegates the responsibility of updating the old value to the provided {@code oldValueSetter} consumer,
     * allowing for additional actions or state updates to be performed in response to the change.</p>
     *
     * @param prop
     *         The {@code EFXStyleableObjectProperty} representing the supporting text position. This property triggers the method upon invalidation.
     * @param oldValue
     *         The previous value of the supporting text position before the invalidation occurred. This value is restored if the method determines that the position change is invalid.
     * @param oldValueSetter
     *         A {@code Consumer} that accepts a {@code SupportingTextPosition} value, used to perform additional actions when the old value needs to be updated legally.
     *
     * @throws IllegalArgumentException
     *         if an attempt is made to set the supporting text position while supporting text is disabled.
     */
    protected void supportingTextPositionInvalidated(EFXStyleableObjectProperty<SupportingTextPosition> prop, SupportingTextPosition oldValue, Consumer<SupportingTextPosition> oldValueSetter) {
        if (!isSupportingTextEnabled()) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.set(oldValue);
            throw new IllegalArgumentException("Cannot set support text position if supporting text is disabled");
        }
        oldValueSetter.accept(prop.get());
    }

    /**
     * Retrieves the CSS metadata associated with the class.
     *
     * @return An unmodifiable list of CssMetaData objects representing the CSS properties that can be applied to this class.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return EFXSupportedControl.STYLES_MANAGER.getCssMetaDataList();
    }

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {return getClassCssMetaData();}

    //endregion Styleable Properties
}
