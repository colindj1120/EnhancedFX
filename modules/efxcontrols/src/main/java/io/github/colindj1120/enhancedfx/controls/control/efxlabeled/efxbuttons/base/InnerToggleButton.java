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
package io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * The {@code InnerToggleButton} interface defines common functionalities for an inner {@link ToggleButton} control. It extends {@link InnerButtonBase} to provide toggle-specific behavior to custom button
 * implementations. By encapsulating the behavior specific to toggle buttons, this interface allows for a consistent API across custom controls that internally use a {@link ToggleButton}.
 *
 * <h2>Capabilities:</h2>
 * <p>
 * <ul>
 *     <li>Toggle Selection: Allows querying and modifying the selected state of the inner toggle button.</li>
 *     <li>Toggle Group Association: Facilitates adding or removing the toggle button from a {@link ToggleGroup}, enabling single selection within a group.</li>
 *     <li>Property Access: Exposes the {@code selectedProperty} and {@code toggleGroupProperty} for binding or direct manipulation.</li>
 *     <li>State Toggle: Provides a method to programmatically toggle the selected state, switching between selected and deselected.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This interface ensures that any control implementing it can act as a toggle button or incorporate toggle button functionality seamlessly. It abstracts away the direct manipulation of the
 * {@link ToggleButton}'s properties and actions, promoting a cleaner, higher-level interface for component interaction.
 * </p>
 *
 * @param <T>
 *         the specific type of {@link ToggleButton} that is being wrapped or used internally
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InnerButtonBase
 * @see ToggleButton
 * @see ToggleGroup
 */
public interface InnerToggleButton<T extends ToggleButton> extends InnerButtonBase<T> {
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
