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
package io.github.colindj1120.enhancedfx.base.factory.configurators.base.interfaces.controls;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * The {@code ButtonBaseConfig} interface provides a comprehensive set of methods for configuring the behavior and properties of buttons within JavaFX applications. It allows for the dynamic addition and
 * removal of change and invalidation listeners to button properties such as the "armed" state and the "on-action" event, facilitating intricate control over button interactions and responses. Additionally,
 * this interface offers methods to directly set these properties and perform actions like arming, disarming, and firing the button programmatically.
 *
 * <p>
 * Through the use of method chaining, {@code ButtonBaseConfig} enables a fluid and intuitive approach to button configuration, enhancing the readability and maintainability of code. This design pattern
 * encourages the application of multiple configurations in a single statement, streamlining the setup process and reducing boilerplate code.
 * </p>
 *
 * <p>
 * Implementations of {@code ButtonBaseConfig} should ensure that all configurations and state changes are applied effectively, reflecting the desired behaviors in the UI. This interface plays a crucial role in
 * creating interactive and responsive JavaFX applications, where buttons act as fundamental elements for user interaction.
 * </p>
 *
 * <h2>Key functionalities include:</h2>
 * <p>
 * <ul>
 *     <li>Adding and removing listeners for button properties to react to changes or invalidations dynamically.</li>
 *     <li>Binding and unbinding for button properties</li>
 *     <li>Configuring the "on-action" event handler to define custom behavior when the button is activated.</li>
 *     <li>Directly invoking actions such as arming, disarming, and firing the button, offering programmatic control over its state.</li>
 * </ul>
 * Usage of {@code ButtonBaseConfig} greatly simplifies the process of customizing button behavior, making it an indispensable tool for developers aiming to leverage advanced features and interactions in
 * their JavaFX applications.
 * </p>
 *
 * <p>
 * This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of {@link Button} nodes. It serves as a foundational component of the EnhancedFX
 * library, which seeks to provide extended functionality and utilities beyond the core JavaFX library.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ChangeListener
 * @see InvalidationListener
 * @see EventHandler
 */
@SuppressWarnings("UnusedReturnValue")
public interface ButtonBaseConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} that listens for changes to the "armed" property of the button.
     *
     * @param changeListener
     *         the listener to be notified when the "armed" state changes
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig addArmedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} that listens for invalidations of the "armed" property.
     *
     * @param invalidationListener
     *         the listener to be notified of invalidation events related to the "armed" property
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig addArmedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} to monitor changes to the button's on-action event handler.
     *
     * @param changeListener
     *         the listener to be notified when the on-action event handler changes
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations on the on-action property of the button.
     *
     * @param invalidationListener
     *         the listener to be notified of invalidation events related to the on-action property
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig addOnActionInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the "armed" property of the button.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig removeArmedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the "armed" property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig removeArmedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the on-action event handler property of the button.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the on-action property of the button.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig removeOnActionInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //On Action Property

    /**
     * Binds the onAction property of the button to an observable value. The onAction property defines the event handler called when the button is activated.
     *
     * @param observableValue
     *         the observable value to bind to the onAction property
     *
     * @return the {@code ButtonBaseConfig} instance, enabling a streamlined configuration process
     */
    ButtonBaseConfig bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue);

    /**
     * Unbinds the onAction property of the button. This action restores the button's onAction property to its default behavior, removing any previously bound observable value.
     *
     * @return the {@code ButtonBaseConfig} instance, facilitating further configuration.
     */
    ButtonBaseConfig unbindOnActionProperty();

    /**
     * Establishes a bidirectional binding between the button's onAction property and another {@code Property} of type {@code EventHandler<ActionEvent>}. This ensures that the event handler assigned to the
     * button's onAction property and the event handler in the other property are kept synchronized.
     *
     * @param otherProperty
     *         the other {@code Property<EventHandler<ActionEvent>>} to bind with.
     *
     * @return the {@code ButtonBaseConfig} instance, allowing for fluent configuration chaining.
     */
    ButtonBaseConfig bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty);

    /**
     * Removes a bidirectional binding between the button's onAction property and another {@code Property} of type {@code EventHandler<ActionEvent>}. This action decouples the button's onAction property from
     * the other property, allowing them to be independently changed.
     *
     * @param otherProperty
     *         the {@code Property<EventHandler<ActionEvent>>} to unbind from the button's onAction property.
     *
     * @return the {@code ButtonBaseConfig} instance, enabling continuous configuration adjustments.
     */
    ButtonBaseConfig unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the on-action event handler for the button.
     *
     * @param value
     *         the event handler for the on-action event
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig setOnAction(EventHandler<ActionEvent> value);

    //endregion Set Functions

    //region Button Action Functions
    //*****************************************************************
    // Button Action Functions
    //*****************************************************************

    /**
     * Arms the button, indicating that it is ready to be activated.
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig arm();

    /**
     * Disarms the button, indicating that it should not be activated.
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig disarm();

    /**
     * Fires the button, simulating an action event as if it were clicked by the user.
     *
     * @return this {@code ButtonBaseConfig} instance for method chaining
     */
    ButtonBaseConfig fire();

    //endregion Button Action Functions
}
