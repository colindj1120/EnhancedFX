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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombuttonbase;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombuttonbase.base.CustomButtonBaseConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;

import java.util.Objects;

/**
 * The {@code CustomButtonBaseConfigurator} class provides a fluent API for configuring {@link ButtonBase} instances in JavaFX applications. It is designed to allow for easy customization of button base
 * properties, including style, behavior, and event handling, with a focus on type safety and developer convenience. By extending {@link ConfiguratorBase} and implementing {@link CustomButtonBaseConfig}, it
 * offers a comprehensive set of functionalities for enhancing the standard JavaFX {@code ButtonBase} components.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Provides a fluent API for configuring {@code ButtonBase} components, making code more readable and concise.</li>
 *   <li>Ensures type safety by parameterizing the configurator with the specific {@code ButtonBase} subclass being configured.</li>
 *   <li>Facilitates easy integration and customization of button behavior, including custom event handling and property binding.</li>
 *   <li>Leverages the capabilities of the {@link ConfiguratorBase} to provide foundational configuration functionalities.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomButton myButton = new CustomButton("Click Me");
 * CustomButtonBaseConfigurator<CustomButton> configurator = CustomButtonBaseConfigurator.create(myButton);
 *
 * configurator
 *     .style("-fx-background-color: green;")
 *     .onAction(event -> System.out.println("Button clicked!"));
 * }
 * </pre>
 *
 * <p>In this example, the {@code CustomButtonBaseConfigurator} is used to apply a custom background color and define an action event handler for a {@code CustomButton}. This demonstrates the configurator's
 * ability to easily modify properties and enhance the functionality of custom button components in JavaFX.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomButtonBaseConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the
 * developer's toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code ButtonBase} being configured, ensuring type safety across customization operations.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomButtonBaseConfig
 * @see ConfiguratorBase
 * @see ButtonBase
 */
public class CustomButtonBaseConfigurator<T extends ButtonBase> extends ConfiguratorBase implements CustomButtonBaseConfig<CustomButtonBaseConfigurator<T>> {
    /**
     * Creates a new {@code CustomButtonConfigurator} instance for the specified {@link ButtonBase}. This method infers the type of the button base to configure and returns a type-specific configurator. Note
     * that this method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link ButtonBase}.
     *
     * @param customButtonBase
     *         The {@link ButtonBase} to be configured by the newly created {@code CustomButtonConfigurator}.
     * @param <T>
     *         The type of the custom button base.
     *
     * @return A new instance of {@code CustomButtonConfigurator} linked to the specified button base.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends ButtonBase> CustomButtonBaseConfigurator<T> create(T customButtonBase) {
        Class<T> type = (Class<T>) customButtonBase.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomButtonBaseConfigurator<>(customButtonBase, type);
    }

    /**
     * The type parameter of the custom button base being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom button base. Storing the class of {@code T}
     * enables type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended button base type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom button base being configured. This field holds a reference to the specific button base object upon which the configuration methods will act. It enables the modification and
     * customization of the button base's properties and behavior through a fluent API.
     */
    private T customButtonBase;

    /**
     * Constructs a {@code CustomButtonBaseConfigurator} for a specific {@link ButtonBase} type. This protected constructor initializes the configurator with a custom Button instance and its class type. It
     * ensures that the custom Button Base is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for
     * customized configurations tailored to the specific Button Base subclass.
     *
     * @param customButtonBase
     *         The custom Button Base instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom Button Base. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customButtonBase} is null, ensuring that a valid Button Base instance is provided for configuration.
     */
    protected CustomButtonBaseConfigurator(T customButtonBase, Class<T> type) {
        EFXObjectUtils.isNotNull(customButtonBase, () -> "Custom Button Base object cannot be null when using the Custom Button Base Configurator");
        this.customButtonBase = customButtonBase;
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
    public CustomButtonBaseConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBase getNode() {
        return customButtonBase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Button Base object cannot be null when setting the button base for the Custom Button Base Configurator");
        this.customButtonBase = checkNodeAndCast(value, type, CustomButtonBaseConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomButtonBaseConfigurator.class, "nodeEquals");
        return this.customButtonBase.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customButtonBase.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomButtonBaseConfigurator<?> customButtonBaseConfigurator) {
            return Objects.equals(customButtonBaseConfigurator.getNode(), this.customButtonBase);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomButtonBaseConfigurator current custom button base: %s", customButtonBase.toString());
    }

    //endregion Overridden Methods
}
