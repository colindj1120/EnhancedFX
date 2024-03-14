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
package io.github.colindj1120.enhancedfx.base.beans.binding.base;

import io.github.colindj1120.enhancedfx.base.beans.binding.base.ObservableValueFunctions;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.value.ObservableStringValue;

/**
 * The {@code StringExpressionFunctions} interface extends {@link ObservableValueFunctions} to provide specialized functionality for
 * dealing with {@link ObservableStringValue}s within JavaFX. This interface enables the creation, manipulation, and observation of
 * string properties, facilitating dynamic and responsive JavaFX application development where string values are bound to UI components
 * or other data models.
 *
 * <p>
 * Through {@code StringExpressionFunctions}, developers gain access to a suite of methods designed for string manipulation and
 * comparison, such as concatenation, equality checks (both case-sensitive and insensitive), and length measurements. This set of
 * utilities enhances the ability to work with text data in a reactive programming model, allowing for the implementation of complex UI
 * logic based on string property changes.
 * </p>
 *
 * <p>
 * <h2>Key functionalities include:</h2>
 * <ul>
 *     <li>Access to the underlying {@link StringBinding} for more sophisticated bindings.</li>
 *     <li>Utility methods for safe value retrieval, ensuring null-safe string operations.</li>
 *     <li>Methods to compare strings for equality or inequality, with support for case sensitivity controls.</li>
 *     <li>Capabilities to assess string properties such as length, emptiness, or presence of content.</li>
 * </ul>
 * This interface is invaluable for developers looking to implement robust string-based property bindings in their JavaFX applications,
 * ensuring UI components remain in sync with the application's state and logic.
 * </p>
 *
 * <p>
 * Utilizing {@code StringExpressionFunctions} streamlines the process of working with string properties, making it easier to create
 * interactive and data-driven JavaFX UIs.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0
 * @see ObservableStringValue
 * @see StringBinding
 * @see BooleanBinding
 * @see IntegerBinding
 */
public interface StringExpressionFunctions extends ObservableValueFunctions<String> {
    /**
     * This method returns a StringBinding.
     *
     * @return the StringBinding.
     */
    StringBinding getBinding();

    /**
     * Retrieves the safe value of the binding.
     *
     * @return The safe value of the binding as a {@code String}. If the binding is null, returns an empty string.
     */
    default String getValueSafe() {
        return getBinding().getValueSafe();
    }

    /**
     * Concatenates another object with the current StringExpression.
     *
     * @param other
     *         the object to be concatenated with the StringExpression
     *
     * @return a new StringExpression which is the result of concatenation
     */
    default StringExpression concat(Object other) {
        return getBinding().concat(other);
    }

    /**
     * Checks if the value of this observable string is equal to the value of the given other observable string. Returns a boolean
     * binding that represents the result of the comparison.
     *
     * @param other
     *         The other observable string to compare with.
     *
     * @return A BooleanBinding that represents the result of the comparison. If the values are equal, it will have the value true;
     *         otherwise, it will have the value false.
     */
    default BooleanBinding isEqualTo(final ObservableStringValue other) {
        return getBinding().isEqualTo(other);
    }

    /**
     * Checks if the value of the StringBinding is equal to the specified String.
     *
     * @param other
     *         the specified String to compare with
     *
     * @return a BooleanBinding that represents whether the value of the StringBinding is equal to the specified String
     */
    default BooleanBinding isEqualTo(final String other) {
        return getBinding().isEqualTo(other);
    }

    /**
     * Checks if the value of this BooleanBinding is not equal to the value of the given ObservableStringValue.
     *
     * @param other
     *         the ObservableStringValue to compare with
     *
     * @return a BooleanBinding that represents the result of the inequality comparison
     */
    default BooleanBinding isNotEqualTo(final ObservableStringValue other) {
        return getBinding().isNotEqualTo(other);
    }

    /**
     * Checks if the current value of the binding is not equal to the given string.
     *
     * @param other
     *         the string to compare the current value with
     *
     * @return a BooleanBinding representing whether the current value is not equal to the given string
     */
    default BooleanBinding isNotEqualTo(final String other) {
        return getBinding().isNotEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the underlying value of this ObservableStringValue is equal to the underlying
     * value of the specified ObservableStringValue, ignoring case sensitivity.
     *
     * @param other
     *         The ObservableStringValue to compare the value with.
     *
     * @return A BooleanBinding that evaluates to true if the underlying values are equal (ignoring case), A BooleanBinding of false
     *         otherwise.
     */
    default BooleanBinding isEqualToIgnoreCase(final ObservableStringValue other) {
        return getBinding().isEqualToIgnoreCase(other);
    }

    /**
     * Checks if the value of this BooleanBinding is equal to the value of another string, ignoring case.
     *
     * @param other
     *         The string to compare with.
     *
     * @return A BooleanBinding that represents whether the value of this BooleanBinding is equal to the value of the other string,
     *         ignoring the case.
     */
    default BooleanBinding isEqualToIgnoreCase(final String other) {
        return getBinding().isEqualToIgnoreCase(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this ObservableStringValue is not equal to the value of the other
     * ObservableStringValue, ignoring case sensitivity.
     *
     * @param other
     *         The other ObservableStringValue to compare with.
     *
     * @return A BooleanBinding representing whether the values are not equal, case-insensitive.
     */
    default BooleanBinding isNotEqualToIgnoreCase(final ObservableStringValue other) {
        return getBinding().isNotEqualToIgnoreCase(other);
    }

    /**
     * Checks if the string value of the binding is not equal to the specified string-ignoring case.
     *
     * @param other
     *         the string to compare to
     *
     * @return a BooleanBinding that represents the result of the comparison
     */
    default BooleanBinding isNotEqualToIgnoreCase(final String other) {
        return getBinding().isNotEqualToIgnoreCase(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this ObservableStringValue is greater than the value of the
     * provided ObservableStringValue 'other'.
     *
     * @param other
     *         The ObservableStringValue to compare against.
     *
     * @return A BooleanBinding that holds a value of true if the value of this ObservableStringValue is greater than the value of
     *         'other', otherwise false.
     */
    default BooleanBinding greaterThan(final ObservableStringValue other) {
        return getBinding().greaterThan(other);
    }

    /**
     * Checks if the value of the current binding is greater than the given string.
     *
     * @param other
     *         the string to compare against
     *
     * @return a BooleanBinding representing the result of the comparison
     */
    default BooleanBinding greaterThan(final String other) {
        return getBinding().greaterThan(other);
    }

    /**
     * Checks if the value of this ObservableStringValue is less than the value of the provided ObservableStringValue.
     *
     * @param other
     *         the ObservableStringValue to compare with
     *
     * @return a BooleanBinding that represents whether this value is less than the provided value
     */
    default BooleanBinding lessThan(final ObservableStringValue other) {
        return getBinding().lessThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this binding is less than the given String.
     *
     * @param other
     *         The String to compare against.
     *
     * @return A BooleanBinding that is true if the value of this binding is less than the given String, A BooleanBinding of false
     *         otherwise.
     */
    default BooleanBinding lessThan(final String other) {
        return getBinding().lessThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of the current ObservableStringValue is greater than or equal to the
     * value of the specified ObservableStringValue.
     *
     * @param other
     *         the other ObservableStringValue to compare against
     *
     * @return a BooleanBinding that is true if the current value is greater than or equal to the other value, and false otherwise
     */
    default BooleanBinding greaterThanOrEqualTo(final ObservableStringValue other) {
        return getBinding().greaterThanOrEqualTo(other);
    }

    /**
     * Checks if the value of this BooleanBinding is greater than or equal to the value of the specified String.
     *
     * @param other
     *         the String value to compare with
     *
     * @return a BooleanBinding that evaluates to true if the value of this BooleanBinding is greater than or equal to the specified
     *         String value, A BooleanBinding of false otherwise
     */
    default BooleanBinding greaterThanOrEqualTo(final String other) {
        return getBinding().greaterThanOrEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this ObservableStringValue is less than or equal to the value of
     * the given ObservableStringValue.
     *
     * @param other
     *         The other ObservableStringValue to compare to.
     *
     * @return A BooleanBinding indicating whether the value of this ObservableStringValue is less than or equal to the value of the
     *         given ObservableStringValue.
     */
    default BooleanBinding lessThanOrEqualTo(final ObservableStringValue other) {
        return getBinding().lessThanOrEqualTo(other);
    }

    /**
     * Checks if the value of the binding is less than or equal to the specified string.
     *
     * @param other
     *         the string to compare against the value of the binding
     *
     * @return a BooleanBinding that represents the result of the comparison
     */
    default BooleanBinding lessThanOrEqualTo(final String other) {
        return getBinding().lessThanOrEqualTo(other);
    }

    /**
     * Checks if the underlying binding is null.
     *
     * @return A BooleanBinding of true if the underlying binding is null, A BooleanBinding of otherwise false.
     */
    default BooleanBinding isNull() {
        return getBinding().isNull();
    }

    /**
     * Returns a BooleanBinding indicating whether the value of the binding is not null.
     *
     * @return a BooleanBinding representing whether the value is not null
     */
    default BooleanBinding isNotNull() {
        return getBinding().isNotNull();
    }

    /**
     * Returns the length of the binding.
     *
     * @return the length of the binding as an IntegerBinding
     */
    default IntegerBinding length() {
        return getBinding().length();
    }

    /**
     * Returns a BooleanBinding indicating whether the binding is empty or not.
     *
     * @return a BooleanBinding indicating whether the binding is empty or not
     */
    default BooleanBinding isEmpty() {
        return getBinding().isEmpty();
    }

    /**
     * Checks if the BooleanBinding is not empty.
     *
     * @return {@code true} if the BooleanBinding is not empty, {@code false} otherwise.
     */
    default BooleanBinding isNotEmpty() {
        return getBinding().isNotEmpty();
    }
}
