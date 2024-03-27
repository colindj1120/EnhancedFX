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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.control.base.ControlConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.control.Control;

/**
 * The {@code CustomControlConfig} interface amalgamates the extensive customization tools provided by {@link BaseCustomConfig} with the fundamental configuration options of {@link ControlConfig}, focusing on
 * the customization of {@link Control} components within JavaFX. This union offers a powerful set of capabilities for developers, enabling them to extensively customize Control elements to meet specific
 * application requirements and user expectations. It paves the way for the development of Control components that are not only functionally rich but also visually distinctive, enhancing the overall user
 * experience of JavaFX applications.
 *
 * <h2>Capabilities</h2>
 * <em>Harmonizes all functionalities from {@link ControlConfig} and {@link BaseCustomConfig}, such as:</em>
 * <ul>
 *   <li>Advanced event handling strategies, facilitating the creation of Controls that interact with users in sophisticated ways.</li>
 *   <li>Robust property binding mechanisms, allowing for the dynamic adjustment of Control properties in response to changes in application state or user input.</li>
 *   <li>Opportunities for in-depth customization of behaviors and styles, leveraging the extended customization opportunities afforded by {@link BaseCustomConfig}.</li>
 *   <li>Direct access to the {@link Control} node for precise adjustments and enhancements, enabling a high degree of customization.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * A conceptual demonstration of using the {@code CustomControlConfig} to configure a custom Control might look as follows:
 * <pre>
 * {@code
 * // Set up a configurator for customizing a Control
 * CustomControlConfigurator configurator = new CustomControlConfigurator();
 *
 * // Establish a Control with tailored configurations
 * Button myCustomControl = new Button("Click Me");
 * configurator.setNode(myCustomControl)
 *             .setTooltip(new Tooltip("Action Button")) // Inherited from ControlConfig
 *             .enableCustomAction() // A hypothetical method from BaseCustomConfig
 *             .applyDynamicStyling(); // Another hypothetical method for applying interactive styling
 *
 * // The Control, myCustomControl, is now fully outfitted with both the standard features of JavaFX Controls and customized enhancements
 * }
 * </pre>
 *
 * <p>This example underscores the vast customization possibilities afforded by {@code CustomControlConfig}, showcasing its value in creating Controls that go beyond conventional functionalities to offer
 * users a more engaging and personalized interaction model.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see ControlConfig
 * @see Control
 */
public interface CustomControlConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, ControlConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    Control getNode();
}
