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
package io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.base.EFXStyleablePropertyBase;
import io.github.colindj1120.enhancedfx.utils.consumers.TriConsumer;
import javafx.css.CssMetaData;
import javafx.css.StyleOrigin;
import javafx.css.Styleable;
import javafx.css.StyleableLongProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.base.EFXStyleablePropertyBase.invalidatorsNullCheck;
import static io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.base.EFXStyleablePropertyBase.nullCheck;

/**
 * The {@code EFXStyleableLongProperty} class is a specialized implementation of {@link StyleableLongProperty} that incorporates additional functionality to enhance interaction with JavaFX CSS. It provides
 * mechanisms to handle property invalidation with custom callbacks, making it a versatile tool for developers seeking to extend the capabilities of styleable properties within their JavaFX applications.
 *
 * <p>
 * This class supports various callbacks for property invalidation, allowing for specific actions to be taken when the property value changes or becomes invalidated. These callbacks include:
 * <ul>
 *     <li>A void callback that does not require the property's current value.</li>
 *     <li>A property callback that provides the current {@code StyleableLongProperty} instance, allowing direct interaction.</li>
 *     <li>A cached value callback that offers both the property and its old value, enabling comparisons and conditional logic based on changes.</li>
 * </ul>
 * Additionally, {@code EFXStyleableLongProperty} is designed with a focus on customization and ease of use,
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
 * EFXStyleableLongProperty customProperty = new EFXStyleableLongProperty.Builder()
 *         .bean(this)
 *         .name("customProperty")
 *         .cssMetaData(myCssMetaData)
 *         .defaultValue(1L)
 *         .invalidatedVoidCallback(() -> System.out.println("Property invalidated"))
 *         .build();
 * }</pre>
 * This class and its builder facilitate the creation of styleable properties that are deeply integrated with the
 * JavaFX styling system, providing developers with enhanced control and flexibility over UI appearance and behavior.
 * </p>
 *
 * @see StyleableLongProperty
 * @see EFXStyleablePropertyBase
 * @see CssMetaData
 * @see Styleable
 * @see Consumer
 * @see TriConsumer
 */
public class EFXStyleableLongProperty extends StyleableLongProperty implements EFXStyleablePropertyBase {
    private final Consumer<Void>                                           invalidatedVoidCallback;
    private final Consumer<StyleableLongProperty>                          invalidatedPropCallback;
    private final TriConsumer<StyleableLongProperty, Long, Consumer<Long>> invalidatedCachedCallback;
    private final String                                                   name;
    private final Object                                                   bean;
    private final CssMetaData<? extends Styleable, Number>                 cssMetaData;
    private       Long                                                     oldValue;

    /**
     * Constructs an {@code EFXStyleableLongProperty} with a specified default value, using the provided {@code Builder} instance for other configuration options. This constructor extends the functionality
     * of the no-argument constructor by allowing the specification of an initial value for the property.
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
    protected EFXStyleableLongProperty(Builder builder, long defaultValue) {
        super(defaultValue);
        nullCheck(builder, "EFXStyleableLongProperty Builder", this.getClass());
        this.invalidatedVoidCallback = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean = builder.bean;
        this.cssMetaData = builder.cssMetaData;
        this.oldValue = defaultValue;

        invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, this.getClass());
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
     * Checks if the value of the property equals the provided value.
     *
     * @param value
     *        The value to compare with the property value.
     *
     * @return true if the value of the property equals the provided value, false otherwise.
     */
    public boolean valueEquals(Long value) {
        return getValue().equals(value);
    }

    /**
     * The {@code Builder} class for {@code EFXStyleableLongProperty} provides a fluent interface for incrementally constructing an {@code EFXStyleableLongProperty}. This builder allows for the
     * detailed configuration of the property, including its CSS metadata, name, bean, default value, and callbacks for various invalidation scenarios.
     * <p>
     * Usage of this builder facilitates the creation of a custom {@code EFXStyleableLongProperty} tailored to specific needs, enhancing the property's integration within a JavaFX application's styling
     * and behavior management system.
     * <p>
     * Features and Options:
     * <ul>
     *     <li>{@code invalidatedVoidCallback}: Specifies a callback to be executed when the property is invalidated, without
     *     requiring the current value.</li>
     *     <li>{@code invalidatedPropCallback}: Defines a callback with access to the {@code StyleableLongProperty}, allowing
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
     * The {@code build()} method finalizes the construction of the {@code EFXStyleableLongProperty}, ensuring that all
     * required configurations are set. It performs null checks on essential fields to prevent misconfigurations.
     * <p>
     * Example Usage:
     * <pre>
     * EFXStyleableLongProperty customProperty = new EFXStyleableLongProperty.Builder()
     *         .bean(this)
     *         .name("customOpacity")
     *         .cssMetaData(CustomCssMetaData.CUSTOM_OPACITY)
     *         .defaultValue(1L)
     *         .invalidatedVoidCallback(() -> System.out.println("Opacity invalidated"))
     *         .build();
     * </pre>
     * This approach to constructing an {@code EFXStyleableLongProperty} provides a clear and modular way to configure
     * property behavior and integration within the broader JavaFX styling system.
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
         * Builder class for constructing {@link EFXStyleableLongProperty} instances.
         *
         * <p>
         * This builder provides methods to configure and customize the property before finalizing its construction. It offers a fluent interface for intuitive and straightforward
         * configuration.
         * </p>
         */
        public Builder() {}

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
         * Sets the callback function for when the cached value of the property is invalidated. The callback function receives the StyleableLongProperty instance, the old value, and a Consumer to set the new
         * value.
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
         * Sets the bean for the {@link EFXStyleableLongProperty} instance.
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
         * Specifies the CSS metadata for the EFXStyleableLongProperty. This method sets the CSS metadata, which integrates the property with JavaFX's CSS styling system, allowing for enhanced styling
         * capabilities.
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
         * Finalizes the construction of an {@code EFXStyleableLongProperty} based on the current configuration of the builder. This method ensures that all required fields have been properly set and that
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
         * After passing all checks, the method constructs a new {@code EFXStyleableLongProperty}, optionally setting
         * a default value if one has been specified. If no default value is provided, the property is initialized with its
         * inherent default state.
         * </p>
         *
         * @return A fully configured {@code EFXStyleableLongProperty} instance.
         *
         * @throws IllegalArgumentException
         *         if any required configuration is missing or incorrectly specified.
         */
        public EFXStyleableLongProperty build() {
            invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, EFXStyleableLongProperty.class);
            nullCheck(bean, "EFXStyleableLongProperty Bean", EFXStyleableLongProperty.class);
            nullCheck(name, "EFXStyleableLongProperty Name", EFXStyleableLongProperty.class);
            nullCheck(cssMetaData, "EFXStyleableLongProperty CssMetaData", EFXStyleableLongProperty.class);

            return Optional.ofNullable(defaultValue)
                           .map(v -> new EFXStyleableLongProperty(this, v))
                           .orElse(new EFXStyleableLongProperty(this, 0L));
        }
    }
}



