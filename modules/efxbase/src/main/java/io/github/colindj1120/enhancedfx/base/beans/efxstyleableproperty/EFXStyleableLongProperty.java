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
 * Represents a styleable long property within the EnhancedFX framework, extending the functionality of {@link EFXStyleablePropertyBase} by specializing in longs.
 *
 * <p>This class allows for the creation and management of styleable properties that can be used within JavaFX's CSS styling system, providing enhanced flexibility and utility for defining and manipulating
 * styleable attributes.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Facilitates the creation and management of styleable long properties.</li>
 *     <li>Allows for the long properties to be styled directly via CSS, enhancing UI customization.</li>
 *     <li>Provides enhanced string representations, aiding in debugging and logging.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * EFXStyleableLongProperty visibleProperty = EFXStyleableLongProperty.create()
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
 * <p>This example demonstrates creating a styleable long property for controlling the visibility of a component, which can then be manipulated through CSS.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXStyleablePropertyBase
 */
public class EFXStyleableLongProperty extends EFXStyleablePropertyBase<EFXStyleableLongProperty, Long> {
    //region Static Factory Method
    //*****************************************************************
    // Static Factory Method
    //*****************************************************************

    /**
     * Provides a static factory method to initialize a builder for {@code EFXStyleableLongProperty}.
     *
     * @return a new instance of {@link EFXStyleableLongProperty.EFXStyleableLongPropertyBuilder}
     */
    public static  EFXStyleableLongProperty.EFXStyleableLongPropertyBuilder create() {
        return new EFXStyleableLongProperty.EFXStyleableLongPropertyBuilder();
    }

    //endregion Static Factory Method

    //region Constructor
    //*****************************************************************
    // Constructor
    //*****************************************************************

    /**
     * Constructs an instance of {@link EFXStyleableLongProperty} using the provided builder.
     *
     * <p>This constructor is protected to ensure that instances of {@code EFXStyleableLongProperty} are only created through the builder pattern, promoting a consistent and clear initialization process. The
     * builder contains all necessary configurations for the property, including its name, initial value, CSS metadata, and style converter if specified.</p>
     *
     * <p>The constructor delegates to the superclass constructor of {@link EFXStyleablePropertyBase}, passing along the builder, which allows the base class to perform common initialization tasks and apply
     * the configurations specified in the builder to the newly created property.</p>
     *
     * <p>It's important to note that this constructor is not intended to be used directly by client code. Instead, instances of {@code EFXStyleableLongProperty} should be created using the static
     * {@code create()} method on {@code EFXStyleableLongProperty}, followed by builder method calls to configure the property, and finally calling {@code build()} on the builder to obtain the configured
     * {@code EFXStyleableLongProperty} instance.</p>
     *
     * @param builder
     *         The {@code EFXStyleableLongPropertyBuilder} containing the configurations for this property. It must not be {@code null} and should be fully configured.
     */
    protected EFXStyleableLongProperty(EFXStyleableLongProperty.EFXStyleableLongPropertyBuilder builder) {
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
    protected EFXStyleableLongProperty getProperty() {
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
     * The builder class for {@link EFXStyleableLongProperty}, inheriting the fluid builder pattern and functionality from {@link EFXStyleablePropertyBuilder}, and providing a method to build an instance of
     * {@code EFXStyleableLongProperty}.
     *
     * <h2>Capabilities:</h2>
     * <ul>
     *     <li>Fluent API design for building {@code EFXStyleableLongProperty} instances.</li>
     *     <li>Customizable initial value, name, CSS meta-data, and style converter.</li>
     * </ul>
     *
     * <h2>Usage Example:</h2>
     * <pre>
     * {@code
     * EFXStyleableLongProperty<Color> backgroundColor = EFXStyleableLongProperty.<Color>create()
     *         .name("background-color")
     *         .initialValue(Color.WHITE)
     *         .styleConverter(ColorConverter.getInstance())
     *         .build();
     * }
     * </pre>
     *
     *  <p>This example creates a {@code EFXStyleableLongProperty} for background color using the builder.</p>
     */
    public static class EFXStyleableLongPropertyBuilder extends EFXStyleablePropertyBuilder<EFXStyleableLongProperty.EFXStyleableLongPropertyBuilder, EFXStyleableLongProperty, Long> {
        /**
         * Constructs a new {@code EFXStyleableLongPropertyBuilder} instance with default settings.
         *
         * <p>This builder is used to create instances of {@link EFXStyleableLongProperty} through a fluent API design. Upon initialization, the builder's initial value is set to {@code null}, indicating that
         * the {@link EFXStyleableLongProperty} created by this builder will not have a predefined initial value unless explicitly set via the builder's methods.</p>
         *
         * <p>This constructor initializes the builder in a state ready for setting up an {@code EFXStyleableLongProperty} instance. Properties such as the property name, CSS metadata, and the style
         * converter can be configured using the provided builder methods.</p>
         *
         * <h2>Usage Example:</h2>
         * <pre>
         * {@code
         * EFXStyleableLongPropertyBuilder<Color> builder = new EFXStyleableLongPropertyBuilder();
         * EFXStyleableLongProperty<Color> colorProperty = builder
         *         .name("text-color")
         *         .initialValue(Color.BLACK)
         *         .styleConverter(ColorConverter.getInstance())
         *         .build();
         * }
         * </pre>
         *
         * <p>In this example, a new {@code EFXStyleableLongPropertyBuilder} is created to build a {@code EFXStyleableLongProperty} for a text color property. The property is configured with an initial
         * value of {@code Color.BLACK} and uses a {@code ColorConverter} for CSS styling.</p>
         */
        public EFXStyleableLongPropertyBuilder() {
            this.initialValue = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected EFXStyleableLongProperty.EFXStyleableLongPropertyBuilder getBuilder() {
            return this;
        }

        /**
         * Finalizes the construction of {@link EFXStyleableLongProperty} instance.
         *
         * @return a new instance of {@code EFXStyleableLongProperty}
         */
        public EFXStyleableLongProperty build() {
            return new EFXStyleableLongProperty(this);
        }
    }

    //endregion Builder
}

