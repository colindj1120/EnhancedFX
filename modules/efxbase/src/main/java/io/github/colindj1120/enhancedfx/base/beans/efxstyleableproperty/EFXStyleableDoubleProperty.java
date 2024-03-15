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
import javafx.css.StyleableDoubleProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.base.EFXStyleablePropertyBase.invalidatorsNullCheck;
import static io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.base.EFXStyleablePropertyBase.nullCheck;

/**
 * The {@code EFXStyleableDoubleProperty} class is a specialized implementation of {@link StyleableDoubleProperty} that incorporates additional functionality to enhance interaction with JavaFX CSS. It
 * provides mechanisms to handle property invalidation with custom callbacks, making it a versatile tool for developers seeking to extend the capabilities of styleable properties within their JavaFX
 * applications.
 *
 * <p>
 * This class supports various callbacks for property invalidation, allowing for specific actions to be taken when the property value changes or becomes invalidated. These callbacks include:
 * <ul>
 *     <li>A void callback that does not require the property's current value.</li>
 *     <li>A property callback that provides the current {@code StyleableDoubleProperty} instance, allowing direct interaction.</li>
 *     <li>A cached value callback that offers both the property and its old value, enabling comparisons and conditional logic based on changes.</li>
 * </ul>
 * Additionally, {@code EFXStyleableDoubleProperty} is designed with a focus on customization and ease of use,
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
 * EFXStyleableDoubleProperty customProperty = new EFXStyleableDoubleProperty.Builder()
 *         .bean(this)
 *         .name("customProperty")
 *         .cssMetaData(myCssMetaData)
 *         .defaultValue(1.0)
 *         .invalidatedVoidCallback(() -> System.out.println("Property invalidated"))
 *         .build();
 * }</pre>
 * This class and its builder facilitate the creation of styleable properties that are deeply integrated with the
 * JavaFX styling system, providing developers with enhanced control and flexibility over UI appearance and behavior.
 * </p>
 *
 * @see StyleableDoubleProperty
 * @see EFXStyleablePropertyBase
 * @see CssMetaData
 * @see Styleable
 * @see Consumer
 * @see TriConsumer
 */
public class EFXStyleableDoubleProperty extends StyleableDoubleProperty implements EFXStyleablePropertyBase {
    private final Consumer<Void>                                                 invalidatedVoidCallback;
    private final Consumer<StyleableDoubleProperty>                              invalidatedPropCallback;
    private final TriConsumer<StyleableDoubleProperty, Double, Consumer<Double>> invalidatedCachedCallback;
    private final String                                                         name;
    private final Object                                                         bean;
    private final CssMetaData<? extends Styleable, Number>                       cssMetaData;
    private       Double                                                         oldValue;

    /**
     * Constructs an {@code EFXStyleableDoubleProperty} with a specified default value, using the provided {@code Builder} instance for other configuration options. This constructor extends the
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
    protected EFXStyleableDoubleProperty(Builder builder, double defaultValue) {
        super(defaultValue);
        nullCheck(builder, "EFXStyleableDoubleProperty Builder", this.getClass());
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
     * Checks if this Double value is equal to the provided value.
     *
     * @param value
     *         The Double value to compare with.
     *
     * @return {@code true} if this Double value is equal to the provided value, {@code false} otherwise.
     */
    public boolean valueEquals(Double value) {
        return getValue().equals(value);
    }

    /**
     * The {@code Builder} class for {@code EFXStyleableDoubleProperty} provides a fluent interface for incrementally constructing an {@code EFXStyleableDoubleProperty}. This builder allows for the
     * detailed configuration of the property, including its CSS metadata, name, bean, default value, and callbacks for various invalidation scenarios.
     * <p>
     * Usage of this builder facilitates the creation of a custom {@code EFXStyleableDoubleProperty} tailored to specific needs, enhancing the property's integration within a JavaFX application's styling
     * and behavior management system.
     * <p>
     * Features and Options:
     * <ul>
     *     <li>{@code invalidatedVoidCallback}: Specifies a callback to be executed when the property is invalidated, without
     *     requiring the current value.</li>
     *     <li>{@code invalidatedPropCallback}: Defines a callback with access to the {@code StyleableDoubleProperty}, allowing
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
     * The {@code build()} method finalizes the construction of the {@code EFXStyleableDoubleProperty}, ensuring that all
     * required configurations are set. It performs null checks on essential fields to prevent misconfigurations.
     * <p>
     * Example Usage:
     * <pre>
     * EFXStyleableDoubleProperty customProperty = new EFXStyleableDoubleProperty.Builder()
     *         .bean(this)
     *         .name("customOpacity")
     *         .cssMetaData(CustomCssMetaData.CUSTOM_OPACITY)
     *         .defaultValue(1.0)
     *         .invalidatedVoidCallback(() -> System.out.println("Opacity invalidated"))
     *         .build();
     * </pre>
     * This approach to constructing an {@code EFXStyleableDoubleProperty} provides a clear and modular way to configure
     * property behavior and integration within the broader JavaFX styling system.
     */
    public static class Builder {
        private Consumer<Void>                                                 invalidatedVoidCallback;
        private Consumer<StyleableDoubleProperty>                              invalidatedPropCallback;
        private TriConsumer<StyleableDoubleProperty, Double, Consumer<Double>> invalidatedCachedCallback;
        private String                                                         name;
        private Object                                                         bean;
        private CssMetaData<? extends Styleable, Number>                       cssMetaData;
        private Double                                                         defaultValue;

        /**
         * Builder class for constructing {@link EFXStyleableDoubleProperty} instances.
         *
         * <p>
         * This builder provides methods to configure and customize the property before finalizing its construction. It offers a fluent interface for intuitive and straightforward
         * configuration.
         * </p>
         */
        public Builder() {}

        /**
         * Sets a callback function that will be invoked when the property associated with this builder is invalidated.
         *
         * @param invalidatedVoidCallback
         *         the callback function to be set
         *
         * @return the modified Builder object
         */
        public Builder invalidatedVoidCallback(Consumer<Void> invalidatedVoidCallback) {
            this.invalidatedVoidCallback = invalidatedVoidCallback;
            return this;
        }

        /**
         * Sets the callback function to be executed when the property is invalidated.
         *
         * @param invalidatedPropCallback
         *         the callback function to be executed when the property is invalidated
         *
         * @return the modified Builder object
         */
        public Builder invalidatedPropCallback(Consumer<StyleableDoubleProperty> invalidatedPropCallback) {
            this.invalidatedPropCallback = invalidatedPropCallback;
            return this;
        }

        /**
         * Sets the invalidated cached callback for the Builder object.
         *
         * @param invalidatedCachedCallback
         *         the function that will be called when the StyleableDoubleProperty is invalidated
         *
         * @return the modified Builder object
         */
        public Builder invalidatedCachedCallback(TriConsumer<StyleableDoubleProperty, Double, Consumer<Double>> invalidatedCachedCallback) {
            this.invalidatedCachedCallback = invalidatedCachedCallback;
            return this;
        }

        /**
         * Sets the name of this Builder object.
         *
         * @param name
         *         the name to be set
         *
         * @return the modified Builder object
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the bean property to the specified value.
         *
         * @param bean
         *         the bean object to set
         *
         * @return the instance of the Builder class
         */
        public Builder bean(Object bean) {
            this.bean = bean;
            return this;
        }

        /**
         * Sets the CSS metadata for this builder.
         *
         * @param cssMetaData
         *         the CSS metadata to be set
         *
         * @return the modified Builder object
         */
        public Builder cssMetaData(CssMetaData<? extends Styleable, Number> cssMetaData) {
            this.cssMetaData = cssMetaData;
            return this;
        }

        /**
         * Sets the default value for the property associated with this Builder object.
         *
         * @param defaultValue
         *         the default value to be set
         *
         * @return the modified Builder object
         */
        public Builder defaultValue(Double defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Finalizes the construction of an {@code EFXStyleableDoubleProperty} based on the current configuration of the builder. This method ensures that all required fields have been properly set and
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
         * After passing all checks, the method constructs a new {@code EFXStyleableDoubleProperty}, optionally setting
         * a default value if one has been specified. If no default value is provided, the property is initialized with its
         * inherent default state.
         * </p>
         *
         * @return A fully configured {@code EFXStyleableDoubleProperty} instance.
         *
         * @throws IllegalArgumentException
         *         if any required configuration is missing or incorrectly specified.
         */
        public EFXStyleableDoubleProperty build() {
            invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, EFXStyleableDoubleProperty.class);
            nullCheck(bean, "EFXStyleableDoubleProperty Bean", EFXStyleableDoubleProperty.class);
            nullCheck(name, "EFXStyleableDoubleProperty Name", EFXStyleableDoubleProperty.class);
            nullCheck(cssMetaData, "EFXStyleableDoubleProperty CssMetaData", EFXStyleableDoubleProperty.class);

            return Optional.ofNullable(defaultValue)
                           .map(v -> new EFXStyleableDoubleProperty(this, v))
                           .orElse(new EFXStyleableDoubleProperty(this, 0.0));
        }
    }
}


