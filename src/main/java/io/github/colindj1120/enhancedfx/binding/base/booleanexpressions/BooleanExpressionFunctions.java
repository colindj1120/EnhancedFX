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
package io.github.colindj1120.enhancedfx.binding.base.booleanexpressions;

import io.github.colindj1120.enhancedfx.binding.base.ObservableValueFunctions;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;

/**
 * The {@code BooleanExpressionFunctions} interface represents a specialization of {@code ObservableValueFunctions} for boolean
 * expressions within the EnhancedFX framework. It extends the basic observable functionalities to include boolean-specific operations,
 * thus enabling a rich set of reactive programming capabilities for handling boolean logic in JavaFX applications. This interface
 * facilitates the creation, manipulation, and observation of {@link BooleanBinding} instances, providing a declarative way to express
 * complex boolean logic that automatically updates in response to changes in its dependencies.
 *
 * <p>
 * <h2>Core Functionalities:</h2>
 * <ul>
 *   <li><b>Logical Operations:</b> Supports fundamental boolean operations such as AND, OR, NOT, equality, and inequality
 *       checks, allowing for the construction of expressive boolean expressions directly tied to the application state.</li>
 *   <li><b>Conversion Utilities:</b> Offers methods to convert boolean expressions into {@link StringBinding} and
 *       {@link ObjectExpression}, enhancing the interface's utility in a broader range of application scenarios, such as UI
 *       binding or conditional logic.</li>
 *   <li><b>Seamless Integration:</b> By extending {@code ObservableValueFunctions}, it inherits a comprehensive suite of
 *       functionalities for observing, transforming, and binding observable values, ensuring compatibility and
 *       extendability within the JavaFX property system.</li>
 * </ul>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableValueFunctions
 * @see BooleanBinding
 * @see StringBinding
 * @see ObservableValue
 */
public interface BooleanExpressionFunctions extends ObservableValueFunctions<Boolean> {
    /**
     * Retrieves the BooleanBinding instance.
     *
     * @return the BooleanBinding instance.
     */
    BooleanBinding getBinding();

    /**
     * Retrieves the current value of this BooleanBinding.
     *
     * @return the current boolean value.
     */
    default boolean get() {
        return getBinding().get();
    }

    /**
     * Performs a logical AND operation on this binding and another ObservableBooleanValue.
     *
     * @param other the other ObservableBooleanValue.
     * @return a new BooleanBinding resulting from the AND operation.
     */
    default BooleanBinding and(final ObservableBooleanValue other) {
        return getBinding().and(other);
    }

    /**
     * Performs a logical OR operation on this binding and another ObservableBooleanValue.
     *
     * @param other the other ObservableBooleanValue.
     * @return a new BooleanBinding resulting from the OR operation.
     */
    default BooleanBinding or(final ObservableBooleanValue other) {
        return getBinding().or(other);
    }

    /**
     * Inverts the value of this BooleanBinding.
     *
     * @return a new BooleanBinding that is the negation of this BooleanBinding.
     */
    default BooleanBinding not() {
        return getBinding().not();
    }

    /**
     * Compares the value of this BooleanBinding to another ObservableBooleanValue for equality.
     *
     * @param other the other ObservableBooleanValue.
     * @return a new BooleanBinding that is true if both bindings are equal.
     */
    default BooleanBinding isEqualTo(final ObservableBooleanValue other) {
        return getBinding().isEqualTo(other);
    }

    /**
     * Compares the value of this BooleanBinding to another ObservableBooleanValue for inequality.
     *
     * @param other the other ObservableBooleanValue.
     * @return a new BooleanBinding that is true if the bindings are not equal.
     */
    default BooleanBinding isNotEqualTo(final ObservableBooleanValue other) {
        return getBinding().isNotEqualTo(other);
    }

    /**
     * Converts the value of this BooleanBinding to a StringBinding.
     *
     * @return a new StringBinding representing the string form of this BooleanBinding's value.
     */
    default StringBinding asString() {
        return getBinding().asString();
    }

    /**
     * Converts the value of this BooleanBinding to an ObjectExpression<Boolean>.
     *
     * @return a new ObjectExpression<Boolean> representing this BooleanBinding's value.
     */
    default ObjectExpression<Boolean> asObject() {
        return getBinding().asObject();
    }
}
