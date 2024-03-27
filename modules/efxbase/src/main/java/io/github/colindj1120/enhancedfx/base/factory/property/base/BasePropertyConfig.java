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
import javafx.beans.property.StringProperty;

/**
 * The {@code BasePropertyConfig} interface defines a contract for configuring properties within the EnhancedFX framework. It pairs property instances with their configurators, allowing for enhanced
 * manipulation and setup of {@link Property} objects.
 *
 * <p>This interface serves as a foundational element in property configuration, facilitating a structured approach to apply custom configurations and listeners to properties. It bridges property instances with
 * their specific configurators, enabling advanced property manipulation techniques.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Links {@link Property} instances with their respective configurators, ensuring a cohesive configuration process.</li>
 *     <li>Allows for easy retrieval of property configurators, enabling complex property configuration strategies to be applied.</li>
 *     <li>Supports a wide range of property types, making it versatile for use across different application domains.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * public class MyPropertyConfigurator extends PropertyConfiguratorBase<String> {
 *     private StringProperty property;
 *
 *     public MyPropertyConfigurator(StringProperty property) {
 *         this.property = property;
 *     }
 *
 *     @Override
 *     protected Property<String> getProperty() {
 *         return property;
 *     }
 *
 *     @Override
 *     protected void setProperty(Property<String> value) {
 *         if (value instanceof StringProperty) {
 *             this.property = (StringProperty) value;
 *         } else {
 *             throw new IllegalArgumentException("Property must be a StringProperty");
 *         }
 *     }
 * }
 *
 * StringProperty myStringProperty = new SimpleStringProperty("Initial Value");
 * MyPropertyConfigurator configurator = new MyPropertyConfigurator(myStringProperty);
 * BasePropertyConfig<String, MyPropertyConfigurator> baseConfig = // instantiation logic here;
 *
 * baseConfig.getProperty().addListener((observable, oldValue, newValue) -> {
 *     System.out.println("Property value changed from " + oldValue + " to " + newValue);
 * });
 * }</pre>
 *
 * <p>This example showcases how to implement a {@code PropertyConfiguratorBase} for a {@link StringProperty}, providing custom configuration logic. The {@code BasePropertyConfig} interface facilitates the
 * linkage between the property and its configurator, enabling advanced property manipulation and listening for changes.</p>
 *
 * @param <T>
 *         the type of the value that the {@link Property} holds
 * @param <R>
 *         the type of the configurator that is associated with the property
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Property
 * @see PropertyConfiguratorBase
 */
public interface BasePropertyConfig<T, R extends PropertyConfiguratorBase<T>> {
    /**
     * Retrieves the configurator associated with this property configuration. This configurator is responsible for applying specific configurations to the property.
     *
     * @return the configurator associated with the property
     */
    R getConfigurator();

    /**
     * Retrieves the property being managed by this configuration. This method provides access to the property for further manipulation or observation.
     *
     * @return the property being configured
     */
    Property<T> getProperty();
}

