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
package io.github.colindj1120.enhancedfx.utils.converters;

import javafx.util.StringConverter;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Provides a mechanism for creating customizable {@link StringConverter} instances for JavaFX components, facilitating bidirectional conversion between strings and objects of a specified type {@code T}. This
 * interface supports the definition of conversion logic through functional programming constructs, enabling concise and flexible implementations.
 *
 * <p>
 * Implementers can define custom logic for converting objects to strings and vice versa, allowing for integration with UI components that require specific formatting or parsing of textual data. Additionally,
 * utility methods within the interface offer predefined converters for common scenarios, including handling null values and custom exceptions.
 * </p>
 *
 * <h2>Usage Examples</h2>
 * <p>
 * {@code FunctionalFullStringConverter} can be used to easily create converters for JavaFX property bindings, table columns, choice boxes, etc., by providing lambda expressions or method references that match
 * the desired conversion behavior.
 * </p>
 *
 * <p>
 * <h2>Utility Methods</h2>
 * <ul>
 *   <li>{@code of(FunctionalToStringConverter<T>, FunctionalFromStringConverter<T>)} Creates a {@link StringConverter} for simple bidirectional conversion.</li>
 *   <li>{@code of(FunctionalToStringConverter<T>, FunctionalFromStringConverter<T>, T)} Extends the simple converter to include a default return value for conversions from string to object, handling null or
 *   empty strings gracefully.</li>
 *   <li>{@code of(FunctionalToStringConverter<T>, FunctionalFromStringConverter<T>, Supplier<X>)} Offers a converter that throws a custom exception for invalid input, allowing for strict error handling
 *   during the conversion process.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This interface promotes the use of functional programming in JavaFX application development,
 * simplifying the creation of converters that are both compact and expressive.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see StringConverter
 */
@FunctionalInterface
public interface FunctionalFullStringConverter<T> {
    /**
     * Returns the StringConverter created using this FunctionalFullStringConverter.
     *
     * @return A StringConverter that incorporates the custom bidirectional conversion logic defined by this FunctionalFullStringConverter.
     */
    StringConverter<T> getConverter();

    /**
     * Creates a StringConverter using separate toString and fromString converters.
     *
     * @param <T>
     *         The type of object for the conversion.
     * @param toStringConverter
     *         A FunctionalToStringConverter for converting objects to strings.
     * @param fromStringConverter
     *         A FunctionalFromStringConverter for converting strings to objects.
     *
     * @return A StringConverter that uses the provided converters for bi-directional conversion.
     *
     * @see FunctionalToStringConverter
     * @see FunctionalFromStringConverter
     */
    static <T> StringConverter<T> of(FunctionalToStringConverter<T> toStringConverter, FunctionalFromStringConverter<T> fromStringConverter) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                return toStringConverter.toString(object);
            }

            @Override
            public T fromString(String string) {
                return fromStringConverter.fromString(string);
            }
        };
    }

    /**
     * Creates a {@link StringConverter} instance using provided lambda expressions or method references for converting to and from strings, along with a default return value for the {@code fromString} method.
     *
     * <p>
     * This method allows for flexible creation of {@code StringConverter} objects without needing to implement the interface explicitly, simplifying the conversion process for any type {@code T}.
     * </p>
     *
     * @param <T>
     *         The type parameter for the objects being converted to and from strings.
     * @param toStringConverter
     *         A {@link FunctionalToStringConverter} lambda or method reference that defines how objects of type {@code T} are converted to strings.
     * @param fromStringConverter
     *         A {@link FunctionalFromStringConverter} lambda or method reference that defines how strings are converted back to objects of type {@code T}.
     * @param defaultReturn
     *         The default value to return from the {@code fromString} method if the input string is {@code null} or empty.
     *
     * @return A {@code StringConverter<T>} instance configured with the provided converters and default return value.
     */

    static <T> StringConverter<T> of(FunctionalToStringConverter<T> toStringConverter, FunctionalFromStringConverter<T> fromStringConverter, T defaultReturn) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                return Optional.ofNullable(object)
                               .map(toStringConverter::toString)
                               .orElseGet(defaultReturn::toString);
            }

            @Override
            public T fromString(String string) {
                return Optional.ofNullable(string)
                               .filter(s -> !s.isEmpty())
                               .map(fromStringConverter::fromString)
                               .orElse(defaultReturn);
            }
        };
    }

    /**
     * Creates a {@link StringConverter} instance using provided lambda expressions or method references for converting to and from strings. This variant throws a custom runtime exception if conversion attempts
     * fail.
     *
     * <p>
     * This approach is useful when strict error handling is required during the conversion process, allowing callers to specify custom exceptions that should be thrown when a conversion cannot be performed.
     * </p>
     *
     * @param <T>
     *         The type parameter for the objects being converted to and from strings.
     * @param <X>
     *         The type of {@link RuntimeException} to be thrown if conversion fails.
     * @param toStringConverter
     *         A {@link FunctionalToStringConverter} lambda or method reference that defines how objects of type {@code T} are converted to strings. If the object is {@code null}, the method will throw the
     *         exception provided by {@code exceptionSupplier}.
     * @param fromStringConverter
     *         A {@link FunctionalFromStringConverter} lambda or method reference that defines how strings are converted back to objects of type {@code T}. If the string is {@code null} or empty, the method
     *         will throw the exception provided by {@code exceptionSupplier}.
     * @param exceptionSupplier
     *         A {@link Supplier} lambda or method reference that provides an instance of exception type {@code X} to be thrown when conversion attempts fail due to {@code null} or invalid inputs.
     *
     * @return A {@code StringConverter<T>} instance configured with the provided converters and exception handling behavior.
     */

    static <T, X extends RuntimeException> StringConverter<T> of(FunctionalToStringConverter<T> toStringConverter, FunctionalFromStringConverter<T> fromStringConverter,
                                                                 Supplier<X> exceptionSupplier) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                return Optional.ofNullable(object)
                               .map(toStringConverter::toString)
                               .orElseThrow(exceptionSupplier);
            }

            @Override
            public T fromString(String string) {
                return Optional.ofNullable(string)
                               .filter(s -> !s.isEmpty())
                               .map(fromStringConverter::fromString)
                               .orElseThrow(exceptionSupplier);
            }
        };
    }
}
