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
import javafx.css.StyleableBooleanProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.base.EFXStyleablePropertyBase.invalidatorsNullCheck;
import static io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.base.EFXStyleablePropertyBase.nullCheck;

/**
 * The {@code EFXStyleableBooleanProperty} class extends {@code StyleableBooleanProperty} to provide enhanced functionality for JavaFX properties that are styleable using CSS. This implementation includes
 * additional callback mechanisms to react to property invalidation events, facilitating complex property change handling scenarios in JavaFX applications.
 *
 * <p>
 * <h2>Features include:</h2>
 * <ul>
 *     <li>Void callback on invalidation, allowing for actions that don't directly depend on the property's value.</li>
 *     <li>Property callback on invalidation, enabling actions that require the current property instance.</li>
 *     <li>Cached value callback on invalidation, supporting actions that compare current and previous values.</li>
 *     <li>Customizable property name, bean reference, and associated CSS metadata, enhancing integration with
 *         JavaFX CSS styling.</li>
 * </ul>
 * <br>
 * <p>
 * This class is designed to be constructed via its {@code Builder} pattern, promoting flexible configuration.
 * It supports direct application of CSS styles while providing hooks for custom behavior when the property's
 * value is invalidated or styled.
 * </p>
 *
 * <p>
 * <em>Example Usage:</em>
 * <pre>
 * EFXStyleableBooleanProperty myProperty = new EFXStyleableBooleanProperty.Builder()
 *         .bean(this)
 *         .name("myProperty")
 *         .cssMetaData(myCssMetaData)
 *         .invalidatedPropCallback(prop -> System.out.println("Property invalidated"))
 *         .build();
 * </pre>
 * <p>
 * The {@code invalidated()} method is overridden to execute configured callbacks, providing a versatile mechanism
 * to react to changes. {@code applyStyle()} is also overridden to allow for additional actions when styles are applied,
 * while {@code getCssMetaData()}, {@code getBean()}, and {@code getName()} offer access to the property's metadata.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see StyleableBooleanProperty
 * @see EFXStyleablePropertyBase
 * @see CssMetaData
 * @see Styleable
 * @see Consumer
 * @see TriConsumer
 */
public class EFXStyleableBooleanProperty extends StyleableBooleanProperty implements EFXStyleablePropertyBase {
    private final Consumer<Void>                                                    invalidatedVoidCallback;
    private final Consumer<StyleableBooleanProperty>                                invalidatedPropCallback;
    private final TriConsumer<StyleableBooleanProperty, Boolean, Consumer<Boolean>> invalidatedCachedCallback;
    private final String                                                            name;
    private final Object                                                            bean;
    private final CssMetaData<? extends Styleable, Boolean>                         cssMetaData;
    private       Boolean                                                           oldValue;

    /**
     * Initializes an {@code EFXStyleableBooleanProperty} with a specific default value and additional configuration provided by the {@code Builder}. This constructor extends the basic initialization by
     * explicitly setting an initial value for the property, alongside configuring other features like invalidation callbacks and CSS metadata.
     *
     * <p>
     * This variant is particularly useful when the property must start with a predefined boolean value rather than the superclass default. It adopts a comprehensive approach to property setup, incorporating:
     * <ul>
     *     <li>Custom invalidation callbacks for enhanced behavior on property changes.</li>
     *     <li>Identification and styling configurations through the property's name, bean association, and CSS metadata.</li>
     *     <li>An initial boolean value defining the property's starting state.</li>
     * </ul>
     * Such detailed configuration capabilities make this property suited for complex UI components requiring specific
     * initial states and responsive, stylable behavior.
     * </p>
     *
     * @param builder
     *         The {@code Builder} providing configuration settings for this property.
     * @param defaultValue
     *         The default value to initialize the property with.
     *
     * @throws IllegalArgumentException
     *         if the builder or any critical configuration element is null.
     */
    private EFXStyleableBooleanProperty(Builder builder, boolean defaultValue) {
        super(defaultValue);
        nullCheck(builder, "EFXStyleableBooleanProperty Builder", this.getClass());
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
    public void applyStyle(StyleOrigin origin, Boolean v) {
        if (Objects.nonNull(origin)) {
            super.applyStyle(origin, v);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CssMetaData<? extends Styleable, Boolean> getCssMetaData() {
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
     * @return {@code true} if the value of this property is equal to the specified value, {@code false} otherwise.
     */
    public boolean valueEquals(Boolean value) {
        return getValue().equals(value);
    }

    /**
     * The {@code Builder} class for {@code EFXStyleableBooleanProperty} provides a fluent interface for configuring and creating an instance of {@code EFXStyleableBooleanProperty}. It allows setting
     * up custom callbacks for property invalidation, defining the property's name, bean, CSS metadata, and default value.
     *
     * <p>
     * This builder class supports the following configuration methods:
     * <ul>
     *     <li>{@code invalidatedVoidCallback(Consumer<Void>)}: Sets a callback to be called when the property is invalidated,
     *     without needing the property's value.</li>
     *     <li>{@code invalidatedPropCallback(Consumer<StyleableBooleanProperty>)}: Sets a callback with access to the
     *     {@code StyleableBooleanProperty} when invalidated, enabling direct interaction with the property itself.</li>
     *     <li>{@code invalidatedCachedCallback(TriConsumer<StyleableBooleanProperty, Boolean, Consumer<Boolean>>)}: Sets
     *     a more complex callback that provides the property, its old value, and a consumer to set a new cached value.</li>
     *     <li>{@code name(String)}: Specifies the name of the property, used for identifying it within the associated bean
     *     and potentially in CSS.</li>
     *     <li>{@code bean(Object)}: Sets the JavaFX bean this property is associated with, which can be any {@code Object}
     *     that holds this property.</li>
     *     <li>{@code cssMetaData(CssMetaData<? extends Styleable, Boolean>)}: Defines the CSS metadata for this property,
     *     allowing it to be styled via CSS.</li>
     *     <li>{@code defaultValue(Boolean)}: Sets the default value for the property, used when no other value is explicitly
     *     set.</li>
     * </ul>
     * The {@code build()} method creates an instance of {@code EFXStyleableBooleanProperty} with the configured
     * settings. It checks for null values in essential fields to ensure the integrity of the property configuration.
     * </p>
     */
    public static class Builder {
        private Consumer<Void>                                                    invalidatedVoidCallback;
        private Consumer<StyleableBooleanProperty>                                invalidatedPropCallback;
        private TriConsumer<StyleableBooleanProperty, Boolean, Consumer<Boolean>> invalidatedCachedCallback;
        private String                                                            name;
        private Object                                                            bean;
        private CssMetaData<? extends Styleable, Boolean>                         cssMetaData;
        private Boolean                                                           defaultValue;

        /**
         * Builder class for constructing {@link EFXStyleableBooleanProperty} instances.
         *
         * <p>
         * This builder provides methods to configure and customize the property before finalizing its construction. It offers a fluent interface for intuitive and straightforward
         * configuration.
         * </p>
         */
        public Builder() {}

        /**
         * Sets the callback function invoked when the property is invalidated. The callback function does not take any arguments and does not return a value.
         *
         * @param invalidatedVoidCallback
         *         The callback function to set.
         *
         * @return This builder instance.
         */
        public Builder invalidatedVoidCallback(Consumer<Void> invalidatedVoidCallback) {
            this.invalidatedVoidCallback = invalidatedVoidCallback;
            return this;
        }

        /**
         * Sets the callback function invoked when the property is invalidated.
         *
         * @param invalidatedPropCallback
         *         The callback function to be set
         *
         * @return The current instance of the Builder class
         */
        public Builder invalidatedPropCallback(Consumer<StyleableBooleanProperty> invalidatedPropCallback) {
            this.invalidatedPropCallback = invalidatedPropCallback;
            return this;
        }

        /**
         * Sets the invalidated cached callback for the builder.
         *
         * @param invalidatedCachedCallback
         *         The callback function that is invoked when the property is invalidated. It takes three parameters: StyleableBooleanProperty, Boolean, and Consumer<Boolean>.
         *
         * @return The builder object.
         */
        public Builder invalidatedCachedCallback(TriConsumer<StyleableBooleanProperty, Boolean, Consumer<Boolean>> invalidatedCachedCallback) {
            this.invalidatedCachedCallback = invalidatedCachedCallback;
            return this;
        }

        /**
         * Sets the name of the object.
         *
         * @param name
         *         the name to set
         *
         * @return the Builder instance
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the bean associated with the property and returns the current Builder instance.
         *
         * @param bean
         *         the bean associated with the property
         *
         * @return the current Builder instance
         */
        public Builder bean(Object bean) {
            this.bean = bean;
            return this;
        }

        /**
         * Sets the CSS metadata for the property.
         *
         * @param cssMetaData
         *         the CSS metadata for the property
         *
         * @return the updated instance of Builder
         */
        public Builder cssMetaData(CssMetaData<? extends Styleable, Boolean> cssMetaData) {
            this.cssMetaData = cssMetaData;
            return this;
        }

        /**
         * Sets the default value for the property and returns the current instance of the builder.
         *
         * @param defaultValue
         *         The default value for the property.
         *
         * @return The current instance of the builder.
         */
        public Builder defaultValue(Boolean defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Builds and returns a new {@code EFXStyleableBooleanProperty} instance based on the current configuration of this builder. This method ensures that all necessary parts and callbacks are properly
         * set before creating the property. If any critical configuration is missing, an {@code IllegalArgumentException} is thrown, indicating the specific component that is missing.
         * <p>
         * Before creating the property, it performs null checks on the following essential parts:
         * <ul>
         *     <li>The bean associated with the property, ensuring it is not {@code null}.</li>
         *     <li>The name of the property, ensuring it is provided.</li>
         *     <li>The CSS metadata for the property, ensuring it is provided for CSS styling capabilities.</li>
         * </ul>
         * Additionally, it checks for the presence of callback functions that are invoked when the property is invalidated.
         * If any of these callbacks are provided but are {@code null}, an exception is thrown to prevent runtime errors
         * related to null invocations.
         * <p>
         * After validation, the method constructs the {@code EFXStyleableBooleanProperty} instance. If a default value
         * has been specified using the builder, it is used to initialize the property; otherwise, the property is initialized
         * with its default behavior.
         *
         * @return A fully configured {@code EFXStyleableBooleanProperty} instance.
         *
         * @throws IllegalArgumentException
         *         if any required configuration is missing or if any provided callback is {@code null}.
         */
        public EFXStyleableBooleanProperty build() {
            invalidatorsNullCheck(this.invalidatedVoidCallback, this.invalidatedPropCallback, this.invalidatedCachedCallback, EFXStyleableBooleanProperty.class);
            nullCheck(bean, "EFXStyleableBooleanProperty Bean", EFXStyleableBooleanProperty.class);
            nullCheck(name, "EFXStyleableBooleanProperty Name", EFXStyleableBooleanProperty.class);
            nullCheck(cssMetaData, "EFXStyleableBooleanProperty CssMetaData", EFXStyleableBooleanProperty.class);

            return Optional.ofNullable(defaultValue)
                           .map(v -> new EFXStyleableBooleanProperty(this, v))
                           .orElse(new EFXStyleableBooleanProperty(this, false));
        }
    }
}

