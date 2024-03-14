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
package io.github.colindj1120.enhancedfx.factories.effects;

import io.github.colindj1120.enhancedfx.effects.ripple.RippleEffect;
import io.github.colindj1120.enhancedfx.enums.State;
import io.github.colindj1120.enhancedfx.enums.effects.RippleDirection;
import io.github.colindj1120.enhancedfx.enums.effects.RippleShape;
import io.github.colindj1120.enhancedfx.exceptions.RippleEffectException;
import javafx.animation.Interpolator;
import javafx.scene.effect.BlurType;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Optional;

import static io.github.colindj1120.enhancedfx.effects.base.RippleDefaults.*;

/**
 * A factory for creating {@link RippleEffect} instances with customized settings. This factory allows for the chaining of setter methods to configure a {@code RippleEffect} with specific properties
 * such as shape, color, duration, interpolator, and various states before building the final effect. It supports setting up both ripple and drop shadow effects with extensive customization options,
 * adhering to Material Design principles.
 *
 * <p>
 * The factory design pattern is utilized to encapsulate the complexity of constructing a {@code RippleEffect} instance. It ensures that the {@code RippleEffect} is correctly initialized with all the
 * necessary parameters before use.
 * </p>
 *
 * <p>
 * The factory will throw a {@link RippleEffectException} if any of the passed in inputs are null or if when build is called and target node is null
 * </p>
 *
 * <p>
 * Usage example:
 * <pre>
 * RippleEffect rippleEffect = new RippleEffectFactory()
 *     .targetNode(myRegion)
 *     .rippleColor(Color.BLUE)
 *     .rippleDuration(Duration.millis(300))
 *     .build();
 * </pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RippleEffect
 * @see RippleEffectException
 */
public class RippleEffectFactory {
    private Region          targetNode         = null; //required field
    private State           rippleState        = DEFAULT_RIPPLE_STATE;
    private RippleShape     rippleShape        = DEFAULT_RIPPLE_SHAPE;
    private RippleShape     rippleClipShape    = DEFAULT_RIPPLE_CLIP_SHAPE;
    private Color           rippleColor        = DEFAULT_RIPPLE_COLOR;
    private Duration        rippleDuration     = DEFAULT_RIPPLE_DURATION;
    private Interpolator    rippleInterpolator = DEFAULT_RIPPLE_INTERPOLATOR;
    private State           rippleFillState    = DEFAULT_RIPPLE_FILL_STATE;
    private double          rippleRadius       = DEFAULT_RIPPLE_RADIUS;
    private Color           rippleStrokeColor  = DEFAULT_RIPPLE_STROKE_COLOR;
    private double          rippleStrokeWidth  = DEFAULT_RIPPLE_STROKE_WIDTH;
    private RippleDirection rippleDirection    = DEFAULT_RIPPLE_DIRECTION;
    private State           rippleFadeState    = DEFAULT_RIPPLE_FADE_STATE;
    private BlurType        dropShadowBlurType = DEFAULT_DROPSHADOW_BLUR_TYPE;
    private Color           dropShadowColor    = DEFAULT_DROPSHADOW_COLOR;
    private double          dropShadowRadius   = DEFAULT_DROPSHADOW_RADIUS;
    private double          dropShadowSpread   = DEFAULT_DROPSHADOW_SPREAD;
    private double          dropShadowOffsetX  = DEFAULT_DROPSHADOW_OFFSET_X;
    private double          dropShadowOffsetY  = DEFAULT_DROPSHADOW_OFFSET_Y;
    private State           dropShadowState    = DEFAULT_DROPSHADOW_STATE;

    /**
     * Sets the target node for the RippleEffect and checks if the input is null.
     *
     * @param targetNode
     *         the target node to set for the RippleEffect
     *
     * @return the RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory targetNode(Region targetNode) {
        checkInput(targetNode, "Target Node");
        this.targetNode = targetNode;
        return this;
    }

    /**
     * Sets the ripple state of the RippleEffectFactory.
     *
     * @param rippleState
     *         the ripple state to set
     *
     * @return the updated RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleState(State rippleState) {
        checkInput(rippleState, "Ripple State");
        this.rippleState = rippleState;
        return this;
    }

    /**
     * Sets the shape of the ripple effect.
     *
     * @param rippleShape
     *         the shape of the ripple effect
     *
     * @return the instance of RippleEffectFactory
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleShape(RippleShape rippleShape) {
        checkInput(rippleShape, "Ripple Shape");
        this.rippleShape = rippleShape;
        return this;
    }

    /**
     * Sets the shape of the ripple clip for the RippleEffectFactory. The ripple clip shape is used to define the shape of the graphical ripple that appears in response to user interactions, such as
     * mouse clicks or touches, on a UI component.
     *
     * @param rippleClipShape
     *         the shape of the ripple clip
     *
     * @return the RippleEffectFactory instance with the updated ripple clip shape
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleClipShape(RippleShape rippleClipShape) {
        checkInput(rippleClipShape, "Ripple Clip Shape");
        this.rippleClipShape = rippleClipShape;
        return this;
    }

    /**
     * Sets the color of the ripple effect. Throws a RippleEffectException if the input is null.
     *
     * @param rippleColor
     *         the color of the ripple effect
     *
     * @return the RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleColor(Color rippleColor) {
        checkInput(rippleColor, "Ripple Color");
        this.rippleColor = rippleColor;
        return this;
    }

    /**
     * Sets the duration of the ripple effect.
     *
     * @param rippleDuration
     *         the duration of the ripple effect
     *
     * @return the RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleDuration(Duration rippleDuration) {
        checkInput(rippleDuration, "Ripple Duration");
        this.rippleDuration = rippleDuration;
        return this;
    }

    /**
     * Sets the ripple interpolator for the RippleEffectFactory.
     *
     * @param rippleInterpolator
     *         the Interpolator to set as the ripple interpolator
     *
     * @return the RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleInterpolator(Interpolator rippleInterpolator) {
        checkInput(rippleInterpolator, "Ripple Interpolator");
        this.rippleInterpolator = rippleInterpolator;
        return this;
    }

    /**
     * Sets the ripple fill state for the RippleEffectFactory object.
     *
     * @param rippleFillState
     *         the new ripple fill state for the RippleEffectFactory object
     *
     * @return the RippleEffectFactory object with the updated ripple fill state
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleFillState(State rippleFillState) {
        checkInput(rippleFillState, "Ripple Fill State");
        this.rippleFillState = rippleFillState;
        return this;
    }

    /**
     * Sets the ripple radius of the RippleEffectFactory. The ripple radius determines the size of the ripple effect.
     *
     * @param rippleRadius
     *         the radius of the ripple effect
     *
     * @return the RippleEffectFactory instance with the updated ripple radius
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleRadius(double rippleRadius) {
        checkInput(rippleRadius, "Ripple Radius");
        this.rippleRadius = rippleRadius;
        return this;
    }

    /**
     * Sets the color of the stroke for the ripple effect.
     *
     * @param rippleStrokeColor
     *         the color of the stroke for the ripple effect
     *
     * @return the RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleStrokeColor(Color rippleStrokeColor) {
        checkInput(rippleStrokeColor, "Ripple Stroke Color");
        this.rippleStrokeColor = rippleStrokeColor;
        return this;
    }

    /**
     * Sets the width of the ripple effect stroke. Throws a RippleEffectException if the input is null.
     *
     * @param rippleStrokeWidth
     *         the width of the ripple stroke
     *
     * @return the RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleStrokeWidth(double rippleStrokeWidth) {
        checkInput(rippleStrokeWidth, "Ripple Stroke Width");
        this.rippleStrokeWidth = rippleStrokeWidth;
        return this;
    }

    /**
     * Sets the direction of the ripple effect in animations or UI interactions. This method is used to specify whether the ripple effect should move inward towards the center of the UI element or
     * outward from the center.
     *
     * @param rippleDirection
     *         the direction of the ripple effect
     *
     * @return the RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleDirection(RippleDirection rippleDirection) {
        checkInput(rippleDirection, "Ripple Direction");
        this.rippleDirection = rippleDirection;
        return this;
    }

    /**
     * Sets the ripple fade state of the RippleEffectFactory.
     *
     * @param rippleFadeState
     *         the ripple fade state to set
     *
     * @return the updated RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory rippleFadeState(State rippleFadeState) {
        checkInput(rippleFadeState, "Ripple Fade State");
        this.rippleFadeState = rippleFadeState;
        return this;
    }

    /**
     * Sets the blur type of the drop shadow effect for the RippleEffectFactory.
     *
     * @param dropShadowBlurType
     *         the blur type of the drop shadow effect
     *
     * @return the RippleEffectFactory instance with the updated drop shadow blur type
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory dropShadowBlurType(BlurType dropShadowBlurType) {
        checkInput(dropShadowBlurType, "Drop Shadow Blur Type");
        this.dropShadowBlurType = dropShadowBlurType;
        return this;
    }

    /**
     * Sets the drop shadow color for the RippleEffectFactory.
     *
     * @param dropShadowColor
     *         the drop shadow color to set
     *
     * @return the updated RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory dropShadowColor(Color dropShadowColor) {
        checkInput(dropShadowColor, "Drop Shadow Color");
        this.dropShadowColor = dropShadowColor;
        return this;
    }

    /**
     * Sets the drop shadow radius for the RippleEffectFactory.
     *
     * @param dropShadowRadius
     *         the drop shadow radius to set
     *
     * @return the RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory dropShadowRadius(double dropShadowRadius) {
        checkInput(dropShadowRadius, "Drop Shadow Radius");
        this.dropShadowRadius = dropShadowRadius;
        return this;
    }

    /**
     * Sets the spread value of the drop shadow effect for the RippleEffectFactory.
     *
     * @param dropShadowSpread
     *         the spread value of the drop shadow effect
     *
     * @return the RippleEffectFactory instance with the updated drop shadow spread
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory dropShadowSpread(double dropShadowSpread) {
        checkInput(dropShadowSpread, "Drop Shadow Spread");
        this.dropShadowSpread = dropShadowSpread;
        return this;
    }

    /**
     * Sets the x offset of the drop shadow effect for the RippleEffectFactory.
     *
     * @param dropShadowOffsetX
     *         the x offset of the drop shadow effect
     *
     * @return the updated RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory dropShadowOffsetX(double dropShadowOffsetX) {
        checkInput(dropShadowOffsetX, "Drop Shadow Offset X");
        this.dropShadowOffsetX = dropShadowOffsetX;
        return this;
    }

    /**
     * Sets the vertical offset for the drop shadow effect.
     *
     * @param dropShadowOffsetY
     *         the vertical offset for the drop shadow effect
     *
     * @return the RippleEffectFactory instance with the updated dropShadowOffsetY
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory dropShadowOffsetY(double dropShadowOffsetY) {
        checkInput(dropShadowOffsetY, "Drop Shadow Offset Y");
        this.dropShadowOffsetY = dropShadowOffsetY;
        return this;
    }

    /**
     * Sets the drop shadow state of the RippleEffectFactory.
     *
     * @param dropShadowState
     *         the drop shadow state to set
     *
     * @return the updated RippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public RippleEffectFactory dropShadowState(State dropShadowState) {
        checkInput(dropShadowState, "Drop Shadow State");
        this.dropShadowState = dropShadowState;
        return this;
    }

    /**
     * Checks if the input is null. If the input is null, a RippleEffectException is thrown.
     *
     * @param <T>
     *         the type of the input
     * @param input
     *         the input to check
     * @param name
     *         the name of the input
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    private <T> void checkInput(T input, String name) {
        if (input == null) {
            throw new RippleEffectException(name + " cannot be null when passed into the setter function");
        }
    }

    /**
     * Builds and returns a {@link RippleEffect} instance based on the configured properties. This method leverages {@link java.util.Optional} to ensure that the target node is not null, encapsulating
     * the construction of {@code RippleEffect} within a functional paradigm.
     * <p>
     * If the target node is not specified (i.e., {@code null}), this method throws a {@link RippleEffectException} to indicate the failure to create a {@code RippleEffect} due to the absence of a
     * required parameter.
     *
     * <p>
     * The method uses a fluent API design, setting various properties on the {@code RippleEffect} instance such as state, shape, color, duration, and more, based on the previously configured values
     * in the factory. This approach ensures that the {@code RippleEffect} is fully prepared and customized before being returned for use.
     * </p>
     *
     * <p>
     * This method applies a series of configurations to the {@code RippleEffect}, including visual characteristics like color and shape, temporal properties such as duration and interpolator, as well
     * as more complex features like drop shadow and background handling. It provides a comprehensive setup for creating a {@code RippleEffect} that is tailored to the specific needs of the
     * application.
     * </p>
     *
     * <p>
     * Example usage:
     * <pre>
     * RippleEffect rippleEffect = new RippleEffectFactory()
     *     .targetNode(myNode)
     *     .rippleColor(Color.BLUE)
     *     .build();
     * </pre>
     * </p>
     *
     * @return A fully configured {@link RippleEffect} instance ready for use.
     *
     * @throws RippleEffectException
     *         if the target node is {@code null}.
     * @see RippleEffect
     * @see RippleEffectException
     */
    @SuppressWarnings("DuplicatedCode")
    public RippleEffect build() {
        return Optional.ofNullable(targetNode)
                       .map(node -> {
                           RippleEffect rippleEffect = new RippleEffect(targetNode);
                           rippleEffect.setRippleState(rippleState);
                           rippleEffect.setRippleShape(rippleShape);
                           rippleEffect.setRippleClipShape(rippleClipShape);
                           rippleEffect.setRippleColor(rippleColor);
                           rippleEffect.setRippleDuration(rippleDuration);
                           rippleEffect.setRippleInterpolator(rippleInterpolator);
                           rippleEffect.setRippleFillState(rippleFillState);
                           rippleEffect.setRippleRadius(rippleRadius);
                           rippleEffect.setRippleStrokeColor(rippleStrokeColor);
                           rippleEffect.setRippleStrokeWidth(rippleStrokeWidth);
                           rippleEffect.setRippleDirection(rippleDirection);
                           rippleEffect.setRippleFadeState(rippleFadeState);
                           rippleEffect.setDropShadowBlurType(dropShadowBlurType);
                           rippleEffect.setDropShadowColor(dropShadowColor);
                           rippleEffect.setDropShadowRadius(dropShadowRadius);
                           rippleEffect.setDropShadowSpread(dropShadowSpread);
                           rippleEffect.setDropShadowOffsetX(dropShadowOffsetX);
                           rippleEffect.setDropShadowOffsetY(dropShadowOffsetY);
                           rippleEffect.setDropShadowState(dropShadowState);

                           return rippleEffect;
                       })
                       .orElseThrow(() -> new RippleEffectException("The Ripple Effect requires Target Node to not be null"));
    }
}
