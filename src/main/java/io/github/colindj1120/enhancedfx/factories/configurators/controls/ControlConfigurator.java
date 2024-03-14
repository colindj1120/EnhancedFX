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
package io.github.colindj1120.enhancedfx.factories.configurators.controls;

import io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.controls.ControlConfig;
import io.github.colindj1120.enhancedfx.factories.configurators.layout.RegionConfigurator;
import io.github.colindj1120.enhancedfx.factories.configurators.scene.NodeConfigurator;
import io.github.colindj1120.enhancedfx.factories.configurators.scene.ParentConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Tooltip;

/**
 * A versatile configurator for {@link Control} instances within JavaFX applications, extending the {@link RegionConfigurator} and
 * implementing the {@link ControlConfig} interface. This abstract class provides a rich set of functionalities for configuring various
 * aspects of controls, including their skins, tooltips, and context menus, in a fluent and intuitive manner.
 *
 * <p>
 * By inheriting capabilities from {@link NodeConfigurator}, {@link ParentConfigurator}, {@link RegionConfigurator}, and
 * {@link ControlConfigurator}, this configurator allows for comprehensive setup and customization of control properties, event
 * handling, and appearance. Method chaining between all these configurator functions enhances the developer experience by facilitating
 * succinct and expressive code.
 * </p>
 *
 * <p>
 * Capabilities include:
 * <ul>
 *     <li>Adding and removing change and invalidation listeners for control's skin, tooltip, and context menu properties.</li>
 *     <li>Binding these properties to observable values or other properties for dynamic UI updates and synchronization.</li>
 *     <li>Establishing and removing bidirectional bindings for the skin, tooltip, and context menu properties.</li>
 *     <li>Directly setting the skin, tooltip, and context menu to customize control appearance and behavior.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This configurator streamlines the process of customizing {@link Control} instances, making it easier to maintain and read the code
 * by abstracting boilerplate setup into a series of method calls.
 * </p>
 * <p>
 * Note: As this is an abstract class, concrete implementations should provide specific configuration capabilities tailored to
 * individual {@link Control} subclasses.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 * @see ChangeListener
 * @see InvalidationListener
 * @see ObservableValue
 * @see Skin
 * @see Tooltip
 * @see ContextMenu
 * @see NodeConfigurator
 * @see ParentConfigurator
 * @see RegionConfigurator
 * @see ControlConfig
 */
public abstract class ControlConfigurator extends RegionConfigurator implements ControlConfig {
    /**
     * The {@link Control} instance that is being configured. This field holds a reference to the specific control object upon which
     * configuration methods will act, enabling the modification and customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the control, allowing changes to be made through the configurator's methods rather
     * than direct access, promoting a more structured and maintainable approach to UI customization. The configurator provides a
     * fluent API for configuring various aspects of the control, including its appearance, behavior, and event handling.
     * </p>
     */
    private Control control;

    /**
     * Constructs a {@code ControlConfigurator} for the specified {@link Control}. This constructor initializes the configurator with a
     * target control. It sets up the environment for further configuration specific to {@link Control} instances. The
     * {@code ControlConfigurator.class} is used as the class reference for error reporting.
     *
     * @param control
     *         The {@link Control} to be configured. Must not be {@code null}, and an {@link IllegalArgumentException} will be thrown
     *         if a null control is passed. This ensures that the configurator has a valid target for configuration.
     */
    protected ControlConfigurator(Control control) {
        super(checkNodeNotNullAndInstanceOf(control, Control.class, ControlConfigurator.class, "Constructor"));
        this.control = control;
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        super.setNode(checkNodeNotNullAndInstanceOf(value, Control.class, ControlConfigurator.class, "setNode"));
        this.control = ((Control) value);
    }

    //endregion Overridden Methods

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        control.skinProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator addSkinInvalidationListener(InvalidationListener invalidationListener) {
        control.skinProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        control.tooltipProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator addTooltipInvalidationListener(InvalidationListener invalidationListener) {
        control.tooltipProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        control.contextMenuProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator addContextMenuInvalidationListener(InvalidationListener invalidationListener) {
        control.contextMenuProperty()
               .addListener(invalidationListener);
        return this;
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        control.skinProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator removeSkinInvalidationListener(InvalidationListener invalidationListener) {
        control.skinProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        control.tooltipProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator removeTooltipInvalidationListener(InvalidationListener invalidationListener) {
        control.tooltipProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        control.contextMenuProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator removeContextMenuInvalidationListener(InvalidationListener invalidationListener) {
        control.contextMenuProperty()
               .removeListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Bind Functions
    //*****************************************************************
    // Bind Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue) {
        control.skinProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator unbindSkinProperty() {
        control.skinProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator bindBidirectionalSkinProperty(Property<Skin<?>> property) {
        control.skinProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator unbindBidirectionalSkinProperty(Property<Skin<?>> property) {
        control.skinProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue) {
        control.tooltipProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator unbindTooltipProperty() {
        control.tooltipProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator bindBidirectionalTooltipProperty(Property<Tooltip> property) {
        control.tooltipProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator unbindBidirectionalTooltipProperty(Property<Tooltip> property) {
        control.tooltipProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue) {
        control.contextMenuProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator unbindContextMenuProperty() {
        control.contextMenuProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator bindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        control.contextMenuProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator unbindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        control.contextMenuProperty()
               .unbindBidirectional(property);
        return this;
    }

    //endregion Bind Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator setSkin(Skin<?> value) {
        control.setSkin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator setToolTip(Tooltip value) {
        control.setTooltip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControlConfigurator setContextMenu(ContextMenu value) {
        control.setContextMenu(value);
        return this;
    }

    //endregion Set Functions
}
