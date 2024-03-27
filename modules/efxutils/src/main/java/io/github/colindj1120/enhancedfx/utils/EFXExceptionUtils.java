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
import javafx.scene.Node;

/**
 * The {@code EFXExceptionUtils} class provides utility functions related to exception handling within the EnhancedFX framework.
 *
 * <p>It offers specialized methods for generating and managing exceptions, particularly focusing on scenarios where invalid instance types are encountered. This class is part of the EnhancedFX utility package,
 * aimed at aiding developers in creating more robust and error-resistant JavaFX applications.</p>
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li><b>Exception Generation:</b> Facilitates the creation of customized exceptions, particularly {@link InvalidInstanceOfException}, to signal errors related to incorrect type usage within the
 *   EnhancedFX framework.</li>
 *   <li><b>Utility-Oriented Design:</b> Designed purely as a utility class, it contains static methods only and cannot be instantiated or extended, reinforcing its utility role within the library.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Here's how you might use {@code EFXExceptionUtils} to generate an {@code InvalidInstanceOfException} when a node is not of an expected type:
 * <pre>{@code
 * public void checkNodeType(Node node) {
 *     if (!(node instanceof DesiredType)) {
 *         throw EFXExceptionUtils.getInvalidInstanceOfException(node, DesiredType.class, this.getClass(), "checkNodeType");
 *     }
 * }
 * }</pre>
 *
 * <p>This example demonstrates how to use {@code EFXExceptionUtils} to generate and throw a specific exception when a node does not match the desired type. It aids in maintaining type safety and providing
 * clear error messages within EnhancedFX-based applications.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InvalidInstanceOfException
 */
public class EFXExceptionUtils {
    /**
     * Private constructor to prevent instantiation of the {@code EFXExceptionUtils} class.
     *
     * <p>
     * This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code EFXExceptionUtils}.
     * </p>
     */
    private EFXExceptionUtils() {}

    public static InvalidInstanceOfException getInvalidInstanceOfException(Node node, Class<?> instanceType, Class<?> implementingClass, String methodName) throws InvalidInstanceOfException {
        String errorMessage = String.format("Node:{%s} is not an instance of %s in {Class: %s, Method: %s}", node.toString(), instanceType.getSimpleName(), implementingClass.getSimpleName(), methodName);
        return new InvalidInstanceOfException(errorMessage);
    }
}
