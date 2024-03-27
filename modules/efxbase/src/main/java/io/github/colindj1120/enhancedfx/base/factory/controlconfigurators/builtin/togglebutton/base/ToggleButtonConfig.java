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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.togglebutton.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.buttonbase.base.ButtonBaseConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * Enables fluent configuration of {@link ToggleButton} components in JavaFX applications.
 *
 * <p>This interface extends {@link ButtonBaseConfig}, providing toggle button-specific functionalities that allow developers to customize toggle buttons' selected states, toggle groups, and to bind or listen
 * for changes in these properties.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *   <li>Setting and getting the {@link ToggleButton} instance for configuration.</li>
 *   <li>Adding and removing listeners for the selected state and toggle group properties.</li>
 *   <li>Binding the selected state and toggle group properties to other observable values for synchronized changes.</li>
 *   <li>Setting the selected state and toggle group directly, providing control over the toggle button's behavior.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * ToggleButton toggleButton = new ToggleButton("Toggle Me");
 * ToggleGroup toggleGroup = new ToggleGroup();
 * ToggleButtonConfigurator.configure(toggleButton)
 *     .setSelected(true)
 *     .setToggleGroup(toggleGroup)
 *     .addSelectedChangeListener((observable, oldValue, newValue) -> System.out.println("Toggle EFXState Changed: " + newValue))
 *     .bindBidirectionalSelectedProperty(someOtherBooleanProperty);
 * }</pre>
 *
 * <p>This example shows how to create a {@link ToggleButton}, set its initial selected state, assign it to a {@link ToggleGroup}, add a listener to react to changes in its selected state, and bind its
 * selected property bidirectionally to another boolean property.</p>
 *
 * <p>Designed for use within a configurator pattern, each method in this interface returns the instance of the configurator (`T`), enabling method chaining for concise and readable configuration calls. This
 * design promotes a clean and intuitive approach to configuring {@link ToggleButton} properties and behaviors.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ToggleButton
 * @see ButtonBaseConfig
 * @see ConfiguratorBase
 */

@SuppressWarnings("UnusedReturnValue")
public interface ToggleButtonConfig<T extends ConfiguratorBase> extends ButtonBaseConfig<T> {
    /*
     * Methods Available:
     *  - ToggleButton getNode()
     *
     * Add Listener Functions
     *  - T addSelectedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addSelectedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addToggleGroupChangeListener(ChangeListener<? super ToggleGroup> changeListener)
     *  - T addToggleGroupInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeSelectedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeSelectedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeToggleGroupChangeListener(ChangeListener<? super ToggleGroup> changeListener)
     *  - T removeToggleGroupInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindSelectedProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindSelectedProperty()
     *  - T bindBidirectionalSelectedProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalSelectedProperty(Property<Boolean> otherProperty)
     *  - T bindToggleGroupProperty(ObservableValue<? extends ToggleGroup> observableValue)
     *  - T unbindToggleGroupProperty()
     *  - T bindBidirectionalToggleGroupProperty(Property<ToggleGroup> otherProperty)
     *  - T unbindBidirectionalToggleGroupProperty(Property<ToggleGroup> otherProperty)
     *
     * Set Functions
     *  - T setSelected(Boolean value)
     *  - T setToggleGroup(ToggleGroup value)
     */

    /**
     * Returns the {@link ToggleButton} node associated with this configurator.
     *
     * <p>This method provides access to the toggle button that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link ToggleButton} associated with the current configurator instance
     */
    @Override
    ToggleButton getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the selected property. This listener is notified whenever the selected status changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSelectedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().selectedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the selected property. This listener is notified whenever the selected status changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSelectedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the toggle group property. This listener is notified whenever the toggle group changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addToggleGroupChangeListener(ChangeListener<? super ToggleGroup> changeListener) {
        getNode().toggleGroupProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the toggle group property. This listener is notified whenever the toggle group changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addToggleGroupInvalidationListener(InvalidationListener invalidationListener) {
        getNode().toggleGroupProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from listening for changes to the selected property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSelectedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().selectedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the selected property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSelectedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the toggle group property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeToggleGroupChangeListener(ChangeListener<? super ToggleGroup> changeListener) {
        getNode().toggleGroupProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the toggle group property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeToggleGroupInvalidationListener(InvalidationListener invalidationListener) {
        getNode().toggleGroupProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Selected Property

    /**
     * Binds the selected property to the provided {@link ObservableValue}. The selected status of the toggle button will reflect changes to the provided observable value.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindSelectedProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().selectedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the selected property if it is currently bound to an observable value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindSelectedProperty() {
        getNode().selectedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the selected property bidirectionally to the provided {@link Property}. Changes to either the toggle button's selected status or the provided property will be reflected in both.
     *
     * @param otherProperty
     *         The property to bind bidirectionally to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalSelectedProperty(Property<Boolean> otherProperty) {
        getNode().selectedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the bidirectional binding between the selected property and the provided {@link Property}.
     *
     * @param otherProperty
     *         The property to unbind from bidirectionally.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalSelectedProperty(Property<Boolean> otherProperty) {
        getNode().selectedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Toggle Group Property

    /**
     * Binds the toggle group property to the provided {@link ObservableValue}. The toggle group of the toggle button will reflect changes to the provided observable value.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindToggleGroupProperty(ObservableValue<? extends ToggleGroup> observableValue) {
        getNode().toggleGroupProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the toggle group property if it is currently bound to an observable value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindToggleGroupProperty() {
        getNode().toggleGroupProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the toggle group property bidirectionally to the provided {@link Property}. Changes to either the toggle button's toggle group or the provided property will be reflected in both.
     *
     * @param otherProperty
     *         The property to bind bidirectionally to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalToggleGroupProperty(Property<ToggleGroup> otherProperty) {
        getNode().toggleGroupProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the bidirectional binding between the toggle group property and the provided {@link Property}.
     *
     * @param otherProperty
     *         The property to unbind from bidirectionally.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalToggleGroupProperty(Property<ToggleGroup> otherProperty) {
        getNode().toggleGroupProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the selected status of the toggle button.
     *
     * @param value
     *         The new selected status.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setSelected(Boolean value) {
        getNode().setSelected(value);
        return getConfigurator();
    }

    /**
     * Sets the toggle group of the toggle button.
     *
     * @param value
     *         The toggle group to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setToggleGroup(ToggleGroup value) {
        getNode().setToggleGroup(value);
        return getConfigurator();
    }

    //endregion Set Functions
}