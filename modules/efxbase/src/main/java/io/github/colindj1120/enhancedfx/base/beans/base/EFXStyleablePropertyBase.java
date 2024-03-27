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
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.StyleOrigin;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * The base class for all styleable properties within the EnhancedFX framework. This class provides a foundational structure for creating and managing styleable properties that can be manipulated through CSS
 * within a JavaFX application.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Defines the core functionality for styleable properties, including the association with {@link CssMetaData}.</li>
 *     <li>Supports applying styles from CSS, tracking the origin of style changes.</li>
 *     <li>Offers robust equality and hashing methods that consider both the property's value and its CSS metadata.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * public class MyStyleableProperty extends EFXStyleablePropertyBase<MyStyleableProperty, Color> {
 *     public MyStyleableProperty(EFXStyleablePropertyBuilder<?, MyStyleableProperty, Color> builder) {
 *         super(builder);
 *     }
 *
 *     // Define additional functionality as needed
 * }
 *
 * // Usage in a custom control
 * MyStyleableProperty colorProperty = new MyStyleablePropertyBuilder<>()
 *         .cssMetaData(myCssMetaData)
 *         .initialValue(Color.RED)
 *         .build();
 * }
 * </pre>
 *
 * <p>This example demonstrates how to extend {@code EFXStyleablePropertyBase} to create a custom styleable property for a JavaFX control, capable of being styled via CSS.</p>
 *
 * @param <C>
 *         The concrete property class type.
 * @param <T>
 *         The type of the property value.
 */
public abstract class EFXStyleablePropertyBase<C extends EFXStyleablePropertyBase<C, T>, T> extends EFXPropertyBase<C, T> implements StyleableProperty<T> {
    protected final CssMetaData<? extends Styleable, T> cssMetaData;

    protected StyleOrigin origin = null;

    /**
     * Protected constructor for creating an instance of {@code EFXStyleablePropertyBase} using the provided builder. This constructor initializes the styleable property with configurations specified in the
     * builder, including the CSS metadata necessary for integrating the property with JavaFX's CSS styling system.
     *
     * <p>This constructor ensures that the essential component of the styleable property, its CSS metadata, is not null. It leverages {@link EFXObjectUtils#isNotNull(Object, Supplier)} to validate the
     * presence of CSS metadata, throwing an informative exception if the validation fails. This approach guarantees that every instance of {@code EFXStyleablePropertyBase} created through the builder is
     * properly configured for use with CSS.</p>
     *
     * <p><em>Note:</em> This constructor is protected to enforce the builder pattern for creating instances of {@code EFXStyleablePropertyBase} and its subclasses, ensuring that all necessary configurations are
     * provided before the property is used.</p>
     *
     * @param builder
     *         The {@code EFXStyleablePropertyBuilder} containing the configurations for this property, most importantly the CSS metadata.
     *
     * @throws IllegalArgumentException
     *         If the CSS metadata in the builder is null.
     * @see EFXObjectUtils
     */
    protected EFXStyleablePropertyBase(EFXStyleablePropertyBuilder<?, C, T> builder) {
        EFXObjectUtils.isNotNull(builder.cssMetaData, () -> String.format(nullFormatString, "cssMetaData", EFXStyleablePropertyBase.class.getSimpleName()));
        super(builder);
        this.cssMetaData = builder.cssMetaData;
    }

    /**
     * Returns the CSS metadata associated with this property.
     *
     * @return The {@link CssMetaData} instance.
     */
    public CssMetaData<? extends Styleable, T> getCssMetaData() {
        return cssMetaData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(cssMetaData);
    }

    /**
     * Determines whether the specified object is equal to this {@code EFXStyleablePropertyBase} instance. Equality is based on a deep comparison that includes both the inherent properties from the superclass
     * {@link EFXPropertyBase} and the CSS metadata associated with this styleable property.
     *
     * <p>This method extends the equality check of {@link EFXPropertyBase} by incorporating the CSS metadata comparison into the equality logic. The CSS metadata, which is essential for integrating the
     * property with JavaFX's CSS styling system, must also be equal for two {@code EFXStyleablePropertyBase} instances to be considered equal.</p>
     *
     * <h2>The equality check follows these steps:</h2>
     * <ol>
     *     <li>Verify the object is not null and is an instance of {@code EFXStyleablePropertyBase}.</li>
     *     <li>Check superclass equality by invoking {@code super.equals(obj)}.</li>
     *     <li>Compare the CSS metadata of this property with that of the other property.</li>
     * </ol>
     *
     * <p><em>Note:</em> The CSS metadata comparison relies on the {@link Objects#equals(Object, Object)} method, which is suitable for most implementations of {@link CssMetaData}. However, the accuracy of this
     * comparison may depend on the specific implementation of {@link CssMetaData#equals(Object)}.</p>
     *
     * @param obj
     *         The object to be compared with this {@code EFXStyleablePropertyBase} instance for equality.
     *
     * @return {@code true} if the specified object is equal to this {@code EFXStyleablePropertyBase} instance; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        boolean styleableEqualityCheck = Optional.ofNullable(obj)
                                                 .filter(EFXStyleablePropertyBase.class::isInstance)
                                                 .map(EFXStyleablePropertyBase.class::cast)
                                                 .map(other -> Objects.equals(cssMetaData, other.getCssMetaData()))
                                                 .orElse(false);

        return super.equals(obj) && styleableEqualityCheck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("""
                             %s
                             EFXStyleablePropertyBase Class {
                                cssMetaData=%s
                             }
                             """, super.toString(), getCssMetaData().toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyStyle(StyleOrigin origin, T v) {
        //This was overridden to prevent null pointer exceptions if the origin is null
        //TODO: double check above statement
        if (Objects.nonNull(origin)) {
            set(v);
            this.origin = origin;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StyleOrigin getStyleOrigin() {
        return origin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(ObservableValue<? extends T> observable) {
        super.bind(observable);
        origin = StyleOrigin.USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(T v) {
        super.set(v);
        origin = StyleOrigin.USER;
    }

    /**
     * The builder class for {@code EFXStyleablePropertyBase}. It follows the builder pattern to provide a fluent interface for constructing instances of {@code EFXStyleablePropertyBase}.
     *
     * <h2>Capabilities:</h2>
     * <ul>
     *     <li>Enables setting CSS metadata for the property being built.</li>
     * </ul>
     *
     * <h2>Usage Example:</h2>
     * <pre>
     * {@code
     * EFXStyleablePropertyBase<MyStyleableProperty, Color> myProperty = new EFXStyleablePropertyBuilder<MyStyleableProperty, Color>()
     *         .cssMetaData(myCssMetaData)
     *         .initialValue(Color.BLACK)
     *         .build();
     * }
     * </pre>
     *
     * <p>This example illustrates how to use the builder to create a styleable property with specific CSS metadata.</p>
     */
    public static abstract class EFXStyleablePropertyBuilder<P extends EFXStyleablePropertyBuilder<P, C, T>, C extends EFXPropertyBase<C, T>, T> extends EFXPropertyBuilder<P, C, T> {
        private CssMetaData<? extends Styleable, T> cssMetaData;

        /**
         * Constructs a new instance of {@code EFXStyleablePropertyBuilder} with default settings. This builder is intended for creating instances of subclasses of {@code EFXStyleablePropertyBase}. It provides
         * a fluent API to configure styleable properties, including their CSS metadata, before instantiation.
         *
         * <p>This default constructor initializes the builder with no initial values or configurations. Use the builder's methods to set properties such as CSS metadata, initial values, and other relevant
         * configurations specific to the styleable property being constructed.</p>
         */
        public EFXStyleablePropertyBuilder() {}

        /**
         * Sets the CSS metadata for the property being built.
         *
         * @param cssMetaData
         *         The CSS metadata.
         *
         * @return This builder instance.
         */
        public P cssMetaData(CssMetaData<? extends Styleable, T> cssMetaData) {
            this.cssMetaData = cssMetaData;
            return getBuilder();
        }
    }
}
