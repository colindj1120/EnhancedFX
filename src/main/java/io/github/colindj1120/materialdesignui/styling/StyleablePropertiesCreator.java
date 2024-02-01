/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.materialdesignui.styling;

import io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty.*;

/**
 * Provides a centralized and streamlined approach to creating various types of styleable properties. This class serves as a factory for creating builders of different styleable property types. Each
 * nested static class corresponds to a specific styleable property type and offers a static method to obtain a builder for that property type. These builders facilitate the creation of custom,
 * extended styleable properties that can be used in various UI elements for styling purposes.
 * <p>
 * The provided property types include:
 * <ul>
 *     <li>Integer</li>
 *     <li>Double</li>
 *     <li>Float</li>
 *     <li>Long</li>
 *     <li>Boolean</li>
 *     <li>String</li>
 *     <li>Object</li>
 * </ul>
 * Each nested class's builder method returns an instance of a corresponding {@code Builder} class,
 * which is designed to construct a specialized {@code ExtendedStyleableProperty} of the respective type.
 * Utilizing this class simplifies the process of creating different types of styleable properties
 * while ensuring consistency and reducing boilerplate code.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ExtendedStyleableIntegerProperty
 * @see ExtendedStyleableDoubleProperty
 * @see ExtendedStyleableFloatProperty
 * @see ExtendedStyleableLongProperty
 * @see ExtendedStyleableBooleanProperty
 * @see ExtendedStyleableStringProperty
 * @see ExtendedStyleableObjectProperty
 */
public class StyleablePropertiesCreator {
    /**
     * Facilitates the creation of styleable integer properties. This class acts as a factory for building {@link ExtendedStyleableIntegerProperty} instances, providing a static method to obtain a
     * builder specifically for integer properties.
     */
    public static final class StyleableIntegerPropertyCreator {
        /**
         * Generates a builder for constructing {@link ExtendedStyleableIntegerProperty} instances. This static method provides a pathway to a specialized builder dedicated to the creation of
         * styleable integer properties. Featuring a fluent API, this builder enables an intuitive and straightforward process for configuring integer properties for use in UI styling.
         * <p>
         * The utilization of this method is essential in scenarios where integer properties need to be integrated into user interface elements. It allows for the development of custom properties that
         * enhance the interactive and visual aspects of UI components, specifically handling integer values in a styleable and efficient manner.
         * </p>
         *
         * @return a builder for creating a customized {@code ExtendedStyleableIntegerProperty}
         *
         * @see ExtendedStyleableIntegerProperty
         * @see ExtendedStyleableIntegerProperty.Builder
         */
        public static ExtendedStyleableIntegerProperty.Builder builder() {
            return new ExtendedStyleableIntegerProperty.Builder();
        }

    }

    /**
     * Facilitates the creation of styleable double properties. This class provides a static method to obtain a builder for {@link ExtendedStyleableDoubleProperty}, enabling the construction of
     * customized double properties for styling purposes.
     */
    public static final class StyleableDoublePropertyCreator {
        /**
         * Generates a builder for creating {@link ExtendedStyleableDoubleProperty} instances. This static method offers access to a specialized builder that facilitates the construction of styleable
         * double properties. The builder features a fluent interface, enabling straightforward and intuitive configuration of double properties for UI styling purposes.
         * <p>
         * This method is crucial for developing custom double properties in user interface components, allowing developers to finely tune their appearance and behavior. Such customization is key to
         * creating interactive and aesthetically appealing UI elements that handle double precision numerical values in a styleable and user-friendly way.
         * </p>
         *
         * @return a builder for creating a customized {@code ExtendedStyleableDoubleProperty}
         *
         * @see ExtendedStyleableDoubleProperty
         * @see ExtendedStyleableDoubleProperty.Builder
         */
        public static ExtendedStyleableDoubleProperty.Builder builder() {
            return new ExtendedStyleableDoubleProperty.Builder();
        }

    }

    /**
     * Facilitates the creation of styleable float properties. This class serves as a factory for {@link ExtendedStyleableFloatProperty} builders, offering a static method to construct a builder for
     * float properties.
     */
    public static final class StyleableFloatPropertyCreator {
        /**
         * Generates a builder for constructing {@link ExtendedStyleableFloatProperty} instances. This static method provides access to a builder tailored for creating styleable float properties. The
         * builder is designed with a fluent API, facilitating the easy and intuitive configuration of float properties for UI styling purposes.
         * <p>
         * This method is instrumental in developing custom float properties for user interface elements, allowing developers to precisely control their appearance and behavior. Such capabilities are
         * crucial for creating responsive and visually engaging UI components that manage floating-point values in a styleable and user-friendly manner.
         * </p>
         *
         * @return a builder for creating a customized {@code ExtendedStyleableFloatProperty}
         *
         * @see ExtendedStyleableFloatProperty
         * @see ExtendedStyleableFloatProperty.Builder
         */
        public static ExtendedStyleableFloatProperty.Builder builder() {
            return new ExtendedStyleableFloatProperty.Builder();
        }

    }

    /**
     * Facilitates the creation of styleable long properties. This class provides a static method to acquire a builder for {@link ExtendedStyleableLongProperty}, simplifying the process of creating
     * customized long properties for UI styling.
     */
    public static final class StyleableLongPropertyCreator {
        /**
         * Generates a builder for creating {@link ExtendedStyleableLongProperty} instances. This static method provides access to a specialized builder designed for the construction of styleable long
         * properties. With a fluent API, the builder enables straightforward and flexible configuration of long properties tailored for UI styling purposes.
         * <p>
         * This method is particularly useful in scenarios where long properties need to be associated with UI elements, offering enhanced control over their styling and behavior. It's an effective
         * tool for developers to create customized, interactive, and visually consistent UI components that can handle long numerical values in a styleable manner.
         * </p>
         *
         * @return a builder for creating a customized {@code ExtendedStyleableLongProperty}
         *
         * @see ExtendedStyleableLongProperty
         * @see ExtendedStyleableLongProperty.Builder
         */
        public static ExtendedStyleableLongProperty.Builder builder() {
            return new ExtendedStyleableLongProperty.Builder();
        }

    }

    /**
     * Facilitates the creation of styleable boolean properties. This class acts as a factory for obtaining a builder for {@link ExtendedStyleableBooleanProperty}, enabling the creation of boolean
     * properties with customizable styling aspects.
     */
    public static final class StyleableBooleanPropertyCreator {
        /**
         * Generates a builder for constructing {@link ExtendedStyleableBooleanProperty} instances. This static method offers access to a specialized builder that facilitates the creation of styleable
         * boolean properties. The builder features a fluent interface, enabling easy and intuitive configuration of boolean properties for UI styling.
         * <p>
         * This method is essential for developing custom boolean properties in user interface components, allowing developers to fine-tune their appearance and behavior. Such customization is vital
         * for creating interactive and visually appealing UI elements that respond to user interactions or application states in a dynamic way.
         * </p>
         *
         * @return a builder for creating a customized {@code ExtendedStyleableBooleanProperty}
         *
         * @see ExtendedStyleableBooleanProperty
         * @see ExtendedStyleableBooleanProperty.Builder
         */
        public static ExtendedStyleableBooleanProperty.Builder builder() {
            return new ExtendedStyleableBooleanProperty.Builder();
        }

    }

    /**
     * Facilitates the creation of styleable string properties. This class provides a static method for constructing a builder for {@link ExtendedStyleableStringProperty}, allowing for the creation of
     * string properties with specific styling attributes.
     */
    public static final class StyleableStringPropertyCreator {
        /**
         * Generates a new {@link ExtendedStyleableStringProperty.Builder} for building styleable string properties. This static method provides access to a builder specifically designed for the
         * construction of {@code ExtendedStyleableStringProperty} instances. The builder offers a fluent interface, allowing for intuitive and flexible configuration of styleable string properties.
         * <p>
         * The method is particularly valuable for defining custom string properties in UI elements, enabling enhanced control over their styling and behavior. This approach facilitates the creation
         * of properties tailored to specific UI design requirements, improving the overall aesthetics and functionality of the user interface.
         * </p>
         *
         * @return a builder for creating a customized {@code ExtendedStyleableStringProperty}
         *
         * @see ExtendedStyleableStringProperty
         * @see ExtendedStyleableStringProperty.Builder
         */
        public static ExtendedStyleableStringProperty.Builder builder() {
            return new ExtendedStyleableStringProperty.Builder();
        }

    }

    /**
     * Facilitates the creation of styleable object properties of a generic type. This class offers a static method to obtain a builder for {@link ExtendedStyleableObjectProperty}, designed to create
     * object properties with customizable styling options.
     */
    public static final class StyleableObjectPropertyCreator {
        /**
         * Generates a new {@link ExtendedStyleableObjectProperty.Builder} instance for styleable object properties. This generic static method provides a builder for creating instances of
         * {@code ExtendedStyleableObjectProperty} with a specified type parameter. The builder offers a fluent API, enabling easy configuration and customization of the styleable property.
         * <p>
         * This method is particularly useful when creating styleable properties for various object types in UI components, allowing for a high degree of flexibility and customization in UI styling.
         * The generic nature of this method makes it adaptable to a wide range of object types.
         * </p>
         *
         * @param <T>
         *         the type of the object for which the property is to be created
         *
         * @return a builder for creating a customized {@code ExtendedStyleableObjectProperty} of type {@code T}
         *
         * @see ExtendedStyleableObjectProperty
         * @see ExtendedStyleableObjectProperty.Builder
         */
        public static <T> ExtendedStyleableObjectProperty.Builder<T> builder() {
            return new ExtendedStyleableObjectProperty.Builder<>();
        }

    }

}
