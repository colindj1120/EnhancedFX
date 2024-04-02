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
package io.github.colindj1120.enhancedfx.utils.converters.stringconverters;

import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Facilitates the creation of customizable {@link StringConverter} instances for converting strings to objects of type {@code T}. This interface is intended to streamline the development of JavaFX applications
 * by providing a concise and flexible means to define how textual data entered into UI components is converted into domain-specific objects.
 *
 * <p>The {@code FunctionalFromStringConverter} interface supports not only straightforward conversions but also allows for handling null or empty input strings by either providing a default value or throwing a
 * custom exception. This capability ensures robust and user-friendly error handling and data validation within JavaFX UI components.</p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Easy definition of conversion logic using lambda expressions or method references.</li>
 *     <li>Support for default values or custom exceptions when dealing with null or empty input strings.</li>
 *     <li>Seamless integration with JavaFX property and UI component models.</li>
 * </ul>
 *
 * <h2>Usage Examples:</h2>
 * {@code FunctionalFromStringConverter} can be effectively used in various JavaFX application scenarios:
 * <pre>
 * {@code
 *     // Converting string to LocalDate with a default value
 *     StringConverter<LocalDate> localDateConverter = FunctionalFromStringConverter.of(LocalDate::parse, LocalDate.now());
 *
 *     // Converting string to Integer and throwing a custom exception for invalid input
 *     StringConverter<Integer> integerConverter = FunctionalFromStringConverter.of(Integer::valueOf, () -> new NumberFormatException("Invalid integer input"));
 * }
 * </pre>
 *
 * <p>By employing {@code FunctionalFromStringConverter}, developers can implement data parsing and conversion logic in a declarative manner, enhancing code readability and maintainability.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see StringConverter
 * @see DefaultStringConverter
 * @see IntegerStringConverter
 */
@FunctionalInterface
public interface FunctionalFromStringConverter<T> {
    /**
     * Converts a given string to an object of type {@code T}.
     *
     * @param string
     *         The string to be converted.
     *
     * @return An object of type {@code T} derived from the input string.
     */
    T fromString(String string);

    /**
     * Creates a {@code StringConverter} that uses the provided {@code FunctionalFromStringConverter} for converting strings back to objects of type {@code T}. This converter does not support {@code toString}
     * conversion.
     *
     * @param <T>
     *         The type of the object to be converted from a string.
     * @param converter
     *         The converter used to convert from a string to an object of type {@code T}.
     *
     * @return A {@code StringConverter} configured with the provided from-string conversion logic.
     */
    static <T> StringConverter<T> of(FunctionalFromStringConverter<T> converter) {
        return new StringConverter<>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public String toString(T object) {
                throw new UnsupportedOperationException("ToString conversion not implemented");
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public T fromString(String string) {
                return converter.fromString(string);
            }
        };
    }

    /**
     * Creates a {@code StringConverter} with a specified default return value for when conversion input is null or empty. This converter does not support {@code toString} conversion.
     *
     * @param <T>
     *         The type of the object to be converted from a string.
     * @param converter
     *         The converter used to convert from a string to an object of type {@code T}.
     * @param defaultReturn
     *         The default value to return if the input string is null or empty.
     *
     * @return A {@code StringConverter} configured with the provided from-string conversion logic and default return value.
     */
    static <T> StringConverter<T> of(FunctionalFromStringConverter<T> converter, T defaultReturn) {
        return createStringConverter(converter, () -> defaultReturn);
    }

    /**
     * Creates a {@code StringConverter} that throws a custom runtime exception if the input string for conversion is null or empty. This converter does not support {@code toString} conversion.
     *
     * @param <T>
     *         The type of the object to be converted from a string.
     * @param <X>
     *         The type of {@code RuntimeException} to be thrown.
     * @param converter
     *         The converter used to convert from a string to an object of type {@code T}.
     * @param exceptionSupplier
     *         A supplier providing the exception to be thrown when the input string is null or empty.
     *
     * @return A {@code StringConverter} configured with the provided from-string conversion logic and exception handling.
     *
     * @throws X
     *         if the string input is null or empty.
     */
    static <T, X extends RuntimeException> StringConverter<T> of(FunctionalFromStringConverter<T> converter, Supplier<X> exceptionSupplier) {
        return createStringConverter(converter, () -> {throw exceptionSupplier.get();});
    }

    /**
     * A private helper method to create a {@code StringConverter} instance, encapsulating the common logic for handling string-to-object conversion, including handling of null or empty inputs via a fallback
     * action.
     *
     * @param <T>
     *         The type of object to be converted from a string.
     * @param converter
     *         The converter to use for the string to object conversion.
     * @param fallbackAction
     *         A {@code Supplier} providing the fallback action, either returning a default value or throwing an exception.
     *
     * @return A configured {@code StringConverter} instance.
     */
    private static <T> StringConverter<T> createStringConverter(FunctionalFromStringConverter<T> converter, Supplier<T> fallbackAction) {
        return new StringConverter<>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public String toString(T object) {
                throw new UnsupportedOperationException("ToString conversion not implemented");
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public T fromString(String string) {
                return Optional.ofNullable(string)
                               .filter(s -> !s.isEmpty())
                               .map(converter::fromString)
                               .orElseGet(fallbackAction);
            }
        };
    }
}
