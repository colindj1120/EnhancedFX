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
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Defines a configuration interface for {@link Label} components in JavaFX, facilitating a fluent and intuitive approach to
 * customizing labels. This interface provides a comprehensive suite of methods for managing the 'labelFor' property, enabling dynamic
 * updates through listeners, bindings, and direct property setting. It is designed to enhance the accessibility and functionality of
 * labels by closely linking them with other UI components they describe.
 * <p>
 * <h2>Capabilities include:</h2>
 * <ul>
 *     <li>Adding and removing change listeners and invalidation listeners for the 'labelFor' property to respond to updates
 *     dynamically.</li>
 *     <li>Binding the 'labelFor' property to an {@link ObservableValue} for automatic synchronization with other UI components or
 *     model properties.</li>
 *     <li>Establishing bidirectional bindings with other {@link Property} instances, ensuring mutual updates between the label and
 *     another component.</li>
 *     <li>Directly setting the 'labelFor' property to associate the label with a specific {@link Node}, improving form readability
 *     and UX design.</li>
 * </ul>
 * </p>
 *
 * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 * {@link Label} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * @see Label
 * @see ChangeListener
 * @see InvalidationListener
 * @see ObservableValue
 * @see Node
 */
public interface LabelConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the 'labelFor' property of the {@link Label}. This listener is notified
     * whenever the node that the label is for changes.
     *
     * @param changeListener
     *         the listener that will be notified when the 'labelFor' property changes
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig addLabelForChangeListener(ChangeListener<? super Node> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the 'labelFor' property of the {@link Label}. This listener
     * is notified whenever the 'labelFor' property becomes invalid.
     *
     * @param invalidationListener
     *         the listener that will be notified of invalidation
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig addLabelForInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a previously added {@link ChangeListener} from the 'labelFor' property of the {@link Label}.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig removeLabelForChangeListener(ChangeListener<? super Node> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} from the 'labelFor' property of the {@link Label}.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig removeLabelForInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //Label For Property

    /**
     * Binds the 'labelFor' property of the {@link Label} to an {@link ObservableValue}. This binding ensures that the 'labelFor'
     * property automatically updates whenever the observable value changes.
     *
     * @param observableValue
     *         the observable value to bind to the 'labelFor' property
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig bindLabelForProperty(ObservableValue<? extends Node> observableValue);

    /**
     * Unbinds the 'labelFor' property of the {@link Label} from its currently bound {@link ObservableValue}.
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig unbindLabelForProperty();

    /**
     * Establishes a bidirectional binding between the 'labelFor' property of the {@link Label} and another {@link Property}.
     *
     * @param otherProperty
     *         the other property to bind with the 'labelFor' property bidirectionally
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig bindBidirectionalLabelForProperty(Property<Node> otherProperty);

    /**
     * Removes a bidirectional binding between the 'labelFor' property of the {@link Label} and another {@link Property}.
     *
     * @param otherProperty
     *         the other property to unbind from the 'labelFor' property
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig unbindBidirectionalLabelForProperty(Property<Node> otherProperty);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Directly sets the 'labelFor' property of the {@link Label} to specify the node that this label is associated with. This property
     * is used to designate a label as describing a specific control, enhancing accessibility and usability.
     *
     * @param value
     *         the node to associate with this label
     *
     * @return the current instance of {@link LabelConfig} for method chaining
     */
    LabelConfig setLabelFor(Node value);

    //endregion Set Functions
}
