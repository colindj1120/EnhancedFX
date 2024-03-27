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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textinputcontrol;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textinputcontrol.base.TextInputControlConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;

import java.util.Objects;

/**
 * The {@code TextInputControlConfigurator} class is a comprehensive tool designed to simplify the configuration of {@link TextInputControl} elements within the JavaFX framework.
 *
 * <p>This class enables developers to customize text input controls, such as text fields and text areas, through a fluent API that supports method chaining. The configurator abstracts the complexity involved
 * in direct manipulation of text input properties, thereby improving code readability and maintainability, through through the implementation of {@link TextInputControlConfig} which serves as a way to access
 * all the necessary {@link TextInputControl} functions including the ones it extends, and its extension of {@link ConfiguratorBase}.</p>
 *
 * <p>This class is part of the EnhancedFX library, which aims to extend JavaFX's capabilities, making it easier for developers to create rich, interactive user interfaces. The
 * {@code TextInputControlConfigurator} is particularly focused on facilitating the setup and customization of text input controls, enabling developers to efficiently apply a wide range of configurations with
 * minimal boilerplate code.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Configuration Factory</b>: Utilizes a static factory method to instantiate configurator objects, ensuring a streamlined initialization process.</li>
 *     <li><b>Fluent API Design</b>: Employs method chaining to provide a concise and intuitive way of configuring text input controls.</li>
 *     <li><b>Comprehensive Property Customization</b>: Allows for detailed customization of text input control properties, including but not limited to text, font size, and event handlers.</li>
 *     <li><b>Encapsulation of TextInputControl</b>: Encourages best practices by encapsulating the {@link TextInputControl} instance, thus promoting the use of the configurator's methods for property
 *     adjustments.</li>
 *     <li><b>Utility Methods Integration</b>: Incorporates utility methods from {@code EFXObjectUtils} to ensure non-null configurator instances and facilitate safe type conversions.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(TextInputControl)}: Creates a new instance of {@code TextInputControlConfigurator} for the specified {@link TextInputControl}.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the current instance of {@code TextInputControlConfigurator}, maintaining the fluent API design.</li>
 *     <li>{@link #getNode()}: Accesses the {@link TextInputControl} being configured, enabling further modifications.</li>
 *     <li>{@link #setNode(Node)}: Replaces the target {@link TextInputControl}, allowing the configurator to modify a different control.</li>
 *     <li>{@link #nodeEquals(Node)}: Checks if the given {@link Node} is equivalent to the {@link TextInputControl} currently being configured.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Configuring a TextField</h3>
 * <pre>{@code
 * TextField textField = new TextField();
 * TextInputControlConfigurator.create(textField)
 *     .promptText("Enter your name...")
 *     .applyCss("-fx-font-size: 16px;")
 *     .apply();
 * }</pre>
 *
 * <h3>Setting Up a TextArea with Event Handlers</h3>
 * <pre>{@code
 * TextArea textArea = new TextArea();
 * TextInputControlConfigurator.create(textArea)
 *     .wrapText(true)
 *     .onAction(event -> System.out.println("Submitted!"))
 *     .apply();
 * }</pre>
 *
 * <p>This configurator significantly reduces the effort required to configure text input controls in JavaFX applications, abstracting away the boilerplate code and offering a more elegant solution for UI
 * customization.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextInputControlConfig
 * @see TextInputControl
 * @see Node
 * @see ConfiguratorBase
 */
public class TextInputControlConfigurator extends ConfiguratorBase implements TextInputControlConfig<TextInputControlConfigurator> {
    /**
     * Creates a new {@code TextInputControlConfigurator} instance for the specified {@link TextInputControl}. This factory method facilitates the creation of a configurator tailored to the provided text input
     * control, enabling immediate access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of text input controls through method chaining.</p>
     *
     * @param textInputControl
     *         The {@link TextInputControl} to be configured by the newly created {@code TextInputControlConfigurator}.
     *
     * @return A new instance of {@code TextInputControlConfigurator} linked to the specified text input control.
     */
    public static TextInputControlConfigurator create(TextInputControl textInputControl) {
        return new TextInputControlConfigurator(textInputControl);
    }

    /**
     * The {@link TextInputControl} instance that is being configured. This field holds a reference to the specific textInputControl object upon which configuration methods will act, enabling the modification
     * and customization of its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the textInputControl, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and
     * maintainable approach to UI customization. The configurator provides a fluent API for configuring various aspects of the textInputControl, including its appearance, behavior, and event handling.</p>
     */
    private TextInputControl textInputControl;

    /**
     * Constructs a new instance of {@code TextInputControlConfigurator}, associating it with the specified JavaFX {@link TextInputControl}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link TextInputControl} object, ensuring that the text input control is not null. This validation step is crucial as it guarantees that the configurator has a
     * valid text input control to configure, thus preventing null pointer exceptions and other potential issues related to uninitialized text input controls. If the passed text input control is null, an
     * {@link IllegalArgumentException} is thrown, indicating improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link TextInputControl} properties, enhancing readability and ease of use when setting up UI components programmatically. By
     * ensuring the text input control is non-null at construction, this design promotes a more robust and error-resistant approach to text input control configuration.</p>
     *
     * @param textInputControl
     *         The {@link TextInputControl} to be configured by this {@code TextInputControlConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code textInputControl} is null, to enforce the presence of a text input control to configure.
     * @see TextInputControl
     */
    protected TextInputControlConfigurator(TextInputControl textInputControl) {
        EFXObjectUtils.isNotNull(textInputControl, () -> "Text Input Control object cannot be null when using the Text Input Control Configurator");
        this.textInputControl = textInputControl;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControl getNode() {
        return textInputControl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Text Input Control object cannot be null when setting the node for the Text Input Control Configurator");

        this.textInputControl = checkNodeAndCast(value, TextInputControl.class, TextInputControlConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        TextInputControl castedValue = checkNodeAndCast(value, TextInputControl.class, TextInputControlConfigurator.class, "nodeEquals");
        return this.textInputControl.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return textInputControl.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextInputControlConfigurator textInputControlConfigurator) {
            return Objects.equals(textInputControlConfigurator.getNode(), this.textInputControl);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("TextInputControlConfigurator current text input control: %s", textInputControl.toString());
    }

    //endregion Overridden Functions
}
