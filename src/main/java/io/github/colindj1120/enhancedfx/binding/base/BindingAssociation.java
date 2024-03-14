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
package io.github.colindj1120.enhancedfx.binding.base;

import javafx.beans.binding.Binding;

import java.util.Optional;

/**
 * The BindingAssociation interface provides a structured way to associate a {@link Binding} with its corresponding bean, ensuring that
 * the relationship between a binding and its underlying property or properties is clearly defined and managed. This interface offers
 * utility methods for validation and retrieval of both the binding and its associated bean, facilitating a robust and type-safe way to
 * handle bindings in JavaFX.
 *
 * <p>
 * <h2>Key features:</h2>
 * <ul>
 *     <li><b>Type Safety:</b> Through generic typing and runtime type checks, it enforces the correct usage of bindings
 *     with their respective beans.</li>
 *     <li><b>Validation:</b> It provides methods to ensure non-null beans and correct instance types for bindings, throwing
 *     IllegalArgumentExceptions when validations fail. This prevents common errors such as null pointer exceptions and
 *     class cast exceptions when working with bindings.</li>
 *     <li><b>Retrieval:</b> Methods to get the current binding and its associated bean are provided, enabling easy access to
 *     these components for further manipulation or inspection.</li>
 * </ul>
 * </p>generate javadocs for these
 *
 * <p>This interface is especially useful in complex JavaFX applications where bindings play a crucial role in UI logic and
 * data representation, providing a standardized way to manage and utilize bindings across the application.</p>
 *
 * @param <T>
 *         the type of the value that the binding computes
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Binding
 */
public interface BindingAssociation<T> {

    /**
     * Checks if the provided bean is not null. Throws an {@link IllegalArgumentException} if the bean is null.
     *
     * @param bean
     *         the bean to check for nullity
     * @param clazz
     *         the class where the check is being made, for error message context
     *
     * @throws IllegalArgumentException
     *         if the bean is null
     */
    default void checkBeanIsNotNull(Object bean, Class<?> clazz) {
        String nullMessage = String.format("Bean cannot be null in %s", clazz.getSimpleName());
        Optional.ofNullable(bean)
                .orElseThrow(() -> new IllegalArgumentException(nullMessage));
    }

    /**
     * Validates that a given binding is an instance of a specific class. If the binding is not an instance of the specified class, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param binding
     *         the binding to check
     * @param expectedType
     *         the class the binding is expected to be an instance of
     * @param <U>
     *         the type parameter of the expected binding class
     *
     * @return the casted binding if it's an instance of the expected type
     *
     * @throws IllegalArgumentException
     *         if the binding is not an instance of the specified class
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    default <U extends Binding<?>> U checkInstanceOf(Binding<?> binding, Class<U> expectedType) {
        if (!expectedType.isInstance(binding)) {
            throw new IllegalArgumentException("Binding must be an instance of " + expectedType.getSimpleName());
        }
        return (U) binding; // Perform the cast after the check
    }

    /**
     * Retrieves the associated binding.
     *
     * @return the {@link Binding} instance associated with this binding association
     */
    Binding<T> getBinding();

    /**
     * Retrieves the bean associated with this binding. The bean typically represents the object on which the binding depends.
     *
     * @return the bean associated with the binding
     */
    Object getBean();
}

