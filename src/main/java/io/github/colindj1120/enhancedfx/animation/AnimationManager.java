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
package io.github.colindj1120.enhancedfx.animation;

import javafx.animation.Animation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * The AnimationManager class is designed to centralize and simplify the management of multiple JavaFX animations. It provides functionalities to add, remove, play, pause, stop, and control various
 * properties of animations.
 *
 * <p>Key features include:</p>
 * <ul>
 *     <li>Adding and removing animations using unique keys.</li>
 *     <li>Controlling individual animations (play, pause, stop, jump to a time or cue point, set rate, etc.).</li>
 *     <li>Applying operations to all managed animations at once (like playAll, pauseAll, stopAll).</li>
 *     <li>Retrieving and setting properties of animations such as cycle count, delay, rate, and auto-reverse.</li>
 *     <li>Handling the 'onFinished' event of animations.</li>
 *     <li>Managing cue points for synchronization purposes.</li>
 * </ul>
 *
 * <p>This manager is particularly useful in complex UIs where multiple animations need coordinated control and monitoring.
 * It abstracts the low-level details of handling each animation and provides a unified interface to work with animations collectively or individually.</p>
 *
 * <p>Usage Example:</p>
 * <pre>
 *     AnimationManager manager = new AnimationManager();
 *     manager.addAnimation("fadeIn", fadeInAnimation);
 *     manager.addAnimation("slide", slideAnimation);
 *     manager.playAll();
 * </pre>
 *
 * <p>It's important to note that the manager uses a LinkedHashMap internally to maintain the order of animations as they are added.
 * This can be useful for operations that depend on the order of animations.</p>
 *
 * <p>Each method is designed to be self-explanatory and straightforward to use, enhancing the readability and maintainability of code dealing with animations.</p>
 *
 * @author Colin Jokisch
 * @version 1.0
 */
@SuppressWarnings("UnusedReturnValue")
public class AnimationManager {
    private static final String                 INVALID_ANIMATION_KEY = "Animation with key '%s' does not exist in the animation manager";
    private final        Map<String, Animation> animations;

    /**
     * Constructs an AnimationManager.
     */
    public AnimationManager() {
        this.animations = new LinkedHashMap<>();
    }

    /**
     * Checks if the specified key is present in the animations.
     *
     * @param key
     *         the key to be checked for existence in the animations
     *
     * @return true if the animations contain the specified key, false otherwise
     */
    public boolean containsAnimation(String key) {
        return animations.containsKey(key);
    }

    /**
     * Retrieves a map of animations.
     *
     * @return A map that contains the animations. The key is the name of the animation and the value is the Animation object.
     */
    public Map<String, Animation> getAnimations() {
        return Collections.unmodifiableMap(animations);
    }

    public void clearAnimations() {
        animations.clear();
    }

    /**
     * Adds an animation to the manager with a specified key.
     *
     * @param key
     *         The key for the animation.
     * @param animation
     *         The animation to add.
     */
    public void addAnimation(String key, Animation animation) {
        animations.put(key, animation);
    }

    /**
     * Removes an animation from the manager by its key.
     *
     * @param key
     *         The key of the animation to remove.
     *
     * @return An Optional containing the removed animation, or an empty Optional if no animation was found for the key.
     */
    public Optional<Animation> removeAnimation(String key) {
        return Optional.ofNullable(animations.remove(key));
    }

    /**
     * Retrieves an animation from the animation manager using the specified key.
     *
     * @param key
     *         The key of the animation to retrieve.
     *
     * @return An Optional object containing the animation associated with the given key, or an empty Optional if no animation was found for the key.
     */
    public Optional<Animation> getAnimation(String key) {
        return Optional.ofNullable(animations.get(key));
    }

    /**
     * Plays an animation by its key.
     *
     * @param key
     *         The key of the animation to play.
     */
    public void playAnimation(String key) {
        applyToAnimation(key, Animation::play);
    }

    /**
     * Plays the animation from a specified time.
     *
     * @param key
     *         The key of the animation.
     * @param time
     *         The time from which to start playing.
     */
    public void playFrom(String key, Duration time) {
        applyToAnimation(key, animation -> {
            animation.jumpTo(time);
            animation.play();
        });
    }

    /**
     * Plays the animation from a specified cue point.
     *
     * @param key
     *         The key of the animation.
     * @param cuePoint
     *         The cue point from which to start playing.
     */
    public void playFrom(String key, String cuePoint) {
        applyToAnimation(key, animation -> {
            animation.jumpTo(cuePoint);
            animation.play();
        });
    }

    /**
     * Plays the animation from the start.
     *
     * @param key
     *         The key of the animation.
     */
    public void playFromStart(String key) {
        applyToAnimation(key, Animation::playFromStart);
    }

    /**
     * Plays all animations managed by this AnimationManager.
     */
    public void playAll() {
        applyToAllAnimations(Animation::play);
    }

    /**
     * This method applies the "playFromStart" method of the Animation interface to all animations to play them from the beginning.
     */
    public void playAllFromStart() {
        applyToAllAnimations(Animation::playFromStart);
    }

    /**
     * Pauses an animation by its key.
     *
     * @param key
     *         The key of the animation to pause.
     */
    public void pauseAnimation(String key) {
        applyToAnimation(key, Animation::pause);
    }

    /**
     * Pauses all animations managed by this AnimationManager.
     */
    public void pauseAll() {
        applyToAllAnimations(Animation::pause);
    }

    /**
     * Stops an animation by its key.
     *
     * @param key
     *         The key of the animation to stop.
     */
    public void stopAnimation(String key) {
        applyToAnimation(key, Animation::stop);
    }

    /**
     * Stops all animations managed by this AnimationManager.
     */
    public void stopAll() {
        applyToAllAnimations(Animation::stop);
    }

    /**
     * Jumps to a specified time or cue point in the animation.
     *
     * @param key
     *         The key of the animation.
     * @param time
     *         The time to jump to.
     */
    public void jumpTo(String key, Duration time) {
        applyToAnimation(key, animation -> animation.jumpTo(time));
    }

    /**
     * Jumps to a specified cue point in the animation.
     *
     * @param key
     *         The key of the animation.
     * @param cuePoint
     *         The cue point to jump to.
     */
    public void jumpTo(String key, String cuePoint) {
        applyToAnimation(key, animation -> animation.jumpTo(cuePoint));
    }

    /**
     * Resets an animation to its start state.
     *
     * @param key
     *         The key of the animation.
     */
    public void resetAnimation(String key) {
        applyToAnimation(key, animation -> {
            animation.stop();
            animation.jumpTo(Duration.ZERO);
        });
    }

    /**
     * Checks if the specified animation is playing.
     *
     * @param key
     *         The key of the animation.
     *
     * @return true if the animation is playing, false otherwise.
     */
    public boolean isAnimationPlaying(String key) {
        return checkAnimationStatus(key, Animation.Status.RUNNING);
    }

    /**
     * Checks if the specified animation is stopped.
     *
     * @param key
     *         The key of the animation.
     *
     * @return true if the animation is stopped, false otherwise.
     */
    public boolean isAnimationStopped(String key) {
        return checkAnimationStatus(key, Animation.Status.STOPPED);
    }

    /**
     * Checks if the specified animation is paused.
     *
     * @param key
     *         The key of the animation.
     *
     * @return true if the animation is paused, false otherwise.
     */
    public boolean isAnimationPaused(String key) {
        return checkAnimationStatus(key, Animation.Status.PAUSED);
    }

    /**
     * Sets the rate of an animation.
     *
     * @param key
     *         The key of the animation.
     * @param rate
     *         The rate to set for the animation.
     */
    public void setAnimationRate(String key, double rate) {
        applyToAnimation(key, animation -> animation.setRate(rate));
    }

    /**
     * Sets the rate for all animations.
     *
     * @param rate
     *         The rate to set for all animations.
     */
    public void setAllAnimationRate(double rate) {
        applyToAllAnimations(animation -> animation.setRate(rate));
    }

    /**
     * Retrieves the rate property of an animation.
     *
     * @param key
     *         The key of the animation.
     *
     * @return The rate property of the animation.
     */
    public ReadOnlyDoubleProperty getAnimationRateProperty(String key) {
        return getAnimationProperty(key, Animation::rateProperty);
    }

    /**
     * Retrieves the total duration of an animation.
     *
     * @param key
     *         The key of the animation.
     *
     * @return The duration of the animation.
     */
    public Duration getAnimationDuration(String key) {
        return getAnimationPropertyValue(key, Animation::getTotalDuration);
    }

    /**
     * Retrieves the total duration property of an animation.
     *
     * @param key
     *         The key of the animation.
     *
     * @return The duration property of the animation.
     */
    public ReadOnlyObjectProperty<Duration> getAnimationDurationProperty(String key) {
        return getAnimationProperty(key, Animation::totalDurationProperty);
    }

    /**
     * Retrieves the current time of an animation.
     *
     * @param key
     *         The key of the animation.
     *
     * @return The current time of the animation.
     */
    public Duration getAnimationCurrentTime(String key) {
        return getAnimationPropertyValue(key, Animation::getCurrentTime);
    }

    /**
     * Retrieves the current time property of an animation.
     *
     * @param key
     *         The key of the animation.
     *
     * @return The current time property of the animation.
     */
    public ReadOnlyObjectProperty<Duration> getAnimationCurrentTimeProperty(String key) {
        return getAnimationProperty(key, Animation::currentTimeProperty);
    }

    /**
     * Retrieves the cycle count of an animation.
     *
     * @param key
     *         The key of the animation.
     *
     * @return The cycle count of the animation.
     */
    public int getAnimationCycleCount(String key) {
        return getAnimationPropertyValue(key, Animation::getCycleCount);
    }

    /**
     * Sets the cycle count for the animation identified by the given key.
     *
     * @param key
     *         the key identifying the animation
     * @param count
     *         the number of times the animation should repeat
     */
    public void setAnimationCycleCount(String key, int count) {
        applyToAnimation(key, animation -> animation.setCycleCount(count));
    }

    /**
     * Retrieves the cycle count property of an animation.
     *
     * @param key
     *         The key of the animation.
     *
     * @return The cycle count property of the animation.
     */
    public ReadOnlyIntegerProperty getAnimationCycleCountProperty(String key) {
        return getAnimationProperty(key, Animation::cycleCountProperty);
    }

    /**
     * Retrieves the animation delay for the specified key.
     *
     * @param key
     *         The key associated with the animation delay.
     *
     * @return The animation delay as a Duration.
     */
    public Duration getAnimationDelay(String key) {
        return getAnimationPropertyValue(key, Animation::getDelay);
    }

    /**
     * Sets the animation delay for the specified key.
     * <p>
     * The animation delay determines how long the animation should wait before starting.
     * </p>
     *
     * @param key
     *         the key of the animation
     * @param delay
     *         the duration of the delay
     */
    public void setAnimationDelay(String key, Duration delay) {
        applyToAnimation(key, animation -> animation.setDelay(delay));
    }

    /**
     * Returns the ObjectProperty of type Duration for the specified key.
     *
     * @param key
     *         the key used to retrieve the ObjectProperty
     *
     * @return the ObjectProperty of type Duration associated with the specified key
     */
    public ObjectProperty<Duration> getAnimationDelayProperty(String key) {
        return getAnimationProperty(key, Animation::delayProperty);
    }

    /**
     * Retrieves the current status of an animation with the specified key.
     *
     * @param key
     *         the key used to identify the animation
     *
     * @return the current status of the animation
     */
    public Animation.Status getAnimationStatus(String key) {
        return getAnimationPropertyValue(key, Animation::getStatus);
    }

    /**
     * This method retrieves the read-only object property representing the status of an animation with the specified key.
     *
     * @param key
     *         The key used to identify the animation.
     *
     * @return The read-only object property representing the status of the animation.
     */
    public ReadOnlyObjectProperty<Animation.Status> getAnimationStatusProperty(String key) {
        return getAnimationProperty(key, Animation::statusProperty);
    }

    /**
     * Sets the auto reverse flag for the animation specified by the given key.
     *
     * @param key
     *         the key of the animation to set the auto reverse flag for
     * @param autoReverse
     *         the value indicating whether the animation should be automatically reversed
     */
    public void setAnimationAutoReverse(String key, boolean autoReverse) {
        applyToAnimation(key, animation -> animation.setAutoReverse(autoReverse));
    }

    /**
     * Checks whether the animation reverses a direction on alternating cycles.
     *
     * @param key
     *         The key of the animation.
     *
     * @return True if the animation auto-reverses, false otherwise.
     */
    public boolean isAnimationAutoReversing(String key) {
        return getAnimationPropertyValue(key, Animation::isAutoReverse);
    }

    /**
     * Sets an EventHandler that will be called when the animation with the specified key finishes.
     *
     * @param key
     *         The key of the animation.
     * @param onFinishedHandler
     *         The event handler to be called on animation finish.
     */
    public void setOnFinished(String key, EventHandler<ActionEvent> onFinishedHandler) {
        applyToAnimation(key, animation -> animation.setOnFinished(onFinishedHandler));
    }

    /**
     * Gets the EventHandler that is called when the animation with the specified key finishes.
     *
     * @param key
     *         The key of the animation.
     *
     * @return The onFinished event handler, if set; otherwise, an empty Optional.
     */
    public Optional<EventHandler<ActionEvent>> getOnFinished(String key) {
        return Optional.ofNullable(getAnimationPropertyValue(key, Animation::getOnFinished));
    }

    /**
     * Adds or updates a cue point for the specified animation.
     *
     * @param key
     *         The key of the animation.
     * @param cuePointName
     *         The name of the cue point.
     * @param time
     *         The time of the cue point.
     */
    public void addCuePoint(String key, String cuePointName, Duration time) {
        applyToAnimation(key, animation -> animation.getCuePoints()
                                                    .put(cuePointName, time));
    }

    /**
     * Retrieves the time for a specified cue point in the animation.
     *
     * @param key
     *         The key of the animation.
     * @param cuePointName
     *         The name of the cue point.
     *
     * @return The time of the cue point, or null if not found.
     */
    public Duration getCuePoint(String key, String cuePointName) {
        return getAnimationPropertyValue(key, animation -> animation.getCuePoints()
                                                                    .get(cuePointName));
    }

    /**
     * Removes a cue point from the specified animation.
     *
     * @param key
     *         The key of the animation.
     * @param cuePointName
     *         The name of the cue points to remove.
     */
    public void removeCuePoint(String key, String cuePointName) {
        applyToAnimation(key, animation -> animation.getCuePoints()
                                                    .remove(cuePointName));
    }

    /**
     * Retrieves all cue points for a given key.
     *
     * @param key
     *         the key associated with the cue points
     *
     * @return an ObservableMap representing the cue points with their respective durations
     */
    public ObservableMap<String, Duration> getAllCuePoints(String key) {
        return getAnimationProperty(key, Animation::getCuePoints);
    }

    /**
     * Applies an action to an animation if it exists in the collection.
     *
     * @param key
     *         The key of the animation.
     * @param action
     *         The action to apply to the animation.
     */
    private void applyToAnimation(String key, Consumer<Animation> action) {
        Optional.ofNullable(animations.get(key))
                .ifPresentOrElse(action, () -> {throw new IllegalArgumentException(String.format(INVALID_ANIMATION_KEY, key));});
    }

    /**
     * Applies a consumer action (like play, pause, stop) to all animations.
     *
     * @param action
     *         The consumer action to apply to each animation.
     */
    private void applyToAllAnimations(Consumer<Animation> action) {
        animations.values()
                  .forEach(action);
    }

    /**
     * Checks the status of an animation using the specified key and status.
     *
     * @param key
     *         The key of the animation.
     * @param status
     *         The expected status of the animation.
     *
     * @return true if the animation matches the expected status, false otherwise.
     *
     * @throws IllegalArgumentException
     *         if the animation with the given key is not found.
     */
    private boolean checkAnimationStatus(String key, Animation.Status status) {
        return Optional.ofNullable(animations.get(key))
                       .map(anim -> anim.getStatus() == status)
                       .orElseThrow(() -> new IllegalArgumentException(String.format(INVALID_ANIMATION_KEY, key)));
    }

    /**
     * Retrieves a property of an animation using a functional interface.
     *
     * @param key
     *         The key of the animation.
     * @param propertyExtractor
     *         A function to extract the desired property from the animation.
     * @param <T>
     *         The type of the property.
     *
     * @return The property, or null if the animation does not exist.
     */
    private <T> T getAnimationProperty(String key, Function<Animation, T> propertyExtractor) {
        return extractFromAnimation(key, propertyExtractor);
    }

    /**
     * Retrieves the value of a property of an animation.
     *
     * @param key
     *         The key of the animation.
     * @param valueExtractor
     *         A function to extract the value of the desired property from the animation.
     * @param <T>
     *         The type of the property value.
     *
     * @return The value of the property.
     */
    public <T> T getAnimationPropertyValue(String key, Function<Animation, T> valueExtractor) {
        return extractFromAnimation(key, valueExtractor);
    }

    /**
     * Extracts a value from an Animation object using the provided function.
     *
     * @param key
     *         The key associated with the Animation object.
     * @param extractor
     *         The function used to extract the desired value from the Animation object.
     * @param <T>
     *         The type of the extracted value.
     *
     * @return The extracted value.
     *
     * @throws IllegalArgumentException
     *         if the Animation object with the given key is not found.
     */
    private <T> T extractFromAnimation(String key, Function<Animation, T> extractor) {
        return Optional.ofNullable(animations.get(key))
                       .map(extractor)
                       .orElseThrow(() -> new IllegalArgumentException(String.format(INVALID_ANIMATION_KEY, key)));
    }
}

