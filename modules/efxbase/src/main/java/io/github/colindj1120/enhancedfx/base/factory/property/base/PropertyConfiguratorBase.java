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
package io.github.colindj1120.enhancedfx.base.factory.property.base;

import io.github.colindj1120.enhancedfx.base.factory.property.PropertyConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;

/**
 * Serves as an abstract foundation for creating configurators for JavaFX {@link Property} objects, facilitating fluent API design for
 * property configuration within the EnhancedFX framework. This base class encapsulates common functionality for property manipulation,
 * including adding change and invalidation listeners, binding properties in a unidirectional or bidirectional manner, and setting
 * property values.
 * <p>
 * Implementations of this class should provide concrete methods to apply specific configuration strategies to {@link Property}
 * instances. By leveraging this base class, developers can create specialized configurators that enhance the usability and flexibility
 * of property handling in JavaFX applications, promoting cleaner and more maintainable code.
 * </p>
 * <p>
 * <h2>Key functionalities include:</h2>
 * <ul>
 *     <li>Adding change and invalidation listeners to properties.</li>
 *     <li>Binding properties to other properties, both uni-directionally and bidirectionally.</li>
 *     <li>Setting the value of properties.</li>
 *     <li>Unbinding properties from other properties.</li>
 * </ul>
 * </p>
 * <p>
 * This abstract class requires implementations to define how these operations are specifically handled,
 * allowing for customization and extension based on the needs of the application or the specific type
 * of property being configured.
 * </p>
 *
 * @param <T>
 *         the type of the property value that this configurator manipulates
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
@SuppressWarnings("UnusedReturnValue")
public abstract class PropertyConfiguratorBase<T> {
    protected Property<T> property;

    /**
     * Constructs a new {@code PropertyConfiguratorBase} instance associated with the specified {@link Property}.
     *
     * @param property
     *         The {@link Property} to be configured by this configurator. This property represents the target on which all
     *         configuration actions (such as binding, setting values, etc.) will be performed.
     */
    protected PropertyConfiguratorBase(Property<T> property) {
        this.property = property;
    }

    /**
     * Adds a {@link ChangeListener} to the property, which will be notified whenever the property's value changes.
     *
     * @param listener
     *         The {@link ChangeListener} to be added.
     *
     * @return A reference to this {@code PropertyConfigurator}, allowing for method chaining.
     */
    public abstract PropertyConfigurator<T> addChangeListener(ChangeListener<? super T> listener);

    /**
     * Adds an {@link InvalidationListener} to the property. The listener will be notified whenever the property becomes invalid,
     * typically after its value changes.
     *
     * @param invalidationListener
     *         The {@link InvalidationListener} to be added.
     *
     * @return A reference to this {@code PropertyConfigurator}, allowing for method chaining.
     */
    public abstract PropertyConfigurator<T> addInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Binds this configurator's property to another {@link Property}, causing the first property to automatically update its value to
     * match the second whenever the second property's value changes.
     *
     * @param otherProperty
     *         The {@link Property} to which this configurator's property will be bound.
     *
     * @return A reference to this {@code PropertyConfigurator}, allowing for method chaining.
     */
    public abstract PropertyConfigurator<T> bind(Property<T> otherProperty);

    /**
     * Unbinds the property from any other property it is currently bound to. After calling this method, the property will no longer
     * automatically update its value to match that of the other property.
     *
     * @return A reference to this {@code PropertyConfigurator}, allowing for method chaining.
     */
    public abstract PropertyConfigurator<T> unbind();

    /**
     * Establishes a bidirectional binding between this configurator's property and another {@link Property}. Both properties will
     * automatically update to match each other's value whenever either of their values changes.
     *
     * @param otherProperty
     *         The {@link Property} to bind with this configurator's property bidirectionally.
     *
     * @return A reference to this {@code PropertyConfigurator}, allowing for method chaining.
     */
    public abstract PropertyConfigurator<T> bindBidirectional(Property<T> otherProperty);

    /**
     * Removes a bidirectional binding between this configurator's property and another {@link Property}. After calling this method,
     * the properties will no longer automatically update to match each other's value.
     *
     * @param otherProperty
     *         The {@link Property} from which the bidirectional binding will be removed.
     *
     * @return A reference to this {@code PropertyConfigurator}, allowing for method chaining.
     */
    public abstract PropertyConfigurator<T> unbindBidirectional(Property<T> otherProperty);

    /**
     * Sets the value of the property. This method allows for directly setting the value of the property managed by this configurator.
     *
     * @param value
     *         The new value to set for the property.
     *
     * @return A reference to this {@code PropertyConfigurator}, allowing for method chaining.
     */
    public abstract PropertyConfigurator<T> setValue(T value);
}
