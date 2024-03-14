/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/MaterialDesignUI).
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
package io.github.colindj1120.enhancedfx.controls.control.base;

import io.github.colindj1120.enhancedfx.base.enums.State;
import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleDirection;
import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleShape;
import io.github.colindj1120.enhancedfx.graphics.effects.ripple.EFXRippleEffect;

import javafx.animation.Interpolator;
import javafx.css.StyleableDoubleProperty;
import javafx.css.StyleableObjectProperty;
import javafx.scene.effect.BlurType;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Defines the contract for UI components that support ripple effects, providing methods to get and set the properties of a {@link EFXRippleEffect}. This interface ensures that implementing classes can
 * manage ripple effect characteristics such as state, shape, color, and duration, enabling dynamic visual feedback in response to user interactions.
 *
 * <p>
 * Implementing classes are expected to provide a concrete implementation of {@link #getRippleEffect()}, which serves as the foundation for the default methods defined within this interface. These
 * methods delegate operations to the {@link EFXRippleEffect} instance, facilitating a standardized approach to configuring ripple effects across different UI components.
 * </p>
 *
 * <p>
 * Usage of this interface allows for a flexible and extensible way to add material design-like ripple effects to JavaFX applications, enhancing the user experience with visual cues that indicate
 * interactive elements.
 * </p>
 */
public interface RippleControls {
    EFXRippleEffect getRippleEffect();

    /**
     * Retrieves the current state of the ripple effect.
     *
     * @return The current state of the ripple effect.
     */
    default State getRippleState() {
        return getRippleEffect().getRippleState();
    }

    /**
     * Returns the ripple state property of the EFXRippleEffect.
     *
     * @return The ripple state property.
     */
    default StyleableObjectProperty<State> rippleStateProperty() {
        return getRippleEffect().rippleStateProperty();
    }

    /**
     * Sets the state of the ripple effect.
     *
     * @param rippleState
     *         The state of the ripple effect. It can be either State.ENABLED or State.DISABLED.
     */
    default void setRippleState(State rippleState) {
        getRippleEffect().setRippleState(rippleState);
    }

    /**
     * Checks if the ripple effect is enabled.
     *
     * @return {@code true} if the ripple effect is enabled, {@code false} otherwise.
     */
    default boolean isRippleEnabled() {
        return getRippleEffect().isRippleEnabled();
    }

    /**
     * Retrieves the ripple shape.
     *
     * @return The ripple shape.
     */
    default EFXRippleShape getRippleShape() {
        return getRippleEffect().getRippleShape();
    }

    /**
     * Retrieves the value of the rippleShape property.
     *
     * @return the rippleShape property value
     */
    default StyleableObjectProperty<EFXRippleShape> rippleShapeProperty() {
        return getRippleEffect().rippleShapeProperty();
    }

    /**
     * Sets the ripple shape for the view.
     *
     * @param efxRippleShape
     *         the {@link EFXRippleShape} to set
     */
    default void setRippleShape(EFXRippleShape efxRippleShape) {
        getRippleEffect().setRippleShape(efxRippleShape);
    }

    /**
     * Retrieves the ripple clip shape.
     *
     * @return The ripple clip shape.
     */
    default EFXRippleShape getRippleClipShape() {
        return getRippleEffect().getRippleClipShape();
    }

    /**
     * Gets the styleable object property for the ripple clip shape.
     *
     * @return The styleable object property for the ripple clip shape.
     */
    default StyleableObjectProperty<EFXRippleShape> rippleClipShapeProperty() {
        return getRippleEffect().rippleShapeProperty();
    }

    /**
     * Set the shape of the ripple clip.
     *
     * @param rippleClipShape
     *         The shape of the ripple clip.
     */
    default void setRippleClipShape(EFXRippleShape rippleClipShape) {
        getRippleEffect().setRippleClipShape(rippleClipShape);
    }

    /**
     * Returns the ripple color used in the ripple effect.
     *
     * @return The ripple color.
     */
    default Color getRippleColor() {
        return getRippleEffect().getRippleColor();
    }

    /**
     * Returns the ripple color property of the EFXRippleEffect.
     *
     * @return The ripple color property.
     */
    default StyleableObjectProperty<Color> rippleColorProperty() {
        return getRippleEffect().rippleColorProperty();
    }

    /**
     * Sets the color of the ripple effect.
     *
     * @param rippleColor
     *         The color to be set for the ripple effect.
     */
    default void setRippleColor(Color rippleColor) {
        getRippleEffect().setRippleColor(rippleColor);
    }

    /**
     * Retrieves the duration of the ripple effect animation.
     *
     * @return The duration of the ripple effect animation.
     */
    default Duration getRippleDuration() {
        return getRippleEffect().getRippleDuration();
    }

    /**
     * Returns the StyleableObjectProperty representing the ripple duration of the EFXRippleEffect.
     *
     * @return The StyleableObjectProperty representing the ripple duration of the EFXRippleEffect.
     */
    default StyleableObjectProperty<Duration> rippleDurationProperty() {
        return getRippleEffect().rippleDurationProperty();
    }

    /**
     * Sets the duration of the ripple effect animation.
     *
     * @param rippleDuration
     *         The duration of the ripple effect animation.
     */
    default void setRippleDuration(Duration rippleDuration) {
        getRippleEffect().setRippleDuration(rippleDuration);
    }

    /**
     * Retrieves the RippleInterpolator property value.
     *
     * @return The RippleInterpolator property value.
     */
    default Interpolator getRippleInterpolator() {
        return getRippleEffect().getRippleInterpolator();
    }

    /**
     * Returns the styleable property for the ripple interpolator. The ripple interpolator defines the timing of the ripple effect animation.
     *
     * @return The styleable property for the ripple interpolator.
     */
    default StyleableObjectProperty<Interpolator> rippleInterpolatorProperty() {
        return getRippleEffect().rippleInterpolatorProperty();
    }

    /**
     * Sets the ripple interpolator for the EFXRippleEffect.
     *
     * @param rippleInterpolator
     *         The interpolator to be set for the ripple effect.
     */
    default void setRippleInterpolator(Interpolator rippleInterpolator) {
        getRippleEffect().setRippleInterpolator(rippleInterpolator);
    }

    /**
     * Retrieves the ripple fill state.
     *
     * @return the current ripple fill state
     */
    default State getRippleFillState() {
        return getRippleEffect().getRippleFillState();
    }

    /**
     * Retrieves the ripple fill state property which represents the fill state of the ripple effect.
     *
     * @return the ripple fill state property
     */
    default StyleableObjectProperty<State> rippleFillStateProperty() {
        return getRippleEffect().rippleFillStateProperty();
    }

    /**
     * Set the fill state of the ripple effect.
     *
     * @param rippleFillState
     *         The fill state of the ripple effect.
     */
    default void setRippleFillState(State rippleFillState) {
        getRippleEffect().setRippleFillState(rippleFillState);
    }

    /**
     * Determines whether the ripple fill state is enabled.
     *
     * @return {@code true} if the ripple fill state is enabled , {@code false} otherwise.
     */
    default boolean isRippleFillStateEnabled() {
        return getRippleEffect().isRippleFillStateEnabled();
    }

    /**
     * Retrieves the current ripple radius used in the ripple effect.
     *
     * @return The current ripple radius.
     */
    default double getRippleRadius() {
        return getRippleEffect().getRippleRadius();
    }

    /**
     * Retrieves the ripple radius property of the EFXRippleEffect. The ripple radius represents the radius of the ripple circle in pixels.
     *
     * @return The ripple radius property.
     */
    default StyleableDoubleProperty rippleRadiusProperty() {
        return getRippleEffect().rippleRadiusProperty();
    }

    /**
     * Sets the radius of the ripple effect.
     *
     * @param rippleRadius
     *         The radius of the ripple effect to be set.
     */
    default void setRippleRadius(double rippleRadius) {
        getRippleEffect().setRippleRadius(rippleRadius);
    }

    /**
     * Returns the width of the ripple stroke.
     *
     * @return the width of the ripple stroke
     */
    default double getRippleStrokeWidth() {
        return getRippleEffect().getRippleStrokeWidth();
    }

    /**
     * Gets the ripple stroke width property of this object.
     *
     * @return the ripple stroke width property
     */
    default StyleableDoubleProperty rippleStrokeWidthProperty() {
        return getRippleEffect().rippleStrokeWidthProperty();
    }

    /**
     * Set the width of the stroke for the ripple effect.
     *
     * @param rippleStrokeWidth
     *         the desired stroke width for the ripple effect
     */
    default void setRippleStrokeWidth(double rippleStrokeWidth) {
        getRippleEffect().setRippleStrokeWidth(rippleStrokeWidth);
    }

    /**
     * Returns the ripple stroke color.
     *
     * @return the ripple stroke color
     */
    default Color getRippleStrokeColor() {
        return getRippleEffect().getRippleStrokeColor();
    }

    /**
     * Retrieves the ripple stroke color property of the object.
     *
     * @return the StyleableObjectProperty representing the ripple stroke color
     */
    default StyleableObjectProperty<Color> rippleStrokeColorProperty() {
        return getRippleEffect().rippleStrokeColorProperty();
    }

    /**
     * Sets the color of the stroke for the ripple effect.
     *
     * @param rippleStrokeColor
     *         the color of the ripple stroke
     */
    default void setRippleStrokeColor(Color rippleStrokeColor) {
        getRippleEffect().setRippleStrokeColor(rippleStrokeColor);
    }

    /**
     * Determines if the ripple effect is set to move outward from the center of the UI element.
     *
     * @return {@code true} if the ripple effect is set to move outward, {@code false} otherwise.
     */
    default boolean isRippleDirectionOut() {
        return getRippleDirection() == EFXRippleDirection.OUT;
    }

    /**
     * Checks if the current ripple direction is inward.
     *
     * @return {@code true} if the ripple direction is inward, {@code false} otherwise.
     */
    default boolean isRippleDirectionIn() {
        return getRippleDirection() == EFXRippleDirection.IN;
    }

    /**
     * Retrieves the current direction of the ripple effect.
     *
     * @return The current direction of the ripple effect. It can be either {@code EFXRippleDirection.IN} or {@code EFXRippleDirection.OUT}.
     *
     * @see EFXRippleDirection
     */
    default EFXRippleDirection getRippleDirection() {
        return getRippleEffect().getRippleDirection();
    }

    /**
     * Returns the ripple direction property of the EFXRippleEffect.
     *
     * <p>The ripple direction property specifies the direction of the ripple effect
     * in animations or UI interactions. It can be used to control whether the ripple effect should move inward towards the center of the UI element or outward from the center. The direction can
     * enhance the visual cue provided to users, indicating the type of action or transition occurring.</p>
     *
     * @return The ripple direction property.
     *
     * @see EFXRippleDirection
     * @see EFXRippleEffect
     */
    default StyleableObjectProperty<EFXRippleDirection> rippleDirectionProperty() {
        return getRippleEffect().rippleDirectionProperty();
    }

    /**
     * Sets the direction of the ripple effect.
     *
     * @param efxRippleDirection
     *         The direction of the ripple effect. Valid values are EFXRippleDirection.IN and EFXRippleDirection.OUT.
     */
    default void setRippleDirection(EFXRippleDirection efxRippleDirection) {
        getRippleEffect().setRippleDirection(efxRippleDirection);
    }

    /**
     * Retrieves the target node to which the ripple effect will be applied.
     *
     * @return The target node.
     */
    default Region getTargetNode() {
        return getRippleEffect().getTargetNode();
    }

    /**
     * Retrieves the current status of the ripple fade.
     *
     * @return The current status of the ripple fade.
     */
    default State getRippleFade() {
        return getRippleEffect().getRippleFadeState();
    }

    /**
     * Gets the ripple fade property.
     *
     * @return the ripple fade property
     */
    default StyleableObjectProperty<State> rippleFadeProperty() {
        return getRippleEffect().rippleFadeStateProperty();
    }

    /**
     * Sets the ripple fade status.
     *
     * @param rippleFade
     *         the ripple fade status to set
     */
    default void setRippleFade(State rippleFade) {
        getRippleEffect().setRippleFadeState(rippleFade);
    }

    /**
     * Returns whether the ripple fade effect is enabled.
     *
     * @return {@code true} if the ripple fade effect is enabled, otherwise {@code false}
     */
    default boolean isRippleFadeEnabled() {
        return getRippleFade() == State.ENABLED;
    }

    /**
     * Retrieves the type of blur applied to the drop shadow effect.
     *
     * @return the BlurType of the drop shadow effect
     */
    default BlurType getDropShadowBlurType() {
        return getRippleEffect().getDropShadowBlurType();
    }

    /**
     * Returns the property representing the blur type of the drop shadow effect.
     *
     * @return the property representing the blur type of the drop shadow effect
     */
    default StyleableObjectProperty<BlurType> dropShadowBlurTypeProperty() {
        return getRippleEffect().dropShadowBlurTypeProperty();
    }

    /**
     * Sets the blur type for the drop shadow effect.
     *
     * @param dropShadowBlurType
     *         the blur type to be set
     */
    default void setDropShadowBlurType(BlurType dropShadowBlurType) {
        getRippleEffect().setDropShadowBlurType(dropShadowBlurType);
    }

    /**
     * Retrieves the drop shadow color.
     *
     * @return The drop shadow color.
     */
    default Color getDropShadowColor() {
        return getRippleEffect().getDropShadowColor();
    }

    /**
     * Returns the drop shadow color property of this object.
     *
     * @return the drop shadow color property
     */
    default StyleableObjectProperty<Color> dropShadowColorProperty() {
        return getRippleEffect().dropShadowColorProperty();
    }

    /**
     * Sets the drop shadow color for this object.
     *
     * @param dropShadowColor
     *         the color to set as the drop shadow color
     */
    default void setDropShadowColor(Color dropShadowColor) {
        getRippleEffect().setDropShadowColor(dropShadowColor);
    }

    /**
     * Retrieves the drop shadow radius value.
     *
     * @return The drop shadow radius.
     */
    default double getDropShadowRadius() {
        return getRippleEffect().getDropShadowRadius();
    }

    /**
     * Retrieves the dropShadowRadius property.
     *
     * @return The dropShadowRadius property
     */
    default StyleableDoubleProperty dropShadowRadiusProperty() {
        return getRippleEffect().dropShadowRadiusProperty();
    }

    /**
     * Sets the drop shadow radius.
     *
     * @param dropShadowRadius
     *         the radius of the drop shadow
     */
    default void setDropShadowRadius(double dropShadowRadius) {
        getRippleEffect().setDropShadowRadius(dropShadowRadius);
    }

    /**
     * Retrieves the spread radius of the drop shadow effect applied to this object.
     *
     * @return The spread radius as a double value.
     */
    default double getDropShadowSpread() {
        return getRippleEffect().getDropShadowSpread();
    }

    /**
     * Returns the StyleableDoubleProperty for the 'dropShadowSpread' property.
     *
     * @return the StyleableDoubleProperty for the 'dropShadowSpread' property
     */
    default StyleableDoubleProperty dropShadowSpreadProperty() {
        return getRippleEffect().dropShadowSpreadProperty();
    }

    /**
     * Sets the spread value for the drop shadow effect.
     *
     * @param dropShadowSpread
     *         the spread value of the drop shadow effect
     */
    default void setDropShadowSpread(double dropShadowSpread) {
        getRippleEffect().setDropShadowSpread(dropShadowSpread);
    }

    /**
     * Retrieves the value of the drop shadow offset along the x-axis.
     *
     * @return The value of the drop shadow offset along the x-axis.
     */
    default double getDropShadowOffsetX() {
        return getRippleEffect().getDropShadowOffsetX();
    }

    /**
     * Returns the drop shadow offset X property of the node.
     *
     * @return the drop shadow offset X property.
     */
    default StyleableDoubleProperty dropShadowOffsetXProperty() {
        return getRippleEffect().dropShadowOffsetXProperty();
    }

    /**
     * Sets the offset of the drop shadow effect along the x-axis.
     *
     * @param dropShadowOffsetX
     *         the offset of the drop shadow effect along the x-axis
     */
    default void setDropShadowOffsetX(double dropShadowOffsetX) {
        getRippleEffect().setDropShadowOffsetX(dropShadowOffsetX);
    }

    /**
     * Returns the offset in the Y direction for the drop shadow effect.
     *
     * @return the offset in the Y direction for the drop shadow effect
     */
    default double getDropShadowOffsetY() {
        return getRippleEffect().getDropShadowOffsetY();
    }

    /**
     * Returns the JavaFX property representing the offset of the drop shadow effect in the y-axis direction.
     *
     * @return The JavaFX property representing the drop shadow offset in the y-axis.
     */
    default StyleableDoubleProperty dropShadowOffsetYProperty() {
        return getRippleEffect().dropShadowOffsetYProperty();
    }

    /**
     * Sets the offset of the drop shadow effect along the Y-axis.
     *
     * @param dropShadowOffsetY
     *         the offset value along the Y-axis
     */
    default void setDropShadowOffsetY(double dropShadowOffsetY) {
        getRippleEffect().setDropShadowOffsetY(dropShadowOffsetY);
    }

    /**
     * Retrieves the current drop shadow state.
     *
     * @return The drop shadow state.
     */
    default State getDropShadowState() {
        return getRippleEffect().getDropShadowState();
    }

    /**
     * Retrieves the drop shadow state property of the object.
     *
     * @return the drop shadow state property
     */
    default StyleableObjectProperty<State> dropShadowStateProperty() {
        return getRippleEffect().dropShadowStateProperty();
    }

    /**
     * Sets the drop shadow state for the object.
     *
     * @param dropShadowState
     *         the new drop shadow state to be set
     */
    default void setDropShadowState(State dropShadowState) {
        getRippleEffect().setDropShadowState(dropShadowState);
    }

    /**
     * Determines if the drop shadow is enabled.
     *
     * @return {@code true} if the drop shadow is enabled, {@code false} otherwise.
     */
    default boolean isDropShadowEnabled() {
        return getRippleEffect().isDropShadowEnabled();
    }
}
