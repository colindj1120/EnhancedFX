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
package io.github.colindj1120.enhancedfx.controls.factory.configurators.scene;

import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.scene.NodeConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.input.*;
import javafx.scene.transform.Transform;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * The {@code NodeConfigurator} class is designed to simplify the configuration of JavaFX {@link Node} objects. It implements the
 * {@link NodeConfig} interface, providing a comprehensive suite of methods for manipulating a wide array of Node properties, including
 * visual appearance, layout parameters, and event handling. This configurator aims to offer a fluent and intuitive approach to Node
 * customization, making it easier to achieve desired UI outcomes without verbose boilerplate code.
 *
 * <h2>Key functionalities provided by the {@code NodeConfigurator} include:</h2>
 * <ul>
 *   <li>Adding and removing style classes dynamically to alter the node's appearance.</li>
 *   <li>Creating and dismantling bindings for all bindable Node properties, facilitating responsive UI designs.</li>
 *   <li>Adding or removing event filters and handlers, enhancing the node's interactivity.</li>
 *   <li>Invoking layout-related functions (e.g., {@code toBack}, {@code toFront}, {@code resize}), enabling precise control over
 *   the node's placement and dimensions.</li>
 * </ul>
 *
 * <p>By leveraging the {@code NodeConfigurator}, developers can streamline the customization and configuration process for
 * JavaFX nodes, focusing on defining the behavior and appearance that best suits their application's needs.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see NodeConfig
 * @see ConfiguratorBase
 * @see Node
 */
public abstract class NodeConfigurator extends ConfiguratorBase implements NodeConfig {
    /**
     * The {@link Node} instance that is being configured. This field holds a reference to the specific node object upon which
     * configuration methods will act, enabling the modification and customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the node, allowing changes to be made through the configurator's methods rather
     * than direct access, promoting a more structured and maintainable approach to UI customization. The configurator provides a
     * fluent API for configuring various aspects of the node, including its appearance, behavior, and event handling.
     * </p>
     */
    private Node node;

    /**
     * Constructs a new {@code NodeConfigurator} with the specified {@link Node}. This constructor ensures that the provided node is
     * not null and is an instance of {@link Node} class, leveraging a validation method to check these conditions. If the node fails
     * to meet these criteria, an {@link IllegalArgumentException} is thrown, indicating either a null value or a type mismatch. This
     * ensures the integrity and applicability of the configurator to the provided node.
     *
     * @param node
     *         The {@link Node} to be configured by this {@code NodeConfigurator}. Must not be null and must be an instance of
     *         {@link Node}.
     *
     * @throws IllegalArgumentException
     *         if {@code node} is null or not an instance of {@link Node}, ensuring that the configurator is always initialized with a
     *         valid, non-null {@link Node}.
     */
    protected NodeConfigurator(Node node) {
        this.node = checkNodeNotNullAndInstanceOf(node, Node.class, NodeConfigurator.class, "Constructor");
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        this.node = checkNodeNotNullAndInstanceOf(value, Node.class, NodeConfigurator.class, "setNode");
    }

    //endregion Overridden Functions

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        node.accessibleHelpProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        node.accessibleHelpProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        node.accessibleRoleDescriptionProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        node.accessibleRoleDescriptionProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        node.accessibleRoleProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        node.accessibleRoleProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        node.accessibleTextProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        node.accessibleTextProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        node.blendModeProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        node.blendModeProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        node.boundsInLocalProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        node.boundsInLocalProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        node.boundsInParentProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        node.boundsInParentProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        node.cacheHintProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        node.cacheHintProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.cacheProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addCacheInvalidationListener(InvalidationListener invalidationListener) {
        node.cacheProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addClipChangeListener(ChangeListener<? super Node> changeListener) {
        node.clipProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addClipInvalidationListener(InvalidationListener invalidationListener) {
        node.clipProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        node.cursorProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addCursorInvalidationListener(InvalidationListener invalidationListener) {
        node.cursorProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        node.depthTestProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        node.depthTestProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.disabledProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addDisabledInvalidationListener(InvalidationListener invalidationListener) {
        node.disabledProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.disableProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addDisableInvalidationListener(InvalidationListener invalidationListener) {
        node.disableProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        node.effectiveNodeOrientationProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        node.effectiveNodeOrientationProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        node.effectProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addEffectInvalidationListener(InvalidationListener invalidationListener) {
        node.effectProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        node.eventDispatcherProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        node.eventDispatcherProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.focusedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFocusedInvalidationListener(InvalidationListener invalidationListener) {
        node.focusedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.focusTraversableProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        node.focusTraversableProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.focusVisibleProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        node.focusVisibleProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.focusWithinProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        node.focusWithinProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.hoverProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addHoverInvalidationListener(InvalidationListener invalidationListener) {
        node.hoverProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addIdChangeListener(ChangeListener<? super String> changeListener) {
        node.idProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addIdInvalidationListener(InvalidationListener invalidationListener) {
        node.idProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        node.inputMethodRequestsProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        node.inputMethodRequestsProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        node.layoutBoundsProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        node.layoutBoundsProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        node.layoutXProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        node.layoutXProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        node.layoutYProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        node.layoutYProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        node.localToParentTransformProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        node.localToParentTransformProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        node.localToSceneTransformProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        node.localToSceneTransformProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.managedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addManagedInvalidationListener(InvalidationListener invalidationListener) {
        node.managedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.mouseTransparentProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        node.mouseTransparentProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        node.nodeOrientationProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        node.nodeOrientationProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnContextMenuRequestedChangeListener(
            ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        node.onContextMenuRequestedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        node.onContextMenuRequestedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onDragDetectedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragDetectedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragDoneProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragDoneProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragDroppedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragDroppedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragEnteredProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragEnteredProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragExitedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragExitedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragOverProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragOverProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnInputMethodTextChangedChangeListener(
            ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        node.onInputMethodTextChangedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        node.onInputMethodTextChangedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        node.onKeyPressedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        node.onKeyPressedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        node.onKeyReleasedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        node.onKeyReleasedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        node.onKeyTypedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        node.onKeyTypedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseClickedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseClickedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDragEnteredChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        node.onMouseDragEnteredProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDragEnteredProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDragExitedChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        node.onMouseDragExitedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDragExitedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseDraggedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDraggedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDragOverChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        node.onMouseDragOverProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDragOverProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDragReleasedChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        node.onMouseDragReleasedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDragReleasedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseEnteredProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseEnteredProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseExitedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseExitedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseMovedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseMovedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMousePressedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMousePressedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseReleasedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseReleasedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        node.onRotateProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        node.onRotateProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnRotationFinishedChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        node.onRotationFinishedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        node.onRotationFinishedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnRotationStartedChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        node.onRotationStartedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        node.onRotationStartedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnScrollFinishedChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        node.onScrollFinishedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        node.onScrollFinishedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        node.onScrollProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        node.onScrollProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnScrollStartedChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        node.onScrollStartedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        node.onScrollStartedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        node.onSwipeDownProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        node.onSwipeDownProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        node.onSwipeLeftProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        node.onSwipeLeftProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        node.onSwipeRightProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        node.onSwipeRightProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        node.onSwipeUpProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        node.onSwipeUpProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        node.onTouchMovedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        node.onTouchMovedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        node.onTouchPressedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        node.onTouchPressedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        node.onTouchReleasedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        node.onTouchReleasedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnTouchStationaryChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        node.onTouchStationaryProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        node.onTouchStationaryProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        node.onZoomFinishedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        node.onZoomFinishedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        node.onZoomProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        node.onZoomProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        node.onZoomStartedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        node.onZoomStartedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        node.opacityProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addOpacityInvalidationListener(InvalidationListener invalidationListener) {
        node.opacityProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addParentChangeListener(ChangeListener<? super Parent> changeListener) {
        node.parentProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addParentInvalidationListener(InvalidationListener invalidationListener) {
        node.parentProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.pickOnBoundsProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        node.pickOnBoundsProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addPressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.pressedProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addPressedInvalidationListener(InvalidationListener invalidationListener) {
        node.pressedProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        node.sceneProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addSceneInvalidationListener(InvalidationListener invalidationListener) {
        node.sceneProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addRotateChangeListener(ChangeListener<? super Number> changeListener) {
        node.rotateProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addRotateInvalidationListener(InvalidationListener invalidationListener) {
        node.rotateProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        node.rotationAxisProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        node.rotationAxisProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        node.scaleXProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addScaleXInvalidationListener(InvalidationListener invalidationListener) {
        node.scaleXProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        node.scaleYProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addScaleYInvalidationListener(InvalidationListener invalidationListener) {
        node.scaleYProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        node.scaleZProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addScaleZInvalidationListener(InvalidationListener invalidationListener) {
        node.scaleZProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addStyleChangeListener(ChangeListener<? super String> changeListener) {
        node.styleProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addStyleInvalidationListener(InvalidationListener invalidationListener) {
        node.styleProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        node.getStyleClass()
            .addListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addStyleListInvalidationListener(InvalidationListener invalidationListener) {
        node.getStyleClass()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        node.getTransforms()
            .addListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTransformListInvalidationListener(InvalidationListener invalidationListener) {
        node.getTransforms()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        node.translateXProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        node.translateXProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        node.translateYProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        node.translateYProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        node.translateZProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        node.translateZProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        node.viewOrderProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        node.viewOrderProperty()
            .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.visibleProperty()
            .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addVisibleInvalidationListener(InvalidationListener invalidationListener) {
        node.visibleProperty()
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
    public NodeConfigurator removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        node.accessibleHelpProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        node.accessibleHelpProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        node.accessibleRoleDescriptionProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        node.accessibleRoleDescriptionProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        node.accessibleRoleProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        node.accessibleRoleProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        node.accessibleTextProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        node.accessibleTextProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        node.blendModeProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        node.blendModeProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        node.boundsInLocalProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        node.boundsInLocalProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        node.boundsInParentProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        node.boundsInParentProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        node.cacheHintProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        node.cacheHintProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.cacheProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeCacheInvalidationListener(InvalidationListener invalidationListener) {
        node.cacheProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeClipChangeListener(ChangeListener<? super Node> changeListener) {
        node.clipProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeClipInvalidationListener(InvalidationListener invalidationListener) {
        node.clipProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        node.cursorProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeCursorInvalidationListener(InvalidationListener invalidationListener) {
        node.cursorProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        node.depthTestProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        node.depthTestProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.disabledProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeDisabledInvalidationListener(InvalidationListener invalidationListener) {
        node.disabledProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.disableProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeDisableInvalidationListener(InvalidationListener invalidationListener) {
        node.disableProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        node.effectiveNodeOrientationProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        node.effectiveNodeOrientationProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        node.effectProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeEffectInvalidationListener(InvalidationListener invalidationListener) {
        node.effectProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        node.eventDispatcherProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        node.eventDispatcherProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.focusedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFocusedInvalidationListener(InvalidationListener invalidationListener) {
        node.focusedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.focusTraversableProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        node.focusTraversableProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.focusVisibleProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        node.focusVisibleProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.focusWithinProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        node.focusWithinProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.hoverProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeHoverInvalidationListener(InvalidationListener invalidationListener) {
        node.hoverProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeIdChangeListener(ChangeListener<? super String> changeListener) {
        node.idProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeIdInvalidationListener(InvalidationListener invalidationListener) {
        node.idProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        node.inputMethodRequestsProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        node.inputMethodRequestsProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        node.layoutBoundsProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        node.layoutBoundsProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        node.layoutXProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        node.layoutXProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        node.layoutYProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        node.layoutYProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        node.localToParentTransformProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        node.localToParentTransformProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        node.localToSceneTransformProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        node.localToSceneTransformProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.managedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeManagedInvalidationListener(InvalidationListener invalidationListener) {
        node.managedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.mouseTransparentProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        node.mouseTransparentProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        node.nodeOrientationProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        node.nodeOrientationProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnContextMenuRequestedChangeListener(
            ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        node.onContextMenuRequestedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        node.onContextMenuRequestedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragDetectedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onDragDetectedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragDetectedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragDoneProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragDoneProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragDroppedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragDroppedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragEnteredProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragEnteredProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragExitedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragExitedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        node.onDragOverProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        node.onDragOverProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnInputMethodTextChangedChangeListener(
            ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        node.onInputMethodTextChangedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        node.onInputMethodTextChangedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        node.onKeyPressedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        node.onKeyPressedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        node.onKeyReleasedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        node.onKeyReleasedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        node.onKeyTypedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        node.onKeyTypedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseClickedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseClickedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseClickedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDragEnteredChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        node.onMouseDragEnteredProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDragEnteredProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDragExitedChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        node.onMouseDragExitedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDragExitedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDraggedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseDraggedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDraggedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDragOverChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        node.onMouseDragOverProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDragOverProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDragReleasedChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        node.onMouseDragReleasedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseDragReleasedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseEnteredChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseEnteredProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseEnteredProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseExitedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseExitedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseExitedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseMovedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseMovedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMousePressedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMousePressedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMousePressedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseReleasedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        node.onMouseReleasedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        node.onMouseReleasedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        node.onRotateProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        node.onRotateProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnRotationFinishedChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        node.onRotationFinishedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        node.onRotationFinishedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnRotationStartedChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        node.onRotationStartedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        node.onRotationStartedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnScrollFinishedChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        node.onScrollFinishedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        node.onScrollFinishedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        node.onScrollProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        node.onScrollProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnScrollStartedChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        node.onScrollStartedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        node.onScrollStartedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        node.onSwipeDownProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        node.onSwipeDownProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        node.onSwipeLeftProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        node.onSwipeLeftProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        node.onSwipeRightProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        node.onSwipeRightProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        node.onSwipeUpProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        node.onSwipeUpProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        node.onTouchMovedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        node.onTouchMovedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnTouchPressedChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        node.onTouchPressedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        node.onTouchPressedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnTouchReleasedChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        node.onTouchReleasedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        node.onTouchReleasedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnTouchStationaryChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        node.onTouchStationaryProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        node.onTouchStationaryProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnZoomFinishedChangeListener(
            ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        node.onZoomFinishedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        node.onZoomFinishedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        node.onZoomProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        node.onZoomProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnZoomStartedChangeListener(
            ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        node.onZoomStartedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        node.onZoomStartedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        node.opacityProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeOpacityInvalidationListener(InvalidationListener invalidationListener) {
        node.opacityProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeParentChangeListener(ChangeListener<? super Parent> changeListener) {
        node.parentProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeParentInvalidationListener(InvalidationListener invalidationListener) {
        node.parentProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.pickOnBoundsProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        node.pickOnBoundsProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removePressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.pressedProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removePressedInvalidationListener(InvalidationListener invalidationListener) {
        node.pressedProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        node.sceneProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeSceneInvalidationListener(InvalidationListener invalidationListener) {
        node.sceneProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeRotateChangeListener(ChangeListener<? super Number> changeListener) {
        node.rotateProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeRotateInvalidationListener(InvalidationListener invalidationListener) {
        node.rotateProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        node.rotationAxisProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        node.rotationAxisProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        node.scaleXProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeScaleXInvalidationListener(InvalidationListener invalidationListener) {
        node.scaleXProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        node.scaleYProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeScaleYInvalidationListener(InvalidationListener invalidationListener) {
        node.scaleYProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        node.scaleZProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeScaleZInvalidationListener(InvalidationListener invalidationListener) {
        node.scaleZProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeStyleChangeListener(ChangeListener<? super String> changeListener) {
        node.styleProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeStyleInvalidationListener(InvalidationListener invalidationListener) {
        node.styleProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        node.getStyleClass()
            .removeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeStyleListInvalidationListener(InvalidationListener invalidationListener) {
        node.getStyleClass()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        node.getTransforms()
            .removeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTransformListInvalidationListener(InvalidationListener invalidationListener) {
        node.getTransforms()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        node.translateXProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        node.translateXProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        node.translateYProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        node.translateYProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        node.translateZProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        node.translateZProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        node.viewOrderProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        node.viewOrderProperty()
            .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        node.visibleProperty()
            .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeVisibleInvalidationListener(InvalidationListener invalidationListener) {
        node.visibleProperty()
            .removeListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setAccessibleHelp(String value) {
        node.setAccessibleHelp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setAccessibleRole(AccessibleRole value) {
        node.setAccessibleRole(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setAccessibleRoleDescription(String value) {
        node.setAccessibleRoleDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setAccessibleText(String value) {
        node.setAccessibleHelp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setBlendMode(BlendMode value) {
        node.setBlendMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setCache(boolean value) {
        node.setCache(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setCacheHint(CacheHint value) {
        node.setCacheHint(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setClip(Node value) {
        node.setClip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setCursor(Cursor value) {
        node.setCursor(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setDepthTest(DepthTest value) {
        node.setDepthTest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setDisable(boolean value) {
        node.setDisable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setEffect(Effect value) {
        node.setEffect(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setEventDispatcher(EventDispatcher value) {
        node.setEventDispatcher(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setFocusTraversable(boolean value) {
        node.setFocusTraversable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setId(String value) {
        node.setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setInputMethodRequests(InputMethodRequests value) {
        node.setInputMethodRequests(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setLayoutX(double value) {
        node.setLayoutX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setLayoutY(double value) {
        node.setLayoutY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setManaged(boolean value) {
        node.setManaged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setMouseTransparent(boolean value) {
        node.setMouseTransparent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setNodeOrientation(NodeOrientation value) {
        node.setNodeOrientation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
        node.setOnContextMenuRequested(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnDragDetected(EventHandler<? super MouseEvent> value) {
        node.setOnDragDetected(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnDragDone(EventHandler<? super DragEvent> value) {
        node.setOnDragDone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnDragDropped(EventHandler<? super DragEvent> value) {
        node.setOnDragDropped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnDragEntered(EventHandler<? super DragEvent> value) {
        node.setOnDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnDragExited(EventHandler<? super DragEvent> value) {
        node.setOnDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnDragOver(EventHandler<? super DragEvent> value) {
        node.setOnDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
        node.setOnInputMethodTextChanged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnKeyPressed(EventHandler<? super KeyEvent> value) {
        node.setOnKeyPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnKeyTyped(EventHandler<? super KeyEvent> value) {
        node.setOnKeyTyped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseClicked(EventHandler<? super MouseEvent> value) {
        node.setOnMouseClicked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value) {
        node.setOnMouseDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseDragExited(EventHandler<? super MouseDragEvent> value) {
        node.setOnMouseDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseDragOver(EventHandler<? super MouseDragEvent> value) {
        node.setOnMouseDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value) {
        node.setOnMouseDragReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseEntered(EventHandler<? super MouseEvent> value) {
        node.setOnMouseEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseExited(EventHandler<? super MouseEvent> value) {
        node.setOnMouseExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        node.setOnMouseMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMousePressed(EventHandler<? super MouseEvent> value) {
        node.setOnMousePressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        node.setOnMouseReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnRotate(EventHandler<? super RotateEvent> value) {
        node.setOnRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnRotationFinished(EventHandler<? super RotateEvent> value) {
        node.setOnRotationFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnRotationStarted(EventHandler<? super RotateEvent> value) {
        node.setOnRotationStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnScroll(EventHandler<? super ScrollEvent> value) {
        node.setOnScroll(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnScrollFinished(EventHandler<? super ScrollEvent> value) {
        node.setOnScrollFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnScrollStarted(EventHandler<? super ScrollEvent> value) {
        node.setOnScrollStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOpacity(double value) {
        node.setOpacity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setStyle(String style) {
        node.setStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnSwipeDown(EventHandler<? super SwipeEvent> value) {
        node.setOnSwipeDown(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
        node.setOnSwipeLeft(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnSwipeRight(EventHandler<? super SwipeEvent> value) {
        node.setOnSwipeRight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnSwipeUp(EventHandler<? super SwipeEvent> value) {
        node.setOnSwipeUp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnTouchMoved(EventHandler<? super TouchEvent> value) {
        node.setOnTouchMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnTouchPressed(EventHandler<? super TouchEvent> value) {
        node.setOnTouchPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnTouchReleased(EventHandler<? super TouchEvent> value) {
        node.setOnTouchReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnTouchStationary(EventHandler<? super TouchEvent> value) {
        node.setOnTouchStationary(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnZoom(EventHandler<? super ZoomEvent> value) {
        node.setOnZoom(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnZoomFinished(EventHandler<? super ZoomEvent> value) {
        node.setOnZoomFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setOnZoomStarted(EventHandler<? super ZoomEvent> value) {
        node.setOnZoomStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setPickOnBounds(boolean value) {
        node.setPickOnBounds(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setRotate(double value) {
        node.setRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setRotationAxis(Point3D value) {
        node.setRotationAxis(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setScaleX(double value) {
        node.setScaleX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setScaleY(double value) {
        node.setScaleY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setScaleZ(double value) {
        node.setScaleZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setTranslateX(double value) {
        node.setTranslateX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setTranslateY(double value) {
        node.setTranslateY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setTranslateZ(double value) {
        node.setTranslateZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setUserData(Object value) {
        node.setUserData(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setViewOrder(double value) {
        node.setViewOrder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator setVisible(boolean value) {
        node.setVisible(value);
        return this;
    }

    //endregion Set Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue) {
        node.accessibleHelpProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindAccessibleHelpProperty() {
        node.accessibleHelpProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalAccessibleHelpProperty(Property<String> property) {
        node.accessibleHelpProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalAccessibleHelpProperty(Property<String> property) {
        node.accessibleHelpProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue) {
        node.accessibleRoleDescriptionProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindAccessibleRoleDescriptionProperty() {
        node.accessibleRoleDescriptionProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        node.accessibleRoleDescriptionProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        node.accessibleRoleDescriptionProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue) {
        node.accessibleRoleProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindAccessibleRoleProperty() {
        node.accessibleRoleProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        node.accessibleRoleProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        node.accessibleRoleProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindAccessibleTextProperty(ObservableValue<? extends String> observableValue) {
        node.accessibleTextProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindAccessibleTextProperty() {
        node.accessibleTextProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalAccessibleTextProperty(Property<String> property) {
        node.accessibleTextProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalAccessibleTextProperty(Property<String> property) {
        node.accessibleTextProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue) {
        node.blendModeProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBlendModeProperty() {
        node.blendModeProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        node.blendModeProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        node.blendModeProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue) {
        node.cacheHintProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindCacheHintProperty() {
        node.cacheHintProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        node.cacheHintProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        node.cacheHintProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindCacheProperty(ObservableValue<? extends Boolean> observableValue) {
        node.cacheProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindCacheProperty() {
        node.cacheProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalCacheProperty(Property<Boolean> property) {
        node.cacheProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalCacheProperty(Property<Boolean> property) {
        node.cacheProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindClipProperty(ObservableValue<? extends Node> observableValue) {
        node.clipProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindClipProperty() {
        node.clipProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalClipProperty(Property<Node> property) {
        node.clipProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalClipProperty(Property<Node> property) {
        node.clipProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindCursorProperty(ObservableValue<? extends Cursor> observableValue) {
        node.cursorProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindCursorProperty() {
        node.cursorProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalCursorProperty(Property<Cursor> property) {
        node.cursorProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalCursorProperty(Property<Cursor> property) {
        node.cursorProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue) {
        node.depthTestProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindDepthTestProperty() {
        node.depthTestProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        node.depthTestProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        node.depthTestProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindDisableProperty(ObservableValue<? extends Boolean> observableValue) {
        node.disableProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindDisableProperty() {
        node.disableProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalDisableProperty(Property<Boolean> property) {
        node.disableProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalDisableProperty(Property<Boolean> property) {
        node.disableProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindEffectProperty(ObservableValue<? extends Effect> observableValue) {
        node.effectProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindEffectProperty() {
        node.effectProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalEffectProperty(Property<Effect> property) {
        node.effectProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalEffectProperty(Property<Effect> property) {
        node.effectProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue) {
        node.eventDispatcherProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindEventDispatcherProperty() {
        node.eventDispatcherProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        node.eventDispatcherProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        node.eventDispatcherProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue) {
        node.focusTraversableProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindFocusTraversableProperty() {
        node.focusTraversableProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        node.focusTraversableProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        node.focusTraversableProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindIdProperty(ObservableValue<? extends String> observableValue) {
        node.idProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindIdProperty() {
        node.idProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalIdProperty(Property<String> property) {
        node.idProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalIdProperty(Property<String> property) {
        node.idProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue) {
        node.inputMethodRequestsProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindInputMethodRequestsProperty() {
        node.inputMethodRequestsProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        node.inputMethodRequestsProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        node.inputMethodRequestsProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindLayoutXProperty(ObservableValue<? extends Number> observableValue) {
        node.layoutXProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindLayoutXProperty() {
        node.layoutXProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalLayoutXProperty(Property<Number> property) {
        node.layoutXProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalLayoutXProperty(Property<Number> property) {
        node.layoutXProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindLayoutYProperty(ObservableValue<? extends Number> observableValue) {
        node.layoutYProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindLayoutYProperty() {
        node.layoutYProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalLayoutYProperty(Property<Number> property) {
        node.layoutYProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalLayoutYProperty(Property<Number> property) {
        node.layoutYProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindManagedProperty(ObservableValue<? extends Boolean> observableValue) {
        node.managedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindManagedProperty() {
        node.managedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalManagedProperty(Property<Boolean> property) {
        node.managedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalManagedProperty(Property<Boolean> property) {
        node.managedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue) {
        node.mouseTransparentProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindMouseTransparentProperty() {
        node.mouseTransparentProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        node.mouseTransparentProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        node.mouseTransparentProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue) {
        node.nodeOrientationProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindNodeOrientationProperty() {
        node.nodeOrientationProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        node.nodeOrientationProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        node.nodeOrientationProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnContextMenuRequestedProperty(
            ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue) {
        node.onContextMenuRequestedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnContextMenuRequestedProperty() {
        node.onContextMenuRequestedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnContextMenuRequestedProperty(
            Property<EventHandler<? super ContextMenuEvent>> property) {
        node.onContextMenuRequestedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnContextMenuRequestedProperty(
            Property<EventHandler<? super ContextMenuEvent>> property) {
        node.onContextMenuRequestedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        node.onDragDetectedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnDragDetectedProperty() {
        node.onDragDetectedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onDragDetectedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onDragDetectedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        node.onDragDoneProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnDragDoneProperty() {
        node.onDragDoneProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragDoneProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragDoneProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        node.onDragDroppedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnDragDroppedProperty() {
        node.onDragDroppedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragDroppedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragDroppedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        node.onDragEnteredProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnDragEnteredProperty() {
        node.onDragEnteredProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragEnteredProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragEnteredProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        node.onDragExitedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnDragExitedProperty() {
        node.onDragExitedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragExitedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragExitedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        node.onDragOverProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnDragOverProperty() {
        node.onDragOverProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragOverProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        node.onDragOverProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnInputMethodTextChangedProperty(
            ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue) {
        node.onInputMethodTextChangedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnInputMethodTextChangedProperty() {
        node.onInputMethodTextChangedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnInputMethodTextChangedProperty(
            Property<EventHandler<? super InputMethodEvent>> property) {
        node.onInputMethodTextChangedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnInputMethodTextChangedProperty(
            Property<EventHandler<? super InputMethodEvent>> property) {
        node.onInputMethodTextChangedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        node.onKeyPressedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnKeyPressedProperty() {
        node.onKeyPressedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        node.onKeyPressedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        node.onKeyPressedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        node.onKeyReleasedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnKeyReleasedProperty() {
        node.onKeyReleasedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        node.onKeyReleasedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        node.onKeyReleasedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        node.onKeyTypedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnKeyTypedProperty() {
        node.onKeyTypedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        node.onKeyTypedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        node.onKeyTypedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        node.onMouseClickedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseClickedProperty() {
        node.onMouseClickedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseClickedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseClickedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseDragEnteredProperty(
            ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        node.onMouseDragEnteredProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseDragEnteredProperty() {
        node.onMouseDragEnteredProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        node.onMouseDragEnteredProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        node.onMouseDragEnteredProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseDragExitedProperty(
            ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        node.onMouseDragExitedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseDragExitedProperty() {
        node.onMouseDragExitedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        node.onMouseDragExitedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        node.onMouseDragExitedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        node.onMouseDraggedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseDraggedProperty() {
        node.onMouseDraggedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseDraggedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseDraggedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseDragOverProperty(
            ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        node.onMouseDragOverProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseDragOverProperty() {
        node.onMouseDragOverProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        node.onMouseDragOverProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        node.onMouseDragOverProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseDragReleasedProperty(
            ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        node.onMouseDragReleasedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseDragReleasedProperty() {
        node.onMouseDragReleasedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        node.onMouseDragReleasedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        node.onMouseDragReleasedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        node.onMouseEnteredProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseEnteredProperty() {
        node.onMouseEnteredProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseEnteredProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseEnteredProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        node.onMouseExitedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseExitedProperty() {
        node.onMouseExitedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseExitedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseExitedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        node.onMouseMovedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseMovedProperty() {
        node.onMouseMovedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseMovedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseMovedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        node.onMousePressedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMousePressedProperty() {
        node.onMousePressedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMousePressedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMousePressedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        node.onMouseReleasedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnMouseReleasedProperty() {
        node.onMouseReleasedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseReleasedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        node.onMouseReleasedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        node.onRotateProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnRotateProperty() {
        node.onRotateProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        node.onRotateProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        node.onRotateProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnRotationFinishedProperty(
            ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        node.onRotationFinishedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnRotationFinishedProperty() {
        node.onRotationFinishedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        node.onRotationFinishedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        node.onRotationFinishedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnRotationStartedProperty(
            ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        node.onRotationStartedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnRotationStartedProperty() {
        node.onRotationStartedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        node.onRotationStartedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        node.onRotationStartedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        node.onScrollProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnScrollProperty() {
        node.onScrollProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        node.onScrollProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        node.onScrollProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnScrollFinishedProperty(
            ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        node.onScrollFinishedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnScrollFinishedProperty() {
        node.onScrollFinishedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        node.onScrollFinishedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        node.onScrollFinishedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        node.onScrollStartedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnScrollStartedProperty() {
        node.onScrollStartedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        node.onScrollStartedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        node.onScrollStartedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        node.onSwipeDownProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnSwipeDownProperty() {
        node.onSwipeDownProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        node.onSwipeDownProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        node.onSwipeDownProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        node.onSwipeLeftProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnSwipeLeftProperty() {
        node.onSwipeLeftProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        node.onSwipeLeftProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        node.onSwipeLeftProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        node.onSwipeRightProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnSwipeRightProperty() {
        node.onSwipeRightProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        node.onSwipeRightProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        node.onSwipeRightProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        node.onSwipeUpProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnSwipeUpProperty() {
        node.onSwipeUpProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        node.onSwipeUpProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        node.onSwipeUpProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        node.onTouchMovedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnTouchMovedProperty() {
        node.onTouchMovedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        node.onTouchMovedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        node.onTouchMovedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        node.onTouchPressedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnTouchPressedProperty() {
        node.onTouchPressedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        node.onTouchPressedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        node.onTouchPressedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        node.onTouchReleasedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnTouchReleasedProperty() {
        node.onTouchReleasedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        node.onTouchReleasedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        node.onTouchReleasedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnTouchStationaryProperty(
            ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        node.onTouchStationaryProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnTouchStationaryProperty() {
        node.onTouchStationaryProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        node.onTouchStationaryProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        node.onTouchStationaryProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        node.onZoomProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnZoomProperty() {
        node.onZoomProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        node.onZoomProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        node.onZoomProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        node.onZoomFinishedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnZoomFinishedProperty() {
        node.onZoomFinishedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        node.onZoomFinishedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        node.onZoomFinishedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        node.onZoomStartedProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOnZoomStartedProperty() {
        node.onZoomStartedProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        node.onZoomStartedProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        node.onZoomStartedProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindOpacityProperty(ObservableValue<? extends Number> observableValue) {
        node.opacityProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindOpacityProperty() {
        node.opacityProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalOpacityProperty(Property<Number> property) {
        node.opacityProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalOpacityProperty(Property<Number> property) {
        node.opacityProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue) {
        node.pickOnBoundsProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindPickOnBoundsProperty() {
        node.pickOnBoundsProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        node.pickOnBoundsProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        node.pickOnBoundsProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindRotateProperty(ObservableValue<? extends Number> observableValue) {
        node.rotateProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindRotateProperty() {
        node.rotateProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalRotateProperty(Property<Number> property) {
        node.rotateProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalRotateProperty(Property<Number> property) {
        node.rotateProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue) {
        node.rotationAxisProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindRotationAxisProperty() {
        node.rotationAxisProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        node.rotationAxisProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        node.rotationAxisProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindScaleXProperty(ObservableValue<? extends Number> observableValue) {
        node.scaleXProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindScaleXProperty() {
        node.scaleXProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalScaleXProperty(Property<Number> property) {
        node.scaleXProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalScaleXProperty(Property<Number> property) {
        node.scaleXProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindScaleYProperty(ObservableValue<? extends Number> observableValue) {
        node.scaleYProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindScaleYProperty() {
        node.scaleYProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalScaleYProperty(Property<Number> property) {
        node.scaleYProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalScaleYProperty(Property<Number> property) {
        node.scaleYProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindScaleZProperty(ObservableValue<? extends Number> observableValue) {
        node.scaleZProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindScaleZProperty() {
        node.scaleZProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalScaleZProperty(Property<Number> property) {
        node.scaleZProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalScaleZProperty(Property<Number> property) {
        node.scaleZProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindStyleProperty(ObservableValue<? extends String> observableValue) {
        node.styleProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindStyleProperty() {
        node.styleProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalStyleProperty(Property<String> property) {
        node.styleProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalStyleProperty(Property<String> property) {
        node.styleProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindTranslateXProperty(ObservableValue<? extends Number> observableValue) {
        node.translateXProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindTranslateXProperty() {
        node.translateXProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalTranslateXProperty(Property<Number> property) {
        node.translateXProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalTranslateXProperty(Property<Number> property) {
        node.translateXProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindTranslateYProperty(ObservableValue<? extends Number> observableValue) {
        node.translateYProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindTranslateYProperty() {
        node.translateYProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalTranslateYProperty(Property<Number> property) {
        node.translateYProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalTranslateYProperty(Property<Number> property) {
        node.translateYProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindTranslateZProperty(ObservableValue<? extends Number> observableValue) {
        node.translateZProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindTranslateZProperty() {
        node.translateZProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalTranslateZProperty(Property<Number> property) {
        node.translateZProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalTranslateZProperty(Property<Number> property) {
        node.translateZProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindViewOrderProperty(ObservableValue<? extends Number> observableValue) {
        node.viewOrderProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindViewOrderProperty() {
        node.viewOrderProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalViewOrderProperty(Property<Number> property) {
        node.viewOrderProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalViewOrderProperty(Property<Number> property) {
        node.viewOrderProperty()
            .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindVisibleProperty(ObservableValue<? extends Boolean> observableValue) {
        node.visibleProperty()
            .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindVisibleProperty() {
        node.visibleProperty()
            .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator bindBidirectionalVisibleProperty(Property<Boolean> property) {
        node.visibleProperty()
            .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator unbindBidirectionalVisibleProperty(Property<Boolean> property) {
        node.visibleProperty()
            .unbindBidirectional(property);
        return this;
    }

    //endregion Binding Functions

    //region Event Functions
    //*****************************************************************
    // Event Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator fireEvent(Event event) {
        node.fireEvent(event);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> NodeConfigurator addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        node.addEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> NodeConfigurator addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        node.addEventHandler(eventType, eventHandler);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> NodeConfigurator removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        node.addEventHandler(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> NodeConfigurator removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        node.addEventHandler(eventType, eventHandler);
        return this;
    }

    //endregion Event Functions

    //region Add EFXStyle Functions
    //*****************************************************************
    // Add EFXStyle Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFirstStyleClass(String styleClass) {
        node.getStyleClass()
            .addFirst(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLastStyleClass(String styleClass) {
        node.getStyleClass()
            .addLast(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addStyleClass(String styleClass) {
        node.getStyleClass()
            .add(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addStyleClass(int index, String styleClass) {
        node.getStyleClass()
            .add(index, styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAllStyleClasses(String... styleClasses) {
        node.getStyleClass()
            .addAll(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAllStyleClasses(Collection<? extends String> c) {
        node.getStyleClass()
            .addAll(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAllStyleClasses(int index, Collection<? extends String> c) {
        node.getStyleClass()
            .addAll(index, c);
        return this;
    }

    //endregion Add EFXStyle Functions

    //region Remove EFXStyle Functions
    //*****************************************************************
    // Remove EFXStyle Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFirstStyleClass() {
        node.getStyleClass()
            .removeFirst();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLastStyleClass() {
        node.getStyleClass()
            .removeLast();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeStyleClass(String styleClass) {
        node.getStyleClass()
            .remove(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeStyleClasses(int from, int to) {
        node.getStyleClass()
            .remove(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeStyleClassesIf(Predicate<? super String> filter) {
        node.getStyleClass()
            .removeIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAllStyleClasses(String... styleClasses) {
        node.getStyleClass()
            .removeAll(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAllStyleClasses(Collection<? extends String> c) {
        node.getStyleClass()
            .removeAll(c);
        return this;
    }

    //endregion Remove EFXStyle Functions

    //region EFXStyle Functions
    //*****************************************************************
    // EFXStyle Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator pseudoClassStateChange(PseudoClass pseudoClass, boolean active) {
        node.pseudoClassStateChanged(pseudoClass, active);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator applyCss() {
        node.applyCss();
        return this;
    }

    //endregion EFXStyle Functions

    //region Add Transform Functions
    //*****************************************************************
    // Add Transform Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addFirstTransform(Transform transform) {
        node.getTransforms()
            .addFirst(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addLastTransform(Transform transform) {
        node.getTransforms()
            .addLast(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTransform(Transform transform) {
        node.getTransforms()
            .add(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addTransform(int index, Transform transform) {
        node.getTransforms()
            .add(index, transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAllTransforms(Transform... transforms) {
        node.getTransforms()
            .addAll(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAllTransforms(Collection<? extends Transform> c) {
        node.getTransforms()
            .addAll(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator addAllTransforms(int index, Collection<? extends Transform> c) {
        node.getTransforms()
            .addAll(index, c);
        return this;
    }

    //endregion Add Transform Functions

    //region Remove Transform Functions
    //*****************************************************************
    // Remove Transform Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeFirstTransform() {
        node.getTransforms()
            .removeFirst();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeLastTransform() {
        node.getTransforms()
            .removeLast();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTransform(Transform transform) {
        node.getTransforms()
            .remove(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTransforms(int from, int to) {
        node.getTransforms()
            .remove(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeTransformsIf(Predicate<? super Transform> filter) {
        node.getTransforms()
            .removeIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAllTransforms(Transform... transforms) {
        node.getTransforms()
            .removeAll(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator removeAllTransforms(Collection<? extends Transform> c) {
        node.getTransforms()
            .removeAll(c);
        return this;
    }

    //endregion Remove Transform Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator toBack() {
        node.toBack();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator toFront() {
        node.toFront();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator resize(double width, double height) {
        node.resize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator autosize() {
        node.autosize();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator resizeRelocate(double x, double y, double width, double height) {
        node.resizeRelocate(x, y, width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeConfigurator requestFocus() {
        node.requestFocus();
        return this;
    }

    //endregion Layout Functions
}
