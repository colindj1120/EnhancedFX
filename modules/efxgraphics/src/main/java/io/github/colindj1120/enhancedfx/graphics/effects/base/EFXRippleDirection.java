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
package io.github.colindj1120.enhancedfx.graphics.effects.base;

import io.github.colindj1120.enhancedfx.graphics.effects.ripple.EFXRippleEffect;

/**
 * Defines the direction of ripple effects for interactive UI components in the EnhancedFX framework, dictating how ripple animations propagate in response to user interactions.
 *
 * <p>
 * Ripple effects serve as a visual confirmation of user actions, such as clicks or touches, enhancing the user experience by providing immediate feedback. The direction of the ripple effect can significantly
 * influence the user's perception of the interaction, making this enum crucial for designing intuitive and responsive UIs.
 * </p>
 *
 * <p>
 * <h2>Directions:</h2>
 * <ul>
 *     <li>{@code IN} - Specifies that the ripple effect should animate from the boundary towards the center of the UI component. This direction often suggests a converging action, suitable for selections or
 *     activations.</li>
 *     <li>{@code OUT} - Specifies that the ripple effect should animate from the center of the UI component towards its boundaries. This direction can imply expansion or outward interaction, useful for
 *     buttons that trigger the opening of menus, dialogs, or other expanding elements.</li>
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
 * Employing {@code EFXRippleDirection} allows developers to customize the interactive feedback of UI components within the EnhancedFX framework, ensuring a dynamic and engaging user experience.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXRippleEffect
 */
public enum EFXRippleDirection {
    IN,
    OUT;

    EFXRippleDirection() {}

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
