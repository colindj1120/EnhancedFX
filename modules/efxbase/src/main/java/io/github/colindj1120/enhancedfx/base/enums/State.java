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
package io.github.colindj1120.enhancedfx.base.enums;

/**
 * Represents the operational states of UI components within the EnhancedFX framework, distinguishing between active (enabled) and inactive (disabled) states.
 *
 * <p>
 * The {@code State} enum is crucial for managing the interactivity of UI components, allowing developers to easily toggle between states that permit user interaction and those that do not. This capability is
 * essential for creating dynamic and responsive user interfaces that can adapt to various conditions, such as user permissions, application context, or input validation.
 * </p>
 *
 * <p>
 * <h2>States:</h2>
 * <ul>
 *     <li>{@code ENABLED} - Indicates that the UI component is active and can interact with the user. Components in this state are fully functional and respond to user inputs.</li>
 *     <li>{@code DISABLED} - Indicates that the UI component is inactive and cannot interact with the user. Components in this state do not respond to user inputs and are often visually distinct to
 *     communicate their inactivity.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Utility Methods:</h2>
 * <ul>
 *     <li>{@code toString} - Returns the enum constant's name as declared, aiding in logging and debugging where a straightforward string representation is beneficial.</li>
 *     <li>{@code toLowerCase} - Converts the enum constant's name to lowercase, accommodating case-insensitive usage scenarios, such as in CSS class names or system integrations.</li>
 *     <li>{@code toTitleCase} - Enhances the enum constant's name readability by capitalizing only the first letter, ideal for display in user interfaces or documentation.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Employing {@code State} enables developers to succinctly manage and reflect the status of UI components across the EnhancedFX application, ensuring a coherent and accessible user experience.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum State {
    ENABLED,
    DISABLED;

    /**
     * Constructor for the {@code State} enum.
     */
    State() {}

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
