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
package io.github.colindj1120.enhancedfx.base.factory;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;

/**
 * Facilitates the creation of styleable object properties of a generic type. This class offers a static method to obtain a builder for {@link EFXStyleableObjectProperty}, designed to create
 * object properties with customizable styling options.
 */
public class ExtendedStyleableObjectPropertyFactory {
    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private ExtendedStyleableObjectPropertyFactory() {}

    /**
     * Generates a new {@link EFXStyleableObjectProperty.Builder} instance for styleable object properties. This generic static method provides a builder for creating instances of
     * {@code EFXStyleableObjectProperty} with a specified type parameter. The builder offers a fluent API, enabling easy configuration and customization of the styleable property.
     * <p>
     * This method is particularly useful when creating styleable properties for various object types in UI components, allowing for a high degree of flexibility and customization in UI styling.
     * The generic nature of this method makes it adaptable to a wide range of object types.
     * </p>
     *
     * @param <T>
     *         the type of the object for which the property is to be created
     *
     * @return a builder for creating a customized {@code EFXStyleableObjectProperty} of type {@code T}
     *
     * @see EFXStyleableObjectProperty
     * @see EFXStyleableObjectProperty.Builder
     */
    public static <T> EFXStyleableObjectProperty.Builder<T> builder() {
        return new EFXStyleableObjectProperty.Builder<>();
    }

}
