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
package io.github.colindj1120.materialdesignui.utils;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Collection;
import java.util.Optional;

/**
 * The AnimationUtils class is a collection of static utility methods designed to facilitate the creation and management of JavaFX animations. This class serves as a toolkit for common
 * animation-related tasks, enabling developers to construct, modify, and control animations with greater ease and flexibility.
 *
 * <p>Key functionalities include:</p>
 * <ul>
 *     <li>Creating various types of {@link Timeline} animations, with support for custom frame rates and keyframes.</li>
 *     <li>Generating {@link KeyValue} instances, which are fundamental to defining animation states.</li>
 *     <li>Constructing {@link KeyFrame} objects, representing significant points in an animation timeline.</li>
 *     <li>Providing a range of {@link Interpolator} types for customizing animation pacing and easing effects.</li>
 * </ul>
 *
 * <p>By offering methods to handle repetitive or complex tasks, AnimationUtils simplifies the process of creating sophisticated and smooth animations in JavaFX applications. Its utility methods
 * cover the essentials of animation creation, from setting up keyframes and values to applying various interpolations for nuanced motion effects.</p>
 *
 * <p>As a utility class, AnimationUtils cannot be instantiated; it is designed to be used directly through its static methods.</p>
 *
 * <p>Usage Example:</p>
 * <pre>
 *     // Creating a simple linear animation
 *     Timeline timeline = AnimationUtils.createAnimation();
 *     KeyValue keyValue = AnimationUtils.createKeyValue(someProperty, newValue);
 *     KeyFrame keyFrame = AnimationUtils.createKeyFrame(Duration.seconds(1), keyValue);
 *     timeline.getKeyFrames().add(keyFrame);
 *     timeline.play();
 * </pre>
 *
 * <p>This class enhances code readability and maintainability by abstracting complex animation setup details, allowing developers to focus on crafting their desired animation effects.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Timeline
 * @see KeyValue
 * @see KeyFrame
 * @see Interpolator
 */
public final class AnimationUtils {
    /**
     * The AnimationUtils class provides utility methods for animation-related tasks. This class cannot be instantiated and only contains static methods.
     */
    private AnimationUtils() {}

    /**
     * Creates a basic Timeline animation without any keyframes. This is useful as a starting point for dynamically adding keyframes later or for simple animations that don't require specific
     * keyframes.
     *
     * @return A new instance of the Timeline class.
     */
    public static Timeline createAnimation() {
        return new Timeline();
    }

    /**
     * Creates a Timeline animation with the given target frame rate. The frame rate determines how often the animation updates per second. A higher frame rate can make the animation appear smoother
     * but may require more processing power.
     *
     * <p>Use a higher frame rate for complex animations where smoothness is crucial. A lower frame rate
     * might be more suitable for simpler or slower animations, conserving resources.</p>
     *
     * @param targetFrameRate
     *         The frame rate at which the animation should run, in frames per second.
     *
     * @return A Timeline animation with the given target frame rate.
     */
    public static Timeline createAnimation(double targetFrameRate) {
        return new Timeline(targetFrameRate);
    }

    /**
     * Creates a Timeline animation composed of the given keyframes. Use this method when you have predefined keyframes for your animation. Each keyframe defines a specific state at a specific time
     * during the animation.
     *
     * @param keyframes
     *         The keyframes to include in the animation.
     *
     * @return A Timeline animation composed of the given keyframes.
     */
    public static Timeline createAnimation(KeyFrame... keyframes) {
        return new Timeline(keyframes);
    }

    /**
     * Creates a Timeline animation from the given keyframes at a target frame rate. The frame rate determines how often * the animation updates per second. A higher frame rate can make the animation
     * appear smoother but may require more processing power.
     *
     * <p>Use a higher frame rate for complex animations where smoothness is crucial. A lower frame rate * might be more suitable for
     * simpler or slower animations, conserving resources.</p>
     *
     * @param targetFrameRate
     *         The frame rate at which the animation should run.
     * @param keyframes
     *         The keyframes to include in the animation.
     *
     * @return A Timeline animation composed of the given keyframes.
     */
    public static Timeline createAnimation(double targetFrameRate, KeyFrame... keyframes) {
        return new Timeline(targetFrameRate, keyframes);
    }

    /**
     * Creates a KeyValue with a specified target value and interpolator. KeyValue is a key concept in defining animations in JavaFX. It specifies the target object and the end value of a property
     * when a KeyFrame is reached.
     *
     * <p>The target is a WritableValue, typically representing a property of a UI element (like the
     * opacity of a node or the position of a shape). This method simplifies the process of creating KeyValues for animations.</p>
     *
     * @param target
     *         The property to be animated (e.g., the opacity property of a node).
     * @param endValue
     *         The end value of the property at the KeyFrame.
     * @param interpolator
     *         The interpolator for the KeyValue. Can be null for default (linear).
     *
     * @return A KeyValue with specified parameters.
     */
    public static <T> KeyValue createKeyValue(WritableValue<T> target, T endValue, Interpolator interpolator) {
        return new KeyValue(target, endValue, Optional.ofNullable(interpolator)
                                                      .orElse(Interpolator.LINEAR));
    }

    /**
     * Creates a KeyValue with specified target value.
     *
     * @param target
     *         The property to be animated.
     * @param endValue
     *         The end value of the property.
     *
     * @return A KeyValue with specified parameters and a default interpolator.
     */
    public static <T> KeyValue createKeyValue(WritableValue<T> target, T endValue) {
        return createKeyValue(target, endValue, null);
    }

    /**
     * Creates a KeyFrame with the specified duration and key values.
     *
     * @param duration
     *         The duration of the key frame.
     * @param keyValues
     *         The key values to include in the key frame.
     *
     * @return A KeyFrame with the specified duration and key values.
     */
    public static KeyFrame createKeyFrame(Duration duration, KeyValue... keyValues) {
        return new KeyFrame(duration, keyValues);
    }

    /**
     * Creates a KeyFrame with the specified duration, name, and key values.
     *
     * @param duration
     *         The duration of the key frame.
     * @param name
     *         The name of the key frame.
     * @param keyValues
     *         The key values of the key frame.
     *
     * @return A KeyFrame with the specified parameters.
     */
    public static KeyFrame createKeyFrame(Duration duration, String name, KeyValue... keyValues) {
        return new KeyFrame(duration, name, keyValues);
    }

    /**
     * Creates a KeyFrame with the specified duration, EventHandler, and KeyValues.
     *
     * @param duration
     *         The duration of the KeyFrame.
     * @param onFinished
     *         The EventHandler to be invoked when the KeyFrame finishes.
     * @param keyValues
     *         The KeyValues to be applied during the KeyFrame.
     *
     * @return A new KeyFrame instance.
     */
    public static KeyFrame createKeyFrame(Duration duration, EventHandler<ActionEvent> onFinished, KeyValue... keyValues) {
        return new KeyFrame(duration, onFinished, keyValues);
    }

    /**
     * Creates a KeyFrame with the specified duration, name, onFinished event handler, and key values.
     *
     * @param duration
     *         The duration of the KeyFrame.
     * @param name
     *         The name of the KeyFrame.
     * @param onFinished
     *         The event handler to be executed when the KeyFrame finishes.
     * @param keyValues
     *         The key values to include in the KeyFrame.
     *
     * @return A KeyFrame with the specified parameters.
     */
    public static KeyFrame createKeyFrame(Duration duration, String name, EventHandler<ActionEvent> onFinished, KeyValue... keyValues) {
        return new KeyFrame(duration, name, onFinished, keyValues);
    }

    /**
     * Creates a KeyFrame with the specified duration, name, onFinished event handler, and key values.
     *
     * @param duration
     *         The duration of the key frame.
     * @param name
     *         The name of the key frame.
     * @param onFinished
     *         The event handler to be executed when the key frame finishes.
     * @param keyValues
     *         The collection of key values for the key frame.
     *
     * @return A KeyFrame with the specified parameters.
     */
    public static KeyFrame createKeyFrame(Duration duration, String name, EventHandler<ActionEvent> onFinished, Collection<KeyValue> keyValues) {
        return new KeyFrame(duration, name, onFinished, keyValues);
    }

    /**
     * Returns the discrete interpolator. This interpolator causes the animation to jump to the end value instantly at the end of the animation. It does not provide a smooth transition but rather an
     * abrupt change from the start value to the end value.
     *
     * <p>This is particularly useful for animations where a smooth transition is not required, or for
     * triggering an instantaneous change at a specific point in the timeline.</p>
     *
     * @return The discrete interpolator.
     */
    public static Interpolator discreteInterpolator() {
        return Interpolator.DISCRETE;
    }

    /**
     * Returns a linear interpolator. This interpolator provides a constant rate of change, making the animation progress at a steady pace from start to end. It creates a linear transition between
     * keyframes.
     *
     * <p>Linear interpolation is useful when a uniform rate of change is desired throughout the animation,
     * without any acceleration or deceleration effects.</p>
     *
     * @return The linear interpolator.
     */
    public static Interpolator linearInterpolator() {
        return Interpolator.LINEAR;
    }

    /**
     * Returns an ease-in interpolator. This interpolator causes the animation to start slowly and then accelerate as it progresses. It provides a gradual start, creating a smooth and soft beginning
     * to the animation.
     *
     * <p>Use this interpolator when you want to create a more natural and less abrupt start to an
     * animation, as it simulates the gradual build-up of speed.</p>
     *
     * @return An instance of the Interpolator class with ease-in interpolation.
     */
    public static Interpolator easeInInterpolator() {
        return Interpolator.EASE_IN;
    }

    /**
     * Returns the ease-out interpolator. This interpolator causes the animation to start at a faster pace and then decelerate towards the end. It provides a smooth slowing down effect as the
     * animation completes.
     *
     * <p>Ideal for animations where a gentle and gradual stop is desired, simulating a natural
     * deceleration like objects coming to rest.</p>
     *
     * @return The ease-out interpolator.
     */
    public static Interpolator easeOutInterpolator() {
        return Interpolator.EASE_OUT;
    }

    /**
     * Returns an instance of the Interpolator class that provides both ease-in and ease-out effects. This interpolator causes the animation to start slowly, accelerate in the middle, and then slow
     * down again towards the end.
     *
     * <p>This type of interpolation is very common and natural-looking, as it simulates the way
     * many physical objects move in the real world. It's particularly useful for animations aiming for a smooth and organic feel.</p>
     *
     * @return An Interpolator instance with both ease-in and ease-out effects.
     */
    public static Interpolator easeBothInterpolator() {
        return Interpolator.EASE_BOTH;
    }

    /**
     * Creates a spline interpolator based on cubic Bézier curve control points. This interpolator allows for custom easing effects in animations by defining how the animation progresses between
     * keyframes.
     *
     * <p>The control points define the shape of a cubic Bezier curve, which determines the rate of change of the animation.
     * The first point (t1, v1) and the second point (t2, v2) are control points for the curve, influencing the acceleration and deceleration of the interpolated values.</p>
     *
     * <p>Typically, 't' values are time fractions (ranging from 0 to 1) and 'v' values are the corresponding values at
     * those fractions. For example, (0.25, 0.1) as the first control point means at 25% of the animation duration, the animated value should be at 10% of its way between the start and end
     * values.</p>
     *
     * @param t1
     *         The time fraction of the first control point (typically between 0 and 1).
     * @param v1
     *         The value fraction of the first control point (typically between 0 and 1).
     * @param t2
     *         The time fraction of the second control point (typically between 0 and 1).
     * @param v2
     *         The value fraction of the second control point (typically between 0 and 1).
     *
     * @return A spline interpolator based on the specified cubic Bézier curve control points.
     */
    public static Interpolator splineInterpolator(double t1, double v1, double t2, double v2) {
        return Interpolator.SPLINE(t1, v1, t2, v2);
    }

    /**
     * Creates a tangent interpolator based on a single control point. This interpolator is useful for creating custom easing effects, especially for animations requiring a specific rate of change at
     * a particular moment.
     *
     * <p>The control point (t, v) defines the slope of the tangent at that point on the interpolation curve.
     * The 't' value is the time fraction, and 'v' is the value of the slope at that time. A higher 'v' value results in a steeper slope, indicating a faster change.</p>
     *
     * <p>This method is particularly useful for animations that need to start or end at a specific rate of change,
     * rather than easing in or out.</p>
     *
     * @param t
     *         The duration fraction for the control point (typically between 0 and 1).
     * @param v
     *         The value of the slope at the control point.
     *
     * @return A Tangent interpolator based on the specified control point.
     */
    public static Interpolator tangentInterpolator(Duration t, double v) {
        return Interpolator.TANGENT(t, v);
    }

    /**
     * Creates a tangent interpolator between two points, allowing for more precise control over the animation's rate of change. This interpolator is ideal for animations requiring a specific rate of
     * change between two moments in time.
     *
     * <p>Each control point (t1, v1) and (t2, v2) defines the slope of the tangent at specific points on the
     * interpolation curve. The 't' values are time fractions, and 'v' values are the slopes at those times. Higher 'v' values result in steeper slopes, indicating faster changes.</p>
     *
     * <p>This method is useful for animations that need a specific acceleration or deceleration pattern between
     * two points in time.</p>
     *
     * @param t1
     *         The duration fraction for the first control point (typically between 0 and 1).
     * @param v1
     *         The value of the slope at the first control point.
     * @param t2
     *         The duration fraction for the second control point (typically between 0 and 1).
     * @param v2
     *         The value of the slope at the second control point.
     *
     * @return A Tangent interpolator between the two specified points.
     */
    public static Interpolator tangentInterpolator(Duration t1, double v1, Duration t2, double v2) {
        return Interpolator.TANGENT(t1, v1, t2, v2);
    }
}
