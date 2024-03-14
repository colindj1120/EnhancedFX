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
package io.github.colindj1120.enhancedfx.beans.property.extendedstyleableproperty;

import io.github.colindj1120.enhancedfx.beans.property.base.ExtendedStyleablePropertyBase;
import io.github.colindj1120.enhancedfx.consumers.TriConsumer;
import javafx.css.CssMetaData;
import javafx.css.StyleOrigin;
import javafx.css.Styleable;
import javafx.css.StyleableFloatProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static io.github.colindj1120.enhancedfx.beans.property.base.ExtendedStyleablePropertyBase.invalidatorsNullCheck;
import static io.github.colindj1120.enhancedfx.beans.property.base.ExtendedStyleablePropertyBase.nullCheck;

/**
 * The {@code ExtendedStyleableFloatProperty} class is a specialized implementation of {@link StyleableFloatProperty} that incorporates additional functionality to enhance interaction with JavaFX CSS. It
 * provides mechanisms to handle property invalidation with custom callbacks, making it a versatile tool for developers seeking to extend the capabilities of styleable properties within their JavaFX
 * applications.
 *
 * <p>
 * This class supports various callbacks for property invalidation, allowing for specific actions to be taken when the property value changes or becomes invalidated. These callbacks include:
 * <ul>
 *     <li>A void callback that does not require the property's current value.</li>
 *     <li>A property callback that provides the current {@code StyleableFloatProperty} instance, allowing direct interaction.</li>
 *     <li>A cached value callback that offers both the property and its old value, enabling comparisons and conditional logic based on changes.</li>
 * </ul>
 * Additionally, {@code ExtendedStyleableFloatProperty} is designed with a focus on customization and ease of use,
 * featuring a builder pattern for construction. This approach allows for a fluent configuration of properties, including
 * the property name, the JavaFX bean associated with the property, CSS metadata integration, and the specification of
 * a default value.
 * </p>
 *
 * <p>
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Enhanced CSS styling capabilities through {@link CssMetaData} integration.</li>
 *     <li>Customizable callbacks for handling property invalidation.</li>
 *     <li>Builder pattern for flexible and intuitive property configuration.</li>
 * </ul>
 * Usage of this class is intended for scenarios where advanced property management and styling customization are required,
 * offering a sophisticated toolset for JavaFX UI development.
 * </p>
 *
 * <p>
 * <em>Example Usage:</em>
 * <pre>{@code
 * ExtendedStyleableFloatProperty customProperty = new ExtendedStyleableFloatProperty.Builder()
 *         .bean(this)
 *         .name("customProperty")
 *         .cssMetaData(myCssMetaData)
 *         .defaultValue(1.0f)
 *         .invalidatedVoidCallback(() -> System.out.println("Property invalidated"))
 *         .build();
 * }</pre>
 * This class and its builder facilitate the creation of styleable properties that are deeply integrated with the
 * JavaFX styling system, providing developers with enhanced control and flexibility over UI appearance and behavior.
 * </p>
 *
 * @see StyleableFloatProperty
 * @see ExtendedStyleablePropertyBase
 * @see CssMetaData
 * @see Styleable
 * @see Consumer
 * @see TriConsumer
 */
public class ExtendedStyleableFloatProperty extends StyleableFloatProperty implements ExtendedStyleablePropertyBase {
    private final Consumer<Void>                                              invalidatedVoidCallback;
    private final Consumer<StyleableFloatProperty>                            invalidatedPropCallback;
    private final TriConsumer<StyleableFloatProperty, Float, Consumer<Float>> invalidatedCachedCallback;
    private final String                                                      name;
    private final Object                                                      bean;
    private final CssMetaData<? extends Styleable, Number>                    cssMetaData;
    private       Float                                                       oldValue = get();

    /**
     * Constructs an {@code ExtendedStyleableFloatProperty} using the provided {@code Builder} instance. This constructor initializes the property without a default value, configuring it with the options
     * specified in the builder.
     * <p>
     * It performs null checks on the builder and its critical fields to ensure a fully configured property. The property is associated with a bean, a name, CSS metadata, and optional callbacks for property
     * invalidation handling.
     * <p>
     * The callbacks include:
     * <ul>
     *     <li>A void callback for general invalidation handling.</li>
     *     <li>A property-specific callback that provides the property itself for more contextual actions upon invalidation.</li>
     *     <li>A cached value callback that facilitates actions based on the change from the old value to the new value.</li>
     * </ul>
     * This constructor is designed for use when no default value for the property is specified, relying on the superclasses
     * default initialization mechanism.
     *
     * @param builder
     *         The {@code Builder} instance containing the configuration for this property.
     *
     * @throws IllegalArgumentException
     *         if the builder or any required configuration option is null.
     */
    protected ExtendedStyleableFloatProperty(Builder builder) {
        super();
        nullCheck(builder, "ExtendedStyleableFloatProperty Builder", this.getClass());
        this.invalidatedVoidCallback = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean = builder.bean;
        this.cssMetaData = builder.cssMetaData;
    }

    /**
     * Constructs an {@code ExtendedStyleableFloatProperty} with a specified default value, using the provided {@code Builder} instance for other configuration options. This constructor extends the
     * functionality of the no-argument constructor by allowing the specification of an initial value for the property.
     * <p>
     * Similar to the no-default constructor, this constructor performs null checks on the builder and its critical configuration options. It sets up the property with a bean, a name, CSS metadata, and
     * callbacks for invalidation handling, but it also initializes the property's value to the provided {@code defaultValue}.
     * <p>
     * This constructor is suited for scenarios where an initial property value is known at construction time and needs to be explicitly set.
     *
     * @param builder
     *         The {@code Builder} instance containing the configuration for this property.
     * @param defaultValue
     *         The initial value to set for this property.
     *
     * @throws IllegalArgumentException
     *         if the builder or any required configuration option is null.
     */
    protected ExtendedStyleableFloatProperty(Builder builder, Float defaultValue) {
        super(defaultValue);
        nullCheck(builder, "ExtendedStyleableFloatProperty Builder", this.getClass());
        this.invalidatedVoidCallback = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean = builder.bean;
        this.cssMetaData = builder.cssMetaData;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public void applyStyle(StyleOrigin origin, Number v) {
        if (Objects.nonNull(origin)) {
            super.applyStyle(origin, v);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CssMetaData<? extends Styleable, Number> getCssMetaData() {
        return cssMetaData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getBean() {
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Checks if the value of this property is equal to the specified value.
     *
     * @param value The value to compare with the value of this property.
     * @return true if the values are equal, false otherwise.
     */
    public boolean valueEquals(Float value) {
        return getValue().equals(value);
    }

    /**
     * The {@code Builder} class for {@code ExtendedStyleableFloatProperty} provides a fluent interface for incrementally constructing an {@code ExtendedStyleableFloatProperty}. This builder allows for the
     * detailed configuration of the property, including its CSS metadata, name, bean, default value, and callbacks for various invalidation scenarios.
     * <p>
     * Usage of this builder facilitates the creation of a custom {@code ExtendedStyleableFloatProperty} tailored to specific needs, enhancing the property's integration within a JavaFX application's styling
     * and behavior management system.
     * <p>
     * Features and Options:
     * <ul>
     *     <li>{@code invalidatedVoidCallback}: Specifies a callback to be executed when the property is invalidated, without
     *     requiring the current value.</li>
     *     <li>{@code invalidatedPropCallback}: Defines a callback with access to the {@code StyleableFloatProperty}, allowing
     *     actions that involve the property itself upon invalidation.</li>
     *     <li>{@code invalidatedCachedCallback}: Sets a callback that provides the old value and a consumer for potentially
     *     updating a cached value, enabling complex change handling logic.</li>
     *     <li>{@code name}: Sets the name of the property, used for identification within the associated bean and potentially
     *     within CSS.</li>
     *     <li>{@code bean}: Associates the property with a JavaFX bean, embedding it within an object's property hierarchy.</li>
     *     <li>{@code cssMetaData}: Links the property with {@link CssMetaData}, allowing it to be styled through CSS.</li>
     *     <li>{@code defaultValue}: Defines an initial value for the property, setting its state prior to any external modifications.</li>
     * </ul>
     * <p>
     * The {@code build()} method finalizes the construction of the {@code ExtendedStyleableFloatProperty}, ensuring that all
     * required configurations are set. It performs null checks on essential fields to prevent misconfigurations.
     * <p>
     * Example Usage:
     * <pre>
     * ExtendedStyleableFloatProperty customProperty = new ExtendedStyleableFloatProperty.Builder()
     *         .bean(this)
     *         .name("customOpacity")
     *         .cssMetaData(CustomCssMetaData.CUSTOM_OPACITY)
     *         .defaultValue(1.0f)
     *         .invalidatedVoidCallback(() -> System.out.println("Opacity invalidated"))
     *         .build();
     * </pre>
     * This approach to constructing an {@code ExtendedStyleableFloatProperty} provides a clear and modular way to configure
     * property behavior and integration within the broader JavaFX styling system.
     */
    public static class Builder {
        private Consumer<Void>                                              invalidatedVoidCallback;
        private Consumer<StyleableFloatProperty>                            invalidatedPropCallback;
        private TriConsumer<StyleableFloatProperty, Float, Consumer<Float>> invalidatedCachedCallback;
        private String                                                      name;
        private Object                                                      bean;
        private CssMetaData<? extends Styleable, Number>                    cssMetaData;
        private Float                                                       defaultValue;

        /**
         * Sets the invalidation callback for void invalidation events.
         *
         * @param invalidatedVoidCallback
         *         The void invalidation callback to set.
         *
         * @return The builder instance.
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
         * @return The Builder object for method chaining.
         */
        public Builder invalidatedPropCallback(Consumer<StyleableFloatProperty> invalidatedPropCallback) {
            this.invalidatedPropCallback = invalidatedPropCallback;
            return this;
        }

        /**
         * Sets the invalidated cached callback for the builder.
         *
         * @param invalidatedCachedCallback
         *         The invalidated cached callback function to be set. It takes a StyleableFloatProperty, a Float, and a Consumer<Float> as parameters. The StyleableFloatProperty is the property that is being
         *         invalidated. The Float is the old value of the property. The Consumer<Float> is a callback function that can be used to update the old value of the property.
         *
         * @return The current instance of the builder.
         */
        public Builder invalidatedCachedCallback(TriConsumer<StyleableFloatProperty, Float, Consumer<Float>> invalidatedCachedCallback) {
            this.invalidatedCachedCallback = invalidatedCachedCallback;
            return this;
        }

        /**
         * Sets the name of the property.
         *
         * @param name
         *         the name of the property
         *
         * @return the Builder instance
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the bean associated with this Builder.
         *
         * @param bean
         *         the bean to be associated with this Builder
         *
         * @return the updated Builder object
         */
        public Builder bean(Object bean) {
            this.bean = bean;
            return this;
        }

        /**
         * Sets the CSS metadata for the property.
         *
         * @param cssMetaData
         *         The CSS metadata to set for the property.
         *
         * @return The builder instance.
         */
        public Builder cssMetaData(CssMetaData<? extends Styleable, Number> cssMetaData) {
            this.cssMetaData = cssMetaData;
            return this;
        }

        /**
         * Sets the default value for the property.
         *
         * @param defaultValue
         *         The default value for the property.
         *
         * @return The builder object.
         */
        public Builder defaultValue(Float defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Finalizes the construction of an {@code ExtendedStyleableFloatProperty} based on the current configuration of the builder. This method ensures that all required fields have been properly set and that
         * the property is fully prepared for use within a JavaFX application.
         *
         * <p>
         * Prior to constructing the property, this method performs several null checks to ensure the integrity of the configuration:
         * <ul>
         *     <li>Ensures that callbacks for property invalidation are not null, facilitating robust property change handling.</li>
         *     <li>Validates that the bean associated with the property is specified, anchoring the property within a specific
         *     object's context.</li>
         *     <li>Checks for the presence of a property name, critical for identification within the bean and potentially
         *     within CSS.</li>
         *     <li>Confirms that CSS metadata has been provided, enabling the property to be styled through CSS.</li>
         * </ul>
         * If any of these validations fail, an {@code IllegalArgumentException} is thrown, indicating the specific configuration
         * issue. This ensures the property is correctly configured before it is used.
         * </p>
         *
         * <p>
         * After passing all checks, the method constructs a new {@code ExtendedStyleableFloatProperty}, optionally setting
         * a default value if one has been specified. If no default value is provided, the property is initialized with its
         * inherent default state.
         * </p>
         *
         * @return A fully configured {@code ExtendedStyleableFloatProperty} instance.
         *
         * @throws IllegalArgumentException
         *         if any required configuration is missing or incorrectly specified.
         */
        public ExtendedStyleableFloatProperty build() {
            invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, ExtendedStyleableFloatProperty.class);
            nullCheck(bean, "ExtendedStyleableFloatProperty Bean", ExtendedStyleableFloatProperty.class);
            nullCheck(name, "ExtendedStyleableFloatProperty Name", ExtendedStyleableFloatProperty.class);
            nullCheck(cssMetaData, "ExtendedStyleableFloatProperty CssMetaData", ExtendedStyleableFloatProperty.class);

            return Optional.ofNullable(defaultValue)
                           .map(v -> new ExtendedStyleableFloatProperty(this, v))
                           .orElse(new ExtendedStyleableFloatProperty(this));
        }
    }
}
