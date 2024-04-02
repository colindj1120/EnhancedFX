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
 * Extends {@link RuntimeException} to signify errors that have cascading or "ripple" effects across an application.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Cascading Error Identification:</b> Specially designed to represent errors that trigger a cascade of failures or mishaps across different layers or components of an application.</li>
 *     <li><b>Enhanced Debugging Context:</b> Facilitates debugging by encapsulating both the immediate cause and the broader context of cascading errors, providing more insight into the error's origin and
 *     its propagation path.</li>
 *     <li><b>Flexible Initialization:</b> Offers multiple constructors for diverse initialization needs, enabling the specification of detailed error messages and underlying causes.</li>
 *     <li><b>Serialization Support:</b> Implements serialization, making it suitable for distributed systems where errors might need to be transmitted across network boundaries for logging or analysis.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <em>Example demonstrating how to use `RippleEffectException` to signal and handle a cascading error scenario in a database operation.</em>
 * <pre>
 * {@code
 *     public void performDatabaseUpdate() {
 *         try {
 *             updatePrimaryRecords();
 *             updateSecondaryRecords();
 *         } catch (DatabaseAccessException e) {
 *             throw new RippleEffectException("Failed to complete database update due to access error", e);
 *         }
 *     }
 *
 *     private void handleDatabaseUpdateFailure() {
 *         try {
 *             performDatabaseUpdate();
 *         } catch (RippleEffectException e) {
 *             System.err.println("Cascading failure encountered: " + e.getMessage());
 *             e.printStackTrace();
 *             rollbackDatabaseChanges();
 *         }
 *     }
 * }
 * </pre>
 *
 * <p>The `RippleEffectException` is instrumental in identifying and managing errors that have the potential to cause subsequent failures, aiding in the robustness and resilience of application error
 * handling strategies.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RuntimeException
 * @see Throwable
 */
public class RippleEffectException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2972645068075507712L;

    /**
     * Constructs a new ripple effect exception with {@code null} as its detail message. The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public RippleEffectException() {
        super();
    }

    /**
     * Constructs a new ripple effect exception with the specified detail message. The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message
     *         the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public RippleEffectException(String message) {
        super(message);
    }

    /**
     * Constructs a new ripple effect exception with the specified detail message and cause.
     *
     * <p>Note that the detail message associated with {@code cause} is not automatically incorporated into this exception's detail message.</p>
     *
     * @param message
     *         the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public RippleEffectException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ripple effect exception with the specified cause and a detail message of {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of
     * {@code cause}).
     *
     * <p>This constructor is useful for runtime exceptions that are little more than wrappers for other throwable.</p>
     *
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public RippleEffectException(Throwable cause) {
        super(cause);
    }
}

