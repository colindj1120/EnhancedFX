package io.github.colindj1120.enhancedfx.skins.base;

import io.github.colindj1120.enhancedfx.controls.EnhancedTextField;
import io.github.colindj1120.enhancedfx.controls.base.EnhancedSupportedControl;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.LabelConfigurator;
import io.github.colindj1120.enhancedfx.utils.InsetUtils;
import io.github.colindj1120.enhancedfx.utils.PropertyUtils;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;

import static io.github.colindj1120.enhancedfx.utils.UIUtils.TRANSPARENT_BACKGROUND_PROPERTY;
import static io.github.colindj1120.enhancedfx.utils.UIUtils.manageLabelVisibility;

public abstract class EnhancedSupportedControlSkin<T extends EnhancedSupportedControl<?>> extends EnhancedControlSkin<T> {
    public static final String SUPPORTING_TEXT_LABEL_STYLE = "supporting-text-label";
    protected final     Label  supportingTextLabel         = new Label();

    /**
     * Constructs an {@code EnhancedSupportedControlSkin} instance for the specified control. This constructor initializes the skin with the control that it will be associated with. It calls the superclass
     * constructor with the control as an argument, ensuring that the base skin functionalities are properly initialized. It then calls {@code setupSupportingTextLabel} to set up the internal supporting text
     * label.
     *
     * @param control
     *         The control for which this skin is being created. This control should not be {@code null}.
     */
    protected EnhancedSupportedControlSkin(T control) {
        super(control);
        setupSupportingTextLabel();
    }

    /**
     * Sets up the supporting text label for the given control. The supporting text label is a label that provides additional information or context for the control.
     *
     * <p>This method binds various properties of the supporting text label to the properties of the control,
     * such as visibility, text content, background, and style class.</p>
     *
     * <p>Additionally, it adds a listener to the text property of the supporting text label,
     * which triggers a layout request for the control whenever the text changes.</p>
     *
     * @see EnhancedTextField
     * @see Label
     */
    protected void setupSupportingTextLabel() {
        T control = getSkinnable();

        LabelConfigurator.create(supportingTextLabel)
                         .bindBackgroundProperty(TRANSPARENT_BACKGROUND_PROPERTY)
                         .bindManagedProperty(PropertyUtils.toBooleanProperty(false))
                         .bindVisibleProperty(Bindings.createBooleanBinding(control::isSupportingTextEnabled, control.supportingTextStateProperty()))
                         .bindTextProperty(control.supportingTextProperty())
                         .addVisibleChangeListener(manageLabelVisibility(supportingTextLabel, getChildren(), control))
                         .addStyleClass(SUPPORTING_TEXT_LABEL_STYLE);

        CustomControlConfigurator.create(control)
                                 .addObjectPropertyInvalidationListener(control.supportingTextXOffsetProperty(), invalidated -> control.requestLayout())
                                 .addObjectPropertyInvalidationListener(control.supportingTextYOffsetProperty(), invalidated -> control.requestLayout());
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
                case TOP -> layoutSupportingTextLabelTop(x, y, w, h, control, supportingTextLabelWidth, supportingTextLabelHeight);
                case BOTTOM -> layoutSupportingTextLabelBottom(x, h, control, supportingTextLabelWidth, supportingTextLabelHeight);
                case LEFT -> layoutSupportingTextLabelLeft(x, y, w, h, control, supportingTextLabelWidth, supportingTextLabelHeight);
                case RIGHT -> layoutSupportingTextLabelRight(x, y, w, h, control, supportingTextLabelWidth, supportingTextLabelHeight);
            }
        }
    }

    private void layoutSupportingTextLabelTop(double x, double y, double w, double h, T control, double labelWidth, double labelHeight) {

    }

    private void layoutSupportingTextLabelBottom(double x, double h, T control, double labelWidth, double labelHeight) {
        double supportingTextLabelX = x + control.getSupportingTextXOffset();
        double supportingTextLabelY = h + InsetUtils.getBottomBorderInset(control) + labelHeight + control.getSupportingTextYOffset();

        supportingTextLabel.resizeRelocate(supportingTextLabelX, supportingTextLabelY, labelWidth, labelHeight);
    }

    private void layoutSupportingTextLabelLeft(double x, double y, double w, double h, T control, double labelWidth, double labelHeight) {

    }

    private void layoutSupportingTextLabelRight(double x, double y, double w, double h, T control, double labelWidth, double labelHeight) {

    }
}
