package io.github.colindj1120.enhancedfx.base.factory;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableIntegerProperty;

/**
 * Facilitates the creation of styleable integer properties. This class acts as a factory for building {@link EFXStyleableIntegerProperty} instances, providing a static method to obtain a
 * builder specifically for integer properties.
 */
public class ExtendedStyleableIntegerPropertyFactory {
    /**
     * Generates a builder for constructing {@link EFXStyleableIntegerProperty} instances. This static method provides a pathway to a specialized builder dedicated to the creation of
     * styleable integer properties. Featuring a fluent API, this builder enables an intuitive and straightforward process for configuring integer properties for use in UI styling.
     * <p>
     * The utilization of this method is essential in scenarios where integer properties need to be integrated into user interface elements. It allows for the development of custom properties that
     * enhance the interactive and visual aspects of UI components, specifically handling integer values in a styleable and efficient manner.
     * </p>
     *
     * @return a builder for creating a customized {@code EFXStyleableIntegerProperty}
     *
     * @see EFXStyleableIntegerProperty
     * @see EFXStyleableIntegerProperty.Builder
     */
    public static EFXStyleableIntegerProperty.Builder builder() {
        return new EFXStyleableIntegerProperty.Builder();
    }

}
