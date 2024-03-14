package io.github.colindj1120.enhancedfx.skins.base;

import io.github.colindj1120.enhancedfx.controls.base.EnhancedControl;
import javafx.scene.control.SkinBase;

public abstract class EnhancedControlSkin<T extends EnhancedControl<?>> extends SkinBase<T> {
    /**
     * Constructs an {@code EnhancedControlSkin} instance for the specified control. This constructor initializes the skin with the control that it will be associated with. It calls the superclass constructor
     * with the control as an argument, ensuring that the base skin functionalities are properly initialized.
     *
     * @param control
     *         The control for which this skin is being created. This control should not be {@code null}.
     */
    protected EnhancedControlSkin(T control) {
        super(control);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset);

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().getMaxWidth() == Double.MAX_VALUE) {return Double.MAX_VALUE;}
        return getSkinnable().prefWidth(-1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset);

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().getMaxHeight() == Double.MAX_VALUE) {return Double.MAX_VALUE;}
        return getSkinnable().prefHeight(-1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
    }
}
