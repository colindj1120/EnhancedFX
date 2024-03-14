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
package io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.controls;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * Provides a fluent interface for configuring {@link TextField} instances within JavaFX applications. This interface defines a
 * comprehensive set of methods for adding listeners to various properties of a {@link TextField}, removing those listeners, binding
 * properties to observable values, and directly setting property values to customize the behavior and appearance of text fields.
 *
 * <p>
 * The {@link TextFieldConfig} interface supports method chaining, allowing for succinct and readable code that can configure text
 * fields in a series of chained method calls. This facilitates a more declarative approach to UI customization in JavaFX.
 * </p>
 *
 * <p>
 * <h2>Capabilities include:</h2>
 * <ul>
 *     <li>Adding and removing change and invalidation listeners for the preferred column count,
 *     onAction event handler, and alignment properties, enabling responsive UI behavior based on user
 *     interactions and property changes.</li>
 *     <li>Binding the preferred column count, onAction event handler, and alignment properties
 *     to external {@link ObservableValue}s or other properties, allowing for dynamic updates and
 *     synchronization with application state.</li>
 *     <li>Establishing and removing bidirectional bindings for the preferred column count,
 *     onAction event handler, and alignment properties, ensuring mutual synchronization between
 *     the {@link TextField} properties and other parts of the UI.</li>
 *     <li>Directly setting values for the preferred column count, onAction event handler,
 *     and alignment, providing straightforward customization of text field characteristics.</li>
 * </ul>
 * </p>
 *
 * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 * {@link TextField} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextField
 * @see ChangeListener
 * @see InvalidationListener
 * @see ObservableValue
 * @see EventHandler
 * @see ActionEvent
 * @see Pos
 */
public interface TextFieldConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes in the preferred column count property of the {@link TextField}. This allows
     * for actions to be taken whenever the preferred column count is modified.
     *
     * @param changeListener
     *         the listener that will be notified when the preferred column count changes
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig addPrefColumnCountChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations in the preferred column count property of the
     * {@link TextField}. This listener is notified whenever the preferred column count property becomes invalid.
     *
     * @param invalidationListener
     *         the listener that will be notified of invalidation
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig addPrefColumnCountInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} to listen for changes to the onAction event handler of the {@link TextField}. This is useful for
     * responding to user interactions, such as pressing the Enter key.
     *
     * @param changeListener
     *         the listener that will be notified when the onAction event handler changes
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations in the onAction event handler of the {@link TextField}. This
     * listener is notified whenever the onAction event handler property becomes invalid.
     *
     * @param invalidationListener
     *         the listener that will be notified of invalidation
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig addOnActionInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} to listen for changes in the alignment property of the {@link TextField}. This allows for actions
     * to be taken whenever the alignment is modified, affecting how the text is displayed.
     *
     * @param changeListener
     *         the listener that will be notified when the alignment changes
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig addAlignmentChangeListener(ChangeListener<? super Pos> changeListener);

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations in the alignment property of the {@link TextField}. This
     * listener is notified whenever the alignment property becomes invalid.
     *
     * @param invalidationListener
     *         the listener that will be notified of invalidation
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig addAlignmentInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the preferred column count property of the {@link TextField}. This reverses the action
     * taken by {@link #addPrefColumnCountChangeListener}.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig removePrefColumnCountChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the preferred column count property of the {@link TextField}. This reverses the
     * action taken by {@link #addPrefColumnCountInvalidationListener}.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig removePrefColumnCountInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the onAction event handler of the {@link TextField}. This reverses the action taken by
     * {@link #addOnActionChangeListener}.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the onAction event handler of the {@link TextField}. This reverses the action taken
     * by {@link #addOnActionInvalidationListener}.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig removeOnActionInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the alignment property of the {@link TextField}. This method allows for detaching
     * previously added change listeners that monitor changes to the text field's alignment.
     *
     * @param changeListener
     *         the change listener to be removed from the alignment property
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the alignment property of the {@link TextField}. This method allows for detaching
     * previously added invalidation listeners that are notified upon invalidation of the text field's alignment property.
     *
     * @param invalidationListener
     *         the invalidation listener to be removed
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig removeAlignmentInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //Pref Column Count Property

    /**
     * Binds the preferred column count property of the {@link TextField} to another observable value. This binding ensures that the
     * preferred column count of the text field automatically updates whenever the observable value changes.
     *
     * @param observableValue
     *         the observable value to bind to the preferred column count property
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig bindPrefColumnCountProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the preferred column count property of the {@link TextField} from its currently bound observable value, if any. This
     * removes the automatic synchronization between the preferred column count and the observable value.
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig unbindPrefColumnCountProperty();

    /**
     * Establishes a bidirectional binding between the preferred column count property of the {@link TextField} and another property.
     * This bidirectional binding ensures that both properties are always synchronized with each other, reflecting changes made to
     * either one.
     *
     * @param otherProperty
     *         the other property to bind with the preferred column count property bidirectionally
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig bindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty);

    /**
     * Removes the bidirectional binding between the preferred column count property of the {@link TextField} and another property.
     * This action ceases the automatic synchronization between the two properties.
     *
     * @param otherProperty
     *         the other property to unbind from the preferred column count property
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig unbindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty);

    //On Action Property

    /**
     * Binds the onAction property of the {@link TextField} to an {@link ObservableValue} that supplies an
     * {@link EventHandler<ActionEvent>}. This binding automatically updates the onAction event handler of the text field whenever the
     * observable value changes.
     *
     * @param observableValue
     *         the observable value providing an EventHandler<ActionEvent> to bind to the onAction property
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue);

    /**
     * Unbinds the onAction property of the {@link TextField} from its currently bound observable value, if any. This removes the
     * automatic synchronization between the onAction event handler and the observable value.
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig unbindOnActionProperty();

    /**
     * Establishes a bidirectional binding between the onAction property of the {@link TextField} and another {@link Property} that
     * also holds an {@link EventHandler<ActionEvent>}. This bidirectional binding ensures that both the onAction event handler of the
     * text field and the other property are always synchronized with each other.
     *
     * @param otherProperty
     *         the other property to bind with the onAction property bidirectionally
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty);

    /**
     * Removes the bidirectional binding between the onAction property of the {@link TextField} and another property. This action
     * ceases the automatic synchronization between the onAction event handler and the other property.
     *
     * @param otherProperty
     *         the other property to unbind from the onAction property
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty);

    //Alignment Property

    /**
     * Binds the alignment property of the {@link TextField} to an {@link ObservableValue} that supplies a {@link Pos} value. This
     * binding automatically updates the alignment of the text field whenever the observable value changes, affecting how the text is
     * displayed within the field.
     *
     * @param observableValue
     *         the observable value providing a Pos value to bind to the alignment property
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig bindAlignmentProperty(ObservableValue<? extends Pos> observableValue);

    /**
     * Unbinds the alignment property of the {@link TextField} from its currently bound observable value, if any. This removes the
     * automatic synchronization between the alignment and the observable value.
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig unbindAlignmentProperty();

    /**
     * Establishes a bidirectional binding between the alignment property of the {@link TextField} and another {@link Property} that
     * holds a {@link Pos} value. This bidirectional binding ensures that both the alignment of the text field and the other property
     * are always synchronized with each other.
     *
     * @param otherProperty
     *         the other property to bind with the alignment property bidirectionally
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig bindBidirectionalAlignmentProperty(Property<Pos> otherProperty);

    /**
     * Removes the bidirectional binding between the alignment property of the {@link TextField} and another property. This action
     * ceases the automatic synchronization between the alignment and the other property.
     *
     * @param otherProperty
     *         the other property to unbind from the alignment property
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig unbindBidirectionalAlignmentProperty(Property<Pos> otherProperty);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the preferred column count for the {@link TextField}. This property influences the preferred width of the text field, with
     * each column roughly equivalent to the width of a single character of the text field's font.
     *
     * @param value
     *         the preferred number of columns
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig setPrefColumnCount(int value);

    /**
     * Sets the {@link EventHandler} for the onAction event of the {@link TextField}. The onAction event is typically triggered when
     * the user presses the Enter key while the text field has focus. This method allows for custom behavior to be defined for this
     * event, such as submitting a form or clearing the text field.
     *
     * @param value
     *         the event handler to be set for handling the onAction event
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig setOnAction(EventHandler<ActionEvent> value);

    /**
     * Sets the alignment of the text within the {@link TextField}. This property controls how the text is positioned horizontally
     * within the text field, which can be especially useful for fields intended to display numeric input or other content where
     * alignment is significant.
     *
     * @param value
     *         the {@link Pos} value specifying the desired text alignment (e.g., {@code Pos.CENTER_RIGHT} for right-aligned text)
     *
     * @return the current instance of {@link TextFieldConfig} for method chaining
     */
    TextFieldConfig setAlignment(Pos value);

    //endregion Set Functions
}
