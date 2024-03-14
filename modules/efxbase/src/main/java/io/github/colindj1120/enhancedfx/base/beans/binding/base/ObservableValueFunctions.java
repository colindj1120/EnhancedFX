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
package io.github.colindj1120.enhancedfx.base.beans.binding.base;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Binding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Subscription;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * The {@code ObservableValueFunctions} interface provides a unified set of default methods for interacting with
 * {@link ObservableValue} and {@link Binding} instances in JavaFX. It abstracts common functionalities such as adding and removing
 * listeners, retrieving the current value, and transforming the observable values through mapping and flat-mapping. Additionally, it
 * offers utility methods for conditional logic and subscription management, facilitating a more expressive and fluent API for reactive
 * programming patterns within JavaFX applications.
 *
 * <p>
 * <h2>Capabilities include:</h2>
 * <ul>
 *   <li><b>Listener Management:</b> Easily add or remove change and invalidation listeners to react to changes in the observables
 *       value or its invalidation events.</li>
 *   <li><b>Value Access:</b> Retrieve the current value held by the observable or binding, enabling direct use in application logic
 *   .</li>
 *   <li><b>Value Transformation:</b> Apply mapping, flat-mapping, and conditional operations to transform the observables value,
 *       supporting complex reactive chains and logic constructs.</li>
 *   <li><b>Subscription Management:</b> Subscribe to changes or invalidations with simplified API calls, supporting both simple
 *       runnable and consumer-based handlers for more nuanced reaction to updates.</li>
 * </ul>
 * </p>
 *
 * @param <T>
 *         the type of the value held by the observable, enabling generic handling of any observable value type
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Binding
 * @see ObservableValue
 */
public interface ObservableValueFunctions<T> {
    /**
     * Retrieves the underlying binding instance.
     *
     * @return the current {@link Binding<T>} instance
     */
    Binding<T> getBinding();

    /**
     * Adds a listener that will be notified whenever the value of the {@link Binding} changes.
     *
     * @param changeListener
     *         the listener to add
     */
    default void addListener(ChangeListener<? super T> changeListener) {
        getBinding().addListener(changeListener);
    }

    /**
     * Adds a listener that will be notified whenever the {@link Binding} is invalidated.
     *
     * @param invalidationListener
     *         the listener to add
     */
    default void addListener(InvalidationListener invalidationListener) {
        getBinding().addListener(invalidationListener);
    }

    /**
     * Removes a previously added change listener from the {@link Binding}.
     *
     * @param changeListener
     *         the listener to remove
     */
    default void removeListener(ChangeListener<? super T> changeListener) {
        getBinding().removeListener(changeListener);
    }

    /**
     * Removes a previously added invalidation listener from the {@link Binding}.
     *
     * @param invalidationListener
     *         the listener to remove
     */
    default void removeListener(InvalidationListener invalidationListener) {
        getBinding().removeListener(invalidationListener);
    }

    /**
     * Returns the current value of this {@link Binding}.
     *
     * @return the current value
     */
    default T getValue() {
        return getBinding().getValue();
    }

    /**
     * Returns a new {@link ObservableValue} that maps the current {@link Binding}'s value to a new form.
     *
     * @param mapper
     *         a function to map the value of this {@link Binding} to a different form
     * @param <U>
     *         the type of the mapped value
     *
     * @return a new {@link ObservableValue<U>} instance
     */
    default <U> ObservableValue<U> map(Function<? super T, ? extends U> mapper) {
        return getBinding().map(mapper);
    }

    /**
     * Returns a new {@link ObservableValue} that will hold a constant value if the current {@link Binding}'s value is {@code null}.
     *
     * @param constant
     *         the value to return if the current value is {@code null}
     *
     * @return a new {@link ObservableValue<T>} instance
     */
    default ObservableValue<T> orElse(T constant) {
        return getBinding().orElse(constant);
    }

    /**
     * Returns a new {@link ObservableValue} that maps the current {@link Binding}'s value to a new {@link ObservableValue}.
     *
     * @param mapper
     *         a function to map the value of this {@link Binding} to a new {@link ObservableValue}
     * @param <U>
     *         the type of the mapped value contained within the new {@link ObservableValue}
     *
     * @return a new {@link ObservableValue<U>} instance
     */
    default <U> ObservableValue<U> flatMap(Function<? super T, ? extends ObservableValue<? extends U>> mapper) {
        return getBinding().flatMap(mapper);
    }

    /**
     * Returns a new {@link ObservableValue} that reflects the value of this {@link Binding} when a specified condition is
     * {@code true}.
     *
     * @param condition
     *         the condition upon which the returned {@link ObservableValue}'s value is based
     *
     * @return a new {@link ObservableValue<T>} instance
     */
    default ObservableValue<T> when(ObservableValue<Boolean> condition) {
        return getBinding().when(condition);
    }

    /**
     * Subscribes to invalidation events of this {@link Binding}.
     *
     * @param invalidationSubscriber
     *         the runnable to execute upon invalidation
     *
     * @return a {@link Subscription} instance for unsubscribing
     */
    default Subscription subscribe(Runnable invalidationSubscriber) {
        return getBinding().subscribe(invalidationSubscriber);
    }

    /**
     * Subscribes to changes in the value of this {@link Binding}.
     *
     * @param valueSubscriber
     *         the consumer to notify upon value changes
     *
     * @return a {@link Subscription} instance for unsubscribing
     */
    default Subscription subscribe(Consumer<? super T> valueSubscriber) {
        return getBinding().subscribe(valueSubscriber);
    }

    /**
     * Subscribes to changes in the value of this {@link Binding}, receiving both the old and new values.
     *
     * @param changeSubscriber
     *         the bi-consumer to notify upon value changes, receiving both the old and new value
     *
     * @return a {@link Subscription} instance for unsubscribing
     */
    default Subscription subscribe(BiConsumer<? super T, ? super T> changeSubscriber) {
        return getBinding().subscribe(changeSubscriber);
    }

}
