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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customparent.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.parent.base.ParentConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.Parent;

/**
 * The {@code CustomParentConfig} interface combines the detailed customization capabilities of {@link BaseCustomConfig} with the foundational functionalities of {@link ParentConfig}, aimed at configuring
 * custom {@link Parent} components in JavaFX. By inheriting methods from both parent interfaces, it provides a rich set of tools for developers to extensively customize Parent components. This interface
 * facilitates the creation of highly refined and functional sophisticated Parent nodes, which can serve as the backbone for complex user interface structures, accommodating specific layout, styling, and
 * behavior requirements.
 *
 * <h2>Capabilities</h2>
 * <em>Merges all functionalities from {@link ParentConfig} and {@link BaseCustomConfig}, offering:</em>
 * <ul>
 *   <li>Comprehensive event handling mechanisms for custom interaction models within the Parent node.</li>
 *   <li>Enhanced property binding capabilities to dynamically adapt the Parent's attributes in concert with changes to other observable properties.</li>
 *   <li>Custom behavior and appearance modification options, leveraging the expansive customization possibilities afforded by {@link BaseCustomConfig}.</li>
 *   <li>Immediate and direct access to the {@link Parent} node, enabling deep customization and fine-tuning of the component's layout and visual presentation.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * An illustrative example of employing the {@code CustomParentConfig} to configure a custom Parent component might be as follows:
 * <pre>
 * {@code
 * // Instantiate a configurator for customizing a Parent component
 * CustomParentConfigurator configurator = new CustomParentConfigurator();
 *
 * // Set up a Parent component with custom configurations
 * Group myCustomParent = new Group();
 * configurator.setNode(myCustomParent)
 *             .setAutoSizeChildren(false) // Inherited from ParentConfig
 *             .addCustomLayoutMechanism() // A hypothetical method introduced by BaseCustomConfig
 *             .applyCustomStyling(); // Another hypothetical method for advanced styling
 *
 * // The Parent component, myCustomParent, now fully embodies both the generic JavaFX capabilities and the specialized custom features
 * }
 * </pre>
 *
 * <p>Through this example, the depth and flexibility of {@code CustomParentConfig} in crafting Parent components are highlighted, showcasing the ability to not just conform to JavaFX's standards but to
 * extend beyond them, enabling the development of complex and uniquely tailored user interface structures.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see ParentConfig
 * @see Parent
 */

public interface CustomParentConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, ParentConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    Parent getNode();
}
