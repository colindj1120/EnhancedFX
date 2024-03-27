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
 * Represents a styleable double property within the EnhancedFX framework, extending the functionality of {@link EFXStyleablePropertyBase} by specializing in doubles.
 *
 * <p>This class allows for the creation and management of styleable properties that can be used within JavaFX's CSS styling system, providing enhanced flexibility and utility for defining and manipulating
 * styleable attributes.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Facilitates the creation and management of styleable double properties.</li>
 *     <li>Allows for the double properties to be styled directly via CSS, enhancing UI customization.</li>
 *     <li>Provides enhanced string representations, aiding in debugging and logging.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * EFXStyleableDoubleProperty visibleProperty = EFXStyleableDoubleProperty.create()
 *         .name("visible")
 *         .initialValue(true)
 *         .build();
 *
 * // Applying the property to a JavaFX node or component
 * myComponent.styleableProperties().add(visibleProperty);
 * }
 *
 * </pre>
 *
 * <p>This example demonstrates creating a styleable double property for controlling the visibility of a component, which can then be manipulated through CSS.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXStyleablePropertyBase
 */
public class EFXStyleableDoubleProperty extends EFXStyleablePropertyBase<EFXStyleableDoubleProperty, Double> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Provides a static factory method to initialize a builder for {@code EFXStyleableDoubleProperty}.
     *
     * @return a new instance of {@link EFXStyleableDoubleProperty.EFXStyleableDoublePropertyBuilder}
     */
    public static  EFXStyleableDoubleProperty.EFXStyleableDoublePropertyBuilder create() {
        return new EFXStyleableDoubleProperty.EFXStyleableDoublePropertyBuilder();
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@link EFXStyleableDoubleProperty} using the provided builder.
     *
     * <p>This constructor is protected to ensure that instances of {@code EFXStyleableDoubleProperty} are only created through the builder pattern, promoting a consistent and clear initialization process. The
     * builder contains all necessary configurations for the property, including its name, initial value, CSS metadata, and style converter if specified.</p>
     *
     * <p>The constructor delegates to the superclass constructor of {@link EFXStyleablePropertyBase}, passing along the builder, which allows the base class to perform common initialization tasks and apply
     * the configurations specified in the builder to the newly created property.</p>
     *
     * <p>It's important to note that this constructor is not intended to be used directly by client code. Instead, instances of {@code EFXStyleableDoubleProperty} should be created using the static
     * {@code create()} method on {@code EFXStyleableDoubleProperty}, followed by builder method calls to configure the property, and finally calling {@code build()} on the builder to obtain the configured
     * {@code EFXStyleableDoubleProperty} instance.</p>
     *
     * @param builder
     *         The {@code EFXStyleableDoublePropertyBuilder} containing the configurations for this property. It must not be {@code null} and should be fully configured.
     */
    protected EFXStyleableDoubleProperty(EFXStyleableDoubleProperty.EFXStyleableDoublePropertyBuilder builder) {
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
    protected EFXStyleableDoubleProperty getProperty() {
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
     * The builder class for {@link EFXStyleableDoubleProperty}, inheriting the fluid builder pattern and functionality from {@link EFXStyleablePropertyBuilder}, and providing a method to build an instance of
     * {@code EFXStyleableDoubleProperty}.
     *
     * <h2>Capabilities:</h2>
     * <ul>
     *     <li>Fluent API design for building {@code EFXStyleableDoubleProperty} instances.</li>
     *     <li>Customizable initial value, name, CSS meta-data, and style converter.</li>
     * </ul>
     *
     * <h2>Usage Example:</h2>
     * <pre>
     * {@code
     * EFXStyleableDoubleProperty<Color> backgroundColor = EFXStyleableDoubleProperty.<Color>create()
     *         .name("background-color")
     *         .initialValue(Color.WHITE)
     *         .styleConverter(ColorConverter.getInstance())
     *         .build();
     * }
     * </pre>
     *
     *  <p>This example creates a {@code EFXStyleableDoubleProperty} for background color using the builder.</p>
     */
    public static class EFXStyleableDoublePropertyBuilder extends EFXStyleablePropertyBuilder<EFXStyleableDoubleProperty.EFXStyleableDoublePropertyBuilder, EFXStyleableDoubleProperty, Double> {
        /**
         * Constructs a new {@code EFXStyleableDoublePropertyBuilder} instance with default settings.
         *
         * <p>This builder is used to create instances of {@link EFXStyleableDoubleProperty} through a fluent API design. Upon initialization, the builder's initial value is set to {@code null}, indicating that
         * the {@link EFXStyleableDoubleProperty} created by this builder will not have a predefined initial value unless explicitly set via the builder's methods.</p>
         *
         * <p>This constructor initializes the builder in a state ready for setting up an {@code EFXStyleableDoubleProperty} instance. Properties such as the property name, CSS metadata, and the style
         * converter can be configured using the provided builder methods.</p>
         *
         * <h2>Usage Example:</h2>
         * <pre>
         * {@code
         * EFXStyleableDoublePropertyBuilder<Color> builder = new EFXStyleableDoublePropertyBuilder();
         * EFXStyleableDoubleProperty<Color> colorProperty = builder
         *         .name("text-color")
         *         .initialValue(Color.BLACK)
         *         .styleConverter(ColorConverter.getInstance())
         *         .build();
         * }
         * </pre>
         *
         * <p>In this example, a new {@code EFXStyleableDoublePropertyBuilder} is created to build a {@code EFXStyleableDoubleProperty} for a text color property. The property is configured with an initial
         * value of {@code Color.BLACK} and uses a {@code ColorConverter} for CSS styling.</p>
         */
        public EFXStyleableDoublePropertyBuilder() {
            this.initialValue = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected EFXStyleableDoubleProperty.EFXStyleableDoublePropertyBuilder getBuilder() {
            return this;
        }

        /**
         * Finalizes the construction of {@link EFXStyleableDoubleProperty} instance.
         *
         * @return a new instance of {@code EFXStyleableDoubleProperty}
         */
        public EFXStyleableDoubleProperty build() {
            return new EFXStyleableDoubleProperty(this);
        }
    }

    //endregion Builder
}

