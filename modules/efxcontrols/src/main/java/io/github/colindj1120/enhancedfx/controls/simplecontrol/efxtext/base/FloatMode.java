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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base;

/**
 * Defines the modes of operation for floating labels within text fields in the EnhancedFX framework. Floating labels provide contextual clues about the text input and can improve the user interface by
 * conserving space and enhancing usability.
 *
 * <p>The {@code FloatMode} enum specifies how and when the label associated with a text field should behave, particularly in terms of its placement relative to the text field's input area. This behavior is
 * crucial for creating intuitive and visually appealing forms that efficiently guide users through data entry.</p>
 *
 * <h2>Float Modes:</h2>
 * <ul>
 *     <li>{@code DISABLED} - Floating labels are not used. Labels remain static, typically positioned outside the text field. This mode is suitable for forms where space is not a constraint or where
 *     floating labels do not fit the design language.</li>
 *     <li>{@code INSIDE} - The label floats within the text field, moving above the typed text or placeholder when the field is active or filled. This mode maximizes space efficiency while ensuring labels
 *     are always visible to the user.</li>
 *     <li>{@code BORDER} - The label floats to the border of the text field, typically used to indicate an active state without intruding on the input area. This subtle effect is useful for designs
 *     emphasizing minimalism and clean lines.</li>
 *     <li>{@code ABOVE} - The label floats above the text field when the field is active or contains input. This mode is particularly effective in maintaining a clear association between labels and their
 *     respective fields while providing a dynamic interaction cue to users.</li>
 * </ul>
 *
 * <h2>Utility Methods:</h2>
 * <ul>
 *     <li>{@code toString} - Returns the enum constant's declaration name, aiding in logging and debugging processes where a string representation is required.</li>
 *     <li>{@code toLowerCase} - Converts the enum constant's name to lowercase, useful for case-insensitive comparisons or when matching UI style requirements dictate lower case text.</li>
 *     <li>{@code toTitleCase} - Transforms the enum constant's name into title case for enhanced readability, especially beneficial in user-facing texts or documentation.</li>
 * </ul>
 *
 * <p>Usage of {@code FloatMode} allows for customizable and flexible design of text input elements within the EnhancedFX UI framework, catering to diverse design needs and improving form interactivity and
 * aesthetics.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum FloatMode {
    DISABLED,
    INSIDE,
    BORDER,
    ABOVE;

    /**
     * Constructor for the {@code FloatMode} enum.
     */
    FloatMode() {}

    /**
     * Returns the name of the enum constant, as declared in its enum declaration.
     *
     * <p>Overrides the {@code toString} method to provide a direct string representation of the enum constant, facilitating easier logging and debugging. This representation matches the exact declaration name
     * of the constant.</p>
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
     * <p>This method facilitates scenarios where a case-insensitive representation of the enum constant is required, such as for user-friendly displays or when interfacing with systems that do not recognize
     * case-sensitivity.</p>
     *
     * @return A {@link String} representing the enum constant's name in lowercase letters.
     */
    public String toLowerCase() {
        return name().toLowerCase();
    }

    /**
     * Converts the name of the enum constant to title case.
     *
     * <p>This method is useful for presenting the enum constant in a more human-readable form, particularly in user interfaces or reports. In title case, only the first letter of the enum constant's name is
     * capitalized, enhancing readability while maintaining its recognizability.</p>
     *
     * @return A {@link String} representing the enum constant's name in title case, with the first letter capitalized and the rest in lowercase.
     */
    public String toTitleCase() {
        String lowerCase = name().toLowerCase();
        return lowerCase.substring(0, 1)
                        .toUpperCase() + lowerCase.substring(1);
    }
}