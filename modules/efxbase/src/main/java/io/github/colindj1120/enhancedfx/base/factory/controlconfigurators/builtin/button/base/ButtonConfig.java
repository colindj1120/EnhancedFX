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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.button.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.buttonbase.base.ButtonBaseConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

/**
 * The {@code ButtonConfig} interface provides a comprehensive suite of configuration capabilities for {@link Button} nodes within JavaFX applications.
 *
 * <p>It extends the {@link ButtonBaseConfig} to include functionality specific to buttons, such as handling default and cancel button properties. This configurator allows for fine-tuned control over button
 * behavior, appearance, and event handling, making it a versatile tool for UI design in JavaFX.</p>
 *
 * <h2>Capabilities</h2>
 * <ul>
 *   <li>Dynamic listener addition and removal for both defaultButton and cancelButton properties.</li>
 *   <li>Property binding capabilities to synchronize button properties with other observable values.</li>
 *   <li>Bidirectional binding support to keep button properties and external properties in sync.</li>
 *   <li>Convenience methods for setting the default and cancel statuses of a button.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Here's how you might use {@code ButtonConfig} to configure a button within a JavaFX application:
 * <pre>
 * {@code
 * // Assuming `buttonConfigurator` is an instance of a class that implements ButtonConfig
 * Button myButton = new Button("Click Me");
 * buttonConfigurator.setNode(myButton)
 *                   .setDefaultButton(true)
 *                   .addDefaultButtonChangeListener((observable, oldValue, newValue) -> System.out.println("Default Button status changed: " + newValue))
 *                   .bindCancelButtonProperty(someOtherProperty);
 * }
 * </pre>
 *
 * <p>In this example, {@code myButton} is configured to be a default button. It listens for changes to its defaultButton property and binds its cancelButton property to another observable property. This
 * demonstrates the flexibility and utility of {@code ButtonConfig} in creating dynamic and responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ButtonBaseConfig
 * @see ConfiguratorBase
 * @see Button
 */
@SuppressWarnings("UnusedReturnValue")
public interface ButtonConfig<T extends ConfiguratorBase> extends ButtonBaseConfig<T> {
    /*
     * Methods Available:
     *  - Button getNode()
     *
     * Add Listener Functions
     *  - T addDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addDefaultButtonInvalidationListener(InvalidationListener invalidationListener)
     *  - T addCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addCancelButtonInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeDefaultButtonInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeCancelButtonInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindDefaultButtonProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindDefaultButtonProperty()
     *  - T bindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty)
     *  - T bindCancelButtonProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindCancelButtonProperty()
     *  - T bindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty)
     *
     * Set Functions
     *  - T setDefaultButton(boolean value)
     *  - T setCancelButton(boolean value)
     */

    /**
     * Returns the {@link Button} node associated with this configurator.
     *
     * <p>This method provides access to the button that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link Button} associated with the current configurator instance
     */
    @Override
    Button getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the defaultButton property. This listener is notified whenever the defaultButton status changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().defaultButtonProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the defaultButton property. This listener is notified whenever the defaultButton property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addDefaultButtonInvalidationListener(InvalidationListener invalidationListener) {
        getNode().defaultButtonProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the cancelButton property. This listener is notified whenever the cancelButton status changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cancelButtonProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the cancelButton property. This listener is notified whenever the cancelButton property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addCancelButtonInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cancelButtonProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the defaultButton property. This method stops the given listener from receiving change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().defaultButtonProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the defaultButton property. This method stops the given listener from receiving invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeDefaultButtonInvalidationListener(InvalidationListener invalidationListener) {
        getNode().defaultButtonProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the cancelButton property. This method stops the given listener from receiving change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cancelButtonProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the cancelButton property. This method stops the given listener from receiving invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeCancelButtonInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cancelButtonProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Default Button Property

    /**
     * Binds the defaultButton property to an {@link ObservableValue}. This method allows the defaultButton property to automatically update when the given observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindDefaultButtonProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().defaultButtonProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the defaultButton property. This method releases any bindings for the defaultButton property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindDefaultButtonProperty() {
        getNode().defaultButtonProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the defaultButton property bidirectionally to another {@link Property}. This method allows two properties to synchronize their values with each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty) {
        getNode().defaultButtonProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the defaultButton property bidirectionally from another {@link Property}. This method stops the bidirectional synchronization between the two properties.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty) {
        getNode().defaultButtonProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cancel Button Property

    /**
     * Binds the cancelButton property to an {@link ObservableValue}. This method allows the cancelButton property to automatically update when the given observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindCancelButtonProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().cancelButtonProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the cancelButton property. This method releases any bindings for the cancelButton property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindCancelButtonProperty() {
        getNode().cancelButtonProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the cancelButton property bidirectionally to another {@link Property}. This method allows two properties to synchronize their values with each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty) {
        getNode().cancelButtonProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the cancelButton property bidirectionally from another {@link Property}. This method stops the bidirectional synchronization between the two properties.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty) {
        getNode().cancelButtonProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the value of the defaultButton property. This method changes the button's defaultButton status to the given value.
     *
     * @param value
     *         The new value for the defaultButton property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setDefaultButton(boolean value) {
        getNode().setDefaultButton(value);
        return getConfigurator();
    }

    /**
     * Sets the value of the cancelButton property. This method changes the button's cancelButton status to the given value.
     *
     * @param value
     *         The new value for the cancelButton property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setCancelButton(boolean value) {
        getNode().setCancelButton(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
