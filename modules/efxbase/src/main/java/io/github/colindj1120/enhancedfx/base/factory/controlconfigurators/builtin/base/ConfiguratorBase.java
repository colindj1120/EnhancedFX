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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base;

import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;

/**
 * Serves as the foundational class for all configurators within the EnhancedFX framework, providing a standardized approach for manipulating and configuring JavaFX {@link Node} properties.
 *
 * <p>
 * The {@code ConfiguratorBase} class facilitates a robust and flexible configuration mechanism through its abstract methods, enabling subclasses to define specific behaviors for setting and retrieving
 * {@link Node} instances, as well as implementing custom logic for equality checks and safe type casting. This design supports a wide range of configurator implementations, tailored to the diverse needs of
 * JavaFX UI components.
 * </p>
 *
 * <h2>Core Functionalities:</h2>
 * <p>
 * <ul>
 *   <li><b>Node Association:</b> Abstract methods {@code getNode()} and {@code setNode(Node value)} allow for getting and setting the {@link Node} associated with the configurator, respectively.</li>
 *   <li><b>Node Equality:</b> The {@code nodeEquals(Node value)} method provides a mechanism for checking the equality of the associated {@link Node} against another {@link Node}, facilitating condition
 *   checks within the UI logic.</li>
 *   <li><b>Type Safety and Casting:</b> The {@code checkNodeAndCast} method offers a utility for validating the type of a {@link Node} and casting it safely, throwing an informative exception if the type
 *   check fails.</li>
 * </ul>
 * </p>
 *
 * <p>
 * By encapsulating common configurator operations within this base class, EnhancedFX aims to simplify the development and maintenance of configurable JavaFX components, enhancing code reusability and
 * readability. Developers can extend {@code ConfiguratorBase} to create specialized configurators for different types of {@link Node}s, leveraging the provided methods to implement the configuration logic
 * efficiently.
 * </p>
 *
 *
 * <h2>Example Usage:</h2>
 * <p>
 * <pre>{@code
 * public class ButtonConfigurator extends ConfiguratorBase {
 *     // Implementation of abstract methods
 * }
 *
 * ButtonConfigurator configurator = new ButtonConfigurator();
 * configurator.setNode(new Button("Click Me!"));
 * Node associatedNode = configurator.getNode();
 * }</pre>
 *
 * <br>
 * <p>
 * This example demonstrates how a custom {@code ButtonConfigurator} might be implemented by extending {@code ConfiguratorBase}, showcasing the process of setting and retrieving a {@link Node}.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Node
 * @see EFXObjectUtils
 */
public abstract class ConfiguratorBase {
    /**
     * Protected constructor for {@code ConfiguratorBase} to prevent direct instantiation.
     *
     * <p>
     * This constructor is protected to encourage subclassing while preventing the direct instantiation of {@code ConfiguratorBase}. Subclasses are expected to provide concrete implementations of the abstract
     * methods defined within this base class, tailoring the configurator's functionality to specific types of {@link Node}s.
     * </p>
     */
    protected ConfiguratorBase() {}

    /**
     * Retrieves the {@link Node} associated with this configurator.
     *
     * <p>
     * Implementations of this method should return the current {@link Node} instance that this configurator is intended to manipulate or configure. This method is crucial for enabling configurator operations
     * that directly affect the properties, layout, or appearance of the target {@link Node}.
     * </p>
     *
     * @return the {@link Node} instance currently associated with this configurator.
     */
    public abstract Node getNode();

    /**
     * Sets the {@link Node} to be associated with this configurator.
     *
     * <p>
     * Implementations of this method should associate the provided {@link Node} with this configurator, enabling subsequent configuration operations to be applied to it. This method allows for the dynamic
     * assignment or reassignment of the target {@link Node}, facilitating flexible configurator reuse.
     * </p>
     *
     * @param value
     *         the {@link Node} to associate with this configurator.
     */
    public abstract void setNode(Node value);

    /**
     * Compares the specified {@link Node} with the configurator's associated {@link Node} for equality.
     *
     * <p>
     * Implementations of this method should determine whether the provided {@link Node} instance is equal to the {@link Node} currently associated with this configurator. Equality criteria may vary based on
     * the specific requirements or characteristics of the {@link Node} instances being compared.
     * </p>
     *
     * @param value
     *         the {@link Node} to compare with the configurator's associated {@link Node}.
     *
     * @return {@code true} if the specified {@link Node} is equal to the configurator's associated {@link Node}; {@code false} otherwise.
     */
    public abstract boolean nodeEquals(Node value);

    /**
     * Validates that the specified {@link Node} is an instance of the given class type and casts it accordingly.
     *
     * <p>
     * This method checks if the provided {@link Node} is an instance of {@code classTypeToCheck}. If the check passes, the {@link Node} is cast to the specified type. This utility method aids in enforcing type
     * safety within configurator operations that require specific types of {@link Node}s. An exception is thrown if the {@link Node} is not an instance of the specified class, with a detailed message including
     * the calling function and classes involved.
     * </p>
     *
     * @param <T>
     *         the type to which the {@link Node} is to be cast.
     * @param <U>
     *         the implementing class used for detailed exception messaging.
     * @param value
     *         the {@link Node} to check and cast.
     * @param classTypeToCheck
     *         the class type to check against the {@link Node}.
     * @param implementingClass
     *         the class from which this method is called, used for detailed exception messaging.
     * @param callingFunction
     *         the name of the method calling this utility, included in the exception message for clarity.
     *
     * @return the {@link Node} cast to the specified type {@code T} if the check passes.
     *
     * @throws IllegalArgumentException
     *         if the {@link Node} is not an instance of {@code classTypeToCheck}, with a detailed error message.
     */
    protected <T, U> T checkNodeAndCast(Node value, Class<T> classTypeToCheck, Class<U> implementingClass, String callingFunction) {
        String message = String.format("Node:{%s} is not an instance of %s in {Class: %s, Method: %s}", value.toString(), classTypeToCheck.getSimpleName(), implementingClass.getSimpleName(), callingFunction);
        return EFXObjectUtils.checkInstanceOfAndCast(value, classTypeToCheck, () -> message);
    }
}
