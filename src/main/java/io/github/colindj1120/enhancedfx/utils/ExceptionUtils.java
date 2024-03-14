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
package io.github.colindj1120.enhancedfx.utils;

/**
 * Provides utility methods for generating {@link IllegalStateException} in various scenarios where method calls or instance checks fail
 * within the EnhancedFX framework. This class is designed to centralize the logic for generating exception messages related to invalid
 * method calls or incorrect instance types, thereby promoting code reuse and enhancing error reporting.
 * <p>
 * The utilities within this class are static methods that can be invoked to immediately throw an {@link IllegalStateException} with a
 * descriptive error message. These methods are particularly useful in the context of dynamic method invocation and type checking
 * within the EnhancedFX library, ensuring that developers receive clear and informative feedback when an operation cannot be performed
 * as expected.
 * </p>
 * <p>
 * <h2>Usage example:</h2>
 * <pre>
 * <h3>To report an invalid method call</h3>
 * throw new ExceptionUtils.invalidMethodCall(MyClass.class, "myMethod");
 *
 * <h3>To report an incorrect instance type in a specific context</h3>
 * throw new ExceptionUtils.invalidInstanceOf("myNode", "ExpectedType", MyClass.class, "myMethod");
 * </pre>
 * These examples demonstrate how to use the class to signal violations of expected behavior or constraints within EnhancedFX-enhanced
 * components or utilities.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see IllegalStateException
 */
public class ExceptionUtils {
    /**
     * Private constructor to prevent instantiation of the {@code ExceptionUtils} class.
     *
     * <p>
     * This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code ExceptionUtils}.
     * </p>
     */
    private ExceptionUtils() {}

    /**
     * Generates an {@link IllegalStateException} indicating that a specified method call is invalid for a given class.
     * This method is typically used to signal that a method, identified by its name, should not be or cannot be
     * invoked on the specified class, either due to access restrictions, the method not existing in the class's context,
     * or the method not being appropriate for the current state of the object.
     *
     * @param clazz       The {@link Class} object representing the class where the invalid method call was attempted.
     * @param methodName  The name of the method that was called invalidly.
     * @return            An {@link IllegalStateException} with a message detailing the invalid method call.
     * @throws IllegalStateException  This exception is thrown to indicate the specific error condition.
     */
    public static IllegalStateException invalidMethodCall(Class<?> clazz, String methodName) {
        return new IllegalStateException(String.format("%s is not a valid method call for %s", methodName, clazz));
    }

    /**
     * Generates an {@link IllegalStateException} to indicate that a specific control or node, identified by its
     * name, is not an instance of the expected type. This utility method is useful for type checking in scenarios
     * where dynamic casting or reflection is used, and the type of an object must be strictly controlled or verified.
     * The exception message provides detailed context, including the name of the node or control, the expected type,
     * and the location (class and method name) where the type mismatch was detected.
     *
     * @param nodeName      The name of the node or control being checked.
     * @param instanceType  The expected type (as a {@link String}) that the node or control should be an instance of.
     * @param clazz         The {@link Class} object representing the class within which the type check was performed.
     * @param methodName    The name of the method within which the incorrect instance type was identified.
     * @return              An {@link IllegalStateException} with a detailed message explaining the type mismatch.
     * @throws IllegalStateException  This exception is thrown to signal the type mismatch error condition.
     */
    public static IllegalStateException invalidInstanceOf(String nodeName, String instanceType, Class<?> clazz, String methodName)
            throws IllegalStateException {
        String errorMessage = String.format("Control{Name: %s} is not an instance of %s in {Class: %s, Method: %s}", nodeName,
                                            instanceType, clazz, methodName);
        return new IllegalStateException(errorMessage);
    }
}
