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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.control;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.control.base.ControlConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.Control;

import java.util.Objects;

/**
 * The {@code ControlConfigurator} class facilitates the fluent and structured configuration of JavaFX {@link Control} instances, enhancing UI development productivity and maintainability through the
 * implementation of {@link ControlConfig} which serves as a way to access all the necessary {@link Control} functions including the ones it extends, and its extension of {@link ConfiguratorBase}.
 *
 * <p>This class abstracts the complexity of directly manipulating control properties, offering a centralized and intuitive mechanism for customizing the appearance and behavior of UI controls. It is designed
 * to integrate seamlessly with JavaFX's property and binding systems, enabling developers to apply custom configurations and styles with minimal boilerplate code.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Fluent API Design</b>: Supports method chaining for concise and readable control configuration.</li>
 *     <li><b>Robust Validation</b>: Ensures control instances are not null, preventing runtime errors and ensuring stability.</li>
 *     <li><b>Extensive Customization</b>: Offers methods for configuring a wide range of control properties, including visuals, behavior, and event handling.</li>
 *     <li><b>Factory Method Pattern</b>: Simplifies configurator instantiation, promoting ease of use and consistency.</li>
 *     <li><b>Enhanced Encapsulation</b>: Restricts direct access to the control's properties, fostering a more structured approach to UI customization.</li>
 *     <li><b>Seamless JavaFX Integration</b>: Designed to work harmoniously with JavaFX's styling and animation frameworks.</li>
 *     <li><b>Improved Maintainability</b>: Encourages separation of concerns by isolating control configuration logic.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(Control)}: Factory method for creating a {@code ControlConfigurator} instance for a specific {@link Control}.</li>
 *     <li>{@link #getConfigurator()}: Returns the configurator instance, supporting the fluent API design.</li>
 *     <li>{@link #getNode()}: Retrieves the current {@link Control} being configured.</li>
 *     <li>{@link #setNode(Node)}: Sets a new {@link Node} to the configurator, allowing dynamic changes to the targeted control.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * Button myButton = new Button("Click Me");
 * ControlConfigurator.create(myButton)
 *     .style("-fx-background-color: blue;")
 *     .onAction(e -> System.out.println("Button clicked!"));
 * }</pre>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ControlConfig
 * @see Control
 * @see Node
 * @see ConfiguratorBase
 */
public class ControlConfigurator extends ConfiguratorBase implements ControlConfig<ControlConfigurator> {
    /**
     * Creates a new {@code ControlConfigurator} instance for the specified {@link Control}. This factory method facilitates the creation of a configurator tailored to the provided control, enabling immediate
     * access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of controls through method chaining.</p>
     *
     * @param control
     *         The {@link Control} to be configured by the newly created {@code ControlConfigurator}.
     *
     * @return A new instance of {@code ControlConfigurator} linked to the specified control.
     */
    public static ControlConfigurator create(Control control) {
        return new ControlConfigurator(control);
    }

    /**
     * The {@link Control} instance that is being configured. This field holds a reference to the specific control object upon which configuration methods will act, enabling the modification and customization
     * of its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the control, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the control, including its appearance, behavior, and event handling.</p>
     */
    private Control control;

    /**
     * Constructs a new instance of {@code ControlConfigurator}, associating it with the specified JavaFX {@link Control}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link Control} object, ensuring that the control is not null. This validation step is crucial as it guarantees that the configurator has a valid control to
     * configure, thus preventing null pointer exceptions and other potential issues related to uninitialized controls. If the passed control is null, an {@link IllegalArgumentException} is thrown, indicating
     * improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link Control} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring the
     * control is non-null at construction, this design promotes a more robust and error-resistant approach to control configuration.</p>
     *
     * @param control
     *         The {@link Control} to be configured by this {@code ControlConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code control} is null, to enforce the presence of a control to configure.
     * @see Control
     */
    protected ControlConfigurator(Control control) {
        EFXObjectUtils.isNotNull(control, () -> "Control object cannot be null when using the Region Configurator");
        this.control = control;
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Control getNode() {
        return this.control;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Control object cannot be null when setting the node for the Control Configurator");
        this.control = checkNodeAndCast(value, Control.class, ControlConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        Control castedValue = checkNodeAndCast(value, Control.class, ControlConfigurator.class, "nodeEquals");
        return this.control.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return control.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ControlConfigurator controlConfigurator) {
            return Objects.equals(controlConfigurator.getNode(), this.control);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("RegionConfigurator current control: %s", control.toString());
    }

    //endregion Overridden Methods
}
