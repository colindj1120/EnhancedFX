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
package io.github.colindj1120.enhancedfx.graphics.factory;

import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleDefaults;
import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleDirection;
import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleShape;
import io.github.colindj1120.enhancedfx.graphics.effects.ripple.EFXRippleEffect;
import io.github.colindj1120.enhancedfx.base.enums.State;
import io.github.colindj1120.enhancedfx.base.exceptions.RippleEffectException;
import javafx.animation.Interpolator;
import javafx.scene.effect.BlurType;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Optional;

/**
 * A factory for creating {@link EFXRippleEffect} instances with customized settings. This factory allows for the chaining of setter methods to configure a {@code EFXRippleEffect} with specific properties
 * such as shape, color, duration, interpolator, and various states before building the final effect. It supports setting up both ripple and drop shadow effects with extensive customization options,
 * adhering to Material Design principles.
 *
 * <p>
 * The factory design pattern is utilized to encapsulate the complexity of constructing a {@code EFXRippleEffect} instance. It ensures that the {@code EFXRippleEffect} is correctly initialized with all the
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
 * EFXRippleEffect rippleEffect = new EFXRippleEffectFactory()
 *     .targetNode(myRegion)
 *     .rippleColor(Color.BLUE)
 *     .rippleDuration(Duration.millis(300))
 *     .build();
 * </pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXRippleEffect
 * @see RippleEffectException
 */
public class EFXRippleEffectFactory {
    private Region      targetNode  = null; //required field
    private State          rippleState     = EFXRippleDefaults.DEFAULT_RIPPLE_STATE;
    private EFXRippleShape efxRippleShape  = EFXRippleDefaults.DEFAULT_RIPPLE_SHAPE;
    private EFXRippleShape rippleClipShape = EFXRippleDefaults.DEFAULT_RIPPLE_CLIP_SHAPE;
    private Color          rippleColor     = EFXRippleDefaults.DEFAULT_RIPPLE_COLOR;
    private Duration        rippleDuration     = EFXRippleDefaults.DEFAULT_RIPPLE_DURATION;
    private Interpolator    rippleInterpolator = EFXRippleDefaults.DEFAULT_RIPPLE_INTERPOLATOR;
    private State           rippleFillState    = EFXRippleDefaults.DEFAULT_RIPPLE_FILL_STATE;
    private double          rippleRadius       = EFXRippleDefaults.DEFAULT_RIPPLE_RADIUS;
    private Color           rippleStrokeColor  = EFXRippleDefaults.DEFAULT_RIPPLE_STROKE_COLOR;
    private double             rippleStrokeWidth  = EFXRippleDefaults.DEFAULT_RIPPLE_STROKE_WIDTH;
    private EFXRippleDirection efxRippleDirection = EFXRippleDefaults.DEFAULT_RIPPLE_DIRECTION;
    private State              rippleFadeState    = EFXRippleDefaults.DEFAULT_RIPPLE_FADE_STATE;
    private BlurType        dropShadowBlurType = EFXRippleDefaults.DEFAULT_DROPSHADOW_BLUR_TYPE;
    private Color           dropShadowColor    = EFXRippleDefaults.DEFAULT_DROPSHADOW_COLOR;
    private double          dropShadowRadius   = EFXRippleDefaults.DEFAULT_DROPSHADOW_RADIUS;
    private double          dropShadowSpread   = EFXRippleDefaults.DEFAULT_DROPSHADOW_SPREAD;
    private double          dropShadowOffsetX  = EFXRippleDefaults.DEFAULT_DROPSHADOW_OFFSET_X;
    private double          dropShadowOffsetY  = EFXRippleDefaults.DEFAULT_DROPSHADOW_OFFSET_Y;
    private State           dropShadowState    = EFXRippleDefaults.DEFAULT_DROPSHADOW_STATE;

    /**
     * Sets the target node for the EFXRippleEffect and checks if the input is null.
     *
     * @param targetNode
     *         the target node to set for the EFXRippleEffect
     *
     * @return the EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory targetNode(Region targetNode) {
        checkInput(targetNode, "Target Node");
        this.targetNode = targetNode;
        return this;
    }

    /**
     * Sets the ripple state of the EFXRippleEffectFactory.
     *
     * @param rippleState
     *         the ripple state to set
     *
     * @return the updated EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleState(State rippleState) {
        checkInput(rippleState, "Ripple State");
        this.rippleState = rippleState;
        return this;
    }

    /**
     * Sets the shape of the ripple effect.
     *
     * @param efxRippleShape
     *         the shape of the ripple effect
     *
     * @return the instance of EFXRippleEffectFactory
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleShape(EFXRippleShape efxRippleShape) {
        checkInput(efxRippleShape, "Ripple Shape");
        this.efxRippleShape = efxRippleShape;
        return this;
    }

    /**
     * Sets the shape of the ripple clip for the EFXRippleEffectFactory. The ripple clip shape is used to define the shape of the graphical ripple that appears in response to user interactions, such as
     * mouse clicks or touches, on a UI component.
     *
     * @param rippleClipShape
     *         the shape of the ripple clip
     *
     * @return the EFXRippleEffectFactory instance with the updated ripple clip shape
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleClipShape(EFXRippleShape rippleClipShape) {
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
     * @return the EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleColor(Color rippleColor) {
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
     * @return the EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleDuration(Duration rippleDuration) {
        checkInput(rippleDuration, "Ripple Duration");
        this.rippleDuration = rippleDuration;
        return this;
    }

    /**
     * Sets the ripple interpolator for the EFXRippleEffectFactory.
     *
     * @param rippleInterpolator
     *         the Interpolator to set as the ripple interpolator
     *
     * @return the EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleInterpolator(Interpolator rippleInterpolator) {
        checkInput(rippleInterpolator, "Ripple Interpolator");
        this.rippleInterpolator = rippleInterpolator;
        return this;
    }

    /**
     * Sets the ripple fill state for the EFXRippleEffectFactory object.
     *
     * @param rippleFillState
     *         the new ripple fill state for the EFXRippleEffectFactory object
     *
     * @return the EFXRippleEffectFactory object with the updated ripple fill state
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleFillState(State rippleFillState) {
        checkInput(rippleFillState, "Ripple Fill State");
        this.rippleFillState = rippleFillState;
        return this;
    }

    /**
     * Sets the ripple radius of the EFXRippleEffectFactory. The ripple radius determines the size of the ripple effect.
     *
     * @param rippleRadius
     *         the radius of the ripple effect
     *
     * @return the EFXRippleEffectFactory instance with the updated ripple radius
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleRadius(double rippleRadius) {
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
     * @return the EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleStrokeColor(Color rippleStrokeColor) {
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
     * @return the EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleStrokeWidth(double rippleStrokeWidth) {
        checkInput(rippleStrokeWidth, "Ripple Stroke Width");
        this.rippleStrokeWidth = rippleStrokeWidth;
        return this;
    }

    /**
     * Sets the direction of the ripple effect in animations or UI interactions. This method is used to specify whether the ripple effect should move inward towards the center of the UI element or
     * outward from the center.
     *
     * @param efxRippleDirection
     *         the direction of the ripple effect
     *
     * @return the EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleDirection(EFXRippleDirection efxRippleDirection) {
        checkInput(efxRippleDirection, "Ripple Direction");
        this.efxRippleDirection = efxRippleDirection;
        return this;
    }

    /**
     * Sets the ripple fade state of the EFXRippleEffectFactory.
     *
     * @param rippleFadeState
     *         the ripple fade state to set
     *
     * @return the updated EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory rippleFadeState(State rippleFadeState) {
        checkInput(rippleFadeState, "Ripple Fade State");
        this.rippleFadeState = rippleFadeState;
        return this;
    }

    /**
     * Sets the blur type of the drop shadow effect for the EFXRippleEffectFactory.
     *
     * @param dropShadowBlurType
     *         the blur type of the drop shadow effect
     *
     * @return the EFXRippleEffectFactory instance with the updated drop shadow blur type
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory dropShadowBlurType(BlurType dropShadowBlurType) {
        checkInput(dropShadowBlurType, "Drop Shadow Blur Type");
        this.dropShadowBlurType = dropShadowBlurType;
        return this;
    }

    /**
     * Sets the drop shadow color for the EFXRippleEffectFactory.
     *
     * @param dropShadowColor
     *         the drop shadow color to set
     *
     * @return the updated EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory dropShadowColor(Color dropShadowColor) {
        checkInput(dropShadowColor, "Drop Shadow Color");
        this.dropShadowColor = dropShadowColor;
        return this;
    }

    /**
     * Sets the drop shadow radius for the EFXRippleEffectFactory.
     *
     * @param dropShadowRadius
     *         the drop shadow radius to set
     *
     * @return the EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory dropShadowRadius(double dropShadowRadius) {
        checkInput(dropShadowRadius, "Drop Shadow Radius");
        this.dropShadowRadius = dropShadowRadius;
        return this;
    }

    /**
     * Sets the spread value of the drop shadow effect for the EFXRippleEffectFactory.
     *
     * @param dropShadowSpread
     *         the spread value of the drop shadow effect
     *
     * @return the EFXRippleEffectFactory instance with the updated drop shadow spread
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory dropShadowSpread(double dropShadowSpread) {
        checkInput(dropShadowSpread, "Drop Shadow Spread");
        this.dropShadowSpread = dropShadowSpread;
        return this;
    }

    /**
     * Sets the x offset of the drop shadow effect for the EFXRippleEffectFactory.
     *
     * @param dropShadowOffsetX
     *         the x offset of the drop shadow effect
     *
     * @return the updated EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory dropShadowOffsetX(double dropShadowOffsetX) {
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
     * @return the EFXRippleEffectFactory instance with the updated dropShadowOffsetY
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory dropShadowOffsetY(double dropShadowOffsetY) {
        checkInput(dropShadowOffsetY, "Drop Shadow Offset Y");
        this.dropShadowOffsetY = dropShadowOffsetY;
        return this;
    }

    /**
     * Sets the drop shadow state of the EFXRippleEffectFactory.
     *
     * @param dropShadowState
     *         the drop shadow state to set
     *
     * @return the updated EFXRippleEffectFactory instance
     *
     * @throws RippleEffectException
     *         if the input is null
     */
    public EFXRippleEffectFactory dropShadowState(State dropShadowState) {
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
     * Builds and returns a {@link EFXRippleEffect} instance based on the configured properties. This method leverages {@link java.util.Optional} to ensure that the target node is not null, encapsulating
     * the construction of {@code EFXRippleEffect} within a functional paradigm.
     * <p>
     * If the target node is not specified (i.e., {@code null}), this method throws a {@link RippleEffectException} to indicate the failure to create a {@code EFXRippleEffect} due to the absence of a
     * required parameter.
     *
     * <p>
     * The method uses a fluent API design, setting various properties on the {@code EFXRippleEffect} instance such as state, shape, color, duration, and more, based on the previously configured values
     * in the factory. This approach ensures that the {@code EFXRippleEffect} is fully prepared and customized before being returned for use.
     * </p>
     *
     * <p>
     * This method applies a series of configurations to the {@code EFXRippleEffect}, including visual characteristics like color and shape, temporal properties such as duration and interpolator, as well
     * as more complex features like drop shadow and background handling. It provides a comprehensive setup for creating a {@code EFXRippleEffect} that is tailored to the specific needs of the
     * application.
     * </p>
     *
     * <p>
     * Example usage:
     * <pre>
     * EFXRippleEffect rippleEffect = new EFXRippleEffectFactory()
     *     .targetNode(myNode)
     *     .rippleColor(Color.BLUE)
     *     .build();
     * </pre>
     * </p>
     *
     * @return A fully configured {@link EFXRippleEffect} instance ready for use.
     *
     * @throws RippleEffectException
     *         if the target node is {@code null}.
     * @see EFXRippleEffect
     * @see RippleEffectException
     */
    @SuppressWarnings("DuplicatedCode")
    public EFXRippleEffect build() {
        return Optional.ofNullable(targetNode)
                       .map(node -> {
                           EFXRippleEffect efxRippleEffect = new EFXRippleEffect(targetNode);
                           efxRippleEffect.setRippleState(rippleState);
                           efxRippleEffect.setRippleShape(efxRippleShape);
                           efxRippleEffect.setRippleClipShape(rippleClipShape);
                           efxRippleEffect.setRippleColor(rippleColor);
                           efxRippleEffect.setRippleDuration(rippleDuration);
                           efxRippleEffect.setRippleInterpolator(rippleInterpolator);
                           efxRippleEffect.setRippleFillState(rippleFillState);
                           efxRippleEffect.setRippleRadius(rippleRadius);
                           efxRippleEffect.setRippleStrokeColor(rippleStrokeColor);
                           efxRippleEffect.setRippleStrokeWidth(rippleStrokeWidth);
                           efxRippleEffect.setRippleDirection(efxRippleDirection);
                           efxRippleEffect.setRippleFadeState(rippleFadeState);
                           efxRippleEffect.setDropShadowBlurType(dropShadowBlurType);
                           efxRippleEffect.setDropShadowColor(dropShadowColor);
                           efxRippleEffect.setDropShadowRadius(dropShadowRadius);
                           efxRippleEffect.setDropShadowSpread(dropShadowSpread);
                           efxRippleEffect.setDropShadowOffsetX(dropShadowOffsetX);
                           efxRippleEffect.setDropShadowOffsetY(dropShadowOffsetY);
                           efxRippleEffect.setDropShadowState(dropShadowState);

                           return efxRippleEffect;
                       })
                       .orElseThrow(() -> new RippleEffectException("The Ripple Effect requires Target Node to not be null"));
    }
}
