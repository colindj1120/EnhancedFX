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

import io.github.colindj1120.enhancedfx.base.beans.binding.base.observablefunctions.ObservableValueFunctions;
import javafx.beans.binding.Binding;
import javafx.collections.ObservableList;

/**
 * Extends {@link ObservableValueFunctions} to provide additional functionality specific to {@link Binding} instances. This interface includes methods for checking validity, invalidating, managing dependencies,
 * and disposing of {@code Binding} objects. It enhances the usability and management of bindings within the EnhancedFX framework, allowing for more detailed control over binding lifecycle and dependencies.
 *
 * <h2>Key Functionalities:</h2>
 * <ul>
 *     <li>Checking the validity of the binding to determine if it needs reevaluation.</li>
 *     <li>Invalidating the binding to force its reevaluation on the next access.</li>
 *     <li>Retrieving the list of dependencies that the binding observes for changes.</li>
 *     <li>Disposing of the binding to release resources and detach from its dependencies.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * Binding<String> concatenatedBinding = Bindings.concat(firstProperty, secondProperty);
 * BindingFunctions<String, Binding<String>> bindingFunctions = () -> concatenatedBinding;
 *
 * // Check if the binding is valid
 * boolean valid = bindingFunctions.isValid();
 *
 * // Invalidate the binding
 * bindingFunctions.invalidate();
 *
 * // Retrieve dependencies
 * ObservableList<?> dependencies = bindingFunctions.getDependencies();
 *
 * // Dispose of the binding
 * bindingFunctions.dispose();
 * }</pre>
 *
 * <p>This example demonstrates the use of {@code BindingFunctions} to manage a {@code Binding<String>} instance. It shows how to check validity, invalidate, retrieve dependencies, and dispose of the
 * binding, illustrating the enhanced control provided by these additional binding functions.</p>
 *
 * @param <T>
 *         The type of the value held by the {@code Binding}.
 * @param <R>
 *         The specific type of {@code Binding} being managed.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Binding
 * @see ObservableValueFunctions
 */
public interface BindingFunctions<T, R extends Binding<T>> extends ObservableValueFunctions<T, R> {
    /**
     * Checks if the binding is currently valid. A binding is valid if its value does not need to be recomputed.
     *
     * @return {@code true} if the binding is valid, {@code false} otherwise.
     */
    default boolean isValid() {
        return getObservableValue().isValid();
    }

    /**
     * Invalidates the binding, indicating that its value needs to be recomputed on the next access.
     */
    default void invalidate() {
        getObservableValue().invalidate();
    }

    /**
     * Retrieves an {@link ObservableList} of dependencies that the binding observes for changes.
     *
     * @return an observable list of dependencies.
     */
    default ObservableList<?> getDependencies() {
        return getObservableValue().getDependencies();
    }

    /**
     * Disposes of the binding, releasing any resources and detaching it from its dependencies.
     * After disposal, the binding should no longer be used.
     */
    default void dispose() {
        getObservableValue().dispose();
    }
}

