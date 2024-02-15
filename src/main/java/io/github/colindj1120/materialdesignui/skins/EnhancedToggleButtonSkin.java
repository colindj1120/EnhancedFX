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
package io.github.colindj1120.materialdesignui.skins;

import io.github.colindj1120.materialdesignui.effects.ripple.RippleEffect;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.skin.ToggleButtonSkin;

/**
 * Represents a customized skin for {@link ToggleButton} that incorporates a {@link RippleEffect}. This skin enhances the standard toggle button with a material design-inspired ripple effect,
 * providing visual feedback when the button is interacted with. The ripple effect is automatically sized and positioned to cover the entire area of the toggle button.
 * <p>
 * Usage of this skin requires the {@link ToggleButton} control to be instantiated with this skin class. The ripple effect is added as a child to the skin's node hierarchy, ensuring that it is
 * displayed whenever the toggle button is pressed or toggled.
 * </p>
 *
 * <h2>Example Usage</h2>
 * <pre>
 * ToggleButton toggleButton = new ToggleButton("Toggle Me");
 * toggleButton.setSkin(new EnhancedToggleButtonSkin(toggleButton));
 * </pre>
 *
 * <h2>Customization</h2>
 * The {@link RippleEffect} can be customized via CSS or programmatically after obtaining the ripple effect instance through the skin class. Customizations may include changing the ripple color,
 * animation duration, or effect radius.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ToggleButtonSkin
 * @see RippleEffect
 */
public class EnhancedToggleButtonSkin extends ToggleButtonSkin {
    private final RippleEffect rippleEffect;

    /**
     * Constructs a new EnhancedToggleButtonSkin for the specified {@link ToggleButton}. This constructor initializes the {@link RippleEffect} and adds it to the children of the toggle button skin,
     * ensuring the ripple effect is displayed on user interaction.
     *
     * @param control
     *         The {@link ToggleButton} control for which this skin is being created.
     */
    public EnhancedToggleButtonSkin(ToggleButton control) {
        super(control);

        rippleEffect = new RippleEffect(control);

        getChildren().add(rippleEffect);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Resizes the ripple effect to be the same size as the control.
     */
    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);
        rippleEffect.resizeRelocate(0, 0, contentWidth, contentHeight);
    }

    /**
     * Returns the RippleEffect instance associated with the EnhancedToggleButtonSkin. The RippleEffect provides visual feedback when the button is interacted with, creating a material design-inspired
     * ripple effect.
     *
     * @return the RippleEffect instance associated with the EnhancedToggleButtonSkin.
     *
     * @see EnhancedToggleButtonSkin
     * @see RippleEffect
     */
    public RippleEffect getRippleEffect() {
        return rippleEffect;
    }
}