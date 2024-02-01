/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.materialdesignui.beans.property.base;

import io.github.colindj1120.materialdesignui.consumers.TriConsumer;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Defines the base interface for extended styleable properties in JavaFX. This interface provides foundational functionality that can be used by extended property classes to enhance and customize
 * their behavior in the context of JavaFX styling.
 * <p>
 * It includes a method to ensure that only one invalidation callback is set at a time, reinforcing best practices in property state management. This approach is designed to avoid conflicting or
 * redundant invalidation handling, which is crucial for maintaining the consistency and reliability of property updates in UI components.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public interface ExtendedStyleablePropertyBase {
    String DEFAULT_NAME = "";

    /**
     * Checks that only one of the provided invalidation callbacks is non-null for a given property. This method is designed to enforce the constraint that only one invalidation callback should be set
     * at any given time for a property. It takes different types of invalidation callbacks and ensures that at most one of them is non-null. If more than one callback is non-null, it throws an
     * IllegalArgumentException.
     * <p>
     * This check is crucial for preventing the setting of multiple conflicting invalidation handlers on a property, which could lead to unpredictable behavior in the property's state and its
     * reflection in the UI.
     * </p>
     *
     * @param invalidatedVoidCallback
     *         The callback for general invalidation without parameters.
     * @param invalidatedPropCallback
     *         The callback for invalidation with property-specific parameters.
     * @param invalidatedCachedCallback
     *         The callback for invalidation with caching behavior.
     * @param clazz
     *         The class of the property for which the invalidation is being checked.
     * @param <P>
     *         The type of the property.
     * @param <T>
     *         The type of the value held by the property.
     *
     * @throws IllegalArgumentException
     *         if more than one invalidator is non-null.
     */
    default <P, T> void invalidatorsNullCheck(Consumer<Void> invalidatedVoidCallback, Consumer<P> invalidatedPropCallback, TriConsumer<P, T, Consumer<T>> invalidatedCachedCallback, Class<?> clazz) {
        long nonNullCount = Stream.of(invalidatedVoidCallback, invalidatedPropCallback, invalidatedCachedCallback)
                                  .filter(Objects::nonNull)
                                  .count();

        if (nonNullCount > 1) {
            throw new IllegalArgumentException("Invalid: There should be zero or one non-null invalidator for " + clazz.getCanonicalName());
        }
    }
}
