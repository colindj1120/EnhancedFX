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
package io.github.colindj1120.materialdesignui.skins;

import io.github.colindj1120.materialdesignui.animation.AnimationManager;
import io.github.colindj1120.materialdesignui.controls.EnhancedTextField;
import io.github.colindj1120.materialdesignui.utils.AnimationUtils;
import io.github.colindj1120.materialdesignui.utils.InsetUtils;
import io.github.colindj1120.materialdesignui.utils.NodeUtils;
import io.github.colindj1120.materialdesignui.utils.UIUtils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static io.github.colindj1120.materialdesignui.utils.UIUtils.TRANSPARENT_BACKGROUND;
import static io.github.colindj1120.materialdesignui.utils.UIUtils.convertToBaseline;

/**
 * {@code EnhancedTextFieldSkin} is a custom skin implementation for {@link EnhancedTextField}, a material design inspired text field component. This skin enhances the standard text field with
 * additional features like floating labels, leading and trailing icons, character count, and support for various text field states with animations. It integrates closely with the
 * {@code EnhancedTextField} control, leveraging JavaFX properties and bindings to respond dynamically to changes in the control's state and user input.
 *
 * <p>Key Features:</p>
 * <ul>
 *     <li>Support for floating labels with animation, improving UX by providing context to the user input.</li>
 *     <li>Ability to add leading and trailing icons, enhancing the visual appeal and functionality (e.g., clear text, password visibility toggle).</li>
 *     <li>Character count feature, which is especially useful for inputs with length restrictions.</li>
 *     <li>Support text for additional information or validation messages.</li>
 *     <li>Customizable through CSS, allowing for extensive styling to match application themes.</li>
 *     <li>Animations managed by {@link AnimationManager}, providing smooth transitions between states.</li>
 * </ul>
 *
 * <p>Layout and Behavior:</p>
 * The skin computes the layout based on the presence of icons, floating label visibility, and text field size.
 * It dynamically adjusts the inner text field, icons, and labels' positions and sizes to achieve the material design
 * look and feel. Animations are applied to the floating label based on focus and input state changes.
 *
 * <p>Usage:</p>
 * This skin is automatically applied to {@code EnhancedTextField} controls. Customization and additional
 * configuration can be done through the control's API and CSS.
 *
 * <p>Example:</p>
 * <pre>
 * EnhancedTextField textField = new EnhancedTextField();
 * textField.setLeadingIcon(new ImageView(leadingIconImage));
 * textField.setTrailingIcon(new ImageView(trailingIconImage));
 * textField.setFloatingText("Label");
 * textField.setSupportingText("Support text");
 * </pre>
 *
 * <p>Styling:</p>
 * The skin supports styling through CSS classes defined in the {@code EnhancedTextField} control.
 * Users can override default styles for the floating label, character count, supporting text, and icons.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EnhancedTextField
 * @see AnimationManager
 * @see SkinBase
 */
public class EnhancedTextFieldSkin extends SkinBase<EnhancedTextField> {
    protected static final Duration FLOATING_TEXT_ANIMATION_DURATION = Duration.millis(150);
    protected static final String   FLOAT_ANIMATION_KEY              = "floatTextAnimation";
    protected static final String   RESET_FLOAT_ANIMATION_KEY        = "resetTextAnimation";

    protected static final int    LEADING_ICON_OFFSET  = 5;
    protected static final int    TRAILING_ICON_OFFSET = 2;
    protected static final double scaleFactor          = .75;

    protected final SimpleObjectProperty<Pos> textFieldAlignmentBase = new SimpleObjectProperty<>(this, "textFieldAlignmentBase");
    protected final Scale                     scale                  = Transform.scale(1, 1, 0, 0);
    protected final Translate                 translate              = Transform.translate(1, 1);
    protected final AnimationManager          animationManager       = new AnimationManager();
    protected final DoubleProperty            floatingTargetY        = new SimpleDoubleProperty(this, "floatingTargetY", 0.0);

    protected CompletableFuture<Void> floatAnimationFinished      = new CompletableFuture<>();
    protected CompletableFuture<Void> resetAnimationFinished      = new CompletableFuture<>();
    protected boolean                 skipMaxCharacterCountLayout = false;

    protected final TextField innerField;
    protected final Label     characterCountLabel;
    protected final Label     supportingTextLabel;
    protected final Label     floatingTextLabel;

    protected final ObjectProperty<Node> leadingIcon;
    protected final ObjectProperty<Node> trailingIcon;

    protected final BooleanBinding floatTextLabelVisibleAndModeInside;

    /**
     * The skin class for the EnhancedTextField control. It customizes the appearance and behavior of the text field.
     */
    public EnhancedTextFieldSkin(EnhancedTextField control) {
        super(control);
        this.innerField          = control.getInnerField();
        this.characterCountLabel = new Label();
        this.supportingTextLabel = new Label();
        this.floatingTextLabel   = new Label();
        this.leadingIcon         = control.leadingIconProperty();
        this.trailingIcon        = control.trailingIconProperty();

        this.floatTextLabelVisibleAndModeInside = floatingTextLabel.visibleProperty()
                                                                   .and(control.isFloatModeInsideProperty());

        setupTextField();
        setupSupportingTextLabel();
        setupCharacterCountLabel();
        setupFloatingTextLabel();
        setupIcons();
        setupAnimations();

        getChildren().addAll(this.innerField);

        control.requestLayout();
    }

    /**
     * Sets up the text field by configuring various properties and adding listeners.
     */
    private void setupTextField() {
        EnhancedTextField control = getSkinnable();
        innerField.paddingProperty()
                  .bind(Bindings.createObjectBinding(InsetUtils::empty));
        innerField.borderProperty()
                  .bind(Bindings.createObjectBinding(() -> Border.EMPTY));
        innerField.backgroundProperty()
                  .bind(UIUtils.TRANSPARENT_BACKGROUND);
        innerField.managedProperty()
                  .bind(Bindings.createBooleanBinding(() -> false));

        innerField.textProperty()
                  .addListener((observable, oldText, newText) -> {
                      int maxCharacterCount = control.getMaxCharacterCount();
                      if (control.isMaxCharacterCountEnabled()) {
                          if (newText.length() > maxCharacterCount) {
                              innerField.setText(oldText);
                          } else if (newText.length() == maxCharacterCount) {
                              if (!skipMaxCharacterCountLayout) {
                                  control.requestLayout();
                                  skipMaxCharacterCountLayout = true;
                              }
                          } else if (newText.length() == maxCharacterCount - 1) {
                              if (skipMaxCharacterCountLayout) {
                                  control.requestLayout();
                                  skipMaxCharacterCountLayout = false;
                              }
                          }
                      }
                  });

        innerField.fontProperty()
                  .addListener((observable, oldFont, newFont) -> {
                      Font floatingLabelFont       = floatingTextLabel.getFont();
                      Font supportingLabelFont     = supportingTextLabel.getFont();
                      Font characterCountLabelFont = characterCountLabel.getFont();

                      setLabelFont(floatingTextLabel, floatingLabelFont, newFont);
                      setLabelFont(supportingTextLabel, supportingLabelFont, newFont);
                      setLabelFont(characterCountLabel, characterCountLabelFont, newFont);
                      control.requestLayout();
                  });

        innerField.focusedProperty()
                  .addListener((observable, oldValue, isFocused) -> {
                      if (isFocused && control.isFloatAnimationEnabled() && isScaleXOne()) {
                          animationManager.playAnimation(FLOAT_ANIMATION_KEY);
                      } else if (!isFocused && control.isFloatAnimationEnabled()) {
                          animationManager.playAnimation(RESET_FLOAT_ANIMATION_KEY);
                      }
                  });

        innerField.alignmentProperty()
                  .addListener((obs, oldAlignment, newAlignment) -> textFieldAlignmentBase.set(newAlignment));
    }

    /**
     * Sets the font of a label to a new font based on the properties of the old font.
     *
     * @param label
     *         the label whose font will be changed
     * @param oldFont
     *         the old font to use as a reference
     * @param newFont
     *         the new font to set for the label
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
        EnhancedTextField control = getSkinnable();
        supportingTextLabel.managedProperty()
                           .bind(Bindings.createBooleanBinding(() -> false));

        supportingTextLabel.visibleProperty()
                           .bind(control.isSupportingTextEnabledProperty());
        supportingTextLabel.backgroundProperty()
                           .bind(TRANSPARENT_BACKGROUND);

        supportingTextLabel.textProperty()
                           .bind(control.supportingTextProperty());

        supportingTextLabel.textProperty()
                           .addListener(invalidated -> control.requestLayout());

        supportingTextLabel.visibleProperty()
                           .addListener(getLabelVisibilityChangeListener(supportingTextLabel));

        supportingTextLabel.getStyleClass()
                           .setAll("supporting-text-label");
    }

    /**
     * Sets up the character count label for the EnhancedTextField control. The character count label displays the current number of characters entered in the text field as well as the maximum
     * character count allowed. The label's visibility, text, background, and layout are bound to the properties of the control.
     */
    protected void setupCharacterCountLabel() {
        EnhancedTextField control = getSkinnable();

        characterCountLabel.managedProperty()
                           .bind(Bindings.createBooleanBinding(() -> false));

        StringExpression charCountOverMax = Bindings.concat(control.lengthProperty()
                                                                   .asString(), "/", control.maxCharacterCountProperty()
                                                                                            .asString());
        characterCountLabel.textProperty()
                           .bind(charCountOverMax);

        characterCountLabel.backgroundProperty()
                           .bind(TRANSPARENT_BACKGROUND);
        characterCountLabel.visibleProperty()
                           .bind(control.isMaxCharacterCountEnabledProperty());
        characterCountLabel.visibleProperty()
                           .addListener(getLabelVisibilityChangeListener(characterCountLabel));

        control.maxCharacterCountPositionProperty()
               .addListener(invalidated -> control.requestLayout());
        control.maxCharacterCountProperty()
               .addListener(invalidated -> control.requestLayout());
        control.maxCharacterCountProperty()
               .addListener((obs, oldMax, newMax) -> {
                   if (control.isMaxCharacterCountEnabled() && innerField.getLength() > newMax.intValue()) {
                       innerField.setText(innerField.getText(0, control.getMaxCharacterCount()));
                   }
               });
        characterCountLabel.getStyleClass()
                           .setAll("character-count-label");
    }

    /**
     * Returns a ChangeListener that controls the visibility of a Label within a container.
     *
     * @param label
     *         the Label to control the visibility of
     *
     * @return a ChangeListener<Boolean> that controls the visibility of the Label
     */
    @NotNull
    private ChangeListener<Boolean> getLabelVisibilityChangeListener(Label label) {
        return (obs, oldVisible, isVisible) -> {
            if (isVisible) {
                getChildren().add(label);
            } else {
                getChildren().remove(label);
            }
            getSkinnable().requestLayout();
        };
    }

    /**
     * Sets up the icons for this component. This method adds a change listener to both the leading icon and trailing icon. When the icon changes, the registered change listener will be notified.
     */
    protected void setupIcons() {
        leadingIcon.addListener(getIconChangeListener());
        trailingIcon.addListener(getIconChangeListener());
    }

    /**
     * Returns a ChangeListener that handles the change of an icon in a node.
     *
     * <p>This listener removes the old icon from the children of the node if the new icon is null.</p>
     * <p>If the new icon is not null, it removes the old icon and adds the new icon to the children of the node.</p>
     * <p>If the old icon is null and the new icon is not null, it adds the new icon to the children of the node.</p>
     * <p>Finally, it requests layout for the skinnable node.</p>
     *
     * @return a ChangeListener that handles the change of an icon
     */
    @NotNull
    protected ChangeListener<Node> getIconChangeListener() {
        return (obs, oldIcon, newIcon) -> {
            if (Objects.isNull(newIcon)) {
                getChildren().remove(oldIcon);
            } else if (Objects.nonNull(oldIcon)) {
                getChildren().remove(oldIcon);
                getChildren().add(newIcon);
            } else {
                newIcon.setManaged(false);
                getChildren().add(newIcon);
            }
            getSkinnable().requestLayout();
        };
    }

    /**
     * Sets up the floating text label for the enhanced text field.
     */
    protected void setupFloatingTextLabel() {
        EnhancedTextField control = getSkinnable();

        floatTextLabelVisibleAndModeInside.addListener((obs) -> {
            if (floatTextLabelVisibleAndModeInside.get()) {
                textFieldAlignmentBase.set(innerField.getAlignment());
                Pos baseline = convertToBaseline(innerField.getAlignment());
                innerField.setAlignment(baseline);
            } else {
                Pos base = Objects.requireNonNullElse(textFieldAlignmentBase.get(), Pos.CENTER_LEFT);
                innerField.setAlignment(base);
            }
        });

        floatingTextLabel.getTransforms()
                         .addAll(scale, translate);

        floatingTextLabel.visibleProperty()
                         .bind(control.isFloatModeDisabledProperty()
                                      .not());

        floatingTextLabel.textProperty()
                         .bind(control.floatingTextProperty());

        floatingTextLabel.backgroundProperty()
                         .bind(Bindings.when(control.isTextModeFilledProperty()
                                                    .or(control.isFloatModeBorderProperty()
                                                               .not()))
                                       .then(TRANSPARENT_BACKGROUND)
                                       .otherwise(control.backgroundProperty()));

        floatingTextLabel.textProperty()
                         .addListener(invalidated -> control.requestLayout());

        floatingTextLabel.visibleProperty()
                         .addListener((obs, oldVisible, isVisible) -> {
                             if (isVisible) {
                                 getChildren().add(floatingTextLabel);
                             } else {
                                 getChildren().remove(floatingTextLabel);
                             }
                             control.requestLayout();
                         });

        floatingTextLabel.managedProperty()
                         .bind(Bindings.createBooleanBinding(() -> false));

        floatingTextLabel.setOnMouseClicked(e -> {
            if (control.isFloatAnimationEnabled() && isScaleXOne()) {
                innerField.requestFocus();
            }
        });

        control.floatModeProperty()
               .addListener((obs, oldMode, newMode) -> {
                   if (oldMode != newMode) {
                       setupAnimationState(true);
                       control.requestLayout();
                   }
               });

        floatingTargetY.addListener(invalidated -> {
            if (control.isAlwaysFloating()) {
                /*
                 * Reset the animation state since the isFloatAnimationEnabled will be false
                 * This allows the floating text label to redraw at its new floating location when the animation
                 * state is set below
                 */
                System.out.println("here2");
                setupAnimationState(true);
            }
            setupAnimations();
            setupAnimationState(control.isFloatAnimationEnabled());
        });

        control.isFloatAnimationEnabledProperty()
               .addListener((obs, oldState, isEnabled) -> {
                   if (!innerField.isFocused()) {
                       setupAnimationState(isEnabled);
                   }
               });

        floatingTextLabel.getStyleClass()
                         .setAll("floating-text-label");
    }

    /**
     * Sets up the animations for the EnhancedTextField control. This method clears any existing animations and sets up the float text animation and reset text animation. If the control's float mode
     * is not disabled, the float text animation and reset text animation will be setup. Additionally, the onFinished event handlers for floatAnimationFinished and resetAnimationFinished are set to
     * complete when the respective animations finish.
     */
    protected void setupAnimations() {
        EnhancedTextField control = getSkinnable();
        animationManager.clearAnimations();
        if (!control.isFloatModeDisabled()) {
            setupFloatTextAnimation();
            setupResetTextAnimation();
            animationManager.setOnFinished(FLOAT_ANIMATION_KEY, e -> floatAnimationFinished.complete(null));
            animationManager.setOnFinished(RESET_FLOAT_ANIMATION_KEY, e -> resetAnimationFinished.complete(null));
        }
    }

    /**
     * Sets up the float text animation. The method creates keyframes for floating animation in both X and Y dimensions and then creates a timeline with these keyframes. The created animation is added
     * to the animation manager.
     */
    protected void setupFloatTextAnimation() {
        KeyFrame keyFrameXFloat = createKeyFrame(scale.xProperty(), scaleFactor, translate.xProperty(), floatingTextLabel.getPadding()
                                                                                                                         .getLeft() * scaleFactor - 1);
        KeyFrame keyFrameYFloat     = createKeyFrame(scale.yProperty(), scaleFactor, translate.yProperty(), floatingTargetY.get());
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
        KeyFrame keyFrameXReset     = createKeyFrame(scale.xProperty(), 1, translate.xProperty(), 1);
        KeyFrame keyFrameYReset     = createKeyFrame(scale.yProperty(), 1, translate.yProperty(), 1);
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
     * Sets up the animation state based on the given isEnabled parameter. If the float animation is currently playing, apply the state on animation finished using the floatAnimationFinished
     * CompletableFuture. If the reset animation is currently playing, apply the state on animation finished using the resetAnimationFinished CompletableFuture. Otherwise, applies the float animation
     * state directly.
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
        EnhancedTextField control = getSkinnable();
        if (control.isFloatModeDisabled()) {
            return 0.0;
        }

        double top            = InsetUtils.getTopInsetWithBorder(control);
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
        return animationManager.containsAnimation(FLOAT_ANIMATION_KEY) && animationManager.isAnimationPlaying(FLOAT_ANIMATION_KEY);
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
     * Applies the animation state based on the given parameters. If the animation is completed, the {@code applyFloatAnimationState} method is called on the specified {@code completableFuture}. Once
     * the animation state is applied, the specified {@code runnable} is executed.
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
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computeMinWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        double leadingWidth  = NodeUtils.getNodePrefWidth(leadingIcon.get());
        double trailingWidth = NodeUtils.getNodePrefWidth(trailingIcon.get());

        return leftInset + leadingWidth + innerField.prefWidth(-1) + trailingWidth + rightInset;
    }

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
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        EnhancedTextField control        = getSkinnable();
        double            leadingHeight  = NodeUtils.getNodePrefHeight(leadingIcon.get());
        double            trailingHeight = NodeUtils.getNodePrefHeight(trailingIcon.get());
        double            iconMax        = topInset + Math.max(leadingHeight, trailingHeight) + bottomInset;

        double height = topInset + innerField.prefHeight(-1) + bottomInset;

        if (control.isFloatModeInside()) {
            height += floatingTextLabel.prefHeight(-1);
        }

        return Math.max(iconMax, height);
    }

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
        layoutInnerTextField(x, y, w, h);
        layoutLeadingIcon(x, y, h);
        layoutTrailingIcon(x, y, w, h);
        layoutSupportingTextLabel(x, h);
        layoutCharacterCountLabel(x, w, h);
        layoutFloatingTextLabel(x, y, h);
    }

    /**
     * Layouts the leading icon within the specified bounds. This method positions the leading icon based on the given x, y coordinates and the height of the layout bounds. It calculates the icon's
     * preferred width and height, then vertically centers it within the layout bounds. If the leading icon is not present (null), no action is taken.
     *
     * @param x
     *         the x-coordinate of the layout bounds.
     * @param y
     *         the y-coordinate of the layout bounds.
     * @param h
     *         the height of the layout bounds.
     */
    private void layoutLeadingIcon(double x, double y, double h) {
        Optional.ofNullable(leadingIcon.get())
                .ifPresent(icon -> {
                    double iconWidth   = icon.prefWidth(-1);
                    double iconHeight  = icon.prefHeight(-1);
                    double verticalPos = y + (h - iconHeight) / 2;
                    icon.resizeRelocate(x, verticalPos, iconWidth, iconHeight);
                });
    }

    /**
     * Layouts the trailing icon within the specified bounds. This method positions the trailing icon based on the given x, y coordinates, width, and height of the layout bounds. It calculates the
     * icon's preferred width and height, then aligns it to the right within the bounds while vertically centering it. If the trailing icon is not present (null), no action is taken.
     *
     * @param x
     *         the x-coordinate of the layout bounds.
     * @param y
     *         the y-coordinate of the layout bounds.
     * @param w
     *         the width of the layout bounds.
     * @param h
     *         the height of the layout bounds.
     */
    private void layoutTrailingIcon(double x, double y, double w, double h) {
        Optional.ofNullable(trailingIcon.get())
                .ifPresent(icon -> {
                    double iconWidth      = icon.prefWidth(-1);
                    double iconHeight     = icon.prefHeight(-1);
                    double verticalCenter = (h - iconHeight) / 2;
                    icon.resizeRelocate(x + w - iconWidth, y + verticalCenter, iconWidth, iconHeight);
                });
    }

    /**
     * Layouts the inner text field within the specified bounds. This method calculates the offset for the text field based on the presence and preferred width of leading and trailing icons. It then
     * positions and resizes the inner text field accordingly. The method ensures that the text field is appropriately placed within the available space, taking into account the icons if present.
     *
     * @param x
     *         the x-coordinate of the layout bounds.
     * @param y
     *         the y-coordinate of the layout bounds.
     * @param w
     *         the width of the layout bounds.
     * @param h
     *         the height of the layout bounds.
     */
    private void layoutInnerTextField(double x, double y, double w, double h) {
        double xOffset = Optional.ofNullable(leadingIcon.get())
                                 .map(icon -> icon.prefWidth(-1) + LEADING_ICON_OFFSET)
                                 .orElse(0.0);
        double wOffset = Optional.ofNullable(trailingIcon.get())
                                 .map(icon -> icon.prefWidth(-1) + TRAILING_ICON_OFFSET)
                                 .orElse(0.0);

        innerField.resizeRelocate(x + xOffset, y, w - xOffset - wOffset, h);
    }

    /**
     * Layouts the supporting text label within the specified bounds. If supporting text is enabled, this method calculates the preferred width and height of the label and positions it below the
     * specified layout bounds. The method performs no action if supporting text is not enabled.
     *
     * @param x
     *         the x-coordinate of the layout bounds.
     * @param h
     *         the height of the layout bounds.
     */
    private void layoutSupportingTextLabel(double x, double h) {
        EnhancedTextField control = getSkinnable();
        if (control.isSupportingTextEnabled()) {
            double supportingTextLabelWidth  = supportingTextLabel.prefWidth(-1);
            double supportingTextLabelHeight = supportingTextLabel.prefHeight(-1);
            double supportingTextLabelY      = h + InsetUtils.getBottomBorderInset(control) + supportingTextLabelHeight;
            supportingTextLabel.resizeRelocate(x, supportingTextLabelY, supportingTextLabelWidth, supportingTextLabelHeight);
        }
    }

    /**
     * Layouts the character count label within the specified bounds. This method is called when the maximum character count feature is enabled. It calculates the preferred width and height of the
     * character count label and positions it based on the provided layout bounds. The label is placed to the right, with its position adjusted based on whether it's above or below the text field. The
     * method performs no action if the maximum character count is not enabled.
     *
     * @param x
     *         the x-coordinate of the layout bounds.
     * @param w
     *         the width of the layout bounds.
     * @param h
     *         the height of the layout bounds.
     */
    private void layoutCharacterCountLabel(double x, double w, double h) {
        EnhancedTextField control = getSkinnable();
        if (control.isMaxCharacterCountEnabled()) {
            double characterCountLabelWidth  = characterCountLabel.prefWidth(-1);
            double characterCountLabelHeight = characterCountLabel.prefHeight(-1);
            double characterCountLabelX      = x + w - characterCountLabelWidth + 3;

            double characterCountLabelY;
            if (control.isMaxCharacterCountPosBelow()) {
                characterCountLabelY = h + InsetUtils.getBottomBorderInset(control) + characterCountLabelHeight;
            } else {
                characterCountLabelY = 0 - InsetUtils.getTopBorderInset(control) - characterCountLabelHeight;
            }
            characterCountLabel.resizeRelocate(characterCountLabelX, characterCountLabelY, characterCountLabelWidth, characterCountLabelHeight);
        }
    }

    /**
     * Layouts the floating text label within the specified bounds. This method is applicable when float mode is not disabled. It calculates the offset based on the presence of a leading icon and
     * positions the floating text label accordingly. The label is vertically centered within the layout bounds. The method also updates the target Y-position for the floating label's animation. If
     * float mode is disabled, the method performs no action.
     *
     * @param x
     *         the x-coordinate of the layout bounds.
     * @param y
     *         the y-coordinate of the layout bounds.
     * @param h
     *         the height of the layout bounds.
     */
    private void layoutFloatingTextLabel(double x, double y, double h) {
        EnhancedTextField control = getSkinnable();
        if (!control.isFloatModeDisabled()) {
            double leadingWidth = Optional.ofNullable(leadingIcon.get())
                                          .map(icon -> icon.prefWidth(-1) + LEADING_ICON_OFFSET)
                                          .orElse(0.0);
            double floatingTextLabelWidth  = floatingTextLabel.prefWidth(-1);
            double floatingTextLabelHeight = floatingTextLabel.prefHeight(-1);
            double floatingTextLabelX      = x + leadingWidth - InsetUtils.getLeftInset(floatingTextLabel);
            double floatingTextLabelY      = h / 2 + y - floatingTextLabelHeight / 2;

            floatingTextLabel.resizeRelocate(floatingTextLabelX, floatingTextLabelY, floatingTextLabelWidth, floatingTextLabelHeight);

            floatingTargetY.set(getFloatingLabelTargetY(floatingTextLabelY));
        }
    }
}
