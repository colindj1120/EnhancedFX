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
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.value.ObservableStringValue;

/**
 * Extends {@link ObservableValueFunctions} to provide specialized operations for manipulating and evaluating {@link StringExpression} instances. This interface encompasses a broad range of string-specific
 * functionalities, including concatenation, comparison (both case-sensitive and case-insensitive), length checking, and nullity evaluation, facilitating comprehensive string manipulation within the EnhancedFX
 * framework.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Concatenate string expressions with other objects or strings, supporting dynamic string construction.</li>
 *     <li>Perform case-sensitive and case-insensitive comparisons between string expressions and other observable strings or static string values.</li>
 *     <li>Evaluate string length, and perform checks for empty or non-empty strings, enhancing conditional bindings based on string content.</li>
 *     <li>Check for nullity within string expressions, allowing for safe null checks and conditional bindings based on string presence.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * StringProperty firstName = new SimpleStringProperty("John");
 * StringProperty lastName = new SimpleStringProperty("Doe");
 * StringExpressionFunctions<StringExpression> stringFunctions = () -> firstName;
 *
 * // Concatenation
 * StringExpression fullName = stringFunctions.concat(" ").concat(lastName);
 *
 * // Comparisons
 * BooleanBinding isJohn = stringFunctions.isEqualTo("John");
 * BooleanBinding isNotJohn = stringFunctions.isNotEqualTo("Jane");
 * BooleanBinding isJohnIgnoreCase = stringFunctions.isEqualToIgnoreCase("john");
 *
 * // Length and empty checks
 * IntegerBinding nameLength = stringFunctions.length();
 * BooleanBinding isNameEmpty = stringFunctions.isEmpty();
 *
 * // Null checks
 * BooleanBinding isFirstNameNull = stringFunctions.isNull();
 *
 * // Example output usage
 * System.out.println("Full Name: " + fullName.get());
 * System.out.println("Is John: " + isJohn.get());
 * System.out.println("Is Not John: " + isNotJohn.get());
 * System.out.println("Is John (Ignore Case): " + isJohnIgnoreCase.get());
 * System.out.println("Name Length: " + nameLength.get());
 * System.out.println("Is Name Empty: " + isNameEmpty.get());
 * System.out.println("Is First Name Null: " + isFirstNameNull.get());
 * }</pre>
 *
 * <p>This example showcases the use of {@code StringExpressionFunctions} for string concatenation, comparisons, length and emptiness checks, and nullity evaluation, illustrating the powerful capabilities for
 * string manipulation and evaluation within the EnhancedFX framework.</p>
 *
 * @param <R>
 *         the specific type of {@link StringExpression} being operated on
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableValueFunctions
 * @see StringExpression
 * @see BooleanBinding
 * @see IntegerBinding
 * @see ObservableStringValue
 */
public interface StringExpressionFunctions<R extends StringExpression> extends ObservableValueFunctions<String, R> {
    /**
     * Returns the safe string value of this expression, avoiding null.
     *
     * @return the current value of this expression, or an empty string if the value is null.
     */
    default String getValueSafe() {
        return getObservableValue().getValueSafe();
    }

    /**
     * Concatenates this string expression with another object, returning a new {@link StringExpression}.
     *
     * @param other
     *         the object to concatenate with this string expression
     *
     * @return a new {@link StringExpression} that represents the concatenation of this string with the other object
     */
    default StringExpression concat(Object other) {
        return getObservableValue().concat(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is equal to another {@link ObservableStringValue}.
     *
     * @param other
     *         the {@link ObservableStringValue} to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is equal to the specified observable string value
     */
    default BooleanBinding isEqualTo(final ObservableStringValue other) {
        return getObservableValue().isEqualTo(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is equal to a specified string.
     *
     * @param other
     *         the string to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is equal to the specified string
     */
    default BooleanBinding isEqualTo(final String other) {
        return getObservableValue().isEqualTo(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is not equal to another {@link ObservableStringValue}.
     *
     * @param other
     *         the {@link ObservableStringValue} to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is not equal to the specified observable string value
     */
    default BooleanBinding isNotEqualTo(final ObservableStringValue other) {
        return getObservableValue().isNotEqualTo(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is not equal to a specified string.
     *
     * @param other
     *         the string to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is not equal to the specified string
     */
    default BooleanBinding isNotEqualTo(final String other) {
        return getObservableValue().isNotEqualTo(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is equal to another {@link ObservableStringValue}, ignoring case considerations.
     *
     * @param other
     *         the {@link ObservableStringValue} to compare with, ignoring case
     *
     * @return a {@link BooleanBinding} that is true if this string expression is equal to the specified observable string value, ignoring case considerations
     */
    default BooleanBinding isEqualToIgnoreCase(final ObservableStringValue other) {
        return getObservableValue().isEqualToIgnoreCase(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is equal to a specified string, ignoring case considerations.
     *
     * @param other
     *         the string to compare with, ignoring case
     *
     * @return a {@link BooleanBinding} that is true if this string expression is equal to the specified string, ignoring case considerations
     */
    default BooleanBinding isEqualToIgnoreCase(final String other) {
        return getObservableValue().isEqualToIgnoreCase(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is not equal to another {@link ObservableStringValue}, ignoring case considerations.
     *
     * @param other
     *         the {@link ObservableStringValue} to compare with, ignoring case
     *
     * @return a {@link BooleanBinding} that is true if this string expression is not equal to the specified observable string value, ignoring case considerations
     */
    default BooleanBinding isNotEqualToIgnoreCase(final ObservableStringValue other) {
        return getObservableValue().isNotEqualToIgnoreCase(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is not equal to a specified string, ignoring case considerations.
     *
     * @param other
     *         the string to compare with, ignoring case
     *
     * @return a {@link BooleanBinding} that is true if this string expression is not equal to the specified string, ignoring case considerations
     */
    default BooleanBinding isNotEqualToIgnoreCase(final String other) {
        return getObservableValue().isNotEqualToIgnoreCase(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is lexicographically greater than another {@link ObservableStringValue}.
     *
     * @param other
     *         the {@link ObservableStringValue} to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is lexicographically greater than the specified observable string value
     */
    default BooleanBinding greaterThan(final ObservableStringValue other) {
        return getObservableValue().greaterThan(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is lexicographically greater than a specified string.
     *
     * @param other
     *         the string to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is lexicographically greater than the specified string
     */
    default BooleanBinding greaterThan(final String other) {
        return getObservableValue().greaterThan(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is lexicographically less than another {@link ObservableStringValue}.
     *
     * @param other
     *         the {@link ObservableStringValue} to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is lexicographically less than the specified observable string value
     */
    default BooleanBinding lessThan(final ObservableStringValue other) {
        return getObservableValue().lessThan(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is lexicographically less than a specified string.
     *
     * @param other
     *         the string to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is lexicographically less than the specified string
     */
    default BooleanBinding lessThan(final String other) {
        return getObservableValue().lessThan(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is lexicographically greater than or equal to another {@link ObservableStringValue}.
     *
     * @param other
     *         the {@link ObservableStringValue} to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is lexicographically greater than or equal to the specified observable string value
     */
    default BooleanBinding greaterThanOrEqualTo(final ObservableStringValue other) {
        return getObservableValue().greaterThanOrEqualTo(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds true if this string expression is lexicographically greater than or equal to a specified string.
     *
     * @param other
     *         the string to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is lexicographically greater than or equal to the specified string
     */
    default BooleanBinding greaterThanOrEqualTo(final String other) {
        return getObservableValue().greaterThanOrEqualTo(other);
    }

    /**
     * Determines if the string value of this expression is lexicographically less than or equal to another {@link ObservableStringValue}.
     *
     * @param other
     *         the {@link ObservableStringValue} to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is less than or equal to the specified observable string value
     */
    default BooleanBinding lessThanOrEqualTo(final ObservableStringValue other) {
        return getObservableValue().lessThanOrEqualTo(other);
    }

    /**
     * Determines if the string value of this expression is lexicographically less than or equal to a specified string.
     *
     * @param other
     *         the string to compare with
     *
     * @return a {@link BooleanBinding} that is true if this string expression is less than or equal to the specified string
     */
    default BooleanBinding lessThanOrEqualTo(final String other) {
        return getObservableValue().lessThanOrEqualTo(other);
    }

    /**
     * Determines if the value of this string expression is null.
     *
     * @return a {@link BooleanBinding} that is true if the value of this expression is null
     */
    default BooleanBinding isNull() {
        return getObservableValue().isNull();
    }

    /**
     * Determines if the value of this string expression is not null.
     *
     * @return a {@link BooleanBinding} that is true if the value of this expression is not null
     */
    default BooleanBinding isNotNull() {
        return getObservableValue().isNotNull();
    }

    /**
     * Returns a binding to the length of the string value of this expression.
     *
     * @return an {@link IntegerBinding} that holds the length of the string value of this expression
     */
    default IntegerBinding length() {
        return getObservableValue().length();
    }

    /**
     * Determines if the string value of this expression is empty.
     *
     * @return a {@link BooleanBinding} that is true if the string value of this expression is empty
     */
    default BooleanBinding isEmpty() {
        return getObservableValue().isEmpty();
    }

    /**
     * Determines if the string value of this expression is not empty.
     *
     * @return a {@link BooleanBinding} that is true if the string value of this expression is not empty
     */
    default BooleanBinding isNotEmpty() {
        return getObservableValue().isNotEmpty();
    }

}
