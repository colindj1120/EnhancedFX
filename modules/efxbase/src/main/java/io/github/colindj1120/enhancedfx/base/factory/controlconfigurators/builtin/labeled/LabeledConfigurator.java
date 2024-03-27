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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.labeled;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.labeled.base.LabeledConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;

import java.util.Objects;

/**
 * The {@code LabeledConfigurator} class specializes in configuring JavaFX {@link Labeled} components, encompassing {@link Label}, {@link Button}, and other UI controls that contain text.
 *
 * <p>It offers a fluent interface for setting properties such as text content, font, and alignment, facilitating precise control over the appearance and behavior of labeled controls, through the implementation
 * of {@link LabeledConfig} which serves as a way to access all the necessary {@link Labeled} functions including the ones it extends, and its extension of {@link ConfiguratorBase}.</p>
 *
 * <p>By abstracting direct property manipulation into a structured configurator pattern, this class significantly enhances the ease of customizing labeled controls. It integrates seamlessly with JavaFX's
 * property system, allowing for dynamic and responsive UI designs.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Text Customization</b>: Supports dynamic text content changes, font adjustments, and text alignment configurations.</li>
 *     <li><b>Consistency and Validation</b>: Ensures the labeled instance is non-null, preventing null pointer exceptions and fostering consistent UI behavior.</li>
 *     <li><b>Graphic Integration</b>: Enables the association of graphics with text, enhancing the visual appeal and information conveyance of UI controls.</li>
 *     <li><b>Event Handling</b>: Facilitates the binding of event handlers to labeled controls, streamlining interaction design.</li>
 *     <li><b>Factory Method Pattern</b>: Employs a static factory method for configurator instantiation, promoting a clean and intuitive API.</li>
 *     <li><b>Enhanced Encapsulation</b>: Limits direct access to labeled control properties, advocating for a modular and maintainable approach to UI design.</li>
 *     <li><b>Extensible Design</b>: Designed for extensibility, allowing subclasses to introduce additional configuration capabilities.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(Labeled)}: Initializes a {@code LabeledConfigurator} for a specified {@link Labeled}, enabling immediate access to configuration options.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the current configurator instance, ensuring compatibility with the fluent API design.</li>
 *     <li>{@link #getNode()}: Obtains the {@link Labeled} control being configured, allowing for further customization and inspection.</li>
 *     <li>{@link #setNode(Node)}: Updates the configurator's target, enabling dynamic reconfiguration of different labeled controls.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Configuring a Label</h3>
 * <pre>{@code
 * Label myLabel = new Label("Initial Text");
 * LabeledConfigurator.create(myLabel)
 *     .text("Updated Text")
 *     .textFill(Color.BLUE)
 *     .font(Font.font("Verdana", FontWeight.BOLD, 12))
 *     .apply();
 * }</pre>
 *
 * <h3>Configuring a Button</h3>
 * <pre>{@code
 * Button myButton = new Button("Click Me");
 * LabeledConfigurator.create(myButton)
 *     .text("Don't Click Me")
 *     .onAction(e -> System.out.println("Button was clicked!"))
 *     .apply();
 * }</pre>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see LabeledConfig
 * @see Labeled
 * @see Label
 * @see Button
 * @see ConfiguratorBase
 */
public class LabeledConfigurator extends ConfiguratorBase implements LabeledConfig<LabeledConfigurator> {
    /**
     * Creates a new {@code LabeledConfigurator} instance for the specified {@link Labeled}.
     *
     * <p>This factory method facilitates the creation of a configurator tailored to the provided labeled, enabling immediate access to configuration methods. This approach abstracts the instantiation process,
     * allowing for fluent and intuitive setup of labeled type objects through method chaining.</p>
     *
     * @param labeled
     *         The {@link Labeled} to be configured by the newly created {@code LabeledConfigurator}.
     *
     * @return A new instance of {@code LabeledConfigurator} linked to the specified labeled.
     */
    public static LabeledConfigurator create(Labeled labeled) {
        return new LabeledConfigurator(labeled);
    }

    /**
     * The {@link Labeled} instance that is being configured.
     *
     * <p>This field holds a reference to the specific labeled object upon which configuration methods will act, enabling the modification and customization of its properties and behavior.</p>
     *
     * <p>This private member ensures encapsulation of the labeled, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the labeled, including its appearance, behavior, and event handling.</p>
     */
    private Labeled labeled;

    /**
     * Constructs a new instance of {@code LabeledConfigurator}, associating it with the specified JavaFX {@link Labeled}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link Labeled} object, ensuring that the labeled is not null. This validation step is crucial as it guarantees that the configurator has a valid labeled to
     * configure, thus preventing null pointer exceptions and other potential issues related to uninitialized labeled objects. If the passed labeled is null, an {@link IllegalArgumentException} is thrown,
     * indicating improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link Labeled} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring the
     * labeled is non-null at construction, this design promotes a more robust and error-resistant approach to labeled configuration.</p>
     *
     * @param labeled
     *         The {@link Labeled} to be configured by this {@code LabeledConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code labeled} is null, to enforce the presence of a labeled to configure.
     * @see Labeled
     */
    protected LabeledConfigurator(Labeled labeled) {
        EFXObjectUtils.isNotNull(labeled, () -> "Labeled object cannot be null when using the Labeled Configurator");
        this.labeled = labeled;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Labeled getNode() {
        return labeled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Labeled object cannot be null when setting the node for the Labeled Configurator");
        this.labeled = checkNodeAndCast(value, Labeled.class, LabeledConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        Labeled castedValue = checkNodeAndCast(value, Labeled.class, LabeledConfigurator.class, "nodeEquals");
        return this.labeled.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return labeled.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LabeledConfigurator labeledConfigurator) {
            return Objects.equals(labeledConfigurator.getNode(), this.labeled);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("LabeledConfigurator current labeled: %s", labeled.toString());
    }

    //endregion Overridden Functions
}
