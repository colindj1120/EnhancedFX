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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.togglebutton;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.togglebutton.base.ToggleButtonConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;

import java.util.Objects;

/**
 * The {@code ToggleButtonConfigurator} class is a dedicated tool within the EnhancedFX framework, designed to facilitate the customization and configuration of {@link ToggleButton} controls in JavaFX
 * applications through the implementation of {@link ToggleButtonConfig} which serves as a way to access all the necessary {@link ToggleButton} functions including the ones it extends, and its extension of
 * {@link ConfiguratorBase}. It offers a fluent API that simplifies the process of setting various properties and behaviors of toggle buttons, making UI development more intuitive and efficient.
 *
 * <p>By encapsulating the complexity of direct property manipulation, this configurator promotes a cleaner, more maintainable approach to UI design. It leverages the method chaining pattern to enhance code
 * readability and encourage a declarative style of UI programming. The configurator is tailored to the needs of developers looking to streamline the setup of toggle buttons with minimal code.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Fluent Configuration API</b>: Enables the chaining of configuration methods for concise and readable code.</li>
 *     <li><b>Customization Support</b>: Offers extensive support for customizing the appearance and behavior of toggle buttons, including text, styling, and event handling.</li>
 *     <li><b>Factory Method for Instantiation</b>: Utilizes a factory method pattern to simplify the creation of configurator instances, thereby streamlining the configuration process.</li>
 *     <li><b>Encapsulation and Safety</b>: Ensures the safety of toggle button customization by encapsulating the {@link ToggleButton} instance, thus preventing direct access and modification.</li>
 *     <li><b>Utility Method Integration</b>: Incorporates utility methods for non-null validation and type-safe casting, enhancing robustness and error handling.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(ToggleButton)}: Initializes a new {@code ToggleButtonConfigurator} for a given {@link ToggleButton}, ready for configuration.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the current {@code ToggleButtonConfigurator} instance, supporting the fluent interface pattern.</li>
 *     <li>{@link #getNode()}: Accesses the {@link ToggleButton} being configured, allowing for further customization.</li>
 *     <li>{@link #setNode(Node)}: Sets a new {@link ToggleButton} for configuration, facilitating the reuse of the configurator for different instances.</li>
 *     <li>{@link #nodeEquals(Node)}: Compares the configurator's current toggle button with another node, enabling conditional configuration logic.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Basic Toggle Button Configuration</h3>
 * <pre>{@code
 * ToggleButton toggleButton = new ToggleButton("Toggle Me");
 * ToggleButtonConfigurator.create(toggleButton)
 *     .selected(true)
 *     .text("Active State")
 *     .apply();
 * }</pre>
 *
 * <h3>Advanced Toggle Button Customization</h3>
 * <pre>{@code
 * ToggleButton advancedToggleButton = new ToggleButton("Advanced Toggle");
 * ToggleButtonConfigurator.create(advancedToggleButton)
 *     .selected(false)
 *     .style("-fx-background-color: lightblue;")
 *     .onAction(event -> System.out.println("Toggled!"))
 *     .apply();
 * }</pre>
 *
 * <p>The {@code ToggleButtonConfigurator} plays a vital role in simplifying JavaFX UI development by providing a powerful yet straightforward mechanism for configuring toggle buttons, enhancing both
 * productivity and code quality.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ToggleButtonConfig
 * @see ToggleButton
 * @see Node
 * @see ConfiguratorBase
 */
public class ToggleButtonConfigurator extends ConfiguratorBase implements ToggleButtonConfig<ToggleButtonConfigurator> {
    /**
     * Creates a new {@code ToggleButtonConfigurator} instance for the specified {@link ToggleButton}. This factory method facilitates the creation of a configurator tailored to the provided toggle button,
     * enabling immediate access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of toggle buttons through method chaining.</p>
     *
     * @param toggleButton
     *         The {@link ToggleButton} to be configured by the newly created {@code ToggleButtonConfigurator}.
     *
     * @return A new instance of {@code ToggleButtonConfigurator} linked to the specified toggle button.
     */
    public static ToggleButtonConfigurator create(ToggleButton toggleButton) {
        return new ToggleButtonConfigurator(toggleButton);
    }

    /**
     * The {@link ToggleButton} instance that is being configured. This field holds a reference to the specific toggle button object upon which configuration methods will act, enabling the modification and
     * customization of its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the toggle button , allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the toggle button , including its appearance, behavior, and event handling.</p>
     */
    private ToggleButton toggleButton;

    /**
     * Constructs a new instance of {@code ToggleButtonConfigurator}, associating it with the specified JavaFX {@link ToggleButton}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link ToggleButton} object, ensuring that the toggle button is not null. This validation step is crucial as it guarantees that the configurator has a valid
     * toggle button to configure, thus preventing null pointer exceptions and other potential issues related to uninitialized toggle buttons. If the passed toggle button is null, an
     * {@link IllegalArgumentException} is thrown, indicating improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link ToggleButton} properties, enhancing readability and ease of use when setting up UI components programmatically. By
     * ensuring the toggle button is non-null at construction, this design promotes a more robust and error-resistant approach to toggle button configuration.</p>
     *
     * @param toggleButton
     *         The {@link ToggleButton} to be configured by this {@code ToggleButtonConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code toggleButton} is null, to enforce the presence of a toggle button to configure.
     * @see ToggleButton
     */
    protected ToggleButtonConfigurator(ToggleButton toggleButton) {
        EFXObjectUtils.isNotNull(toggleButton, () -> "Toggle Button object cannot be null when using the Toggle Button Configurator");
        this.toggleButton = toggleButton;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ToggleButtonConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToggleButton getNode() {
        return toggleButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Toggle Button object cannot be null when setting the node for the Toggle Button Configurator");
        this.toggleButton = checkNodeAndCast(value, ToggleButton.class, ToggleButtonConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        ToggleButton castedValue = checkNodeAndCast(value, ToggleButton.class, ToggleButtonConfigurator.class, "nodeEquals");
        return this.toggleButton.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return toggleButton.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToggleButtonConfigurator toggleButtonConfigurator) {
            return Objects.equals(toggleButtonConfigurator.getNode(), this.toggleButton);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("ToggleButtonConfigurator current toggle button: %s", toggleButton.toString());
    }

    //endregion Overridden Functions
}
