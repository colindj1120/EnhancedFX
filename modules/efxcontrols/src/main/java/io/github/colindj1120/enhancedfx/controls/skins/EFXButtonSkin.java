package io.github.colindj1120.enhancedfx.controls.skins;

import io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.EFXButton;
import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.ButtonConfigurator;
import io.github.colindj1120.enhancedfx.controls.skins.base.EFXSupportedControlSkin;
import io.github.colindj1120.enhancedfx.graphics.effects.ripple.EFXRippleEffect;
import io.github.colindj1120.enhancedfx.utils.EFXInsetUtils;
import io.github.colindj1120.enhancedfx.utils.EFXPropertyUtils;
import io.github.colindj1120.enhancedfx.utils.EFXUIUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

public class EFXButtonSkin extends EFXSupportedControlSkin<EFXButton> {
    private EFXRippleEffect efxRippleEffect;

    private final Label icon = new Label();

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control
     *         The control for which this Skin should attach to.
     */
    public EFXButtonSkin(EFXButton control) {
        super(control);
        setupInnerControl();
        setupRippleEffect();

        getChildren().addAll(control.getInnerControl(), efxRippleEffect);

        control.requestLayout();
    }

    private void setupInnerControl() {
        EFXButton control = getSkinnable();

        ObjectProperty<Insets> paddingProperty = EFXPropertyUtils.toObjectProperty(EFXInsetUtils.empty());
        ObjectProperty<Border> borderProperty  = EFXPropertyUtils.toObjectProperty(Border.EMPTY);

        ButtonConfigurator.create(control.getInnerControl())
                          .bindPaddingProperty(paddingProperty)
                          .bindBorderProperty(borderProperty)
                          .bindBackgroundProperty(EFXUIUtils.TRANSPARENT_BACKGROUND_PROPERTY)
                          .bindManagedProperty(EFXPropertyUtils.toBooleanProperty(false))
                          .addFontChangeListener(handleFontChange(control));
    }

    @NotNull
    private ChangeListener<Font> handleFontChange(EFXButton control) {
        return (observable, oldFont, newFont) -> {
            Font supportingLabelFont = supportingTextLabel.getFont();

            EFXUIUtils.setLabelFont(supportingTextLabel, supportingLabelFont, newFont);
            control.requestLayout();
        };
    }

    private void setupRippleEffect() {
        efxRippleEffect = new EFXRippleEffect(getSkinnable());
        efxRippleEffect.managedProperty()
                       .bind(Bindings.createBooleanBinding(() -> false));
    }

    /**
     * Returns the EFXRippleEffect instance associated with the EFXToggleButtonSkin. The EFXRippleEffect provides visual feedback
     * when the button is interacted with, creating a material design-inspired ripple effect.
     *
     * @return the EFXRippleEffect instance associated with the EFXToggleButtonSkin.
     *
     * @see EFXToggleButtonSkin
     * @see EFXRippleEffect
     */
    public EFXRippleEffect getRippleEffect() {
        return efxRippleEffect;
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
        efxRippleEffect.resizeRelocate(0, 0, w, h);
    }
}
