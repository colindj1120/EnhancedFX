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

import javafx.beans.property.Property;

/**
 * The {@code PropertyConfiguratorBase} abstract class serves as the foundation for property configurators within the EnhancedFX library, facilitating the management and configuration of {@link Property}
 * instances in JavaFX.
 *
 * <p>Designed to be extended by concrete configurators, it provides a structured approach to property customization, enabling the development of rich, responsive user interfaces. This base class encapsulates
 * common functionalities needed for property configuration, ensuring consistency and reducing redundancy across different types of property configurators. It mandates the implementation of key methods for
 * accessing and setting the {@link Property} being managed, thereby establishing a contract for its subclasses.</p>
 *
 * <h2>Core Features</h2>
 * <ul>
 *     <li><b>Abstract Design</b>: Intended to be subclassed, not instantiated directly, promoting a clear and modular architecture.</li>
 *     <li><b>Property Management</b>: Offers a framework for accessing and modifying JavaFX {@link Property} instances, crucial for dynamic UI updates.</li>
 *     <li><b>Encapsulation</b>: Encourages encapsulation of property logic within configurators, leading to cleaner and more maintainable code.</li>
 *     <li><b>Extensibility</b>: Provides a solid base for creating specific property configurators, supporting diverse property types and customization needs.</li>
 * </ul>
 *
 * <h2>Abstract Methods</h2>
 * Subclasses of {@code PropertyConfiguratorBase} are required to implement the following abstract methods:
 * <ul>
 *     <li>{@link #getProperty()}: Retrieves the {@link Property} instance being configured.</li>
 *     <li>{@link #setProperty(Property)}: Associates a new {@link Property} instance with the configurator.</li>
 * </ul>
 *
 * <p>By extending {@code PropertyConfiguratorBase}, developers can create specialized configurators that streamline the customization of JavaFX properties, enhancing UI interactivity and responsiveness.</p>
 *
 * @param <T>
 *         the type of the value that the {@link Property} can hold
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Property
 */
public abstract class PropertyConfiguratorBase<T> {
    /**
     * Constructs a new instance of {@code PropertyConfiguratorBase}.
     *
     * <p>This constructor is protected to ensure that only subclasses within the package can instantiate it, preserving the integrity of the property configuration mechanism.</p>
     */
    protected PropertyConfiguratorBase() {}

    /**
     * Retrieves the {@link Property} associated with this configurator. Subclasses must provide an implementation that returns the specific {@link Property} instance they are managing.
     *
     * @return the {@link Property} instance being configured by this configurator
     */
    protected abstract Property<T> getProperty();

    /**
     * Sets the {@link Property} to be managed by this configurator.
     *
     * <p>This method allows subclasses to define how a new {@link Property} instance is associated with the configurator, enabling dynamic property management.</p>
     *
     * @param value
     *         the new {@link Property} instance to be managed by this configurator
     */
    protected abstract void setProperty(final Property<T> value);
}
