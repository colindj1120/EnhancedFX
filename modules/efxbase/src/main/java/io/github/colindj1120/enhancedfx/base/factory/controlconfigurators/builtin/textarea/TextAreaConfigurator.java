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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textarea;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textarea.base.TextAreaConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

import java.util.Objects;

/**
 * The {@code TextAreaConfigurator} class is a specialized tool within the EnhancedFX framework aimed at streamlining the configuration process for JavaFX {@link TextArea} elements. By offering a fluent and
 * intuitive API, this configurator enables developers to efficiently customize text areas, facilitating the implementation of dynamic and interactive text input fields in user interfaces, through through the
 * implementation of {@link TextAreaConfig} which serves as a way to access all the necessary {@link TextArea} functions including the ones it extends, and its extension of {@link ConfiguratorBase}.
 *
 * <p>Encapsulating the complexity of directly manipulating text area properties, the configurator enhances the readability and maintainability of code related to text input customization. It leverages
 * JavaFX's powerful property and binding mechanisms, providing developers with a straightforward way to adjust text area characteristics such as text content, font, and behavior.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Text Management</b>: Facilitates the manipulation of text content, including setting initial text, placeholder text, and handling text input and changes.</li>
 *     <li><b>Visual Customization</b>: Enables adjustments to the text area's appearance, including font size, background color, and border styles.</li>
 *     <li><b>Behavioral Adjustments</b>: Allows for the configuration of text area behavior, such as wrapping text, scroll policies, and editable states.</li>
 *     <li><b>Event Handling</b>: Simplifies the process of attaching event listeners for handling user interactions and text input events.</li>
 *     <li><b>Factory Method Pattern</b>: Employs a static factory method for easy instantiation, ensuring a clean and efficient start to text area configuration.</li>
 *     <li><b>Encapsulated Property Access</b>: Promotes the use of configurator methods for property adjustments, supporting safer and more structured code.</li>
 *     <li><b>Integration with JavaFX</b>: Designed to complement JavaFX's architecture, ensuring that configurations are performant and consistent with JavaFX standards.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(TextArea)}: Prepares a {@code TextAreaConfigurator} for a specified {@link TextArea}, readying it for detailed customization.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the instance of the configurator, maintaining the fluent interface design for ongoing configuration.</li>
 *     <li>{@link #getNode()}: Obtains the {@link TextArea} being configured, permitting further inspection and adjustments.</li>
 *     <li>{@link #setNode(Node)}: Dynamically reassigns the target text area, enabling the reconfiguration of various text area instances as needed.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Basic TextArea Configuration</h3>
 * <pre>{@code
 * TextArea textArea = new TextArea();
 * TextAreaConfigurator.create(textArea)
 *     .promptText("Enter your text here...")
 *     .wrapText(true)
 *     .apply();
 * }</pre>
 *
 * <h3>Styling a TextArea</h3>
 * <pre>{@code
 * TextAreaConfigurator.create(textArea)
 *     .style("-fx-text-fill: darkblue; -fx-font-size: 14px;")
 *     .apply();
 * }</pre>
 *
 * <p>This configurator is a crucial component for developers looking to enhance the user interaction capabilities of their JavaFX applications. It abstracts away the intricacies of text area configuration,
 * enabling the efficient and effective customization of text input fields.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextAreaConfig
 * @see TextArea
 * @see Node
 * @see ConfiguratorBase
 */
public class TextAreaConfigurator extends ConfiguratorBase implements TextAreaConfig<TextAreaConfigurator> {
    /**
     * Creates a new {@code TextAreaConfigurator} instance for the specified {@link TextArea}. This factory method facilitates the creation of a configurator tailored to the provided text field, enabling
     * immediate access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of text fields through method chaining.</p>
     *
     * @param textArea
     *         The {@link TextArea} to be configured by the newly created {@code TextAreaConfigurator}.
     *
     * @return A new instance of {@code TextAreaConfigurator} linked to the specified text field.
     */
    public static TextAreaConfigurator create(TextArea textArea) {
        return new TextAreaConfigurator(textArea);
    }

    /**
     * The {@link TextArea} instance that is being configured. This field holds a reference to the specific textArea object upon which configuration methods will act, enabling the modification and customization
     * of its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the textArea, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the textArea, including its appearance, behavior, and event handling.</p>
     */
    private TextArea textArea;

    /**
     * Constructs a new instance of {@code TextAreaConfigurator}, associating it with the specified JavaFX {@link TextArea}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link TextArea} object, ensuring that the text area is not null. This validation step is crucial as it guarantees that the configurator has a valid text area
     * to configure, thus preventing null pointer exceptions and other potential issues related to uninitialized text areas. If the passed text area is null, an {@link IllegalArgumentException} is thrown,
     * indicating improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link TextArea} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring
     * the text area is non-null at construction, this design promotes a more robust and error-resistant approach to text area configuration.</p>
     *
     * @param textArea
     *         The {@link TextArea} to be configured by this {@code TextAreaConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code textArea} is null, to enforce the presence of a text area to configure.
     * @see TextArea
     */
    protected TextAreaConfigurator(TextArea textArea) {
        EFXObjectUtils.isNotNull(textArea, () -> "Text Area object cannot be null when using the Text Area Configurator");
        this.textArea = textArea;
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextArea getNode() {
        return textArea;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Text Area object cannot be null when setting the node for the Text Area Configurator");
        this.textArea = checkNodeAndCast(value, TextArea.class, TextAreaConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        TextArea castedValue = checkNodeAndCast(value, TextArea.class, TextAreaConfigurator.class, "nodeEquals");
        return this.textArea.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return textArea.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextAreaConfigurator textAreaConfigurator) {
            return Objects.equals(textAreaConfigurator.getNode(), this.textArea);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("TextAreaConfigurator current text area: %s", textArea.toString());
    }

    //endregion Overridden Methods
}
