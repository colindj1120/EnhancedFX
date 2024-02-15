/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
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
package io.github.colindj1120.materialdesignui.controls.base.innertext;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * {@code InnerTextField} is an interface that extends {@link InnerTextInputControl} specifically for the {@link javafx.scene.control.TextField} control. It provides additional functionalities
 * tailored to a TextField, enhancing its capabilities within a larger custom component context.
 *
 * <p>Key functionalities provided by this interface include:</p>
 * <ul>
 *   <li>Preferred column count management: Methods to get, set, and observe changes to the preferred
 *       number of columns in the text field. This affects the width of the text field based on the number
 *       of characters it is expected to display.</li>
 *   <li>Action event handling: Facilities for setting and retrieving an event handler for 'onAction' events,
 *       which are typically triggered when the user presses the Enter key while the text field has focus.</li>
 *   <li>Text alignment: Methods to set and retrieve the text alignment within the text field, allowing
 *       customization of how text is visually presented.</li>
 * </ul>
 *
 * <p>This interface is particularly useful in scenarios where a {@code TextField} is embedded within a
 * custom composite component, and there's a need to directly manipulate or respond to specific
 * TextField properties or behaviors. Implementers of this interface can provide concrete functionality
 * to these abstract methods, enabling seamless integration and control of a TextField's features.</p>
 *
 * <p>Usage examples:</p>
 * <pre>
 * public class CustomTextFieldComponent implements InnerTextField {
 *     // Implementation of methods...
 * }
 *
 * CustomTextFieldComponent customComponent = new CustomTextFieldComponent();
 * customComponent.setPrefColumnCount(10);
 * customComponent.setOnAction(event -> System.out.println("Action performed!"));
 * customComponent.setTextFieldAlignment(Pos.CENTER_RIGHT);
 * </pre>
 *
 * <p>{@code InnerTextField} enhances the flexibility and reusability of {@code TextField} controls in
 * complex JavaFX UI applications, making it easier to create sophisticated and user-friendly text input interfaces.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextField
 * @see InnerTextInputControl
 */
public  interface  InnerTextField<T extends TextField> extends InnerTextInputControl<T> {
    /**
     * Returns the property for the preferred column count of the text field.
     *
     * @return the property for the preferred column count
     */
    default IntegerProperty prefColumnCountProperty() {return getTextField().prefColumnCountProperty();}

    /**
     * Returns the preferred number of columns for the text field.
     *
     * @return The preferred number of columns for the text field.
     */
    default int getPrefColumnCount() {return getTextField().getPrefColumnCount();}

    /**
     * Sets the preferred column count for the text field.
     *
     * @param value
     *         the number of columns to set
     */
    default void setPrefColumnCount(int value) {getTextField().setPrefColumnCount(value);}

    /**
     * Returns the property for the onAction event handler.
     *
     * @return The ObjectProperty for the onAction event handler.
     */
    default ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {return getTextField().onActionProperty();}

    /**
     * Returns the event handler for the "onAction" event.
     *
     * @return the event handler for the "onAction" event
     */
    default EventHandler<ActionEvent> getOnAction() {return getTextField().getOnAction();}

    /**
     * Sets the handler to be invoked when the text field's action event occurs.
     *
     * @param value
     *         the event handler
     */
    default void setOnAction(EventHandler<ActionEvent> value) {getTextField().setOnAction(value);}

    /**
     * Returns the property representing the alignment of the text in the MDTextField.
     *
     * @return the property representing the alignment of the text
     */
    default ObjectProperty<Pos> textFieldAlignmentProperty() {return getTextField().alignmentProperty();}

    /**
     * Sets the alignment of the text within the MDTextField.
     *
     * @param value
     *         the alignment to set for the text
     */
    default void setTextFieldAlignment(Pos value) {getTextField().setAlignment(value);}

    /**
     * Returns the alignment property of the text field.
     *
     * @return the alignment property of the text field
     */
    default Pos getTextFieldAlignment() {return getTextField().getAlignment();}
}
