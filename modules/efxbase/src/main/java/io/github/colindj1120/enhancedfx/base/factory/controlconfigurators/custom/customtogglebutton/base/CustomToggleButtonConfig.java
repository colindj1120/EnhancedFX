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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtogglebutton.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.togglebutton.base.ToggleButtonConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.control.ToggleButton;

/**
 * The {@code CustomToggleButtonConfig} interface extends the capabilities of both {@link BaseCustomConfig} and {@link ToggleButtonConfig} to provide specialized configuration options for custom toggle button
 * components in JavaFX. This interface inherits all methods from its parent interfaces, thus offering a comprehensive approach to configuring custom toggle buttons. These configurations include enhanced
 * properties and behaviors beyond the standard JavaFX {@link ToggleButton} functionalities, making it suitable for developers aiming to craft highly customized toggle button components tailored to specific
 * requirements and interaction patterns.
 *
 * <h2>Capabilities</h2>
 * <em>Inherits all capabilities from {@link ToggleButtonConfig} and {@link BaseCustomConfig}, such as:</em>
 * <ul>
 *   <li>Enhanced listener management for toggle button properties like selected state.</li>
 *   <li>Advanced property binding options to synchronize the toggle button's state with other observable values effectively.</li>
 *   <li>Extended customization options for defining custom behaviors and appearances that leverage the unique features provided by {@link BaseCustomConfig}.</li>
 *   <li>Direct access to the {@link ToggleButton} node being configured, facilitating modifications that go beyond standard toggle button configurations.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Given a class that implements {@code CustomToggleButtonConfig}, below is an example of how it might be used to configure a custom toggle button:
 * <pre>
 * {@code
 * // Create an instance of the custom toggle button configurator
 * CustomToggleButtonConfigurator configurator = new CustomToggleButtonConfigurator();
 *
 * // Set up a new toggle button with custom configurations
 * ToggleButton myCustomToggleButton = new ToggleButton("Toggle Me");
 * configurator.setNode(myCustomToggleButton)
 *             .setSelected(true) // Inherited from ToggleButtonConfig
 *             .addCustomBehavior() // A hypothetical method from BaseCustomConfig or an extension thereof
 *             .applyCustomStyling(); // Another hypothetical method for custom styling
 *
 * // The myCustomToggleButton is now fully configured with both standard and bespoke settings
 * }
 * </pre>
 *
 * <p>This illustrative example demonstrates the enhanced flexibility and capability of {@code CustomToggleButtonConfig} in developing and configuring toggle buttons that surpass the default JavaFX
 * capabilities, thereby enabling the creation of distinctive and highly tailored user interface components.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see ToggleButtonConfig
 * @see ToggleButton
 */
public interface CustomToggleButtonConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, ToggleButtonConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    ToggleButton getNode();
}
