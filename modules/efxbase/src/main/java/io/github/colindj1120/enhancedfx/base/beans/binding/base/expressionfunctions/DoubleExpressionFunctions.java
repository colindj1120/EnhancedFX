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

import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.DoubleProperty;

/**
 * Extends {@link NumberExpressionFunctions} to provide specialized functionality for {@link DoubleExpression} instances within the EnhancedFX framework. This interface offers utility methods tailored for
 * working with expressions that return {@code double} values, enabling more expressive and convenient operations in user code.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Retrieve the current {@code double} value of the {@link DoubleExpression}.</li>
 *     <li>Convert the {@code double} value to an {@link ObjectExpression} for further operations or bindings.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * DoubleProperty doubleProperty = new SimpleDoubleProperty(3.14);
 * DoubleExpressionFunctions<DoubleExpression> doubleExpressionFunctions = () -> doubleProperty;
 *
 * // Retrieve the current double value
 * double currentValue = doubleExpressionFunctions.get();
 *
 * // Convert to ObjectExpression<Double>
 * ObjectExpression<Double> objectExpression = doubleExpressionFunctions.asObject();
 *
 * // Example output usage
 * System.out.println("Current double value: " + currentValue);
 * System.out.println("ObjectExpression value: " + objectExpression.get());
 * }</pre>
 *
 * <p>This example illustrates how to use {@code DoubleExpressionFunctions} to work with a {@code DoubleExpression}, showing how to retrieve its current value and convert it into an {@link ObjectExpression}.
 * It demonstrates the convenience and functionality provided by this interface for handling double values within the EnhancedFX framework.</p>
 *
 * @param <T>
 *         the type of the {@link DoubleExpression} this interface operates on
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see NumberExpressionFunctions
 * @see DoubleExpression
 * @see DoubleProperty
 * @see ObjectExpression
 */
public interface DoubleExpressionFunctions<T extends DoubleExpression> extends NumberExpressionFunctions<T> {
    /**
     * Retrieves the current {@code double} value of the {@link DoubleExpression}.
     *
     * @return the current value of the observable double expression
     */
    default double get() {
        return this.getObservableValue()
                   .get();
    }

    /**
     * Converts this {@link DoubleExpression} into an {@link ObjectExpression} of {@link Double}. This is particularly useful for binding the double value to other properties or expressions in a type-safe
     * manner.
     *
     * @return an {@link ObjectExpression} representing the double value as an object
     */
    default ObjectExpression<Double> asObject() {
        return this.getObservableValue()
                   .asObject();
    }
}

