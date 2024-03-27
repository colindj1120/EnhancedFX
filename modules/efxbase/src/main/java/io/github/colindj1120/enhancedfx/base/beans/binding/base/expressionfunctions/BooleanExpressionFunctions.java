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
import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableBooleanValue;

/**
 * Extends {@link ObservableValueFunctions} to incorporate boolean-specific operations for {@link BooleanExpression} instances within the EnhancedFX framework. This interface facilitates functional-style
 * operations on boolean expressions, such as logical AND, OR, NOT, equality checks, and conversion methods, enhancing readability and usability in user code.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Perform logical AND operations between two {@link ObservableBooleanValue}s.</li>
 *     <li>Perform logical OR operations between two {@link ObservableBooleanValue}s.</li>
 *     <li>Perform logical NOT operation to invert the boolean value of the expression.</li>
 *     <li>Check for equality or inequality between two {@link ObservableBooleanValue}s.</li>
 *     <li>Convert the boolean value to a {@link StringBinding} or an {@link ObjectExpression}.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * BooleanProperty firstBooleanProperty = new SimpleBooleanProperty(true);
 * BooleanProperty secondBooleanProperty = new SimpleBooleanProperty(false);
 * BooleanExpressionFunctions<BooleanExpression> booleanExpressionFunctions = () -> firstBooleanProperty;
 *
 * // Perform logical AND operation
 * BooleanBinding andResult = booleanExpressionFunctions.and(secondBooleanProperty);
 *
 * // Perform logical OR operation
 * BooleanBinding orResult = booleanExpressionFunctions.or(secondBooleanProperty);
 *
 * // Perform logical NOT operation
 * BooleanBinding notResult = booleanExpressionFunctions.not();
 *
 * // Check for equality
 * BooleanBinding isEqualToResult = booleanExpressionFunctions.isEqualTo(secondBooleanProperty);
 *
 * // Convert to StringBinding
 * StringBinding stringResult = booleanExpressionFunctions.asString();
 *
 * // Example output usage
 * System.out.println("AND result: " + andResult.get());
 * System.out.println("OR result: " + orResult.get());
 * System.out.println("NOT result: " + notResult.get());
 * System.out.println("Is Equal To result: " + isEqualToResult.get());
 * System.out.println("As String result: " + stringResult.get());
 * }</pre>
 *
 * <p>This example demonstrates how to use {@code BooleanExpressionFunctions} to perform logical operations on boolean properties, check equality, and convert boolean values to string representation. It
 * illustrates the ease with which boolean expressions can be manipulated and combined using this interface.</p>
 *
 * @param <T>
 *         the type of the {@link BooleanExpression} this interface operates on
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableValueFunctions
 * @see BooleanExpression
 * @see BooleanProperty
 * @see BooleanBinding
 * @see ObservableBooleanValue
 */
public interface BooleanExpressionFunctions<T extends BooleanExpression> extends ObservableValueFunctions<Boolean, T> {
    /**
     * Retrieves the current boolean value of the {@link BooleanExpression}.
     *
     * @return the current value of the observable boolean expression
     */
    default boolean get() {
        return getObservableValue().get();
    }

    /**
     * Creates a new {@link BooleanBinding} that holds the result of a logical AND operation between this expression and another {@link ObservableBooleanValue}.
     *
     * @param other
     *         the other {@link ObservableBooleanValue} to perform the AND operation with
     *
     * @return a new {@link BooleanBinding} representing the logical AND of this expression and {@code other}
     */
    default BooleanBinding and(final ObservableBooleanValue other) {
        return getObservableValue().and(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds the result of a logical OR operation between this expression and another {@link ObservableBooleanValue}.
     *
     * @param other
     *         the other {@link ObservableBooleanValue} to perform the OR operation with
     *
     * @return a new {@link BooleanBinding} representing the logical OR of this expression and {@code other}
     */
    default BooleanBinding or(final ObservableBooleanValue other) {
        return getObservableValue().or(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds the result of the logical negation (NOT operation) of this expression.
     *
     * @return a new {@link BooleanBinding} representing the logical NOT of this expression
     */
    default BooleanBinding not() {
        return getObservableValue().not();
    }

    /**
     * Creates a new {@link BooleanBinding} that holds the result of an equality check between this expression and another {@link ObservableBooleanValue}.
     *
     * @param other
     *         the other {@link ObservableBooleanValue} to check for equality with
     *
     * @return a new {@link BooleanBinding} representing the result of the equality check
     */
    default BooleanBinding isEqualTo(final ObservableBooleanValue other) {
        return getObservableValue().isEqualTo(other);
    }

    /**
     * Creates a new {@link BooleanBinding} that holds the result of a non-equality check between this expression and another {@link ObservableBooleanValue}.
     *
     * @param other
     *         the other {@link ObservableBooleanValue} to check for non-equality with
     *
     * @return a new {@link BooleanBinding} representing the result of the non-equality check
     */
    default BooleanBinding isNotEqualTo(final ObservableBooleanValue other) {
        return getObservableValue().isNotEqualTo(other);
    }

    /**
     * Converts this {@link BooleanExpression} into a {@link StringBinding}.
     *
     * @return a new {@link StringBinding} representing the string form of this boolean expression
     */
    default StringBinding asString() {
        return getObservableValue().asString();
    }

    /**
     * Converts this {@link BooleanExpression} into an {@link ObjectExpression}.
     *
     * @return a new {@link ObjectExpression} representing this boolean expression as an object
     */
    default ObjectExpression<Boolean> asObject() {
        return getObservableValue().asObject();
    }
}
