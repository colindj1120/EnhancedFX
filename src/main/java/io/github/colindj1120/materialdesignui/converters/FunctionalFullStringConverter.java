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

import java.util.function.Supplier;

/**
 * Functional interface for creating custom full (both toString and fromString) converters.
 * <p>
 * This interface is intended for use in JavaFX applications where there is a need for custom conversion logic both to and from strings for objects of a particular type. It leverages the functional
 * programming features of Java to provide a flexible and concise way to define conversion behavior in both directions.
 * </p>
 * <p><pre>
 * The interface provides several static methods to create StringConverters:
 * - A basic converter that uses separate FunctionalToStringConverter and FunctionalFromStringConverter.
 * - A converter that uses a default value for null objects in both toString and fromString operations.
 * - A converter that throws a specified runtime exception if the object (for toString) or the string (for fromString) is null or empty.
 * </pre></p>
 * <p>
 * This facilitates integration of custom bi-directional string conversion logic in various JavaFX UI components such as ListView, TableView, and ComboBox, enhancing the flexibility in representing
 * and processing data within the application.
 * </p>
 * <pre>
 * Example Usage:
 * {@code StringConverter<Person> personConverter = FunctionalFullStringConverter.of(
 *      person -> person.getName() + " (" + person.getAge() + " years)",
 *      str -> new Person(str, 30)
 * );
 * }
 * </pre>
 *
 * @param <T>
 *         The type of object for which the string conversion is to be defined.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 *
 * @see FunctionalToStringConverter
 * @see FunctionalFromStringConverter
 */
@FunctionalInterface
public interface FunctionalFullStringConverter<T> {
    /**
     * Returns the StringConverter created using this FunctionalFullStringConverter.
     *
     * @return A StringConverter that incorporates the custom bi-directional conversion logic defined by this FunctionalFullStringConverter.
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
     * Creates a StringConverter using separate converters and a default value for null objects.
     *
     * @param <T>
     *         The type of object for the conversion.
     * @param toStringConverter
     *         A FunctionalToStringConverter for converting objects to strings.
     * @param fromStringConverter
     *         A FunctionalFromStringConverter for converting strings to objects.
     * @param defaultReturn
     *         The default value to return for null inputs.
     *
     * @return A StringConverter that handles null objects using the default value.
     *
     * @see FunctionalToStringConverter
     * @see FunctionalFromStringConverter
     */
    static <T> StringConverter<T> of(FunctionalToStringConverter<T> toStringConverter, FunctionalFromStringConverter<T> fromStringConverter, T defaultReturn) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                return (object != null) ? toStringConverter.toString(object) : defaultReturn.toString();
            }

            @Override
            public T fromString(String string) {
                return (string != null && !string.isEmpty()) ? fromStringConverter.fromString(string) : defaultReturn;
            }
        };
    }

    /**
     * Creates a StringConverter that can throw a runtime exception for null inputs.
     *
     * @param <T>
     *         The type of object for the conversion.
     * @param <X>
     *         The type of RuntimeException to be thrown.
     * @param toStringConverter
     *         A FunctionalToStringConverter for converting objects to strings.
     * @param fromStringConverter
     *         A FunctionalFromStringConverter for converting strings to objects.
     * @param exceptionSupplier
     *         Supplier of the exception to be thrown for null inputs.
     *
     * @return A StringConverter that throws a specified exception for null inputs.
     *
     * @throws X
     *         A runtime exception if the input object or string is null.
     *
     * @see FunctionalToStringConverter
     * @see FunctionalFromStringConverter
     */
    static <T, X extends RuntimeException> StringConverter<T> of(FunctionalToStringConverter<T> toStringConverter, FunctionalFromStringConverter<T> fromStringConverter,
                                                                 Supplier<X> exceptionSupplier) {
        return new StringConverter<>() {
            @Override
            public String toString(T object) {
                if (object == null) {
                    throw exceptionSupplier.get();
                }
                return toStringConverter.toString(object);
            }

            @Override
            public T fromString(String string) {
                if (string == null || string.isEmpty()) {
                    throw exceptionSupplier.get();
                }
                return fromStringConverter.fromString(string);
            }
        };
    }
}
