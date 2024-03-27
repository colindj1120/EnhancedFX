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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.button.base.ButtonConfig;
import javafx.scene.Node;

/**
 * The {@code BaseConfig} interface defines the foundational structure for a configurator pattern implementation tailored for JavaFX UI component and control configuration. It serves as a generic contract for
 * configurators, enabling fluent and intuitive setup of UI elements within JavaFX applications.
 *
 * <p>By leveraging this interface, developers can create custom configurator classes that facilitate a streamlined and efficient approach to configuring JavaFX {@link Node} properties, event handlers, styles,
 * and more. The interface promotes a cohesive and readable code style by allowing for the chaining of configuration methods, thus avoiding the repetitive and verbose code typically associated with UI
 * setup.</p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li><b>Fluent Configuration:</b> Supports the chaining of method calls to configure UI components in a clear and concise manner.</li>
 *   <li><b>Direct Access to Target Node:</b> Provides access to the JavaFX {@link Node} being configured, enabling direct manipulation and setup of its properties and behaviors.</li>
 *   <li><b>Extensibility:</b> Allows for the creation of specialized configurators for different types of UI components by extending the base configurator functionality.</li>
 * </ul>
 *
 * <p>Implementers of this interface are encouraged to provide additional configuration methods specific to the UI component they target, ensuring that the configurator is versatile and capable of handling
 * various setup requirements. Proper implementation should focus on maintaining the integrity and applicability of configurations to the intended UI components, enhancing the user interface's usability and
 * aesthetics.</p>
 *
 * @param <T>
 *         The type parameter extending {@link ConfiguratorBase}, which represents the specific configurator implementation used for fluent method chaining
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ConfiguratorBase
 */
public interface BaseConfig<T extends ConfiguratorBase> {
    /**
     * Retrieves the current instance of the configurator.
     *
     * <p>
     * This method is instrumental in implementing the configurator or builder pattern within the application, primarily used for configuring components, controls, or other entities in a fluent and intuitive
     * manner. It returns the current instance of the configurator, allowing for the chaining of configuration methods to define the setup in a concise and readable format.
     * </p>
     *
     * <p>
     * The returned configurator instance provides access to a suite of configuration methods tailored to the specific needs of the component or control being configured. This design pattern enhances code
     * readability and maintainability by consolidating configuration steps into a single, fluent sequence of method calls.
     * </p>
     *
     * <p>
     * Employing the configurator pattern with this method is particularly beneficial in complex UI setups, initialization logic, or any scenario where multiple configuration steps are required. It simplifies
     * the codebase by eliminating the need for repetitive accessors or boilerplate code, focusing instead on a clear and logical sequence of configuration actions.
     * </p>
     *
     * <p>
     * Implementers of this method should ensure that the configurator instance returned is properly initialized and ready to perform the necessary configuration tasks. The method's usability extends across
     * various contexts, including but not limited to UI component setup, application configuration, and dynamic content generation.
     * </p>
     *
     * @return @return the configurator instance of type {@code T}, enabling further configuration through method chaining within the application.
     */
    T getConfigurator();

    /**
     * Retrieves the JavaFX {@link Node} that is the target of this configurator.
     *
     * <p>
     * This method is central to the configuration process in JavaFX applications, providing direct access to the UI component or control that is currently being configured. The returned {@link Node} serves as
     * the foundation for various configuration operations, such as setting properties, applying styles, attaching listeners, and more. It enables the configurator to apply configurations directly to the
     * correct UI element, ensuring that all modifications are relevant and correctly applied.
     * </p>
     *
     * <p>
     * The {@code getNode} method supports a wide range of use cases in UI development, from simple property adjustments to complex behavior and layout configurations. By offering a direct link to the
     * underlying UI component, it facilitates precise control over the component's appearance and functionality, allowing developers to tailor UI elements to specific requirements.
     * </p>
     *
     * <p>
     * Usage of this method is typically reserved for internal operations within the configurator pattern, acting as a bridge between the abstract configuration logic and the concrete UI elements. However, it
     * can also be used in client code to gain access to the configured Node for additional customizations or for querying current configurations.
     * </p>
     *
     * <p>
     * It is important for implementers of this method to ensure that the correct {@link Node} instance is returned, i.e. override this method and change the return type such as Button getNode() for
     * {@link ButtonConfig} it directly impacts the effectiveness and accuracy of the configuration process. Incorrectly associated Nodes could lead to misconfigurations or runtime errors.
     * </p>
     *
     * @return The current {@link Node} associated with the current configurator instance
     */
    Node getNode();
}
