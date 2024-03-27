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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textarea.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textinputcontrol.base.TextInputControlConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

/**
 * Provides a fluent interface for configuring {@link TextArea} components in JavaFX applications. This interface extends the capabilities of {@link TextInputControlConfig}, allowing for the customization of
 * {@link TextArea} specific properties such as wrapText, prefColumnCount, prefRowCount, scrollTop, and scrollLeft through various methods.
 *
 * <p>{@code TextAreaConfig} enables the addition and removal of property change listeners, binding and unbinding properties to other observable values, and directly setting property values to customize the
 * appearance and behavior of a {@link TextArea}. This approach promotes cleaner code and a more declarative style of programming.</p>
 *
 * <h2>Usage example:</h2>
 * <pre>{@code
 * TextArea textArea = new TextArea();
 * TextAreaConfigurator.configure(textArea)
 *     .setWrapText(true)
 *     .setPrefRowCount(10)
 *     .addWrapTextChangeListener((observable, oldValue, newValue) -> System.out.println("Wrap Text Changed: " + newValue))
 *     .setScrollTop(100);
 * }</pre>
 *
 * <p>This example demonstrates how to create and configure a {@link TextArea}, including setting the wrap text property, preferred row count, adding a listener for wrap text changes, and setting the scroll top
 * offset.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextInputControlConfig
 * @see TextArea
 * @see ConfiguratorBase
 */
@SuppressWarnings("UnusedReturnValue")
public interface TextAreaConfig<T extends ConfiguratorBase> extends TextInputControlConfig<T> {
    /*
     * Methods Available:
     *  - TextArea getNode()
     *
     * Add Listener Functions
     *  - T addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addWrapTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T addPrefColumnCountChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addPrefColumnCountInvalidationListener(InvalidationListener invalidationListener)
     *  - T addPrefRowCountChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addPrefRowCountInvalidationListener(InvalidationListener invalidationListener)
     *  - T addScrollTopChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addScrollTopInvalidationListener(InvalidationListener invalidationListener)
     *  - T addScrollLeftChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addScrollLeftInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeWrapTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T removePrefColumnCountChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removePrefColumnCountInvalidationListener(InvalidationListener invalidationListener)
     *  - T removePrefRowCountChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removePrefRowCountInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeScrollTopChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeScrollTopInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeScrollLeftChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeScrollLeftInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindWrapTextProperty()
     *  - T bindBidirectionalWrapTextProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalWrapTextProperty(Property<Boolean> otherProperty)
     *  - T bindPrefColumnCountProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindPrefColumnCountProperty()
     *  - T bindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty)
     *  - T bindPrefRowCountProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindPrefRowCountProperty()
     *  - T bindBidirectionalPrefRowCountProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalPrefRowCountProperty(Property<Number> otherProperty)
     *  - T bindScrollTopProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindScrollTopProperty()
     *  - T bindBidirectionalScrollTopProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalScrollTopProperty(Property<Number> otherProperty)
     *  - T bindScrollLeftProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindScrollLeftProperty()
     *  - T bindBidirectionalScrollLeftProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalScrollLeftProperty(Property<Number> otherProperty)
     *
     * Set Functions
     *  - T setWrapText(boolean value)
     *  - T setPrefColumnCount(int value)
     *  - T setPrefRowCount(int value)
     *  - T setScrollTop(double value)
     */

    /**
     * Returns the {@link TextArea} node associated with this configurator.
     *
     * <p>This method provides access to the text area that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link TextArea} associated with the current configurator instance
     */
    @Override
    TextArea getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the wrapText property. This listener is notified whenever the wrapText status changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().wrapTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the wrapText property. This listener is notified whenever the wrapText property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().wrapTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

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
     * Adds a {@link ChangeListener} to listen for changes to the prefRowCount property. This listener is notified whenever the prefRowCount value changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPrefRowCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefRowCountProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the prefRowCount property. This listener is notified whenever the prefRowCount property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPrefRowCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefRowCountProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the scrollTop property. This listener is notified whenever the scrollTop value changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addScrollTopChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scrollTopProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the scrollTop property. This listener is notified whenever the scrollTop property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addScrollTopInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scrollTopProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the scrollLeft property. This listener is notified whenever the scrollLeft value changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addScrollLeftChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scrollLeftProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the scrollLeft property. This listener is notified whenever the scrollLeft property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addScrollLeftInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scrollLeftProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the wrapText property. This listener will no longer be notified of changes to the wrapText status.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().wrapTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the wrapText property. This listener will no longer be notified of invalidations of the wrapText property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().wrapTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

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
     * Removes a {@link ChangeListener} from the prefRowCount property. This listener will no longer be notified of changes to the prefRowCount value.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePrefRowCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefColumnCountProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the prefRowCount property. This listener will no longer be notified of invalidations of the prefRowCount property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePrefRowCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefRowCountProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the scrollTop property. This listener will no longer be notified of changes to the scrollTop value.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeScrollTopChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scrollTopProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the scrollTop property. This listener will no longer be notified of invalidations of the scrollTop property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeScrollTopInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scrollTopProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the scrollLeft property. This listener will no longer be notified of changes to the scrollLeft value.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeScrollLeftChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scrollLeftProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the scrollLeft property. This listener will no longer be notified of invalidations of the scrollLeft property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeScrollLeftInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scrollLeftProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Wrap Text Property

    /**
     * Binds the wrapText property to the provided observable value. The wrapText property will reflect the value of the provided observable value.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().wrapTextProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the wrapText property from any previously bound observable value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindWrapTextProperty() {
        getNode().wrapTextProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the wrapText property bidirectionally to another property.
     *
     * @param otherProperty
     *         The property to bind bidirectionally with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalWrapTextProperty(Property<Boolean> otherProperty) {
        getNode().wrapTextProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the wrapText property from another property bidirectionally.
     *
     * @param otherProperty
     *         The property to unbind from bidirectionally.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalWrapTextProperty(Property<Boolean> otherProperty) {
        getNode().wrapTextProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pref Column Count Property

    /**
     * Binds the prefColumnCount property to the provided observable value. The prefColumnCount property will reflect the value of the provided observable value.
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
     * Unbinds the prefColumnCount property from any previously bound observable value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindPrefColumnCountProperty() {
        getNode().prefColumnCountProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the prefColumnCount property bidirectionally to another property.
     *
     * @param otherProperty
     *         The property to bind bidirectionally with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        getNode().prefColumnCountProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the prefColumnCount property from another property bidirectionally.
     *
     * @param otherProperty
     *         The property to unbind from bidirectionally.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        getNode().prefColumnCountProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pref Row Count Property

    /**
     * Binds the prefRowCount property to the provided observable value. The prefRowCount property will reflect the value of the provided observable value.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindPrefRowCountProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefRowCountProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the prefRowCount property from any previously bound observable value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindPrefRowCountProperty() {
        getNode().prefRowCountProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the prefRowCount property bidirectionally to another property.
     *
     * @param otherProperty
     *         The property to bind bidirectionally with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalPrefRowCountProperty(Property<Number> otherProperty) {
        getNode().prefRowCountProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the prefRowCount property from another property bidirectionally.
     *
     * @param otherProperty
     *         The property to unbind from bidirectionally.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalPrefRowCountProperty(Property<Number> otherProperty) {
        getNode().prefRowCountProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scroll Top Property

    /**
     * Binds the scrollTop property to the provided observable value. The scrollTop property will reflect the value of the provided observable value.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindScrollTopProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scrollTopProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the scrollTop property from any previously bound observable value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindScrollTopProperty() {
        getNode().scrollTopProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the scrollTop property bidirectionally to another property.
     *
     * @param otherProperty
     *         The property to bind bidirectionally with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalScrollTopProperty(Property<Number> otherProperty) {
        getNode().scrollTopProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the scrollTop property from another property bidirectionally.
     *
     * @param otherProperty
     *         The property to unbind from bidirectionally.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalScrollTopProperty(Property<Number> otherProperty) {
        getNode().scrollTopProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scroll Left Property

    /**
     * Binds the scrollLeft property to the provided observable value. The scrollLeft property will reflect the value of the provided observable value.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindScrollLeftProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scrollLeftProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the scrollLeft property from any previously bound observable value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindScrollLeftProperty() {
        getNode().scrollLeftProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the scrollLeft property bidirectionally to another property.
     *
     * @param otherProperty
     *         The property to bind bidirectionally with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalScrollLeftProperty(Property<Number> otherProperty) {
        getNode().scrollLeftProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the scrollLeft property from another property bidirectionally.
     *
     * @param otherProperty
     *         The property to unbind from bidirectionally.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalScrollLeftProperty(Property<Number> otherProperty) {
        getNode().scrollLeftProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the wrapText property to the specified value.
     *
     * @param value
     *         The value to set for the wrapText property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setWrapText(boolean value) {
        getNode().setWrapText(value);
        return getConfigurator();
    }

    /**
     * Sets the prefColumnCount property to the specified value.
     *
     * @param value
     *         The value to set for the prefColumnCount property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setPrefColumnCount(int value) {
        getNode().setPrefColumnCount(value);
        return getConfigurator();
    }

    /**
     * Sets the prefRowCount property to the specified value.
     *
     * @param value
     *         The value to set for the prefRowCount property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setPrefRowCount(int value) {
        getNode().setPrefColumnCount(value);
        return getConfigurator();
    }

    /**
     * Sets the scrollTop property to the specified value.
     *
     * @param value
     *         The value to set for the scrollTop property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setScrollTop(double value) {
        getNode().setScrollTop(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
