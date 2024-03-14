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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * {@code ToggleButtonConfig} provides a fluent API for configuring {@link ToggleButton}s. This interface defines methods to add and remove listeners, bind properties, and set values directly on the toggle
 * button's properties. It's designed to enhance code readability and maintainability by allowing property configurations and event handling to be chained and expressed in a clear, concise manner.
 *
 * <h2>Capabilities:</h2>
 * <p>
 * <ul>
 *     <li><b>Add Listener Functions:</b> Allows adding change and invalidation listeners to both the selected state and the toggle group association of the toggle button.</li>
 *     <li><b>Remove Listener Functions:</b> Enables removal of previously added listeners for the selected state and toggle group association, ensuring dynamic control over event handling.</li>
 *     <li><b>Binding Functions:</b> Supports binding the selected state and toggle group properties to other observable values, enabling automatic synchronization of states between the toggle button and
 *     other parts of the UI. It allows both one-way and bidirectional bindings.</li>
 *     <li><b>Set Functions:</b> Provides methods to directly set the selected state and toggle group of the toggle button, facilitating immediate updates to the toggle button's configuration.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This configurator empowers developers to efficiently manage toggle buttons within JavaFX applications, streamlining the process of setting up button behavior, appearance, and interactions within the user
 * interface.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ToggleButton
 * @see ToggleGroup
 */
public interface ToggleButtonConfig {

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to monitor changes in the selected state of the toggle button. When the selected state changes, the given {@code changeListener} will be invoked with the new value.
     *
     * @param changeListener
     *         the listener to be added
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining
     */
    ToggleButtonConfig addSelectedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for any invalidation in the selected state of the toggle button.
     *
     * @param invalidationListener
     *         the listener to be added
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining
     */
    ToggleButtonConfig addSelectedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} to monitor changes in the {@link ToggleGroup} association of the toggle button. When the toggle button's group changes, the provided {@code changeListener} will be invoked
     * with the new value.
     *
     * @param changeListener
     *         the listener to be added
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining
     */
    ToggleButtonConfig addToggleGroupChangeListener(ChangeListener<? super ToggleGroup> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for any invalidation in the {@link ToggleGroup} association of the toggle button.
     *
     * @param invalidationListener
     *         the listener to be added
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining
     */
    ToggleButtonConfig addToggleGroupInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a previously added {@link ChangeListener} for the selected state of the toggle button.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining and further configuration
     */
    ToggleButtonConfig removeSelectedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the selected state of the toggle button.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return the {@code ToggleButtonConfig} instance, facilitating method chaining for additional adjustments
     */
    ToggleButtonConfig removeSelectedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} that was monitoring changes in the {@link ToggleGroup} of the toggle button.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return the {@code ToggleButtonConfig} instance, enabling continuous configuration through method chaining
     */
    ToggleButtonConfig removeToggleGroupChangeListener(ChangeListener<? super ToggleGroup> changeListener);

    /**
     * Removes an {@link InvalidationListener} that was attached to observe invalidations in the toggle button's {@link ToggleGroup}.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return the {@code ToggleButtonConfig} instance, supporting fluent configuration via method chaining
     */
    ToggleButtonConfig removeToggleGroupInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //Selected Property

    /**
     * Binds the selected property of the toggle button to an {@link ObservableValue}. This allows the selected state of the toggle button to dynamically reflect the value of the provided observable.
     *
     * @param observableValue
     *         the {@code ObservableValue} to bind to the selected property.
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining.
     */
    ToggleButtonConfig bindSelectedProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the selected property of the toggle button, restoring its ability to be independently controlled.
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining.
     */
    ToggleButtonConfig unbindSelectedProperty();

    /**
     * Creates a bidirectional binding between the toggle button's selected property and another {@code Property<Boolean>}. This ensures that changes in either the selected state of the toggle button or the
     * other property are synchronized between both.
     *
     * @param otherProperty
     *         the {@code Property<Boolean>} to bind bidirectionally to the selected property.
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining.
     */
    ToggleButtonConfig bindBidirectionalSelectedProperty(Property<Boolean> otherProperty);

    /**
     * Removes a bidirectional binding between the toggle button's selected property and another {@code Property<Boolean>}, allowing for independent changes to the selected state and the previously bound
     * property.
     *
     * @param otherProperty
     *         the {@code Property<Boolean>} from which to remove the bidirectional binding.
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining.
     */
    ToggleButtonConfig unbindBidirectionalSelectedProperty(Property<Boolean> otherProperty);

    //Toggle Group Property

    /**
     * Binds the toggle group property of the toggle button to an {@link ObservableValue}. This binding ensures that the toggle button's group membership dynamically reflects the value of the provided
     * observable.
     *
     * @param observableValue
     *         the {@code ObservableValue} to bind to the toggle group property.
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining.
     */
    ToggleButtonConfig bindToggleGroupProperty(ObservableValue<? extends ToggleGroup> observableValue);

    /**
     * Unbinds the toggle group property, enabling independent control of the toggle button's group membership.
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining.
     */
    ToggleButtonConfig unbindToggleGroupProperty();

    /**
     * Establishes a bidirectional binding between the toggle button's toggle group property and another {@code Property<ToggleGroup>}. This ensures the synchronization of group membership between the toggle
     * button and the bound property.
     *
     * @param otherProperty
     *         the {@code Property<ToggleGroup>} to bind bidirectionally to the toggle group property.
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining.
     */
    ToggleButtonConfig bindBidirectionalToggleGroupProperty(Property<ToggleGroup> otherProperty);

    /**
     * Removes a bidirectional binding between the toggle button's toggle group property and another {@code Property<ToggleGroup>}, allowing for separate management of the toggle button's group membership and
     * the previously bound property.
     *
     * @param otherProperty
     *         the {@code Property<ToggleGroup>} from which to remove the bidirectional binding.
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for method chaining.
     */
    ToggleButtonConfig unbindBidirectionalToggleGroupProperty(Property<ToggleGroup> otherProperty);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the selected state of the toggle button to the specified value. This can be used to programmatically change the selection state of the toggle button.
     *
     * @param value
     *         the desired selected state of the toggle button
     *
     * @return the {@code ToggleButtonConfig} instance, allowing for a fluent configuration experience
     */
    ToggleButtonConfig setSelected(Boolean value);

    /**
     * Sets the {@link ToggleGroup} for the toggle button. This allows the toggle button to be part of a group of buttons where only one button can be selected at a time.
     *
     * @param value
     *         the {@link ToggleGroup} to assign to the toggle button
     *
     * @return the {@code ToggleButtonConfig} instance, providing a chainable configuration process
     */
    ToggleButtonConfig setToggleGroup(ToggleGroup value);

    //endregion Set Functions
}
