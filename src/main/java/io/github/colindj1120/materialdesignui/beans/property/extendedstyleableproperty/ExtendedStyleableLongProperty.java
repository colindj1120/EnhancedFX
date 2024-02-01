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
package io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty;

import io.github.colindj1120.materialdesignui.beans.property.base.ExtendedStyleablePropertyBase;
import io.github.colindj1120.materialdesignui.consumers.TriConsumer;
import javafx.css.CssMetaData;
import javafx.css.StyleOrigin;
import javafx.css.Styleable;
import javafx.css.StyleableLongProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * ExtendedStyleableLongProperty is an enhanced version of JavaFX's {@link StyleableLongProperty} with additional features and flexibility. This class integrates closely with JavaFX's CSS engine,
 * allowing for more sophisticated styling and interaction with UI components.
 * <p>
 * Features:
 * <ul>
 *     <li>Custom invalidation logic through provided callbacks.</li>
 *     <li>Integration with CSS metadata for enhanced styling capabilities.</li>
 *     <li>Support for dynamic changes in property values and corresponding CSS updates.</li>
 * </ul>
 * </p>
 * <p>
 * The class offers various customization options via invalidation callbacks:
 * <ul>
 *     <li>{@code invalidatedVoidCallback}: A simple callback invoked when the property value changes.</li>
 *     <li>{@code invalidatedPropCallback}: A callback with access to the property itself for more complex scenarios.</li>
 *     <li>{@code invalidatedCachedCallback}: A callback that provides the old value, new value, and a consumer for the new value, enabling caching mechanisms.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The property's name, bean, and associated CSS metadata can be specified, ensuring full control over
 * the property's behavior and presentation in the JavaFX application.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see StyleableLongProperty
 * @see ExtendedStyleablePropertyBase
 */
public class ExtendedStyleableLongProperty extends StyleableLongProperty implements ExtendedStyleablePropertyBase {
    private final Consumer<Void>                                           invalidatedVoidCallback;
    private final Consumer<StyleableLongProperty>                          invalidatedPropCallback;
    private final TriConsumer<StyleableLongProperty, Long, Consumer<Long>> invalidatedCachedCallback;
    private final String                                                   name;
    private final Object                                                   bean;
    private final CssMetaData<? extends Styleable, Number>                 cssMetaData;
    private       Long                                                     oldValue = get();

    /**
     * Constructs an ExtendedStyleableLongProperty with the specified builder configurations. This constructor initializes the property based on the settings provided in the {@link Builder}. It sets
     * up the custom invalidation callbacks, the property's name, bean, and associated CSS metadata. The property is initialized without a default value, relying on the default initialization of
     * StyleableLongProperty.
     *
     * @param builder
     *         The builder containing configurations for this property.
     */
    private ExtendedStyleableLongProperty(Builder builder) {
        super();
        this.invalidatedVoidCallback   = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback   = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name                      = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean                      = builder.bean;
        this.cssMetaData               = builder.cssMetaData;

        invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, this.getClass());
    }

    /**
     * Constructs an ExtendedStyleableLongProperty with the specified builder configurations and a default value. This constructor is similar to the one above but also initializes the property's value
     * to the given default. It is particularly useful when a predefined initial value is required for the property.
     *
     * @param builder
     *         The builder containing configurations for this property.
     * @param defaultValue
     *         The initial value to set for this property.
     */
    private ExtendedStyleableLongProperty(Builder builder, Long defaultValue) {
        super(defaultValue);
        this.invalidatedVoidCallback   = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback   = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name                      = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean                      = builder.bean;
        this.cssMetaData               = builder.cssMetaData;
    }

    /**
     * Applies a specified style value to this property. This method is overridden to handle the application of a style value directly to this property, integrating with JavaFX's CSS styling system.
     * This method had to be overridden because something behind the scenes is causing an origin of null to be passed in during loading and this was causing some of the states to be reset when they
     * shouldn't be.
     *
     * @param origin
     *         The origin of the style being applied.
     * @param v
     *         The new value to apply.
     */
    @Override
    public void applyStyle(StyleOrigin origin, Number v) {
        if (Objects.nonNull(origin)) {
            super.applyStyle(origin, v);
        }
    }

    /**
     * Returns the CSS metadata for this property. CSS metadata integrates the property with JavaFX's CSS styling system, allowing for enhanced styling capabilities.
     *
     * @return The CSS metadata for the property.
     */
    @Override
    public CssMetaData<? extends Styleable, Number> getCssMetaData() {
        return cssMetaData;
    }

    /**
     * Invoked when the property's value is invalidated. This method triggers the appropriate invalidation callback, based on the type of callback set during the property's construction. It is a
     * crucial part of the property's change notification mechanism, ensuring that any side effects of a property value change are properly handled.
     */
    @Override
    protected void invalidated() {
        Optional.ofNullable(invalidatedVoidCallback)
                .ifPresent(callback -> callback.accept(null));
        Optional.ofNullable(invalidatedPropCallback)
                .ifPresent(callback -> callback.accept(this));
        Optional.ofNullable(invalidatedCachedCallback)
                .ifPresent(callback -> callback.accept(this, oldValue, val -> oldValue = val));
    }

    /**
     * Retrieves the bean associated with this property.
     *
     * @return The bean associated with this property.
     */
    @Override
    public Object getBean() {
        return bean;
    }

    /**
     * Returns the name of the property.
     *
     * @return The name of the property.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Builder class for {@link ExtendedStyleableLongProperty}. This class follows the builder pattern to provide a user-friendly way to configure and instantiate an ExtendedStyleableLongProperty.
     * <p>
     * The Builder allows setting up the following:
     * <ul>
     *     <li>Invalidation callbacks for various scenarios.</li>
     *     <li>Name and bean of the property for identification and association purposes.</li>
     *     <li>CSS metadata for integration with JavaFX's styling system.</li>
     *     <li>Default value to initialize the property with a specific value.</li>
     * </ul>
     * </p>
     * <p>
     * This approach encapsulates the construction logic, simplifying the creation process and
     * ensuring the robust configuration of ExtendedStyleableLongProperty instances.
     * </p>
     */
    public static class Builder {
        private Consumer<Void>                                           invalidatedVoidCallback;
        private Consumer<StyleableLongProperty>                          invalidatedPropCallback;
        private TriConsumer<StyleableLongProperty, Long, Consumer<Long>> invalidatedCachedCallback;
        private String                                                   name;
        private Object                                                   bean;
        private CssMetaData<? extends Styleable, Number>                 cssMetaData;
        private Long                                                     defaultValue;

        /**
         * Sets the callback function to be invoked when the property value changes. The callback function takes no arguments and returns no value.
         *
         * @param invalidatedVoidCallback
         *         The callback function to be invoked.
         *
         * @return The same Builder instance.
         */
        public Builder invalidatedVoidCallback(Consumer<Void> invalidatedVoidCallback) {
            this.invalidatedVoidCallback = invalidatedVoidCallback;
            return this;
        }

        /**
         * Sets the callback function to be invoked when the property is invalidated.
         *
         * @param invalidatedPropCallback
         *         The callback function to be invoked when the property is invalidated.
         *
         * @return The builder instance.
         */
        public Builder invalidatedPropCallback(Consumer<StyleableLongProperty> invalidatedPropCallback) {
            this.invalidatedPropCallback = invalidatedPropCallback;
            return this;
        }

        /**
         * Sets the callback function for when the cached value of the property is invalidated. The callback function receives the StyleableLongProperty instance, the old value, and a Consumer to set
         * the new value.
         *
         * @param invalidatedCachedCallback
         *         the callback function to be called when the cached value is invalidated
         *
         * @return the Builder instance
         */
        public Builder invalidatedCachedCallback(TriConsumer<StyleableLongProperty, Long, Consumer<Long>> invalidatedCachedCallback) {
            this.invalidatedCachedCallback = invalidatedCachedCallback;
            return this;
        }

        /**
         * Sets the name of the property.
         *
         * @param name
         *         The name of the property.
         *
         * @return The Builder instance.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the bean for the {@link ExtendedStyleableLongProperty} instance.
         *
         * @param bean
         *         The bean to set.
         *
         * @return The {@link Builder} instance with the bean set.
         */
        public Builder bean(Object bean) {
            this.bean = bean;
            return this;
        }

        /**
         * Specifies the CSS metadata for the ExtendedStyleableLongProperty. This method sets the CSS metadata, which integrates the property with JavaFX's CSS styling system, allowing for enhanced
         * styling capabilities.
         *
         * @param cssMetaData
         *         The CSS metadata for the property.
         *
         * @return The Builder instance.
         */
        public Builder cssMetaData(CssMetaData<? extends Styleable, Number> cssMetaData) {
            this.cssMetaData = cssMetaData;
            return this;
        }

        /**
         * Sets the default value for the property.
         *
         * @param defaultValue
         *         The default value to be set.
         *
         * @return The Builder object.
         */
        public Builder defaultValue(Long defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Constructs and returns an instance of ExtendedStyleableLongProperty. The method validates the provided settings and applies them to create a fully configured ExtendedStyleableLongProperty.
         * If a default value is provided, it initializes the property with this value; otherwise, it uses a standard constructor.
         *
         * @return An instance of ExtendedStyleableLongProperty with the specified configuration.
         *
         * @throws IllegalArgumentException
         *         if essential configuration parameters are missing.
         */
        public ExtendedStyleableLongProperty build() {
            if (Objects.isNull(defaultValue)) {
                return new ExtendedStyleableLongProperty(this);
            } else {
                return new ExtendedStyleableLongProperty(this, defaultValue);
            }
        }
    }
}



