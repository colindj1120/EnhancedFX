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

import javafx.beans.binding.FloatBinding;
import javafx.beans.binding.ObjectExpression;

/**
 * Interface for enhancing the functionality of {@link FloatBinding} through extension methods. FloatExpressionFunctions extend
 * {@link NumberExpressionFunctions}, providing additional capabilities specifically tailored for handling float values within the
 * JavaFX property and binding system. This interface allows for the seamless integration and manipulation of float values within the
 * broader context of observable and bindable properties, enhancing the fluency and expressiveness of JavaFX application development.
 *
 * <p>
 * The primary focus of FloatExpressionFunctions is to augment the standard operations available to {@link FloatBinding}, such as
 * retrieval and conversion of its value. By implementing this interface, classes gain access to a suite of utility functions that
 * facilitate common tasks such as converting the float value to other observable types, directly accessing the float value, and more.
 * </p>
 *
 * <p>
 * This interface is particularly useful in scenarios where precise control over numerical values and their representation is required,
 * such as in UI development, where dynamic feedback based on numerical inputs or calculations is a common requirement. The added
 * methods provide a convenient and type-safe way to work with float values in a reactive programming model, enabling developers to
 * write cleaner, more readable, and maintainable code.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see FloatBinding
 * @see NumberExpressionFunctions
 */
public interface FloatExpressionFunctions extends NumberExpressionFunctions {
    /**
     * Retrieves the {@link FloatBinding} instance associated with this object. This method provides direct access to the underlying
     * {@link FloatBinding}, allowing further manipulation or observation of its value.
     *
     * @return the {@link FloatBinding} instance associated with this object.
     */
    FloatBinding getBinding();

    /**
     * Returns the current value of the {@link FloatBinding}. This method is a convenience wrapper that allows for easy access to the
     * float value represented by the binding, facilitating its use in calculations or UI updates.
     *
     * @return the current float value of the {@link FloatBinding}.
     */
    default float get() {
        return getBinding().get();
    }

    /**
     * Converts the {@link FloatBinding} to an {@link ObjectExpression<Float>}. This method enables the float value to be used in
     * contexts that require an {@link ObjectExpression}, providing greater flexibility in how the value is bound to UI components or
     * used in other bindings.
     *
     * @return an {@link ObjectExpression<Float>} representing the current float value of the {@link FloatBinding}.
     */
    default ObjectExpression<Float> asObject() {
        return getBinding().asObject();
    }

}
