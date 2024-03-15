package io.github.colindj1120.enhancedfx.base.factory;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableDoubleProperty;

/**
 * Facilitates the creation of styleable double properties. This class provides a static method to obtain a builder for {@link EFXStyleableDoubleProperty}, enabling the construction of
 * customized double properties for styling purposes.
 */
public class ExtendedStyleableDoublePropertyFactory {
    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private ExtendedStyleableDoublePropertyFactory() {}

    /**
     * Generates a builder for creating {@link EFXStyleableDoubleProperty} instances. This static method offers access to a specialized builder that facilitates the construction of styleable
     * double properties. The builder features a fluent interface, enabling straightforward and intuitive configuration of double properties for UI styling purposes.
     * <p>
     * This method is crucial for developing custom double properties in user interface components, allowing developers to finely tune their appearance and behavior. Such customization is key to
     * creating interactive and aesthetically appealing UI elements that handle double precision numerical values in a styleable and user-friendly way.
     * </p>
     *
     * @return a builder for creating a customized {@code EFXStyleableDoubleProperty}
     *
     * @see EFXStyleableDoubleProperty
     * @see EFXStyleableDoubleProperty.Builder
     */
    public static EFXStyleableDoubleProperty.Builder builder() {
        return new EFXStyleableDoubleProperty.Builder();
    }

}
