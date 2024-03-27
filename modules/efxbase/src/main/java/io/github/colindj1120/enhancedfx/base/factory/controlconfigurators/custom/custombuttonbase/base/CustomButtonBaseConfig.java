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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.custombuttonbase.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.buttonbase.base.ButtonBaseConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.control.ButtonBase;

/**
 * The {@code CustomButtonBaseConfig} interface merges the detailed customization abilities of {@link BaseCustomConfig} with the core configuration options of {@link ButtonBaseConfig}, targeting the
 * customization of {@link ButtonBase} components within JavaFX. This combined interface facilitates the creation of button components that are highly adaptable, interactive, and visually appealing, extending
 * beyond the capabilities of standard JavaFX buttons. It provides developers with a comprehensive suite of tools for tailoring ButtonBase components to fit specific application needs and enhance user
 * engagement through custom functionalities and styles.
 *
 * <h2>Capabilities</h2>
 * <em>Encapsulates all features from {@link ButtonBaseConfig} and {@link BaseCustomConfig}, including:</em>
 * <ul>
 *   <li>Sophisticated event handling capabilities, enabling the development of buttons with custom interactions.</li>
 *   <li>Dynamic property binding options, allowing the button's behavior and appearance to reactively change in response to user actions or application events.</li>
 *   <li>Advanced customization of behavior and appearance, making use of the extensive tools provided by {@link BaseCustomConfig} for creating unique user experiences.</li>
 *   <li>Direct access to the {@link ButtonBase} node, offering the ability to apply precise and complex customizations directly to the button component.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Here is an illustrative example of how {@code CustomButtonBaseConfig} might be used to configure a custom ButtonBase:
 * <pre>
 * {@code
 * // Initialize a configurator for a custom ButtonBase
 * CustomButtonBaseConfigurator configurator = new CustomButtonBaseConfigurator();
 *
 * // Configure a ButtonBase with custom settings
 * Button myCustomButton = new Button("Press Me");
 * configurator.setNode(myCustomButton)
 *             .setOnAction(e -> System.out.println("Button pressed!")) // Inherited from ButtonBaseConfig
 *             .enableCustomFeedback() // A hypothetical method from BaseCustomConfig
 *             .applyCustomStyling(); // Another hypothetical method for unique styling
 *
 * // The ButtonBase, myCustomButton, now incorporates both standard JavaFX functionality and enhanced custom features
 * }
 * </pre>
 *
 * <p>This example highlights the depth and flexibility of {@code CustomButtonBaseConfig} in creating ButtonBase components that not only fulfill basic user interactions but also introduce additional,
 * customized functionalities for a more engaging and intuitive user interface.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see ButtonBaseConfig
 * @see ButtonBase
 */

public interface CustomButtonBaseConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, ButtonBaseConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    ButtonBase getNode();
}
