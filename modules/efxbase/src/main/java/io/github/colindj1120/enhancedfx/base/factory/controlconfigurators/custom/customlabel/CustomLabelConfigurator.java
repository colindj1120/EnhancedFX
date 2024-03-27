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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customlabel;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customlabel.base.CustomLabelConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.Objects;

/**
 * The {@code CustomLabelConfigurator} class provides a fluent and type-safe API for configuring {@link Label} components within JavaFX applications. By extending {@link ConfiguratorBase} and implementing
 * {@link CustomLabelConfig}, it offers a comprehensive toolkit for modifying the appearance, behavior, and functionality of labels, catering to the nuanced needs of sophisticated user interfaces.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Enables fluent and intuitive configuration of {@code Label} properties, enhancing code readability and maintainability.</li>
 *   <li>Supports type-safe customization, reducing runtime errors and increasing developer productivity.</li>
 *   <li>Leverages the foundational capabilities of {@link ConfiguratorBase} to apply advanced configurations seamlessly.</li>
 *   <li>Facilitates the implementation of dynamic UI themes and styles, promoting a consistent and engaging user experience.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomLabel titleLabel = new CustomLabel("Welcome");
 * CustomLabelConfigurator<CustomLabel> configurator = CustomLabelConfigurator.create(titleLabel);
 *
 * configurator
 *     .style("-fx-text-fill: navy;")
 *     .wrapText(true)
 *     .font(new Font("Arial", 16));
 * }
 * </pre>
 *
 * <p>This example illustrates the use of {@code CustomLabelConfigurator} to customize a {@code CustomLabel} with specific style properties. It demonstrates the configurator's capacity to easily adjust font,
 * color, and text wrapping, enabling developers to fine-tune UI components with minimal effort.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomLabelConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the developer's
 * toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code Label} being configured, ensuring type safety throughout the customization process.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomLabelConfig
 * @see ConfiguratorBase
 * @see Label
 */
public class CustomLabelConfigurator<T extends Label> extends ConfiguratorBase implements CustomLabelConfig<CustomLabelConfigurator<T>> {
    /**
     * Creates a new {@code CustomLabelConfigurator} instance for the specified {@link Label}. This method infers the type of the label to configure and returns a type-specific configurator. Note that this
     * method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link Label}.
     *
     * @param customLabel
     *         The {@link Label} to be configured by the newly created {@code CustomLabelConfigurator}.
     * @param <T>
     *         The type of the custom label.
     *
     * @return A new instance of {@code CustomLabelConfigurator} linked to the specified label.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends Label> CustomLabelConfigurator<T> create(T customLabel) {
        Class<T> type = (Class<T>) customLabel.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomLabelConfigurator<>(customLabel, type);
    }

    /**
     * The type parameter of the custom label being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom label. Storing the class of {@code T} enables
     * type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended label type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom label being configured. This field holds a reference to the specific label object upon which the configuration methods will act. It enables the modification and customization
     * of the label's properties and behavior through a fluent API.
     */
    private T customLabel;

    /**
     * Constructs a {@code CustomLabelConfigurator} for a specific {@link Label} type. This protected constructor initializes the configurator with a custom Label instance and its class type. It ensures that
     * the custom Label is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for customized configurations
     * tailored to the specific Label subclass.
     *
     * @param customLabel
     *         The custom Label instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom Label. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customLabel} is null, ensuring that a valid Label instance is provided for configuration.
     */
    protected CustomLabelConfigurator(T customLabel, Class<T> type) {
        EFXObjectUtils.isNotNull(customLabel, () -> "Custom Label object cannot be null when using the Custom Label Configurator");
        this.customLabel = customLabel;
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
    public CustomLabelConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Label getNode() {
        return customLabel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Label object cannot be null when setting the label for the Custom Label Configurator");
        this.customLabel = checkNodeAndCast(value, type, CustomLabelConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomLabelConfigurator.class, "nodeEquals");
        return this.customLabel.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customLabel.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomLabelConfigurator<?> customLabelConfigurator) {
            return Objects.equals(customLabelConfigurator.getNode(), this.customLabel);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomLabelConfigurator current custom label: %s", customLabel.toString());
    }

    //endregion Overridden Methods
}
