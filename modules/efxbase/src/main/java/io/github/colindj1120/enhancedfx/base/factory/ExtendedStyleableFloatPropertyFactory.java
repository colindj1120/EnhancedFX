package io.github.colindj1120.enhancedfx.base.factory;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableFloatProperty;

/**
 * Facilitates the creation of styleable float properties. This class serves as a factory for {@link EFXStyleableFloatProperty} builders, offering a static method to construct a builder for
 * float properties.
 */
public class ExtendedStyleableFloatPropertyFactory {
    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private ExtendedStyleableFloatPropertyFactory() {}

    /**
     * Generates a builder for constructing {@link EFXStyleableFloatProperty} instances. This static method provides access to a builder tailored for creating styleable float properties. The
     * builder is designed with a fluent API, facilitating the easy and intuitive configuration of float properties for UI styling purposes.
     * <p>
     * This method is instrumental in developing custom float properties for user interface elements, allowing developers to precisely control their appearance and behavior. Such capabilities are
     * crucial for creating responsive and visually engaging UI components that manage floating-point values in a styleable and user-friendly manner.
     * </p>
     *
     * @return a builder for creating a customized {@code EFXStyleableFloatProperty}
     *
     * @see EFXStyleableFloatProperty
     * @see EFXStyleableFloatProperty.Builder
     */
    public static EFXStyleableFloatProperty.Builder builder() {
        return new EFXStyleableFloatProperty.Builder();
    }

}
