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

import io.github.colindj1120.enhancedfx.base.beans.binding.base.observablefunctions.ObservableNumberValueFunctions;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableNumberValue;

import java.util.Locale;

/**
 * Extends {@link ObservableNumberValueFunctions} to provide a comprehensive set of operations for {@link NumberExpression} instances. This interface enables arithmetic operations, comparison, and string
 * conversion methods for numerical expressions, facilitating advanced mathematical manipulations and evaluations within the EnhancedFX framework.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Perform arithmetic operations (addition, subtraction, multiplication, division) on number expressions.</li>
 *     <li>Compare number expressions to other observable numbers or static values (greater than, less than, etc.).</li>
 *     <li>Check equality or inequality with a precision threshold to account for floating-point inaccuracies.</li>
 *     <li>Convert number expressions to string representations, optionally using specific formats or locales.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * DoubleProperty firstNumber = new SimpleDoubleProperty(10.5);
 * IntegerProperty secondNumber = new SimpleIntegerProperty(5);
 * NumberExpressionFunctions<NumberExpression> numberFunctions = () -> firstNumber;
 *
 * // Arithmetic operations
 * NumberBinding sum = numberFunctions.add(secondNumber);
 * NumberBinding difference = numberFunctions.subtract(secondNumber);
 * NumberBinding product = numberFunctions.multiply(secondNumber);
 * NumberBinding quotient = numberFunctions.divide(secondNumber);
 *
 * // Comparisons
 * BooleanBinding isGreater = numberFunctions.greaterThan(secondNumber);
 * BooleanBinding isEqual = numberFunctions.isEqualTo(secondNumber, 0.01);
 *
 * // String conversion
 * StringBinding stringRepresentation = numberFunctions.asString("%.2f");
 *
 * // Example output usage
 * System.out.println("Sum: " + sum.getValue());
 * System.out.println("Product: " + product.getValue());
 * System.out.println("Is Greater: " + isGreater.get());
 * System.out.println("Is Equal: " + isEqual.get());
 * System.out.println("String Representation: " + stringRepresentation.get());
 * }</pre>
 *
 * <p>This example demonstrates the use of {@code NumberExpressionFunctions} for performing arithmetic operations, making comparisons, and converting number expressions to string format. It illustrates the
 * versatility and power of these functions in facilitating complex numerical manipulations within the EnhancedFX framework.</p>
 *
 * @param <T>
 *         the type of the {@link NumberExpression} this interface operates on
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableNumberValueFunctions
 * @see NumberExpression
 * @see NumberBinding
 * @see BooleanBinding
 * @see StringBinding
 * @see DoubleProperty
 * @see IntegerProperty
 */
public interface NumberExpressionFunctions<T extends NumberExpression> extends ObservableNumberValueFunctions<T> {
    //region Negation
    //*****************************************************************
    // Negation
    //*****************************************************************

    /**
     * Negates the value of this {@link NumberExpression}.
     *
     * @return a {@link NumberBinding} that represents the negated value of this expression.
     */
    default NumberBinding negate() {
        return getObservableValue().negate();
    }

    //endregion Negation

    //region Addition
    //*****************************************************************
    // Addition
    //*****************************************************************

    /**
     * Adds the given {@link ObservableNumberValue} to this expression.
     *
     * @param other
     *         the {@link ObservableNumberValue} to add
     *
     * @return a {@link NumberBinding} that represents the sum of this expression and {@code other}.
     */
    default NumberBinding add(final ObservableNumberValue other) {
        return getObservableValue().add(other);
    }

    /**
     * Adds the given double value to this expression.
     *
     * @param other
     *         the double value to add
     *
     * @return a {@link NumberBinding} that represents the sum of this expression and {@code other}.
     */
    default NumberBinding add(final double other) {
        return getObservableValue().add(other);
    }

    /**
     * Adds the given float value to this expression.
     *
     * @param other
     *         the float value to add
     *
     * @return a {@link NumberBinding} that represents the sum of this expression and {@code other}.
     */
    default NumberBinding add(final float other) {
        return getObservableValue().add(other);
    }

    /**
     * Adds the given long value to this expression.
     *
     * @param other
     *         the long value to add
     *
     * @return a {@link NumberBinding} that represents the sum of this expression and {@code other}.
     */
    default NumberBinding add(final long other) {
        return getObservableValue().add(other);
    }

    /**
     * Adds the given int value to this expression.
     *
     * @param other
     *         the int value to add
     *
     * @return a {@link NumberBinding} that represents the sum of this expression and {@code other}.
     */
    default NumberBinding add(final int other) {
        return getObservableValue().add(other);
    }

    //endregion Addition

    //region Subtraction
    //*****************************************************************
    // Subtraction
    //*****************************************************************

    /**
     * Subtracts the given {@link ObservableNumberValue} from this expression.
     *
     * @param other
     *         the {@link ObservableNumberValue} to subtract
     *
     * @return a {@link NumberBinding} that represents the difference between this expression and {@code other}.
     */
    default NumberBinding subtract(final ObservableNumberValue other) {
        return getObservableValue().subtract(other);
    }

    /**
     * Subtracts the given double value from this expression.
     *
     * @param other
     *         the double value to subtract
     *
     * @return a {@link NumberBinding} that represents the difference between this expression and {@code other}.
     */
    default NumberBinding subtract(final double other) {
        return getObservableValue().subtract(other);
    }

    /**
     * Subtracts the given float value from this expression.
     *
     * @param other
     *         the float value to subtract
     *
     * @return a {@link NumberBinding} that represents the difference between this expression and {@code other}.
     */
    default NumberBinding subtract(final float other) {
        return getObservableValue().subtract(other);
    }

    /**
     * Subtracts the given long value from this expression.
     *
     * @param other
     *         the long value to subtract
     *
     * @return a {@link NumberBinding} that represents the difference between this expression and {@code other}.
     */
    default NumberBinding subtract(final long other) {
        return getObservableValue().subtract(other);
    }

    /**
     * Subtracts the given int value from this expression.
     *
     * @param other
     *         the int value to subtract
     *
     * @return a {@link NumberBinding} that represents the difference between this expression and {@code other}.
     */
    default NumberBinding subtract(final int other) {
        return getObservableValue().subtract(other);
    }

    //endregion Subtraction

    //region Multiplication
    //*****************************************************************
    // Multiplication
    //*****************************************************************

    /**
     * Multiplies this expression by another {@link ObservableNumberValue}.
     *
     * @param other
     *         the {@link ObservableNumberValue} to multiply with
     *
     * @return a {@link NumberBinding} that represents the product of this expression and {@code other}.
     */
    default NumberBinding multiply(final ObservableNumberValue other) {
        return getObservableValue().multiply(other);
    }

    /**
     * Multiplies this expression by a double value.
     *
     * @param other
     *         the double value to multiply with
     *
     * @return a {@link NumberBinding} that represents the product of this expression and {@code other}.
     */
    default NumberBinding multiply(final double other) {
        return getObservableValue().multiply(other);
    }

    /**
     * Multiplies this expression by a float value.
     *
     * @param other
     *         the float value to multiply with
     *
     * @return a {@link NumberBinding} that represents the product of this expression and {@code other}.
     */
    default NumberBinding multiply(final float other) {
        return getObservableValue().multiply(other);
    }

    /**
     * Multiplies this expression by a long value.
     *
     * @param other
     *         the long value to multiply with
     *
     * @return a {@link NumberBinding} that represents the product of this expression and {@code other}.
     */
    default NumberBinding multiply(final long other) {
        return getObservableValue().multiply(other);
    }

    /**
     * Multiplies this expression by an int value.
     *
     * @param other
     *         the int value to multiply with
     *
     * @return a {@link NumberBinding} that represents the product of this expression and {@code other}.
     */
    default NumberBinding multiply(final int other) {
        return getObservableValue().multiply(other);
    }

    //endregion Multiplication

    //region Division
    //*****************************************************************
    // Division
    //*****************************************************************

    /**
     * Divides this expression by another {@link ObservableNumberValue}.
     *
     * @param other
     *         the {@link ObservableNumberValue} to divide by
     *
     * @return a {@link NumberBinding} that represents the quotient of this expression divided by {@code other}.
     */
    default NumberBinding divide(final ObservableNumberValue other) {
        return getObservableValue().divide(other);
    }

    /**
     * Divides this expression by a double value.
     *
     * @param other
     *         the double value to divide by
     *
     * @return a {@link NumberBinding} that represents the quotient of this expression divided by {@code other}.
     */
    default NumberBinding divide(final double other) {
        return getObservableValue().divide(other);
    }

    /**
     * Divides this expression by a float value.
     *
     * @param other
     *         the float value to divide by
     *
     * @return a {@link NumberBinding} that represents the quotient of this expression divided by {@code other}.
     */
    default NumberBinding divide(final float other) {
        return getObservableValue().divide(other);
    }

    /**
     * Divides this expression by a long value.
     *
     * @param other
     *         the long value to divide by
     *
     * @return a {@link NumberBinding} that represents the quotient of this expression divided by {@code other}.
     */
    default NumberBinding divide(final long other) {
        return getObservableValue().divide(other);
    }

    /**
     * Divides this expression by an int value.
     *
     * @param other
     *         the int value to divide by
     *
     * @return a {@link NumberBinding} that represents the quotient of this expression divided by {@code other}.
     */
    default NumberBinding divide(final int other) {
        return getObservableValue().divide(other);
    }

    //endregion Division

    //region IsEqualTo
    //*****************************************************************
    // IsEqualTo
    //*****************************************************************

    /**
     * Checks if this expression is equal to another {@link ObservableNumberValue}.
     *
     * @param other
     *         the {@link ObservableNumberValue} to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other}.
     */
    default BooleanBinding isEqualTo(final ObservableNumberValue other) {
        return getObservableValue().isEqualTo(other);
    }

    /**
     * Checks if this expression is equal to another {@link ObservableNumberValue} within a tolerance.
     *
     * @param other
     *         the {@link ObservableNumberValue} to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isEqualTo(final ObservableNumberValue other, double epsilon) {
        return getObservableValue().isEqualTo(other, epsilon);
    }

    /**
     * Checks if this expression is equal to a double value within a tolerance.
     *
     * @param other
     *         the double value to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isEqualTo(final double other, double epsilon) {
        return getObservableValue().isEqualTo(other, epsilon);
    }

    /**
     * Checks if this expression is equal to a float value within a tolerance.
     *
     * @param other
     *         the float value to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isEqualTo(final float other, double epsilon) {
        return getObservableValue().isEqualTo(other, epsilon);
    }

    /**
     * Checks if this expression is equal to a long value.
     *
     * @param other
     *         the long value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other}.
     */
    default BooleanBinding isEqualTo(final long other) {
        return getObservableValue().isEqualTo(other);
    }

    /**
     * Checks if this expression is equal to a long value within a tolerance.
     *
     * @param other
     *         the long value to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isEqualTo(final long other, double epsilon) {
        return getObservableValue().isEqualTo(other, epsilon);
    }

    /**
     * Checks if this expression is equal to an int value.
     *
     * @param other
     *         the int value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other}.
     */
    default BooleanBinding isEqualTo(final int other) {
        return getObservableValue().isEqualTo(other);
    }

    /**
     * Checks if this expression is equal to an int value within a tolerance.
     *
     * @param other
     *         the int value to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isEqualTo(final int other, double epsilon) {
        return getObservableValue().isEqualTo(other, epsilon);
    }

    //endregion IsEqualTo

    //region IsNotEqualTo
    //*****************************************************************
    // IsNotEqualTo
    //*****************************************************************

    /**
     * Checks if this expression is not equal to another {@link ObservableNumberValue}.
     *
     * @param other
     *         the {@link ObservableNumberValue} to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other}.
     */
    default BooleanBinding isNotEqualTo(final ObservableNumberValue other) {
        return getObservableValue().isNotEqualTo(other);
    }

    /**
     * Checks if this expression is not equal to another {@link ObservableNumberValue} within a tolerance.
     *
     * @param other
     *         the {@link ObservableNumberValue} to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isNotEqualTo(final ObservableNumberValue other, double epsilon) {
        return getObservableValue().isNotEqualTo(other, epsilon);
    }

    /**
     * Checks if this expression is not equal to a double value within a tolerance.
     *
     * @param other
     *         the double value to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isNotEqualTo(final double other, double epsilon) {
        return getObservableValue().isNotEqualTo(other, epsilon);
    }

    /**
     * Checks if this expression is not equal to a float value within a tolerance.
     *
     * @param other
     *         the float value to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isNotEqualTo(final float other, double epsilon) {
        return getObservableValue().isNotEqualTo(other, epsilon);
    }

    /**
     * Checks if this expression is not equal to a long value.
     *
     * @param other
     *         the long value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other}.
     */
    default BooleanBinding isNotEqualTo(final long other) {
        return getObservableValue().isNotEqualTo(other);
    }

    /**
     * Checks if this expression is not equal to a long value within a tolerance.
     *
     * @param other
     *         the long value to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isNotEqualTo(final long other, double epsilon) {
        return getObservableValue().isNotEqualTo(other, epsilon);
    }

    /**
     * Checks if this expression is not equal to an int value.
     *
     * @param other
     *         the int value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other}.
     */
    default BooleanBinding isNotEqualTo(final int other) {
        return getObservableValue().isNotEqualTo(other);
    }

    /**
     * Checks if this expression is not equal to an int value within a tolerance.
     *
     * @param other
     *         the int value to compare with
     * @param epsilon
     *         the tolerance for the comparison
     *
     * @return a {@link BooleanBinding} that represents whether this expression is not equal to {@code other} within the specified tolerance.
     */
    default BooleanBinding isNotEqualTo(final int other, double epsilon) {
        return getObservableValue().isNotEqualTo(other, epsilon);
    }

    //endregion IsNotEqualTo

    //region IsGreaterThan
    //*****************************************************************
    // IsGreaterThan
    //*****************************************************************

    /**
     * Checks if this expression is greater than another {@link ObservableNumberValue}.
     *
     * @param other
     *         the {@link ObservableNumberValue} to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than {@code other}.
     */
    default BooleanBinding greaterThan(final ObservableNumberValue other) {
        return getObservableValue().greaterThan(other);
    }

    /**
     * Checks if this expression is greater than a double value.
     *
     * @param other
     *         the double value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than {@code other}.
     */
    default BooleanBinding greaterThan(final double other) {
        return getObservableValue().greaterThan(other);
    }

    /**
     * Checks if this expression is greater than a float value.
     *
     * @param other
     *         the float value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than {@code other}.
     */
    default BooleanBinding greaterThan(final float other) {
        return getObservableValue().greaterThan(other);
    }

    /**
     * Checks if this expression is greater than a long value.
     *
     * @param other
     *         the long value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than {@code other}.
     */
    default BooleanBinding greaterThan(final long other) {
        return getObservableValue().greaterThan(other);
    }

    /**
     * Checks if this expression is greater than an int value.
     *
     * @param other
     *         the int value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than {@code other}.
     */
    default BooleanBinding greaterThan(final int other) {
        return getObservableValue().greaterThan(other);
    }

    //endregion IsGreaterThan

    //region IsLesserThan
    //*****************************************************************
    // IsLesserThan
    //*****************************************************************

    /**
     * Checks if this expression is less than another {@link ObservableNumberValue}.
     *
     * @param other
     *         the {@link ObservableNumberValue} to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than {@code other}.
     */
    default BooleanBinding lessThan(final ObservableNumberValue other) {
        return getObservableValue().lessThan(other);
    }

    /**
     * Checks if this expression is less than a double value.
     *
     * @param other
     *         the double value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than {@code other}.
     */
    default BooleanBinding lessThan(final double other) {
        return getObservableValue().lessThan(other);
    }

    /**
     * Checks if this expression is less than a float value.
     *
     * @param other
     *         the float value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than {@code other}.
     */
    default BooleanBinding lessThan(final float other) {
        return getObservableValue().lessThan(other);
    }

    /**
     * Checks if this expression is less than a long value.
     *
     * @param other
     *         the long value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than {@code other}.
     */
    default BooleanBinding lessThan(final long other) {
        return getObservableValue().lessThan(other);
    }

    /**
     * Checks if this expression is less than an int value.
     *
     * @param other
     *         the int value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than {@code other}.
     */
    default BooleanBinding lessThan(final int other) {
        return getObservableValue().lessThan(other);
    }

    //endregion IsLesserThan

    //region IsGreaterThanOrEqualTo
    //*****************************************************************
    // IsGreaterThanOrEqualTo
    //*****************************************************************

    /**
     * Checks if this expression is greater than or equal to another {@link ObservableNumberValue}.
     *
     * @param other
     *         the {@link ObservableNumberValue} to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than or equal to {@code other}.
     */
    default BooleanBinding greaterThanOrEqualTo(final ObservableNumberValue other) {
        return getObservableValue().greaterThanOrEqualTo(other);
    }

    /**
     * Checks if this expression is greater than or equal to a double value.
     *
     * @param other
     *         the double value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than or equal to {@code other}.
     */
    default BooleanBinding greaterThanOrEqualTo(final double other) {
        return getObservableValue().greaterThanOrEqualTo(other);
    }

    /**
     * Checks if this expression is greater than or equal to a float value.
     *
     * @param other
     *         the float value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than or equal to {@code other}.
     */
    default BooleanBinding greaterThanOrEqualTo(final float other) {
        return getObservableValue().greaterThanOrEqualTo(other);
    }

    /**
     * Checks if this expression is greater than or equal to a long value.
     *
     * @param other
     *         the long value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than or equal to {@code other}.
     */
    default BooleanBinding greaterThanOrEqualTo(final long other) {
        return getObservableValue().greaterThanOrEqualTo(other);
    }

    /**
     * Checks if this expression is greater than or equal to an int value.
     *
     * @param other
     *         the int value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is greater than or equal to {@code other}.
     */
    default BooleanBinding greaterThanOrEqualTo(final int other) {
        return getObservableValue().greaterThanOrEqualTo(other);
    }

    //endregion IsGreaterThanOrEqualTo

    //region IsLessThanOrEqualTo
    //*****************************************************************
    // IsLessThanOrEqualTo
    //*****************************************************************

    /**
     * Checks if this expression is less than or equal to another {@link ObservableNumberValue}.
     *
     * @param other
     *         the {@link ObservableNumberValue} to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than or equal to {@code other}.
     */
    default BooleanBinding lessThanOrEqualTo(final ObservableNumberValue other) {
        return getObservableValue().lessThanOrEqualTo(other);
    }

    /**
     * Checks if this expression is less than or equal to a double value.
     *
     * @param other
     *         the double value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than or equal to {@code other}.
     */
    default BooleanBinding lessThanOrEqualTo(final double other) {
        return getObservableValue().lessThanOrEqualTo(other);
    }

    /**
     * Checks if this expression is less than or equal to a float value.
     *
     * @param other
     *         the float value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than or equal to {@code other}.
     */
    default BooleanBinding lessThanOrEqualTo(final float other) {
        return getObservableValue().lessThanOrEqualTo(other);
    }

    /**
     * Checks if this expression is less than or equal to a long value.
     *
     * @param other
     *         the long value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than or equal to {@code other}.
     */
    default BooleanBinding lessThanOrEqualTo(final long other) {
        return getObservableValue().lessThanOrEqualTo(other);
    }

    /**
     * Checks if this expression is less than or equal to an int value.
     *
     * @param other
     *         the int value to compare with
     *
     * @return a {@link BooleanBinding} that represents whether this expression is less than or equal to {@code other}.
     */
    default BooleanBinding lessThanOrEqualTo(final int other) {
        return getObservableValue().lessThanOrEqualTo(other);
    }

    //endregion IsLessThanOrEqualTo

    //region String Conversions
    //*****************************************************************
    // String Conversions
    //*****************************************************************

    /**
     * Converts the numeric value of this expression to its equivalent string representation.
     *
     * @return a {@link StringBinding} that represents the string form of this expression.
     */
    default StringBinding asString() {
        return getObservableValue().asString();
    }

    /**
     * Converts the numeric value of this expression to its equivalent string representation, using the specified format.
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
     * Converts the numeric value of this expression to its equivalent string representation, using the specified locale and format.
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

    //endregion String Conversions
}
