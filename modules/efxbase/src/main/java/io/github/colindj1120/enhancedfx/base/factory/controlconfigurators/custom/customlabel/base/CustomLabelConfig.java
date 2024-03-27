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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customlabel.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label.base.LabelConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.control.Label;

/**
 * The {@code CustomLabelConfig} interface blends the functionalities of {@link BaseCustomConfig} with those provided by {@link LabelConfig}, facilitating a comprehensive approach to configuring {@link Label}
 * components in JavaFX. This interface allows for a highly customizable configuration process, empowering developers to enhance Labels with advanced functionalities, behaviors, and styles beyond the
 * capabilities offered by the standard JavaFX Label component. It serves as a foundation for creating Labels that are not only visually appealing but also interactive and dynamic, catering to the specific
 * needs of sophisticated JavaFX applications.
 *
 * <h2>Capabilities</h2>
 * <em>Integrates all capabilities from {@link LabelConfig} and {@link BaseCustomConfig}, including:</em>
 * <ul>
 *   <li>Enhanced event handling capabilities, enabling Labels to respond to a wide array of user interactions in custom ways.</li>
 *   <li>Detailed property binding options, ensuring the Label's properties can dynamically adapt to changes within the application's state or user interface.</li>
 *   <li>Extensive customization of behavior and aesthetics, leveraging the powerful tools provided by {@link BaseCustomConfig} for creating unique and engaging user interfaces.</li>
 *   <li>Immediate access to the {@link Label} node, allowing for direct and precise adjustments to its configuration and presentation.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * An illustrative example of how the {@code CustomLabelConfig} might be used to configure a custom Label is as follows:
 * <pre>
 * {@code
 * // Initialize a configurator for a custom Label
 * CustomLabelConfigurator configurator = new CustomLabelConfigurator();
 *
 * // Configure a Label with custom settings
 * Label myCustomLabel = new Label("Default Text");
 * configurator.setNode(myCustomLabel)
 *             .setTextFill(Color.RED) // Inherited from LabelConfig
 *             .addTooltip("Hover for info") // A hypothetical method from BaseCustomConfig
 *             .applyCustomFontStyle(); // Another hypothetical method for unique styling
 *
 * // myCustomLabel is now fully equipped with both standard JavaFX features and customized enhancements
 * }
 * </pre>
 *
 * <p>This example showcases the extensive customization capabilities of {@code CustomLabelConfig}, demonstrating its utility in developing Labels that exceed the default JavaFX functionalities, offering
 * users a more enriched and tailored interactive experience.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see LabelConfig
 * @see Label
 */
public interface CustomLabelConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, LabelConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    Label getNode();
}
