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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customnode.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.node.base.NodeConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.base.BaseCustomConfig;
import javafx.scene.Node;

/**
 * The {@code CustomNodeConfig} interface is designed to merge the advanced customization capabilities of {@link BaseCustomConfig} with the foundational configuration options provided by {@link NodeConfig},
 * targeting the customization of {@link Node} components within JavaFX. This powerful interface inherits from its predecessors, enabling developers to significantly enhance the functionality, appearance, and
 * behavior of Node components. It offers a broad range of customization possibilities, from simple aesthetic modifications to complex behavioral enhancements, facilitating the creation of highly specialized
 * and interactive user interface components.
 *
 * <h2>Capabilities</h2>
 * <em>Incorporates all features from {@link NodeConfig} and {@link BaseCustomConfig}, including:</em>
 * <ul>
 *   <li>Rich event handling options for crafting custom interaction patterns within the Node.</li>
 *   <li>Extensive property binding features, allowing for dynamic changes to the Node's attributes in sync with other parts of the application UI.</li>
 *   <li>The ability to define custom behaviors and visual styles, capitalizing on the advanced customization opportunities offered by {@link BaseCustomConfig}.</li>
 *   <li>Direct manipulation of the {@link Node} itself, providing the means to apply detailed and complex customizations directly to the UI component.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * Here is a conceptual demonstration of how {@code CustomNodeConfig} might be employed to configure a custom Node:
 * <pre>
 * {@code
 * // Prepare a configurator for a custom Node
 * CustomNodeConfigurator configurator = new CustomNodeConfigurator();
 *
 * // Establish a Node with tailored configurations
 * Rectangle myCustomNode = new Rectangle(100, 200);
 * configurator.setNode(myCustomNode)
 *             .setFill(Color.BLUE) // Inherited from NodeConfig
 *             .enableDragAndDrop() // A hypothetical method from BaseCustomConfig
 *             .applySpecialEffects(); // Another hypothetical method for applying unique visual effects
 *
 * // The Node, myCustomNode, is now comprehensively configured, blending JavaFX standard features with enhanced custom functionality
 * }
 * </pre>
 *
 * <p>This example accentuates the comprehensive customization potential of {@code CustomNodeConfig}, highlighting its utility in developing Node components that not only meet the standard JavaFX
 * capabilities but also introduce additional, customized functionalities for a more engaging and dynamic user experience.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseCustomConfig
 * @see NodeConfig
 * @see Node
 */
public interface CustomNodeConfig<T extends ConfiguratorBase> extends BaseCustomConfig<T>, NodeConfig<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    Node getNode();
}
