package io.github.colindj1120.enhancedfx.controls;

import io.github.colindj1120.enhancedfx.beans.property.extendedstyleableproperty.ExtendedStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.controls.base.EnhancedTextBase;
import io.github.colindj1120.enhancedfx.controls.base.inner.innertext.InnerTextArea;
import io.github.colindj1120.enhancedfx.enums.State;
import io.github.colindj1120.enhancedfx.enums.styling.Theme;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.factories.styling.CssFactory;
import io.github.colindj1120.enhancedfx.factories.styling.ExtendedStyleableObjectPropertyFactory;
import io.github.colindj1120.enhancedfx.skins.EnhancedTextAreaSkin;
import io.github.colindj1120.enhancedfx.styling.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.enums.styling.Stylesheets;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.EnumConverter;
import javafx.scene.control.Skin;
import javafx.scene.control.TextArea;

import java.util.List;

import static io.github.colindj1120.enhancedfx.utils.PropertyUtils.checkProperty;

/**
 * The {@code EnhancedTextArea} class is an extension of the {@link EnhancedTextBase} tailored specifically for {@link TextArea} controls within the EnhancedFX library. It encapsulates functionalities that are
 * unique to text areas, providing additional features on top of the standard {@code TextArea} functionalities.
 *
 * <p>
 * <em>This class integrates seamlessly with the EnhancedFX styling and behavior mechanisms, offering a highly customizable text area component. It supports enhanced features such as:</em>
 * <ul>
 *     <li>Custom title text with enabled and disabled states</li>
 *     <li>Extended styling options through CSS</li>
 *     <li>Integration with the EnhancedFX skin system for a consistent look and feel</li>
 * </ul>
 * </p>
 *
 * <h2>Key Features</h2>
 * <p>
 * <ul>
 *     <li><b>Title Text:</b> Allows for the addition of a title above the text area, which can be dynamically shown or hidden based on the component's state. This feature is particularly useful for
 *     providing context or instructions related to the text area's content.</li>
 *     <li><b>Styling Through CSS:</b> Leverages the EnhancedFX CSS styling capabilities to allow for detailed customization of the text area's appearance, including the title text and other state-dependent
 *     styles.</li>
 *     <li><b>Enhanced Skinning:</b> Utilizes a custom skin, {@link EnhancedTextAreaSkin}, to apply the enhanced features and styling, ensuring a cohesive user experience across different EnhancedFX
 *     components.</li>
 * </ul>
 * </p>
 *
 * <h2>Usage Example</h2>
 * <p>
 * <pre>{@code
 * EnhancedTextArea enhancedTextArea = new EnhancedTextArea("Initial Text");
 * enhancedTextArea.setTitleText("Enter your feedback");
 * enhancedTextArea.setTitleState(State.ENABLED);
 * }</pre>
 * </p>
 *
 * <p>
 * This class can be used as a direct replacement for the standard {@code TextArea}, with the added benefit of EnhancedFX's extended features and styling options. It is designed to be easy to use and
 * integrate into existing JavaFX applications, enhancing the UI without extensive changes to the underlying code.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EnhancedTextAreaSkin
 * @see EnhancedTextBase
 * @see InnerTextArea
 */
public class EnhancedTextArea extends EnhancedTextBase<TextArea> implements InnerTextArea<TextArea> {
    private static final String ENHANCED_TEXT_AREA_STYLE_CLASS = "enhanced-text-area";

    private static final StyleablePropertiesManager STYLES_MANAGER = new StyleablePropertiesManager(EnhancedTextBase.getClassCssMetaData());

    protected static final PseudoClass TITLE_TEXT_ENABLED_PSEUDO_CLASS  = PseudoClass.getPseudoClass("title-text-enabled");
    protected static final PseudoClass TITLE_TEXT_DISABLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("title-text-disabled");

    private final TextArea       innerControl = new TextArea();
    private final StringProperty titleText    = new SimpleStringProperty(this, "titleText", "");

    private ExtendedStyleableObjectProperty<State> titleState;

    static {
        //region Title State
        //*****************************************************************
        // Title State
        //*****************************************************************

        CssFactory<EnhancedTextArea, State> titleStateFactory;
        titleStateFactory = CssFactory.<EnhancedTextArea, State>create()
                                      .property("-efx-title-state")
                                      .converter(EnumConverter.getEnumConverter(State.class))
                                      .initialValue(State.DISABLED)
                                      .isSettableFunction(node -> checkProperty(node.titleState))
                                      .propertyGetterFunction(node -> node.titleState);
        STYLES_MANAGER.addCssMetaData(titleStateFactory);

        //endregion Title State
    }

    /**
     * Constructs a new {@code EnhancedTextArea} instance with no initial text.
     *
     * <p>
     * This default constructor initializes an empty {@code EnhancedTextArea}, setting up the foundational configuration inherited from the superclass and preparing the text area for subsequent configuration or
     * text entry. It's ideal for scenarios where the text content is to be provided by the user or programmatically set at a later time.
     * </p>
     */
    public EnhancedTextArea() {
        super();
    }

    /**
     * Constructs a new {@code EnhancedTextArea} instance with the specified initial text.
     *
     * <p>
     * This constructor provides a convenient way to create an {@code EnhancedTextArea} pre-populated with given text, streamlining the setup for use cases where initial content is known at construction time.
     * This can be particularly useful in scenarios where the text area is used to display predefined content or edit existing information.
     * </p>
     *
     * <p>
     * After performing the necessary initializations inherited from the superclass, the provided text is set as the current content of the text area, making it immediately visible and editable to the user.
     * </p>
     *
     * @param text
     *         The initial text to set in the text area. If {@code null}, the text area will be initialized as empty. This behavior ensures that the component can be safely used even when initial content might
     *         not be available or is dynamically determined.
     */
    public EnhancedTextArea(String text) {
        super();
        innerControl.setText(text);
    }

    //region EnhancedControlBase Functions
    //*****************************************************************
    // EnhancedControlBase Functions
    //*****************************************************************

    /**
     * Retrieves the current instance of {@code EnhancedTextArea}. This method is an implementation of an abstract method from the superclass, designed to return the specific control instance that it
     * represents. In the context of this class, it returns {@code this}, indicating that the current object itself is the {@code EnhancedTextArea} control in use.
     *
     * <p>
     * This method is crucial for internal mechanisms and utilities that require a reference to the control instance, ensuring consistent and type-safe access to the enhanced text area's functionalities and
     * properties.
     * </p>
     *
     * @return the current instance of {@code EnhancedTextArea}, signifying the control itself. This facilitates direct interaction with the enhanced features and behaviors specific to the
     *         {@code EnhancedTextArea}.
     */
    @Override
    protected EnhancedTextArea getControl() {
        return this;
    }

    @Override
    protected void loadNewTheme(Observable obs, Theme oldTheme, Theme newTheme) {
        loadNewThemeHelper(Stylesheets.ENHANCED_TEXT_AREA, oldTheme, newTheme);
    }

    /**
     * This method builds upon {@code setupStyleableProperties} from the superclass, and configures the styleable properties specific to this {@code EnhancedTextArea} control instance such as {@code titleState}
     * to {@code EnhancedTextArea} control's capabilities.
     *
     * <p>
     * The {@code titleState} property represents the state of the title text in the graphical interface, such as being enabled or disabled. It holds a value of the {@code State} enumeration.
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
        titleState = ExtendedStyleableObjectPropertyFactory.<State>builder()
                                                           .bean(this)
                                                           .name("titleState")
                                                           .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-title-state"))
                                                           .defaultValue(State.DISABLED)
                                                           .invalidatedPropCallback(this::titleTextStateInvalidated)
                                                           .build();
    }

    /**
     * Configures the control with necessary styling and functional enhancements.
     *
     * <p>
     * This method builds upon {@code setupControl} from the superclass, applying an additional stylesheet and style class specific to {@code EnhancedTextArea}. The configuration ensures the control adheres to
     * a consistent styling scheme, complemented by the enhanced functionality provided by the custom stylesheet and class.
     * </p>
     *
     * <p>
     * Utilizing {@code CustomControlConfigurator}, this setup process demonstrates a cohesive approach to control initialization, emphasizing ease of maintenance and scalability in control customization.
     * </p>
     *
     * @implNote The stylesheet path is validated to ensure the stylesheet exists and is accessible. This method employs the {@link CustomControlConfigurator} to fluently apply the stylesheet, style
     *         class, and property change listeners, emphasizing a builder pattern approach for elegant and efficient control setup.
     */
    @Override
    protected void setupControl() {
        super.setupControl();
        String stylesheetPath = checkStylesheetPathExists(Stylesheets.ENHANCED_TEXT_AREA, this.getClass());

        CustomControlConfigurator.create(getControl())
                                 .addStyleClass(ENHANCED_TEXT_AREA_STYLE_CLASS)
                                 .addStylesheet(stylesheetPath);
    }

    /**
     * This method builds upon {@code updatePseudoClassStates} from the superclass, and updates the pseudo class states of the component based on the current state of the title text
     *
     * @see EnhancedTextArea#TITLE_TEXT_ENABLED_PSEUDO_CLASS
     * @see EnhancedTextArea#TITLE_TEXT_DISABLED_PSEUDO_CLASS
     */
    @Override
    protected void updatePseudoClassStates() {
        super.updatePseudoClassStates();
        pseudoClassStateChanged(TITLE_TEXT_ENABLED_PSEUDO_CLASS, isTitleTextEnabled());
        pseudoClassStateChanged(TITLE_TEXT_DISABLED_PSEUDO_CLASS, isTitleTextDisabled());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EnhancedTextAreaSkin(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextArea getInnerControl() {
        return innerControl;
    }

    //endregion EnhancedControlBase Functions

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Retrieves the title text of the EnhancedTextArea.
     *
     * @return The title text of the EnhancedTextArea.
     */
    public String getTitleText() {
        return titleText.get();
    }

    /**
     * Retrieves the string property representing the title text of the EnhancedTextArea.
     *
     * @return the string property representing the title text
     */
    public StringProperty titleTextProperty() {
        return titleText;
    }

    /**
     * Sets the title text of the EnhancedTextArea.
     *
     * @param titleText
     *         the new title text to set
     */
    public void setTitleText(String titleText) {
        this.titleText.set(titleText);
    }

    /**
     * Returns a boolean value that indicates whether the title text is enabled or not.
     *
     * @return {@code true} if the title text is enabled, {@code false} otherwise.
     */
    public boolean isTitleTextEnabled() {
        return this.titleState.valueEquals(State.ENABLED);
    }

    /**
     * Determines whether the title text of the EnhancedTextArea is disabled.
     *
     * @return {@code true} if the title text is disabled, {@code false} otherwise.
     */
    public boolean isTitleTextDisabled() {
        return this.titleState.valueEquals(State.DISABLED);
    }

    //endregion Getters and Setters

    //region StyleableProperties Invalidate Functions
    //*****************************************************************
    // StyleableProperties Invalidate Functions
    //*****************************************************************

    /**
     * Handles the invalidation of the {@link StyleableObjectProperty} representing the {@link State} of the title text.
     *
     * @param prop
     *         the styleable object property representing the state
     */
    private void titleTextStateInvalidated(StyleableObjectProperty<State> prop) {
        State state = prop.get();

        pseudoClassStateChanged(TITLE_TEXT_ENABLED_PSEUDO_CLASS, state.equals(State.ENABLED));
        pseudoClassStateChanged(TITLE_TEXT_DISABLED_PSEUDO_CLASS, state.equals(State.DISABLED));
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
        return EnhancedTextArea.STYLES_MANAGER.getCssMetaDataList();
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
