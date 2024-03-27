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
package io.github.colindj1120.enhancedfx.controls.skins;

import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.efxbuttons.EFXToggleButton;
import io.github.colindj1120.enhancedfx.controls.skins.base.EFXControlSkin;
import io.github.colindj1120.enhancedfx.graphics.effects.ripple.EFXRippleEffect;
import io.github.colindj1120.enhancedfx.controls.skins.base.EFXSupportedControlSkin;

public class EFXToggleButtonSkin extends EFXSupportedControlSkin<EFXToggleButton> {
    private final EFXRippleEffect efxRippleEffect;

    public static EFXToggleButtonSkin create(EFXToggleButton control) {
        return EFXControlSkin.create(EFXToggleButtonSkin.class, control);
    }

    @Override
    protected void initialize() {
        super.initialize();
        getChildren().add(efxRippleEffect);
    }

    public EFXToggleButtonSkin(EFXToggleButton control) {
        super(control);

        efxRippleEffect = EFXRippleEffect.create(control);


    }

    /**
     * Returns the EFXRippleEffect instance associated with the EFXToggleButtonSkin. The EFXRippleEffect provides visual feedback when the button is interacted with, creating a material design-inspired
     * ripple effect.
     *
     * @return the EFXRippleEffect instance associated with the EFXToggleButtonSkin.
     *
     * @see EFXToggleButtonSkin
     * @see EFXRippleEffect
     */
    public EFXRippleEffect getRippleEffect() {
        return efxRippleEffect;
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 0; //TODO:
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 0; //TODO:
    }

    /**
     * {@inheritDoc}
     * <p>
     * Resizes the ripple effect to be the same size as the control.
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        efxRippleEffect.resizeRelocate(0, 0, w, h);
    }
}