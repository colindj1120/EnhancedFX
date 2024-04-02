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
 * Represents a specialized {@link RuntimeException} that signifies an error due to invalid associations or linkages between entities or components within an application.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Error Identification:</b> Facilitates precise identification of issues related to invalid associations, aiding in quick diagnosis and resolution.</li>
 *     <li><b>Flexible Initialization:</b> Supports various constructors for initializing with detailed messages, causes, or both, allowing developers to convey specific error information.</li>
 *     <li><b>Chain of Causes:</b> Enables chaining of exceptions by accepting a cause, thus preserving the stack trace of underlying errors that led to this exception.</li>
 *     <li><b>Serializable:</b> Marked with {@code @Serial} to support serialization, ensuring that instances of this exception can be transmitted or stored for later analysis.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <em>Demonstrates throwing and catching an {@code InvalidAssociationException} to handle errors arising from invalid entity linkages in an application.</em>
 * <pre>
 * {@code
 *     public void linkEntities(Entity parent, Entity child) throws InvalidAssociationException {
 *         if (parent == null || child == null) {
 *             throw new InvalidAssociationException("Cannot link entities: One or more entities are null.");
 *         }
 *         try {
 *             // Logic to link entities
 *         } catch (SomeSpecificException e) {
 *             throw new InvalidAssociationException("Failed to link entities due to specific error.", e);
 *         }
 *     }
 *
 *     try {
 *         linkEntities(entity1, entity2);
 *     } catch (InvalidAssociationException e) {
 *         System.err.println("Error linking entities: " + e.getMessage());
 *         e.printStackTrace();
 *     }
 * }
 * </pre>
 *
 * <p>Through the use of `InvalidAssociationException`, applications can effectively signal and manage errors related to the improper linking or association of components, contributing to a robust and
 * maintainable codebase.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RuntimeException
 * @see Throwable
 */
public class InvalidAssociationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4354642063005507712L;

    /**
     * Constructs a new {@code InvalidAssociationException} without a detailed message or cause.
     *
     * <p>This constructor initializes a newly created {@code InvalidAssociationException} with no detail message or cause. It provides a generic exception that indicates an invalid type instance was
     * encountered, but does not specify further details about the error condition.</p>
     */
    public InvalidAssociationException() {
        super();
    }

    /**
     * Constructs a new {@code InvalidAssociationException} with the specified detail message.
     *
     * <p>This constructor initializes the exception with a specific message that describes the invalid instance error. The detail message can help identify the specific scenario in which the error occurred and
     * facilitate debugging.</p>
     *
     * @param message
     *         the detail message. The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public InvalidAssociationException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code InvalidAssociationException} with the specified detail message and cause.
     *
     * <p>This constructor initializes the exception with both a detailed error message and a {@code Throwable} cause. The cause can be used to capture and relay an underlying exception that led to this
     * exception, providing a more comprehensive view of the error condition.</p>
     *
     * @param message
     *         the detail message. The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method). A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.
     */
    public InvalidAssociationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code InvalidAssociationException} with the specified cause.
     *
     * <p>This constructor initializes the exception with a cause but without a detailed message. The cause is a {@code Throwable} that indicates the underlying reason for the exception. This constructor is
     * useful in scenarios where the exception is a wrapper for another exception.</p>
     *
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method). A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.
     */
    public InvalidAssociationException(Throwable cause) {
        super(cause);
    }
}
