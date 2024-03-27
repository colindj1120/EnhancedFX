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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textfield;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textfield.base.TextFieldConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.Objects;

/**
 * The {@code TextFieldConfigurator} class is a specialized tool in the EnhancedFX framework, designed to provide a fluent and intuitive way to configure JavaFX {@link TextField} components.
 *
 * <p>This configurator empowers developers to easily customize text fields, facilitating the creation of interactive and user-friendly text input areas in their applications, through through the
 * implementation of {@link TextFieldConfig} which serves as a way to access all the necessary {@link TextField} functions including the ones it extends, and its extension of {@link ConfiguratorBase}.</p>
 *
 * <p>It abstracts away the complexity of manipulating text field properties directly, promoting cleaner and more maintainable code. With this configurator, setting properties such as text content, prompt
 * text, and text field dimensions becomes straightforward, enhancing the development workflow.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Text and Placeholder Management</b>: Simplifies the process of setting and updating the text and placeholder text within the text field.</li>
 *     <li><b>Appearance Customization</b>: Provides methods for modifying the text field's visual attributes, including font size, text color, and background.</li>
 *     <li><b>Input Validation and Control</b>: Facilitates the implementation of input restrictions and validation logic to ensure data integrity.</li>
 *     <li><b>Event Handling</b>: Streamlines the attachment of event listeners for handling user interactions and input events.</li>
 *     <li><b>Factory Method Pattern</b>: Utilizes a static factory method for easy instantiation, promoting a streamlined start to text field customization.</li>
 *     <li><b>Direct Property Access Encapsulation</b>: Encourages the use of configurator methods for adjustments, enhancing code safety and structure.</li>
 *     <li><b>JavaFX Integration</b>: Designed to complement JavaFX's architecture, ensuring configurations are effective and aligned with best practices.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(TextField)}: Initializes a {@code TextFieldConfigurator} for a specific {@link TextField}, ready for customization.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the configurator instance, supporting the fluent interface pattern for method chaining.</li>
 *     <li>{@link #getNode()}: Obtains the {@link TextField} being configured, allowing for further customization and property inspection.</li>
 *     <li>{@link #setNode(Node)}: Dynamically reassigns the target text field, facilitating the configuration of various text field instances.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Configuring a TextField for Usernames</h3>
 * <pre>{@code
 * TextField usernameField = new TextField();
 * TextFieldConfigurator.create(usernameField)
 *     .promptText("Username")
 *     .prefColumnCount(20)
 *     .apply();
 * }</pre>
 *
 * <h3>Applying CSS Styles to a TextField</h3>
 * <pre>{@code
 * TextFieldConfigurator.create(usernameField)
 *     .style("-fx-border-color: blue; -fx-border-width: 2px;")
 *     .apply();
 * }</pre>
 *
 * <p>This configurator significantly simplifies the task of setting up text fields in JavaFX applications, providing developers with a powerful tool to enhance user input mechanisms. By streamlining the
 * configuration process, it enables the creation of more engaging and intuitive user interfaces.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextFieldConfig
 * @see TextField
 * @see Node
 * @see ConfiguratorBase
 */
public class TextFieldConfigurator extends ConfiguratorBase implements TextFieldConfig<TextFieldConfigurator> {
    /**
     * Creates a new {@code TextFieldConfigurator} instance for the specified {@link TextField}. This factory method facilitates the creation of a configurator tailored to the provided text field, enabling
     * immediate access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of text fields through method chaining.</p>
     *
     * @param textField
     *         The {@link TextField} to be configured by the newly created {@code TextFieldConfigurator}.
     *
     * @return A new instance of {@code TextFieldConfigurator} linked to the specified text field.
     */
    public static TextFieldConfigurator create(TextField textField) {
        return new TextFieldConfigurator(textField);
    }

    /**
     * The {@link TextField} instance that is being configured. This field holds a reference to the specific textField object upon which configuration methods will act, enabling the modification and
     * customization of its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the textField, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the textField, including its appearance, behavior, and event handling.</p>
     */
    private TextField textField;

    /**
     * Constructs a new instance of {@code TextFieldConfigurator}, associating it with the specified JavaFX {@link TextField}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link TextField} object, ensuring that the text field is not null. This validation step is crucial as it guarantees that the configurator has a valid text
     * field to configure, thus preventing null pointer exceptions and other potential issues related to uninitialized text fields. If the passed text field is null, an {@link IllegalArgumentException} is
     * thrown, indicating improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link TextField} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring
     * the text field is non-null at construction, this design promotes a more robust and error-resistant approach to text field configuration.</p>
     *
     * @param textField
     *         The {@link TextField} to be configured by this {@code TextFieldConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code textField} is null, to enforce the presence of a text field to configure.
     * @see TextField
     */
    protected TextFieldConfigurator(TextField textField) {
        EFXObjectUtils.isNotNull(textField, () -> "Text Field object cannot be null when using the Text Field Configurator");
        this.textField = textField;
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField getNode() {
        return textField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Text Field object cannot be null when setting the node for the Text Field Configurator");
        this.textField = checkNodeAndCast(value, TextField.class, TextFieldConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        TextField castedValue = checkNodeAndCast(value, TextField.class, TextFieldConfigurator.class, "nodeEquals");
        return this.textField.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return textField.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextFieldConfigurator textFieldConfigurator) {
            return Objects.equals(textFieldConfigurator.getNode(), this.textField);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("TextFieldConfigurator current text field: %s", textField.toString());
    }

    //endregion Overridden Methods
}
