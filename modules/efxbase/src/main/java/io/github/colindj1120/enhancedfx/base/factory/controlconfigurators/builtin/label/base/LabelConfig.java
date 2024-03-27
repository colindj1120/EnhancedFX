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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.labeled.base.LabeledConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Provides configuration capabilities for {@link Label} nodes within the EnhancedFX framework.
 *
 * <p>This interface extends {@link LabeledConfig} to offer specialized configuration methods tailored to {@link Label} nodes, facilitating the setup of properties, bindings, and event listeners specific to
 * label controls.</p>
 *
 * <h2>Capabilities include:</h2>
 * <ul>
 *     <li>Adding and removing {@link ChangeListener} and {@link InvalidationListener} to the labelFor property.</li>
 *     <li>Binding and unbinding the labelFor property to and from other properties or observable values.</li>
 *     <li>Setting the node for which the label is providing labeling.</li>
 * </ul>
 *
 * <h2>Usage example:</h2>
 * <pre>{@code
 * Label myLabel = new Label("Username");
 * TextField myTextField = new TextField();
 * LabelConfigurator.configure(myLabel)
 *     .setLabelFor(myTextField)  // Link the label to the text field
 *     .addLabelForChangeListener((observable, oldValue, newValue) -> {
 *         System.out.println("LabelFor property changed from " + oldValue + " to " + newValue);
 *     });
 * }</pre>
 *
 * <p>This configurator provides a fluent API to chain configuration calls in a concise manner, improving code readability and ease of maintenance.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see LabeledConfig
 * @see ConfiguratorBase
 * @see Label
 */
@SuppressWarnings("UnusedReturnValue")
public interface LabelConfig<T extends ConfiguratorBase> extends LabeledConfig<T> {
    /*
     * Methods Available:
     *  - Label getNode()
     *
     * Add Listener Functions
     *  - T addLabelForChangeListener(ChangeListener<? super Node> changeListener)
     *  - T addLabelForInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeLabelForChangeListener(ChangeListener<? super Node> changeListener)
     *  - T removeLabelForInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindLabelForProperty(ObservableValue<? extends Node> observableValue)
     *  - T unbindLabelForProperty()
     *  - T bindBidirectionalLabelForProperty(Property<Node> otherProperty)
     *  - T unbindBidirectionalLabelForProperty(Property<Node> otherProperty)
     *
     * Set Functions
     *  - T setLabelFor(Node value)
     */

    /**
     * Returns the {@link Label} node associated with this configurator.
     *
     * <p>This method provides access to the label node that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link Label} associated with the current configurator instance
     */
    @Override
    Label getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to the labelFor property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLabelForChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().labelForProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the labelFor property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLabelForInvalidationListener(InvalidationListener invalidationListener) {
        getNode().labelForProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the labelFor property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLabelForChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().labelForProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the labelFor property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLabelForInvalidationListener(InvalidationListener invalidationListener) {
        getNode().labelForProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    /**
     * Binds the labelFor property of the node to an {@link ObservableValue}.
     *
     * @param observableValue
     *         The observable value to bind to the labelFor property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindLabelForProperty(ObservableValue<? extends Node> observableValue) {
        getNode().labelForProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the labelFor property of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindLabelForProperty() {
        getNode().labelForProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between the labelFor property of the node and another {@link Property}.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalLabelForProperty(Property<Node> otherProperty) {
        getNode().labelForProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the labelFor property of the node and another {@link Property}.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalLabelForProperty(Property<Node> otherProperty) {
        getNode().labelForProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the value of the labelFor property for the node.
     *
     * @param value
     *         The new value for the labelFor property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setLabelFor(Node value) {
        getNode().setLabelFor(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
