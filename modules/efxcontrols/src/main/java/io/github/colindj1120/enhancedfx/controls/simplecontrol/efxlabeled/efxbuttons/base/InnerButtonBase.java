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

import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.base.InnerLabeled;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ToggleButton;

/**
 * {@link InnerButtonBase} extends the {@link InnerLabeled} interface to provide foundational functionalities specific to {@link ButtonBase} controls within JavaFX like {@link Button} and {@link ToggleButton}.
 *
 * <p>This includes handling of action events, arming and disarming the button, and executing accessible actions, thereby enhancing the button's interactivity and accessibility features.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Arming and disarming: Controls the button's armed state, indicating readiness to fire an action event.</li>
 *     <li>Action event handling: Allows setting and retrieving an action event handler that is called when the button is activated.</li>
 *     <li>Accessible action execution: Supports executing predefined accessible actions, enhancing the control's accessibility.</li>
 *     <li>Firing: Programmatically triggers the button's action event, simulating a user click.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * InnerButtonBase<ButtonBase> myButtonBase = // instantiation of InnerButtonBase
 * myButtonBase.setOnAction(event -> System.out.println("Button clicked!"));
 * myButtonBase.arm(); // Arms the button, indicating it's ready to perform its action
 * myButtonBase.fire(); // Programmatically clicks the button, firing its action event
 * myButtonBase.disarm(); // Disarms the button after the action is performed
 * }
 * </pre>
 *
 * <p>This interface is crucial for developers seeking to create rich, interactive, and accessible user interfaces by leveraging advanced button functionalities provided by JavaFX.</p>
 *
 * @param <T>
 *         the type of {@link ButtonBase} being enhanced by this interface
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ButtonBase
 * @see InnerLabeled
 */
public interface InnerButtonBase<T extends ButtonBase> extends InnerLabeled<T> {
    /*
     * Methods Available:
     *  - ReadOnlyBooleanProperty armedProperty()
     *  - boolean isArmed()
     *  - ObjectProperty<EventHandler<ActionEvent>> onActionProperty()
     *  - void setOnAction(EventHandler<ActionEvent> value)
     *  - EventHandler<ActionEvent> getOnAction()
     *  - void arm()
     *  - void disarm()
     *  - void fire()
     *  - void executeAccessibleAction(AccessibleAction action, Object... parameters)
     */

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
     * Arms the inner control. This sets the "armed" state of control to true.
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
