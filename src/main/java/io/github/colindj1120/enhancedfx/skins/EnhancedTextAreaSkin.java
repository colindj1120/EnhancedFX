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
package io.github.colindj1120.enhancedfx.skins;

import io.github.colindj1120.enhancedfx.controls.EnhancedTextArea;
import io.github.colindj1120.enhancedfx.skins.base.EnhancedTextBaseSkin;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class EnhancedTextAreaSkin extends EnhancedTextBaseSkin<EnhancedTextArea> {
    protected final Label titleTextLabel;

    public EnhancedTextAreaSkin(EnhancedTextArea control) {
        super(control);
        titleTextLabel = new Label();

        setupTextArea();
        setupTitleTextLabel();
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
