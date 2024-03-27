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

import javafx.beans.binding.IntegerExpression;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.IntegerProperty;

/**
 * Extends {@link NumberExpressionFunctions} to provide specialized functionality for {@link IntegerExpression} instances within the EnhancedFX framework. This interface is designed to facilitate operations
 * specifically tailored to expressions returning integer values, thereby enhancing code expressiveness and simplification of operations involving integer expressions.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Retrieve the current {@code int} value of the {@link IntegerExpression}.</li>
 *     <li>Convert the {@code int} value to an {@link ObjectExpression} for advanced binding operations.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * IntegerProperty integerProperty = new SimpleIntegerProperty(42);
 * IntegerExpressionFunctions<IntegerExpression> integerExpressionFunctions = () -> integerProperty;
 *
 * // Retrieve the current integer value
 * int currentValue = integerExpressionFunctions.get();
 *
 * // Convert to ObjectExpression<Integer>
 * ObjectExpression<Integer> objectExpression = integerExpressionFunctions.asObject();
 *
 * // Example output usage
 * System.out.println("Current integer value: " + currentValue);
 * System.out.println("ObjectExpression value: " + objectExpression.get());
 * }</pre>
 *
 * <p>This example illustrates the use of {@code IntegerExpressionFunctions} with an {@code IntegerExpression}, showing how to obtain its current value and convert it into an {@link ObjectExpression}. It
 * underscores the interface's utility in simplifying the handling of integer values, demonstrating its value within the EnhancedFX framework.</p>
 *
 * @param <T>
 *         the type of the {@link IntegerExpression} this interface operates on
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see NumberExpressionFunctions
 * @see IntegerExpression
 * @see IntegerProperty
 * @see ObjectExpression
 */
public interface IntegerExpressionFunctions<T extends IntegerExpression> extends NumberExpressionFunctions<T> {
    /**
     * Retrieves the current {@code int} value of the {@link IntegerExpression}. This method allows for the direct fetching of the integer value from the underlying observable value, simplifying access in user
     * code.
     *
     * @return the current value of the observable integer expression
     */
    default int get() {
        return getObservableValue().get();
    }

    /**
     * Converts this {@link IntegerExpression} into an {@link ObjectExpression} of {@link Integer}. This conversion is useful for binding the integer value to other properties or expressions, facilitating a
     * type-safe approach to manipulating observable values within the application.
     *
     * @return an {@link ObjectExpression} representing the integer value as an object
     */
    default ObjectExpression<Integer> asObject() {
        return getObservableValue().asObject();
    }
}

