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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol.base;

import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import javafx.beans.Observable;
import javafx.scene.control.Control;

import java.lang.reflect.Constructor;

/**
 * The {@code EFXControlBase} class serves as the foundational base class for all custom controls in the EnhancedFX framework, encapsulating essential functionalities such as theme management, initialization
 * routines, and common control properties.
 *
 * <p>This abstraction allows for a consistent approach to control development within the EnhancedFX ecosystem.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><strong>Theme Management:</strong> Supports dynamic theme changes, enabling controls to adapt their styles according to the selected {@link EFXTheme}.</li>
 *     <li><strong>Factory Creation:</strong> Utilizes a factory method pattern to instantiate control objects, ensuring proper initialization and configuration.</li>
 *     <li><strong>Initialization Routine:</strong> Provides a structured initialization sequence through the {@code initialize} method, which is automatically invoked post-instantiation to set up styleable
 *     properties, configure control specifics, and update pseudo-class states.</li>
 *     <li><strong>Customization Hooks:</strong> Abstract methods such as {@code setupControl}, {@code setupStyleableProperties}, and {@code updatePseudoClassStates} offer hooks for customization in
 *     subclasses, allowing for precise control over the appearance and behavior of controls.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <p>Below is a hypothetical example demonstrating how to implement a new custom control {@code EnhancedButton} that extends {@code EFXControlBase}:</p>
 * <pre>
 * {@code
 * public class EnhancedButton extends EFXControlBase<Button> {
 *     private Button button;
 *
 *     public EnhancedButton() {
 *         super();
 *     }
 *
 *     @Override
 *     protected void initialize() {
 *         super.initialize();
 *         this.button = new Button("Click Me");
 *         getChildren().add(button);
 *     }
 *
 *     @Override
 *     public Button getInnerControl() {
 *         return button;
 *     }
 *
 *     @Override
 *     protected EFXControlBase<?> getControl() {
 *         return this;
 *     }
 *
 *     @Override
 *     protected void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
 *         // Implement theme change logic here
 *     }
 *
 *     @Override
 *     protected void setupControl() {
 *         // Setup control specifics
 *     }
 *
 *     @Override
 *     protected void setupStyleableProperties() {
 *         // Setup styleable properties
 *     }
 *
 *     @Override
 *     protected void updatePseudoClassStates() {
 *         // Update pseudoclass states
 *     }
 * }
 * }
 * </pre>
 *
 * <p>This example showcases the typical structure of a custom control extending {@code EFXControlBase}. Implementers are required to define the control-specific behaviors, appearance adjustments, and handle
 * theme changes as necessary to fit their requirements.</p>
 *
 * <p>Note: The actual integration and usage of custom controls within the EnhancedFX framework or a JavaFX application may involve additional steps, including CSS styling, event handling, and integration
 * with other UI components or services.</p>
 *
 * @param <T>
 *         the type of the inner control that this {@code EFXControlBase} class wraps or manages.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 * @see EFXTheme
 */
public abstract class EFXControlBase<T extends Control> extends Control {
    /**
     * Default constructor for EFXControlBase.
     *
     * <p>Initializes a new instance of an EnhancedFX control. This constructor is protected to ensure that instances of EFXControlBase or its subclasses can only be created through the class's factory method
     * or by subclasses.</p>
     */
    protected EFXControlBase() {}

    /**
     * Initializes the control by setting up styleable properties, the control itself, and updating the pseudo-class states.
     *
     * <p>This method is called immediately after a new instance of the control is created and provides a centralized initialization sequence for subclasses to ensure a consistent setup procedure.</p>
     */
    protected void initialize() {
        setupStyleableProperties();
        setupControl();
        updatePseudoClassStates();
    }

    /**
     * Creates a new instance of a given EFXControlBase subclass.
     *
     * <p>This factory method uses reflection to instantiate subclasses that may have protected constructors. It ensures that all control instances are properly initialized before being returned for use.</p>
     *
     * @param clazz
     *         The class of the control to be instantiated. This class must extend EFXControlBase.
     * @param <U>
     *         The type of the control to be created, extending EFXControlBase.
     *
     * @return A new instance of the specified EFXControlBase subclass, properly initialized.
     *
     * @throws RuntimeException
     *         If there is an error during the creation or initialization of the control instance. This includes reflection-related errors such as class not found, access issues, or instantiation issues.
     */
    protected static <U extends EFXControlBase<?>> U create(Class<U> clazz) {
        try {
            Constructor<U> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true); // Enables access to protected constructor
            U instance = constructor.newInstance();

            instance.initialize(); // Assuming an initialization method needs to be called
            return instance;
        }
        catch (Exception e) {
            throw new RuntimeException(String.format("Error creating %s instance", clazz.getSimpleName()), e);
        }
    }

    /**
     * Retrieves the inner control of the parent control.
     *
     * <p>The inner control is a component embedded within the parent control. Each specific subclass should provide its own implementation of this method.</p>
     *
     * @return the inner control of the parent control
     */
    public abstract T getInnerControl();

    /**
     * Retrieves the enhanced control associated with this class.
     *
     * <p>This method must be implemented by subclasses to return the specific instance of the enhanced control they represent. It serves as a crucial part of the interface between generic functionality
     * provided by the base class and the specific implementations provided by concrete subclasses.</p>
     *
     * @return An instance of {@code EFXControlBase} representing the specific control managed by this class.
     */
    protected abstract EFXControlBase<?> getControl();

    /**
     * Handles the loading of a new theme for the control.
     *
     * <p>This method must be implemented by subclasses of EFXControlBase to specify how a control responds to a theme change. It allows for dynamic updates of the control's visual appearance in response to
     * changes in the application's theme.</p>
     *
     * @param obs
     *         The observable object that triggered the theme change. This parameter allows for the implementation of observer patterns and can be used to react to specific changes in theme-related properties.
     * @param oldEFXTheme
     *         The previous theme that was applied to the control.
     * @param newEFXTheme
     *         The new theme that is being applied to the control.
     */
    protected abstract void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme);

    /**
     * Sets up the parent control.
     *
     * <p>This method is called during the initialization of the parent control. Specific setup logic is determined by the subclasses that provide their own implementation of this method.</p>
     */
    protected abstract void setupControl();

    /**
     * Sets up the styleable properties for this control.
     *
     * <p>This method is called to set up styleable properties, which control the appearance of this control. Each subclass should provide its own implementation of this method to customize its styleable
     * properties.</p>
     */
    protected abstract void setupStyleableProperties();

    /**
     * Updates the pseudoclass states for this control.
     *
     * <p>Pseudoclasses are used in CSS to define a special state of an element. This method is called to update the pseudoclass states of the control. Each subclass provides its own implementation of this
     * method to handle its unique pseudoclass states.</p>
     */
    protected abstract void updatePseudoClassStates();
}
