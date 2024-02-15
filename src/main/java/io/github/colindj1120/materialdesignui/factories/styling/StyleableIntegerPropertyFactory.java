package io.github.colindj1120.materialdesignui.factories.styling;

import io.github.colindj1120.materialdesignui.beans.property.extendedstyleableproperty.ExtendedStyleableIntegerProperty;

/**
 * Facilitates the creation of styleable integer properties. This class acts as a factory for building {@link ExtendedStyleableIntegerProperty} instances, providing a static method to obtain a
 * builder specifically for integer properties.
 */
public class StyleableIntegerPropertyFactory {
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
