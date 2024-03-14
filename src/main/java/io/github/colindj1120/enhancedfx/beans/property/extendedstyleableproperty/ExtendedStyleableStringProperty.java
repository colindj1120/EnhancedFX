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
import javafx.css.*;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static io.github.colindj1120.enhancedfx.beans.property.base.ExtendedStyleablePropertyBase.invalidatorsNullCheck;
import static io.github.colindj1120.enhancedfx.beans.property.base.ExtendedStyleablePropertyBase.nullCheck;

/**
 * The {@code ExtendedStyleableStringProperty} class is a specialized implementation of {@link StyleableStringProperty} that incorporates additional functionality to enhance interaction with JavaFX CSS. It
 * provides mechanisms to handle property invalidation with custom callbacks, making it a versatile tool for developers seeking to extend the capabilities of styleable properties within their JavaFX
 * applications.
 *
 * <p>
 * This class supports various callbacks for property invalidation, allowing for specific actions to be taken when the property value changes or becomes invalidated. These callbacks include:
 * <ul>
 *     <li>A void callback that does not require the property's current value.</li>
 *     <li>A property callback that provides the current {@code StyleableStringProperty} instance, allowing direct interaction.</li>
 *     <li>A cached value callback that offers both the property and its old value, enabling comparisons and conditional logic based on changes.</li>
 * </ul>
 * Additionally, {@code ExtendedStyleableStringProperty} is designed with a focus on customization and ease of use,
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
 * ExtendedStyleableStringProperty customProperty = new ExtendedStyleableStringProperty.Builder()
 *         .bean(this)
 *         .name("customProperty")
 *         .cssMetaData(myCssMetaData)
 *         .defaultValue("hello")
 *         .invalidatedVoidCallback(() -> System.out.println("Property invalidated"))
 *         .build();
 * }</pre>
 * This class and its builder facilitate the creation of styleable properties that are deeply integrated with the
 * JavaFX styling system, providing developers with enhanced control and flexibility over UI appearance and behavior.
 * </p>
 *
 * @see StyleableStringProperty
 * @see ExtendedStyleablePropertyBase
 * @see CssMetaData
 * @see Styleable
 * @see Consumer
 * @see TriConsumer
 */
public class ExtendedStyleableStringProperty extends StyleableStringProperty implements ExtendedStyleablePropertyBase {
    private final Consumer<Void>                                                 invalidatedVoidCallback;
    private final Consumer<StyleableStringProperty>                              invalidatedPropCallback;
    private final TriConsumer<StyleableStringProperty, String, Consumer<String>> invalidatedCachedCallback;
    private final String                                                         name;
    private final Object                                                         bean;
    private final CssMetaData<? extends Styleable, String>                       cssMetaData;
    private       String                                                         oldValue = get();

    /**
     * Constructs an {@code ExtendedStyleableStringProperty} using the provided {@code Builder} instance. This constructor initializes the property without a default value, configuring it with the options
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
    protected ExtendedStyleableStringProperty(Builder builder) {
        super();
        nullCheck(builder, "ExtendedStyleableStringProperty Builder", this.getClass());
        this.invalidatedVoidCallback   = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback   = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name                      = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean                      = builder.bean;
        this.cssMetaData               = builder.cssMetaData;
    }

    /**
     * Constructs an {@code ExtendedStyleableStringProperty} with a specified default value, using the provided {@code Builder} instance for other configuration options. This constructor extends the
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
    protected ExtendedStyleableStringProperty(Builder builder, String defaultValue) {
        super(defaultValue);
        nullCheck(builder, "ExtendedStyleableStringProperty Builder", this.getClass());
        this.invalidatedVoidCallback   = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback   = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name                      = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean                      = builder.bean;
        this.cssMetaData               = builder.cssMetaData;
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
    public void applyStyle(StyleOrigin origin, String v) {
        if (Objects.nonNull(origin)) {
            super.applyStyle(origin, v);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CssMetaData<? extends Styleable, String> getCssMetaData() {
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
     * Determines whether the given element is equal to the value of this property.
     *
     * @param value The String to compare with the value of this property.
     *
     * @return {@code true} if the given element is equal to the value of this property, {@code false} otherwise.
     */
    public boolean valueEquals(String value) {
        return getValue().equals(value);
    }

    /**
     * Checks whether the specified element is equal to the property value, ignoring case sensitivity.
     * <p>
     * This method compares the specified element with the value of the property, ignoring any differences in case.
     * </p>
     *
     * @param value The String to be compared with the property value.
     * @return {@code true} if the element is equal to the property value, ignoring case sensitivity,
     *         {@code false} otherwise.
     */
    public boolean valueEqualsIgnoreCase(String value) {
        return getValue().equalsIgnoreCase(value);
    }

    /**
     * The {@code Builder} class for {@code ExtendedStyleableStringProperty} provides a fluent interface for incrementally constructing an {@code ExtendedStyleableStringProperty}. This builder allows for the
     * detailed configuration of the property, including its CSS metadata, name, bean, default value, and callbacks for various invalidation scenarios.
     * <p>
     * Usage of this builder facilitates the creation of a custom {@code ExtendedStyleableStringProperty} tailored to specific needs, enhancing the property's integration within a JavaFX application's styling
     * and behavior management system.
     * <p>
     * Features and Options:
     * <ul>
     *     <li>{@code invalidatedVoidCallback}: Specifies a callback to be executed when the property is invalidated, without
     *     requiring the current value.</li>
     *     <li>{@code invalidatedPropCallback}: Defines a callback with access to the {@code StyleableStringProperty}, allowing
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
     * The {@code build()} method finalizes the construction of the {@code ExtendedStyleableStringProperty}, ensuring that all
     * required configurations are set. It performs null checks on essential fields to prevent misconfigurations.
     * <p>
     * Example Usage:
     * <pre>
     * ExtendedStyleableStringProperty customProperty = new ExtendedStyleableStringProperty.Builder()
     *         .bean(this)
     *         .name("customOpacity")
     *         .cssMetaData(CustomCssMetaData.CUSTOM_OPACITY)
     *         .defaultValue("hello")
     *         .invalidatedVoidCallback(() -> System.out.println("Opacity invalidated"))
     *         .build();
     * </pre>
     * This approach to constructing an {@code ExtendedStyleableStringProperty} provides a clear and modular way to configure
     * property behavior and integration within the broader JavaFX styling system.
     */
    public static class Builder {
        private Consumer<Void>                                                 invalidatedVoidCallback;
        private Consumer<StyleableStringProperty>                              invalidatedPropCallback;
        private TriConsumer<StyleableStringProperty, String, Consumer<String>> invalidatedCachedCallback;
        private String                                                         name;
        private Object                                                         bean;
        private CssMetaData<? extends Styleable, String>                       cssMetaData;
        private String                                                         defaultValue;

        /**
         * Sets the invalidation callback for void properties.
         *
         * @param invalidatedVoidCallback The invalidation callback for void properties.
         * @return The Builder object.
         */
        public Builder invalidatedVoidCallback(Consumer<Void> invalidatedVoidCallback) {
            this.invalidatedVoidCallback = invalidatedVoidCallback;
            return this;
        }

        /**
         * Sets the invalidated Property callback for the Builder.
         *
         * @param invalidatedPropCallback The callback function to be called when the Property is invalidated.
         * @return The Builder instance.
         */
        public Builder invalidatedPropCallback(Consumer<StyleableStringProperty> invalidatedPropCallback) {
            this.invalidatedPropCallback = invalidatedPropCallback;
            return this;
        }

        /**
         * Sets the callback function to be invoked when the cached value of the {@link StyleableStringProperty} is invalidated.
         * This callback function takes three parameters: the property itself, the old value of the property, and a consumer
         * that can be used to set the new value of the property. This is useful when you want to cache the computed value
         * of the property and invalidate it only when necessary.
         *
         * @param invalidatedCachedCallback The callback function to be invoked when the cached value is invalidated.
         * @return The current instance of the Builder.
         */
        public Builder invalidatedCachedCallback(TriConsumer<StyleableStringProperty, String, Consumer<String>> invalidatedCachedCallback) {
            this.invalidatedCachedCallback = invalidatedCachedCallback;
            return this;
        }

        /**
         * Sets the name of the Builder.
         *
         * @param name The name to set for the Builder.
         * @return The updated Builder object.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the bean for the Builder.
         *
         * @param bean The bean object to set.
         * @return The Builder instance.
         */
        public Builder bean(Object bean) {
            this.bean = bean;
            return this;
        }

        /**
         * Sets the CSS metadata for the property.
         *
         * @param cssMetaData the CSS metadata for the property.
         * @return the Builder instance.
         */
        public Builder cssMetaData(CssMetaData<? extends Styleable, String> cssMetaData) {
            this.cssMetaData = cssMetaData;
            return this;
        }

        /**
         * Sets the default value for the property being built.
         *
         * @param defaultValue the default value for the property
         * @return the Builder instance
         */
        public Builder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Finalizes the construction of an {@code ExtendedStyleableStringProperty} based on the current configuration of the builder. This method ensures that all required fields have been properly set and
         * that the property is fully prepared for use within a JavaFX application.
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
         * After passing all checks, the method constructs a new {@code ExtendedStyleableStringProperty}, optionally setting
         * a default value if one has been specified. If no default value is provided, the property is initialized with its
         * inherent default state.
         * </p>
         *
         * @return A fully configured {@code ExtendedStyleableStringProperty} instance.
         *
         * @throws IllegalArgumentException
         *         if any required configuration is missing or incorrectly specified.
         */
        public ExtendedStyleableStringProperty build() {
            invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, ExtendedStyleableStringProperty.class);
            nullCheck(bean, "ExtendedStyleableStringProperty Bean", ExtendedStyleableStringProperty.class);
            nullCheck(name, "ExtendedStyleableStringProperty Name", ExtendedStyleableStringProperty.class);
            nullCheck(cssMetaData, "ExtendedStyleableStringProperty CssMetaData", ExtendedStyleableStringProperty.class);

            return Optional.ofNullable(defaultValue)
                           .map(v -> new ExtendedStyleableStringProperty(this, v))
                           .orElse(new ExtendedStyleableStringProperty(this));
        }
    }
}

