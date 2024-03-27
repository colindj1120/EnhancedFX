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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextarea.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textarea.base.TextAreaConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.control.TextArea;

/**
 * The {@code CustomTextAreaConfig} interface enriches the customization framework for TextArea components in JavaFX, blending the functionalities provided by both {@link BaseCustomConfig} and
 * {@link TextAreaConfig}. By inheriting methods from these parent interfaces, it enables a detailed and nuanced approach to configuring TextArea components. This interface allows developers to introduce
 * sophisticated properties and behaviors beyond the capabilities of the standard JavaFX {@link TextArea}, facilitating the creation of text areas that are intricately customized to suit specific user
 * interactions and visual styles.
 *
 * <h2>Capabilities</h2>
 * <em>Combines all functionalities from {@link TextAreaConfig} and {@link BaseCustomConfig}, including:</em>
 * <ul>
 *   <li>Advanced management of event listeners for actions such as text changes and selection updates within the text area.</li>
 *   <li>Comprehensive options for property bindings, enabling seamless integration of the TextArea's properties with other observable values in the application.</li>
 *   <li>Opportunities for custom modifications in behavior and appearance, leveraging the extended features offered by {@link BaseCustomConfig} for superior customization.</li>
 *   <li>Immediate access to the {@link TextArea} node being configured, permitting direct application of custom styles and functionalities.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Here's an illustrative example of how a class implementing {@code CustomTextAreaConfig} might be used to configure a custom TextArea:
 * <pre>
 * {@code
 * // Create a configurator for customizing a TextArea
 * CustomTextAreaConfigurator configurator = new CustomTextAreaConfigurator();
 *
 * // Set up a TextArea with bespoke configurations
 * TextArea myCustomTextArea = new TextArea("Default text");
 * configurator.setNode(myCustomTextArea)
 *             .setWrapText(true) // Inherited from TextAreaConfig
 *             .addTextProcessingFeature() // A hypothetical method from BaseCustomConfig
 *             .applyEnhancedStyling(); // Another hypothetical method for advanced styling
 *
 * // The TextArea, myCustomTextArea, is now thoroughly configured with both the standard and advanced custom settings
 * }
 * </pre>
 *
 * <p>This example highlights the depth and flexibility of {@code CustomTextAreaConfig} in crafting TextArea components that not only align with JavaFX's standard functionality but also include additional,
 * customized features and aesthetics for a richer user interaction and presentation.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see TextAreaConfig
 * @see TextArea
 */
public interface CustomTextAreaConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, TextAreaConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    TextArea getNode();
}
