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
package io.github.colindj1120.enhancedfx.controls.skins.base;

import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxsupportedcontrol.EFXSupportedControl;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxsupportedcontrol.base.SupportingTextPosition;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.EFXTextField;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label.LabelConfigurator;
import io.github.colindj1120.enhancedfx.utils.EFXInsetUtils;
import io.github.colindj1120.enhancedfx.utils.EFXPropertyUtils;
import io.github.colindj1120.enhancedfx.utils.EFXUIUtils;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;

public abstract class EFXSupportedControlSkin<T extends EFXSupportedControl<?>> extends EFXControlSkin<T> {
    public static final String SUPPORTING_TEXT_LABEL_STYLE = "supporting-text-label";
    protected final     Label  supportingTextLabel         = new Label();

    protected EFXSupportedControlSkin(T control) {
        super(control);
    }

    @Override
    protected void initialize() {
        this.setupSupportingTextLabel();
    }

    /**
     * Sets up the supporting text label for the given control. The supporting text label is a label that provides additional information or context for the control.
     *
     * <p>This method binds various properties of the supporting text label to the properties of the control, such as visibility, text content, background, and style class.</p>
     *
     * <p>Additionally, it adds a listener to the text property of the supporting text label, which triggers a layout request for the control whenever the text changes.</p>
     *
     * @see EFXTextField
     * @see Label
     */
    private void setupSupportingTextLabel() {
        T control = getSkinnable();

        LabelConfigurator.create(supportingTextLabel)
                         .bindBackgroundProperty(EFXUIUtils.TRANSPARENT_BACKGROUND_PROPERTY)
                         .bindManagedProperty(EFXPropertyUtils.toBooleanProperty(false))
                         .bindVisibleProperty(Bindings.createBooleanBinding(control::isSupportingTextEnabled, control.supportingTextStateProperty()))
                         .bindTextProperty(control.supportingTextProperty())
                         .addVisibleChangeListener(EFXUIUtils.manageLabelVisibility(supportingTextLabel, getChildren(), control))
                         .addStyleClass(SUPPORTING_TEXT_LABEL_STYLE);

        InvalidationListener invalidationListener = ignored -> control.requestLayout();
        CustomControlConfigurator.create(control)
                                 .addObjectPropertyInvalidationListener(control.supportingTextXOffsetProperty(), invalidationListener)
                                 .addObjectPropertyInvalidationListener(control.supportingTextYOffsetProperty(), invalidationListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutSupportingTextLabel(x, y, w, h);
    }

    private void layoutSupportingTextLabel(double x, double y, double w, double h) {
        T control = getSkinnable();
        if (control.isSupportingTextEnabled()) {
            double supportingTextLabelWidth  = supportingTextLabel.prefWidth(-1);
            double supportingTextLabelHeight = supportingTextLabel.prefHeight(-1);

            switch (control.getSupportingTextPosition()) {
                case SupportingTextPosition.TOP -> layoutSupportingTextLabelTop(x, y, w, h, control, supportingTextLabelWidth, supportingTextLabelHeight);
                case SupportingTextPosition.BOTTOM -> layoutSupportingTextLabelBottom(x, h, control, supportingTextLabelWidth, supportingTextLabelHeight);
                case SupportingTextPosition.LEFT -> layoutSupportingTextLabelLeft(x, y, w, h, control, supportingTextLabelWidth, supportingTextLabelHeight);
                case SupportingTextPosition.RIGHT -> layoutSupportingTextLabelRight(x, y, w, h, control, supportingTextLabelWidth, supportingTextLabelHeight);
            }
        }
    }

    private void layoutSupportingTextLabelTop(double x, double y, double w, double h, T control, double labelWidth, double labelHeight) {

    }

    private void layoutSupportingTextLabelBottom(double x, double h, T control, double labelWidth, double labelHeight) {
        double supportingTextLabelX = x + control.getSupportingTextXOffset();
        double supportingTextLabelY = h + EFXInsetUtils.getBottomBorderInset(control) + labelHeight + control.getSupportingTextYOffset();

        supportingTextLabel.resizeRelocate(supportingTextLabelX, supportingTextLabelY, labelWidth, labelHeight);
    }

    private void layoutSupportingTextLabelLeft(double x, double y, double w, double h, T control, double labelWidth, double labelHeight) {

    }

    private void layoutSupportingTextLabelRight(double x, double y, double w, double h, T control, double labelWidth, double labelHeight) {

    }
}
