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
package io.github.colindj1120.materialdesignui.effects.ripple;

import io.github.colindj1120.materialdesignui.animation.AnimationManager;
import io.github.colindj1120.materialdesignui.controls.EnhancedToggleButton;
import io.github.colindj1120.materialdesignui.converters.InterpolatorStyleConverter;
import io.github.colindj1120.materialdesignui.enums.Status;
import io.github.colindj1120.materialdesignui.enums.effects.RippleDirection;
import io.github.colindj1120.materialdesignui.enums.effects.RippleShape;
import io.github.colindj1120.materialdesignui.exceptions.RippleEffectException;
import io.github.colindj1120.materialdesignui.factories.styling.CssFactory;
import io.github.colindj1120.materialdesignui.factories.styling.StyleableDoublePropertyFactory;
import io.github.colindj1120.materialdesignui.factories.styling.StyleableObjectPropertyFactory;
import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesManager;
import io.github.colindj1120.materialdesignui.utils.PropertyUtils;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableDoubleProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.ColorConverter;
import javafx.css.converter.DurationConverter;
import javafx.css.converter.EnumConverter;
import javafx.css.converter.SizeConverter;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static io.github.colindj1120.materialdesignui.effects.base.RippleDefaults.*;
import static io.github.colindj1120.materialdesignui.utils.AnimationUtils.*;

/**
 * Implements a ripple effect for UI components, which can be customized through CSS. This effect creates visual feedback for user interactions like mouse clicks. The Ripple effect can take on many
 * different shapes determined by {@link RippleShape}
 *
 * <p>The RippleEffect class supports various customizable properties such as color, duration,
 * interpolator, and more, which can be defined in CSS files. This allows for a flexible and dynamic approach to styling JavaFX components with ripple effects.</p>
 *
 * <p>Example CSS to customize the ripple effect:</p>
 * <pre>
 * .button {
 *     -fx-ripple-color: #FF0000;
 *     -fx-ripple-shape: CIRCLE;
 *     -fx-ripple-clip-shape: ROUNDED_RECTANGLE;
 *     -fx-ripple-duration: 400;
 *     -fx-ripple-interpolator: "EASE_OUT";
 *     -fx-ripple-radius: 20;
 *     -fx-ripple-stroke-width: .25;
 *     -fx-ripple-stroke-color: #FF0000;
 *     -fx-ripple-fill-state: ENABLED;
 *     -fx-ripple-direction: OUT;
 *     -fx-ripple-fade-state: ENABLED;
 *     -fx-ripple-drop-shadow-blur-type: GAUSSIAN;
 *     -fx-ripple-drop-shadow-color: rgba(0, 0, 0, 0.5);
 *     -fx-ripple-drop-shadow-radius: 5;
 *     -fx-ripple-drop-shadow-spread: 0.2;
 *     -fx-ripple-drop-shadow-offset-x: 2;
 *     -fx-ripple-drop-shadow-offset-y: 2;
 *     -fx-ripple-drop-shadow-state: ENABLED;
 * }
 * </pre>
 *
 * <p>Properties such as {@code -fx-ripple-color}, {@code -fx-ripple-duration}, and others
 * allow for detailed customization of the ripple effect, including its appearance and behavior.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RippleShape
 * @see Interpolator
 * @see InterpolatorStyleConverter
 * @see Duration
 * @see Color
 */
public class RippleEffect extends Region {
    private static final String                     RIPPLE_STYLE     = "ripple-effect";
    private final AnimationManager           animationManager = new AnimationManager();
    private static final StyleablePropertiesManager stylesManager    = new StyleablePropertiesManager(Region.getClassCssMetaData());

    private StyleableObjectProperty<Status>          rippleState;
    private StyleableObjectProperty<RippleShape>     rippleShape;
    private StyleableObjectProperty<RippleShape>     rippleClipShape;
    private StyleableObjectProperty<Color>           rippleColor;
    private StyleableObjectProperty<Duration>        rippleDuration;
    private StyleableObjectProperty<Interpolator>    rippleInterpolator;
    private StyleableObjectProperty<Status>          rippleFillState;
    private StyleableDoubleProperty                  rippleRadius;
    private StyleableObjectProperty<Color>           rippleStrokeColor;
    private StyleableDoubleProperty                  rippleStrokeWidth;
    private StyleableObjectProperty<RippleDirection> rippleDirection;
    private StyleableObjectProperty<Status>          rippleFadeState;
    private StyleableObjectProperty<BlurType>        dropShadowBlurType;
    private StyleableObjectProperty<Color>           dropShadowColor;
    private StyleableDoubleProperty                  dropShadowRadius;
    private StyleableDoubleProperty                  dropShadowSpread;
    private StyleableDoubleProperty                  dropShadowOffsetX;
    private StyleableDoubleProperty                  dropShadowOffsetY;
    private StyleableObjectProperty<Status>          dropShadowState;

    private final Region targetNode;

    static {
        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Status>create()
                                               .property("-fx-ripple-state")
                                               .converter(EnumConverter.getEnumConverter(Status.class))
                                               .initialValue(DEFAULT_RIPPLE_STATE)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleState))
                                               .propertyGetterFunction(node -> node.rippleState));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, RippleShape>create()
                                               .property("-fx-ripple-shape")
                                               .converter(EnumConverter.getEnumConverter(RippleShape.class))
                                               .initialValue(DEFAULT_RIPPLE_SHAPE)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleShape))
                                               .propertyGetterFunction(node -> node.rippleShape));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, RippleShape>create()
                                               .property("-fx-ripple-clip-shape")
                                               .converter(EnumConverter.getEnumConverter(RippleShape.class))
                                               .initialValue(DEFAULT_RIPPLE_CLIP_SHAPE)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleClipShape))
                                               .propertyGetterFunction(node -> node.rippleClipShape));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Color>create()
                                               .property("-fx-ripple-color")
                                               .converter(ColorConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_COLOR)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleColor))
                                               .propertyGetterFunction(node -> node.rippleColor));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Duration>create()
                                               .property("-fx-ripple-duration")
                                               .converter(DurationConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_DURATION)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleDuration))
                                               .propertyGetterFunction(node -> node.rippleDuration));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Interpolator>create()
                                               .property("-fx-ripple-interpolator")
                                               .converter(InterpolatorStyleConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_INTERPOLATOR)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleInterpolator))
                                               .propertyGetterFunction(node -> node.rippleInterpolator));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Status>create()
                                               .property("-fx-ripple-fill-state")
                                               .converter(EnumConverter.getEnumConverter(Status.class))
                                               .initialValue(DEFAULT_RIPPLE_FILL_STATE)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleFillState))
                                               .propertyGetterFunction(node -> node.rippleFillState));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Number>create()
                                               .property("-fx-ripple-radius")
                                               .converter(SizeConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_RADIUS)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleRadius))
                                               .propertyGetterFunction(node -> node.rippleRadius));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Number>create()
                                               .property("-fx-ripple-stroke-width")
                                               .converter(SizeConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_STROKE_WIDTH)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleStrokeWidth))
                                               .propertyGetterFunction(node -> node.rippleStrokeWidth));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Color>create()
                                               .property("-fx-ripple-stroke-color")
                                               .converter(ColorConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_STROKE_COLOR)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleStrokeColor))
                                               .propertyGetterFunction(node -> node.rippleStrokeColor));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, RippleDirection>create()
                                               .property("-fx-ripple-direction")
                                               .converter(EnumConverter.getEnumConverter(RippleDirection.class))
                                               .initialValue(DEFAULT_RIPPLE_DIRECTION)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleDirection))
                                               .propertyGetterFunction(node -> node.rippleDirection));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Status>create()
                                               .property("-fx-ripple-fade-state")
                                               .converter(EnumConverter.getEnumConverter(Status.class))
                                               .initialValue(DEFAULT_RIPPLE_FADE_STATE)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.rippleFadeState))
                                               .propertyGetterFunction(node -> node.rippleFadeState));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, BlurType>create()
                                               .property("-fx-ripple-drop-shadow-blur-type")
                                               .converter(EnumConverter.getEnumConverter(BlurType.class))
                                               .initialValue(DEFAULT_DROPSHADOW_BLUR_TYPE)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.dropShadowBlurType))
                                               .propertyGetterFunction(node -> node.dropShadowBlurType));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Color>create()
                                               .property("-fx-ripple-drop-shadow-color")
                                               .converter(ColorConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_COLOR)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.dropShadowColor))
                                               .propertyGetterFunction(node -> node.dropShadowColor));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Number>create()
                                               .property("-fx-ripple-drop-shadow-radius")
                                               .converter(SizeConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_RADIUS)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.dropShadowRadius))
                                               .propertyGetterFunction(node -> node.dropShadowRadius));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Number>create()
                                               .property("-fx-ripple-drop-shadow-spread")
                                               .converter(SizeConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_SPREAD)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.dropShadowSpread))
                                               .propertyGetterFunction(node -> node.dropShadowSpread));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Number>create()
                                               .property("-fx-ripple-drop-shadow-offset-x")
                                               .converter(SizeConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_OFFSET_X)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.dropShadowOffsetX))
                                               .propertyGetterFunction(node -> node.dropShadowOffsetX));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Number>create()
                                               .property("-fx-ripple-drop-shadow-offset-y")
                                               .converter(SizeConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_OFFSET_Y)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.dropShadowOffsetY))
                                               .propertyGetterFunction(node -> node.dropShadowOffsetY));

        stylesManager.addCssMetaData(CssFactory.<RippleEffect, Status>create()
                                               .property("-fx-ripple-drop-shadow-state")
                                               .converter(EnumConverter.getEnumConverter(Status.class))
                                               .initialValue(DEFAULT_DROPSHADOW_STATE)
                                               .isSettableFunction(node -> PropertyUtils.checkProperty(node.dropShadowState))
                                               .propertyGetterFunction(node -> node.dropShadowState));
    }

    /**
     * Initializes the ripple effect with a target node. The ripple effect properties are set to their default values, which can be overridden by CSS.
     *
     * @param targetNode
     *         The node to which the ripple effect will be applied.
     */
    public RippleEffect(Region targetNode) {
        initializeStyleableProperties();

        this.targetNode = targetNode;
        this.setPickOnBounds(false);
        targetNode.setOnMouseClicked(this::createAndAnimateRipple);

        InvalidationListener clipInvalidationListener = invalidated -> this.setClip(createRippleClip());
        targetNode.widthProperty()
                  .addListener(clipInvalidationListener);
        targetNode.heightProperty()
                  .addListener(clipInvalidationListener);
        targetNode.backgroundProperty()
                  .addListener(clipInvalidationListener);
        rippleClipShape.addListener(clipInvalidationListener);

        this.getStyleClass()
            .setAll(RIPPLE_STYLE);
    }

    /**
     * Constructs a {@link Shape} to be used as a clipping mask based on the current {@code rippleShape} configuration. This method determines the appropriate clipping shape to match the ripple effect
     * shape, ensuring the visual boundaries of the ripple effect are consistent with the intended design. The method supports various shapes such as rectangles, rounded rectangles, circles, and
     * ellipses. It utilizes {@link Optional} to gracefully handle potential null values and enforce the requirement that a valid shape configuration is provided.
     *
     * @return A {@link Shape} object that serves as a clipping mask for the ripple effect.
     *
     * @throws RippleEffectException
     *         if {@code rippleShape} is null, indicating that a valid shape must be specified for creating a clipping mask.
     */
    private Shape createRippleClip() {
        return Optional.ofNullable(rippleClipShape.get())
                       .map(shape -> switch (shape) {
                           case RECTANGLE -> createRectangleClip();
                           case ROUNDED_RECTANGLE -> createRoundedRectangleClip();
                           case CIRCLE -> createCircleClip();
                           case VERTICAL_ELLIPSE, HORIZONTAL_ELLIPSE -> createEllipseClip();
                       })
                       .orElseThrow(() -> new RippleEffectException("Ripple Shape cannot be null when creating a ripple clip"));

    }

    /**
     * Creates a rectangle clipping mask matching the dimensions of the target node. This method provides a straightforward rectangular clip without rounded corners.
     *
     * @return A {@link Rectangle} object representing the clipping mask.
     */
    private Rectangle createRectangleClip() {
        return new Rectangle(targetNode.getWidth(), targetNode.getHeight());
    }

    /**
     * Creates a rounded rectangle clipping mask based on the target node's dimensions and corner radii. This method allows for a clipping mask with rounded corners, providing a more aesthetically
     * pleasing visual effect that can match the UI component's design.
     *
     * @return A {@link Rectangle} with rounded corners representing the clipping mask.
     */
    private Rectangle createRoundedRectangleClip() {
        Rectangle clip = new Rectangle();
        clip.setWidth(targetNode.getWidth());
        clip.setHeight(targetNode.getHeight());

        CornerRadii radii = getCornerRadii(targetNode);

        clip.setArcWidth(radii.getTopLeftHorizontalRadius() * 2);
        clip.setArcHeight(radii.getTopLeftVerticalRadius() * 2);

        return clip;
    }

    /**
     * Creates a circular clipping mask. This method assumes the target node is square (width equals height) and uses the smaller dimension as the circle's radius. If the target node is not square, it
     * throws an {@link RippleEffectException} to indicate the mismatch.
     *
     * @return A {@link Circle} representing the clipping mask.
     *
     * @throws RippleEffectException
     *         if the target node's width and height are not equal, indicating a circular clip cannot be accurately applied.
     */
    private Circle createCircleClip() {
        if (targetNode.getWidth() != targetNode.getHeight()) {
            throw new RippleEffectException("Clip Shape should be ellipse not circle. Target node width and height are not equal");
        }
        double radius = Math.min(targetNode.getWidth(), targetNode.getHeight()) / 2;
        return new Circle(radius);
    }

    /**
     * Creates an ellipse clipping mask based on the target node's dimensions. This method is suitable for scenarios where the target node's width and height differ, providing an elliptical clip that
     * matches the aspect ratio of the target node.
     *
     * @return An {@link Ellipse} representing the clipping mask.
     */
    private Ellipse createEllipseClip() {
        double horizontalRadius = targetNode.getWidth() / 2;
        double verticalRadius   = targetNode.getHeight() / 2;
        return new Ellipse(horizontalRadius, verticalRadius);
    }

    /**
     * Retrieves the {@link CornerRadii} of the target node's background, if any. This method is used to determine the appropriate rounded corners for clipping masks and ripple effects based on the
     * target node's current design.
     *
     * @param targetNode
     *         The {@link Region} whose corner radii are to be determined.
     *
     * @return The {@link CornerRadii} of the target node's background or {@link CornerRadii#EMPTY} if no background or radii are defined.
     */
    private CornerRadii getCornerRadii(Region targetNode) {
        return Optional.ofNullable(targetNode.getBackground())
                       .flatMap(background -> Optional.ofNullable(background.getFills()))
                       .filter(fills -> !fills.isEmpty())
                       .map(fills -> fills.getFirst()
                                          .getRadii())
                       .orElse(CornerRadii.EMPTY);
    }

    /**
     * Generates and animates a ripple effect in response to a mouse click event on the target node. The animation properties such as duration and interpolator are based on the current styleable
     * property values.
     *
     * @param event
     *         The mouse event that triggers the ripple effect.
     */
    private void createAndAnimateRipple(MouseEvent event) {
        if (isRippleEnabled()) {
            Shape ripple = createRippleEffect(event);
            this.getChildren()
                .add(ripple);

            double       endScale     = getEndScale(event);
            double       endOpacity   = isRippleFadeEnabled() ? 0 : 1;
            Interpolator interpolator = getRippleInterpolator();

            //@formatter:off
            Timeline rippleTimeline = createAnimation(createKeyFrame(getRippleDuration(), e -> this.getChildren().remove(ripple),
                                                                    createKeyValue(ripple.scaleXProperty(), endScale, interpolator),
                                                                    createKeyValue(ripple.scaleYProperty(), endScale, interpolator),
                                                                    createKeyValue(ripple.opacityProperty(), endOpacity, interpolator)));
            //@formatter:on

            String rippleKey = "Ripple" + ripple.hashCode();
            animationManager.addAnimation(rippleKey, rippleTimeline);
            animationManager.playAnimation(rippleKey);
            animationManager.setOnFinished(rippleKey, e -> animationManager.removeAnimation(rippleKey));
        }
    }

    /**
     * Calculates the end scale for the ripple effect based on the event's location and the target node's dimensions. This determines how far the ripple should expand.
     *
     * @param event
     *         The mouse event that triggers the ripple effect.
     *
     * @return The calculated end scale for the ripple animation.
     */
    private double getEndScale(MouseEvent event) {
        // Calculate the maximum distance from the event point to the corners of the target node
        double maxX = Math.max(event.getX(), targetNode.prefWidth(-1) - event.getX());
        double maxY = Math.max(event.getY(), targetNode.prefHeight(-1) - event.getY());
        // The diagonal distance will be the furthest point the ripple needs to cover
        double maxDistance = Math.sqrt(maxX * maxX + maxY * maxY);

        // Calculate the end scale factor based on the maximum distance and the initial radius of the ripple
        return isRippleDirectionOut() ? maxDistance / rippleRadius.getValue() * 2 : 0.1; // Multiply by 2 for both directions
    }

    /**
     * Dynamically creates a ripple effect {@link Shape} based on the specified ripple shape configuration. This method utilizes {@link Optional} to safely handle the potential nullability of
     * {@code rippleShape}. Depending on the configured {@code rippleShape} value, it delegates to specific methods to create the appropriate {@link Shape} instance for the ripple effect, such as a
     * rectangle, circle, or ellipse. The shape and behavior of the ripple are determined by the type of the event-triggering UI component and the mouse event coordinates.
     *
     * <p>
     * This implementation ensures that a {@link RippleEffectException} is thrown if {@code rippleShape} is not set, enforcing the requirement that a valid shape must be specified to create a ripple
     * effect. This approach enhances the robustness of the method by explicitly handling the case where the ripple shape configuration might be missing or incorrectly set.
     * </p>
     *
     * @param event
     *         The {@link MouseEvent} that triggers the creation of the ripple effect, used to determine the origin and characteristics of the ripple.
     *
     * @return A {@link Shape} object that represents the created ripple effect, tailored according to the configured shape and the event details.
     *
     * @throws RippleEffectException
     *         if {@code rippleShape} is null, ensuring that a valid shape is always provided for the creation of a ripple effect.
     */
    @NotNull
    private Shape createRippleEffect(MouseEvent event) {
        return Optional.ofNullable(rippleShape.get())
                       .map(shape -> switch (shape) {
                           case ROUNDED_RECTANGLE -> createRoundedRectangleRipple(event);
                           case RECTANGLE -> createRectangleRipple(event);
                           case CIRCLE -> createCircleRipple(event);
                           case HORIZONTAL_ELLIPSE -> createHorizontalEllipseRipple(event);
                           case VERTICAL_ELLIPSE -> createVerticalEllipseRipple(event);
                       })
                       .orElseThrow(() -> new RippleEffectException("Ripple Shape cannot be null when creating a ripple effect"));
    }

    /**
     * Creates a rounded rectangle ripple effect shape. This method calculates the ripple's size and position based on the {@link MouseEvent} coordinates and the predefined ripple radius. It also
     * applies rounded corners to the rectangle based on the target node's corner radii, providing a visually integrated ripple effect within the UI component.
     *
     * @param event
     *         The {@link MouseEvent} triggering the ripple effect, used for calculating the ripple's center.
     *
     * @return A {@link Rectangle} with rounded corners representing the ripple effect.
     */
    @NotNull
    private Rectangle createRoundedRectangleRipple(MouseEvent event) {
        CornerRadii radii        = getCornerRadii(targetNode);
        double      rippleRadius = getRippleRadius();

        double width  = rippleRadius * 2;
        double height = rippleRadius * 2;

        Rectangle ripple = new Rectangle(event.getX() - rippleRadius, event.getY() - rippleRadius, width, height);

        ripple.setArcWidth(radii.getTopLeftHorizontalRadius());
        ripple.setArcHeight(radii.getTopLeftVerticalRadius());

        applyRippleEffectProperties(ripple);

        return ripple;
    }

    /**
     * Creates a rectangle ripple effect shape. This method constructs a {@link Rectangle} based on the {@link MouseEvent} coordinates and the predefined ripple radius, offering a simple, geometric
     * ripple effect suitable for various UI components.
     *
     * @param event
     *         The {@link MouseEvent} triggering the ripple effect, used for determining the rectangle's origin.
     *
     * @return A {@link Rectangle} representing the ripple effect.
     */
    @NotNull
    private Rectangle createRectangleRipple(MouseEvent event) {
        double    width  = rippleRadius.getValue() * 2;
        double    height = rippleRadius.getValue() * 2;
        Rectangle ripple = new Rectangle(event.getX() - rippleRadius.getValue(), event.getY() - rippleRadius.getValue(), width, height);
        applyRippleEffectProperties(ripple);
        return ripple;
    }

    /**
     * Creates a circle ripple effect shape. The method generates a {@link Circle} using the {@link MouseEvent} coordinates as the center and the predefined ripple radius. This shape provides a
     * symmetrical, visually appealing ripple effect, ideal for circular or small UI elements.
     *
     * @param event
     *         The {@link MouseEvent} triggering the ripple effect, used to set the circle's center.
     *
     * @return A {@link Circle} representing the ripple effect.
     */
    @NotNull
    private Circle createCircleRipple(MouseEvent event) {
        Circle ripple = new Circle(event.getX(), event.getY(), rippleRadius.getValue());
        applyRippleEffectProperties(ripple);
        return ripple;
    }

    /**
     * Creates a horizontal ellipse ripple effect. This method forms an {@link Ellipse} with its major axis aligned horizontally. The ellipse's size and position are determined based on the
     * {@link MouseEvent} coordinates and the predefined ripple radius, creating a stretched ripple effect along the horizontal axis.
     *
     * @param event
     *         The {@link MouseEvent} triggering the ripple effect, used to set the ellipse's center.
     *
     * @return An {@link Ellipse} representing the horizontal ripple effect.
     */
    @NotNull
    private Ellipse createHorizontalEllipseRipple(MouseEvent event) {
        double radius = getRippleRadius();

        Ellipse ripple = new Ellipse(event.getX(), event.getY(), radius * 2, radius);
        applyRippleEffectProperties(ripple);
        return ripple;
    }

    /**
     * Creates a vertical ellipse ripple effect. Similar to the horizontal ellipse method, this creates an {@link Ellipse} but with its major axis aligned vertically. It uses the {@link MouseEvent}
     * coordinates and the predefined ripple radius to determine the ellipse's size and positioning, offering a unique, vertically stretched ripple effect.
     *
     * @param event
     *         The {@link MouseEvent} triggering the ripple effect, used to establish the ellipse's center.
     *
     * @return An {@link Ellipse} representing the vertical ripple effect.
     */
    @NotNull
    private Ellipse createVerticalEllipseRipple(MouseEvent event) {
        double radius = getRippleRadius();

        Ellipse ripple = new Ellipse(event.getX(), event.getY(), radius, radius * 2);
        applyRippleEffectProperties(ripple);
        return ripple;
    }

    /**
     * Applies common ripple effect properties to a given {@link Shape}. This method configures the fill color, stroke width, stroke color, and optionally a drop shadow effect based on the current
     * settings. It standardizes the appearance and styling of the ripple effect across different shapes, ensuring consistency in the visual presentation.
     *
     * @param ripple
     *         The {@link Shape} to which the ripple effect properties are applied.
     */
    private void applyRippleEffectProperties(Shape ripple) {
        Paint color = isRippleFillStateEnabled() ? rippleColor.getValue() : Color.TRANSPARENT;
        ripple.setFill(color);
        ripple.setStrokeWidth(rippleStrokeWidth.getValue());
        ripple.setStroke(rippleStrokeColor.getValue());
        if (isDropShadowEnabled()) {
            ripple.setEffect(new DropShadow(dropShadowBlurType.get(), dropShadowColor.get(), dropShadowRadius.get(), dropShadowSpread.get(), dropShadowOffsetX.get(), dropShadowOffsetY.get()));
        }
    }

    /**
     * Initializes the styleable properties for the ripple effect. This method is called during the construction of the RippleEffect object to set up the CSS metadata for all customizable properties.
     */
    private void initializeStyleableProperties() {
        rippleState = StyleableObjectPropertyFactory.<Status>builder()
                                                    .name("rippleState")
                                                    .bean(RippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-state"))
                                                    .defaultValue(DEFAULT_RIPPLE_STATE)
                                                    .build();

        rippleShape = StyleableObjectPropertyFactory.<RippleShape>builder()
                                                    .name("rippleShape")
                                                    .bean(RippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-shape"))
                                                    .defaultValue(DEFAULT_RIPPLE_SHAPE)
                                                    .build();

        rippleClipShape = StyleableObjectPropertyFactory.<RippleShape>builder()
                                                        .name("rippleClipShape")
                                                        .bean(RippleEffect.this)
                                                        .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-clip-shape"))
                                                        .defaultValue(DEFAULT_RIPPLE_CLIP_SHAPE)
                                                        .build();

        rippleColor = StyleableObjectPropertyFactory.<Color>builder()
                                                    .name("rippleColor")
                                                    .bean(RippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-color"))
                                                    .defaultValue(DEFAULT_RIPPLE_COLOR)
                                                    .build();

        rippleDuration = StyleableObjectPropertyFactory.<Duration>builder()
                                                       .name("rippleDuration")
                                                       .bean(RippleEffect.this)
                                                       .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-duration"))
                                                       .defaultValue(DEFAULT_RIPPLE_DURATION)
                                                       .build();

        rippleInterpolator = StyleableObjectPropertyFactory.<Interpolator>builder()
                                                           .name("rippleInterpolator")
                                                           .bean(RippleEffect.this)
                                                           .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-interpolator"))
                                                           .defaultValue(DEFAULT_RIPPLE_INTERPOLATOR)
                                                           .build();

        rippleFillState = StyleableObjectPropertyFactory.<Status>builder()
                                                        .name("rippleFillState")
                                                        .bean(RippleEffect.this)
                                                        .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-fill-state"))
                                                        .defaultValue(DEFAULT_RIPPLE_FILL_STATE)
                                                        .build();

        rippleRadius = StyleableDoublePropertyFactory.builder()
                                                     .name("rippleRadius")
                                                     .bean(RippleEffect.this)
                                                     .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-radius"))
                                                     .defaultValue(DEFAULT_RIPPLE_RADIUS)
                                                     .build();

        rippleStrokeWidth = StyleableDoublePropertyFactory.builder()
                                                          .name("rippleStrokeWidth")
                                                          .bean(RippleEffect.this)
                                                          .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-stroke-width"))
                                                          .defaultValue(DEFAULT_RIPPLE_STROKE_WIDTH)
                                                          .build();

        rippleStrokeColor = StyleableObjectPropertyFactory.<Color>builder()
                                                          .name("rippleStrokeColor")
                                                          .bean(RippleEffect.this)
                                                          .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-stroke-color"))
                                                          .defaultValue(DEFAULT_RIPPLE_STROKE_COLOR)
                                                          .build();

        rippleDirection = StyleableObjectPropertyFactory.<RippleDirection>builder()
                                                        .name("rippleDirection")
                                                        .bean(RippleEffect.this)
                                                        .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-direction"))
                                                        .defaultValue(DEFAULT_RIPPLE_DIRECTION)
                                                        .build();

        rippleFadeState = StyleableObjectPropertyFactory.<Status>builder()
                                                        .name("rippleFadeState")
                                                        .bean(RippleEffect.this)
                                                        .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-fade-state"))
                                                        .defaultValue(DEFAULT_RIPPLE_FADE_STATE)
                                                        .build();

        dropShadowBlurType = StyleableObjectPropertyFactory.<BlurType>builder()
                                                           .name("dropShadowBlurType")
                                                           .bean(RippleEffect.this)
                                                           .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-drop-shadow-blur-type"))
                                                           .defaultValue(DEFAULT_DROPSHADOW_BLUR_TYPE)
                                                           .build();

        dropShadowColor = StyleableObjectPropertyFactory.<Color>builder()
                                                        .name("dropShadowColor")
                                                        .bean(RippleEffect.this)
                                                        .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-drop-shadow-color"))
                                                        .defaultValue(DEFAULT_DROPSHADOW_COLOR)
                                                        .build();

        dropShadowRadius = StyleableDoublePropertyFactory.builder()
                                                         .name("dropShadowRadius")
                                                         .bean(RippleEffect.this)
                                                         .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-drop-shadow-radius"))
                                                         .defaultValue(DEFAULT_DROPSHADOW_RADIUS)
                                                         .build();

        dropShadowSpread = StyleableDoublePropertyFactory.builder()
                                                         .name("dropShadowSpread")
                                                         .bean(RippleEffect.this)
                                                         .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-drop-shadow-spread"))
                                                         .defaultValue(DEFAULT_DROPSHADOW_SPREAD)
                                                         .build();

        dropShadowOffsetX = StyleableDoublePropertyFactory.builder()
                                                          .name("dropShadowOffsetX")
                                                          .bean(RippleEffect.this)
                                                          .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-drop-shadow-offset-x"))
                                                          .defaultValue(DEFAULT_DROPSHADOW_OFFSET_X)
                                                          .build();

        dropShadowOffsetY = StyleableDoublePropertyFactory.builder()
                                                          .name("dropShadowOffsetY")
                                                          .bean(RippleEffect.this)
                                                          .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-drop-shadow-offset-y"))
                                                          .defaultValue(DEFAULT_DROPSHADOW_OFFSET_Y)
                                                          .build();

        dropShadowState = StyleableObjectPropertyFactory.<Status>builder()
                                                        .name("dropShadowState")
                                                        .bean(RippleEffect.this)
                                                        .cssMetaData(stylesManager.findCssMetaData("-fx-ripple-drop-shadow-state"))
                                                        .defaultValue(DEFAULT_DROPSHADOW_STATE)
                                                        .build();
    }

    /**
     * Retrieves the CSS metadata for the class.
     *
     * @return A list of {@code CssMetaData} objects representing the CSS properties that can be applied to this class.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return stylesManager.getCssMetaDataList();
    }

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {return getClassCssMetaData();}

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Retrieves the current state of the ripple effect.
     *
     * @return The current state of the ripple effect.
     */
    public Status getRippleState() {
        return rippleState.get();
    }

    /**
     * Returns the ripple state property of the RippleEffect.
     *
     * @return The ripple state property.
     */
    public StyleableObjectProperty<Status> rippleStateProperty() {
        return rippleState;
    }

    /**
     * Sets the state of the ripple effect.
     *
     * @param rippleState
     *         The state of the ripple effect. It can be either Status.ENABLED or Status.DISABLED.
     */
    public void setRippleState(Status rippleState) {
        this.rippleState.set(rippleState);
    }

    /**
     * Checks if the ripple effect is enabled.
     *
     * @return {@code true} if the ripple effect is enabled, {@code false} otherwise.
     */
    public boolean isRippleEnabled() {
        return getRippleState() == Status.ENABLED;
    }

    /**
     * Retrieves the ripple shape.
     *
     * @return The ripple shape.
     */
    public RippleShape getRippleShape() {
        return rippleShape.get();
    }

    /**
     * Retrieves the value of the rippleShape property.
     *
     * @return the rippleShape property value
     */
    public StyleableObjectProperty<RippleShape> rippleShapeProperty() {
        return rippleShape;
    }

    /**
     * Sets the ripple shape for the view.
     *
     * @param rippleShape
     *         the {@link RippleShape} to set
     */
    public void setRippleShape(RippleShape rippleShape) {
        this.rippleShape.set(rippleShape);
    }

    /**
     * Retrieves the ripple clip shape.
     *
     * @return The ripple clip shape.
     */
    public RippleShape getRippleClipShape() {
        return rippleClipShape.get();
    }

    /**
     * Gets the styleable object property for the ripple clip shape.
     *
     * @return The styleable object property for the ripple clip shape.
     */
    public StyleableObjectProperty<RippleShape> rippleClipShapeProperty() {
        return rippleClipShape;
    }

    /**
     * Set the shape of the ripple clip.
     *
     * @param rippleClipShape
     *         The shape of the ripple clip.
     */
    public void setRippleClipShape(RippleShape rippleClipShape) {
        this.rippleClipShape.set(rippleClipShape);
    }

    /**
     * Returns the ripple color used in the ripple effect.
     *
     * @return The ripple color.
     */
    public Color getRippleColor() {
        return rippleColor.get();
    }

    /**
     * Returns the ripple color property of the RippleEffect.
     *
     * @return The ripple color property.
     */
    public StyleableObjectProperty<Color> rippleColorProperty() {
        return rippleColor;
    }

    /**
     * Sets the color of the ripple effect.
     *
     * @param rippleColor
     *         The color to be set for the ripple effect.
     */
    public void setRippleColor(Color rippleColor) {
        this.rippleColor.set(rippleColor);
    }

    /**
     * Retrieves the duration of the ripple effect animation.
     *
     * @return The duration of the ripple effect animation.
     */
    public Duration getRippleDuration() {
        return rippleDuration.get();
    }

    /**
     * Returns the StyleableObjectProperty representing the ripple duration of the RippleEffect.
     *
     * @return The StyleableObjectProperty representing the ripple duration of the RippleEffect.
     */
    public StyleableObjectProperty<Duration> rippleDurationProperty() {
        return rippleDuration;
    }

    /**
     * Sets the duration of the ripple effect animation.
     *
     * @param rippleDuration
     *         The duration of the ripple effect animation.
     */
    public void setRippleDuration(Duration rippleDuration) {
        this.rippleDuration.set(rippleDuration);
    }

    /**
     * Retrieves the RippleInterpolator property value.
     *
     * @return The RippleInterpolator property value.
     */
    public Interpolator getRippleInterpolator() {
        return rippleInterpolator.get();
    }

    /**
     * Returns the styleable property for the ripple interpolator. The ripple interpolator defines the timing of the ripple effect animation.
     *
     * @return The styleable property for the ripple interpolator.
     */
    public StyleableObjectProperty<Interpolator> rippleInterpolatorProperty() {
        return rippleInterpolator;
    }

    /**
     * Sets the ripple interpolator for the RippleEffect.
     *
     * @param rippleInterpolator
     *         The interpolator to be set for the ripple effect.
     */
    public void setRippleInterpolator(Interpolator rippleInterpolator) {
        this.rippleInterpolator.set(rippleInterpolator);
    }

    /**
     * Retrieves the ripple fill state.
     *
     * @return the current ripple fill state
     */
    public Status getRippleFillState() {
        return rippleFillState.get();
    }

    /**
     * Retrieves the ripple fill state property which represents the fill state of the ripple effect.
     *
     * @return the ripple fill state property
     */
    public StyleableObjectProperty<Status> rippleFillStateProperty() {
        return rippleFillState;
    }

    /**
     * Set the fill state of the ripple effect.
     *
     * @param rippleFillState
     *         The fill state of the ripple effect.
     */
    public void setRippleFillState(Status rippleFillState) {
        this.rippleFillState.set(rippleFillState);
    }

    /**
     * Determines whether the ripple fill state is enabled.
     *
     * @return {@code true} if the ripple fill state is enabled , {@code false} otherwise.
     */
    public boolean isRippleFillStateEnabled() {
        return getRippleFillState() == Status.ENABLED;
    }

    /**
     * Retrieves the current ripple radius used in the ripple effect.
     *
     * @return The current ripple radius.
     */
    public double getRippleRadius() {
        return rippleRadius.get();
    }

    /**
     * Retrieves the ripple radius property of the RippleEffect. The ripple radius represents the radius of the ripple circle in pixels.
     *
     * @return The ripple radius property.
     */
    public StyleableDoubleProperty rippleRadiusProperty() {
        return rippleRadius;
    }

    /**
     * Sets the radius of the ripple effect.
     *
     * @param rippleRadius
     *         The radius of the ripple effect to be set.
     */
    public void setRippleRadius(double rippleRadius) {
        this.rippleRadius.set(rippleRadius);
    }

    /**
     * Returns the width of the ripple stroke.
     *
     * @return the width of the ripple stroke
     */
    public double getRippleStrokeWidth() {
        return rippleStrokeWidth.get();
    }

    /**
     * Gets the ripple stroke width property of this object.
     *
     * @return the ripple stroke width property
     */
    public StyleableDoubleProperty rippleStrokeWidthProperty() {
        return rippleStrokeWidth;
    }

    /**
     * Set the width of the stroke for the ripple effect.
     *
     * @param rippleStrokeWidth
     *         the desired stroke width for the ripple effect
     */
    public void setRippleStrokeWidth(double rippleStrokeWidth) {
        this.rippleStrokeWidth.set(rippleStrokeWidth);
    }

    /**
     * Returns the ripple stroke color.
     *
     * @return the ripple stroke color
     */
    public Color getRippleStrokeColor() {
        return rippleStrokeColor.get();
    }

    /**
     * Retrieves the ripple stroke color property of the object.
     *
     * @return the StyleableObjectProperty representing the ripple stroke color
     */
    public StyleableObjectProperty<Color> rippleStrokeColorProperty() {
        return rippleStrokeColor;
    }

    /**
     * Sets the color of the stroke for the ripple effect.
     *
     * @param rippleStrokeColor
     *         the color of the ripple stroke
     */
    public void setRippleStrokeColor(Color rippleStrokeColor) {
        this.rippleStrokeColor.set(rippleStrokeColor);
    }

    /**
     * Determines if the ripple effect is set to move outward from the center of the UI element.
     *
     * @return {@code true} if the ripple effect is set to move outward, {@code false} otherwise.
     */
    public boolean isRippleDirectionOut() {
        return getRippleDirection() == RippleDirection.OUT;
    }

    /**
     * Checks if the current ripple direction is inward.
     *
     * @return {@code true} if the ripple direction is inward, {@code false} otherwise.
     */
    public boolean isRippleDirectionIn() {
        return getRippleDirection() == RippleDirection.IN;
    }

    /**
     * Retrieves the current direction of the ripple effect.
     *
     * @return The current direction of the ripple effect. It can be either {@code RippleDirection.IN} or {@code RippleDirection.OUT}.
     *
     * @see RippleDirection
     */
    public RippleDirection getRippleDirection() {
        return rippleDirection.get();
    }

    /**
     * Returns the ripple direction property of the RippleEffect.
     *
     * <p>The ripple direction property specifies the direction of the ripple effect
     * in animations or UI interactions. It can be used to control whether the ripple effect should move inward towards the center of the UI element or outward from the center. The direction can
     * enhance the visual cue provided to users, indicating the type of action or transition occurring.</p>
     *
     * @return The ripple direction property.
     *
     * @see RippleDirection
     * @see RippleEffect
     */
    public StyleableObjectProperty<RippleDirection> rippleDirectionProperty() {
        return rippleDirection;
    }

    /**
     * Sets the direction of the ripple effect.
     *
     * @param rippleDirection
     *         The direction of the ripple effect. Valid values are RippleDirection.IN and RippleDirection.OUT.
     */
    public void setRippleDirection(RippleDirection rippleDirection) {
        this.rippleDirection.set(rippleDirection);
    }

    /**
     * Retrieves the target node to which the ripple effect will be applied.
     *
     * @return The target node.
     */
    public Region getTargetNode() {
        return targetNode;
    }

    /**
     * Retrieves the current status of the ripple fade.
     *
     * @return The current status of the ripple fade.
     */
    public Status getRippleFadeState() {
        return rippleFadeState.get();
    }

    /**
     * Gets the ripple fade property.
     *
     * @return the ripple fade property
     */
    public StyleableObjectProperty<Status> rippleFadeStateProperty() {
        return rippleFadeState;
    }

    /**
     * Sets the ripple fade status.
     *
     * @param rippleFadeState
     *         the ripple fade status to set
     */
    public void setRippleFadeState(Status rippleFadeState) {
        this.rippleFadeState.set(rippleFadeState);
    }

    /**
     * Returns whether the ripple fade effect is enabled.
     *
     * @return {@code true} if the ripple fade effect is enabled, otherwise {@code false}
     */
    public boolean isRippleFadeEnabled() {
        return getRippleFadeState() == Status.ENABLED;
    }

    /**
     * Retrieves the type of blur applied to the drop shadow effect.
     *
     * @return the BlurType of the drop shadow effect
     */
    public BlurType getDropShadowBlurType() {
        return dropShadowBlurType.get();
    }

    /**
     * Returns the property representing the blur type of the drop shadow effect.
     *
     * @return the property representing the blur type of the drop shadow effect
     */
    public StyleableObjectProperty<BlurType> dropShadowBlurTypeProperty() {
        return dropShadowBlurType;
    }

    /**
     * Sets the blur type for the drop shadow effect.
     *
     * @param dropShadowBlurType
     *         the blur type to be set
     */
    public void setDropShadowBlurType(BlurType dropShadowBlurType) {
        this.dropShadowBlurType.set(dropShadowBlurType);
    }

    /**
     * Retrieves the drop shadow color.
     *
     * @return The drop shadow color.
     */
    public Color getDropShadowColor() {
        return dropShadowColor.get();
    }

    /**
     * Returns the drop shadow color property of this object.
     *
     * @return the drop shadow color property
     */
    public StyleableObjectProperty<Color> dropShadowColorProperty() {
        return dropShadowColor;
    }

    /**
     * Sets the drop shadow color for this object.
     *
     * @param dropShadowColor
     *         the color to set as the drop shadow color
     */
    public void setDropShadowColor(Color dropShadowColor) {
        this.dropShadowColor.set(dropShadowColor);
    }

    /**
     * Retrieves the drop shadow radius value.
     *
     * @return The drop shadow radius.
     */
    public double getDropShadowRadius() {
        return dropShadowRadius.get();
    }

    /**
     * Retrieves the dropShadowRadius property.
     *
     * @return The dropShadowRadius property
     */
    public StyleableDoubleProperty dropShadowRadiusProperty() {
        return dropShadowRadius;
    }

    /**
     * Sets the drop shadow radius.
     *
     * @param dropShadowRadius
     *         the radius of the drop shadow
     */
    public void setDropShadowRadius(double dropShadowRadius) {
        this.dropShadowRadius.set(dropShadowRadius);
    }

    /**
     * Retrieves the spread radius of the drop shadow effect applied to this object.
     *
     * @return The spread radius as a double value.
     */
    public double getDropShadowSpread() {
        return dropShadowSpread.get();
    }

    /**
     * Returns the StyleableDoubleProperty for the 'dropShadowSpread' property.
     *
     * @return the StyleableDoubleProperty for the 'dropShadowSpread' property
     */
    public StyleableDoubleProperty dropShadowSpreadProperty() {
        return dropShadowSpread;
    }

    /**
     * Sets the spread value for the drop shadow effect.
     *
     * @param dropShadowSpread
     *         the spread value of the drop shadow effect
     */
    public void setDropShadowSpread(double dropShadowSpread) {
        this.dropShadowSpread.set(dropShadowSpread);
    }

    /**
     * Retrieves the value of the drop shadow offset along the x-axis.
     *
     * @return The value of the drop shadow offset along the x-axis.
     */
    public double getDropShadowOffsetX() {
        return dropShadowOffsetX.get();
    }

    /**
     * Returns the drop shadow offset X property of the node.
     *
     * @return the drop shadow offset X property.
     */
    public StyleableDoubleProperty dropShadowOffsetXProperty() {
        return dropShadowOffsetX;
    }

    /**
     * Sets the offset of the drop shadow effect along the x-axis.
     *
     * @param dropShadowOffsetX
     *         the offset of the drop shadow effect along the x-axis
     */
    public void setDropShadowOffsetX(double dropShadowOffsetX) {
        this.dropShadowOffsetX.set(dropShadowOffsetX);
    }

    /**
     * Returns the offset in the Y direction for the drop shadow effect.
     *
     * @return the offset in the Y direction for the drop shadow effect
     */
    public double getDropShadowOffsetY() {
        return dropShadowOffsetY.get();
    }

    /**
     * Returns the JavaFX property representing the offset of the drop shadow effect in the y-axis direction.
     *
     * @return The JavaFX property representing the drop shadow offset in the y-axis.
     */
    public StyleableDoubleProperty dropShadowOffsetYProperty() {
        return dropShadowOffsetY;
    }

    /**
     * Sets the offset of the drop shadow effect along the Y-axis.
     *
     * @param dropShadowOffsetY
     *         the offset value along the Y-axis
     */
    public void setDropShadowOffsetY(double dropShadowOffsetY) {
        this.dropShadowOffsetY.set(dropShadowOffsetY);
    }

    /**
     * Retrieves the current drop shadow state.
     *
     * @return The drop shadow state.
     */
    public Status getDropShadowState() {
        return dropShadowState.get();
    }

    /**
     * Retrieves the drop shadow state property of the object.
     *
     * @return the drop shadow state property
     */
    public StyleableObjectProperty<Status> dropShadowStateProperty() {
        return dropShadowState;
    }

    /**
     * Sets the drop shadow state for the object.
     *
     * @param dropShadowState
     *         the new drop shadow state to be set
     */
    public void setDropShadowState(Status dropShadowState) {
        this.dropShadowState.set(dropShadowState);
    }

    /**
     * Determines if the drop shadow is enabled.
     *
     * @return {@code true} if the drop shadow is enabled, {@code false} otherwise.
     */
    public boolean isDropShadowEnabled() {
        return getDropShadowState() == Status.ENABLED;
    }

    //endregion Getters and Setters
}