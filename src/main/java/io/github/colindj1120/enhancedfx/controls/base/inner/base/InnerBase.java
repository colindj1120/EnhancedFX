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
package io.github.colindj1120.enhancedfx.controls.base.inner.base;

import javafx.scene.control.Control;

/**
 * Represents a functional interface that provides a contract for retrieving the inner control of a larger control structure in a JavaFX application.
 *
 * <p>
 * This interface defines a single abstract method, `getInnerControl()`, which returns an object of type `T` extending `javafx.scene.control.Control`. The specific type `T` denotes the type of the inner control
 * managed by the implementing class. "Inner" controls are typically embedded components within a larger control or container control.
 * </p>
 *
 * <p>
 * Being a functional interface ensures that `InnerBase` can be used in contexts expecting a functional programming style such as lambda expressions or method references. Classes or interfaces implementing
 * `InnerBase` are expected to provide a concrete implementation for the `getInnerControl()` method.
 * </p>
 *
 * @param <T>
 *         a subclass of `javafx.scene.control.Control` that represents the type of the inner control
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 */
@FunctionalInterface
public interface InnerBase<T extends Control> {
    /**
     * Retrieves the inner control of a larger control structure.
     *
     * <p>
     * An inner control is a component embedded within a parent control. The specific type of the inner control, represented by `T`, is determined by the implementing class.
     * </p>
     *
     * @return the inner control of the parent control
     */
    T getInnerControl();
}
