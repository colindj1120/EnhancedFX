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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombutton.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.button.base.ButtonConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.control.Button;

/**
 * The {@code CustomButtonConfig} interface extends the capabilities of both {@link BaseCustomConfig} and {@link ButtonConfig} to offer specialized configuration for custom button components in JavaFX. It
 * inherits all the methods from its parent interfaces, providing a unified approach to configuring custom buttons with enhanced properties and behaviors beyond the standard JavaFX {@link Button}
 * functionalities. This configurator is ideal for developers looking to create highly customized button components with specific behavior, appearance, and interaction patterns.
 *
 * <h2>Capabilities</h2>
 * <em>Inherits all capabilities from {@link ButtonConfig} and {@link BaseCustomConfig}, including:</em>
 * <ul>
 *   <li>Adding and removing listeners for button properties such as defaultButton and cancelButton.</li>
 *   <li>Binding and unbinding button properties to synchronize with other observable values.</li>
 *   <li>Setting properties and bindings specific to custom properties and bindings, leveraging the extended functionality provided by {@link BaseCustomConfig}.</li>
 *   <li>Direct access to the {@link Button} node being configured, with support for customization beyond standard button configurations.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Assuming you have a class that implements {@code CustomButtonConfig}, here's a simple example of how it can be used to configure a custom button:
 * <pre>
 * {@code
 * // Instantiate the custom button configurator
 * CustomButtonConfigurator configurator = new CustomButtonConfigurator();
 *
 * // Configure a new button with custom settings
 * Button myCustomButton = new Button("Custom Button");
 * configurator.setNode(myCustomButton)
 *             .setDefaultButton(true) // Inherited from ButtonConfig
 *             .addCustomBehavior() // A hypothetical method defined in BaseCustomConfig or a further extension
 *             .applyStyling(); // Another hypothetical method for custom styling
 *
 * // Now, myCustomButton is fully configured with both standard and custom settings
 * }
 * </pre>
 *
 * <p>This example demonstrates the flexibility and power of {@code CustomButtonConfig} in creating and configuring buttons that go beyond the default capabilities, allowing for the creation of unique and
 * tailored user interface components.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see ButtonConfig
 * @see Button
 */
public interface CustomButtonConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, ButtonConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    Button getNode();
}
