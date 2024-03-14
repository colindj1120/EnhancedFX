package io.github.colindj1120.enhancedfx.factories.styling;

import io.github.colindj1120.enhancedfx.beans.property.extendedstyleableproperty.ExtendedStyleableLongProperty;

/**
 * Facilitates the creation of styleable long properties. This class provides a static method to acquire a builder for {@link ExtendedStyleableLongProperty}, simplifying the process of creating
 * customized long properties for UI styling.
 */
public class ExtendedStyleableLongPropertyFactory {
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
