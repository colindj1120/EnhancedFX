package io.github.colindj1120.enhancedfx.base.factory;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableBooleanProperty;

/**
 * Facilitates the creation of styleable boolean properties. This class acts as a factory for obtaining a builder for {@link EFXStyleableBooleanProperty}, enabling the creation of boolean
 * properties with customizable styling aspects.
 */
public class ExtendedStyleableBooleanPropertyFactory {
    /**
     * Generates a builder for constructing {@link EFXStyleableBooleanProperty} instances. This static method offers access to a specialized builder that facilitates the creation of styleable
     * boolean properties. The builder features a fluent interface, enabling easy and intuitive configuration of boolean properties for UI styling.
     * <p>
     * This method is essential for developing custom boolean properties in user interface components, allowing developers to fine-tune their appearance and behavior. Such customization is vital
     * for creating interactive and visually appealing UI elements that respond to user interactions or application states in a dynamic way.
     * </p>
     *
     * @return a builder for creating a customized {@code EFXStyleableBooleanProperty}
     *
     * @see EFXStyleableBooleanProperty
     * @see EFXStyleableBooleanProperty.Builder
     */
    public static EFXStyleableBooleanProperty.Builder builder() {
        return new EFXStyleableBooleanProperty.Builder();
    }

}
