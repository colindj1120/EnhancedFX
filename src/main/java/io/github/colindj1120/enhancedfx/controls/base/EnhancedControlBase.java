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
package io.github.colindj1120.enhancedfx.controls.base;

import io.github.colindj1120.enhancedfx.enums.styling.Theme;
import javafx.beans.Observable;
import javafx.scene.control.Control;

/**
 * An abstract base class for creating enhanced UI controls within the EnhancedFX framework.
 *
 * <p>
 * {@code EnhancedControlBase} provides a structured approach to extending JavaFX's {@link Control} class, offering a foundation upon which custom controls with additional functionalities and stylings can be
 * built. This class introduces a set of abstract methods that subclasses must implement, ensuring consistent behavior across all enhanced controls, such as theme management, styleable property setup, and
 * pseudoclass state updates.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <p>
 * <ul>
 *     <li><strong>Theme Management:</strong> Supports dynamic theme changes, allowing subclasses to implement specific logic for loading new themes and adjusting the control's appearance accordingly.</li>
 *     <li><strong>Styleable Properties:</strong> Encourages the setup of custom CSS styleable properties unique to the enhanced control, enhancing the control's integration with JavaFX's CSS styling.</li>
 *     <li><strong>Pseudoclass State Management:</strong> Facilitates the management of CSS pseudoclass states, enabling responsive and dynamic styling based on the control's state.</li>
 * </ul>
 * </p>
 *
 * <h2>Abstract Methods:</h2>
 * <p>
 * <em>Subclasses of {@code EnhancedControlBase} are required to implement the following abstract methods:</em>
 * <ul>
 *     <li>{@link #getControl()} - Retrieves the specific instance of the enhanced control.</li>
 *     <li>{@link #loadNewTheme(Observable, Theme, Theme)} - Handles theme changes.</li>
 *     <li>{@link #getInnerControl()} - Accesses the inner control for further customization.</li>
 *     <li>{@link #setupControl()} - Performs initial setup of the control.</li>
 *     <li>{@link #setupStyleableProperties()} - Configures custom styleable properties.</li>
 *     <li>{@link #updatePseudoClassStates()} - Updates the control's pseudoclass states.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This class, and its subclasses, are designed to be used within JavaFX applications that require custom UI controls with enhanced functionalities and styling not provided by the default JavaFX control set.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 */
public abstract class EnhancedControlBase<T extends Control> extends Control {
    /**
     * Retrieves the inner control of the parent control.
     *
     * <p>
     * The inner control is a component embedded within the parent control. Each specific subclass should provide its own implementation of this method.
     * </p>
     *
     * @return the inner control of the parent control
     */
    public abstract T getInnerControl();

    /**
     * Retrieves the enhanced control associated with this class.
     *
     * <p>
     * This method must be implemented by subclasses to return the specific instance of the enhanced control they represent. It serves as a crucial part of the interface between generic functionality provided
     * by the base class and the specific implementations provided by concrete subclasses.
     * </p>
     *
     * @return An instance of {@code EnhancedControlBase} representing the specific control managed by this class.
     */
    protected abstract EnhancedControlBase<?> getControl();

    protected abstract void loadNewTheme(Observable obs, Theme oldTheme, Theme newTheme);

    /**
     * Sets up the parent control.
     *
     * <p>
     * This method is called during the initialization of the parent control. Specific setup logic is determined by the subclasses that provide their own implementation of this method.
     * </p>
     */
    protected abstract void setupControl();

    /**
     * Sets up the styleable properties for this control.
     *
     * <p>
     * This method is called to set up styleable properties, which control the appearance of this control. Each subclass should provide its own implementation of this method to customize its styleable
     * properties.
     * </p>
     */
    protected abstract void setupStyleableProperties();

    /**
     * Updates the pseudoclass states for this control.
     *
     * <p>
     * Pseudoclasses are used in CSS to define a special state of an element. This method is called to update the pseudoclass states of the control. Each subclass provides its own implementation of this method
     * to handle its unique pseudoclass states.
     * </p>
     */
    protected abstract void updatePseudoClassStates();

}
