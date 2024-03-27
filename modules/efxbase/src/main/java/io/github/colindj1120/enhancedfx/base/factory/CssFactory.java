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

import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.scene.layout.Region;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * The {@code CssFactory} class serves as a builder for creating {@link CssMetaData} instances, which define the CSS properties applicable to JavaFX {@link Styleable} objects.
 *
 * <p>This class streamlines the process of defining custom CSS properties, including their types, default values, and inheritance behavior. It enables developers to create rich, stylable JavaFX components with
 * custom properties, enhancing the application's visual design and user experience.</p>
 *
 * <h2>Features</h2>
 * <ul>
 *   <li>Generic creation method facilitating type-safe construction of {@code CssFactory} instances for any {@link Styleable}.</li>
 *   <li>Support for defining CSS property names, value types, and converters for type-safe CSS value translation.</li>
 *   <li>Option to specify initial values, inheritance, and associated sub-properties for comprehensive CSS styling.</li>
 *   <li>Customizable property settable and getter functions for enhanced control over property application and retrieval.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * The following example demonstrates how to use {@code CssFactory} to define a custom CSS property for a JavaFX component:
 * <pre>
 * {@code
 * CssMetaData<MyCustomControl, Number> customOpacityProperty = CssFactory.<MyCustomControl, Number>create()
 *     .property("-custom-opacity")
 *     .converter(StyleConverter.getNumberConverter())
 *     .initialValue(1.0)
 *     .inherits(true)
 *     .isSettableFunction(MyCustomControl::isCustomOpacitySettable)
 *     .propertyGetterFunction(MyCustomControl::customOpacityProperty)
 *     .build();
 * }
 * </pre>
 *
 * <p>This example illustrates the definition of a custom opacity property for {@code MyCustomControl}, specifying its name, type, initial value, and inheritance behavior. It uses the {@code CssFactory}
 * builder pattern to set up and build the {@code CssMetaData} instance, which can then be included in the control's CSS metadata list.</p>
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
    /**
     * Creates and returns a new instance of {@code CssFactory}. This static factory method serves as a generic constructor for creating instances of {@code CssFactory} tailored to specific types of
     * {@link Styleable} objects and their associated value types.
     *
     * <p>The method is generic, allowing it to be used for any subclass of {@link Styleable}, which represents any JavaFX node that can be styled using CSS, and any value type {@code V} that corresponds to
     * the styleable properties of interest. This design facilitates a flexible and type-safe way to create and manage CSS factories for various UI components and their styling attributes.</p>
     *
     * <h2>Example Usage:</h2>
     * <pre>
     * {@code
     * CssFactory<Region, String> regionCssFactory = CssFactory.create();
     * }
     * </pre>
     *
     * <p>In this example, {@code CssFactory.create()} is used to instantiate a new {@code CssFactory} for {@link Region} objects with a value type of {@code String}. This enables the creation of CSS-related
     * functionalities specific to {@code Region} nodes.</p>
     *
     * @param <S>
     *         the type parameter extending {@link Styleable}, indicating the type of {@code Styleable} objects this factory will work with
     * @param <V>
     *         the type parameter representing the value type associated with the styleable properties managed by this factory
     *
     * @return a new instance of {@code CssFactory} for the specified {@code Styleable} and value type
     */
    public static <S extends Styleable, V> CssFactory<S, V> create() {
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
     * Constructs a new instance of {@code CssFactory}. This private constructor prevents direct instantiation of the {@code CssFactory} class from outside the class itself, enforcing the use of the static
     * factory method {@link #create()} for obtaining instances.
     *
     * <p>The design choice to restrict instantiation through a private constructor ensures a controlled creation process, allowing for future enhancements or modifications to the instance creation logic
     * without affecting the clients of this class. It supports the singleton or factory pattern by centralizing the instantiation logic within the {@code create} method, providing a clear and consistent
     * approach for obtaining instances of {@code CssFactory}.</p>
     */
    private CssFactory() {}

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
     *         the function that determines if the property is settable. The function takes a target object of type T as parameter and returns a boolean value. Return true if the property is settable for the
     *         given target object, otherwise false.
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
     * Builds and returns a customized {@code CssMetaData} object based on the previously specified parameters.
     *
     * <p>This method finalizes the creation of a {@code CssMetaData} instance, ensuring all mandatory properties are set and applying custom behavior through overridden methods. Before constructing the
     * {@code CssMetaData}, this method validates the non-nullity of essential properties such as {@code property}, {@code converter}, {@code isSettableFunction}, and {@code propertyGetterFunction}. If any of
     * these are null, an {@link IllegalArgumentException} is thrown, indicating a misconfiguration in the builder setup.</p>
     *
     * <h2>{@code CssMetaData} includes:</h2>
     * <ul>
     *     <li>{@code getInitialValue(S styleable)}: Determines the initial value for the CSS property, optionally using a provided function. If the function is not specified or does not yield a result, the
     *     initial value specified at construction is used.</li>
     *     <li>{@code isSettable(S styleable)}: Checks whether the CSS property can be set on the given {@code styleable}, based on a supplied function.</li>
     *     <li>{@code getStyleableProperty(S styleable)}: Retrieves the {@link StyleableProperty} for the CSS property from the given {@code styleable}, using a provided function.</li>
     * </ul>
     *
     * <p>This method enables the creation of {@code CssMetaData} objects that are highly configurable and adaptable to specific requirements, enhancing the flexibility and functionality of CSS styling in
     * JavaFX applications.</p>
     *
     * @return a fully constructed and customized {@code CssMetaData} object
     *
     * @throws IllegalArgumentException
     *         if any of the essential properties ({@code property}, {@code converter}, {@code isSettableFunction}, or {@code propertyGetterFunction}) are null
     */
    public CssMetaData<S, V> build() {
        EFXObjectUtils.isNotNull(property, () -> "Property cannot be null in the CssBuilder");
        EFXObjectUtils.isNotNull(converter, () -> "Converter cannot be null in the CssBuilder");
        EFXObjectUtils.isNotNull(isSettableFunction, () -> "IsSettable function cannot be null in the CssBuilder");
        EFXObjectUtils.isNotNull(propertyGetterFunction, () -> "PropertyGetter function cannot be null in the CssBuilder");

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
