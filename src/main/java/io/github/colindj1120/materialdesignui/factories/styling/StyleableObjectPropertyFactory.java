package io.github.colindj1120.materialdesignui.factories.styling;

import io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty.ExtendedStyleableObjectProperty;

/**
 * Facilitates the creation of styleable object properties of a generic type. This class offers a static method to obtain a builder for {@link ExtendedStyleableObjectProperty}, designed to create
 * object properties with customizable styling options.
 */
public class StyleableObjectPropertyFactory {
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
