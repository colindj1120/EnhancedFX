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
package io.github.colindj1120.enhancedfx.effects.base;

import io.github.colindj1120.enhancedfx.effects.ripple.RippleEffect;
import io.github.colindj1120.enhancedfx.enums.State;
import io.github.colindj1120.enhancedfx.enums.effects.RippleDirection;
import io.github.colindj1120.enhancedfx.enums.effects.RippleShape;
import javafx.animation.Interpolator;
import javafx.scene.effect.BlurType;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Defines the default constants used for configuring {@link RippleEffect} instances. This class encapsulates a set of static final constants that represent the default values for various properties
 * of the ripple effect and its associated drop shadow. These defaults serve as the baseline configuration for ripple effects applied to UI components, ensuring a consistent look and feel across
 * different implementations.
 * <p>
 * The constants in this class include settings for ripple state, shape, color, duration, interpolator type, background presence, radius, stroke width, stroke color, direction, fade state, as well as
 * drop shadow properties like the blur type, color, radius, spread, and offset. These settings can be used directly or overridden by custom configurations in the ripple effect factory.
 * </p>
 *
 * <p>
 * This class is instrumental in maintaining a standard for visual effects, making it easier to apply uniform styles and behaviors for ripple effects throughout an application.
 * </p>
 *
 * <p>
 * Usage example:
 * <pre>
 * RippleEffect rippleEffect = new RippleEffectFactory()
 *     .rippleColor(RippleDefaults.DEFAULT_RIPPLE_COLOR)
 *     .rippleDuration(RippleDefaults.DEFAULT_RIPPLE_DURATION)
 *     .build();
 * </pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RippleEffect
 * @see State
 * @see RippleShape
 * @see Color
 * @see Duration
 * @see Interpolator
 * @see RippleDirection
 * @see BlurType
 */
public class RippleDefaults {
    public static final State           DEFAULT_RIPPLE_STATE         = State.ENABLED;
    public static final RippleShape     DEFAULT_RIPPLE_SHAPE         = RippleShape.CIRCLE;
    public static final RippleShape     DEFAULT_RIPPLE_CLIP_SHAPE    = RippleShape.RECTANGLE;
    public static final Color           DEFAULT_RIPPLE_COLOR         = Color.BLACK;
    public static final Duration        DEFAULT_RIPPLE_DURATION      = Duration.millis(600);
    public static final Interpolator    DEFAULT_RIPPLE_INTERPOLATOR  = Interpolator.EASE_OUT;
    public static final State           DEFAULT_RIPPLE_FILL_STATE    = State.ENABLED;
    public static final double          DEFAULT_RIPPLE_RADIUS        = 10.0;
    public static final double          DEFAULT_RIPPLE_STROKE_WIDTH  = .25;
    public static final Color           DEFAULT_RIPPLE_STROKE_COLOR  = Color.BLACK;
    public static final RippleDirection DEFAULT_RIPPLE_DIRECTION     = RippleDirection.OUT;
    public static final State           DEFAULT_RIPPLE_FADE_STATE    = State.ENABLED;
    public static final BlurType        DEFAULT_DROPSHADOW_BLUR_TYPE = BlurType.GAUSSIAN;
    public static final Color           DEFAULT_DROPSHADOW_COLOR     = Color.rgb(0, 0, 0, .5);
    public static final double          DEFAULT_DROPSHADOW_RADIUS    = 8.0;
    public static final double          DEFAULT_DROPSHADOW_SPREAD    = .2;
    public static final double          DEFAULT_DROPSHADOW_OFFSET_X  = 4.0;
    public static final double          DEFAULT_DROPSHADOW_OFFSET_Y  = 4.0;
    public static final State           DEFAULT_DROPSHADOW_STATE     = State.ENABLED;
}
