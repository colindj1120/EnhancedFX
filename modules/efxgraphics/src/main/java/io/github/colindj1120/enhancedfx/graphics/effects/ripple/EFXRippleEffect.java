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
package io.github.colindj1120.enhancedfx.graphics.effects.ripple;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableDoubleProperty;
import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.enums.EFXState;
import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.region.RegionConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customregion.CustomRegionConfigurator;
import io.github.colindj1120.enhancedfx.graphics.animation.EFXAnimationManager;
import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleDirection;
import io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleShape;
import io.github.colindj1120.enhancedfx.graphics.shapes.AsymmetricRoundedRectangle;
import io.github.colindj1120.enhancedfx.utils.EFXAnimationUtils;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import io.github.colindj1120.enhancedfx.utils.EFXPropertyUtils;
import io.github.colindj1120.enhancedfx.utils.converters.styleconverters.DoubleStyleConverter;
import io.github.colindj1120.enhancedfx.utils.converters.styleconverters.InterpolatorStyleConverter;
import io.github.colindj1120.enhancedfx.utils.exceptions.RippleEffectException;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.converter.ColorConverter;
import javafx.css.converter.DurationConverter;
import javafx.css.converter.EnumConverter;
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
import java.util.function.Supplier;

import static io.github.colindj1120.enhancedfx.graphics.effects.base.EFXRippleDefaults.*;

/**
 * {@code EFXRippleEffect} enhances JavaFX {@link Region} components by adding a customizable ripple effect, simulating material design-like interaction feedback.
 *
 * <p>This effect dynamically applies visual cues in response to mouse events, indicating an interactive area to the user. It integrates seamlessly with JavaFX's styling and animation framework, allowing for
 * extensive customization through both Java code and CSS.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Shape Variability</b>: Choose from multiple shapes (rectangle, circle, ellipse) for the ripple effect, enabling consistency with the component's design.</li>
 *     <li><b>Color Customization</b>: Define the ripple's color to match or contrast with the UI theme, enhancing visual appeal or signaling interactivity.</li>
 *     <li><b>Animation Tweaking</b>: Adjust animation duration and easing to align with the application's interaction speed and style.</li>
 *     <li><b>Advanced Effects</b>: Apply optional drop shadows to the ripple, lending depth and prominence on activation.</li>
 *     <li><b>Dynamic Response</b>: The effect reacts to the size and shape of the target region, ensuring that the visual feedback is always appropriately scaled.</li>
 *     <li><b>CSS Integration</b>: Leverage CSS for theming and styling, providing a familiar and powerful tool for designers.</li>
 *     <li><b>Performance Optimized</b>: Designed with performance in mind, ensuring minimal overhead on UI responsiveness.</li>
 * </ul>
 *
 * <h2>Key Properties and Methods</h2>
 * <ul>
 *     <li>{@link #create(Region)}: Static factory method to instantiate and apply the effect to a {@link Region}.</li>
 *     <li>{@link #createRippleClip()}: Creates the clip shape for the ripple effect.</li>
 *     <li>{@link #createAndAnimateRipple(MouseEvent)}: Creates the ripple animation and starts the animation</li>
 *     <li>{@link #createRippleEffect(MouseEvent)}: Creates the ripple effect for the desired shape</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 *
 * <h3>Basic Application</h3>
 * <pre>
 * {@code
 *     Region button = new Region();
 *     EFXRippleEffect.applyTo(button);
 *     // Optionally, customize the effect further
 *     button.getStyleClass().add("my-custom-button");
 * }
 * </pre>
 *
 * <h3>Customization through Code</h3>
 * <pre>
 * {@code
 *     EFXRippleEffect rippleEffect = EFXRippleEffect.create(button);
 *     rippleEffect.setRippleColor(Color.BLUE);
 *     rippleEffect.setRippleDuration(Duration.millis(300));
 *     rippleEffect.setRippleInterpolator(Interpolator.EASE_OUT);
 * }
 * </pre>
 *
 * <h3>Styling with CSS</h3>
 * The ripple effect's properties can be styled using CSS, offering an alternative to programmatic customization.
 * <pre>
 *  {@code
 *     .my-custom-button {
 *         -efx-ripple-color: #3498db;
 *         -efx-ripple-duration: 300ms;
 *         -efx-ripple-interpolator: ease-out;
 *     }
 * }
 * </pre>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Region
 * @see MouseEvent
 * @see Interpolator
 * @see DropShadow
 * @see Styleable
 */
public class EFXRippleEffect extends Region {
    private static final String                     RIPPLE_STYLE  = "ripple-effect";
    private static final StyleablePropertiesManager stylesManager = new StyleablePropertiesManager(Region.getClassCssMetaData());

    private final EFXAnimationManager efxAnimationManager = new EFXAnimationManager();
    private final Region              targetNode;

    private EFXStyleableObjectProperty<EFXState>           rippleState;
    private EFXStyleableObjectProperty<EFXRippleShape>     rippleShape;
    private EFXStyleableObjectProperty<EFXRippleShape>     rippleClipShape;
    private EFXStyleableObjectProperty<Color>              rippleColor;
    private EFXStyleableObjectProperty<Duration>           rippleDuration;
    private EFXStyleableObjectProperty<Interpolator>       rippleInterpolator;
    private EFXStyleableObjectProperty<EFXState>           rippleFillState;
    private EFXStyleableDoubleProperty                     rippleRadius;
    private EFXStyleableObjectProperty<Color>              rippleStrokeColor;
    private EFXStyleableDoubleProperty                     rippleStrokeWidth;
    private EFXStyleableObjectProperty<EFXRippleDirection> rippleDirection;
    private EFXStyleableObjectProperty<EFXState>           rippleFadeState;
    private EFXStyleableObjectProperty<BlurType>           dropShadowBlurType;
    private EFXStyleableObjectProperty<Color>              dropShadowColor;
    private EFXStyleableDoubleProperty                     dropShadowRadius;
    private EFXStyleableDoubleProperty                     dropShadowSpread;
    private EFXStyleableDoubleProperty                     dropShadowOffsetX;
    private EFXStyleableDoubleProperty                     dropShadowOffsetY;
    private EFXStyleableObjectProperty<EFXState>           dropShadowState;

    static {
        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, EFXState>create()
                                               .property("-efx-ripple-state")
                                               .converter(EnumConverter.getEnumConverter(EFXState.class))
                                               .initialValue(DEFAULT_RIPPLE_EFX_STATE)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleState))
                                               .propertyGetterFunction(node -> node.rippleState));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, EFXRippleShape>create()
                                               .property("-efx-ripple-shape")
                                               .converter(EnumConverter.getEnumConverter(EFXRippleShape.class))
                                               .initialValue(DEFAULT_RIPPLE_SHAPE)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleShape))
                                               .propertyGetterFunction(node -> node.rippleShape));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, EFXRippleShape>create()
                                               .property("-efx-ripple-clip-shape")
                                               .converter(EnumConverter.getEnumConverter(EFXRippleShape.class))
                                               .initialValue(DEFAULT_RIPPLE_CLIP_SHAPE)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleClipShape))
                                               .propertyGetterFunction(node -> node.rippleClipShape));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Color>create()
                                               .property("-efx-ripple-color")
                                               .converter(ColorConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_COLOR)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleColor))
                                               .propertyGetterFunction(node -> node.rippleColor));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Duration>create()
                                               .property("-efx-ripple-duration")
                                               .converter(DurationConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_DURATION)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleDuration))
                                               .propertyGetterFunction(node -> node.rippleDuration));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Interpolator>create()
                                               .property("-efx-ripple-interpolator")
                                               .converter(InterpolatorStyleConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_INTERPOLATOR)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleInterpolator))
                                               .propertyGetterFunction(node -> node.rippleInterpolator));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, EFXState>create()
                                               .property("-efx-ripple-fill-state")
                                               .converter(EnumConverter.getEnumConverter(EFXState.class))
                                               .initialValue(DEFAULT_RIPPLE_FILL_EFX_STATE)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleFillState))
                                               .propertyGetterFunction(node -> node.rippleFillState));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Double>create()
                                               .property("-efx-ripple-radius")
                                               .converter(DoubleStyleConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_RADIUS)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleRadius))
                                               .propertyGetterFunction(node -> node.rippleRadius));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Double>create()
                                               .property("-efx-ripple-stroke-width")
                                               .converter(DoubleStyleConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_STROKE_WIDTH)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleStrokeWidth))
                                               .propertyGetterFunction(node -> node.rippleStrokeWidth));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Color>create()
                                               .property("-efx-ripple-stroke-color")
                                               .converter(ColorConverter.getInstance())
                                               .initialValue(DEFAULT_RIPPLE_STROKE_COLOR)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleStrokeColor))
                                               .propertyGetterFunction(node -> node.rippleStrokeColor));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, EFXRippleDirection>create()
                                               .property("-efx-ripple-direction")
                                               .converter(EnumConverter.getEnumConverter(EFXRippleDirection.class))
                                               .initialValue(DEFAULT_RIPPLE_DIRECTION)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleDirection))
                                               .propertyGetterFunction(node -> node.rippleDirection));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, EFXState>create()
                                               .property("-efx-ripple-fade-state")
                                               .converter(EnumConverter.getEnumConverter(EFXState.class))
                                               .initialValue(DEFAULT_RIPPLE_FADE_EFX_STATE)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.rippleFadeState))
                                               .propertyGetterFunction(node -> node.rippleFadeState));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, BlurType>create()
                                               .property("-efx-ripple-drop-shadow-blur-type")
                                               .converter(EnumConverter.getEnumConverter(BlurType.class))
                                               .initialValue(DEFAULT_DROPSHADOW_BLUR_TYPE)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.dropShadowBlurType))
                                               .propertyGetterFunction(node -> node.dropShadowBlurType));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Color>create()
                                               .property("-efx-ripple-drop-shadow-color")
                                               .converter(ColorConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_COLOR)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.dropShadowColor))
                                               .propertyGetterFunction(node -> node.dropShadowColor));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Double>create()
                                               .property("-efx-ripple-drop-shadow-radius")
                                               .converter(DoubleStyleConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_RADIUS)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.dropShadowRadius))
                                               .propertyGetterFunction(node -> node.dropShadowRadius));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Double>create()
                                               .property("-efx-ripple-drop-shadow-spread")
                                               .converter(DoubleStyleConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_SPREAD)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.dropShadowSpread))
                                               .propertyGetterFunction(node -> node.dropShadowSpread));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Double>create()
                                               .property("-efx-ripple-drop-shadow-offset-x")
                                               .converter(DoubleStyleConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_OFFSET_X)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.dropShadowOffsetX))
                                               .propertyGetterFunction(node -> node.dropShadowOffsetX));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, Double>create()
                                               .property("-efx-ripple-drop-shadow-offset-y")
                                               .converter(DoubleStyleConverter.getInstance())
                                               .initialValue(DEFAULT_DROPSHADOW_OFFSET_Y)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.dropShadowOffsetY))
                                               .propertyGetterFunction(node -> node.dropShadowOffsetY));

        stylesManager.addCssMetaData(CssFactory.<EFXRippleEffect, EFXState>create()
                                               .property("-efx-ripple-drop-shadow-state")
                                               .converter(EnumConverter.getEnumConverter(EFXState.class))
                                               .initialValue(DEFAULT_DROPSHADOW_EFX_STATE)
                                               .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.dropShadowState))
                                               .propertyGetterFunction(node -> node.dropShadowState));
    }

    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Creates a new instance of {@code EFXRippleEffect} associated with the specified {@link Region}.
     *
     * <p>This static factory method provides a convenient way to instantiate {@code EFXRippleEffect} objects. The created effect will be applied to the given {@code targetNode}, enabling ripple effects on
     * mouse clicks within that {@code Region}.</p>
     *
     * @param targetNode
     *         The {@link Region} to which the ripple effect will be applied. This cannot be {@code null}.
     *
     * @return A new instance of {@code EFXRippleEffect} initialized with the target node.
     *
     * @throws IllegalArgumentException
     *         if {@code targetNode} is {@code null}.
     */
    public static EFXRippleEffect create(Region targetNode) {
        return new EFXRippleEffect(targetNode);
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@code EFXRippleEffect} for the given target node.
     *
     * <p>This constructor initializes the ripple effect, setting up the necessary style, event filters, and listeners to ensure that the ripple animation is displayed correctly on the target region.</p>
     *
     * <p>It ensures the target node is not null with {@link EFXObjectUtils#isNotNull(Object, Supplier)} and sets the {@code pickOnBounds} property to false to ensure that only visible parts of the target node
     * can trigger the ripple effect. Additionally, it applies a specific style class to the effect, initializes all styleable properties, and sets up listeners to properly handle changes to the target node's
     * dimensions, background, and the ripple clip shape.</p>
     *
     * <p>This constructor is private to enforce the use of the static factory method {@link #create(Region)} for object creation, promoting a consistent and controlled way of instantiating
     * {@code EFXRippleEffect}.</p>
     *
     * @param targetNode
     *         The {@link Region} to which this ripple effect is to be applied. Must not be {@code null}.
     *
     * @throws IllegalArgumentException
     *         if {@code targetNode} is {@code null}, ensuring that a valid region is provided.
     */
    private EFXRippleEffect(Region targetNode) {
        super();
        EFXObjectUtils.isNotNull(targetNode, () -> "targetNode cannot be null when creating a ripple effect");
        this.targetNode = targetNode;
        this.initializeStyleableProperties();

        InvalidationListener clipInvalidationListener = ignored -> this.setClip(createRippleClip());

        CustomRegionConfigurator.create(this)
                                .setPickOnBounds(false)
                                .setAllStyleClasses(RIPPLE_STYLE)
                                .addObjectPropertyInvalidationListener(rippleClipShape, clipInvalidationListener);

        RegionConfigurator.create(targetNode)
                          .addEventFilter(MouseEvent.MOUSE_CLICKED, this::createAndAnimateRipple)
                          .addWidthInvalidationListener(clipInvalidationListener)
                          .addHeightInvalidationListener(clipInvalidationListener)
                          .addBackgroundInvalidationListener(clipInvalidationListener);
    }

    //endregion Constructor

    //region Ripple Clip Creation
    //*****************************************************************
    // Ripple Clip Creation
    //*****************************************************************

    /**
     * Creates a clip shape based on the currently set ripple effect shape.
     *
     * <p>The method uses the rippleClipShape property to determine which specific shape to create, supporting various shapes like rectangle, rounded rectangle (both uniform and asymmetric), circle, and
     * ellipse.</p>
     *
     * @return The created {@link Shape} that will be used as a clip.
     *
     * @throws RippleEffectException
     *         if the ripple shape is null or not recognized.
     */
    private Shape createRippleClip() {
        return Optional.ofNullable(rippleClipShape.get())
                       .map(shape -> switch (shape) {
                           case EFXRippleShape.RECTANGLE -> createRectangleClip();
                           case EFXRippleShape.UNIFORM_ROUNDED_RECTANGLE -> createRoundedRectangleClip();
                           case EFXRippleShape.ASYMMETRIC_ROUNDED_RECTANGLE -> createAsymmetricRoundedRectangle();
                           case EFXRippleShape.CIRCLE -> createCircleClip();
                           case EFXRippleShape.VERTICAL_ELLIPSE, EFXRippleShape.HORIZONTAL_ELLIPSE -> createEllipseClip();
                       })
                       .orElseThrow(() -> new RippleEffectException("Ripple Shape cannot be null when creating a ripple clip"));

    }

    /**
     * Creates a clip shape based on the currently set ripple effect shape.
     *
     * <p>The method uses the rippleClipShape property to determine which specific shape to create, supporting various shapes like rectangle, rounded rectangle (both uniform and asymmetric), circle, and
     * ellipse.</p>
     *
     * @return The created {@link Shape} that will be used as a clip.
     *
     * @throws RippleEffectException
     *         if the ripple shape is null or not recognized.
     */
    private Rectangle createRectangleClip() {
        return new Rectangle(targetNode.getWidth(), targetNode.getHeight());
    }

    /**
     * Creates a uniformly rounded rectangle clip. The corner radii are determined by examining the target node's background fills, using the first fill's radii as the basis for the clip's rounded corners.
     *
     * @return A {@link Rectangle} with rounded corners for clipping.
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
     * Creates an asymmetrically rounded rectangle based on the target node's dimensions and corner radii.
     *
     * <p>This allows for different corner radii across the rectangle, providing more customization for the clip shape.</p>
     *
     * @return An instance of {@link AsymmetricRoundedRectangle} for clipping.
     */
    private AsymmetricRoundedRectangle createAsymmetricRoundedRectangle() {
        return AsymmetricRoundedRectangle.create(targetNode.getWidth(), targetNode.getHeight(), getCornerRadii(targetNode));
    }

    /**
     * Retrieves the corner radii of the target node's first background fill. If the target node has no background or the background has no fills, it returns {@link CornerRadii#EMPTY}.
     *
     * @param targetNode
     *         The {@link Region} from which to extract corner radii.
     *
     * @return The corner radii of the target node's first background fill.
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
     * Creates a circle clip centered on the target node.
     *
     * <p>The radius is set to half the smaller of the node's width or height. This method ensures that the created clip is a perfect circle by throwing an exception if the target node's width and height are
     * not equal.</p>
     *
     * @return A {@link Circle} shaped clip.
     *
     * @throws RippleEffectException
     *         if the target node's width and height are not equal.
     */
    private Circle createCircleClip() {
        if (targetNode.getWidth() != targetNode.getHeight()) {
            throw new RippleEffectException("Clip Shape should be ellipse not circle. Target node width and height are not equal");
        }
        double radius = Math.min(targetNode.getWidth(), targetNode.getHeight()) / 2;
        return new Circle(radius);
    }

    /**
     * Creates an ellipse clip based on the target node's dimensions.
     *
     * <p>The horizontal and vertical radii are set to half the width and height of the target node, respectively, allowing the ellipse to exactly fit the node's bounds.</p>
     *
     * @return An {@link Ellipse} shaped clip.
     */
    private Ellipse createEllipseClip() {
        double horizontalRadius = targetNode.getWidth() / 2;
        double verticalRadius   = targetNode.getHeight() / 2;
        return new Ellipse(horizontalRadius, verticalRadius);
    }

    //endregion Ripple Clip Creation

    //region Ripple Animation Creation
    //*****************************************************************
    // Ripple Animation Creation
    //*****************************************************************

    /**
     * Creates and animates a ripple effect in response to a {@link MouseEvent}.
     *
     * <p>This method first checks if the ripple effect is enabled through {@code isRippleEnabled()}. If so, it generates a ripple shape at the event location and adds it to the children of this component. The
     * ripple animation is configured based on the properties defined for this effect, such as end scale, end opacity, and the interpolator to use. The animation makes the ripple expand and fade out (if fade is
     * enabled) over the duration specified by {@code getRippleDuration()}.</p>
     *
     * <p>Once the animation is created, it is managed and played by the {@code efxAnimationManager}, ensuring that the ripple effect is properly timed and cleaned up after completion. The method consumes the
     * mouse event to prevent further handling.</p>
     *
     * @param event
     *         The {@link MouseEvent} that triggered the ripple creation.
     */
    private void createAndAnimateRipple(MouseEvent event) {
        if (isRippleEnabled()) {
            Shape ripple = createRippleEffect(event);
            this.getChildren()
                .add(ripple);

            double       endScale     = getEndScale();
            double       endOpacity   = isRippleFadeEnabled() ? 0 : 1;
            Interpolator interpolator = getRippleInterpolator();
            //@formatter:off
            Timeline rippleTimeline = EFXAnimationUtils.createAnimation(EFXAnimationUtils.createKeyFrame(getRippleDuration(), e -> this.getChildren().remove(ripple),
                                                                    EFXAnimationUtils.createKeyValue(ripple.scaleXProperty(), endScale, interpolator),
                                                                    EFXAnimationUtils.createKeyValue(ripple.scaleYProperty(), endScale, interpolator),
                                                                    EFXAnimationUtils.createKeyValue(ripple.opacityProperty(), endOpacity, interpolator)));
            //@formatter:on

            String rippleKey = "Ripple" + ripple.hashCode();
            efxAnimationManager.addAnimation(rippleKey, rippleTimeline);
            efxAnimationManager.playAnimation(rippleKey);
            efxAnimationManager.setOnFinished(rippleKey, e -> efxAnimationManager.removeAnimation(rippleKey));
        }
        event.consume();
    }

    /**
     * Calculates the end scale factor for the ripple animation.
     *
     * <p>This factor determines how much the ripple should scale from its origin point to cover the entire target node. The calculation involves finding the diagonal distance from the event source to the
     * furthest corner of the target node, which ensures the ripple can fully expand beyond the visible bounds of the node.</p>
     *
     * <p>If the ripple direction is set to expand outwards ({@code isRippleDirectionOut()} returns true), the end scale is determined by this maximum distance. Otherwise, a default minimal scale factor is
     * returned, useful for inward animations or specific stylistic effects.</p>
     *
     * @return The calculated end scale factor for the ripple effect.
     */
    private double getEndScale() {
        // Calculate the maximum distance from the event point to the corners of the target node
        double maxX = targetNode.prefWidth(-1);
        double maxY = targetNode.prefHeight(-1);
        // The diagonal distance will be the furthest point the ripple needs to cover
        double maxDistance = Math.sqrt(maxX * maxX + maxY * maxY);

        // Calculate the end scale factor based on the maximum distance and the initial radius of the ripple
        return isRippleDirectionOut() ? maxDistance : 0.1; // Multiply by 2 for both directions
    }

    //endregion Ripple Animation Creation

    //region Ripple Creation
    //*****************************************************************
    // Ripple Creation
    //*****************************************************************

    /**
     * Creates a ripple effect based on the specified {@link MouseEvent} and the current ripple shape.
     *
     * <p>This method determines the shape of the ripple effect by inspecting the {@code rippleShape} property, creating a shape-specific ripple at the location of the event. Supported shapes include uniform
     * and asymmetric rounded rectangles, rectangle, circle, and both horizontal and vertical ellipses. The method throws a {@link RippleEffectException} if the ripple shape is not set.</p>
     *
     * @param event
     *         The {@link MouseEvent} that triggers the ripple effect.
     *
     * @return A {@link Shape} representing the created ripple effect, not null.
     *
     * @throws RippleEffectException
     *         if the ripple shape is null.
     */
    @NotNull
    private Shape createRippleEffect(MouseEvent event) {
        return Optional.ofNullable(rippleShape.get())
                       .map(shape -> switch (shape) {
                           case EFXRippleShape.UNIFORM_ROUNDED_RECTANGLE -> createRoundedRectangleRipple(event);
                           case EFXRippleShape.ASYMMETRIC_ROUNDED_RECTANGLE -> createAsymmetricRoundedRectangleRipple(event);
                           case EFXRippleShape.RECTANGLE -> createRectangleRipple(event);
                           case EFXRippleShape.CIRCLE -> createCircleRipple(event);
                           case EFXRippleShape.HORIZONTAL_ELLIPSE -> createHorizontalEllipseRipple(event);
                           case EFXRippleShape.VERTICAL_ELLIPSE -> createVerticalEllipseRipple(event);
                       })
                       .orElseThrow(() -> new RippleEffectException("Ripple Shape cannot be null when creating a ripple effect"));
    }

    /**
     * Creates a rounded rectangle ripple at the location of the provided mouse event.
     *
     * <p>The corner radii of the ripple are determined by the target node's current corner radii, ensuring the ripple complements the target's appearance. The size of the ripple is based on the
     * {@code rippleRadius}, and it is positioned such that the event location is at its center. Ripple effect properties are then applied to the created shape.</p>
     *
     * @param event
     *         The {@link MouseEvent} that triggers the creation of the ripple.
     *
     * @return A {@link Rectangle} representing the ripple, with rounded corners, not null.
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
     * Creates an asymmetrically rounded rectangle ripple based on the mouse event location.
     *
     * <p>The ripple's corner radii are adjusted from the target node's corner radii to fit the ripple's aesthetics. This method calculates the ripple's dimensions using {@code rippleRadius} and positions it
     * so that the mouse event's location is at the center of the ripple. Ripple effect properties are applied to enhance its visual appearance.</p>
     *
     * @param event
     *         The {@link MouseEvent} that triggers the ripple.
     *
     * @return An {@link AsymmetricRoundedRectangle} that represents the created ripple, not null.
     */
    @NotNull
    private AsymmetricRoundedRectangle createAsymmetricRoundedRectangleRipple(MouseEvent event) {
        CornerRadii radii         = getCornerRadii(targetNode);
        CornerRadii adjustedRadii = getAdjustedRadii(radii);
        double      rippleRadius  = getRippleRadius();
        double      width         = rippleRadius * 2;
        double      height        = rippleRadius * 2;

        AsymmetricRoundedRectangle ripple = AsymmetricRoundedRectangle.create(event.getX() - rippleRadius, event.getY() - rippleRadius, width, height, adjustedRadii);

        applyRippleEffectProperties(ripple);

        return ripple;
    }

    /**
     * Adjusts the corner radii for the ripple effect based on the original radii of the target node's background and the ripple radius.
     *
     * <p>This adjustment ensures that the ripple's corners are proportionally rounded relative to its size, maintaining a consistent design aesthetic. The method calculates the multiplier based on the
     * {@code rippleRadius} and applies it to each corner radius of the original {@link CornerRadii}.</p>
     *
     * @param originalRadii
     *         The original corner radii of the target node's background.
     *
     * @return A new {@link CornerRadii} object with adjusted corner radii for the ripple effect.
     */
    private CornerRadii getAdjustedRadii(CornerRadii originalRadii) {
        //@formatter:off
        double multiplier = getRippleRadius()/100.0;
        return new CornerRadii(originalRadii.getTopLeftHorizontalRadius() * multiplier,
                               originalRadii.getTopLeftVerticalRadius() * multiplier,
                               originalRadii.getTopRightVerticalRadius() * multiplier,
                               originalRadii.getTopRightHorizontalRadius() * multiplier,
                               originalRadii.getBottomLeftHorizontalRadius() * multiplier,
                               originalRadii.getBottomLeftVerticalRadius() * multiplier,
                               originalRadii.getBottomRightVerticalRadius() * multiplier,
                               originalRadii.getBottomRightHorizontalRadius() * multiplier,
                               false, false, false, false, false, false, false, false);
        //@formatter:on
    }

    /**
     * Creates a rectangular ripple effect centered at the mouse event location.
     *
     * <p>The size of the rectangle is determined by the {@code rippleRadius} property, ensuring the ripple expands symmetrically from the event point. After creating the rectangle, it applies common ripple
     * effect properties such as fill color, stroke width, and optional drop shadow through {@link #applyRippleEffectProperties(Shape)}.</p>
     *
     * @param event
     *         The {@link MouseEvent} that triggers the ripple effect.
     *
     * @return A {@link Rectangle} shaped ripple effect, positioned based on the event location.
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
     * Creates a circular ripple effect at the location of the mouse event.
     *
     * <p>The radius of the circle is defined by the {@code rippleRadius} property, creating a ripple that emanates from the point of interaction. This method also applies standardized ripple effect properties
     * to the circle, including fill and stroke colors, stroke width, and potentially a drop shadow if enabled.</p>
     *
     * @param event
     *         The {@link MouseEvent} that initiates the ripple effect.
     *
     * @return A {@link Circle} representing the ripple effect, centered on the event location.
     */
    @NotNull
    private Circle createCircleRipple(MouseEvent event) {
        Circle ripple = new Circle(event.getX(), event.getY(), rippleRadius.getValue());
        applyRippleEffectProperties(ripple);
        return ripple;
    }

    /**
     * Generates a horizontally elongated ellipse ripple centered at the mouse event's location.
     *
     * <p>The major axis (horizontal) of the ellipse is twice the length of the {@code rippleRadius} property, while the minor axis (vertical) matches the {@code rippleRadius}, creating a horizontally
     * stretched ripple effect. Common ripple effect properties are applied to enhance the visual appearance of the ellipse, including color, stroke, and shadow effects as configured.</p>
     *
     * @param event
     *         The {@link MouseEvent} that triggers this ripple effect.
     *
     * @return An {@link Ellipse} with a horizontal orientation, centered at the event point.
     */
    @NotNull
    private Ellipse createHorizontalEllipseRipple(MouseEvent event) {
        double radius = getRippleRadius();

        Ellipse ripple = new Ellipse(event.getX(), event.getY(), radius * 2, radius);
        applyRippleEffectProperties(ripple);
        return ripple;
    }

    /**
     * Creates a vertically elongated ellipse ripple centered on the mouse event's location.
     *
     * <p>This method sets the ellipse's vertical radius to twice the {@code rippleRadius} property value, and the horizontal radius to the {@code rippleRadius}, resulting in a vertically stretched appearance.
     * Similar to other shapes, this ripple undergoes styling through {@link #applyRippleEffectProperties(Shape)} to apply fill color, stroke, and possibly a drop shadow to enhance its visual effect.</p>
     *
     * @param event
     *         The {@link MouseEvent} responsible for initiating the ripple.
     *
     * @return An {@link Ellipse} shaped ripple effect with a vertical orientation.
     */
    @NotNull
    private Ellipse createVerticalEllipseRipple(MouseEvent event) {
        double radius = getRippleRadius();

        Ellipse ripple = new Ellipse(event.getX(), event.getY(), radius, radius * 2);
        applyRippleEffectProperties(ripple);
        return ripple;
    }

    /**
     * Applies common ripple effect properties to the specified {@link Shape}, including fill color, stroke color, stroke width, and an optional drop shadow.
     *
     *
     * <p>The method determines the fill color based on whether the ripple fill state is enabled, applying either the specified {@code rippleColor} or making it transparent. If the drop shadow effect is
     * enabled, it configures and applies a {@link DropShadow} effect based on the configured properties such as color, radius, spread, and offsets.</p>
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

    //endregion Ripple Creation

    //region Styleable Properties
    //*****************************************************************
    // Styleable Properties
    //*****************************************************************

    /**
     * Initializes the styleable properties for the {@code EFXRippleEffect}.
     *
     * <p>This method sets up various properties that can be styled via CSS, allowing for customization of the ripple effect's appearance and behavior. Each property is created with a specific name, initial
     * value, and linked to its CSS metadata defined in the styles' manager.</p>
     *
     * <h2>The following styleable properties are initialized:</h2>
     * <ul>
     *     <li>{@code rippleState}: Defines the overall state of the ripple effect.</li>
     *     <li>{@code rippleShape}: Determines the shape of the ripple effect.</li>
     *     <li>{@code rippleClipShape}: Specifies the clipping shape of the ripple effect.</li>
     *     <li>{@code rippleColor}: Sets the fill color of the ripple.</li>
     *     <li>{@code rippleDuration}: Configures the duration of the ripple animation.</li>
     *     <li>{@code rippleInterpolator}: Defines the interpolation method for the ripple animation.</li>
     *     <li>{@code rippleFillState}: Indicates whether the ripple is filled or not.</li>
     *     <li>{@code rippleRadius}: Determines the radius of the ripple effect.</li>
     *     <li>{@code rippleStrokeWidth}: Sets the stroke width of the ripple.</li>
     *     <li>{@code rippleStrokeColor}: Specifies the stroke color of the ripple.</li>
     *     <li>{@code rippleDirection}: Defines the direction of the ripple expansion.</li>
     *     <li>{@code rippleFadeState}: Indicates whether the ripple should fade out after animation.</li>
     *     <li>{@code dropShadowBlurType}: Configures the blur type of the optional drop shadow.</li>
     *     <li>{@code dropShadowColor}: Sets the color of the drop shadow.</li>
     *     <li>{@code dropShadowRadius}: Determines the radius of the drop shadow.</li>
     *     <li>{@code dropShadowSpread}: Configures the spread of the drop shadow.</li>
     *     <li>{@code dropShadowOffsetX}: Sets the horizontal offset of the drop shadow.</li>
     *     <li>{@code dropShadowOffsetY}: Sets the vertical offset of the drop shadow.</li>
     *     <li>{@code dropShadowState}: Defines the state of the drop shadow effect.</li>
     * </ul>
     *
     * <p>These properties allow for extensive customization through CSS, making it possible to adjust the visual characteristics of the ripple effect to fit the design requirements.</p>
     */
    private void initializeStyleableProperties() {
        rippleState = EFXStyleableObjectProperty.<EFXState>create()
                                                .name("rippleEFXState")
                                                .bean(EFXRippleEffect.this)
                                                .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-state"))
                                                .initialValue(DEFAULT_RIPPLE_EFX_STATE)
                                                .build();

        rippleShape = EFXStyleableObjectProperty.<EFXRippleShape>create()
                                                .name("efxRippleShape")
                                                .bean(EFXRippleEffect.this)
                                                .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-shape"))
                                                .initialValue(DEFAULT_RIPPLE_SHAPE)
                                                .build();

        rippleClipShape = EFXStyleableObjectProperty.<EFXRippleShape>create()
                                                    .name("rippleClipShape")
                                                    .bean(EFXRippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-clip-shape"))
                                                    .initialValue(DEFAULT_RIPPLE_CLIP_SHAPE)
                                                    .build();

        rippleColor = EFXStyleableObjectProperty.<Color>create()
                                                .name("rippleColor")
                                                .bean(EFXRippleEffect.this)
                                                .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-color"))
                                                .initialValue(DEFAULT_RIPPLE_COLOR)
                                                .build();

        rippleDuration = EFXStyleableObjectProperty.<Duration>create()
                                                   .name("rippleDuration")
                                                   .bean(EFXRippleEffect.this)
                                                   .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-duration"))
                                                   .initialValue(DEFAULT_RIPPLE_DURATION)
                                                   .build();

        rippleInterpolator = EFXStyleableObjectProperty.<Interpolator>create()
                                                       .name("rippleInterpolator")
                                                       .bean(EFXRippleEffect.this)
                                                       .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-interpolator"))
                                                       .initialValue(DEFAULT_RIPPLE_INTERPOLATOR)
                                                       .build();

        rippleFillState = EFXStyleableObjectProperty.<EFXState>create()
                                                    .name("rippleFillEFXState")
                                                    .bean(EFXRippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-fill-state"))
                                                    .initialValue(DEFAULT_RIPPLE_FILL_EFX_STATE)
                                                    .build();

        rippleRadius = EFXStyleableDoubleProperty.create()
                                                 .name("rippleRadius")
                                                 .bean(EFXRippleEffect.this)
                                                 .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-radius"))
                                                 .initialValue(DEFAULT_RIPPLE_RADIUS)
                                                 .build();

        rippleStrokeWidth = EFXStyleableDoubleProperty.create()
                                                      .name("rippleStrokeWidth")
                                                      .bean(EFXRippleEffect.this)
                                                      .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-stroke-width"))
                                                      .initialValue(DEFAULT_RIPPLE_STROKE_WIDTH)
                                                      .build();

        rippleStrokeColor = EFXStyleableObjectProperty.<Color>create()
                                                      .name("rippleStrokeColor")
                                                      .bean(EFXRippleEffect.this)
                                                      .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-stroke-color"))
                                                      .initialValue(DEFAULT_RIPPLE_STROKE_COLOR)
                                                      .build();

        rippleDirection = EFXStyleableObjectProperty.<EFXRippleDirection>create()
                                                    .name("efxRippleDirection")
                                                    .bean(EFXRippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-direction"))
                                                    .initialValue(DEFAULT_RIPPLE_DIRECTION)
                                                    .build();

        rippleFadeState = EFXStyleableObjectProperty.<EFXState>create()
                                                    .name("rippleFadeEFXState")
                                                    .bean(EFXRippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-fade-state"))
                                                    .initialValue(DEFAULT_RIPPLE_FADE_EFX_STATE)
                                                    .build();

        dropShadowBlurType = EFXStyleableObjectProperty.<BlurType>create()
                                                       .name("dropShadowBlurType")
                                                       .bean(EFXRippleEffect.this)
                                                       .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-drop-shadow-blur-type"))
                                                       .initialValue(DEFAULT_DROPSHADOW_BLUR_TYPE)
                                                       .build();

        dropShadowColor = EFXStyleableObjectProperty.<Color>create()
                                                    .name("dropShadowColor")
                                                    .bean(EFXRippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-drop-shadow-color"))
                                                    .initialValue(DEFAULT_DROPSHADOW_COLOR)
                                                    .build();

        dropShadowRadius = EFXStyleableDoubleProperty.create()
                                                     .name("dropShadowRadius")
                                                     .bean(EFXRippleEffect.this)
                                                     .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-drop-shadow-radius"))
                                                     .initialValue(DEFAULT_DROPSHADOW_RADIUS)
                                                     .build();

        dropShadowSpread = EFXStyleableDoubleProperty.create()
                                                     .name("dropShadowSpread")
                                                     .bean(EFXRippleEffect.this)
                                                     .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-drop-shadow-spread"))
                                                     .initialValue(DEFAULT_DROPSHADOW_SPREAD)
                                                     .build();

        dropShadowOffsetX = EFXStyleableDoubleProperty.create()
                                                      .name("dropShadowOffsetX")
                                                      .bean(EFXRippleEffect.this)
                                                      .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-drop-shadow-offset-x"))
                                                      .initialValue(DEFAULT_DROPSHADOW_OFFSET_X)
                                                      .build();

        dropShadowOffsetY = EFXStyleableDoubleProperty.create()
                                                      .name("dropShadowOffsetY")
                                                      .bean(EFXRippleEffect.this)
                                                      .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-drop-shadow-offset-y"))
                                                      .initialValue(DEFAULT_DROPSHADOW_OFFSET_Y)
                                                      .build();

        dropShadowState = EFXStyleableObjectProperty.<EFXState>create()
                                                    .name("dropShadowEFXState")
                                                    .bean(EFXRippleEffect.this)
                                                    .cssMetaData(stylesManager.findCssMetaData("-efx-ripple-drop-shadow-state"))
                                                    .initialValue(DEFAULT_DROPSHADOW_EFX_STATE)
                                                    .build();
    }

    /**
     * Returns a list of the CSS metadata for the class.
     *
     * @return a list of the CSS metadata for the class.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return stylesManager.getCssMetaDataList();
    }

    /**
     * Retrieves the list of CSS metadata for this class.
     *
     * @return the list of CSS metadata for this class
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {return getClassCssMetaData();}

    //endregion Styleable Properties

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Retrieves the ripple state of the EFX object.
     *
     * @return The current ripple state of the EFX.
     */
    public EFXState getRippleState() {
        return rippleState.get();
    }

    /**
     * Retrieves the ripple state property of this object.
     *
     * @return the ripple state property
     */
    public EFXStyleableObjectProperty<EFXState> rippleStateProperty() {
        return rippleState;
    }

    /**
     * Sets the ripple state.
     *
     * @param rippleEFXState
     *         the new ripple state
     */
    public void setRippleState(EFXState rippleEFXState) {
        this.rippleState.set(rippleEFXState);
    }

    /**
     * Returns whether the ripple effect is enabled or not.
     *
     * @return {@code true} if the ripple effect is enabled, {@code false} otherwise.
     */
    public boolean isRippleEnabled() {
        return getRippleState() == EFXState.ENABLED;
    }

    /**
     * Returns the EFXRippleShape object associated with this method.
     *
     * @return the EFXRippleShape object representing the ripple shape
     */
    public EFXRippleShape getRippleShape() {
        return rippleShape.get();
    }

    /**
     * Retrieves the ripple shape property.
     *
     * @return the ripple shape property
     */
    public EFXStyleableObjectProperty<EFXRippleShape> rippleShapeProperty() {
        return rippleShape;
    }

    /**
     * Sets the ripple shape.
     *
     * @param efxRippleShape
     *         the shape of the ripple.
     */
    public void setRippleShape(EFXRippleShape efxRippleShape) {
        this.rippleShape.set(efxRippleShape);
    }

    /**
     * Retrieves the shape of the ripple clip at the current state.
     *
     * @return The shape of the ripple clip at the current state.
     */
    public EFXRippleShape getRippleClipShape() {
        return rippleClipShape.get();
    }

    /**
     * Retrieves the ripple clip shape property of this object.
     *
     * @return The ripple clip shape property.
     */
    public EFXStyleableObjectProperty<EFXRippleShape> rippleClipShapeProperty() {
        return rippleClipShape;
    }

    /**
     * Sets the shape of the ripple clipping area.
     *
     * @param rippleClipShape
     *         the shape of the ripple clipping area to set
     */
    public void setRippleClipShape(EFXRippleShape rippleClipShape) {
        this.rippleClipShape.set(rippleClipShape);
    }

    /**
     * Retrieves the color of the ripple effect.
     *
     * @return The color of the ripple effect.
     */
    public Color getRippleColor() {
        return rippleColor.get();
    }

    /**
     * Returns the EFXStyleableObjectProperty representing the ripple color of the object.
     *
     * @return The EFXStyleableObjectProperty representing the ripple color of the object.
     */
    public EFXStyleableObjectProperty<Color> rippleColorProperty() {
        return rippleColor;
    }

    /**
     * Sets the color of the ripple effect.
     *
     * @param rippleColor
     *         the color of the ripple effect
     */
    public void setRippleColor(Color rippleColor) {
        this.rippleColor.set(rippleColor);
    }

    /**
     * Retrieves the duration of the ripple effect.
     *
     * @return The duration of the ripple effect.
     */
    public Duration getRippleDuration() {
        return rippleDuration.get();
    }

    /**
     * Retrieves the property representing the duration of the ripple effect.
     *
     * @return the property representing the duration of the ripple effect
     */
    public EFXStyleableObjectProperty<Duration> rippleDurationProperty() {
        return rippleDuration;
    }

    /**
     * Sets the duration of the ripple effect.
     *
     * @param rippleDuration
     *         the duration of the ripple effect
     */
    public void setRippleDuration(Duration rippleDuration) {
        this.rippleDuration.set(rippleDuration);
    }

    /**
     * Returns the Ripple interpolator.
     *
     * @return The Ripple interpolator.
     */
    public Interpolator getRippleInterpolator() {
        return rippleInterpolator.get();
    }

    /**
     * Returns the property representing the ripple interpolator for this object.
     *
     * @return The property object representing the ripple interpolator.
     */
    public EFXStyleableObjectProperty<Interpolator> rippleInterpolatorProperty() {
        return rippleInterpolator;
    }

    /**
     * Sets the Interpolator for the ripple effect on the component.
     *
     * @param rippleInterpolator
     *         the Interpolator to be set for the ripple effect
     */
    public void setRippleInterpolator(Interpolator rippleInterpolator) {
        this.rippleInterpolator.set(rippleInterpolator);
    }

    /**
     * Retrieves the current state of the ripple fill.
     *
     * @return the EFXState object representing the state of the ripple fill
     */
    public EFXState getRippleFillState() {
        return rippleFillState.get();
    }

    /**
     * Retrieves the styleable object property representing the ripple fill state.
     *
     * @return The styleable object property for ripple fill state
     */
    public EFXStyleableObjectProperty<EFXState> rippleFillStateProperty() {
        return rippleFillState;
    }

    /**
     * Sets the state of the ripple fill effect.
     *
     * @param rippleFillEFXState
     *         the EFXState to set as the ripple fill state
     */
    public void setRippleFillState(EFXState rippleFillEFXState) {
        this.rippleFillState.set(rippleFillEFXState);
    }

    /**
     * Checks whether the ripple fill state is enabled.
     *
     * @return true if the ripple fill state is enabled, false otherwise.
     */
    public boolean isRippleFillStateEnabled() {
        return getRippleFillState() == EFXState.ENABLED;
    }

    /**
     * Retrieves the radius of the ripple effect.
     *
     * @return The radius of the ripple effect.
     */
    public double getRippleRadius() {
        return rippleRadius.get();
    }

    /**
     * Get the ripple radius property.
     *
     * @return the ripple radius property.
     */
    public EFXStyleableDoubleProperty rippleRadiusProperty() {
        return rippleRadius;
    }

    /**
     * Sets the radius of the ripple effect.
     *
     * @param rippleRadius
     *         the new radius of the ripple effect
     */
    public void setRippleRadius(double rippleRadius) {
        this.rippleRadius.set(rippleRadius);
    }

    /**
     * Gets the width of the stroke for the ripple effect.
     *
     * @return The width of the ripple stroke.
     */
    public double getRippleStrokeWidth() {
        return rippleStrokeWidth.get();
    }

    /**
     *
     */
    public EFXStyleableDoubleProperty rippleStrokeWidthProperty() {
        return rippleStrokeWidth;
    }

    /**
     * Sets the width of the ripple effect stroke.
     *
     * @param rippleStrokeWidth
     *         the width of the ripple effect stroke to be set
     */
    public void setRippleStrokeWidth(double rippleStrokeWidth) {
        this.rippleStrokeWidth.set(rippleStrokeWidth);
    }

    /**
     * Retrieves the color used for the ripple stroke.
     *
     * @return The color used for the ripple stroke.
     */
    public Color getRippleStrokeColor() {
        return rippleStrokeColor.get();
    }

    /**
     * Returns the property representing the stroke color of the ripple effect.
     *
     * @return The property representing the stroke color.
     */
    public EFXStyleableObjectProperty<Color> rippleStrokeColorProperty() {
        return rippleStrokeColor;
    }

    /**
     * Sets the color for the ripple stroke.
     *
     * @param rippleStrokeColor
     *         The new color for the ripple stroke
     */
    public void setRippleStrokeColor(Color rippleStrokeColor) {
        this.rippleStrokeColor.set(rippleStrokeColor);
    }

    /**
     * Returns the ripple direction of the EFXRippleEffect.
     *
     * @return The ripple direction.
     */
    public boolean isRippleDirectionOut() {
        return getRippleDirection() == EFXRippleDirection.OUT;
    }

    /**
     * Checks if the ripple direction is set to EFXRippleDirection.IN.
     *
     * @return true if the ripple direction is EFXRippleDirection.IN, otherwise false
     */
    public boolean isRippleDirectionIn() {
        return getRippleDirection() == EFXRippleDirection.IN;
    }

    /**
     * Retrieves the direction of the ripple effect.
     *
     * @return The direction of the ripple effect.
     */
    public EFXRippleDirection getRippleDirection() {
        return rippleDirection.get();
    }

    /**
     * Returns the styleable object property representing the ripple direction of the EFXRippleEffect.
     *
     * @return the ripple direction property
     */
    public EFXStyleableObjectProperty<EFXRippleDirection> rippleDirectionProperty() {
        return rippleDirection;
    }

    /**
     * Sets the ripple direction of the EFXRippleEffect.
     *
     * @param efxRippleDirection
     *         The ripple direction to set.
     */
    public void setRippleDirection(EFXRippleDirection efxRippleDirection) {
        this.rippleDirection.set(efxRippleDirection);
    }

    /**
     * Retrieves the target node associated with the ripple effect.
     *
     * @return the target node
     */
    public Region getTargetNode() {
        return targetNode;
    }

    /**
     * Retrieves the current fade state of the ripple effect.
     *
     * @return The current fade state of the ripple effect.
     */
    public EFXState getRippleFadeState() {
        return rippleFadeState.get();
    }

    /**
     * Returns the styleable property representing the fade state of the ripple effect.
     *
     * @return The styleable property representing the fade state.
     */
    public EFXStyleableObjectProperty<EFXState> rippleFadeStateProperty() {
        return rippleFadeState;
    }

    /**
     * Sets the ripple fade state of the EFXRippleEffect.
     *
     * @param rippleFadeEFXState
     *         the new ripple fade state to be set
     */
    public void setRippleFadeState(EFXState rippleFadeEFXState) {
        this.rippleFadeState.set(rippleFadeEFXState);
    }

    /**
     * Returns the current state of the ripple fade effect.
     *
     * @return The current state of the ripple fade effect.
     */
    public boolean isRippleFadeEnabled() {
        return getRippleFadeState() == EFXState.ENABLED;
    }

    /**
     * Retrieves the blur type used for the drop shadow effect in the ripple effect.
     *
     * @return The blur type used for the drop shadow effect.
     *
     * @see BlurType
     */
    public BlurType getDropShadowBlurType() {
        return dropShadowBlurType.get();
    }

    /**
     * Retrieves the property representing the blur type of the drop shadow effect for the ripple effect.
     *
     * @return The property representing the blur type of the drop shadow effect.
     */
    public EFXStyleableObjectProperty<BlurType> dropShadowBlurTypeProperty() {
        return dropShadowBlurType;
    }

    /**
     * Sets the blur type of the drop shadow effect for the ripple effect.
     *
     * @param dropShadowBlurType
     *         the blur type to set for the drop shadow effect
     */
    public void setDropShadowBlurType(BlurType dropShadowBlurType) {
        this.dropShadowBlurType.set(dropShadowBlurType);
    }

    /**
     * Retrieves the drop shadow color property of the EFXRippleEffect.
     *
     * @return The drop shadow color property.
     *
     * @see Color
     */
    public Color getDropShadowColor() {
        return dropShadowColor.get();
    }

    /**
     * Retrieves the drop shadow color property.
     *
     * @return The drop shadow color property.
     */
    public EFXStyleableObjectProperty<Color> dropShadowColorProperty() {
        return dropShadowColor;
    }

    /**
     * Sets the color of the drop shadow effect for the ripple effect.
     *
     * @param dropShadowColor
     *         the color to set for the drop shadow effect
     */
    public void setDropShadowColor(Color dropShadowColor) {
        this.dropShadowColor.set(dropShadowColor);
    }

    /**
     * Returns the drop shadow radius of the ripple effect.
     *
     * @return the drop shadow radius of the ripple effect.
     */
    public double getDropShadowRadius() {
        return dropShadowRadius.get();
    }

    /**
     * Returns the property representing the drop shadow radius of the EFXRippleEffect.
     *
     * @return The property representing the drop shadow radius.
     */
    public EFXStyleableDoubleProperty dropShadowRadiusProperty() {
        return dropShadowRadius;
    }

    /**
     * Sets the radius of the drop shadow for the ripple effect.
     *
     * @param dropShadowRadius
     *         the radius of the drop shadow to be set
     */
    public void setDropShadowRadius(double dropShadowRadius) {
        this.dropShadowRadius.set(dropShadowRadius);
    }

    /**
     * Retrieves the spread value of the drop shadow effect applied to the ripple effect.
     *
     * @return The spread value of the drop shadow effect.
     */
    public double getDropShadowSpread() {
        return dropShadowSpread.get();
    }

    /**
     * Returns the styleable double property representing the drop shadow spread of the EFXRippleEffect.
     *
     * @return the styleable double property for the drop shadow spread
     */
    public EFXStyleableDoubleProperty dropShadowSpreadProperty() {
        return dropShadowSpread;
    }

    /**
     * Sets the spread of the drop shadow.
     *
     * @param dropShadowSpread
     *         The spread of the drop shadow
     */
    public void setDropShadowSpread(double dropShadowSpread) {
        this.dropShadowSpread.set(dropShadowSpread);
    }

    /**
     * Retrieves the offset on the x-axis of the drop shadow effect applied to the element.
     *
     * @return the offset on the x-axis of the drop shadow effect
     */
    public double getDropShadowOffsetX() {
        return dropShadowOffsetX.get();
    }

    /**
     * Retrieves the EFXStyleableDoubleProperty for the drop shadow offset in the X direction.
     *
     * @return The EFXStyleableDoubleProperty for the drop shadow offset in the X direction.
     */
    public EFXStyleableDoubleProperty dropShadowOffsetXProperty() {
        return dropShadowOffsetX;
    }

    /**
     * Sets the horizontal offset of the drop shadow effect applied to the ripple effect.
     *
     * @param dropShadowOffsetX
     *         the horizontal offset of the drop shadow
     */
    public void setDropShadowOffsetX(double dropShadowOffsetX) {
        this.dropShadowOffsetX.set(dropShadowOffsetX);
    }

    /**
     * Retrieves the offset in the Y-direction of the drop shadow effect applied to the ripple effect.
     *
     * @return The offset in the Y-direction of the drop shadow effect.
     */
    public double getDropShadowOffsetY() {
        return dropShadowOffsetY.get();
    }

    /**
     * Returns the styleable double property for the drop shadow's Y offset.
     *
     * @return the styleable double property for the drop shadow's Y offset
     */
    public EFXStyleableDoubleProperty dropShadowOffsetYProperty() {
        return dropShadowOffsetY;
    }

    /**
     * Sets the offset in the y-direction for the drop shadow effect.
     *
     * @param dropShadowOffsetY
     *         the offset in the y-direction for the drop shadow effect
     */
    public void setDropShadowOffsetY(double dropShadowOffsetY) {
        this.dropShadowOffsetY.set(dropShadowOffsetY);
    }

    /**
     * Returns the current state of the drop shadow.
     *
     * @return the current state of the drop shadow
     */
    public EFXState getDropShadowState() {
        return dropShadowState.get();
    }

    /**
     * Retrieves the styleable property representing the drop shadow state for the EFXRippleEffect.
     *
     * @return The styleable property representing the drop shadow state.
     */
    public EFXStyleableObjectProperty<EFXState> dropShadowStateProperty() {
        return dropShadowState;
    }

    /**
     * Set the drop shadow state of the EFXRippleEffect.
     *
     * @param dropShadowEFXState
     *         The new drop shadow state.
     */
    public void setDropShadowState(EFXState dropShadowEFXState) {
        this.dropShadowState.set(dropShadowEFXState);
    }

    /**
     * Returns whether the drop shadow effect is enabled or not.
     *
     * @return true if drop shadow is enabled, false otherwise
     */
    public boolean isDropShadowEnabled() {
        return getDropShadowState() == EFXState.ENABLED;
    }

    //endregion Getters and Setters
}