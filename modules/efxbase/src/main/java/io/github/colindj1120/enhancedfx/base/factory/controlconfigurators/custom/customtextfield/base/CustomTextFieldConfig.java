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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextfield.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textfield.base.TextFieldConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.control.TextField;

/**
 * The {@code CustomTextFieldConfig} interface extends the functionality of both {@link BaseCustomConfig} and {@link TextFieldConfig}, providing a specialized framework for configuring custom TextField
 * components in JavaFX. This interface inherits methods from its parent interfaces, allowing for a holistic approach to customizing text fields. Through these configurations, developers can leverage advanced
 * properties and behaviors that surpass the capabilities of the standard JavaFX {@link TextField}, enabling the creation of text fields that are finely tailored to the application's specific interaction
 * requirements and aesthetic preferences.
 *
 * <h2>Capabilities</h2>
 * <em>Inherits all capabilities from {@link TextFieldConfig} and {@link BaseCustomConfig}, such as:</em>
 * <ul>
 *   <li>Enhanced handling of listener events for text input, including text changes and focus state.</li>
 *   <li>Comprehensive property binding capabilities, facilitating the synchronization of the text field's properties with other observable values within the application.</li>
 *   <li>Custom behavior and appearance modifications, utilizing the extended functionality provided by {@link BaseCustomConfig} to go beyond standard TextField customizations.</li>
 *   <li>Direct access to the {@link TextField} node being configured, allowing for direct modifications and the application of custom styles and behaviors.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * An example usage of the {@code CustomTextFieldConfig} to configure a custom TextField might look like this:
 * <pre>
 * {@code
 * // Instantiate a configurator for a custom TextField
 * CustomTextFieldConfigurator configurator = new CustomTextFieldConfigurator();
 *
 * // Configure a TextField with custom settings
 * TextField myCustomTextField = new TextField("Initial text");
 * configurator.setNode(myCustomTextField)
 *             .setPromptText("Enter your text here") // Inherited from TextFieldConfig
 *             .addCustomListener() // A hypothetical method introduced in BaseCustomConfig
 *             .applyCustomStyle(); // Another hypothetical method for applying custom styling
 *
 * // The TextField, myCustomTextField, is now fully configured with both standard and custom settings
 * }
 * </pre>
 *
 * <p>This example demonstrates the versatility and enhanced capabilities of {@code CustomTextFieldConfig} for developing text fields that not only meet the default JavaFX functionality but also incorporate
 * additional custom features and behaviors for a more rich and interactive user experience.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see TextFieldConfig
 * @see TextField
 */
public interface CustomTextFieldConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, TextFieldConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    TextField getNode();
}
