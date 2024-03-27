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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.buttonbase.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.labeled.base.LabeledConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;

/**
 * The {@code ButtonBaseConfig} interface provides a foundational framework for configuring {@link ButtonBase} nodes in JavaFX.
 *
 * <p>It extends the {@link LabeledConfig} to include functionalities specific to button bases, enabling developers to customize button behaviors, appearance, and event handling with precision. This
 * configurator is instrumental in creating dynamic and interactive user interfaces by allowing for the modification of button properties and the addition/removal of listeners and bindings.</p>
 *
 * <h2>Capabilities</h2>
 * <ul>
 *   <li>Listener management for button properties such as armed status and action events.</li>
 *   <li>Binding support to synchronize button properties with other observable values, enhancing UI responsiveness.</li>
 *   <li>Bidirectional property binding to ensure consistency between the button properties and other model values.</li>
 *   <li>Utility functions for arming, disarming, and firing buttons programmatically, facilitating complex UI interactions.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Here's an example demonstrating how to configure a {@link Button} using {@code ButtonBaseConfig}:
 * <pre>{@code
 * Button myButton = new Button("Press Me");
 * ButtonBaseConfigurator<Button> configurator = new ButtonBaseConfigurator<>(myButton);
 *
 * configurator.addArmedChangeListener((observable, oldValue, newValue) -> System.out.println("Button armed state changed to: " + newValue))
 *              .addOnActionChangeListener((observable, oldValue, newValue) -> System.out.println("Button action handler changed"))
 *              .setOnAction(event -> System.out.println("Button pressed!"))
 *              .arm()
 *              .disarm()
 *              .fire();
 *
 * // This sets up a button with customized behavior, demonstrating the flexibility and power of ButtonBaseConfig in application UI design.
 * }</pre>
 *
 * <p>This usage example illustrates how to leverage {@code ButtonBaseConfig} for enhancing button functionality within a JavaFX application, from setting up event handlers to programmatically controlling
 * button states.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see LabeledConfig
 * @see ConfiguratorBase
 * @see ButtonBase
 * @see Button
 */
@SuppressWarnings("UnusedReturnValue")
public interface ButtonBaseConfig<T extends ConfiguratorBase> extends LabeledConfig<T> {
    /*
     * Methods Available:
     *  - ButtonBase getNode()
     *
     * Add Listener Functions
     *  - T addArmedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addArmedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener)
     *  - T addOnActionInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeArmedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeArmedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener)
     *  - T removeOnActionInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue)
     *  - T unbindOnActionProperty()
     *  - T bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty)
     *  - T unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty)
     *
     * Set Functions
     *  - T setOnAction(EventHandler<ActionEvent> value)
     *
     * Button Action Functions
     *  - T arm()
     *  - T disarm()
     *  - T fire()
     */

    /**
     * Returns the {@link ButtonBase} node associated with this configurator.
     *
     * <p>This method provides access to the button base that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link ButtonBase} associated with the current configurator instance
     */
    @Override
    ButtonBase getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the armed property. This listener is notified whenever the armed status changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addArmedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().armedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the armed property. This listener is notified whenever the armed property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addArmedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().armedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the onAction property. This listener is notified whenever the onAction event handler changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        getNode().onActionProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the onAction property. This listener is notified whenever the onAction property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addOnActionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onActionProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the armed property. This method stops the given listener from receiving change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeArmedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().armedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the armed property. This method stops the given listener from receiving invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeArmedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().armedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onAction property. This method stops the given listener from receiving change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        getNode().onActionProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onAction property. This method stops the given listener from receiving invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeOnActionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onActionProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // On Action Property

    /**
     * Binds the onAction property to an {@link ObservableValue}. This method allows the onAction property to automatically update when the given observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue) {
        getNode().onActionProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onAction property. This method releases any bindings for the onAction property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindOnActionProperty() {
        getNode().onActionProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the onAction property bidirectionally to another {@link Property}. This method allows two properties to synchronize their values with each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        getNode().onActionProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the onAction property bidirectionally from another {@link Property}. This method stops the bidirectional synchronization between the two properties.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        getNode().onActionProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the onAction event handler. This method assigns a new {@link EventHandler} for handling ActionEvent to the button.
     *
     * @param value
     *         The event handler to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setOnAction(EventHandler<ActionEvent> value) {
        getNode().setOnAction(value);
        return getConfigurator();
    }

    //endregion Set Functions

    //region Button Action Functions
    //*****************************************************************
    // Button Action Functions
    //*****************************************************************

    /**
     * Arms the button. This method marks the button as being pressed, simulating the start of an action.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T arm() {
        getNode().arm();
        return getConfigurator();
    }

    /**
     * Disarms the button. This method reverts the button to its normal state, indicating that an action has either completed or been canceled.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T disarm() {
        getNode().disarm();
        return getConfigurator();
    }

    /**
     * Fires the button. This method triggers the button's onAction event as if the button had been clicked.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T fire() {
        getNode().fire();
        return getConfigurator();
    }

    //endregion Button Action Functions
}
