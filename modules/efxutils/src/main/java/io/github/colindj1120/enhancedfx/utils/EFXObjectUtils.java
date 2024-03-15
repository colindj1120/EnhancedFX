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

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * The {@code EFXObjectUtils} class provides a collection of static utility methods for object validation and resource verification. It simplifies common tasks such as null checks, string validation, and ensuring
 * the existence of classpath resources. Designed for direct static import, it allows for concise and readable validation code throughout an application.
 *
 * <h2>Capabilities</h2>
 * <p>
 * <ul>
 *   <li><b>Null Checks</b>: Offers methods to assert the non-nullity of references, either returning a boolean result or throwing an exception with a custom message for null references.</li>
 *   <li><b>String Validation</b>: Provides methods to check if a string is not null and not empty, supporting both boolean results and exception throwing with custom messages for invalid strings.</li>
 *   <li><b>Resource Existence Verification</b>: Includes methods to verify the existence of a resource within the application's classpath, throwing an exception with a custom message if the resource is not
 *   found.</li>
 * </ul>
 * </p>
 *
 * <h2>Example Usage</h2>
 * <p>
 * <pre>{@code
 * // Check for non-null object with a custom error message
 * EFXObjectUtils.checkNotNull(user, () -> "User object must not be null.");
 *
 * // Validate that a configuration string is not null or empty
 * boolean isValidConfig = EFXObjectUtils.isNotNullOrEmpty(configValue, () -> "Configuration value cannot be empty.");
 *
 * // Assert the existence of a classpath resource
 * EFXObjectUtils.checkResourcePathNotNull("config/settings.xml", () -> "Settings configuration file is missing.");
 * }</pre>
 * </>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Objects
 * @see Optional
 */
@SuppressWarnings("UnusedReturnValue")
public class EFXObjectUtils {
    /**
     * Private constructor to prevent instantiation of the {@code EFXObjectUtils} class.
     *
     * <p>
     * This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code EFXObjectUtils}.
     * </p>
     */
    private EFXObjectUtils() {}

    /**
     * Checks if the provided object is not null.
     *
     * @param <T>
     *         the type of the object to check
     * @param toCheck
     *         the object to check for nullity
     *
     * @return {@code true} if {@code toCheck} is not null; {@code false} otherwise.
     */
    public static <T> boolean isNotNull(T toCheck) {
        return Objects.nonNull(toCheck);
    }

    /**
     * Checks if the provided object is not null, throwing an exception with a custom message if it is.
     *
     * @param <T>
     *         the type of the object to check
     * @param toCheck
     *         the object to check for nullity
     * @param messageSupplier
     *         supplies the custom message to include in the exception if {@code toCheck} is null
     *
     * @return {@code true} if {@code toCheck} is not null.
     *
     * @throws IllegalArgumentException
     *         if {@code toCheck} is null, with the custom message provided by {@code messageSupplier}
     */
    public static <T> boolean isNotNull(T toCheck, Supplier<String> messageSupplier) {
        return Optional.ofNullable(toCheck)
                       .map(c -> true)
                       .orElseThrow(() -> new IllegalArgumentException(messageSupplier.get()));
    }

    /**
     * Checks if the provided string is not null and not empty.
     *
     * @param str
     *         the string to check
     *
     * @return {@code true} if {@code str} is not null and not empty; {@code false} otherwise.
     */
    public static boolean isNotNullOrEmpty(String str) {
        return Objects.nonNull(str) && !str.isEmpty();
    }

    /**
     * Checks if the provided string is not null and not empty, throwing an exception with a custom message if it is.
     *
     * @param str
     *         the string to check
     * @param messageSupplier
     *         supplies the custom message to include in the exception if {@code str} is null or empty
     *
     * @return {@code true} if {@code str} is not null and not empty.
     *
     * @throws IllegalArgumentException
     *         if {@code str} is null or empty, with the custom message provided by {@code messageSupplier}
     */
    public static boolean isNotNullOrEmpty(String str, Supplier<String> messageSupplier) {
        return Optional.ofNullable(str)
                       .filter(s -> !s.isEmpty())
                       .map(s -> true)
                       .orElseThrow(() -> new IllegalArgumentException(messageSupplier.get()));
    }
}
