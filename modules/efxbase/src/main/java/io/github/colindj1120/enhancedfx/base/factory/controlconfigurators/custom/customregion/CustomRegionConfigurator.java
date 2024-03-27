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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customregion;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customregion.base.CustomRegionConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.util.Objects;

/**
 * The {@code CustomRegionConfigurator} class provides a fluent and type-safe mechanism for configuring {@link Region} components within JavaFX applications. As an extension of {@link ConfiguratorBase} and an
 * implementation of {@link CustomRegionConfig}, this class enables developers to precisely adjust the properties, styling, and overall behavior of Region-based components, which are foundational to creating
 * structured and visually appealing user interfaces.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Offers a fluent API for the seamless configuration of Region properties, fostering code readability and ease of maintenance.</li>
 *   <li>Ensures type safety through generic parameterization, reducing the risk of type mismatches and runtime errors.</li>
 *   <li>Builds upon the robust capabilities of {@link ConfiguratorBase}, facilitating the implementation of complex configurations.</li>
 *   <li>Supports the customization of a wide variety of JavaFX Region subclasses, enabling flexible UI design and implementation.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomStackPane myStackPane = new CustomStackPane();
 * CustomRegionConfigurator<CustomStackPane> configurator = CustomRegionConfigurator.create(myStackPane);
 *
 * configurator
 *     .padding(new Insets(10))
 *     .style("-fx-background-color: #336699;");
 * }
 * </pre>
 *
 * <p>This example showcases the use of {@code CustomRegionConfigurator} to configure a {@code CustomStackPane} with specific padding and background color. It demonstrates the configurator's utility in
 * fine-tuning the appearance and layout of Custom Region components, thereby enhancing the visual and functional aspects of JavaFX applications.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomRegionConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the developer's
 * toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code Region} being configured, promoting type-safe and precise customizations.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomRegionConfig
 * @see ConfiguratorBase
 * @see Region
 */
public class CustomRegionConfigurator<T extends Region> extends ConfiguratorBase implements CustomRegionConfig<CustomRegionConfigurator<T>> {
    /**
     * Creates a new {@code CustomRegionConfigurator} instance for the specified {@link Region}. This method infers the type of the region to configure and returns a type-specific configurator. Note that this
     * method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link Region}.
     *
     * @param customRegion
     *         The {@link Region} to be configured by the newly created {@code CustomRegionConfigurator}.
     * @param <T>
     *         The type of the custom region.
     *
     * @return A new instance of {@code CustomRegionConfigurator} linked to the specified region.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends Region> CustomRegionConfigurator<T> create(T customRegion) {
        Class<T> type = (Class<T>) customRegion.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomRegionConfigurator<>(customRegion, type);
    }

    /**
     * The type parameter of the custom region being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom region. Storing the class of {@code T} enables
     * type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended region type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom region being configured. This field holds a reference to the specific region object upon which the configuration methods will act. It enables the modification and customization
     * of the region's properties and behavior through a fluent API.
     */
    private T customRegion;

    /**
     * Constructs a {@code CustomRegionConfigurator} for a specific {@link Region} type. This protected constructor initializes the configurator with a custom Region instance and its class type. It ensures that
     * the custom Region is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for customized configurations
     * tailored to the specific Region subclass.
     *
     * @param customRegion
     *         The custom Region instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom Region. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customRegion} is null, ensuring that a valid Region instance is provided for configuration.
     */
    protected CustomRegionConfigurator(T customRegion, Class<T> type) {
        EFXObjectUtils.isNotNull(customRegion, () -> "Custom Region object cannot be null when using the Custom Region Configurator");
        this.customRegion = customRegion;
        this.type = type; // Initialize the class field
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomRegionConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Region getNode() {
        return customRegion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Region object cannot be null when setting the node for the Custom Region Configurator");
        this.customRegion = checkNodeAndCast(value, type, CustomRegionConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomRegionConfigurator.class, "nodeEquals");
        return this.customRegion.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customRegion.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomRegionConfigurator<?> customRegionConfigurator) {
            return Objects.equals(customRegionConfigurator.getNode(), this.customRegion);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomRegionConfigurator current custom region: %s", customRegion.toString());
    }

    //endregion Overridden Methods
}
