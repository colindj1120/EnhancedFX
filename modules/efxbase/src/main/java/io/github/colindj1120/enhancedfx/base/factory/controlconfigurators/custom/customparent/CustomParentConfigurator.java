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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customparent;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customparent.base.CustomParentConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Objects;

/**
 * The {@code CustomParentConfigurator} class facilitates the fluent and type-safe configuration of {@link Parent} components in JavaFX applications. By extending {@link ConfiguratorBase} and implementing
 * {@link CustomParentConfig}, it equips developers with a powerful toolkit for tailoring the properties, behavior, and styling of Parent components, which is essential for constructing dynamic and complex user
 * interfaces.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Empowers developers to configure Parent components through a fluent API, enhancing code clarity and maintainability.</li>
 *   <li>Ensures type safety via generic type parameterization, significantly reducing the likelihood of runtime errors.</li>
 *   <li>Leverages the solid foundation provided by {@link ConfiguratorBase} for applying intricate configurations effortlessly.</li>
 *   <li>Accommodates customization for a broad array of JavaFX Parent types, supporting diverse UI design requirements.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomVBox myVBox = new CustomVBox();
 * CustomParentConfigurator<CustomVBox> configurator = CustomParentConfigurator.create(myVBox);
 *
 * configurator
 *     .spacing(10)
 *     .alignment(Pos.CENTER);
 * }
 * </pre>
 *
 * <p>This example demonstrates the utilization of {@code CustomParentConfigurator} to configure a {@code CustomVBox} with specific spacing and alignment properties. It exemplifies how the configurator
 * simplifies the customization of Custom Parent components, enabling developers to create more engaging and user-friendly interfaces.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomParentConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the developer's
 * toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code Parent} being configured, facilitating type-safe customizations.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomParentConfig
 * @see ConfiguratorBase
 * @see Parent
 */
public class CustomParentConfigurator<T extends Parent> extends ConfiguratorBase implements CustomParentConfig<CustomParentConfigurator<T>> {
    /**
     * Creates a new {@code CustomParentConfigurator} instance for the specified {@link Parent}. This method infers the type of the parent to configure and returns a type-specific configurator. Note that this
     * method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link Parent}.
     *
     * @param customParent
     *         The {@link Parent} to be configured by the newly created {@code CustomParentConfigurator}.
     * @param <T>
     *         The type of the custom parent.
     *
     * @return A new instance of {@code CustomParentConfigurator} linked to the specified parent.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends Parent> CustomParentConfigurator<T> create(T customParent) {
        Class<T> type = (Class<T>) customParent.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomParentConfigurator<>(customParent, type);
    }

    /**
     * The type parameter of the custom parent being configured. This field holds the {@link Class} object representing the specific type {@code T} of the custom parent. Storing the class of {@code T} enables
     * type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended parent type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom parent being configured. This field holds a reference to the specific parent object upon which the configuration methods will act. It enables the modification and customization
     * of the parent's properties and behavior through a fluent API.
     */
    private T customParent;

    /**
     * Constructs a {@code CustomParentConfigurator} for a specific {@link Parent} type. This protected constructor initializes the configurator with a custom Parent instance and its class type. It ensures that
     * the custom Parent is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's operations, allowing for customized configurations
     * tailored to the specific Parent subclass.
     *
     * @param customParent
     *         The custom Parent instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom Parent. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customParent} is null, ensuring that a valid Parent instance is provided for configuration.
     */
    protected CustomParentConfigurator(T customParent, Class<T> type) {
        EFXObjectUtils.isNotNull(customParent, () -> "Custom Parent object cannot be null when using the Custom Parent Configurator");
        this.customParent = customParent;
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
    public CustomParentConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parent getNode() {
        return customParent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Parent object cannot be null when setting the parent for the Custom Parent Configurator");
        this.customParent = checkNodeAndCast(value, type, CustomParentConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomParentConfigurator.class, "nodeEquals");
        return this.customParent.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customParent.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomParentConfigurator<?> customParentConfigurator) {
            return Objects.equals(customParentConfigurator.getNode(), this.customParent);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomParentConfigurator current custom parent: %s", customParent.toString());
    }

    //endregion Overridden Methods
}
