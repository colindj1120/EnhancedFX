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
package io.github.colindj1120.enhancedfx.enums.effects;

import io.github.colindj1120.enhancedfx.effects.ripple.RippleEffect;

/**
 * Enumerates the various shapes that can be used for ripple effects in the EnhancedFX UI framework, providing a versatile set of visual feedback options for interactive elements.
 *
 * <p>
 * Ripple effects are visual indicators of interaction with a UI component, such as a button click or a touch event. The shape of the ripple can significantly affect the user's perception and understanding of
 * the interaction. By offering multiple ripple shapes, EnhancedFX enables developers to customize the user experience to fit the design language and functional requirements of their applications.
 * </p>
 *
 * <p>
 * <h2>Shapes available:</h2>
 * <ul>
 *     <li>{@code RECTANGLE} - Produces a rectangular ripple effect, suitable for rectangular UI elements.</li>
 *     <li>{@code UNIFORM_ROUNDED_RECTANGLE} - A rounded rectangle with uniform corner radii, providing as ofter appearance suitable for buttons and cards.</li>
 *     <li>{@code ASYMMETRIC_ROUNDED_RECTANGLE} - A rounded rectangle with asymmetric corner radii, allowing for more creative and dynamic design choices.</li>
 *     <li>{@code CIRCLE} - Generates a circular ripple effect, ideal for circular buttons and icons.</li>
 *     <li>{@code VERTICAL_ELLIPSE} - Creates an elliptical ripple effect that is taller than it is wide, suitable for vertical interactive elements.</li>
 *     <li>{@code HORIZONTAL_ELLIPSE} - Produces an elliptical ripple effect that is wider than it is tall, complementing horizontal interactive elements.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <h2>Utility Methods:</h2>
 * <ul>
 *     <li>{@code toString} - Returns the enum constant's declaration name, aiding in logging and debugging processes where a string representation is required.</li>
 *     <li>{@code toLowerCase} - Converts the enum constant's name to lowercase, facilitating case-insensitive comparisons and displays.</li>
 *     <li>{@code toTitleCase} - Transforms the enum constant's name into title case for enhanced readability, especially beneficial in user interfaces and documentation.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This enumeration plays a crucial role in the EnhancedFX framework, allowing for the customization of ripple effects to enhance user interaction and visual feedback across a wide range of UI components.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RippleEffect
 */
public enum RippleShape {
    RECTANGLE,
    UNIFORM_ROUNDED_RECTANGLE,
    ASYMMETRIC_ROUNDED_RECTANGLE,
    CIRCLE,
    VERTICAL_ELLIPSE,
    HORIZONTAL_ELLIPSE;

    /**
     *
     */
    RippleShape() {}

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

