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

import javafx.beans.property.*;

import java.util.Objects;

/**
 * Utility class offering methods to inspect and enforce constraints on {@link Property} instances within JavaFX applications.
 * This class is designed to facilitate the management of property states and to ensure that properties are correctly used in
 * accordance with application logic and UI requirements.
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
 *     if (EFXPropertyUtils.checkProperty(myProperty)) {
 *         myProperty.setValue("newValue");
 *     } else {
 *         // Handle the case where the property cannot be modified
 *     }
 *
 *     try {
 *         EFXPropertyUtils.cannotChangeProperty("myReadOnlyProperty", "setMyReadOnlyProperty(String newValue)");
 *     } catch (IllegalArgumentException e) {
 *         System.err.println(e.getMessage());
 *         // Further handling...
 *     }
 * </pre>
 *
 * <p>By centralizing these checks and constraints into utility methods, the {@code EFXPropertyUtils} class
 * aids in maintaining clean, safe, and consistent property management practices throughout JavaFX
 * applications.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Property
 */
public class EFXPropertyUtils {
    /**
     * Private constructor to prevent instantiation of the {@code EFXPropertyUtils} class.
     *
     * <p>
     * This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code EFXInsetUtils}.
     * </p>
     */
    private EFXPropertyUtils() {}

    /**
     * Checks if a given property is non-null and not bound to another property. This method is useful for determining if a
     * property can be safely modified.
     *
     * @param <T>
     *         the type of the {@link Property}
     * @param property
     *         the property to check
     *
     * @return {@code true} if the property is non-null and not bound, {@code false} otherwise
     */
    public static <T extends Property<?>> boolean checkProperty(T property) {
        return Objects.nonNull(property) && !property.isBound();
    }

    /**
     * Throws an {@link IllegalArgumentException} indicating that a property cannot be changed directly and suggesting an
     * alternative method to achieve the desired modification. This is useful for enforcing read-only property access or guiding
     * developers to use proper methods for property changes.
     *
     * @param property
     *         the name of the property that cannot be changed
     * @param function
     *         the name of the function to use instead for modifying the property
     */
    public static void cannotChangeProperty(String property, String function) {
        throw new IllegalArgumentException(String.format("%s cannot be changed. Please use %s instead", property, function));
    }

    /**
     * Converts a generic value into an {@link ObjectProperty}. This method provides a straightforward way to wrap a generic value
     * in a property, allowing it to be observed and bound within the JavaFX property system.
     *
     * @param <T>
     *         The type of the value to be converted.
     * @param var
     *         The value to be wrapped in an {@link ObjectProperty}.
     *
     * @return An {@link ObjectProperty} containing the provided value.
     */
    public static <T> ObjectProperty<T> toObjectProperty(T var) {
        return new SimpleObjectProperty<>(var);
    }

    /**
     * Wraps a {@link String} value in a {@link StringProperty}. This utility method enables the string value to be observed and
     * bound to other properties, facilitating dynamic updates and bindings in JavaFX applications.
     *
     * @param var
     *         The {@link String} value to be wrapped.
     *
     * @return A {@link StringProperty} containing the provided string value.
     */
    public static StringProperty toStringProperty(String var) {
        return new SimpleStringProperty(var);
    }

    /**
     * Converts a {@link Boolean} value into a {@link BooleanProperty}. This method allows boolean values to participate in the
     * JavaFX property system, supporting observable changes and property bindings.
     *
     * @param var
     *         The {@link Boolean} value to be converted.
     *
     * @return A {@link BooleanProperty} representing the given boolean value.
     */
    public static BooleanProperty toBooleanProperty(Boolean var) {
        return new SimpleBooleanProperty(var);
    }

    /**
     * Wraps an {@link Integer} value in an {@link IntegerProperty}. By converting the integer to a property, it can be observed
     * for changes and bound to other properties, enabling reactive programming patterns in JavaFX.
     *
     * @param var
     *         The {@link Integer} value to be wrapped.
     *
     * @return An {@link IntegerProperty} containing the provided integer value.
     */
    public static IntegerProperty toIntegerProperty(Integer var) {
        return new SimpleIntegerProperty(var);
    }

    /**
     * Converts a {@link Float} value into a {@link FloatProperty}. This utility method facilitates the integration of float
     * values into the JavaFX property system, allowing them to be observed and bound as part of the UI logic.
     *
     * @param var
     *         The {@link Float} value to be converted.
     *
     * @return A {@link FloatProperty} representing the given float value.
     */
    public static FloatProperty toFloatProperty(Float var) {
        return new SimpleFloatProperty(var);
    }

    /**
     * Wraps a {@link Double} value in a {@link DoubleProperty}. This conversion enables double values to be used within the
     * JavaFX property system, supporting dynamic UI updates and property bindings based on the value.
     *
     * @param var
     *         The {@link Double} value to be wrapped.
     *
     * @return A {@link DoubleProperty} containing the provided double value.
     */
    public static DoubleProperty toDoubleProperty(Double var) {
        return new SimpleDoubleProperty(var);
    }
}
