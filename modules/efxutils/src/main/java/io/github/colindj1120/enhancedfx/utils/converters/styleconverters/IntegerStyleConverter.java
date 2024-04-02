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
package io.github.colindj1120.enhancedfx.utils.converters.styleconverters;

import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

import java.util.Arrays;

/**
 * The {@code IntegerStyleConverter} class extends {@link StyleConverter} and is designed to convert string representations of numbers into their {@link Integer} equivalents. This conversion is essential for
 * CSS-like styling in JavaFX where numeric values are often represented as strings. This class implements the singleton pattern to ensure that only a single instance of the converter is ever created, promoting
 * efficient resource use and consistent behavior across the application.
 *
 * <p>The inner {@code Holder} class contains the singleton instances of both {@code IntegerStyleConverter} and its sibling {@code SequenceConverter}, the latter of which is specialized for converting arrays
 * of strings to arrays of {@link Integer} objects. This setup allows for both individual integer values and sequences of integers to be handled with the same level of efficiency and consistency.</p>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 *     ParsedValue<String, Integer> parsedValue = new ParsedValue<>("12", null);
 *     Integer result = IntegerStyleConverter.getInstance().convert(parsedValue, null);
 *     System.out.println("Converted value: " + result); // Outputs: Converted value: 12
 * }
 * </pre>
 *
 * <p>This class is particularly useful in the context of JavaFX styling, where it can be used to parse and convert string-based style specifications into their numerical counterparts, thereby facilitating the
 * application of dynamic styles to UI components.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see StyleConverter
 * @see ParsedValue
 * @see Font
 */
public class IntegerStyleConverter extends StyleConverter<String, Integer> {
    /**
     * This class serves as a converter to transform string representations of numbers into their {@link Integer} equivalents for style values. It is implemented as a singleton to ensure only one instance of
     * this converter exists.
     */
    private static class Holder {
        /**
         * The singleton instance of IntegerStyleConverter.
         */
        static final IntegerStyleConverter INSTANCE = new IntegerStyleConverter();

        /**
         * The singleton instance of SequenceConverter for converting sequences of values.
         */
        static final SequenceConverter SEQUENCE_INSTANCE = new SequenceConverter();
    }

    /**
     * Private constructor to prevent instantiation from outside this class, ensuring the singleton pattern.
     */
    private IntegerStyleConverter() {}

    /**
     * Provides access to the singleton instance of the {@link IntegerStyleConverter}.
     *
     * @return The single instance of {@link IntegerStyleConverter}.
     */
    public static IntegerStyleConverter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Converts a parsed string value into a {@link Integer}. This method attempts to parse the string provided by the {@link ParsedValue} argument. If the string cannot be parsed into a valid integer, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param value
     *         The {@link ParsedValue} containing the string to be converted.
     * @param font
     *         The font currently in use, not used in this converter but required by the interface.
     *
     * @return The converted {@link Integer} value.
     *
     * @throws IllegalArgumentException
     *         If the string cannot be converted into a {@link Integer}.
     */
    @Override
    public Integer convert(ParsedValue<String, Integer> value, Font font) {
        try {
            return Integer.valueOf(value.getValue());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unable to convert value to Integer: " + value.getValue(), e);
        }
    }

    /**
     * Provides a string representation of this {@link IntegerStyleConverter}.
     *
     * @return The simple name of this class, indicating its purpose.
     */
    @Override
    public String toString() {
        return "IntegerStyleConverter";
    }

    /**
     * {@code SequenceConverter} is a specialized {@link StyleConverter} designed to convert an array of parsed values into an array of {@link Integer} objects. This converter is particularly useful for styles
     * that accept a sequence of integer numbers as input. Each string in the input array is individually converted to a {@link Integer} using the {@link IntegerStyleConverter}. This class follows the
     * singleton pattern to ensure that only one instance is created and used throughout the application.
     *
     * <h2>Usage Example:</h2>
     * <pre>
     * {@code
     *     ParsedValue<ParsedValue<String, Integer>[], Integer[]> sequenceValue = new ParsedValue<>(
     *         new ParsedValue[] {
     *             new ParsedValue<>("10", null),
     *             new ParsedValue<>("20", null)
     *         }, null);
     *     Integer[] results = SequenceConverter.getInstance().convert(sequenceValue, null);
     *     System.out.println("Converted values: " + Arrays.toString(results));
     *     // Outputs: Converted values: [10, 20]
     * }
     * </pre>
     *
     * <p>Usage of this converter facilitates the conversion process in styling mechanisms where multiple integer values are needed in sequence, such as in specifying multiple margins or paddings in CSS-like
     * styling languages.</p>
     *
     * @apiNote This class extends {@link StyleConverter}, leveraging generic types to specify the input and output formats required for conversion. The input is expected to be an array of
     *         {@link ParsedValue} objects, each containing a string and its corresponding {@link Integer} representation. The output is an array of {@link Integer} objects.
     */
    public static final class SequenceConverter extends StyleConverter<ParsedValue<String, Integer>[], Integer[]> {
        /**
         * Returns the singleton instance of {@code SequenceConverter}.
         *
         * @return The single instance of {@code SequenceConverter}, ensuring consistent behavior and performance across the application.
         */
        public static IntegerStyleConverter.SequenceConverter getInstance() {
            return IntegerStyleConverter.Holder.SEQUENCE_INSTANCE;
        }

        /**
         * Private constructor to prevent external instantiation. Use {@link #getInstance()} to get the singleton instance of this class.
         */
        private SequenceConverter() {
            super();
        }

        /**
         * Converts an array of {@link ParsedValue} objects into an array of {@link Integer} objects. Each {@link ParsedValue} is individually converted to {@link Integer} using {@link IntegerStyleConverter},
         * ensuring accurate conversion of string representations to integer values.
         *
         * @param value
         *         The {@link ParsedValue} array containing the strings to be converted along with their expected {@link Integer} type.
         * @param font
         *         The font currently in use, not used in this converter but required by the interface.
         *
         * @return An array of {@link Integer} objects corresponding to the converted values.
         */
        @Override
        public Integer[] convert(ParsedValue<ParsedValue<String, Integer>[], Integer[]> value, Font font) {
            return Arrays.stream(value.getValue())
                         .map(parsedValue -> IntegerStyleConverter.getInstance()
                                                                  .convert(parsedValue, font))
                         .toArray(Integer[]::new);
        }

        /**
         * Provides a string representation of this {@code SequenceConverter}.
         *
         * @return A string indicating the specific role of this converter in handling sequences of integer numbers.
         */
        @Override
        public String toString() {
            return "Integer.SequenceConverter";
        }

    }
}
