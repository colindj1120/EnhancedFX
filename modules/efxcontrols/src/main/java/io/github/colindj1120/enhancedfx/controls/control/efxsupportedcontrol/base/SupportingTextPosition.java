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
package io.github.colindj1120.enhancedfx.controls.control.efxsupportedcontrol.base;

/**
 * Enumerates the possible positions for displaying supporting text relative to a control within the EnhancedFX UI framework. This enumeration allows developers to specify where additional descriptive text
 * should be placed in relation to the main UI element, such as labels or instructions adjacent to input fields.
 *
 * <p>
 * The placement of supporting text is critical for creating intuitive and user-friendly interfaces, guiding users effectively and improving the overall usability and accessibility of the application. This enum
 * provides a straightforward way to standardize the positioning of such text across different components.
 * </p>
 *
 * <p>
 * <h2>Positions Defined:</h2>
 * <ul>
 *     <li>{@code TOP} - Places the supporting text above the control. Ideal for contexts where vertical space is available and the supporting text serves as a header or title.</li>
 *     <li>{@code BOTTOM} - Places the supporting text below the control. Commonly used for additional instructions or information following user input.</li>
 *     <li>{@code LEFT} - Places the supporting text to the left of the control. Suitable for layouts with horizontal space, helping to maintain a compact form factor.</li>
 *     <li>{@code RIGHT} - Places the supporting text to the right of the control. Useful for annotations or brief tips related to the control's functionality.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Utility Methods:</h2>
 * <ul>
 *     <li>{@code toString} - Returns the enum constant's name as declared, ensuring a precise match with the source code declaration.</li>
 *     <li>{@code toLowerCase} - Provides a lowercase version of the enum constant's name, facilitating case-insensitive comparisons and displays.</li>
 *     <li>{@code toTitleCase} - Transforms the enum constant's name into title case for improved readability, particularly suitable for user interfaces and documentation.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Usage of {@code SupportingTextPosition} enhances the flexibility and consistency of layout design, allowing for easy adjustments to the spatial relationship between controls and their descriptive texts.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum SupportingTextPosition {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT;

    /**
     * Constructor for the {@code SupportingTextPosition} enum.
     */
    SupportingTextPosition() {}

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
