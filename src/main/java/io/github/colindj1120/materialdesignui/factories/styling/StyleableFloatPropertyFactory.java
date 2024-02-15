package io.github.colindj1120.materialdesignui.factories.styling;

import io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty.ExtendedStyleableFloatProperty;

/**
 * Facilitates the creation of styleable float properties. This class serves as a factory for {@link ExtendedStyleableFloatProperty} builders, offering a static method to construct a builder for
 * float properties.
 */
public class StyleableFloatPropertyFactory {
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
