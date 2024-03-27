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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textfield.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textinputcontrol.base.TextInputControlConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * Facilitates fluent configuration of {@link TextField} components in JavaFX applications, extending the {@link TextInputControlConfig} with additional features specific to text fields.
 *
 * <p>This interface allows developers to customize text field properties such as prefColumnCount, onAction event handlers, and alignment, alongside the addition, removal, and binding of property listeners for
 * dynamic and responsive UI development.</p>
 *
 * <h2>Key capabilities include:</h2>
 * <ul>
 *   <li>Adding and removing listeners for property changes and invalidations.</li>
 *   <li>Binding properties to other observable values for synchronized updates.</li>
 *   <li>Directly setting property values to configure the text field's behavior and appearance.</li>
 * </ul>
 *
 * <h2>Usage example:</h2>
 * <pre>{@code
 * TextField textField = new TextField();
 * TextFieldConfigurator.configure(textField)
 *     .setPrefColumnCount(20)
 *     .setAlignment(Pos.CENTER)
 *     .addOnActionChangeListener((observable, oldValue, newValue) -> System.out.println("Action Event Changed"))
 *     .setOnAction(event -> System.out.println("TextField Action Performed"));
 * }</pre>
 *
 * <p>This example illustrates the creation of a {@link TextField}, configuring its preferred column count and alignment, adding a change listener for action events, and setting an action event handler.</p>
 *
 * <p>It's designed to be used within a configurator pattern implementation, supporting method chaining by returning the configurator instance (`T`) from each method. This interface thus promotes a cleaner,
 * more intuitive approach to configuring {@link TextField} properties within JavaFX UI development.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextInputControlConfig
 * @see TextField
 * @see ConfiguratorBase
 */
@SuppressWarnings("UnusedReturnValue")
public interface TextFieldConfig<T extends ConfiguratorBase> extends TextInputControlConfig<T> {
    /*
     * Methods Available:
     *  - TextField getNode()
     *
     * Add Listener Functions
     *  - T addPrefColumnCountChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addPrefColumnCountInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener)
     *  - T addOnActionInvalidationListener(InvalidationListener invalidationListener)
     *  - T addAlignmentChangeListener(ChangeListener<? super Pos> changeListener)
     *  - T addAlignmentInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removePrefColumnCountChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removePrefColumnCountInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener)
     *  - T removeOnActionInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener)
     *  - T removeAlignmentInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindPrefColumnCountProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindPrefColumnCountProperty()
     *  - T bindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty)
     *  - T bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue)
     *  - T unbindOnActionProperty()
     *  - T bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty)
     *  - T unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty)
     *  - T bindAlignmentProperty(ObservableValue<? extends Pos> observableValue)
     *  - T unbindAlignmentProperty()
     *  - T bindBidirectionalAlignmentProperty(Property<Pos> otherProperty)
     *  - T unbindBidirectionalAlignmentProperty(Property<Pos> otherProperty)
     *
     * Set Functions
     *  - T setPrefColumnCount(int value)
     *  - T setOnAction(EventHandler<ActionEvent> value)
     *  - T setAlignment(Pos value)
     */

    /**
     * Returns the {@link TextField} node associated with this configurator.
     *
     * <p>This method provides access to the text field that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link TextField} associated with the current configurator instance
     */
    @Override
    TextField getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the prefColumnCount property. This listener is notified whenever the prefColumnCount value changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPrefColumnCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefColumnCountProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the prefColumnCount property. This listener is notified whenever the prefColumnCount property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPrefColumnCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefColumnCountProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the onAction property. This listener is notified whenever the onAction status changes.
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

    /**
     * Adds a {@link ChangeListener} to listen for changes to the alignment property. This listener is notified whenever the alignment status changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        getNode().alignmentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the alignment property. This listener is notified whenever the alignment property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().alignmentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the prefColumnCount property. This listener will no longer be notified of changes to the prefColumnCount value.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePrefColumnCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefColumnCountProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the prefColumnCount property. This listener will no longer be notified of invalidations of the prefColumnCount property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePrefColumnCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefColumnCountProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onAction property. This listener will no longer be notified of changes to the onAction status.
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
     * Removes an {@link InvalidationListener} from the onAction property. This listener will no longer be notified of invalidations of the onAction property.
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

    /**
     * Removes a {@link ChangeListener} from the alignment property. This listener will no longer be notified of changes to the alignment status.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        getNode().alignmentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the alignment property. This listener will no longer be notified of invalidations of the alignment property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().alignmentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Pref Column Count Property

    /**
     * Binds the prefColumnCount property to the specified {@link ObservableValue}. The prefColumnCount property will reflect the value of the observableValue.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindPrefColumnCountProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefColumnCountProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the prefColumnCount property. The prefColumnCount property will no longer be bound to any value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindPrefColumnCountProperty() {
        getNode().prefColumnCountProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the prefColumnCount property bidirectionally to the specified {@link Property}. Changes in one property will update the other.
     *
     * @param otherProperty
     *         The other property to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        getNode().prefColumnCountProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the bidirectional binding between the prefColumnCount property and the specified {@link Property}.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        getNode().prefColumnCountProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Action Property

    /**
     * Binds the onAction property to the specified {@link ObservableValue}. The onAction property will reflect the value of the observableValue.
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
     * Unbinds the onAction property. The onAction property will no longer be bound to any value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindOnActionProperty() {
        getNode().onActionProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the onAction property bidirectionally to the specified {@link Property}. Changes in one property will update the other.
     *
     * @param otherProperty
     *         The other property to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        getNode().onActionProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the bidirectional binding between the onAction property and the specified {@link Property}.
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

    // Alignment Property

    /**
     * Binds the alignment property to the specified {@link ObservableValue}. The alignment property will reflect the value of the observableValue.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindAlignmentProperty(ObservableValue<? extends Pos> observableValue) {
        getNode().alignmentProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the alignment property. The alignment property will no longer be bound to any value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindAlignmentProperty() {
        getNode().alignmentProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the alignment property bidirectionally to the specified {@link Property}. Changes in one property will update the other.
     *
     * @param otherProperty
     *         The other property to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalAlignmentProperty(Property<Pos> otherProperty) {
        getNode().alignmentProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the bidirectional binding between the alignment property and the specified {@link Property}.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalAlignmentProperty(Property<Pos> otherProperty) {
        getNode().alignmentProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the preferred column count of the node.
     *
     * @param value
     *         The preferred column count value to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setPrefColumnCount(int value) {
        getNode().setPrefColumnCount(value);
        return getConfigurator();
    }

    /**
     * Sets the action event handler for the node.
     *
     * @param value
     *         The action event handler to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setOnAction(EventHandler<ActionEvent> value) {
        getNode().setOnAction(value);
        return getConfigurator();
    }

    /**
     * Sets the alignment of the node.
     *
     * @param value
     *         The alignment value to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setAlignment(Pos value) {
        getNode().setAlignment(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
