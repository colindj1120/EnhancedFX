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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.buttonbase;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.buttonbase.base.ButtonBaseConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;

import java.util.Objects;

/**
 * The {@code ButtonBaseConfigurator} class offers a robust mechanism for configuring {@link ButtonBase} instances in JavaFX applications, part of the EnhancedFX library. This configurator simplifies the
 * process of setting properties and behaviors of button bases, such as buttons and toggle buttons, through a fluent API designed for ease of use and code clarity.
 *
 * <p>Encapsulating the complexities associated with direct property manipulation, the configurator allows developers to maintain a clean and readable codebase, through through the implementation of
 * {@link ButtonBaseConfig} which serves as a way to access all the necessary {@link ButtonBase} functions including the ones it extends, and its extension of {@link ConfiguratorBase}. By leveraging method
 * chaining, it facilitates a declarative approach to UI programming, enhancing the overall development experience.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Efficient Instantiation</b>: Utilizes a factory method to instantiate configurator instances, streamlining the configuration setup.</li>
 *     <li><b>Fluent Interface</b>: Employs method chaining to enable straightforward and intuitive property setting.</li>
 *     <li><b>Extensive Customization</b>: Offers a wide array of configuration options for modifying the appearance, behavior, and event handling of button bases.</li>
 *     <li><b>Encapsulation</b>: Ensures safe configuration by encapsulating the {@link ButtonBase} instance, preventing direct access and promoting a more structured UI customization approach.</li>
 *     <li><b>Utility Integration</b>: Incorporates utility methods for non-null checks and safe type conversions, enhancing the reliability and robustness of the configuration process.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(ButtonBase)}: Prepares a {@code ButtonBaseConfigurator} for a specified {@link ButtonBase}, readying it for customization.</li>
 *     <li>{@link #getConfigurator()}: Obtains the current instance of the configurator, supporting the fluent interface design.</li>
 *     <li>{@link #getNode()}: Accesses the {@link ButtonBase} being configured, permitting further customization.</li>
 *     <li>{@link #setNode(Node)}: Assigns a new {@link ButtonBase} for configuration, allowing dynamic changes to different instances.</li>
 *     <li>{@link #nodeEquals(Node)}: Compares the current button base with another node, facilitating conditional customization logic.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Configuring a Button</h3>
 * <pre>{@code
 * Button button = new Button("Initial Text");
 * ButtonBaseConfigurator.create(button)
 *     .text("Updated Text")
 *     .style("-fx-background-color: green;")
 *     .onAction(e -> System.out.println("Button clicked"))
 *     .apply();
 * }</pre>
 *
 * <h3>Applying Configuration to Multiple Button Bases</h3>
 * <pre>{@code
 * ToggleButton toggleButton = new ToggleButton("Toggle Me");
 * ButtonBaseConfigurator configurator = ButtonBaseConfigurator.create(toggleButton)
 *     .text("Toggled Text")
 *     .apply();
 *
 * Button anotherButton = new Button("Another Button");
 * configurator.setNode(anotherButton)
 *     .text("Button Text Updated")
 *     .style("-fx-border-color: red;")
 *     .apply();
 * }</pre>
 *
 * <p>The {@code ButtonBaseConfigurator} significantly eases the customization of button bases in JavaFX, ensuring developers can efficiently create interactive and visually appealing UI components.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ButtonBaseConfig
 * @see ButtonBase
 * @see Node
 * @see ConfiguratorBase
 */
public class ButtonBaseConfigurator extends ConfiguratorBase implements ButtonBaseConfig<ButtonBaseConfigurator> {
    /**
     * Creates a new {@code ButtonBaseConfigurator} instance for the specified {@link ButtonBase}. This factory method facilitates the creation of a configurator tailored to the provided button base, enabling
     * immediate access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of button bases through method chaining.</p>
     *
     * @param buttonBase
     *         The {@link ButtonBase} to be configured by the newly created {@code ButtonBaseConfigurator}.
     *
     * @return A new instance of {@code ButtonBaseConfigurator} linked to the specified button base.
     */
    public static ButtonBaseConfigurator create(ButtonBase buttonBase) {
        return new ButtonBaseConfigurator(buttonBase);
    }

    /**
     * The {@link ButtonBase} instance that is being configured. This field holds a reference to the specific button base object upon which configuration methods will act, enabling the modification and
     * customization of its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the button base, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the button base, including its appearance, behavior, and event handling.</p>
     */
    private ButtonBase buttonBase;

    /**
     * Constructs a new instance of {@code ButtonBaseConfigurator}, associating it with the specified JavaFX {@link ButtonBase}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link ButtonBase} object, ensuring that the button base is not null. This validation step is crucial as it guarantees that the configurator has a valid button
     * base to configure, thus preventing null pointer exceptions and other potential issues related to uninitialized button bases. If the passed button base is null, an {@link IllegalArgumentException} is
     * thrown, indicating improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link ButtonBase} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring
     * the button base is non-null at construction, this design promotes a more robust and error-resistant approach to button base configuration.</p>
     *
     * @param buttonBase
     *         The {@link ButtonBase} to be configured by this {@code ButtonBaseConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code buttonBase} is null, to enforce the presence of a button base to configure.
     * @see Label
     */
    protected ButtonBaseConfigurator(ButtonBase buttonBase) {
        EFXObjectUtils.isNotNull(buttonBase, () -> "Button Base object cannot be null when using the Button Base Configurator");
        this.buttonBase = buttonBase;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBase getNode() {
        return buttonBase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Button Base object cannot be null when setting the node for the Button Base Configurator");
        this.buttonBase = checkNodeAndCast(value, ButtonBase.class, ButtonBaseConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        ButtonBase castedValue = checkNodeAndCast(value, ButtonBase.class, ButtonBaseConfigurator.class, "nodeEquals");
        return this.buttonBase.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return buttonBase.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ButtonBaseConfigurator buttonBaseConfigurator) {
            return Objects.equals(buttonBaseConfigurator.getNode(), this.buttonBase);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("ButtonBaseConfigurator current button base: %s", buttonBase.toString());
    }

    //endregion Overridden Functions
}
