/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
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
package io.github.colindj1120.materialdesignui.factories.styling;

import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * A builder class for creating {@link CssMetaData} instances for JavaFX styleable objects. This class provides a fluent API to define various aspects of CSS metadata, including the property name,
 * converter, initial value, inheritance behavior, and associated sub-properties. It also allows for the specification of custom functions to determine if a property is settable, to get the property
 * value, and to obtain the initial value of the property.
 * <p>
 * The builder is designed to be used in scenarios where custom CSS properties need to be created for JavaFX components, enabling developers to define these properties in a structured and flexible
 * manner. This approach ensures that all necessary aspects of a CSS property are accounted for and can be easily integrated into the JavaFX styling system.
 * </p>
 * <p>
 * Example Usage:
 * <pre>
 * {@code
 * CssBuilder<Styleable, Color> colorCssBuilder = new CssBuilder<Styleable, Color>()
 *     .property("-custom-color")
 *     .converter(StyleConverter.getColorConverter())
 *     .initialValue(Color.BLACK)
 *     .inherits(true)
 *     .isSettableFunction(styleable -> true)
 *     .propertyGetterFunction(styleable -> styleable.styleableProperty())
 *     .build();
 * }
 * </pre>
 * </p>
 *
 * @param <S>
 *         The type of the styleable object that the CSS metadata is associated with.
 * @param <V>
 *         The type of the value that the CSS metadata represents.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CssMetaData
 * @see Styleable
 * @see StyleConverter
 * @see StyleableProperty
 */
public class CssFactory<S extends Styleable, V> {
    public static <T extends Styleable,U> CssFactory<T,U> create() {
        return new CssFactory<>();
    }

    private String                                    property                = null;
    private StyleConverter<?, V>                      converter               = null;
    private V                                         initialValue            = null;
    private boolean                                   inherits                = false;
    private List<CssMetaData<? extends Styleable, ?>> subProperties           = null;
    private Function<S, Boolean>                      isSettableFunction      = null;
    private Function<S, StyleableProperty<V>>         propertyGetterFunction  = null;
    private Function<S, V>                            getInitialValueFunction = null;

    /**
     * Sets the CSS property for the builder.
     *
     * @param property
     *         the CSS property to be set
     *
     * @return the CssBuilder instance
     */
    public CssFactory<S, V> property(String property) {
        this.property = property;
        return this;
    }

    /**
     * Sets the converter for parsing and formatting the CSS property value.
     *
     * @param converter
     *         The converter to be set.
     *
     * @return The CssBuilder instance with the converter set.
     */
    public CssFactory<S, V> converter(StyleConverter<?, V> converter) {
        this.converter = converter;
        return this;
    }

    /**
     * Sets the initial value for the CssBuilder.
     *
     * @param initialValue
     *         the initial value to set
     *
     * @return the CssBuilder instance
     */
    public CssFactory<S, V> initialValue(V initialValue) {
        this.initialValue = initialValue;
        return this;
    }

    /**
     * Sets whether the CSS property should be inherited by child elements or not.
     *
     * @param inherits
     *         a boolean value indicating whether the property should be inherited
     *
     * @return the CssBuilder instance with the updated 'inherits' value
     */
    public CssFactory<S, V> inherits(boolean inherits) {
        this.inherits = inherits;
        return this;
    }

    /**
     * Sets the subProperties for the CSS builder.
     *
     * @param subProperties
     *         the list of sub-properties to be set
     *
     * @return the modified CSS builder with the updated subProperties
     */
    public CssFactory<S, V> subProperties(List<CssMetaData<? extends Styleable, ?>> subProperties) {
        this.subProperties = subProperties;
        return this;
    }

    /**
     * Sets the function that determines if the property is settable for a given target object.
     *
     * @param isSettableFunction
     *         the function that determines if the property is settable. The function takes a target object of type T as parameter and returns a boolean value. Return true if the property is settable
     *         for the given target object, otherwise false.
     *
     * @return the CssBuilder instance with the updated isSettableFunction.
     */
    public CssFactory<S, V> isSettableFunction(Function<S, Boolean> isSettableFunction) {
        this.isSettableFunction = isSettableFunction;
        return this;
    }

    /**
     * Sets the property getter function for the CssBuilder.
     *
     * @param propertyGetterFunction
     *         the function that retrieves the StyleableProperty for the given object
     *
     * @return the CssBuilder instance
     */
    public CssFactory<S, V> propertyGetterFunction(Function<S, StyleableProperty<V>> propertyGetterFunction) {
        this.propertyGetterFunction = propertyGetterFunction;
        return this;
    }

    /**
     * Sets the function used to retrieve the initial value for the CSS property.
     *
     * @param getInitialValueFunction
     *         the function used to retrieve the initial value
     *
     * @return the CssBuilder instance
     */
    public CssFactory<S, V> getInitialValueFunction(Function<S, V> getInitialValueFunction) {
        this.getInitialValueFunction = getInitialValueFunction;
        return this;
    }

    /**
     * Builds and returns a {@link CssMetaData} object based on the parameters set in the builder. This method finalizes the construction of the CSS metadata by using the provided property name,
     * converter, initial value, inheritance behavior, sub-properties, and custom functions for property handling in JavaFX styleable objects.
     * <p>
     * Before building, the method checks for the non-nullness of essential fields like property, converter, isSettable function, and propertyGetter function. If any of these are null, an
     * IllegalArgumentException is thrown, ensuring that all necessary data is provided to create a valid {@code CssMetaData} instance.
     * <p>
     * The resulting {@code CssMetaData} object is tailored to the specific needs of the styleable property, incorporating custom behavior as defined by the builder's methods.
     *
     * @return A fully constructed {@code CssMetaData} object.
     *
     * @throws IllegalArgumentException
     *         if any required field (property, converter, isSettableFunction, or propertyGetterFunction) is null.
     * @see CssMetaData
     * @see Styleable
     * @see StyleConverter
     * @see StyleableProperty
     */
    public CssMetaData<S, V> build() {
        if (Objects.isNull(property)) {
            throw new IllegalArgumentException("Property cannot be null in the CssBuilder");
        }

        if (Objects.isNull(converter)) {
            throw new IllegalArgumentException("Converter cannot be null in the CssBuilder");

        }

        if (Objects.isNull(isSettableFunction)) {
            throw new IllegalArgumentException("IsSettable function cannot be null in the CssBuilder");
        }

        if (Objects.isNull(propertyGetterFunction)) {
            throw new IllegalArgumentException("PropertyGetter function cannot be null in the CssBuilder");
        }

        return new CssMetaData<>(property, converter, initialValue, inherits, subProperties) {
            @Override
            public V getInitialValue(S styleable) {
                return Optional.ofNullable(getInitialValueFunction)
                               .map(f -> f.apply(styleable))
                               .orElse(super.getInitialValue(styleable));
            }

            @Override
            public boolean isSettable(S styleable) {
                return Objects.nonNull(styleable) && isSettableFunction.apply(styleable);
            }

            @Override
            public StyleableProperty<V> getStyleableProperty(S styleable) {
                return propertyGetterFunction.apply(styleable);
            }
        };
    }
}
