package io.github.colindj1120.enhancedfx.factories.styling;

import io.github.colindj1120.enhancedfx.beans.property.extendedstyleableproperty.ExtendedStyleableBooleanProperty;

/**
 * Facilitates the creation of styleable boolean properties. This class acts as a factory for obtaining a builder for {@link ExtendedStyleableBooleanProperty}, enabling the creation of boolean
 * properties with customizable styling aspects.
 */
public class ExtendedStyleableBooleanPropertyFactory {
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
