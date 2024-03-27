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
package io.github.colindj1120.enhancedfx.base.factory.property;

import io.github.colindj1120.enhancedfx.base.factory.property.base.CustomPropertyConfig;
import io.github.colindj1120.enhancedfx.base.factory.property.base.PropertyConfiguratorBase;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;

/**
 * {@code CustomPropertyConfigurator} is a utility class designed for advanced configuration of JavaFX {@link Property} objects within the EnhancedFX framework. It extends {@link PropertyConfiguratorBase} and
 * implements {@link CustomPropertyConfig}, offering a fluent API for property manipulation.
 *
 * <p>This configurator provides specialized functionalities to enhance the usability and control of property objects, particularly focusing on locking and unlocking mechanisms. Such capabilities allow
 * developers to temporarily make a property read-only, preventing any external modifications to its value.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Enables locking of a property to prevent changes to its value, effectively making it read-only.</li>
 *     <li>Allows unlocking of the property, restoring its ability to accept value changes.</li>
 *     <li>Supports fluent API design, enabling chaining of configuration methods for concise code.</li>
 *     <li>Provides a static factory method for easy instantiation and configuration of properties.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * // Assume there's a JavaFX property for configuration
 * StringProperty nameProperty = new SimpleStringProperty("Initial Name");
 *
 * // Creating a configurator instance for the property
 * CustomPropertyConfigurator<String> configurator =
 *     CustomPropertyConfigurator.create(nameProperty);
 *
 * // Locking the property to prevent changes
 * configurator.lockProperty();
 * nameProperty.set("New Name"); // This change will not take effect
 *
 * // Unlocking the property to allow changes
 * configurator.unlockProperty();
 * nameProperty.set("New Name"); // The change will now take effect
 * }
 * </pre>
 *
 * <p>This example demonstrates how {@code CustomPropertyConfigurator} can be used to control the mutability of a {@link Property}'s value through its lock and unlock functionalities, illustrating the ease
 * of managing property access within the EnhancedFX framework.</p>
 *
 * @param <T>
 *         The type of the property's value
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Property
 * @see CustomPropertyConfig
 * @see PropertyConfiguratorBase
 */
@SuppressWarnings("UnusedReturnValue")
public class CustomPropertyConfigurator<T> extends PropertyConfiguratorBase<T> implements CustomPropertyConfig<T, CustomPropertyConfigurator<T>> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Static factory method to create a new {@code CustomPropertyConfigurator} instance for a specific property. This method promotes a consistent method of instantiation, abstracting the constructor.
     *
     * @param property
     *         The JavaFX {@link Property} to be configured
     * @param <T>
     *         The type of the property's value
     *
     * @return A new instance of {@code CustomPropertyConfigurator}
     */
    public static <T> CustomPropertyConfigurator<T> create(Property<T> property) {
        return new CustomPropertyConfigurator<>(property);
    }

    //endregion Static Factory Method

    private Property<T> property;

    /**
     * Listener for locking the property. When a property is locked using {@link #lockProperty()}, this listener is added to it. The listener reverts any changes made to the property's value, effectively
     * locking it.
     */
    private final ChangeListener<T> lockChangeListener = (obs, oldValue, newValue) -> {
        // Revert to old value, effectively locking the property
        getProperty().setValue(oldValue);
    };

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Creates a new instance of {@code CustomPropertyConfigurator} for the specified property. This constructor is protected to encourage the use of the static factory method {@link #create(Property)} for
     * object creation.
     *
     * @param property
     *         The {@link Property} to be configured
     */
    protected CustomPropertyConfigurator(Property<T> property) {
        super();
        this.property = property;
    }

    //endregion Constructor

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomPropertyConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<T> getProperty() {
        return this.property;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setProperty(Property<T> value) {
        this.property = value;
    }

    //endregion Overridden Methods

    //region Utility Methods
    //*****************************************************************
    // Utility Methods
    //*****************************************************************

    /**
     * Locks the property, preventing its value from being changed. Once locked, any attempt to change the property's value will be reverted to its previous value.
     *
     * @return This {@code CustomPropertyConfigurator} instance, allowing for method chaining
     */
    public CustomPropertyConfigurator<T> lockProperty() {
        this.property.addListener(lockChangeListener);
        return this;
    }

    /**
     * Unlocks the property, allowing its value to be changed again. This removes the restriction put in place by {@link #lockProperty()}.
     *
     * @return This {@code CustomPropertyConfigurator} instance, allowing for method chaining
     */
    public CustomPropertyConfigurator<T> unlockProperty() {
        this.property.removeListener(lockChangeListener);
        return this;
    }

    //endregion Utility Methods
}
