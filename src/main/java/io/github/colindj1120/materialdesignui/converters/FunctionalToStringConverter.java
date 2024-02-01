/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.materialdesignui.converters;

import javafx.util.StringConverter;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Functional interface for defining custom toString conversions.
 * <p>
 * This interface is designed to facilitate the creation of {@link StringConverter}s in JavaFX applications, specifically focusing on converting objects of type T to their String representations. It
 * provides a functional approach to defining custom toString behavior, enabling the use of lambda expressions and method references for concise and flexible conversion logic.
 * </p>
 * <p><pre>
 * The interface offers several static factory methods to create StringConverters with different behaviors:
 * - A basic converter that uses the provided functional interface for conversion.
 * - A converter that returns a default string for null objects.
 * - A converter that throws a custom exception for null objects.
 * </pre></p>
 * <p>
 * This design allows for easy integration of custom string conversion logic in JavaFX components such as ListView, TableView, and ComboBox, where custom rendering of objects is often required. By
 * using this interface, developers can quickly implement and change the way objects are represented as strings in the UI, without the need for verbose anonymous classes or complex implementations.
 * </p>
 * <pre>
 * Example Usage:
 * {@code
 * StringConverter<Person> personConverter = FunctionalToStringConverter.of(
 *     person -> person.getName() + " (" + person.getAge() + " years)",
 *     "Unknown Person"
 * );
 * }
 * </pre>
 *
 * @param <T>
 *         The type of object to be converted to a String.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
@FunctionalInterface
public interface FunctionalToStringConverter<T> {
    /**
     * Converts an object of type T to its String representation.
     *
     * @param object
     *         The object to be converted to a String.
     *
     * @return The String representation of the object.
     */
    String toString(T object);

    /**
     * Creates a StringConverter using the provided FunctionalToStringConverter. If the object to be converted is null, it returns an empty string.
     *
     * @param <T>
     *         The type of object to be converted to a String.
     * @param converter
     *         The FunctionalToStringConverter to use for conversion.
     *
     * @return A StringConverter that uses the provided converter for the toString method.
     */
    static <T> StringConverter<T> of(FunctionalToStringConverter<T> converter) {
        return of(converter, "");
    }

    /**
     * Creates a StringConverter using the provided FunctionalToStringConverter. Allows specifying a custom String to return when the object to be converted is null.
     *
     * @param <T>
     *         The type of object to be converted to a String.
     * @param converter
     *         The FunctionalToStringConverter to use for conversion.
     * @param nullReturn
     *         The String to return when the object is null.
     *
     * @return A StringConverter that uses the provided converter and handles null objects.
     */
    static <T> StringConverter<T> of(FunctionalToStringConverter<T> converter, String nullReturn) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                return Optional.ofNullable(object)
                               .map(converter::toString)
                               .orElse(nullReturn);
            }

            @Override
            public T fromString(String string) {
                throw new UnsupportedOperationException("FromString conversion not implemented");
            }
        };
    }

    /**
     * Creates a StringConverter using the provided FunctionalToStringConverter. If the object to be converted is null, it throws a runtime exception provided by the exception supplier.
     *
     * @param <T>
     *         The type of object to be converted to a String.
     * @param <X>
     *         The type of RuntimeException to be thrown if the object is null.
     * @param converter
     *         The FunctionalToStringConverter to use for conversion.
     * @param exceptionSupplier
     *         Supplier of the exception to be thrown when the object is null.
     *
     * @return A StringConverter that uses the provided converter and throws a custom exception for null objects.
     *
     * @throws X
     *         if the object to be converted is null.
     */
    static <T, X extends RuntimeException> StringConverter<T> of(FunctionalToStringConverter<T> converter, Supplier<X> exceptionSupplier) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                return Optional.ofNullable(object)
                               .map(converter::toString)
                               .orElseThrow(exceptionSupplier);
            }

            @Override
            public T fromString(String string) {
                throw new UnsupportedOperationException("FromString conversion not implemented");
            }
        };
    }
}
