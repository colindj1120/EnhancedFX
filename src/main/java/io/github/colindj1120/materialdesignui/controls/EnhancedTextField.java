/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.materialdesignui.controls;

import io.github.colindj1120.materialdesignui.animation.AnimationManager;
import io.github.colindj1120.materialdesignui.controls.base.EnhancedTextFieldBase;
import io.github.colindj1120.materialdesignui.enums.EnabledStatus;
import io.github.colindj1120.materialdesignui.enums.FloatMode;
import io.github.colindj1120.materialdesignui.utils.AnimationUtils;
import io.github.colindj1120.materialdesignui.utils.InsetUtils;
import io.github.colindj1120.materialdesignui.utils.WhenUtility;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static io.github.colindj1120.materialdesignui.utils.UIUtils.TRANSPARENT_BACKGROUND;

/**
 * {@code EnhancedTextField} is an advanced extension of {@link EnhancedTextFieldBase}, tailored for providing a feature-rich and customizable text field control in JavaFX. It incorporates additional
 * functionalities such as floating labels, character count, supporting text, and various animation effects to enhance the user interface. This class is part of the
 * {@code io.github.colindj1120.materialdesignui.controls} package, contributing to a Material Design UI framework for JavaFX applications.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Floating Labels:</b> Supports labels that animate and float above the text field when it gains focus.</li>
 *   <li><b>Character Count:</b> Provides character count functionality, helpful for input validation and user guidance.</li>
 *   <li><b>Supporting Text:</b> Allows adding additional descriptive text below the text field to assist the user.</li>
 *   <li><b>Icon Management:</b> Facilitates adding and managing leading and trailing icons for enhanced interactivity.</li>
 *   <li><b>Customizable Appearance:</b> Extensive CSS styling options for customizing the look and feel of the text field.</li>
 *   <li><b>Animation Effects:</b> Incorporates various animations for label transitions and focus changes.</li>
 * </ul>
 *
 * <p><b>Important Methods:</b></p>
 * <ul>
 *   <li>{@link #setupThis()} - Configures the basic properties and event handlers of the text field.</li>
 *   <li>{@link #setupIcons()} - Sets up the leading and trailing icons with their respective event listeners.</li>
 *   <li>{@link #setupTextField()} - Initializes the inner text field and binds it to necessary properties.</li>
 *   <li>{@link #setupCountLabel()} - Configures the character count label with its visibility and positioning.</li>
 *   <li>{@link #setupSupportingTextLabel()} - Sets up the supporting text label for additional information display.</li>
 *   <li>{@link #setupFloatingTextLabel()} - Prepares the floating text label with animations and visibility logic.</li>
 *   <li>{@link #iconChange(Node, Node, Label, boolean)} - Manages the addition and removal of icons in the text field.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * {@code EnhancedTextField} can be used in any JavaFX application where an enriched text input experience
 * is desired. Its versatile features and customizations make it suitable for various application scenarios,
 * from simple forms to complex user interfaces requiring detailed input assistance and validation.
 *
 * <pre>
 * EnhancedTextField enhancedTextField = new EnhancedTextField();
 * enhancedTextField.setPromptText("Enter text here");
 * enhancedTextField.setFloatingText("Floating Label");
 * // Additional configurations...
 * </pre>
 *
 * <p>This class is designed to be used as a standalone control within JavaFX scenes and layouts,
 * providing both functional and aesthetic enhancements over standard text field controls.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EnhancedTextFieldBase
 * @see TextField
 * @see Label
 * @see Node
 */
public class EnhancedTextField extends EnhancedTextFieldBase {
    protected static final Duration                FLOATING_TEXT_ANIMATION_DURATION      = Duration.millis(150);
    protected static final int                     DEFAULT_WIDTH                         = 180;
    protected static final int                     DEFAULT_SPACING                       = 5;
    protected static final int                     DEFAULT_VERTICAL_PADDING              = 0;
    protected static final int                     DEFAULT_HORIZONTAL_PADDING            = 8;
    protected static final int                     SCALE_XY_END_VALUE                    = 1;
    protected static final int                     TRANSLATE_XY_END_VALUE                = 1;
    protected static final int                     X_OFFSET                              = 1;
    protected static final int                     DEFAULT_TEXT_FIELD_HORIZONTAL_PADDING = 0;
    protected static final int                     DEFAULT_TEXT_FIELD_VERTICAL_PADDING   = 4;
    protected static final int                     CHARACTER_COUNT_ABOVE_OFFSET          = 1;
    protected static final int                     CHARACTER_COUNT_BELOW_OFFSET          = 1;
    protected static final int                     SUPPORTING_TEXT_OFFSET                = 1;
    protected static final int                     FLOAT_ABOVE_OFFSET                    = 1;
    protected static final int                     FLOAT_INSIDE_OFFSET                   = 2;
    protected static final String                  FLOAT_ANIMATION_KEY                   = "floatTextAnimation";
    protected static final String                  RESET_FLOAT_ANIMATION_KEY             = "resetTextAnimation";
    protected static final String                  TEXT_FIELD_STYLE                      = "inner-field";
    protected static final String                  CHARACTER_COUNT_STYLE                 = "count-label";
    protected static final String                  SUPPORTING_TEXT_STYLE                 = "supporting-text-label";
    protected static final String                  FLOATING_LABEL_STYLE                  = "floating-label";
    protected final        DoubleProperty          floatingTargetY                       = new SimpleDoubleProperty(this, "floatingTargetY", DEFAULT_VERTICAL_PADDING);
    protected final        AnimationManager        animationManager                      = new AnimationManager();
    protected              CompletableFuture<Void> floatAnimationFinished                = new CompletableFuture<>();
    protected              CompletableFuture<Void> resetAnimationFinished                = new CompletableFuture<>();
    protected              double                  oldTargetY                            = 0.0;

    /**
     * Creates a new instance of EnhancedTextField.
     *
     * <p>This method initializes the EnhancedTextField by applying CSS, configuring the initial state, setting up various components, icons, and labels, and setting the floating mode.</p>
     *
     * <p>It also updates the pseudo class states and sets up animations.</p>
     */
    public EnhancedTextField() {
        super();
        applyCss();
        setupThis();
        setupTextField();
        setupIcons();
        setupCountLabel();
        setupSupportingTextLabel();
        setupFloatingTextLabel();
        updatePseudoClassStates();

        // Platform.runLater is needed so that the initial setup of the animations happens after the GUI has been loaded
        Platform.runLater(this::setupAnimations);
    }

    /**
     * Sets up the initial configuration and behavior of the EnhancedTextField. This method adds necessary child elements to the parent container and applies various settings.
     */
    protected void setupThis() {
        this.getChildren()
            .addAll(textFieldContainer);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(DEFAULT_SPACING);
        this.setPadding(InsetUtils.verticalHorizontal(DEFAULT_VERTICAL_PADDING, DEFAULT_HORIZONTAL_PADDING));

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            textField.requestFocus();
            textField.deselect();
        });

        this.setPrefWidth(DEFAULT_WIDTH);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(Double.MAX_VALUE);

        this.setFocused(false);
        this.setFocusTraversable(false);

        this.focusedProperty()
            .addListener(obs -> cannotChangeProperty("focusedProperty", "textFieldFocusedProperty"));
        this.focusTraversableProperty()
            .addListener(obs -> cannotChangeProperty("focusTraversableProperty", "textFieldFocusTraversableProperty"));
        this.focusVisibleProperty()
            .addListener(obs -> cannotChangeProperty("focusVisibleProperty", "textFieldFocusVisibleProperty"));
        this.alignmentProperty()
            .addListener(obs -> cannotChangeProperty("alignmentProperty", "textFieldAlignmentProperty"));

        // Platform.runLater is needed so that the target Y position is calculated after the GUI is loaded into view
        Platform.runLater(() -> {
            DoubleBinding createdBinding = Bindings.createDoubleBinding(this::getFloatingLabelTargetY, boundsInParentProperty(), floatingTextLabel.boundsInParentProperty(),
                                                                        floatingTextLabel.paddingProperty(), textField.boundsInParentProperty(), textField.paddingProperty(), floatMode);

            this.floatingTargetY.bind(createdBinding);

            this.floatingTargetY.addListener(this::handleFloatingTargetYChange);
        });
    }

    /**
     * Handles changes in the floating target Y value.
     *
     * @param obs
     *         the Observable object that triggered the change
     * @param oldTargetValue
     *         the previous value of the target Y
     * @param newTargetValue
     *         the new value of the target Y
     */
    protected void handleFloatingTargetYChange(Observable obs, Number oldTargetValue, Number newTargetValue) {
        if (isFloatAnimationPlaying()) {
            return;
        }

        double newTarget = newTargetValue.doubleValue();
        // Greater than 1 ensures that oldTargetY is only updated if there is a change in the height
        if (isScaleXOne() && Math.abs(newTarget - oldTargetY) > 1) {
            oldTargetY = newTarget;
            setupAnimations();
        }
    }

    /**
     * Checks if the float animation is currently playing.
     *
     * @return {@code true} if the float animation is playing, {@code false} otherwise.
     */
    protected boolean isFloatAnimationPlaying() {
        return animationManager.containsAnimation(FLOAT_ANIMATION_KEY) && animationManager.isAnimationPlaying(FLOAT_ANIMATION_KEY);
    }

    /**
     * Throws an IllegalArgumentException indicating that the specified property cannot be changed.
     *
     * @param property
     *         the property name that cannot be changed
     * @param function
     *         the function name that should be used instead
     *
     * @throws IllegalArgumentException
     *         indicating that the property cannot be changed
     */
    protected void cannotChangeProperty(String property, String function) {
        throw new IllegalArgumentException(String.format("%s cannot be change as this call affects the text fields container, not the text field itself. Please use %s instead", property, function));
    }

    /**
     * Sets up the animations for the EnhancedTextField.
     * <p>
     * This method clears any existing animations and sets up animations for the floating text and reset text.
     * </p>
     * <p>
     * If the float mode is not disabled, it also sets the onFinished event for the float animation and reset animation.
     * </p>
     */
    protected void setupAnimations() {
        animationManager.clearAnimations();
        if (!isFloatModeDisabled()) {
            setupFloatTextAnimation();
            setupResetTextAnimation();
            animationManager.setOnFinished(FLOAT_ANIMATION_KEY, e -> floatAnimationFinished.complete(null));
            animationManager.setOnFinished(RESET_FLOAT_ANIMATION_KEY, e -> resetAnimationFinished.complete(null));
        }
    }

    /**
     * Sets up the float text animation for the EnhancedTextField.
     *
     * <p>This method creates a keyframe animation for the x and y properties of the scale and translate objects. The scale.xProperty is animated from its current value to the scaleFactor. The
     * translate.xProperty is animated from its current value to "floatingTextLabel.getPadding().getLeft() * scaleFactor - X_OFFSET". The scale.yProperty is animated from its current value to
     * scaleFactor. The translate.yProperty is animated from its current value to "-1 * floatingTargetY.get()".</p>
     *
     * <p>The keyframes are passed to the AnimationUtils.createAnimation() method to create the floatTextAnimation timeline. The floatTextAnimation timeline is added to the animationManager with the
     * FLOAT_ANIMATION_KEY.</p>
     */
    protected void setupFloatTextAnimation() {
        KeyFrame keyFrameXFloat = createKeyFrame(scale.xProperty(), scaleFactor, translate.xProperty(), floatingTextLabel.getPadding()
                                                                                                                         .getLeft() * scaleFactor - X_OFFSET);
        KeyFrame keyFrameYFloat     = createKeyFrame(scale.yProperty(), scaleFactor, translate.yProperty(), -1 * floatingTargetY.get());
        Timeline floatTextAnimation = AnimationUtils.createAnimation(keyFrameXFloat, keyFrameYFloat);
        animationManager.addAnimation(FLOAT_ANIMATION_KEY, floatTextAnimation);
    }

    /**
     * Sets up the reset text animation for the EnhancedTextField.
     *
     * <p>This method creates key frames for resetting the x-scale and x-translation properties, as well as the y-scale and y-translation properties. It then creates a timeline animation using the
     * created key frames and adds it to the animation manager.</p>
     *
     * <p>This animation is used to reset the text field to its initial state after it has been scaled or translated.</p>
     */
    protected void setupResetTextAnimation() {
        KeyFrame keyFrameXReset     = createKeyFrame(scale.xProperty(), SCALE_XY_END_VALUE, translate.xProperty(), TRANSLATE_XY_END_VALUE);
        KeyFrame keyFrameYReset     = createKeyFrame(scale.yProperty(), SCALE_XY_END_VALUE, translate.yProperty(), TRANSLATE_XY_END_VALUE);
        Timeline resetTextAnimation = AnimationUtils.createAnimation(keyFrameXReset, keyFrameYReset);
        animationManager.addAnimation(RESET_FLOAT_ANIMATION_KEY, resetTextAnimation);
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
        return AnimationUtils.createKeyFrame(FLOATING_TEXT_ANIMATION_DURATION, AnimationUtils.createKeyValue(scaleProperty, scaleEndValue),
                                             AnimationUtils.createKeyValue(translateProperty, translateEndValue));
    }

    /**
     * Sets up the icons for the text field.
     *
     * <p>This method adds listeners to the leadingIcon and trailingIcon properties, and calls the iconChange method when the icons change. It binds the graphicProperty of the leadingIconLabel to the
     * leadingIcon property, and the graphicProperty of the trailingIconLabel to the trailingIcon property.</p>
     */
    protected void setupIcons() {
        leadingIcon.addListener((obs, oldIcon, newIcon) -> iconChange(oldIcon, newIcon, leadingIconLabel, true));
        trailingIcon.addListener((obs, oldIcon, newIcon) -> iconChange(oldIcon, newIcon, trailingIconLabel, false));

        leadingIconLabel.graphicProperty()
                        .bind(leadingIcon);
        trailingIconLabel.graphicProperty()
                         .bind(trailingIcon);
    }

    /**
     * Sets up the text field with necessary configurations and listeners.
     */
    protected void setupTextField() {
        textField.getStyleClass()
                 .setAll(TEXT_FIELD_STYLE);
        textField.setPadding(InsetUtils.verticalHorizontal(DEFAULT_TEXT_FIELD_VERTICAL_PADDING, DEFAULT_TEXT_FIELD_HORIZONTAL_PADDING));

        textField.setBackground(TRANSPARENT_BACKGROUND.get());

        textField.focusedProperty()
                 .addListener((observable, oldValue, isFocused) -> {
                     if (isFocused && isFloatAnimationEnabled() && isScaleXOne()) {
                         animationManager.playAnimation(FLOAT_ANIMATION_KEY);
                     } else if (!isFocused && isFloatAnimationEnabled()) {
                         animationManager.playAnimation(RESET_FLOAT_ANIMATION_KEY);
                     }
                 });

        textField.fontProperty()
                 .addListener((observable, oldFont, newFont) -> {
                     Font floatingLabelFont   = floatingTextLabel.getFont();
                     Font supportingLabelFont = supportingTextLabel.getFont();

                     setLabelFont(floatingTextLabel, floatingLabelFont, newFont);
                     setLabelFont(supportingTextLabel, supportingLabelFont, newFont);
                 });

        textField.prefHeightProperty()
                 .bind(prefHeightProperty());
        textField.maxHeightProperty()
                 .bind(maxHeightProperty());

        textField.prefWidthProperty()
                 .bind(Bindings.createDoubleBinding(() -> {
                     double padding           = getPadding().getLeft() + getPadding().getRight();
                     double leadingIconWidth  = Objects.nonNull(getLeadingIcon()) ? leadingIconLabel.prefWidth(Region.USE_COMPUTED_SIZE) + DEFAULT_SPACING : 0;
                     double trailingIconWidth = Objects.nonNull(getTrailingIcon()) ? trailingIconLabel.prefWidth(Region.USE_COMPUTED_SIZE) + DEFAULT_SPACING : 0;

                     return getWidth() - padding - leadingIconWidth - trailingIconWidth;
                 }, widthProperty(), leadingIcon, trailingIcon, paddingProperty()));

        floatTextLabelVisibleAndModeInside.addListener((obs) -> {
            if (floatTextLabelVisibleAndModeInside.get()) {
                Pos baseline = convertToBaseline(textField.getAlignment());
                textField.setAlignment(baseline);
            } else {
                Pos base = Objects.requireNonNullElse(textFieldAlignmentBase.get(), Pos.CENTER_LEFT);
                textField.setAlignment(base);
            }
        });

        textField.textProperty()
                 .addListener((observable, oldValue, newValue) -> {
                     if (isMaxCharacterCountEnabled.get() && newValue.length() > maxCharacterCount.get()) {
                         textField.setText(oldValue);
                     }
                 });
    }

    /**
     * Determines whether the x-scale of the object is equal to 1.
     *
     * @return {@code true} if the x-scale is equal to 1, {@code false} otherwise.
     */
    protected boolean isScaleXOne() {
        return scale.getX() == SCALE_XY_END_VALUE;
    }

    /**
     * Sets up the count label for character count in a text field.
     * <p>
     * This method binds the length of the text in the text field to the text length property, sets up the binding for displaying the character count in the count label, and configures the visibility
     * and background of the count label.
     * </p>
     * <p>
     * It also adds a listener to the max character count property, and truncates the text in the text field if it exceeds the new max character count.
     * </p>
     * <p>
     * Additionally, it sets up the mouse click event to request focus when the count label is clicked, and applies a CSS style class to the count label.
     * </p>
     */
    protected void setupCountLabel() {
        characterCountLabel.translateXProperty()
                           .bind(Bindings.createDoubleBinding(() -> getHalvedWidthWithTextField(null, 1) - this.getPadding()
                                                                                                               .getRight(), textField.widthProperty(), this.paddingProperty()));

        DoubleBinding aboveBinding = Bindings.createDoubleBinding(() -> -1 * getHalvedHeightWithTextField(characterCountLabel) - CHARACTER_COUNT_ABOVE_OFFSET, textField.heightProperty(),
                                                                  characterCountLabel.heightProperty());

        DoubleBinding belowBinding = Bindings.createDoubleBinding(() -> getHalvedHeightWithTextField(characterCountLabel) + CHARACTER_COUNT_BELOW_OFFSET, textField.heightProperty(),
                                                                  characterCountLabel.heightProperty());

        characterCountLabel.translateYProperty()
                           .bind(WhenUtility.when(isMaxCharacterCountPosBelow.not(), aboveBinding)
                                            .andWhen(isMaxCharacterCountPosBelow, belowBinding)
                                            .otherwise(0.0)
                                            .asDoubleBinding());

        StringExpression charCountOverMax = Bindings.concat(lengthProperty().asString(), "/", maxCharacterCount.asString());
        setupLabel(charCountOverMax, characterCountLabel, isMaxCharacterCountEnabled);

        maxCharacterCount.addListener((obs, oldMax, newMax) -> {
            if (isMaxCharacterCountEnabled.get() && textField.getLength() > newMax.intValue()) {
                textField.setText(textField.getText(DEFAULT_VERTICAL_PADDING, getMaxCharacterCount()));
            }
        });

        characterCountLabel.getStyleClass()
                           .setAll(CHARACTER_COUNT_STYLE);
    }

    /**
     * Sets up the supporting text label.
     * <p>
     * This method is responsible for configuring the supporting text label by setting its style class, text, visibility, background, and mouse event handler. The supporting text label will be added
     * to or removed from the parent container based on its visibility.
     * </p>
     *
     * @see #isSupportingTextEnabled()
     */
    protected void setupSupportingTextLabel() {
        supportingTextLabel.translateXProperty()
                           .bind(Bindings.createDoubleBinding(() -> getHalvedWidthWithTextField(supportingTextLabel, -1) + floatingTextLabel.getPadding()
                                                                                                                                            .getLeft() + X_OFFSET, textField.widthProperty(),
                                                              supportingTextLabel.widthProperty(), floatingTextLabel.paddingProperty()));

        supportingTextLabel.translateYProperty()
                           .bind(Bindings.createDoubleBinding(() -> getHalvedHeightWithTextField(supportingTextLabel) + SUPPORTING_TEXT_OFFSET, textField.heightProperty(),
                                                              supportingTextLabel.heightProperty()));

        setupLabel(supportingText, supportingTextLabel, isSupportingTextEnabled);

        supportingTextLabel.getStyleClass()
                           .setAll(SUPPORTING_TEXT_STYLE);
    }

    /**
     * Sets up the label for displaying text in a user interface component.
     *
     * @param stringExpression
     *         the StringExpression to bind to the label's text property
     * @param label
     *         the Label to set up
     * @param booleanBinding
     *         the BooleanBinding to bind to the label's visible property
     */
    protected void setupLabel(StringExpression stringExpression, Label label, BooleanBinding booleanBinding) {
        label.textProperty()
             .bind(stringExpression);

        label.backgroundProperty()
             .bind(TRANSPARENT_BACKGROUND);

        label.visibleProperty()
             .bind(booleanBinding);

        label.visibleProperty()
             .addListener((obs, oldVisible, isVisible) -> {
                 if (isVisible) {
                     textFieldContainer.getChildren()
                                       .add(label);
                 } else {
                     textFieldContainer.getChildren()
                                       .remove(label);
                 }
                 autosize();
             });
    }

    /**
     * Sets up the floating text label for a text field. The floating text label is displayed above the text field when it is not empty. It is used to provide a hint or description of the expected
     * input in the text field. This method configures the visual appearance and behavior of the floating text label.
     */
    protected void setupFloatingTextLabel() {
        floatingTextLabel.translateXProperty()
                         .bind(Bindings.createDoubleBinding(() -> getHalvedWidthWithTextField(floatingTextLabel, -1) - floatingTextLabel.getPadding()
                                                                                                                                        .getLeft(), textField.widthProperty(),
                                                            floatingTextLabel.widthProperty(), floatingTextLabel.widthProperty()));

        floatingTextLabel.getTransforms()
                         .addAll(scale, translate);

        floatingTextLabel.backgroundProperty()
                         .bind(Bindings.when(isTextModeFilled.or(isFloatModeBorder.not()))
                                       .then(TRANSPARENT_BACKGROUND)
                                       .otherwise(backgroundProperty()));

        floatingTextLabel.setOnMouseClicked(e -> {
            if (isFloatAnimationEnabled() && isScaleXOne()) {
                textField.requestFocus();
            }
        });

        floatingTextLabel.visibleProperty()
                         .bind(isFloatModeDisabled.not());

        floatingTextLabel.textProperty()
                         .bind(floatingText);

        isFloatAnimationEnabled.addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                floatAnimationState.set(EnabledStatus.DISABLED);
            } else if (!isTextFieldFocused()) {
                floatAnimationState.set(EnabledStatus.ENABLED);
            }
        });

        floatAnimationState.addListener((obs, oldState, newState) -> {
            if (isFloatAnimationPlaying()) {
                applyStateOnAnimFinished(floatAnimationFinished, newState, () -> floatAnimationFinished = new CompletableFuture<>());
            } else if (isResetAnimationPlaying()) {
                applyStateOnAnimFinished(resetAnimationFinished, newState, () -> resetAnimationFinished = new CompletableFuture<>());
            } else {
                applyFloatAnimationState(newState);
            }
        });

        //Platform.runLater is needed because the label and field height need to be loaded into view before this can run
        floatMode.addListener((obs, oldMode, newMode) -> Platform.runLater(() -> {
            if (newMode == FloatMode.INSIDE) {
                double height = prefHeight(Region.USE_COMPUTED_SIZE);
                double combinedHeight = floatingTextLabel.prefHeight(Region.USE_COMPUTED_SIZE) + textField.prefHeight(Region.USE_COMPUTED_SIZE) - textField.getPadding()
                                                                                                                                                           .getBottom();
                if (combinedHeight > height) {
                    setPrefHeight(combinedHeight);
                }
            } else if (oldMode == FloatMode.INSIDE) {
                setPrefHeight(Region.USE_COMPUTED_SIZE);
            }
        }));

        floatingTextLabel.getStyleClass()
                         .setAll(FLOATING_LABEL_STYLE);
    }

    /**
     * Calculates the halved height of a Label with a TextField.
     *
     * @param label
     *         The Label element to calculate the height with.
     *
     * @return The halved height of the Label with the TextField.
     */
    protected double getHalvedHeightWithTextField(Label label) {
        return (textField.getHeight() / 2) + (label.getHeight() / 2);
    }

    /**
     * This method calculates the halved width with a given multiplier. If the label parameter is null, it calculates the halved width based on the width of the textField. If the label parameter is
     * not null, it calculates the halved width based on the sum of the widths of the textField and the label.
     *
     * @param label
     *         the label to consider in the calculation (null if not applicable)
     * @param multiplier
     *         the multiplier to be applied on the calculated halved width
     *
     * @return the halved width multiplied by the given multiplier
     */
    protected double getHalvedWidthWithTextField(Label label, double multiplier) {
        if (Objects.isNull(label)) {
            return multiplier * textField.getWidth() / 2;
        }
        return multiplier * (textField.getWidth() / 2) + (label.getWidth() / 2);
    }

    /**
     * Applies the given state of enabled status on animation finished.
     *
     * @param completableFuture
     *         The CompletableFuture that represents the completion of the animation.
     * @param newState
     *         The new state to be applied.
     * @param runnable
     *         The Runnable to be run after applying the state.
     */
    protected void applyStateOnAnimFinished(CompletableFuture<Void> completableFuture, EnabledStatus newState, Runnable runnable) {
        completableFuture.whenComplete((v, t) -> applyFloatAnimationState(newState))
                         .thenRun(runnable);
    }

    /**
     * Checks if the reset animation is currently playing.
     *
     * @return true if the reset animation is playing, false otherwise.
     */
    protected boolean isResetAnimationPlaying() {
        return animationManager.containsAnimation(RESET_FLOAT_ANIMATION_KEY) && animationManager.isAnimationPlaying(RESET_FLOAT_ANIMATION_KEY);
    }

    /**
     * Apply the float animation state based on the given {@link EnabledStatus}.
     *
     * @param newState
     *         The new state to apply.
     */
    protected void applyFloatAnimationState(EnabledStatus newState) {
        if (newState == EnabledStatus.ENABLED) {
            applyAnimationState(scale, translate, TRANSLATE_XY_END_VALUE, TRANSLATE_XY_END_VALUE, TRANSLATE_XY_END_VALUE, TRANSLATE_XY_END_VALUE);
        } else if (isScaleXOne()) {
            applyFloatState();
        }
    }

    /**
     * Applies animation state to the given Scale and Translate objects.
     */
    protected void applyFloatState() {
        double translateX = floatingTextLabel.getPadding()
                                             .getLeft() * scaleFactor - X_OFFSET;
        double translateY = -1 * floatingTargetY.get();
        applyAnimationState(scale, translate, scaleFactor, scaleFactor, translateX, translateY);
    }

    /**
     * Handles the change of an icon associated with the text field. This method is responsible for adding or removing a label that represents an icon (either leading or trailing) in the text field.
     * The method ensures that the label is added or removed from the appropriate position in the children of this control based on whether the icon is the first (leading) or last (trailing) element.
     *
     * <p>The method follows these rules:</p>
     * <ul>
     *   <li>If a new icon is being added (newIcon is non-null) and there was no old icon (oldIcon is null),
     *       the label is added to the children at the first or last position based on the {@code isFirst} flag.</li>
     *   <li>If an old icon is being removed or replaced (oldIcon is non-null), the corresponding label is
     *       removed from the first or last position in the children, again based on the {@code isFirst} flag.</li>
     * </ul>
     *
     * <p>Usage example:</p>
     * <pre>
     *  iconChange(null, newIcon, label, true); // Adds a new leading icon
     *  iconChange(oldIcon, null, label, false); // Removes a trailing icon
     * </pre>
     *
     * @param oldIcon
     *         The old icon node that was previously set. If this is non-null, it indicates that an icon is being replaced or removed.
     * @param newIcon
     *         The new icon node to be set. If this is non-null, it indicates that an icon is being added or replaced.
     * @param label
     *         The label that represents the icon. This label is added to or removed from the text field's children based on the presence or absence of the old and new icons.
     * @param isFirst
     *         A boolean flag indicating the position of the icon. If {@code true}, the icon is treated as the first (leading) element; if {@code false}, it is treated as the last (trailing) element.
     */
    protected void iconChange(Node oldIcon, Node newIcon, Label label, boolean isFirst) {
        ObservableList<Node> children = this.getChildren();
        if (Objects.nonNull(newIcon) && Objects.isNull(oldIcon)) {
            if (isFirst) {
                children.addFirst(label);
            } else {
                children.addLast(label);
            }
        } else if (Objects.nonNull(oldIcon)) {
            if (isFirst) {
                children.removeFirst();
            } else {
                children.removeLast();
            }
        }
    }

    /**
     * Sets the font of a given label to a new font based on the old font.
     *
     * @param label
     *         The label whose font needs to be set.
     * @param oldFont
     *         The old font from which the new font should be derived.
     * @param newFont
     *         The new font to be set for the label.
     */
    protected void setLabelFont(Label label, Font oldFont, Font newFont) {
        //@formatter:off
        label.setFont(Font.font(newFont.getFamily(),
                                FontWeight.findByName(oldFont.getStyle()),
                                FontPosture.findByName(oldFont.getStyle()),
                                newFont.getSize()));
        //@formatter:on
    }

    /**
     * Calculates the target Y position for the floating label based on the current float mode.
     *
     * @return The target Y position for the floating label.
     */
    protected double getFloatingLabelTargetY() {
        double floatingTextLabelMinY = floatingTextLabel.getBoundsInParent()
                                                        .getMinY();
        double floatingHeight = floatingTextLabel.getHeight();

        return switch (getFloatMode()) {
            case ABOVE -> floatingTextLabelMinY / scaleFactor + floatingHeight + FLOAT_ABOVE_OFFSET;
            case BORDER -> floatingTextLabelMinY / scaleFactor + floatingHeight / 2;
            case INSIDE -> floatingTextLabelMinY / scaleFactor - FLOAT_INSIDE_OFFSET;
            case DISABLED -> DEFAULT_VERTICAL_PADDING;
        };
    }

    /**
     * Applies animation state to the given Scale and Translate objects.
     *
     * @param scale
     *         the Scale object to apply animation state to.
     * @param translate
     *         the Translate object to apply animation state to.
     * @param scaleX
     *         the value to set for the X scale.
     * @param scaleY
     *         the value to set for the Y scale.
     * @param translateX
     *         the value to set for the X translation.
     * @param translateY
     *         the value to set for the Y translation.
     */
    protected void applyAnimationState(Scale scale, Translate translate, double scaleX, double scaleY, double translateX, double translateY) {
        scale.setX(scaleX);
        scale.setY(scaleY);
        translate.setX(translateX);
        translate.setY(translateY);
    }

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {return STYLEABLE_PROPERTIES_MANAGER.getCssMetaDataList();}
}
