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
package io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons;

import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.exceptions.SkinException;
import io.github.colindj1120.enhancedfx.controls.control.base.RippleControls;
import io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.base.InnerToggleButton;
import io.github.colindj1120.enhancedfx.controls.control.efxsupportedcontrol.EFXSupportedControl;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.controls.skins.EFXToggleButtonSkin;
import io.github.colindj1120.enhancedfx.graphics.effects.ripple.EFXRippleEffect;
import javafx.beans.Observable;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;

import java.util.List;

public class EFXToggleButton extends EFXSupportedControl<ToggleButton> implements RippleControls, InnerToggleButton<ToggleButton> {
    private static final StyleablePropertiesManager STYLES_MANAGER                     = new StyleablePropertiesManager(EFXSupportedControl.getClassCssMetaData());
    private static final String                     ENHANCED_TOGGLE_BUTTON_STYLE_CLASS = "enhanced-toggle-button";

    private final ToggleButton innerControl = new ToggleButton();

    public EFXToggleButton() {
        super();
    }

    public EFXToggleButton(String text) {
        super();
        innerControl.setText(text);
    }

    //region EFXControlBase Functions
    //*****************************************************************
    // EFXControlBase Functions
    //*****************************************************************

    @Override
    protected EFXToggleButton getControl() {
        return this;
    }

    @Override
    protected void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
        loadNewThemeHelper(EFXStylesheets.ENHANCED_TOGGLE_BUTTON, oldEFXTheme, newEFXTheme);
    }

    @Override
    protected void setupStyleableProperties() {
        //TODO
    }

    @Override
    protected void setupControl() {
        String stylesheetPath = checkStylesheetPathExists(EFXStylesheets.ENHANCED_BUTTON, this.getClass());

        CustomControlConfigurator.create(this)
                                 .addStyleClass(ENHANCED_TOGGLE_BUTTON_STYLE_CLASS)
                                 .addStylesheet(stylesheetPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EFXToggleButtonSkin(this);
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

    //endregion EFXControlBase Functions

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     *
     * @throws SkinException
     *         When the getSkin Method does not return a skin of type EFXToggleButtonSkin
     */
    @Override
    public EFXRippleEffect getRippleEffect() {
        if (getSkin() instanceof EFXToggleButtonSkin skin) {
            return skin.getRippleEffect();
        } else {
            throw new SkinException("Enhanced Toggle Button's associated skin isn't of the type EFXToggleButtonSkin");
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

