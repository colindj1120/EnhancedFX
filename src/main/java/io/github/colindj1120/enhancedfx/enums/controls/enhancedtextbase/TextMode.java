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
package io.github.colindj1120.enhancedfx.enums.controls.enhancedtextbase;

/**
 * Defines the visual styling modes for text elements within the application, offering a mechanism to switch between different presentation styles for text-based UI components.
 *
 * <p>
 * `TextMode` is utilized to specify how text elements should be displayed, impacting their aesthetic and functional roles within the user interface. It supports the application's flexibility in UI design,
 * allowing for dynamic adjustments to the presentation of text according to context or user preference.
 * </p>
 *
 * <p>
 * The enum provides several utility methods to work with the mode names, facilitating their use in logging, user interfaces, or any context where a string representation is required. These methods include
 * converting the name to a standard, lowercase, or title case format.
 * </p>
 *
 * <p>
 * <h2>Enum Constants:</h2>
 * <ul>
 *     <li>{@code FILLED} - Represents a mode where text elements are displayed with a solid background. This mode is typically used to highlight active or primary text fields.</li>
 *     <li>{@code OUTLINED} - Represents a mode where text elements are displayed with an outline and no background fill. This mode is used for a more subtle presentation, often for secondary text
 *     elements.</li>
 * </ul>
 *</p>
 *
 * <p>
 * <h2>Utility Methods:</h2>
 * <ul>
 *     <li>{@code toString} - Returns the name of the enum constant as declared.</li>
 *     <li>{@code toLowerCase} - Converts the enum constant's name to lowercase.</li>
 *     <li>{@code toTitleCase} - Converts the enum constant's name to title case, capitalizing only the first letter.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This enumeration is integral to the flexible design of the application's user interface, allowing for consistent yet adaptable text presentation.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum TextMode {
    FILLED,
    OUTLINED;

    /**
     * Constructor for the {@code TextMode} enum.
     */
    TextMode() {}

    /**
     * Returns the name of the enum constant, as declared in its enum declaration.
     *
     * <p>
     * Overrides the {@code toString} method to provide a direct string representation of the enum constant, facilitating easier logging and debugging. This representation matches the exact declaration name of
     * the constant.
     * </p>
     *
     * @return A {@link String} representing the name of the enum constant.
     */
    @Override
    public String toString() {
        return name();
    }

    /**
     * Converts the name of the enum constant to lowercase.
     *
     * <p>
     * This method facilitates scenarios where a case-insensitive representation of the enum constant is required, such as for user-friendly displays or when interfacing with systems that do not recognize case
     * sensitivity.
     * </p>
     *
     * @return A {@link String} representing the enum constant's name in lowercase letters.
     */
    public String toLowerCase() {
        return name().toLowerCase();
    }

    /**
     * Converts the name of the enum constant to title case.
     *
     * <p>
     * This method is useful for presenting the enum constant in a more human-readable form, particularly in user interfaces or reports. In title case, only the first letter of the enum constant's name is
     * capitalized, enhancing readability while maintaining its recognizability.
     * </p>
     *
     * @return A {@link String} representing the enum constant's name in title case, with the first letter capitalized and the rest in lowercase.
     */
    public String toTitleCase() {
        String lowerCase = name().toLowerCase();
        return lowerCase.substring(0, 1)
                        .toUpperCase() + lowerCase.substring(1);
    }
}
