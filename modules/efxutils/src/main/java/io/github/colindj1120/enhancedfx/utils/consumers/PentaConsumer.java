/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/EnhancedFX).
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
package io.github.colindj1120.enhancedfx.utils.consumers;

import java.util.function.Consumer;

/**
 * Represents a functional interface that accepts five arguments and performs an operation without returning a result. This interface is designed for operations that require handling of five input parameters
 * simultaneously, facilitating complex scenarios where multiple data points or objects need to be processed together. It extends the concept of {@link Consumer} to support a higher arity, thus enabling more
 * flexible and expressive lambdas or method references in a variety of applications.
 *
 * <p>The {@code PentaConsumer} can be particularly useful in contexts such as batch processing, complex validations, multi-dimensional data manipulation, or when orchestrating actions that involve multiple
 * resources or entities. Implementations of this interface are expected to operate via side-effects, as it does not return any value.</p>
 *
 * <p>Usage of this interface promotes cleaner, more readable code by abstracting complex operations into simpler, functionally described components. It supports chaining with other functional interfaces
 * through composition methods (not inherently provided by this interface but can be implemented if needed), enhancing its utility in functional programming patterns.</p>
 *
 * <p>As a functional interface, {@code PentaConsumer} can also be used in the context of stream operations, parallel processing, or any scenario that benefits from concise, lambda-based expressions for
 * handling multiple data points.</p>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 *     PentaConsumer<String, Integer, Double, Boolean, List<String>> complexOperation = (a, b, c, d, e) -> {
 *         // Implementation of a complex operation involving five parameters
 *     };
 *     complexOperation.accept("Example", 42, 3.14, true, Arrays.asList("One", "Two", "Three"));
 * }
 * </pre>
 *
 * @param <T>
 *         the type of the first argument to the operation
 * @param <U>
 *         the type of the second argument to the operation
 * @param <V>
 *         the type of the third argument to the operation
 * @param <S>
 *         the type of the fourth argument to the operation
 * @param <P>
 *         the type of the fifth argument to the operation
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
@FunctionalInterface
public interface PentaConsumer<T, U, V, S, P> {
    /**
     * Performs this operation on the given arguments.
     *
     * @param t
     *         the first input argument
     * @param u
     *         the second input argument
     * @param v
     *         the third input argument
     * @param s
     *         the fourth input argument
     * @param p
     *         the fifth input argument
     */
    void accept(T t, U u, V v, S s, P p);
}
