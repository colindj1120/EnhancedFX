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
package io.github.colindj1120.enhancedfx.utils.exceptions;

import java.io.Serial;

/**
 * Represents exceptions that may occur during the application of custom skins to UI components within the EnhancedFX framework. This class extends {@link RuntimeException} to provide a
 * flexible mechanism for reporting and handling errors related to skinning operations, such as applying, loading, or customizing skins.
 *
 * <p>
 * The {@code SkinException} can be thrown in various scenarios where skin operations fail due to reasons such as invalid skin configurations, errors in skin resources, or runtime issues in the skin
 * application process. It supports different constructors to cater to various contexts of exception occurrences, allowing developers to specify detailed messages and underlying causes.
 * </p>
 *
 * <p>
 * Usage Example:
 * <pre>
 * try {
 *     applyCustomSkin(component);
 * } catch (SkinException e) {
 *     log.error("Failed to apply custom skin", e);
 * }
 * </pre>
 * </p>
 *
 * <p>
 * This approach facilitates better error diagnosis and handling, ensuring that skin-related issues can be effectively communicated and managed within the application.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public class SkinException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6600219367537647616L;

    /**
     * Constructs a new skin exception with {@code null} as its detail message.
     */
    public SkinException() {
        super();
    }

    /**
     * Constructs a new skin exception with the specified detail message.
     *
     * @param message
     *         the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public SkinException(String message) {
        super(message);
    }

    /**
     * Constructs a new skin exception with the specified detail message and cause.
     *
     * @param message
     *         the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SkinException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new skin exception with the specified cause and a detail message of {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of
     * {@code cause}). This constructor is useful for exceptions that are little more than wrappers for other throwables.
     *
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SkinException(Throwable cause) {
        super(cause);
    }
}
