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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextinputcontrol.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textinputcontrol.base.TextInputControlConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.control.TextInputControl;

/**
 * The {@code CustomTextInputControlConfig} interface enhances the configuration capabilities for custom text input controls within JavaFX, integrating the functionalities of both {@link BaseCustomConfig} and
 * {@link TextInputControlConfig}. This interface inherits all methods from its superclass interfaces, enabling a comprehensive approach towards configuring custom text input controls. These configurations
 * include advanced properties and behaviors that exceed the standard JavaFX {@link TextInputControl} functionalities, making it ideal for developers who aim to create highly customized text input components
 * with specific requirements for behavior, appearance, and interaction patterns.
 *
 * <h2>Capabilities</h2>
 * <em>Inherits all capabilities from {@link TextInputControlConfig} and {@link BaseCustomConfig}, including:</em>
 * <ul>
 *   <li>Advanced listener management for properties relevant to text input controls, such as text value changes.</li>
 *   <li>Enhanced property binding options, allowing for effective synchronization of the text input control's state with other observable values.</li>
 *   <li>Extended customization possibilities for defining custom behaviors and appearances, utilizing the functionalities provided by {@link BaseCustomConfig}.</li>
 *   <li>Direct access to the {@link TextInputControl} node being configured, supporting modifications that extend beyond the standard configurations available for text input controls.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Below is an example of how a class implementing {@code CustomTextInputControlConfig} might be used to configure a custom text input control:
 * <pre>
 * {@code
 * // Instantiate the custom text input control configurator
 * CustomTextInputControlConfigurator configurator = new CustomTextInputControlConfigurator();
 *
 * // Set up a new text input control with custom configurations
 * TextField myCustomTextField = new TextField();
 * configurator.setNode(myCustomTextField)
 *             .setText("Initial Text") // Inherited from TextInputControlConfig
 *             .addCustomBehavior() // A hypothetical method from BaseCustomConfig or an extension thereof
 *             .applyCustomStyling(); // Another hypothetical method for custom styling
 *
 * // The myCustomTextField is now fully configured with both standard and custom settings
 * }
 * </pre>
 *
 * <p>This example showcases the powerful flexibility and enhanced capability of {@code CustomTextInputControlConfig} in creating and configuring text input controls that go beyond the default JavaFX
 * offerings, allowing for the development of unique and highly specialized user interface components.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see TextInputControlConfig
 * @see TextInputControl
 */
public interface CustomTextInputControlConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, TextInputControlConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    TextInputControl getNode();
}
