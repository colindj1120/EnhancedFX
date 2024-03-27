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

public abstract class EFXControlBase<T extends Control> extends Control {
    protected EFXControlBase() {}

    protected void initialize() {
        setupStyleableProperties();
        setupControl();
        updatePseudoClassStates();
    }

    protected static <U extends EFXControlBase<?>> U create(Class<U> clazz) {
        try {
            Constructor<U> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true); // Enables access to protected constructor
            U instance = constructor.newInstance();

            instance.initialize(); // Assuming an initialization method needs to be called
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error creating %s instance", clazz.getSimpleName()), e);
        }
    }

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
     * @return An instance of {@code EFXControlBase} representing the specific control managed by this class.
     */
    protected abstract EFXControlBase<?> getControl();

    protected abstract void loadNewTheme(Observable obs, EFXTheme oldEFXTheme, EFXTheme newEFXTheme);

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
