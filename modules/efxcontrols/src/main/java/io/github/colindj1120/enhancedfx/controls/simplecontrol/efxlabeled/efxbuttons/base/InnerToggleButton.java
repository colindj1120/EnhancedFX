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
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * This interface extends {@link InnerButtonBase} to provide specific functionalities for {@link ToggleButton} controls within JavaFX.
 *
 * <p>It encapsulates common toggle button operations such as managing the selected state, associating with a {@link ToggleGroup}, and toggling the state. This enables a more intuitive and flexible way to work
 * with toggle buttons in JavaFX applications.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Managing the selected state: Query and modify whether the toggle button is selected.</li>
 *     <li>Toggle group association: Associate the toggle button with a {@link ToggleGroup} to create mutually exclusive selection groups.</li>
 *     <li>State toggling: Easily toggle the selected state between on and off.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * InnerToggleButton<ToggleButton> myToggleButton = // instantiation of InnerToggleButton
 * myToggleButton.setSelected(true); // Selects the toggle button
 * myToggleButton.setToggleGroup(myToggleGroup); // Associates the toggle button with a toggle group
 * myToggleButton.toggle(); // Toggles the selected state
 * }
 * </pre>
 *
 * <p>This interface is part of the EnhancedFX library, aimed at providing extended functionalities and utilities for JavaFX controls, making the development of rich and interactive JavaFX applications more
 * convenient and efficient.</p>
 *
 * @param <T>
 *         the specific type of {@link ToggleButton} being enhanced by this interface
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InnerButtonBase
 * @see ToggleButton
 */
public interface InnerToggleButton<T extends ToggleButton> extends InnerButtonBase<T> {
    /*
     * Methods Available:
     *  - BooleanProperty selectedProperty()
     *  - boolean isSelected()
     *  - void setSelected(boolean value)
     *  - ObjectProperty<ToggleGroup> toggleGroupProperty()
     *  - ToggleGroup getToggleGroup()
     *  - void setToggleGroup(ToggleGroup value)
     *  - void toggle()
     */

    /**
     * Provides access to the {@code selectedProperty} of the {@link ToggleButton}.
     *
     * @return the {@link BooleanProperty} representing the selected state of the toggle button
     */
    default BooleanProperty selectedProperty() {
        return getInnerControl().selectedProperty();
    }

    /**
     * Checks if the {@link ToggleButton} is selected.
     *
     * @return {@code true} if the toggle button is selected, {@code false} otherwise
     */
    default boolean isSelected() {
        return getInnerControl().isSelected();
    }

    /**
     * Sets the selected state of the {@link ToggleButton}.
     *
     * @param value
     *         {@code true} to select the toggle button, {@code false} to deselect it
     */
    default void setSelected(boolean value) {
        getInnerControl().setSelected(value);
    }

    /**
     * Provides access to the {@code toggleGroupProperty} of the {@link ToggleButton}.
     *
     * @return the {@link ObjectProperty} managing the {@link ToggleGroup} association of the toggle button
     */
    default ObjectProperty<ToggleGroup> toggleGroupProperty() {
        return getInnerControl().toggleGroupProperty();
    }

    /**
     * Retrieves the {@link ToggleGroup} to which the {@link ToggleButton} belongs.
     *
     * @return the {@link ToggleGroup} of the toggle button, or {@code null} if it's not part of any group
     */
    default ToggleGroup getToggleGroup() {
        return getInnerControl().getToggleGroup();
    }

    /**
     * Assigns the {@link ToggleButton} to a specific {@link ToggleGroup}, effectively making it part of a group of toggle buttons where at most one button can be selected at any time.
     *
     * @param value
     *         the {@link ToggleGroup} to set for the toggle button; a {@code null} value removes it from its current group
     */
    default void setToggleGroup(ToggleGroup value) {
        getInnerControl().setToggleGroup(value);
    }

    /**
     * Toggles the selected state of the {@link ToggleButton}. If the button is currently selected, it will be deselected, and vice versa.
     */
    default void toggle() {
        getInnerControl().setSelected(!getInnerControl().isSelected());
    }
}
