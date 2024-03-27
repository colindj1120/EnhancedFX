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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.label.base.LabelConfig;
import io.github.colindj1120.enhancedfx.utils.EFXExceptionUtils;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.Objects;

/**
 * The {@code LabelConfigurator} class is designed to provide a fluent and intuitive way to configure JavaFX {@link Label} components within the EnhancedFX framework, significantly simplifying UI design and
 * customization through the implementation of {@link LabelConfig} which serves as a way to access all the necessary {@link Label} functions including the ones it extends, and its extension of
 * {@link ConfiguratorBase}.
 *
 * <p>By encapsulating the complexity of direct property manipulation, this class offers a centralized mechanism for modifying the appearance, behavior, and event handling of labels. It adheres to JavaFX's
 * property and binding system, enabling developers to apply custom configurations and styles efficiently.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Fluent API Design</b>: Facilitates method chaining for concise, readable label configuration, enhancing code clarity and maintainability.</li>
 *     <li><b>Validation and Stability</b>: Ensures the label instance is non-null at construction, providing early failure notifications and preventing runtime exceptions.</li>
 *     <li><b>Comprehensive Customization</b>: Offers extensive methods for customizing label properties such as text, font, color, and alignment, catering to diverse UI requirements.</li>
 *     <li><b>Factory Method Pattern</b>: Utilizes a static factory method to instantiate {@code LabelConfigurator}, promoting consistency and easing the configurator's usage.</li>
 *     <li><b>Encapsulation and Safety</b>: Encourages the use of configurator methods for modifications, limiting direct access to the label's properties and fostering a safer customization approach.</li>
 *     <li><b>JavaFX Integration</b>: Seamlessly integrates with the JavaFX framework, ensuring that label configurations comply with JavaFX conventions and best practices.</li>
 *     <li><b>Maintainability and Scalability</b>: By isolating label configuration logic, it aids in creating a more organized and scalable application structure.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(Label)}: Initializes a {@code LabelConfigurator} for a specified {@link Label}, streamlining the configuration process through method chaining.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the configurator instance, aligning with the fluent API design principle.</li>
 *     <li>{@link #getNode()}: Obtains the current {@link Label} being configured, allowing for introspection and further customization.</li>
 *     <li>{@link #setNode(Node)}: Dynamically updates the configurator's target, facilitating runtime changes to the configured label.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * Label myLabel = new Label("Initial Text");
 * LabelConfigurator.create(myLabel)
 *     .text("Updated Text")
 *     .textFill(Color.RED)
 *     .font(new Font("Arial", 16));
 * }</pre>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see LabelConfig
 * @see Label
 * @see Node
 * @see ConfiguratorBase
 */
public class LabelConfigurator extends ConfiguratorBase implements LabelConfig<LabelConfigurator> {
    /**
     * Creates a new {@code LabelConfigurator} instance for the specified {@link Label}.
     *
     * <p>This factory method facilitates the creation of a configurator tailored to the provided label, enabling immediate access to configuration methods.</p>
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of labels through method chaining.</p>
     *
     * @param label
     *         The {@link Label} to be configured by the newly created {@code LabelConfigurator}.
     *
     * @return A new instance of {@code LabelConfigurator} linked to the specified label.
     */
    public static LabelConfigurator create(Label label) {
        return new LabelConfigurator(label);
    }

    /**
     * The {@link Label} instance that is being configured.
     *
     * <p>This field holds a reference to the specific label object upon which configuration methods will act, enabling the modification and customization of its properties and behavior.</p>
     *
     * <p>This private member ensures encapsulation of the label, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable approach
     * to UI customization. The configurator provides a fluent API for configuring various aspects of the label, including its appearance, behavior, and event handling.</p>
     */
    private Label label;

    /**
     * Constructs a new instance of {@code LabelConfigurator}, associating it with the specified JavaFX {@link Label}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link Label} object, ensuring that the label is not null. This validation step is crucial as it guarantees that the configurator has a valid label to
     * configure, thus preventing null pointer exceptions and other potential issues related to uninitialized labels. If the passed label is null, an {@link IllegalArgumentException} is thrown, indicating
     * improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link Label} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring the
     * label is non-null at construction, this design promotes a more robust and error-resistant approach to label configuration.</p>
     *
     * @param label
     *         The {@link Label} to be configured by this {@code LabelConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code label} is null, to enforce the presence of a label to configure.
     * @see Label
     */
    protected LabelConfigurator(Label label) {
        EFXObjectUtils.isNotNull(label, () -> "Label object cannot be null when using the Label Configurator");
        this.label = label;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Label getNode() {
        return this.label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Label object cannot be null when setting the node for the Label Configurator");

        if (value instanceof Label o) {
            this.label = o;
        } else {
            throw EFXExceptionUtils.getInvalidInstanceOfException(value, Label.class, LabelConfigurator.class, "setNode");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        Label castedValue = checkNodeAndCast(value, Label.class, LabelConfigurator.class, "nodeEquals");
        return this.label.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return label.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LabelConfigurator labelConfigurator) {
            return Objects.equals(labelConfigurator.getNode(), this.label);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("LabelConfigurator current label: %s", label.toString());
    }

    //endregion Overridden Functions
}
