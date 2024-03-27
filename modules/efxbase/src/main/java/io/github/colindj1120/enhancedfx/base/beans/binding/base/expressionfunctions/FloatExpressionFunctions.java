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

import javafx.beans.binding.FloatExpression;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.FloatProperty;

/**
 * Extends {@link NumberExpressionFunctions} to offer specialized functionality for {@link FloatExpression} instances within the EnhancedFX framework. This interface introduces utility methods specifically
 * designed for expressions that yield float values, enhancing the expressiveness and ease of operations on float expressions in user code.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Retrieve the current {@code float} value of the {@link FloatExpression}.</li>
 *     <li>Convert the {@code float} value to an {@link ObjectExpression} for advanced operations or bindings.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * FloatProperty floatProperty = new SimpleFloatProperty(2.718f);
 * FloatExpressionFunctions<FloatExpression> floatExpressionFunctions = () -> floatProperty;
 *
 * // Retrieve the current float value
 * float currentValue = floatExpressionFunctions.get();
 *
 * // Convert to ObjectExpression<Float>
 * ObjectExpression<Float> objectExpression = floatExpressionFunctions.asObject();
 *
 * // Example output usage
 * System.out.println("Current float value: " + currentValue);
 * System.out.println("ObjectExpression value: " + objectExpression.get());
 * }</pre>
 *
 * <p>This example demonstrates how to use {@code FloatExpressionFunctions} to interact with a {@code FloatExpression}, showcasing the retrieval of its current value and its conversion into an
 * {@link ObjectExpression}. It highlights the ease and flexibility offered by this interface for managing float values within the EnhancedFX framework.</p>
 *
 * @param <T>
 *         the type of the {@link FloatExpression} this interface operates on
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see NumberExpressionFunctions
 * @see FloatExpression
 * @see FloatProperty
 * @see ObjectExpression
 */
public interface FloatExpressionFunctions<T extends FloatExpression> extends NumberExpressionFunctions<T> {
    /**
     * Retrieves the current {@code float} value of the {@link FloatExpression}.
     *
     * @return the current value of the observable float expression
     */
    default float get() {
        return getObservableValue().get();
    }

    /**
     * Converts this {@link FloatExpression} into an {@link ObjectExpression} of {@link Float}. This method is useful for binding the float value to other properties or expressions in a type-safe manner,
     * facilitating complex data manipulations and user interface updates.
     *
     * @return an {@link ObjectExpression} representing the float value as an object
     */
    default ObjectExpression<Float> asObject() {
        return getObservableValue().asObject();
    }
}

