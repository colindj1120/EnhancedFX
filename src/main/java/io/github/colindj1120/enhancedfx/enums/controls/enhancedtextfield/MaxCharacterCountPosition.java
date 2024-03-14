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
package io.github.colindj1120.enhancedfx.enums.controls.enhancedtextfield;

/**
 * Specifies the positions where the maximum character count indicator can be displayed in relation to a text field within the EnhancedFX framework. This enum is used by text field controls to configure the
 * visual placement of the character count, enhancing user feedback and interface design.
 *
 * <p>
 * The placement of the character count indicator helps users understand text input limitations more intuitively, making this enum essential for creating user-friendly, interactive forms and input fields.
 * </p>
 *
 * <p>
 * <h2>Enum Values:</h2>
 * <ul>
 *     <li>{@code BELOW} - Places the character count indicator directly below the text field. This is the default position, providing a clear and unobtrusive view of the character limit.</li>
 *     <li>{@code ABOVE} - Places the character count indicator above the text field. This position can be used when the design or space constraints around the text field necessitate an alternative to the
 *     default placement.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Utility Methods:</h2>
 * <ul>
 *     <li>{@code toString} - Returns the name of the enum constant, ensuring consistency with the enum declaration.</li>
 *     <li>{@code toLowerCase} - Converts the enum constant's name to lowercase, facilitating case-insensitive comparisons or display.</li>
 *     <li>{@code toTitleCase} - Converts the enum constant's name to title case, improving readability for presentation in user interfaces or documents.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Usage of {@code MaxCharacterCountPosition} allows for flexible design options in displaying character limits, thereby enhancing the overall user experience in applications utilizing the EnhancedFX text
 * field controls.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum MaxCharacterCountPosition {
    BELOW,
    ABOVE;

    /**
     * Constructor for the {@code MaxCharacterCountPosition} enum.
     */
    MaxCharacterCountPosition() {}

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
