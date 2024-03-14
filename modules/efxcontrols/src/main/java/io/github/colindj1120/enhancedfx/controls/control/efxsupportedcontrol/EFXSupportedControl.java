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
package io.github.colindj1120.enhancedfx.controls.control.efxsupportedcontrol;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableDoubleProperty;
import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import io.github.colindj1120.enhancedfx.base.factory.ExtendedStyleableDoublePropertyFactory;
import io.github.colindj1120.enhancedfx.base.factory.ExtendedStyleableObjectPropertyFactory;
import io.github.colindj1120.enhancedfx.controls.control.efxcontrol.EFXControl;
import io.github.colindj1120.enhancedfx.base.enums.State;
import io.github.colindj1120.enhancedfx.controls.control.efxsupportedcontrol.base.SupportingTextPosition;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.utils.PropertyUtils;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.EnumConverter;
import javafx.css.converter.SizeConverter;
import javafx.scene.control.Control;

import java.util.List;
import java.util.function.Consumer;

/**
 * Provides a base class for enhanced controls with additional support for dynamic styling, supporting text, and responsive theme changes. {@code EFXSupportedControl} extends the functionality of
 * {@code EFXControl} by introducing mechanisms to manage supporting text visibility and positioning, as well as enhancing the control's ability to adapt to theme changes dynamically.
 *
 * <p>
 * This abstract class introduces several protected fields and methods that subclasses can leverage to implement specific behavior related to supporting text and styling. The class utilizes JavaFX CSS and
 * pseudo-classes to allow for styling customization through external stylesheets, enabling a flexible design that can be easily adjusted to fit the needs of different UI themes.
 * </p>
 *
 * <p>
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Dynamic styling through CSS and pseudo-classes, enabling responsive UI design.</li>
 *     <li>Support for enabling, disabling, and positioning supporting text associated with the control, providing additional context or guidance to the user.</li>
 *     <li>EFXTheme responsiveness, allowing controls to adapt to theme changes without the need for manual reconfiguration.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Subclasses are expected to provide implementations for abstract methods such as {@code getControl()}, which should return the specific control instance, and {@code setupControl()}, which is intended to
 * configure the control upon initialization. Additionally, subclasses may override and extend the functionality provided by this class to suit their specific requirements.
 * </p>
 *
 * @param <T>
 *         the type of control being enhanced, extending {@link Control}
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXControl
 * @see Control
 * @see Styleable
 */
public abstract class EFXSupportedControl<T extends Control> extends EFXControl<T> {
    private static final String ENHANCED_SUPPORTING_TEXT_STYLE = "enhanced-supporting-text";

    private static final   StyleablePropertiesManager STYLES_MANAGER                       = new StyleablePropertiesManager(EFXControl.getClassCssMetaData());
    protected static final PseudoClass                SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("supporting-text-enabled");
    protected static final PseudoClass                SUPPORTING_TEXT_DISABLED_PSEUDO_CLASS   = PseudoClass.getPseudoClass("supporting-text-disabled");
    protected static final PseudoClass                SUPPORTING_TEXT_POS_TOP_PSEUDO_CLASS    = PseudoClass.getPseudoClass("supporting-text-pos-top");
    protected static final PseudoClass                SUPPORTING_TEXT_POS_BOTTOM_PSEUDO_CLASS = PseudoClass.getPseudoClass("supporting-text-pos-bottom");
    protected static final PseudoClass                SUPPORTING_TEXT_POS_LEFT_PSEUDO_CLASS   = PseudoClass.getPseudoClass("supporting-text-pos-left");
    protected static final PseudoClass                SUPPORTING_TEXT_POS_RIGHT_PSEUDO_CLASS  = PseudoClass.getPseudoClass("supporting-text-pos-right");

    protected final SimpleStringProperty supportingText = new SimpleStringProperty(this, "supportingText", "");

    protected EFXStyleableObjectProperty<State>                  supportingTextState;
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
                                                     .isSettableFunction(node -> PropertyUtils.checkProperty(node.supportingTextPosition) && node.isSupportingTextEnabled())
                                                     .propertyGetterFunction(node -> node.supportingTextPosition);
        STYLES_MANAGER.addCssMetaData(supportingTextPositionCssFactory);

        //endregion Supporting Text Position

        //region Supporting Text State
        //*****************************************************************
        // Supporting Text State
        //*****************************************************************

        CssFactory<EFXSupportedControl<?>, State> supportingTextStateCssFactory;
        supportingTextStateCssFactory = CssFactory.<EFXSupportedControl<?>, State>create()
                                                  .property("-efx-supporting-text-state")
                                                  .converter(EnumConverter.getEnumConverter(State.class))
                                                  .initialValue(State.DISABLED)
                                                  .isSettableFunction(node -> PropertyUtils.checkProperty(node.supportingTextState))
                                                  .propertyGetterFunction(node -> node.supportingTextState);
        STYLES_MANAGER.addCssMetaData(supportingTextStateCssFactory);

        //endregion Supporting Text State

        //region Supporting Text X Offset
        //*****************************************************************
        // Supporting Text X Offset
        //*****************************************************************

        CssFactory<EFXSupportedControl<?>, Number> supportingTextXOffsetCssFactory;
        supportingTextXOffsetCssFactory = CssFactory.<EFXSupportedControl<?>, Number>create()
                                                    .property("-efx-supporting-text-x-offset")
                                                    .converter(SizeConverter.getInstance())
                                                    .initialValue(0.0)
                                                    .isSettableFunction(node -> PropertyUtils.checkProperty(node.supportingTextXOffset))
                                                    .propertyGetterFunction(node -> node.supportingTextXOffset);
        STYLES_MANAGER.addCssMetaData(supportingTextXOffsetCssFactory);

        //endregion Supporting Text X Offset

        //region Supporting Text Y Offset
        //*****************************************************************
        // Supporting Text Y Offset
        //*****************************************************************

        CssFactory<EFXSupportedControl<?>, Number> supportingTextYOffsetCssFactory;
        supportingTextYOffsetCssFactory = CssFactory.<EFXSupportedControl<?>, Number>create()
                                                    .property("-efx-supporting-text-y-offset")
                                                    .converter(SizeConverter.getInstance())
                                                    .initialValue(0.0)
                                                    .isSettableFunction(node -> PropertyUtils.checkProperty(node.supportingTextYOffset))
                                                    .propertyGetterFunction(node -> node.supportingTextYOffset);
        STYLES_MANAGER.addCssMetaData(supportingTextYOffsetCssFactory);

        //endregion Supporting Text Y Offset

    }

    /**
     * Constructs an instance of EFXSupportedControl.
     *
     * <p>
     * This constructor calls the `super()` method to initialize the parent class constructor.
     * </p>
     */
    public EFXSupportedControl() {
        super();
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

    @Override
    protected void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
        loadNewThemeHelper(EFXStylesheets.ENHANCED_SUPPORTED_CONTROL, oldEFXTheme, newEFXTheme);
    }

    /**
     * This method builds upon {@code setupStyleableProperties} from the superclass, and sets up the styleable properties for this subclass instance. It is responsible for initializing the
     * {@code supportingTextState} and {@code supportingTextPosition} properties of this object's state.
     *
     * <p>
     * The {@code supportingTextState} property represents the state of supporting text in the graphical interface, such as being enabled or disabled. It holds a value of the {@code State} enumeration.
     * </p>
     *
     * <p>
     * The {@code supportingTextPosition} property represents the position of supporting text in the interface, such as being on the left or right. It holds a value of the {@code SupportingTextPosition}
     * enumeration.
     * </p>
     *
     * <p>
     * <em>Both properties are initialized with:</em>
     * <ul>
     *     <li><b>Bean</b>: This instance serving as the bean in which the property resides.</li>
     *     <li><b>Name</b>: The name of the property, set to either "supportingTextState" or "supportingTextPosition".</li>
     *     <li><b>CssMetaData</b>: The CSS metadata linked to the property, obtained by searching for "-supporting-text-state" or "-supporting-text-position".</li>
     *     <li><b>DefaultValue</b>: The initial value of the property, set to `State.DISABLED` or `SupportingTextPosition.LEFT` respectively.</li>
     *     <li><b>InvalidatedPropCallback/InvalidatedCachedCallback</b>: The callback method that executes when the property becomes invalid.</li>
     * </ul>
     * </p>
     *
     * <p>
     * This process uses the {@code ExtendedStyleableObjectPropertyFactory.builder()} to configure and initialize the property.
     * </p>
     *
     * <p>
     * This subclass method adds styleable properties relevant to the specifics of this subclass, supplementing and complementing those set up via the superclass method.
     * </p>
     */
    protected void setupStyleableProperties() {
        supportingTextState = ExtendedStyleableObjectPropertyFactory.<State>builder()
                                                                    .bean(this)
                                                                    .type(State.class)
                                                                    .name("supportingTextState")
                                                                    .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-supporting-text-state"))
                                                                    .defaultValue(State.DISABLED)
                                                                    .invalidatedPropCallback(this::supportingTextStateInvalidated)
                                                                    .build();

        supportingTextPosition = ExtendedStyleableObjectPropertyFactory.<SupportingTextPosition>builder()
                                                                       .bean(this)
                                                                       .type(SupportingTextPosition.class)
                                                                       .name("supportingTextPosition")
                                                                       .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-supporting-text-position"))
                                                                       .defaultValue(SupportingTextPosition.LEFT)
                                                                       .invalidatedCachedCallback(this::supportingTextPositionInvalidated)
                                                                       .build();

        supportingTextXOffset = ExtendedStyleableDoublePropertyFactory.builder()
                                                                      .bean(this)
                                                                      .name("supportingTextXOffset")
                                                                      .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-supporting-text-x-offset"))
                                                                      .defaultValue(0.0)
                                                                      .build();

        supportingTextYOffset = ExtendedStyleableDoublePropertyFactory.builder()
                                                                      .bean(this)
                                                                      .name("supportingTextYOffset")
                                                                      .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-supporting-text-y-offset"))
                                                                      .defaultValue(0.0)
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
     *         to missing resources. The {@code CustomControlConfigurator} is used to fluently apply configuration settings to the control, showcasing an example of the Builder pattern for setup tasks.
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
     * @return The current state of the supporting text as a State object. Possible values are ENABLED and DISABLED.
     */
    public State getSupportingTextState() {
        return supportingTextState.get();
    }

    /**
     * Retrieves the supporting text state property of the object. The supporting text state property is an instance of `EFXStyleableObjectProperty<State>`.
     *
     * @return The supporting text state property of the object.
     */
    public EFXStyleableObjectProperty<State> supportingTextStateProperty() {
        return supportingTextState;
    }

    /**
     * Sets the supporting text state of the EFXSupportedControl.
     * <p>
     * The supporting text state indicates whether the supporting text of the control is currently active and can interact with the user or inactive and cannot interact with the user .
     * </p>
     *
     * @param supportingTextState
     *         The supporting text state to set (ENABLED or DISABLED) as a State enum.
     */
    public void setSupportingTextState(State supportingTextState) {
        this.supportingTextState.set(supportingTextState);
    }

    /**
     * Checks if the supporting text of the EFXSupportedControl is currently enabled.
     *
     * @return {@code true} if the supporting text is enabled, {@code false} otherwise.
     */
    public Boolean isSupportingTextEnabled() {
        return supportingTextState.valueEquals(State.ENABLED);
    }

    /**
     * Checks if the supporting text is currently disabled.
     *
     * @return {@code true} if the supporting text is disabled, {@code false} otherwise
     */
    public Boolean isSupportingTextDisabled() {
        return supportingTextState.valueEquals(State.DISABLED);
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
     * @param supportingTextXOffset the x offset value to set
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
     * @param supportingTextYOffset The y offset value for supporting text.
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
     * Invalidates the supporting text state of a styleable object property and updates the pseudo-class states of the text field accordingly.
     *
     * @param prop
     *         the styleable object property representing the supporting text state
     */
    private void supportingTextStateInvalidated(StyleableObjectProperty<State> prop) {
        State state = prop.get();
        pseudoClassStateChanged(SUPPORTING_TEXT_ENABLED_PSEUDO_CLASS, state.equals(State.ENABLED));
    }

    /**
     * Invalidates the supporting text position of a styleable object property and performs necessary actions.
     *
     * @param prop
     *         the styleable object property representing the supporting text position
     * @param oldValue
     *         the old value of the supporting text position
     * @param oldValueSetter
     *         a consumer to set the old value of the supporting text position
     *
     * @throws IllegalArgumentException
     *         if supporting text is disabled and an attempt is made to set the position
     */
    protected void supportingTextPositionInvalidated(StyleableObjectProperty<SupportingTextPosition> prop, SupportingTextPosition oldValue, Consumer<SupportingTextPosition> oldValueSetter) {
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
