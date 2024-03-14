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
package io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons;

import io.github.colindj1120.enhancedfx.controls.control.base.RippleControls;
import io.github.colindj1120.enhancedfx.controls.control.efxsupportedcontrol.EFXSupportedControl;
import io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.base.InnerButton;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.controls.skins.EFXButtonSkin;
import io.github.colindj1120.enhancedfx.graphics.effects.ripple.EFXRippleEffect;
import io.github.colindj1120.enhancedfx.base.exceptions.SkinException;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.controls.CustomControlConfigurator;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

public class EFXButton extends EFXSupportedControl<Button> implements RippleControls, InnerButton<Button> {
    private static final StyleablePropertiesManager stylesManager               = new StyleablePropertiesManager(EFXSupportedControl.getClassCssMetaData());
    private static final String                     ENHANCED_BUTTON_STYLE_CLASS = "enhanced-button";

    protected final Button               innerButton = new Button();
    protected final ObjectProperty<Node> icon        = new SimpleObjectProperty<>(this, "icon");

    public EFXButton() {
        super();
        setupButton("", null);
    }

    public EFXButton(String text) {
        super();
        setupButton(text, null);
    }

    public EFXButton(String text, Node graphic) {
        super();
        setupButton(text, graphic);
    }

    //region EFXControlBase Functions
    //*****************************************************************
    // EFXControlBase Functions
    //*****************************************************************

    /**
     * Returns the current instance of {@code EFXButton}. This implementation of the abstract method from the superclass is designed to provide access to the specific control instance represented by this
     * class. In this context, it directly returns {@code this}, indicating the current object is the {@code EFXButton} itself.
     *
     * <p>
     * This method is essential for obtaining a reference to the enhanced button instance, enabling internal and external mechanisms to interact with the button's enhanced functionalities and properties in a
     * type-safe manner.
     * </p>
     *
     * @return the current instance of {@code EFXButton}, which is the control itself. This enables straightforward access to the control's enhanced features and behaviors, tailored specifically for the
     *         {@code EFXButton}.
     */
    @Override
    protected EFXButton getControl() {
        return this;
    }

    @Override
    protected void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
        loadNewThemeHelper(EFXStylesheets.ENHANCED_BUTTON, oldEFXTheme, newEFXTheme);
    }

    @Override
    protected void setupStyleableProperties() {
        super.setupStyleableProperties();
        //TODO
    }

    @Override
    protected void setupControl() {
        super.setupControl();
        String stylesheetPath = checkStylesheetPathExists(EFXStylesheets.ENHANCED_BUTTON, this.getClass());

        CustomControlConfigurator.create(this)
                                 .addStyleClass(ENHANCED_BUTTON_STYLE_CLASS)
                                 .addStylesheet(stylesheetPath);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EFXButtonSkin(this);
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

    //endregion EFXControlBase Functions

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
    public EFXRippleEffect getRippleEffect() {
        if (getSkin() instanceof EFXButtonSkin skin) {
            return skin.getRippleEffect();
        } else {
            throw new SkinException("Enhanced Button's associated skin isn't of the type EFXButtonSkin");
        }
    }

    //endregion Overridden Methods

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Retrieves the icon associated with the EFXButton.
     *
     * @return The icon as a Node object.
     */
    public Node getIcon() {
        return icon.get();
    }

    /**
     * Returns the icon property of the EFXButton.
     *
     * @return The icon property of the EFXButton as an ObjectProperty<Node>.
     */
    public ObjectProperty<Node> iconProperty() {
        return icon;
    }

    /**
     * Sets the icon for the EFXButton.
     *
     * @param icon
     *         The icon to be set for the EFXButton.
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
        return EFXButton.stylesManager.getCssMetaDataList();
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
