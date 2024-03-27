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

import javafx.beans.binding.LongExpression;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.LongProperty;

/**
 * Extends {@link NumberExpressionFunctions} to incorporate specialized functionality for {@link LongExpression} instances within the EnhancedFX framework. This interface is tailored for expressions that return
 * long values, providing utility methods that enhance the usability and expressiveness of working with long expressions in user code.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Retrieve the current {@code long} value of the {@link LongExpression}.</li>
 *     <li>Convert the {@code long} value to an {@link ObjectExpression} for enhanced operation capabilities and binding.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * LongProperty longProperty = new SimpleLongProperty(314159265358979L);
 * LongExpressionFunctions<LongExpression> longExpressionFunctions = () -> longProperty;
 *
 * // Retrieve the current long value
 * long currentValue = longExpressionFunctions.get();
 *
 * // Convert to ObjectExpression<Long>
 * ObjectExpression<Long> objectExpression = longExpressionFunctions.asObject();
 *
 * // Example output usage
 * System.out.println("Current long value: " + currentValue);
 * System.out.println("ObjectExpression value: " + objectExpression.get());
 * }</pre>
 *
 * <p>This example showcases how to use {@code LongExpressionFunctions} to interact with a {@code LongExpression}, highlighting the process of obtaining its current value and converting it into an
 * {@link ObjectExpression}. It demonstrates the simplicity and effectiveness of managing long values within the EnhancedFX framework.</p>
 *
 * @param <T>
 *         the type of the {@link LongExpression} this interface operates on
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see NumberExpressionFunctions
 * @see LongExpression
 * @see LongProperty
 * @see ObjectExpression
 */
public interface LongExpressionFunctions<T extends LongExpression> extends NumberExpressionFunctions<T> {
    /**
     * Retrieves the current {@code long} value of the {@link LongExpression}. This method facilitates direct access to the long value from the underlying observable value, making it easier to retrieve and
     * utilize in various application scenarios.
     *
     * @return the current value of the observable long expression
     */
    default long get() {
        return getObservableValue().get();
    }

    /**
     * Converts this {@link LongExpression} into an {@link ObjectExpression} of {@link Long}. This functionality is particularly useful for bindings and other operations that require the long value to be
     * treated as an object, enabling type-safe manipulations and interactions with the long value within the application.
     *
     * @return an {@link ObjectExpression} representing the long value as an object
     */
    default ObjectExpression<Long> asObject() {
        return getObservableValue().asObject();
    }
}

