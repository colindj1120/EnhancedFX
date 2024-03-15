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

import io.github.colindj1120.enhancedfx.base.beans.binding.EFXBooleanBinding;
import io.github.colindj1120.enhancedfx.controls.control.efxtext.EFXTextField;
import io.github.colindj1120.enhancedfx.controls.control.efxtext.base.FloatMode;
import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.LabelConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.TextFieldConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.property.PropertyConfigurator;
import io.github.colindj1120.enhancedfx.controls.skins.base.EFXTextBaseSkin;
import io.github.colindj1120.enhancedfx.graphics.animation.EFXAnimationManager;
import io.github.colindj1120.enhancedfx.utils.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * The skin class for the {@code EFXTextFieldSkin} control.
 *
 * <p>
 * It customizes the appearance and behavior of the text field. This skin extends EFXTextBaseSkin and provides additional functionality for handling floating text labels, icons, animations, and layout
 * customization.
 * </p>
 *
 * <p>
 * The {@code EFXTextFieldSkin} class is responsible for creating a custom skin for the EFXTextField control. It enhances the standard text field by adding features such as floating text labels,
 * icons, animations, and advanced layout management. This class extends {@link EFXTextBaseSkin} to leverage existing functionality for handling text input and styling.
 * </p>
 *
 * <p>
 * The skin provides methods to set up the text field, floating text label, icons, and animations. It also handles layout calculations for the inner text field, leading and trailing icons, and the floating text
 * label. By extending {@link EFXTextBaseSkin}, it inherits basic functionality for managing text input and applying styles.
 * </p>
 *
 * <p>
 * <em>Key Features:</em>
 * <ul>
 *   <li>Floating text labels for providing context and enhancing user experience.</li>
 *   <li>Support for leading and trailing icons to visually enhance the text field.</li>
 *   <li>Configurable animations for smooth transitions between states.</li>
 *   <li>Advanced layout management to ensure proper alignment and spacing of elements.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * EFXTextField textField = new EFXTextField();
 * textField.setPromptText("Enter your text");
 * textField.setFloatModeInside(true);
 * textField.setFloatAnimationEnabled(true);
 * textField.setLeadingIcon(new ImageView("leading_icon.png"));
 * textField.setTrailingIcon(new ImageView("trailing_icon.png"));
 * textField.setFloatText("Floating Label");
 * textField.setAlwaysFloating(true);
 * }</pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXTextField
 * @see EFXTextBaseSkin
 */

public class EFXTextFieldSkin extends EFXTextBaseSkin<EFXTextField> {
    protected static final Duration FLOATING_TEXT_ANIMATION_DURATION = Duration.millis(150);
    protected static final String   FLOAT_ANIMATION_KEY              = "floatTextAnimation";
    protected static final String   RESET_FLOAT_ANIMATION_KEY        = "resetTextAnimation";

    protected static final double scaleFactor          = .75;

    protected final SimpleObjectProperty<Pos> textFieldAlignmentBase = new SimpleObjectProperty<>(this, "textFieldAlignmentBase");
    protected final Scale                     scale                  = Transform.scale(1, 1, 0, 0);
    protected final Translate           translate           = Transform.translate(1, 1);
    protected final EFXAnimationManager efxAnimationManager = new EFXAnimationManager();
    protected final DoubleProperty      floatingTargetY     = new SimpleDoubleProperty(this, "floatingTargetY", 0.0);

    protected final Label                     floatingTextLabel;
    protected       CompletableFuture<Void>   floatAnimationFinished;
    protected       CompletableFuture<Void> resetAnimationFinished;
    protected final EFXBooleanBinding       floatTextLabelVisibleAndModeInside;

    /**
     * The skin class for the EFXTextField control. It customizes the appearance and behavior of the text field.
     */
    public EFXTextFieldSkin(EFXTextField control) {
        super(control);
        this.floatingTextLabel = new Label();
        this.floatAnimationFinished = new CompletableFuture<>();
        this.resetAnimationFinished = new CompletableFuture<>();

        Callable<Boolean> isFloatModeInsideAndLabelVisible = () -> control.isFloatModeInside() && floatingTextLabel.visibleProperty()
                                                                                                                   .get();

        BooleanBinding binding = Bindings.createBooleanBinding(isFloatModeInsideAndLabelVisible, control.floatModeProperty(), floatingTextLabel.visibleProperty());

        this.floatTextLabelVisibleAndModeInside = EFXBooleanBinding.create(binding, this);

        setupTextField();
        setupFloatingTextLabel();
        setupAnimations();

        getChildren().addAll(control.getInnerControl());

        control.requestLayout();
    }

    /**
     * Sets up the text field by configuring various properties and adding listeners.
     */
    private void setupTextField() {
        EFXTextField control = getSkinnable();

        TextFieldConfigurator.create(control.getInnerControl())
                             .addFontChangeListener(handleTextFieldFontChange(control))
                             .addFocusedChangeListener(handleTextFieldFocusChange(control))
                             .addAlignmentChangeListener(handleTextFieldAlignmentChange())
                             .bindPaddingProperty(EFXPropertyUtils.toObjectProperty(EFXInsetUtils.empty()))
                             .bindBorderProperty(EFXPropertyUtils.toObjectProperty(Border.EMPTY))
                             .bindBackgroundProperty(EFXUIUtils.TRANSPARENT_BACKGROUND_PROPERTY)
                             .bindManagedProperty(EFXPropertyUtils.toBooleanProperty(false))
                             .addTextChangeListener(handleTextChanged(control))
                             .addFontChangeListener(handleFontChange(control));
    }

    @NotNull
    private ChangeListener<Pos> handleTextFieldAlignmentChange() {
        return (obs, oldAlignment, newAlignment) -> textFieldAlignmentBase.set(newAlignment);
    }

    @NotNull
    private ChangeListener<Boolean> handleTextFieldFocusChange(EFXTextField control) {
        return (observable, oldValue, isFocused) -> {
            if (isFocused && control.isFloatAnimationEnabled() && isScaleXOne()) {
                efxAnimationManager.playAnimation(FLOAT_ANIMATION_KEY);
            } else if (!isFocused && control.isFloatAnimationEnabled()) {
                efxAnimationManager.playAnimation(RESET_FLOAT_ANIMATION_KEY);
            }
        };
    }

    @NotNull
    private ChangeListener<Font> handleTextFieldFontChange(EFXTextField control) {
        return (observable, oldFont, newFont) -> {
            Font floatingLabelFont = floatingTextLabel.getFont();

            EFXUIUtils.setLabelFont(floatingTextLabel, floatingLabelFont, newFont);
            control.requestLayout();
        };
    }

    /**
     * Sets up the floating text label for the enhanced text field.
     */
    protected void setupFloatingTextLabel() {
        EFXTextField control    = getSkinnable();
        TextField    innerField = control.getInnerControl();

        floatTextLabelVisibleAndModeInside.addListener(handleFloatVisibleAndModeInvalid(innerField));

        ObjectProperty<Background> backgroundProperty = getBackgroundProperty(control);

        ChangeListener<Boolean> handleFloatTextVisible = EFXUIUtils.manageLabelVisibility(floatingTextLabel, getChildren(), control);

        LabelConfigurator.create(floatingTextLabel)
                         .bindBackgroundProperty(backgroundProperty)
                         .bindManagedProperty(EFXPropertyUtils.toBooleanProperty(false))
                         .bindVisibleProperty(Bindings.createBooleanBinding(() -> !control.isFloatModeDisabled(), control.floatModeProperty()))
                         .bindTextProperty(control.floatingTextProperty())
                         .addTextInvalidationListener(EFXUIUtils.requestControlLayout(control))
                         .addVisibleChangeListener(handleFloatTextVisible)
                         .setOnMouseClicked(handleFloatingTextClicked(control, innerField))
                         .addAllTransforms(scale, translate)
                         .addStyleClass("floating-text-label");

        CustomControlConfigurator.create(control)
                                 .addObjectPropertyChangeListener(control.floatModeProperty(), handleFloatMode(control))
                                 .addBooleanBindingAssociationChangeListener(control.isFloatAnimationEnabledProperty(), handleFloatAnimEnabled(innerField));

        PropertyConfigurator.create(floatingTargetY)
                            .addInvalidationListener(handleFloatTargetYInvalid(control));
    }

    @NotNull
    private InvalidationListener handleFloatVisibleAndModeInvalid(TextField innerField) {
        return (obs) -> {
            if (floatTextLabelVisibleAndModeInside.get()) {
                textFieldAlignmentBase.set(innerField.getAlignment());
                Pos baseline = EFXUIUtils.convertToBaseline(innerField.getAlignment());
                innerField.setAlignment(baseline);
            } else {
                Pos base = Objects.requireNonNullElse(textFieldAlignmentBase.get(), Pos.CENTER_LEFT);
                innerField.setAlignment(base);
            }
        };
    }

    @NotNull
    private EventHandler<MouseEvent> handleFloatingTextClicked(EFXTextField control, TextField innerField) {
        return e -> {
            if (control.isFloatAnimationEnabled() && isScaleXOne()) {
                innerField.requestFocus();
            }
        };
    }

    @NotNull
    private static ObjectProperty<Background> getBackgroundProperty(EFXTextField control) {
        Callable<Boolean> booleanCallable = () -> control.isTextModeFilled() || !control.isFloatModeBorder();

        BooleanBinding isTextModeFilledOrNotFloatModeBorder = Bindings.createBooleanBinding(booleanCallable, control.floatModeProperty(), control.textModeProperty());

        ObjectBinding<Background> backgroundBinding = Bindings.when(isTextModeFilledOrNotFloatModeBorder)
                                                              .then(EFXUIUtils.TRANSPARENT_BACKGROUND_PROPERTY)
                                                              .otherwise(control.backgroundProperty());
        return EFXBindingUtils.bindingToObjectProperty(backgroundBinding);
    }

    @NotNull
    private ChangeListener<FloatMode> handleFloatMode(EFXTextField control) {
        return (obs, oldMode, newMode) -> {
            if (oldMode != newMode) {
                setupAnimationState(true);
                control.requestLayout();
            }
        };
    }

    @NotNull
    private ChangeListener<Boolean> handleFloatAnimEnabled(TextField innerField) {
        return (obs, oldState, isEnabled) -> {
            if (!innerField.isFocused()) {
                setupAnimationState(isEnabled);
            }
        };
    }

    @NotNull
    private InvalidationListener handleFloatTargetYInvalid(EFXTextField control) {
        return invalidated -> {
            if (control.isAlwaysFloating()) {
                /*
                 * Reset the animation state since the isFloatAnimationEnabled will be false
                 * This allows the floating text label to redraw at its new floating location when the animation
                 * state is set below
                 */
                setupAnimationState(true);
            }
            setupAnimations();
            setupAnimationState(control.isFloatAnimationEnabled());
        };
    }

    /**
     * Sets up the animations for the EFXTextField control. This method clears any existing animations and sets up the float text animation and reset text animation. If the control's float mode is not
     * disabled, the float text animation and reset text animation will be setup. Additionally, the onFinished event handlers for floatAnimationFinished and resetAnimationFinished are set to complete when the
     * respective animations finish.
     */
    protected void setupAnimations() {
        EFXTextField control = getSkinnable();
        efxAnimationManager.clearAnimations();
        if (!control.isFloatModeDisabled()) {
            setupFloatTextAnimation();
            setupResetTextAnimation();
            efxAnimationManager.setOnFinished(FLOAT_ANIMATION_KEY, e -> floatAnimationFinished.complete(null));
            efxAnimationManager.setOnFinished(RESET_FLOAT_ANIMATION_KEY, e -> resetAnimationFinished.complete(null));
        }
    }

    /**
     * Sets up the float text animation. The method creates keyframes for floating animation in both X and Y dimensions and then creates a timeline with these keyframes. The created animation is added to the
     * animation manager.
     */
    protected void setupFloatTextAnimation() {
        KeyFrame keyFrameXFloat = createKeyFrame(scale.xProperty(), scaleFactor, translate.xProperty(), floatingTextLabel.getPadding()
                                                                                                                         .getLeft() * scaleFactor - 1);
        KeyFrame keyFrameYFloat     = createKeyFrame(scale.yProperty(), scaleFactor, translate.yProperty(), floatingTargetY.get());
        Timeline floatTextAnimation = EFXAnimationUtils.createAnimation(keyFrameXFloat, keyFrameYFloat);
        efxAnimationManager.addAnimation(FLOAT_ANIMATION_KEY, floatTextAnimation);
    }

    /**
     * Sets up the reset text animation for the EFXTextField.
     *
     * <p>This method creates key frames for resetting the x-scale and x-translation properties, as well as the y-scale and
     * y-translation properties. It then creates a timeline animation using the created key frames and adds it to the animation manager.</p>
     *
     * <p>This animation is used to reset the text field to its initial state after it has been scaled or translated.</p>
     */
    protected void setupResetTextAnimation() {
        KeyFrame keyFrameXReset     = createKeyFrame(scale.xProperty(), 1, translate.xProperty(), 1);
        KeyFrame keyFrameYReset     = createKeyFrame(scale.yProperty(), 1, translate.yProperty(), 1);
        Timeline resetTextAnimation = EFXAnimationUtils.createAnimation(keyFrameXReset, keyFrameYReset);
        efxAnimationManager.addAnimation(RESET_FLOAT_ANIMATION_KEY, resetTextAnimation);
    }

    /**
     * Creates a KeyFrame for animating the scale and translate properties of an object.
     *
     * @param scaleProperty
     *         The DoubleProperty representing the scale property to animate.
     * @param scaleEndValue
     *         The final value of the scale property.
     * @param translateProperty
     *         The DoubleProperty representing the translate property to animate.
     * @param translateEndValue
     *         The final value of the translate property.
     *
     * @return The created KeyFrame.
     */
    protected KeyFrame createKeyFrame(DoubleProperty scaleProperty, double scaleEndValue, DoubleProperty translateProperty, double translateEndValue) {
        return EFXAnimationUtils.createKeyFrame(FLOATING_TEXT_ANIMATION_DURATION, EFXAnimationUtils.createKeyValue(scaleProperty, scaleEndValue), EFXAnimationUtils.createKeyValue(translateProperty, translateEndValue));
    }

    /**
     * Sets up the animation state based on the given isEnabled parameter. If the float animation is currently playing, apply the state on animation finished using the floatAnimationFinished CompletableFuture.
     * If the reset animation is currently playing, apply the state on animation finished using the resetAnimationFinished CompletableFuture. Otherwise, applies the float animation state directly.
     *
     * @param isEnabled
     *         true if the animation should be enabled, false otherwise.
     */
    private void setupAnimationState(boolean isEnabled) {
        if (isFloatAnimationPlaying()) {
            applyStateOnAnimFinished(floatAnimationFinished, isEnabled, () -> floatAnimationFinished = new CompletableFuture<>());
        } else if (isResetAnimationPlaying()) {
            applyStateOnAnimFinished(resetAnimationFinished, isEnabled, () -> resetAnimationFinished = new CompletableFuture<>());
        } else {
            applyFloatAnimationState(isEnabled);
        }
    }

    /**
     * Calculates the target Y position for the floating label based on the current float mode.
     *
     * @return The target Y position for the floating label.
     */
    protected double getFloatingLabelTargetY(double floatingTextLabelY) {
        EFXTextField control = getSkinnable();
        if (control.isFloatModeDisabled()) {
            return 0.0;
        }

        double top            = EFXInsetUtils.getTopInsetWithBorder(control);
        double floatingHeight = floatingTextLabel.prefHeight(-1);

        return switch (getSkinnable().getFloatMode()) {
            case ABOVE -> (0 - floatingTextLabelY - top - floatingHeight / 2) / scaleFactor;
            case BORDER -> (0 - floatingTextLabelY - top) / scaleFactor;
            case INSIDE -> (0 - floatingTextLabelY - top + floatingHeight / 2) / scaleFactor;
            case DISABLED -> 0.0;
        };
    }

    /**
     * Checks if the float animation is currently playing.
     *
     * @return {@code true} if the float animation is playing, {@code false} otherwise.
     */
    protected boolean isFloatAnimationPlaying() {
        return efxAnimationManager.containsAnimation(FLOAT_ANIMATION_KEY) && efxAnimationManager.isAnimationPlaying(FLOAT_ANIMATION_KEY);
    }

    /**
     * Checks if the reset animation is currently playing.
     *
     * @return true if the reset animation is playing, false otherwise.
     */
    protected boolean isResetAnimationPlaying() {
        return efxAnimationManager.containsAnimation(RESET_FLOAT_ANIMATION_KEY) && efxAnimationManager.isAnimationPlaying(RESET_FLOAT_ANIMATION_KEY);
    }

    /**
     * Applies the animation state based on the given parameters. If the animation is completed, the {@code applyFloatAnimationState} method is called on the specified {@code completableFuture}. Once the
     * animation state is applied, the specified {@code runnable} is executed.
     *
     * @param completableFuture
     *         The CompletableFuture representing the completion of the animation.
     * @param isEnabled
     *         true if the animation should be enabled, false otherwise.
     * @param runnable
     *         The Runnable to be executed after applying the animation state.
     */
    protected void applyStateOnAnimFinished(CompletableFuture<Void> completableFuture, boolean isEnabled, Runnable runnable) {
        completableFuture.whenComplete((v, t) -> applyFloatAnimationState(isEnabled))
                         .thenRun(runnable);
    }

    /**
     * Applies the animation state based on the given isEnabled parameter.
     *
     * @param isEnabled
     *         true if the animation should be enabled, false otherwise.
     */
    protected void applyFloatAnimationState(boolean isEnabled) {
        if (isEnabled) {
            applyAnimationState(scale, translate, 1, 1, 1, 1);
        } else if (isScaleXOne()) {
            double translateX = floatingTextLabel.getPadding()
                                                 .getLeft() * scaleFactor + 1;
            applyAnimationState(scale, translate, scaleFactor, scaleFactor, translateX, floatingTargetY.get());
        }
    }

    /**
     * Applies the animation state to manipulate the given Scale and Translate properties.
     *
     * @param scale
     *         The Scale object to manipulate.
     * @param translate
     *         The Translate object to manipulate.
     * @param scaleX
     *         The new value for scaleX property of the Scale object.
     * @param scaleY
     *         The new value for scaleY property of the Scale object.
     * @param translateX
     *         The new value for translateX property of the Translate object.
     * @param translateY
     *         The new value for translateY property of the Translate object.
     */
    protected void applyAnimationState(Scale scale, Translate translate, double scaleX, double scaleY, double translateX, double translateY) {
        scale.setX(scaleX);
        scale.setY(scaleY);
        translate.setX(translateX);
        translate.setY(translateY);
    }

    /**
     * Determines whether the x-scale of the object is equal to 1.
     *
     * @return {@code true} if the x-scale is equal to 1, {@code false} otherwise.
     */
    protected boolean isScaleXOne() {
        return scale.getX() == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computePrefWidth(height, topInset, rightInset, bottomInset, leftInset);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        EFXTextField control = getSkinnable();
        double       height  = super.computePrefHeight(width, topInset, rightInset, bottomInset, leftInset);

        if (control.isFloatModeInside()) {
            height += floatingTextLabel.prefHeight(-1);
        }

        return height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutFloatingTextLabel(x, y, h);
    }

    /**
     * Layouts the floating text label within the specified bounds. This method is applicable when float mode is not disabled. It calculates the offset based on the presence of a leading icon and positions the
     * floating text label accordingly. The label is vertically centered within the layout bounds. The method also updates the target Y-position for the floating label's animation. If float mode is disabled,
     * the method performs no action.
     *
     * @param x
     *         the x-coordinate of the layout bounds.
     * @param y
     *         the y-coordinate of the layout bounds.
     * @param h
     *         the height of the layout bounds.
     */
    private void layoutFloatingTextLabel(double x, double y, double h) {
        EFXTextField control = getSkinnable();
        if (!control.isFloatModeDisabled()) {
            double leadingWidth            = EFXNodeUtils.getNodeWidth(control.getLeadingIcon(), LEADING_ICON_OFFSET);
            double floatingTextLabelWidth  = floatingTextLabel.prefWidth(-1);
            double floatingTextLabelHeight = floatingTextLabel.prefHeight(-1);
            double floatingTextLabelX      = x + leadingWidth - EFXInsetUtils.getLeftInset(floatingTextLabel);
            double floatingTextLabelY      = h / 2 + y - floatingTextLabelHeight / 2;

            floatingTextLabel.resizeRelocate(floatingTextLabelX, floatingTextLabelY, floatingTextLabelWidth, floatingTextLabelHeight);

            floatingTargetY.set(getFloatingLabelTargetY(floatingTextLabelY));
        }
    }
}
