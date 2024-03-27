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
package io.github.colindj1120.enhancedfx.base.beans.base;

import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import io.github.colindj1120.enhancedfx.utils.consumers.TriConsumer;
import javafx.beans.property.ObjectPropertyBase;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An abstract base class for all custom property implementations in the EnhancedFX framework, extending {@link ObjectPropertyBase} and providing additional functionality and integration capabilities with
 * JavaFX's property and CSS styling systems.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Associates custom properties with JavaFX beans and CSS metadata.</li>
 *     <li>Enables custom invalidation logic through consumer callbacks.</li>
 *     <li>Tracks the previous value of the property for potential use in callbacks or validation.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * public class MyCustomProperty extends EFXPropertyBase<MyCustomProperty, String> {
 *     public MyCustomProperty(EFXPropertyBuilder<?, MyCustomProperty, String> builder) {
 *         super(builder);
 *     }
 *
 *     // Additional methods and logic specific to 'MyCustomProperty'
 * }
 *
 * // Builder pattern used for instantiation
 * MyCustomProperty myProperty = new MyCustomPropertyBuilder<>()
 *         .name("myProperty")
 *         .bean(myBean)
 *         .initialValue("default")
 *         .invalidatedVoidCallback(() -> System.out.println("Property invalidated!"))
 *         .build();
 * }
 * </pre>
 * This illustrates how to define and instantiate a custom property with specific initial value, name, and
 * invalidation logic, showcasing the flexibility and extensibility of the EFXPropertyBase class.
 *
 * @param <C>
 *         The type of the custom property class extending EFXPropertyBase.
 * @param <T>
 *         The type of the value held by the property.
 */
public abstract class EFXPropertyBase<C extends EFXPropertyBase<C, T>, T> extends ObjectPropertyBase<T> {
    protected static final String nullFormatString = "The argument '%s' in %s cannot be null.";

    protected final String                         name;
    protected final Object                         bean;
    protected final Consumer<Void>                 invalidatedVoidCallback;
    protected final Consumer<C>                    invalidatedPropCallback;
    protected final TriConsumer<C, T, Consumer<T>> invalidatedCachedCallback;
    protected       T                              oldValue;

    /**
     * Constructs a new {@code EFXPropertyBase} instance using the specified builder, initializing the property with the configurations provided. This constructor is designed to ensure that essential parameters
     * such as the property's name, its associated bean, and initial value are not null, enforcing a contract for the creation of robust and well-defined properties.
     *
     * <p>This approach allows for a flexible yet safe creation of property instances, leveraging the builder pattern to gather all necessary configurations before instantiation. It integrates directly with
     * JavaFX's property system while adding enhanced capabilities specific to the EnhancedFX framework, such as custom invalidation callbacks.</p>
     *
     * <h2>Parameters:</h2>
     * <ul>
     *     <li>{@code builder}: The {@code EFXPropertyBuilder} containing all necessary configurations for this property, including the property's name, associated bean, initial value, and optional
     *     invalidation callbacks.</li>
     * </ul>
     *
     * <h2>Ensured Conditions:</h2>
     * <ul>
     *     <li>The property's name, bean, and initial value must not be null, validated using {@link EFXObjectUtils#isNotNull(Object, Supplier)}.</li>
     *     <li>Invalidation callbacks are optional and are set based on the builder's configuration.</li>
     * </ul>
     *
     * <p>This constructor is protected to ensure that instances of {@code EFXPropertyBase} and its subclasses are only created through their respective builders, maintaining the integrity and intended use
     * of the property and its configurations.</p>
     *
     * @param builder
     *         The {@code EFXPropertyBuilder} containing configurations for this property.
     *
     * @throws IllegalArgumentException
     *         If the name, bean, or initial value provided in the builder is null, indicating a contract breach in property creation.
     */
    protected EFXPropertyBase(EFXPropertyBuilder<?, C, T> builder) {
        EFXObjectUtils.isNotNull(builder.name, () -> String.format(nullFormatString, "name", EFXPropertyBase.class.getSimpleName()));
        EFXObjectUtils.isNotNull(builder.bean, () -> String.format(nullFormatString, "bean", EFXPropertyBase.class.getSimpleName()));
        EFXObjectUtils.isNotNull(builder.initialValue, () -> String.format(nullFormatString, "initialValue", EFXPropertyBase.class.getSimpleName()));

        super(builder.initialValue);

        this.invalidatedVoidCallback = builder.invalidatedVoidCallback;
        this.invalidatedPropCallback = builder.invalidatedPropCallback;
        this.invalidatedCachedCallback = builder.invalidatedCachedCallback;
        this.name = builder.name;
        this.bean = builder.bean;
    }

    /**
     * Provides a protected method for subclasses to access the property instance itself. This method is typically used in callback consumers to refer to the property.
     *
     * @return The current property instance.
     */
    protected abstract C getProperty();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void invalidated() {
        super.invalidated();
        Optional.ofNullable(getInvalidatedVoidCallback())
                .ifPresent(callback -> callback.accept(null));
        Optional.ofNullable(getInvalidatedPropCallback())
                .ifPresent(callback -> callback.accept(getProperty()));
        Optional.ofNullable(getInvalidatedCachedCallback())
                .ifPresent(callback -> callback.accept(getProperty(), oldValue, val -> oldValue = val));
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(bean, name);
    }

    /**
     * Compares this {@code EFXPropertyBase} instance with another object for equality. The comparison is deep, taking into account the property's name, associated bean, old value, and its invalidation
     * callbacks. This method is designed to ensure a comprehensive equality check suitable for the advanced features provided by the EnhancedFX framework properties.
     *
     * <h2>The equality check involves:</h2>
     * <ul>
     *     <li>Verifying that the other object is an instance of {@code EFXPropertyBase} or its subclass.</li>
     *     <li>Comparing the property's name, bean, and old value for equality.</li>
     *     <li>Checking if the invalidation callbacks (void, property-specific, and cached value callbacks) are equivalent. This involves comparing the {@code Consumer} and {@code TriConsumer} references for
     *     equality, which may only check reference equality unless specifically overridden in a callback implementation.</li>
     * </ul>
     *
     * <p><em>Note:</em> Due to the nature of callback comparisons, this method may not accurately reflect functional equality of the callbacks themselves, as it relies on {@link Object#equals(Object)}
     * which, for
     * lambda expressions and anonymous classes, typically compares object references.</p>
     *
     * <h2>Usage Example:</h2>
     * <pre>{@code
     * EFXPropertyBase<MyProperty, String> property1 = new MyPropertyBuilder<>()
     *     .name("exampleProperty")
     *     .bean(beanInstance)
     *     .initialValue("Initial")
     *     .build();
     *
     * EFXPropertyBase<MyProperty, String> property2 = new MyPropertyBuilder<>()
     *     .name("exampleProperty")
     *     .bean(beanInstance)
     *     .initialValue("Initial")
     *     .build();
     *
     * boolean isEqual = property1.equals(property2);
     * // isEqual will be true if property2 has the same name, bean, initial value, and identical callback instances.
     * }</pre>
     *
     * <p>This method is overridden to adhere to the contract defined by {@link Object#equals(Object)}, providing a means to compare {@code EFXPropertyBase} instances beyond the default identity comparison.</p>
     *
     * @param obj
     *         The object to be compared with this instance for equality.
     *
     * @return {@code true} if the specified object is equal to this instance, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return Optional.ofNullable(obj)
                       .filter(EFXPropertyBase.class::isInstance)
                       .map(EFXPropertyBase.class::cast)
                       .map(other -> Objects.equals(this.getInvalidatedVoidCallback(), other.getInvalidatedVoidCallback()) &&
                                     Objects.equals(this.getInvalidatedPropCallback(), other.getInvalidatedPropCallback()) &&
                                     Objects.equals(this.getInvalidatedCachedCallback(), other.getInvalidatedCachedCallback()) && Objects.equals(this.getBean(), other.getBean()) &&
                                     Objects.equals(this.getName(), other.getName()) && Objects.equals(this.getOldValue(), other.getOldValue()))
                       .orElse(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String className = this.getClass()
                               .getSimpleName();
        return String.format("""
                             %s
                             EFXPropertyBase Class {
                                name='%s',
                                bean=%s,
                                value='%s',
                                invalidatedVoidCallback=%s,
                                invalidatedPropCallback=%s,
                                invalidatedCachedCallback=%s
                             }
                             """, className, getName(), getBean().toString(), getValue(), getVoidString(), getPropString(), getCachedString());
    }

    /**
     * Gets the callback function invoked when the property becomes invalid.
     *
     * @return The {@code Consumer<Void>} callback function for invalidation.
     */
    public Consumer<Void> getInvalidatedVoidCallback() {
        return invalidatedVoidCallback;
    }

    /**
     * Returns the callback consumer for property invalidation events.
     *
     * @return The callback consumer for property invalidation events.
     */
    public Consumer<C> getInvalidatedPropCallback() {
        return invalidatedPropCallback;
    }

    /**
     * Retrieves the invalidated cached callback associated with this EFXPropertyBase.
     *
     * @return The invalidated cached callback associated with this EFXPropertyBase.
     *
     * @see TriConsumer
     */
    public TriConsumer<C, T, Consumer<T>> getInvalidatedCachedCallback() {
        return invalidatedCachedCallback;
    }

    /**
     * Compares the given value with the value of this property for equality.
     *
     * @param value
     *         The value to compare with the property value.
     *
     * @return {@code true} if the given value is equal to the property value, {@code false} otherwise.
     */
    public boolean valueEquals(T value) {
        return Objects.equals(getValue(), value);
    }

    /**
     * Retrieves the old value of the property before it was changed.
     *
     * @return The old value of the property.
     */
    public T getOldValue() {
        return oldValue;
    }

    /**
     * Returns the string representation of the presence or absence of the invalidatedVoidCallback.
     *
     * @return "present" if the invalidatedVoidCallback is not null, "absent" otherwise.
     */
    protected String getVoidString() {
        return Objects.nonNull(invalidatedVoidCallback) ? "present" : "absent";
    }

    /**
     * Returns a string indicating whether the invalidatedPropCallback is present or absent.
     *
     * @return "present" if the invalidatedPropCallback is not null, "absent" otherwise.
     */
    protected String getPropString() {
        return Objects.nonNull(invalidatedPropCallback) ? "present" : "absent";
    }

    /**
     * Returns a string indicating whether the invalidatedCachedCallback is present or absent.
     *
     * @return "present" if the invalidatedCachedCallback is not null, "absent" otherwise.
     */
    protected String getCachedString() {
        return Objects.nonNull(invalidatedCachedCallback) ? "present" : "absent";
    }

    /**
     * Represents the builder class for EFXPropertyBase, providing a fluent API to configure and instantiate subclasses of EFXPropertyBase.
     *
     * <h2>Capabilities:</h2>
     * <ul>
     *     <li>Configures the initial value, name, and associated bean of the property.</li>
     *     <li>Sets custom invalidation callbacks to respond to property changes.</li>
     * </ul>
     *
     * <h2>Usage Example:</h2>
     * <pre>
     * {@code
     * EFXPropertyBase<MyCustomProperty, String> myProperty = new EFXPropertyBuilder<MyCustomProperty, String>()
     *         .name("myProperty")
     *         .bean(myBean)
     *         .initialValue("default")
     *         .invalidatedVoidCallback(() -> System.out.println("Property invalidated!"))
     *         .build();
     * }
     * </pre>
     * This example configures a custom property with a builder, specifying its initial value, name, bean,
     * and a callback for when the property is invalidated.
     *
     * @param <P>
     *         The type of the builder itself, allowing method chaining.
     * @param <C>
     *         The type of the custom property class extending EFXPropertyBase.
     * @param <T>
     *         The type of the value held by the property.
     */
    public static abstract class EFXPropertyBuilder<P extends EFXPropertyBuilder<P, C, T>, C extends EFXPropertyBase<C, T>, T> {
        protected String                         name                      = "";
        protected Object                         bean                      = null;
        protected Consumer<Void>                 invalidatedVoidCallback   = null;
        protected Consumer<C>                    invalidatedPropCallback   = null;
        protected TriConsumer<C, T, Consumer<T>> invalidatedCachedCallback = null;
        protected T                              initialValue;

        /**
         * Constructs a new instance of {@code EFXPropertyBuilder}. This default constructor initializes the builder with default settings, preparing it to configure a new {@code EFXPropertyBase} instance. The
         * builder follows the fluent API pattern, allowing for chaining of configuration methods to set up the property specifics such as name, bean, initial value, and invalidation callbacks.
         *
         * <p>This constructor is designed to be used by subclasses of {@code EFXPropertyBase} to facilitate the creation and configuration of custom properties in a flexible and expressive manner.</p>
         */
        public EFXPropertyBuilder() {}

        /**
         * Provides a protected method to retrieve the current builder instance. This method is intended to be overridden by subclasses of {@code EFXPropertyBuilder} to return the correct type of the builder
         * itself, supporting the fluent API pattern by enabling method chaining.
         *
         * <p>The method must be implemented by each subclass to ensure that the correct type is returned for chaining. This design allows for extending the builder pattern to support additional configurations
         * specific to the subclass.</p>
         *
         * @return The current instance of the builder, correctly typed.
         */
        protected abstract P getBuilder();

        /**
         * Sets the initial value for the property.
         *
         * @param initialValue
         *         the initial value to be set
         *
         * @return the builder instance
         */
        public P initialValue(T initialValue) {
            this.initialValue = initialValue;
            return getBuilder();
        }

        /**
         * Sets the callback function to be called when the property is invalidated and doesn't return a value.
         *
         * @param invalidatedVoidCallback
         *         The consumer function to be called when the property is invalidated
         *
         * @return The builder instance
         */
        public P invalidatedVoidCallback(Consumer<Void> invalidatedVoidCallback) {
            this.invalidatedVoidCallback = invalidatedVoidCallback;
            return getBuilder();
        }

        /**
         * Sets the invalidated property callback for the property. This callback is invoked when the property's value is invalidated, allowing for dynamic response to changes.
         *
         * @param invalidatedPropCallback
         *         The callback function to be invoked when the property's value is invalidated. It takes a single parameter of type C, which represents the type of the property being invalidated.
         *
         * @return The current instance of the class.
         */
        public P invalidatedPropCallback(Consumer<C> invalidatedPropCallback) {
            this.invalidatedPropCallback = invalidatedPropCallback;
            return getBuilder();
        }

        /**
         * Sets the callback function to be executed when the cached value is invalidated.
         *
         * @param invalidatedCachedCallback
         *         The callback function to be executed. It accepts three arguments: the current value of type C, the new value of type T, and a Consumer that can be used to update the cached value.
         *
         * @return The instance of the builder.
         */
        public P invalidatedCachedCallback(TriConsumer<C, T, Consumer<T>> invalidatedCachedCallback) {
            this.invalidatedCachedCallback = invalidatedCachedCallback;
            return getBuilder();
        }

        /**
         * Sets the name of the object.
         *
         * @param name
         *         the name to set
         *
         * @return the builder object
         */
        public P name(String name) {
            this.name = name;
            return getBuilder();
        }

        /**
         * Sets the bean object for this instance.
         *
         * @param bean
         *         the bean object to be set
         *
         * @return the builder instance
         */
        public P bean(Object bean) {
            this.bean = bean;
            return getBuilder();
        }
    }

}
