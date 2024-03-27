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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextarea;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextarea.base.CustomTextAreaConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

import java.util.Objects;

/**
 * The {@code CustomTextAreaConfigurator} class enables fluent and type-safe configuration of {@link TextArea} components in JavaFX applications. Extending {@link ConfiguratorBase} and implementing
 * {@link CustomTextAreaConfig}, it offers developers a rich set of tools for modifying text area properties, such as text content, font styles, and layout, crucial for creating interactive and user-friendly
 * text input fields.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Facilitates a fluent API for configuring TextArea properties, improving code readability and maintenance.</li>
 *   <li>Provides type safety through generic type parameterization, minimizing the risk of errors.</li>
 *   <li>Utilizes the foundational capabilities of {@link ConfiguratorBase} for advanced and complex configurations.</li>
 *   <li>Supports customization of TextArea components, enabling developers to tailor text input fields according to specific UI requirements.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomTextArea myTextArea = new CustomTextArea("Initial text...");
 * CustomTextAreaConfigurator<CustomTextArea> configurator = CustomTextAreaConfigurator.create(myTextArea);
 *
 * configurator
 *     .prefWidth(400)
 *     .wrapText(true)
 *     .style("-fx-text-fill: darkblue; -fx-font-size: 14px;");
 * }
 * </pre>
 *
 * <p>In this example, {@code CustomTextAreaConfigurator} is used to adjust the width, enable text wrapping, and apply styling to a CustomTextArea component. This illustrates the configurator's capability to
 * easily modify Custom TextArea attributes, facilitating the development of customized text input areas that enhance user interaction.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomTextAreaConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the developer's
 * toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code TextArea} being configured, ensuring type-safe customization operations.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomTextAreaConfig
 * @see ConfiguratorBase
 * @see TextArea
 */
public class CustomTextAreaConfigurator<T extends TextArea> extends ConfiguratorBase implements CustomTextAreaConfig<CustomTextAreaConfigurator<T>> {
    /**
     * Creates a new {@code CustomTextAreaConfigurator} instance for the specified {@link TextArea}. This method infers the type of the text area to configure and returns a type-specific configurator. Note that
     * this method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link TextArea}.
     *
     * @param customTextArea
     *         The {@link TextArea} to be configured by the newly created {@code CustomTextAreaConfigurator}.
     * @param <T>
     *         The type of the custom text area.
     *
     * @return A new instance of {@code CustomTextAreaConfigurator} linked to the specified text area.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends TextArea> CustomTextAreaConfigurator<T> create(T customTextArea) {
        Class<T> type = (Class<T>) customTextArea.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomTextAreaConfigurator<>(customTextArea, type);
    }

    /**
     * The type parameter of the custom text area being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom text area. Storing the class of {@code T}
     * enables type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended text area type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom text area being configured. This field holds a reference to the specific text area object upon which the configuration methods will act. It enables the modification and
     * customization of the text area's properties and behavior through a fluent API.
     */
    private T customTextArea;

    /**
     * Constructs a {@code CustomTextAreaConfigurator} for a specific {@link TextArea} type. This protected constructor initializes the configurator with a custom TextArea instance and its class type. It
     * ensures that the custom TextArea is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for customized
     * configurations tailored to the specific TextArea subclass.
     *
     * @param customTextArea
     *         The custom TextArea instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom TextArea. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customTextArea} is null, ensuring that a valid TextArea instance is provided for configuration.
     */
    protected CustomTextAreaConfigurator(T customTextArea, Class<T> type) {
        EFXObjectUtils.isNotNull(customTextArea, () -> "Custom Text Area object cannot be null when using the Custom Text Area Configurator");
        this.customTextArea = customTextArea;
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
    public CustomTextAreaConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextArea getNode() {
        return customTextArea;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Text Area object cannot be null when setting the text area for the Custom Text Area Configurator");
        this.customTextArea = checkNodeAndCast(value, type, CustomTextAreaConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomTextAreaConfigurator.class, "nodeEquals");
        return this.customTextArea.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customTextArea.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomTextAreaConfigurator<?> customTextAreaConfigurator) {
            return Objects.equals(customTextAreaConfigurator.getNode(), this.customTextArea);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomTextAreaConfigurator current custom text area: %s", customTextArea.toString());
    }

    //endregion Overridden Methods
}
