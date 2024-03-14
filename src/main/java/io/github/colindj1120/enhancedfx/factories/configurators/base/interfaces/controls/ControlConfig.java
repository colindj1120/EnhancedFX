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
package io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.controls;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Tooltip;

/**
 * Defines a generic interface for configuring JavaFX {@link Control} objects, encompassing functionalities for adding and removing
 * listeners to control properties, binding and unbinding control properties to other observable values and properties, and setting
 * specific control attributes like skin, tooltip, and context menu.
 * <p>
 * Implementations of this interface allow for fluent chaining of configuration methods, enabling a declarative and intuitive approach
 * to control configuration. This interface serves as a foundation for creating configurable control components within the JavaFX
 * framework, facilitating the customization and dynamic behavior of UI elements.
 * </p>
 * <p>
 * The {@code ControlConfig} interface emphasizes flexibility and ease of use in UI development, providing methods for:
 * <ul>
 *     <li>Adding and removing change and invalidation listeners for skin, tooltip, and context menu properties.</li>
 *     <li>Binding and unbinding these properties to/from other observable values, allowing for responsive UIs
 *         that react to data changes.</li>
 *     <li>Setting control-specific attributes directly to enhance control appearance and functionality.</li>
 * </ul>
 * </p>
 *
 * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 * {@link Control} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 * @see ChangeListener
 * @see InvalidationListener
 * @see ObservableValue
 */
public interface ControlConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to the skin property of the control.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    ControlConfig addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener);

    /**
     * Adds an {@link InvalidationListener} to the skin property of the control.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    ControlConfig addSkinInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} to the tooltip property of the control.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    ControlConfig addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener);

    /**
     * Adds an {@link InvalidationListener} to the tooltip property of the control.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    ControlConfig addTooltipInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} to the context menu property of the control.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    ControlConfig addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener);

    /**
     * Adds an {@link InvalidationListener} to the context menu property of the control.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    ControlConfig addContextMenuInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the skin property of the control.
     *
     * @param changeListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ControlConfig removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the skin property of the control.
     *
     * @param invalidationListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ControlConfig removeSkinInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the tooltip property of the control.
     *
     * @param changeListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ControlConfig removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the tooltip property of the control.
     *
     * @param invalidationListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ControlConfig removeTooltipInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the context menu property of the control.
     *
     * @param changeListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ControlConfig removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the context menu property of the control.
     *
     * @param invalidationListener
     *         the listener to remove
     *
     * @return the current instance for method chaining
     */
    ControlConfig removeContextMenuInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // SkinProperty

    /**
     * Binds the skin property of the control to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the skin property
     *
     * @return the current instance for method chaining
     */
    ControlConfig bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue);

    /**
     * Unbinds the skin property of the control.
     *
     * @return the current instance for method chaining
     */
    ControlConfig unbindSkinProperty();

    /**
     * Establishes a bidirectional binding between the skin property of the control and another property.
     *
     * @param property
     *         the property to bind with the skin property bidirectionally
     *
     * @return the current instance for method chaining
     */
    ControlConfig bindBidirectionalSkinProperty(Property<Skin<?>> property);

    /**
     * Removes a bidirectional binding between the skin property of the control and another property.
     *
     * @param property
     *         the property to unbind from the skin property
     *
     * @return the current instance for method chaining
     */
    ControlConfig unbindBidirectionalSkinProperty(Property<Skin<?>> property);

    // TooltipProperty

    /**
     * Binds the tooltip property of the control to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the tooltip property
     *
     * @return the current instance for method chaining
     */
    ControlConfig bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue);

    /**
     * Unbinds the tooltip property of the control.
     *
     * @return the current instance for method chaining
     */
    ControlConfig unbindTooltipProperty();

    /**
     * Establishes a bidirectional binding between the tooltip property of the control and another property.
     *
     * @param property
     *         the property to bind with the tooltip property bidirectionally
     *
     * @return the current instance for method chaining
     */
    ControlConfig bindBidirectionalTooltipProperty(Property<Tooltip> property);

    /**
     * Removes a bidirectional binding between the tooltip property of the control and another property.
     *
     * @param property
     *         the property to unbind from the tooltip property
     *
     * @return the current instance for method chaining
     */
    ControlConfig unbindBidirectionalTooltipProperty(Property<Tooltip> property);

    // ContextMenuProperty

    /**
     * Binds the context menu property of the control to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the context menu property
     *
     * @return the current instance for method chaining
     */
    ControlConfig bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue);

    /**
     * Unbinds the context menu property of the control.
     *
     * @return the current instance for method chaining
     */
    ControlConfig unbindContextMenuProperty();

    /**
     * Establishes a bidirectional binding between the context menu property of the control and another property.
     *
     * @param property
     *         the property to bind with the context menu property bidirectionally
     *
     * @return the current instance for method chaining
     */
    ControlConfig bindBidirectionalContextMenuProperty(Property<ContextMenu> property);

    /**
     * Removes a bidirectional binding between the context menu property of the control and another property.
     *
     * @param property
     *         the property to unbind from the context menu property
     *
     * @return the current instance for method chaining
     */
    ControlConfig unbindBidirectionalContextMenuProperty(Property<ContextMenu> property);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the skin of the control.
     *
     * @param value
     *         the new skin to set
     *
     * @return the current instance for method chaining
     */
    ControlConfig setSkin(Skin<?> value);

    /**
     * Sets the tooltip of the control.
     *
     * @param value
     *         the new tooltip to set
     *
     * @return the current instance for method chaining
     */
    ControlConfig setToolTip(Tooltip value);

    /**
     * Sets the context menu of the control.
     *
     * @param value
     *         the new context menu to set
     *
     * @return the current instance for method chaining
     */
    ControlConfig setContextMenu(ContextMenu value);

    //endregion Set Functions
}
