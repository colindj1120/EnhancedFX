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

import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.ObjectExpression;

/**
 * Interface for enhancing the functionality of {@link IntegerBinding} through extension methods. IntegerExpressionFunctions extend
 * {@link NumberExpressionFunctions}, providing additional capabilities specifically tailored for handling integer values within the
 * JavaFX property and binding system. This interface allows for the seamless integration and manipulation of integer values within the
 * broader context of observable and bindable properties, enhancing the fluency and expressiveness of JavaFX application development.
 *
 * <p>
 * The primary focus of IntegerExpressionFunctions is to augment the standard operations available to {@link IntegerBinding}, such as
 * retrieval and conversion of its value. By implementing this interface, classes gain access to a suite of utility functions that
 * facilitate common tasks such as converting the integer value to other observable types, directly accessing the integer value, and
 * more.
 * </p>
 *
 * <p>
 * This interface is particularly useful in scenarios where precise control over numerical values and their representation is required,
 * such as in UI development, where dynamic feedback based on numerical inputs or calculations is a common requirement. The added
 * methods provide a convenient and type-safe way to work with integer values in a reactive programming model, enabling developers to
 * write cleaner, more readable, and maintainable code.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see IntegerBinding
 * @see NumberExpressionFunctions
 */
public interface IntegerExpressionFunctions extends NumberExpressionFunctions {
    /**
     * Retrieves the {@link IntegerBinding} instance.
     *
     * @return the {@link IntegerBinding} instance, enabling access to its functionality.
     */
    IntegerBinding getBinding();

    /**
     * Gets the current value of this {@link IntegerBinding}.
     *
     * @return the current integer value held by this binding.
     */
    default int get() {
        return getBinding().get();
    }

    /**
     * Converts this {@link IntegerBinding} into an {@link ObjectExpression<Integer>}. This allows the integer value to be treated as an
     * object, facilitating operations and transformations that require object types.
     *
     * @return an {@link ObjectExpression<Integer>} representing the current value of this {@link IntegerBinding}.
     */
    default ObjectExpression<Integer> asObject() {
        return getBinding().asObject();
    }
}
