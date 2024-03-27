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
package io.github.colindj1120.enhancedfx.base.beans.binding.base.observablefunctions;

import javafx.beans.value.ObservableNumberValue;

/**
 * Defines additional functionalities for working with {@link ObservableNumberValue} instances within the EnhancedFX framework. This interface extends {@link ObservableValueFunctions} to provide convenience
 * methods for retrieving the primitive values of an {@code ObservableNumberValue}.
 *
 * <p>Implementing this interface allows for easy access to the numeric value represented by an {@code ObservableNumberValue} in various primitive forms (int, long, float, double), enhancing the usability and
 * flexibility of numeric observable values in JavaFX application development.</p>
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Retrieval of the observed number value as an int, long, float, or double, simplifying the process of working with different numeric types in JavaFX.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * ObservableDoubleValue observableDoubleValue = new SimpleDoubleProperty(42.0);
 * ObservableNumberValueFunctions<ObservableDoubleValue> observableFunctions = () -> observableDoubleValue;
 *
 * int intValue = observableFunctions.intValue();
 * long longValue = observableFunctions.longValue();
 * float floatValue = observableFunctions.floatValue();
 * double doubleValue = observableFunctions.doubleValue();
 *
 * System.out.println("Observed values - int: " + intValue + ", long: " + longValue + ", float: " + floatValue + ", double: " + doubleValue);
 * }</pre>
 *
 * <p>This example demonstrates the use of {@code ObservableNumberValueFunctions} to retrieve the value of an {@code ObservableDoubleValue} as different primitive types, showcasing the interface's utility in
 * handling observable numeric values.</p>
 *
 * @param <T>
 *         the specific type of {@link ObservableNumberValue} being extended
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableNumberValue
 * @see ObservableValueFunctions
 */
public interface ObservableNumberValueFunctions<T extends ObservableNumberValue> extends ObservableValueFunctions<Number, T> {
    /**
     * Retrieves the value of the {@link ObservableNumberValue} as an int.
     *
     * @return the numeric value represented as an int
     */
    default int intValue() {
        return getObservableValue().intValue();
    }

    /**
     * Retrieves the value of the {@link ObservableNumberValue} as a long.
     *
     * @return the numeric value represented as a long
     */
    default long longValue() {
        return getObservableValue().longValue();
    }

    /**
     * Retrieves the value of the {@link ObservableNumberValue} as a float.
     *
     * @return the numeric value represented as a float
     */
    default float floatValue() {
        return getObservableValue().floatValue();
    }

    /**
     * Retrieves the value of the {@link ObservableNumberValue} as a double.
     *
     * @return the numeric value represented as a double
     */
    default double doubleValue() {
        return getObservableValue().doubleValue();
    }
}

