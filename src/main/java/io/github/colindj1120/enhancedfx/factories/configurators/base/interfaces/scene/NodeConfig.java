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
package io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.scene;

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
 * The {@code NodeConfig} interface defines a comprehensive suite of configuration and manipulation methods for {@link Node} objects
 * within JavaFX applications. This interface is designed to facilitate advanced customization, dynamic property binding, event
 * handling, and layout adjustments for nodes, thereby enhancing the flexibility and interactivity of user interfaces.
 *
 * <p>
 * <h2>Key functionalities provided by the {@code NodeConfig} interface include:</h2>
 * <ul>
 *     <li>Dynamic property binding to allow nodes to automatically update in response to changes in observable values.</li>
 *     <li>Event filtering and handling mechanisms to intercept or respond to user interactions and system events.</li>
 *     <li>Layout and visibility adjustments to control the placement, size, and appearance of nodes within the scene graph.</li>
 *     <li>Style management to apply CSS styles dynamically, enabling runtime changes to the node's appearance.</li>
 *     <li>Effect application to enhance nodes visually with drop shadows, blurs, and other graphical effects.</li>
 * </ul>
 * </p>
 *
 * <p>Implementors of {@code NodeConfig} are encouraged to provide thread-safe operations to ensure that node configurations
 * can be safely performed in multi-threaded environments, adhering to JavaFX's threading model.</p>
 *
 * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 * {@link Node} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Node
 * @see ObservableValue
 * @see Event
 * @see Effect
 */
public interface NodeConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a change listener to add when the accessible help property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an invalidation listener to add when the accessible help property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the accessible role description property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an invalidation listener to add when the accessible role description property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the accessible role property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener);

    /**
     * Adds an invalidation listener to add when the accessible role property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the accessible text property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAccessibleTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an invalidation listener to add when the accessible text property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAccessibleTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the blend mode property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener);

    /**
     * Adds an invalidation listener to add when the blend mode property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addBlendModeInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the bounds in local property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener);

    /**
     * Adds an invalidation listener to add when the bounds in local property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the bounds in parent property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener);

    /**
     * Adds an invalidation listener to add when the bounds in parent property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addBoundsInParentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the cache hint property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener);

    /**
     * Adds an invalidation listener to add when the cache hint property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addCacheHintInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the cache property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addCacheChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the cache property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addCacheInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the clip property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addClipChangeListener(ChangeListener<? super Node> changeListener);

    /**
     * Adds an invalidation listener to add when the clip property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addClipInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the cursor property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addCursorChangeListener(ChangeListener<? super Cursor> changeListener);

    /**
     * Adds an invalidation listener to add when the cursor property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addCursorInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the depth test property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener);

    /**
     * Adds an invalidation listener to add when the depth test property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addDepthTestInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the disabled property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addDisabledChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the disabled property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addDisabledInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the disable property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addDisableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the disable property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addDisableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the effective node orientation property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener);

    /**
     * Adds an invalidation listener to add when the effective node orientation property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the effect property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addEffectChangeListener(ChangeListener<? super Effect> changeListener);

    /**
     * Adds an invalidation listener to add when the effect property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addEffectInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the event dispatcher property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener);

    /**
     * Adds an invalidation listener to add when the event dispatcher property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addEventDispatcherInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the focused property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFocusedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the focused property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFocusedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the focus-traversable property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the focus-traversable property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFocusTraversableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the focus visible property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the focus visible property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFocusVisibleInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the focus within property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the focus within property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFocusWithinInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the hover property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addHoverChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the hover property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addHoverInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the id property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addIdChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an invalidation listener to add when the id property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addIdInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the input method requests property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener);

    /**
     * Adds an invalidation listener to add when the input method requests property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the layout bounds property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener);

    /**
     * Adds an invalidation listener to add when the layout bounds property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the layout X property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLayoutXChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the layout X property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLayoutXInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the layout Y property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLayoutYChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to add when the layout Y property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLayoutYInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the local to parent transform property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener);

    /**
     * Adds an invalidation listener to add when the local to parent transform property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the local to scene transform property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener);

    /**
     * Adds an invalidation listener to add when the local to scene transform property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the managed property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addManagedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the managed property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addManagedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the mouseTransparent property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to add when the mouseTransparent property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addMouseTransparentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when the nodeOrientation property changes.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener);

    /**
     * Adds an invalidation listener to add when the nodeOrientation property's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addNodeOrientationInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a context menu requested event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a context menu requested event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a drag detected event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a drag detected event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a drag done event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a drag done event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragDoneInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a drag-dropped event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a drag dropped event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a drag-entered event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a drag entered event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a drag-exited event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a drag exited event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragExitedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a drag over event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a drag over event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnDragOverInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when an input method text changed event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnInputMethodTextChangedChangeListener(
            ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when an input method text changed event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a key pressed event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a key pressed event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a key released event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a key released event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a key typed event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a key typed event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse clicked event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse clicked event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse drag entered event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse drag entered event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse drag exited event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse drag exited event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse dragged event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse dragged event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse drag over event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse drag over event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse drag released event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse drag released event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse-entered event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse entered event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse exited event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse exited event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse-moved event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse moved event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse pressed event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse pressed event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMousePressedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a mouse released event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a mouse released event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a rotate event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a rotate event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnRotateInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a rotation finished event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a rotation-finished event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a rotation-started event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a rotation started event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a scroll finished event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a scroll finished event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a scroll event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a scroll event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnScrollInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to add when a scroll started event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener);

    /**
     * Adds an invalidation listener to add when a scroll started event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a swipe down event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a swipe down event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a swipe left event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a swipe left event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a swipe right event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a swipe right event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a swipe up event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a swipe up event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a touch moved event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a touch moved event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a touch pressed event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a touch pressed event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a touch released event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a touch released event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a touch stationary event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a touch stationary event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a zoom finished event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a zoom finished event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a zoom event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a zoom event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnZoomInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to be notified when a zoom-started event occurs.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener);

    /**
     * Adds an invalidation listener to be notified when a zoom started event's registration changes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener when the opacity of the Node changes.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOpacityChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener when the opacity property of the Node is invalidated.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addOpacityInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener when the parent of the Node changes.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addParentChangeListener(ChangeListener<? super Parent> changeListener);

    /**
     * Adds an invalidation listener when the parent property of the Node is invalidated.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addParentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener when the pickOnBounds property of the Node changes.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener when the pickOnBounds property of the Node is invalidated.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener when the pressed state of the Node changes.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addPressedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener when the pressed property of the Node is invalidated.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addPressedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener when the scene of the Node changes.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addSceneChangeListener(ChangeListener<? super Scene> changeListener);

    /**
     * Adds an invalidation listener when the scene property of the Node is invalidated.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addSceneInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener when the rotation angle of the Node changes.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addRotateChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener when the rotate property of the Node is invalidated.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addRotateInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener when the rotation axis of the Node changes.
     *
     * @param changeListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener);

    /**
     * Adds an invalidation listener when the rotationAxis property of the Node is invalidated.
     *
     * @param invalidationListener
     *         the listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addRotationAxisInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the scaleX property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addScaleXChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to the scaleX property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addScaleXInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the scaleY property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addScaleYChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to the scaleY property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addScaleYInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the scaleZ property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addScaleZChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to the scaleZ property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addScaleZInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the style property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addStyleChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an invalidation listener to the style property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addStyleInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a ListChangeListener to the list of CSS style classes.
     *
     * @param listChangeListener
     *         the ListChangeListener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addStyleListChangeListener(ListChangeListener<? super String> listChangeListener);

    /**
     * Adds an invalidation listener to the list of CSS style classes.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addStyleListInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a ListChangeListener to the list of transforms.
     *
     * @param listChangeListener
     *         the ListChangeListener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener);

    /**
     * Adds an invalidation listener to the list of transforms.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTransformListInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the translateX property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTranslateXChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to the translateX property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTranslateXInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the translateY property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTranslateYChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to the translateY property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTranslateYInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the translateZ property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTranslateZChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to the translateZ property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTranslateZInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the view order property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addViewOrderChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an invalidation listener to the view order property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addViewOrderInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a change listener to the visible property.
     *
     * @param changeListener
     *         the change listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addVisibleChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an invalidation listener to the visible property.
     *
     * @param invalidationListener
     *         the invalidation listener to add
     *
     * @return the current instance for method chaining
     */
    NodeConfig addVisibleInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a change listener from the accessible help property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes an invalidation listener from the accessible help property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the accessible role description property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes an invalidation listener from the accessible role description property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the accessible role property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener);

    /**
     * Removes an invalidation listener from the accessible role property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the accessible text property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes an invalidation listener from the accessible text property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the blend mode property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener);

    /**
     * Removes an invalidation listener from the blend mode property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeBlendModeInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the bounds in local property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener);

    /**
     * Removes an invalidation listener from the bounds in local property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the bounds in parent property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener);

    /**
     * Removes an invalidation listener from the bounds in parent property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the cache hint property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener);

    /**
     * Removes an invalidation listener from the cache hint property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeCacheHintInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the cache property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeCacheChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the cache property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeCacheInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the clip property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeClipChangeListener(ChangeListener<? super Node> changeListener);

    /**
     * Removes an invalidation listener from the clip property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeClipInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the cursor property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeCursorChangeListener(ChangeListener<? super Cursor> changeListener);

    /**
     * Removes an invalidation listener from the cursor property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeCursorInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the depth test property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener);

    /**
     * Removes an invalidation listener from the depth test property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeDepthTestInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the disabled property. Note: This is for components that have a property directly named
     * "disabled".
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the disabled property. Note: This is for components that have a property directly named
     * "disabled".
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeDisabledInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the disabled property. Note: This is typically for the property controlling whether a component
     * is enabled or disabled.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeDisableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the disabled property. Note: This is typically for the property controlling whether a
     * component is enabled or disabled.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeDisableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the effective node orientation property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener);

    /**
     * Removes an invalidation listener from the effective node orientation property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the effect property of the component. This listener is notified whenever the visual effect is
     * applied to the component changes.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeEffectChangeListener(ChangeListener<? super Effect> changeListener);

    /**
     * Removes an invalidation listener from the effect property of the component. This listener is notified whenever the effect
     * property becomes invalid.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeEffectInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the event dispatcher property of the component. This listener is notified of changes to the event
     * dispatcher which handles the routing of events to the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener);

    /**
     * Removes an invalidation listener from the event dispatcher property of the component. This listener is notified whenever the
     * event dispatcher property becomes invalid.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the focused property of the component. This listener is notified whenever the focus state of the
     * component changes, indicating whether it is the current target for keyboard input.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the focused property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFocusedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the focus-traversable property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the focus-traversable property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the focus visible property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the focus visible property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the focus within property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the focus within property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFocusWithinInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the hover property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeHoverChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the hover property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeHoverInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the ID property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeIdChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes an invalidation listener from the ID property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeIdInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the input method requests property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener);

    /**
     * Removes an invalidation listener from the input method requests property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the layout bounds property of the component. This listener is notified of changes to the
     * component's layout bounds.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener);

    /**
     * Removes an invalidation listener from the layout bounds property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the layout X property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLayoutXChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the layout X property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLayoutXInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the layout Y property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLayoutYChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the layout Y property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLayoutYInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the local to parent transform property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener);

    /**
     * Removes an invalidation listener from the local to parent transform property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the local to scene transform property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener);

    /**
     * Removes an invalidation listener from the local to scene transform property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the managed property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeManagedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the managed property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeManagedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the mouse transparent property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the mouse transparent property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the node orientation property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener);

    /**
     * Removes an invalidation listener from the node orientation property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onContextMenuRequested event handler property of the component.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnContextMenuRequestedChangeListener(
            ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onContextMenuRequested event handler property of the component.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onDragDetected event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onDragDetected event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onDragDone event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onDragDone event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onDragDropped event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onDragDropped event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onDragEntered event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onDragEntered event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onDragExited event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onDragExited event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onDragOver event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onDragOver event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnDragOverInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onInputMethodTextChanged event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnInputMethodTextChangedChangeListener(
            ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onInputMethodTextChanged event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onKeyPressed event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onKeyPressed event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onKeyReleased event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onKeyReleased event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onKeyTyped event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onKeyTyped event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseClicked event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseClicked event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseDragEntered event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseDragEntered event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseDragExited event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseDragExited event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseDragged event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseDragged event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseDragOver event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseDragOver event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseDragReleased event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseDragReleased event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseEntered event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseEntered event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseExited event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseExited event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseMoved event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseMoved event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMousePressed event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMousePressed event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onMouseReleased event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onMouseReleased event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onRotate event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onRotate event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnRotateInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onRotationFinished event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onRotationFinished event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onRotationStarted event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onRotationStarted event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onScroll event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onScroll event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnScrollInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onScrollStarted event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onScrollStarted event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onScrollFinished event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onScrollFinished event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onSwipeDown event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onSwipeDown event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onSwipeLeft event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onSwipeLeft event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onSwipeRight event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onSwipeRight event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onSwipeUp event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onSwipeUp event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onTouchMoved event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onTouchMoved event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onTouchPressed event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onTouchPressed event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onTouchReleased event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onTouchReleased event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onTouchStationary event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onTouchStationary event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onZoom event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onZoom event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnZoomInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onZoomStarted event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onZoomStarted event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the onZoomFinished event handler property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener);

    /**
     * Removes an invalidation listener from the onZoomFinished event handler property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the opacity property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOpacityChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the opacity property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeOpacityInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the parent property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeParentChangeListener(ChangeListener<? super Parent> changeListener);

    /**
     * Removes an invalidation listener from the parent property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeParentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the pick on bounds property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the pick on bounds property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the pressed property. This property indicates whether the component is currently being pressed.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removePressedChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the pressed property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removePressedInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the scene property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeSceneChangeListener(ChangeListener<? super Scene> changeListener);

    /**
     * Removes an invalidation listener from the scene property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeSceneInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the rotate property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeRotateChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the rotate property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeRotateInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the rotation axis property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener);

    /**
     * Removes an invalidation listener from the rotation axis property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeRotationAxisInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the scaleX property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeScaleXChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the scaleX property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeScaleXInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the scaleY property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeScaleYChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the scaleY property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeScaleYInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the scaleZ property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeScaleZChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the scaleZ property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeScaleZInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the style property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeStyleChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes an invalidation listener from the style property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeStyleInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a ListChangeListener from the list of CSS style classes.
     *
     * @param listChangeListener
     *         the ListChangeListener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener);

    /**
     * Removes an invalidation listener from the list of CSS style classes.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeStyleListInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a ListChangeListener from the list of transforms.
     *
     * @param listChangeListener
     *         the ListChangeListener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener);

    /**
     * Removes an invalidation listener from the list of transforms.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTransformListInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the translateX property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTranslateXChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the translateX property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTranslateXInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the translateY property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTranslateYChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the translateY property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTranslateYInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the translateZ property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTranslateZChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the translateZ property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTranslateZInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the view order property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeViewOrderChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an invalidation listener from the view order property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeViewOrderInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a change listener from the visible property.
     *
     * @param changeListener
     *         the change listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an invalidation listener from the visible property.
     *
     * @param invalidationListener
     *         the invalidation listener to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeVisibleInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the accessible help text for the component.
     *
     * @param value
     *         the accessible help text to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setAccessibleHelp(String value);

    /**
     * Sets the accessible role for the component.
     *
     * @param value
     *         the {@link AccessibleRole} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setAccessibleRole(AccessibleRole value);

    /**
     * Sets the description of the accessible role for the component.
     *
     * @param value
     *         the accessible role description to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setAccessibleRoleDescription(String value);

    /**
     * Sets the accessible text for the component.
     *
     * @param value
     *         the accessible text to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setAccessibleText(String value);

    /**
     * Sets the blend mode for the component.
     *
     * @param value
     *         the {@link BlendMode} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setBlendMode(BlendMode value);

    /**
     * Sets the cache state for the component. When true, caching is enabled.
     *
     * @param value
     *         the cache state to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setCache(boolean value);

    /**
     * Sets the cache hint for the component, suggesting how caching should be used.
     *
     * @param value
     *         the {@link CacheHint} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setCacheHint(CacheHint value);

    /**
     * Sets the clipping node for the component.
     *
     * @param value
     *         the {@link Node} to use for clipping
     *
     * @return the current instance for method chaining
     */
    NodeConfig setClip(Node value);

    /**
     * Sets the cursor for the component.
     *
     * @param value
     *         the {@link Cursor} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setCursor(Cursor value);

    /**
     * Sets the depth test mode for the component.
     *
     * @param value
     *         the {@link DepthTest} mode to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setDepthTest(DepthTest value);

    /**
     * Sets the disabled state of the component.
     *
     * @param value
     *         the disabled state to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setDisable(boolean value);

    /**
     * Sets the visual effect for the component.
     *
     * @param value
     *         the {@link Effect} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setEffect(Effect value);

    /**
     * Sets the event dispatcher for the component.
     *
     * @param value
     *         the {@link EventDispatcher} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setEventDispatcher(EventDispatcher value);

    /**
     * Sets the focus-traversable state of the component.
     *
     * @param value
     *         the focus traversable state to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setFocusTraversable(boolean value);

    /**
     * Sets the ID of the component.
     *
     * @param value
     *         the ID to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setId(String value);

    /**
     * Sets the input method requests handler for the component.
     *
     * @param value
     *         the {@link InputMethodRequests} handler to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setInputMethodRequests(InputMethodRequests value);

    /**
     * Sets the layout X position of the component.
     *
     * @param value
     *         the X position to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setLayoutX(double value);

    /**
     * Sets the layout Y position of the component.
     *
     * @param value
     *         the Y position to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setLayoutY(double value);

    /**
     * Sets the managed state of the component. When true, the component is managed by its parent.
     *
     * @param value
     *         the managed state to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setManaged(boolean value);

    /**
     * Sets whether the component is mouse transparent. If true, mouse events will pass through the component.
     *
     * @param value
     *         the mouse transparency state to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setMouseTransparent(boolean value);

    /**
     * Sets the node orientation for the component, determining the direction (left-to-right or right-to-left) in which the component
     * is laid out.
     *
     * @param value
     *         the {@link NodeOrientation} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setNodeOrientation(NodeOrientation value);

    /**
     * Sets the event handler for context menu requests on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link ContextMenuEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value);

    /**
     * Sets the event handler for drag detection on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnDragDetected(EventHandler<? super MouseEvent> value);

    /**
     * Sets the event handler for the completion of a drag operation on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link DragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnDragDone(EventHandler<? super DragEvent> value);

    /**
     * Sets the event handler for drag drop events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link DragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnDragDropped(EventHandler<? super DragEvent> value);

    /**
     * Sets the event handler for drag-entered events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link DragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnDragEntered(EventHandler<? super DragEvent> value);

    /**
     * Sets the event handler for drag exited events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link DragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnDragExited(EventHandler<? super DragEvent> value);

    /**
     * Sets the event handler for drag over events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link DragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnDragOver(EventHandler<? super DragEvent> value);

    /**
     * Sets the event handler for input method text changes on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link InputMethodEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value);

    /**
     * Sets the event handler for key pressed events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link KeyEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnKeyPressed(EventHandler<? super KeyEvent> value);

    /**
     * Sets the event handler for key typed events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link KeyEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnKeyTyped(EventHandler<? super KeyEvent> value);

    /**
     * Sets the event handler for mouse clicked events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseClicked(EventHandler<? super MouseEvent> value);

    /**
     * Sets the event handler for mouse drag entered events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseDragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value);

    /**
     * Sets the event handler for mouse drag exited events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseDragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseDragExited(EventHandler<? super MouseDragEvent> value);

    /**
     * Sets the event handler for mouse drag over events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseDragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseDragOver(EventHandler<? super MouseDragEvent> value);

    /**
     * Sets the event handler for mouse drag released events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseDragEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value);

    /**
     * Sets the event handler for mouse-entered events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseEntered(EventHandler<? super MouseEvent> value);

    /**
     * Sets the event handler for mouse-exited events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseExited(EventHandler<? super MouseEvent> value);

    /**
     * Sets the event handler for mouse moved events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseMoved(EventHandler<? super MouseEvent> value);

    /**
     * Sets the event handler for mouse pressed events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMousePressed(EventHandler<? super MouseEvent> value);

    /**
     * Sets the event handler for mouse-released events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link MouseEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnMouseReleased(EventHandler<? super MouseEvent> value);

    /**
     * Sets the event handler for rotation events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link RotateEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnRotate(EventHandler<? super RotateEvent> value);

    /**
     * Sets the event handler for rotation finished events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link RotateEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnRotationFinished(EventHandler<? super RotateEvent> value);

    /**
     * Sets the event handler for rotation started events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link RotateEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnRotationStarted(EventHandler<? super RotateEvent> value);

    /**
     * Sets the event handler for scroll events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link ScrollEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnScroll(EventHandler<? super ScrollEvent> value);

    /**
     * Sets the event handler for scroll finished events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link ScrollEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnScrollFinished(EventHandler<? super ScrollEvent> value);

    /**
     * Sets the event handler for scroll started events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link ScrollEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnScrollStarted(EventHandler<? super ScrollEvent> value);

    /**
     * Sets the opacity of the component.
     *
     * @param value
     *         the opacity value to set, ranging from 0.0 (fully transparent) to 1.0 (fully opaque)
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOpacity(double value);

    /**
     * Sets the CSS style string for the component.
     *
     * @param style
     *         the CSS style string to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setStyle(String style);

    /**
     * Sets whether the bounds of the component are considered for mouse picking.
     *
     * @param value
     *         true if the bounds should be considered for mouse picking, false otherwise
     *
     * @return the current instance for method chaining
     */
    NodeConfig setPickOnBounds(boolean value);

    /**
     * Sets the event handler for swipe down gestures on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link SwipeEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnSwipeDown(EventHandler<? super SwipeEvent> value);

    /**
     * Sets the event handler for swipe-left gestures on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link SwipeEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnSwipeLeft(EventHandler<? super SwipeEvent> value);

    /**
     * Sets the event handler for swipe right gestures on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link SwipeEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnSwipeRight(EventHandler<? super SwipeEvent> value);

    /**
     * Sets the event handler for swipe up gestures on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link SwipeEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnSwipeUp(EventHandler<? super SwipeEvent> value);

    /**
     * Sets the event handler for touch moved events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link TouchEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnTouchMoved(EventHandler<? super TouchEvent> value);

    /**
     * Sets the event handler for touch pressed events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link TouchEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnTouchPressed(EventHandler<? super TouchEvent> value);

    /**
     * Sets the event handler for touch released events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link TouchEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnTouchReleased(EventHandler<? super TouchEvent> value);

    /**
     * Sets the event handler for touch stationary events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link TouchEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnTouchStationary(EventHandler<? super TouchEvent> value);

    /**
     * Sets the event handler for zoom gestures on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link ZoomEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnZoom(EventHandler<? super ZoomEvent> value);

    /**
     * Sets the event handler for zoom finished events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link ZoomEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnZoomFinished(EventHandler<? super ZoomEvent> value);

    /**
     * Sets the event handler for zoom started events on the component.
     *
     * @param value
     *         the {@link EventHandler} for {@link ZoomEvent} to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setOnZoomStarted(EventHandler<? super ZoomEvent> value);

    /**
     * Sets the rotation angle of the component around its Z-axis.
     *
     * @param value
     *         the rotation angle in degrees
     *
     * @return the current instance for method chaining
     */
    NodeConfig setRotate(double value);

    /**
     * Sets the axis of rotation for the component.
     *
     * @param value
     *         the {@link Point3D} axis to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setRotationAxis(Point3D value);

    /**
     * Sets the scale factor of the component along the X-axis.
     *
     * @param value
     *         the scale factor to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setScaleX(double value);

    /**
     * Sets the scale factor of the component along the Y-axis.
     *
     * @param value
     *         the scale factor to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setScaleY(double value);

    /**
     * Sets the scale factor of the component along the Z-axis.
     *
     * @param value
     *         the scale factor to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setScaleZ(double value);

    /**
     * Sets the translation of the component along the X-axis.
     *
     * @param value
     *         the translation distance to set along the X-axis
     *
     * @return the current instance for method chaining
     */
    NodeConfig setTranslateX(double value);

    /**
     * Sets the translation of the component along the Y-axis.
     *
     * @param value
     *         the translation distance to set along the Y-axis
     *
     * @return the current instance for method chaining
     */
    NodeConfig setTranslateY(double value);

    /**
     * Sets the translation of the component along the Z-axis.
     *
     * @param value
     *         the translation distance to set along the Z-axis
     *
     * @return the current instance for method chaining
     */
    NodeConfig setTranslateZ(double value);

    /**
     * Sets the user data associated with the component. This can be any object used to store application-specific data.
     *
     * @param value
     *         the user data object to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setUserData(Object value);

    /**
     * Sets the view order of the component. A lower view order renders the component in front of components with a higher view order.
     *
     * @param value
     *         the view order value to set
     *
     * @return the current instance for method chaining
     */
    NodeConfig setViewOrder(double value);

    /**
     * Sets the visibility of the component. When set to false, the component will not be rendered.
     *
     * @param value
     *         true to make the component visible; false to make it invisible
     *
     * @return the current instance for method chaining
     */
    NodeConfig setVisible(boolean value);

    //endregion Set Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // AccessibleHelpProperty

    /**
     * Binds the accessible help property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the accessible help property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the accessible help property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindAccessibleHelpProperty();

    /**
     * Establishes a bidirectional binding between the accessible help property of the component and another property.
     *
     * @param property
     *         the property to bind with the accessible help property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalAccessibleHelpProperty(Property<String> property);

    /**
     * Removes a bidirectional binding between the accessible help property of the component and another property.
     *
     * @param property
     *         the property to unbind from the accessible help property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalAccessibleHelpProperty(Property<String> property);

    // AccessibleRoleDescriptionProperty

    /**
     * Binds the accessible role description property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the accessible role description property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the accessible role description property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindAccessibleRoleDescriptionProperty();

    /**
     * Establishes a bidirectional binding between the accessible role description property of the component and another property.
     *
     * @param property
     *         the property to bind with the accessible role description property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property);

    /**
     * Removes a bidirectional binding between the accessible role description property of the component and another property.
     *
     * @param property
     *         the property to unbind from the accessible role description property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property);

    // AccessibleRoleProperty

    /**
     * Binds the accessible role property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the accessible role property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue);

    /**
     * Unbinds the accessible role property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindAccessibleRoleProperty();

    /**
     * Establishes a bidirectional binding between the accessible role property of the component and another property.
     *
     * @param property
     *         the property to bind with the accessible role property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property);

    /**
     * Removes a bidirectional binding between the accessible role property of the component and another property.
     *
     * @param property
     *         the property to unbind from the accessible role property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property);

    // AccessibleTextProperty

    /**
     * Binds the accessible text property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the accessible text property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindAccessibleTextProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the accessible text property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindAccessibleTextProperty();

    /**
     * Establishes a bidirectional binding between the accessible text property of the component and another property.
     *
     * @param property
     *         the property to bind with the accessible text property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalAccessibleTextProperty(Property<String> property);

    /**
     * Removes a bidirectional binding between the accessible text property of the component and another property.
     *
     * @param property
     *         the property to unbind from the accessible text property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalAccessibleTextProperty(Property<String> property);

    // BlendModeProperty

    /**
     * Binds the blend mode property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the blend mode property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue);

    /**
     * Unbinds the blend mode property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBlendModeProperty();

    /**
     * Establishes a bidirectional binding between the blend mode property of the component and another property.
     *
     * @param property
     *         the property to bind with the blend mode property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalBlendModeProperty(Property<BlendMode> property);

    /**
     * Removes a bidirectional binding between the blend mode property of the component and another property.
     *
     * @param property
     *         the property to unbind from the blend mode property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalBlendModeProperty(Property<BlendMode> property);

    // CacheHintProperty

    /**
     * Binds the cache hint property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the cache hint property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue);

    /**
     * Unbinds the cache hint property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindCacheHintProperty();

    /**
     * Establishes a bidirectional binding between the cache hint property of the component and another property.
     *
     * @param property
     *         the property to bind with the cache hint property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalCacheHintProperty(Property<CacheHint> property);

    /**
     * Removes a bidirectional binding between the cache hint property of the component and another property.
     *
     * @param property
     *         the property to unbind from the cache hint property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalCacheHintProperty(Property<CacheHint> property);

    // CacheProperty

    /**
     * Binds the cache property of the component to another observable value indicating whether caching is enabled.
     *
     * @param observableValue
     *         the observable value to bind to the cache property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindCacheProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the cache property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindCacheProperty();

    /**
     * Establishes a bidirectional binding between the cache property of the component and another property indicating whether caching
     * is enabled.
     *
     * @param property
     *         the property to bind with the cache property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalCacheProperty(Property<Boolean> property);

    /**
     * Removes a bidirectional binding between the cache property of the component and another property.
     *
     * @param property
     *         the property to unbind from the cache property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalCacheProperty(Property<Boolean> property);

    // ClipProperty

    /**
     * Binds the clip property of the component to another observable value representing a clipping node.
     *
     * @param observableValue
     *         the observable value to bind to the clip property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindClipProperty(ObservableValue<? extends Node> observableValue);

    /**
     * Unbinds the clip property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindClipProperty();

    /**
     * Establishes a bidirectional binding between the clip property of the component and another property representing a clipping
     * node.
     *
     * @param property
     *         the property to bind with the clip property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalClipProperty(Property<Node> property);

    /**
     * Removes a bidirectional binding between the clip property of the component and another property.
     *
     * @param property
     *         the property to unbind from the clip property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalClipProperty(Property<Node> property);

    // CursorProperty

    /**
     * Binds the cursor property of the component to another observable value indicating the cursor type.
     *
     * @param observableValue
     *         the observable value to bind to the cursor property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindCursorProperty(ObservableValue<? extends Cursor> observableValue);

    /**
     * Unbinds the cursor property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindCursorProperty();

    /**
     * Establishes a bidirectional binding between the cursor property of the component and another property indicating the cursor
     * type.
     *
     * @param property
     *         the property to bind with the cursor property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalCursorProperty(Property<Cursor> property);

    /**
     * Removes a bidirectional binding between the cursor property of the component and another property.
     *
     * @param property
     *         the property to unbind from the cursor property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalCursorProperty(Property<Cursor> property);

    // DepthTestProperty

    /**
     * Binds the depth test property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the depth test property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue);

    /**
     * Unbinds the depth test property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindDepthTestProperty();

    /**
     * Establishes a bidirectional binding between the depth test property of the component and another property.
     *
     * @param property
     *         the property to bind with the depth test property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalDepthTestProperty(Property<DepthTest> property);

    /**
     * Removes a bidirectional binding between the depth test property of the component and another property.
     *
     * @param property
     *         the property to unbind from the depth test property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalDepthTestProperty(Property<DepthTest> property);

    // DisableProperty

    /**
     * Binds the disabled property of the component to another observable value indicating whether the component is disabled.
     *
     * @param observableValue
     *         the observable value to bind to the disabled property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindDisableProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the disabled property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindDisableProperty();

    /**
     * Establishes a bidirectional binding between the disabled property of the component and another property.
     *
     * @param property
     *         the property to bind with the disabled property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalDisableProperty(Property<Boolean> property);

    /**
     * Removes a bidirectional binding between the disabled property of the component and another property.
     *
     * @param property
     *         the property to unbind from the disabled property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalDisableProperty(Property<Boolean> property);

    // EffectProperty

    /**
     * Binds the effect property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the effect property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindEffectProperty(ObservableValue<? extends Effect> observableValue);

    /**
     * Unbinds the effect property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindEffectProperty();

    /**
     * Establishes a bidirectional binding between the effect property of the component and another property.
     *
     * @param property
     *         the property to bind with the effect property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalEffectProperty(Property<Effect> property);

    /**
     * Removes a bidirectional binding between the effect property of the component and another property.
     *
     * @param property
     *         the property to unbind from the effect property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalEffectProperty(Property<Effect> property);

    // EventDispatcherProperty

    /**
     * Binds the event dispatcher property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the event dispatcher property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue);

    /**
     * Unbinds the event dispatcher property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindEventDispatcherProperty();

    /**
     * Establishes a bidirectional binding between the event dispatcher property of the component and another property.
     *
     * @param property
     *         the property to bind with the event dispatcher property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property);

    /**
     * Removes a bidirectional binding between the event dispatcher property of the component and another property.
     *
     * @param property
     *         the property to unbind from the event dispatcher property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property);

    // FocusTraversableProperty

    /**
     * Binds the focus-traversable property of the component to another observable value indicating whether the component can gain
     * focus.
     *
     * @param observableValue
     *         the observable value to bind to the focus-traversable property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the focus-traversable property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindFocusTraversableProperty();

    /**
     * Establishes a bidirectional binding between the focus-traversable property of the component and another property.
     *
     * @param property
     *         the property to bind with the focus-traversable property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalFocusTraversableProperty(Property<Boolean> property);

    /**
     * Removes a bidirectional binding between the focus-traversable property of the component and another property.
     *
     * @param property
     *         the property to unbind from the focus-traversable property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalFocusTraversableProperty(Property<Boolean> property);

    // IdProperty

    /**
     * Binds the ID property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the ID property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindIdProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the ID property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindIdProperty();

    /**
     * Establishes a bidirectional binding between the ID property of the component and another property.
     *
     * @param property
     *         the property to bind with the ID property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalIdProperty(Property<String> property);

    /**
     * Removes a bidirectional binding between the ID property of the component and another property.
     *
     * @param property
     *         the property to unbind from the ID property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalIdProperty(Property<String> property);

    // InputMethodRequestsProperty

    /**
     * Binds the input method requests property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the input method requests property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue);

    /**
     * Unbinds the input method requests property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindInputMethodRequestsProperty();

    /**
     * Establishes a bidirectional binding between the input method requests property of the component and another property.
     *
     * @param property
     *         the property to bind with the input method requests property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property);

    /**
     * Removes a bidirectional binding between the input method requests property of the component and another property.
     *
     * @param property
     *         the property to unbind from the input method requests property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property);

    // LayoutXProperty

    /**
     * Binds the layout X property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the layout X property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindLayoutXProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the layout X property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindLayoutXProperty();

    /**
     * Establishes a bidirectional binding between the layout X property of the component and another property.
     *
     * @param property
     *         the property to bind with the layout X property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalLayoutXProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the layout X property of the component and another property.
     *
     * @param property
     *         the property to unbind from the layout X property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalLayoutXProperty(Property<Number> property);

    // LayoutYProperty

    /**
     * Binds the layout Y property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the layout Y property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindLayoutYProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the layout Y property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindLayoutYProperty();

    /**
     * Establishes a bidirectional binding between the layout Y property of the component and another property.
     *
     * @param property
     *         the property to bind with the layout Y property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalLayoutYProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the layout Y property of the component and another property.
     *
     * @param property
     *         the property to unbind from the layout Y property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalLayoutYProperty(Property<Number> property);

    // ManagedProperty

    /**
     * Binds the managed property of the component to another observable value indicating whether the component is managed.
     *
     * @param observableValue
     *         the observable value to bind to the managed property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindManagedProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the managed property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindManagedProperty();

    /**
     * Establishes a bidirectional binding between the managed property of the component and another property.
     *
     * @param property
     *         the property to bind with the managed property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalManagedProperty(Property<Boolean> property);

    /**
     * Removes a bidirectional binding between the managed property of the component and another property.
     *
     * @param property
     *         the property to unbind from the managed property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalManagedProperty(Property<Boolean> property);

    // MouseTransparentProperty

    /**
     * Binds the mouse transparent property of the component to another observable value indicating whether the component is mouse
     * transparent.
     *
     * @param observableValue
     *         the observable value to bind to the mouse transparent property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the mouse transparent property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindMouseTransparentProperty();

    /**
     * Establishes a bidirectional binding between the mouse transparent property of the component and another property.
     *
     * @param property
     *         the property to bind with the mouse transparent property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalMouseTransparentProperty(Property<Boolean> property);

    /**
     * Removes a bidirectional binding between the mouse transparent property of the component and another property.
     *
     * @param property
     *         the property to unbind from the mouse transparent property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalMouseTransparentProperty(Property<Boolean> property);

    // NodeOrientationProperty

    /**
     * Binds the node orientation property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the node orientation property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue);

    /**
     * Unbinds the node orientation property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindNodeOrientationProperty();

    /**
     * Establishes a bidirectional binding between the node orientation property of the component and another property.
     *
     * @param property
     *         the property to bind with the node orientation property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property);

    /**
     * Removes a bidirectional binding between the node orientation property of the component and another property.
     *
     * @param property
     *         the property to unbind from the node orientation property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property);

    // OnContextMenuRequestedProperty

    /**
     * Binds the onContextMenuRequested event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onContextMenuRequested event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnContextMenuRequestedProperty(ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue);

    /**
     * Unbinds the onContextMenuRequested event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnContextMenuRequestedProperty();

    /**
     * Establishes a bidirectional binding between the onContextMenuRequested event handler property of the component and another
     * property.
     *
     * @param property
     *         the property to bind with the onContextMenuRequested event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property);

    /**
     * Removes a bidirectional binding between the onContextMenuRequested event handler property of the component and another
     * property.
     *
     * @param property
     *         the property to unbind from the onContextMenuRequested event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property);

    // OnDragDetectedProperty

    /**
     * Binds the onDragDetected event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onDragDetected event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue);

    /**
     * Unbinds the onDragDetected event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnDragDetectedProperty();

    /**
     * Establishes a bidirectional binding between the onDragDetected event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onDragDetected event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property);

    /**
     * Removes a bidirectional binding between the onDragDetected event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onDragDetected event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property);

    // OnDragDoneProperty

    /**
     * Binds the onDragDone event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onDragDone event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue);

    /**
     * Unbinds the onDragDone event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnDragDoneProperty();

    /**
     * Establishes a bidirectional binding between the onDragDone event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onDragDone event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property);

    /**
     * Removes a bidirectional binding between the onDragDone event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onDragDone event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property);

    // OnDragDroppedProperty

    /**
     * Binds the onDragDropped event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onDragDropped event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue);

    /**
     * Unbinds the onDragDropped event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnDragDroppedProperty();

    /**
     * Establishes a bidirectional binding between the onDragDropped event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onDragDropped event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property);

    /**
     * Removes a bidirectional binding between the onDragDropped event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onDragDropped event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property);

    // OnDragEnteredProperty

    /**
     * Binds the onDragEntered event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onDragEntered event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue);

    /**
     * Unbinds the onDragEntered event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnDragEnteredProperty();

    /**
     * Establishes a bidirectional binding between the onDragEntered event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onDragEntered event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property);

    /**
     * Removes a bidirectional binding between the onDragEntered event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onDragEntered event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property);

    // OnDragExitedProperty

    /**
     * Binds the onDragExited event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onDragExited event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue);

    /**
     * Unbinds the onDragExited event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnDragExitedProperty();

    /**
     * Establishes a bidirectional binding between the onDragExited event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onDragExited event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property);

    /**
     * Removes a bidirectional binding between the onDragExited event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onDragExited event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property);

    // OnDragOverProperty

    /**
     * Binds the onDragOver event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onDragOver event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue);

    /**
     * Unbinds the onDragOver event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnDragOverProperty();

    /**
     * Establishes a bidirectional binding between the onDragOver event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onDragOver event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property);

    /**
     * Removes a bidirectional binding between the onDragOver event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onDragOver event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property);

    // OnInputMethodTextChangedProperty

    /**
     * Binds the onInputMethodTextChanged event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onInputMethodTextChanged event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnInputMethodTextChangedProperty(ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue);

    /**
     * Unbinds the onInputMethodTextChanged event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnInputMethodTextChangedProperty();

    /**
     * Establishes a bidirectional binding between the onInputMethodTextChanged event handler property of the component and another
     * property.
     *
     * @param property
     *         the property to bind with the onInputMethodTextChanged event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property);

    /**
     * Removes a bidirectional binding between the onInputMethodTextChanged event handler property of the component and another
     * property.
     *
     * @param property
     *         the property to unbind from the onInputMethodTextChanged event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property);

    // OnKeyPressedProperty

    /**
     * Binds the onKeyPressed event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onKeyPressed event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue);

    /**
     * Unbinds the onKeyPressed event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnKeyPressedProperty();

    /**
     * Establishes a bidirectional binding between the onKeyPressed event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onKeyPressed event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property);

    /**
     * Removes a bidirectional binding between the onKeyPressed event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onKeyPressed event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property);

    // OnKeyReleasedProperty

    /**
     * Binds the onKeyReleased event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onKeyReleased event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue);

    /**
     * Unbinds the onKeyReleased event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnKeyReleasedProperty();

    /**
     * Establishes a bidirectional binding between the onKeyReleased event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onKeyReleased event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property);

    /**
     * Removes a bidirectional binding between the onKeyReleased event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onKeyReleased event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property);

    // OnKeyTypedProperty

    /**
     * Binds the onKeyTyped event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onKeyTyped event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue);

    /**
     * Unbinds the onKeyTyped event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnKeyTypedProperty();

    /**
     * Establishes a bidirectional binding between the onKeyTyped event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onKeyTyped event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property);

    /**
     * Removes a bidirectional binding between the onKeyTyped event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onKeyTyped event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property);

    // OnMouseClickedProperty

    /**
     * Binds the onMouseClicked event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseClicked event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue);

    /**
     * Unbinds the onMouseClicked event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseClickedProperty();

    /**
     * Establishes a bidirectional binding between the onMouseClicked event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMouseClicked event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseClicked event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseClicked event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property);

    // OnMouseDragEnteredProperty

    /**
     * Binds the onMouseDragEntered event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseDragEntered event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseDragEnteredProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue);

    /**
     * Unbinds the onMouseDragEntered event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseDragEnteredProperty();

    /**
     * Establishes a bidirectional binding between the onMouseDragEntered event handler property of the component and another
     * property.
     *
     * @param property
     *         the property to bind with the onMouseDragEntered event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseDragEntered event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseDragEntered event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property);

    // OnMouseDragExitedProperty

    /**
     * Binds the onMouseDragExited event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseDragExited event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseDragExitedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue);

    /**
     * Unbinds the onMouseDragExited event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseDragExitedProperty();

    /**
     * Establishes a bidirectional binding between the onMouseDragExited event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMouseDragExited event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseDragExited event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseDragExited event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property);

    // OnMouseDraggedProperty

    /**
     * Binds the onMouseDragged event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseDragged event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue);

    /**
     * Unbinds the onMouseDragged event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseDraggedProperty();

    /**
     * Establishes a bidirectional binding between the onMouseDragged event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMouseDragged event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseDragged event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseDragged event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property);

    // OnMouseDragOverProperty

    /**
     * Binds the onMouseDragOver event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseDragOver event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseDragOverProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue);

    /**
     * Unbinds the onMouseDragOver event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseDragOverProperty();

    /**
     * Establishes a bidirectional binding between the onMouseDragOver event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMouseDragOver event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseDragOver event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseDragOver event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property);

    // OnMouseDragReleasedProperty

    /**
     * Binds the onMouseDragReleased event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseDragReleased event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseDragReleasedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue);

    /**
     * Unbinds the onMouseDragReleased event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseDragReleasedProperty();

    /**
     * Establishes a bidirectional binding between the onMouseDragReleased event handler property of the component and another
     * property.
     *
     * @param property
     *         the property to bind with the onMouseDragReleased event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseDragReleased event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseDragReleased event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property);

    // OnMouseEnteredProperty

    /**
     * Binds the onMouseEntered event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseEntered event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue);

    /**
     * Unbinds the onMouseEntered event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseEnteredProperty();

    /**
     * Establishes a bidirectional binding between the onMouseEntered event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMouseEntered event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseEntered event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseEntered event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property);

    // OnMouseExitedProperty

    /**
     * Binds the onMouseExited event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseExited event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue);

    /**
     * Unbinds the onMouseExited event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseExitedProperty();

    /**
     * Establishes a bidirectional binding between the onMouseExited event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMouseExited event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseExited event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseExited event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property);

    // OnMouseMovedProperty

    /**
     * Binds the onMouseMoved event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseMoved event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue);

    /**
     * Unbinds the onMouseMoved event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseMovedProperty();

    /**
     * Establishes a bidirectional binding between the onMouseMoved event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMouseMoved event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseMoved event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseMoved event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property);

    // OnMousePressedProperty

    /**
     * Binds the onMousePressed event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMousePressed event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue);

    /**
     * Unbinds the onMousePressed event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMousePressedProperty();

    /**
     * Establishes a bidirectional binding between the onMousePressed event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMousePressed event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property);

    /**
     * Removes a bidirectional binding between the onMousePressed event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMousePressed event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property);

    // OnMouseReleasedProperty

    /**
     * Binds the onMouseReleased event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onMouseReleased event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue);

    /**
     * Unbinds the onMouseReleased event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnMouseReleasedProperty();

    /**
     * Establishes a bidirectional binding between the onMouseReleased event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onMouseReleased event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property);

    /**
     * Removes a bidirectional binding between the onMouseReleased event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onMouseReleased event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property);

    // OnRotateProperty

    /**
     * Binds the onRotate event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onRotate event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue);

    /**
     * Unbinds the onRotate event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnRotateProperty();

    /**
     * Establishes a bidirectional binding between the onRotate event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onRotate event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property);

    /**
     * Removes a bidirectional binding between the onRotate event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onRotate event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property);

    // OnRotationFinishedProperty

    /**
     * Binds the onRotationFinished event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onRotationFinished event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnRotationFinishedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue);

    /**
     * Unbinds the onRotationFinished event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnRotationFinishedProperty();

    /**
     * Establishes a bidirectional binding between the onRotationFinished event handler property of the component and another
     * property.
     *
     * @param property
     *         the property to bind with the onRotationFinished event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property);

    /**
     * Removes a bidirectional binding between the onRotationFinished event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onRotationFinished event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property);

    // OnRotationStartedProperty

    /**
     * Binds the onRotationStarted event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onRotationStarted event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnRotationStartedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue);

    /**
     * Unbinds the onRotationStarted event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnRotationStartedProperty();

    /**
     * Establishes a bidirectional binding between the onRotationStarted event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onRotationStarted event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property);

    /**
     * Removes a bidirectional binding between the onRotationStarted event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onRotationStarted event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property);

    // OnScrollProperty

    /**
     * Binds the onScroll event handler property of the component to another observable value. This event occurs when a scroll gesture
     * is detected.
     *
     * @param observableValue
     *         the observable value to bind to the onScroll event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue);

    /**
     * Unbinds the onScroll event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnScrollProperty();

    /**
     * Establishes a bidirectional binding between the onScroll event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onScroll event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property);

    /**
     * Removes a bidirectional binding between the onScroll event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onScroll event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property);

    // OnScrollFinishedProperty

    /**
     * Binds the onScrollFinished event handler property of the component to another observable value. This event occurs when a scroll
     * gesture has finished.
     *
     * @param observableValue
     *         the observable value to bind to the onScrollFinished event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnScrollFinishedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue);

    /**
     * Unbinds the onScrollFinished event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnScrollFinishedProperty();

    /**
     * Establishes a bidirectional binding between the onScrollFinished event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onScrollFinished event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property);

    /**
     * Removes a bidirectional binding between the onScrollFinished event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onScrollFinished event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property);

    // OnScrollStartedProperty

    /**
     * Binds the onScrollStarted event handler property of the component to another observable value. This event occurs when a scroll
     * gesture is started.
     *
     * @param observableValue
     *         the observable value to bind to the onScrollStarted event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue);

    /**
     * Unbinds the onScrollStarted event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnScrollStartedProperty();

    /**
     * Establishes a bidirectional binding between the onScrollStarted event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onScrollStarted event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property);

    /**
     * Removes a bidirectional binding between the onScrollStarted event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onScrollStarted event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property);

    // OnSwipeDownProperty

    /**
     * Binds the onSwipeDown event handler property of the component to another observable value. This event occurs when a swipe down
     * gesture is detected.
     *
     * @param observableValue
     *         the observable value to bind to the onSwipeDown event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue);

    /**
     * Unbinds the onSwipeDown event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnSwipeDownProperty();

    /**
     * Establishes a bidirectional binding between the onSwipeDown event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onSwipeDown event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property);

    /**
     * Removes a bidirectional binding between the onSwipeDown event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onSwipeDown event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property);

    // OnSwipeLeftProperty

    /**
     * Binds the onSwipeLeft event handler property of the component to another observable value. This event occurs when a swipe left
     * gesture is detected.
     *
     * @param observableValue
     *         the observable value to bind to the onSwipeLeft event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue);

    /**
     * Unbinds the onSwipeLeft event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnSwipeLeftProperty();

    /**
     * Establishes a bidirectional binding between the onSwipeLeft event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onSwipeLeft event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property);

    /**
     * Removes a bidirectional binding between the onSwipeLeft event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onSwipeLeft event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property);

    // OnSwipeRightProperty

    /**
     * Binds the onSwipeRight event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onSwipeRight event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue);

    /**
     * Unbinds the onSwipeRight event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnSwipeRightProperty();

    /**
     * Establishes a bidirectional binding between the onSwipeRight event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onSwipeRight event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property);

    /**
     * Removes a bidirectional binding between the onSwipeRight event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onSwipeRight event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property);

    // OnSwipeUpProperty

    /**
     * Binds the onSwipeUp event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onSwipeUp event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue);

    /**
     * Unbinds the onSwipeUp event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnSwipeUpProperty();

    /**
     * Establishes a bidirectional binding between the onSwipeUp event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onSwipeUp event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property);

    /**
     * Removes a bidirectional binding between the onSwipeUp event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onSwipeUp event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property);

    // OnTouchMovedProperty

    /**
     * Binds the onTouchMoved event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onTouchMoved event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue);

    /**
     * Unbinds the onTouchMoved event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnTouchMovedProperty();

    /**
     * Establishes a bidirectional binding between the onTouchMoved event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onTouchMoved event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property);

    /**
     * Removes a bidirectional binding between the onTouchMoved event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onTouchMoved event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property);

    // OnTouchPressedProperty

    /**
     * Binds the onTouchPressed event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onTouchPressed event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue);

    /**
     * Unbinds the onTouchPressed event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnTouchPressedProperty();

    /**
     * Establishes a bidirectional binding between the onTouchPressed event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onTouchPressed event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property);

    /**
     * Removes a bidirectional binding between the onTouchPressed event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onTouchPressed event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property);

    // OnTouchReleasedProperty

    /**
     * Binds the onTouchReleased event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onTouchReleased event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue);

    /**
     * Unbinds the onTouchReleased event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnTouchReleasedProperty();

    /**
     * Establishes a bidirectional binding between the onTouchReleased event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onTouchReleased event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property);

    /**
     * Removes a bidirectional binding between the onTouchReleased event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onTouchReleased event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property);

    // OnTouchStationaryProperty

    /**
     * Binds the onTouchStationary event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onTouchStationary event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnTouchStationaryProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue);

    /**
     * Unbinds the onTouchStationary event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnTouchStationaryProperty();

    /**
     * Establishes a bidirectional binding between the onTouchStationary event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onTouchStationary event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property);

    /**
     * Removes a bidirectional binding between the onTouchStationary event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onTouchStationary event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property);

    // OnZoomProperty

    /**
     * Binds the onZoom event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onZoom event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue);

    /**
     * Unbinds the onZoom event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnZoomProperty();

    /**
     * Establishes a bidirectional binding between the onZoom event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onZoom event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property);

    /**
     * Removes a bidirectional binding between the onZoom event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onZoom event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property);

    // OnZoomFinishedProperty

    /**
     * Binds the onZoomFinished event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onZoomFinished event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue);

    /**
     * Unbinds the onZoomFinished event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnZoomFinishedProperty();

    /**
     * Establishes a bidirectional binding between the onZoomFinished event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onZoomFinished event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property);

    /**
     * Removes a bidirectional binding between the onZoomFinished event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onZoomFinished event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property);

    // OnZoomStartedProperty

    /**
     * Binds the onZoomStarted event handler property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the onZoomStarted event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue);

    /**
     * Unbinds the onZoomStarted event handler property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOnZoomStartedProperty();

    /**
     * Establishes a bidirectional binding between the onZoomStarted event handler property of the component and another property.
     *
     * @param property
     *         the property to bind with the onZoomStarted event handler property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property);

    /**
     * Removes a bidirectional binding between the onZoomStarted event handler property of the component and another property.
     *
     * @param property
     *         the property to unbind from the onZoomStarted event handler property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property);

    // OpacityProperty

    /**
     * Binds the opacity property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the opacity property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindOpacityProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the opacity property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindOpacityProperty();

    /**
     * Establishes a bidirectional binding between the opacity property of the component and another property.
     *
     * @param property
     *         the property to bind with the opacity property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalOpacityProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the opacity property of the component and another property.
     *
     * @param property
     *         the property to unbind from the opacity property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalOpacityProperty(Property<Number> property);

    // PickOnBoundsProperty

    /**
     * Binds the pickOnBounds property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the pickOnBounds property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the pickOnBounds property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindPickOnBoundsProperty();

    /**
     * Establishes a bidirectional binding between the pickOnBounds property of the component and another property.
     *
     * @param property
     *         the property to bind with the pickOnBounds property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalPickOnBoundsProperty(Property<Boolean> property);

    /**
     * Removes a bidirectional binding between the pickOnBounds property of the component and another property.
     *
     * @param property
     *         the property to unbind from the pickOnBounds property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalPickOnBoundsProperty(Property<Boolean> property);

    // RotateProperty

    /**
     * Binds the rotate property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the rotate property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindRotateProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the rotate property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindRotateProperty();

    /**
     * Establishes a bidirectional binding between the rotate property of the component and another property.
     *
     * @param property
     *         the property to bind with the rotate property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalRotateProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the rotate property of the component and another property.
     *
     * @param property
     *         the property to unbind from the rotate property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalRotateProperty(Property<Number> property);

    // RotationAxisProperty

    /**
     * Binds the rotationAxis property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the rotationAxis property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue);

    /**
     * Unbinds the rotationAxis property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindRotationAxisProperty();

    /**
     * Establishes a bidirectional binding between the rotationAxis property of the component and another property.
     *
     * @param property
     *         the property to bind with the rotationAxis property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalRotationAxisProperty(Property<Point3D> property);

    /**
     * Removes a bidirectional binding between the rotationAxis property of the component and another property.
     *
     * @param property
     *         the property to unbind from the rotationAxis property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalRotationAxisProperty(Property<Point3D> property);

    // ScaleXProperty

    /**
     * Binds the scaleX property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the scaleX property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindScaleXProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the scaleX property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindScaleXProperty();

    /**
     * Establishes a bidirectional binding between the scaleX property of the component and another property.
     *
     * @param property
     *         the property to bind with the scaleX property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalScaleXProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the scaleX property of the component and another property.
     *
     * @param property
     *         the property to unbind from the scaleX property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalScaleXProperty(Property<Number> property);

    // ScaleYProperty

    /**
     * Binds the scaleY property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the scaleY property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindScaleYProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the scaleY property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindScaleYProperty();

    /**
     * Establishes a bidirectional binding between the scaleY property of the component and another property.
     *
     * @param property
     *         the property to bind with the scaleY property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalScaleYProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the scaleY property of the component and another property.
     *
     * @param property
     *         the property to unbind from the scaleY property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalScaleYProperty(Property<Number> property);

    // ScaleZProperty

    /**
     * Binds the scaleZ property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the scaleZ property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindScaleZProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the scaleZ property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindScaleZProperty();

    /**
     * Establishes a bidirectional binding between the scaleZ property of the component and another property.
     *
     * @param property
     *         the property to bind with the scaleZ property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalScaleZProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the scaleZ property of the component and another property.
     *
     * @param property
     *         the property to unbind from the scaleZ property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalScaleZProperty(Property<Number> property);

    // StyleProperty

    /**
     * Binds the style property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the style property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindStyleProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the style property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindStyleProperty();

    /**
     * Establishes a bidirectional binding between the style property of the component and another property.
     *
     * @param property
     *         the property to bind with the style property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalStyleProperty(Property<String> property);

    /**
     * Removes a bidirectional binding between the style property of the component and another property.
     *
     * @param property
     *         the property to unbind from the style property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalStyleProperty(Property<String> property);

    // TranslateXProperty

    /**
     * Binds the translateX property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the translateX property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindTranslateXProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the translateX property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindTranslateXProperty();

    /**
     * Establishes a bidirectional binding between the translateX property of the component and another property.
     *
     * @param property
     *         the property to bind with the translateX property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalTranslateXProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the translateX property of the component and another property.
     *
     * @param property
     *         the property to unbind from the translateX property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalTranslateXProperty(Property<Number> property);

    // TranslateYProperty

    /**
     * Binds the translateY property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the translateY property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindTranslateYProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the translateY property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindTranslateYProperty();

    /**
     * Establishes a bidirectional binding between the translateY property of the component and another property.
     *
     * @param property
     *         the property to bind with the translateY property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalTranslateYProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the translateY property of the component and another property.
     *
     * @param property
     *         the property to unbind from the translateY property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalTranslateYProperty(Property<Number> property);

    // TranslateZProperty

    /**
     * Binds the translateZ property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the translateZ property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindTranslateZProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the translateZ property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindTranslateZProperty();

    /**
     * Establishes a bidirectional binding between the translateZ property of the component and another property.
     *
     * @param property
     *         the property to bind with the translateZ property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalTranslateZProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the translateZ property of the component and another property.
     *
     * @param property
     *         the property to unbind from the translateZ property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalTranslateZProperty(Property<Number> property);

    // ViewOrderProperty

    /**
     * Binds the viewOrder property of the component to another observable value.
     *
     * @param observableValue
     *         the observable value to bind to the viewOrder property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindViewOrderProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the viewOrder property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindViewOrderProperty();

    /**
     * Establishes a bidirectional binding between the viewOrder property of the component and another property.
     *
     * @param property
     *         the property to bind with the viewOrder property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalViewOrderProperty(Property<Number> property);

    /**
     * Removes a bidirectional binding between the viewOrder property of the component and another property.
     *
     * @param property
     *         the property to unbind from the viewOrder property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalViewOrderProperty(Property<Number> property);

    // VisibleProperty

    /**
     * Binds the visible property of the component to another observable value indicating whether the component is visible.
     *
     * @param observableValue
     *         the observable value to bind to the visible property
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindVisibleProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the visible property of the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindVisibleProperty();

    /**
     * Establishes a bidirectional binding between the visible property of the component and another property.
     *
     * @param property
     *         the property to bind with the visible property bidirectionally
     *
     * @return the current instance for method chaining
     */
    NodeConfig bindBidirectionalVisibleProperty(Property<Boolean> property);

    /**
     * Removes a bidirectional binding between the visible property of the component and another property.
     *
     * @param property
     *         the property to unbind from the visible property
     *
     * @return the current instance for method chaining
     */
    NodeConfig unbindBidirectionalVisibleProperty(Property<Boolean> property);

    //endregion Binding Functions

    //region Event Functions
    //*****************************************************************
    // Event Functions
    //*****************************************************************

    /**
     * Fires an {@link Event} on this component. This method allows the component to programmatically dispatch an event.
     *
     * @param event
     *         the {@code Event} to fire
     *
     * @return the current instance for method chaining
     */
    NodeConfig fireEvent(Event event);

    /**
     * Adds an {@link EventHandler} as an event filter to the component. Event filters get the opportunity to process events before
     * event handlers. If an event filter consumes an event, it will not propagate further to event handlers.
     *
     * @param <S>
     *         the specific {@code Event} subclass
     * @param eventType
     *         the type of the event to filter
     * @param eventFilter
     *         the filter to apply to the event
     *
     * @return the current instance for method chaining
     */
    <S extends Event> NodeConfig addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter);

    /**
     * Adds an {@link EventHandler} to the component. Event handlers are notified after event filters and can process the event.
     *
     * @param <S>
     *         the specific {@code Event} subclass
     * @param eventType
     *         the type of the event to handle
     * @param eventHandler
     *         the handler for the event
     *
     * @return the current instance for method chaining
     */
    <S extends Event> NodeConfig addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler);

    /**
     * Removes an {@link EventHandler} from the set of event filters for a specific event type. This allows for dynamic modification of
     * event processing behavior.
     *
     * @param <S>
     *         the specific {@code Event} subclass
     * @param eventType
     *         the type of the event
     * @param eventFilter
     *         the event filter to remove
     *
     * @return the current instance for method chaining
     */
    <S extends Event> NodeConfig removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter);

    /**
     * Removes an {@link EventHandler} from the set of event handlers for a specific event type. This enables changing how events are
     * processed at runtime.
     *
     * @param <S>
     *         the specific {@code Event} subclass
     * @param eventType
     *         the type of the event
     * @param eventHandler
     *         the event handler to remove
     *
     * @return the current instance for method chaining
     */
    <S extends Event> NodeConfig removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler);

    //endregion Event Functions

    //region Add Style Functions
    //*****************************************************************
    // Add Style Functions
    //*****************************************************************

    /**
     * Adds a style class to the beginning of the component's style class list.
     *
     * @param styleClass
     *         the style class to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFirstStyleClass(String styleClass);

    /**
     * Adds a style class to the end of the component's style class list.
     *
     * @param styleClass
     *         the style class to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLastStyleClass(String styleClass);

    /**
     * Adds a style class to the component's style class list.
     *
     * @param styleClass
     *         the style class to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addStyleClass(String styleClass);

    /**
     * Inserts a style class at the specified position in the component's style class list.
     *
     * @param index
     *         the position at which the style class should be inserted
     * @param styleClass
     *         the style class to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addStyleClass(int index, String styleClass);

    /**
     * Adds multiple style classes to the component's style class list.
     *
     * @param styleClasses
     *         an array of style classes to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAllStyleClasses(String... styleClasses);

    /**
     * Adds all the style classes in the specified collection to the component's style class list.
     *
     * @param c
     *         the collection of style classes to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAllStyleClasses(Collection<? extends String> c);

    /**
     * Adds all the style classes in the specified collection to the component's style class list at the given index.
     *
     * @param index
     *         the position at which the first style class from the collection should be inserted
     * @param c
     *         the collection of style classes to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAllStyleClasses(int index, Collection<? extends String> c);

    //endregion Add Style Functions

    //region Remove Style Functions
    //*****************************************************************
    // Remove Style Functions
    //*****************************************************************

    /**
     * Removes the first style class applied to the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFirstStyleClass();

    /**
     * Removes the last style class applied to the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLastStyleClass();

    /**
     * Removes a specific style class from the component.
     *
     * @param styleClass
     *         the name of the style class to be removed
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeStyleClass(String styleClass);

    /**
     * Removes a range of style classes applied to the component based on their index positions.
     *
     * @param from
     *         the starting index of the range to remove (inclusive)
     * @param to
     *         the ending index of the range to remove (exclusive)
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeStyleClasses(int from, int to);

    /**
     * Removes style classes that match the given predicate.
     *
     * @param filter
     *         a {@link Predicate} specifying which style classes to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeStyleClassesIf(Predicate<? super String> filter);

    /**
     * Removes all instances of the specified style classes from the component.
     *
     * @param styleClasses
     *         an array of style class names to be removed
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAllStyleClasses(String... styleClasses);

    /**
     * Removes all the style classes present in the specified collection from the component.
     *
     * @param c
     *         a {@link Collection} containing names of style classes to be removed
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAllStyleClasses(Collection<? extends String> c);

    //endregion Remove Style Functions

    //region Style Functions
    //*****************************************************************
    // Style Functions
    //*****************************************************************

    /**
     * Changes the pseudo-class state of the component.
     *
     * @param pseudoClass
     *         the {@link PseudoClass} to be manipulated
     * @param active
     *         the new state of the pseudo-class (true to activate, false to deactivate)
     *
     * @return the current instance for method chaining
     */
    NodeConfig pseudoClassStateChange(PseudoClass pseudoClass, boolean active);

    /**
     * Triggers the application of CSS to this component. This method is useful when manual control over the timing of CSS application
     * is needed, such as after adding or modifying style classes.
     *
     * @return the current instance for method chaining
     */
    NodeConfig applyCss();

    //endregion Style Functions

    //region Add Transform Functions
    //*****************************************************************
    // Add Transform Functions
    //*****************************************************************

    /**
     * Adds a {@link Transform} to the beginning of the component's transform list.
     *
     * @param transform
     *         the {@link Transform} to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addFirstTransform(Transform transform);

    /**
     * Adds a {@link Transform} to the end of the component's transform list.
     *
     * @param transform
     *         the {@link Transform} to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addLastTransform(Transform transform);

    /**
     * Adds a {@link Transform} to the component's transform list. The new transform is added in the default position.
     *
     * @param transform
     *         the {@link Transform} to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTransform(Transform transform);

    /**
     * Inserts a {@link Transform} at the specified position in the component's transform list.
     *
     * @param index
     *         the position at which the transform should be inserted
     * @param transform
     *         the {@link Transform} to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addTransform(int index, Transform transform);

    /**
     * Adds multiple {@link Transform} objects to the component's transform list.
     *
     * @param transforms
     *         an array of {@link Transform} objects to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAllTransforms(Transform... transforms);

    /**
     * Adds all of the {@link Transform} objects in the specified collection to the component's transform list.
     *
     * @param c
     *         the collection of {@link Transform} objects to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAllTransforms(Collection<? extends Transform> c);

    /**
     * Adds all of the {@link Transform} objects in the specified collection to the component's transform list at the given index.
     *
     * @param index
     *         the position at which the first transform from the collection should be inserted
     * @param c
     *         the collection of {@link Transform} objects to be added
     *
     * @return the current instance for method chaining
     */
    NodeConfig addAllTransforms(int index, Collection<? extends Transform> c);

    //endregion Add Transform Functions

    //region Remove Transform Functions
    //*****************************************************************
    // Remove Transform Functions
    //*****************************************************************

    /**
     * Removes the first transformation applied to the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeFirstTransform();

    /**
     * Removes the last transformation applied to the component.
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeLastTransform();

    /**
     * Removes a specific transformation from the component.
     *
     * @param transform
     *         the {@link Transform} to be removed
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTransform(Transform transform);

    /**
     * Removes a range of transformations applied to the component.
     *
     * @param from
     *         the starting index of the range to remove (inclusive)
     * @param to
     *         the ending index of the range to remove (exclusive)
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTransforms(int from, int to);

    /**
     * Removes transformations that match the given predicate.
     *
     * @param filter
     *         a {@link Predicate} specifying which {@link Transform}s to remove
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeTransformsIf(Predicate<? super Transform> filter);

    /**
     * Removes all instances of the specified transformations from the component.
     *
     * @param transforms
     *         an array of {@link Transform} objects to be removed
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAllTransforms(Transform... transforms);

    /**
     * Removes all the transformations present in the specified collection from the component.
     *
     * @param c
     *         a {@link Collection} containing {@link Transform} objects to be removed
     *
     * @return the current instance for method chaining
     */
    NodeConfig removeAllTransforms(Collection<? extends Transform> c);

    //endregion Remove Transform Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    /**
     * Sends the control to the back of its sibling nodes in the scene graph.
     *
     * @return the current instance for method chaining
     */
    NodeConfig toBack();

    /**
     * Brings the control to the front of its sibling nodes in the scene graph.
     *
     * @return the current instance for method chaining
     */
    NodeConfig toFront();

    /**
     * Resizes the control to the specified width and height.
     *
     * @param width
     *         the new width to resize to
     * @param height
     *         the new height to resize to
     *
     * @return the current instance for method chaining
     */
    NodeConfig resize(double width, double height);

    /**
     * Invokes the control's built-in mechanism to compute its own preferred size.
     *
     * @return the current instance for method chaining
     */
    NodeConfig autosize();

    /**
     * Moves and resizes the control to the specified x, y coordinates and dimensions.
     *
     * @param x
     *         the new x-coordinate of the control
     * @param y
     *         the new y-coordinate of the control
     * @param width
     *         the new width to resize to
     * @param height
     *         the new height to resize to
     *
     * @return the current instance for method chaining
     */
    NodeConfig resizeRelocate(double x, double y, double width, double height);

    /**
     * Requests that the control gains the input focus.
     *
     * @return the current instance for method chaining
     */
    NodeConfig requestFocus();

    //endregion Layout Functions
}
