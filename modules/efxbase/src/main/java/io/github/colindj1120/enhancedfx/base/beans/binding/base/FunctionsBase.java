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

import javafx.beans.value.ObservableValue;

/**
 * Defines the foundational behaviors for EnhancedFX binding classes, encapsulating the core functionalities needed to interact with {@link ObservableValue} instances. This interface is intended to serve as a
 * base for more specialized binding function interfaces within the EnhancedFX framework, providing a consistent API for retrieving and setting the underlying {@link ObservableValue}.
 *
 * <p>Implementing classes are expected to provide mechanisms to access and modify the observable value they encapsulate, thereby facilitating the integration and manipulation of observable values in a uniform
 * manner.</p>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * public class MyCustomBinding<T> implements FunctionsBase<T, ObservableValue<T>> {
 *     private ObservableValue<T> observableValue;
 *
 *     public MyCustomBinding(ObservableValue<T> observableValue) {
 *         this.observableValue = observableValue;
 *     }
 *
 *     @Override
 *     public ObservableValue<T> getObservableValue() {
 *         return observableValue;
 *     }
 *
 *     @Override
 *     public void setObservableValue(ObservableValue<T> value) {
 *         this.observableValue = value;
 *     }
 * }
 * }</pre>
 *
 * <p>This example demonstrates a simple implementation of {@code FunctionsBase}, where {@code MyCustomBinding} manages an {@code ObservableValue<T>}. It provides straightforward methods to get and set the
 * observable value, illustrating the basic contract expected from implementations of this interface.</p>
 *
 * @param <T>
 *         the type of the value held by the observable value
 * @param <R>
 *         the type of the observable value being managed
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableValue
 */
public interface FunctionsBase<T, R extends ObservableValue<T>> {

    /**
     * Retrieves the current {@link ObservableValue} managed by this instance.
     *
     * @return the current observable value
     */
    R getObservableValue();

    /**
     * Updates the {@link ObservableValue} managed by this instance.
     *
     * @param value
     *         the new observable value to be managed
     */
    void setObservableValue(R value);
}

