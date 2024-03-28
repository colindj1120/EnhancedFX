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
 * Defines an interface to enhance the functionality of {@link Button} controls by providing methods to manipulate their default and cancel properties, as well as a method to programmatically trigger the
 * button's action.
 *
 * <p>This interface builds upon {@link InnerButtonBase}, adding button-specific capabilities to the foundational functionalities provided by the base interface.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Marking a button as a default button, which will be activated when the user presses the Enter key.</li>
 *     <li>Marking a button as a cancel button, which will be activated when the user presses the Escape key.</li>
 *     <li>Programmatically firing the button's action event, simulating a user click.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * InnerButton<Button> myButton = // instantiation of InnerButton
 * myButton.setDefaultButton(true); // Marks the button as a default button
 * myButton.setCancelButton(false); // Ensures the button is not treated as a cancel button
 * myButton.fire(); // Programmatically clicks the button
 * }
 * </pre>
 *
 * <p>This interface is particularly useful for customizing button behaviors in complex user interfaces, allowing developers to easily assign roles to buttons and invoke their actions programmatically,
 * enhancing the user experience and streamlining UI interactions.</p>
 *
 * @param <T>
 *         the specific type of {@link Button} being enhanced by this interface
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Button
 * @see InnerButtonBase
 */
public interface InnerButton<T extends Button> extends InnerButtonBase<T> {
    /*
     * Methods Available:
     *  - void setDefaultButton(boolean value)
     *  - boolean isDefaultButton()
     *  - BooleanProperty defaultButtonProperty()
     *  - void setCancelButton(boolean value)
     *  - boolean isCancelButton()
     *  - BooleanProperty cancelButtonProperty()
     *  - void fire()
     */

    /**
     * Sets the default button property of the inner control.
     *
     * <p>When the default button property is set to true, the inner control will respond to the "Enter" key being pressed, triggering its default action.</p>
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
     * <p>Calling this method simulates a user interaction with the button, causing the associated inner control to perform its action. This is useful when programmatically triggering the action of a button,
     * such as when implementing custom keyboard shortcuts or automated tests.</p>
     *
     * <p>This method delegates to the {@code fire()} method of the inner control, invoking any registered {@code onAction} event handler and executing any accessible actions defined for the control.</p>
     *
     * @see InnerButtonBase#fire()
     */
    default void fire() {getInnerControl().fire();}
}
