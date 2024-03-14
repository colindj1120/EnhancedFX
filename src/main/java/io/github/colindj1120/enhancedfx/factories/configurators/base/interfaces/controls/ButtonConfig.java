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
package io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.controls;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

/**
 * The {@code ButtonConfig} interface defines a fluent API for configuring button properties in JavaFX applications, particularly focusing on setting and responding to changes in default and cancel button
 * states. It offers a set of methods to add or remove {@link ChangeListener}s and {@link InvalidationListener}s that react to changes in these button properties, as well as methods to directly set the states
 * of buttons as default or cancel buttons.
 *
 * <p>
 * Implementations of this interface allow for a declarative and chainable way to configure button behaviors, making it easier to manage the interaction patterns and UI logic in a JavaFX application. This
 * interface facilitates the creation of more dynamic, responsive, and intuitive user interfaces by enabling developers to succinctly express the configuration of button states and their reactions to state
 * changes.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <p>
 * <ul>
 *     <li>Method chaining for fluid configuration.</li>
 *     <li>Support for adding and removing listeners to button properties.</li>
 *     <li>Support for binding and unbinding to button properties.</li>
 *     <li>Ability to set buttons as default or cancel buttons directly.</li>
 * </ul>
 * Through its design, {@code ButtonConfig} supports the development of complex JavaFX UIs with minimal boilerplate, promoting a clean and intuitive approach to UI configuration.
 * </p>
 *
 * <p>
 * This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of {@link Button} nodes. It serves as a foundational component of the EnhancedFX
 * library, which seeks to provide extended functionality and utilities beyond the core JavaFX library.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Button
 * @see ChangeListener
 * @see InvalidationListener
 */
public interface ButtonConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to monitor changes to the property indicating whether this button is the default button.
     *
     * @param changeListener
     *         the listener to be notified when the default button property changes
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig addDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations on the default button property.
     *
     * @param invalidationListener
     *         the listener to be notified of invalidation
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig addDefaultButtonInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} to monitor changes to the property indicating whether this button is the cancel button.
     *
     * @param changeListener
     *         the listener to be notified when the cancel button property changes
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig addCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations on the cancel button property.
     *
     * @param invalidationListener
     *         the listener to be notified of invalidation
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig addCancelButtonInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the property indicating whether this button is the default button.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig removeDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the default button property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig removeDefaultButtonInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the property indicating whether this button is the cancel button.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig removeCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the cancel button property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig removeCancelButtonInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //Default Button Property

    /**
     * Binds the default button property of the button to an {@link ObservableValue}. This means the default button state of the button will dynamically reflect the value of the provided observable.
     *
     * @param observableValue
     *         the {@code ObservableValue} to bind to the default button property.
     *
     * @return the {@code ButtonConfig} instance, allowing for method chaining.
     */
    ButtonConfig bindDefaultButtonProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the default button property, reverting it to its original state before the binding was applied. This method allows for dynamic changes to the binding of the default button property.
     *
     * @return the {@code ButtonConfig} instance, allowing for method chaining.
     */
    ButtonConfig unbindDefaultButtonProperty();

    /**
     * Establishes a bidirectional binding between the button's default button property and another {@code Property<Boolean>}. This ensures that changes to either the button's default button state or the other
     * property are reflected across both, keeping them synchronized.
     *
     * @param otherProperty
     *         the {@code Property<Boolean>} to bind bidirectionally to the button's default button property.
     *
     * @return the {@code ButtonConfig} instance, allowing for method chaining.
     */
    ButtonConfig bindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty);

    /**
     * Removes a bidirectional binding between the button's default button property and another {@code Property<Boolean>}. This action allows for independent modification of the button's default button state
     * and the previously bound property.
     *
     * @param otherProperty
     *         the {@code Property<Boolean>} from which to remove the bidirectional binding.
     *
     * @return the {@code ButtonConfig} instance, allowing for method chaining.
     */
    ButtonConfig unbindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty);

    //Cancel Button Property

    /**
     * Binds the cancel button property of the button to an {@link ObservableValue}. This means the cancel button state of the button will dynamically reflect the value of the provided observable.
     *
     * @param observableValue
     *         the {@code ObservableValue} to bind to the cancel button property.
     *
     * @return the {@code ButtonConfig} instance, allowing for method chaining.
     */
    ButtonConfig bindCancelButtonProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the cancel button property, reverting it to its original state before the binding was applied. This method allows for dynamic changes to the binding of the cancel button property.
     *
     * @return the {@code ButtonConfig} instance, allowing for method chaining.
     */
    ButtonConfig unbindCancelButtonProperty();

    /**
     * Establishes a bidirectional binding between the button's cancel button property and another {@code Property<Boolean>}. This ensures that changes to either the button's cancel button state or the other
     * property are reflected across both, keeping them synchronized.
     *
     * @param otherProperty
     *         the {@code Property<Boolean>} to bind bidirectionally to the button's cancel button property.
     *
     * @return the {@code ButtonConfig} instance, allowing for method chaining.
     */
    ButtonConfig bindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty);

    /**
     * Removes a bidirectional binding between the button's cancel button property and another {@code Property<Boolean>}. This action allows for independent modification of the button's cancel button state and
     * the previously bound property.
     *
     * @param otherProperty
     *         the {@code Property<Boolean>} from which to remove the bidirectional binding.
     *
     * @return the {@code ButtonConfig} instance, allowing for method chaining.
     */
    ButtonConfig unbindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the property indicating whether this button is the default button.
     *
     * @param value
     *         true if this button is to be set as the default button, false otherwise
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig setDefaultButton(boolean value);

    /**
     * Sets the property indicating whether this button is the cancel button.
     *
     * @param value
     *         true if this button is to be set as the cancel button, false otherwise
     *
     * @return this {@code ButtonConfig} instance for method chaining
     */
    ButtonConfig setCancelButton(boolean value);

    //endregion Set Functions

}
