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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.base.CustomControlConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.Control;

import java.util.Objects;

/**
 * The {@code CustomControlConfigurator} class serves as a specialized configurator for {@link Control} components within JavaFX applications. It extends {@link ConfiguratorBase} and implements
 * {@link CustomControlConfig}, providing a type-safe, fluent API for modifying and enhancing the functionalities of JavaFX control elements. This configurator simplifies the process of customizing control
 * properties, behaviors, and styles, ensuring a seamless development experience when building sophisticated user interfaces.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Facilitates the type-safe configuration of {@code Control} components through a fluent API, enhancing code readability.</li>
 *   <li>Enables extensive customization of control properties, including styles, behaviors, and event handling.</li>
 *   <li>Utilizes the robust foundation provided by {@link ConfiguratorBase} to apply comprehensive control configurations.</li>
 *   <li>Supports a wide array of JavaFX controls, offering flexible options for UI development and design.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomComboBox<String> myComboBox = new ComboBox<>();
 * CustomControlConfigurator<CustomComboBox<String>> configurator = CustomControlConfigurator.create(myComboBox);
 *
 * configurator
 *     .style("-fx-font-size: 14px;")
 *     .addItem("Option 1")
 *     .addItem("Option 2")
 *     .selectItem("Option 1");
 * }
 * </pre>
 *
 * <p>In this example, {@code CustomControlConfigurator} is utilized to configure a {@code CustomComboBox} with custom font size and items. It demonstrates how the configurator can be used to apply specific
 * customizations to custom controls, improving the flexibility and functionality of JavaFX UI components.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomControlConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the developer's
 * toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code Control} being configured, enabling type-safe customization.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomControlConfig
 * @see ConfiguratorBase
 * @see Control
 */
public class CustomControlConfigurator<T extends Control> extends ConfiguratorBase implements CustomControlConfig<CustomControlConfigurator<T>> {
    /**
     * Creates a new {@code CustomNodeConfigurator} instance for the specified {@link Control}. This method infers the type of the control to configure and returns a type-specific configurator. Note that this
     * method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link Control}.
     *
     * @param customControl
     *         The {@link Control} to be configured by the newly created {@code CustomNodeConfigurator}.
     * @param <T>
     *         The type of the custom control.
     *
     * @return A new instance of {@code CustomNodeConfigurator} linked to the specified control.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends Control> CustomControlConfigurator<T> create(T customControl) {
        Class<T> type = (Class<T>) customControl.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomControlConfigurator<>(customControl, type);
    }

    /**
     * The type parameter of the custom control being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom control. Storing the class of {@code T} enables
     * type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended control type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom control being configured. This field holds a reference to the specific control object upon which the configuration methods will act. It enables the modification and
     * customization of the control's properties and behavior through a fluent API.
     */
    private T customControl;

    /**
     * Constructs a {@code CustomControlConfigurator} for a specific {@link Control} type. This protected constructor initializes the configurator with a custom Control instance and its class type. It ensures
     * that the custom Control is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for customized
     * configurations tailored to the specific Control subclass.
     *
     * @param customControl
     *         The custom Control instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom Control. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customControl} is null, ensuring that a valid Control instance is provided for configuration.
     */
    protected CustomControlConfigurator(T customControl, Class<T> type) {
        EFXObjectUtils.isNotNull(customControl, () -> "Custom Control object cannot be null when using the Custom Control Configurator");
        this.customControl = customControl;
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
    public CustomControlConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Control getNode() {
        return customControl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Control object cannot be null when setting the node for the Custom Control Configurator");
        this.customControl = checkNodeAndCast(value, type, CustomControlConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomControlConfigurator.class, "nodeEquals");
        return this.customControl.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customControl.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomControlConfigurator<?> customControlConfigurator) {
            return Objects.equals(customControlConfigurator.getNode(), this.customControl);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomControlConfigurator current custom control: %s", customControl.toString());
    }

    //endregion Overridden Methods
}
