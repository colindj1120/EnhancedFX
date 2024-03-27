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

import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.EFXTextArea;
import io.github.colindj1120.enhancedfx.controls.skins.base.EFXControlSkin;
import io.github.colindj1120.enhancedfx.controls.skins.base.EFXTextBaseSkin;
import javafx.scene.control.Label;

public class EFXTextAreaSkin extends EFXTextBaseSkin<EFXTextArea> {
    protected final Label titleTextLabel = new Label();

    public static EFXTextAreaSkin create(EFXTextArea control) {
        return EFXControlSkin.create(EFXTextAreaSkin.class, control);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.setupTextArea();
        this.setupTitleTextLabel();
    }

    protected EFXTextAreaSkin(EFXTextArea control) {
        super(control);
    }

    private void setupTextArea() {

    }

    private void setupTitleTextLabel() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutTitleTextLabel(x, y, w, h);
    }

    private void layoutTitleTextLabel(double x, double y, double w, double h) {

    }
}
