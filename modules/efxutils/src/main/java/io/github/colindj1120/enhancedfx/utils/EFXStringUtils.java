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
package io.github.colindj1120.enhancedfx.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * EFXStringUtils is a utility class providing a collection of static methods designed for various string manipulation tasks, particularly focused on formatting and indentation for enhanced readability in text
 * and code.
 *
 * <p>This class is not meant to be instantiated, as demonstrated by its private constructor, and serves as a centralized resource for common string operations.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Indentation Adjustment:</b> Offers methods to adjust the indentation level of text, making it simpler to format code or text with varying levels of indentation.</li>
 *     <li><b>Flexible Indentation Levels:</b> Through the use of an enumeration, {@link IndentationLevel}, it provides predefined indentation levels, allowing for consistent application of spaces across
 *         different text blocks.</li>
 *     <li><b>Enhanced Readability:</b> The utility methods aim to improve the readability of text and code by systematically applying formatting rules.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <em>Below is an example demonstrating how to use {@link EFXStringUtils} to add indentation to a multi-line string.</em>
 * <pre>
 * {@code
 *     String sampleText = "First line\nSecond line\nThird line";
 *     String indentedText = EFXStringUtils.addSpacesToEveryLine(sampleText, EFXStringUtils.IndentationLevel.LEVEL_2);
 *     System.out.println(indentedText);
 * }
 * </pre>
 * <em>Output:</em>
 * <pre>
 *         First line
 *         Second line
 *         Third line
 * </pre>
 *
 * <p>This example takes a simple string with multiple lines and applies LEVEL_2 indentation, resulting in each line being prefixed with 8 spaces.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public class EFXStringUtils {
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private EFXStringUtils() {}

    /**
     * Enumeration defining various levels of indentation, represented as the number of spaces.
     *
     * <p>Each level explicitly defines the number of spaces to be used for that indentation level, allowing for consistent and configurable text formatting across different use cases.</p>
     */
    public enum IndentationLevel {
        LEVEL_0(0),
        LEVEL_1(4),
        LEVEL_2(8),
        LEVEL_3(12),
        LEVEL_4(16),
        LEVEL_5(20),
        LEVEL_6(24),
        LEVEL_7(28),
        LEVEL_8(32),
        LEVEL_9(36);

        private final int numberOfSpaces;

        /**
         * Constructor for the indentation level enum.
         *
         * @param numberOfSpaces
         *         the number of spaces that constitute this indentation level.
         */
        IndentationLevel(int numberOfSpaces) {
            this.numberOfSpaces = numberOfSpaces;
        }

        /**
         * Retrieves the number of spaces that define the current indentation level.
         *
         * @return the number of spaces for this indentation level.
         */
        public int getNumberOfSpaces() {
            return numberOfSpaces;
        }
    }

    /**
     * Adds a specified number of spaces (as defined by the {@link IndentationLevel}) to the beginning of every line in the provided input string.
     *
     * <p>This method is useful for increasing the indentation level of text or code blocks for improved readability.</p>
     *
     * @param input
     *         the string to which indentation will be added. It can contain multiple lines.
     * @param indentationLevel
     *         the {@link IndentationLevel} specifying the number of spaces to add before each line.
     *
     * @return a new string with the specified number of spaces added to the beginning of each line of the original input.
     */
    public static String addSpacesToEveryLine(String input, IndentationLevel indentationLevel) {
        String space = " ".repeat(indentationLevel.getNumberOfSpaces());

        return Arrays.stream(input.split("\\n", -1))
                     .map(line -> space + line)
                     .collect(Collectors.joining("\n"));
    }
}
