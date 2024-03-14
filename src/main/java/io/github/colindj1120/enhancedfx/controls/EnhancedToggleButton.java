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
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.controls;

import io.github.colindj1120.enhancedfx.controls.base.EnhancedSupportedControl;
import io.github.colindj1120.enhancedfx.controls.base.effects.RippleControls;
import io.github.colindj1120.enhancedfx.controls.base.inner.innerbutton.InnerToggleButton;
import io.github.colindj1120.enhancedfx.effects.ripple.RippleEffect;
import io.github.colindj1120.enhancedfx.enums.styling.Theme;
import io.github.colindj1120.enhancedfx.exceptions.SkinException;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.skins.EnhancedToggleButtonSkin;
import io.github.colindj1120.enhancedfx.styling.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.enums.styling.Stylesheets;
import javafx.beans.Observable;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;

import java.util.List;

public class EnhancedToggleButton extends EnhancedSupportedControl<ToggleButton> implements RippleControls, InnerToggleButton<ToggleButton> {
    private static final StyleablePropertiesManager STYLES_MANAGER = new StyleablePropertiesManager(EnhancedSupportedControl.getClassCssMetaData());
    private static final String ENHANCED_TOGGLE_BUTTON_STYLE_CLASS = "enhanced-toggle-button";

    private final ToggleButton innerControl = new ToggleButton();

    public EnhancedToggleButton() {
        super();
    }

    public EnhancedToggleButton(String text) {
        super();
        innerControl.setText(text);
    }

    //region EnhancedControlBase Functions
    //*****************************************************************
    // EnhancedControlBase Functions
    //*****************************************************************

    @Override
    protected EnhancedToggleButton getControl() {
        return this;
    }

    @Override
    protected void loadNewTheme(Observable obs, Theme oldTheme, Theme newTheme) {
        loadNewThemeHelper(Stylesheets.ENHANCED_TOGGLE_BUTTON, oldTheme, newTheme);
    }

    @Override
    protected void setupStyleableProperties() {
        //TODO
    }

    @Override
    protected void setupControl() {
        String stylesheetPath = checkStylesheetPathExists(Stylesheets.ENHANCED_BUTTON, this.getClass());

        CustomControlConfigurator.create(this)
                                 .addStyleClass(ENHANCED_TOGGLE_BUTTON_STYLE_CLASS)
                                 .addStylesheet(stylesheetPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EnhancedToggleButtonSkin(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToggleButton getInnerControl() {
        return innerControl;
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

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     *
     * @throws SkinException
     *         When the getSkin Method does not return a skin of type EnhancedToggleButtonSkin
     */
    @Override
    public RippleEffect getRippleEffect() {
        if (getSkin() instanceof EnhancedToggleButtonSkin skin) {
            return skin.getRippleEffect();
        } else {
            throw new SkinException("Enhanced Toggle Button's associated skin isn't of the type EnhancedToggleButtonSkin");
        }
    }

    //endregion Overridden Functions

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

