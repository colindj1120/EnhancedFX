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
package io.github.colindj1120.enhancedfx.base.factory.property.base;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The {@code CustomPropertyConfig} interface extends {@link BasePropertyConfig} to provide advanced configuration options for JavaFX {@link Property} instances. It offers enhanced capabilities for adding and
 * removing listeners, binding properties conditionally, and performing utility operations that facilitate sophisticated property manipulation.
 *
 * <p>Implementations of this interface allow for detailed configuration and management of properties, including conditional bindings, specific value change listeners, and synchronization with other
 * properties. This makes {@code CustomPropertyConfig} a powerful tool for developing dynamic and responsive JavaFX applications.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Adds and removes change and invalidation listeners to properties.</li>
 *     <li>Supports binding properties both unidirectionally and bidirectionally, including conditional bindings.</li>
 *     <li>Enables property value validation and specific value change actions, enhancing control over property behavior.</li>
 *     <li>Facilitates synchronization between properties, allowing transformations and ensuring consistent state across properties.</li>
 *     <li>Provides utility functions to perform actions upon property changes, increasing the reactivity of the application.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * CustomPropertyConfig<String, PropertyConfiguratorBase<String>> config = // Initialization
 *
 * // Add a listener that performs an action when the property's value changes to "Completed"
 * config.addChangeListenerForSpecificValue("Completed", () -> System.out.println("Task completed!"));
 *
 * // Validate the new property value before accepting it, reverting to the old value on invalid input
 * config.addChangeListenerValidator(value -> value != null && !value.trim().isEmpty(),
 *                                   invalidValue -> System.out.println("Invalid input detected"));
 *
 * // Bind this property conditionally to another property based on a specific condition
 * Property<String> otherProperty = new SimpleStringProperty("Initial value");
 * config.bindConditional(otherProperty, value -> value.startsWith("Valid"));
 * }
 * </pre>
 *
 * <p>This example demonstrates configuring a {@code StringProperty} using {@code CustomPropertyConfig}, including adding specific value change listeners, validating property values, and conditionally
 * binding the property to another property.</p>
 *
 * @param <T>
 *         the type of the value that the {@link Property} holds
 * @param <R>
 *         the specific configurator type associated with the property
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BasePropertyConfig
 * @see Property
 * @see ChangeListener
 * @see InvalidationListener
 */
@SuppressWarnings("UnusedReturnValue")
public interface CustomPropertyConfig<T, R extends PropertyConfiguratorBase<T>> extends BasePropertyConfig<T, R> {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a change listener to the property.
     *
     * @param listener
     *         the change listener to add
     *
     * @return the configurator instance for chaining
     */
    default R addChangeListener(ChangeListener<? super T> listener) {
        getProperty().addListener(listener);
        return getConfigurator();
    }

    /**
     * Adds an invalidation listener to the property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the configurator instance for chaining
     */
    default R addInvalidationListener(InvalidationListener invalidationListener) {
        getProperty().addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a change listener that triggers a given action if the property's value changes to a specific value.
     *
     * @param specificValue
     *         the specific value to trigger the action
     * @param action
     *         the action to run
     *
     * @return the configurator instance for chaining
     */
    default R addChangeListenerForSpecificValue(T specificValue, Runnable action) {
        getProperty().addListener((obs, oldValue, newValue) -> {
            if (Objects.equals(newValue, specificValue)) {
                action.run();
            }
        });
        return getConfigurator();
    }

    /**
     * Adds a change listener that validates the property's new value with a given predicate, and executes a consumer if the value is invalid.
     *
     * @param validator
     *         the predicate to test the new value
     * @param onInvalidValue
     *         the consumer to execute if the value is invalid
     *
     * @return the configurator instance for chaining
     */
    default R addChangeListenerValidator(Predicate<T> validator, Consumer<T> onInvalidValue) {
        getProperty().addListener((obs, oldValue, newValue) -> {
            if (!validator.test(newValue)) {
                onInvalidValue.accept(newValue);
            }
        });
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a change listener from the property.
     *
     * @param listener
     *         the change listener to remove
     *
     * @return the configurator instance for chaining
     */
    default R removeChangeListener(ChangeListener<? super T> listener) {
        getProperty().removeListener(listener);
        return getConfigurator();
    }

    /**
     * Removes an invalidation listener from the property.
     *
     * @param listener
     *         the invalidation listener to remove
     *
     * @return the configurator instance for chaining
     */
    default R removeInvalidationListener(InvalidationListener listener) {
        getProperty().removeListener(listener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Bind Functions
    //*****************************************************************
    // Bind Functions
    //*****************************************************************

    /**
     * Binds the property to another property.
     *
     * @param otherProperty
     *         the other property to bind to
     *
     * @return the configurator instance for chaining
     */
    default R bind(Property<T> otherProperty) {
        getProperty().bind(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the property from its current binding.
     *
     * @return the configurator instance for chaining
     */
    default R unbind() {
        getProperty().unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between this property and another property.
     *
     * @param otherProperty
     *         the other property to bind with
     *
     * @return the configurator instance for chaining
     */
    default R bindBidirectional(Property<T> otherProperty) {
        getProperty().bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the property bidirectionally from another property.
     *
     * @param otherProperty
     *         the other property to unbind from
     *
     * @return the configurator instance for chaining
     */
    default R unbindBidirectional(Property<T> otherProperty) {
        getProperty().unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Conditionally binds the property to another property based on a given condition.
     *
     * @param otherProperty
     *         the other property to conditionally bind to
     * @param condition
     *         the condition that controls the binding
     *
     * @return the configurator instance for chaining
     */
    default R bindConditional(Property<T> otherProperty, Predicate<T> condition) {
        getProperty().addListener((obs, oldValue, newValue) -> {
            if (condition.test(newValue)) {
                getProperty().bind(otherProperty);
            } else {
                getProperty().unbind();
                getProperty().setValue(newValue); // Reset to the last value that doesn't meet the condition
            }
        });
        return getConfigurator();
    }

    //endregion Bind Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the value of the property.
     *
     * @param value
     *         the new value to set
     *
     * @return the configurator instance for chaining
     */
    default R setValue(T value) {
        getProperty().setValue(value);
        return getConfigurator();
    }

    //endregion Set Functions

    //region Utility Functions
    //*****************************************************************
    // Utility Functions
    //*****************************************************************

    /**
     * Synchronizes this property with another property, transforming its value using a specified function.
     *
     * @param <U>
     *         the type of the source property
     * @param sourceProperty
     *         the source property to synchronize with
     * @param transformation
     *         the function to transform the source property's value
     *
     * @return the configurator instance for chaining
     */
    default <U> R syncWith(Property<U> sourceProperty, Function<U, T> transformation) {
        sourceProperty.addListener((obs, oldValue, newValue) -> {
            getProperty().setValue(transformation.apply(newValue));
        });
        return getConfigurator();
    }

    /**
     * Adds a listener that executes a given action when the property's value changes.
     *
     * @param action
     *         the action to perform on value change
     *
     * @return the configurator instance for chaining
     */
    default R onPropertyChange(Consumer<T> action) {
        getProperty().addListener((obs, oldValue, newValue) -> action.accept(newValue));
        return getConfigurator();
    }

    //endregion Utility Functions
}
