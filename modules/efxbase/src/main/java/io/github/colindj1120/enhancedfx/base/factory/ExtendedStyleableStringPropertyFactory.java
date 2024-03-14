package io.github.colindj1120.enhancedfx.base.factory;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableStringProperty;

/**
 * Facilitates the creation of styleable string properties. This class provides a static method for constructing a builder for {@link EFXStyleableStringProperty}, allowing for the creation of
 * string properties with specific styling attributes.
 */
public class ExtendedStyleableStringPropertyFactory {
    /**
     * Generates a new {@link EFXStyleableStringProperty.Builder} for building styleable string properties. This static method provides access to a builder specifically designed for the
     * construction of {@code EFXStyleableStringProperty} instances. The builder offers a fluent interface, allowing for intuitive and flexible configuration of styleable string properties.
     * <p>
     * The method is particularly valuable for defining custom string properties in UI elements, enabling enhanced control over their styling and behavior. This approach facilitates the creation
     * of properties tailored to specific UI design requirements, improving the overall aesthetics and functionality of the user interface.
     * </p>
     *
     * @return a builder for creating a customized {@code EFXStyleableStringProperty}
     *
     * @see EFXStyleableStringProperty
     * @see EFXStyleableStringProperty.Builder
     */
    public static EFXStyleableStringProperty.Builder builder() {
        return new EFXStyleableStringProperty.Builder();
    }

}
