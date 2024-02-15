/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.materialdesignui.utils;

import javafx.beans.property.Property;

import java.util.Objects;

/**
 * Utility class offering methods to inspect and enforce constraints on {@link Property} instances within
 * JavaFX applications. This class is designed to facilitate the management of property states and to
 * ensure that properties are correctly used in accordance with application logic and UI requirements.
 *
 * <p>Key functionalities include:</p>
 * <ul>
 *     <li>Checking the validity and binding state of a property to ensure it is suitable for modification.</li>
 *     <li>Generating standardized error messages when illegal attempts are made to change properties that
 *     should not be directly modified.</li>
 * </ul>
 *
 * <p>These utilities are particularly useful in scenarios where properties are exposed to external manipulation,
 * and there is a need to maintain strict control over their state and changes, thereby preventing unintended
 * side effects in the application's behavior and UI presentation.</p>
 *
 * <p>Example Usage:</p>
 * <pre>
 *     SimpleStringProperty myProperty = new SimpleStringProperty("initialValue");
 *     if (PropertyUtils.checkProperty(myProperty)) {
 *         myProperty.setValue("newValue");
 *     } else {
 *         // Handle the case where the property cannot be modified
 *     }
 *
 *     try {
 *         PropertyUtils.cannotChangeProperty("myReadOnlyProperty", "setMyReadOnlyProperty(String newValue)");
 *     } catch (IllegalArgumentException e) {
 *         System.err.println(e.getMessage());
 *         // Further handling...
 *     }
 * </pre>
 *
 * <p>By centralizing these checks and constraints into utility methods, the {@code PropertyUtils} class
 * aids in maintaining clean, safe, and consistent property management practices throughout JavaFX
 * applications.</p>
 */
public class PropertyUtils {
    /**
     * Checks if a given property is non-null and not bound to another property. This method is useful
     * for determining if a property can be safely modified.
     *
     * @param <T> the type of the {@link Property}
     * @param property the property to check
     * @return {@code true} if the property is non-null and not bound, {@code false} otherwise
     */
    public static <T extends Property<?>> boolean checkProperty(T property) {
        return Objects.nonNull(property) && !property.isBound();
    }

    /**
     * Throws an {@link IllegalArgumentException} indicating that a property cannot be changed directly
     * and suggesting an alternative method to achieve the desired modification. This is useful for
     * enforcing read-only property access or guiding developers to use proper methods for property changes.
     *
     * @param property the name of the property that cannot be changed
     * @param function the name of the function to use instead for modifying the property
     */
    public static void cannotChangeProperty(String property, String function) {
        throw new IllegalArgumentException(String.format("%s cannot be changed. Please use %s instead", property, function));
    }
}
