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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtogglebutton;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtogglebutton.base.CustomToggleButtonConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;

import java.util.Objects;

/**
 * The {@code CustomToggleButtonConfigurator} class enables the fluent and type-safe customization of {@link ToggleButton} components within JavaFX applications. Extending {@link ConfiguratorBase} and
 * implementing {@link CustomToggleButtonConfig}, this configurator offers a comprehensive toolkit for modifying ToggleButton properties, including their appearance, behavior, and text. This is essential for
 * developers looking to create intuitive and visually appealing toggle buttons for user interfaces.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Provides a fluent API for the straightforward configuration of ToggleButton properties, improving code readability.</li>
 *   <li>Ensures type safety with generic parameterization, enhancing code reliability and reducing errors.</li>
 *   <li>Utilizes the foundational features of {@link ConfiguratorBase} for advanced configuration capabilities.</li>
 *   <li>Facilitates a wide range of customizations for ToggleButtons, enabling precise control over UI components.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomToggleButton myToggleButton = new CustomToggleButton("Toggle Me");
 * CustomToggleButtonConfigurator<CustomToggleButton> configurator = CustomToggleButtonConfigurator.create(myToggleButton);
 *
 * configurator
 *     .selected(true)
 *     .style("-fx-background-color: lightblue; -fx-text-fill: navy;");
 * }
 * </pre>
 *
 * <p>This example demonstrates the use of {@code CustomToggleButtonConfigurator} to set the initial selection state and apply custom styling to a CustomToggleButton. It illustrates the configurator's
 * ability to easily enhance and tailor CustomToggleButton components for specific UI design needs.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomToggleButtonConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the
 * developer's toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code ToggleButton} being configured, ensuring type-safe customization operations.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomToggleButtonConfig
 * @see ConfiguratorBase
 * @see ToggleButton
 * @see BaseCustomConfig
 */
public class CustomToggleButtonConfigurator<T extends ToggleButton> extends ConfiguratorBase implements CustomToggleButtonConfig<CustomToggleButtonConfigurator<T>> {
    /**
     * Creates a new {@code CustomToggleButtonConfigurator} instance for the specified {@link ToggleButton}. This method infers the type of the toggle button to configure and returns a type-specific
     * configurator. Note that this method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link ToggleButton}.
     *
     * @param customToggleButton
     *         The {@link ToggleButton} to be configured by the newly created {@code CustomToggleButtonConfigurator}.
     * @param <T>
     *         The type of the custom toggle button.
     *
     * @return A new instance of {@code CustomToggleButtonConfigurator} linked to the specified toggle button.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends ToggleButton> CustomToggleButtonConfigurator<T> create(T customToggleButton) {
        Class<T> type = (Class<T>) customToggleButton.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomToggleButtonConfigurator<>(customToggleButton, type);
    }

    /**
     * The type parameter of the custom toggle button being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom toggle button. Storing the class of
     * {@code T} enables type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended toggle button type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom toggle button being configured. This field holds a reference to the specific toggle button object upon which the configuration methods will act. It enables the modification and
     * customization of the toggle button's properties and behavior through a fluent API.
     */
    private T customToggleButton;

    /**
     * Constructs a {@code CustomToggleButtonConfigurator} for a specific {@link ToggleButton} type. This protected constructor initializes the configurator with a custom ToggleButton instance and its class
     * type. It ensures that the custom ToggleButton is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for
     * customized configurations tailored to the specific ToggleButton subclass.
     *
     * @param customToggleButton
     *         The custom ToggleButton instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom ToggleButton. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customToggleButton} is null, ensuring that a valid ToggleButton instance is provided for configuration.
     */
    protected CustomToggleButtonConfigurator(T customToggleButton, Class<T> type) {
        EFXObjectUtils.isNotNull(customToggleButton, () -> "Custom Toggle Button object cannot be null when using the Custom Toggle Button Configurator");
        this.customToggleButton = customToggleButton;
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
    public CustomToggleButtonConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToggleButton getNode() {
        return customToggleButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Toggle Button object cannot be null when setting the toggle button for the Custom Toggle Button Configurator");
        this.customToggleButton = checkNodeAndCast(value, type, CustomToggleButtonConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomToggleButtonConfigurator.class, "nodeEquals");
        return this.customToggleButton.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customToggleButton.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomToggleButtonConfigurator<?> customToggleButtonConfigurator) {
            return Objects.equals(customToggleButtonConfigurator.getNode(), this.customToggleButton);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomToggleButtonConfigurator current custom toggle button: %s", customToggleButton.toString());
    }

    //endregion Overridden Methods
}
