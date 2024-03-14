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

import io.github.colindj1120.enhancedfx.base.beans.binding.*;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.*;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;

import java.util.function.Consumer;

/**
 * Provides a comprehensive configuration interface for custom JavaFX {@link Control} instances. This interface defines a set of
 * methods to manipulate various aspects of a control's properties, including adding listeners, binding properties, and setting
 * property values. The interface is designed to be extended by specific control configurators, enabling fluent configuration through
 * method chaining.
 * <p>
 * Capabilities include:
 * <ul>
 *     <li>Retrieving and setting the custom control instance being configured.</li>
 *     <li>Adding and removing change and invalidation listeners to properties and binding associations.</li>
 *     <li>Binding properties to observable values and other properties, both uni-directionally and bidirectionally.</li>
 *     <li>Setting values of properties directly, supporting various data types such as {@link Double}, {@link Float},
 *         {@link Integer}, {@link Long}, {@link Number}, {@link Boolean}, {@link String}, and generic types.</li>
 *     <li>Ensuring the integrity and validity of the properties being manipulated through the custom control instance.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Implementors of this interface must ensure that all configuration actions are applied to the specified custom control instance and
 * that property manipulations are validated to prevent unintended effects on unrelated controls.
 * </p>
 * <p>
 *  * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 *  custom
 *  * {@link Control} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 *  * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * @param <T>
 *         The type of the custom control being configured, extending {@link Control}.
 * @param <U>
 *         The type of the configurator implementor, extending {@code CustomConfig<T, U>}, to facilitate fluent method chaining.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 */
@SuppressWarnings("UnusedReturnValue")
public interface CustomConfig<T extends Control, U extends CustomConfig<T, U>> {
    /**
     * Retrieves the custom control instance associated with this configurator. This method is essential for accessing the specific
     * control that is being configured, allowing further operations or queries on the custom control outside the configuration
     * context.
     * <p>
     * It serves as a bridge between the configuration domain and the control's functional domain, enabling the retrieval of the
     * control instance for additional manipulation or inspection as required.
     * </p>
     *
     * @return The custom control {@code T} instance currently associated with this configurator. The method guarantees that the
     *         returned instance is the actual control being configured, facilitating direct interactions with the control.
     */
    T getCustomControl();

    /**
     * Provides a reference to the implementor of this configuration interface. This method is designed to return the instance of the
     * class implementing the {@code CustomConfig} interface, facilitating method chaining and fluent configurations specific to the
     * custom control.
     *
     * <p>
     * Utilizing this method allows for a type-safe way to chain configuration calls specific to the implementor's class, enhancing the
     * usability and readability of the configuration API. It effectively enables the pattern of fluent interfaces by returning the
     * implementor instance, thus allowing for a continuous chain of method calls.
     * </p>
     *
     * @return An instance of {@code U}, which is the type of the implementor. This ensures that the returned object is the same
     *         instance upon which the method is called, allowing for further configuration actions to be applied directly to the
     *         implementor.
     */
    U implementor();

    /**
     * Ensures that the specified property is part of the custom control managed by this configurator. This method checks if the bean
     * of the provided property matches the custom control instance held by the configurator. It is designed to be run in every
     * function where a property of the custom control is passed as an argument to validate its ownership.
     *
     * <p>
     * This method helps maintain the integrity of property-related operations, ensuring that only properties belonging to the custom
     * control are manipulated. It is a safeguard against inadvertent application of configuration actions to unrelated properties,
     * enhancing the robustness of the configurator's functionality.
     * </p>
     *
     * @param <S>
     *         The type of the property's value.
     * @param property
     *         The {@link Property} to validate. This property should have been obtained from the custom control that is being
     *         configured. The method checks that the property's bean (the owning object) is the same as the custom control instance
     *         managed by this configurator.
     *
     * @throws IllegalArgumentException
     *         If the property does not belong to the custom control. This exception indicates that the provided property's bean does
     *         not match the configurator's custom control, suggesting an incorrect or unintended use of the configurator method.
     */
    default <S> void checkPropertyBeanIsValid(Property<S> property) {
        if (property.getBean() != getCustomControl()) {
            throw new IllegalArgumentException("The property does not belong to the configured control.");
        }
    }

    default void checkBindingAssociationBeanIsValid(Object bean) {
        if (!bean.equals(getCustomControl())) {
            throw new IllegalArgumentException("The binding does not belong to the configured control.");
        }
    }

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a change listener to a Boolean property to observe changes. Throws an IllegalArgumentException if the property's bean does
     * not match the custom control.
     *
     * @param property
     *         The Boolean property to observe.
     * @param changeListener
     *         The change listener to notify on value changes.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addBooleanPropertyChangeListener(Property<Boolean> property, ChangeListener<? super Boolean> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a Boolean property to observe invalidations. This listener is notified anytime the property
     * becomes invalid, regardless of the value change. Throw an IllegalArgumentException if the property's bean does not match the
     * custom control.
     *
     * @param property
     *         The Boolean property to observe.
     * @param invalidationListener
     *         The invalidation listener to notify when the property becomes invalid.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addBooleanPropertyInvalidationListener(Property<Boolean> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a generic Object property to monitor changes. An IllegalArgumentException is thrown if the property's
     * bean does not align with the custom control.
     *
     * @param <S>
     *         The type of the property.
     * @param property
     *         The generic Object property to observe.
     * @param changeListener
     *         The change listener to be notified of value changes.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default <S> U addObjectPropertyChangeListener(Property<S> property, ChangeListener<? super S> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a generic Object property for observing invalidations. The listener is triggered whenever the
     * property becomes invalid. An IllegalArgumentException is thrown if the property's bean does not match the custom control.
     *
     * @param <S>
     *         The generic type of the property.
     * @param property
     *         The generic Object property to monitor.
     * @param invalidationListener
     *         The invalidation listener to be notified upon property invalidation.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default <S> U addObjectPropertyInvalidationListener(Property<S> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a Double property to observe changes. Ensures that any modification to the property's value triggers
     * the provided change listener. Throw an IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The Double property to observe.
     * @param changeListener
     *         The listener to notify about value changes.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addDoublePropertyChangeListener(Property<Double> property, ChangeListener<? super Double> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a Double property for observing invalidations. The listener is triggered whenever the property
     * becomes invalid. An IllegalArgumentException is thrown if the property's bean does not match the custom control.
     *
     * @param property
     *         The Double property to monitor.
     * @param invalidationListener
     *         The invalidation listener to be notified upon property becoming invalid.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addDoublePropertyInvalidationListener(Property<Double> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a Float property to track changes. The provided change listener is called whenever the property's
     * value changes. Throw an IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The Float property to observe.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addFloatPropertyChangeListener(Property<Float> property, ChangeListener<? super Float> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a Float property to monitor invalidations. This listener is activated whenever the property
     * becomes invalid. An IllegalArgumentException is thrown if the property's bean does not match the custom control.
     *
     * @param property
     *         The Float property to observe.
     * @param invalidationListener
     *         The invalidation listener to notify upon property becoming invalid.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addFloatPropertyInvalidationListener(Property<Float> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to an Integer property to observe changes. This method ensures that modifications to the property value
     * trigger the provided change listener. An IllegalArgumentException is thrown if the property's bean does not match the custom
     * control.
     *
     * @param property
     *         The Integer property to observe.
     * @param changeListener
     *         The change listener to notify on value changes.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addIntegerPropertyChangeListener(Property<Integer> property, ChangeListener<? super Integer> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to an Integer property to observe invalidations. This listener is notified anytime the property
     * becomes invalid, regardless of the value change. An IllegalArgumentException is thrown if the property's bean does not match the
     * custom control.
     *
     * @param property
     *         The Integer property to observe.
     * @param invalidationListener
     *         The invalidation listener to notify when the property becomes invalid.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addIntegerPropertyInvalidationListener(Property<Integer> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a Long property to monitor changes. Ensures that any modification to the property's value triggers the
     * provided change listener. Throw an IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The Long property to observe.
     * @param changeListener
     *         The listener to notify about value changes.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addLongPropertyChangeListener(Property<Long> property, ChangeListener<? super Long> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a Long property for observing invalidations. The listener is triggered whenever the property
     * becomes invalid. An IllegalArgumentException is thrown if the property's bean does not match the custom control.
     *
     * @param property
     *         The Long property to monitor.
     * @param invalidationListener
     *         The invalidation listener to be notified upon property invalidation.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addLongPropertyInvalidationListener(Property<Long> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a Number property to observe changes. This listener is invoked with any modification to the property's
     * value. An IllegalArgumentException is thrown if the property's bean does not align with the custom control.
     *
     * @param property
     *         The Number property to observe.
     * @param changeListener
     *         The change listener to be notified of value changes.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addNumberPropertyChangeListener(Property<Number> property, ChangeListener<? super Number> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a Number property for invalidation observation. This listener is notified whenever the property
     * becomes invalid, independent of actual value changes. An IllegalArgumentException is thrown if the property's bean does not
     * match the custom control.
     *
     * @param property
     *         The Number property to observe.
     * @param invalidationListener
     *         The invalidation listener to notify on property invalidation.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addNumberPropertyInvalidationListener(Property<Number> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a String property to track changes. The provided change listener is called whenever the property's
     * value changes. Throw an IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The String property to observe.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addStringPropertyChangeListener(Property<String> property, ChangeListener<? super String> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a String property to monitor invalidations. This listener is activated whenever the property
     * becomes invalid. An IllegalArgumentException is thrown if the property's bean does not match the custom control.
     *
     * @param property
     *         The String property to observe.
     * @param invalidationListener
     *         The invalidation listener to notify upon property becoming invalid.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U addStringPropertyInvalidationListener(Property<String> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a {@link EFXBooleanBinding}. Validates the association belongs to the custom control.
     *
     * @param bindingAssociation
     *         The {@link EFXBooleanBinding} to add the change listener to.
     * @param changeListener
     *         The change listener to be added.
     *
     * @return The current configurator instance for chaining.
     */
    default U addBooleanBindingAssociationChangeListener(EFXBooleanBinding bindingAssociation,
                                                         ChangeListener<? super Boolean> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a {@link EFXBooleanBinding}. Validates the association belongs to the custom control.
     *
     * @param bindingAssociation
     *         The {@link EFXBooleanBinding} to add the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be added.
     *
     * @return The current configurator instance for chaining.
     */
    default U addBooleanBindingAssociationInvalidationListener(EFXBooleanBinding bindingAssociation,
                                                               InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to an {@link EFXObjectBinding}. Validates the association belongs to the custom control.
     *
     * @param bindingAssociation
     *         The {@link EFXObjectBinding} to add the change listener to.
     * @param changeListener
     *         The change listener to be added.
     *
     * @return The current configurator instance for chaining.
     */
    default <S> U addObjectBindingAssociationChangeListener(EFXObjectBinding<S> bindingAssociation,
                                                            ChangeListener<? super S> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to an {@link EFXObjectBinding}. Validates the association belongs to the custom control.
     *
     * @param bindingAssociation
     *         The {@link EFXObjectBinding} to add the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be added.
     *
     * @return The current configurator instance for chaining.
     */
    default <S> U addObjectBindingAssociationInvalidationListener(EFXObjectBinding<S> bindingAssociation,
                                                                  InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a {@link EFXDoubleBinding}. Validates the association belongs to the custom control.
     *
     * @param bindingAssociation
     *         The {@link EFXDoubleBinding} to add the change listener to.
     * @param changeListener
     *         The change listener to be added.
     *
     * @return The current configurator instance for chaining.
     */
    default U addDoubleBindingAssociationChangeListener(EFXDoubleBinding bindingAssociation,
                                                        ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a {@link EFXDoubleBinding}. Validates the association belongs to the custom control.
     *
     * @param bindingAssociation
     *         The {@link EFXDoubleBinding} to add the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be added.
     *
     * @return The current configurator instance for chaining.
     */
    default U addDoubleBindingAssociationInvalidationListener(EFXDoubleBinding bindingAssociation,
                                                              InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a {@link EFXFloatBinding}. Ensures the association is valid before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXFloatBinding} to add the change listener to.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U addFloatBindingAssociationChangeListener(EFXFloatBinding bindingAssociation,
                                                       ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a {@link EFXFloatBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXFloatBinding} to add the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U addFloatBindingAssociationInvalidationListener(EFXFloatBinding bindingAssociation,
                                                             InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to an {@link EFXIntegerBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXIntegerBinding} to add the change listener to.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U addIntegerBindingAssociationChangeListener(EFXIntegerBinding bindingAssociation,
                                                         ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to an {@link EFXIntegerBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXIntegerBinding} to add the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U addIntegerBindingAssociationInvalidationListener(EFXIntegerBinding bindingAssociation,
                                                               InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a {@link EFXLongBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXLongBinding} to add the change listener to.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U addLongBindingAssociationChangeListener(EFXLongBinding bindingAssociation,
                                                      ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a {@link EFXLongBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXLongBinding} to add the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U addLongBindingAssociationInvalidationListener(EFXLongBinding bindingAssociation,
                                                            InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a {@link EFXNumberBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXNumberBinding} to add the change listener to.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U addNumberBindingAssociationChangeListener(EFXNumberBinding bindingAssociation,
                                                        ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a {@link EFXNumberBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXNumberBinding} to add the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U addNumberBindingAssociationInvalidationListener(EFXNumberBinding bindingAssociation,
                                                              InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Adds a change listener to a {@link EFXStringBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXStringBinding} to add the change listener to.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U addStringBindingAssociationChangeListener(EFXStringBinding bindingAssociation,
                                                        ChangeListener<? super String> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(changeListener);
        return implementor();
    }

    /**
     * Adds an invalidation listener to a {@link EFXStringBinding}. Validates the association before adding.
     *
     * @param bindingAssociation
     *         The {@link EFXStringBinding} to add the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U addStringBindingAssociationInvalidationListener(EFXStringBinding bindingAssociation,
                                                              InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation.getBean());
        bindingAssociation.addListener(invalidationListener);
        return implementor();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a change listener from a Boolean property. If the property's bean does not match the custom control, an
     * IllegalArgumentException is thrown to indicate the mismatch.
     *
     * @param property
     *         The Boolean property from which the change listener is to be removed.
     * @param changeListener
     *         The change listener to remove.
     *
     * @return The current {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeBooleanPropertyChangeListener(Property<Boolean> property, ChangeListener<? super Boolean> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener from a Boolean property. This operation is subject to validation that the property's bean
     * matches the custom control, throwing an IllegalArgumentException otherwise.
     *
     * @param property
     *         The Boolean property from which the invalidation listener is to be removed.
     * @param invalidationListener
     *         The invalidation listener to remove.
     *
     * @return The current {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeBooleanPropertyInvalidationListener(Property<Boolean> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a property of any type. An IllegalArgumentException is thrown if the property's bean does not
     * match the custom control.
     *
     * @param <S>
     *         The generic type of the property.
     * @param property
     *         The property from which the change listener is to be removed.
     * @param changeListener
     *         The change listener to remove.
     *
     * @return The current {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default <S> U removeObjectPropertyChangeListener(Property<S> property, ChangeListener<? super S> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener from a property of any type. Validates that the property's bean matches the custom control,
     * throwing an IllegalArgumentException if it does not.
     *
     * @param <S>
     *         The generic type of the property.
     * @param property
     *         The property from which the invalidation listener is to be removed.
     * @param invalidationListener
     *         The invalidation listener to remove.
     *
     * @return The current {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default <S> U removeObjectPropertyInvalidationListener(Property<S> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a Double property. An IllegalArgumentException is thrown if the property's bean does not match
     * the custom control.
     *
     * @param property
     *         The Double property from which the change listener is to be removed.
     * @param changeListener
     *         The change listener to remove.
     *
     * @return The current {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeDoublePropertyChangeListener(Property<Double> property, ChangeListener<? super Double> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener from a Double property. This method ensures the integrity of the control's configuration by
     * validating the property's bean against the custom control.
     *
     * @param property
     *         The Double property from which the invalidation listener is to be removed.
     * @param invalidationListener
     *         The invalidation listener to remove.
     *
     * @return The current {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeDoublePropertyInvalidationListener(Property<Double> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a Float property. Throws an IllegalArgumentException if the property's bean does not match the
     * custom control.
     *
     * @param property
     *         The Float property from which the change listener is to be removed.
     * @param changeListener
     *         The change listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeFloatPropertyChangeListener(Property<Float> property, ChangeListener<? super Float> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener from a Float property. Throws an IllegalArgumentException if the property's bean does not match
     * the custom control.
     *
     * @param property
     *         The Float property from which the invalidation listener is to be removed.
     * @param invalidationListener
     *         The invalidation listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeFloatPropertyInvalidationListener(Property<Float> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from an Integer property. Throws an IllegalArgumentException if the property's bean does not match the
     * custom control.
     *
     * @param property
     *         The Integer property from which the change listener is to be removed.
     * @param changeListener
     *         The change listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeIntegerPropertyChangeListener(Property<Integer> property, ChangeListener<? super Integer> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener from an Integer property. Throws an IllegalArgumentException if the property's bean does not
     * match the custom control.
     *
     * @param property
     *         The Integer property from which the invalidation listener is to be removed.
     * @param invalidationListener
     *         The invalidation listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeIntegerPropertyInvalidationListener(Property<Integer> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a Long property. Throws an IllegalArgumentException if the property's bean does not match the
     * custom control.
     *
     * @param property
     *         The Long property from which the change listener is to be removed.
     * @param changeListener
     *         The change listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeLongPropertyChangeListener(Property<Long> property, ChangeListener<? super Long> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener from a Long property. Throws an IllegalArgumentException if the property's bean does not match
     * the custom control.
     *
     * @param property
     *         The Long property from which the invalidation listener is to be removed.
     * @param invalidationListener
     *         The invalidation listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeLongPropertyInvalidationListener(Property<Long> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a Number property. Throws an IllegalArgumentException if the property's bean does not match the
     * custom control.
     *
     * @param property
     *         The Number property from which the change listener is to be removed.
     * @param changeListener
     *         The change listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeNumberPropertyChangeListener(Property<Number> property, ChangeListener<? super Number> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener from a Number property. Throws an IllegalArgumentException if the property's bean does not
     * match the custom control.
     *
     * @param property
     *         The Number property from which the invalidation listener is to be removed.
     * @param invalidationListener
     *         The invalidation listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeNumberPropertyInvalidationListener(Property<Number> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a String property. Throws an IllegalArgumentException if the property's bean does not match the
     * custom control.
     *
     * @param property
     *         The String property from which the change listener is to be removed.
     * @param changeListener
     *         The change listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeStringPropertyChangeListener(Property<String> property, ChangeListener<? super String> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener from a String property. Throws an IllegalArgumentException if the property's bean does not
     * match the custom control.
     *
     * @param property
     *         The String property from which the invalidation listener is to be removed.
     * @param invalidationListener
     *         The invalidation listener to remove.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U removeStringPropertyInvalidationListener(Property<String> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a {@link EFXBooleanBinding}. Validates the association belongs to the custom control.
     *
     * @param bindingAssociation
     *         The {@link EFXBooleanBinding} to remove the change listener from.
     * @param changeListener
     *         The change listener to be removed.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeBooleanBindingAssociationChangeListener(EFXBooleanBinding bindingAssociation,
                                                            ChangeListener<? super Boolean> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener to a {@link EFXBooleanBinding}. Validates the association belongs to the custom
     * control.
     *
     * @param bindingAssociation
     *         The {@link EFXBooleanBinding} to remove the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be removed.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeBooleanBindingAssociationInvalidationListener(EFXBooleanBinding bindingAssociation,
                                                                  InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from an {@link EFXObjectBinding}. Validates the association belongs to the custom control.
     *
     * @param property
     *         The {@link EFXObjectBinding} to remove the change listener from.
     * @param changeListener
     *         The change listener to be removed.
     *
     * @return The current configurator instance for chaining.
     */
    default <S> U removeObjectBindingAssociationChangeListener(EFXObjectBinding<S> property,
                                                               ChangeListener<? super S> changeListener) {
        checkBindingAssociationBeanIsValid(property);
        property.removeListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener to an {@link EFXObjectBinding}. Validates the association belongs to the custom
     * control.
     *
     * @param bindingAssociation
     *         The {@link EFXObjectBinding} to remove the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be removed.
     *
     * @return The current configurator instance for chaining.
     */
    default <S> U removeObjectBindingAssociationInvalidationListener(EFXObjectBinding<S> bindingAssociation,
                                                                     InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a {@link EFXDoubleBinding}. Validates the association belongs to the custom control.
     *
     * @param bindingAssociation
     *         The {@link EFXDoubleBinding} to remove the change listener from.
     * @param changeListener
     *         The change listener to be removed.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeDoubleBindingAssociationChangeListener(EFXDoubleBinding bindingAssociation,
                                                           ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener to a {@link EFXDoubleBinding}. Validates the association belongs to the custom
     * control.
     *
     * @param bindingAssociation
     *         The {@link EFXDoubleBinding} to remove the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be removed.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeDoubleBindingAssociationInvalidationListener(EFXDoubleBinding bindingAssociation,
                                                                 InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a {@link EFXFloatBinding}. Ensures the association is valid before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXFloatBinding} to remove the change listener from.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeFloatBindingAssociationChangeListener(EFXFloatBinding bindingAssociation,
                                                          ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener to a {@link EFXFloatBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXFloatBinding} to remove the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeFloatBindingAssociationInvalidationListener(EFXFloatBinding bindingAssociation,
                                                                InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from an {@link EFXIntegerBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXIntegerBinding} to remove the change listener from.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeIntegerBindingAssociationChangeListener(EFXIntegerBinding bindingAssociation,
                                                            ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener to an {@link EFXIntegerBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXIntegerBinding} to remove the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeIntegerBindingAssociationInvalidationListener(EFXIntegerBinding bindingAssociation,
                                                                  InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a {@link EFXLongBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXLongBinding} to remove the change listener from.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeLongBindingAssociationChangeListener(EFXLongBinding bindingAssociation,
                                                         ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener to a {@link EFXLongBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXLongBinding} to remove the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeLongBindingAssociationInvalidationListener(EFXLongBinding bindingAssociation,
                                                               InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a {@link EFXNumberBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXNumberBinding} to remove the change listener from.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeNumberBindingAssociationChangeListener(EFXNumberBinding bindingAssociation,
                                                           ChangeListener<? super Number> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener to a {@link EFXNumberBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXNumberBinding} to remove the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeNumberBindingAssociationInvalidationListener(EFXNumberBinding bindingAssociation,
                                                                 InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(invalidationListener);
        return implementor();
    }

    /**
     * Removes a change listener from a {@link EFXStringBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXStringBinding} to remove the change listener from.
     * @param changeListener
     *         The change listener to be notified of changes.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeStringBindingAssociationChangeListener(EFXStringBinding bindingAssociation,
                                                           ChangeListener<? super String> changeListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(changeListener);
        return implementor();
    }

    /**
     * Removes an invalidation listener to a {@link EFXStringBinding}. Validates the association before removing.
     *
     * @param bindingAssociation
     *         The {@link EFXStringBinding} to remove the invalidation listener to.
     * @param invalidationListener
     *         The invalidation listener to be notified when the binding becomes invalid.
     *
     * @return The current configurator instance for chaining.
     */
    default U removeStringBindingAssociationInvalidationListener(EFXStringBinding bindingAssociation,
                                                                 InvalidationListener invalidationListener) {
        checkBindingAssociationBeanIsValid(bindingAssociation);
        bindingAssociation.removeListener(invalidationListener);
        return implementor();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //Double Property

    /**
     * Binds a {@link Property<Double>} to an {@link ObservableValue}. This allows the property's value to automatically update
     * whenever the observable value changes. Throws IllegalArgumentException if the property's bean does not match the custom
     * control.
     *
     * @param property
     *         The double property to be bound.
     * @param observableValue
     *         The observable value to bind to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U bindDoubleProperty(Property<Double> property, ObservableValue<? extends Double> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return implementor();
    }

    /**
     * Unbinds a {@link Property<Double>} from its bound {@link ObservableValue}. This stops the property's value from automatically
     * updating. Throws IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The double property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U unbindDoubleProperty(Property<Double> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return implementor();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Double>} objects. Changes to one will be reflected in the other,
     * and vice versa. Throws IllegalArgumentException if either property's bean does not match the custom control.
     *
     * @param property
     *         The first double property.
     * @param otherProperty
     *         The second double property to bind bidirectionally with the first.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U bindBidirectionalDoubleProperty(Property<Double> property, Property<Double> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return implementor();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Double>} objects. After calling, changes to one will no longer
     * affect the other. Throws IllegalArgumentException if either property's bean does not match the custom control.
     *
     * @param property
     *         The first double property.
     * @param otherProperty
     *         The second double property from which to unbind bidirectionally.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U unbindBidirectionalDoubleProperty(Property<Double> property, Property<Double> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return implementor();
    }

    //Float Property

    /**
     * Binds a {@link Property<Float>} to an {@code ObservableValue<? extends Float>}. This allows the property's value to
     * automatically update whenever the observable value changes. An IllegalArgumentException is thrown if the property's bean does
     * not match the custom control, ensuring the property is part of the control being configured.
     *
     * @param property
     *         The float property to be bound.
     * @param observableValue
     *         The observable value to bind to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U bindFloatProperty(Property<Float> property, ObservableValue<? extends Float> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return implementor();
    }

    /**
     * Unbinds a {@link Property<Float>} from its bound {@link ObservableValue}. This stops the property's value from automatically
     * updating. An IllegalArgumentException is thrown if the property's bean does not match the custom control.
     *
     * @param property
     *         The float property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U unbindFloatProperty(Property<Float> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return implementor();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Float>} objects. Changes to one will be reflected in the other,
     * and vice versa. An IllegalArgumentException is thrown if either property's bean does not match the custom control, ensuring both
     * properties are part of the control being configured.
     *
     * @param property
     *         The first float property.
     * @param otherProperty
     *         The second float property to bind bidirectionally with the first.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U bindBidirectionalFloatProperty(Property<Float> property, Property<Float> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return implementor();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Float>} objects. After calling, changes to one will no longer affect
     * the other. An IllegalArgumentException is thrown if either property's bean does not match the custom control, ensuring both
     * properties are part of the control being configured.
     *
     * @param property
     *         The first float property.
     * @param otherProperty
     *         The second float property from which to unbind bidirectionally.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U unbindBidirectionalFloatProperty(Property<Float> property, Property<Float> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return implementor();
    }

    //Integer Property

    /**
     * Binds an {@link Property<Integer>} to an {@code ObservableValue<? extends Integer>}, allowing automatic updates of the
     * property's value when the observable value changes. Throws an IllegalArgumentException if the property's bean does not match the
     * custom control.
     *
     * @param property
     *         The integer property to be bound.
     * @param observableValue
     *         The observable value to bind to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U bindIntegerProperty(Property<Integer> property, ObservableValue<? extends Integer> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return implementor();
    }

    /**
     * Unbinds an {@link Property<Integer>} from its bound {@link ObservableValue}, stopping automatic updates of the property's value.
     * Throws an IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The integer property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U unbindIntegerProperty(Property<Integer> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return implementor();
    }

    /**
     * Creates a bidirectional binding between two {@link Property<Integer>} objects, ensuring mutual updates. Throws an
     * IllegalArgumentException if either property's bean does not match the custom control.
     *
     * @param property
     *         The first integer property.
     * @param otherProperty
     *         The second integer property for the bidirectional binding.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U bindBidirectionalIntegerProperty(Property<Integer> property, Property<Integer> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return implementor();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Integer>} objects. Throws an IllegalArgumentException if either
     * property's bean does not match the custom control.
     *
     * @param property
     *         The first integer property.
     * @param otherProperty
     *         The second integer property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U unbindBidirectionalIntegerProperty(Property<Integer> property, Property<Integer> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return implementor();
    }

    //Long Property

    /**
     * Binds an {@link Property<Long>} to an {@code ObservableValue<? extends Long>}, enabling the property's value to automatically
     * update with the observable value. Throws an IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The long property to be bound.
     * @param observableValue
     *         The observable value to bind to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U bindLongProperty(Property<Long> property, ObservableValue<? extends Long> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return implementor();
    }

    /**
     * Unbinds an {@link Property<Long>} from its bound {@link ObservableValue}. Throws an IllegalArgumentException if the property's
     * bean does not match the custom control.
     *
     * @param property
     *         The long property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U unbindLongProperty(Property<Long> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return implementor();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Long>} objects. Throws an IllegalArgumentException if either
     * property's bean does not match the custom control.
     *
     * @param property
     *         The first long property.
     * @param otherProperty
     *         The second long property for bidirectional binding.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U bindBidirectionalLongProperty(Property<Long> property, Property<Long> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return implementor();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Long>} objects. Throws an IllegalArgumentException if either
     * property's bean does not match the custom control.
     *
     * @param property
     *         The first long property.
     * @param otherProperty
     *         The second long property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U unbindBidirectionalLongProperty(Property<Long> property, Property<Long> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return implementor();
    }

    //Number Property

    /**
     * Binds a {@link Property<Number>} to an {@code ObservableValue<? extends Number>}, facilitating the property's automatic update
     * when the observable value changes. Throws an IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The number property to be bound.
     * @param observableValue
     *         The observable value to bind to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U bindNumberProperty(Property<Number> property, ObservableValue<? extends Number> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return implementor();
    }

    /**
     * Unbinds a {@link Property<Number>} from its bound {@link ObservableValue}. Throws an IllegalArgumentException if the property's
     * bean does not match the custom control.
     *
     * @param property
     *         The number property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U unbindNumberProperty(Property<Number> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return implementor();
    }

    /**
     * Creates a bidirectional binding between two {@link Property<Number>} objects. Throws an IllegalArgumentException if either
     * property's bean does not match the custom control.
     *
     * @param property
     *         The first number property.
     * @param otherProperty
     *         The second number property for bidirectional binding.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U bindBidirectionalNumberProperty(Property<Number> property, Property<Number> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return implementor();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Number>} objects. Throws an IllegalArgumentException if either
     * property's bean does not match the custom control.
     *
     * @param property
     *         The first number property.
     * @param otherProperty
     *         The second number property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U unbindBidirectionalNumberProperty(Property<Number> property, Property<Number> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return implementor();
    }

    //Boolean Property

    /**
     * Binds a {@link Property<Boolean>} to an {@code ObservableValue<? extends Boolean>}, enabling automatic updates of the property's
     * value when the observable value changes. An IllegalArgumentException is thrown if the property's bean does not match the custom
     * control.
     *
     * @param property
     *         The boolean property to be bound.
     * @param observableValue
     *         The observable value to bind to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U bindBooleanProperty(Property<Boolean> property, ObservableValue<? extends Boolean> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return implementor();
    }

    /**
     * Unbinds a {@link Property<Boolean>} from its bound {@link ObservableValue}, ceasing automatic updates of the property's value.
     * An IllegalArgumentException is thrown if the property's bean does not match the custom control.
     *
     * @param property
     *         The boolean property to unbind.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U unbindBooleanProperty(Property<Boolean> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return implementor();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Boolean>} objects, ensuring mutual updates. An
     * IllegalArgumentException is thrown if either property's bean does not match the custom control.
     *
     * @param property
     *         The first boolean property.
     * @param otherProperty
     *         The second boolean property for the bidirectional binding.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U bindBidirectionalBooleanProperty(Property<Boolean> property, Property<Boolean> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return implementor();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Boolean>} objects. An IllegalArgumentException is thrown if either
     * property's bean does not match the custom control.
     *
     * @param property
     *         The first boolean property.
     * @param otherProperty
     *         The second boolean property to unbind bidirectionally.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U unbindBidirectionalBooleanProperty(Property<Boolean> property, Property<Boolean> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return implementor();
    }

    //String Property

    /**
     * Binds a {@link Property<String>} to an {@code ObservableValue<? extends String>}, allowing the property's value to automatically
     * update with the observable value. Throws an IllegalArgumentException if the property's bean does not match the custom control.
     *
     * @param property
     *         The string property to be bound.
     * @param observableValue
     *         The observable value to bind to the property.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U bindStringProperty(Property<String> property, ObservableValue<? extends String> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return implementor();
    }

    /**
     * Unbinds a {@link Property<String>} from its bound {@link ObservableValue}. Throws an IllegalArgumentException if the property's
     * bean does not match the custom control.
     *
     * @param property
     *         The string property to unbind.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U unbindStringProperty(Property<String> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return implementor();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<String>} objects. Throws an IllegalArgumentException if either
     * property's bean does not match the custom control.
     *
     * @param property
     *         The first string property.
     * @param otherProperty
     *         The second string property for bidirectional binding.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U bindBidirectionalStringProperty(Property<String> property, Property<String> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return implementor();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<String>} objects. Throws an IllegalArgumentException if either
     * property's bean does not match the custom control.
     *
     * @param property
     *         The first string property.
     * @param otherProperty
     *         The second string property to unbind bidirectionally.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default U unbindBidirectionalStringProperty(Property<String> property, Property<String> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return implementor();
    }

    //Object Property

    /**
     * Binds a generic {@link Property<S>} to an {@code ObservableValue<? extends S>}, allowing the property's value to automatically
     * update with changes in the observable value. Throws an IllegalArgumentException if the property's bean does not match the custom
     * control.
     *
     * @param <S>
     *         The type of the property.
     * @param property
     *         The property to be bound.
     * @param observableValue
     *         The observable value to bind to the property.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default <S> U bindObjectProperty(Property<S> property, ObservableValue<? extends S> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return implementor();
    }

    /**
     * Unbinds a generic {@link Property<S>} from its bound {@link ObservableValue}. Throws an IllegalArgumentException if the
     * property's bean does not match the custom control.
     *
     * @param <S>
     *         The type of the property.
     * @param property
     *         The property to unbind.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default <S> U unbindObjectProperty(Property<S> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return implementor();
    }

    /**
     * Establishes a bidirectional binding between two generic {@link Property<S>} objects. Throws an IllegalArgumentException if
     * either property's bean does not match the custom control.
     *
     * @param <S>
     *         The type of the property.
     * @param property
     *         The first property.
     * @param otherProperty
     *         The second property for bidirectional binding.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default <S> U bindBidirectionalObjectProperty(Property<S> property, Property<S> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return implementor();
    }

    /**
     * Removes a bidirectional binding between two generic {@link Property<S>} objects. Throws an IllegalArgumentException if either
     * property's bean does not match the custom control.
     *
     * @param <S>
     *         The type of the property.
     * @param property
     *         The first property.
     * @param otherProperty
     *         The second property to unbind bidirectionally.
     *
     * @return The {@code U} instance for method chaining.
     *
     * @throws IllegalArgumentException
     *         If either property's bean does not match the custom control.
     */
    default <S> U unbindBidirectionalObjectProperty(Property<S> property, Property<S> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return implementor();
    }

    //endregion Binding Functions

    //region Set Bindings Functions
    //*****************************************************************
    // Set Bindings Functions
    //*****************************************************************

    /**
     * Sets and modifies a {@link EFXDoubleBinding} with a new {@link DoubleBinding} and bean. Validates the bean before
     * creating a new association and applies the modification through the provided consumer.
     *
     * @param observableValue
     *         The new {@link DoubleBinding} to associate.
     * @param bean
     *         The bean associated with the binding.
     * @param associationModifier
     *         A {@link Consumer} that modifies the newly created {@link EFXDoubleBinding}.
     *
     * @return The current configurator instance for chaining.
     */
    default U setDoubleAssociationBinding(DoubleBinding observableValue, Object bean,
                                          Consumer<EFXDoubleBinding> associationModifier) {
        checkBindingAssociationBeanIsValid(bean);
        associationModifier.accept(EFXDoubleBinding.create(observableValue, bean));
        return implementor();
    }

    /**
     * Sets and modifies a {@link EFXFloatBinding} with a new {@link FloatBinding} and bean. Validates the bean before creating
     * a new association and applies the modification through the provided consumer.
     *
     * @param observableValue
     *         The new {@link FloatBinding} to associate.
     * @param bean
     *         The bean associated with the binding.
     * @param associationModifier
     *         A {@link Consumer} that modifies the newly created {@link EFXFloatBinding}.
     *
     * @return The current configurator instance for chaining.
     */
    default U setFloatAssociationBinding(FloatBinding observableValue, Object bean,
                                         Consumer<EFXFloatBinding> associationModifier) {
        checkBindingAssociationBeanIsValid(bean);
        associationModifier.accept(EFXFloatBinding.create(observableValue, bean));
        return implementor();
    }

    /**
     * Sets and modifies an {@link EFXIntegerBinding} with a new {@link IntegerBinding} and bean. Validates the bean before
     * creating a new association and applies the modification through the provided consumer.
     *
     * @param observableValue
     *         The new {@link IntegerBinding} to associate.
     * @param bean
     *         The bean associated with the binding.
     * @param associationModifier
     *         A {@link Consumer} that modifies the newly created {@link EFXIntegerBinding}.
     *
     * @return The current configurator instance for chaining.
     */
    default U setIntegerAssociationBinding(IntegerBinding observableValue, Object bean,
                                           Consumer<EFXIntegerBinding> associationModifier) {
        checkBindingAssociationBeanIsValid(bean);
        associationModifier.accept(EFXIntegerBinding.create(observableValue, bean));
        return implementor();
    }

    /**
     * Sets and modifies a {@link EFXLongBinding} with a new {@link LongBinding} and bean. Validates the bean before creating a
     * new association and applies the modification through the provided consumer.
     *
     * @param observableValue
     *         The new {@link LongBinding} to associate.
     * @param bean
     *         The bean associated with the binding.
     * @param associationModifier
     *         A {@link Consumer} that modifies the newly created {@link EFXLongBinding}.
     *
     * @return The current configurator instance for chaining.
     */
    default U setLongAssociationBinding(LongBinding observableValue, Object bean,
                                        Consumer<EFXLongBinding> associationModifier) {
        checkBindingAssociationBeanIsValid(bean);
        associationModifier.accept(EFXLongBinding.create(observableValue, bean));
        return implementor();
    }

    /**
     * Sets and modifies a {@link EFXBooleanBinding} with a new {@link BooleanBinding} and bean. Validates the bean before
     * creating a new association and applies the modification through the provided consumer.
     *
     * @param observableValue
     *         The new {@link BooleanBinding} to associate.
     * @param bean
     *         The bean associated with the binding.
     * @param associationModifier
     *         A {@link Consumer} that modifies the newly created {@link EFXBooleanBinding}.
     *
     * @return The current configurator instance for chaining.
     */
    default U setBooleanAssociationBinding(BooleanBinding observableValue, Object bean,
                                           Consumer<EFXBooleanBinding> associationModifier) {
        checkBindingAssociationBeanIsValid(bean);
        associationModifier.accept(EFXBooleanBinding.create(observableValue, bean));
        return implementor();
    }

    /**
     * Sets and modifies a {@link EFXStringBinding} with a new {@link StringBinding} and bean. Validates the bean before
     * creating a new association and applies the modification through the provided consumer.
     *
     * @param observableValue
     *         The new {@link StringBinding} to associate.
     * @param bean
     *         The bean associated with the binding.
     * @param associationModifier
     *         A {@link Consumer} that modifies the newly created {@link EFXStringBinding}.
     *
     * @return The current configurator instance for chaining.
     */
    default U setStringAssociationBinding(StringBinding observableValue, Object bean,
                                          Consumer<EFXStringBinding> associationModifier) {
        checkBindingAssociationBeanIsValid(bean);
        associationModifier.accept(EFXStringBinding.create(observableValue, bean));
        return implementor();
    }

    /**
     * Sets and modifies an {@link EFXObjectBinding} with a new {@link ObjectBinding} and bean. Validates the bean before
     * creating a new association and applies the modification through the provided consumer.
     *
     * @param <S>
     *         The type of the object in the {@link ObjectBinding}.
     * @param observableValue
     *         The new {@link ObjectBinding} to associate.
     * @param bean
     *         The bean associated with the binding.
     * @param associationModifier
     *         A {@link Consumer} that modifies the newly created {@link EFXObjectBinding}.
     *
     * @return The current configurator instance for chaining.
     */
    default <S> U setObjectAssociationBinding(ObjectBinding<S> observableValue, Object bean,
                                              Consumer<EFXObjectBinding<S>> associationModifier) {
        checkBindingAssociationBeanIsValid(bean);
        associationModifier.accept(EFXObjectBinding.create(observableValue, bean));
        return implementor();
    }

    //endregion Set Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the value of a specified {@link Property<Double>} within the custom control. If the property's bean does not match the
     * custom control, an IllegalArgumentException is thrown to ensure the property belongs to the control being configured.
     *
     * @param property
     *         The double property to be set.
     * @param value
     *         The new value to assign to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U setDoublePropertyValue(Property<Double> property, Double value) {
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return implementor();
    }

    /**
     * Sets the value of a specified {@link Property<Float>} within the custom control. Throws an IllegalArgumentException if the
     * property's bean does not correspond to the custom control.
     *
     * @param property
     *         The float property to be set.
     * @param value
     *         The new value to assign to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U setFloatPropertyValue(Property<Float> property, Float value) {
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return implementor();
    }

    /**
     * Sets the value of a specified {@link Property<Integer>} within the custom control. An IllegalArgumentException is thrown if the
     * property's bean does not match the custom control, ensuring property integrity.
     *
     * @param property
     *         The integer property to be set.
     * @param value
     *         The new value to assign to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U setIntegerPropertyValue(Property<Integer> property, Integer value) {
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return implementor();
    }

    /**
     * Sets the value of a specified {@link Property<Long>} within the custom control. Verifies the property's bean matches the custom
     * control, throwing IllegalArgumentException if not.
     *
     * @param property
     *         The long property to be set.
     * @param value
     *         The new value to assign to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U setLongPropertyValue(Property<Long> property, Long value) {
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return implementor();
    }

    /**
     * Sets the value of a specified {@link Property<Number>} within the custom control. Ensures the property belongs to the custom
     * control, throwing an IllegalArgumentException for mismatched beans.
     *
     * @param property
     *         The number property to be set.
     * @param value
     *         The new value to assign to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U setNumberPropertyValue(Property<Number> property, Number value) {
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return implementor();
    }

    /**
     * Sets the value of a specified {@link Property<Boolean>} within the custom control. If the property's bean does not match, an
     * IllegalArgumentException is thrown to maintain consistency.
     *
     * @param property
     *         The boolean property to be set.
     * @param value
     *         The new boolean value to assign to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U setBooleanPropertyValue(Property<Boolean> property, boolean value) {
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return implementor();
    }

    /**
     * Sets the value of a specified {@link Property<String>} within the custom control. Checks for property ownership, throwing an
     * IllegalArgumentException for any bean mismatch.
     *
     * @param property
     *         The string property to be set.
     * @param value
     *         The new string to assign to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default U setStringPropertyValue(Property<String> property, String value) {
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return implementor();
    }

    /**
     * Sets the value of a specified {@link Property<S>} with a generic type within the custom control. Verifies the property belongs
     * to the control, throwing an IllegalArgumentException if the property's bean does not match.
     *
     * @param <S>
     *         The type of the property's value.
     * @param property
     *         The generic property to be set, accommodating any type.
     * @param value
     *         The new value to assign to the property.
     *
     * @return The {@code U} instance to allow for method chaining.
     *
     * @throws IllegalArgumentException
     *         If the property's bean does not match the custom control.
     */
    default <S> U setObjectPropertyValue(Property<S> property, S value) {
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return implementor();
    }

    //endregion Set Functions
}
