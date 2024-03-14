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
package io.github.colindj1120.enhancedfx.controls.factory.configurators.base;

import javafx.scene.Node;

/**
 * Serves as the foundational base for all configurator classes within the EnhancedFX framework, providing common functionality and
 * utility methods to interact with {@link Node} instances in JavaFX. This abstract class outlines the essential operations that all
 * configurators must implement, specifically targeting the retrieval and setting of the {@link Node} to be configured.
 *
 * <p>
 * By centralizing common configuration logic, {@code ConfiguratorBase} allows for a standardized approach to modifying the properties
 * and event handlers of JavaFX components. It also ensures type safety and reduces code duplication across the different configurator
 * implementations.
 * </p>
 *
 * <p>
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Abstract methods for getting and setting the target {@link Node}, enabling dynamic reconfiguration of UI components.</li>
 *     <li>A utility method for validating the type and non-nullity of the node, ensuring robustness and error-free configuration.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The design encourages a modular architecture where specific configurators can extend this base class to inherit common
 * functionality, focusing on extending and specializing the configuration process for different types of {@link Node} elements.
 * </p>
 *
 * <p>
 * <em>Usage Example:</em>
 * <pre>{@code
 * public class ButtonConfigurator extends ConfiguratorBase {
 *     private Button button;
 *
 *     public ButtonConfigurator(Button button) {
 *         this.setNode(button);
 *     }
 *
 *     @Override
 *     public Node getNode() {
 *         return this.button;
 *     }
 *
 *     @Override
 *     public void setNode(Node value) {
 *         this.button = checkNodeNotNullAndInstanceOf(value, Button.class, ButtonConfigurator.class, "setNode");
 *     }
 *
 *     // Additional configuration methods specific to Button...
 * }
 *
 * Button myButton = new Button("Click Me");
 * ButtonConfigurator configurator = new ButtonConfigurator(myButton);
 * configurator.setNode(new Button("New Button"));
 * }</pre>
 * </p>
 *
 * <p>This class is instrumental in establishing a consistent and scalable structure for configuring UI components within the
 * EnhancedFX library, fostering a clear separation of concerns and promoting code reuse.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Node
 */
public abstract class ConfiguratorBase {
    /**
     * Retrieves the {@link Node} instance associated with this configurator. The node represents the target for all configuration
     * actions performed by implementing classes. This method is a key part of the {@code ConfiguratorBase} interface, as it provides
     * the link between the configurator and the actual UI component being configured.
     *
     * @return The {@link Node} instance to be configured. Implementations should ensure that this method never returns {@code null} to
     *         avoid null pointer exceptions during the configuration process.
     */
    public abstract Node getNode();

    /**
     * Sets the {@link Node} that this configurator is responsible for configuring. This method allows changing the target {@link Node}
     * of the configurator, enabling reuse of the same configurator instance for different nodes. It abstractly defines the contract
     * for implementing classes to provide a specific mechanism to update the node reference.
     *
     * @param value
     *         The new {@link Node} to be configured by this configurator. It replaces the current node reference within the
     *         configurator, allowing subsequent configuration actions to be applied to the new node. The method ensures that the
     *         configurator always has a valid {@link Node} reference to work with, facilitating dynamic changes to the UI elements
     *         being configured.
     */
    public abstract void setNode(Node value);

    /**
     * Validates that a given node is not null and is an instance of the specified class.
     *
     * @param <T>
     *         The type of the node, extending from {@link Node}.
     * @param node
     *         The node to check.
     * @param nodeClazz
     *         The class object of the expected type.
     * @param implementingClazz
     *         The class implementing the functionality, used for error messaging.
     * @param functionName
     *         The name of the function from which this method is called, used for error messaging.
     *
     * @return The node, if validation passes, ensuring it is not null and of the specified type.
     *
     * @throws IllegalArgumentException
     *         if the node is null or not an instance of the specified class.
     */
    protected static <T extends Node> T checkNodeNotNullAndInstanceOf(Node node, Class<T> nodeClazz, Class<?> implementingClazz,
                                                                      String functionName) {
        if (node == null) {
            String nullMessage = "%s cannot be null when using the %s called from %s".formatted(nodeClazz.getSimpleName(),
                                                                                                implementingClazz.getSimpleName(),
                                                                                                functionName);
            throw new IllegalArgumentException(nullMessage);
        }

        if (!nodeClazz.isInstance(node)) {
            String errorMessageFormat = "Node must be an instance of %s when using the %s called from %s. Found: %s";
            String instanceErrorMessage = errorMessageFormat.formatted(nodeClazz.getSimpleName(), implementingClazz.getSimpleName(),
                                                                       functionName, node.getClass()
                                                                                         .getSimpleName());
            throw new IllegalArgumentException(instanceErrorMessage);
        }

        return nodeClazz.cast(node);
    }
}
