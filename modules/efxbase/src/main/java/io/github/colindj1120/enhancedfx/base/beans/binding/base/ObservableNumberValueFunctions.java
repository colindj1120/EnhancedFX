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

import javafx.beans.binding.Binding;
import javafx.beans.binding.NumberBinding;

/**
 * The {@code ObservableNumberValueFunctions} interface extends {@link BindingFunctions} with specialized
 * support for numeric values in JavaFX property bindings. It defines methods for retrieving numeric values
 * from bindings and converting them into different numeric types. This interface is part of a framework
 * designed to simplify the creation and management of bindings in JavaFX applications, particularly those
 * that involve numeric computations or need to react dynamically to changes in numeric data.
 *
 * <p>
 * Through {@code ObservableNumberValueFunctions}, developers can easily access and manipulate numeric
 * properties within their JavaFX application. The interface provides a unified approach to dealing with
 * numeric data, whether it's obtaining an {@link NumberBinding} for further operations, or directly retrieving
 * numeric values in various primitive types such as {@code int}, {@code long}, {@code float}, and {@code double}.
 * </p>
 *
 * <p>
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Access to the underlying {@link NumberBinding} for complex binding scenarios.</li>
 *     <li>Convenience methods for converting the binding's value to various primitive numeric types.</li>
 * </ul>
 * This interface streamlines handling numeric values in data-binding contexts, making it easier to develop
 * responsive and data-driven JavaFX UI components that automatically update based on the underlying model's state.
 * </p>
 *
 * This interface is essential for developers looking to leverage the full power of JavaFX binding mechanisms
 * with numeric data, enabling more readable, maintainable, and efficient code.
 *
 * @version 1.0
 * @author Colin Jokisch
 * @see NumberBinding
 * @see Binding
 */
public interface ObservableNumberValueFunctions extends BindingFunctions<Number> {
    /**
     * Retrieves the {@link NumberBinding} instance associated with this object. This method provides direct access to the underlying
     * {@link NumberBinding}, allowing further manipulation or observation of its value.
     *
     * @return the {@link NumberBinding} instance associated with this object.
     */
    NumberBinding getBinding();

    /**
     * Returns the integer value of this binding.
     *
     * @return the integer value of this binding
     */
    default int intValue() {
        return getBinding().intValue();
    }

    /**
     * Returns the value of this observable number as a long primitive.
     *
     * @return the long value of this observable number
     */
    default long longValue() {
        return getBinding().longValue();
    }

    /**
     * Returns the float value of the binding. This method delegates to the `floatValue()` method of the underlying `Binding` object obtained from `getBinding()`.
     *
     * @return the float value of the binding
     * @see ObservableNumberValueFunctions#getBinding()
     * @see NumberBinding#floatValue()
     */
    default float floatValue() {
        return getBinding().floatValue();
    }

    /**
     * Retrieves the double value of the binding.
     *
     * @return the double value of the binding
     */
    default double doubleValue() {
        return getBinding().doubleValue();
    }
}
