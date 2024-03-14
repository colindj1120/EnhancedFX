/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of EnhancedFX (https://github.com/colindj1120/EnhancedFX).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.enhancedfx.controls.base.inner.innerbutton;

import io.github.colindj1120.enhancedfx.controls.base.inner.base.InnerLabeled;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ToggleButton;

/**
 * Provides an interface to define the essential functionalities of an inner button base, extending the capabilities of {@link InnerLabeled}. This interface is designed to be implemented by classes
 * that wish to encapsulate a {@link ButtonBase} control, providing a unified API to access and modify common button properties and behaviors. It serves as a foundational component in building a more
 * complex UI control hierarchy, especially in contexts where button-like behavior is desired, but direct inheritance from ButtonBase is not feasible or desired.
 *
 * <p>
 * Through this interface, implementers can access and manipulate properties such as the button's armed state, action event handlers, and execute accessible actions, thereby offering a flexible
 * mechanism for interacting with the underlying button control. It facilitates the creation of composite controls that need button functionalities, enabling developers to maintain a clear separation
 * of concerns and enhance the reusability of UI components within the MaterialDesignUI library.
 * </p>
 *
 * <p>
 * <em>Key functionalities exposed by this interface include:</em>
 * <ul>
 *     <li>Querying and setting the armed state of the button.</li>
 *     <li>Managing the on-action event handler, allowing for custom actions to be defined when the button is activated.</li>
 *     <li>Directly invoking the button's action through the {@code fire()} method, simulating a user interaction.</li>
 *     <li>Executing accessible actions, enhancing the accessibility of the control by supporting actions defined by {@link AccessibleAction}.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Implementers are expected to delegate these functionalities to an inner {@link ButtonBase} instance, encapsulated within the control. This approach promotes composition over inheritance,
 * providing a more versatile and modular way to construct the control's functionality.
 * </p>
 *
 * <p>
 * Usage of this interface is particularly suited to scenarios where custom button-like controls are needed, but extending existing JavaFX button controls is not practical. It enables developers to
 * embed and manage a {@link ButtonBase} instance as part of their custom control's implementation, leveraging the rich feature set of JavaFX buttons while adding unique behaviors and styles as
 * required.
 * </p>
 *
 * @param <T>
 *         The specific type of {@link ButtonBase} that this InnerButtonBase is encapsulating, such as {@link Button} or {@link ToggleButton}, allowing for type-specific button functionalities to be
 *         accessed and modified.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InnerLabeled
 * @see ButtonBase
 * @see Button
 * @see ToggleButton
 */
public interface InnerButtonBase<T extends ButtonBase> extends InnerLabeled<T> {
    /**
     * Returns the armed property of the inner control.
     *
     * @return the armed property of the inner control
     */
    default ReadOnlyBooleanProperty armedProperty() {return getInnerControl().armedProperty();}

    /**
     * Returns whether the button is currently armed.
     *
     * @return true if the button is armed, false otherwise
     */
    default boolean isArmed() {return getInnerControl().isArmed();}

    /**
     * Returns the ObjectProperty that holds the EventHandler<ActionEvent> for the onAction event of the control.
     *
     * @return the ObjectProperty that holds the EventHandler<ActionEvent>
     */
    default ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {return getInnerControl().onActionProperty();}

    /**
     * Sets the event handler to be invoked when this control is activated.
     *
     * @param value
     *         The event handler to be invoked.
     */
    default void setOnAction(EventHandler<ActionEvent> value) {getInnerControl().setOnAction(value);}

    /**
     * Returns the event handler for the action event of the inner control.
     *
     * @return the event handler for the action event of the inner control
     */
    default EventHandler<ActionEvent> getOnAction() {return getInnerControl().getOnAction();}

    /**
     * Arms the inner control. This sets the "armed" state of the control to true.
     *
     * @see InnerButtonBase#arm()
     */
    default void arm() {getInnerControl().arm();}

    /**
     * Disarms the button.
     */
    default void disarm() {getInnerControl().disarm();}

    /**
     * Executes the fire action on the inner control.
     */
    default void fire() {getInnerControl().fire();}

    /**
     * Executes the specified accessible action on the inner control of this button.
     *
     * @param action
     *         the accessible action to execute
     * @param parameters
     *         the parameters required for executing the accessible action
     */
    default void executeAccessibleAction(AccessibleAction action, Object... parameters) {getInnerControl().executeAccessibleAction(action, parameters);}
}
