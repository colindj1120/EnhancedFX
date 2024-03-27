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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.node;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.node.base.NodeConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;

import java.util.Objects;

/**
 * The {@code NodeConfigurator} class provides a fluent and comprehensive interface for configuring JavaFX {@link Node} objects within the EnhancedFX framework. It simplifies the customization of nodes,
 * offering an intuitive approach to adjusting properties, behavior, and event handling of JavaFX scene graph elements, through the implementation of {@link NodeConfig} which serves as a way to access all the
 * necessary {@link Node} functions.
 *
 * <p>By encapsulating property manipulation into a structured and fluent API and extending {@link ConfiguratorBase}, this configurator enhances the readability and maintainability of UI setup code. It is
 * designed to work seamlessly with JavaFX's property and binding mechanisms, allowing developers to create dynamic and responsive UIs with minimal effort.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Fluent Configuration</b>: Enables a chainable method pattern for straightforward node customization, improving code readability.</li>
 *     <li><b>Robust Validation</b>: Guarantees that the node instance is not null, enhancing stability by avoiding null pointer exceptions.</li>
 *     <li><b>Flexible Customization</b>: Provides access to a wide range of properties for fine-tuned control over node appearance and functionality.</li>
 *     <li><b>Event Handling Setup</b>: Simplifies the process of attaching event handlers and listeners to nodes, facilitating interaction design.</li>
 *     <li><b>Factory Method</b>: Utilizes a static factory method for instantiation, ensuring a consistent and simplified configuration start point.</li>
 *     <li><b>Encapsulated Property Access</b>: Encourages the use of configurator methods for property adjustments, promoting encapsulation and safety.</li>
 *     <li><b>Seamless JavaFX Integration</b>: Designed to complement JavaFX's architecture, ensuring that configurations are both effective and performant.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(Node)}: Instantiates a {@code NodeConfigurator} for a specific {@link Node}, enabling immediate customization.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the configurator instance itself, supporting fluent configuration chaining.</li>
 *     <li>{@link #getNode()}: Returns the currently targeted {@link Node}, allowing for inspection and further customization.</li>
 *     <li>{@link #setNode(Node)}: Updates the configurator's target, facilitating the reconfiguration of different nodes as needed.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Configuring a Rectangle</h3>
 * <pre>{@code
 * Rectangle rectangle = new Rectangle(100, 100, Color.GREEN);
 * NodeConfigurator.create(rectangle)
 *     .rotate(45)
 *     .scaleX(1.5)
 *     .scaleY(1.5)
 *     .apply();
 * }</pre>
 *
 * <h3>Configuring a Group with Event Handlers</h3>
 * <pre>{@code
 * Group group = new Group();
 * NodeConfigurator.create(group)
 *     .onMouseEntered(e -> group.setOpacity(0.7))
 *     .onMouseExited(e -> group.setOpacity(1.0))
 *     .apply();
 * }</pre>
 *
 * <h3>Applying Effects to a Node</h3>
 * <pre>{@code
 * Text text = new Text("Hello, EnhancedFX!");
 * NodeConfigurator.create(text)
 *     .effect(new DropShadow(10, Color.BLACK))
 *     .apply();
 * }</pre>
 *
 * <p>This configurator streamlines the task of setting up and customizing JavaFX nodes, fostering a clean and organized approach to UI development. By abstracting direct property manipulation, it allows
 * developers to focus on creating engaging and dynamic user interfaces.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see NodeConfig
 * @see Node
 * @see ConfiguratorBase
 */
public class NodeConfigurator extends ConfiguratorBase implements NodeConfig<NodeConfigurator> {
    /**
     * Creates a new {@code NodeConfigurator} instance for the specified {@link Node}. This factory method facilitates the creation of a configurator tailored to the provided node, enabling immediate access to
     * configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of nodes through method chaining.</p>
     *
     * @param node
     *         The {@link Node} to be configured by the newly created {@code NodeConfigurator}.
     *
     * @return A new instance of {@code NodeConfigurator} linked to the specified node.
     */
    public static NodeConfigurator create(Node node) {
        return new NodeConfigurator(node);
    }

    /**
     * The {@link Node} instance that is being configured. This field holds a reference to the specific node object upon which configuration methods will act, enabling the modification and customization of its
     * properties and behavior.
     *
     * <p>This private member ensures encapsulation of the node, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable approach
     * to UI customization. The configurator provides a fluent API for configuring various aspects of the node, including its appearance, behavior, and event handling.</p>
     */
    private Node node;

    /**
     * Constructs a new instance of {@code NodeConfigurator}, associating it with the specified JavaFX {@link Node}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link Node} object, ensuring that the node is not null. This validation step is crucial as it guarantees that the configurator has a valid node to
     * configure, thus preventing null pointer exceptions and other potential issues related to uninitialized nodes. If the passed node is null, an {@link IllegalArgumentException} is thrown, indicating
     * improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link Node} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring the
     * node is non-null at construction, this design promotes a more robust and error-resistant approach to node configuration.</p>
     *
     * @param node
     *         The {@link Node} to be configured by this {@code NodeConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code node} is null, to enforce the presence of a node to configure.
     * @see Node
     */
    protected NodeConfigurator(Node node) {
        EFXObjectUtils.isNotNull(node, () -> "NOde object cannot be null when using the Node Configurator");
        this.node = node;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getNode() {
        return node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Node object cannot be null when setting the node for the Node Configurator");
        this.node = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        return this.node.equals(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return node.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NodeConfigurator configurator) {
            return Objects.equals(configurator.getNode(), this.node);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("NodeConfigurator current node: %s", node.toString());
    }

    //endregion Overridden Functions
}
