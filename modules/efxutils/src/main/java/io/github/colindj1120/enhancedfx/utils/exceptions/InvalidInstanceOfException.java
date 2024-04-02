/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/EnhancedFX).
 *
 * EnhancedFX is free software: you can redistribute it and/or modify
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
package io.github.colindj1120.enhancedfx.utils.exceptions;

import java.io.Serial;

/**
 * Extends {@link RuntimeException} to signal incorrect type usage or invalid type casting attempts at runtime.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Type Safety Enforcement:</b> Aids in enforcing type safety by providing a mechanism to signal mismatches in expected and actual types.</li>
 *     <li><b>Detailed Error Reporting:</b> Supports initializing with detailed messages or underlying causes, thereby facilitating easier debugging and error tracing.</li>
 *     <li><b>Exception Chaining:</b> Allows for exception chaining by including a cause, enhancing error context and aiding in pinpointing the source of type mismatches.</li>
 *     <li><b>Serializable Exception:</b> Implements serialization support, making it suitable for distributed systems where exceptions might need to be transferred across network boundaries.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <em>Demonstrates catching an `InvalidInstanceOfException` when an incorrect type is encountered during a casting operation.</em>
 * <pre>
 * {@code
 *     public <T> T castObject(Object obj, Class<T> clazz) {
 *         try {
 *             return clazz.cast(obj);
 *         } catch (ClassCastException e) {
 *             throw new InvalidInstanceOfException("Failed to cast object to " + clazz.getSimpleName(), e);
 *         }
 *     }
 *
 *     try {
 *         MyCustomClass instance = castObject(someObject, MyCustomClass.class);
 *     } catch (InvalidInstanceOfException e) {
 *         System.err.println("Error: " + e.getMessage());
 *         e.printStackTrace();
 *     }
 * }
 * </pre>
 *
 * <p>Utilizing `InvalidInstanceOfException` encourages robust type checking and error handling, ensuring that type misuse is clearly communicated and managed within the application.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RuntimeException
 * @see Throwable
 */
public class InvalidInstanceOfException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2974645063075507712L;

    /**
     * Constructs a new {@code InvalidInstanceOfException} without a detailed message or cause.
     *
     * <p>This constructor initializes a newly created {@code InvalidInstanceOfException} with no detail message or cause. It provides a generic exception that indicates an invalid type instance was
     * encountered, but does not specify further details about the error condition.</p>
     */
    public InvalidInstanceOfException() {
        super();
    }

    /**
     * Constructs a new {@code InvalidInstanceOfException} with the specified detail message.
     *
     * <p>This constructor initializes the exception with a specific message that describes the invalid instance error. The detail message can help identify the specific scenario in which the error occurred and
     * facilitate debugging.</p>
     *
     * @param message
     *         the detail message. The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public InvalidInstanceOfException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code InvalidInstanceOfException} with the specified detail message and cause.
     *
     * <p>This constructor initializes the exception with both a detailed error message and a {@code Throwable} cause. The cause can be used to capture and relay an underlying exception that led to this
     * exception, providing a more comprehensive view of the error condition.</p>
     *
     * @param message
     *         the detail message. The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method). A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.
     */
    public InvalidInstanceOfException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code InvalidInstanceOfException} with the specified cause.
     *
     * <p>This constructor initializes the exception with a cause but without a detailed message. The cause is a {@code Throwable} that indicates the underlying reason for the exception. This constructor is
     * useful in scenarios where the exception is a wrapper for another exception.</p>
     *
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method). A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.
     */
    public InvalidInstanceOfException(Throwable cause) {
        super(cause);
    }
}
