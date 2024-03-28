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

import io.github.colindj1120.enhancedfx.utils.exceptions.InvalidInstanceOfException;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * The {@code EFXObjectUtils} class provides a collection of static utility methods for object validation and resource verification. It simplifies common tasks such as null checks, string validation, and
 * ensuring the existence of classpath resources. Designed for direct static import, it allows for concise and readable validation code throughout an application.
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

    //region Null/Not Null Checks
    //*****************************************************************
    // Null/Not Null Checks
    //*****************************************************************

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

    //endregion Null/Not Null Checks

    //region Instance Checks
    //*****************************************************************
    // Instance Checks
    //*****************************************************************

    public static <T, U> T checkInstanceOfAndCast(U obj, Class<T> clazz, Supplier<String> messageSupplier) throws InvalidInstanceOfException {
        if (clazz.isInstance(obj)) {
            return clazz.cast(obj);
        } else {
            throw new InvalidInstanceOfException(messageSupplier.get());
        }
    }

    //endregion Instance Checks

    //region Execute Based On
    //*****************************************************************
    // Execute Based On
    //*****************************************************************

    /**
     * Compares two objects for equality, executing one of two provided {@code Runnable} instances based on the comparison result.
     *
     * <p>If the objects are equal (as determined by {@link Objects#equals(Object, Object)}), the {@code onTrue} is executed. If the objects are not equal, the {@code onFalse} is executed. This generic method
     * allows for a flexible handling of equality checks between any two objects, with customizable actions for each outcome.</p>
     *
     * @param <T>
     *         the type of the objects being compared
     * @param obj1
     *         the first object to compare
     * @param obj2
     *         the second object to compare
     * @param onTrue
     *         the {@code Runnable} to execute if the objects are equal
     * @param onFalse
     *         the {@code Runnable} to execute if the objects are not equal
     */
    public static <T> void executeBasedOnEquality(T obj1, T obj2, Runnable onTrue, Runnable onFalse) {
        if (Objects.equals(obj1, obj2)) {
            onTrue.run();
        } else {
            onFalse.run();
        }
    }

    /**
     * Compares two objects for equality, executing one of two provided {@link Consumer} instances based on the comparison result.
     *
     * <p>If the objects are equal (as determined by {@link Objects#equals(Object, Object)}), the {@code onTrue} is executed with the objects as its input. If the objects are not equal, the {@code onFalse} is
     * executed with the objects as its input. This generic method enhances flexibility in handling equality checks between any two objects, allowing for actions that can make use of the compared objects within
     * the executed {@code Consumer}.</p>
     *
     * @param <T>
     *         the type of the objects being compared
     * @param obj1
     *         the first object to compare
     * @param obj2
     *         the second object to compare
     * @param onTrue
     *         the {@code Consumer<T>} to execute if the objects are equal, receiving the first object
     * @param onFalse
     *         the {@code Consumer<T>} to execute if the objects are not equal, receiving the first object
     */
    public static <T> void executeBasedOnEquality(T obj1, T obj2, Consumer<T> onTrue, Consumer<T> onFalse) {
        if (Objects.equals(obj1, obj2)) {
            onTrue.accept(obj1);
        } else {
            onFalse.accept(obj1);
        }
    }

    /**
     * Executes actions based on the result of applying a predicate to an object.
     *
     * <p>If the predicate evaluates to {@code true} when applied to the given object, the {@code onTrue} action is executed. Otherwise, the {@code onFalse} action is executed. This method provides flexibility
     * in handling different scenarios based on whether the predicate condition is satisfied or not, allowing for actions to be performed accordingly.</p>
     *
     * @param <T>
     *         the type of the object being evaluated by the predicate
     * @param predicate
     *         the predicate to apply to the object
     * @param obj
     *         the object to which the predicate is applied
     * @param onTrue
     *         the {@code Runnable} action to execute if the predicate evaluates to {@code true}
     * @param onFalse
     *         the {@code Runnable} action to execute if the predicate evaluates to {@code false}
     */
    public static <T> void executeBasedOnPredicate(Predicate<T> predicate, T obj, Runnable onTrue, Runnable onFalse) {
        if (predicate.test(obj)) {
            onTrue.run();
        } else {
            onFalse.run();
        }
    }

    /**
     * Executes actions based on the result of applying a predicate to an object, with the option to consume the object.
     *
     * <p>If the predicate evaluates to {@code true} when applied to the given object, the {@code onTrue} consumer is applied to the object. Otherwise, the {@code onFalse} consumer is applied to the object.
     * This method provides flexibility in handling different scenarios based on whether the predicate condition is satisfied or not, allowing for actions to be performed accordingly.</p>
     *
     * @param <T>
     *         the type of the object being evaluated by the predicate
     * @param predicate
     *         the predicate to apply to the object
     * @param obj
     *         the object to which the predicate is applied
     * @param onTrue
     *         the {@code Consumer<T>} action to execute if the predicate evaluates to {@code true}
     * @param onFalse
     *         the {@code Consumer<T>} action to execute if the predicate evaluates to {@code false}
     */
    public static <T> void executeBasedOnPredicate(Predicate<T> predicate, T obj, Consumer<T> onTrue, Consumer<T> onFalse) {
        if (predicate.test(obj)) {
            onTrue.accept(obj);
        } else {
            onFalse.accept(obj);
        }
    }

    /**
     * Executes actions based on the result of a supplier.
     *
     * <p>If the supplied value is not {@code null}, the {@code onPresent} action is executed with the supplied value. Otherwise, the {@code onAbsent} action is executed. This method provides flexibility in
     * handling scenarios where actions need to be performed based on the presence or absence of a supplied value.</p>
     *
     * @param <T>
     *         the type of the supplied value
     * @param supplier
     *         the supplier providing the value
     * @param onPresent
     *         the {@code Consumer<T>} action to execute if the supplier provides a non-null value
     * @param onAbsent
     *         the {@code Runnable} action to execute if the supplier provides a null value
     */
    public static <T> void executeBasedOnSupplier(Supplier<T> supplier, Consumer<T> onPresent, Runnable onAbsent) {
        T value = supplier.get();
        Optional.ofNullable(value)
                .ifPresentOrElse(onPresent, onAbsent);
    }

    /**
     * Executes actions based on the result of applying a function to an input.
     *
     * <p>The function is applied to the input, and if the result is not {@code null}, the {@code onResult} action is executed with the result. Otherwise, the {@code onNull} action is executed. This method
     * provides flexibility in handling different scenarios based on the result of applying a function to an input.</p>
     *
     * @param <T>
     *         the type of the input to the function
     * @param <R>
     *         the type of the result of the function
     * @param input
     *         the input to apply the function to
     * @param function
     *         the function to apply to the input
     * @param onResult
     *         the {@code Consumer<R>} action to execute if the function result is not {@code null}
     * @param onNull
     *         the {@code Runnable} action to execute if the function result is {@code null}
     */
    public static <T, R> void executeBasedOnFunction(T input, Function<T, R> function, Consumer<R> onResult, Runnable onNull) {
        R result = function.apply(input);
        Optional.ofNullable(result)
                .ifPresentOrElse(onResult, onNull);
    }

    //endregion Execute Based On

}
