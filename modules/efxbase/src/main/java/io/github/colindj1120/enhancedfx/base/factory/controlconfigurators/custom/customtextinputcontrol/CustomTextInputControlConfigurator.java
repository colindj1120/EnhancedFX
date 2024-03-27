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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextinputcontrol;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customtextinputcontrol.base.CustomTextInputControlConfig;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;

import java.util.Objects;

/**
 * The {@code CustomTextInputControlConfigurator} class provides a fluent and type-safe approach to customizing {@link TextInputControl} components in JavaFX applications. Extending {@link ConfiguratorBase} and
 * implementing {@link CustomTextInputControlConfig}, it enables developers to fine-tune the properties, styling, and functionality of TextInputControl elements, which are essential for creating interactive and
 * user-friendly text-based user interfaces.
 *
 * <h2>Key Features</h2>
 * <ul>
 *   <li>Facilitates fluent configuration of TextInputControl properties, enhancing code readability and maintainability.</li>
 *   <li>Ensures type safety through generic type parameterization, reducing the risk of runtime errors.</li>
 *   <li>Leverages the foundational capabilities of {@link ConfiguratorBase} for applying complex and nuanced configurations.</li>
 *   <li>Supports a broad range of customizations for TextInputControl components, enabling tailored text input solutions.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * {@code
 * CustomTextField myTextField = new CustomTextField();
 * CustomTextInputControlConfigurator<CustomTextField> configurator = CustomTextInputControlConfigurator.create(myTextField);
 *
 * configurator
 *     .promptText("Enter your name...")
 *     .prefColumnCount(20)
 *     .style("-fx-text-box-border: blue; -fx-focus-color: blue;");
 * }
 * </pre>
 *
 * <p>In this example, {@code CustomTextInputControlConfigurator} is utilized to set a prompt text, preferred column count, and focus color for a CustomTextField, showcasing the configurator's capability to
 * easily customize Custom TextInputControl attributes for improved UX design and functionality.</p>
 *
 * <p>Additionally, the configurator inherits from {@code BaseCustomConfig}, which provides a robust set of default methods for binding properties, managing listeners, initializing custom EFX bindings, and
 * directly setting property values. This inclusion grants {@code CustomTextInputControlConfigurator} access to a rich set of functionalities for detailed and dynamic customization, further enriching the
 * developer's toolkit for creating customized, responsive JavaFX UI components.</p>
 *
 * @param <T>
 *         The specific type of {@code TextInputControl} being configured, ensuring type-safe customization.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see CustomTextInputControlConfig
 * @see ConfiguratorBase
 * @see TextInputControl
 */
public class CustomTextInputControlConfigurator<T extends TextInputControl> extends ConfiguratorBase implements CustomTextInputControlConfig<CustomTextInputControlConfigurator<T>> {
    /**
     * Creates a new {@code CustomTextInputControlConfigurator} instance for the specified {@link TextInputControl}. This method infers the type of the text input control to configure and returns a
     * type-specific configurator. Note that this method performs an unchecked cast internally. Users of this API should ensure that they are passing the correct type of {@link TextInputControl}.
     *
     * @param customTextInputControl
     *         The {@link TextInputControl} to be configured by the newly created {@code CustomTextInputControlConfigurator}.
     * @param <T>
     *         The type of the custom text input control.
     *
     * @return A new instance of {@code CustomTextInputControlConfigurator} linked to the specified text input control.
     */
    @SuppressWarnings("unchecked") // Suppress unchecked cast warning
    public static <T extends TextInputControl> CustomTextInputControlConfigurator<T> create(T customTextInputControl) {
        Class<T> type = (Class<T>) customTextInputControl.getClass(); // This cast is logically safe but unchecked at compile time.
        return new CustomTextInputControlConfigurator<>(customTextInputControl, type);
    }

    /**
     * The type parameter of the custom text input control being configured. This input control holds the {@link Class} object representing the specific type {@code T} of the custom text input control. Storing
     * the class of {@code T} enables type-safe operations such as casting and instance checks, ensuring the configurator works correctly with the intended text input control type.
     */
    private final Class<T> type;

    /**
     * The instance of the custom text input control being configured. This input control holds a reference to the specific text input control object upon which the configuration methods will act. It enables
     * the modification and customization of the text input control's properties and behavior through a fluent API.
     */
    private T customTextInputControl;

    /**
     * Constructs a {@code CustomTextInputControlConfigurator} for a specific {@link TextInputControl} type. This protected constructor initializes the configurator with a custom TextInputControl instance and
     * its class type. It ensures that the custom TextInputControl is not null, throwing an exception if the validation fails. This approach enforces type safety and consistency in the configurator's
     * operations, allowing for customized configurations tailored to the specific TextInputControl subclass.
     *
     * @param customTextInputControl
     *         The custom TextInputControl instance to be configured. Must not be null.
     * @param type
     *         The {@link Class} object representing the type of the custom TextInputControl. Used for type-safe operations and validations.
     *
     * @throws IllegalArgumentException
     *         if {@code customTextInputControl} is null, ensuring that a valid TextInputControl instance is provided for configuration.
     */
    protected CustomTextInputControlConfigurator(T customTextInputControl, Class<T> type) {
        EFXObjectUtils.isNotNull(customTextInputControl, () -> "Custom Text Input Control object cannot be null when using the Custom Text Input Control Configurator");
        this.customTextInputControl = customTextInputControl;
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
    public CustomTextInputControlConfigurator<T> getConfigurator() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControl getNode() {
        return customTextInputControl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        EFXObjectUtils.isNotNull(value, () -> "Custom Text Input Control object cannot be null when setting the text input control for the Custom Text Input Control Configurator");
        this.customTextInputControl = checkNodeAndCast(value, type, CustomTextInputControlConfigurator.class, "setNode");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean nodeEquals(Node value) {
        T castedValue = checkNodeAndCast(value, type, CustomTextInputControlConfigurator.class, "nodeEquals");
        return this.customTextInputControl.equals(castedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return customTextInputControl.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomTextInputControlConfigurator<?> customTextInputControlConfigurator) {
            return Objects.equals(customTextInputControlConfigurator.getNode(), this.customTextInputControl);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("CustomTextInputControlConfigurator current custom text input control: %s", customTextInputControl.toString());
    }

    //endregion Overridden Methods
}
