/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/EnhancedFX).
 *
 * EnhancedFX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EnhancedFX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with EnhancedFX.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextfield;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextfield.base.CustomTextFieldConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.Objects;

/**
 * The {@code CustomTextFieldConfigurator} class is designed to provide a fluent and type-safe way to configure {@link TextField} components in JavaFX applications. By extending {@link ConfiguratorBase} and
 * implementing {@link CustomTextFieldConfig}, this class allows developers to customize TextFields extensively, including their text content, appearance, and behavior. This is crucial for creating intuitive
 * and interactive forms and input fields in user interfaces.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Offers a fluent API for modifying TextField properties, promoting clean and understandable code.</li>
 *   <li>Ensures type safety with generic type parameterization, preventing common programming mistakes.</li>
 *   <li>Expands on the foundational configuration capabilities provided by {@link ConfiguratorBase} for comprehensive customization.</li>
 *   <li>Enables diverse customizations of TextField components, from basic styling to complex behavior adjustments, catering to varied UI design needs.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomTextField usernameField = new CustomTextField();
 * CustomTextFieldConfigurator<TextField> configurator = CustomTextFieldConfigurator.create(usernameField);
 *
 * configurator
 *     .promptText("Username")
 *     .prefColumnCount(20)
 *     .style("-fx-text-inner-color: grey;");
 * }
 * </pre>
 *
 * <p>This example illustrates the usage of {@code CustomTextFieldConfigurator} to set a prompt text, preferred column count, and inner text color for a CustomTextField. This highlights the configurator's
 * ability to easily tailor Custom TextField attributes for enhancing user interface design and usability.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomTextFieldConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the
 * developer's toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code TextField} being configured, ensuring type-safe customization practices.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomTextFieldConfig
 * @see ConfiguratorBase
 * @see TextField
 */

public class CustomTextFieldConfigurator<T extends TextField> extends ConfiguratorBase implements CustomTextFieldConfig<CustomTextFieldConfigurator<T>> {
    /**
     * Creates a new {@code CustomTextFieldConfigurator} instance for the specified {@link TextField}. This method infers the type of the text field to configure and returns a type-specific configurator. Note
     * that this method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link TextField}.
     *
     * @param customTextField
     *         The {@link TextField} to be configured by the newly created {@code CustomTextFieldConfigurator}.
     * @param <T>
     *         The type of the custom text field.
     *
     * @return A new instance of {@code CustomTextFieldConfigurator} linked to the specified text field.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends TextField> CustomTextFieldConfigurator<T> create(T customTextField) {
        Class<T> type = (Class<T>) customTextField.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomTextFieldConfigurator<>(customTextField, type);
    }

    /**
     * The type parameter of the custom text field being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom text field. Storing the class of {@code T}
     * enables type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended text field type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom text field being configured. This field holds a reference to the specific text field object upon which the configuration methods will act. It enables the modification and
     * customization of the text field's properties and behavior through a fluent API.
     */
    private T customTextField;

    /**
     * Constructs a {@code CustomTextFieldConfigurator} for a specific {@link TextField} type. This protected constructor initializes the configurator with a custom TextField instance and its class type. It
     * ensures that the custom TextField is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for customized
     * configurations tailored to the specific TextField subclass.
     *
     * @param customTextField
     *         The custom TextField instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom TextField. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customTextField} is null, ensuring that a valid TextField instance is provided for configuration.
     */
    protected CustomTextFieldConfigurator(T customTextField, Class<T> type) {
        EFXObjectUtils.isNotNull(customTextField, () -> "Custom Text Field object cannot be null when using the Custom Text Field Configurator");
        this.customTextField = customTextField;
        this.type = type; // Initialize the class field
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomTextFieldConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField getNode() {
        return customTextField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Text Field object cannot be null when setting the text field for the Custom Text Field Configurator");
        this.customTextField = checkNodeAndCast(value, type, CustomTextFieldConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomTextFieldConfigurator.class, "nodeEquals");
        return this.customTextField.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customTextField.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomTextFieldConfigurator<?> customTextFieldConfigurator) {
            return Objects.equals(customTextFieldConfigurator.getNode(), this.customTextField);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomTextFieldConfigurator current custom text field: %s", customTextField.toString());
    }

    //endregion Overridden Methods
}
