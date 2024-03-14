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

import javafx.collections.ObservableList;

/**
 * The {@code BindingFunctions} interface enriches the {@code ObservableValueFunctions} with functionalities tailored specifically for
 * bindings in JavaFX. It defines a suite of default methods to assess and manage the state of bindings, encapsulating operations for
 * validity checks, invalidation, dependency tracking, and resource management. Implementors of this interface can leverage these
 * methods to interact with bindings in a more streamlined and standardized manner, enhancing code readability and maintainability.
 *
 * <p>
 * <h2>Key capabilities include:</h2>
 * <ul>
 *   <li><b>Validity Checks:</b> Determine whether the current value of a binding is up-to-date relative to its dependencies, ensuring
 *       that consumers of the binding's value can trust its currency and accuracy.</li>
 *   <li><b>Invalidation:</b> Explicitly mark a binding as out-of-date, prompting a recalculation of its value the next time it is
 *       requested. This is particularly useful in scenarios where the binding's value needs to be refreshed to reflect changes in
 *       external conditions or dependencies.</li>
 *   <li><b>Dependency Tracking:</b> Access a comprehensive list of all observables that a binding depends on. This facilitates
 *       understanding and debugging the reactive chains within an application, allowing developers to trace how changes propagate
 *       through the system.</li>
 *   <li><b>Resource Management:</b> Properly dispose of bindings, releasing any resources they may be holding and preventing memory
 *       leaks. This is crucial for applications with dynamic UIs where bindings are created and discarded frequently.</li>
 * </ul>
 * </p>
 *
 * @param <T>
 *         the type of the value that the binding holds, providing a generic approach to handle any type of binding
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ObservableValueFunctions
 */
public interface BindingFunctions<T> extends ObservableValueFunctions<T> {

    /**
     * Checks if the binding is currently valid. A binding is considered valid if its value is up-to-date with its dependencies.
     *
     * @return {@code true} if the binding is valid, {@code false} otherwise
     */
    default boolean isValid() {
        return getBinding().isValid();
    }

    /**
     * Invalidates the binding, marking its value as out-of-date. The next time the binding's value is requested, it will be
     * recalculated.
     */
    default void invalidate() {
        getBinding().invalidate();
    }

    /**
     * Retrieves a list of observables that the binding depends on. Changes to any of these observables will potentially cause the
     * binding to be invalidated and its value to be recalculated.
     *
     * @return an {@link ObservableList} containing the dependencies of the binding
     */
    default ObservableList<?> getDependencies() {
        return getBinding().getDependencies();
    }

    /**
     * Disposes of the binding, releasing any resources it may be holding. After disposal, the binding should not be used.
     */
    default void dispose() {
        getBinding().dispose();
    }
}

