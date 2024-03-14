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
package io.github.colindj1120.enhancedfx.controls.factory.configurators.controls;

import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.controls.ButtonBaseConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;

/**
 * The {@code ButtonBaseConfigurator} abstract class provides a comprehensive configurator for {@link ButtonBase} instances in JavaFX. It extends {@link LabeledConfigurator} to include label-related
 * configurations and implements {@link ButtonBaseConfig} to add specific functionalities for configuring button behaviors and properties.
 *
 * <p>
 * This configurator enables developers to add or remove listeners for properties such as "armed" and "on-action", set action event handlers, and programmatically control the armed state and firing of the
 * button. It encapsulates the {@link ButtonBase} instance to be configured, promoting a fluent API style that allows for method chaining and thereby facilitating a more readable and concise setup.
 * </p>
 *
 * <p>
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Listening to changes and invalidations on "armed" and "on-action" properties.</li>
 *     <li>Binding and unbind both unidirectionally and bidirectionally for the "on-action" property</li>
 *     <li>Setting the on-action event handler for the button.</li>
 *     <li>Arming, disarming, and firing the button programmatically.</li>
 * </ul>
 * By providing a targeted and extensible approach to button configuration, {@code ButtonBaseConfigurator} serves as a foundational tool for developers to customize button behavior and interaction patterns 
 * within their JavaFX applications, enhancing the user experience through dynamic and responsive UI elements.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see LabeledConfigurator
 * @see ButtonBaseConfig
 * @see ButtonBase
 * @see ChangeListener
 * @see InvalidationListener
 * @see EventHandler
 */
public abstract class ButtonBaseConfigurator extends LabeledConfigurator implements ButtonBaseConfig {
    /**
     * The {@link ButtonBase} instance that is being configured. This field holds a reference to the specific button base object upon which configuration methods will act, enabling the modification and
     * customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the button base, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the button base, including its appearance, behavior, and event handling.
     * </p>
     */
    private ButtonBase buttonBase;

    /**
     * Constructs a {@code ButtonBaseConfigurator} for the specified {@link ButtonBase}. This constructor initializes the configurator with a target button base. It sets up the environment for further
     * configuration specific to {@link ButtonBase} instances. The {@code ButtonBaseConfigurator.class} is used as the class reference for error reporting.
     *
     * @param buttonBase
     *         The {@link ButtonBase} to be configured. Must not be {@code null}, and an {@link IllegalArgumentException} will be thrown if a null button base is passed. This ensures that the configurator has a
     *         valid target for configuration.
     */
    protected ButtonBaseConfigurator(ButtonBase buttonBase) {
        super(checkNodeNotNullAndInstanceOf(buttonBase, ButtonBase.class, ButtonBaseConfigurator.class, "Constructor"));
        this.buttonBase = buttonBase;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        super.setNode(checkNodeNotNullAndInstanceOf(value, ButtonBase.class, ButtonBaseConfigurator.class, "setNode"));
        this.buttonBase = ((ButtonBase) value);
    }

    //endregion Overridden Functions

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig addArmedChangeListener(ChangeListener<? super Boolean> changeListener) {
        buttonBase.armedProperty()
                  .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig addArmedInvalidationListener(InvalidationListener invalidationListener) {
        buttonBase.armedProperty()
                  .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        buttonBase.onActionProperty()
                  .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig addOnActionInvalidationListener(InvalidationListener invalidationListener) {
        buttonBase.onActionProperty()
                  .addListener(invalidationListener);
        return this;
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig removeArmedChangeListener(ChangeListener<? super Boolean> changeListener) {
        buttonBase.armedProperty()
                  .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig removeArmedInvalidationListener(InvalidationListener invalidationListener) {
        buttonBase.armedProperty()
                  .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        buttonBase.onActionProperty()
                  .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig removeOnActionInvalidationListener(InvalidationListener invalidationListener) {
        buttonBase.onActionProperty()
                  .removeListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************


    // On Action Property

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue) {
        buttonBase.onActionProperty().bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig unbindOnActionProperty() {
        buttonBase.onActionProperty().unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        buttonBase.onActionProperty().bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        buttonBase.onActionProperty().unbindBidirectional(otherProperty);
        return this;
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig setOnAction(EventHandler<ActionEvent> value) {
        buttonBase.setOnAction(value);
        return this;
    }

    //endregion Set Functions

    //region Button Action Functions
    //*****************************************************************
    // Button Action Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig arm() {
        buttonBase.arm();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig disarm() {
        buttonBase.disarm();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig fire() {
        buttonBase.fire();
        return this;
    }

    //endregion Button Action Functions
}
