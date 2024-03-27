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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.region;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.region.base.RegionConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.util.Objects;

/**
 * The {@code RegionConfigurator} class is a core component of the EnhancedFX framework, designed to facilitate the fluent configuration of JavaFX {@link Region} objects. By providing a robust and intuitive API
 * for modifying region properties, this configurator enhances the efficiency and readability of UI setup code.
 *
 * <p>This class abstracts away the complexity associated with direct manipulation of region properties, offering a centralized mechanism for customizing layout constraints, padding, background, and other
 * styling aspects. It is built to integrate seamlessly with JavaFX's property and binding systems, enabling developers to craft dynamic and responsive UIs with ease, through the implementation of
 * {@link RegionConfig} which serves as a way to access all the necessary {@link Region} functions including the ones it extends and its extension of {@link ConfiguratorBase}.</p>
 *
 * <h2>Capabilities List</h2>
 * <ul>
 *     <li><b>Layout Customization</b>: Facilitates the adjustment of layout-related properties, such as padding and margin, to achieve the desired UI layout.</li>
 *     <li><b>Background and Styling</b>: Enables the configuration of backgrounds, borders, and other stylistic elements, aligning the region's appearance with the application theme.</li>
 *     <li><b>Size and Resizing Policies</b>: Offers methods to set preferred, minimum, and maximum sizes, as well as resizing behavior, ensuring that the region behaves as expected within different screen
 *     sizes and resolutions.</li>
 *     <li><b>Event Handling Setup</b>: Simplifies the attachment of event handlers to regions, promoting interactive and dynamic UI designs.</li>
 *     <li><b>Factory Method Pattern</b>: Utilizes a static factory method to instantiate the configurator, promoting a consistent and simplified approach to region configuration.</li>
 *     <li><b>Encapsulation and Direct Access Prevention</b>: Encourages the use of configurator methods over direct property access, supporting safer and more maintainable code practices.</li>
 *     <li><b>Seamless JavaFX Integration</b>: Designed to complement and enhance JavaFX's existing architecture, ensuring configurations are both efficient and effective.</li>
 * </ul>
 *
 * <h2>Key Methods</h2>
 * <ul>
 *     <li>{@link #create(Region)}: Prepares a {@code RegionConfigurator} for a specified {@link Region}, readying it for property customization.</li>
 *     <li>{@link #getConfigurator()}: Retrieves the configurator instance, adhering to the fluent interface design for continuous configuration chaining.</li>
 *     <li>{@link #getNode()}: Acquires the {@link Region} being configured, allowing for further inspection and customization.</li>
 *     <li>{@link #setNode(Node)}: Enables dynamic reassignment of the target region, facilitating the reconfiguration of different region instances as required.</li>
 * </ul>
 *
 * <h2>Usage Examples</h2>
 * <h3>Configuring a Pane with Custom Background</h3>
 * <pre>{@code
 * Pane pane = new Pane();
 * RegionConfigurator.create(pane)
 *     .background(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)))
 *     .prefSize(200, 100)
 *     .apply();
 * }</pre>
 *
 * <h3>Adjusting Padding and Margins</h3>
 * <pre>{@code
 * VBox vbox = new VBox();
 * RegionConfigurator.create(vbox)
 *     .padding(new Insets(10))
 *     .apply();
 * }</pre>
 *
 * <p>This configurator is indispensable for developing sophisticated JavaFX applications, offering an elegant and powerful approach to UI customization. It abstracts complex configurations into a
 * streamlined, fluent interface, allowing developers to focus on creating engaging and aesthetically pleasing user interfaces.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see RegionConfig
 * @see Region
 * @see Node
 * @see ConfiguratorBase
 */
public class RegionConfigurator extends ConfiguratorBase implements RegionConfig<RegionConfigurator> {
    /**
     * Creates a new {@code RegionConfigurator} instance for the specified {@link Region}. This factory method facilitates the creation of a configurator tailored to the provided region, enabling immediate
     * access to configuration methods.
     *
     * <p>This approach abstracts the instantiation process, allowing for fluent and intuitive setup of regions through method chaining.</p>
     *
     * @param region
     *         The {@link Region} to be configured by the newly created {@code RegionConfigurator}.
     *
     * @return A new instance of {@code RegionConfigurator} linked to the specified region.
     */
    public static RegionConfigurator create(Region region) {
        return new RegionConfigurator(region);
    }

    /**
     * The {@link Region} instance that is being configured. This field holds a reference to the specific region object upon which configuration methods will act, enabling the modification and customization of
     * its properties and behavior.
     *
     * <p>This private member ensures encapsulation of the region, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable
     * approach to UI customization. The configurator provides a fluent API for configuring various aspects of the region, including its appearance, behavior, and event handling.</p>
     */
    private Region region;

    /**
     * Constructs a new instance of {@code RegionConfigurator}, associating it with the specified JavaFX {@link Region}.
     *
     * <p>This constructor is protected, intended for use within the package or by subclasses that aim to extend the configurator's functionality.</p>
     *
     * <p>It initializes the configurator with a {@link Region} object, ensuring that the region is not null. This validation step is crucial as it guarantees that the configurator has a valid region to
     * configure, thus preventing null pointer exceptions and other potential issues related to uninitialized regions. If the passed region is null, an {@link IllegalArgumentException} is thrown, indicating
     * improper usage.</p>
     *
     * <p>This configurator is designed to provide a fluent API for configuring {@link Region} properties, enhancing readability and ease of use when setting up UI components programmatically. By ensuring the
     * region is non-null at construction, this design promotes a more robust and error-resistant approach to region configuration.</p>
     *
     * @param region
     *         The {@link Region} to be configured by this {@code RegionConfigurator}. Must not be null.
     *
     * @throws IllegalArgumentException
     *         if {@code region} is null, to enforce the presence of a region to configure.
     * @see Region
     */
    protected RegionConfigurator(Region region) {
        EFXObjectUtils.isNotNull(region, () -> "Region object cannot be null when using the Region Configurator");
        this.region = region;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionConfigurator getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Region getNode() {
        return this.region;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Region object cannot be null when setting the node for the Region Configurator");
        this.region = checkNodeAndCast(value, Region.class, RegionConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        Region castedValue = checkNodeAndCast(value, Region.class, RegionConfigurator.class, "nodeEquals");
        return this.region.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return region.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RegionConfigurator regionConfigurator) {
            return Objects.equals(regionConfigurator.getNode(), this.region);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("RegionConfigurator current region: %s", region.toString());
    }

    //endregion Overridden Functions
}
