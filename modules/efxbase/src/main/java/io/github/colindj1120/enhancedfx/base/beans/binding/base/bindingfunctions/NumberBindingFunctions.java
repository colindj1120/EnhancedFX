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
package io.github.colindj1120.enhancedfx.base.beans.binding.base.bindingfunctions;

import javafx.beans.binding.Binding;

/**
 * A specialized interface that extends {@link BindingFunctions} for bindings that produce {@link Number} values. This interface is targeted towards {@link Binding} instances that specifically deal with numeric
 * values, providing a streamlined API for operations and manipulations on numeric bindings within the EnhancedFX framework.
 *
 * <p>Implementing this interface allows numeric bindings to inherit the methods defined in {@code BindingFunctions}, facilitating operations such as validity checks, invalidation, dependency management, and
 * disposal specific to numeric bindings. It serves as a marker interface to distinguish numeric bindings and potentially provide additional numeric-specific functionalities in the future.</p>
 *
 * <h2>Applicability:</h2>
 * This interface is particularly useful for bindings that compute numeric results, such as arithmetic operations, aggregations, or any other computations that result in a {@link Number}. It ensures that such
 * bindings can be managed and utilized consistently with other types of bindings while acknowledging their specific focus on numeric outcomes.
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * DoubleBinding doubleBinding = Bindings.add(firstNumberProperty, secondNumberProperty);
 * NumberBindingFunctions<DoubleBinding> numberBindingFunctions = () -> doubleBinding;
 *
 * // Check if the numeric binding is valid
 * boolean isValid = numberBindingFunctions.isValid();
 *
 * // Invalidate the numeric binding
 * numberBindingFunctions.invalidate();
 *
 * // Dispose of the numeric binding
 * numberBindingFunctions.dispose();
 * }</pre>
 *
 * <p>In this example, {@code NumberBindingFunctions} is used to manage a {@code DoubleBinding} created from the sum of two {@code Property<Number>} instances. The interface's methods are employed to perform
 * common binding operations, demonstrating how numeric bindings benefit from the functionalities provided by {@code BindingFunctions}.</p>
 *
 * @param <T>
 *         the specific type of {@link Binding} being managed, constrained to those producing {@link Number} values
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Number
 * @see Binding
 * @see BindingFunctions
 */
public interface NumberBindingFunctions<T extends Binding<Number>> extends BindingFunctions<Number, T> {}
