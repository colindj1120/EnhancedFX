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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base;

import io.github.colindj1120.enhancedfx.base.beans.binding.*;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import io.github.colindj1120.enhancedfx.utils.exceptions.InvalidAssociationException;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.*;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

import java.util.function.Consumer;

/**
 * Provides a generic interface for configurators, designed to enable fluent and dynamic configuration of JavaFX properties, event listeners, bindings, and custom EFX bindings within the context of a JavaFX
 * application. This interface is intended to serve as a backbone for creating configurable UI components, allowing developers to extend and customize the behavior and properties of JavaFX Nodes through a
 * chainable, declarative API.
 *
 * <h2>Implementing Classes</h2>
 * <em>Need to implement:</em>
 * <ul>
 *     <li>{@code getConfigurator()}</li>
 *     <li>{@code getNode()}</li>
 * </ul>
 *
 * <em>This enables a wide range of default functions for the implement class</em>
 * <ul>
 *     <li>Dynamic Property Binding</li>
 *     <li>Listener Management</li>
 *     <li>Custom EFX Binding Initialization</li>
 *     <li>Direct Property Value Setting</li>
 * </ul>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Retrieval of Configurator:</b> Access the configurator instance for method chaining.</li>
 *     <li><b>Node Access:</b> Obtain the JavaFX Node associated with the configurator for property manipulation.</li>
 *     <li><b>Property Validation:</b> Validate JavaFX property associations to ensure correct configuration application.</li>
 *     <li><b>Listener Management:</b> Add and remove listeners to react to property changes and invalidations.</li>
 *     <li><b>Binding Management:</b> Create and manage property bindings for synchronized value updates.</li>
 *     <li><b>Custom EFX Binding Support:</b> Initialize and manage custom EFX bindings for advanced binding capabilities.</li>
 *     <li><b>Property Value Setting:</b> Directly set property values for immediate state updates.</li>
 *     <li>Each method includes checks to ensure that the properties and bindings are correctly associated with the targeted JavaFX node, throwing {@link InvalidAssociationException} if mismatches are
 *     identified</li>
 *     <li>Each set method includes checks to ensure the the properties on which you are performing the set option are not null, throwing {@link IllegalArgumentException} if null is encountered</li>
 * </ul>
 *
 * <h2>Usage example:</h2>
 * <pre>
 * {@code
 * // Assuming a concrete implementation of BaseCustomConfig for a custom control
 * MyCustomConfigurator configurator = new MyCustomConfigurator(myCustomNode);
 *
 * // Fluent API usage for property binding, listener addition, and direct value setting
 * configurator
 *     .bindDoubleProperty(myCustomNode.doubleProperty(), otherDoubleObservableValue)
 *     .addBooleanPropertyChangeListener(myCustomNode.booleanProperty(), (obs, oldVal, newVal) -> System.out.println("New value: " + newVal))
 *     .setIntegerPropertyValue(myCustomNode.integerProperty(), 42);
 * }
 * </pre>
 *
 * <p>This approach enables a modular, readable, and maintainable way of configuring UI components and their behaviors,
 * significantly enhancing the developer experience when working with JavaFX.</p>
 *
 * @param <T>
 *         The type parameter indicating the concrete configurator class, allowing for fluent type-safe method chaining.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ConfiguratorBase
 * @see EFXObjectUtils
 */
@SuppressWarnings("UnusedReturnValue")
public interface BaseCustomConfig<T extends ConfiguratorBase> {
    /*
     * Methods Available:
     *  - T getConfigurator()
     *  - Node getNode()
     *  - <S> void checkPropertyBeanIsValid(Property<S> property)
     *  - void checkEFXBindingBeanIsValid(Object bean)
     *
     * Add Listener Functions
     *  - T addBooleanPropertyChangeListener(Property<Boolean> property, ChangeListener<? super Boolean> changeListener)
     *  - T addBooleanPropertyInvalidationListener(Property<Boolean> property, InvalidationListener invalidationListener)
     *  - <S> T addObjectPropertyChangeListener(Property<S> property, ChangeListener<? super S> changeListener)
     *  - <S> T addObjectPropertyInvalidationListener(Property<S> property, InvalidationListener invalidationListener)
     *  - T addDoublePropertyChangeListener(Property<Double> property, ChangeListener<? super Double> changeListener)
     *  - T addDoublePropertyInvalidationListener(Property<Double> property, InvalidationListener invalidationListener)
     *  - T addFloatPropertyChangeListener(Property<Float> property, ChangeListener<? super Float> changeListener)
     *  - T addFloatPropertyInvalidationListener(Property<Float> property, InvalidationListener invalidationListener)
     *  - T addIntegerPropertyChangeListener(Property<Integer> property, ChangeListener<? super Integer> changeListener)
     *  - T addIntegerPropertyInvalidationListener(Property<Integer> property, InvalidationListener invalidationListener)
     *  - T addLongPropertyChangeListener(Property<Long> property, ChangeListener<? super Long> changeListener)
     *  - T addLongPropertyInvalidationListener(Property<Long> property, InvalidationListener invalidationListener)
     *  - T addNumberPropertyChangeListener(Property<Number> property, ChangeListener<? super Number> changeListener)
     *  - T addNumberPropertyInvalidationListener(Property<Number> property, InvalidationListener invalidationListener)
     *  - T addStringPropertyChangeListener(Property<String> property, ChangeListener<? super String> changeListener)
     *  - T addStringPropertyInvalidationListener(Property<String> property, InvalidationListener invalidationListener)
     *  - T addEFXBooleanBindingChangeListener(EFXBooleanBinding efxBinding, ChangeListener<? super Boolean> changeListener)
     *  - T addEFXBooleanBindingInvalidationListener(EFXBooleanBinding efxBinding, InvalidationListener invalidationListener)
     *  - <S> T addEFXObjectBindingChangeListener(EFXObjectBinding<S> efxBinding, ChangeListener<? super S> changeListener)
     *  - <S> T addEFXObjectBindingInvalidationListener(EFXObjectBinding<S> efxBinding, InvalidationListener invalidationListener)
     *  - T addEFXDoubleBindingChangeListener(EFXDoubleBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T addEFXDoubleBindingInvalidationListener(EFXDoubleBinding efxBinding, InvalidationListener invalidationListener)
     *  - T addEFXFloatBindingChangeListener(EFXFloatBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T addEFXFloatBindingInvalidationListener(EFXFloatBinding efxBinding, InvalidationListener invalidationListener)
     *  - T addEFXIntegerBindingChangeListener(EFXIntegerBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T addEFXIntegerBindingInvalidationListener(EFXIntegerBinding efxBinding, InvalidationListener invalidationListener)
     *  - T addEFXLongBindingChangeListener(EFXLongBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T addEFXLongBindingInvalidationListener(EFXLongBinding efxBinding, InvalidationListener invalidationListener)
     *  - T addEFXNumberBindingChangeListener(EFXNumberBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T addEFXNumberBindingInvalidationListener(EFXNumberBinding efxBinding, InvalidationListener invalidationListener)
     *  - T addEFXStringBindingChangeListener(EFXStringBinding efxBinding, ChangeListener<? super String> changeListener)
     *  - T addEFXStringBindingInvalidationListener(EFXStringBinding efxBinding, InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeBooleanPropertyChangeListener(Property<Boolean> property, ChangeListener<? super Boolean> changeListener)
     *  - T removeBooleanPropertyInvalidationListener(Property<Boolean> property, InvalidationListener invalidationListener)
     *  - <S> T removeObjectPropertyChangeListener(Property<S> property, ChangeListener<? super S> changeListener)
     *  - <S> T removeObjectPropertyInvalidationListener(Property<S> property, InvalidationListener invalidationListener)
     *  - T removeDoublePropertyChangeListener(Property<Double> property, ChangeListener<? super Double> changeListener)
     *  - T removeDoublePropertyInvalidationListener(Property<Double> property, InvalidationListener invalidationListener)
     *  - T removeFloatPropertyChangeListener(Property<Float> property, ChangeListener<? super Float> changeListener)
     *  - T removeFloatPropertyInvalidationListener(Property<Float> property, InvalidationListener invalidationListener)
     *  - T removeIntegerPropertyChangeListener(Property<Integer> property, ChangeListener<? super Integer> changeListener)
     *  - T removeIntegerPropertyInvalidationListener(Property<Integer> property, InvalidationListener invalidationListener)
     *  - T removeLongPropertyChangeListener(Property<Long> property, ChangeListener<? super Long> changeListener)
     *  - T removeLongPropertyInvalidationListener(Property<Long> property, InvalidationListener invalidationListener)
     *  - T removeNumberPropertyChangeListener(Property<Number> property, ChangeListener<? super Number> changeListener)
     *  - T removeNumberPropertyInvalidationListener(Property<Number> property, InvalidationListener invalidationListener)
     *  - T removeStringPropertyChangeListener(Property<String> property, ChangeListener<? super String> changeListener)
     *  - T removeStringPropertyInvalidationListener(Property<String> property, InvalidationListener invalidationListener)
     *  - T removeEFXBooleanBindingChangeListener(EFXBooleanBinding efxBinding, ChangeListener<? super Boolean> changeListener)
     *  - T removeEFXBooleanBindingInvalidationListener(EFXBooleanBinding efxBinding, InvalidationListener invalidationListener)
     *  - <S> T removeEFXObjectBindingChangeListener(EFXObjectBinding<S> efxBinding, ChangeListener<? super S> changeListener)
     *  - <S> T removeEFXObjectBindingInvalidationListener(EFXObjectBinding<S> efxBinding, InvalidationListener invalidationListener)
     *  - T removeEFXDoubleBindingChangeListener(EFXDoubleBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T removeEFXDoubleBindingInvalidationListener(EFXDoubleBinding efxBinding, InvalidationListener invalidationListener)
     *  - T removeEFXFloatBindingChangeListener(EFXFloatBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T removeEFXFloatBindingInvalidationListener(EFXFloatBinding efxBinding, InvalidationListener invalidationListener)
     *  - T removeEFXIntegerBindingChangeListener(EFXIntegerBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T removeEFXIntegerBindingInvalidationListener(EFXIntegerBinding efxBinding, InvalidationListener invalidationListener)
     *  - T removeEFXLongBindingChangeListener(EFXLongBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T removeEFXLongBindingInvalidationListener(EFXLongBinding efxBinding, InvalidationListener invalidationListener)
     *  - T removeEFXNumberBindingChangeListener(EFXNumberBinding efxBinding, ChangeListener<? super Number> changeListener)
     *  - T removeEFXNumberBindingInvalidationListener(EFXNumberBinding efxBinding, InvalidationListener invalidationListener)
     *  - T removeEFXStringBindingChangeListener(EFXStringBinding efxBinding, ChangeListener<? super String> changeListener)
     *  - T removeEFXStringBindingInvalidationListener(EFXStringBinding efxBinding, InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindDoubleProperty(Property<Double> property, ObservableValue<? extends Double> observableValue)
     *  - T unbindDoubleProperty(Property<Double> property)
     *  - T bindBidirectionalDoubleProperty(Property<Double> property, Property<Double> otherProperty)
     *  - T unbindBidirectionalDoubleProperty(Property<Double> property, Property<Double> otherProperty)
     *
     *  - T bindFloatProperty(Property<Float> property, ObservableValue<? extends Float> observableValue)
     *  - T unbindFloatProperty(Property<Float> property)
     *  - T bindBidirectionalFloatProperty(Property<Float> property, Property<Float> otherProperty)
     *  - T unbindBidirectionalFloatProperty(Property<Float> property, Property<Float> otherProperty)
     *
     *  - T bindIntegerProperty(Property<Integer> property, ObservableValue<? extends Integer> observableValue)
     *  - T unbindIntegerProperty(Property<Integer> property)
     *  - T bindBidirectionalIntegerProperty(Property<Integer> property, Property<Integer> otherProperty)
     *  - T unbindBidirectionalIntegerProperty(Property<Integer> property, Property<Integer> otherProperty)
     *
     *  - T bindLongProperty(Property<Long> property, ObservableValue<? extends Long> observableValue)
     *  - T unbindLongProperty(Property<Long> property)
     *  - T bindBidirectionalLongProperty(Property<Long> property, Property<Long> otherProperty)
     *  - T unbindBidirectionalLongProperty(Property<Long> property, Property<Long> otherProperty)
     *
     *  - T bindNumberProperty(Property<Number> property, ObservableValue<? extends Number> observableValue)
     *  - T unbindNumberProperty(Property<Number> property)
     *  - T bindBidirectionalNumberProperty(Property<Number> property, Property<Number> otherProperty)
     *  - T unbindBidirectionalNumberProperty(Property<Number> property, Property<Number> otherProperty)
     *
     *  - T bindBooleanProperty(Property<Boolean> property, ObservableValue<? extends Boolean> observableValue)
     *  - T unbindBooleanProperty(Property<Boolean> property)
     *  - T bindBidirectionalBooleanProperty(Property<Boolean> property, Property<Boolean> otherProperty)
     *  - T unbindBidirectionalBooleanProperty(Property<Boolean> property, Property<Boolean> otherProperty)
     *
     *  - T bindStringProperty(Property<String> property, ObservableValue<? extends String> observableValue)
     *  - T unbindStringProperty(Property<String> property)
     *  - T bindBidirectionalStringProperty(Property<String> property, Property<String> otherProperty)
     *  - T unbindBidirectionalStringProperty(Property<String> property, Property<String> otherProperty)
     *
     *  - <S> T bindObjectProperty(Property<S> property, ObservableValue<? extends S> observableValue)
     *  - <S> T unbindObjectProperty(Property<S> property)
     *  - <S> T bindBidirectionalObjectProperty(Property<S> property, Property<S> otherProperty)
     *  - <S> T unbindBidirectionalObjectProperty(Property<S> property, Property<S> otherProperty)
     *
     * Initialize EFXBindings Functions
     *  - T initializeEFXDoubleBinding(DoubleBinding observableValue, Object bean, Consumer<EFXDoubleBinding> efxInitializer)
     *  - T initializeEFXFloatBinding(FloatBinding observableValue, Object bean, Consumer<EFXFloatBinding> efxInitializer)
     *  - T initializeEFXIntegerBinding(IntegerBinding observableValue, Object bean, Consumer<EFXIntegerBinding> efxInitializer)
     *  - T initializeEFXLongBinding(LongBinding observableValue, Object bean, Consumer<EFXLongBinding> efxInitializer)
     *  - T initializeEFXBooleanBinding(BooleanBinding observableValue, Object bean, Consumer<EFXBooleanBinding> efxInitializer)
     *  - T initializeEFXStringBinding(StringBinding observableValue, Object bean, Consumer<EFXStringBinding> efxInitializer)
     *  - <S> T initializeEFXObjectBinding(ObjectBinding<S> observableValue, Object bean, Consumer<EFXObjectBinding<S>> efxInitializer)
     *
     * Set Functions
     *  - T setDoublePropertyValue(Property<Double> property, Double value)
     *  - T setFloatPropertyValue(Property<Float> property, Float value)
     *  - T setIntegerPropertyValue(Property<Integer> property, Integer value)
     *  - T setLongPropertyValue(Property<Long> property, Long value)
     *  - T setNumberPropertyValue(Property<Number> property, Number value)
     *  - T setBooleanPropertyValue(Property<Boolean> property, boolean value)
     *  - T setStringPropertyValue(Property<String> property, String value)
     *  - <S> T setObjectPropertyValue(Property<S> property, S value)
     *  - T setEFXDoubleBinding(EFXDoubleBinding efxBinding, DoubleBinding newBinding)
     *  - T setEFXFloatBinding(EFXFloatBinding efxBinding, FloatBinding newBinding)
     *  - T setEFXIntegerBinding(EFXIntegerBinding efxBinding, IntegerBinding newBinding)
     *  - T setEFXLongBinding(EFXLongBinding efxBinding, LongBinding newBinding)
     *  - T setEFXBooleanBinding(EFXBooleanBinding efxBinding, BooleanBinding newBinding)
     *  - T setEFXStringBinding(EFXStringBinding efxBinding, StringBinding newBinding)
     *  - <S> T setEFXObjectBinding(EFXObjectBinding<S> efxBinding, ObjectBinding<S> newBinding)
     */

    /**
     * Retrieves the current instance of the configurator.
     *
     * <p>
     * This method is instrumental in implementing the configurator or builder pattern within the application, primarily used for configuring components, controls, or other entities in a fluent and intuitive
     * manner. It returns the current instance of the configurator, allowing for the chaining of configuration methods to define the setup in a concise and readable format.
     * </p>
     *
     * <p>
     * The returned configurator instance provides access to a suite of configuration methods tailored to the specific needs of the component or control being configured. This design pattern enhances code
     * readability and maintainability by consolidating configuration steps into a single, fluent sequence of method calls.
     * </p>
     *
     * <p>
     * Employing the configurator pattern with this method is particularly beneficial in complex UI setups, initialization logic, or any scenario where multiple configuration steps are required. It simplifies
     * the codebase by eliminating the need for repetitive accessors or boilerplate code, focusing instead on a clear and logical sequence of configuration actions.
     * </p>
     *
     * <p>
     * Implementers of this method should ensure that the configurator instance returned is properly initialized and ready to perform the necessary configuration tasks. The method's usability extends across
     * various contexts, including but not limited to UI component setup, application configuration, and dynamic content generation.
     * </p>
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining within the application.
     */
    T getConfigurator();

    /**
     * Retrieves the JavaFX {@link Node} that is the target of this configurator.
     *
     * <p>
     * This method is central to the configuration process in JavaFX applications, providing direct access to the UI component or control that is currently being configured. The returned {@link Node} serves as
     * the foundation for various configuration operations, such as setting properties, applying styles, attaching listeners, and more. It enables the configurator to apply configurations directly to the
     * correct UI element, ensuring that all modifications are relevant and correctly applied.
     * </p>
     *
     * <p>
     * The {@code getNode} method supports a wide range of use cases in UI development, from simple property adjustments to complex behavior and layout configurations. By offering a direct link to the
     * underlying UI component, it facilitates precise control over the component's appearance and functionality, allowing developers to tailor UI elements to specific requirements.
     * </p>
     *
     * <p>
     * Usage of this method is typically reserved for internal operations within the configurator pattern, acting as a bridge between the abstract configuration logic and the concrete UI elements. However, it
     * can also be used in client code to gain access to the configured Node for additional customizations or for querying current configurations.
     * </p>
     *
     * <p>
     * It is important for implementers of this method to ensure that the correct {@link Node} instance is returned, as it directly impacts the effectiveness and accuracy of the configuration process.
     * Incorrectly associated Nodes could lead to misconfigurations or runtime errors.
     * </p>
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     */
    Node getNode();

    /**
     * Validates that the bean of a specified property is associated with the control targeted by this configurator. This method ensures the integrity of property configurations within the context of the
     * configured control by verifying that the property's bean (the object to which the property belongs) matches the Node obtained from {@link #getNode()}. This validation is crucial for maintaining coherent
     * and correct configurations, particularly in complex UIs where properties might be associated with multiple controls.
     *
     * <p>
     * The method performs a reference comparison between the property's bean and the control's Node. If the property's bean does not reference the same object as the Node of the configured control, an
     * {@link InvalidAssociationException} is thrown. This exception indicates a misconfiguration, where a property intended for one control is being erroneously associated with another.
     * </p>
     *
     * <p>
     * By calling this method before applying configurations to a property, developers are alerted to configuration errors early, preventing runtime issues and ensuring that property configurations (such as
     * bindings, listeners, or value settings) are correctly applied to the intended control.
     * </p>
     *
     * <p>
     * Usage of this method is recommended whenever properties are configured or manipulated to ensure that such actions are relevant to the correct UI component, enhancing the application's stability and user
     * interface coherence.
     * </p>
     *
     * @param <S>
     *         the generic type of the property
     * @param property
     *         the {@link Property} to be validated against the control's Node
     *
     * @throws InvalidAssociationException
     *         if the property's bean does not match the Node of the configured control, signaling an invalid property association
     */
    default <S> void checkPropertyBeanIsValid(Property<S> property) {
        if (property.getBean() != getNode()) {
            throw new InvalidAssociationException("The property does not belong to the configured control.");
        }
    }

    /**
     * Validates that a specified binding bean is correctly associated with the control managed by this configurator. This method is critical for ensuring that bindings are only applied to the intended control,
     * thereby maintaining the configuration's coherence and preventing misconfigurations. It compares the provided bean with the node obtained from {@link #getNode()}, which represents the control targeted by
     * this configurator.
     *
     * <p>
     * This validation process is essential for configurations involving bindings, as it safeguards against the application of bindings to unintended controls. Such a misalignment could lead to runtime errors
     * or incorrect application behavior. By enforcing this validation step, the method enhances the stability and reliability of the binding configuration process.
     * </p>
     *
     * <p>
     * If the bean does not match the node of the configured control, an {@link InvalidAssociationException} is thrown, signaling a discrepancy between the intended control and the actual control associated
     * with the binding. This exception acts as a fail-fast mechanism, alerting developers to configuration issues early in the application's lifecycle.
     * </p>
     *
     * @param bean
     *         the binding bean to be validated against the control's node
     *
     * @throws InvalidAssociationException
     *         if the bean does not match the node of the configured control, indicating that the binding is not associated with the correct control
     */
    default void checkEFXBindingBeanIsValid(Object bean) {
        if (!bean.equals(getNode())) {
            throw new InvalidAssociationException("The binding does not belong to the configured control.");
        }
    }

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to a {@link Property<Boolean>} after verifying the property's association with the control. This method ensures that changes to the boolean property are monitored only if
     * the property is part of the control in question, maintaining the integrity of the configuration. By using this method, clients can react to changes in the property's value through the provided
     * {@code changeListener}.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Boolean>} to which the {@code changeListener} is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addBooleanPropertyChangeListener(Property<Boolean> property, ChangeListener<? super Boolean> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to a {@link Property<Boolean>} after ensuring the property's correct association with the control. This method facilitates the monitoring of the property's
     * invalidation events, allowing clients to perform actions when the property becomes invalid, regardless of its new value. It is particularly useful for scenarios where the mere act of changing the
     * property (not necessarily its value) triggers a required action.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Boolean>} to which the {@code invalidationListener} is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the property is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addBooleanPropertyInvalidationListener(Property<Boolean> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to a {@link Property} of any type {@code S}, after verifying the property's correct association with the control managed by this configurator. This method allows for dynamic
     * response to changes in the property's value, facilitating adaptive and responsive application behavior based on property state.
     *
     * <p>
     * Before adding the {@code changeListener}, this method invokes {@link #checkPropertyBeanIsValid(Property)} to ensure the property is indeed part of the control's configuration context. This validation
     * step is crucial for maintaining the integrity of the configuration, preventing the accidental configuration of listeners on unrelated properties.
     * </p>
     *
     * @param <S>
     *         the type of the property's value
     * @param property
     *         the {@link Property} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified upon changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default <S> T addObjectPropertyChangeListener(Property<S> property, ChangeListener<? super S> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to a {@link Property} of any type {@code S}, ensuring beforehand that the property is properly associated with the control managed by this configurator. This method
     * is useful for cases where an action needs to be triggered by any invalidation of the property's state, not necessarily tied to a change in its value.
     *
     * <p>
     * The {@link #checkPropertyBeanIsValid(Property)} method is called to validate the property's association with the control, safeguarding against the configuration of listeners on properties that are not
     * part of the control's context. This validation ensures that listeners are only added to relevant properties, thus maintaining the configurator's configuration integrity.
     * </p>
     *
     * @param <S>
     *         the type of the property's value
     * @param property
     *         the {@link Property} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default <S> T addObjectPropertyInvalidationListener(Property<S> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to a {@link Property<Double>} after verifying the property's association with the control. This method ensures that changes to the double property are monitored only if the
     * property is part of the control in question, maintaining the integrity of the configuration. By using this method, clients can react to changes in the property's value through the provided
     * {@code changeListener}.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Double>} to which the {@code changeListener} is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addDoublePropertyChangeListener(Property<Double> property, ChangeListener<? super Double> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to a {@link Property<Double>} after ensuring the property's correct association with the control. This method facilitates the monitoring of the property's
     * invalidation events, allowing clients to perform actions when the property becomes invalid, regardless of its new value. It is particularly useful for scenarios where the mere act of changing the
     * property (not necessarily its value) triggers a required action.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Double>} to which the {@code invalidationListener} is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the property is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addDoublePropertyInvalidationListener(Property<Double> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to a {@link Property<Float>} after verifying the property's association with the control. This method ensures that changes to the float property are monitored only if the
     * property is part of the control in question, maintaining the integrity of the configuration. By using this method, clients can react to changes in the property's value through the provided
     * {@code changeListener}.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Float>} to which the {@code changeListener} is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addFloatPropertyChangeListener(Property<Float> property, ChangeListener<? super Float> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to a {@link Property<Float>} after ensuring the property's correct association with the control. This method facilitates the monitoring of the property's invalidation
     * events, allowing clients to perform actions when the property becomes invalid, regardless of its new value. It is particularly useful for scenarios where the mere act of changing the property (not
     * necessarily its value) triggers a required action.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Float>} to which the {@code invalidationListener} is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the property is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addFloatPropertyInvalidationListener(Property<Float> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to a {@link Property<Integer>} after verifying the property's association with the control. This method ensures that changes to the integer property are monitored only if
     * the property is part of the control in question, maintaining the integrity of the configuration. By using this method, clients can react to changes in the property's value through the provided
     * {@code changeListener}.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Integer>} to which the {@code changeListener} is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addIntegerPropertyChangeListener(Property<Integer> property, ChangeListener<? super Integer> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to a {@link Property<Integer>} after ensuring the property's correct association with the control. This method facilitates the monitoring of the property's
     * invalidation events, allowing clients to perform actions when the property becomes invalid, regardless of its new value. It is particularly useful for scenarios where the mere act of changing the
     * property (not necessarily its value) triggers a required action.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Integer>} to which the {@code invalidationListener} is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the property is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addIntegerPropertyInvalidationListener(Property<Integer> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to a {@link Property<Long>} after verifying the property's association with the control. This method ensures that changes to the long property are monitored only if the
     * property is part of the control in question, maintaining the integrity of the configuration. By using this method, clients can react to changes in the property's value through the provided
     * {@code changeListener}.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Long>} to which the {@code changeListener} is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addLongPropertyChangeListener(Property<Long> property, ChangeListener<? super Long> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to a {@link Property<Long>} after ensuring the property's correct association with the control. This method facilitates the monitoring of the property's invalidation
     * events, allowing clients to perform actions when the property becomes invalid, regardless of its new value. It is particularly useful for scenarios where the mere act of changing the property (not
     * necessarily its value) triggers a required action.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Long>} to which the {@code invalidationListener} is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the property is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addLongPropertyInvalidationListener(Property<Long> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to a {@link Property<Number>} after verifying the property's association with the control. This method ensures that changes to the number property are monitored only if the
     * property is part of the control in question, maintaining the integrity of the configuration. By using this method, clients can react to changes in the property's value through the provided
     * {@code changeListener}.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Number>} to which the {@code changeListener} is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addNumberPropertyChangeListener(Property<Number> property, ChangeListener<? super Number> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to a {@link Property<Number>} after ensuring the property's correct association with the control. This method facilitates the monitoring of the property's
     * invalidation events, allowing clients to perform actions when the property becomes invalid, regardless of its new value. It is particularly useful for scenarios where the mere act of changing the
     * property (not necessarily its value) triggers a required action.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<Number>} to which the {@code invalidationListener} is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the property is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addNumberPropertyInvalidationListener(Property<Number> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to a {@link Property<String>} after verifying the property's association with the control. This method ensures that changes to the string property are monitored only if the
     * property is part of the control in question, maintaining the integrity of the configuration. By using this method, clients can react to changes in the property's value through the provided
     * {@code changeListener}.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<String>} to which the {@code changeListener} is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addStringPropertyChangeListener(Property<String> property, ChangeListener<? super String> changeListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to a {@link Property<String>} after ensuring the property's correct association with the control. This method facilitates the monitoring of the property's
     * invalidation events, allowing clients to perform actions when the property becomes invalid, regardless of its new value. It is particularly useful for scenarios where the mere act of changing the
     * property (not necessarily its value) triggers a required action.
     *
     * <p>
     * Prior to adding the listener, {@link #checkPropertyBeanIsValid(Property)} is invoked to validate the property's association with the control's node. This validation step prevents the configuration of
     * listeners on unrelated properties, safeguarding against unintended side effects.
     * </p>
     *
     * @param property
     *         the {@link Property<String>} to which the {@code invalidationListener} is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the property is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not belong to the correct control, as determined by its node association
     */
    default T addStringPropertyInvalidationListener(Property<String> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to an {@link EFXBooleanBinding}, ensuring the binding is associated with the correct control.
     *
     * <p>
     * This method facilitates dynamic response to changes in the value of a boolean binding, allowing for actions to be triggered based on specific state changes. It first validates the binding's association
     * with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step ensures that the binding is correctly associated with the intended control,
     * maintaining the integrity and relevance of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is attached to the {@code efxBinding}. This listener will be notified whenever the value of the boolean binding changes, enabling
     * developers to define custom behaviors in response to these changes. The method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps
     * to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXBooleanBinding} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the boolean binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXBooleanBindingChangeListener(EFXBooleanBinding efxBinding, ChangeListener<? super Boolean> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to an {@link EFXBooleanBinding}, verifying the binding's association with the configured control.
     * <p>
     * This method enables the application to respond to invalidation events of a boolean binding, which occur whenever the binding's value becomes potentially invalid. Before attaching the
     * {@code invalidationListener}, it validates the binding's association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation ensures that the
     * listener is added only if the binding is correctly associated with the intended control, maintaining the configuration's integrity.
     *
     * <p>
     * Upon successful validation, the specified {@code invalidationListener} is attached to the {@code efxBinding}. This listener will be notified whenever the binding's value becomes potentially invalid. The
     * method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXBooleanBinding} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the binding is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXBooleanBindingInvalidationListener(EFXBooleanBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to an {@link EFXObjectBinding<S>}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method enables the application to respond dynamically to changes in the value of an object binding. Prior to attaching the {@code changeListener}, it conducts a validation check on the binding's
     * association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step is crucial for ensuring that the listener is only added to bindings
     * correctly associated with the intended control, thus maintaining the integrity of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is attached to the {@code efxBinding}. This listener will be notified of changes to the binding's value, facilitating timely and
     * appropriate reactions within the application to such changes. The method adheres to a fluent configuration pattern, allowing for the configurator instance to be returned and enabling additional
     * configuration steps to be chained in a concise and readable manner.
     * </p>
     *
     * @param <S>
     *         the type parameter of the object binding and change listener
     * @param efxBinding
     *         the {@link EFXObjectBinding<S>} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not correctly associated with the control, as determined by its node association
     */
    default <S> T addEFXObjectBindingChangeListener(EFXObjectBinding<S> efxBinding, ChangeListener<? super S> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to an {@link EFXObjectBinding<S>}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method facilitates the application's response to invalidation events of an object binding, which occur whenever the binding's value becomes potentially invalid. Before attaching the
     * {@code invalidationListener}, it validates the binding's association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step ensures that
     * the listener is added only if the binding is correctly associated with the intended control, thereby maintaining the integrity of the configuration.
     * </p>
     *
     * <p>
     * Once the validation is successful, the specified {@code invalidationListener} is attached to the {@code efxBinding}. This listener will be notified whenever the binding's value becomes potentially
     * invalid, enabling the application to react appropriately to such events. By returning the configurator instance, this method supports a fluent configuration pattern, allowing for additional configuration
     * steps to be seamlessly chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXObjectBinding<S>} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the binding becomes potentially invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by the validation process
     */
    default <S> T addEFXObjectBindingInvalidationListener(EFXObjectBinding<S> efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to an {@link EFXDoubleBinding}, ensuring the binding is associated with the correct control.
     *
     * <p>
     * This method facilitates dynamic response to changes in the value of a double binding, allowing for actions to be triggered based on specific state changes. It first validates the binding's association
     * with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step ensures that the binding is correctly associated with the intended control,
     * maintaining the integrity and relevance of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is attached to the {@code efxBinding}. This listener will be notified whenever the value of the double binding changes, enabling
     * developers to define custom behaviors in response to these changes. The method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps
     * to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXDoubleBinding} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the double binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXDoubleBindingChangeListener(EFXDoubleBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to an {@link EFXDoubleBinding}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method enables the application to respond to invalidation events of a double binding, which occur whenever the binding's value becomes potentially invalid. Before attaching the
     * {@code invalidationListener}, it validates the binding's association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation ensures that the
     * listener is added only if the binding is correctly associated with the intended control, maintaining the configuration's integrity.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code invalidationListener} is attached to the {@code efxBinding}. This listener will be notified whenever the binding's value becomes potentially invalid. The
     * method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXDoubleBinding} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the binding is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXDoubleBindingInvalidationListener(EFXDoubleBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to an {@link EFXFloatBinding}, ensuring the binding is associated with the correct control.
     *
     * <p>
     * This method facilitates dynamic response to changes in the value of a float binding, allowing for actions to be triggered based on specific state changes. It first validates the binding's association
     * with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step ensures that the binding is correctly associated with the intended control,
     * maintaining the integrity and relevance of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is attached to the {@code efxBinding}. This listener will be notified whenever the value of the float binding changes, enabling developers
     * to define custom behaviors in response to these changes. The method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be
     * chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXFloatBinding} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the float binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXFloatBindingChangeListener(EFXFloatBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to an {@link EFXFloatBinding}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method enables the application to respond to invalidation events of a float binding, which occur whenever the binding's value becomes potentially invalid. Before attaching the
     * {@code invalidationListener}, it validates the binding's association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation ensures that the
     * listener is added only if the binding is correctly associated with the intended control, maintaining the configuration's integrity.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code invalidationListener} is attached to the {@code efxBinding}. This listener will be notified whenever the binding's value becomes potentially invalid. The
     * method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXFloatBinding} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the binding is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXFloatBindingInvalidationListener(EFXFloatBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to an {@link EFXIntegerBinding}, ensuring the binding is associated with the correct control.
     *
     * <p>
     * This method facilitates dynamic response to changes in the value of a integer binding, allowing for actions to be triggered based on specific state changes. It first validates the binding's association
     * with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step ensures that the binding is correctly associated with the intended control,
     * maintaining the integrity and relevance of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is attached to the {@code efxBinding}. This listener will be notified whenever the value of the integer binding changes, enabling
     * developers to define custom behaviors in response to these changes. The method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps
     * to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXIntegerBinding} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the integer binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXIntegerBindingChangeListener(EFXIntegerBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to an {@link EFXIntegerBinding}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method enables the application to respond to invalidation events of a integer binding, which occur whenever the binding's value becomes potentially invalid. Before attaching the
     * {@code invalidationListener}, it validates the binding's association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation ensures that the
     * listener is added only if the binding is correctly associated with the intended control, maintaining the configuration's integrity.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code invalidationListener} is attached to the {@code efxBinding}. This listener will be notified whenever the binding's value becomes potentially invalid. The
     * method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXIntegerBinding} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the binding is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */

    default T addEFXIntegerBindingInvalidationListener(EFXIntegerBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to an {@link EFXLongBinding}, ensuring the binding is associated with the correct control.
     *
     * <p>
     * This method facilitates dynamic response to changes in the value of a long binding, allowing for actions to be triggered based on specific state changes. It first validates the binding's association with
     * the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step ensures that the binding is correctly associated with the intended control, maintaining
     * the integrity and relevance of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is attached to the {@code efxBinding}. This listener will be notified whenever the value of the long binding changes, enabling developers
     * to define custom behaviors in response to these changes. The method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be
     * chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXLongBinding} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the long binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXLongBindingChangeListener(EFXLongBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to an {@link EFXLongBinding}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method enables the application to respond to invalidation events of a long binding, which occur whenever the binding's value becomes potentially invalid. Before attaching the
     * {@code invalidationListener}, it validates the binding's association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation ensures that the
     * listener is added only if the binding is correctly associated with the intended control, maintaining the configuration's integrity.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code invalidationListener} is attached to the {@code efxBinding}. This listener will be notified whenever the binding's value becomes potentially invalid. The
     * method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXLongBinding} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the binding is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXLongBindingInvalidationListener(EFXLongBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to an {@link EFXNumberBinding}, ensuring the binding is associated with the correct control.
     *
     * <p>
     * This method facilitates dynamic response to changes in the value of a number binding, allowing for actions to be triggered based on specific state changes. It first validates the binding's association
     * with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step ensures that the binding is correctly associated with the intended control,
     * maintaining the integrity and relevance of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is attached to the {@code efxBinding}. This listener will be notified whenever the value of the number binding changes, enabling
     * developers to define custom behaviors in response to these changes. The method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps
     * to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXNumberBinding} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the number binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXNumberBindingChangeListener(EFXNumberBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to an {@link EFXNumberBinding}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method enables the application to respond to invalidation events of a number binding, which occur whenever the binding's value becomes potentially invalid. Before attaching the
     * {@code invalidationListener}, it validates the binding's association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation ensures that the
     * listener is added only if the binding is correctly associated with the intended control, maintaining the configuration's integrity.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code invalidationListener} is attached to the {@code efxBinding}. This listener will be notified whenever the binding's value becomes potentially invalid. The
     * method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXNumberBinding} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the binding is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXNumberBindingInvalidationListener(EFXNumberBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to an {@link EFXStringBinding}, ensuring the binding is associated with the correct control.
     *
     * <p>
     * This method facilitates dynamic response to changes in the value of a string binding, allowing for actions to be triggered based on specific state changes. It first validates the binding's association
     * with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation step ensures that the binding is correctly associated with the intended control,
     * maintaining the integrity and relevance of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is attached to the {@code efxBinding}. This listener will be notified whenever the value of the string binding changes, enabling
     * developers to define custom behaviors in response to these changes. The method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps
     * to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXStringBinding} to which the change listener is to be added
     * @param changeListener
     *         the {@link ChangeListener} that will be notified of changes to the string binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXStringBindingChangeListener(EFXStringBinding efxBinding, ChangeListener<? super String> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to an {@link EFXStringBinding}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method enables the application to respond to invalidation events of a string binding, which occur whenever the binding's value becomes potentially invalid. Before attaching the
     * {@code invalidationListener}, it validates the binding's association with the control targeted by this configurator using {@link #checkEFXBindingBeanIsValid(Object)}. This validation ensures that the
     * listener is added only if the binding is correctly associated with the intended control, maintaining the configuration's integrity.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code invalidationListener} is attached to the {@code efxBinding}. This listener will be notified whenever the binding's value becomes potentially invalid. The
     * method supports a fluent configuration pattern by returning the configurator instance, allowing for additional configuration steps to be chained in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXStringBinding} to which the invalidation listener is to be added
     * @param invalidationListener
     *         the {@link InvalidationListener} that will be notified when the binding is invalidated
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by its node association
     */
    default T addEFXStringBindingInvalidationListener(EFXStringBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a previously added {@link ChangeListener} from a {@link Property<Boolean>} after verifying the property's association with the control. This method allows for the dynamic adjustment of change
     * listeners associated with a boolean property, ensuring that listeners are managed appropriately according to the evolving needs of the application. By using this method, clients can remove specific
     * listeners from the property, ceasing their response to changes in the property's value.
     *
     * <p>
     * The removal process begins with a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is indeed associated with the control's node. This validation is crucial for ensuring
     * that the listener is removed from the intended property, preserving the configuration's integrity and preventing the accidental modification of unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Boolean>} from which the {@code changeListener} is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer receive notifications of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeBooleanPropertyChangeListener(Property<Boolean> property, ChangeListener<? super Boolean> changeListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from a {@link Property<Boolean>} after verifying the property's association with the control. This method allows for the dynamic management of
     * invalidation listeners associated with a boolean property, enabling the removal of specific listeners that are no longer required. It is particularly beneficial in scenarios where the need to react to
     * the property's invalidation events has changed or ceased, providing flexibility in how changes to the property state are handled.
     *
     * <p>
     * The removal process is preceded by a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is correctly associated with the control's node. This validation ensures that
     * listeners are accurately removed from the intended property, maintaining the configuration's integrity and preventing modifications to unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Boolean>} from which the {@code invalidationListener} is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeBooleanPropertyInvalidationListener(Property<Boolean> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from a {@link Property<S>} after confirming the property's association with the control. This method facilitates the dynamic management of change
     * listeners for properties of any type, ensuring that the application's listening behavior remains aligned with current requirements. By using this method, clients gain the ability to selectively remove
     * listeners, halting their reaction to property value changes and potentially optimizing application performance.
     *
     * <p>
     * The process to remove a listener initiates with an invocation of {@link #checkPropertyBeanIsValid(Property)} to ascertain that the property is correctly associated with the control's node. This
     * validation step is critical for maintaining the integrity of the configuration, ensuring that only listeners attached to the intended property are removed, thereby safeguarding against modifications to
     * properties of unrelated controls.
     * </p>
     *
     * @param <S>
     *         the type of the property's value
     * @param property
     *         the {@link Property<S>} from which the {@code changeListener} is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not have the correct association with the control, as verified through the property's node association
     */
    default <S> T removeObjectPropertyChangeListener(Property<S> property, ChangeListener<? super S> changeListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from a {@link Property<S>} after ensuring the property's correct association with the control. This method allows for the dynamic reconfiguration
     * of invalidation listeners for properties of any type, aligning the application's invalidation response mechanism with evolving requirements. Using this method enables clients to selectively discontinue
     * notifications about the property's invalidation events, optimizing application responsiveness and resource usage.
     *
     * <p>
     * The listener removal process is initiated by verifying the property's association with the control's node through {@link #checkPropertyBeanIsValid(Property)}. This crucial validation step ensures the
     * integrity of the configuration by guaranteeing that only listeners linked to the correct property are removed, thereby preventing unintended effects on unrelated controls or properties.
     * </p>
     *
     * @param <S>
     *         the generic type of the property's value
     * @param property
     *         the {@link Property<S>} from which the {@code invalidationListener} is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property does not have the appropriate association with the control, as confirmed by the property's node association
     */
    default <S> T removeObjectPropertyInvalidationListener(Property<S> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from a {@link Property<Double>} after verifying the property's association with the control. This method allows for the dynamic adjustment of change
     * listeners associated with a double property, ensuring that listeners are managed appropriately according to the evolving needs of the application. By using this method, clients can remove specific
     * listeners from the property, ceasing their response to changes in the property's value.
     *
     * <p>
     * The removal process begins with a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is indeed associated with the control's node. This validation is crucial for ensuring
     * that the listener is removed from the intended property, preserving the configuration's integrity and preventing the accidental modification of unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Double>} from which the {@code changeListener} is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer receive notifications of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeDoublePropertyChangeListener(Property<Double> property, ChangeListener<? super Number> changeListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from a {@link Property<Double>} after verifying the property's association with the control. This method allows for the dynamic management of
     * invalidation listeners associated with a double property, enabling the removal of specific listeners that are no longer required. It is particularly beneficial in scenarios where the need to react to the
     * property's invalidation events has changed or ceased, providing flexibility in how changes to the property state are handled.
     *
     * <p>
     * The removal process is preceded by a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is correctly associated with the control's node. This validation ensures that
     * listeners are accurately removed from the intended property, maintaining the configuration's integrity and preventing modifications to unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Double>} from which the {@code invalidationListener} is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeDoublePropertyInvalidationListener(Property<Double> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from a {@link Property<Float>} after verifying the property's association with the control. This method allows for the dynamic adjustment of change
     * listeners associated with a float property, ensuring that listeners are managed appropriately according to the evolving needs of the application. By using this method, clients can remove specific
     * listeners from the property, ceasing their response to changes in the property's value.
     *
     * <p>
     * The removal process begins with a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is indeed associated with the control's node. This validation is crucial for ensuring
     * that the listener is removed from the intended property, preserving the configuration's integrity and preventing the accidental modification of unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Float>} from which the {@code changeListener} is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer receive notifications of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeFloatPropertyChangeListener(Property<Float> property, ChangeListener<? super Number> changeListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from a {@link Property<Float>} after verifying the property's association with the control. This method allows for the dynamic management of
     * invalidation listeners associated with a float property, enabling the removal of specific listeners that are no longer required. It is particularly beneficial in scenarios where the need to react to the
     * property's invalidation events has changed or ceased, providing flexibility in how changes to the property state are handled.
     *
     * <p>
     * The removal process is preceded by a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is correctly associated with the control's node. This validation ensures that
     * listeners are accurately removed from the intended property, maintaining the configuration's integrity and preventing modifications to unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Float>} from which the {@code invalidationListener} is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeFloatPropertyInvalidationListener(Property<Float> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from a {@link Property<Integer>} after verifying the property's association with the control. This method allows for the dynamic adjustment of change
     * listeners associated with an integer property, ensuring that listeners are managed appropriately according to the evolving needs of the application. By using this method, clients can remove specific
     * listeners from the property, ceasing their response to changes in the property's value.
     *
     * <p>
     * The removal process begins with a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is indeed associated with the control's node. This validation is crucial for ensuring
     * that the listener is removed from the intended property, preserving the configuration's integrity and preventing the accidental modification of unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Integer>} from which the {@code changeListener} is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer receive notifications of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeIntegerPropertyChangeListener(Property<Integer> property, ChangeListener<? super Number> changeListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from a {@link Property<Integer>} after verifying the property's association with the control. This method allows for the dynamic management of
     * invalidation listeners associated with an integer property, enabling the removal of specific listeners that are no longer required. It is particularly beneficial in scenarios where the need to react to
     * the property's invalidation events has changed or ceased, providing flexibility in how changes to the property state are handled.
     *
     * <p>
     * The removal process is preceded by a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is correctly associated with the control's node. This validation ensures that
     * listeners are accurately removed from the intended property, maintaining the configuration's integrity and preventing modifications to unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Integer>} from which the {@code invalidationListener} is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeIntegerPropertyInvalidationListener(Property<Integer> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from a {@link Property<Long>} after verifying the property's association with the control. This method allows for the dynamic adjustment of change
     * listeners associated with a long property, ensuring that listeners are managed appropriately according to the evolving needs of the application. By using this method, clients can remove specific
     * listeners from the property, ceasing their response to changes in the property's value.
     *
     * <p>
     * The removal process begins with a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is indeed associated with the control's node. This validation is crucial for ensuring
     * that the listener is removed from the intended property, preserving the configuration's integrity and preventing the accidental modification of unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Long>} from which the {@code changeListener} is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer receive notifications of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeLongPropertyChangeListener(Property<Long> property, ChangeListener<? super Number> changeListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from a {@link Property<Long>} after verifying the property's association with the control. This method allows for the dynamic management of
     * invalidation listeners associated with a long property, enabling the removal of specific listeners that are no longer required. It is particularly beneficial in scenarios where the need to react to the
     * property's invalidation events has changed or ceased, providing flexibility in how changes to the property state are handled.
     *
     * <p>
     * The removal process is preceded by a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is correctly associated with the control's node. This validation ensures that
     * listeners are accurately removed from the intended property, maintaining the configuration's integrity and preventing modifications to unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Long>} from which the {@code invalidationListener} is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeLongPropertyInvalidationListener(Property<Long> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from a {@link Property<Number>} after verifying the property's association with the control. This method allows for the dynamic adjustment of change
     * listeners associated with a number property, ensuring that listeners are managed appropriately according to the evolving needs of the application. By using this method, clients can remove specific
     * listeners from the property, ceasing their response to changes in the property's value.
     *
     * <p>
     * The removal process begins with a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is indeed associated with the control's node. This validation is crucial for ensuring
     * that the listener is removed from the intended property, preserving the configuration's integrity and preventing the accidental modification of unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Number>} from which the {@code changeListener} is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer receive notifications of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeNumberPropertyChangeListener(Property<Number> property, ChangeListener<? super Number> changeListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from a {@link Property<Number>} after verifying the property's association with the control. This method allows for the dynamic management of
     * invalidation listeners associated with a number property, enabling the removal of specific listeners that are no longer required. It is particularly beneficial in scenarios where the need to react to the
     * property's invalidation events has changed or ceased, providing flexibility in how changes to the property state are handled.
     *
     * <p>
     * The removal process is preceded by a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is correctly associated with the control's node. This validation ensures that
     * listeners are accurately removed from the intended property, maintaining the configuration's integrity and preventing modifications to unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<Number>} from which the {@code invalidationListener} is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeNumberPropertyInvalidationListener(Property<Number> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from a {@link Property<String>} after verifying the property's association with the control. This method allows for the dynamic adjustment of change
     * listeners associated with a string property, ensuring that listeners are managed appropriately according to the evolving needs of the application. By using this method, clients can remove specific
     * listeners from the property, ceasing their response to changes in the property's value.
     *
     * <p>
     * The removal process begins with a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is indeed associated with the control's node. This validation is crucial for ensuring
     * that the listener is removed from the intended property, preserving the configuration's integrity and preventing the accidental modification of unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<String>} from which the {@code changeListener} is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer receive notifications of changes to the property's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeStringPropertyChangeListener(Property<String> property, ChangeListener<? super String> changeListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from a {@link Property<String>} after verifying the property's association with the control. This method allows for the dynamic management of
     * invalidation listeners associated with a string property, enabling the removal of specific listeners that are no longer required. It is particularly beneficial in scenarios where the need to react to the
     * property's invalidation events has changed or ceased, providing flexibility in how changes to the property state are handled.
     *
     * <p>
     * The removal process is preceded by a call to {@link #checkPropertyBeanIsValid(Property)} to confirm that the property is correctly associated with the control's node. This validation ensures that
     * listeners are accurately removed from the intended property, maintaining the configuration's integrity and preventing modifications to unrelated properties.
     * </p>
     *
     * @param property
     *         the {@link Property<String>} from which the {@code invalidationListener} is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the property becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not associated with the correct control, as validated by the property's node association
     */
    default T removeStringPropertyInvalidationListener(Property<String> property, InvalidationListener invalidationListener) {
        checkPropertyBeanIsValid(property);
        property.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from an {@link EFXBooleanBinding}, after verifying the binding's association with the correct control.
     *
     * <p>
     * This method enables the dynamic adjustment of change listeners for a boolean binding, allowing for the removal of listeners that are no longer necessary or relevant. The binding's association with the
     * control targeted by this configurator is validated using {@link #checkEFXBindingBeanIsValid(Object)}. This ensures that the removal of the listener is appropriately aligned with the intended control,
     * maintaining the integrity of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the specified {@code changeListener} is detached from the {@code efxBinding}. This operation allows developers to modify the behavior of their application in response to
     * changes in the binding's state, enhancing flexibility and responsiveness. By returning the configurator instance, this method supports a fluent configuration pattern, facilitating the chaining of further
     * configuration actions in an efficient and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXBooleanBinding} from which the change listener is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the boolean binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not associated with the correct control, as verified through validation
     */
    default T removeEFXBooleanBindingChangeListener(EFXBooleanBinding efxBinding, ChangeListener<? super Boolean> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from an {@link EFXBooleanBinding}, ensuring the binding's correct association with the configured control.
     *
     * <p>
     * This method facilitates the application's ability to dynamically modify its response to invalidation events of a boolean binding. It validates the binding's association with the targeted control via
     * {@link #checkEFXBindingBeanIsValid(Object)}, ensuring that the listener removal is conducted on the correct binding and thus maintaining the configuration's accuracy and integrity.
     * </p>
     *
     * <p>
     * With successful validation, the specified {@code invalidationListener} is removed from the {@code efxBinding}. This removal process allows for the optimization of event handling and resource management,
     * as listeners that are no longer needed can be efficiently discarded. The method endorses a fluent configuration approach by returning the configurator instance, promoting the seamless addition of
     * subsequent configuration steps.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXBooleanBinding} from which the invalidation listener is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the binding becomes potentially invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as confirmed through validation
     */
    default T removeEFXBooleanBindingInvalidationListener(EFXBooleanBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from an {@link EFXObjectBinding<S>}, verifying the binding's association with the correct control.
     * <p>
     * This method allows for the dynamic reconfiguration of change listeners associated with an object binding of type {@code S}, facilitating the removal of listeners that may no longer be required. It begins
     * with a validation step, employing {@link #checkEFXBindingBeanIsValid(Object)} to ensure the binding's correct association with the targeted control, thus maintaining the integrity of the overall
     * configuration.
     * </p>
     * <p>
     * Upon successful validation, the specified {@code changeListener} is detached from the {@code property}. This operation enables developers to fine-tune the application's behavior in response to the
     * binding's value changes, ensuring that only relevant change events trigger the desired actions. The method promotes a fluent configuration approach by returning the configurator instance, allowing for
     * the continuation of configuration tasks in a readable and efficient manner.
     * </p>
     *
     * @param <S>
     *         the type parameter of the object binding and change listener
     * @param property
     *         the {@link EFXObjectBinding<S>} from which the change listener is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not properly associated with the control, as determined by the validation process
     */
    default <S> T removeEFXObjectBindingChangeListener(EFXObjectBinding<S> property, ChangeListener<? super S> changeListener) {
        checkEFXBindingBeanIsValid(property);
        property.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from an {@link EFXObjectBinding<S>}, ensuring the binding's correct association with the configured control.
     *
     * <p>
     * This method enables the application to dynamically adjust its response to invalidation events for an object binding of type {@code S}. The removal process is preceded by a validation of the binding's
     * association with the control using {@link #checkEFXBindingBeanIsValid(Object)}, safeguarding the configuration's integrity by confirming that the listener is removed from the intended binding.
     * </p>
     *
     * <p>
     * With the validation confirmed, the specified {@code invalidationListener} is removed from the {@code efxBinding}. This allows for the optimization of the application's event-handling mechanisms,
     * discarding unnecessary listeners and potentially improving performance. By returning the configurator instance, the method supports a fluent configuration pattern, facilitating the addition of further
     * configuration actions in a streamlined and logical sequence.
     * </p>
     *
     * @param <S>
     *         the type parameter of the object binding
     * @param efxBinding
     *         the {@link EFXObjectBinding<S>} from which the invalidation listener is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified upon the binding's invalidation
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as verified by the validation step
     */
    default <S> T removeEFXObjectBindingInvalidationListener(EFXObjectBinding<S> efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from an {@link EFXDoubleBinding}, verifying the binding's correct association with the configured control.
     *
     * <p>
     * This method provides a means to dynamically adjust the change listener configuration for a double binding. It starts by validating the binding's association with the control using
     * {@link #checkEFXBindingBeanIsValid(Object)}. Successful validation ensures that the listener is correctly removed from the intended binding, maintaining the configuration's accuracy and integrity.
     * </p>
     *
     * <p>
     * After validation, the specified {@code changeListener} is detached from the {@code efxBinding}, allowing for the modification of the application's behavior in response to changes in the binding's state.
     * This enhances the application's adaptability and performance by ensuring that only relevant change events are processed. The method endorses a fluent configuration pattern by returning the configurator
     * instance, thereby facilitating subsequent configuration steps in a coherent manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXDoubleBinding} from which the change listener is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the double binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not properly associated with the control, as determined through validation
     */
    default T removeEFXDoubleBindingChangeListener(EFXDoubleBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from an {@link EFXDoubleBinding}, after ensuring the binding's correct association with the control.
     *
     * <p>
     * This method allows for the removal of invalidation listeners from a double binding, streamlining the application's response mechanism to changes in the binding's state. Validation of the binding's
     * association with the control is conducted via {@link #checkEFXBindingBeanIsValid(Object)}, ensuring that the listener removal is precise and maintains the configuration's integrity.
     * </p>
     *
     * <p>
     * Following validation, the specified {@code invalidationListener} is removed from the {@code efxBinding}. This operation optimizes the application's event handling, eliminating unnecessary notifications
     * and improving overall performance. By returning the configurator instance, the method supports fluent configuration, allowing for easy addition of further configuration actions.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXDoubleBinding} from which the invalidation listener is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the binding becomes potentially invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not associated with the correct control, as verified by the validation step
     */
    default T removeEFXDoubleBindingInvalidationListener(EFXDoubleBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from an {@link EFXFloatBinding}, verifying the binding's association with the configured control.
     *
     * <p>
     * This method allows for dynamic reconfiguration by facilitating the removal of change listeners from a float binding. The initial step involves validating the binding's association with the control using
     * {@link #checkEFXBindingBeanIsValid(Object)}. This ensures that the listener is precisely removed from the intended binding, maintaining the integrity of the configuration.
     * </p>
     *
     * <p>
     * Following successful validation, the specified {@code changeListener} is detached from the {@code efxBinding}, enabling the application to adjust its response to the binding's value changes efficiently.
     * This enhances application flexibility and performance by managing which change events are monitored. The configurator instance is returned, endorsing a fluent configuration approach for further
     * configuration actions.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXFloatBinding} from which the change listener is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the float binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not correctly associated with the control, as confirmed by the validation process
     */
    default T removeEFXFloatBindingChangeListener(EFXFloatBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from an {@link EFXFloatBinding}, ensuring the binding's correct association with the control.
     *
     * <p>
     * By facilitating the removal of invalidation listeners from a float binding, this method streamlines the application's event-handling strategy. The removal process is predicated on a validation of the
     * binding's association with the control via {@link #checkEFXBindingBeanIsValid(Object)}, safeguarding the configuration's accuracy.
     * </p>
     *
     * <p>
     * Once validated, the specified {@code invalidationListener} is removed from the {@code efxBinding}, optimizing the application's responsiveness to binding invalidations. This operation is crucial for
     * maintaining application performance and relevance of event responses. Returning the configurator instance supports fluent configuration, facilitating additional configuration steps.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXFloatBinding} from which the invalidation listener is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified upon the binding's invalidation
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as established by the validation process
     */
    default T removeEFXFloatBindingInvalidationListener(EFXFloatBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from an {@link EFXIntegerBinding}, after confirming the binding's association with the configured control.
     *
     * <p>
     * This method enables the targeted removal of change listeners from an integer binding, enhancing the application's control over which binding value changes trigger actions. It initiates with a validation
     * check using {@link #checkEFXBindingBeanIsValid(Object)} to ensure the binding's correct association, preserving the configuration's integrity.
     * </p>
     *
     * <p>
     * With validation confirmed, the {@code changeListener} is removed from the {@code efxBinding}, allowing for the fine-tuning of the application's response to binding changes. The configurator instance is
     * then returned, encouraging fluent configuration by enabling additional, easily chained configuration actions.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXIntegerBinding} from which the change listener is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the integer binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not associated with the correct control, as verified through validation
     */
    default T removeEFXIntegerBindingChangeListener(EFXIntegerBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from an {@link EFXIntegerBinding}, verifying the binding's correct association with the control.
     *
     * <p>
     * This method simplifies the process of dynamically adjusting the application's response to integer binding invalidations by allowing the precise removal of invalidation listeners. Validation of the
     * binding's association is carried out using {@link #checkEFXBindingBeanIsValid(Object)}, ensuring that the listener is removed from the correct binding.
     * </p>
     *
     * <p>
     * Following successful validation, the specified {@code invalidationListener} is removed, optimizing the application's handling of invalidation events. By returning the configurator instance, the method
     * supports a fluent configuration pattern, enabling the chaining of subsequent configuration steps.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXIntegerBinding} from which the invalidation listener is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the binding becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding does not belong to the correct control, as determined by the validation process
     */
    default T removeEFXIntegerBindingInvalidationListener(EFXIntegerBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from an {@link EFXLongBinding}, after confirming the binding's correct association with the control.
     * <p>
     * This method allows for dynamic adjustments to the monitoring of long binding value changes by facilitating the precise removal of change listeners. It ensures that listeners are managed appropriately,
     * enhancing application responsiveness and performance. The initial validation step, utilizing {@link #checkEFXBindingBeanIsValid(Object)}, guarantees the listener is removed from the correct binding,
     * maintaining the configuration's integrity.
     * </p>
     * <p>
     * After validation, the specified {@code changeListener} is detached from the {@code efxBinding}, allowing the application to fine-tune its behavior in response to changes in the binding's value. The
     * method supports fluent configuration by returning the configurator instance, enabling the chaining of further configuration actions in an efficient and coherent manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXLongBinding} from which the change listener is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the long binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not associated with the correct control, as verified through validation
     */
    default T removeEFXLongBindingChangeListener(EFXLongBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from an {@link EFXLongBinding}, ensuring the binding's proper association with the configured control.
     * <p>
     * By allowing for the removal of invalidation listeners, this method streamlines the application's response to long binding invalidations, optimizing event handling and potentially improving application
     * efficiency. Validation of the binding's association with the control is conducted using {@link #checkEFXBindingBeanIsValid(Object)}, ensuring accurate and intentional configuration adjustments.
     * </p>
     * <p>
     * Once validated, the specified {@code invalidationListener} is removed from the {@code efxBinding}, enabling adjustments to how the application responds to the binding's invalidation events. This method
     * endorses a fluent configuration approach by returning the configurator instance, facilitating additional configuration tasks in a streamlined process.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXLongBinding} from which the invalidation listener is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the binding becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not correctly associated with the control, as established by the validation process
     */
    default T removeEFXLongBindingInvalidationListener(EFXLongBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from an {@link EFXNumberBinding}, verifying the binding's association with the correct control.
     * <p>
     * This method supports dynamic configuration by enabling the targeted removal of change listeners from a number binding, enhancing the application's control and responsiveness to binding value changes. The
     * validation of the binding's association, using {@link #checkEFXBindingBeanIsValid(Object)}, ensures the removal process is precise and maintains the configuration's integrity.
     * </p>
     * <p>
     * Following successful validation, the {@code changeListener} is detached from the {@code efxBinding}, allowing for the modification of the application's response to changes in the binding's state. By
     * returning the configurator instance, the method facilitates fluent configuration, enabling the seamless addition of subsequent configuration steps.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXNumberBinding} from which the change listener is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the number binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not associated with the correct control, as confirmed by the validation process
     */
    default T removeEFXNumberBindingChangeListener(EFXNumberBinding efxBinding, ChangeListener<? super Number> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from an {@link EFXNumberBinding}, after ensuring the binding's correct association with the control.
     * <p>
     * This method allows the application to dynamically adjust its invalidation response strategy for a number binding by facilitating the removal of invalidation listeners. The initial validation step, via
     * {@link #checkEFXBindingBeanIsValid(Object)}, guarantees that the listener is accurately removed, preserving the configuration's integrity.
     * </p>
     * <p>
     * With the validation confirmed, the specified {@code invalidationListener} is removed from the {@code efxBinding}, optimizing the application's handling of invalidation events and improving performance.
     * The configurator instance is returned, endorsing a fluent configuration approach for additional configuration actions.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXNumberBinding} from which the invalidation listener is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified upon the binding's invalidation
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not correctly associated with the control, as verified through validation
     */
    default T removeEFXNumberBindingInvalidationListener(EFXNumberBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link ChangeListener} from an {@link EFXStringBinding}, verifying the binding's association with the configured control.
     * <p>
     * This method enables the application to dynamically reconfigure its response to string binding value changes by allowing for the targeted removal of change listeners. Validating the binding's association
     * with the control using {@link #checkEFXBindingBeanIsValid(Object)} ensures that the configuration adjustments are precise and maintain the integrity of the setup.
     * </p>
     * <p>
     * After successful validation, the {@code changeListener} is removed from the {@code efxBinding}, facilitating adjustments to the application's behavior in response to changes in the binding's state. This
     * enhances the adaptability and performance of the application. The method supports fluent configuration by returning the configurator instance, enabling further configuration actions to be efficiently
     * chained.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXStringBinding} from which the change listener is to be removed
     * @param changeListener
     *         the {@link ChangeListener} that will no longer be notified of changes to the string binding's value
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not associated with the correct control, as confirmed through validation
     */
    default T removeEFXStringBindingChangeListener(EFXStringBinding efxBinding, ChangeListener<? super String> changeListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a previously added {@link InvalidationListener} from an {@link EFXStringBinding}, ensuring the binding's proper association with the control.
     * <p>
     * By facilitating the removal of invalidation listeners from a string binding, this method streamlines the application's event-handling mechanism, allowing for the optimization of responses to invalidation
     * events. The validation of the binding's association with the control, carried out via {@link #checkEFXBindingBeanIsValid(Object)}, ensures the accuracy of the configuration adjustments.
     * </p>
     * <p>
     * Once validated, the specified {@code invalidationListener} is removed from the {@code efxBinding}, enhancing the application's performance and responsiveness by managing which invalidation events are
     * monitored. The configurator instance is returned, supporting fluent configuration and facilitating the addition of subsequent configuration actions.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXStringBinding} from which the invalidation listener is to be removed
     * @param invalidationListener
     *         the {@link InvalidationListener} that will no longer be notified when the binding becomes invalid
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the binding is not correctly associated with the control, as verified by the validation process
     */
    default T removeEFXStringBindingInvalidationListener(EFXStringBinding efxBinding, InvalidationListener invalidationListener) {
        checkEFXBindingBeanIsValid(efxBinding);
        efxBinding.removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //Double Property

    /**
     * Binds a {@link Property<Double>} to an {@link ObservableValue} of type {@code Double}, establishing a dependency relationship where the property's value will automatically update to match that of the
     * observable value.
     *
     * <p>
     * Before establishing the binding, this method validates that the property is correctly associated with the control targeted by this configurator using {@link #checkPropertyBeanIsValid(Property)}. This
     * validation ensures that the binding is applied to the intended property, maintaining the integrity of the configuration.
     * </p>
     *
     * <p>
     * Upon successful validation, the property is bound to the specified observable value, allowing for dynamic updates that reflect changes in the observable value's state. This method supports a fluent
     * configuration pattern by returning the configurator instance, enabling further configuration steps to be chained in an efficient manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Double>} to be bound
     * @param observableValue
     *         the {@link ObservableValue} to which the property is to be bound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindDoubleProperty(Property<Double> property, ObservableValue<? extends Double> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds a {@link Property<Double>} from its current binding, if any, effectively removing the dependency relationship and allowing the property's value to be manually set once again.
     *
     * <p>
     * This method first validates the property's association with the control using {@link #checkPropertyBeanIsValid(Property)} to ensure that the unbinding action is performed on the intended property,
     * safeguarding the configuration's integrity.
     * </p>
     *
     * <p>
     * After validation, the property is unbound, disconnecting it from any previously bound {@link ObservableValue}. This method enhances the configurator's flexibility by returning the configurator instance,
     * supporting fluent chaining of configuration actions.
     * </p>
     *
     * @param property
     *         the {@link Property<Double>} to be unbound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindDoubleProperty(Property<Double> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Double>} objects, ensuring that any change in the value of one property is automatically reflected in the other, and vice versa.
     *
     * <p>
     * Prior to creating the bidirectional binding, this method validates the association of the initiating property with the control using {@link #checkPropertyBeanIsValid(Property)}. This validation confirms
     * that the binding is correctly applied between properties associated with the intended controls, preserving the configuration's accuracy.
     * </p>
     *
     * <p>
     * Following validation, a bidirectional binding is established between the specified properties, enabling synchronized value updates. This method supports fluent configuration patterns by returning the
     * configurator instance, facilitating the addition of further configuration steps in a coherent sequence.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Double>} for the bidirectional binding
     * @param otherProperty
     *         the other {@link Property<Double>} to be bound bidirectionally with the initiating property
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindBidirectionalDoubleProperty(Property<Double> property, Property<Double> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a previously established bidirectional binding between two {@link Property<Double>} objects, allowing each property's value to be independently set thereafter.
     *
     * <p>
     * This method begins by validating the association of the initiating property with the control via {@link #checkPropertyBeanIsValid(Property)}, ensuring that the unbinding action targets the correct
     * properties and maintains the integrity of the configuration.
     * </p>
     *
     * <p>
     * Once validated, the bidirectional binding between the specified properties is removed, decoupling their values. By returning the configurator instance, this method supports a fluent configuration
     * pattern, enabling the seamless chaining of additional configuration actions.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Double>} from which the bidirectional binding is to be removed
     * @param otherProperty
     *         the other {@link Property<Double>} involved in the bidirectional binding
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindBidirectionalDoubleProperty(Property<Double> property, Property<Double> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //Float Property

    /**
     * Binds a {@link Property<Float>} to an {@link ObservableValue} of type {@code Float}, establishing a dynamic link where the property's value automatically updates to reflect the observable value's state.
     *
     * <p>
     * Prior to establishing the binding, the property's association with the control is validated using {@link #checkPropertyBeanIsValid(Property)}. This ensures that the binding is applied to the correct
     * property, preserving the configuration's integrity and relevance.
     * </p>
     *
     * <p>
     * Upon successful validation, the property is bound to the observable value, facilitating automatic updates that mirror changes in the observable value. This method promotes a fluent configuration approach
     * by returning the configurator instance, enabling further configuration actions to be efficiently chained in a coherent manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Float>} to be bound
     * @param observableValue
     *         the {@link ObservableValue} to which the property is to be bound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindFloatProperty(Property<Float> property, ObservableValue<? extends Float> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds a {@link Property<Float>} from its current binding, removing the dynamic link and allowing the property's value to be manually set.
     *
     * <p>
     * This method begins by validating the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the unbinding action targets the correct property and
     * maintains the configuration's accuracy.
     * </p>
     *
     * <p>
     * After ensuring the property is correctly associated, it is unbound, disconnecting it from any observable values it was previously bound to. The method supports fluent configuration patterns by returning
     * the configurator instance, enabling the continuation of configuration tasks.
     * </p>
     *
     * @param property
     *         the {@link Property<Float>} to be unbound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindFloatProperty(Property<Float> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Float>} objects, synchronizing their values so that any change in one is reflected in the other.
     *
     * <p>
     * The initiating property's association with the control is validated using {@link #checkPropertyBeanIsValid(Property)} before the bidirectional binding is created. This validation confirms the binding is
     * correctly applied, ensuring the integrity of the configuration between properties associated with the intended controls.
     * </p>
     *
     * <p>
     * Once validation is successful, a bidirectional binding is established, enabling synchronized value updates between the properties. This method enhances the configurator's usability by returning the
     * configurator instance, facilitating additional configuration steps in a seamless and readable sequence.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Float>} for the bidirectional binding
     * @param otherProperty
     *         the other {@link Property<Float>} to be bound bidirectionally
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindBidirectionalFloatProperty(Property<Float> property, Property<Float> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Float>} objects, allowing their values to be independently set once again.
     *
     * <p>
     * This method validates the initiating property's association with the control via {@link #checkPropertyBeanIsValid(Property)}, ensuring that the unbinding action is accurately targeted and maintains the
     * configuration's integrity.
     * </p>
     *
     * <p>
     * After validation, the bidirectional binding is removed, decoupling the properties' values. By returning the configurator instance, this method supports a fluent configuration pattern, enabling the
     * chaining of further configuration actions in an efficient and logical manner.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Float>} from which the bidirectional binding is to be removed
     * @param otherProperty
     *         the other {@link Property<Float>} involved in the bidirectional binding
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindBidirectionalFloatProperty(Property<Float> property, Property<Float> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //Integer Property

    /**
     * Binds an {@link Property<Integer>} to an {@link ObservableValue}, creating a dynamic link where the property's value is automatically updated to reflect the observable value's state.
     *
     * <p>
     * This method performs a validation check using {@link #checkPropertyBeanIsValid(Property)} to ensure the property's association with the targeted control, maintaining the configuration's integrity. After
     * successful validation, the property is bound to the observable value, enabling automatic synchronization of its value with changes in the observable value.
     * </p>
     *
     * <p>
     * By supporting a fluent configuration pattern, this method returns the configurator instance, allowing for the chaining of further configuration actions in a coherent and efficient manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Integer>} to be bound
     * @param observableValue
     *         the {@link ObservableValue} to which the property is to be bound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindIntegerProperty(Property<Integer> property, ObservableValue<? extends Integer> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds an {@link Property<Integer>} from its current binding, removing the dynamic link and allowing manual setting of the property's value.
     *
     * <p>
     * The method begins with a validation of the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring the unbinding action targets the intended property and
     * maintains the configuration's accuracy. After validation, the property is unbound, disconnecting it from any observable values it was previously bound to.
     * </p>
     *
     * <p>
     * This method supports fluent configuration patterns by returning the configurator instance, enabling the continuation of configuration tasks in a seamless manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Integer>} to be unbound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindIntegerProperty(Property<Integer> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Integer>} objects, synchronizing their values to reflect changes in either.
     *
     * <p>
     * Before creating the bidirectional binding, the method validates the initiating property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring the binding is correctly
     * applied and maintains the integrity of the configuration.
     * </p>
     *
     * <p>
     * A bidirectional binding is then established, enabling synchronized value updates between the properties. This enhances the configurator's flexibility by returning the configurator instance, facilitating
     * the chaining of further configuration steps in a logical and readable sequence.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Integer>} for the bidirectional binding
     * @param otherProperty
     *         the other {@link Property<Integer>} to be bound bidirectionally
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindBidirectionalIntegerProperty(Property<Integer> property, Property<Integer> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Integer>} objects, allowing for independent setting of each property's value.
     *
     * <p>
     * The initiating property's association with the control is validated via {@link #checkPropertyBeanIsValid(Property)}, ensuring the unbinding action is precise and maintains the configuration's integrity.
     * Following validation, the bidirectional binding is removed, decoupling the properties' values.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration pattern, enabling the efficient chaining of additional configuration actions.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Integer>} from which the bidirectional binding is to be removed
     * @param otherProperty
     *         the other {@link Property<Integer>} involved in the bidirectional binding
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindBidirectionalIntegerProperty(Property<Integer> property, Property<Integer> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //Long Property

    /**
     * Binds a {@link Property<Long>} to an {@link ObservableValue}, creating a dynamic connection where the property's value is automatically updated to match the observable value's state.
     *
     * <p>
     * This operation begins with a validation check using {@link #checkPropertyBeanIsValid(Property)} to ensure the property's correct association with the control, maintaining the integrity of the
     * configuration. Once validated, the property is bound to the observable value, enabling the property to automatically reflect changes in the observable value.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, allowing for subsequent configuration steps to be efficiently chained in a coherent manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Long>} to be bound
     * @param observableValue
     *         the {@link ObservableValue} to which the property is to be bound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindLongProperty(Property<Long> property, ObservableValue<? extends Long> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds a {@link Property<Long>} from its current binding, removing the dynamic connection and allowing the property's value to be manually set.
     *
     * <p>
     * The method validates the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring the unbinding action targets the correct property and maintains the
     * configuration's accuracy. Once validated, the property is unbound, freeing it from any previous observable value connections.
     * </p>
     *
     * <p>
     * This method supports fluent configuration patterns by returning the configurator instance, enabling the continuation of configuration tasks in a seamless manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Long>} to be unbound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindLongProperty(Property<Long> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Long>} objects, ensuring that any change in one property's value is automatically reflected in the other, and vice versa.
     *
     * <p>
     * Before the bidirectional binding is created, the initiating property's association with the control is validated using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the binding is correctly
     * applied, and the configuration's integrity is preserved.
     * </p>
     *
     * <p>
     * Following successful validation, a bidirectional binding is established, facilitating synchronized value updates between the properties. This enhances the configurator's flexibility by returning the
     * configurator instance, facilitating the chaining of additional configuration steps in a logical and readable sequence.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Long>} for the bidirectional binding
     * @param otherProperty
     *         the other {@link Property<Long>} to be bound bidirectionally
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindBidirectionalLongProperty(Property<Long> property, Property<Long> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Long>} objects, allowing for independent management of each property's value.
     *
     * <p>
     * This method includes a validation step via {@link #checkPropertyBeanIsValid(Property)}, confirming that the initiating property's association with the control is accurate, thus maintaining the integrity
     * of the configuration. After validation, the bidirectional binding is removed, decoupling the properties' value changes.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, enabling efficient chaining of further configuration actions.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Long>} from which the bidirectional binding is to be removed
     * @param otherProperty
     *         the other {@link Property<Long>} involved in the bidirectional binding
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindBidirectionalLongProperty(Property<Long> property, Property<Long> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //Number Property

    /**
     * Binds a {@link Property<Number>} to an {@link ObservableValue}, establishing a dynamic connection where the property's value automatically updates to reflect the observable value's state.
     *
     * <p>
     * Before establishing the binding, the property's association with the control is validated using {@link #checkPropertyBeanIsValid(Property)}. This step ensures that the binding is applied to the correct
     * property, preserving the integrity and relevance of the configuration.
     * </p>
     *
     * <p>
     * Once validation is successful, the property is bound to the observable value, facilitating automatic synchronization with changes in the observable value. This method promotes a fluent configuration
     * approach by returning the configurator instance, allowing for subsequent configuration actions to be efficiently chained in a coherent manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Number>} to be bound
     * @param observableValue
     *         the {@link ObservableValue} to which the property is to be bound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindNumberProperty(Property<Number> property, ObservableValue<? extends Number> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds a {@link Property<Number>} from its current binding, removing the dynamic link and allowing the property's value to be manually set.
     *
     * <p>
     * This method begins with a validation of the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the unbinding action targets the correct property and
     * maintains the configuration's accuracy. Once validated, the property is unbound, freeing it from any previous connections to observable values.
     * </p>
     *
     * <p>
     * Supporting fluent configuration patterns, this method returns the configurator instance, enabling the continuation of configuration tasks in a seamless manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Number>} to be unbound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindNumberProperty(Property<Number> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Number>} objects, synchronizing their values so that any change in one is automatically reflected in the other, and vice versa.
     *
     * <p>
     * The method validates the initiating property's association with the control using {@link #checkPropertyBeanIsValid(Property)} before creating the bidirectional binding. This ensures the binding is
     * correctly applied, preserving the integrity of the configuration between properties associated with the intended controls.
     * </p>
     *
     * <p>
     * Following validation, a bidirectional binding is established, enabling synchronized updates between the properties. Enhancing the configurator's flexibility, this method returns the configurator
     * instance, facilitating the chaining of further configuration steps in a logical and readable sequence.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Number>} for the bidirectional binding
     * @param otherProperty
     *         the other {@link Property<Number>} to be bound bidirectionally
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindBidirectionalNumberProperty(Property<Number> property, Property<Number> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Number>} objects, allowing for independent management of each property's value.
     *
     * <p>
     * This method includes a validation step via {@link #checkPropertyBeanIsValid(Property)}, confirming that the initiating property's association with the control is accurate, thus maintaining the integrity
     * of the configuration. Following validation, the bidirectional binding is removed, decoupling the properties' value changes.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, enabling efficient chaining of further configuration actions.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Number>} from which the bidirectional binding is to be removed
     * @param otherProperty
     *         the other {@link Property<Number>} involved in the bidirectional binding
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindBidirectionalNumberProperty(Property<Number> property, Property<Number> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //Boolean Property

    /**
     * Binds a {@link Property<Boolean>} to an {@link ObservableValue}, establishing a dynamic relationship where the property's value automatically updates to reflect the observable value's current state.
     *
     * <p>
     * Prior to the binding, this method validates the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}. This step ensures that the binding is applied to the intended
     * property, maintaining the configuration's integrity. Upon successful validation, the property is bound to the observable value, enabling it to automatically mirror changes in the observable value.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, allowing for subsequent configuration steps to be efficiently chained in a coherent manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Boolean>} to be bound
     * @param observableValue
     *         the {@link ObservableValue} to which the property is to be bound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindBooleanProperty(Property<Boolean> property, ObservableValue<? extends Boolean> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds a {@link Property<Boolean>} from its current binding, removing the dynamic link and permitting manual adjustments to the property's value.
     *
     * <p>
     * The method initiates with a validation of the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the unbinding action targets the appropriate
     * property and upholds the configuration's accuracy. Once validated, the property is unbound, disconnecting it from any previously bound observable values.
     * </p>
     *
     * <p>
     * Supporting fluent configuration patterns, this method returns the configurator instance, enabling the continuation of configuration tasks in a seamless manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Boolean>} to be unbound
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindBooleanProperty(Property<Boolean> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<Boolean>} objects, synchronizing their values so that any change in one is automatically reflected in the other, and vice versa.
     *
     * <p>
     * Before creating the bidirectional binding, the initiating property's association with the control is validated using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the binding is correctly
     * applied and the configuration's integrity is preserved.
     * </p>
     *
     * <p>
     * A bidirectional binding is then established, enabling synchronized value updates between the properties. This method enhances the configurator's usability by returning the configurator instance,
     * facilitating the chaining of additional configuration steps in a logical and readable sequence.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Boolean>} for the bidirectional binding
     * @param otherProperty
     *         the other {@link Property<Boolean>} to be bound bidirectionally
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindBidirectionalBooleanProperty(Property<Boolean> property, Property<Boolean> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<Boolean>} objects, allowing for independent management of each property's value.
     *
     * <p>
     * This method incorporates a validation step via {@link #checkPropertyBeanIsValid(Property)}, confirming the initiating property's correct association with the control, thus maintaining the configuration's
     * integrity. Following validation, the bidirectional binding is removed, decoupling the properties' value changes.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, enabling efficient chaining of further configuration actions.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Boolean>} from which the bidirectional binding is to be removed
     * @param otherProperty
     *         the other {@link Property<Boolean>} involved in the bidirectional binding
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindBidirectionalBooleanProperty(Property<Boolean> property, Property<Boolean> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //String Property

    /**
     * Binds a {@link Property<String>} to an {@link ObservableValue}, establishing a dynamic relationship where the property's value automatically updates to reflect the observable value's current state.
     *
     * <p>
     * Prior to the binding, this method validates the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}. This step ensures that the binding is applied to the intended
     * property, maintaining the configuration's integrity. Upon successful validation, the property is bound to the observable value, enabling it to automatically mirror changes in the observable value.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, allowing for subsequent configuration steps to be efficiently chained in a coherent manner.
     * </p>
     *
     * @param property
     *         the {@link Property<String>} to be bound
     * @param observableValue
     *         the {@link ObservableValue} to which the property is to be bound
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindStringProperty(Property<String> property, ObservableValue<? extends String> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds a {@link Property<String>} from its current binding, removing the dynamic link and permitting manual adjustments to the property's value.
     *
     * <p>
     * The method initiates with a validation of the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the unbinding action targets the appropriate
     * property and upholds the configuration's accuracy. Once validated, the property is unbound, disconnecting it from any previously bound observable values.
     * </p>
     *
     * <p>
     * Supporting fluent configuration patterns, this method returns the configurator instance, enabling the continuation of configuration tasks in a seamless manner.
     * </p>
     *
     * @param property
     *         the {@link Property<String>} to be unbound
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindStringProperty(Property<String> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<String>} objects, synchronizing their values so that any change in one is automatically reflected in the other, and vice versa.
     *
     * <p>
     * Before creating the bidirectional binding, the initiating property's association with the control is validated using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the binding is correctly
     * applied and the configuration's integrity is preserved.
     * </p>
     *
     * <p>
     * A bidirectional binding is then established, enabling synchronized value updates between the properties. This method enhances the configurator's usability by returning the configurator instance,
     * facilitating the chaining of additional configuration steps in a logical and readable sequence.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<Boolean>} for the bidirectional binding
     * @param otherProperty
     *         the other {@link Property<String>} to be bound bidirectionally
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T bindBidirectionalStringProperty(Property<String> property, Property<String> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<String>} objects, allowing for independent management of each property's value.
     *
     * <p>
     * This method incorporates a validation step via {@link #checkPropertyBeanIsValid(Property)}, confirming the initiating property's correct association with the control, thus maintaining the configuration's
     * integrity. Following validation, the bidirectional binding is removed, decoupling the properties' value changes.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, enabling efficient chaining of further configuration actions.
     * </p>
     *
     * @param property
     *         the initiating {@link Property<String>} from which the bidirectional binding is to be removed
     * @param otherProperty
     *         the other {@link Property<String>} involved in the bidirectional binding
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T unbindBidirectionalStringProperty(Property<String> property, Property<String> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //Object Property

    /**
     * Binds a {@link Property<S>} to an {@link ObservableValue}, creating a dynamic link where the property's value automatically updates to reflect the observable value's current state.
     *
     * <p>
     * This method begins by validating the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring the binding is correctly applied to the intended property and
     * maintaining the configuration's integrity. After successful validation, the property is bound to the observable value, enabling it to automatically adjust based on changes in the observable value.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, allowing for subsequent configuration steps to be efficiently chained in a coherent manner.
     * </p>
     *
     * @param <S>
     *         the type of the property and observable value
     * @param property
     *         the {@link Property<S>} to be bound
     * @param observableValue
     *         the {@link ObservableValue} to which the property is to be bound
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default <S> T bindObjectProperty(Property<S> property, ObservableValue<? extends S> observableValue) {
        checkPropertyBeanIsValid(property);
        property.bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds a {@link Property<S>} from its current binding, removing the dynamic link and permitting manual adjustments to the property's value.
     *
     * <p>
     * The method initiates with a validation of the property's association with the control using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the unbinding action targets the appropriate
     * property and upholds the configuration's accuracy. Once validated, the property is unbound, disconnecting it from any previously bound observable values.
     * </p>
     *
     * <p>
     * Supporting fluent configuration patterns, this method returns the configurator instance, enabling the continuation of configuration tasks in a seamless manner.
     * </p>
     *
     * @param <S>
     *         the type of the property
     * @param property
     *         the {@link Property<S>} to be unbound
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default <S> T unbindObjectProperty(Property<S> property) {
        checkPropertyBeanIsValid(property);
        property.unbind();
        return getConfigurator();
    }

    /**
     * Establishes a bidirectional binding between two {@link Property<S>} objects, synchronizing their values so that any change in one is automatically reflected in the other, and vice versa.
     *
     * <p>
     * Before creating the bidirectional binding, the initiating property's association with the control is validated using {@link #checkPropertyBeanIsValid(Property)}, ensuring that the binding is correctly
     * applied and the configuration's integrity is preserved.
     * </p>
     *
     * <p>
     * A bidirectional binding is then established, enabling synchronized value updates between the properties. This method enhances the configurator's usability by returning the configurator instance,
     * facilitating the chaining of additional configuration steps in a logical and readable sequence.
     * </p>
     *
     * @param <S>
     *         the type of the properties
     * @param property
     *         the initiating {@link Property<S>} for the bidirectional binding
     * @param otherProperty
     *         the other {@link Property<S>} to be bound bidirectionally
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default <S> T bindBidirectionalObjectProperty(Property<S> property, Property<S> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between two {@link Property<S>} objects, allowing for independent management of each property's value.
     *
     * <p>
     * This method incorporates a validation step via {@link #checkPropertyBeanIsValid(Property)}, confirming the initiating property's correct association with the control, thus maintaining the configuration's
     * integrity. Following validation, the bidirectional binding is removed, decoupling the properties' value changes.
     * </p>
     *
     * <p>
     * By returning the configurator instance, this method supports a fluent configuration approach, enabling efficient chaining of further configuration actions.
     * </p>
     *
     * @param <S>
     *         the type of the properties
     * @param property
     *         the initiating {@link Property<S>} from which the bidirectional binding is to be removed
     * @param otherProperty
     *         the other {@link Property<S>} involved in the bidirectional binding
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default <S> T unbindBidirectionalObjectProperty(Property<S> property, Property<S> otherProperty) {
        checkPropertyBeanIsValid(property);
        property.unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Initialize EFXBindings Functions
    //*****************************************************************
    // Initialize EFXBindings Functions
    //*****************************************************************

    /**
     * Initializes an {@link EFXDoubleBinding} with a specified {@link DoubleBinding}, bean, and an initialization consumer.
     *
     * <p>
     * This method facilitates the creation and customization of an {@code EFXDoubleBinding} instance. It validates the non-nullity of both the observable value and the initializer before proceeding with the
     * binding's initialization. This ensures that essential components for the binding's setup are present and correctly specified.
     * </p>
     *
     * <p>
     * The bean associated with the {@code EFXDoubleBinding} is validated to ensure it has the correct association within the configurator's context. This step is crucial for maintaining the integrity and
     * applicability of the binding within the application's UI.
     * </p>
     *
     * <p>
     * Upon successful validation, the provided {@code efxInitializer} consumer is invoked with a newly created {@code EFXDoubleBinding} instance, allowing for further configuration and customization of the
     * binding. This mechanism offers flexibility in defining the behavior and properties of the binding according to specific application needs.
     *
     * <br>
     *
     * <em>Usage Example:</em>
     * <pre>{@code
     * private EFXDoubleBinding efxDoubleBinding;
     * CustomControlConfigurator.create(customControl)
     *                          .initializeEFXDoubleBinding(doubleBinding, customControl, createdEFXBinding -> efxDoubleBinding = createdEFXBinding);
     * }</pre>
     * </p>
     *
     * <p>
     * The method supports fluent configuration patterns by returning the configurator instance, enabling the chaining of additional configuration operations. This approach enhances readability and efficiency
     * in the setup process of UI components and their associated behaviors.
     * </p>
     *
     * @param observableValue
     *         the {@link DoubleBinding} to be associated with the new {@code EFXDoubleBinding}
     * @param bean
     *         the bean object associated with the {@code EFXDoubleBinding}, used for validation to ensure correct context and association
     * @param efxInitializer
     *         a {@code Consumer<EFXDoubleBinding>} for custom initialization and setup of the {@code EFXDoubleBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws NullPointerException
     *         if either {@code observableValue} or {@code efxInitializer} is null, indicating a critical omission in the binding's setup requirements
     * @throws InvalidAssociationException
     *         if the bean does not have the correct association within the configurator's context, safeguarding the relevance and applicability of the binding
     */
    default T initializeEFXDoubleBinding(DoubleBinding observableValue, Object bean, Consumer<EFXDoubleBinding> efxInitializer) {
        EFXObjectUtils.isNotNull(observableValue, () -> "The observableValue binding cannot be null in initializeEFXDoubleBinding");
        EFXObjectUtils.isNotNull(efxInitializer, () -> "The efxInitializer cannot be null in initializeEFXDoubleBinding");
        checkEFXBindingBeanIsValid(bean);
        efxInitializer.accept(EFXDoubleBinding.create(bean, observableValue));
        return getConfigurator();
    }

    /**
     * Initializes an {@link EFXFloatBinding} with a specified {@link FloatBinding}, bean, and an initialization consumer.
     *
     * <p>
     * This method facilitates the creation and customization of an {@code EFXFloatBinding} instance. It validates the non-nullity of both the observable value and the initializer before proceeding with the
     * binding's initialization. This ensures that essential components for the binding's setup are present and correctly specified.
     * </p>
     *
     * <p>
     * The bean associated with the {@code EFXFloatBinding} is validated to ensure it has the correct association within the configurator's context. This step is crucial for maintaining the integrity and
     * applicability of the binding within the application's UI.
     * </p>
     *
     * <p>
     * Upon successful validation, the provided {@code efxInitializer} consumer is invoked with a newly created {@code EFXFloatBinding} instance, allowing for further configuration and customization of the
     * binding. This mechanism offers flexibility in defining the behavior and properties of the binding according to specific application needs.
     *
     * <br>
     *
     * <em>Usage Example:</em>
     * <pre>{@code
     * private EFXFloatBinding efxFloatBinding;
     * CustomControlConfigurator.create(customControl)
     *                          .initializeEFXFloatBinding(doubleBinding, customControl, createdEFXBinding -> efxFloatBinding = createdEFXBinding);
     * }</pre>
     * </p>
     *
     * <p>
     * The method supports fluent configuration patterns by returning the configurator instance, enabling the chaining of additional configuration operations. This approach enhances readability and efficiency
     * in the setup process of UI components and their associated behaviors.
     * </p>
     *
     * @param observableValue
     *         the {@link FloatBinding} to be associated with the new {@code EFXFloatBinding}
     * @param bean
     *         the bean object associated with the {@code EFXFloatBinding}, used for validation to ensure correct context and association
     * @param efxInitializer
     *         a {@code Consumer<EFXFloatBinding>} for custom initialization and setup of the {@code EFXFloatBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws NullPointerException
     *         if either {@code observableValue} or {@code efxInitializer} is null, indicating a critical omission in the binding's setup requirements
     * @throws InvalidAssociationException
     *         if the bean does not have the correct association within the configurator's context, safeguarding the relevance and applicability of the binding
     */
    default T initializeEFXFloatBinding(FloatBinding observableValue, Object bean, Consumer<EFXFloatBinding> efxInitializer) {
        EFXObjectUtils.isNotNull(observableValue, () -> "The observableValue binding cannot be null in initializeEFXFloatBinding");
        EFXObjectUtils.isNotNull(efxInitializer, () -> "The efxInitializer cannot be null in initializeEFXFloatBinding");
        checkEFXBindingBeanIsValid(bean);
        efxInitializer.accept(EFXFloatBinding.create(bean, observableValue));
        return getConfigurator();
    }

    /**
     * Initializes an {@link EFXIntegerBinding} with a specified {@link IntegerBinding}, bean, and an initialization consumer.
     *
     * <p>
     * This method facilitates the creation and customization of an {@code EFXIntegerBinding} instance. It validates the non-nullity of both the observable value and the initializer before proceeding with the
     * binding's initialization. This ensures that essential components for the binding's setup are present and correctly specified.
     * </p>
     *
     * <p>
     * The bean associated with the {@code EFXIntegerBinding} is validated to ensure it has the correct association within the configurator's context. This step is crucial for maintaining the integrity and
     * applicability of the binding within the application's UI.
     * </p>
     *
     * <p>
     * Upon successful validation, the provided {@code efxInitializer} consumer is invoked with a newly created {@code EFXIntegerBinding} instance, allowing for further configuration and customization of the
     * binding. This mechanism offers flexibility in defining the behavior and properties of the binding according to specific application needs.
     *
     * <br>
     *
     * <em>Usage Example:</em>
     * <pre>{@code
     * private EFXIntegerBinding efxIntegerBinding;
     * CustomControlConfigurator.create(customControl)
     *                          .initializeEFXIntegerBinding(doubleBinding, customControl, createdEFXBinding -> efxIntegerBinding = createdEFXBinding);
     * }</pre>
     * </p>
     *
     * <p>
     * The method supports fluent configuration patterns by returning the configurator instance, enabling the chaining of additional configuration operations. This approach enhances readability and efficiency
     * in the setup process of UI components and their associated behaviors.
     * </p>
     *
     * @param observableValue
     *         the {@link IntegerBinding} to be associated with the new {@code EFXIntegerBinding}
     * @param bean
     *         the bean object associated with the {@code EFXIntegerBinding}, used for validation to ensure correct context and association
     * @param efxInitializer
     *         a {@code Consumer<EFXIntegerBinding>} for custom initialization and setup of the {@code EFXIntegerBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws NullPointerException
     *         if either {@code observableValue} or {@code efxInitializer} is null, indicating a critical omission in the binding's setup requirements
     * @throws InvalidAssociationException
     *         if the bean does not have the correct association within the configurator's context, safeguarding the relevance and applicability of the binding
     */
    default T initializeEFXIntegerBinding(IntegerBinding observableValue, Object bean, Consumer<EFXIntegerBinding> efxInitializer) {
        EFXObjectUtils.isNotNull(observableValue, () -> "The observableValue binding cannot be null in initializeEFXIntegerBinding");
        EFXObjectUtils.isNotNull(efxInitializer, () -> "The efxInitializer cannot be null in initializeEFXIntegerBinding");
        checkEFXBindingBeanIsValid(bean);
        efxInitializer.accept(EFXIntegerBinding.create(bean, observableValue));
        return getConfigurator();
    }

    /**
     * Initializes an {@link EFXLongBinding} with a specified {@link LongBinding}, bean, and an initialization consumer.
     *
     * <p>
     * This method facilitates the creation and customization of an {@code EFXLongBinding} instance. It validates the non-nullity of both the observable value and the initializer before proceeding with the
     * binding's initialization. This ensures that essential components for the binding's setup are present and correctly specified.
     * </p>
     *
     * <p>
     * The bean associated with the {@code EFXLongBinding} is validated to ensure it has the correct association within the configurator's context. This step is crucial for maintaining the integrity and
     * applicability of the binding within the application's UI.
     * </p>
     *
     * <p>
     * Upon successful validation, the provided {@code efxInitializer} consumer is invoked with a newly created {@code EFXLongBinding} instance, allowing for further configuration and customization of the
     * binding. This mechanism offers flexibility in defining the behavior and properties of the binding according to specific application needs.
     *
     * <br>
     *
     * <em>Usage Example:</em>
     * <pre>{@code
     * private EFXLongBinding efxLongBinding;
     * CustomControlConfigurator.create(customControl)
     *                          .initializeEFXLongBinding(doubleBinding, customControl, createdEFXBinding -> efxLongBinding = createdEFXBinding);
     * }</pre>
     * </p>
     *
     * <p>
     * The method supports fluent configuration patterns by returning the configurator instance, enabling the chaining of additional configuration operations. This approach enhances readability and efficiency
     * in the setup process of UI components and their associated behaviors.
     * </p>
     *
     * @param observableValue
     *         the {@link LongBinding} to be associated with the new {@code EFXLongBinding}
     * @param bean
     *         the bean object associated with the {@code EFXLongBinding}, used for validation to ensure correct context and association
     * @param efxInitializer
     *         a {@code Consumer<EFXLongBinding>} for custom initialization and setup of the {@code EFXLongBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws NullPointerException
     *         if either {@code observableValue} or {@code efxInitializer} is null, indicating a critical omission in the binding's setup requirements
     * @throws InvalidAssociationException
     *         if the bean does not have the correct association within the configurator's context, safeguarding the relevance and applicability of the binding
     */
    default T initializeEFXLongBinding(LongBinding observableValue, Object bean, Consumer<EFXLongBinding> efxInitializer) {
        EFXObjectUtils.isNotNull(observableValue, () -> "The observableValue binding cannot be null in initializeEFXLongBinding");
        EFXObjectUtils.isNotNull(efxInitializer, () -> "The efxInitializer cannot be null in initializeEFXLongBinding");
        checkEFXBindingBeanIsValid(bean);
        efxInitializer.accept(EFXLongBinding.create(bean, observableValue));
        return getConfigurator();
    }

    /**
     * Initializes an {@link EFXBooleanBinding} with a specified {@link BooleanBinding}, bean, and an initialization consumer.
     *
     * <p>
     * This method facilitates the creation and customization of an {@code EFXBooleanBinding} instance. It validates the non-nullity of both the observable value and the initializer before proceeding with the
     * binding's initialization. This ensures that essential components for the binding's setup are present and correctly specified.
     * </p>
     *
     * <p>
     * The bean associated with the {@code EFXBooleanBinding} is validated to ensure it has the correct association within the configurator's context. This step is crucial for maintaining the integrity and
     * applicability of the binding within the application's UI.
     * </p>
     *
     * <p>
     * Upon successful validation, the provided {@code efxInitializer} consumer is invoked with a newly created {@code EFXBooleanBinding} instance, allowing for further configuration and customization of the
     * binding. This mechanism offers flexibility in defining the behavior and properties of the binding according to specific application needs.
     *
     * <br>
     *
     * <em>Usage Example:</em>
     * <pre>{@code
     * private EFXBooleanBinding efxBooleanBinding;
     * CustomControlConfigurator.create(customControl)
     *                          .initializeEFXBooleanBinding(doubleBinding, customControl, createdEFXBinding -> efxBooleanBinding = createdEFXBinding);
     * }</pre>
     * </p>
     *
     * <p>
     * The method supports fluent configuration patterns by returning the configurator instance, enabling the chaining of additional configuration operations. This approach enhances readability and efficiency
     * in the setup process of UI components and their associated behaviors.
     * </p>
     *
     * @param observableValue
     *         the {@link BooleanBinding} to be associated with the new {@code EFXBooleanBinding}
     * @param bean
     *         the bean object associated with the {@code EFXBooleanBinding}, used for validation to ensure correct context and association
     * @param efxInitializer
     *         a {@code Consumer<EFXBooleanBinding>} for custom initialization and setup of the {@code EFXBooleanBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws NullPointerException
     *         if either {@code observableValue} or {@code efxInitializer} is null, indicating a critical omission in the binding's setup requirements
     * @throws InvalidAssociationException
     *         if the bean does not have the correct association within the configurator's context, safeguarding the relevance and applicability of the binding
     */
    default T initializeEFXBooleanBinding(BooleanBinding observableValue, Object bean, Consumer<EFXBooleanBinding> efxInitializer) {
        EFXObjectUtils.isNotNull(observableValue, () -> "The observableValue binding cannot be null in initializeEFXBooleanBinding");
        EFXObjectUtils.isNotNull(efxInitializer, () -> "The efxInitializer cannot be null in initializeEFXBooleanBinding");
        checkEFXBindingBeanIsValid(bean);
        efxInitializer.accept(EFXBooleanBinding.create(bean, observableValue));
        return getConfigurator();
    }

    /**
     * Initializes an {@link EFXStringBinding} with a specified {@link StringBinding}, bean, and an initialization consumer.
     *
     * <p>
     * This method facilitates the creation and customization of an {@code EFXStringBinding} instance. It validates the non-nullity of both the observable value and the initializer before proceeding with the
     * binding's initialization. This ensures that essential components for the binding's setup are present and correctly specified.
     * </p>
     *
     * <p>
     * The bean associated with the {@code EFXStringBinding} is validated to ensure it has the correct association within the configurator's context. This step is crucial for maintaining the integrity and
     * applicability of the binding within the application's UI.
     * </p>
     *
     * <p>
     * Upon successful validation, the provided {@code efxInitializer} consumer is invoked with a newly created {@code EFXStringBinding} instance, allowing for further configuration and customization of the
     * binding. This mechanism offers flexibility in defining the behavior and properties of the binding according to specific application needs.
     *
     * <br>
     *
     * <em>Usage Example:</em>
     * <pre>{@code
     * private EFXStringBinding efxStringBinding;
     * CustomControlConfigurator.create(customControl)
     *                          .initializeEFXStringBinding(doubleBinding, customControl, createdEFXBinding -> efxStringBinding = createdEFXBinding);
     * }</pre>
     * </p>
     *
     * <p>
     * The method supports fluent configuration patterns by returning the configurator instance, enabling the chaining of additional configuration operations. This approach enhances readability and efficiency
     * in the setup process of UI components and their associated behaviors.
     * </p>
     *
     * @param observableValue
     *         the {@link StringBinding} to be associated with the new {@code EFXStringBinding}
     * @param bean
     *         the bean object associated with the {@code EFXStringBinding}, used for validation to ensure correct context and association
     * @param efxInitializer
     *         a {@code Consumer<EFXStringBinding>} for custom initialization and setup of the {@code EFXStringBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws NullPointerException
     *         if either {@code observableValue} or {@code efxInitializer} is null, indicating a critical omission in the binding's setup requirements
     * @throws InvalidAssociationException
     *         if the bean does not have the correct association within the configurator's context, safeguarding the relevance and applicability of the binding
     */
    default T initializeEFXStringBinding(StringBinding observableValue, Object bean, Consumer<EFXStringBinding> efxInitializer) {
        EFXObjectUtils.isNotNull(observableValue, () -> "The observableValue binding cannot be null in initializeEFXStringBinding");
        EFXObjectUtils.isNotNull(efxInitializer, () -> "The efxInitializer cannot be null in initializeEFXStringBinding");
        checkEFXBindingBeanIsValid(bean);
        efxInitializer.accept(EFXStringBinding.create(bean, observableValue));
        return getConfigurator();
    }

    /**
     * Initializes an {@link EFXObjectBinding<S>} with a specified {@link ObjectBinding<S>}, bean, and an initialization consumer.
     *
     * <p>
     * This method provides the infrastructure necessary for creating and tailoring an {@code EFXObjectBinding<S>} instance. Prior to the binding's actual initialization, it performs essential validations to
     * ensure neither the observable value nor the initializer are null, affirming that all prerequisites for the binding's setup are appropriately met and defined.
     * </p>
     *
     * <p>
     * Additionally, it confirms the bean's correct association with the configurator's context. This verification is critical for upholding the configuration's integrity, ensuring that the binding accurately
     * corresponds to and interacts with the intended UI component or context within the application.
     * </p>
     *
     * <p>
     * Upon passing these validations, the {@code efxInitializer} consumer is executed with a freshly instantiated {@code EFXObjectBinding<S>}, granting the opportunity for further detailed configuration and
     * adaptation of the binding. This enables the definition of specific behaviors and properties for the binding, tailored to meet particular needs of the application or UI logic.
     * </p>
     *
     * <p>
     * To facilitate a smooth and readable configuration process, the method adheres to a fluent configuration pattern, returning the configurator instance. This approach allows for the sequential chaining of
     * configuration operations, enhancing the clarity and efficacy of setting up UI components and their dynamic behaviors.
     * </p>
     *
     * <p>
     * <em>Usage Example:</em>
     * <pre>{@code
     * private EFXObjectBinding<YourType>; efxObjectBinding;
     * CustomControlConfigurator.create(customControl)
     *                          .initializeEFXObjectBinding(objectBinding, customControl, createdEFXBinding -> efxObjectBinding = createdEFXBinding);
     * }</pre>
     * </p>
     *
     * @param <S>
     *         the generic type parameter of the {@link ObjectBinding<S>} and {@link EFXObjectBinding<S>}
     * @param observableValue
     *         the {@link ObjectBinding<S>} to be associated with the new {@code EFXObjectBinding<S>}
     * @param bean
     *         the bean object tied to the {@code EFXObjectBinding<S>}, used for validation to confirm the correct contextual association
     * @param efxInitializer
     *         a {@code Consumer<EFXObjectBinding<S>>} tasked with custom initialization and configuration of the {@code EFXObjectBinding<S>}
     *
     * @return the configurator instance of type {@code T}, facilitating additional configuration through method chaining
     *
     * @throws NullPointerException
     *         if either {@code observableValue} or {@code efxInitializer} is null, pinpointing an essential deficiency in the binding's initialization prerequisites
     * @throws InvalidAssociationException
     *         if the bean's association does not align with the configurator's context, thus ensuring the binding's relevance and applicability
     */
    default <S> T initializeEFXObjectBinding(ObjectBinding<S> observableValue, Object bean, Consumer<EFXObjectBinding<S>> efxInitializer) {
        EFXObjectUtils.isNotNull(observableValue, () -> "The observableValue binding cannot be null in initializeEFXObjectBinding");
        EFXObjectUtils.isNotNull(efxInitializer, () -> "The efxInitializer cannot be null in initializeEFXObjectBinding");
        checkEFXBindingBeanIsValid(bean);
        efxInitializer.accept(EFXObjectBinding.create(bean, observableValue));
        return getConfigurator();
    }

    //endregion EFXInitialize Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the value of a specified {@link Property<Double>} to a given {@code Double} value.
     *
     * <p>
     * This method first validates that the specified property is associated with the correct bean, ensuring it is a valid target for the operation. This validation is crucial for maintaining the integrity of
     * the property's configuration and ensuring that the property update does not affect unrelated components or data.
     * </p>
     *
     * <p>
     * After validation {@link #checkPropertyBeanIsValid(Property)}, the method sets the property's value to the specified {@code Double} value. This operation can trigger any change listeners attached to the
     * property, enabling reactive behavior in the application where property updates lead to corresponding updates in the UI or other parts of the system.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance it belongs to, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Double>} whose value is to be set; must not be {@code null}
     * @param value
     *         the {@code Double} value to set for the property
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws IllegalArgumentException
     *         if the property is null
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T setDoublePropertyValue(Property<Double> property, Double value) {
        EFXObjectUtils.isNotNull(property, () -> "property object cannot be null in the setDoublePropertyValue method");
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return getConfigurator();
    }

    /**
     * Sets the value of a specified {@link Property<Double>} to a given {@code Double} value.
     *
     * <p>
     * This method first validates that the specified property is associated with the correct bean, ensuring it is a valid target for the operation. This validation is crucial for maintaining the integrity of
     * the property's configuration and ensuring that the property update does not affect unrelated components or data.
     * </p>
     *
     * <p>
     * After validation {@link #checkPropertyBeanIsValid(Property)}, the method sets the property's value to the specified {@code Double} value. This operation can trigger any change listeners attached to the
     * property, enabling reactive behavior in the application where property updates lead to corresponding updates in the UI or other parts of the system.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance it belongs to, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Double>} whose value is to be set; must not be {@code null}
     * @param value
     *         the {@code Double} value to set for the property
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws IllegalArgumentException
     *         if the property is null
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T setFloatPropertyValue(Property<Float> property, Float value) {
        EFXObjectUtils.isNotNull(property, () -> "property object cannot be null in the setFloatPropertyValue method");
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return getConfigurator();
    }

    /**
     * Sets the value of a specified {@link Property<Integer>} to a given {@code Integer} value.
     *
     * <p>
     * This method first validates that the specified property is associated with the correct bean, ensuring it is a valid target for the operation. This validation is crucial for maintaining the integrity of
     * the property's configuration and ensuring that the property update does not affect unrelated components or data.
     * </p>
     *
     * <p>
     * After validation {@link #checkPropertyBeanIsValid(Property)}, the method sets the property's value to the specified {@code Integer} value. This operation can trigger any change listeners attached to the
     * property, enabling reactive behavior in the application where property updates lead to corresponding updates in the UI or other parts of the system.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance it belongs to, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Integer>} whose value is to be set; must not be {@code null}
     * @param value
     *         the {@code Double} value to set for the property
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws IllegalArgumentException
     *         if the property is null
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T setIntegerPropertyValue(Property<Integer> property, Integer value) {
        EFXObjectUtils.isNotNull(property, () -> "property object cannot be null in the setIntegerPropertyValue method");
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return getConfigurator();
    }

    /**
     * Sets the value of a specified {@link Property<Long>} to a given {@code Long} value.
     *
     * <p>
     * This method first validates that the specified property is associated with the correct bean, ensuring it is a valid target for the operation. This validation is crucial for maintaining the integrity of
     * the property's configuration and ensuring that the property update does not affect unrelated components or data.
     * </p>
     *
     * <p>
     * After validation {@link #checkPropertyBeanIsValid(Property)}, the method sets the property's value to the specified {@code Long} value. This operation can trigger any change listeners attached to the
     * property, enabling reactive behavior in the application where property updates lead to corresponding updates in the UI or other parts of the system.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance it belongs to, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Long>} whose value is to be set; must not be {@code null}
     * @param value
     *         the {@code Double} value to set for the property
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws IllegalArgumentException
     *         if the property is null
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T setLongPropertyValue(Property<Long> property, Long value) {
        EFXObjectUtils.isNotNull(property, () -> "property object cannot be null in the setLongPropertyValue method");
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return getConfigurator();
    }

    /**
     * Sets the value of a specified {@link Property<Number>} to a given {@code Number} value.
     *
     * <p>
     * This method first validates that the specified property is associated with the correct bean, ensuring it is a valid target for the operation. This validation is crucial for maintaining the integrity of
     * the property's configuration and ensuring that the property update does not affect unrelated components or data.
     * </p>
     *
     * <p>
     * After validation {@link #checkPropertyBeanIsValid(Property)}, the method sets the property's value to the specified {@code Number} value. This operation can trigger any change listeners attached to the
     * property, enabling reactive behavior in the application where property updates lead to corresponding updates in the UI or other parts of the system.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance it belongs to, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Number>} whose value is to be set; must not be {@code null}
     * @param value
     *         the {@code Double} value to set for the property
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws IllegalArgumentException
     *         if the property is null
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T setNumberPropertyValue(Property<Number> property, Number value) {
        EFXObjectUtils.isNotNull(property, () -> "property object cannot be null in the setNumberPropertyValue method");
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return getConfigurator();
    }

    /**
     * Sets the value of a specified {@link Property<Boolean>} to a given {@code Double} value.
     *
     * <p>
     * This method first validates that the specified property is associated with the correct bean, ensuring it is a valid target for the operation. This validation is crucial for maintaining the integrity of
     * the property's configuration and ensuring that the property update does not affect unrelated components or data.
     * </p>
     *
     * <p>
     * After validation {@link #checkPropertyBeanIsValid(Property)}, the method sets the property's value to the specified {@code Boolean} value. This operation can trigger any change listeners attached to the
     * property, enabling reactive behavior in the application where property updates lead to corresponding updates in the UI or other parts of the system.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance it belongs to, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param property
     *         the {@link Property<Boolean>} whose value is to be set; must not be {@code null}
     * @param value
     *         the {@code Double} value to set for the property
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws IllegalArgumentException
     *         if the property is null
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T setBooleanPropertyValue(Property<Boolean> property, boolean value) {
        EFXObjectUtils.isNotNull(property, () -> "property object cannot be null in the setBooleanPropertyValue method");
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return getConfigurator();
    }

    /**
     * Sets the value of a specified {@link Property<String>} to a given {@code String} value.
     *
     * <p>
     * This method first validates that the specified property is associated with the correct bean, ensuring it is a valid target for the operation. This validation is crucial for maintaining the integrity of
     * the property's configuration and ensuring that the property update does not affect unrelated components or data.
     * </p>
     *
     * <p>
     * After validation {@link #checkPropertyBeanIsValid(Property)}, the method sets the property's value to the specified {@code String} value. This operation can trigger any change listeners attached to the
     * property, enabling reactive behavior in the application where property updates lead to corresponding updates in the UI or other parts of the system.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance it belongs to, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param property
     *         the {@link Property<String>} whose value is to be set; must not be {@code null}
     * @param value
     *         the {@code Double} value to set for the property
     *
     * @return the configurator instance of type {@code T}, enabling further configuration through method chaining
     *
     * @throws IllegalArgumentException
     *         if the property is null
     * @throws InvalidAssociationException
     *         if the property is not correctly associated with the control, as verified by the validation process
     */
    default T setStringPropertyValue(Property<String> property, String value) {
        EFXObjectUtils.isNotNull(property, () -> "property object cannot be null in the setStringPropertyValue method");
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return getConfigurator();
    }

    /**
     * Sets the value of a specified {@link Property<S>} to a given value of type S.
     *
     * <p>
     * This method begins by validating that the specified property is correctly associated with the intended bean, ensuring that it is an appropriate target for the value assignment. This validation step is
     * essential for preserving the integrity of the property's configuration and guaranteeing that updates to the property do not inadvertently influence unrelated components or data structures within the
     * application.
     * </p>
     *
     * <p>
     * After successfully validating the property with {@link #checkPropertyBeanIsValid(Property)}, the method proceeds to set the property's value to the provided value of type S. Executing this operation may
     * activate any change listeners associated with the property, fostering a reactive application design where updates to property values promptly reflect in the user interface or other interconnected parts
     * of the application architecture.
     * </p>
     *
     * <p>
     * Furthermore, this method promotes a fluent configuration style by returning the configurator instance to which it belongs. This approach facilitates the chaining of multiple configuration operations in a
     * manner that is both streamlined and easily understandable.
     * </p>
     *
     * @param <S>
     *         the type parameter of the property and value
     * @param property
     *         the {@link Property<S>} whose value is intended to be set; must not be {@code null}
     * @param value
     *         the value of type S to set for the property
     *
     * @return the configurator instance of type {@code T}, enabling further configuration via method chaining
     *
     * @throws IllegalArgumentException
     *         if the property is null
     * @throws InvalidAssociationException
     *         if the property is not suitably associated with the control, as confirmed by the validation process
     */
    default <S> T setObjectPropertyValue(Property<S> property, S value) {
        EFXObjectUtils.isNotNull(property, () -> "property object cannot be null in the setObjectPropertyValue method");
        checkPropertyBeanIsValid(property);
        property.setValue(value);
        return getConfigurator();
    }

    /**
     * Sets a new {@link DoubleBinding} to an {@link EFXDoubleBinding}.
     *
     * <p>
     * Validates that the provided {@code EFXDoubleBinding} is associated with the correct bean using {@link #checkEFXBindingBeanIsValid(Object)}. Upon successful validation, the method sets the new
     * {@code DoubleBinding} to the {@code EFXDoubleBinding}. This operation enables the {@code EFXDoubleBinding} to reflect the values and changes from the specified {@code DoubleBinding}.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXDoubleBinding} on which to set the new binding on
     * @param newBinding
     *         the {@link DoubleBinding} to set on the {@code EFXDoubleBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration via method chaining
     *
     * @throws IllegalArgumentException
     *         if the {@code EFXDoubleBinding} is null when passed into the function
     * @throws InvalidAssociationException
     *         if the {@code EFXDoubleBinding} is not suitably associated with the control, as confirmed by the validation process
     */
    default T setEFXDoubleBinding(EFXDoubleBinding efxBinding, DoubleBinding newBinding) {
        EFXObjectUtils.isNotNull(efxBinding, () -> "efxBinding object cannot be null in the setEFXDoubleBinding method");
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.setObservableValue(newBinding);
        return getConfigurator();
    }

    /**
     * Sets a new {@link FloatBinding} to an {@link EFXFloatBinding}.
     *
     * <p>
     * Validates that the provided {@code EFXFloatBinding} is associated with the correct bean using {@link #checkEFXBindingBeanIsValid(Object)}. Upon successful validation, the method sets the new
     * {@code FloatBinding} to the {@code EFXFloatBinding}. This operation enables the {@code EFXFloatBinding} to reflect the values and changes from the specified {@code FloatBinding}.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXFloatBinding} on which to set the new binding on
     * @param newBinding
     *         the {@link FloatBinding} to set on the {@code EFXFloatBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration via method chaining
     *
     * @throws IllegalArgumentException
     *         if the {@code EFXFloatBinding} is null when passed into the function
     * @throws InvalidAssociationException
     *         if the {@code EFXFloatBinding} is not suitably associated with the control, as confirmed by the validation process
     */
    default T setEFXFloatBinding(EFXFloatBinding efxBinding, FloatBinding newBinding) {
        EFXObjectUtils.isNotNull(efxBinding, () -> "efxBinding object cannot be null in the setEFXFloatBinding method");
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.setObservableValue(newBinding);
        return getConfigurator();
    }

    /**
     * Sets a new {@link IntegerBinding} to an {@link EFXIntegerBinding}.
     *
     * <p>
     * Validates that the provided {@code EFXIntegerBinding} is associated with the correct bean using {@link #checkEFXBindingBeanIsValid(Object)}. Upon successful validation, the method sets the new
     * {@code IntegerBinding} to the {@code EFXIntegerBinding}. This operation enables the {@code EFXIntegerBinding} to reflect the values and changes from the specified {@code IntegerBinding}.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXIntegerBinding} on which to set the new binding on
     * @param newBinding
     *         the {@link IntegerBinding} to set on the {@code EFXIntegerBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration via method chaining
     *
     * @throws IllegalArgumentException
     *         if the {@code EFXIntegerBinding} is null when passed into the function
     * @throws InvalidAssociationException
     *         if the {@code EFXIntegerBinding} is not suitably associated with the control, as confirmed by the validation process
     */
    default T setEFXIntegerBinding(EFXIntegerBinding efxBinding, IntegerBinding newBinding) {
        EFXObjectUtils.isNotNull(efxBinding, () -> "efxBinding object cannot be null in the setEFXIntegerBinding method");
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.setObservableValue(newBinding);
        return getConfigurator();
    }

    /**
     * Sets a new {@link LongBinding} to an {@link EFXLongBinding}.
     *
     * <p>
     * Validates that the provided {@code EFXLongBinding} is associated with the correct bean using {@link #checkEFXBindingBeanIsValid(Object)}. Upon successful validation, the method sets the new
     * {@code LongBinding} to the {@code EFXLongBinding}. This operation enables the {@code EFXLongBinding} to reflect the values and changes from the specified {@code LongBinding}.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXLongBinding} on which to set the new binding on
     * @param newBinding
     *         the {@link LongBinding} to set on the {@code EFXLongBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration via method chaining
     *
     * @throws IllegalArgumentException
     *         if the {@code EFXLongBinding} is null when passed into the function
     * @throws InvalidAssociationException
     *         if the {@code EFXLongBinding} is not suitably associated with the control, as confirmed by the validation process
     */
    default T setEFXLongBinding(EFXLongBinding efxBinding, LongBinding newBinding) {
        EFXObjectUtils.isNotNull(efxBinding, () -> "efxBinding object cannot be null in the setEFXLongBinding method");
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.setObservableValue(newBinding);
        return getConfigurator();
    }

    /**
     * Sets a new {@link BooleanBinding} to an {@link EFXBooleanBinding}.
     *
     * <p>
     * Validates that the provided {@code EFXBooleanBinding} is associated with the correct bean using {@link #checkEFXBindingBeanIsValid(Object)}. Upon successful validation, the method sets the new
     * {@code BooleanBinding} to the {@code EFXBooleanBinding}. This operation enables the {@code EFXBooleanBinding} to reflect the values and changes from the specified {@code BooleanBinding}.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXBooleanBinding} on which to set the new binding on
     * @param newBinding
     *         the {@link BooleanBinding} to set on the {@code EFXBooleanBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration via method chaining
     *
     * @throws IllegalArgumentException
     *         if the {@code EFXBooleanBinding} is null when passed into the function
     * @throws InvalidAssociationException
     *         if the {@code EFXBooleanBinding} is not suitably associated with the control, as confirmed by the validation process
     */
    default T setEFXBooleanBinding(EFXBooleanBinding efxBinding, BooleanBinding newBinding) {
        EFXObjectUtils.isNotNull(efxBinding, () -> "efxBinding object cannot be null in the setEFXBooleanBinding method");
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.setObservableValue(newBinding);
        return getConfigurator();
    }

    /**
     * Sets a new {@link StringBinding} to an {@link EFXStringBinding}.
     *
     * <p>
     * Validates that the provided {@code EFXStringBinding} is associated with the correct bean using {@link #checkEFXBindingBeanIsValid(Object)}. Upon successful validation, the method sets the new
     * {@code StringBinding} to the {@code EFXStringBinding}. This operation enables the {@code EFXStringBinding} to reflect the values and changes from the specified {@code StringBinding}.
     * </p>
     *
     * <p>
     * The method supports fluent configuration by returning the configurator instance, allowing for chaining multiple configuration operations in a concise and readable manner.
     * </p>
     *
     * @param efxBinding
     *         the {@link EFXStringBinding} on which to set the new binding on
     * @param newBinding
     *         the {@link StringBinding} to set on the {@code EFXStringBinding}
     *
     * @return the configurator instance of type {@code T}, enabling further configuration via method chaining
     *
     * @throws IllegalArgumentException
     *         if the {@code EFXStringBinding} is null when passed into the function
     * @throws InvalidAssociationException
     *         if the {@code EFXStringBinding} is not suitably associated with the control, as confirmed by the validation process
     */
    default T setEFXStringBinding(EFXStringBinding efxBinding, StringBinding newBinding) {
        EFXObjectUtils.isNotNull(efxBinding, () -> "efxBinding object cannot be null in the setEFXStringBinding method");
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.setObservableValue(newBinding);
        return getConfigurator();
    }

    /**
     * Sets a new {@link ObjectBinding<S>} to an {@link EFXObjectBinding<S>}.
     *
     * <p>
     * This method ensures that the provided {@code EFXObjectBinding<S>} is correctly associated with its bean by utilizing {@link #checkEFXBindingBeanIsValid(Object)}. After validating this association, it
     * assigns the new {@code ObjectBinding<S>} to the {@code EFXObjectBinding<S>}. This action allows the {@code EFXObjectBinding<S>} to accurately represent the values and changes encapsulated by the
     * {@code ObjectBinding<S>}, facilitating dynamic updates and interactions based on the binding's current state.
     * </p>
     *
     * <p>
     * Supporting fluent configuration, this method enhances usability by returning the configurator instance. This return value permits the chaining of multiple configuration operations, promoting a clear and
     * efficient setup process. Through this mechanism, users can seamlessly integrate various configuration steps into a coherent sequence, simplifying complex setups.
     * </p>
     *
     * @param <S>
     *         the type parameter defining the data type bound by the {@code ObjectBinding<S>}
     * @param efxBinding
     *         the {@link EFXObjectBinding<S>} to be updated with a new binding
     * @param newBinding
     *         the {@link ObjectBinding<S>} to be set on the {@code EFXObjectBinding<S>}, providing new dynamic behavior
     *
     * @return the configurator instance of type {@code T}, facilitating further configuration through method chaining
     *
     * @throws IllegalArgumentException
     *         if the {@code EFXObjectBinding<S>} is null when passed into the function
     * @throws InvalidAssociationException
     *         if the {@code EFXObjectBinding<S>} is not properly associated with the expected bean, as verified through the validation process
     */
    default <S> T setEFXObjectBinding(EFXObjectBinding<S> efxBinding, ObjectBinding<S> newBinding) {
        EFXObjectUtils.isNotNull(efxBinding, () -> "efxBinding object cannot be null in the setEFXObjectBinding method");
        checkEFXBindingBeanIsValid(efxBinding.getBean());
        efxBinding.setObservableValue(newBinding);
        return getConfigurator();
    }

    //endregion Set Functions
}
