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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.efxbuttons.base;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Button;

/**
 * The {@code InnerButton} interface defines essential functionalities for an inner {@link Button} control. It extends {@link InnerButtonBase} to add button-specific properties and actions, enabling custom
 * controls to incorporate standard button functionalities seamlessly.
 *
 * <h2>Capabilities:</h2>
 * <p>
 * <ul>
 *     <li>Default Button Configuration: Supports setting and querying whether the button acts as the default button, which is activated upon pressing the Enter key.</li>
 *     <li>Cancel Button Configuration: Allows setting and determining if the button serves as the cancel button, activated with the Escape key.</li>
 *     <li>Property Access: Exposes properties for the default and cancel button states, facilitating bindings and state observation.</li>
 *     <li>Action Invocation: Provides a method to programmatically trigger the button's action, mimicking a user click or activation.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This interface ensures a consistent API for custom controls leveraging button functionalities, abstracting the direct manipulation of a {@link Button}'s properties and providing a clean, intuitive way to
 * integrate button behavior into custom components.
 * </p>
 *
 * <p>
 * Implementers can easily add standard button behavior to their controls, ensuring user interactions and accessibility features like keyboard activation are handled consistently with native JavaFX
 * components.
 * </p>
 *
 * @param <T>
 *         the specific type of {@link Button} that is being wrapped or used internally
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InnerButtonBase
 * @see Button
 */
public interface InnerButton<T extends Button> extends InnerButtonBase<T> {
    /**
     * Sets the default button property of the inner control.
     *
     * <p>
     * When the default button property is set to true, the inner control will respond to the "Enter" key being pressed, triggering its default action.
     * </p>
     *
     * @param value
     *         true to set the default button property to true, false otherwise
     */
    default void setDefaultButton(boolean value) {getInnerControl().setDefaultButton(value);}

    /**
     * Returns whether the button is the default button. The default button is the button activated when the user presses the Enter key.
     *
     * @return true if the button is the default button, false otherwise
     */
    default boolean isDefaultButton() {return getInnerControl().isDefaultButton();}

    /**
     * Returns the boolean property that indicates whether the button is the default button.
     *
     * @return the default button property
     */
    default BooleanProperty defaultButtonProperty() {return getInnerControl().defaultButtonProperty();}

    /**
     * Sets the cancel button property of the inner control.
     *
     * @param value
     *         the boolean value indicating whether the button should behave as a cancel button
     */
    default void setCancelButton(boolean value) {getInnerControl().setCancelButton(value);}

    /**
     * Returns whether the button is the cancel button.
     *
     * @return {@code true} if the button is the cancel button, {@code false} otherwise
     */
    default boolean isCancelButton() {return getInnerControl().isCancelButton();}

    /**
     * Retrieves the cancel button property of the given control.
     *
     * @return the cancel button property of the control
     */
    default BooleanProperty cancelButtonProperty() {return getInnerControl().cancelButtonProperty();}

    /**
     * Fires the inner control associated with this button.
     *
     * <p>
     * Calling this method simulates a user interaction with the button, causing the associated inner control to perform its action. This is useful when programmatically triggering the action of a button, such
     * as when implementing custom keyboard shortcuts or automated tests.
     * </p>
     *
     * <p>
     * This method delegates to the {@code fire()} method of the inner control, invoking any registered {@code onAction} event handler and executing any accessible actions defined for the control.
     * </p>
     *
     * @see InnerButtonBase#fire()
     */
    default void fire() {getInnerControl().fire();}
}
