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
package io.github.colindj1120.enhancedfx.base.collections.base;

/**
 * Enumerates the types of update actions that can be performed on collections within the EnhancedFX framework, serving as a cornerstone for event handling and change notification systems.
 *
 * <p>
 * The {@code UpdateActions} enum provides a comprehensive set of constants representing various operations that can alter the content or structure of a collection, such as adding or removing elements, or
 * replacing content. By categorizing these actions, EnhancedFX facilitates precise and efficient communication of changes between components, enabling reactive programming patterns and enhancing application
 * responsiveness.
 * </p>
 *
 * <p>
 * <h2>Defined Actions:</h2>
 * <ul>
 *     <li>{@code ADDED} - Signifies the addition of a single element to the collection.</li>
 *     <li>{@code BULK_ADD} - Indicates the addition of multiple elements to the collection in a single operation.</li>
 *     <li>{@code REMOVED} - Represents the removal of a single element from the collection.</li>
 *     <li>{@code BULK_REMOVE} - Denotes the removal of multiple elements from the collection in a single operation.</li>
 *     <li>{@code REPLACED} - Implies that an element within the collection has been replaced with another element.</li>
 *     <li>{@code BULK_REPLACED} - Suggests that multiple elements in the collection have been replaced in a single operation.</li>
 *     <li>{@code CLEARED} - Conveys that all elements in the collection have been removed, clearing the collection.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Utility Methods:</h2>
 * <ul>
 *     <li>{@code toString} - Provides the enum constant's declaration name, aiding in logging and debugging by offering a straightforward string representation.</li>
 *     <li>{@code toLowerCase} - Returns the enum constant's name in lowercase, useful for case-insensitive comparisons or conforming to a specific text style in user interfaces.</li>
 *     <li>{@code toTitleCase} - Enhances readability by capitalizing only the first letter of the enum constant's name, ideal for presentations or documentation where title case is preferred.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Implementing the {@code UpdateActions} enum in collection-related event handling systems allows for clear and concise notification of changes, supporting the development of interactive and dynamic user
 * interfaces within the EnhancedFX framework.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum UpdateActions {
    ADDED,
    BULK_ADD,
    REMOVED,
    BULK_REMOVE,
    REPLACED,
    BULK_REPLACED,
    CLEARED;

    /**
     * Constructor for the {@code UpdateActions} enum.
     */
    UpdateActions() {}

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
