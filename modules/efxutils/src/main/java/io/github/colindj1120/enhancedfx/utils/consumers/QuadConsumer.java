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
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.utils.consumers;

import java.util.function.Consumer;

/**
 * Represents a functional interface that accepts four arguments and performs an operation without returning any result. This interface is akin to the {@link Consumer} interface but is designed for scenarios
 * requiring the simultaneous handling of four parameters. It is ideal for complex operations that necessitate multiple inputs, thereby enabling more elaborate data processing or actions within a single method
 * call.
 *
 * <p>The {@code QuadConsumer} is particularly useful in contexts requiring the orchestration of multiple operations, complex data manipulation, batch processing, or when actions on entities involve several
 * distinct pieces of data. This functional interface encourages cleaner and more modular code by abstracting operations into discrete, functionally descriptive components. Although it inherently does not
 * support method chaining given its nature of not returning a result, it can be used to foster readability and maintainability in code where complex operations are common.</p>
 *
 * <p>Implementations of this interface are expected to exhibit side-effects, as the interface's primary purpose is to perform operations based on the input arguments without the need for returning a value. It
 * is particularly suited for operations such as logging, data manipulation without direct output, or other procedural actions.</p>
 *
 * <p>Example usage might include applying a set of operations on a tuple of four elements, such as updating records in a database, performing calculations and side effects without needing the results, or
 * conducting multi-step validations.</p>
 *
 * <h2>Example:</h2>
 * <pre>
 * {@code
 *     QuadConsumer<String, Integer, Double, Boolean> logData = (name, age, salary, isActive) -> {
 *         System.out.println("Processing data for " + name + " with age " + age + ", salary " + salary + ", active: " + isActive);
 *         // Further processing here
 *     };
 *     logData.accept("John Doe", 30, 55000.0, true);
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
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
@FunctionalInterface
public interface QuadConsumer<T, U, V, S> {
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
     */
    void accept(T t, U u, V v, S s);
}
