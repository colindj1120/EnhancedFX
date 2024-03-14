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
package io.github.colindj1120.enhancedfx.binding.base.numberexpressions;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableNumberValue;

import java.util.Locale;

/**
 * The {@code NumberExpressionFunctions} interface defines a comprehensive suite of default methods for performing arithmetic
 * operations, comparisons, and type conversions on observable number values within a JavaFX application. This interface extends
 * {@link ObservableNumberValueFunctions}, inheriting its basic functionality and building upon it to offer a richer set of operations
 * that are common to numerical computations and UI bindings.
 * <p>
 * Implementations of this interface can provide dynamic and reactive behavior in UI components by allowing properties to be computed
 * based on the current and changing values of other properties or expressions. This is particularly useful in data-binding scenarios
 * where the UI needs to update automatically in response to changes in the underlying data model.
 * </p>
 *
 * <p>
 * <h2>Operations provided by this interface include:</h2>
 * <ul>
 *     <li>Negation</li>
 *     <li>Addition</li>
 *     <li>Subtraction</li>
 *     <li>Multiplication</li>
 *     <li>Division</li>
 *     <li>Equality and Inequality Comparisons</li>
 *     <li>Greater than, Less than, Greater than or equal to, Less than or equal to Comparisons</li>
 *     <li>String Conversions</li>
 * </ul>
 * </p>
 *
 * <p>
 * Each method in this interface returns a binding that automatically updates its value when any of the
 * operands' values change, thus enabling a declarative way of expressing complex UI logic.
 *</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableNumberValue
 * @see NumberBinding
 * @see BooleanBinding
 * @see StringBinding
 */
public interface NumberExpressionFunctions extends ObservableNumberValueFunctions {
    //region Negation
    //*****************************************************************
    // Negation
    //*****************************************************************

    /**
     * Returns a NumberBinding that represents the negation of the current binding value. The resulting binding updates automatically
     * whenever the current binding or its dependencies change.
     *
     * @return a NumberBinding representing the negation of the current binding value
     */
    default NumberBinding negate() {
        return getBinding().negate();
    }

    //endregion Negation

    //region Addition
    //*****************************************************************
    // Addition
    //*****************************************************************

    /**
     * Adds the value of the given ObservableNumberValue to the current binding value.
     *
     * @param other
     *         the ObservableNumberValue to add
     *
     * @return a NumberBinding representing the sum of the current binding value and the value of the other ObservableNumberValue
     */
    default NumberBinding add(final ObservableNumberValue other) {
        return getBinding().add(other);
    }

    /**
     * Adds the specified value to the current binding value.
     *
     * @param other
     *         the value to add to the current binding value
     *
     * @return a NumberBinding representing the result of the addition
     */
    default NumberBinding add(final double other) {
        return getBinding().add(other);
    }

    /**
     * Adds the given float value to the current binding value and returns a NumberBinding representing the result.
     *
     * @param other
     *         the float value to be added to the current binding value
     *
     * @return a NumberBinding representing the result of the addition
     */
    default NumberBinding add(final float other) {
        return getBinding().add(other);
    }

    /**
     * Adds the given {@code other} value to the current binding's value, producing a new {@code NumberBinding} that represents the
     * result.
     *
     * @param other
     *         the value to add to the current binding's value
     *
     * @return a new NumberBinding representing the result of adding the {@code other} value to the current binding's value
     */
    default NumberBinding add(final long other) {
        return getBinding().add(other);
    }

    /**
     * Adds the given integer value to the current binding value and returns a new NumberBinding representing the result.
     *
     * @param other
     *         the integer value to add to the current binding value
     *
     * @return a NumberBinding representing the result of adding the given value to the current binding value
     */
    default NumberBinding add(final int other) {
        return getBinding().add(other);
    }

    //endregion Addition

    //region Subtraction
    //*****************************************************************
    // Subtraction
    //*****************************************************************

    /**
     * Subtracts the value of the given ObservableNumberValue from the current binding.
     *
     * @param other
     *         the ObservableNumberValue to subtract from the current binding
     *
     * @return a new NumberBinding representing the subtraction of the given value from the current binding
     */
    default NumberBinding subtract(final ObservableNumberValue other) {
        return getBinding().subtract(other);
    }

    /**
     * Subtracts the specified double value from the current binding value and returns a NumberBinding representing the result.
     *
     * @param other
     *         the double value to subtract from the current binding value
     *
     * @return a NumberBinding representing the result of the subtraction
     */
    default NumberBinding subtract(final double other) {
        return getBinding().subtract(other);
    }

    /**
     * Subtracts the given float value from the current binding value.
     *
     * @param other
     *         the float value to subtract from the binding value
     *
     * @return a NumberBinding representing the result of the subtraction
     */
    default NumberBinding subtract(final float other) {
        return getBinding().subtract(other);
    }

    /**
     * Subtracts the given long value from the current binding value.
     *
     * @param other
     *         the long value to subtract from the current binding value
     *
     * @return a NumberBinding representing the result of the subtraction
     */
    default NumberBinding subtract(final long other) {
        return getBinding().subtract(other);
    }

    /**
     * Subtracts the specified integer value from the current binding value. Returns a NumberBinding that represents the subtraction of
     * the two values. The resulting binding updates automatically whenever the current binding or its dependencies change.
     *
     * @param other
     *         the integer value to subtract from the current binding value
     *
     * @return a NumberBinding representing the subtraction of the specified value from the current binding value
     */
    default NumberBinding subtract(final int other) {
        return getBinding().subtract(other);
    }

    //endregion Subtraction

    //region Multiplication
    //*****************************************************************
    // Multiplication
    //*****************************************************************

    /**
     * Multiplies the current binding value with another observable number value. The resulting binding represents the multiplication
     * of the two values and will update automatically whenever either the current binding or the other observable number value
     * changes.
     *
     * @param other
     *         the observable number value to multiply with
     *
     * @return a number binding representing the multiplication of the current binding value and the other observable number value
     */
    default NumberBinding multiply(final ObservableNumberValue other) {
        return getBinding().multiply(other);
    }

    /**
     * Returns a NumberBinding that represents the multiplication of the current binding value with the specified double value.
     *
     * @param other
     *         the value to multiply the current binding value by
     *
     * @return a NumberBinding representing the multiplication of the current binding value with the specified double value
     */
    default NumberBinding multiply(final double other) {
        return getBinding().multiply(other);
    }

    /**
     * Returns a NumberBinding that represents the product of the current binding value and a floating-point number.
     *
     * @param other
     *         the floating-point number to multiply with the current binding value
     *
     * @return a NumberBinding representing the product of the current binding value and the specified floating-point number
     */
    default NumberBinding multiply(final float other) {
        return getBinding().multiply(other);
    }

    /**
     * Multiplies the current binding value by the specified long value.
     *
     * @param other
     *         the long value to multiply the binding value by
     *
     * @return a NumberBinding representing the result of the multiplication
     */
    default NumberBinding multiply(final long other) {
        return getBinding().multiply(other);
    }

    /**
     * Multiplies the current binding value by the given integer value.
     *
     * @param other
     *         the integer value to multiply by
     *
     * @return a NumberBinding representing the result of the multiplication
     */
    default NumberBinding multiply(final int other) {
        return getBinding().multiply(other);
    }

    //endregion Multiplication

    //region Division
    //*****************************************************************
    // Division
    //*****************************************************************

    /**
     * Returns a NumberBinding that represents the division of the current binding value by the specified ObservableNumberValue.
     *
     * @param other
     *         the ObservableNumberValue to divide the current binding value by
     *
     * @return a NumberBinding representing the division of the current binding value by the specified ObservableNumberValue
     */
    default NumberBinding divide(final ObservableNumberValue other) {
        return getBinding().divide(other);
    }

    /**
     * Divides the current binding value by the specified double value and returns a NumberBinding representing the result.
     *
     * @param other
     *         the double value to divide the current binding value by
     *
     * @return a NumberBinding representing the division of the current binding value by the specified double value
     */
    default NumberBinding divide(final double other) {
        return getBinding().divide(other);
    }

    /**
     * Divides the current binding value by the specified float value and returns a NumberBinding representing the result.
     *
     * @param other
     *         the float value to divide the current binding value by
     *
     * @return a NumberBinding representing the result of the division
     */
    default NumberBinding divide(final float other) {
        return getBinding().divide(other);
    }

    /**
     * Returns a NumberBinding that represents the division of the current binding value by the specified long value. The resulting
     * binding updates automatically whenever the current binding or the specified value changes.
     *
     * @param other
     *         the long value to divide the binding value by
     *
     * @return a NumberBinding representing the result of the division
     */
    default NumberBinding divide(final long other) {
        return getBinding().divide(other);
    }

    /**
     * Returns a NumberBinding that represents the division of the current binding value by the specified integer.
     *
     * @param other
     *         the integer value to divide the current binding value by
     *
     * @return a NumberBinding representing the division of the current binding value by the specified integer
     */
    default NumberBinding divide(final int other) {
        return getBinding().divide(other);
    }

    //endregion Division

    //region IsEqualTo
    //*****************************************************************
    // IsEqualTo
    //*****************************************************************

    /**
     * Returns a BooleanBinding that indicates whether the current binding value is equal to the specified ObservableNumberValue.
     *
     * @param other
     *         the ObservableNumberValue to compare with the current binding value
     *
     * @return a BooleanBinding representing whether the current binding value is equal to the specified ObservableNumberValue
     */
    default BooleanBinding isEqualTo(final ObservableNumberValue other) {
        return getBinding().isEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the current binding value is equal to the value of the given
     * ObservableNumberValue within the specified epsilon.
     *
     * @param other
     *         the ObservableNumberValue to compare with
     * @param epsilon
     *         the maximum difference allowed between the two values for them to be considered equal
     *
     * @return a BooleanBinding representing whether the current binding value is equal to the value of the given ObservableNumberValue
     *         within the specified epsilon
     */
    default BooleanBinding isEqualTo(final ObservableNumberValue other, double epsilon) {
        return getBinding().isEqualTo(other, epsilon);
    }

    /**
     * Returns a BooleanBinding indicating whether the current binding value is equal to the specified double value.
     *
     * @param other
     *         the double value to compare against the current binding value
     * @param epsilon
     *         the maximum difference allowed between the two values for them to be considered equal
     *
     * @return a BooleanBinding representing the result of the comparison
     */
    default BooleanBinding isEqualTo(final double other, double epsilon) {
        return getBinding().isEqualTo(other, epsilon);
    }

    /**
     * Returns a BooleanBinding that represents whether the current binding value is equal to the specified float value within the
     * given epsilon.
     *
     * @param other
     *         the float value to compare with the current binding value
     * @param epsilon
     *         the maximum difference between the float values for them to be considered equal
     *
     * @return a BooleanBinding representing whether the current binding value is equal to the specified float value within the given
     *         epsilon
     */
    default BooleanBinding isEqualTo(final float other, double epsilon) {
        return getBinding().isEqualTo(other, epsilon);
    }

    /**
     * Returns a BooleanBinding that represents whether the current binding value is equal to the given long value. The resulting
     * binding updates automatically whenever the current binding or its dependencies change.
     *
     * @param other
     *         the long value to compare with the current binding value
     *
     * @return a BooleanBinding representing whether the current binding value is equal to the given long value
     */
    default BooleanBinding isEqualTo(final long other) {
        return getBinding().isEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this NumberExpression is equal to the given long value within a
     * specified epsilon.
     *
     * @param other
     *         the long value to compare with
     * @param epsilon
     *         the maximum difference between the values to be considered equal
     *
     * @return a BooleanBinding representing whether the value of this NumberExpression is equal to the given long value within the
     *         specified epsilon
     */
    default BooleanBinding isEqualTo(final long other, double epsilon) {
        return getBinding().isEqualTo(other, epsilon);
    }

    /**
     * Returns a BooleanBinding that represents whether the current binding value is equal to the specified integer value.
     *
     * @param other
     *         the integer value to compare with the current binding value
     *
     * @return a BooleanBinding representing whether the current binding value is equal to the specified value
     */
    default BooleanBinding isEqualTo(final int other) {
        return getBinding().isEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the current binding value is equal to the specified integer value within a
     * given epsilon.
     *
     * @param other
     *         the integer value to compare with the current binding value
     * @param epsilon
     *         the maximum allowed difference between the values for them to be considered equal
     *
     * @return a BooleanBinding representing whether the current binding value is equal to the specified integer value within the given
     *         epsilon
     */
    default BooleanBinding isEqualTo(final int other, double epsilon) {
        return getBinding().isEqualTo(other, epsilon);
    }

    //endregion IsEqualTo

    //region IsNotEqualTo
    //*****************************************************************
    // IsNotEqualTo
    //*****************************************************************

    /**
     * Returns a BooleanBinding that represents whether the value of this ObservableNumberValue is not equal to the value of the given
     * ObservableNumberValue.
     *
     * @param other
     *         the ObservableNumberValue to compare with.
     *
     * @return a BooleanBinding representing the result of the inequality comparison.
     */
    default BooleanBinding isNotEqualTo(final ObservableNumberValue other) {
        return getBinding().isNotEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this property is not equal to the value of the specified
     * ObservableNumberValue within the given epsilon.
     *
     * @param other
     *         the ObservableNumberValue to compare to
     * @param epsilon
     *         the maximum difference allowed between the two values
     *
     * @return a BooleanBinding that represents whether the values are not equal within the given epsilon
     */
    default BooleanBinding isNotEqualTo(final ObservableNumberValue other, double epsilon) {
        return getBinding().isNotEqualTo(other, epsilon);
    }

    /**
     * Checks if the value of this binding is not equal to the specified double value within the given epsilon.
     *
     * @param other
     *         the double value to compare against
     * @param epsilon
     *         the maximum difference between the values to consider them as not equal
     *
     * @return a BooleanBinding that represents the result of the comparison
     */
    default BooleanBinding isNotEqualTo(final double other, double epsilon) {
        return getBinding().isNotEqualTo(other, epsilon);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this binding is not equal to the specified {@code other} value
     * within the given {@code epsilon} range.
     *
     * @param other
     *         the value to compare with
     * @param epsilon
     *         the acceptable range for equality comparison
     *
     * @return a BooleanBinding indicating whether the value of this binding is not equal to the specified {@code other} value within
     *         the given {@code epsilon} range
     */
    default BooleanBinding isNotEqualTo(final float other, double epsilon) {
        return getBinding().isNotEqualTo(other, epsilon);
    }

    /**
     * Returns a BooleanBinding that represents the logical "not equal to" comparison between the value of this binding and the
     * specified long value.
     *
     * @param other
     *         the specified long value to compare with.
     *
     * @return a BooleanBinding that is true if the value of this binding is not equal to the specified long value, otherwise false.
     */
    default BooleanBinding isNotEqualTo(final long other) {
        return getBinding().isNotEqualTo(other);
    }

    /**
     * Determines whether the value of the binding is not equal to the specified long value with the given epsilon.
     *
     * @param other
     *         the long value to compare with
     * @param epsilon
     *         the maximum difference between the values for them to be considered not equal
     *
     * @return a BooleanBinding representing the result of the comparison
     */
    default BooleanBinding isNotEqualTo(final long other, double epsilon) {
        return getBinding().isNotEqualTo(other, epsilon);
    }

    /**
     * Determines whether the value represented by the binding is not equal to the specified integer value.
     *
     * @param other
     *         The integer value to compare against.
     *
     * @return A BooleanBinding representing the result of the inequality comparison.
     */
    default BooleanBinding isNotEqualTo(final int other) {
        return getBinding().isNotEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of a property is not equal to a given int value, considering a
     * specified epsilon for approximate comparison.
     *
     * @param other
     *         the int value to compare against
     * @param epsilon
     *         the epsilon value to determine the range for approximate comparison
     *
     * @return a BooleanBinding that represents the result of the inequality comparison
     */
    default BooleanBinding isNotEqualTo(final int other, double epsilon) {
        return getBinding().isNotEqualTo(other, epsilon);
    }

    //endregion IsNotEqualTo

    //region IsGreaterThan
    //*****************************************************************
    // IsGreaterThan
    //*****************************************************************

    /**
     * Returns a BooleanBinding that represents the greater-than comparison between the value of this ObservableNumberValue and the
     * value of the given ObservableNumberValue.
     *
     * @param other
     *         The ObservableNumberValue to compare against.
     *
     * @return A BooleanBinding that is true if the value of this ObservableNumberValue is greater than the value of the given
     *         ObservableNumberValue, and false otherwise.
     */
    default BooleanBinding greaterThan(final ObservableNumberValue other) {
        return getBinding().greaterThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this binding is greater than the specified value.
     *
     * @param other
     *         the value to compare against
     *
     * @return a BooleanBinding representing the result of the comparison
     */
    default BooleanBinding greaterThan(final double other) {
        return getBinding().greaterThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value is greater than the specified float.
     *
     * @param other
     *         the float value to compare against
     *
     * @return a BooleanBinding representing whether the value is greater than the specified float
     */
    default BooleanBinding greaterThan(final float other) {
        return getBinding().greaterThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this binding is greater than the given value.
     *
     * @param other
     *         The value to compare with.
     *
     * @return A BooleanBinding that represents if the value is greater than the given value.
     */
    default BooleanBinding greaterThan(final long other) {
        return getBinding().greaterThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value in the binding is greater than the specified value.
     *
     * @param other
     *         the value to compare against
     *
     * @return a BooleanBinding that represents whether the value in the binding is greater than the specified value
     */
    default BooleanBinding greaterThan(final int other) {
        return getBinding().greaterThan(other);
    }

    //endregion IsGreaterThan

    //region IsLesserThan
    //*****************************************************************
    // IsLesserThan
    //*****************************************************************

    /**
     * Returns a BooleanBinding that represents whether the value of this ObservableNumberValue is less than the value of the given
     * ObservableNumberValue.
     *
     * @param other
     *         the ObservableNumberValue to compare against
     *
     * @return a BooleanBinding representing whether the value is less than the given ObservableNumberValue
     */
    default BooleanBinding lessThan(final ObservableNumberValue other) {
        return getBinding().lessThan(other);
    }

    /**
     * Creates a BooleanBinding that represents whether the value of this binding is less than the specified double value.
     *
     * @param other
     *         the double value to compare against
     *
     * @return a BooleanBinding that is true if the value of this binding is less than the specified value, and false otherwise
     */
    default BooleanBinding lessThan(final double other) {
        return getBinding().lessThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of the binding is less than the specified float value.
     *
     * @param other
     *         the float value to compare against the value of the binding
     *
     * @return the BooleanBinding that represents whether the value of the binding is less than the specified float value
     */
    default BooleanBinding lessThan(final float other) {
        return getBinding().lessThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of the binding is less than the specified long value.
     *
     * @param other
     *         the long value to compare against
     *
     * @return a BooleanBinding representing whether the value is less than the specified long value
     */
    default BooleanBinding lessThan(final long other) {
        return getBinding().lessThan(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this binding is less than the specified integer value.
     *
     * @param other
     *         the integer value to compare with
     *
     * @return a BooleanBinding representing whether the value of this binding is less than the specified integer value
     */
    default BooleanBinding lessThan(final int other) {
        return getBinding().lessThan(other);
    }

    //endregion IsLesserThan

    //region IsGreaterThanOrEqualTo
    //*****************************************************************
    // IsGreaterThanOrEqualTo
    //*****************************************************************

    /**
     * Returns a BooleanBinding that represents whether the observable number value is greater than or equal to the other observable
     * number value.
     *
     * @param other
     *         The other observable number value to compare with.
     *
     * @return A BooleanBinding that is true if the observable number value is greater than or equal to the other observable number
     *         value, or false otherwise.
     */
    default BooleanBinding greaterThanOrEqualTo(final ObservableNumberValue other) {
        return getBinding().greaterThanOrEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that evaluates to {@code true} if the value of this binding is greater than or equal to the specified
     * {@code other} value, {@code false} otherwise.
     *
     * @param other
     *         the value to compare against
     *
     * @return a BooleanBinding that reflects the evaluation result
     */
    default BooleanBinding greaterThanOrEqualTo(final double other) {
        return getBinding().greaterThanOrEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this binding is greater than or equal to the specified value.
     *
     * @param other
     *         the value to compare against
     *
     * @return a BooleanBinding that represents whether the value of this binding is greater than or equal to the specified value
     */
    default BooleanBinding greaterThanOrEqualTo(final float other) {
        return getBinding().greaterThanOrEqualTo(other);
    }

    /**
     * Returns a BooleanBinding representing whether the value of this binding is greater than or equal to the specified long value.
     *
     * @param other
     *         the long value to compare against
     *
     * @return a BooleanBinding that is true if the value of this binding is greater than or equal to the specified value, and false
     *         otherwise
     */
    default BooleanBinding greaterThanOrEqualTo(final long other) {
        return getBinding().greaterThanOrEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this binding is greater than or equal to the specified integer
     * value.
     *
     * @param other
     *         the integer value to be compared with
     *
     * @return a BooleanBinding that represents whether the value of this binding is greater than or equal to the specified value
     */
    default BooleanBinding greaterThanOrEqualTo(final int other) {
        return getBinding().greaterThanOrEqualTo(other);
    }

    //endregion IsGreaterThanOrEqualTo

    //region IsLessThanOrEqualTo
    //*****************************************************************
    // IsLessThanOrEqualTo
    //*****************************************************************

    /**
     * Returns a BooleanBinding that represents whether the value of this observable number is less than or equal to the value of the
     * specified observable number.
     *
     * @param other
     *         the other observable number to compare to
     *
     * @return a BooleanBinding that represents whether the value of this observable number is less than or equal to the value of the
     *         specified observable number
     */
    default BooleanBinding lessThanOrEqualTo(final ObservableNumberValue other) {
        return getBinding().lessThanOrEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of this binding is less than or equal to the provided value.
     *
     * @param other
     *         the value to compare against
     *
     * @return a BooleanBinding that represents the comparison result
     */
    default BooleanBinding lessThanOrEqualTo(final double other) {
        return getBinding().lessThanOrEqualTo(other);
    }

    /**
     * Returns a Boolean binding that represents whether the value of the calling object is less than or equal to the given float
     * value.
     *
     * @param other
     *         the float value to compare against
     *
     * @return a BooleanBinding that evaluates to true if the value of the calling object is less than or equal to the given float
     *         value, otherwise false
     */
    default BooleanBinding lessThanOrEqualTo(final float other) {
        return getBinding().lessThanOrEqualTo(other);
    }

    /**
     * Returns a BooleanBinding that represents whether the value of the binding is less than or equal to the specified long value.
     *
     * @param other
     *         the long value to compare against the value of the binding
     *
     * @return a BooleanBinding that represents whether the value of the binding is less than or equal to the specified long value
     */
    default BooleanBinding lessThanOrEqualTo(final long other) {
        return getBinding().lessThanOrEqualTo(other);
    }

    /**
     * Returns a BooleanBinding representing whether the value of this binding is less than or equal to the specified value.
     *
     * @param other
     *         the value to be compared
     *
     * @return a BooleanBinding representing whether the value of this binding is less than or equal to the specified value
     */
    default BooleanBinding lessThanOrEqualTo(final int other) {
        return getBinding().lessThanOrEqualTo(other);
    }

    //endregion IsLessThanOrEqualTo

    //region String Conversions
    //*****************************************************************
    // String Conversions
    //*****************************************************************

    /**
     * Returns a string representation of the value of the binding.
     *
     * @return the string representation of the value of the binding.
     */
    default StringBinding asString() {
        return getBinding().asString();
    }

    /**
     * Converts the value of this binding to a string representation using the specified format.
     *
     * @param format
     *         the format string to use for the conversion
     *
     * @return a string with the value of this binding formatted, according to the specified format
     */
    default StringBinding asString(String format) {
        return getBinding().asString(format);
    }

    /**
     * Returns a StringBinding that represents the value of this object as a formatted string, according to the specified locale and
     * format.
     *
     * @param locale
     *         the locale to be used for formatting the string
     * @param format
     *         the format string that specifies the desired format
     *
     * @return a StringBinding object representing the formatted string value of this object
     */
    default StringBinding asString(Locale locale, String format) {
        return getBinding().asString(locale, format);
    }

    //endregion String Conversions
}
