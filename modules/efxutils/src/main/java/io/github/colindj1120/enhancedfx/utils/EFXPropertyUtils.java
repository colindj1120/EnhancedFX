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
 * Provides utility methods for creating and managing JavaFX properties, facilitating type-safe property creation and conversions within the JavaFX framework.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Property Creation:</b> Simplifies the creation of JavaFX properties from basic data types, enabling easy integration with the JavaFX binding and observable mechanisms.</li>
 *     <li><b>Type Safety and Conversion:</b> Offers methods to convert observable values to specific properties, ensuring type safety and reducing boilerplate code for property binding and manipulation.</li>
 *     <li><b>Read-Only Property Enforcement:</b> Includes utilities for enforcing read-only access to properties, guiding the use of appropriate modification methods and enhancing encapsulation.</li>
 *     <li><b>Custom Error Handling:</b> Provides methods for throwing customized exceptions when illegal property modifications are attempted, improving error feedback in property-bound applications.</li>
 * </ul>
 *
 * <h2>Usage Examples:</h2>
 *
 * <h3>Example 1:</h3>
 * <em>Creating a StringProperty from a String value</em>
 * <pre>
 * {@code
 *     String myString = "Hello, world!";
 *     StringProperty myStringProperty = EFXPropertyUtils.toStringProperty(myString);
 * }
 * </pre>
 *
 * <h3>Example 2:</h3>
 * <em>Binding an ObservableValue to a new ObjectProperty</em>
 * <pre>
 * {@code
 *     ObservableValue<String> observableString = ...;
 *     ObjectProperty<String> stringProperty = EFXPropertyUtils.observableToObjectProperty(observableString);
 * }
 * </pre>
 *
 * <h3>Example 3:</h3>
 * <em>Using checkProperty to safely modify a property</em>
 * <pre>
 * {@code
 *     if (EFXPropertyUtils.checkProperty(myProperty)) {
 *         myProperty.set(newValue);
 *     } else {
 *         System.out.println("Property is bound and cannot be directly modified.");
 *     }
 * }
 * </pre>
 *
 * <h3>Example 4:</h3>
 * <em>Enforcing read-only access by throwing an exception when an attempt is made to change a property</em>
 * <pre>
 * {@code
 *     public void setMyProperty(String newValue) {
 *         EFXPropertyUtils.cannotChangeProperty("myProperty", "use setMyPropertyMethod instead");
 *     }
 * }
 * </pre>
 *
 * <p>The {@code EFXPropertyUtils} class is an essential toolset for developing robust JavaFX applications, offering a streamlined approach to property management and enhancing the readability and
 * maintainability of property-related code.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Property
 * @see SimpleObjectProperty
 * @see SimpleStringProperty
 * @see SimpleBooleanProperty
 * @see SimpleIntegerProperty
 * @see SimpleLongProperty
 * @see SimpleDoubleProperty
 * @see SimpleFloatProperty
 */
public class EFXPropertyUtils {
    /**
     * Private constructor to prevent instantiation of the {@code EFXPropertyUtils} class.
     *
     * <p>This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code EFXInsetUtils}.</p>
     */
    private EFXPropertyUtils() {}

    /**
     * Checks if a given property is non-null and not bound to another property. This method is useful for determining if a property can be safely modified.
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
     * Throws an {@link IllegalArgumentException} indicating that a property cannot be changed directly and suggesting an alternative method to achieve the desired modification.
     *
     * <p>This is useful for enforcing read-only property access or guiding developers to use proper methods for property changes.</p>
     *
     * @param property
     *         the name of the property that cannot be changed
     * @param function
     *         the name of the function to use instead for modifying the property
     */
    public static void cannotChangeProperty(String property, String function) {
        throw new IllegalArgumentException(String.format("%s cannot be changed. Please use %s instead", property, function));
    }

    //region Object Property Functions
    //*****************************************************************
    // Object Property Functions
    //*****************************************************************

    /**
     * Converts a generic value into an {@link ObjectProperty}.
     *
     * <p>This method provides a straightforward way to wrap a generic value in a property, allowing it to be observed and bound within the JavaFX property system.</p>
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
     * Converts a generic value into an {@link ObjectProperty}.
     *
     * <p>This method provides a straightforward way to wrap a generic value in a property, allowing it to be observed and bound within the JavaFX property system.</p>
     *
     * @param <T>
     *         The type of the value to be converted.
     * @param bean
     *         The bean on which to wrap the property.
     * @param name
     *         The name of the property.
     * @param var
     *         The value to be wrapped in an {@link ObjectProperty}.
     *
     * @return An {@link ObjectProperty} containing the provided value.
     */
    public static <T> ObjectProperty<T> toObjectProperty(Object bean, String name, T var) {
        return new SimpleObjectProperty<>(bean, name, var);
    }

    //endregion Object Property Functions

    //region String Property Functions
    //*****************************************************************
    // String Property Functions
    //*****************************************************************

    /**
     * Wraps a {@link String} value in a {@link StringProperty}.
     *
     * <p>This utility method enables the string value to be observed and bound to other properties, facilitating dynamic updates and bindings in JavaFX applications.</p>
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
     * Converts a string variable into a {@link StringProperty}.
     *
     * <p>This utility method enables a string value to be observed and bound to other properties, facilitating dynamic updates and bindings in JavaFX applications.</p>
     *
     * @param bean
     *         The bean on which to wrap the property.
     * @param name
     *         The name of the property.
     * @param var
     *         The string variable to be wrapped.
     *
     * @return A {@link StringProperty} containing the provided string value.
     */
    public static StringProperty toStringProperty(Object bean, String name, String var) {
        return new SimpleStringProperty(bean, name, var);
    }

    //endregion String Property Functions

    //region Boolean Property Functions
    //*****************************************************************
    // Boolean Property Functions
    //*****************************************************************

    /**
     * Converts a {@link Boolean} value into a {@link BooleanProperty}. This method allows boolean values to participate in the JavaFX property system, supporting observable changes and property bindings.
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
     * Converts a {@link Boolean} value into a {@link BooleanProperty}. This method allows boolean values to participate in the JavaFX property system, supporting observable changes and property bindings.
     *
     * @param bean
     *         The bean on which to wrap the property.
     * @param name
     *         The name of the property.
     * @param var
     *         The {@link Boolean} value to be converted.
     *
     * @return A {@link BooleanProperty} representing the given boolean value.
     */
    public static BooleanProperty toBooleanProperty(Object bean, String name, Boolean var) {
        return new SimpleBooleanProperty(bean, name, var);
    }

    //endregion Boolean Property Functions

    //region Integer Property Functions
    //*****************************************************************
    // Integer Property Functions
    //*****************************************************************

    /**
     * Wraps an {@link Integer} value in an {@link IntegerProperty}.
     *
     * <p>By converting the integer to a property, it can be observed for changes and bound to other properties, enabling reactive programming patterns in JavaFX.</p>
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
     * Wraps an {@link Integer} value in an {@link IntegerProperty}.
     *
     * <p>By converting the integer to a property, it can be observed for changes and bound to other properties, enabling reactive programming patterns in JavaFX.</p>
     *
     * @param bean
     *         The bean on which to wrap the property.
     * @param name
     *         The name of the property.
     * @param var
     *         The {@link Integer} value to be wrapped.
     *
     * @return An {@link IntegerProperty} containing the provided integer value.
     */
    public static IntegerProperty toIntegerProperty(Object bean, String name, Integer var) {
        return new SimpleIntegerProperty(bean, name, var);
    }

    //endregion Integer Property Functions

    //region Long Property Functions
    //*****************************************************************
    // Long Property Functions
    //*****************************************************************

    /**
     * Wraps an {@link Long} value in an {@link LongProperty}.
     *
     * <p>By converting the long to a property, it can be observed for changes and bound to other properties, enabling reactive programming patterns in JavaFX.</p>
     *
     * @param var
     *         The {@link Long} value to be wrapped.
     *
     * @return An {@link LongProperty} containing the provided long value.
     */
    public static LongProperty toLongProperty(Long var) {
        return new SimpleLongProperty(var);
    }

    /**
     * Wraps an {@link Long} value in an {@link LongProperty}.
     *
     * <p>By converting the long to a property, it can be observed for changes and bound to other properties, enabling reactive programming patterns in JavaFX.</p>
     *
     * @param bean
     *         The bean on which to wrap the property.
     * @param name
     *         The name of the property.
     * @param var
     *         The {@link Long} value to be wrapped.
     *
     * @return An {@link LongProperty} containing the provided long value.
     */
    public static LongProperty toLongProperty(Object bean, String name, Long var) {
        return new SimpleLongProperty(bean, name, var);
    }

    //endregion Long Property Functions

    //region Double Property Changes
    //*****************************************************************
    // Double Property Changes
    //*****************************************************************

    /**
     * Wraps a {@link Double} value in a {@link DoubleProperty}.
     *
     * <p>This conversion enables double values to be used within the JavaFX property system, supporting dynamic UI updates and property bindings based on the value.</p>
     *
     * @param var
     *         The {@link Double} value to be wrapped.
     *
     * @return A {@link DoubleProperty} containing the provided double value.
     */
    public static DoubleProperty toDoubleProperty(Double var) {
        return new SimpleDoubleProperty(var);
    }

    /**
     * Wraps a {@link Double} value in a {@link DoubleProperty}.
     *
     * <p>This conversion enables double values to be used within the JavaFX property system, supporting dynamic UI updates and property bindings based on the value.</p>
     *
     * @param bean
     *         The bean on which to wrap the property.
     * @param name
     *         The name of the property.
     * @param var
     *         The {@link Double} value to be wrapped.
     *
     * @return A {@link DoubleProperty} containing the provided double value.
     */
    public static DoubleProperty toDoubleProperty(Object bean, String name, Double var) {
        return new SimpleDoubleProperty(var);
    }

    //endregion Double Property Changes

    //region Float Property Changes
    //*****************************************************************
    // Float Property Changes
    //*****************************************************************

    /**
     * Converts a {@link Float} value into a {@link FloatProperty}.
     *
     * <p>This utility method facilitates the integration of float values into the JavaFX property system, allowing them to be observed and bound as part of the UI logic.</p>
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
     * Converts a {@link Float} value into a {@link FloatProperty}.
     *
     * <p>This utility method facilitates the integration of float values into the JavaFX property system, allowing them to be observed and bound as part of the UI logic.</p>
     *
     * @param bean
     *         The bean on which to wrap the property.
     * @param name
     *         The name of the property.
     * @param var
     *         The {@link Float} value to be converted.
     *
     * @return A {@link FloatProperty} representing the given float value.
     */
    public static FloatProperty toFloatProperty(Object bean, String name, Float var) {
        return new SimpleFloatProperty(bean, name, var);
    }

    //endregion Float Property Changes
}
