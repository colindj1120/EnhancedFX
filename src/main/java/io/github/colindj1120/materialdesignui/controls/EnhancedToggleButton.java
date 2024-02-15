/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.materialdesignui.controls;

import io.github.colindj1120.materialdesignui.controls.base.effects.RippleControls;
import io.github.colindj1120.materialdesignui.effects.ripple.RippleEffect;
import io.github.colindj1120.materialdesignui.exceptions.SkinException;
import io.github.colindj1120.materialdesignui.skins.EnhancedToggleButtonSkin;
import io.github.colindj1120.materialdesignui.styling.Stylesheets;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;

/**
 * Provides a custom implementation of a toggle button with enhanced functionalities. This class extends {@link ToggleButton} to provide additional features and customizations that are not available
 * in the standard toggle button. The enhancements can include visual modifications, behavior adjustments, or integration with other components or services. This class supports all the constructors of
 * its superclass and allows for further extension and customization through its methods.
 *
 * <p>
 * The {@code EnhancedToggleButton} can be instantiated without any text or with a specific text label. It overrides the {@code createDefaultSkin} method to provide a custom skin for the toggle
 * button, enabling unique styling and behavior that is not provided by the default implementation.
 * </p>
 * <p>
 * Usage Example:
 * <pre>
 * EnhancedToggleButton enhancedToggleButton = new EnhancedToggleButton("Click Me");
 * enhancedToggleButton.setOnAction(event -> {
 *     System.out.println("Enhanced Toggle Button Pressed");
 * });
 * </pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ToggleButton
 * @see Skin
 */
public class EnhancedToggleButton extends ToggleButton implements RippleControls {
    private static final String ENHANCED_TOGGLE_BUTTON_STYLE_CLASS = "enhanced-toggle-button";

    /**
     * Constructs an {@code EnhancedToggleButton} with no text. This constructor initializes a new toggle button with no label. The button will exhibit the enhanced features and custom skin provided
     * by this class.
     */
    public EnhancedToggleButton() {
        super();
        init();
    }

    /**
     * Constructs an {@code EnhancedToggleButton} with the specified text. This constructor initializes a new toggle button with the provided label text. The button will exhibit the enhanced features
     * and custom skin provided by this class.
     *
     * @param text
     *         the text to display on the toggle button
     */
    public EnhancedToggleButton(String text) {
        super(text);
        init();
    }

    /**
     * Initializes the EnhancedToggleButton. This method is called internally within the class to perform the necessary initialization steps for the toggle button. It sets the style class and
     * stylesheet for the button.
     */
    private void init() {
        setStyle();
    }

    /**
     * Sets the style of the EnhancedToggleButton. This method applies the enhanced toggle button style class to the control's style class list, and sets the stylesheet of the control to the
     * EnhancedToggleButton stylesheet. Any existing style classes or stylesheets will be replaced.
     */
    private void setStyle() {
        getStyleClass().setAll(ENHANCED_TOGGLE_BUTTON_STYLE_CLASS);
        getStylesheets().setAll(Stylesheets.ENHANCED_TOGGLE_BUTTON.getStyleSheet());
    }

    /**
     * Creates and returns the default skin for this {@code EnhancedToggleButton}. This method is overridden to supply a custom skin specific to the enhanced toggle button, allowing for customization
     * beyond the default look and behavior provided by the superclass.
     *
     * <p>
     * The returned skin is responsible for the visual representation of the toggle button. Developers can extend {@code EnhancedToggleButtonSkin} to further customize appearance and interactions.
     * </p>
     *
     * @return a new instance of {@code EnhancedToggleButtonSkin} specific to this toggle button
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EnhancedToggleButtonSkin(this);
    }

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
}

