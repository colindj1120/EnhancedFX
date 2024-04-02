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
package io.github.colindj1120.enhancedfx.utils;

import javafx.beans.binding.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@code EFXWhenUtility} is a flexible utility class designed to facilitate the creation of dynamic and complex conditional bindings in JavaFX applications.
 *
 * <p>It encapsulates the logic of binding observable values based on a series of boolean conditions, offering a fluent and readable API. This class significantly simplifies the process of setting up
 * conditional bindings, thus enhancing code readability and maintainability.</p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Fluent API:</b> Provides a fluent interface for defining conditional bindings, allowing for easy chaining of multiple conditions and values.</li>
 *   <li><b>Support for Various Binding Types:</b> Capable of creating {@link ObjectBinding}, {@link DoubleBinding}, {@link StringBinding}, and {@link BooleanBinding}, making it versatile for different data
 *       types and use cases.</li>
 *   <li><b>Customizable Fallback Values:</b> Offers the ability to specify default values or observable values to be used when none of the conditions are satisfied.</li>
 *   <li><b>Dynamic Value Computation:</b> Ensures the values are dynamically computed based on the current state of conditions, providing up-to-date results.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <pre>
 * {@code
 *     EFXWhenUtility.when(someCondition, someValue)
 *                   .andWhen(anotherCondition, anotherValue)
 *                   .otherwise(fallbackValue)
 *                   .asObjectBinding();
 * }
 * </pre>
 *
 * <p>This class is particularly useful for JavaFX applications where UI elements need to react to changes in state or context, governed by complex conditional logic. It abstracts the underlying complexities
 * of JavaFX bindings, offering a cleaner and more expressive way to handle conditional logic in UI development.</p>
 *
 * @param <T>
 *         The type of the value to be bound, providing flexibility in binding different types of values.
 *
 * @author Colin Jokisch
 * @version 1.5.0
 * @see ObjectBinding
 * @see DoubleBinding
 * @see FloatBinding
 * @see IntegerBinding
 * @see LongBinding
 * @see StringBinding
 * @see BooleanBinding
 */
public class EFXWhenUtility<T> {
    private final List<ConditionValueBindingPair<T>>     conditionValuePairs;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private       Optional<Supplier<ObservableValue<T>>> otherwiseValueSupplier = Optional.empty();

    /**
     * Private constructor for {@code EFXWhenUtility}.
     *
     * <p>It initializes the list of condition-value binding pairs. This constructor is private because the {@code EFXWhenUtility} class uses a static factory method for instantiation to provide a fluent API
     * for the users.</p>
     */
    private EFXWhenUtility() {
        conditionValuePairs = new ArrayList<>();
    }

    /**
     * Creates a new instance of {@link EFXWhenUtility} with the first condition-value pair. This static factory method is the entry point for creating a {@code EFXWhenUtility} instance. It initializes the
     * utility with a given condition and its corresponding value.
     *
     * @param <T>
     *         The type of the value to be bound.
     * @param condition
     *         The initial condition as an {@link ObservableValue} of {@link Boolean}.
     * @param value
     *         The value to be returned when the condition is true, as an {@link ObservableValue}.
     *
     * @return An instance of {@link EFXWhenUtility} with the first condition-value pair added.
     */
    public static <T> EFXWhenUtility<T> when(final ObservableValue<Boolean> condition, final ObservableValue<T> value) {
        EFXWhenUtility<T> efxWhenUtility = new EFXWhenUtility<>();
        return efxWhenUtility.andWhen(condition, value);
    }

    /**
     * Adds another condition-value pair to this {@code EFXWhenUtility} instance. This method allows chaining multiple conditions. If the condition is true, the corresponding value is used in the binding.
     *
     * @param condition
     *         The additional condition as an {@link ObservableValue} of {@link Boolean}.
     * @param value
     *         The value to be returned when the provided condition is true, as an {@link ObservableValue}.
     *
     * @return The current instance of {@link EFXWhenUtility} for chaining multiple condition-value pairs.
     */
    public EFXWhenUtility<T> andWhen(final ObservableValue<Boolean> condition, final ObservableValue<T> value) {
        conditionValuePairs.add(new ConditionValueBindingPair<>(condition, value));
        return this;
    }

    /**
     * Specifies a default value to be used if none of the conditions in the {@link EFXWhenUtility} instance are met. This method sets a static value as the fallback option. The value is wrapped within a
     * {@link ReadOnlyObjectWrapper} to create an {@link ObservableValue}.
     *
     * <p>
     * <em>Example Usage:</em>
     * <pre>
     * {@code
     *     EFXWhenUtility.when(condition, value)
     *                   .otherwise(defaultValue)
     *                   .asObjectBinding();
     * }
     * </pre>
     * </p>
     *
     * @param value
     *         The default value to be used if all conditions are false.
     *
     * @return The current instance of {@link EFXWhenUtility} for further chaining or for final binding creation.
     */
    public EFXWhenUtility<T> otherwise(final T value) {
        this.otherwiseValueSupplier = Optional.of(() -> new ReadOnlyObjectWrapper<>(value));
        return this;
    }

    /**
     * Similar to the {@link #otherwise(Object)} method, but allows providing a dynamic {@link ObservableValue} as the default fallback.
     *
     * <p>This is useful when the default value is not static and needs to be computed or can change over time.</p>
     *
     * <p>
     * <em>Example Usage:</em>
     * <pre>
     * {@code
     *     EFXWhenUtility.when(condition, value)
     *                   .otherwise(dynamicObservableValue)
     *                   .asObjectBinding();
     * }
     * </pre>
     * </p>
     *
     * @param value
     *         The default {@link ObservableValue} to be used if all conditions are false.
     *
     * @return The current instance of {@link EFXWhenUtility} for further chaining or for final binding creation.
     */
    public EFXWhenUtility<T> otherwise(final ObservableValue<T> value) {
        this.otherwiseValueSupplier = Optional.of(() -> value);
        return this;
    }

    /**
     * Creates an {@link ObjectBinding} based on the specified conditions and values.
     *
     * <p>If a condition is true, the corresponding value is returned. If no conditions are met, null is returned. This method is a convenience wrapper around {@link #getObjectBinding(Object)} with a null
     * default value.</p>
     *
     * @return An {@link ObjectBinding} that computes its value based on the specified conditions.
     */
    public ObjectBinding<T> asObjectBinding() {
        return getObjectBinding(null);
    }

    /**
     * Similar to {@link #asObjectBinding()}, but allows specifying a default value to return when all conditions evaluate to false.
     *
     * @param nullValue
     *         The value to return when no conditions are met.
     *
     * @return An {@link ObjectBinding} with a specified default value.
     */
    public ObjectBinding<T> asObjectBinding(final T nullValue) {
        return getObjectBinding(nullValue);
    }

    /**
     * A private helper method that creates an {@link ObjectBinding}.
     *
     * <p>This method binds to each condition and value pair and dynamically computes the value based on the first true condition. If no conditions are true, a specified default value is returned.</p>
     *
     * @param nullValue
     *         The value to return when no conditions are met.
     *
     * @return An {@link ObjectBinding} based on the conditions and values specified.
     */
    @NotNull
    private ObjectBinding<T> getObjectBinding(final T nullValue) {
        return new ObjectBinding<>() {
            {
                // Bind to each condition-value pair
                conditionValuePairs.forEach(pair -> {
                    bind(pair.condition);
                    bind(pair.value);
                });
                // Bind to the otherwise supplier if present
                otherwiseValueSupplier.ifPresent(supplier -> bind(supplier.get()));
            }

            @Override
            protected T computeValue() {
                // Compute the value based on the first true condition or return the default value
                return conditionValuePairs.stream()
                                          .filter(pair -> Boolean.TRUE.equals(pair.condition.getValue()))
                                          .findFirst()
                                          .map(pair -> pair.value.getValue())
                                          .orElseGet(() -> otherwiseValueSupplier.map(Supplier::get)
                                                                                 .map(ObservableValue::getValue)
                                                                                 .orElse(nullValue));
            }

            @Override
            public void dispose() {
                // Unbind when disposed
                conditionValuePairs.forEach(pair -> {
                    unbind(pair.condition);
                    unbind(pair.value);
                });
                otherwiseValueSupplier.ifPresent(supplier -> unbind(supplier.get()));
            }
        };
    }

    /**
     * Creates a {@link DoubleBinding} based on the specified conditions and values.
     *
     * <p>If a condition is true and the corresponding value is a number, its double value is returned. Otherwise, {@link Double#NaN} is returned. This method is a convenience wrapper around
     * {@link #getDoubleBinding(double)} with Double.NaN as the default value.</p>
     *
     * @return A {@link DoubleBinding} that computes its value based on the specified conditions.
     */
    public DoubleBinding asDoubleBinding() {
        return getDoubleBinding(Double.NaN);
    }

    /**
     * Similar to {@link #asDoubleBinding()}, but allows specifying a custom value to return when the value is not a number or no conditions are met.
     *
     * @param nullValue
     *         The value to return when the binding's value is not a number or no conditions are met.
     *
     * @return A {@link DoubleBinding} with a specified default value.
     */
    public DoubleBinding asDoubleBinding(final double nullValue) {
        return getDoubleBinding(nullValue);
    }

    /**
     * A private helper method that creates a {@link DoubleBinding}.
     *
     * <p>This method binds to a base {@link ObjectBinding} and computes a double value based on the first true condition's value. If the value is not a number or no conditions are met, a specified default
     * value is returned.</p>
     *
     * @param nullValue
     *         The value to return when the binding's value is not a number or no conditions are met.
     *
     * @return A {@link DoubleBinding} based on the specified conditions and values.
     */
    @NotNull
    private DoubleBinding getDoubleBinding(final double nullValue) {
        ObjectBinding<T> baseBinding = asObjectBinding();
        return new DoubleBinding() {
            {
                bind(baseBinding);
            }

            @Override
            protected double computeValue() {
                // Compute the double value or return the default value
                return Optional.ofNullable(baseBinding.getValue())
                               .map(value -> value instanceof Number num ? num.doubleValue() : Double.NaN)
                               .orElse(nullValue);
            }
        };
    }

    /**
     * Creates a {@link StringBinding} based on the specified conditions and values.
     *
     * <p>The binding evaluates the conditions and returns the {@code toString()} representation of the corresponding value for the first true condition. If no conditions are met, an empty string is
     * returned.</p>
     *
     * @return A {@link StringBinding} representing the string value of the first true condition's value.
     */
    public StringBinding asStringBinding() {
        return getStringBinding("");
    }

    /**
     * Similar to {@link #asStringBinding()}, but allows specifying a custom string to return when the binding resolves to null.
     *
     * @param nullValue
     *         The string value to return when the binding resolves to null.
     *
     * @return A {@link StringBinding} that returns a custom null value string if no conditions are met.
     */
    public StringBinding asStringBinding(final String nullValue) {
        return getStringBinding(nullValue);
    }

    /**
     * A private helper method to create a {@link StringBinding}. It forms the core logic for {@link #asStringBinding()} and {@link #asStringBinding(String)}.
     *
     * @param nullValue
     *         The string to return when the binding resolves to null.
     *
     * @return A {@link StringBinding} based on the conditions and values specified.
     */
    @NotNull
    private StringBinding getStringBinding(final String nullValue) {
        ObjectBinding<T> baseBinding = asObjectBinding();
        return Bindings.createStringBinding(() -> Optional.ofNullable(baseBinding.getValue())
                                                          .map(Object::toString)
                                                          .orElse(nullValue), baseBinding);
    }

    /**
     * Creates a {@link BooleanBinding} based on the specified conditions.
     *
     * <p>The binding evaluates the conditions and returns true if the value of the first true condition is {@link Boolean#TRUE}, otherwise false.</p>
     *
     * @return A {@link BooleanBinding} that evaluates to true if the first true condition's value is Boolean.TRUE.
     */
    public BooleanBinding asBooleanBinding() {
        return getBooleanBinding(false);
    }

    /**
     * Similar to {@link #asBooleanBinding()}, but allows specifying a custom boolean value to return when the binding resolves to null.
     *
     * @param nullValue
     *         The boolean value to return when the binding resolves to null.
     *
     * @return A {@link BooleanBinding} that returns a specified boolean value if no conditions are met.
     */
    public BooleanBinding asBooleanBinding(final boolean nullValue) {
        return getBooleanBinding(nullValue);
    }

    /**
     * A private helper method to create a {@link BooleanBinding}. It forms the core logic for {@link #asBooleanBinding()} and {@link #asBooleanBinding(boolean)}.
     *
     * @param nullValue
     *         The boolean value to return when the binding resolves to null.
     *
     * @return A {@link BooleanBinding} that evaluates to a specified boolean value based on the conditions.
     */
    @NotNull
    private BooleanBinding getBooleanBinding(final boolean nullValue) {
        ObjectBinding<T> baseBinding = asObjectBinding();
        return new BooleanBinding() {
            {
                bind(baseBinding);
            }

            @Override
            protected boolean computeValue() {
                return Optional.ofNullable(baseBinding.getValue())
                               .map(Boolean.TRUE::equals)
                               .orElse(nullValue);
            }
        };
    }

    /**
     * A record that pairs an {@link ObservableValue} representing a condition with an {@link ObservableValue} representing the value to be used if the condition is true.
     *
     * <p>This record is used internally in the {@link EFXWhenUtility} class to manage condition-value bindings.</p>
     *
     * @param <T>
     *         The type of value associated with the condition.
     * @param condition
     *         The condition as an {@link ObservableValue} of {@link Boolean}.
     * @param value
     *         The value associated with the condition, as an {@link ObservableValue}.
     */
    private record ConditionValueBindingPair<T>(ObservableValue<Boolean> condition, ObservableValue<T> value) {}
}
