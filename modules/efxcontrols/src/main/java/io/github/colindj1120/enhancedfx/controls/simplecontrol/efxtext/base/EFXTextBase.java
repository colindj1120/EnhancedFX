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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableIntegerProperty;
import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.enums.EFXState;
import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxsupportedcontrol.EFXSupportedControl;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxsupportedcontrol.base.SupportingTextPosition;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.EFXTextArea;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.EFXTextField;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.utils.EFXPropertyUtils;
import io.github.colindj1120.enhancedfx.utils.EFXUIUtils;
import io.github.colindj1120.enhancedfx.utils.converters.styleconverters.IntegerStyleConverter;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.css.converter.ColorConverter;
import javafx.css.converter.EnumConverter;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.function.Consumer;

/**
 * The {@code EFXTextBase} class serves as the foundation for creating enhanced text-based controls within the EnhancedFX library. It extends {@link EFXSupportedControl} and implements
 * {@link InnerTextInputControl}, providing a rich set of functionalities and styling options for text input controls, such as {@link EFXTextField} or {@link EFXTextArea}.
 *
 * <p>
 * <em>This abstract class encapsulates common behaviors and properties shared among enhanced text controls, including:</em>
 * <ul>
 *     <li>Character count with maximum limit</li>
 *     <li>Custom text and prompt text colors</li>
 *     <li>Different text modes (filled, outlined)</li>
 *     <li>Leading and trailing icons</li>
 * </ul>
 * </p>
 *
 * <p>By leveraging CSS and styleable properties, it offers extensive customization and styling capabilities, adhering to modern UI design principles.</p>
 *
 * <h2>Key Features</h2>
 * <p>
 * <ul>
 *     <li><b>Leading and Trailing Icons:</b> Allows embedding icons within the text field for additional interactions or indications.</li>
 *     <li><b>Character Count Management:</b> Supports setting a maximum character count, along with customizable states and positions for the character count indicator, enhancing usability and input
 *     validation.</li>
 *     <li><b>Text Fill and Prompt Text Fill:</b> Enables fine-tuning of text and prompt colors, facilitating better visual design and user experience.</li>
 *     <li><b>Text Modes:</b> Offers multiple text presentation modes, such as filled and outlined, allowing for diverse design choices.</li>
 *     <li><b>Extensive CSS Styling:</b> Provides a rich set of CSS properties for deep customization, aligning with the overall design system of an application.</li>
 * </ul>
 * </p>
 *
 * @param <T>
 *         the specific type of {@link TextInputControl} this class is enhancing, such as {@link TextField} or {@link TextArea}
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextInputControl
 * @see InnerTextInputControl
 * @see EFXSupportedControl
 * @see EFXTextArea
 * @see EFXTextField
 */
public abstract class EFXTextBase<T extends TextInputControl> extends EFXSupportedControl<T> implements InnerTextInputControl<T> {
    private static final String ENHANCED_TEXT_BASE_STYLE = "enhanced-text-base";

    private static final StyleablePropertiesManager STYLES_MANAGER = new StyleablePropertiesManager(EFXSupportedControl.getClassCssMetaData());

    protected static final PseudoClass MAX_CHARACTER_COUNT_ENABLED_PSEUDO_CLASS   = PseudoClass.getPseudoClass("max-char-count-enabled");
    protected static final PseudoClass MAX_CHARACTER_COUNT_DISABLED_PSEUDO_CLASS  = PseudoClass.getPseudoClass("max-char-count-disabled");
    protected static final PseudoClass MAX_CHARACTER_COUNT_POS_ABOVE_PSEUDO_CLASS = PseudoClass.getPseudoClass("max-char-count-pos-above");
    protected static final PseudoClass MAX_CHARACTER_COUNT_POS_BELOW_PSEUDO_CLASS = PseudoClass.getPseudoClass("max-char-count-pos-below");
    protected static final PseudoClass TEXTMODE_FILLED_PSEUDO_CLASS               = PseudoClass.getPseudoClass("textmode-filled");
    protected static final PseudoClass TEXTMODE_OUTLINED_PSEUDO_CLASS             = PseudoClass.getPseudoClass("textmode-outlined");

    private ObjectProperty<Node> leadingIcon;
    private ObjectProperty<Node> trailingIcon;

    protected EFXStyleableIntegerProperty                           maxCharCount;
    protected EFXStyleableObjectProperty<EFXState>                  maxCharCountState;
    protected EFXStyleableObjectProperty<MaxCharacterCountPosition> maxCharCountPos;
    protected EFXStyleableObjectProperty<Color>                     textFill;
    protected EFXStyleableObjectProperty<Color>                     promptTextFill;
    protected EFXStyleableObjectProperty<TextMode>                  textMode;

    static {
        //region Text Fill
        //*****************************************************************
        // Text Fill
        //*****************************************************************

        CssFactory<EFXTextField, Color> textFillCssFactory;
        textFillCssFactory = CssFactory.<EFXTextField, Color>create()
                                       .property("-efx-text-fill")
                                       .converter(ColorConverter.getInstance())
                                       .initialValue(javafx.scene.paint.Color.valueOf("#000000"))
                                       .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.textFill))
                                       .propertyGetterFunction(node -> node.textFill);
        STYLES_MANAGER.addCssMetaData(textFillCssFactory);

        //endregion Text Fill

        //region Prompt Text Fill
        //*****************************************************************
        // Prompt Text Fill
        //*****************************************************************

        CssFactory<EFXTextField, Color> promptTextFillCssFactory;
        promptTextFillCssFactory = CssFactory.<EFXTextField, Color>create()
                                             .property("-efx-prompt-text-fill")
                                             .converter(ColorConverter.getInstance())
                                             .initialValue(javafx.scene.paint.Color.valueOf("#000000"))
                                             .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.promptTextFill))
                                             .propertyGetterFunction(node -> node.promptTextFill);
        STYLES_MANAGER.addCssMetaData(promptTextFillCssFactory);

        //endregion Prompt Text Fill

        //region Max Character Count EFXState
        //*****************************************************************
        // Max Character Count EFXState
        //*****************************************************************

        CssFactory<EFXTextField, EFXState> maxCharCountStateCssFactory;
        maxCharCountStateCssFactory = CssFactory.<EFXTextField, EFXState>create()
                                                .property("-efx-max-char-count-state")
                                                .converter(EnumConverter.getEnumConverter(EFXState.class))
                                                .initialValue(EFXState.DISABLED)
                                                .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.maxCharCountState))
                                                .propertyGetterFunction(node -> node.maxCharCountState);
        STYLES_MANAGER.addCssMetaData(maxCharCountStateCssFactory);

        //endregion Max Character Count EFXState

        //region Max Character Count
        //*****************************************************************
        // Max Character Count
        //*****************************************************************

        CssFactory<EFXTextField, Integer> maxCharCountCssFactory;
        maxCharCountCssFactory = CssFactory.<EFXTextField, Integer>create()
                                           .property("-efx-max-char-count")
                                           .converter(IntegerStyleConverter.getInstance())
                                           .initialValue(50)
                                           .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.maxCharCount) && node.isMaxCharacterCountEnabled())
                                           .propertyGetterFunction(node -> node.maxCharCount);
        STYLES_MANAGER.addCssMetaData(maxCharCountCssFactory);

        //endregion Max Character Count

        //region Max Character Count Position
        //*****************************************************************
        // Max Character Count Position
        //*****************************************************************

        CssFactory<EFXTextField, MaxCharacterCountPosition> maxCharCountPosCssFactory;
        maxCharCountPosCssFactory = CssFactory.<EFXTextField, MaxCharacterCountPosition>create()
                                              .property("-efx-max-char-count-pos")
                                              .converter(EnumConverter.getEnumConverter(MaxCharacterCountPosition.class))
                                              .initialValue(MaxCharacterCountPosition.ABOVE)
                                              .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.maxCharCountPos) && node.isMaxCharacterCountEnabled())
                                              .propertyGetterFunction(node -> node.maxCharCountPos);
        STYLES_MANAGER.addCssMetaData(maxCharCountPosCssFactory);

        //endregion Max Character Count Position

        //region Text Mode
        //*****************************************************************
        // Text Mode
        //*****************************************************************

        CssFactory<EFXTextBase<?>, TextMode> textModeCssFactory;
        textModeCssFactory = CssFactory.<EFXTextBase<?>, TextMode>create()
                                       .property("-efx-text-mode")
                                       .converter(EnumConverter.getEnumConverter(TextMode.class))
                                       .initialValue(TextMode.OUTLINED)
                                       .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.textMode))
                                       .propertyGetterFunction(node -> node.textMode);
        STYLES_MANAGER.addCssMetaData(textModeCssFactory);

        //endregion Text Mode

    }

    @Override
    protected void initialize() {
        super.initialize();
        this.leadingIcon = new SimpleObjectProperty<>(this, "leadingIcon");
        this.trailingIcon = new SimpleObjectProperty<>(this, "trailingIcon");
    }

    /**
     * Constructs an instance of {@code EFXTextBase}, initializing the base state for this UI component.
     *
     * <p>
     * This constructor initializes the {@code EFXTextBase} component with default settings, ensuring it is ready for use or further customization. It is designed to set up essential properties, listeners, or
     * default styles that are common to all instances of {@code EFXTextBase}. The call to the superclass constructor ({@code super()}) ensures that any initialization logic defined in the superclass is also
     * executed, maintaining the integrity of the component's initial state.
     * </p>
     * <p>
     * Usage of this constructor is typical for subclasses or when creating instances of {@code EFXTextBase} directly, allowing for further customization through setters or by overriding default behavior.
     * </p>
     */
    protected EFXTextBase() {
        super();
    }

    //region EFXControlBase Functions
    //*****************************************************************
    // EFXControlBase Functions
    //*****************************************************************

    /**
     * Retrieves the current instance of {@link EFXTextBase}. This method acts as an abstraction layer, requiring subclasses to provide a concrete implementation that returns the specific instance of the
     * text-based control being enhanced. The control is expected to offer advanced text handling capabilities, building upon the functionality provided by this base class.
     *
     * @return The {@link EFXTextBase} instance associated with this text control, tailored with additional features or configurations specific to the control's implementation.
     */
    @Override
    protected abstract EFXTextBase<?> getControl();

    @Override
    protected void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
        loadNewThemeHelper(EFXStylesheets.ENHANCED_TEXT_BASE, oldEFXTheme, newEFXTheme);
    }

    /**
     * This method builds upon {@code setupStyleableProperties} from the superclass, and configures the styleable properties specific to this enhanced text control instance. It is aimed at initializing
     * properties such as {@code maxCharCount}, {@code maxCharCountEFXState}, {@code maxCharCountPos}, {@code textFill}, {@code promptTextFill}, and {@code textMode} to enhance the text control's capabilities.
     *
     * <p>
     * The {@code maxCharCount} property controls the maximum number of characters that can be entered, enhancing the control's functionality with a character limit feature. It holds an integer value.
     * </p>
     *
     * <p>
     * The {@code maxCharCountEFXState} property indicates whether the maximum character count feature is enabled or disabled, using the {@code EFXState} enumeration for its values.
     * </p>
     *
     * <p>
     * The {@code maxCharCountPos} property determines the position of the character count indicator relative to the text control, using the {@code MaxCharacterCountPosition} enumeration for its values.
     * </p>
     *
     * <p>
     * The {@code textFill} and {@code promptTextFill} properties control the colors of the text and prompt text within the control, respectively, offering extensive customization of the control's appearance.
     * </p>
     *
     * <p>
     * The {@code textMode} property represents the mode of the text, like being filled or outlined in the interface. It holds a value of the {@code TextMode} enumeration.
     * </p>
     *
     * <p>
     * <em>Properties are initialized with:</em>
     * <ul>
     *     <li><b>Bean</b>: This instance, serving as the bean in which the property resides.</li>
     *     <li><b>Name</b>: The name of the property, uniquely identifying it within the control.</li>
     *     <li><b>CssMetaData</b>: The CSS metadata associated with the property, allowing for CSS customization.</li>
     *     <li><b>DefaultValue</b>: The initial value for the property, ensuring a consistent default state.</li>
     *     <li><b>InvalidatedCachedCallback</b>: A callback method that is invoked when the property's value is invalidated, allowing for dynamic response to changes.</li>
     * </ul>
     * </p>
     *
     * <p>
     * The configuration and initialization of these properties are facilitated by the {@code ExtendedStyleableIntegerPropertyFactory} and {@code ExtendedStyleableObjectPropertyFactory.builder()}, showcasing
     * a builder pattern to streamline the setup process.
     * </p>
     *
     * <p>
     * Through this method, the enhanced text control introduces additional styleable properties that extend its styling and functional capabilities, complementing those inherited from its superclass.
     * </p>
     */
    @Override
    protected void setupStyleableProperties() {
        super.setupStyleableProperties();

        maxCharCount = EFXStyleableIntegerProperty.create()
                                                  .bean(this)
                                                  .name("maxCharacterCount")
                                                  .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-max-char-count"))
                                                  .initialValue(50)
                                                  .invalidatedCachedCallback(this::maxCharacterCountInvalidated)
                                                  .build();

        maxCharCountState = EFXStyleableObjectProperty.<EFXState>create()
                                                      .bean(this)
                                                      .name("maxCharacterCountEnabled")
                                                      .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-max-char-count-state"))
                                                      .initialValue(EFXState.DISABLED)
                                                      .invalidatedPropCallback(this::maxCharacterCountStateInvalidated)
                                                      .build();

        maxCharCountPos = EFXStyleableObjectProperty.<MaxCharacterCountPosition>create()
                                                    .bean(this)
                                                    .name("maxCharacterCountPosition")
                                                    .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-max-char-count-pos"))
                                                    .initialValue(MaxCharacterCountPosition.ABOVE)
                                                    .invalidatedCachedCallback(this::maxCharacterCountPositionInvalidated)
                                                    .build();

        textFill = EFXStyleableObjectProperty.<Color>create()
                                             .bean(this)
                                             .name("textFill")
                                             .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-text-fill"))
                                             .initialValue(Color.valueOf("#000000"))
                                             .build();

        promptTextFill = EFXStyleableObjectProperty.<Color>create()
                                                   .bean(this)
                                                   .name("promptTextFill")
                                                   .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-prompt-text-fill"))
                                                   .initialValue(Color.valueOf("#000000"))
                                                   .build();

        textMode = EFXStyleableObjectProperty.<TextMode>create()
                                             .bean(this)
                                             .name("textMode")
                                             .initialValue(TextMode.OUTLINED)
                                             .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-text-mode"))
                                             .invalidatedCachedCallback(this::textModeInvalidated)
                                             .build();
    }

    /**
     * Prepares the text control with necessary configurations upon initialization. This includes checking for and applying the appropriate stylesheet, adding a specific style class, and setting up listeners
     * for property changes that affect the control's appearance. The method exemplifies a proactive approach to control setup, ensuring all necessary stylistic and functional configurations are applied early
     * in the control lifecycle.
     *
     * <p>
     * This method builds upon {@code setupControl} from the superclass, the control is further customized through the addition of listeners for text fill and prompt text fill properties, showcasing an
     * attention to detail in handling dynamic UI updates. This method ensures the control is fully prepared to render with the correct styling and behavior as defined by its enhanced features.
     * </p>
     *
     * @implNote The stylesheet path is validated to ensure the stylesheet exists and is accessible. This method employs the {@link CustomControlConfigurator} to fluently apply the stylesheet, style
     *         class, and property change listeners, emphasizing a builder pattern approach for elegant and efficient control setup.
     */
    @Override
    protected void setupControl() {
        super.setupControl();
        String stylesheetPath = checkStylesheetPathExists(EFXStylesheets.ENHANCED_TEXT_BASE, this.getClass());

        CustomControlConfigurator.create(this)
                                 .<Color>addObjectPropertyChangeListener(textFill, this::handleTextFillChange)
                                 .<Color>addObjectPropertyChangeListener(promptTextFill, this::handlePromptTextFillChange)
                                 .addObjectPropertyInvalidationListener(supportingTextState, invalidated -> {
                                     if (!supportingTextPosition.valueEquals(SupportingTextPosition.BOTTOM)) {
                                         supportingTextPosition.set(SupportingTextPosition.BOTTOM);
                                     }
                                 })
                                 .addStyleClass(ENHANCED_TEXT_BASE_STYLE)
                                 .addStylesheet(stylesheetPath);
    }

    /**
     * This method builds upon {@code updatePseudoClassStates} from the superclass, and updates the pseudo class states of the component based on the current max character count state and the current max
     * character count position. The pseudo class states are updated for the enabled, disabled, position above, position below, text mode filled and text mode outlined modes.
     *
     * @see EFXTextBase#MAX_CHARACTER_COUNT_ENABLED_PSEUDO_CLASS
     * @see EFXTextBase#MAX_CHARACTER_COUNT_DISABLED_PSEUDO_CLASS
     * @see EFXTextBase#MAX_CHARACTER_COUNT_POS_ABOVE_PSEUDO_CLASS
     * @see EFXTextBase#MAX_CHARACTER_COUNT_POS_BELOW_PSEUDO_CLASS
     * @see EFXTextField#TEXTMODE_FILLED_PSEUDO_CLASS
     * @see EFXTextField#TEXTMODE_OUTLINED_PSEUDO_CLASS
     */
    @Override
    protected void updatePseudoClassStates() {
        super.updatePseudoClassStates();
        pseudoClassStateChanged(MAX_CHARACTER_COUNT_ENABLED_PSEUDO_CLASS, isMaxCharacterCountEnabled());
        pseudoClassStateChanged(MAX_CHARACTER_COUNT_DISABLED_PSEUDO_CLASS, isMaxCharacterCountDisabled());
        pseudoClassStateChanged(MAX_CHARACTER_COUNT_POS_ABOVE_PSEUDO_CLASS, isMaxCharacterCountPosAbove());
        pseudoClassStateChanged(MAX_CHARACTER_COUNT_POS_BELOW_PSEUDO_CLASS, isMaxCharacterCountPosBelow());
        pseudoClassStateChanged(TEXTMODE_FILLED_PSEUDO_CLASS, isTextModeFilled());
        pseudoClassStateChanged(TEXTMODE_OUTLINED_PSEUDO_CLASS, isTextModeOutlined());
    }

    //endregion EFXControlBase Functions

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * Attempts to set the position of the supporting text, but this operation is not supported for this class.
     *
     * <p>
     * This method override indicates that changing the position of supporting text is not applicable for certain UI components like text areas or text fields within the current context. The method
     * intentionally throws an {@link UnsupportedOperationException} to enforce this constraint and signal to developers that such an operation is not allowed due to design or functionality considerations
     * specific to these components.
     * </p>
     *
     * @param supportingTextPosition
     *         The intended position for the supporting text, which is ignored by this implementation.
     *
     * @throws UnsupportedOperationException
     *         to indicate that this operation is not supported for text areas or text fields, and to prevent its usage in contexts where it might lead to inconsistent or undefined behavior.
     */
    @Override
    public void setSupportingTextPosition(SupportingTextPosition supportingTextPosition) {
        if (!supportingTextPosition.equals(SupportingTextPosition.BOTTOM)) {
            throw new UnsupportedOperationException("The position of the supporting text cannot be changed for text areas or text fields");
        }
    }

    //endregion Overridden Functions

    //region Listener Functions
    //*****************************************************************
    // Listener Functions
    //*****************************************************************

    /**
     * Handles changes in the color value of a control's text fill.
     *
     * <p>
     * This method is designed to react to an observable color property of a JavaFX control. It will be called automatically when the observed color value changes. The new color will be applied to the text fill
     * of the control.
     * </p>
     *
     * @param obs
     *         An ObservableValue representing the color value that has been changed.
     * @param newColor
     *         The new Color object that observable value has changed to.
     * @param color
     *         The old Color object that observable value was before the change.
     *
     * @see EFXUIUtils#updateStyleWithNewColor
     */
    private void handleTextFillChange(ObservableValue<? extends Color> obs, Color newColor, Color color) {
        EFXUIUtils.updateStyleWithNewColor(newColor, getInnerControl(), "-efx-text-fill");
    }

    /**
     * Handles changes in the color value of a control's prompt text fill.
     *
     * <p>
     * This method is designed to react to an observable color property of a JavaFX control, specifically related to the prompt text. It will be called automatically when the observed color value changes. The
     * new color will be applied to the fill of the prompt text in the control.
     * </p>
     *
     * @param obs
     *         An ObservableValue representing the color value that has been changed.
     * @param newColor
     *         The new Color object that observable value has changed to.
     * @param color
     *         The old Color object that observable value was before the change.
     *
     * @see EFXUIUtils#updateStyleWithNewColor
     */
    private void handlePromptTextFillChange(ObservableValue<? extends Color> obs, Color newColor, Color color) {
        EFXUIUtils.updateStyleWithNewColor(newColor, getInnerControl(), "-efx-prompt-text-fill");
    }
    //endregion Listener Functions

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
     * Retrieves the maximum character count state.
     *
     * @return the maximum character count state
     */
    public EFXState getMaxCharCountState() {
        return maxCharCountState.get();
    }

    /**
     * Returns the maxCharCountEFXState property.
     *
     * @return the maxCharCountEFXState property.
     */
    public EFXStyleableObjectProperty<EFXState> maxCharCountStateProperty() {
        return maxCharCountState;
    }

    /**
     * Sets the state of the maximum character count.
     *
     * @param maxCharCountEFXState
     *         the state of the maximum character count
     */
    public void setMaxCharCountState(EFXState maxCharCountEFXState) {
        this.maxCharCountState.set(maxCharCountEFXState);
    }

    /**
     * Checks if the max character count is enabled.
     *
     * @return {@code true} if the max character count is enabled, {@code false} otherwise.
     */
    public Boolean isMaxCharacterCountEnabled() {
        return maxCharCountState.valueEquals(EFXState.ENABLED);
    }

    /**
     * Check if the maximum character count is disabled.
     *
     * @return {@code true} if the maximum character count is disabled, {@code false} otherwise.
     */
    public Boolean isMaxCharacterCountDisabled() {
        return maxCharCountState.valueEquals(EFXState.DISABLED);
    }

    /**
     * Retrieves the position of the maximum character count.
     *
     * @return The position of the maximum character count.
     */
    public MaxCharacterCountPosition getMaxCharCountPos() {
        return maxCharCountPos.get();
    }

    /**
     * Retrieves the EFXStyleableObjectProperty for the maximum character count position.
     *
     * @return The EFXStyleableObjectProperty for the maximum character count position.
     */
    public EFXStyleableObjectProperty<MaxCharacterCountPosition> maxCharCountPosProperty() {
        return maxCharCountPos;
    }

    /**
     * Sets the position of the maximum character count.
     *
     * @param maxCharCountPos
     *         the position of the maximum character count
     */
    public void setMaxCharCountPos(MaxCharacterCountPosition maxCharCountPos) {
        this.maxCharCountPos.set(maxCharCountPos);
    }

    /**
     * Determines if the maximum character count position is set to "ABOVE".
     *
     * @return true if the maximum character count position is above, otherwise false.
     */
    public Boolean isMaxCharacterCountPosAbove() {
        return maxCharCountPos.valueEquals(MaxCharacterCountPosition.ABOVE);
    }

    /**
     * Determines if the maximum character count position is below a certain value.
     *
     * @return true if the maximum character count position is below the value, otherwise false.
     */
    public Boolean isMaxCharacterCountPosBelow() {
        return maxCharCountPos.valueEquals(MaxCharacterCountPosition.BELOW);
    }

    /**
     * Retrieves the maximum character count.
     *
     * @return the maximum character count
     */
    public int getMaxCharCount() {
        return maxCharCount.get();
    }

    /**
     * Retrieves the maximum character count property.
     *
     * @return the maximum character count property
     */
    public EFXStyleableIntegerProperty maxCharCountProperty() {
        return maxCharCount;
    }

    /**
     * Sets the maximum character count.
     *
     * @param maxCharCount
     *         the maximum character count to be set
     */
    public void setMaxCharCount(int maxCharCount) {
        this.maxCharCount.set(maxCharCount);
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
    public EFXStyleableObjectProperty<Color> textFillProperty() {
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
     * Returns the EFXStyleableObjectProperty that represents the fill color of the prompt text.
     *
     * @return The EFXStyleableObjectProperty that represents the fill color of the prompt text.
     */
    public EFXStyleableObjectProperty<Color> promptTextFillProperty() {
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

    /**
     * Returns whether the text mode is filled or not.
     *
     * @return true if the text mode is filled, false otherwise.
     */
    public Boolean isTextModeFilled() {
        return textMode.valueEquals(TextMode.FILLED);
    }

    /**
     * Checks whether the text mode of the EFXTextField is set to "outlined".
     *
     * @return {@code true} if the text mode is outlined, {@code false} otherwise.
     */
    public Boolean isTextModeOutlined() {
        return textMode.valueEquals(TextMode.OUTLINED);
    }

    /**
     * Retrieves the current mode of the text.
     *
     * @return The TextMode representing the current mode of the text.
     */
    public TextMode getTextMode() {
        return textMode.get();
    }

    /**
     * Gets the text mode property.
     *
     * @return the text mode property
     */
    public EFXStyleableObjectProperty<TextMode> textModeProperty() {
        return textMode;
    }

    /**
     * Sets the mode of a text.
     *
     * @param textMode
     *         the mode to set the text to
     */
    public void setTextMode(TextMode textMode) {
        this.textMode.set(textMode);
    }

    //endregion Getters and Setters

    //region StyleableProperties Invalidate Functions
    //*****************************************************************
    // StyleableProperties Invalidate Functions
    //*****************************************************************

    private void maxCharacterCountPositionInvalidated(EFXStyleableObjectProperty<MaxCharacterCountPosition> prop, MaxCharacterCountPosition oldValue, Consumer<MaxCharacterCountPosition> oldValueSetter) {
        MaxCharacterCountPosition pos = prop.get();

        if (!isMaxCharacterCountEnabled()) {
            if (prop.isBound()) {
                prop.unbind();
            }
            prop.set(oldValue);
            throw new IllegalArgumentException("Cannot set character count position if max character count is disabled");
        }

        pseudoClassStateChanged(MAX_CHARACTER_COUNT_POS_ABOVE_PSEUDO_CLASS, pos.equals(MaxCharacterCountPosition.ABOVE));
        pseudoClassStateChanged(MAX_CHARACTER_COUNT_POS_BELOW_PSEUDO_CLASS, pos.equals(MaxCharacterCountPosition.BELOW));
        oldValueSetter.accept(pos);
    }

    private void maxCharacterCountInvalidated(EFXStyleableIntegerProperty prop, Integer oldValue, Consumer<Integer> oldValueSetter) {
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

    private void maxCharacterCountStateInvalidated(EFXStyleableObjectProperty<EFXState> prop) {
        EFXState efxState = prop.get();

        pseudoClassStateChanged(MAX_CHARACTER_COUNT_ENABLED_PSEUDO_CLASS, efxState.equals(EFXState.ENABLED));
        pseudoClassStateChanged(MAX_CHARACTER_COUNT_DISABLED_PSEUDO_CLASS, efxState.equals(EFXState.DISABLED));
    }

    protected void textModeInvalidated(EFXStyleableObjectProperty<TextMode> prop, TextMode oldValue, Consumer<TextMode> oldValueSetter) {
        TextMode mode = prop.get();

        pseudoClassStateChanged(TEXTMODE_OUTLINED_PSEUDO_CLASS, mode.equals(TextMode.OUTLINED));
        pseudoClassStateChanged(TEXTMODE_FILLED_PSEUDO_CLASS, mode.equals(TextMode.FILLED));

        if (!oldValue.equals(mode)) {
            oldValueSetter.accept(mode);
        }
    }

    @Override
    protected void supportingTextPositionInvalidated(EFXStyleableObjectProperty<SupportingTextPosition> prop, SupportingTextPosition oldValue, Consumer<SupportingTextPosition> oldValueSetter) {
        SupportingTextPosition position = prop.get();
        if (!position.equals(SupportingTextPosition.BOTTOM)) {
            throw new UnsupportedOperationException("Supporting text position cannot be changed for Enhanced Text Fields or Enhanced Text Areas");
        }
        super.supportingTextPositionInvalidated(prop, oldValue, oldValueSetter);
    }

    //endregion StyleableProperties Invalidate Functions

    //region Css Metadata
    //*****************************************************************
    // Css Metadata
    //*****************************************************************

    /**
     * Returns the list of CSS metadata for the class.
     *
     * @return The list of CSS metadata for the class.
     */
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

    //endregion Css Metadata
}
