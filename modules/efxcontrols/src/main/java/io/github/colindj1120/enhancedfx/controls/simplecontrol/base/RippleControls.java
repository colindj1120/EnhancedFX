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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.base;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableDoubleProperty;
import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.base.enums.EFXState;
import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleDirection;
import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleShape;
import io.github.colindj1120.enhancedfx.graphics.effects.ripple.EFXRippleEffect;
import javafx.animation.Interpolator;
import javafx.scene.effect.BlurType;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Defines a set of control functionalities for managing ripple effects in EnhancedFX components.
 *
 * <p>Implementations of this interface provide access and control over the properties and behavior of an associated {@link EFXRippleEffect}, allowing for detailed customization of ripple animations and
 * styles.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Access and modify the ripple effect's properties such as color, shape, and duration.</li>
 *     <li>Control the state and behavior of the ripple effect, including enabling/disabling it or setting its direction and fill state.</li>
 *     <li>Customize the ripple effect's appearance further with properties like clip shape, stroke width, color, and drop shadow characteristics.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * public class MyCustomControl extends Region implements RippleControls {
 *     private EFXRippleEffect rippleEffect = new EFXRippleEffect(this);
 *
 *     public MyCustomControl() {
 *         // Initialize and customize ripple effect
 *         setRippleColor(Color.BLUE);
 *         setRippleShape(EFXRippleShape.CIRCLE);
 *         setRippleDuration(Duration.millis(300));
 *     }
 *
 *     @Override
 *     public EFXRippleEffect getRippleEffect() {
 *         return rippleEffect;
 *     }
 *
 *     // Additional methods and logic specific to the control
 * }
 * }</pre>
 *
 * <p>In this example, {@code MyCustomControl} implements {@code RippleControls} to provide a customized ripple effect. The control initializes the ripple effect with specific properties like color, shape,
 * and duration.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXRippleEffect
 * @see EFXStyleableObjectProperty
 * @see EFXStyleableDoubleProperty
 */
public interface RippleControls {
    /**
     * Retrieves the {@link EFXRippleEffect} associated with the implementing control.
     *
     * <p>The {@code EFXRippleEffect} represents a visual ripple animation effect, typically triggered by user interactions such as mouse clicks or touches, providing a tactile feedback mechanism within the
     * user interface.</p>
     *
     * <p>Implementors of the {@code RippleControls} interface must provide access to their specific instance of {@code EFXRippleEffect}, allowing external manipulation and customization of the ripple effect's
     * properties and behaviors. This method enables direct access to the ripple effect, facilitating detailed configurations such as color, duration, interpolator, and various states that define how the ripple
     * animation behaves and appears.</p>
     *
     * <h2>Expected Behavior:</h2>
     * <ul>
     *     <li>The returned {@code EFXRippleEffect} should not be {@code null}. Implementors should ensure an instance of {@code EFXRippleEffect} is created and associated with the control during its
     *     initialization phase.</li>
     *     <li>Subsequent calls to {@code getRippleEffect()} should return the same {@code EFXRippleEffect} instance unless explicitly reconfigured or replaced within the control. </li>
     * </ul>
     *
     * @return The {@link EFXRippleEffect} instance associated with this control.
     */
    EFXRippleEffect getRippleEffect();

    /**
     * Retrieves the current state of the ripple effect.
     *
     * @return The current state of the ripple effect as a EFXState object.
     *
     * @see EFXState
     */
    default EFXState getRippleState() {
        return getRippleEffect().getRippleState();
    }

    /**
     * Retrieves the rippleStateProperty of the EFXRippleEffect associated with this RippleControls instance.
     *
     * @return The EFXStyleableObjectProperty representing the rippleStateProperty.
     *
     * @see EFXState
     */
    default EFXStyleableObjectProperty<EFXState> rippleStateProperty() {
        return getRippleEffect().rippleStateProperty();
    }

    /**
     * Sets the ripple state of the RippleEffect.
     *
     * @param rippleEFXState
     *         the desired state of the ripple effect
     */
    default void setRippleState(EFXState rippleEFXState) {
        getRippleEffect().setRippleState(rippleEFXState);
    }

    /**
     * Returns whether the ripple effect is enabled or not.
     *
     * @return {@code true} if the ripple effect is enabled, {@code false} otherwise
     */
    default boolean isRippleEnabled() {
        return getRippleEffect().isRippleEnabled();
    }

    /**
     * Retrieves the ripple shape used by the ripple effect.
     *
     * @return The ripple shape used by the ripple effect.
     */
    default EFXRippleShape getRippleShape() {
        return getRippleEffect().getRippleShape();
    }

    /**
     * Returns the ripple shape property of the RippleControls class.
     *
     * @return The EFXStyleableObjectProperty representing the ripple shape property.
     *
     * @see EFXRippleShape
     * @see EFXRippleEffect
     */
    default EFXStyleableObjectProperty<EFXRippleShape> rippleShapeProperty() {
        return getRippleEffect().rippleShapeProperty();
    }

    /**
     * Sets the ripple shape for the ripple effect.
     *
     * @param efxRippleShape
     *         the desired shape for the ripple effect
     *
     * @see EFXRippleShape
     */
    default void setRippleShape(EFXRippleShape efxRippleShape) {
        getRippleEffect().setRippleShape(efxRippleShape);
    }

    /**
     * Retrieves the ripple clip shape associated with the ripple effect.
     *
     * @return The ripple clip shape associated with the ripple effect.
     *
     * @see EFXRippleShape
     */
    default EFXRippleShape getRippleClipShape() {
        return getRippleEffect().getRippleClipShape();
    }

    /**
     * Returns the ripple clip shape property of the RippleControls.
     *
     * @return The ripple clip shape property of type EFXStyleableObjectProperty<EFXRippleShape>.
     *
     * @see RippleControls
     * @see EFXRippleShape
     */
    default EFXStyleableObjectProperty<EFXRippleShape> rippleClipShapeProperty() {
        return getRippleEffect().rippleShapeProperty();
    }

    /**
     * Sets the ripple clip shape for the ripple effect.
     *
     * @param rippleClipShape
     *         the new ripple clip shape to be set
     *
     * @see EFXRippleShape
     */
    default void setRippleClipShape(EFXRippleShape rippleClipShape) {
        getRippleEffect().setRippleClipShape(rippleClipShape);
    }

    /**
     * Retrieves the ripple color of the ripple effect.
     *
     * @return The ripple color of the ripple effect.
     *
     * @see Color
     */
    default Color getRippleColor() {
        return getRippleEffect().getRippleColor();
    }

    /**
     * Returns the EFXStyleableObjectProperty for the ripple color of the RippleControls object's RippleEffect.
     *
     * @return the EFXStyleableObjectProperty for the ripple color of the RippleEffect
     *
     * @see Color
     */
    default EFXStyleableObjectProperty<Color> rippleColorProperty() {
        return getRippleEffect().rippleColorProperty();
    }

    /**
     * Sets the ripple color of the ripple effect.
     *
     * @param rippleColor
     *         the color of the ripple effect
     *
     * @see Color
     */
    default void setRippleColor(Color rippleColor) {
        getRippleEffect().setRippleColor(rippleColor);
    }

    /**
     * Retrieves the duration of the ripple effect.
     *
     * @return The duration of the ripple effect.
     *
     * @see Duration
     */
    default Duration getRippleDuration() {
        return getRippleEffect().getRippleDuration();
    }

    /**
     * Returns the ripple duration property of the EFXStyleableObjectProperty object associated with the ripple effect. The ripple duration property determines how long the ripple effect lasts.
     *
     * @return the ripple duration property
     *
     * @see Duration
     */
    default EFXStyleableObjectProperty<Duration> rippleDurationProperty() {
        return getRippleEffect().rippleDurationProperty();
    }

    /**
     * Sets the duration of the ripple effect.
     *
     * @param rippleDuration
     *         the duration of the ripple effect
     *
     * @see Duration
     */
    default void setRippleDuration(Duration rippleDuration) {
        getRippleEffect().setRippleDuration(rippleDuration);
    }

    /**
     * Returns the default Interpolator for the ripple effect.
     *
     * @return the default Interpolator for the ripple effect
     *
     * @see Interpolator
     */
    default Interpolator getRippleInterpolator() {
        return getRippleEffect().getRippleInterpolator();
    }

    /**
     * Returns the EFXStyleableObjectProperty representing the ripple interpolator of the RippleEffect.
     *
     * @return The EFXStyleableObjectProperty representing the ripple interpolator of the RippleEffect.
     *
     * @see Interpolator
     */
    default EFXStyleableObjectProperty<Interpolator> rippleInterpolatorProperty() {
        return getRippleEffect().rippleInterpolatorProperty();
    }

    /**
     * Sets the Interpolator for the ripple effect.
     *
     * @param rippleInterpolator
     *         the Interpolator to be set for the ripple effect
     *
     * @see Interpolator
     */
    default void setRippleInterpolator(Interpolator rippleInterpolator) {
        getRippleEffect().setRippleInterpolator(rippleInterpolator);
    }

    /**
     * Retrieves the current state of the ripple fill effect.
     *
     * @return The current state of the ripple fill effect as a EFXState object.
     *
     * @see EFXState
     */
    default EFXState getRippleFillState() {
        return getRippleEffect().getRippleFillState();
    }

    /**
     * Retrieves the ripple fill state property of the RippleControls object's RippleEffect. The ripple fill state property represents the fill state of the ripple effect, which determines whether the ripple is
     * filled or not.
     *
     * @return The EFXStyleableObjectProperty representing the ripple fill state property.
     *
     * @see EFXState
     * @see EFXStyleableObjectProperty
     */
    default EFXStyleableObjectProperty<EFXState> rippleFillStateProperty() {
        return getRippleEffect().rippleFillStateProperty();
    }

    /**
     * Sets the fill state of the ripple effect.
     *
     * @param rippleFillEFXState
     *         The desired fill state of the ripple effect.
     *
     * @see EFXState
     */
    default void setRippleFillState(EFXState rippleFillEFXState) {
        getRippleEffect().setRippleFillState(rippleFillEFXState);
    }

    /**
     * Returns whether the ripple fill state is enabled for the current ripple effect.
     *
     * @return {@code true} if the ripple fill state is enabled, otherwise {@code false}
     */
    default boolean isRippleFillStateEnabled() {
        return getRippleEffect().isRippleFillStateEnabled();
    }

    /**
     * Returns the radius of the ripple effect.
     *
     * @return the radius of the ripple effect
     */
    default double getRippleRadius() {
        return getRippleEffect().getRippleRadius();
    }

    /**
     * Returns the ripple radius property of the RippleControls object's RippleEffect.
     *
     * @return The EFXStyleableDoubleProperty representing the ripple radius property.
     */
    default EFXStyleableDoubleProperty rippleRadiusProperty() {
        return getRippleEffect().rippleRadiusProperty();
    }

    /**
     * Sets the ripple radius for the effect.
     *
     * @param rippleRadius
     *         the desired ripple radius
     */
    default void setRippleRadius(double rippleRadius) {
        getRippleEffect().setRippleRadius(rippleRadius);
    }

    /**
     * Retrieves the ripple stroke width.
     *
     * @return The ripple stroke width.
     */
    default double getRippleStrokeWidth() {
        return getRippleEffect().getRippleStrokeWidth();
    }

    /**
     * Retrieves the EFXStyleableDoubleProperty for the ripple stroke width of the RippleEffect associated with this RippleControls instance.
     *
     * @return The EFXStyleableDoubleProperty representing the ripple stroke width of the RippleEffect.
     */
    default EFXStyleableDoubleProperty rippleStrokeWidthProperty() {
        return getRippleEffect().rippleStrokeWidthProperty();
    }

    /**
     * Sets the width of the ripple effect stroke.
     *
     * @param rippleStrokeWidth
     *         the width of the ripple effect stroke to be set
     */
    default void setRippleStrokeWidth(double rippleStrokeWidth) {
        getRippleEffect().setRippleStrokeWidth(rippleStrokeWidth);
    }

    /**
     * Retrieve the color used for the ripple stroke.
     *
     * @return The color used for the ripple stroke.
     */
    default Color getRippleStrokeColor() {
        return getRippleEffect().getRippleStrokeColor();
    }

    /**
     * Returns the EFXStyleableObjectProperty representing the ripple stroke color of the RippleControls object's RippleEffect.
     *
     * @return The EFXStyleableObjectProperty representing the ripple stroke color of the RippleEffect.
     *
     * @see Color
     */
    default EFXStyleableObjectProperty<Color> rippleStrokeColorProperty() {
        return getRippleEffect().rippleStrokeColorProperty();
    }

    /**
     * Sets the color for the ripple stroke.
     *
     * @param rippleStrokeColor
     *         The new color for the ripple stroke
     */
    default void setRippleStrokeColor(Color rippleStrokeColor) {
        getRippleEffect().setRippleStrokeColor(rippleStrokeColor);
    }

    /**
     * Checks whether the ripple direction is set to OUT.
     *
     * @return {@code true} if the ripple direction is OUT, {@code false} otherwise
     *
     * @see EFXRippleDirection
     */
    default boolean isRippleDirectionOut() {
        return getRippleDirection() == EFXRippleDirection.OUT;
    }

    /**
     * Determines if the ripple direction is set to "IN".
     *
     * @return {@code true} if the ripple direction is "IN", {@code false} otherwise.
     *
     * @see EFXRippleDirection
     */
    default boolean isRippleDirectionIn() {
        return getRippleDirection() == EFXRippleDirection.IN;
    }

    /**
     * Retrieves the ripple direction of the current ripple effect.
     *
     * @return The ripple direction of the current ripple effect.
     *
     * @see EFXRippleDirection
     */
    default EFXRippleDirection getRippleDirection() {
        return getRippleEffect().getRippleDirection();
    }

    /**
     * Returns the rippleDirection property of the EFXRippleEffect associated with this object.
     *
     * @return the rippleDirection property of the EFXRippleEffect
     *
     * @see EFXRippleDirection
     */
    default EFXStyleableObjectProperty<EFXRippleDirection> rippleDirectionProperty() {
        return getRippleEffect().rippleDirectionProperty();
    }

    /**
     * Sets the ripple direction for the ripple effect.
     *
     * @param efxRippleDirection
     *         the direction of the ripple effect
     *
     * @see EFXRippleDirection
     */
    default void setRippleDirection(EFXRippleDirection efxRippleDirection) {
        getRippleEffect().setRippleDirection(efxRippleDirection);
    }

    /**
     * Retrieves the target node associated with the ripple effect.
     *
     * @return the target node
     */
    default Region getTargetNode() {
        return getRippleEffect().getTargetNode();
    }

    /**
     * Retrieves the state of the ripple fade effect.
     *
     * @return the current state of the ripple fade effect
     *
     * @see EFXState
     */
    default EFXState getRippleFade() {
        return getRippleEffect().getRippleFadeState();
    }

    /**
     * Returns the EFXStyleableObjectProperty representing the ripple fade state of the ripple effect.
     *
     * @return the EFXStyleableObjectProperty representing the ripple fade state of the ripple effect
     *
     * @see EFXState
     */
    default EFXStyleableObjectProperty<EFXState> rippleFadeProperty() {
        return getRippleEffect().rippleFadeStateProperty();
    }

    /**
     * Sets the state of the ripple fade effect.
     *
     * @param rippleFade
     *         the state of the ripple fade effect
     *
     * @see EFXState
     */
    default void setRippleFade(EFXState rippleFade) {
        getRippleEffect().setRippleFadeState(rippleFade);
    }

    /**
     * Returns whether the ripple fade effect is enabled.
     *
     * @return {@code true} if the ripple fade effect is enabled, {@code false} otherwise.
     *
     * @see EFXState
     */
    default boolean isRippleFadeEnabled() {
        return getRippleFade() == EFXState.ENABLED;
    }

    /**
     * Returns the blur type used for the drop shadow effect in the ripple effect.
     *
     * @return the BlurType used for the drop shadow effect
     *
     * @see BlurType
     */
    default BlurType getDropShadowBlurType() {
        return getRippleEffect().getDropShadowBlurType();
    }

    /**
     * Returns the drop shadow blur type property of this object. The drop shadow blur type property represents the type of blur used on the drop shadow effect.
     *
     * @return the drop shadow blur type property of this object
     *
     * @see BlurType
     */
    default EFXStyleableObjectProperty<BlurType> dropShadowBlurTypeProperty() {
        return getRippleEffect().dropShadowBlurTypeProperty();
    }

    /**
     * Sets the blur type for the drop shadow effect used in the ripple effect.
     *
     * @param dropShadowBlurType
     *         the blur type to set for the drop shadow effect
     *
     * @see BlurType
     */
    default void setDropShadowBlurType(BlurType dropShadowBlurType) {
        getRippleEffect().setDropShadowBlurType(dropShadowBlurType);
    }

    /**
     * Gets the drop shadow color for the ripple effect.
     *
     * @return The drop shadow color.
     *
     * @see Color
     */
    default Color getDropShadowColor() {
        return getRippleEffect().getDropShadowColor();
    }

    /**
     * Returns the drop shadow color property of the EFXStyleableObjectProperty.
     *
     * @return the drop shadow color property
     *
     * @see Color
     */
    default EFXStyleableObjectProperty<Color> dropShadowColorProperty() {
        return getRippleEffect().dropShadowColorProperty();
    }

    /**
     * Sets the color of the drop shadow effect for the ripple effect.
     *
     * @param dropShadowColor
     *         the color to set for the drop shadow effect
     *
     * @see Color
     */
    default void setDropShadowColor(Color dropShadowColor) {
        getRippleEffect().setDropShadowColor(dropShadowColor);
    }

    /**
     * Returns the drop shadow radius of the ripple effect.
     *
     * @return the drop shadow radius of the ripple effect.
     */
    default double getDropShadowRadius() {
        return getRippleEffect().getDropShadowRadius();
    }

    /**
     * Returns an EFXStyleableDoubleProperty for the drop shadow radius of the ripple effect.
     *
     * @return The EFXStyleableDoubleProperty for the drop shadow radius.
     */
    default EFXStyleableDoubleProperty dropShadowRadiusProperty() {
        return getRippleEffect().dropShadowRadiusProperty();
    }

    /**
     * Sets the drop shadow radius for the ripple effect.
     *
     * @param dropShadowRadius
     *         the radius of the drop shadow to be set
     */
    default void setDropShadowRadius(double dropShadowRadius) {
        getRippleEffect().setDropShadowRadius(dropShadowRadius);
    }

    /**
     * Retrieves the spread value of the drop shadow effect applied to the ripple effect.
     *
     * @return The spread value of the drop shadow effect.
     */
    default double getDropShadowSpread() {
        return getRippleEffect().getDropShadowSpread();
    }

    /**
     * Gets the drop shadow spread property.
     *
     * @return The drop shadow spread property.
     */
    default EFXStyleableDoubleProperty dropShadowSpreadProperty() {
        return getRippleEffect().dropShadowSpreadProperty();
    }

    /**
     * Sets the spread value for the drop shadow effect of the ripple effect.
     *
     * @param dropShadowSpread
     *         the spread value for the drop shadow effect
     */
    default void setDropShadowSpread(double dropShadowSpread) {
        getRippleEffect().setDropShadowSpread(dropShadowSpread);
    }

    /**
     * Retrieves the offset on the x-axis of the drop shadow effect applied to the element.
     *
     * @return the offset on the x-axis of the drop shadow effect
     */
    default double getDropShadowOffsetX() {
        return getRippleEffect().getDropShadowOffsetX();
    }

    /**
     * Retrieves the EFXStyleableDoubleProperty representing the offset along the x-axis of the drop shadow effect.
     *
     * @return The EFXStyleableDoubleProperty representing the drop shadow offset along the x-axis.
     */
    default EFXStyleableDoubleProperty dropShadowOffsetXProperty() {
        return getRippleEffect().dropShadowOffsetXProperty();
    }

    /**
     * Sets the horizontal offset of the drop shadow effect applied to the ripple effect.
     *
     * @param dropShadowOffsetX
     *         the horizontal offset of the drop shadow
     */
    default void setDropShadowOffsetX(double dropShadowOffsetX) {
        getRippleEffect().setDropShadowOffsetX(dropShadowOffsetX);
    }

    /**
     * Retrieves the offset in the Y-direction of the drop shadow effect applied to the ripple effect.
     *
     * @return The offset in the Y-direction of the drop shadow effect.
     */
    default double getDropShadowOffsetY() {
        return getRippleEffect().getDropShadowOffsetY();
    }

    /**
     * Returns the {@code EFXStyleableDoubleProperty} representing the offset along the Y-axis of the drop shadow effect applied to the ripple effect.
     *
     * @return the {@code EFXStyleableDoubleProperty} representing the offset along the Y-axis of the drop shadow effect
     */
    default EFXStyleableDoubleProperty dropShadowOffsetYProperty() {
        return getRippleEffect().dropShadowOffsetYProperty();
    }

    /**
     * Sets the offset of the drop shadow effect along the Y-axis.
     *
     * @param dropShadowOffsetY
     *         the value of the offset
     */
    default void setDropShadowOffsetY(double dropShadowOffsetY) {
        getRippleEffect().setDropShadowOffsetY(dropShadowOffsetY);
    }

    /**
     * Returns the drop shadow state of the ripple effect.
     *
     * @return The drop shadow state of the ripple effect.
     *
     * @see EFXState
     */
    default EFXState getDropShadowState() {
        return getRippleEffect().getDropShadowState();
    }

    /**
     * Returns the EFXStyleableObjectProperty representing the state of the drop shadow effect.
     *
     * @return The EFXStyleableObjectProperty representing the state of the drop shadow effect.
     *
     * @see EFXState
     */
    default EFXStyleableObjectProperty<EFXState> dropShadowStateProperty() {
        return getRippleEffect().dropShadowStateProperty();
    }

    /**
     * Sets the drop shadow state for the ripple effect.
     *
     * @param dropShadowEFXState
     *         the drop shadow state to be set
     *
     * @see EFXState
     */
    default void setDropShadowState(EFXState dropShadowEFXState) {
        getRippleEffect().setDropShadowState(dropShadowEFXState);
    }

    /**
     * Returns whether drop shadow effect is enabled for the component's ripple effect.
     *
     * @return true if drop shadow effect is enabled, false otherwise
     *
     * @see EFXState
     */
    default boolean isDropShadowEnabled() {
        return getRippleEffect().isDropShadowEnabled();
    }
}
