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

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Provides a functional interface to simplify the creation of {@link StringConverter} instances with custom conversion logic for JavaFX properties and controls, using lambda expressions or method references.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Custom Conversion Logic:</b> Enables the definition of bidirectional conversion logic between objects of type {@code T} and strings, allowing for custom textual representation and parsing
 *     strategies for UI elements.</li>
 *     <li><b>Flexible Error Handling:</b> Supports specifying custom runtime exceptions for handling conversion errors, facilitating robust error management in UI applications.</li>
 *     <li><b>Default Values:</b> Allows specifying default return values for conversions, enhancing usability by providing fallback options for invalid inputs.</li>
 *     <li><b>Functional Interface:</b> Leverages functional programming concepts, enabling concise and readable code by using lambda expressions or method references.</li>
 * </ul>
 *
 * <h2>Usage Examples:</h2>
 *
 * <h3>Create a {@code StringConverter} for a {@code LocalDate} property  in a {@code DatePicker} control, with custom parsing and formatting logic</h3>
 * <pre>
 * {@code
 *     DatePicker datePicker = new DatePicker();
 *     StringConverter<LocalDate> converter = FunctionalFullStringConverter.of(
 *         localDate -> localDate.format(DateTimeFormatter.ISO_LOCAL_DATE), // Custom toString logic
 *         string -> LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE), // Custom fromString logic
 *         LocalDate.now() // Default value for invalid input
 *     );
 *     datePicker.setConverter(converter);
 * }
 * </pre>
 *
 * <h3>Custom Error Handling:</h3>
 * <em>Example showing how to throw a custom exception for invalid conversions.</em>
 * <pre>
 * {@code
 *     StringConverter<Integer> converter = FunctionalFullStringConverter.of(
 *         Object::toString, // Use Object's toString method for conversion to string
 *         Integer::valueOf, // Use Integer's valueOf method for conversion from string
 *         () -> new IllegalArgumentException("Invalid input for conversion.") // Custom exception supplier
 *     );
 * }
 * </pre>
 *
 * <p>This interface and its static methods offer a flexible and powerful way to define {@code StringConverter}s for use in JavaFX property bindings and UI controls, enhancing the application's interactivity
 * and user experience.</p>
 *
 * @param <T>
 *         the type parameter for the objects being converted to and from strings
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see StringConverter
 * @see FunctionalToStringConverter
 * @see FunctionalFromStringConverter
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
