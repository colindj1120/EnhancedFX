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

import io.github.colindj1120.enhancedfx.controls.base.EnhancedSupportedControl;
import io.github.colindj1120.enhancedfx.controls.base.effects.RippleControls;
import io.github.colindj1120.enhancedfx.controls.base.inner.innerbutton.InnerButton;
import io.github.colindj1120.enhancedfx.effects.ripple.RippleEffect;
import io.github.colindj1120.enhancedfx.enums.styling.Theme;
import io.github.colindj1120.enhancedfx.exceptions.SkinException;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.skins.EnhancedButtonSkin;
import io.github.colindj1120.enhancedfx.styling.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.enums.styling.Stylesheets;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;

import java.util.List;
import java.util.Optional;

/*
TODO:
1. button should have one icon that shows up right of the next
2. button should have display modes Rounded, Square, Raised, etc
3. button should have supporting text that can show up Top, Bottom, Left or Right
 */

public class EnhancedButton extends EnhancedSupportedControl<Button> implements RippleControls, InnerButton<Button> {
    private static final StyleablePropertiesManager stylesManager               = new StyleablePropertiesManager(EnhancedSupportedControl.getClassCssMetaData());
    private static final String                     ENHANCED_BUTTON_STYLE_CLASS = "enhanced-button";

    protected final Button               innerButton = new Button();
    protected final ObjectProperty<Node> icon        = new SimpleObjectProperty<>(this, "icon");

    public EnhancedButton() {
        super();
        setupButton("", null);
    }

    public EnhancedButton(String text) {
        super();
        setupButton(text, null);
    }

    public EnhancedButton(String text, Node graphic) {
        super();
        setupButton(text, graphic);
    }

    //region EnhancedControlBase Functions
    //*****************************************************************
    // EnhancedControlBase Functions
    //*****************************************************************

    /**
     * Returns the current instance of {@code EnhancedButton}. This implementation of the abstract method from the superclass is designed to provide access to the specific control instance represented by this
     * class. In this context, it directly returns {@code this}, indicating the current object is the {@code EnhancedButton} itself.
     *
     * <p>
     * This method is essential for obtaining a reference to the enhanced button instance, enabling internal and external mechanisms to interact with the button's enhanced functionalities and properties in a
     * type-safe manner.
     * </p>
     *
     * @return the current instance of {@code EnhancedButton}, which is the control itself. This enables straightforward access to the control's enhanced features and behaviors, tailored specifically for the
     *         {@code EnhancedButton}.
     */
    @Override
    protected EnhancedButton getControl() {
        return this;
    }

    @Override
    protected void loadNewTheme(Observable obs, Theme oldTheme, Theme newTheme) {
        loadNewThemeHelper(Stylesheets.ENHANCED_BUTTON, oldTheme, newTheme);
    }

    @Override
    protected void setupStyleableProperties() {
        super.setupStyleableProperties();
        //TODO
    }

    @Override
    protected void setupControl() {
        super.setupControl();
        String stylesheetPath = checkStylesheetPathExists(Stylesheets.ENHANCED_BUTTON, this.getClass());

        CustomControlConfigurator.create(this)
                                 .addStyleClass(ENHANCED_BUTTON_STYLE_CLASS)
                                 .addStylesheet(stylesheetPath);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EnhancedButtonSkin(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Button getInnerControl() {
        return innerButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        //TODO:
        return "";
    }

    //endregion EnhancedControlBase Functions

    //region Setup Methods
    //*****************************************************************
    // Setup Methods
    //*****************************************************************

    private void setupButton(String text, Node graphic) {
        Optional.ofNullable(graphic)
                .ifPresent(g -> innerButton.setGraphic(graphic));
        innerButton.setText(text);
    }

    //endregion Setup Methods

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RippleEffect getRippleEffect() {
        if (getSkin() instanceof EnhancedButtonSkin skin) {
            return skin.getRippleEffect();
        } else {
            throw new SkinException("Enhanced Button's associated skin isn't of the type EnhancedButtonSkin");
        }
    }

    //endregion Overridden Methods

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Retrieves the icon associated with the EnhancedButton.
     *
     * @return The icon as a Node object.
     */
    public Node getIcon() {
        return icon.get();
    }

    /**
     * Returns the icon property of the EnhancedButton.
     *
     * @return The icon property of the EnhancedButton as an ObjectProperty<Node>.
     */
    public ObjectProperty<Node> iconProperty() {
        return icon;
    }

    /**
     * Sets the icon for the EnhancedButton.
     *
     * @param icon
     *         The icon to be set for the EnhancedButton.
     */
    public void setIcon(Node icon) {
        this.icon.set(icon);
    }

    //endregion Getters and Setters

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
        return EnhancedButton.stylesManager.getCssMetaDataList();
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
