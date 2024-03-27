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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.node.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.BaseConfig;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
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
import java.util.function.UnaryOperator;

/**
 * NodeConfig is a highly flexible and powerful interface designed to configure various aspects of nodes in a JavaFX application.
 *
 * <p>It extends {@link BaseConfig} and provides methods to manipulate and observe properties of nodes such as accessibility attributes, blend mode, bounds, cache settings, clip, cursor, depth test, disable
 * state, effect, event dispatcher, focus traversable, hover, ID, input method requests, layout, local to parent/scene transforms, managed state, mouse transparency, node orientation, opacity, parent, pick on
 * bounds, pressed, rotate, scale, style, transform, translate, visibility, and view order. Additionally, it offers capabilities to handle events and manipulate style and transform lists.</p>
 *
 * <h2>Capabilities include:</h2>
 * <ul>
 *     <li>Adding and removing change and invalidation listeners for various properties.</li>
 *     <li>Binding and unbinding properties to and from observable values and other properties, with support for bidirectional bindings.</li>
 *     <li>Adding and removing event filters and handlers for different types of events.</li>
 *     <li>Setting properties directly for accessibility attributes, blend mode, cache settings, clip, cursor, depth test, disable state, effect, event dispatcher, focus traversable, ID, input method
 *     requests, layout, local to parent/scene transforms, managed state, mouse transparency, node orientation, opacity, parent, pick on bounds, pressed, rotate, scale, style, transform, translate,
 *     visibility, and view order.</li>
 *     <li>Adding, removing, and manipulating style and transform lists.</li>
 *     <li>Applying CSS, managing pseudo-class state changes, and performing layout actions like toFront, toBack, resize, autosize, resizeRelocate, and requestFocus.</li>
 * </ul>
 *
 * <h2>Usage example:</h2>
 * <pre>{@code
 * NodeConfig<MyNode> config = ...; // Obtain an implementation instance
 * config.setAccessibleText("Node description")
 *       .addAccessibleRoleChangeListener((observable, oldValue, newValue) -> ...)
 *       .setBlendMode(BlendMode.MULTIPLY)
 *       .setOnMouseClicked(event -> System.out.println("Node clicked!"))
 *       .toFront();
 * }</pre>
 *
 * <p>This interface empowers developers to create more interactive, accessible, and visually appealing JavaFX applications by providing an extensive set of methods for configuring and handling node
 * properties and events.
 *
 * @param <T>
 *         The type parameter extending {@link ConfiguratorBase}, ensuring that implementations can chain method calls within the confines of their specific type.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see BaseConfig
 * @see ConfiguratorBase
 * @see Node
 */
@SuppressWarnings("UnusedReturnValue")
public interface NodeConfig<T extends ConfiguratorBase> extends BaseConfig<T> {
    /*
     * Methods Available:
     * Add Listener Functions
     *  - T addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener)
     *  - T addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener)
     *  - T addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener)
     *  - T addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener)
     *  - T addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener)
     *  - T addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener)
     *  - T addAccessibleTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T addAccessibleTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener)
     *  - T addBlendModeInvalidationListener(InvalidationListener invalidationListener)
     *  - T addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener)
     *  - T addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener)
     *  - T addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener)
     *  - T addBoundsInParentInvalidationListener(InvalidationListener invalidationListener)
     *  - T addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener)
     *  - T addCacheHintInvalidationListener(InvalidationListener invalidationListener)
     *  - T addCacheChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addCacheInvalidationListener(InvalidationListener invalidationListener)
     *  - T addClipChangeListener(ChangeListener<? super Node> changeListener)
     *  - T addClipInvalidationListener(InvalidationListener invalidationListener)
     *  - T addCursorChangeListener(ChangeListener<? super Cursor> changeListener)
     *  - T addCursorInvalidationListener(InvalidationListener invalidationListener)
     *  - T addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener)
     *  - T addDepthTestInvalidationListener(InvalidationListener invalidationListener)
     *  - T addDisabledChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addDisabledInvalidationListener(InvalidationListener invalidationListener)
     *  - T addDisableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addDisableInvalidationListener(InvalidationListener invalidationListener)
     *  - T addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener)
     *  - T addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener)
     *  - T addEffectChangeListener(ChangeListener<? super Effect> changeListener)
     *  - T addEffectInvalidationListener(InvalidationListener invalidationListener)
     *  - T addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener)
     *  - T addEventDispatcherInvalidationListener(InvalidationListener invalidationListener)
     *  - T addFocusedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addFocusedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addFocusTraversableInvalidationListener(InvalidationListener invalidationListener)
     *  - T addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addFocusVisibleInvalidationListener(InvalidationListener invalidationListener)
     *  - T addHoverChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addHoverInvalidationListener(InvalidationListener invalidationListener)
     *  - T addIdChangeListener(ChangeListener<? super String> changeListener)
     *  - T addIdInvalidationListener(InvalidationListener invalidationListener)
     *  - T addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener)
     *  - T addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener)
     *  - T addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener)
     *  - T addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener)
     *  - T addLayoutXChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addLayoutXInvalidationListener(InvalidationListener invalidationListener)
     *  - T addLayoutYChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addLayoutYInvalidationListener(InvalidationListener invalidationListener)
     *  - T addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener)
     *  - T addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener)
     *  - T addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener)
     *  - T addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener)
     *  - T addManagedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addManagedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addMouseTransparentInvalidationListener(InvalidationListener invalidationListener)
     *  - T addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener)
     *  - T addNodeOrientationInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener)
     *  - T addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T addOnDragDoneInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T addOnDragExitedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T addOnDragOverInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener)
     *  - T addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener)
     *  - T addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener)
     *  - T addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener)
     *  - T addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener)
     *  - T addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener)
     *  - T addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener)
     *  - T addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener)
     *  - T addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T addOnMousePressedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener)
     *  - T addOnRotateInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener)
     *  - T addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener)
     *  - T addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener)
     *  - T addOnScrollInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener)
     *  - T addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener)
     *  - T addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener)
     *  - T addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener)
     *  - T addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener)
     *  - T addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener)
     *  - T addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener)
     *  - T addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener)
     *  - T addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener)
     *  - T addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener)
     *  - T addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener)
     *  - T addOnZoomInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener)
     *  - T addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener)
     *  - T addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addOpacityChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addOpacityInvalidationListener(InvalidationListener invalidationListener)
     *  - T addParentChangeListener(ChangeListener<? super Parent> changeListener)
     *  - T addParentInvalidationListener(InvalidationListener invalidationListener)
     *  - T addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener)
     *  - T addPressedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addPressedInvalidationListener(InvalidationListener invalidationListener)
     *  - T addSceneChangeListener(ChangeListener<? super Scene> changeListener)
     *  - T addSceneInvalidationListener(InvalidationListener invalidationListener)
     *  - T addRotateChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addRotateInvalidationListener(InvalidationListener invalidationListener)
     *  - T addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener)
     *  - T addRotationAxisInvalidationListener(InvalidationListener invalidationListener)
     *  - T addScaleXChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addScaleXInvalidationListener(InvalidationListener invalidationListener)
     *  - T addScaleYChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addScaleYInvalidationListener(InvalidationListener invalidationListener)
     *  - T addScaleZChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addScaleZInvalidationListener(InvalidationListener invalidationListener)
     *  - T addStyleChangeListener(ChangeListener<? super String> changeListener)
     *  - T addStyleInvalidationListener(InvalidationListener invalidationListener)
     *  - T addStyleListChangeListener(ListChangeListener<? super String> listChangeListener)
     *  - T addStyleListInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener)
     *  - T addTransformListInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTranslateXChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addTranslateXInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTranslateYChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addTranslateYInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTranslateZChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addTranslateZInvalidationListener(InvalidationListener invalidationListener)
     *  - T addViewOrderChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addViewOrderInvalidationListener(InvalidationListener invalidationListener)
     *  - T addVisibleChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addVisibleInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener)
     *  - T removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener)
     *  - T removeBlendModeInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener)
     *  - T removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener)
     *  - T removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener)
     *  - T removeCacheHintInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeCacheChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeCacheInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeClipChangeListener(ChangeListener<? super Node> changeListener)
     *  - T removeClipInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeCursorChangeListener(ChangeListener<? super Cursor> changeListener)
     *  - T removeCursorInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener)
     *  - T removeDepthTestInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeDisabledInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeDisableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeDisableInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener)
     *  - T removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeEffectChangeListener(ChangeListener<? super Effect> changeListener)
     *  - T removeEffectInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener)
     *  - T removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeFocusedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeHoverChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeHoverInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeIdChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeIdInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener)
     *  - T removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener)
     *  - T removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeLayoutXChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeLayoutXInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeLayoutYChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeLayoutYInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener)
     *  - T removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener)
     *  - T removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeManagedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeManagedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener)
     *  - T removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener)
     *  - T removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener)
     *  - T removeOnDragOverInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener)
     *  - T removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener)
     *  - T removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener)
     *  - T removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener)
     *  - T removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener)
     *  - T removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener)
     *  - T removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T removeOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener)
     *  - T removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener)
     *  - T removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener)
     *  - T removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener)
     *  - T removeOnRotateInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener)
     *  - T removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener)
     *  - T removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener)
     *  - T removeOnScrollInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener)
     *  - T removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener)
     *  - T removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener)
     *  - T removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener)
     *  - T removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener)
     *  - T removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener)
     *  - T removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener)
     *  - T removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener)
     *  - T removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener)
     *  - T removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener)
     *  - T removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener)
     *  - T removeOnZoomInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener)
     *  - T removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener)
     *  - T removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeOpacityChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeOpacityInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeParentChangeListener(ChangeListener<? super Parent> changeListener)
     *  - T removeParentInvalidationListener(InvalidationListener invalidationListener)
     *  - T removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener)
     *  - T removePressedChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removePressedInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeSceneChangeListener(ChangeListener<? super Scene> changeListener)
     *  - T removeSceneInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeRotateChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeRotateInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener)
     *  - T removeRotationAxisInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeScaleXChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeScaleXInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeScaleYChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeScaleYInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeScaleZChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeScaleZInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeStyleChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeStyleInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener)
     *  - T removeStyleListInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener)
     *  - T removeTransformListInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTranslateXChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeTranslateXInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTranslateYChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeTranslateYInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTranslateZChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeTranslateZInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeViewOrderChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeViewOrderInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeVisibleInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindAccessibleHelpProperty()
     *  - T bindBidirectionalAccessibleHelpProperty(Property<String> otherProperty)
     *  - T unbindBidirectionalAccessibleHelpProperty(Property<String> otherProperty)
     *  - T bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindAccessibleRoleDescriptionProperty()
     *  - T bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> otherProperty)
     *  - T unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> otherProperty)
     *  - T bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue)
     *  - T unbindAccessibleRoleProperty()
     *  - T bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> otherProperty)
     *  - T unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> otherProperty)
     *  - T bindAccessibleTextProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindAccessibleTextProperty()
     *  - T bindBidirectionalAccessibleTextProperty(Property<String> otherProperty)
     *  - T unbindBidirectionalAccessibleTextProperty(Property<String> otherProperty)
     *  - T bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue)
     *  - T unbindBlendModeProperty()
     *  - T bindBidirectionalBlendModeProperty(Property<BlendMode> otherProperty)
     *  - T unbindBidirectionalBlendModeProperty(Property<BlendMode> otherProperty)
     *  - T bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue)
     *  - T unbindCacheHintProperty()
     *  - T bindBidirectionalCacheHintProperty(Property<CacheHint> otherProperty)
     *  - T unbindBidirectionalCacheHintProperty(Property<CacheHint> otherProperty)
     *  - T bindCacheProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindCacheProperty()
     *  - T bindBidirectionalCacheProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalCacheProperty(Property<Boolean> otherProperty)
     *  - T bindClipProperty(ObservableValue<? extends Node> observableValue)
     *  - T unbindClipProperty()
     *  - T bindBidirectionalClipProperty(Property<Node> otherProperty)
     *  - T unbindBidirectionalClipProperty(Property<Node> otherProperty)
     *  - T bindCursorProperty(ObservableValue<? extends Cursor> observableValue)
     *  - T unbindCursorProperty()
     *  - T bindBidirectionalCursorProperty(Property<Cursor> otherProperty)
     *  - T unbindBidirectionalCursorProperty(Property<Cursor> otherProperty)
     *  - T bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue)
     *  - T unbindDepthTestProperty()
     *  - T bindBidirectionalDepthTestProperty(Property<DepthTest> otherProperty)
     *  - T unbindBidirectionalDepthTestProperty(Property<DepthTest> otherProperty)
     *  - T bindDisableProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindDisableProperty()
     *  - T bindBidirectionalDisableProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalDisableProperty(Property<Boolean> otherProperty)
     *  - T bindEffectProperty(ObservableValue<? extends Effect> observableValue)
     *  - T unbindEffectProperty()
     *  - T bindBidirectionalEffectProperty(Property<Effect> otherProperty)
     *  - T unbindBidirectionalEffectProperty(Property<Effect> otherProperty)
     *  - T bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue)
     *  - T unbindEventDispatcherProperty()
     *  - T bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> otherProperty)
     *  - T unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> otherProperty)
     *  - T bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindFocusTraversableProperty()
     *  - T bindBidirectionalFocusTraversableProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalFocusTraversableProperty(Property<Boolean> otherProperty)
     *  - T bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindMouseTransparentProperty()
     *  - T bindBidirectionalMouseTransparentProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalMouseTransparentProperty(Property<Boolean> otherProperty)
     *  - T bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue)
     *  - T unbindNodeOrientationProperty()
     *  - T bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> otherProperty)
     *  - T unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> otherProperty)
     *  - T bindIdProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindIdProperty()
     *  - T bindBidirectionalIdProperty(Property<String> otherProperty)
     *  - T unbindBidirectionalIdProperty(Property<String> otherProperty)
     *  - T bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue)
     *  - T unbindInputMethodRequestsProperty()
     *  - T bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> otherProperty)
     *  - T unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> otherProperty)
     *  - T bindLayoutXProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindLayoutXProperty()
     *  - T bindBidirectionalLayoutXProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalLayoutXProperty(Property<Number> otherProperty)
     *  - T bindLayoutYProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindLayoutYProperty()
     *  - T bindBidirectionalLayoutYProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalLayoutYProperty(Property<Number> otherProperty)
     *  - T bindManagedProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindManagedProperty()
     *  - T bindBidirectionalManagedProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalManagedProperty(Property<Boolean> otherProperty)
     *  - T bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindMouseTransparentProperty()
     *  - T bindBidirectionalMouseTransparentProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalMouseTransparentProperty(Property<Boolean> otherProperty)
     *  - T bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue)
     *  - T unbindNodeOrientationProperty()
     *  - T bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> otherProperty)
     *  - T unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> otherProperty)
     *  - T bindOnContextMenuRequestedProperty(ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue)
     *  - T unbindOnContextMenuRequestedProperty()
     *  - T bindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> otherProperty)
     *  - T unbindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> otherProperty)
     *  - T bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue)
     *  - T unbindOnDragDetectedProperty()
     *  - T bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue)
     *  - T unbindOnDragDoneProperty()
     *  - T bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue)
     *  - T unbindOnDragDroppedProperty()
     *  - T bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue)
     *  - T unbindOnDragEnteredProperty()
     *  - T bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue)
     *  - T unbindOnDragExitedProperty()
     *  - T bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue)
     *  - T unbindOnDragOverProperty()
     *  - T bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> otherProperty)
     *  - T bindOnInputMethodTextChangedProperty(ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue)
     *  - T unbindOnInputMethodTextChangedProperty()
     *  - T bindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> otherProperty)
     *  - T unbindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> otherProperty)
     *  - T bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue)
     *  - T unbindOnKeyPressedProperty()
     *  - T bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> otherProperty)
     *  - T unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> otherProperty)
     *  - T bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue)
     *  - T unbindOnKeyReleasedProperty()
     *  - T bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> otherProperty)
     *  - T unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> otherProperty)
     *  - T bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue)
     *  - T unbindOnKeyTypedProperty()
     *  - T bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> otherProperty)
     *  - T unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> otherProperty)
     *  - T bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue)
     *  - T unbindOnMouseClickedProperty()
     *  - T bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T bindOnMouseDragEnteredProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue)
     *  - T unbindOnMouseDragEnteredProperty()
     *  - T bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty)
     *  - T bindOnMouseDragExitedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue)
     *  - T unbindOnMouseDragExitedProperty()
     *  - T bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty)
     *  - T bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue)
     *  - T unbindOnMouseDraggedProperty()
     *  - T bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T bindOnMouseDragOverProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue)
     *  - T unbindOnMouseDragOverProperty()
     *  - T bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty)
     *  - T bindOnMouseDragReleasedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue)
     *  - T unbindOnMouseDragReleasedProperty()
     *  - T bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty)
     *  - T bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue)
     *  - T unbindOnMouseEnteredProperty()
     *  - T bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue)
     *  - T unbindOnMouseExitedProperty()
     *  - T bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue)
     *  - T unbindOnMouseMovedProperty()
     *  - T bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue)
     *  - T unbindOnMousePressedProperty()
     *  - T bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue)
     *  - T unbindOnMouseReleasedProperty()
     *  - T bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> otherProperty)
     *  - T bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue)
     *  - T unbindOnRotateProperty()
     *  - T bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> otherProperty)
     *  - T unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> otherProperty)
     *  - T bindOnRotationFinishedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue)
     *  - T unbindOnRotationFinishedProperty()
     *  - T bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> otherProperty)
     *  - T unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> otherProperty)
     *  - T bindOnRotationStartedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue)
     *  - T unbindOnRotationStartedProperty()
     *  - T bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> otherProperty)
     *  - T unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> otherProperty)
     *  - T bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue)
     *  - T unbindOnScrollProperty()
     *  - T bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> otherProperty)
     *  - T unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> otherProperty)
     *  - T bindOnScrollFinishedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue)
     *  - T unbindOnScrollFinishedProperty()
     *  - T bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty)
     *  - T unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty)
     *  - T bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue)
     *  - T unbindOnScrollStartedProperty()
     *  - T bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty)
     *  - T unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty)
     *  - T bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue)
     *  - T unbindOnSwipeDownProperty()
     *  - T bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> otherProperty)
     *  - T unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> otherProperty)
     *  - T bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue)
     *  - T unbindOnSwipeLeftProperty()
     *  - T bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> otherProperty)
     *  - T unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> otherProperty)
     *  - T bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue)
     *  - T unbindOnSwipeRightProperty()
     *  - T bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> otherProperty)
     *  - T unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> otherProperty)
     *  - T bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue)
     *  - T unbindOnSwipeUpProperty()
     *  - T bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> otherProperty)
     *  - T unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> otherProperty)
     *  - T bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue)
     *  - T unbindOnTouchMovedProperty()
     *  - T bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> otherProperty)
     *  - T unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> otherProperty)
     *  - T bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue)
     *  - T unbindOnTouchPressedProperty()
     *  - T bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> otherProperty)
     *  - T unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> otherProperty)
     *  - T bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue)
     *  - T unbindOnTouchReleasedProperty()
     *  - T bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> otherProperty)
     *  - T unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> otherProperty)
     *  - T bindOnTouchStationaryProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue)
     *  - T unbindOnTouchStationaryProperty()
     *  - T bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> otherProperty)
     *  - T unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> otherProperty)
     *  - T bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue)
     *  - T unbindOnZoomProperty()
     *  - T bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> otherProperty)
     *  - T unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> otherProperty)
     *  - T bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue)
     *  - T unbindOnZoomFinishedProperty()
     *  - T bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty)
     *  - T unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty)
     *  - T bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue)
     *  - T unbindOnZoomStartedProperty()
     *  - T bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty)
     *  - T unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty)
     *  - T bindOpacityProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindOpacityProperty()
     *  - T bindBidirectionalOpacityProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalOpacityProperty(Property<Number> otherProperty)
     *  - T bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindPickOnBoundsProperty()
     *  - T bindBidirectionalPickOnBoundsProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalPickOnBoundsProperty(Property<Boolean> otherProperty)
     *  - T bindRotateProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindRotateProperty()
     *  - T bindBidirectionalRotateProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalRotateProperty(Property<Number> otherProperty)
     *  - T bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue)
     *  - T unbindRotationAxisProperty()
     *  - T bindBidirectionalRotationAxisProperty(Property<Point3D> otherProperty)
     *  - T unbindBidirectionalRotationAxisProperty(Property<Point3D> otherProperty)
     *  - T bindScaleXProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindScaleXProperty()
     *  - T bindBidirectionalScaleXProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalScaleXProperty(Property<Number> otherProperty)
     *  - T bindScaleYProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindScaleYProperty()
     *  - T bindBidirectionalScaleYProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalScaleYProperty(Property<Number> otherProperty)
     *  - T bindScaleZProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindScaleZProperty()
     *  - T bindBidirectionalScaleZProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalScaleZProperty(Property<Number> otherProperty)
     *  - T bindStyleProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindStyleProperty()
     *  - T bindBidirectionalStyleProperty(Property<String> otherProperty)
     *  - T unbindBidirectionalStyleProperty(Property<String> otherProperty)
     *  - T bindTranslateXProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindTranslateXProperty()
     *  - T bindBidirectionalTranslateXProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalTranslateXProperty(Property<Number> otherProperty)
     *  - T bindTranslateYProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindTranslateYProperty()
     *  - T bindBidirectionalTranslateYProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalTranslateYProperty(Property<Number> otherProperty)
     *  - T bindTranslateZProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindTranslateZProperty()
     *  - T bindBidirectionalTranslateZProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalTranslateZProperty(Property<Number> otherProperty)
     *  - T bindViewOrderProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindViewOrderProperty()
     *  - T bindBidirectionalViewOrderProperty(Property<Number> otherProperty)
     *  - T unbindBidirectionalViewOrderProperty(Property<Number> otherProperty)
     *  - T bindVisibleProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindVisibleProperty()
     *  - T bindBidirectionalVisibleProperty(Property<Boolean> otherProperty)
     *  - T unbindBidirectionalVisibleProperty(Property<Boolean> otherProperty)
     *
     * Event Functions
     *  - T fireEvent(Event event)
     *  - <S extends Event> T addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter)
     *  - <S extends Event> T addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler)
     *  - <S extends Event> T removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter)
     *  - <S extends Event> T removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler)
     *
     * Set Functions
     *  - T setAccessibleHelp(String value)
     *  - T setAccessibleRole(AccessibleRole value)
     *  - T setAccessibleRoleDescription(String value)
     *  - T setAccessibleText(String value)
     *  - T setBlendMode(BlendMode value)
     *  - T setCache(boolean value)
     *  - T setCacheHint(CacheHint value)
     *  - T setClip(Node value)
     *  - T setCursor(Cursor value)
     *  - T setDepthTest(DepthTest value)
     *  - T setDisable(boolean value)
     *  - T setEffect(Effect value)
     *  - T setEventDispatcher(EventDispatcher value)
     *  - T setFocusTraversable(boolean value)
     *  - T setId(String value)
     *  - T setInputMethodRequests(InputMethodRequests value)
     *  - T setLayoutX(double value)
     *  - T setLayoutY(double value)
     *  - T setManaged(boolean value)
     *  - T setMouseTransparent(boolean value)
     *  - T setNodeOrientation(NodeOrientation value)
     *  - T setOpacity(double value)
     *  - T setPickOnBounds(boolean value)
     *  - T setRotate(double value)
     *  - T setRotationAxis(Point3D value)
     *  - T setScaleX(double value)
     *  - T setScaleY(double value)
     *  - T setScaleZ(double value)
     *  - T setStyle(String style)
     *  - T setTranslateX(double value)
     *  - T setTranslateY(double value)
     *  - T setTranslateZ(double value)
     *  - T setUserData(Object value)
     *  - T setViewOrder(double value)
     *  - T setVisible(boolean value)
     *  - T setStyleClass(int index, String element)
     *  - T replaceAllStyleClasses(UnaryOperator<String> operator)
     *  - T setAllStyleClasses(String... elements)
     *  - T setAllStyleClasses(Collection<? extends String> col)
     *  - T setTransform(int index, Transform element)
     *  - T replaceAllTransforms(UnaryOperator<Transform> operator)
     *  - T setAllTransforms(Transform... elements)
     *  - T setAllTransforms(Collection<? extends Transform> col)
     *  - T setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value)
     *  - T setOnDragDetected(EventHandler<? super MouseEvent> value)
     *  - T setOnDragDone(EventHandler<? super DragEvent> value)
     *  - T setOnDragDropped(EventHandler<? super DragEvent> value)
     *  - T setOnDragEntered(EventHandler<? super DragEvent> value)
     *  - T setOnDragExited(EventHandler<? super DragEvent> value)
     *  - T setOnDragOver(EventHandler<? super DragEvent> value)
     *  - T setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value)
     *  - T setOnKeyPressed(EventHandler<? super KeyEvent> value)
     *  - T setOnKeyTyped(EventHandler<? super KeyEvent> value)
     *  - T setOnMouseClicked(EventHandler<? super MouseEvent> value)
     *  - T setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value)
     *  - T setOnMouseDragExited(EventHandler<? super MouseDragEvent> value)
     *  - T setOnMouseDragOver(EventHandler<? super MouseDragEvent> value)
     *  - T setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value)
     *  - T setOnMouseEntered(EventHandler<? super MouseEvent> value)
     *  - T setOnMouseExited(EventHandler<? super MouseEvent> value)
     *  - T setOnMouseMoved(EventHandler<? super MouseEvent> value)
     *  - T setOnMousePressed(EventHandler<? super MouseEvent> value)
     *  - T setOnMouseReleased(EventHandler<? super MouseEvent> value)
     *  - T setOnRotate(EventHandler<? super RotateEvent> value)
     *  - T setOnRotationFinished(EventHandler<? super RotateEvent> value)
     *  - T setOnRotationStarted(EventHandler<? super RotateEvent> value)
     *  - T setOnScroll(EventHandler<? super ScrollEvent> value)
     *  - T setOnScrollFinished(EventHandler<? super ScrollEvent> value)
     *  - T setOnScrollStarted(EventHandler<? super ScrollEvent> value)
     *  - T setOnSwipeDown(EventHandler<? super SwipeEvent> value)
     *  - T setOnSwipeLeft(EventHandler<? super SwipeEvent> value)
     *  - T setOnSwipeRight(EventHandler<? super SwipeEvent> value)
     *  - T setOnSwipeUp(EventHandler<? super SwipeEvent> value)
     *  - T setOnTouchMoved(EventHandler<? super TouchEvent> value)
     *  - T setOnTouchPressed(EventHandler<? super TouchEvent> value)
     *  - T setOnTouchReleased(EventHandler<? super TouchEvent> value)
     *  - T setOnTouchStationary(EventHandler<? super TouchEvent> value)
     *  - T setOnZoom(EventHandler<? super ZoomEvent> value)
     *  - T setOnZoomFinished(EventHandler<? super ZoomEvent> value)
     *  - T setOnZoomStarted(EventHandler<? super ZoomEvent> value)
     *
     * Add Style Functions
     *  - T addFirstStyleClass(String styleClass)
     *  - T addLastStyleClass(String styleClass)
     *  - T addStyleClass(String styleClass)
     *  - T addStyleClass(int index, String styleClass)
     *  - T addAllStyleClasses(String... styleClasses)
     *  - T addAllStyleClasses(Collection<? extends String> c)
     *  - T addAllStyleClasses(int index, Collection<? extends String> c)
     *
     * Remove Style Functions
     *  - T removeFirstStyleClass()
     *  - T removeLastStyleClass()
     *  - T removeStyleClass(String styleClass)
     *  - T removeStyleClasses(int from, int to)
     *  - T removeStyleClassesIf(Predicate<? super String> filter)
     *  - T removeAllStyleClasses(String... styleClasses)
     *  - T removeAllStyleClasses(Collection<? extends String> c)
     *
     * Style Functions
     *  - T pseudoClassStateChange(PseudoClass pseudoClass, boolean active)
     *  - T applyCss()
     *
     * Add Transform Functions
     *  - T addFirstTransform(Transform transform)
     *  - T addLastTransform(Transform transform)
     *  - T addTransform(Transform transform)
     *  - T addTransform(int index, Transform transform)
     *  - T addAllTransforms(Transform... transforms)
     *  - T addAllTransforms(Collection<? extends Transform> c)
     *  - T addAllTransforms(int index, Collection<? extends Transform> c)
     *
     * Remove Transform Functions
     *  - T removeFirstTransform()
     *  - T removeLastTransform()
     *  - T removeTransform(Transform transform)
     *  - T removeTransforms(int from, int to)
     *  - T removeTransformsIf(Predicate<? super Transform> filter)
     *  - T removeAllTransforms(Transform... transforms)
     *  - T removeAllTransforms(Collection<? extends Transform> c)
     *
     * Layout Functions:
     *  - T toBack()
     *  - T toFront()
     *  - T resize(double width, double height)
     *  - T autosize()
     *  - T resizeRelocate(double x, double y, double width, double height)
     *  - T requestFocus()
     */

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to the accessibleHelp property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleHelpProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the accessibleHelp property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleHelpProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the accessibleRoleDescription property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleRoleDescriptionProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the accessibleRoleDescription property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleRoleDescriptionProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the accessibleRole property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        getNode().accessibleRoleProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the accessibleRole property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleRoleProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the accessibleText property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the accessibleText property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the blendMode property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        getNode().blendModeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the blendMode property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().blendModeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the boundsInLocal property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().boundsInLocalProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the boundsInLocal property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        getNode().boundsInLocalProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the boundsInParent property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().boundsInParentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the boundsInParent property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().boundsInParentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the cacheHint property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        getNode().cacheHintProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the cacheHint property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheHintProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the cache property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cacheProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the cache property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addCacheInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the clip property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addClipChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().clipProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the clip property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addClipInvalidationListener(InvalidationListener invalidationListener) {
        getNode().clipProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the cursor property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        getNode().cursorProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the cursor property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addCursorInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cursorProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the depthTest property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        getNode().depthTestProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the depthTest property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        getNode().depthTestProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the disabled property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().disabledProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the disabled property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addDisabledInvalidationListener(InvalidationListener invalidationListener) {
        getNode().disabledProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the disable property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().disableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the disable property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addDisableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().disableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the effectiveNodeOrientation property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        getNode().effectiveNodeOrientationProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the effectiveNodeOrientation property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        getNode().effectiveNodeOrientationProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the effect property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        getNode().effectProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the effect property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addEffectInvalidationListener(InvalidationListener invalidationListener) {
        getNode().effectProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the eventDispatcher property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        getNode().eventDispatcherProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the eventDispatcher property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        getNode().eventDispatcherProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the focused property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the focused property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addFocusedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the focusTraversable property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusTraversableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the focusTraversable property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusTraversableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the focusVisible property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusVisibleProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the focusVisible property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusVisibleProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the focusTraversable property of the node to monitor focus within.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusTraversableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the focusTraversable property of the node to monitor focus within.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusTraversableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the hover property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().hoverProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the hover property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addHoverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().hoverProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the id property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addIdChangeListener(ChangeListener<? super String> changeListener) {
        getNode().idProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the id property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addIdInvalidationListener(InvalidationListener invalidationListener) {
        getNode().idProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the inputMethodRequests property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        getNode().inputMethodRequestsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the inputMethodRequests property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().inputMethodRequestsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the layoutBounds property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().layoutBoundsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the layoutBounds property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutBoundsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the layoutX property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().layoutXProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the layoutX property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutXProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the layoutY property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().layoutYProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the layoutY property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutYProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the localToParentTransform property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        getNode().localToParentTransformProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the localToParentTransform property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        getNode().localToParentTransformProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the localToSceneTransform property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        getNode().localToSceneTransformProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the localToSceneTransform property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        getNode().localToSceneTransformProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the managed property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().managedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the managed property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addManagedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().managedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the mouseTransparent property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().mouseTransparentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the mouseTransparent property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().mouseTransparentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the nodeOrientation property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        getNode().nodeOrientationProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the nodeOrientation property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        getNode().nodeOrientationProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onContextMenuRequested property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        getNode().onContextMenuRequestedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onContextMenuRequested property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onContextMenuRequestedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onDragDetected property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onDragDetectedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onDragDetected property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDetectedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onDragDone property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragDoneProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onDragDone property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDoneProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onDragDropped property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragDroppedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onDragDropped property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDroppedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onDragEntered property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragEnteredProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onDragEntered property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragEnteredProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onDragExited property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragExitedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onDragExited property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragExitedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onDragOver property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragOverProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onDragOver property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragOverProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onInputMethodTextChanged property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        getNode().onInputMethodTextChangedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onInputMethodTextChanged property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onInputMethodTextChangedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onKeyPressed property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyPressedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onKeyPressed property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyPressedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onKeyReleased property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyReleasedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onKeyReleased property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyReleasedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onKeyTyped property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyTypedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onKeyTyped property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyTypedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseClicked property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseClickedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseClicked property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseClickedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseDragEntered property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragEnteredProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseDragEntered property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragEnteredProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseDragExited property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragExitedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseDragExited property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragExitedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseDragged property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseDraggedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseDragged property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDraggedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseDragOver property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragOverProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseDragOver property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragOverProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseDragReleased property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragReleasedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseDragReleased property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragReleasedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseEntered property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseEnteredProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseEntered property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseEnteredProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseExited property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseExitedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseExited property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseExitedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseMoved property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseMovedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseMoved property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseMovedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMousePressed property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMousePressedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMousePressed property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMousePressedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onMouseReleased property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseReleasedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onMouseReleased property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseReleasedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onRotate property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotateProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onRotate property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotateProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onRotationFinished property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotationFinishedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onRotationFinished property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotationFinishedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onRotationStarted property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotationStartedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onRotationStarted property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotationStartedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onScrollFinished property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollFinishedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onScrollFinished property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollFinishedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onScroll property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onScroll property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onScrollStarted property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollStartedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onScrollStarted property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollStartedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onSwipeDown property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeDownProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onSwipeDown property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeDownProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onSwipeLeft property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeLeftProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onSwipeLeft property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeLeftProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onSwipeRight property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeRightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onSwipeRight property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeRightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onSwipeUp property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeUpProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onSwipeUp property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeUpProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onTouchMoved property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchMovedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onTouchMoved property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchMovedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onTouchPressed property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchPressedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onTouchPressed property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchPressedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onTouchReleased property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchReleasedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onTouchReleased property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchReleasedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onTouchStationary property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchStationaryProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onTouchStationary property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchStationaryProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onZoomFinished property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomFinishedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onZoomFinished property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomFinishedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onZoom property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onZoom property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the onZoomStarted property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomStartedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the onZoomStarted property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomStartedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the opacity property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().opacityProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the opacity property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addOpacityInvalidationListener(InvalidationListener invalidationListener) {
        getNode().opacityProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the parent property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addParentChangeListener(ChangeListener<? super Parent> changeListener) {
        getNode().parentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the parent property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addParentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().parentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the pickOnBounds property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().pickOnBoundsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the pickOnBounds property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().pickOnBoundsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the pressed property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addPressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().pressedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the pressed property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().pressedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the scene property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        getNode().sceneProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the scene property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addSceneInvalidationListener(InvalidationListener invalidationListener) {
        getNode().sceneProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the rotate property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addRotateChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().rotateProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the rotate property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addRotateInvalidationListener(InvalidationListener invalidationListener) {
        getNode().rotateProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the rotationAxis property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        getNode().rotationAxisProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the rotationAxis property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        getNode().rotationAxisProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the scaleX property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleXProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the scaleX property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addScaleXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleXProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the scaleY property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleYProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the scaleY property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addScaleYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleYProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the scaleZ property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleZProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the scaleZ property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addScaleZInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleZProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the style property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addStyleChangeListener(ChangeListener<? super String> changeListener) {
        getNode().styleProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the style property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addStyleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().styleProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ListChangeListener} to the style class list of the node.
     *
     * @param listChangeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        getNode().getStyleClass()
                 .addListener(listChangeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the style class list of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addStyleListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getStyleClass()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ListChangeListener} to the transform list of the node.
     *
     * @param listChangeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        getNode().getTransforms()
                 .addListener(listChangeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the transform list of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addTransformListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getTransforms()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the translateX property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateXProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the translateX property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateXProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the translateY property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateYProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the translateY property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateYProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the translateZ property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateZProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the translateZ property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateZProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the viewOrder property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().viewOrderProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the viewOrder property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        getNode().viewOrderProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to the visible property of the node.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().visibleProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to the visible property of the node.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T addVisibleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().visibleProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the accessibleHelp property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleHelpProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the accessibleHelp property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleHelpProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the accessibleRoleDescription property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleRoleDescriptionProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the accessibleRoleDescription property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleRoleDescriptionProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the accessibleRole property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        getNode().accessibleRoleProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the accessibleRole property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleRoleProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the accessibleText property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the accessibleText property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the blendMode property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        getNode().blendModeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the blendMode property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().blendModeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the boundsInLocal property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().boundsInLocalProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the boundsInLocal property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        getNode().boundsInLocalProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the boundsInParent property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().boundsInParentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the boundsInParent property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().boundsInParentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the cacheHint property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        getNode().cacheHintProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the cacheHint property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheHintProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the cache property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cacheProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the cache property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeCacheInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the clip property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeClipChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().clipProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the clip property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeClipInvalidationListener(InvalidationListener invalidationListener) {
        getNode().clipProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the cursor property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        getNode().cursorProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the cursor property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeCursorInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cursorProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the depthTest property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        getNode().depthTestProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the depthTest property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        getNode().depthTestProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the disabled property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().disabledProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the disabled property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeDisabledInvalidationListener(InvalidationListener invalidationListener) {
        getNode().disabledProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the disable property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().disableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the disable property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeDisableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().disableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the effectiveNodeOrientation property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        getNode().effectiveNodeOrientationProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the effectiveNodeOrientation property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        getNode().effectiveNodeOrientationProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the effect property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        getNode().effectProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the effect property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeEffectInvalidationListener(InvalidationListener invalidationListener) {
        getNode().effectProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the eventDispatcher property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        getNode().eventDispatcherProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the eventDispatcher property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        getNode().eventDispatcherProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the focused property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the focused property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeFocusedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the focusTraversable property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusTraversableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the focusTraversable property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusTraversableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the focusVisible property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusVisibleProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the focusVisible property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusVisibleProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the hover property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().hoverProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the hover property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeHoverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().hoverProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the id property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeIdChangeListener(ChangeListener<? super String> changeListener) {
        getNode().idProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the id property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeIdInvalidationListener(InvalidationListener invalidationListener) {
        getNode().idProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the inputMethodRequests property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        getNode().inputMethodRequestsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the inputMethodRequests property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().inputMethodRequestsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the layoutBounds property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().layoutBoundsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the layoutBounds property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutBoundsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the layoutX property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().layoutXProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the layoutX property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutXProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the layoutY property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().layoutYProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the layoutY property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutYProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the localToParentTransform property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        getNode().localToParentTransformProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the localToParentTransform property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        getNode().localToParentTransformProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the localToSceneTransform property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        getNode().localToSceneTransformProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the localToSceneTransform property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        getNode().localToSceneTransformProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the managed property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().managedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the managed property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeManagedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().managedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the mouseTransparent property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().mouseTransparentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the mouseTransparent property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().mouseTransparentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the nodeOrientation property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        getNode().nodeOrientationProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the nodeOrientation property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        getNode().nodeOrientationProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onContextMenuRequested property of the node.
     *
     * @param changeListener
     *         The listener to remove
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        getNode().onContextMenuRequestedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onContextMenuRequested property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onContextMenuRequestedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onDragDetected property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onDragDetectedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onDragDetected property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDetectedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onDragDone property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragDoneProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onDragDone property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDoneProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onDragDropped property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragDroppedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onDragDropped property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDroppedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onDragEntered property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragEnteredProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onDragEntered property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragEnteredProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onDragExited property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragExitedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onDragExited property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragExitedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onDragOver property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragOverProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onDragOver property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragOverProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onInputMethodTextChanged property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        getNode().onInputMethodTextChangedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onInputMethodTextChanged property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onInputMethodTextChangedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onKeyPressed property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyPressedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onKeyPressed property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyPressedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onKeyReleased property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyReleasedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onKeyReleased property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyReleasedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onKeyTyped property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyTypedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onKeyTyped property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyTypedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseClicked property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseClickedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseClicked property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseClickedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseDragEntered property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragEnteredProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseDragEntered property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragEnteredProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseDragExited property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragExitedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseDragExited property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragExitedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseDragged property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseDraggedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseDragOver property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragOverProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseDragOver property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragOverProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseDragReleased property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragReleasedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseDragReleased property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragReleasedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseEntered property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseEnteredProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseEntered property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseEnteredProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseExited property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseExitedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseExited property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseExitedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseMoved property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseMovedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseMoved property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseMovedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMousePressed property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMousePressedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMousePressed property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMousePressedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onMouseReleased property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseReleasedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onMouseReleased property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseReleasedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onRotate property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotateProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onRotate property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotateProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onRotationFinished property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotationFinishedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onRotationFinished property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotationFinishedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onRotationStarted property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotationStartedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onRotationStarted property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotationStartedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onScroll property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onScroll property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onScrollStarted property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollStartedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onScrollStarted property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollStartedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onScrollFinished property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollFinishedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onScrollFinished property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollFinishedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onSwipeDown property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeDownProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onSwipeDown property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeDownProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onSwipeLeft property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeLeftProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onSwipeLeft property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeLeftProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onSwipeRight property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeRightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onSwipeRight property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeRightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onSwipeUp property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeUpProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onSwipeUp property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeUpProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onTouchMoved property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchMovedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onTouchMoved property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchMovedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onTouchPressed property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchPressedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onTouchPressed property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchPressedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onTouchReleased property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchReleasedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onTouchReleased property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchReleasedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onTouchStationary property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchStationaryProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onTouchStationary property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchStationaryProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onZoom property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onZoom property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onZoomStarted property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomStartedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onZoomStarted property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomStartedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the onZoomFinished property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomFinishedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the onZoomFinished property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomFinishedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the opacity property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().opacityProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the opacity property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeOpacityInvalidationListener(InvalidationListener invalidationListener) {
        getNode().opacityProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the parent property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeParentChangeListener(ChangeListener<? super Parent> changeListener) {
        getNode().parentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the parent property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeParentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().parentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the pickOnBounds property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().pickOnBoundsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the pickOnBounds property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().pickOnBoundsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the pressed property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removePressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().pressedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the pressed property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removePressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().pressedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the scene property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        getNode().sceneProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the scene property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeSceneInvalidationListener(InvalidationListener invalidationListener) {
        getNode().sceneProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the rotate property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeRotateChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().rotateProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the rotate property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeRotateInvalidationListener(InvalidationListener invalidationListener) {
        getNode().rotateProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the rotationAxis property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        getNode().rotationAxisProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the rotationAxis property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        getNode().rotationAxisProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the scaleX property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleXProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the scaleX property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeScaleXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleXProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the scaleY property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleYProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the scaleY property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeScaleYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleYProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the scaleZ property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleZProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the scaleZ property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeScaleZInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleZProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the style property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeStyleChangeListener(ChangeListener<? super String> changeListener) {
        getNode().styleProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the style property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeStyleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().styleProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ListChangeListener} from the style class list of the node.
     *
     * @param listChangeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        getNode().getStyleClass()
                 .removeListener(listChangeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the style class list of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeStyleListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getStyleClass()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ListChangeListener} from the transform list of the node.
     *
     * @param listChangeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        getNode().getTransforms()
                 .removeListener(listChangeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the transform list of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeTransformListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getTransforms()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the translateX property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateXProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the translateX property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateXProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the translateY property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateYProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the translateY property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateYProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the translateZ property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateZProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the translateZ property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateZProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the viewOrder property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().viewOrderProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the viewOrder property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        getNode().viewOrderProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the visible property of the node.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().visibleProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the visible property of the node.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T removeVisibleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().visibleProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Accessible Help Property

    /**
     * Binds the accessibleHelp property of the node to an observable value.
     *
     * @param observableValue
     *         The observable value to bind to the accessibleHelp property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue) {
        getNode().accessibleHelpProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the accessibleHelp property of the node, thereby allowing to manually set this property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindAccessibleHelpProperty() {
        getNode().accessibleHelpProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the node's accessibleHelp property and another String property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalAccessibleHelpProperty(Property<String> otherProperty) {
        getNode().accessibleHelpProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the accessibleHelp property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalAccessibleHelpProperty(Property<String> otherProperty) {
        getNode().accessibleHelpProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Accessible Role Description Property

    /**
     * Binds the accessibleRoleDescription property of the node to an observable value.
     *
     * @param observableValue
     *         The observable value to bind to the accessibleRoleDescription property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue) {
        getNode().accessibleRoleDescriptionProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the accessibleRoleDescription property of the node, thereby allowing to manually set this property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindAccessibleRoleDescriptionProperty() {
        getNode().accessibleRoleDescriptionProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the node's accessibleRoleDescription property and another String property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> otherProperty) {
        getNode().accessibleRoleDescriptionProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the accessibleRoleDescription property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> otherProperty) {
        getNode().accessibleRoleDescriptionProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Accessible Role Property

    /**
     * Binds the accessibleRole property of the node to an observable value.
     *
     * @param observableValue
     *         The observable value to bind to the accessibleRole property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue) {
        getNode().accessibleRoleProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the accessibleRole property of the node, thereby allowing to manually set this property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindAccessibleRoleProperty() {
        getNode().accessibleRoleProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the node's accessibleRole property and another AccessibleRole property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> otherProperty) {
        getNode().accessibleRoleProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the accessibleRole property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> otherProperty) {
        getNode().accessibleRoleProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Accessible Text Property

    /**
     * Binds the accessibleText property of the node to an observable value.
     *
     * @param observableValue
     *         The observable value to bind to the accessibleText property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindAccessibleTextProperty(ObservableValue<? extends String> observableValue) {
        getNode().accessibleTextProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the accessibleText property of the node, thereby allowing to manually set this property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindAccessibleTextProperty() {
        getNode().accessibleTextProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the node's accessibleText property and another String property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalAccessibleTextProperty(Property<String> otherProperty) {
        getNode().accessibleTextProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the accessibleText property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalAccessibleTextProperty(Property<String> otherProperty) {
        getNode().accessibleTextProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Blend Mode Property

    /**
     * Binds the blendMode property of the node to an observable value.
     *
     * @param observableValue
     *         The observable value to bind to the blendMode property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue) {
        getNode().blendModeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the blendMode property of the node, thereby allowing to manually set this property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBlendModeProperty() {
        getNode().blendModeProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the node's blendMode property and another BlendMode property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalBlendModeProperty(Property<BlendMode> otherProperty) {
        getNode().blendModeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the blendMode property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalBlendModeProperty(Property<BlendMode> otherProperty) {
        getNode().blendModeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cache Hint Property

    /**
     * Binds the cacheHint property of the node to an observable value.
     *
     * @param observableValue
     *         The observable value to bind to the cacheHint property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue) {
        getNode().cacheHintProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the cacheHint property of the node, thereby allowing to manually set this property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindCacheHintProperty() {
        getNode().cacheHintProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the node's cacheHint property and another CacheHint property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalCacheHintProperty(Property<CacheHint> otherProperty) {
        getNode().cacheHintProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the cacheHint property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalCacheHintProperty(Property<CacheHint> otherProperty) {
        getNode().cacheHintProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cache Property

    /**
     * Binds the cache property of the node to an observable value.
     *
     * @param observableValue
     *         The observable value to bind to the cache property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindCacheProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().cacheProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the cache property of the node, thereby allowing to manually set this property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindCacheProperty() {
        getNode().cacheProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the node's cache property and another Boolean property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalCacheProperty(Property<Boolean> otherProperty) {
        getNode().cacheProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the cache property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalCacheProperty(Property<Boolean> otherProperty) {
        getNode().cacheProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Clip Property

    /**
     * Binds the clip property of the node to an observable value.
     *
     * @param observableValue
     *         The observable value to bind to the clip property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindClipProperty(ObservableValue<? extends Node> observableValue) {
        getNode().clipProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the clip property of the node, thereby allowing to manually set this property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindClipProperty() {
        getNode().clipProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the node's clip property and another Node property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalClipProperty(Property<Node> otherProperty) {
        getNode().clipProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the clip property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalClipProperty(Property<Node> otherProperty) {
        getNode().clipProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cursor Property

    /**
     * Binds the cursor property of the node to an observable value. When the observable value changes, the node's cursor property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the cursor property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindCursorProperty(ObservableValue<? extends Cursor> observableValue) {
        getNode().cursorProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the cursor property of the node. This restores the ability to manually set the cursor of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindCursorProperty() {
        getNode().cursorProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the cursor property of the node and another Cursor property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalCursorProperty(Property<Cursor> otherProperty) {
        getNode().cursorProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the cursor property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalCursorProperty(Property<Cursor> otherProperty) {
        getNode().cursorProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Depth Test Property

    /**
     * Binds the depthTest property of the node to an observable value. When the observable value changes, the node's depthTest property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the depthTest property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue) {
        getNode().depthTestProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the depthTest property of the node. This restores the ability to manually set the depthTest of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindDepthTestProperty() {
        getNode().depthTestProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the depthTest property of the node and another DepthTest property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalDepthTestProperty(Property<DepthTest> otherProperty) {
        getNode().depthTestProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the depthTest property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalDepthTestProperty(Property<DepthTest> otherProperty) {
        getNode().depthTestProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Disable Property

    /**
     * Binds the disable property of the node to an observable value. When the observable value changes, the node's disable property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the disable property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindDisableProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().disableProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the disable property of the node. This restores the ability to manually set the disable state of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindDisableProperty() {
        getNode().disableProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the disable property of the node and another Boolean property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalDisableProperty(Property<Boolean> otherProperty) {
        getNode().disableProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the disable property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalDisableProperty(Property<Boolean> otherProperty) {
        getNode().disableProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Effect Property

    /**
     * Binds the effect property of the node to an observable value. When the observable value changes, the node's effect property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the effect property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindEffectProperty(ObservableValue<? extends Effect> observableValue) {
        getNode().effectProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the effect property of the node. This restores the ability to manually set the effect of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindEffectProperty() {
        getNode().effectProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the effect property of the node and another Effect property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalEffectProperty(Property<Effect> otherProperty) {
        getNode().effectProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the effect property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalEffectProperty(Property<Effect> otherProperty) {
        getNode().effectProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Event Dispatcher Property

    /**
     * Binds the eventDispatcher property of the node to an observable value. When the observable value changes, the node's eventDispatcher property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the eventDispatcher property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue) {
        getNode().eventDispatcherProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the eventDispatcher property of the node. This restores the ability to manually set the eventDispatcher of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindEventDispatcherProperty() {
        getNode().eventDispatcherProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the eventDispatcher property of the node and another EventDispatcher property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> otherProperty) {
        getNode().eventDispatcherProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the eventDispatcher property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> otherProperty) {
        getNode().eventDispatcherProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Focus Traversable Property

    /**
     * Binds the focusTraversable property of the node to an observable value. When the observable value changes, the node's focusTraversable property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the focusTraversable property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().focusTraversableProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the focusTraversable property of the node. This restores the ability to manually set the focusTraversable of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindFocusTraversableProperty() {
        getNode().focusTraversableProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the focusTraversable property of the node and another Boolean property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalFocusTraversableProperty(Property<Boolean> otherProperty) {
        getNode().focusTraversableProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the focusTraversable property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalFocusTraversableProperty(Property<Boolean> otherProperty) {
        getNode().focusTraversableProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // ID Property

    /**
     * Binds the ID property of the node to an observable value. When the observable value changes, the node's ID property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the ID property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindIdProperty(ObservableValue<? extends String> observableValue) {
        getNode().idProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the ID property of the node. This restores the ability to manually set the ID of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindIdProperty() {
        getNode().idProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the ID property of the node and another string property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalIdProperty(Property<String> otherProperty) {
        getNode().idProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the ID property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalIdProperty(Property<String> otherProperty) {
        getNode().idProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Input Method Requests Property

    /**
     * Binds the inputMethodRequests property of the node to an observable value. When the observable value changes, the node's inputMethodRequests property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the inputMethodRequests property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue) {
        getNode().inputMethodRequestsProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the inputMethodRequests property of the node. This restores the ability to manually set the inputMethodRequests of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindInputMethodRequestsProperty() {
        getNode().inputMethodRequestsProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the inputMethodRequests property of the node and another InputMethodRequests property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> otherProperty) {
        getNode().inputMethodRequestsProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the inputMethodRequests property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> otherProperty) {
        getNode().inputMethodRequestsProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Layout X Property

    /**
     * Binds the layoutX property of the node to an observable value. When the observable value changes, the node's layoutX property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the layoutX property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindLayoutXProperty(ObservableValue<? extends Number> observableValue) {
        getNode().layoutXProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the layoutX property of the node. This restores the ability to manually set the layoutX of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindLayoutXProperty() {
        getNode().layoutXProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the layoutX property of the node and another Number property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalLayoutXProperty(Property<Number> otherProperty) {
        getNode().layoutXProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the layoutX property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalLayoutXProperty(Property<Number> otherProperty) {
        getNode().layoutXProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Layout Y Property

    /**
     * Binds the layoutY property of the node to an observable value. When the observable value changes, the node's layoutY property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the layoutY property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindLayoutYProperty(ObservableValue<? extends Number> observableValue) {
        getNode().layoutYProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the layoutY property of the node. This restores the ability to manually set the layoutY of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindLayoutYProperty() {
        getNode().layoutYProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the layoutY property of the node and another Number property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalLayoutYProperty(Property<Number> otherProperty) {
        getNode().layoutYProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the layoutY property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalLayoutYProperty(Property<Number> otherProperty) {
        getNode().layoutYProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Managed Property

    /**
     * Binds the managed property of the node to an observable value. When the observable value changes, the node's managed property will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the managed property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindManagedProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().managedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the managed property of the node. This restores the ability to manually set the managed state of the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindManagedProperty() {
        getNode().managedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the managed property of the node and another Boolean property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalManagedProperty(Property<Boolean> otherProperty) {
        getNode().managedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the managed property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalManagedProperty(Property<Boolean> otherProperty) {
        getNode().managedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Mouse Transparent Property

    /**
     * Binds the mouseTransparent property of the node to an observable value.
     *
     * <p>When the observable value changes, the node's mouseTransparent property will be updated, determining whether it can receive mouse events {@code false} or not {@code true}.</p>
     *
     * @param observableValue
     *         The observable value to bind to the mouseTransparent property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().mouseTransparentProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the mouseTransparent property of the node. This restores the ability to manually set whether the node is mouse transparent.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindMouseTransparentProperty() {
        getNode().mouseTransparentProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the mouseTransparent property of the node and another boolean property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalMouseTransparentProperty(Property<Boolean> otherProperty) {
        getNode().mouseTransparentProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the mouseTransparent property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalMouseTransparentProperty(Property<Boolean> otherProperty) {
        getNode().mouseTransparentProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Node Orientation Property

    /**
     * Binds the nodeOrientation property of the node to an observable value.
     *
     * <p>When the observable value changes, the node's orientation will be updated to reflect the new orientation (LEFT_TO_RIGHT or RIGHT_TO_LEFT).</p>
     *
     * @param observableValue
     *         The observable value to bind to the nodeOrientation property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue) {
        getNode().nodeOrientationProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the nodeOrientation property of the node, restoring the ability to manually set the node's orientation.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindNodeOrientationProperty() {
        getNode().nodeOrientationProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the nodeOrientation property of the node and another NodeOrientation property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> otherProperty) {
        getNode().nodeOrientationProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the nodeOrientation property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> otherProperty) {
        getNode().nodeOrientationProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Context Menu Requested Property

    /**
     * Binds the onContextMenuRequested property of the node to an observable value.
     *
     * <p>When the observable value changes, the onContextMenuRequested event handler will be updated to handle context menu request events.</p>
     *
     * @param observableValue
     *         The observable value to bind to the onContextMenuRequested property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnContextMenuRequestedProperty(ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue) {
        getNode().onContextMenuRequestedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onContextMenuRequested property of the node. This will stop the onContextMenuRequested event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnContextMenuRequestedProperty() {
        getNode().onContextMenuRequestedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onContextMenuRequested property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other in response to context menu request events.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> otherProperty) {
        getNode().onContextMenuRequestedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onContextMenuRequested property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> otherProperty) {
        getNode().onContextMenuRequestedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Detected Property

    /**
     * Binds the onDragDetected property of the node to an observable value. When the observable value changes, the onDragDetected event handler will be updated to handle mouse drag detection events.
     *
     * @param observableValue
     *         The observable value to bind to the onDragDetected property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onDragDetectedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onDragDetected property of the node. This will stop the onDragDetected event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnDragDetectedProperty() {
        getNode().onDragDetectedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onDragDetected property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other in response to mouse drag detection events.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onDragDetectedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onDragDetected property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onDragDetectedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Done Property

    /**
     * Binds the onDragDone property of the node to an observable value. When the observable value changes, the onDragDone event handler will be updated to handle drag done events.
     *
     * @param observableValue
     *         The observable value to bind to the onDragDone property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragDoneProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onDragDone property of the node. This will stop the onDragDone event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnDragDoneProperty() {
        getNode().onDragDoneProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onDragDone property of the node and another property. This allows both properties to automatically update each other in response to drag done events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragDoneProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onDragDone property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragDoneProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Dropped Property

    /**
     * Binds the onDragDropped property of the node to an observable value. When the observable value changes, the onDragDropped event handler will be updated to handle drag-dropped events.
     *
     * @param observableValue
     *         The observable value to bind to the onDragDropped property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragDroppedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onDragDropped property of the node. This will stop the onDragDropped event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnDragDroppedProperty() {
        getNode().onDragDroppedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onDragDropped property of the node and another property. This allows both properties to automatically update each other in response to drag dropped events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragDroppedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onDragDropped property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragDroppedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Entered Property

    /**
     * Binds the onDragEntered property of the node to an observable value. When the observable value changes, the onDragEntered event handler will be updated to handle drag-entered events.
     *
     * @param observableValue
     *         The observable value to bind to the onDragEntered property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragEnteredProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onDragEntered property of the node. This will stop the onDragEntered event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnDragEnteredProperty() {
        getNode().onDragEnteredProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onDragEntered property of the node and another property. This allows both properties to automatically update each other in response to drag entered events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragEnteredProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onDragEntered property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragEnteredProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Exited Property

    /**
     * Binds the onDragExited property of the node to an observable value. When the observable value changes, the onDragExited event handler will be updated to handle drag exited events.
     *
     * @param observableValue
     *         The observable value to bind to the onDragExited property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragExitedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onDragExited property of the node. This will stop the onDragExited event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnDragExitedProperty() {
        getNode().onDragExitedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onDragExited property of the node and another property. This allows both properties to automatically update each other in response to drag exited events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragExitedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onDragExited property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragExitedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Over Property

    /**
     * Binds the onDragOver property of the node to an observable value. When the observable value changes, the onDragOver event handler will be updated to handle drag over events.
     *
     * @param observableValue
     *         The observable value to bind to the onDragOver property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragOverProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onDragOver property of the node. This will stop the onDragOver event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnDragOverProperty() {
        getNode().onDragOverProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onDragOver property of the node and another property. This allows both properties to automatically update each other in response to drag over events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragOverProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onDragOver property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragOverProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Input Method Text Changed Property

    /**
     * Binds the onInputMethodTextChanged property of the node to an observable value.
     *
     * <p>When the observable value changes, the onInputMethodTextChanged event handler will be updated to handle input method text changed events.</p>
     *
     * @param observableValue
     *         The observable value to bind to the onInputMethodTextChanged property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnInputMethodTextChangedProperty(ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue) {
        getNode().onInputMethodTextChangedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onInputMethodTextChanged property of the node. This will stop the onInputMethodTextChanged event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnInputMethodTextChangedProperty() {
        getNode().onInputMethodTextChangedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onInputMethodTextChanged property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other in response to input method text changed events.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> otherProperty) {
        getNode().onInputMethodTextChangedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onInputMethodTextChanged property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> otherProperty) {
        getNode().onInputMethodTextChangedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Key Pressed Property

    /**
     * Binds the onKeyPressed property of the node to an observable value. When the observable value changes, the onKeyPressed event handler will be updated to handle key pressed events.
     *
     * @param observableValue
     *         The observable value to bind to the onKeyPressed property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        getNode().onKeyPressedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onKeyPressed property of the node. This will stop the onKeyPressed event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnKeyPressedProperty() {
        getNode().onKeyPressedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onKeyPressed property of the node and another property. This allows both properties to automatically update each other in response to key pressed events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyPressedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onKeyPressed property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyPressedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Key Released Property

    /**
     * Binds the onKeyReleased property of the node to an observable value. When the observable value changes, the onKeyReleased event handler will be updated to handle key released events.
     *
     * @param observableValue
     *         The observable value to bind to the onKeyReleased property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        getNode().onKeyReleasedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onKeyReleased property of the node. This will stop the onKeyReleased event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnKeyReleasedProperty() {
        getNode().onKeyReleasedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onKeyReleased property of the node and another property. This ensures that both properties can update each other in response to key released events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyReleasedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onKeyReleased property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyReleasedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Key Typed Property

    /**
     * Binds the onKeyTyped property of the node to an observable value. When the observable value changes, the onKeyTyped event handler will be updated to handle key typed events.
     *
     * @param observableValue
     *         The observable value to bind to the onKeyTyped property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        getNode().onKeyTypedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onKeyTyped property of the node. This will stop the onKeyTyped event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnKeyTypedProperty() {
        getNode().onKeyTypedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onKeyTyped property of the node and another property. This allows both properties to automatically update each other in response to key typed events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyTypedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onKeyTyped property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyTypedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Clicked Property

    /**
     * Binds the onMouseClicked property of the node to an observable value. When the observable value changes, the onMouseClicked event handler will be updated to handle mouse-clicked events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseClicked property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseClickedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseClicked property of the node. This will stop the onMouseClicked event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseClickedProperty() {
        getNode().onMouseClickedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseClicked property of the node and another property. This ensures that both properties can update each other in response to mouse-clicked events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseClickedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseClicked property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseClickedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Drag Entered Property

    /**
     * Binds the onMouseDragEntered property of the node to an observable value. When the observable value changes, the onMouseDragEntered event handler will be updated to handle mouse drag entered events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseDragEntered property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseDragEnteredProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        getNode().onMouseDragEnteredProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseDragEntered property of the node. This will stop the onMouseDragEntered event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseDragEnteredProperty() {
        getNode().onMouseDragEnteredProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseDragEntered property of the node and another property.
     *
     * <p>This ensures that both properties can update each other in response to mouse drag entered events.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragEnteredProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseDragEntered property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragEnteredProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Drag Exited Property

    /**
     * Binds the onMouseDragExited property of the node to an observable value. When the observable value changes, the onMouseDragExited event handler will be updated to handle mouse drag exited events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseDragExited property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseDragExitedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        getNode().onMouseDragExitedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseDragExited property of the node. This will stop the onMouseDragExited event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseDragExitedProperty() {
        getNode().onMouseDragExitedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseDragExited property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other in response to mouse drag exited events.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragExitedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseDragExited property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragExitedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Dragged Property

    /**
     * Binds the onMouseDragged property of the node to an observable value. When the observable value changes, the onMouseDragged event handler will be updated to handle mouse-dragged events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseDragged property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseDraggedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseDragged property of the node. This will stop the onMouseDragged event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseDraggedProperty() {
        getNode().onMouseDraggedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseDragged property of the node and another property. This ensures that both properties can update each other in response to mouse dragged events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseDraggedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseDragged property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseDraggedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Drag Over Property

    /**
     * Binds the onMouseDragOver property of the node to an observable value. When the observable value changes, the onMouseDragOver event handler will be updated to handle mouse drag over events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseDragOver property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseDragOverProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        getNode().onMouseDragOverProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseDragOver property of the node. This will stop the onMouseDragOver event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseDragOverProperty() {
        getNode().onMouseDragOverProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseDragOver property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other in response to mouse drag over events.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragOverProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseDragOver property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragOverProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Drag Released Property

    /**
     * Binds the onMouseDragReleased property of the node to an observable value. When the observable value changes, the onMouseDragReleased event handler will be updated to handle mouse drag released events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseDragReleased property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseDragReleasedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        getNode().onMouseDragReleasedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseDragReleased property of the node. This will stop the onMouseDragReleased event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseDragReleasedProperty() {
        getNode().onMouseDragReleasedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseDragReleased property of the node and another property.
     *
     * <p>This ensures that both properties can update each other in response to mouse drag released events.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragReleasedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseDragReleased property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragReleasedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Entered Property

    /**
     * Binds the onMouseEntered property of the node to an observable value. When the observable value changes, the onMouseEntered event handler will be updated to handle mouse entered events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseEntered property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseEnteredProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseEntered property of the node. This will stop the onMouseEntered event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseEnteredProperty() {
        getNode().onMouseEnteredProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseEntered property of the node and another property. This allows both properties to automatically update each other in response to mouse entered events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseEnteredProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseEntered property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseEnteredProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Exited Property

    /**
     * Binds the onMouseExited property of the node to an observable value.
     *
     * <p>When the observable value changes, the onMouseExited event handler will be updated to reflect the new EventHandler for mouse-exited events.</p>
     *
     * @param observableValue
     *         The observable value to bind to the onMouseExited property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseExitedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseExited property of the node from its current binding. This will stop the onMouseExited event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseExitedProperty() {
        getNode().onMouseExitedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseExited property of the node and another property. This allows both properties to automatically update each other whenever either of them changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseExitedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseExited property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseExitedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Moved Property

    /**
     * Binds the onMouseMoved property of the node to an observable value. When the observable value changes, the onMouseMoved event handler will be updated to handle mouse-moved events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseMoved property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseMovedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseMoved property of the node. This will stop the onMouseMoved event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseMovedProperty() {
        getNode().onMouseMovedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseMoved property of the node and another property. This ensures that both properties can update each other in response to mouse moved events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseMovedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseMoved property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseMovedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Pressed Property

    /**
     * Binds the onMousePressed property of the node to an observable value. When the observable value changes, the onMousePressed event handler will be updated to handle mouse-pressed events.
     *
     * @param observableValue
     *         The observable value to bind to the onMousePressed property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMousePressedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMousePressed property of the node. This will stop the onMousePressed event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMousePressedProperty() {
        getNode().onMousePressedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMousePressed property of the node and another property. This allows both properties to automatically update each other in response to mouse pressed events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMousePressedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMousePressed property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMousePressedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Released Property

    /**
     * Binds the onMouseReleased property of the node to an observable value. When the observable value changes, the onMouseReleased event handler will be updated to handle mouse-released events.
     *
     * @param observableValue
     *         The observable value to bind to the onMouseReleased property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseReleasedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onMouseReleased property of the node. This will stop the onMouseReleased event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnMouseReleasedProperty() {
        getNode().onMouseReleasedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onMouseReleased property of the node and another property. This ensures that both properties can update each other in response to mouse released events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseReleasedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onMouseReleased property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseReleasedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Rotate Property

    /**
     * Binds the onRotate property of the node to an observable value. When the observable value changes, the onRotate event handler will be updated to reflect the new EventHandler for rotate events.
     *
     * @param observableValue
     *         The observable value to bind to the onRotate property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        getNode().onRotateProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onRotate property of the node from its current binding. This will stop the onRotate event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnRotateProperty() {
        getNode().onRotateProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onRotate property of the node and another property. This allows both properties to automatically update each other whenever either of them changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotateProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onRotate property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotateProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Rotation Finished Property

    /**
     * Binds the onRotationFinished property of the node to an observable value. When the observable value changes, the onRotationFinished event handler will be updated to handle rotation finished events.
     *
     * @param observableValue
     *         The observable value to bind to the onRotationFinished property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnRotationFinishedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        getNode().onRotationFinishedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onRotationFinished property of the node. This will stop the onRotationFinished event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnRotationFinishedProperty() {
        getNode().onRotationFinishedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onRotationFinished property of the node and another property. This ensures that both properties can update each other in response to rotation finished events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotationFinishedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onRotationFinished property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotationFinishedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Rotation Started Property

    /**
     * Binds the onRotationStarted property of the node to an observable value. When the observable value changes, the onRotationStarted event handler will be updated to handle rotation started events.
     *
     * @param observableValue
     *         The observable value to bind to the onRotationStarted property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnRotationStartedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        getNode().onRotationStartedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onRotationStarted property of the node. This will stop the onRotationStarted event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnRotationStartedProperty() {
        getNode().onRotationStartedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onRotationStarted property of the node and another property. This ensures that both properties can update each other in response to rotation started events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotationStartedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onRotationStarted property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotationStartedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Scroll Property

    /**
     * Binds the onScroll property of the node to an observable value. When the observable value changes, the onScroll event handler will be updated to handle scroll events.
     *
     * @param observableValue
     *         The observable value to bind to the onScroll property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        getNode().onScrollProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onScroll property of the node. This will stop the onScroll event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnScrollProperty() {
        getNode().onScrollProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onScroll property of the node and another property. This allows both properties to automatically update each other in response to scroll events.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onScroll property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Scroll Finished Property

    /**
     * Binds the onScrollFinished property of the node to an observable value. When the observable value changes, the onScrollFinished event handler will be updated to reflect the new EventHandler.
     *
     * @param observableValue
     *         The observable value to bind to the onScrollFinished property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnScrollFinishedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        getNode().onScrollFinishedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onScrollFinished property of the node from its current binding. This will stop the onScrollFinished event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnScrollFinishedProperty() {
        getNode().onScrollFinishedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onScrollFinished property of the node and another property. This allows both properties to automatically update each other whenever either of them changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollFinishedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onScrollFinished property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollFinishedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Scroll Started Property

    /**
     * Binds the onScrollStarted property of the node to an observable value. When the observable value changes, the onScrollStarted event handler will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the onScrollStarted property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        getNode().onScrollStartedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onScrollStarted property of the node. This will stop the onScrollStarted event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnScrollStartedProperty() {
        getNode().onScrollStartedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onScrollStarted property of the node and another property. This ensures that both properties can update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollStartedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onScrollStarted property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollStartedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Swipe Down Property

    /**
     * Binds the onSwipeDown property of the node to an observable value. When the observable value changes, the onSwipeDown event handler will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the onSwipeDown property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        getNode().onSwipeDownProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onSwipeDown property of the node. This will stop the onSwipeDown event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnSwipeDownProperty() {
        getNode().onSwipeDownProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onSwipeDown property of the node and another property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeDownProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onSwipeDown property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeDownProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Swipe Left Property

    /**
     * Binds the onSwipeLeft property of the node to an observable value. When the observable value changes, the onSwipeLeft event handler will be updated to handle swipe left gestures.
     *
     * @param observableValue
     *         The observable value to bind to the onSwipeLeft property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        getNode().onSwipeLeftProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onSwipeLeft property of the node. This will stop the onSwipeLeft event handler from updating automatically based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnSwipeLeftProperty() {
        getNode().onSwipeLeftProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onSwipeLeft property of the node and another property. This ensures that both properties can update each other whenever swipe left gestures are detected.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeLeftProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onSwipeLeft property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeLeftProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Swipe Right Property

    /**
     * Binds the onSwipeRight property of the node to an observable value. When the observable value changes, the onSwipeRight event handler will be updated to handle swipe right gestures.
     *
     * @param observableValue
     *         The observable value to bind to the onSwipeRight property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        getNode().onSwipeRightProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onSwipeRight property of the node. This will stop the onSwipeRight event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnSwipeRightProperty() {
        getNode().onSwipeRightProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onSwipeRight property of the node and another property. This allows both properties to automatically update each other in response to swipe right gestures.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeRightProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onSwipeRight property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeRightProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Swipe Up Property

    /**
     * Binds the onSwipeUp property of the node to an observable value. When the observable value changes, the onSwipeUp event handler will be updated to handle swipe up gestures.
     *
     * @param observableValue
     *         The observable value to bind to the onSwipeUp property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        getNode().onSwipeUpProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onSwipeUp property of the node. This will stop the onSwipeUp event handler from updating automatically based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnSwipeUpProperty() {
        getNode().onSwipeUpProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onSwipeUp property of the node and another property. This ensures that both properties can update each other in response to swipe up gestures.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeUpProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onSwipeUp property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeUpProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Touch Moved Property

    /**
     * Binds the onTouchMoved property of the node to an observable value. When the observable value changes, the onTouchMoved event handler will be updated to reflect the new EventHandler.
     *
     * @param observableValue
     *         The observable value to bind to the onTouchMoved property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        getNode().onTouchMovedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onTouchMoved property of the node from its current binding. This will stop the onTouchMoved event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnTouchMovedProperty() {
        getNode().onTouchMovedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onTouchMoved property of the node and another property. This allows both properties to automatically update each other whenever either of them changes.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchMovedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onTouchMoved property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchMovedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Touch Pressed Property

    /**
     * Binds the onTouchPressed property of the node to an observable value. When the observable value changes, the onTouchPressed event handler will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the onTouchPressed property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        getNode().onTouchPressedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onTouchPressed property of the node. This will stop the onTouchPressed event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnTouchPressedProperty() {
        getNode().onTouchPressedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onTouchPressed property of the node and another property. This ensures that both properties can update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchPressedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onTouchPressed property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchPressedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Touch Released Property

    /**
     * Binds the onTouchReleased property of the node to an observable value. When the observable value changes, the onTouchReleased event handler will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the onTouchReleased property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        getNode().onTouchReleasedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onTouchReleased property of the node. This will stop the onTouchReleased event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnTouchReleasedProperty() {
        getNode().onTouchReleasedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onTouchReleased property of the node and another property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchReleasedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onTouchReleased property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchReleasedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Touch Stationary Property

    /**
     * Binds the onTouchStationary property of the node to an observable value. When the observable value changes, the onTouchStationary event handler will be updated.
     *
     * @param observableValue
     *         The observable value to bind to the onTouchStationary property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnTouchStationaryProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        getNode().onTouchStationaryProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onTouchStationary property of the node. This will stop the onTouchStationary event handler from updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnTouchStationaryProperty() {
        getNode().onTouchStationaryProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onTouchStationary property of the node and another property. This ensures that both properties can update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchStationaryProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onTouchStationary property and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchStationaryProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Zoom Property

    /**
     * Binds the onZoom event handler property of the node to an observable value. When the observable value changes, the onZoom event handler will be updated to reflect the new handler.
     *
     * @param observableValue
     *         The observable value to bind to the onZoom event handler property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        getNode().onZoomProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onZoom event handler property of the node from its current binding. This will stop the onZoom event handler from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnZoomProperty() {
        getNode().onZoomProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onZoom event handler property of the node and another property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onZoom event handler property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Zoom Finished Property

    /**
     * Binds the onZoomFinished event handler property of the node to an observable value. This allows dynamic updates to the event handler based on the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to the onZoomFinished event handler property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        getNode().onZoomFinishedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onZoomFinished event handler property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnZoomFinishedProperty() {
        getNode().onZoomFinishedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onZoomFinished event handler property of the node and another property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomFinishedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onZoomFinished event handler property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomFinishedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Zoom Started Property

    /**
     * Binds the onZoomStarted event handler property of the node to an observable value, allowing for the event handler to dynamically update based on the observable value's changes.
     *
     * @param observableValue
     *         The observable value to bind to the onZoomStarted event handler property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        getNode().onZoomStartedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the onZoomStarted event handler property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOnZoomStartedProperty() {
        getNode().onZoomStartedProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the onZoomStarted event handler property of the node and another property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomStartedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the onZoomStarted event handler property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomStartedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Opacity Property

    /**
     * Binds the opacity property of the node to an observable value.
     *
     * <p>When the observable value changes, the opacity property will be updated to reflect the new value. This allows for dynamic updates to the node's opacity, making it more or less transparent.</p>
     *
     * @param observableValue
     *         The observable value to bind to the opacity property. This value should be a number between 0.0 (completely transparent) and 1.0 (completely opaque).
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindOpacityProperty(ObservableValue<? extends Number> observableValue) {
        getNode().opacityProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the opacity property of the node from its current binding. This will stop the opacity property from updating automatically based on the previously bound observable value.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindOpacityProperty() {
        getNode().opacityProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the opacity property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other whenever either of them changes. This is useful for synchronizing the opacity of multiple nodes or for linking the node's opacity to
     * another numeric property.</p>
     *
     * @param otherProperty
     *         The other property to bind with. This property should also be a number between 0.0 and 1.0.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalOpacityProperty(Property<Number> otherProperty) {
        getNode().opacityProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the opacity property of the node and another property. This allows the properties to be independently changed after being unbound.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalOpacityProperty(Property<Number> otherProperty) {
        getNode().opacityProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pick On Bounds Property

    /**
     * Binds the pickOnBounds property of the node to an observable value.
     *
     * <p>When the observable value changes, the pickOnBounds property will be updated to reflect the new value, determining if the node's bounds are used for mouse pick detection.</p>
     *
     * @param observableValue
     *         The observable value to bind to the pickOnBounds property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().pickOnBoundsProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the pickOnBounds property of the node from its current binding. This will stop the pickOnBounds property from updating automatically based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindPickOnBoundsProperty() {
        getNode().pickOnBoundsProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the pickOnBounds property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other to reflect changes in whether the node's bounds are used for mouse pick detection.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalPickOnBoundsProperty(Property<Boolean> otherProperty) {
        getNode().pickOnBoundsProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the pickOnBounds property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalPickOnBoundsProperty(Property<Boolean> otherProperty) {
        getNode().pickOnBoundsProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Rotate Property

    /**
     * Binds the rotate property of the node to an observable value. When the observable value changes, the rotate property will be updated to reflect the new value, affecting the node's rotation angle.
     *
     * @param observableValue
     *         The observable value to bind to the rotate property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindRotateProperty(ObservableValue<? extends Number> observableValue) {
        getNode().rotateProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the rotate property of the node from its current binding. This will stop the rotate property from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindRotateProperty() {
        getNode().rotateProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the rotate property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other to reflect changes in the node's rotation angle.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalRotateProperty(Property<Number> otherProperty) {
        getNode().rotateProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the rotate property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalRotateProperty(Property<Number> otherProperty) {
        getNode().rotateProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Rotation Axis Property

    /**
     * Binds the rotationAxis property of the node to an observable value.
     *
     * <p>When the observable value changes, the rotationAxis property will be updated to reflect the new Point3D value, affecting the axis around which the node rotates.</p>
     *
     * @param observableValue
     *         The observable value to bind to the rotationAxis property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue) {
        getNode().rotationAxisProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the rotationAxis property of the node from its current binding. This will stop the rotationAxis property from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindRotationAxisProperty() {
        getNode().rotationAxisProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the rotationAxis property of the node and another property.
     *
     * <p>This allows both properties to automatically update each other to reflect changes in the axis around which the node rotates.</p>
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalRotationAxisProperty(Property<Point3D> otherProperty) {
        getNode().rotationAxisProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the rotationAxis property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalRotationAxisProperty(Property<Point3D> otherProperty) {
        getNode().rotationAxisProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scale X Property

    /**
     * Binds the scaleX property of the node to an observable value. When the observable value changes, the scaleX property will be updated to reflect the new value.
     *
     * @param observableValue
     *         The observable value to bind to the scaleX property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindScaleXProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scaleXProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the scaleX property of the node from its current binding. This will stop the scaleX property from updating automatically.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindScaleXProperty() {
        getNode().scaleXProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the scaleX property of the node and another property. This allows both properties to automatically update each other.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalScaleXProperty(Property<Number> otherProperty) {
        getNode().scaleXProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the scaleX property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalScaleXProperty(Property<Number> otherProperty) {
        getNode().scaleXProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scale Y Property

    /**
     * Binds the scaleY property of the node to an observable value. When the observable value changes, the scaleY property will be updated to reflect the new value.
     *
     * @param observableValue
     *         The observable value to bind to the scaleY property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindScaleYProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scaleYProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the scaleY property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindScaleYProperty() {
        getNode().scaleYProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the scaleY property of the node and another property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalScaleYProperty(Property<Number> otherProperty) {
        getNode().scaleYProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the scaleY property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalScaleYProperty(Property<Number> otherProperty) {
        getNode().scaleYProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scale Z Property

    /**
     * Binds the scaleZ property of the node to an observable value. When the observable value changes, the scaleZ property will be updated to reflect the new value.
     *
     * @param observableValue
     *         The observable value to bind to the scaleZ property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindScaleZProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scaleZProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the scaleZ property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindScaleZProperty() {
        getNode().scaleZProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the scaleZ property of the node and another property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalScaleZProperty(Property<Number> otherProperty) {
        getNode().scaleZProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the scaleZ property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalScaleZProperty(Property<Number> otherProperty) {
        getNode().scaleZProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Style Property

    /**
     * Binds the style property of the node to an observable value. When the observable value changes, the style property will be updated to reflect the new value.
     *
     * @param observableValue
     *         The observable value to bind to the style property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindStyleProperty(ObservableValue<? extends String> observableValue) {
        getNode().styleProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the style property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindStyleProperty() {
        getNode().styleProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the style property of the node and another property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalStyleProperty(Property<String> otherProperty) {
        getNode().styleProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the style property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalStyleProperty(Property<String> otherProperty) {
        getNode().styleProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Translate X Property

    /**
     * Binds the translateX property of the node to an observable value. When the observable value changes, the translateX property will be updated to reflect the new value.
     *
     * @param observableValue
     *         The observable value to bind to the translateX property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindTranslateXProperty(ObservableValue<? extends Number> observableValue) {
        getNode().translateXProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the translateX property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindTranslateXProperty() {
        getNode().translateXProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the translateY property of the node and another property. When either property changes, the other will be updated to reflect the change.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTranslateXProperty(Property<Number> otherProperty) {
        getNode().translateXProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the translateX property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTranslateXProperty(Property<Number> otherProperty) {
        getNode().translateXProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Translate Y Property

    /**
     * Binds the translateY property of the node to an observable value. When the observable value changes, the translateY property will be updated accordingly.
     *
     * @param observableValue
     *         The observable value to bind to the translateY property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindTranslateYProperty(ObservableValue<? extends Number> observableValue) {
        getNode().translateYProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the translateY property of the node from its current binding. This will cause the translateY property to stop updating based on the previously bound observable.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindTranslateYProperty() {
        getNode().translateYProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the translateY property of the node and another property. When either property changes, the other will be updated to reflect the change.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTranslateYProperty(Property<Number> otherProperty) {
        getNode().translateYProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the translateY property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTranslateYProperty(Property<Number> otherProperty) {
        getNode().translateYProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Translate Z Property

    /**
     * Binds the translateZ property of the node to an observable value. When the observable value changes, the translateZ property will be updated accordingly.
     *
     * @param observableValue
     *         The observable value to bind to the translateZ property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindTranslateZProperty(ObservableValue<? extends Number> observableValue) {
        getNode().translateZProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the translateZ property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindTranslateZProperty() {
        getNode().translateZProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the translateZ property of the node and another property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTranslateZProperty(Property<Number> otherProperty) {
        getNode().translateZProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the translateZ property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTranslateZProperty(Property<Number> otherProperty) {
        getNode().translateZProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // View Order Property

    /**
     * Binds the view order property of the node to an observable value. This determines the rendering order of the node within its parent.
     *
     * @param observableValue
     *         The observable value to bind to the view order property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindViewOrderProperty(ObservableValue<? extends Number> observableValue) {
        getNode().viewOrderProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the view order property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindViewOrderProperty() {
        getNode().viewOrderProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the view order property of the node and another property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalViewOrderProperty(Property<Number> otherProperty) {
        getNode().viewOrderProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the view order property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalViewOrderProperty(Property<Number> otherProperty) {
        getNode().viewOrderProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Visible Property

    /**
     * Binds the visible property of the node to an observable value. This determines whether the node is rendered and can receive user input.
     *
     * @param observableValue
     *         The observable value to bind to the visible property.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindVisibleProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().visibleProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the visible property of the node from its current binding.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindVisibleProperty() {
        getNode().visibleProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Creates a bidirectional binding between the visible property of the node and another property.
     *
     * @param otherProperty
     *         The other property to bind with.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalVisibleProperty(Property<Boolean> otherProperty) {
        getNode().visibleProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Removes a bidirectional binding between the visible property of the node and another property.
     *
     * @param otherProperty
     *         The other property to unbind from.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalVisibleProperty(Property<Boolean> otherProperty) {
        getNode().visibleProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Event Functions
    //*****************************************************************
    // Event Functions
    //*****************************************************************

    /**
     * Fires an {@link Event} on the node. This method can be used to programmatically trigger event handlers attached to the node.
     *
     * @param event
     *         The event to fire.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T fireEvent(Event event) {
        getNode().fireEvent(event);
        return getConfigurator();
    }

    /**
     * Adds an event filter to the node. Event filters are executed before event handlers and can intercept and consume events to prevent them from being processed by handlers.
     *
     * @param <S>
     *         The type of the event.
     * @param eventType
     *         The type of the event this filter is registered for.
     * @param eventFilter
     *         The event filter to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default <S extends Event> T addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        getNode().addEventFilter(eventType, eventFilter);
        return getConfigurator();
    }

    /**
     * Adds an event handler to the node. Event handlers are notified when an event of the specified type occurs, after the event filters have been processed.
     *
     * @param <S>
     *         The type of the event.
     * @param eventType
     *         The type of the event this handler is registered for.
     * @param eventHandler
     *         The event handler to add.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default <S extends Event> T addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        getNode().addEventHandler(eventType, eventHandler);
        return getConfigurator();
    }

    /**
     * Removes an event filter from the node.
     *
     * <p>This method allows for the removal of previously registered event filters.</p>
     *
     * @param <S>
     *         The type of the event.
     * @param eventType
     *         The type of the event the filter is registered for.
     * @param eventFilter
     *         The event filter to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default <S extends Event> T removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        getNode().removeEventFilter(eventType, eventFilter);
        return getConfigurator();
    }

    /**
     * Removes an event handler from the node.
     *
     * <p>This method allows for the removal of previously registered event handlers.</p>
     *
     * @param <S>
     *         The type of the event.
     * @param eventType
     *         The type of the event the handler is registered for.
     * @param eventHandler
     *         The event handler to remove.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default <S extends Event> T removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        getNode().removeEventHandler(eventType, eventHandler);
        return getConfigurator();
    }

    //endregion Event Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the accessible help text for the node. This text is used by screen readers and other accessibility tools to describe the purpose of the node.
     *
     * @param value
     *         The accessible help text to set.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setAccessibleHelp(String value) {
        getNode().setAccessibleHelp(value);
        return getConfigurator();
    }

    /**
     * Sets the accessible role for the node. This role is used by accessibility tools to understand the function of the node within the application.
     *
     * @param value
     *         The {@link AccessibleRole} to set for the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setAccessibleRole(AccessibleRole value) {
        getNode().setAccessibleRole(value);
        return getConfigurator();
    }

    /**
     * Sets the accessible role description for the node. This description provides more detailed information about the node's role, complementing the role itself.
     *
     * @param value
     *         The description of the accessible role.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setAccessibleRoleDescription(String value) {
        getNode().setAccessibleRoleDescription(value);
        return getConfigurator();
    }

    /**
     * Sets the accessible text for the node. This text is used by screen readers to convey the node's content to users with visual impairments.
     *
     * @param value
     *         The accessible text to set.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setAccessibleText(String value) {
        getNode().setAccessibleText(value);
        return getConfigurator();
    }

    /**
     * Sets the blend mode for the node. Blend mode is used to determine how the node's pixels are blended with the underlying pixels in the scene.
     *
     * @param value
     *         The {@link BlendMode} to set for the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setBlendMode(BlendMode value) {
        getNode().setBlendMode(value);
        return getConfigurator();
    }

    /**
     * Sets whether caching is enabled for the node. When caching is enabled, the node is rendered into a bitmap cache, which can improve performance in some scenarios.
     *
     * @param value
     *         {@code true} to enable caching; {@code false} to disable it.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setCache(boolean value) {
        getNode().setCache(value);
        return getConfigurator();
    }

    /**
     * Sets the cache hint for the node. Cache hints can provide rendering hints to the system, which can optimize the caching strategy.
     *
     * @param value
     *         The {@link CacheHint} to use.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setCacheHint(CacheHint value) {
        getNode().setCacheHint(value);
        return getConfigurator();
    }

    /**
     * Sets the clip for the node. The clip is used to define the visible area of the node. Any parts of the node outside the clip will not be visible.
     *
     * @param value
     *         The {@link Node} to use as a clip.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setClip(Node value) {
        getNode().setClip(value);
        return getConfigurator();
    }

    /**
     * Sets the cursor for the node. The cursor is displayed when the mouse hovers over the node.
     *
     * @param value
     *         The {@link Cursor} to display when the mouse hovers over the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setCursor(Cursor value) {
        getNode().setCursor(value);
        return getConfigurator();
    }

    /**
     * Sets the depth test for the node. Depth testing is used to determine the visibility of nodes in a 3D scene relative to the camera view.
     *
     * @param value
     *         The {@link DepthTest} to set for the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setDepthTest(DepthTest value) {
        getNode().setDepthTest(value);
        return getConfigurator();
    }

    /**
     * Sets the disabled state of the node.
     *
     * @param value
     *         If {@code true}, the node (and all its children) is disabled. If {@code false}, the node is enabled.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setDisable(boolean value) {
        getNode().setDisable(value);
        return getConfigurator();
    }

    /**
     * Sets a graphical effect to apply to the node.
     *
     * @param value
     *         The effect to apply, such as a shadow or blur. Can be null for no effect.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setEffect(Effect value) {
        getNode().setEffect(value);
        return getConfigurator();
    }

    /**
     * Sets the event dispatcher for the node.
     *
     * @param value
     *         The new event dispatcher. It can be used to intercept all events that the node would normally receive.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setEventDispatcher(EventDispatcher value) {
        getNode().setEventDispatcher(value);
        return getConfigurator();
    }

    /**
     * Sets whether the node can gain focus via keyboard navigation.
     *
     * @param value
     *         If {@code true}, the node is focus-traversable; if {@code false}, it is not.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setFocusTraversable(boolean value) {
        getNode().setFocusTraversable(value);
        return getConfigurator();
    }

    /**
     * Sets the ID of the node.
     *
     * @param value
     *         A unique string identifier for the node. This can be used for CSS styling and looking up the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setId(String value) {
        getNode().setId(value);
        return getConfigurator();
    }

    /**
     * Sets the input method requests for the node.
     *
     * @param value
     *         An object containing the input method requests, allowing input methods to interact with the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setInputMethodRequests(InputMethodRequests value) {
        getNode().setInputMethodRequests(value);
        return getConfigurator();
    }

    /**
     * Sets the X layout position of the node.
     *
     * @param value
     *         The X coordinate of the node's layout position.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setLayoutX(double value) {
        getNode().setLayoutX(value);
        return getConfigurator();
    }

    /**
     * Sets the Y layout position of the node.
     *
     * @param value
     *         The Y coordinate of the node's layout position.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setLayoutY(double value) {
        getNode().setLayoutY(value);
        return getConfigurator();
    }

    /**
     * Sets the managed state of the node.
     *
     * @param value
     *         If {@code true}, the node's layout is managed by its parent. If {@code false}, the node's layout is not managed by its parent.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setManaged(boolean value) {
        getNode().setManaged(value);
        return getConfigurator();
    }

    /**
     * Sets the mouse transparency of the node.
     *
     * @param value
     *         If {@code true}, the node does not receive mouse events. If {@code false}, the node can receive mouse events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setMouseTransparent(boolean value) {
        getNode().setMouseTransparent(value);
        return getConfigurator();
    }

    /**
     * Sets the node orientation of the node.
     *
     * @param value
     *         The node orientation (LEFT_TO_RIGHT or RIGHT_TO_LEFT).
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setNodeOrientation(NodeOrientation value) {
        getNode().setNodeOrientation(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for context menu requested events on the node.
     *
     * @param value
     *         The event handler for context menu requested events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
        getNode().setOnContextMenuRequested(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for drag detected events on the node.
     *
     * @param value
     *         The event handler for drag detected events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnDragDetected(EventHandler<? super MouseEvent> value) {
        getNode().setOnDragDetected(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for drag done events on the node.
     *
     * @param value
     *         The event handler for drag done events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnDragDone(EventHandler<? super DragEvent> value) {
        getNode().setOnDragDone(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for drag dropped events on the node.
     *
     * @param value
     *         The event handler for drag dropped events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnDragDropped(EventHandler<? super DragEvent> value) {
        getNode().setOnDragDropped(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for drag entered events on the node.
     *
     * @param value
     *         The event handler for drag entered events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnDragEntered(EventHandler<? super DragEvent> value) {
        getNode().setOnDragEntered(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for drag exited events on the node.
     *
     * @param value
     *         The event handler for drag exited events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnDragExited(EventHandler<? super DragEvent> value) {
        getNode().setOnDragExited(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for drag over events on the node.
     *
     * @param value
     *         The event handler for drag over events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnDragOver(EventHandler<? super DragEvent> value) {
        getNode().setOnDragOver(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for input method text changed events on the node.
     *
     * @param value
     *         The event handler for input method text changed events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
        getNode().setOnInputMethodTextChanged(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for key pressed events on the node.
     *
     * @param value
     *         The event handler for key pressed events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnKeyPressed(EventHandler<? super KeyEvent> value) {
        getNode().setOnKeyPressed(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for key typed events on the node.
     *
     * @param value
     *         The event handler for key typed events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnKeyTyped(EventHandler<? super KeyEvent> value) {
        getNode().setOnKeyTyped(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse clicked events on the node.
     *
     * @param value
     *         The event handler for mouse clicked events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseClicked(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseClicked(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse drag entered events on the node.
     *
     * @param value
     *         The event handler for mouse drag entered events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value) {
        getNode().setOnMouseDragEntered(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse drag exited events on the node.
     *
     * @param value
     *         The event handler for mouse drag exited events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseDragExited(EventHandler<? super MouseDragEvent> value) {
        getNode().setOnMouseDragExited(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse drag over events on the node.
     *
     * @param value
     *         The event handler for mouse drag over events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseDragOver(EventHandler<? super MouseDragEvent> value) {
        getNode().setOnMouseDragOver(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse drag released events on the node.
     *
     * @param value
     *         The event handler for mouse drag released events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value) {
        getNode().setOnMouseDragReleased(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse entered events on the node.
     *
     * @param value
     *         The event handler for mouse entered events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseEntered(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseEntered(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse exited events on the node.
     *
     * @param value
     *         The event handler for mouse exited events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseExited(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseExited(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse moved events on the node.
     *
     * @param value
     *         The event handler for mouse moved events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseMoved(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse pressed events on the node.
     *
     * @param value
     *         The event handler for mouse pressed events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMousePressed(EventHandler<? super MouseEvent> value) {
        getNode().setOnMousePressed(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for mouse released events on the node.
     *
     * @param value
     *         The event handler for mouse released events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseReleased(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for rotate events on the node.
     *
     * @param value
     *         The event handler for rotate events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnRotate(EventHandler<? super RotateEvent> value) {
        getNode().setOnRotate(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for rotation finished events on the node.
     *
     * @param value
     *         The event handler for when a rotation gesture is finished.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnRotationFinished(EventHandler<? super RotateEvent> value) {
        getNode().setOnRotationFinished(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for rotation started events on the node.
     *
     * @param value
     *         The event handler for when a rotation gesture starts.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnRotationStarted(EventHandler<? super RotateEvent> value) {
        getNode().setOnRotationStarted(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for scroll events on the node.
     *
     * @param value
     *         The event handler for scroll events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnScroll(EventHandler<? super ScrollEvent> value) {
        getNode().setOnScroll(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for scroll finished events on the node.
     *
     * @param value
     *         The event handler for when a scroll action is finished.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnScrollFinished(EventHandler<? super ScrollEvent> value) {
        getNode().setOnScrollFinished(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for scroll started events on the node.
     *
     * @param value
     *         The event handler for when a scroll action starts.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnScrollStarted(EventHandler<? super ScrollEvent> value) {
        getNode().setOnScrollStarted(value);
        return getConfigurator();
    }

    /**
     * Sets the opacity of the node. The value should be between 0.0 (completely transparent) and 1.0 (completely opaque).
     *
     * @param value
     *         The opacity value.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOpacity(double value) {
        getNode().setOpacity(value);
        return getConfigurator();
    }

    /**
     * Sets the inline CSS style for the node.
     *
     * @param style
     *         A string representing the inline CSS style to apply to the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setStyle(String style) {
        getNode().setStyle(style);
        return getConfigurator();
    }

    /**
     * Sets the pickOnBounds property of the node. When {@code true}, the node's bounds are used for mouse pick detection. When {@code false}, the node's visual bounds are used.
     *
     * @param value
     *         A boolean indicating the desired pickOnBounds state.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setPickOnBounds(boolean value) {
        getNode().setPickOnBounds(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for swipe down gesture events on the node.
     *
     * @param value
     *         The event handler for swipe down gesture events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnSwipeDown(EventHandler<? super SwipeEvent> value) {
        getNode().setOnSwipeDown(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for swipe-left gesture events on the node.
     *
     * @param value
     *         The event handler for swipe left gesture events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
        getNode().setOnSwipeLeft(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for swipe right gesture events on the node.
     *
     * @param value
     *         The event handler for swipe right gesture events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnSwipeRight(EventHandler<? super SwipeEvent> value) {
        getNode().setOnSwipeRight(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for swipe up gesture events on the node.
     *
     * @param value
     *         The event handler for swipe up gesture events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnSwipeUp(EventHandler<? super SwipeEvent> value) {
        getNode().setOnSwipeUp(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for touch moved events on the node.
     *
     * @param value
     *         The event handler for touch moved events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnTouchMoved(EventHandler<? super TouchEvent> value) {
        getNode().setOnTouchMoved(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for touch pressed events on the node.
     *
     * @param value
     *         The event handler for touch pressed events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnTouchPressed(EventHandler<? super TouchEvent> value) {
        getNode().setOnTouchPressed(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for touch released events on the node.
     *
     * @param value
     *         The event handler for touch released events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnTouchReleased(EventHandler<? super TouchEvent> value) {
        getNode().setOnTouchReleased(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for touch stationary events on the node.
     *
     * @param value
     *         The event handler for touch stationary events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnTouchStationary(EventHandler<? super TouchEvent> value) {
        getNode().setOnTouchStationary(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for zoom gesture events on the node.
     *
     * @param value
     *         The event handler for zoom events.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnZoom(EventHandler<? super ZoomEvent> value) {
        getNode().setOnZoom(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for the completion of a zoom gesture on the node.
     *
     * @param value
     *         The event handler for when a zoom gesture is finished.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnZoomFinished(EventHandler<? super ZoomEvent> value) {
        getNode().setOnZoomFinished(value);
        return getConfigurator();
    }

    /**
     * Sets the handler for the start of a zoom gesture on the node.
     *
     * @param value
     *         The event handler for when a zoom gesture starts.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setOnZoomStarted(EventHandler<? super ZoomEvent> value) {
        getNode().setOnZoomStarted(value);
        return getConfigurator();
    }

    /**
     * Sets the rotation angle of the node.
     *
     * @param value
     *         The rotation angle in degrees.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setRotate(double value) {
        getNode().setRotate(value);
        return getConfigurator();
    }

    /**
     * Sets the axis of rotation for the node.
     *
     * @param value
     *         The axis of rotation as a Point3D object.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setRotationAxis(Point3D value) {
        getNode().setRotationAxis(value);
        return getConfigurator();
    }

    /**
     * Sets the scale factor of the node along the X axis.
     *
     * @param value
     *         The scale factor along the X axis.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setScaleX(double value) {
        getNode().setScaleX(value);
        return getConfigurator();
    }

    /**
     * Sets the scale factor of the node along the Y axis.
     *
     * @param value
     *         The scale factor along the Y axis.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setScaleY(double value) {
        getNode().setScaleY(value);
        return getConfigurator();
    }

    /**
     * Sets the scale factor of the node along the Z axis.
     *
     * @param value
     *         The scale factor along the Z axis.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setScaleZ(double value) {
        getNode().setScaleZ(value);
        return getConfigurator();
    }

    /**
     * Sets the translation of the node along the X axis.
     *
     * @param value
     *         The translation distance along the X axis.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setTranslateX(double value) {
        getNode().setTranslateX(value);
        return getConfigurator();
    }

    /**
     * Sets the translation of the node along the Y axis.
     *
     * @param value
     *         The translation distance along the Y axis.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setTranslateY(double value) {
        getNode().setTranslateY(value);
        return getConfigurator();
    }

    /**
     * Sets the translation of the node along the Z axis.
     *
     * @param value
     *         The translation distance along the Z axis.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setTranslateZ(double value) {
        getNode().setTranslateZ(value);
        return getConfigurator();
    }

    /**
     * Associates arbitrary user data with the node.
     *
     * @param value
     *         The user data to associate with the node.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setUserData(Object value) {
        getNode().setUserData(value);
        return getConfigurator();
    }

    /**
     * Sets the rendering order of the node. Nodes with lower values are rendered in front of nodes with higher values.
     *
     * @param value
     *         The rendering order value.
     *
     * @return The configurator instance, for chaining further configuration calls.
     */
    default T setViewOrder(double value) {
        getNode().setViewOrder(value);
        return getConfigurator();
    }

    /**
     * Sets the visibility of the node.
     *
     * @param value
     *         A boolean indicating the desired visibility state.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setVisible(boolean value) {
        getNode().setVisible(value);
        return getConfigurator();
    }

    /**
     * Sets the style class at a specific index.
     *
     * @param index
     *         The index where the style class should be set.
     * @param element
     *         The style class to set at the specified index.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setStyleClass(int index, String element) {
        getNode().getStyleClass()
                 .set(index, element);
        return getConfigurator();
    }

    /**
     * Replaces all style classes with the result of applying a given operator to each.
     *
     * @param operator
     *         A unary operator to apply to each existing style class.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T replaceAllStyleClasses(UnaryOperator<String> operator) {
        getNode().getStyleClass()
                 .replaceAll(operator);
        return getConfigurator();
    }

    /**
     * Sets all style classes, replacing any existing ones.
     *
     * @param elements
     *         An array of style classes to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setAllStyleClasses(String... elements) {
        getNode().getStyleClass()
                 .setAll(elements);
        return getConfigurator();
    }

    /**
     * Sets all style classes, replacing any existing ones, from a collection.
     *
     * @param col
     *         A collection of style classes to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setAllStyleClasses(Collection<? extends String> col) {
        getNode().getStyleClass()
                 .setAll(col);
        return getConfigurator();
    }

    /**
     * Sets a transform at a specific index.
     *
     * @param index
     *         The index where the transform should be set.
     * @param element
     *         The transform to set at the specified index.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setTransform(int index, Transform element) {
        getNode().getTransforms()
                 .set(index, element);
        return getConfigurator();
    }

    /**
     * Replaces all transforms with the result of applying a given operator to each.
     *
     * @param operator
     *         An unary operator to apply to each existing transform.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T replaceAllTransforms(UnaryOperator<Transform> operator) {
        getNode().getTransforms()
                 .replaceAll(operator);
        return getConfigurator();
    }

    /**
     * Sets all transforms, replacing any existing ones.
     *
     * @param elements
     *         An array of transforms to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setAllTransforms(Transform... elements) {
        getNode().getTransforms()
                 .setAll(elements);
        return getConfigurator();
    }

    /**
     * Sets all transforms, replacing any existing ones, from a collection.
     *
     * @param col
     *         A collection of transforms to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setAllTransforms(Collection<? extends Transform> col) {
        getNode().getTransforms()
                 .setAll(col);
        return getConfigurator();
    }

    //endregion Set Functions

    //region Add Style Functions
    //*****************************************************************
    // Add Style Functions
    //*****************************************************************

    /**
     * Adds a style class at the beginning of the style class list.
     *
     * @param styleClass
     *         The style class to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addFirstStyleClass(String styleClass) {
        getNode().getStyleClass()
                 .addFirst(styleClass);
        return getConfigurator();
    }

    /**
     * Adds a style class at the end of the style class list.
     *
     * @param styleClass
     *         The style class to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLastStyleClass(String styleClass) {
        getNode().getStyleClass()
                 .addLast(styleClass);
        return getConfigurator();
    }

    /**
     * Adds a style class to the style class list.
     *
     * @param styleClass
     *         The style class to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addStyleClass(String styleClass) {
        getNode().getStyleClass()
                 .add(styleClass);
        return getConfigurator();
    }

    /**
     * Adds a style class at a specific index in the style class list.
     *
     * @param index
     *         The index at which the style class should be added.
     * @param styleClass
     *         The style class to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addStyleClass(int index, String styleClass) {
        getNode().getStyleClass()
                 .add(index, styleClass);
        return getConfigurator();
    }

    /**
     * Adds all specified style classes to the style class list.
     *
     * @param styleClasses
     *         An array of style classes to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllStyleClasses(String... styleClasses) {
        getNode().getStyleClass()
                 .addAll(styleClasses);
        return getConfigurator();
    }

    /**
     * Adds all specified style classes from a collection to the style class list.
     *
     * @param c
     *         A collection containing the style classes to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllStyleClasses(Collection<? extends String> c) {
        getNode().getStyleClass()
                 .addAll(c);
        return getConfigurator();
    }

    /**
     * Adds all specified style classes from a collection at a specific index in the style class list.
     *
     * @param index
     *         The index at which the style classes should be added.
     * @param c
     *         A collection containing the style classes to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllStyleClasses(int index, Collection<? extends String> c) {
        getNode().getStyleClass()
                 .addAll(index, c);
        return getConfigurator();
    }

    //endregion Add Style Functions

    //region Remove Style Functions
    //*****************************************************************
    // Remove Style Functions
    //*****************************************************************

    /**
     * Removes the first style class from the style class list.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeFirstStyleClass() {
        getNode().getStyleClass()
                 .removeFirst();
        return getConfigurator();
    }

    /**
     * Removes the last style class from the style class list.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLastStyleClass() {
        getNode().getStyleClass()
                 .removeLast();
        return getConfigurator();
    }

    /**
     * Removes a specific style class from the style class list.
     *
     * @param styleClass
     *         The style class to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeStyleClass(String styleClass) {
        getNode().getStyleClass()
                 .remove(styleClass);
        return getConfigurator();
    }

    /**
     * Removes style classes within a specified range in the style class list.
     *
     * @param from
     *         The starting index (inclusive).
     * @param to
     *         The ending index (exclusive).
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeStyleClasses(int from, int to) {
        getNode().getStyleClass()
                 .remove(from, to);
        return getConfigurator();
    }

    /**
     * Removes style classes that match a given filter.
     *
     * @param filter
     *         A predicate to determine which style classes should be removed.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeStyleClassesIf(Predicate<? super String> filter) {
        getNode().getStyleClass()
                 .removeIf(filter);
        return getConfigurator();
    }

    /**
     * Removes all the specified style classes from the node. This method accepts an array of strings, where each string represents a style class that should be removed from this node's style class list.
     *
     * @param styleClasses
     *         An array of strings where each string is a style class to be removed. Must not be null, but may be empty.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAllStyleClasses(String... styleClasses) {
        getNode().getStyleClass()
                 .removeAll(styleClasses);
        return getConfigurator();
    }

    /**
     * Removes all style classes from the node that are contained within the provided collection.
     *
     * <p>This method is useful when you have a collection of style classes to be removed and prefer to work with Collections rather than arrays.</p>
     *
     * @param c
     *         A collection containing the style classes to be removed. This collection may contain any type of object, but only {@code String} instances will be considered. Must not be null, but may be empty.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAllStyleClasses(Collection<? extends String> c) {
        getNode().getStyleClass()
                 .removeAll(c);
        return getConfigurator();
    }

    //endregion Remove Style Functions

    //region Style Functions
    //*****************************************************************
    // Style Functions
    //*****************************************************************

    /**
     * Changes the state of a specified pseudo-class for this node.
     *
     * <p>This method is used to programmatically change the state of a pseudo-class (e.g., hover, active) on the node. This can be useful for applying style changes based on user actions or other events.</p>
     *
     * @param pseudoClass
     *         The pseudo-class to change.
     * @param active
     *         {@code true} to activate the pseudo-class, {@code false} to deactivate it.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T pseudoClassStateChange(PseudoClass pseudoClass, boolean active) {
        getNode().pseudoClassStateChanged(pseudoClass, active);
        return getConfigurator();
    }

    /**
     * Applies CSS styling to this node.
     *
     * <p>This method is used to apply CSS styling to the node, based on the current stylesheets assigned to the node or its scene. This can be particularly useful when stylesheets are dynamically added or
     * removed, or when pseudo-class states change.</p>
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T applyCss() {
        getNode().applyCss();
        return getConfigurator();
    }

    //endregion Style Functions

    //region Add Transform Functions
    //*****************************************************************
    // Add Transform Functions
    //*****************************************************************

    /**
     * Adds a {@link Transform} to the beginning of this node's transform list.
     *
     * @param transform
     *         The transform to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addFirstTransform(Transform transform) {
        getNode().getTransforms()
                 .addFirst(transform);
        return getConfigurator();
    }

    /**
     * Adds a {@link Transform} to the end of this node's transform list.
     *
     * @param transform
     *         The transform to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLastTransform(Transform transform) {
        getNode().getTransforms()
                 .addLast(transform);
        return getConfigurator();
    }

    /**
     * Adds a {@link Transform} to this node's transform list.
     *
     * @param transform
     *         The transform to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTransform(Transform transform) {
        getNode().getTransforms()
                 .add(transform);
        return getConfigurator();
    }

    /**
     * Adds a {@link Transform} at a specified index in this node's transform list.
     *
     * @param index
     *         The index at which the transform should be added.
     * @param transform
     *         The transform to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTransform(int index, Transform transform) {
        getNode().getTransforms()
                 .add(index, transform);
        return getConfigurator();
    }

    /**
     * Adds all the specified {@link Transform} objects to the end of this node's transform list.
     *
     * @param transforms
     *         The transforms to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllTransforms(Transform... transforms) {
        getNode().getTransforms()
                 .addAll(transforms);
        return getConfigurator();
    }

    /**
     * Adds all the {@link Transform} objects from the specified collection to the end of this node's transform list.
     *
     * @param c
     *         The collection of transforms to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllTransforms(Collection<? extends Transform> c) {
        getNode().getTransforms()
                 .addAll(c);
        return getConfigurator();
    }

    /**
     * Inserts all of the {@link Transform} objects from the specified collection at the specified position in this node's transform list.
     *
     * @param index
     *         The index at which the collection should be inserted.
     * @param c
     *         The collection of transforms to insert.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAllTransforms(int index, Collection<? extends Transform> c) {
        getNode().getTransforms()
                 .addAll(index, c);
        return getConfigurator();
    }

    //endregion Add Transform Functions

    //region Remove Transform Functions
    //*****************************************************************
    // Remove Transform Functions
    //*****************************************************************

    /**
     * Removes the first transform from this node's transform list.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeFirstTransform() {
        getNode().getTransforms()
                 .removeFirst();
        return getConfigurator();
    }

    /**
     * Removes the last transform from this node's transform list.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLastTransform() {
        getNode().getTransforms()
                 .removeLast();
        return getConfigurator();
    }

    /**
     * Removes a specific transform from this node's transform list.
     *
     * @param transform
     *         The transform to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTransform(Transform transform) {
        getNode().getTransforms()
                 .remove(transform);
        return getConfigurator();
    }

    /**
     * Removes a range of transforms from this node's transform list, from the specified start index (inclusive) to the end index (exclusive).
     *
     * @param from
     *         The start index, inclusive.
     * @param to
     *         The end index, exclusive.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTransforms(int from, int to) {
        getNode().getTransforms()
                 .remove(from, to);
        return getConfigurator();
    }

    /**
     * Removes all the transforms that satisfy the given predicate from this node's transform list.
     *
     * @param filter
     *         The predicate to apply to each transform.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTransformsIf(Predicate<? super Transform> filter) {
        getNode().getTransforms()
                 .removeIf(filter);
        return getConfigurator();
    }

    /**
     * Removes all the specified transforms from this node's transform list.
     *
     * @param transforms
     *         The transforms to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAllTransforms(Transform... transforms) {
        getNode().getTransforms()
                 .removeAll(transforms);
        return getConfigurator();
    }

    /**
     * Removes all the transforms from this node's transform list that are contained in the specified collection.
     *
     * @param c
     *         The collection of transforms to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAllTransforms(Collection<? extends Transform> c) {
        getNode().getTransforms()
                 .removeAll(c);
        return getConfigurator();
    }

    //endregion Remove Transform Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    /**
     * Sends this node to the back of its sibling nodes in terms of z-order.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T toBack() {
        getNode().toBack();
        return getConfigurator();
    }

    /**
     * Brings this node to the front of its sibling nodes in terms of z-order.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T toFront() {
        getNode().toFront();
        return getConfigurator();
    }

    /**
     * Resizes the node to the specified width and height. This method is used to directly set the size of a node, overriding its current size.
     *
     * @param width
     *         The new width of the node.
     * @param height
     *         The new height of the node.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T resize(double width, double height) {
        getNode().resize(width, height);
        return getConfigurator();
    }

    /**
     * Invokes the node's internal layout logic to set its size to its preferred size. This method can be used when the node's size should be automatically determined by its own layout constraints.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T autosize() {
        getNode().autosize();
        return getConfigurator();
    }

    /**
     * Resizes and relocates the node to the specified x, y, width, and height. This method is particularly useful for manual layout management, allowing precise control over the node's position and size.
     *
     * @param x
     *         The new x coordinate of the node.
     * @param y
     *         The new y coordinate of the node.
     * @param width
     *         The new width of the node.
     * @param height
     *         The new height of the node.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T resizeRelocate(double x, double y, double width, double height) {
        getNode().resizeRelocate(x, y, width, height);
        return getConfigurator();
    }

    /**
     * Requests that this node gets the input focus. This method is used to request focus for the node, which can be useful for directing user input to a specific element.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T requestFocus() {
        getNode().requestFocus();
        return getConfigurator();
    }

    //endregion Layout Functions
}
