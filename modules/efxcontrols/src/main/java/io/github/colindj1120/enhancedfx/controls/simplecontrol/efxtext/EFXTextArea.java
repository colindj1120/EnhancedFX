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

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.enums.EFXState;
import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol.base.EFXControlBase;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base.EFXTextBase;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base.InnerTextArea;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.controls.skins.EFXTextAreaSkin;
import io.github.colindj1120.enhancedfx.utils.EFXPropertyUtils;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.converter.EnumConverter;
import javafx.scene.control.Skin;
import javafx.scene.control.TextArea;

import java.util.List;

/**
 * The {@code EFXTextArea} class is an extension of the {@link EFXTextBase} tailored specifically for {@link TextArea} controls within the EnhancedFX library. It encapsulates functionalities that are unique to
 * text areas, providing additional features on top of the standard {@code TextArea} functionalities.
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
 *     <li><b>Enhanced Skinning:</b> Utilizes a custom skin, {@link EFXTextAreaSkin}, to apply the enhanced features and styling, ensuring a cohesive user experience across different EnhancedFX
 *     components.</li>
 * </ul>
 * </p>
 *
 * <h2>Usage Example</h2>
 * <p>
 * <pre>{@code
 * EFXTextArea enhancedTextArea = new EFXTextArea("Initial Text");
 * enhancedTextArea.setTitleText("Enter your feedback");
 * enhancedTextArea.setTitleState(EFXState.ENABLED);
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
 * @see EFXTextAreaSkin
 * @see EFXTextBase
 * @see InnerTextArea
 */
public class EFXTextArea extends EFXTextBase<TextArea> implements InnerTextArea<TextArea> {
    private static final String ENHANCED_TEXT_AREA_STYLE_CLASS = "enhanced-text-area";

    private static final StyleablePropertiesManager STYLES_MANAGER = new StyleablePropertiesManager(EFXTextBase.getClassCssMetaData());

    protected static final PseudoClass TITLE_TEXT_ENABLED_PSEUDO_CLASS  = PseudoClass.getPseudoClass("title-text-enabled");
    protected static final PseudoClass TITLE_TEXT_DISABLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("title-text-disabled");

    private final TextArea       innerControl = new TextArea();
    private       StringProperty titleText;

    private EFXStyleableObjectProperty<EFXState> titleState;

    static {
        //region Title EFXState
        //*****************************************************************
        // Title EFXState
        //*****************************************************************

        CssFactory<EFXTextArea, EFXState> titleStateFactory;
        titleStateFactory = CssFactory.<EFXTextArea, EFXState>create()
                                      .property("-efx-title-state")
                                      .converter(EnumConverter.getEnumConverter(EFXState.class))
                                      .initialValue(EFXState.DISABLED)
                                      .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.titleState))
                                      .propertyGetterFunction(node -> node.titleState);
        STYLES_MANAGER.addCssMetaData(titleStateFactory);

        //endregion Title EFXState
    }

    public static EFXTextArea create() {
        return EFXControlBase.create(EFXTextArea.class);
    }

    public static EFXTextArea create(String text) {
        EFXTextArea efxTextArea = EFXControlBase.create(EFXTextArea.class);
        efxTextArea.setText(text);

        return efxTextArea;
    }

    protected EFXTextArea() {
        super();
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.titleText = new SimpleStringProperty(this, "titleText", "");
    }

    //region EFXControlBase Functions
    //*****************************************************************
    // EFXControlBase Functions
    //*****************************************************************

    /**
     * Retrieves the current instance of {@code EFXTextArea}. This method is an implementation of an abstract method from the superclass, designed to return the specific control instance that it represents. In
     * the context of this class, it returns {@code this}, indicating that the current object itself is the {@code EFXTextArea} control in use.
     *
     * <p>
     * This method is crucial for internal mechanisms and utilities that require a reference to the control instance, ensuring consistent and type-safe access to the enhanced text area's functionalities and
     * properties.
     * </p>
     *
     * @return the current instance of {@code EFXTextArea}, signifying the control itself. This facilitates direct interaction with the enhanced features and behaviors specific to the {@code EFXTextArea}.
     */
    @Override
    protected EFXTextArea getControl() {
        return this;
    }

    @Override
    protected void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
        loadNewThemeHelper(EFXStylesheets.ENHANCED_TEXT_AREA, oldEFXTheme, newEFXTheme);
    }

    /**
     * This method builds upon {@code setupStyleableProperties} from the superclass, and configures the styleable properties specific to this {@code EFXTextArea} control instance such as {@code titleState} to
     * {@code EFXTextArea} control's capabilities.
     *
     * <p>
     * The {@code titleState} property represents the state of the title text in the graphical interface, such as being enabled or disabled. It holds a value of the {@code EFXState} enumeration.
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
        titleState = EFXStyleableObjectProperty.<EFXState>create()
                                               .bean(this)
                                               .name("titleState")
                                               .cssMetaData(STYLES_MANAGER.findCssMetaData("-efx-title-state"))
                                               .initialValue(EFXState.DISABLED)
                                               .invalidatedPropCallback(this::titleTextStateInvalidated)
                                               .build();
    }

    /**
     * Configures the control with necessary styling and functional enhancements.
     *
     * <p>
     * This method builds upon {@code setupControl} from the superclass, applying an additional stylesheet and style class specific to {@code EFXTextArea}. The configuration ensures the control adheres to a
     * consistent styling scheme, complemented by the enhanced functionality provided by the custom stylesheet and class.
     * </p>
     *
     * <p>
     * Utilizing {@code CustomNodeConfigurator}, this setup process demonstrates a cohesive approach to control initialization, emphasizing ease of maintenance and scalability in control customization.
     * </p>
     *
     * @implNote The stylesheet path is validated to ensure the stylesheet exists and is accessible. This method employs the {@link CustomControlConfigurator} to fluently apply the stylesheet, style
     *         class, and property change listeners, emphasizing a builder pattern approach for elegant and efficient control setup.
     */
    @Override
    protected void setupControl() {
        super.setupControl();
        String stylesheetPath = checkStylesheetPathExists(EFXStylesheets.ENHANCED_TEXT_AREA, this.getClass());

        CustomControlConfigurator.create(getControl())
                                 .addStyleClass(ENHANCED_TEXT_AREA_STYLE_CLASS)
                                 .addStylesheet(stylesheetPath);
    }

    /**
     * This method builds upon {@code updatePseudoClassStates} from the superclass, and updates the pseudo class states of the component based on the current state of the title text
     *
     * @see EFXTextArea#TITLE_TEXT_ENABLED_PSEUDO_CLASS
     * @see EFXTextArea#TITLE_TEXT_DISABLED_PSEUDO_CLASS
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
        return EFXTextAreaSkin.create(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextArea getInnerControl() {
        return innerControl;
    }

    //endregion EFXControlBase Functions

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Retrieves the title text of the EFXTextArea.
     *
     * @return The title text of the EFXTextArea.
     */
    public String getTitleText() {
        return titleText.get();
    }

    /**
     * Retrieves the string property representing the title text of the EFXTextArea.
     *
     * @return the string property representing the title text
     */
    public StringProperty titleTextProperty() {
        return titleText;
    }

    /**
     * Sets the title text of the EFXTextArea.
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
        return this.titleState.valueEquals(EFXState.ENABLED);
    }

    /**
     * Determines whether the title text of the EFXTextArea is disabled.
     *
     * @return {@code true} if the title text is disabled, {@code false} otherwise.
     */
    public boolean isTitleTextDisabled() {
        return this.titleState.valueEquals(EFXState.DISABLED);
    }

    //endregion Getters and Setters

    //region StyleableProperties Invalidate Functions
    //*****************************************************************
    // StyleableProperties Invalidate Functions
    //*****************************************************************

    /**
     * Handles the invalidation of the {@link EFXStyleableObjectProperty} representing the {@link EFXState} of the title text.
     *
     * @param prop
     *         the styleable object property representing the state
     */
    private void titleTextStateInvalidated(EFXStyleableObjectProperty<EFXState> prop) {
        EFXState efxState = prop.get();

        pseudoClassStateChanged(TITLE_TEXT_ENABLED_PSEUDO_CLASS, efxState.equals(EFXState.ENABLED));
        pseudoClassStateChanged(TITLE_TEXT_DISABLED_PSEUDO_CLASS, efxState.equals(EFXState.DISABLED));
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
        return EFXTextArea.STYLES_MANAGER.getCssMetaDataList();
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
