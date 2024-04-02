/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/MaterialDesignUI).
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
package io.github.colindj1120.enhancedfx.utils.converters.stringconverters;

import javafx.util.StringConverter;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Provides a mechanism for defining custom {@code toString} conversion logic for JavaFX UI components, enabling the conversion of objects to strings in a concise and flexible manner. This interface is
 * particularly useful for integrating custom object representations into JavaFX components, such as ListView, ComboBox, or any other control requiring a specific string format.
 *
 * <p>The {@code FunctionalToStringConverter} interface simplifies the creation of {@link StringConverter} instances by allowing developers to specify how objects of type {@code T} should be represented as
 * strings. It supports scenarios where a straightforward string representation is required, as well as more complex cases where handling null objects or incorporating custom exception handling is
 * necessary.</p>
 *
 * <h2>Core Features:</h2>
 * <ul>
 *     <li>Simple lambda expression or method reference-based conversion definition.</li>
 *     <li>Optional handling for null objects, either by returning a default string or throwing a custom exception.</li>
 *     <li>Integration ease with JavaFX property and UI component models.</li>
 * </ul>
 *
 * <h2>Usage Examples:</h2>
 * {@code FunctionalToStringConverter} can be used in various JavaFX application development scenarios where custom string representations are needed:
 * <pre>
 * {@code
 *      // Convert Person objects to string using their names
 *      StringConverter<Person> personNameConverter = FunctionalToStringConverter.of(Person::getName);
 *
 *      // Convert LocalDate objects to string with custom formatting and null handling
 *      StringConverter<LocalDate> localDateConverter = FunctionalToStringConverter.of(date -> date.format(DateTimeFormatter.ISO_LOCAL_DATE), "No date provided");
 *
 *      // Convert Integer objects to hexadecimal string representation, throwing an exception for null values
 *      StringConverter<Integer> hexConverter = FunctionalToStringConverter.of(Integer::toHexString, () -> new IllegalArgumentException("Cannot convert null to hex string"));
 * }
 * </pre>
 *
 * <p>This interface empowers developers to implement dynamic and context-sensitive string representations for objects in JavaFX applications, enhancing the UI's interactivity and user experience.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see StringConverter
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
     * Creates a {@link StringConverter} instance that converts objects of type {@code T} to their string representation, returning a specified default string if the object is {@code null}.
     *
     * @param <T>
     *         The type of the object to be converted to a string.
     * @param converter
     *         The {@link FunctionalToStringConverter} to use for the conversion from object to string.
     * @param nullReturn
     *         The string to return when the object to convert is {@code null}.
     *
     * @return A {@link StringConverter} configured to use the given {@code converter} for conversion, with a fallback to {@code nullReturn} when the object is {@code null}.
     */

    static <T> StringConverter<T> of(FunctionalToStringConverter<T> converter, String nullReturn) {
        return createStringConverter(converter, () -> nullReturn);
    }

    /**
     * Creates a {@link StringConverter} instance that converts objects of type {@code T} to their string representation. If the object is {@code null}, it throws a custom exception provided by the
     * {@code exceptionSupplier}.
     *
     * @param <T>
     *         The type of the object to be converted to a string.
     * @param <X>
     *         The type of {@link RuntimeException} to be thrown if the object is {@code null}.
     * @param converter
     *         The {@link FunctionalToStringConverter} to use for the conversion from object to string.
     * @param exceptionSupplier
     *         A {@link Supplier} that provides the custom exception to be thrown when the object to convert is {@code null}.
     *
     * @return A {@link StringConverter} configured to use the given {@code converter} for conversion, with a mechanism to throw a custom exception, supplied by {@code exceptionSupplier}, when the object is
     *         {@code null}.
     *
     * @throws X
     *         when the object to convert is {@code null}.
     */

    static <T, X extends RuntimeException> StringConverter<T> of(FunctionalToStringConverter<T> converter, Supplier<X> exceptionSupplier) {
        return createStringConverter(converter, () -> {throw exceptionSupplier.get();});
    }

    /**
     * Internal helper method to create a {@link StringConverter} based on a {@link FunctionalToStringConverter}. This converter converts objects of type {@code T} to their string representation. It uses a
     * {@code fallbackAction} to either provide a default string value or throw an exception when the object is {@code null}.
     *
     * @param <T>
     *         The type of the object to be converted to a string.
     * @param converter
     *         The {@link FunctionalToStringConverter} used for converting the object to a string.
     * @param fallbackAction
     *         A {@link Supplier} providing either a default string value or throwing an exception, based on the configuration.
     *
     * @return A {@link StringConverter} that uses the specified {@code converter} for object-to-string conversion, with a fallback strategy defined by {@code fallbackAction} for handling {@code null} objects.
     */

    private static <T> StringConverter<T> createStringConverter(FunctionalToStringConverter<T> converter, Supplier<String> fallbackAction) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                return Optional.ofNullable(object)
                               .map(converter::toString)
                               .orElseGet(fallbackAction);
            }

            @Override
            public T fromString(String string) {
                throw new UnsupportedOperationException("FromString conversion not implemented");
            }
        };
    }
}
