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
 * Extends {@link RuntimeException} to signify issues related to UI skin components in JavaFX applications.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><b>Skin Component Failure Identification:</b> Specifically tailored to identify issues within UI skin components, helping in isolating the problems within the visual aspects of JavaFX apps.</li>
 *     <li><b>Enhanced Debugging Context:</b> Offers a way to encapsulate and convey detailed information about skin-related issues, facilitating easier debugging and problem resolution.</li>
 *     <li><b>Flexible Initialization:</b> Provides several constructors to accommodate various use cases, allowing developers to specify detailed error messages and root causes for skin exceptions.</li>
 *     <li><b>Serialization Support:</b> Includes serialization capabilities, ensuring that skin exceptions can be serialized for logging or analysis, especially useful in distributed systems.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <em>Example demonstrating how to handle a skin exception when applying a custom skin to a JavaFX control fails.</em>
 * <pre>
 * {@code
 *     public void applyCustomSkin(Control control) {
 *         try {
 *             Skin<?> customSkin = loadCustomSkin();
 *             control.setSkin(customSkin);
 *         } catch (SkinLoadingException e) {
 *             throw new SkinException("Failed to load custom skin", e);
 *         }
 *     }
 *
 *     private void handleSkinApplicationFailure(Control control) {
 *         try {
 *             applyCustomSkin(control);
 *         } catch (SkinException e) {
 *             System.err.println("Error applying custom skin: " + e.getMessage());
 *             e.printStackTrace();
 *             // Fallback to default skin
 *             control.setSkin(new DefaultSkin(control));
 *         }
 *     }
 * }
 * </pre>
 *
 * <p>The `SkinException` class is pivotal in identifying and managing issues related to the application of UI skins in JavaFX, enhancing the stability and user experience of graphical applications.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RuntimeException
 * @see Throwable
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
     * Constructs a new skin exception with the specified cause and a detail message of {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than wrappers for other throwable.
     *
     * @param cause
     *         the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SkinException(Throwable cause) {
        super(cause);
    }
}
