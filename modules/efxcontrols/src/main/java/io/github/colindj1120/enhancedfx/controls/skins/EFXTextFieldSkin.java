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
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label.LabelConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textfield.TextFieldConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.property.CustomPropertyConfigurator;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.EFXTextField;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base.FloatMode;
import io.github.colindj1120.enhancedfx.controls.skins.base.EFXControlSkin;
import io.github.colindj1120.enhancedfx.controls.skins.base.EFXTextBaseSkin;
import io.github.colindj1120.enhancedfx.graphics.animation.EFXAnimationManager;
import io.github.colindj1120.enhancedfx.utils.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
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
 * The {@code EFXTextFieldSkin} class extends {@code EFXTextBaseSkin} to provide a specialized skin for {@link EFXTextField}, incorporating advanced UI features such as floating label animations and dynamic
 * visual adjustments based on control state.
 *
 * <p>This skin enhances the standard text field by introducing interactive behaviors that improve user experience and interface dynamism.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Floating Label Animation:</b> Implements an animation where the label transitions to a floating position above the text field when focused or populated.</li>
 *     <li><b>Dynamic Alignment and Background:</b> Adjusts text alignment and background dynamically, responding to changes in focus, text content, and control state.</li>
 *     <li><b>Custom Animation Controls:</b> Provides mechanisms to customize animation behaviors, including enabling/disabling animations and setting animation durations.</li>
 *     <li><b>Enhanced State Management:</b> Manages and reflects various states (e.g., focused, filled) through visual modifications, promoting an intuitive user interface.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * This example demonstrates how to apply {@code EFXTextFieldSkin} to an {@code EFXTextField} and configure it for a JavaFX application.
 * <pre>
 * {@code
 * public class MainApplication extends Application {
 *     @Override
 *     public void start(Stage primaryStage) {
 *         EFXTextField textField = new EFXTextField();
 *         EFXTextFieldSkin textFieldSkin = EFXTextFieldSkin.create(textField);
 *
 *         textField.setSkin(textFieldSkin);
 *         textField.setFloatingText("Enter your name");
 *         textField.setFloatAnimationEnabled(true);
 *
 *         StackPane root = new StackPane();
 *         root.getChildren().add(textField);
 *         Scene scene = new Scene(root, 300, 200);
 *         primaryStage.setTitle("EFXTextFieldSkin Demo");
 *         primaryStage.setScene(scene);
 *         primaryStage.show();
 *     }
 *
 *     public static void main(String[] args) {
 *         launch(args);
 *     }
 * }
 * }
 * </pre>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXTextBaseSkin
 * @see EFXTextField
 */
public class EFXTextFieldSkin extends EFXTextBaseSkin<EFXTextField> {
    protected static final Duration FLOATING_TEXT_ANIMATION_DURATION = Duration.millis(150);
    protected static final String   FLOAT_ANIMATION_KEY              = "floatTextAnimation";
    protected static final String   RESET_FLOAT_ANIMATION_KEY        = "resetTextAnimation";

    protected static final double scaleFactor = .75;

    protected       SimpleObjectProperty<Pos> textFieldAlignmentBase;
    protected final Scale                     scale               = Transform.scale(1, 1, 0, 0);
    protected final Translate                 translate           = Transform.translate(1, 1);
    protected final EFXAnimationManager       efxAnimationManager = new EFXAnimationManager();

    protected DoubleProperty floatingTargetY;

    protected final Label                   floatingTextLabel      = new Label();
    protected       CompletableFuture<Void> floatAnimationFinished = new CompletableFuture<>();
    protected       CompletableFuture<Void> resetAnimationFinished = new CompletableFuture<>();
    protected       EFXBooleanBinding       floatTextLabelVisibleAndModeInside;

    /**
     * Factory method to create a new instance of {@code EFXTextFieldSkin} for the specified {@code EFXTextField}.
     *
     * @param control
     *         The {@code EFXTextField} control to be skinned.
     *
     * @return A newly created instance of {@code EFXTextFieldSkin} associated with the given control.
     */
    public static EFXTextFieldSkin create(EFXTextField control) {
        return EFXControlSkin.create(EFXTextFieldSkin.class, control);
    }

    /**
     * Constructs an instance of {@code EFXTextFieldSkin} for the specified {@code EFXTextField} control.
     *
     * <p>This constructor initializes the skin with its control context, setting up necessary properties and binding for dynamic behavior.</p>
     *
     * @param control
     *         The {@code EFXTextField} control for which the skin is being created.
     */
    protected EFXTextFieldSkin(EFXTextField control) {
        super(control);
    }

    /**
     * Initializes the skin, setting up bindings for property changes, configuring floating label behavior, and preparing animations.
     *
     * <p>This method extends {@code super.initialize()} to include custom initialization tasks for {@code EFXTextFieldSkin}, such as configuring the text alignment base, setting up visibility bindings for the
     * floating label based on the text field's state, and initializing animations for interactive elements.</p>
     */
    @Override
    protected void initialize() {
        super.initialize();
        this.textFieldAlignmentBase = new SimpleObjectProperty<>(this, "textFieldAlignmentBase");
        this.floatingTargetY = new SimpleDoubleProperty(this, "floatingTargetY", 0.0);

        EFXTextField control = this.getSkinnable();

        BooleanProperty isFloatingLabelVisible = this.floatingTextLabel.visibleProperty();

        Callable<Boolean> isFloatModeInsideAndLabelVisible = () -> control.isFloatModeInside() && isFloatingLabelVisible.get();

        BooleanBinding binding = Bindings.createBooleanBinding(isFloatModeInsideAndLabelVisible, control.floatModeProperty(), isFloatingLabelVisible);

        this.floatTextLabelVisibleAndModeInside = EFXBooleanBinding.create(this, binding);

        this.setupTextField();
        this.setupFloatingTextLabel();
        this.setupAnimations();

        this.getChildren()
            .addAll(control.getInnerControl());

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

    /**
     * Creates a {@link ChangeListener<Pos>} that handles changes to the text field's alignment.
     *
     * <p>This listener updates the base alignment property of the text field whenever the alignment setting changes. This is crucial for ensuring that the text field's content alignment matches the user's
     * preference or requirements set programmatically at runtime.</p>
     *
     * @return A non-null {@link ChangeListener<Pos>} that updates the text field's base alignment property.
     */
    @NotNull
    private ChangeListener<Pos> handleTextFieldAlignmentChange() {
        return (obs, oldAlignment, newAlignment) -> textFieldAlignmentBase.set(newAlignment);
    }

    /**
     * Creates a {@link ChangeListener<Boolean>} that reacts to focus changes in an {@link EFXTextField}.
     *
     * <p>When the text field gains focus and floating animations are enabled, it triggers an animation to emphasize the floating label. Conversely, when the text field loses focus, it plays a reset animation
     * if floating animations are enabled. This behavior enhances the user interface by providing visual cues about the text field's state.</p>
     *
     * @param control
     *         The {@link EFXTextField} control whose focus changes are being monitored.
     *
     * @return A non-null {@link ChangeListener<Boolean>} that triggers animations based on the text field's focus state.
     */
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

    /**
     * Produces a {@link ChangeListener<Font>} dedicated to handling font changes within an {@link EFXTextField}.
     *
     * <p>This listener updates the font of the floating label to match the new font of the text field itself, maintaining consistency in the user interface. Additionally, it requests a layout update for the
     * control to ensure proper appearance after the font change.</p>
     *
     * @param control
     *         The {@link EFXTextField} control whose font changes are to be monitored.
     *
     * @return A non-null {@link ChangeListener<Font>} that updates the floating label's font in response to text field font changes.
     */
    @NotNull
    private ChangeListener<Font> handleTextFieldFontChange(EFXTextField control) {
        return (observable, oldFont, newFont) -> {
            Font floatingLabelFont = floatingTextLabel.getFont();

            EFXUIUtils.setLabelFont(floatingTextLabel, floatingLabelFont, newFont);
            control.requestLayout();
        };
    }

    /**
     * Configures the floating text label for the {@link EFXTextField}.
     *
     * <p>This method sets up the floating text label's visibility, text binding, and interactions. It applies various configurations to ensure that the floating label behaves correctly according to the
     * control's state and user interactions.</p>
     *
     * <p>
     * <em>This includes:</em>
     * <ul>
     *     <li>Binding the label's visibility and text to the control's properties.</li>
     *     <li>Applying a background property to the label.</li>
     *     <li>Configuring click behavior to request focus for the inner text field.</li>
     *     <li>Listening for changes in the control's float mode and animation enablement to adjust label behavior.</li>
     * </ul>
     * </p>
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
                                 .addEFXBooleanBindingChangeListener(control.isFloatAnimationEnabledProperty(), handleFloatAnimEnabled(innerField));

        CustomPropertyConfigurator.create(floatingTargetY)
                                  .addInvalidationListener(handleFloatTargetYInvalid(control));
    }

    /**
     * Returns an {@link InvalidationListener} that adjusts the alignment of the inner text field based on the visibility and mode of the floating label.
     *
     * <p>When the floating label is visible and in 'inside' mode, the text field's alignment is adjusted to maintain visual consistency. This listener ensures that the text field's content alignment
     * dynamically adapts to changes in the floating label's state.</p>
     *
     * @param innerField
     *         The inner {@link TextField} of the control.
     *
     * @return A non-null {@link InvalidationListener} for adjusting text field alignment.
     */
    @NotNull
    private InvalidationListener handleFloatVisibleAndModeInvalid(TextField innerField) {
        return ignored -> {
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

    /**
     * Generates an {@link EventHandler<MouseEvent>} that requests focus for the inner text field when the floating text label is clicked.
     *
     * <p>This event handler enhances the user experience by allowing users to activate the text field by interacting with the floating label, mimicking the behavior of the text field itself.</p>
     *
     * @param control
     *         The {@link EFXTextField} control.
     * @param innerField
     *         The inner {@link TextField} of the control.
     *
     * @return A non-null {@link EventHandler<MouseEvent>} that handles clicks on the floating text label.
     */
    @NotNull
    private EventHandler<MouseEvent> handleFloatingTextClicked(EFXTextField control, TextField innerField) {
        return ignored -> {
            if (control.isFloatAnimationEnabled() && isScaleXOne()) {
                innerField.requestFocus();
            }
        };
    }

    /**
     * Generates an {@link ObjectProperty<Background>} that dynamically updates based on the text field's current text mode and float mode.
     *
     * <p>This method binds the background property to change based on whether the text field is in filled text mode or if float mode is not set to border. It ensures the background of the floating text label
     * or any related component adapts to the control's state, enhancing visual consistency.</p>
     *
     * @param control
     *         The {@link EFXTextField} control for which the background property is being generated.
     *
     * @return A non-null {@link ObjectProperty<Background>} that represents the dynamic background property for the control.
     */
    @NotNull
    private static ObjectProperty<Background> getBackgroundProperty(EFXTextField control) {
        Callable<Boolean> booleanCallable = () -> control.isTextModeFilled() || !control.isFloatModeBorder();

        BooleanBinding isTextModeFilledOrNotFloatModeBorder = Bindings.createBooleanBinding(booleanCallable, control.floatModeProperty(), control.textModeProperty());

        ObjectBinding<Background> backgroundBinding = Bindings.when(isTextModeFilledOrNotFloatModeBorder)
                                                              .then(EFXUIUtils.TRANSPARENT_BACKGROUND_PROPERTY)
                                                              .otherwise(control.backgroundProperty());
        return EFXObservableUtils.bindingToObjectProperty(backgroundBinding);
    }

    /**
     * Returns a {@link ChangeListener<FloatMode>} that updates the skin's animation state when the float mode of the control changes.
     *
     * <p>This listener reacts to changes in the float mode property of the control. It triggers a setup of the animation state and a layout request to ensure that any visual adjustments required due to the
     * float mode change are applied.</p>
     *
     * @param control
     *         The {@link EFXTextField} control being observed.
     *
     * @return A non-null {@link ChangeListener<FloatMode>} for handling changes in float mode.
     */
    @NotNull
    private ChangeListener<FloatMode> handleFloatMode(EFXTextField control) {
        return (obs, oldMode, newMode) -> {
            if (oldMode != newMode) {
                setupAnimationState(true);
                control.requestLayout();
            }
        };
    }

    /**
     * Creates a {@link ChangeListener<Boolean>} that updates the animation state based on the float animation enablement property.
     *
     * <p>This listener is particularly focused on handling changes to the float animation enabled state when the text field is not focused. It ensures that the animation state is appropriately configured
     * based on the enablement status, aiding in the dynamic behavior of the float label.</p>
     *
     * @param innerField
     *         The inner {@link TextField} component of the control.
     *
     * @return A non-null {@link ChangeListener<Boolean>} for handling changes to the float animation enabled state.
     */
    @NotNull
    private ChangeListener<Boolean> handleFloatAnimEnabled(TextField innerField) {
        return (obs, oldState, isEnabled) -> {
            if (!innerField.isFocused()) {
                setupAnimationState(isEnabled);
            }
        };
    }

    /**
     * Produces an {@link InvalidationListener} that is invoked when the target Y position for floating animation is invalidated.
     *
     * <p>This listener ensures that animations and their states are correctly set up based on the control's configuration, such as always floating state and float animation enablement. It triggers a re-setup
     * of animations and their states to adapt to changes in the control's properties.</p>
     *
     * @param control
     *         The {@link EFXTextField} control for which the targets Y position invalidation is handled.
     *
     * @return A non-null {@link InvalidationListener} for handling invalidation of the floating target Y position.
     */
    @NotNull
    private InvalidationListener handleFloatTargetYInvalid(EFXTextField control) {
        return ignored -> {
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
     * Sets up the animations for the EFXTextField control. This method clears any existing animations and sets up the float text animation and reset text animation. If the control's float mode is not disabled,
     * the float text animation and reset text animation will be setup. Additionally, the onFinished event handlers for floatAnimationFinished and resetAnimationFinished are set to complete when the respective
     * animations finish.
     */
    protected void setupAnimations() {
        EFXTextField control = getSkinnable();
        efxAnimationManager.clearAnimations();
        if (!control.isFloatModeDisabled()) {
            setupFloatTextAnimation();
            setupResetTextAnimation();
            efxAnimationManager.setOnFinished(FLOAT_ANIMATION_KEY, ignored -> floatAnimationFinished.complete(null));
            efxAnimationManager.setOnFinished(RESET_FLOAT_ANIMATION_KEY, ignored -> resetAnimationFinished.complete(null));
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
     * <p>This method creates key frames for resetting the x-scale and x-translation properties, as well as the y-scale and y-translation properties. It then creates a timeline animation using the created key
     * frames and adds it to the animation manager.</p>
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
        return EFXAnimationUtils.createKeyFrame(FLOATING_TEXT_ANIMATION_DURATION, EFXAnimationUtils.createKeyValue(scaleProperty, scaleEndValue),
                                                EFXAnimationUtils.createKeyValue(translateProperty, translateEndValue));
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
