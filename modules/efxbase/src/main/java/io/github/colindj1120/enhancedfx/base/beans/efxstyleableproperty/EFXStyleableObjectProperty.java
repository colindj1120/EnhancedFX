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

import io.github.colindj1120.enhancedfx.base.beans.base.EFXStyleablePropertyBase;
import io.github.colindj1120.enhancedfx.utils.EFXStringUtils;

/**
 * Represents a styleable object property within the EnhancedFX framework, extending the functionality of {@link EFXStyleablePropertyBase} by specializing in objects.
 *
 * <p>This class allows for the creation and management of styleable properties that can be used within JavaFX's CSS styling system, providing enhanced flexibility and utility for defining and manipulating
 * styleable attributes.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Creation of styleable object properties through a fluent builder interface.</li>
 *     <li>Integration with JavaFX's CSS styling system, allowing properties to be styled via CSS.</li>
 *     <li>Enhanced formatting and string representation for debugging and logging purposes.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * EFXStyleableObjectProperty<Color> backgroundColor = EFXStyleableObjectProperty.<Color>create()
 *         .name("background-color")
 *         .initialValue(Color.WHITE)
 *         .styleConverter(ColorConverter.getInstance())
 *         .build();
 *
 * somePane.styleableProperties().add(backgroundColor);
 * }
 * </pre>
 *
 * <p>This example demonstrates the creation of a styleable property for background color, using the builder pattern provided by {@code EFXStyleableObjectProperty}.</p>
 *
 * @param <T>
 *         the type of the property value
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXStyleablePropertyBase
 */
public class EFXStyleableObjectProperty<T> extends EFXStyleablePropertyBase<EFXStyleableObjectProperty<T>, T> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Provides a static factory method to initialize a builder for {@code EFXStyleableObjectProperty}.
     *
     * @param <T>
     *         the type of the property value
     *
     * @return a new instance of {@link EFXStyleableObjectPropertyBuilder}
     */
    public static <T> EFXStyleableObjectPropertyBuilder<T> create() {
        return new EFXStyleableObjectPropertyBuilder<>();
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@link EFXStyleableObjectProperty} using the provided builder.
     *
     * <p>This constructor is protected to ensure that instances of {@code EFXStyleableObjectProperty} are only created through the builder pattern, promoting a consistent and clear initialization process. The
     * builder contains all necessary configurations for the property, including its name, initial value, CSS metadata, and style converter if specified.</p>
     *
     * <p>The constructor delegates to the superclass constructor of {@link EFXStyleablePropertyBase}, passing along the builder, which allows the base class to perform common initialization tasks and apply
     * the configurations specified in the builder to the newly created property.</p>
     *
     * <p>It's important to note that this constructor is not intended to be used directly by client code. Instead, instances of {@code EFXStyleableObjectProperty} should be created using the static
     * {@code create()} method on {@code EFXStyleableObjectProperty}, followed by builder method calls to configure the property, and finally calling {@code build()} on the builder to obtain the configured
     * {@code EFXStyleableObjectProperty} instance.</p>
     *
     * @param builder
     *         The {@code EFXStyleableObjectPropertyBuilder} containing the configurations for this property. It must not be {@code null} and should be fully configured.
     */
    protected EFXStyleableObjectProperty(EFXStyleableObjectPropertyBuilder<T> builder) {
        super(builder);
    }

    //endregion Constructor

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    protected EFXStyleableObjectProperty<T> getProperty() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("""
                             %s:
                             %s
                             """, getClass().getSimpleName(), EFXStringUtils.addSpacesToEveryLine(super.toString(), EFXStringUtils.IndentationLevel.LEVEL_1));
    }

    //endregion Overridden Functions

    //region Builder
    //*****************************************************************
    // Builder
    //*****************************************************************

    /**
     * The builder class for {@link EFXStyleableObjectProperty}, inheriting the fluid builder pattern and functionality from {@link EFXStyleablePropertyBuilder}, and providing a method to build an instance of
     * {@code EFXStyleableObjectProperty}.
     *
     * <h2>Capabilities:</h2>
     * <ul>
     *     <li>Fluent API design for building {@code EFXStyleableObjectProperty} instances.</li>
     *     <li>Customizable initial value, name, CSS meta-data, and style converter.</li>
     * </ul>
     *
     * <h2>Usage Example:</h2>
     * <pre>
     * {@code
     * EFXStyleableObjectProperty<Color> backgroundColor = EFXStyleableObjectProperty.<Color>create()
     *         .name("background-color")
     *         .initialValue(Color.WHITE)
     *         .styleConverter(ColorConverter.getInstance())
     *         .build();
     * }
     * </pre>
     *
     *  <p>This example creates a {@code EFXStyleableObjectProperty} for background color using the builder.</p>
     *
     * @param <T>
     *         the type of the property value
     */
    public static class EFXStyleableObjectPropertyBuilder<T> extends EFXStyleablePropertyBuilder<EFXStyleableObjectPropertyBuilder<T>, EFXStyleableObjectProperty<T>, T> {
        /**
         * Constructs a new {@code EFXStyleableObjectPropertyBuilder} instance with default settings.
         *
         * <p>This builder is used to create instances of {@link EFXStyleableObjectProperty} through a fluent API design. Upon initialization, the builder's initial value is set to {@code null}, indicating that
         * the {@link EFXStyleableObjectProperty} created by this builder will not have a predefined initial value unless explicitly set via the builder's methods.</p>
         *
         * <p>This constructor initializes the builder in a state ready for setting up an {@code EFXStyleableObjectProperty} instance. Properties such as the property name, CSS metadata, and the style
         * converter can be configured using the provided builder methods.</p>
         *
         * <h2>Usage Example:</h2>
         * <pre>
         * {@code
         * EFXStyleableObjectPropertyBuilder<Color> builder = new EFXStyleableObjectPropertyBuilder<>();
         * EFXStyleableObjectProperty<Color> colorProperty = builder
         *         .name("text-color")
         *         .initialValue(Color.BLACK)
         *         .styleConverter(ColorConverter.getInstance())
         *         .build();
         * }
         * </pre>
         *
         * <p>In this example, a new {@code EFXStyleableObjectPropertyBuilder} is created to build a {@code EFXStyleableObjectProperty} for a text color property. The property is configured with an initial
         * value of {@code Color.BLACK} and uses a {@code ColorConverter} for CSS styling.</p>
         */
        public EFXStyleableObjectPropertyBuilder() {
            this.initialValue = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected EFXStyleableObjectPropertyBuilder<T> getBuilder() {
            return this;
        }

        /**
         * Finalizes the construction of {@link EFXStyleableObjectProperty} instance.
         *
         * @return a new instance of {@code EFXStyleableObjectProperty}
         */
        public EFXStyleableObjectProperty<T> build() {
            return new EFXStyleableObjectProperty<>(this);
        }
    }

    //endregion Builder
}
