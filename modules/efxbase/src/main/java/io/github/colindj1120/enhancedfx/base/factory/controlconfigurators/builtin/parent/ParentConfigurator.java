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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.parent;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.parent.base.ParentConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Objects;

/**
 * The {@code ParentConfigurator} class is a pivotal component within the EnhancedFX framework, specifically designed to streamline the configuration of JavaFX {@link Parent} elements. This includes panels,
 * layouts, and any container nodes that can hold child elements. It embodies a fluent interface pattern, enabling developers to effortlessly customize parent nodes with method chaining.
 *
 * <p>By encapsulating direct manipulations of parent properties into a cohesive API, this configurator significantly enhances code readability and maintainability, through the implementation of
 * {@link ParentConfig} which serves as a way to access all the necessary {@link Parent} functions including the ones it extends and its extension of {@link ConfiguratorBase}. It aligns with JavaFX's property
 * and binding ecosystem, facilitating the dynamic creation of responsive and interactive UIs.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Structure and Layout Customization</b>: Offers methods to adjust the layout and structural properties, ensuring optimal arrangement of child nodes.</li>
 *     <li><b>Validation for Stability</b>: Ensures the parent instance is non-null, preventing null pointer exceptions and promoting application stability.</li>
 *     <li><b>Enhanced Child Management</b>: Simplifies the addition, removal, and manipulation of child nodes within the parent, enhancing UI dynamism.</li>
 *     <li><b>Styling and Appearance</b>: Facilitates the application of CSS styles and custom visual modifications to parent nodes, aligning with design requirements.</li>
 *     <li><b>Event Handling Enhancement</b>: Makes it easier to bind event listeners to parent nodes, streamlining interaction design.</li>
 *     <li><b>Factory Method Usage</b>: Employs a static factory method for instantiating the configurator, ensuring a clean start point for configurations.</li>
 *     <li><b>Encapsulated Direct Access</b>: Promotes the use of configurator methods over direct property access, fostering safer and more maintainable code.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(Parent)}: Prepares a {@code ParentConfigurator} for a specified {@link Parent}, readying it for immediate property customization.</li>
 *     <li>{@link #getConfigurator()}: Returns the instance of the configurator, adhering to the fluent interface design for continuous configuration chaining.</li>
 *     <li>{@link #getNode()}: Acquires the current {@link Parent} being configured, allowing for introspection and further customizations.</li>
 *     <li>{@link #setNode(Node)}: Dynamically reassigns the target parent, enabling the reconfiguration of different parent nodes as required.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Configuring a VBox Layout</h3>
 * <pre>{@code
 * VBox vbox = new VBox();
 * ParentConfigurator.create(vbox)
 *     .spacing(10)
 *     .padding(new Insets(5))
 *     .align(Pos.CENTER)
 *     .apply();
 * }</pre>
 *
 * <h3>Applying Effects and Styling to a StackPane</h3>
 * <pre>{@code
 * StackPane stackPane = new StackPane();
 * ParentConfigurator.create(stackPane)
 *     .effect(new DropShadow(15, Color.GRAY))
 *     .style("-fx-background-color: lightblue;")
 *     .apply();
 * }</pre>
 *
 * <p>This configurator is essential for developing sophisticated and aesthetically pleasing JavaFX applications, offering an intuitive approach to node customization. It abstracts away the complexity of
 * property manipulation, allowing developers to focus on crafting engaging user interfaces.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ParentConfig
 * @see Parent
 * @see Node
 * @see ConfiguratorBase
 */
public class ParentConfigurator extends ConfiguratorBase implements ParentConfig<ParentConfigurator> {
    /**
     * Creates a new {@code ParentConfigurator} instance for the specified {@link Parent}. This factory method facilitates the creation of a configurator tailored to the provided parent, enabling immediate
     * access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of parents through method chaining.</p>
     *
     * @param parent
     *         The {@link Parent} to be configured by the newly created {@code ParentConfigurator}.
     *
     * @return A new instance of {@code ParentConfigurator} linked to the specified parent.
     */
    public static ParentConfigurator create(Parent parent) {
        return new ParentConfigurator(parent);
    }

    /**
     * The {@link Parent} instance that is being configured. This field holds a reference to the specific parent object upon which configuration methods will act, enabling the modification and customization of
     * its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the parent, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the parent, including its appearance, behavior, and event handling.</p>
     */
    private Parent parent;

    /**
     * Constructs a new instance of {@code ParentConfigurator}, associating it with the specified JavaFX {@link Parent}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link Parent} object, ensuring that the parent is not null. This validation step is crucial as it guarantees that the configurator has a valid parent to
     * configure, thus preventing null pointer exceptions and other potential issues related to uninitialized parents. If the passed parent is null, an {@link IllegalArgumentException} is thrown, indicating
     * improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link Parent} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring the
     * parent is non-null at construction, this design promotes a more robust and error-resistant approach to parent configuration.</p>
     *
     * @param parent
     *         The {@link Parent} to be configured by this {@code ParentConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code parent} is null, to enforce the presence of a parent to configure.
     * @see Parent
     */
    protected ParentConfigurator(Parent parent) {
        EFXObjectUtils.isNotNull(parent, () -> "Parent object cannot be null when using the Parent Configurator");
        this.parent = parent;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ParentConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parent getNode() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Parent object cannot be null when setting the node for the Parent Configurator");
        this.parent = checkNodeAndCast(value, Parent.class, ParentConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        Parent castedValue = checkNodeAndCast(value, Parent.class, ParentConfigurator.class, "nodeEquals");
        return this.parent.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return parent.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ParentConfigurator configurator) {
            return Objects.equals(configurator.getNode(), this.parent);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("ParentConfigurator current parent: %s", parent.toString());
    }

    //endregion Overridden Functions

}
