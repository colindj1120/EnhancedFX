package io.github.colindj1120.materialdesignui.factories.styling;

import io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty.ExtendedStyleableDoubleProperty;

/**
 * Facilitates the creation of styleable double properties. This class provides a static method to obtain a builder for {@link ExtendedStyleableDoubleProperty}, enabling the construction of
 * customized double properties for styling purposes.
 */
public class StyleableDoublePropertyFactory {
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
