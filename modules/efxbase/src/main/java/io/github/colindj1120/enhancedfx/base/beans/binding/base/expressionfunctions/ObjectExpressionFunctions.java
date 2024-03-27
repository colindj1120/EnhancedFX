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
package io.github.colindj1120.enhancedfx.base.beans.binding.base.expressionfunctions;

import io.github.colindj1120.enhancedfx.base.beans.binding.base.observablefunctions.ObservableValueFunctions;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableObjectValue;

import java.util.Locale;

/**
 * Extends {@link ObservableValueFunctions} to offer a rich set of operations for {@link ObjectExpression} instances, providing a robust toolkit for object-oriented binding and expression manipulation within
 * the EnhancedFX framework. This interface facilitates comparisons, nullity checks, and the conversion of object expressions to string representations, thereby broadening the possibilities for dynamic
 * evaluation and manipulation of object properties.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Compare {@link ObjectExpression} instances to other observable object values or static objects, enhancing conditional logic in UI bindings or data processing.</li>
 *     <li>Check for nullity within object expressions, allowing for safe null checks and conditional bindings based on object presence or absence.</li>
 *     <li>Convert object expressions to their string representations, offering customizable string formatting for display or logging purposes.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * ObjectProperty<Person> personProperty = new SimpleObjectProperty<>(new Person("John Doe", 30));
 * ObjectExpressionFunctions<ObjectExpression<Person>, Person> objectFunctions = () -> personProperty;
 *
 * // Comparisons
 * BooleanBinding isJohnDoe = objectFunctions.isEqualTo(new Person("John Doe", 30));
 * BooleanBinding isNotJohnDoe = objectFunctions.isNotEqualTo(new Person("Jane Doe", 25));
 *
 * // Null checks
 * BooleanBinding isNotNull = objectFunctions.isNotNull();
 * BooleanBinding isNull = objectFunctions.isNull();
 *
 * // String conversion
 * StringBinding personAsString = objectFunctions.asString();
 * StringBinding personAsStringWithFormat = objectFunctions.asString("Name: %s, Age: %d");
 *
 * // Example output usage
 * System.out.println("Is John Doe: " + isJohnDoe.get());
 * System.out.println("Is Not John Doe: " + isNotJohnDoe.get());
 * System.out.println("Is Not Null: " + isNotNull.get());
 * System.out.println("Is Null: " + isNull.get());
 * System.out.println("Person as String: " + personAsString.get());
 * System.out.println("Person with Format: " + personAsStringWithFormat.get());
 * }</pre>
 *
 * <p>This example demonstrates how {@code ObjectExpressionFunctions} can be utilized to perform comparisons, check for nullity, and convert object properties to string format, showcasing the interface's
 * utility in facilitating rich object manipulation and evaluation within the EnhancedFX framework.</p>
 *
 * @param <T>
 *         the type of the object held by this {@link ObjectExpression}
 * @param <R>
 *         the specific type of {@link ObjectExpression} being operated on
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableValueFunctions
 * @see ObjectExpression
 * @see BooleanBinding
 * @see StringBinding
 * @see ObservableObjectValue
 */
public interface ObjectExpressionFunctions<T, R extends ObjectExpression<T>> extends ObservableValueFunctions<T, R> {

    /**
     * Checks if this expression is equal to another {@link ObservableObjectValue}.
     *
     * @param other
     *         the {@link ObservableObjectValue} to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other}.
     */
    default BooleanBinding isEqualTo(final ObservableObjectValue<?> other) {
        return getObservableValue().isEqualTo(other);
    }

    /**
     * Checks if this expression is equal to a specified object.
     *
     * @param other
     *         the object to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other}.
     */
    default BooleanBinding isEqualTo(final Object other) {
        return getObservableValue().isEqualTo(other);
    }

    /**
     * Checks if this expression is not equal to another {@link ObservableObjectValue}.
     *
     * @param other
     *         the {@link ObservableObjectValue} to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other}.
     */
    default BooleanBinding isNotEqualTo(final ObservableObjectValue<?> other) {
        return getObservableValue().isNotEqualTo(other);
    }

    /**
     * Checks if this expression is not equal to a specified object.
     *
     * @param other
     *         the object to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other}.
     */
    default BooleanBinding isNotEqualTo(final Object other) {
        return getObservableValue().isNotEqualTo(other);
    }

    /**
     * Checks if the value of this expression is null.
     *
     * @return a {@link BooleanBinding} that represents whether the value of this expression is null.
     */
    default BooleanBinding isNull() {
        return getObservableValue().isNull();
    }

    /**
     * Checks if the value of this expression is not null.
     *
     * @return a {@link BooleanBinding} that represents whether the value of this expression is not null.
     */
    default BooleanBinding isNotNull() {
        return getObservableValue().isNotNull();
    }

    /**
     * Converts the value of this expression to its equivalent string representation.
     *
     * @return a {@link StringBinding} that represents the string form of this expression.
     */
    default StringBinding asString() {
        return getObservableValue().asString();
    }

    /**
     * Converts the value of this expression to its equivalent string representation, using the specified format.
     *
     * @param format
     *         the format to use for the conversion
     *
     * @return a {@link StringBinding} that represents the string form of this expression in the specified format.
     */
    default StringBinding asString(String format) {
        return getObservableValue().asString(format);
    }

    /**
     * Converts the value of this expression to its equivalent string representation, using the specified locale and format.
     *
     * @param locale
     *         the locale to use for the conversion
     * @param format
     *         the format to use for the conversion
     *
     * @return a {@link StringBinding} that represents the string form of this expression in the specified locale and format.
     */
    default StringBinding asString(Locale locale, String format) {
        return getObservableValue().asString(locale, format);
    }
}
