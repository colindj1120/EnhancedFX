package io.github.colindj1120.enhancedfx.skins;

import io.github.colindj1120.enhancedfx.controls.EnhancedButton;
import io.github.colindj1120.enhancedfx.effects.ripple.RippleEffect;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.ButtonConfigurator;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.LabeledConfigurator;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.TextFieldConfigurator;
import io.github.colindj1120.enhancedfx.skins.base.EnhancedSupportedControlSkin;
import io.github.colindj1120.enhancedfx.utils.InsetUtils;
import io.github.colindj1120.enhancedfx.utils.PropertyUtils;
import io.github.colindj1120.enhancedfx.utils.UIUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import static io.github.colindj1120.enhancedfx.utils.UIUtils.TRANSPARENT_BACKGROUND_PROPERTY;

public class EnhancedButtonSkin extends EnhancedSupportedControlSkin<EnhancedButton> {
    private RippleEffect rippleEffect;

    private final Label icon = new Label();

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control
     *         The control for which this Skin should attach to.
     */
    public EnhancedButtonSkin(EnhancedButton control) {
        super(control);
        setupInnerControl();
        setupRippleEffect();

        getChildren().addAll(control.getInnerControl(), rippleEffect);

        control.requestLayout();
    }

    private void setupInnerControl() {
        EnhancedButton control = getSkinnable();

        ObjectProperty<Insets> paddingProperty = PropertyUtils.toObjectProperty(InsetUtils.empty());
        ObjectProperty<Border> borderProperty  = PropertyUtils.toObjectProperty(Border.EMPTY);

        ButtonConfigurator.create(control.getInnerControl())
                          .bindPaddingProperty(paddingProperty)
                          .bindBorderProperty(borderProperty)
                          .bindBackgroundProperty(TRANSPARENT_BACKGROUND_PROPERTY)
                          .bindManagedProperty(PropertyUtils.toBooleanProperty(false))
                          .addFontChangeListener(handleFontChange(control));
    }

    @NotNull
    private ChangeListener<Font> handleFontChange(EnhancedButton control) {
        return (observable, oldFont, newFont) -> {
            Font supportingLabelFont = supportingTextLabel.getFont();

            UIUtils.setLabelFont(supportingTextLabel, supportingLabelFont, newFont);
            control.requestLayout();
        };
    }

    private void setupRippleEffect() {
        rippleEffect = new RippleEffect(getSkinnable());
        rippleEffect.managedProperty()
                    .bind(Bindings.createBooleanBinding(() -> false));
    }

    /**
     * Returns the RippleEffect instance associated with the EnhancedToggleButtonSkin. The RippleEffect provides visual feedback
     * when the button is interacted with, creating a material design-inspired ripple effect.
     *
     * @return the RippleEffect instance associated with the EnhancedToggleButtonSkin.
     *
     * @see EnhancedToggleButtonSkin
     * @see RippleEffect
     */
    public RippleEffect getRippleEffect() {
        return rippleEffect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return leftInset + getSkinnable().getInnerControl()
                                         .prefWidth(-1) + rightInset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return topInset + getSkinnable().getInnerControl()
                                        .prefHeight(-1) + bottomInset;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Resizes the ripple effect to be the same size as the control.
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        getSkinnable().getInnerControl()
                      .resizeRelocate(x, y, w, h);
        rippleEffect.resizeRelocate(0, 0, w, h);
    }
}
