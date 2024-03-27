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
package io.github.colindj1120.enhancedfx.base.beans.binding.base.observablefunctions;

import io.github.colindj1120.enhancedfx.base.beans.binding.base.FunctionsBase;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Subscription;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Provides an interface defining essential functionalities for interacting with {@link ObservableValue} instances. This interface extends {@link FunctionsBase} to incorporate methods for listening to value
 * changes or invalidations, as well as utilities for transforming and chaining observable values. It facilitates advanced operations and manipulations on observable values, making it a fundamental part of the
 * EnhancedFX binding framework.
 *
 * <h2>Functionalities include:</h2>
 * <ul>
 *     <li>Adding and removing listeners for change and invalidation events.</li>
 *     <li>Retrieving the current value held by the {@code ObservableValue}.</li>
 *     <li>Applying transformation functions to the value.</li>
 *     <li>Combining or chaining observable values based on conditions or mappings.</li>
 *     <li>Subscribing to change or invalidation events with more flexible lambda expressions.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * ObservableValue<String> stringValue = new SimpleStringProperty("Hello, world!");
 * ObservableValueFunctions<String, ObservableValue<String>> stringFunctions = () -> stringValue;
 *
 * // Listen to value changes
 * stringFunctions.addListener((obs, oldVal, newVal) -> System.out.println("New value: " + newVal));
 *
 * // Transform the observable value
 * ObservableValue<Integer> lengthValue = stringFunctions.map(String::length);
 *
 * // Use conditionals
 * ObservableValue<String> conditionalValue = stringFunctions.when(new SimpleBooleanProperty(true)).orElse("Default");
 * }</pre>
 *
 * <p>This example illustrates several operations that can be performed using {@code ObservableValueFunctions}, including adding listeners, transforming values, and applying conditional logic.</p>
 *
 * @param <T>
 *         The type of the value held by the {@code ObservableValue}.
 * @param <R>
 *         The specific type of {@code ObservableValue} being managed.
 *
 * @see ObservableValue
 * @see FunctionsBase
 */
public interface ObservableValueFunctions<T, R extends ObservableValue<T>> extends FunctionsBase<T, R> {
    /**
     * Adds a {@link ChangeListener} to observe changes to the value of the observable. The listener is called whenever the value of the observable changes.
     *
     * @param changeListener
     *         the listener to add, not null
     */
    default void addListener(ChangeListener<? super T> changeListener) {
        getObservableValue().addListener(changeListener);
    }

    /**
     * Adds an {@link InvalidationListener} to listen for any invalidations to the observable. The listener is called whenever the observable becomes invalid.
     *
     * @param invalidationListener
     *         the listener to add, not null
     */
    default void addListener(InvalidationListener invalidationListener) {
        getObservableValue().addListener(invalidationListener);
    }

    /**
     * Removes a previously added {@link ChangeListener}.
     *
     * @param changeListener
     *         the listener to remove, not null
     */
    default void removeListener(ChangeListener<? super T> changeListener) {
        getObservableValue().removeListener(changeListener);
    }

    /**
     * Removes a previously added {@link InvalidationListener}.
     *
     * @param invalidationListener
     *         the listener to remove, not null
     */
    default void removeListener(InvalidationListener invalidationListener) {
        getObservableValue().removeListener(invalidationListener);
    }

    /**
     * Retrieves the current value of the observable.
     *
     * @return the current value
     */
    default T getValue() {
        return getObservableValue().getValue();
    }

    /**
     * Transforms the value of this observable into another type using the provided mapping function.
     *
     * @param <U>
     *         the type of the mapped value
     * @param mapper
     *         the mapping function to transform the observable value, not null
     *
     * @return a new {@link ObservableValue} instance containing the transformed value
     */
    default <U> ObservableValue<U> map(Function<? super T, ? extends U> mapper) {
        return getObservableValue().map(mapper);
    }

    /**
     * Provides an alternative value if the observables value is null.
     *
     * @param constant
     *         the value to return if the current observables value is null, may be null
     *
     * @return a new {@link ObservableValue} instance that will never hold a null value
     */
    default ObservableValue<T> orElse(T constant) {
        return getObservableValue().orElse(constant);
    }

    /**
     * Transforms the observables value into another observable using the provided mapping function. Useful for chaining observable values.
     *
     * @param <U>
     *         the type parameter of the returned {@link ObservableValue}
     * @param mapper
     *         the function to apply to this observables value, not null
     *
     * @return a new {@link ObservableValue} instance containing the value resulting from the transformation
     */
    default <U> ObservableValue<U> flatMap(Function<? super T, ? extends ObservableValue<? extends U>> mapper) {
        return getObservableValue().flatMap(mapper);
    }

    /**
     * Applies a conditional check to this observables value, enabling complex conditional logic in bindings.
     *
     * @param condition
     *         the conditional {@link ObservableValue} that determines which value should be used, not null
     *
     * @return a new {@link ObservableValue} that reflects the outcome of the condition
     */
    default ObservableValue<T> when(ObservableValue<Boolean> condition) {
        return getObservableValue().when(condition);
    }

    /**
     * Subscribes to this observables invalidation events with a runnable that gets executed whenever the observable is invalidated.
     *
     * @param invalidationSubscriber
     *         the runnable to execute upon invalidation, not null
     *
     * @return a {@link Subscription} that can be used to unsubscribe from the observable
     */
    default Subscription subscribe(Runnable invalidationSubscriber) {
        return getObservableValue().subscribe(invalidationSubscriber);
    }

    /**
     * Subscribes to this observables value changes with a consumer that accepts the new value whenever it changes.
     *
     * @param valueSubscriber
     *         the consumer to execute with the new value upon change, not null
     *
     * @return a {@link Subscription} that can be used to unsubscribe from the observable
     */
    default Subscription subscribe(Consumer<? super T> valueSubscriber) {
        return getObservableValue().subscribe(valueSubscriber);
    }

    /**
     * Subscribes to this observables value changes with a bi-consumer that accepts both the old and new value upon change.
     *
     * @param changeSubscriber
     *         the bi-consumer to execute with the old and new values upon change, not null
     *
     * @return a {@link Subscription} that can be used to unsubscribe from the observable
     */
    default Subscription subscribe(BiConsumer<? super T, ? super T> changeSubscriber) {
        return getObservableValue().subscribe(changeSubscriber);
    }

}
