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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.button;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.button.base.ButtonConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Objects;

/**
 * The {@code ButtonConfigurator} class is a specialized configuration utility within the EnhancedFX library, designed to facilitate the customization of {@link Button} controls in JavaFX. This class provides a
 * streamlined, fluent API for configuring buttons, enabling developers to easily modify button properties such as text, styling, and behavior through method chaining.
 *
 * <p>By encapsulating the complexity of direct manipulation of button properties, the configurator ensures a higher level of code maintainability and readability, through through the implementation of
 * {@link ButtonConfig} which serves as a way to access all the necessary {@link Button} functions including the ones it extends, and its extension of {@link ConfiguratorBase}. It promotes a structured approach
 * to UI customization, allowing developers to focus on creating intuitive and engaging user interfaces without getting bogged down in boilerplate code.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Factory Method Initialization</b>: Offers a simple and efficient way to instantiate a new {@code ButtonConfigurator} linked to a specific {@link Button}.</li>
 *     <li><b>Fluent API</b>: Employs method chaining to provide a smooth and intuitive configuration experience.</li>
 *     <li><b>Comprehensive Customization</b>: Supports a wide range of customization options for button appearance, behavior, and event handling.</li>
 *     <li><b>Encapsulation of Button</b>: Safeguards against direct manipulation by encapsulating the {@link Button}, thus promoting the use of the configurator for property adjustments.</li>
 *     <li><b>Integration with Utility Methods</b>: Leverages utility methods for validation and type safety, enhancing the robustness of the configuration process.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(Button)}: Initializes the configurator with a specified {@link Button}, ready for customization.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the current configurator instance, facilitating fluent configuration.</li>
 *     <li>{@link #getNode()}: Provides access to the {@link Button} being configured, enabling further customization.</li>
 *     <li>{@link #setNode(Node)}: Allows for the reassignment of the button to be configured, supporting dynamic customization scenarios.</li>
 *     <li>{@link #nodeEquals(Node)}: Compares the current button with another {@link Node}, useful for conditional configuration logic.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Basic Button Customization</h3>
 * <pre>{@code
 * Button simpleButton = new Button("Click Me");
 * ButtonConfigurator.create(simpleButton)
 *     .text("Press Me")
 *     .style("-fx-background-color: yellow;")
 *     .onAction(e -> System.out.println("Button pressed!"))
 *     .apply();
 * }</pre>
 *
 * <h3>Configuring Multiple Buttons</h3>
 * <pre>{@code
 * Button buttonOne = new Button("First Button");
 * Button buttonTwo = new Button("Second Button");
 *
 * ButtonConfigurator configurator = ButtonConfigurator.create(buttonOne)
 *     .text("Updated First Button")
 *     .apply();
 *
 * configurator.setNode(buttonTwo)
 *     .text("Updated Second Button")
 *     .style("-fx-background-color: blue;")
 *     .apply();
 * }</pre>
 *
 * <p>The {@code ButtonConfigurator} class significantly simplifies the process of configuring buttons in JavaFX applications, streamlining UI development and ensuring that developers can efficiently create
 * customized, user-friendly interfaces.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ButtonConfig
 * @see Button
 * @see Node
 * @see ConfiguratorBase
 */
public class ButtonConfigurator extends ConfiguratorBase implements ButtonConfig<ButtonConfigurator> {
    /**
     * Creates a new {@code ButtonConfigurator} instance for the specified {@link Button}. This factory method facilitates the creation of a configurator tailored to the provided button, enabling immediate
     * access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of buttons through method chaining.</p>
     *
     * @param button
     *         The {@link Button} to be configured by the newly created {@code ButtonConfigurator}.
     *
     * @return A new instance of {@code ButtonConfigurator} linked to the specified button.
     */
    public static ButtonConfigurator create(Button button) {
        return new ButtonConfigurator(button);
    }

    /**
     * The {@link Button} instance that is being configured. This field holds a reference to the specific button object upon which configuration methods will act, enabling the modification and customization of
     * its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the button , allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the button , including its appearance, behavior, and event handling.</p>
     */
    private Button button;

    /**
     * Constructs a new instance of {@code ButtonConfigurator}, associating it with the specified JavaFX {@link Button}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link Button} object, ensuring that the button is not null. This validation step is crucial as it guarantees that the configurator has a valid button to
     * configure, thus preventing null pointer exceptions and other potential issues related to uninitialized buttons. If the passed button is null, an {@link IllegalArgumentException} is thrown, indicating
     * improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link Button} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring the
     * button is non-null at construction, this design promotes a more robust and error-resistant approach to button configuration.</p>
     *
     * @param button
     *         The {@link Button} to be configured by this {@code ButtonConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code button} is null, to enforce the presence of a button to configure.
     * @see Button
     */
    protected ButtonConfigurator(Button button) {
        EFXObjectUtils.isNotNull(button, () -> "Button object cannot be null when using the Button Configurator");
        this.button = button;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Button getNode() {
        return button;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Button object cannot be null when setting the node for the Button Configurator");
        this.button = checkNodeAndCast(value, Button.class, ButtonConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        Button castedValue = checkNodeAndCast(value, Button.class, ButtonConfigurator.class, "nodeEquals");
        return this.button.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return button.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ButtonConfigurator buttonConfigurator) {
            return Objects.equals(buttonConfigurator.getNode(), this.button);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("ButtonConfigurator current button: %s", button.toString());
    }

    //endregion Overridden Functions
}
