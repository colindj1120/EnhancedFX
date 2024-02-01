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
package io.github.colindj1120.materialdesignui.styling;

import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;

import java.util.*;
import java.util.function.Function;

/**
 * Manages the CSS metadata properties for Styleable objects within a JavaFX application. This class acts as a central repository for CSS property metadata, allowing for dynamic addition, retrieval,
 * and management of {@link CssMetaData} instances.
 * <p>
 * The {@code StyleablePropertiesManager} is initialized with a list of existing CSS metadata from a parent styleable object. It provides methods to add new CSS metadata via a {@link CssBuilder},
 * retrieve metadata based on property names, and obtain an immutable list of all managed CSS metadata.
 * </p>
 * <p>
 * This class is particularly useful in scenarios where custom styling properties are required for JavaFX components, enabling developers to define and manipulate these properties in a structured and
 * efficient manner. It enhances the ability to create custom styled components with dynamic styling capabilities.
 * </p>
 * <p>
 * Additionally, the inner {@code CssBuilder} class offers a fluent API for creating {@code CssMetaData} instances, simplifying the process of defining new styleable properties.
 * </p>
 * <p>
 * Example Usage:
 * <pre>
 * {@code
 * StyleablePropertiesManager manager = new StyleablePropertiesManager(parentCssMetaData);
 * manager.addCssMetaData(new CssBuilder<Styleable, Color>()
 *                          .property("-custom-color")
 *                          .converter(StyleConverter.getColorConverter())
 *                          .initialValue(Color.BLACK)
 *                          .isSettableFunction(node -> node.customColor != null && !node.customColor.isBound())
 *                          .propertyGetter(node -> node.customColor));
 * CssMetaData<Styleable, Color> metaData = manager.findCssMetaData("-custom-color");
 * }
 * </pre>
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CssMetaData
 * @see Styleable
 * @see StyleableProperty
 */
@SuppressWarnings("unused")
public class StyleablePropertiesManager {
    private final List<CssMetaData<? extends Styleable, ?>> cssMetaDataList;

    /**
     * Constructs a new {@code StyleablePropertiesManager} with an initial list of CSS metadata. This constructor initializes the manager with a given list of {@link CssMetaData} instances, typically
     * derived from a parent styleable object. It allows for the subsequent addition and management of custom CSS metadata for JavaFX styleable objects.
     *
     * @param parentCssMetaData
     *         The initial list of CSS metadata from a parent styleable object.
     */
    public StyleablePropertiesManager(List<CssMetaData<? extends Styleable, ?>> parentCssMetaData) {
        cssMetaDataList = new ArrayList<>(parentCssMetaData);
    }

    /**
     * Adds a new CSS metadata item to the manager. This method accepts a {@link CssBuilder} instance, builds the CSS metadata, and adds it to the manager's internal list. This is useful for
     * dynamically adding new styleable properties to a JavaFX component.
     *
     * @param <T>
     *         The type of the styleable object.
     * @param <V>
     *         The type of the value represented by the CSS metadata.
     * @param builder
     *         The builder used to create the CSS metadata item.
     */
    public <T extends Styleable, V> void addCssMetaData(CssBuilder<T, V> builder) {
        cssMetaDataList.add(builder.build());
    }

    /**
     * Finds and returns the CSS metadata for a specified CSS property. This method searches the internal list of CSS metadata for a property matching the given name. If found, it returns the
     * corresponding {@link CssMetaData} instance, otherwise it throws an IllegalArgumentException.
     *
     * @param <S>
     *         The type of the styleable object.
     * @param <V>
     *         The type of the value represented by the CSS metadata.
     * @param cssProperty
     *         The name of the CSS property to search for.
     *
     * @return The {@code CssMetaData} instance corresponding to the specified property.
     *
     * @throws IllegalArgumentException
     *         If no CSS metadata is found for the given property name.
     */
    @SuppressWarnings("unchecked")
    public <S extends Styleable, V> CssMetaData<S, V> findCssMetaData(String cssProperty) {
        try {
            return (CssMetaData<S, V>) cssMetaDataList.stream()
                                                      .filter(cssMetaData -> cssMetaData.getProperty()
                                                                                        .equals(cssProperty))
                                                      .findFirst()
                                                      .orElseThrow(() -> new IllegalArgumentException("Couldn't find CssMetaData for property: " + cssProperty));

        }
        catch (ClassCastException e) {
            throw new IllegalArgumentException("Error Finding Metadata value found can't be casted to type passed in for S and V", e);
        }
    }

    /**
     * Provides an unmodifiable view of the CSS metadata list managed by this instance. This method returns a list of all {@link CssMetaData} instances currently managed, ensuring that the returned
     * list cannot be modified directly.
     *
     * @return An unmodifiable list of {@code CssMetaData} objects.
     */
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaDataList() {
        return Collections.unmodifiableList(cssMetaDataList);
    }

    /**
     * A builder class for creating {@link CssMetaData} instances for JavaFX styleable objects. This class provides a fluent API to define various aspects of CSS metadata, including the property name,
     * converter, initial value, inheritance behavior, and associated sub-properties. It also allows for the specification of custom functions to determine if a property is settable, to get the
     * property value, and to obtain the initial value of the property.
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
     * @see CssMetaData
     * @see Styleable
     * @see StyleConverter
     * @see StyleableProperty
     */
    public static class CssBuilder<S extends Styleable, V> {
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
        public CssBuilder<S, V> property(String property) {
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
        public CssBuilder<S, V> converter(StyleConverter<?, V> converter) {
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
        public CssBuilder<S, V> initialValue(V initialValue) {
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
        public CssBuilder<S, V> inherits(boolean inherits) {
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
        public CssBuilder<S, V> subProperties(List<CssMetaData<? extends Styleable, ?>> subProperties) {
            this.subProperties = subProperties;
            return this;
        }

        /**
         * Sets the function that determines if the property is settable for a given target object.
         *
         * @param isSettableFunction
         *         the function that determines if the property is settable. The function takes a target object of type T as parameter and returns a boolean value. Return true if the property is
         *         settable for the given target object, otherwise false.
         *
         * @return the CssBuilder instance with the updated isSettableFunction.
         */
        public CssBuilder<S, V> isSettableFunction(Function<S, Boolean> isSettableFunction) {
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
        public CssBuilder<S, V> propertyGetterFunction(Function<S, StyleableProperty<V>> propertyGetterFunction) {
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
        public CssBuilder<S, V> getInitialValueFunction(Function<S, V> getInitialValueFunction) {
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
}
