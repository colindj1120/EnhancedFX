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
import javafx.css.StyleableObjectProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static io.github.colindj1120.enhancedfx.beans.property.base.ExtendedStyleablePropertyBase.invalidatorsNullCheck;
import static io.github.colindj1120.enhancedfx.beans.property.base.ExtendedStyleablePropertyBase.nullCheck;

/**
 * The {@code ExtendedStyleableObjectProperty<T>} class is a generic implementation of {@link StyleableObjectProperty} designed to enhance the default property with additional capabilities such as invalidation
 * callbacks, CSS styling, and custom default values. It is part of the EnhancedFX library, aimed at providing advanced property handling features for JavaFX applications.
 *
 * <p>
 * <h2>Key features include:</h2>
 * <ul>
 *     <li>Custom invalidation callbacks that allow developers to respond to changes in property values with specific actions.</li>
 *     <li>Integration with JavaFX CSS, allowing properties to be styled directly from CSS files using {@link CssMetaData}.</li>
 *     <li>The ability to specify a default value at construction, enhancing the flexibility of property initialization.</li>
 *     <li>Type safety and clarity through generic typing, ensuring that the property value aligns with expected data types.</li>
 * </ul>
 * </p>
 *
 * <p>This property is ideal for developers looking to extend the standard JavaFX property system with more nuanced control over property behavior, especially in UI-heavy applications requiring dynamic
 * responses to user interactions or external changes.</p>
 *
 * <p>
 * <h2>Builder Pattern for Property Construction</h2>
 * The inner {@code Builder<T>} class offers a fluent API for constructing an {@code ExtendedStyleableObjectProperty<T>} instance. This pattern allows for clear and concise configuration of the property,
 * ensuring that all necessary parts are specified before its creation.
 * </p>
 *
 * <p>
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * ExtendedStyleableObjectProperty<String> myProperty = new ExtendedStyleableObjectProperty.Builder<String>()
 *         .bean(this)
 *         .name("myProperty")
 *         .cssMetaData(MyCssMetaData.MY_PROPERTY)
 *         .defaultValue("InitialValue")
 *         .invalidatedVoidCallback(() -> System.out.println("Property invalidated."))
 *         .build();
 * }</pre>
 * </p>
 *
 * <p>In this example, a new {@code ExtendedStyleableObjectProperty<String>} is configured and created, demonstrating the flexibility and ease of use provided by the builder pattern. Developers can tailor
 * the property to their specific needs, ensuring seamless integration into their application's architecture.</p>
 *
 * <p>The {@code ExtendedStyleableObjectProperty<T>} and its builder are part of EnhancedFX, a library dedicated to enriching JavaFX's capabilities and fostering the development of sophisticated,
 * interactive, and highly customizable user interfaces.</p>
 *
 * @param <T>
 *         The type of the property's value, ensuring type safety throughout its usage.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see StyleableObjectProperty
 * @see ExtendedStyleablePropertyBase
 * @see CssMetaData
 * @see Styleable
 * @see Consumer
 * @see TriConsumer
 */
public class ExtendedStyleableObjectProperty<T> extends StyleableObjectProperty<T> implements ExtendedStyleablePropertyBase {
    private final Consumer<Void>                                          invalidatedVoidCallback;
    private final Consumer<StyleableObjectProperty<T>>                    invalidatedPropCallback;
    private final TriConsumer<StyleableObjectProperty<T>, T, Consumer<T>> invalidatedCachedCallback;
    private final String                                                  name;
    private final Object                                                  bean;
    private final CssMetaData<? extends Styleable, T>                     cssMetaData;
    private final Class<T>                                                type;
    private       T                                                       oldValue = get();

    /**
     * Constructs an {@code ExtendedStyleableObjectProperty} using the provided builder configuration, without specifying an explicit default value. This constructor initializes the property with the default
     * value defined in the superclass. It sets up the property with various configurations such as invalidation callbacks, the property's name, associated bean, CSS metadata, and the property's type, as
     * specified in the builder.
     *
     * <p>
     * <h2>Key configurations applied from the builder include:</h2>
     * <ul>
     *     <li>Invalidation callbacks for void, property-specific, and cached value scenarios, allowing for customized
     *     reactions to property invalidation events.</li>
     *     <li>The property's name, crucial for identification within its bean and potentially in CSS.</li>
     *     <li>The bean associated with the property, embedding it within an object's property hierarchy.</li>
     *     <li>CSS metadata, enabling the property to be styled through CSS.</li>
     *     <li>The type of the property, ensuring type safety and correct handling of the property's value.</li>
     * </ul>
     * </p>
     *
     * <p>
     * This constructor performs a null check on the builder to ensure that it is not null, throwing an
     * {@code IllegalArgumentException} if the check fails.
     * </p>
     *
     * @param builder
     *         The {@code Builder} instance containing configuration settings for this property.
     *
     * @throws IllegalArgumentException
     *         if the builder is null.
     */
    protected ExtendedStyleableObjectProperty(Builder<T> builder) {
        super();
        nullCheck(builder, "Property Builder", this.getClass());
        this.invalidatedVoidCallback = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean = builder.bean;
        this.cssMetaData = builder.cssMetaData;
        this.type = builder.type;
    }

    /**
     * Constructs an {@code ExtendedStyleableObjectProperty} with a specific default value using the provided builder configuration. This constructor extends the functionality of the no-default-value
     * constructor by explicitly setting an initial value for the property, alongside configuring other features like invalidation callbacks, property's name, associated bean, CSS metadata, and the property's
     * type.
     *
     * <p>
     * Similar to the no-default constructor, this version ensures that the property is fully prepared for use within a stylable JavaFX component, with thorough validation checks for necessary builder
     * components. The explicit default value is applied to the property, setting its initial state prior to any external modifications.
     * </p>
     *
     * <p>
     * This constructor is suited for scenarios where an initial property value is known at construction time and needs to be explicitly set, enhancing the property's configurability and usability.
     * </p>
     *
     * @param builder
     *         The {@code Builder<T>} instance providing configuration settings for this property.
     * @param defaultValue
     *         The default value to initialize the property with.
     *
     * @throws IllegalArgumentException
     *         if the builder is null.
     */
    protected ExtendedStyleableObjectProperty(Builder<T> builder, T defaultValue) {
        super(defaultValue);
        nullCheck(builder, "Property Builder", this.getClass());
        this.invalidatedVoidCallback = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name = Objects.isNull(builder.name) ? DEFAULT_NAME : builder.name;
        this.bean = builder.bean;
        this.cssMetaData = builder.cssMetaData;
        this.type = builder.type;
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
    public void applyStyle(StyleOrigin origin, T v) {
        if (Objects.nonNull(origin)) {
            super.applyStyle(origin, v);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CssMetaData<? extends Styleable, T> getCssMetaData() {
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
     * Checks if the specified value is equal to the value of this property.
     *
     * @param value The value to compare with.
     * @return {@code true} if the specified value is equal to the value of this property, otherwise {@code false}.
     */
    public boolean valueEquals(T value) {
        if(type.isInstance(value)) {
            return getValue().equals(value);
        }
        return false;
    }

    /**
     * The {@code Builder} class provides a fluent API for constructing instances of {@code ExtendedStyleableObjectProperty<T>}. This generic builder facilitates the customization of styleable object
     * properties, including their invalidation callbacks, name, bean, CSS metadata, default value, and the specific type of the property. It ensures that properties are correctly configured and typed before
     * their creation, enhancing the robustness and flexibility of JavaFX applications.
     *
     * <p>
     * <h2>Key Configuration Options:</h2>
     * <ul>
     *     <li>{@code invalidatedVoidCallback}: Assigns a callback to be invoked when the property becomes invalidated,
     *     without requiring the current value.</li>
     *     <li>{@code invalidatedPropCallback}: Specifies a callback that receives the property itself upon invalidation,
     *     enabling actions that depend on the property's specific state or identity.</li>
     *     <li>{@code invalidatedCachedCallback}: Sets a callback with access to the property, its old value, and a consumer
     *     to update the cached value, supporting complex change-handling logic.</li>
     *     <li>{@code name}: Defines the name of the property, crucial for its identification within its bean and potentially
     *     in CSS.</li>
     *     <li>{@code bean}: Links the property with a JavaFX bean, situating it within an object's property hierarchy.</li>
     *     <li>{@code cssMetaData}: Associates the property with {@link CssMetaData}, enabling it to be styled via CSS.</li>
     *     <li>{@code defaultValue}: Initializes the property with a specified value, setting its initial state.</li>
     *     <li>{@code type}: Specifies the class type of the property, ensuring type safety and correct handling of the
     *     property's value.</li>
     * </ul>
     * <p>
     * The {@code build()} method combines these configurations to instantiate a new {@code ExtendedStyleableObjectProperty<T>}
     * with the specified settings. It performs null checks on essential fields to guarantee a fully-configured property.
     * </p>
     *
     * <p>
     * <ema>Example Usage:</em>
     * <pre>
     * ExtendedStyleableObjectProperty<String> myProperty = new ExtendedStyleableObjectProperty.Builder<String>()
     *         .bean(this)
     *         .name("myCustomProperty")
     *         .cssMetaData(MyCustomCssMetaData.MY_CUSTOM_PROPERTY)
     *         .defaultValue("default")
     *         .type(String.class)
     *         .invalidatedVoidCallback(() -> System.out.println("Property invalidated"))
     *         .build();
     * </pre>
     * <p>
     * This class exemplifies a pattern designed to create sophisticated, type-safe, and customizable styleable object properties
     * for JavaFX applications, contributing to a more modular and maintainable UI development process.
     * </p>
     *
     * @param <T>
     *         The type parameter indicating the type of the property's value.
     */
    public static class Builder<T> {
        private Consumer<Void>                                          invalidatedVoidCallback;
        private Consumer<StyleableObjectProperty<T>>                    invalidatedPropCallback;
        private TriConsumer<StyleableObjectProperty<T>, T, Consumer<T>> invalidatedCachedCallback;
        private String                                                  name;
        private Object                                                  bean;
        private CssMetaData<? extends Styleable, T>                     cssMetaData;
        private T                                                       defaultValue;
        private Class<T>                                                type;

        /**
         * Sets the callback for general invalidation without any parameters.
         *
         * @param invalidatedVoidCallback
         *         The callback for general invalidation.
         *
         * @return The Builder object.
         */
        public Builder<T> invalidatedVoidCallback(Consumer<Void> invalidatedVoidCallback) {
            this.invalidatedVoidCallback = invalidatedVoidCallback;
            return this;
        }

        /**
         * Sets the callback function to be invoked when the {@link StyleableObjectProperty} associated with this builder is invalidated. The callback function takes the invalidated property as its input. This
         * method is typically used to perform custom logic or update the UI when the property's value changes.
         *
         * @param invalidatedPropCallback
         *         the callback function to be invoked when the property is invalidated
         *
         * @return the current instance of the {@link Builder}
         */
        public Builder<T> invalidatedPropCallback(Consumer<StyleableObjectProperty<T>> invalidatedPropCallback) {
            this.invalidatedPropCallback = invalidatedPropCallback;
            return this;
        }

        /**
         * Sets the invalidated cached callback for the Builder. This callback is triggered when the value of the StyleableObjectProperty is changed or invalidated. It accepts three parameters: the
         * StyleableObjectProperty itself, the new value, and a Consumer to apply the new value.
         *
         * @param invalidatedCachedCallback
         *         the invalidated cached callback function
         *
         * @return the Builder instance
         */
        public Builder<T> invalidatedCachedCallback(TriConsumer<StyleableObjectProperty<T>, T, Consumer<T>> invalidatedCachedCallback) {
            this.invalidatedCachedCallback = invalidatedCachedCallback;
            return this;
        }

        /**
         * Sets the name of the Builder.
         *
         * @param name
         *         the name to set
         *
         * @return the Builder object
         */
        public Builder<T> name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the bean for the builder.
         *
         * @param bean
         *         the bean object
         *
         * @return the modified builder instance
         */
        public Builder<T> bean(Object bean) {
            this.bean = bean;
            return this;
        }

        /**
         * Sets the CSS metadata for the builder.
         *
         * @param cssMetaData
         *         the CSS metadata to set
         *
         * @return the builder instance
         */
        public Builder<T> cssMetaData(CssMetaData<? extends Styleable, T> cssMetaData) {
            this.cssMetaData = cssMetaData;
            return this;
        }

        /**
         * Sets the default value for the property.
         *
         * @param defaultValue
         *         the default value for the property
         *
         * @return the Builder instance for method chaining
         */
        public Builder<T> defaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Sets the type of the Builder.
         *
         * @param type
         *         The type of the Builder
         *
         * @return This Builder instance
         */
        public Builder<T> type(Class<T> type) {
            this.type = type;
            return this;
        }

        /**
         * Finalizes the construction of an {@code ExtendedStyleableObjectProperty<T>} based on the configurations provided by this builder. This method ensures that all necessary preconditions and
         * configurations are met before creating the property. It conducts comprehensive checks to validate the presence of essential parts such as the property type, bean, name, and CSS metadata.
         *
         * <p>
         * <h2>The construction process includes several key validation steps:</h2>
         * <ul>
         *     <li>Ensures that callback functions for property invalidation are properly set, if provided, to enable dynamic
         *     response to property changes.</li>
         *     <li>Verifies that the property's type is specified, safeguarding against type mismatches and enhancing type safety.</li>
         *     <li>Checks that the bean associated with the property is defined, anchoring the property within a specific
         *     object context.</li>
         *     <li>Confirms that the property's name is provided, crucial for identification within its bean and CSS.</li>
         *     <li>Validates the inclusion of CSS metadata, enabling the property to be styled through CSS.</li>
         * </ul>
         * </p>
         *
         * <p>
         * After passing these checks, the method proceeds to create an instance of {@code ExtendedStyleableObjectProperty<T>}
         * with the specified default value, if one is provided. If no default value is specified, it initializes the property
         * without setting an explicit initial value.
         * </p>
         *
         * <p>
         * This method encapsulates the essence of the builder pattern, providing a safe and structured approach to
         * configuring and creating a customized {@code ExtendedStyleableObjectProperty<T>}.
         * </p>
         *
         * @return A new {@code ExtendedStyleableObjectProperty<T>} instance, configured according to the builder's specifications.
         *
         * @throws IllegalArgumentException
         *         if any required configuration is missing or if any validation check fails, indicating improper or incomplete setup of the property's configuration.
         */
        public ExtendedStyleableObjectProperty<T> build() {
            invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, ExtendedStyleableObjectProperty.class);
            nullCheck(type, "ExtendedStyleableObjectProperty Type", ExtendedStyleableObjectProperty.class);
            nullCheck(bean, "ExtendedStyleableObjectProperty Bean", ExtendedStyleableObjectProperty.class);
            nullCheck(name, "ExtendedStyleableObjectProperty Name", ExtendedStyleableObjectProperty.class);
            nullCheck(cssMetaData, "ExtendedStyleableObjectProperty CssMetaData", ExtendedStyleableObjectProperty.class);

            return Optional.ofNullable(defaultValue)
                           .map(v -> new ExtendedStyleableObjectProperty<>(this, v))
                           .orElse(new ExtendedStyleableObjectProperty<>(this));
        }
    }
}
