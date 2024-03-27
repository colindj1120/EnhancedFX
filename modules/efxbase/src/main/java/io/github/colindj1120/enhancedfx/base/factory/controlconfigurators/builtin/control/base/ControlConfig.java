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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.control.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.region.base.RegionConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Tooltip;

/**
 * Provides extensive configuration capabilities for JavaFX {@link Control} nodes, facilitating the customization and enhancement of user interface components.
 *
 * <p>This interface extends {@code RegionConfig<T>} and offers a broad range of configuration options for {@code Control} nodes, including setting skins, tooltips, and context menus. It enables the addition
 * and removal of listeners for property changes and invalidations, allowing for dynamic and responsive UI designs. Moreover, it supports property bindings to observable values, ensuring UI components can
 * automatically adapt to changes in data or state.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *   <li><b>Skin Management:</b> Customize the skin of controls to alter their appearance and behavior.</li>
 *   <li><b>Tooltip Configuration:</b> Add, modify, or remove tooltips to provide additional information for a control.</li>
 *   <li><b>Context Menu Configuration:</b> Assign or change context menus to enhance user interaction with right-click options.</li>
 *   <li><b>Listener Management:</b> Attach or detach listeners to control properties for handling changes or invalidations.</li>
 *   <li><b>Property Binding:</b> Bind control properties to observable values for real-time UI updates.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * Here is a simple usage example illustrating how to configure a Button control using an implementation of {@code ControlConfig}.
 * <pre>{@code
 * ControlConfig<ButtonConfigurator> buttonConfigurator = new ButtonConfigurator(button);
 * buttonConfigurator.setTooltip(new Tooltip("Click me!"))
 *                   .addSkinChangeListener((observable, oldValue, newValue) -> System.out.println("Skin changed"))
 *                   .bindTextProperty(viewModel.buttonTextProperty())
 *                   .setContextMenu(new ContextMenu(new MenuItem("Option 1"), new MenuItem("Option 2")));
 * }</pre>
 *
 * <p>In this example, a {@code Button} control is configured with a tooltip, a skin change listener, a bidirectional binding for its text property to a view model, and a context menu with two options.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ControlConfig
 * @see ConfiguratorBase
 * @see Control
 */
@SuppressWarnings("UnusedReturnValue")
public interface ControlConfig<T extends ConfiguratorBase> extends RegionConfig<T> {
    /*
     * Methods Available:
     *  - Control getNode()
     *
     * Add Listener Functions
     *  - T addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener)
     *  - T addSkinInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener)
     *  - T addTooltipInvalidationListener(InvalidationListener invalidationListener)
     *  - T addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener)
     *  - T addContextMenuInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener)
     *  - T removeSkinInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener)
     *  - T removeTooltipInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener)
     *  - T removeContextMenuInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue)
     *  - T unbindSkinProperty()
     *  - T bindBidirectionalSkinProperty(Property<Skin<?>> property)
     *  - T unbindBidirectionalSkinProperty(Property<Skin<?>> property)
     *  - T bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue)
     *  - T unbindTooltipProperty()
     *  - T bindBidirectionalTooltipProperty(Property<Tooltip> property)
     *  - T unbindBidirectionalTooltipProperty(Property<Tooltip> property)
     *  - T bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue)
     *  - T unbindContextMenuProperty()
     *  - T bindBidirectionalContextMenuProperty(Property<ContextMenu> property)
     *  - T unbindBidirectionalContextMenuProperty(Property<ContextMenu> property)
     *
     * Set Functions
     *  - T setSkin(Skin<?> value)
     *  - T setToolTip(Tooltip value)
     *  - T setContextMenu(ContextMenu value)
     */

    /**
     * Returns the {@link Control} node associated with this configurator.
     *
     * <p>This method provides access to the control node that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link Control} associated with the current configurator instance
     */
    @Override
    Control getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} for the skin property. Invoked when the skin of the control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        getNode().skinProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} for the skin property. Invoked when the skin property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSkinInvalidationListener(InvalidationListener invalidationListener) {
        getNode().skinProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} for the tooltip property. Invoked when the tooltip of the control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        getNode().tooltipProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} for the tooltip property. Invoked when the tooltip property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTooltipInvalidationListener(InvalidationListener invalidationListener) {
        getNode().tooltipProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} for the context menu property. Invoked when the context menu of the control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        getNode().contextMenuProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} for the context menu property. Invoked when the context menu property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addContextMenuInvalidationListener(InvalidationListener invalidationListener) {
        getNode().contextMenuProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the skin property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        getNode().skinProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the skin property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSkinInvalidationListener(InvalidationListener invalidationListener) {
        getNode().skinProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the tooltip property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        getNode().tooltipProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the tooltip property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTooltipInvalidationListener(InvalidationListener invalidationListener) {
        getNode().tooltipProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the context menu property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        getNode().contextMenuProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the context menu property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeContextMenuInvalidationListener(InvalidationListener invalidationListener) {
        getNode().contextMenuProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Skin Property

    /**
     * Binds the skin property to an observable value. The skin will update automatically when the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue) {
        getNode().skinProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the skin property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindSkinProperty() {
        getNode().skinProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the skin property bidirectionally with another property.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalSkinProperty(Property<Skin<?>> property) {
        getNode().skinProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the skin property from a bidirectional binding.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalSkinProperty(Property<Skin<?>> property) {
        getNode().skinProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Tooltip Property

    /**
     * Binds the tooltip property to an observable value. The tooltip will update automatically when the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue) {
        getNode().tooltipProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the tooltip property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindTooltipProperty() {
        getNode().tooltipProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the tooltip property bidirectionally with another property.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTooltipProperty(Property<Tooltip> property) {
        getNode().tooltipProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the tooltip property from a bidirectional binding.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTooltipProperty(Property<Tooltip> property) {
        getNode().tooltipProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Context Menu Property

    /**
     * Binds the context menu property to an observable value. The context menu will update automatically when the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue) {
        getNode().contextMenuProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the context menu property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindContextMenuProperty() {
        getNode().contextMenuProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the context menu property bidirectionally with another property.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        getNode().contextMenuProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the context menu property from a bidirectional binding.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        getNode().contextMenuProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the skin for the node.
     *
     * @param value
     *         The skin to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setSkin(Skin<?> value) {
        getNode().setSkin(value);
        return getConfigurator();
    }

    /**
     * Sets the tooltip for the node.
     *
     * @param value
     *         The tooltip to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setToolTip(Tooltip value) {
        getNode().setTooltip(value);
        return getConfigurator();
    }

    /**
     * Sets the context menu for the node.
     *
     * @param value
     *         The context menu to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setContextMenu(ContextMenu value) {
        getNode().setContextMenu(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
