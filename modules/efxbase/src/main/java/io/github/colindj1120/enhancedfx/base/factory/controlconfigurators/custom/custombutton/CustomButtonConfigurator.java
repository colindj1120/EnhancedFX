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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombutton;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombutton.base.CustomButtonConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Objects;

/**
 * The {@code CustomButtonConfigurator} class specializes in customizing {@link Button} components within JavaFX applications. It extends {@link ConfiguratorBase} and implements the {@link CustomButtonConfig}
 * interface, providing a rich set of functionalities for configuring Button properties, behaviors, and styles in a fluent and type-safe manner. This design ensures that all customizations made through
 * {@code CustomButtonConfigurator} are consistent with JavaFX's styling and behavior paradigms, enabling developers to enhance Button components effectively.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Extends {@link ConfiguratorBase} to leverage foundational configuration functionalities.</li>
 *   <li>Implements {@link CustomButtonConfig}, ensuring access to Button-specific configuration methods.</li>
 *   <li>Supports dynamic and flexible customizations through a fluent API, enhancing developer productivity.</li>
 *   <li>Type-safe configurator instantiation, promoting reliable application of custom configurations to Button instances.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomButton myCustomButton = new CustomButton("Click Me");
 * CustomButtonConfigurator<CustomButton> configurator = CustomButtonConfigurator.create(myCustomButton);
 *
 * configurator
 *     .setStyle("-fx-background-color: blue;")
 *     .setOnAction(e -> System.out.println("Button clicked!"));
 * }
 * </pre>
 *
 * <p>In this example, {@code CustomButtonConfigurator} is utilized to apply a custom background color and an action event to a Custom Button. This illustrates the configurator's ability to modify Custom Button
 * components easily and effectively, using the functionalities provided by {@link CustomButtonConfig}.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomButtonConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the developer's
 * toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of Button being configured, ensuring type safety across customization operations.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomButtonConfig
 * @see ConfiguratorBase
 * @see Button
 */
public class CustomButtonConfigurator<T extends Button> extends ConfiguratorBase implements CustomButtonConfig<CustomButtonConfigurator<T>> {
    /**
     * Creates a new {@code CustomButtonConfigurator} instance for the specified {@link Button}. This method infers the type of the button to configure and returns a type-specific configurator. Note that this
     * method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link Button}.
     *
     * @param customButton
     *         The {@link Button} to be configured by the newly created {@code CustomButtonConfigurator}.
     * @param <T>
     *         The type of the custom button.
     *
     * @return A new instance of {@code CustomButtonConfigurator} linked to the specified button.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends Button> CustomButtonConfigurator<T> create(T customButton) {
        Class<T> type = (Class<T>) customButton.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomButtonConfigurator<>(customButton, type);
    }

    /**
     * The type parameter of the custom button being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom button. Storing the class of {@code T} enables
     * type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended button type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom button being configured. This field holds a reference to the specific button object upon which the configuration methods will act. It enables the modification and customization
     * of the button's properties and behavior through a fluent API.
     */
    private T customButton;

    /**
     * Constructs a {@code CustomButtonConfigurator} for a specific {@link Button} type. This protected constructor initializes the configurator with a custom Button instance and its class type. It ensures that
     * the custom Button is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for customized configurations
     * tailored to the specific Button subclass.
     *
     * @param customButton
     *         The custom Button instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom Button. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customButton} is null, ensuring that a valid Button instance is provided for configuration.
     */
    protected CustomButtonConfigurator(T customButton, Class<T> type) {
        EFXObjectUtils.isNotNull(customButton, () -> "Custom Button object cannot be null when using the Custom Button Configurator");
        this.customButton = customButton;
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
    public CustomButtonConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Button getNode() {
        return customButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Button object cannot be null when setting the button for the Custom Button Configurator");
        this.customButton = checkNodeAndCast(value, type, CustomButtonConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomButtonConfigurator.class, "nodeEquals");
        return this.customButton.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customButton.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomButtonConfigurator<?> customButtonConfigurator) {
            return Objects.equals(customButtonConfigurator.getNode(), this.customButton);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomButtonConfigurator current custom button: %s", customButton.toString());
    }

    //endregion Overridden Methods
}
