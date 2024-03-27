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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customnode;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customnode.base.CustomNodeConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;

import java.util.Objects;

/**
 * The {@code CustomNodeConfigurator} class offers a fluent and type-safe approach for configuring {@link Node} components in JavaFX applications. It extends {@link ConfiguratorBase} and implements
 * {@link CustomNodeConfig}, providing developers with a comprehensive set of tools for customizing the properties, behavior, and appearance of any JavaFX Node. This flexibility is crucial for creating visually
 * appealing and functionally rich user interfaces.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Enables fluent API-based configuration of Node properties, promoting clean and readable code.</li>
 *   <li>Guarantees type safety through generic type parameterization, minimizing runtime errors.</li>
 *   <li>Builds on the foundational features of {@link ConfiguratorBase} for applying complex configurations with ease.</li>
 *   <li>Supports customization across the entire spectrum of JavaFX Node types, enhancing UI design and user experience.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomRectangle myRectangle = new CustomRectangle(100, 100, Color.BLUE);
 * CustomNodeConfigurator<CustomRectangle> configurator = CustomNodeConfigurator.create(myRectangle);
 *
 * configurator
 *     .rotate(45)
 *     .opacity(0.75);
 * }
 * </pre>
 *
 * <p>This example demonstrates the usage of {@code CustomNodeConfigurator} to adjust properties of a {@code CustomRectangle}, such as rotation angle and opacity. It showcases the configurator's ability to
 * easily modify custom JavaFX Node attributes, thereby facilitating the creation of dynamic and responsive UI components.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomNodeConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the developer's
 * toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code Node} being configured, enabling type-safe customizations.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomNodeConfig
 * @see ConfiguratorBase
 * @see Node
 */
public class CustomNodeConfigurator<T extends Node> extends ConfiguratorBase implements CustomNodeConfig<CustomNodeConfigurator<T>> {
    /**
     * Creates a new {@code CustomNodeConfigurator} instance for the specified {@link Node}. This method infers the type of the node to configure and returns a type-specific configurator. Note that this method
     * performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link Node}.
     *
     * @param customNode
     *         The {@link Node} to be configured by the newly created {@code CustomNodeConfigurator}.
     * @param <T>
     *         The type of the custom node.
     *
     * @return A new instance of {@code CustomNodeConfigurator} linked to the specified node.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends Node> CustomNodeConfigurator<T> create(T customNode) {
        Class<T> type = (Class<T>) customNode.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomNodeConfigurator<>(customNode, type);
    }

    /**
     * The type parameter of the custom node being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom node. Storing the class of {@code T} enables
     * type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended node type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom node being configured. This field holds a reference to the specific node object upon which the configuration methods will act. It enables the modification and customization of
     * the node's properties and behavior through a fluent API.
     */
    private T customNode;

    /**
     * Constructs a {@code CustomNodeConfigurator} for a specific {@link Node} type. This protected constructor initializes the configurator with a custom Node instance and its class type. It ensures that the
     * custom Node is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for customized configurations
     * tailored to the specific Node subclass.
     *
     * @param customNode
     *         The custom Node instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom Node. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customNode} is null, ensuring that a valid Node instance is provided for configuration.
     */
    protected CustomNodeConfigurator(T customNode, Class<T> type) {
        EFXObjectUtils.isNotNull(customNode, () -> "Custom Node object cannot be null when using the Custom Node Configurator");
        this.customNode = customNode;
        this.type = type; // Initialize the class field
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomNodeConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getNode() {
        return customNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Node object cannot be null when setting the node for the Custom Node Configurator");
        this.customNode = checkNodeAndCast(value, type, CustomNodeConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomNodeConfigurator.class, "nodeEquals");
        return this.customNode.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customNode.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomNodeConfigurator<?> customNodeConfigurator) {
            return Objects.equals(customNodeConfigurator.getNode(), this.customNode);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomNodeConfigurator current custom node: %s", customNode.toString());
    }

    //endregion Overridden Methods
}
