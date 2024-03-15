package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.scene.NodeConfigurator;
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

public interface NodeConfig<T extends NodeConfigurator> extends BaseConfig<T> {
    @Override
    T getConfigurator();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleHelpProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleHelpProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleRoleDescriptionProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleRoleDescriptionProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        getNode().accessibleRoleProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleRoleProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        getNode().blendModeProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().blendModeProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().boundsInLocalProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        getNode().boundsInLocalProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().boundsInParentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().boundsInParentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        getNode().cacheHintProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheHintProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cacheProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addCacheInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addClipChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().clipProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addClipInvalidationListener(InvalidationListener invalidationListener) {
        getNode().clipProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        getNode().cursorProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addCursorInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cursorProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        getNode().depthTestProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        getNode().depthTestProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().disabledProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addDisabledInvalidationListener(InvalidationListener invalidationListener) {
        getNode().disabledProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().disableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addDisableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().disableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        getNode().effectiveNodeOrientationProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        getNode().effectiveNodeOrientationProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        getNode().effectProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addEffectInvalidationListener(InvalidationListener invalidationListener) {
        getNode().effectProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        getNode().eventDispatcherProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        getNode().eventDispatcherProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addFocusedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusTraversableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusTraversableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusVisibleProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusVisibleProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusTraversableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusTraversableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().hoverProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addHoverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().hoverProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addIdChangeListener(ChangeListener<? super String> changeListener) {
        getNode().idProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addIdInvalidationListener(InvalidationListener invalidationListener) {
        getNode().idProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        getNode().inputMethodRequestsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().inputMethodRequestsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().layoutBoundsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutBoundsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().layoutXProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutXProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().layoutYProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutYProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        getNode().localToParentTransformProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        getNode().localToParentTransformProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        getNode().localToSceneTransformProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        getNode().localToSceneTransformProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().managedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addManagedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().managedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().mouseTransparentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().mouseTransparentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        getNode().nodeOrientationProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        getNode().nodeOrientationProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        getNode().onContextMenuRequestedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onContextMenuRequestedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onDragDetectedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDetectedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragDoneProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDoneProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragDroppedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDroppedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragEnteredProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragEnteredProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragExitedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragExitedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragOverProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragOverProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        getNode().onInputMethodTextChangedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onInputMethodTextChangedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyPressedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyPressedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyReleasedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyReleasedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyTypedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyTypedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseClickedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseClickedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragEnteredProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragEnteredProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragExitedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragExitedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseDraggedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDraggedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragOverProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragOverProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragReleasedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragReleasedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseEnteredProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseEnteredProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseExitedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseExitedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseMovedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseMovedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMousePressedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMousePressedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseReleasedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseReleasedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotateProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotateProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotationFinishedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotationFinishedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotationStartedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotationStartedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollFinishedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollFinishedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollStartedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollStartedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeDownProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeDownProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeLeftProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeLeftProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeRightProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeRightProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeUpProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeUpProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchMovedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchMovedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchPressedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchPressedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchReleasedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchReleasedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchStationaryProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchStationaryProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomFinishedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomFinishedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomStartedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomStartedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().opacityProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOpacityInvalidationListener(InvalidationListener invalidationListener) {
        getNode().opacityProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addParentChangeListener(ChangeListener<? super Parent> changeListener) {
        getNode().parentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addParentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().parentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().pickOnBoundsProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().pickOnBoundsProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addPressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().pressedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().pressedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        getNode().sceneProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addSceneInvalidationListener(InvalidationListener invalidationListener) {
        getNode().sceneProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addRotateChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().rotateProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addRotateInvalidationListener(InvalidationListener invalidationListener) {
        getNode().rotateProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        getNode().rotationAxisProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        getNode().rotationAxisProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleXProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addScaleXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleXProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleYProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addScaleYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleYProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleZProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addScaleZInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleZProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addStyleChangeListener(ChangeListener<? super String> changeListener) {
        getNode().styleProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addStyleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().styleProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        getNode().getStyleClass()
                 .addListener(listChangeListener);
        return getConfigurator();
    }

    default T addStyleListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getStyleClass()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        getNode().getTransforms()
                 .addListener(listChangeListener);
        return getConfigurator();
    }

    default T addTransformListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getTransforms()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateXProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateXProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateYProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateYProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateZProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateZProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().viewOrderProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        getNode().viewOrderProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().visibleProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

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

    default T removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleHelpProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleHelpProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleRoleDescriptionProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleRoleDescriptionProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        getNode().accessibleRoleProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleRoleProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().accessibleTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().accessibleTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        getNode().blendModeProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        getNode().blendModeProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().boundsInLocalProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        getNode().boundsInLocalProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().boundsInParentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().boundsInParentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        getNode().cacheHintProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheHintProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cacheProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeCacheInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cacheProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeClipChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().clipProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeClipInvalidationListener(InvalidationListener invalidationListener) {
        getNode().clipProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        getNode().cursorProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeCursorInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cursorProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        getNode().depthTestProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        getNode().depthTestProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().disabledProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeDisabledInvalidationListener(InvalidationListener invalidationListener) {
        getNode().disabledProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().disableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeDisableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().disableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        getNode().effectiveNodeOrientationProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        getNode().effectiveNodeOrientationProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        getNode().effectProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeEffectInvalidationListener(InvalidationListener invalidationListener) {
        getNode().effectProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        getNode().eventDispatcherProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        getNode().eventDispatcherProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeFocusedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusTraversableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusTraversableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusVisibleProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusVisibleProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().focusWithinProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        getNode().focusWithinProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().hoverProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeHoverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().hoverProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeIdChangeListener(ChangeListener<? super String> changeListener) {
        getNode().idProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeIdInvalidationListener(InvalidationListener invalidationListener) {
        getNode().idProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        getNode().inputMethodRequestsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().inputMethodRequestsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        getNode().layoutBoundsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutBoundsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().layoutXProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutXProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().layoutYProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().layoutYProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        getNode().localToParentTransformProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        getNode().localToParentTransformProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        getNode().localToSceneTransformProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        getNode().localToSceneTransformProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().managedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeManagedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().managedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().mouseTransparentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().mouseTransparentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        getNode().nodeOrientationProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        getNode().nodeOrientationProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        getNode().onContextMenuRequestedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onContextMenuRequestedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onDragDetectedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDetectedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragDoneProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDoneProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragDroppedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragDroppedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragEnteredProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragEnteredProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragExitedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragExitedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        getNode().onDragOverProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onDragOverProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        getNode().onInputMethodTextChangedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onInputMethodTextChangedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyPressedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyPressedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyReleasedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyReleasedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        getNode().onKeyTypedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onKeyTypedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseClickedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseClickedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragEnteredProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragEnteredProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragExitedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragExitedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseDraggedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragOverProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragOverProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        getNode().onMouseDragReleasedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseDragReleasedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseEnteredProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseEnteredProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseExitedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseExitedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseMovedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseMovedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMousePressedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMousePressedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        getNode().onMouseReleasedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onMouseReleasedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotateProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotateProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotationFinishedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotationFinishedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        getNode().onRotationStartedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onRotationStartedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollStartedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollStartedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        getNode().onScrollFinishedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onScrollFinishedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeDownProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeDownProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeLeftProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeLeftProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeRightProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeRightProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        getNode().onSwipeUpProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onSwipeUpProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchMovedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchMovedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchPressedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchPressedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchReleasedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchReleasedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        getNode().onTouchStationaryProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onTouchStationaryProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomStartedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomStartedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        getNode().onZoomFinishedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onZoomFinishedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().opacityProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOpacityInvalidationListener(InvalidationListener invalidationListener) {
        getNode().opacityProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeParentChangeListener(ChangeListener<? super Parent> changeListener) {
        getNode().parentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeParentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().parentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().pickOnBoundsProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        getNode().pickOnBoundsProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removePressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().pressedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removePressedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().pressedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        getNode().sceneProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeSceneInvalidationListener(InvalidationListener invalidationListener) {
        getNode().sceneProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeRotateChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().rotateProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeRotateInvalidationListener(InvalidationListener invalidationListener) {
        getNode().rotateProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        getNode().rotationAxisProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        getNode().rotationAxisProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleXProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeScaleXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleXProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleYProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeScaleYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleYProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scaleZProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeScaleZInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scaleZProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeStyleChangeListener(ChangeListener<? super String> changeListener) {
        getNode().styleProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeStyleInvalidationListener(InvalidationListener invalidationListener) {
        getNode().styleProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        getNode().getStyleClass()
                 .removeListener(listChangeListener);
        return getConfigurator();
    }

    default T removeStyleListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getStyleClass()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        getNode().getTransforms()
                 .removeListener(listChangeListener);
        return getConfigurator();
    }

    default T removeTransformListInvalidationListener(InvalidationListener invalidationListener) {
        getNode().getTransforms()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateXProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateXProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateYProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateYProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().translateZProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        getNode().translateZProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().viewOrderProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        getNode().viewOrderProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().visibleProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

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

    default T bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue) {
        getNode().accessibleHelpProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindAccessibleHelpProperty() {
        getNode().accessibleHelpProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalAccessibleHelpProperty(Property<String> otherProperty) {
        getNode().accessibleHelpProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalAccessibleHelpProperty(Property<String> otherProperty) {
        getNode().accessibleHelpProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Accessible Role Description Property

    default T bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue) {
        getNode().accessibleRoleDescriptionProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindAccessibleRoleDescriptionProperty() {
        getNode().accessibleRoleDescriptionProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> otherProperty) {
        getNode().accessibleRoleDescriptionProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> otherProperty) {
        getNode().accessibleRoleDescriptionProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Accessible Role Property

    default T bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue) {
        getNode().accessibleRoleProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindAccessibleRoleProperty() {
        getNode().accessibleRoleProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> otherProperty) {
        getNode().accessibleRoleProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> otherProperty) {
        getNode().accessibleRoleProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Accessible Text Property

    default T bindAccessibleTextProperty(ObservableValue<? extends String> observableValue) {
        getNode().accessibleTextProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindAccessibleTextProperty() {
        getNode().accessibleTextProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalAccessibleTextProperty(Property<String> otherProperty) {
        getNode().accessibleTextProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalAccessibleTextProperty(Property<String> otherProperty) {
        getNode().accessibleTextProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Blend Mode Property

    default T bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue) {
        getNode().blendModeProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindBlendModeProperty() {
        getNode().blendModeProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalBlendModeProperty(Property<BlendMode> otherProperty) {
        getNode().blendModeProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalBlendModeProperty(Property<BlendMode> otherProperty) {
        getNode().blendModeProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cache Hint Property

    default T bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue) {
        getNode().cacheHintProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindCacheHintProperty() {
        getNode().cacheHintProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalCacheHintProperty(Property<CacheHint> otherProperty) {
        getNode().cacheHintProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalCacheHintProperty(Property<CacheHint> otherProperty) {
        getNode().cacheHintProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cache Property

    default T bindCacheProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().cacheProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindCacheProperty() {
        getNode().cacheProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalCacheProperty(Property<Boolean> otherProperty) {
        getNode().cacheProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalCacheProperty(Property<Boolean> otherProperty) {
        getNode().cacheProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Clip Property

    default T bindClipProperty(ObservableValue<? extends Node> observableValue) {
        getNode().clipProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindClipProperty() {
        getNode().clipProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalClipProperty(Property<Node> otherProperty) {
        getNode().clipProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalClipProperty(Property<Node> otherProperty) {
        getNode().clipProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cursor Property

    default T bindCursorProperty(ObservableValue<? extends Cursor> observableValue) {
        getNode().cursorProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindCursorProperty() {
        getNode().cursorProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalCursorProperty(Property<Cursor> otherProperty) {
        getNode().cursorProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalCursorProperty(Property<Cursor> otherProperty) {
        getNode().cursorProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Depth Test Property

    default T bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue) {
        getNode().depthTestProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindDepthTestProperty() {
        getNode().depthTestProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalDepthTestProperty(Property<DepthTest> otherProperty) {
        getNode().depthTestProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalDepthTestProperty(Property<DepthTest> otherProperty) {
        getNode().depthTestProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Disable Property
    default T bindDisableProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().disableProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindDisableProperty() {
        getNode().disableProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalDisableProperty(Property<Boolean> otherProperty) {
        getNode().disableProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalDisableProperty(Property<Boolean> otherProperty) {
        getNode().disableProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Effect Property
    default T bindEffectProperty(ObservableValue<? extends Effect> observableValue) {
        getNode().effectProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindEffectProperty() {
        getNode().effectProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalEffectProperty(Property<Effect> otherProperty) {
        getNode().effectProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalEffectProperty(Property<Effect> otherProperty) {
        getNode().effectProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Event Dispatcher Property
    default T bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue) {
        getNode().eventDispatcherProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindEventDispatcherProperty() {
        getNode().eventDispatcherProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> otherProperty) {
        getNode().eventDispatcherProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> otherProperty) {
        getNode().eventDispatcherProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Focus Traversable Property
    default T bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().focusTraversableProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindFocusTraversableProperty() {
        getNode().focusTraversableProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalFocusTraversableProperty(Property<Boolean> otherProperty) {
        getNode().focusTraversableProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalFocusTraversableProperty(Property<Boolean> otherProperty) {
        getNode().focusTraversableProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // ID Property
    default T bindIdProperty(ObservableValue<? extends String> observableValue) {
        getNode().idProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindIdProperty() {
        getNode().idProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalIdProperty(Property<String> otherProperty) {
        getNode().idProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalIdProperty(Property<String> otherProperty) {
        getNode().idProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Input Method Requests Property
    default T bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue) {
        getNode().inputMethodRequestsProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindInputMethodRequestsProperty() {
        getNode().inputMethodRequestsProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> otherProperty) {
        getNode().inputMethodRequestsProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> otherProperty) {
        getNode().inputMethodRequestsProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Layout X Property
    default T bindLayoutXProperty(ObservableValue<? extends Number> observableValue) {
        getNode().layoutXProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindLayoutXProperty() {
        getNode().layoutXProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalLayoutXProperty(Property<Number> otherProperty) {
        getNode().layoutXProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalLayoutXProperty(Property<Number> otherProperty) {
        getNode().layoutXProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Layout Y Property
    default T bindLayoutYProperty(ObservableValue<? extends Number> observableValue) {
        getNode().layoutYProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindLayoutYProperty() {
        getNode().layoutYProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalLayoutYProperty(Property<Number> otherProperty) {
        getNode().layoutYProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalLayoutYProperty(Property<Number> otherProperty) {
        getNode().layoutYProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Managed Property
    default T bindManagedProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().managedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindManagedProperty() {
        getNode().managedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalManagedProperty(Property<Boolean> otherProperty) {
        getNode().managedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalManagedProperty(Property<Boolean> otherProperty) {
        getNode().managedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Mouse Transparent Property
    default T bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().mouseTransparentProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindMouseTransparentProperty() {
        getNode().mouseTransparentProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalMouseTransparentProperty(Property<Boolean> otherProperty) {
        getNode().mouseTransparentProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalMouseTransparentProperty(Property<Boolean> otherProperty) {
        getNode().mouseTransparentProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Node Orientation Property
    default T bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue) {
        getNode().nodeOrientationProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindNodeOrientationProperty() {
        getNode().nodeOrientationProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> otherProperty) {
        getNode().nodeOrientationProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> otherProperty) {
        getNode().nodeOrientationProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Context Menu Requested Property
    default T bindOnContextMenuRequestedProperty(ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue) {
        getNode().onContextMenuRequestedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnContextMenuRequestedProperty() {
        getNode().onContextMenuRequestedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> otherProperty) {
        getNode().onContextMenuRequestedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> otherProperty) {
        getNode().onContextMenuRequestedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Detected Property
    default T bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onDragDetectedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnDragDetectedProperty() {
        getNode().onDragDetectedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onDragDetectedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onDragDetectedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Done Property
    default T bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragDoneProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnDragDoneProperty() {
        getNode().onDragDoneProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragDoneProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragDoneProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Dropped Property
    default T bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragDroppedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnDragDroppedProperty() {
        getNode().onDragDroppedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragDroppedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragDroppedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Entered Property
    default T bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragEnteredProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnDragEnteredProperty() {
        getNode().onDragEnteredProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragEnteredProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragEnteredProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Exited Property
    default T bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragExitedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnDragExitedProperty() {
        getNode().onDragExitedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragExitedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragExitedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Drag Over Property
    default T bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        getNode().onDragOverProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnDragOverProperty() {
        getNode().onDragOverProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragOverProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> otherProperty) {
        getNode().onDragOverProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Input Method Text Changed Property
    default T bindOnInputMethodTextChangedProperty(ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue) {
        getNode().onInputMethodTextChangedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnInputMethodTextChangedProperty() {
        getNode().onInputMethodTextChangedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> otherProperty) {
        getNode().onInputMethodTextChangedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> otherProperty) {
        getNode().onInputMethodTextChangedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Key Pressed Property
    default T bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        getNode().onKeyPressedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnKeyPressedProperty() {
        getNode().onKeyPressedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyPressedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyPressedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Key Released Property
    default T bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        getNode().onKeyReleasedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnKeyReleasedProperty() {
        getNode().onKeyReleasedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyReleasedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyReleasedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Key Typed Property
    default T bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        getNode().onKeyTypedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnKeyTypedProperty() {
        getNode().onKeyTypedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyTypedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> otherProperty) {
        getNode().onKeyTypedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Clicked Property
    default T bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseClickedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseClickedProperty() {
        getNode().onMouseClickedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseClickedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseClickedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Drag Entered Property
    default T bindOnMouseDragEnteredProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        getNode().onMouseDragEnteredProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseDragEnteredProperty() {
        getNode().onMouseDragEnteredProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragEnteredProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragEnteredProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Drag Exited Property
    default T bindOnMouseDragExitedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        getNode().onMouseDragExitedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseDragExitedProperty() {
        getNode().onMouseDragExitedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragExitedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragExitedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Dragged Property
    default T bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseDraggedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseDraggedProperty() {
        getNode().onMouseDraggedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseDraggedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseDraggedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Drag Over Property
    default T bindOnMouseDragOverProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        getNode().onMouseDragOverProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseDragOverProperty() {
        getNode().onMouseDragOverProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragOverProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragOverProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Drag Released Property
    default T bindOnMouseDragReleasedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        getNode().onMouseDragReleasedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseDragReleasedProperty() {
        getNode().onMouseDragReleasedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragReleasedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> otherProperty) {
        getNode().onMouseDragReleasedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Entered Property
    default T bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseEnteredProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseEnteredProperty() {
        getNode().onMouseEnteredProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseEnteredProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseEnteredProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Exited Property
    default T bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseExitedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseExitedProperty() {
        getNode().onMouseExitedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseExitedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseExitedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Moved Property
    default T bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseMovedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseMovedProperty() {
        getNode().onMouseMovedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseMovedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseMovedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Pressed Property
    default T bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMousePressedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMousePressedProperty() {
        getNode().onMousePressedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMousePressedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMousePressedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Mouse Released Property
    default T bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        getNode().onMouseReleasedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnMouseReleasedProperty() {
        getNode().onMouseReleasedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseReleasedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> otherProperty) {
        getNode().onMouseReleasedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Rotate Property
    default T bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        getNode().onRotateProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnRotateProperty() {
        getNode().onRotateProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotateProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotateProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Rotation Finished Property
    default T bindOnRotationFinishedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        getNode().onRotationFinishedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnRotationFinishedProperty() {
        getNode().onRotationFinishedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotationFinishedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotationFinishedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Rotation Started Property
    default T bindOnRotationStartedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        getNode().onRotationStartedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnRotationStartedProperty() {
        getNode().onRotationStartedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotationStartedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> otherProperty) {
        getNode().onRotationStartedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Scroll Property
    default T bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        getNode().onScrollProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnScrollProperty() {
        getNode().onScrollProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Scroll Finished Property
    default T bindOnScrollFinishedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        getNode().onScrollFinishedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnScrollFinishedProperty() {
        getNode().onScrollFinishedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollFinishedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollFinishedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Scroll Started Property
    default T bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        getNode().onScrollStartedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnScrollStartedProperty() {
        getNode().onScrollStartedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollStartedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> otherProperty) {
        getNode().onScrollStartedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Swipe Down Property
    default T bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        getNode().onSwipeDownProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnSwipeDownProperty() {
        getNode().onSwipeDownProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeDownProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeDownProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Swipe Left Property
    default T bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        getNode().onSwipeLeftProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnSwipeLeftProperty() {
        getNode().onSwipeLeftProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeLeftProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeLeftProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Swipe Right Property
    default T bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        getNode().onSwipeRightProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnSwipeRightProperty() {
        getNode().onSwipeRightProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeRightProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeRightProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Swipe Up Property
    default T bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        getNode().onSwipeUpProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnSwipeUpProperty() {
        getNode().onSwipeUpProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeUpProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> otherProperty) {
        getNode().onSwipeUpProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Touch Moved Property
    default T bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        getNode().onTouchMovedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnTouchMovedProperty() {
        getNode().onTouchMovedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchMovedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchMovedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Touch Pressed Property
    default T bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        getNode().onTouchPressedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnTouchPressedProperty() {
        getNode().onTouchPressedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchPressedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchPressedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Touch Released Property
    default T bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        getNode().onTouchReleasedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnTouchReleasedProperty() {
        getNode().onTouchReleasedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchReleasedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchReleasedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Touch Stationary Property
    default T bindOnTouchStationaryProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        getNode().onTouchStationaryProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnTouchStationaryProperty() {
        getNode().onTouchStationaryProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchStationaryProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> otherProperty) {
        getNode().onTouchStationaryProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Zoom Property
    default T bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        getNode().onZoomProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnZoomProperty() {
        getNode().onZoomProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Zoom Finished Property
    default T bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        getNode().onZoomFinishedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnZoomFinishedProperty() {
        getNode().onZoomFinishedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomFinishedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomFinishedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Zoom Started Property
    default T bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        getNode().onZoomStartedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnZoomStartedProperty() {
        getNode().onZoomStartedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomStartedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> otherProperty) {
        getNode().onZoomStartedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Opacity Property
    default T bindOpacityProperty(ObservableValue<? extends Number> observableValue) {
        getNode().opacityProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOpacityProperty() {
        getNode().opacityProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOpacityProperty(Property<Number> otherProperty) {
        getNode().opacityProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOpacityProperty(Property<Number> otherProperty) {
        getNode().opacityProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pick On Bounds Property
    default T bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().pickOnBoundsProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindPickOnBoundsProperty() {
        getNode().pickOnBoundsProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalPickOnBoundsProperty(Property<Boolean> otherProperty) {
        getNode().pickOnBoundsProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalPickOnBoundsProperty(Property<Boolean> otherProperty) {
        getNode().pickOnBoundsProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Rotate Property
    default T bindRotateProperty(ObservableValue<? extends Number> observableValue) {
        getNode().rotateProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindRotateProperty() {
        getNode().rotateProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalRotateProperty(Property<Number> otherProperty) {
        getNode().rotateProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalRotateProperty(Property<Number> otherProperty) {
        getNode().rotateProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Rotation Axis Property
    default T bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue) {
        getNode().rotationAxisProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindRotationAxisProperty() {
        getNode().rotationAxisProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalRotationAxisProperty(Property<Point3D> otherProperty) {
        getNode().rotationAxisProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalRotationAxisProperty(Property<Point3D> otherProperty) {
        getNode().rotationAxisProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scale X Property
    default T bindScaleXProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scaleXProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindScaleXProperty() {
        getNode().scaleXProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalScaleXProperty(Property<Number> otherProperty) {
        getNode().scaleXProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalScaleXProperty(Property<Number> otherProperty) {
        getNode().scaleXProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scale Y Property
    default T bindScaleYProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scaleYProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindScaleYProperty() {
        getNode().scaleYProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalScaleYProperty(Property<Number> otherProperty) {
        getNode().scaleYProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalScaleYProperty(Property<Number> otherProperty) {
        getNode().scaleYProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scale Z Property
    default T bindScaleZProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scaleZProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindScaleZProperty() {
        getNode().scaleZProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalScaleZProperty(Property<Number> otherProperty) {
        getNode().scaleZProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalScaleZProperty(Property<Number> otherProperty) {
        getNode().scaleZProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Style Property
    default T bindStyleProperty(ObservableValue<? extends String> observableValue) {
        getNode().styleProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindStyleProperty() {
        getNode().styleProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalStyleProperty(Property<String> otherProperty) {
        getNode().styleProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalStyleProperty(Property<String> otherProperty) {
        getNode().styleProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Translate X Property
    default T bindTranslateXProperty(ObservableValue<? extends Number> observableValue) {
        getNode().translateXProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTranslateXProperty() {
        getNode().translateXProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTranslateXProperty(Property<Number> otherProperty) {
        getNode().translateXProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalTranslateXProperty(Property<Number> otherProperty) {
        getNode().translateXProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Translate Y Property
    default T bindTranslateYProperty(ObservableValue<? extends Number> observableValue) {
        getNode().translateYProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTranslateYProperty() {
        getNode().translateYProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTranslateYProperty(Property<Number> otherProperty) {
        getNode().translateYProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalTranslateYProperty(Property<Number> otherProperty) {
        getNode().translateYProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Translate Z Property
    default T bindTranslateZProperty(ObservableValue<? extends Number> observableValue) {
        getNode().translateZProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTranslateZProperty() {
        getNode().translateZProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTranslateZProperty(Property<Number> otherProperty) {
        getNode().translateZProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalTranslateZProperty(Property<Number> otherProperty) {
        getNode().translateZProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // View Order Property
    default T bindViewOrderProperty(ObservableValue<? extends Number> observableValue) {
        getNode().viewOrderProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindViewOrderProperty() {
        getNode().viewOrderProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalViewOrderProperty(Property<Number> otherProperty) {
        getNode().viewOrderProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalViewOrderProperty(Property<Number> otherProperty) {
        getNode().viewOrderProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Visible Property
    default T bindVisibleProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().visibleProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindVisibleProperty() {
        getNode().visibleProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalVisibleProperty(Property<Boolean> otherProperty) {
        getNode().visibleProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

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

    default T fireEvent(Event event) {
        getNode().fireEvent(event);
        return getConfigurator();
    }

    default <S extends Event> T addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        getNode().addEventFilter(eventType, eventFilter);
        return getConfigurator();
    }

    default <S extends Event> T addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        getNode().addEventHandler(eventType, eventHandler);
        return getConfigurator();
    }

    default <S extends Event> T removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        getNode().removeEventFilter(eventType, eventFilter);
        return getConfigurator();
    }

    default <S extends Event> T removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        getNode().removeEventHandler(eventType, eventHandler);
        return getConfigurator();
    }

    //endregion Event Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setAccessibleHelp(String value) {
        getNode().setAccessibleHelp(value);
        return getConfigurator();
    }

    default T setAccessibleRole(AccessibleRole value) {
        getNode().setAccessibleRole(value);
        return getConfigurator();
    }

    default T setAccessibleRoleDescription(String value) {
        getNode().setAccessibleRoleDescription(value);
        return getConfigurator();
    }

    default T setAccessibleText(String value) {
        getNode().setAccessibleText(value);
        return getConfigurator();
    }

    default T setBlendMode(BlendMode value) {
        getNode().setBlendMode(value);
        return getConfigurator();
    }

    default T setCache(boolean value) {
        getNode().setCache(value);
        return getConfigurator();
    }

    default T setCacheHint(CacheHint value) {
        getNode().setCacheHint(value);
        return getConfigurator();
    }

    default T setClip(Node value) {
        getNode().setClip(value);
        return getConfigurator();
    }

    default T setCursor(Cursor value) {
        getNode().setCursor(value);
        return getConfigurator();
    }

    default T setDepthTest(DepthTest value) {
        getNode().setDepthTest(value);
        return getConfigurator();
    }

    default T setDisable(boolean value) {
        getNode().setDisable(value);
        return getConfigurator();
    }

    default T setEffect(Effect value) {
        getNode().setEffect(value);
        return getConfigurator();
    }

    default T setEventDispatcher(EventDispatcher value) {
        getNode().setEventDispatcher(value);
        return getConfigurator();
    }

    default T setFocusTraversable(boolean value) {
        getNode().setFocusTraversable(value);
        return getConfigurator();
    }

    default T setId(String value) {
        getNode().setId(value);
        return getConfigurator();
    }

    default T setInputMethodRequests(InputMethodRequests value) {
        getNode().setInputMethodRequests(value);
        return getConfigurator();
    }

    default T setLayoutX(double value) {
        getNode().setLayoutX(value);
        return getConfigurator();
    }

    default T setLayoutY(double value) {
        getNode().setLayoutY(value);
        return getConfigurator();
    }

    default T setManaged(boolean value) {
        getNode().setManaged(value);
        return getConfigurator();
    }

    default T setMouseTransparent(boolean value) {
        getNode().setMouseTransparent(value);
        return getConfigurator();
    }

    default T setNodeOrientation(NodeOrientation value) {
        getNode().setNodeOrientation(value);
        return getConfigurator();
    }

    default T setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
        getNode().setOnContextMenuRequested(value);
        return getConfigurator();
    }

    default T setOnDragDetected(EventHandler<? super MouseEvent> value) {
        getNode().setOnDragDetected(value);
        return getConfigurator();
    }

    default T setOnDragDone(EventHandler<? super DragEvent> value) {
        getNode().setOnDragDone(value);
        return getConfigurator();
    }

    default T setOnDragDropped(EventHandler<? super DragEvent> value) {
        getNode().setOnDragDropped(value);
        return getConfigurator();
    }

    default T setOnDragEntered(EventHandler<? super DragEvent> value) {
        getNode().setOnDragEntered(value);
        return getConfigurator();
    }

    default T setOnDragExited(EventHandler<? super DragEvent> value) {
        getNode().setOnDragExited(value);
        return getConfigurator();
    }

    default T setOnDragOver(EventHandler<? super DragEvent> value) {
        getNode().setOnDragOver(value);
        return getConfigurator();
    }

    default T setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
        getNode().setOnInputMethodTextChanged(value);
        return getConfigurator();
    }

    default T setOnKeyPressed(EventHandler<? super KeyEvent> value) {
        getNode().setOnKeyPressed(value);
        return getConfigurator();
    }

    default T setOnKeyTyped(EventHandler<? super KeyEvent> value) {
        getNode().setOnKeyTyped(value);
        return getConfigurator();
    }

    default T setOnMouseClicked(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseClicked(value);
        return getConfigurator();
    }

    default T setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value) {
        getNode().setOnMouseDragEntered(value);
        return getConfigurator();
    }

    default T setOnMouseDragExited(EventHandler<? super MouseDragEvent> value) {
        getNode().setOnMouseDragExited(value);
        return getConfigurator();
    }

    default T setOnMouseDragOver(EventHandler<? super MouseDragEvent> value) {
        getNode().setOnMouseDragOver(value);
        return getConfigurator();
    }

    default T setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value) {
        getNode().setOnMouseDragReleased(value);
        return getConfigurator();
    }

    default T setOnMouseEntered(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseEntered(value);
        return getConfigurator();
    }

    default T setOnMouseExited(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseExited(value);
        return getConfigurator();
    }

    default T setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseMoved(value);
        return getConfigurator();
    }

    default T setOnMousePressed(EventHandler<? super MouseEvent> value) {
        getNode().setOnMousePressed(value);
        return getConfigurator();
    }

    default T setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        getNode().setOnMouseReleased(value);
        return getConfigurator();
    }

    default T setOnRotate(EventHandler<? super RotateEvent> value) {
        getNode().setOnRotate(value);
        return getConfigurator();
    }

    default T setOnRotationFinished(EventHandler<? super RotateEvent> value) {
        getNode().setOnRotationFinished(value);
        return getConfigurator();
    }

    default T setOnRotationStarted(EventHandler<? super RotateEvent> value) {
        getNode().setOnRotationStarted(value);
        return getConfigurator();
    }

    default T setOnScroll(EventHandler<? super ScrollEvent> value) {
        getNode().setOnScroll(value);
        return getConfigurator();
    }

    default T setOnScrollFinished(EventHandler<? super ScrollEvent> value) {
        getNode().setOnScrollFinished(value);
        return getConfigurator();
    }

    default T setOnScrollStarted(EventHandler<? super ScrollEvent> value) {
        getNode().setOnScrollStarted(value);
        return getConfigurator();
    }

    default T setOpacity(double value) {
        getNode().setOpacity(value);
        return getConfigurator();
    }

    default T setStyle(String style) {
        getNode().setStyle(style);
        return getConfigurator();
    }

    default T setPickOnBounds(boolean value) {
        getNode().setPickOnBounds(value);
        return getConfigurator();
    }

    default T setOnSwipeDown(EventHandler<? super SwipeEvent> value) {
        getNode().setOnSwipeDown(value);
        return getConfigurator();
    }

    default T setOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
        getNode().setOnSwipeLeft(value);
        return getConfigurator();
    }

    default T setOnSwipeRight(EventHandler<? super SwipeEvent> value) {
        getNode().setOnSwipeRight(value);
        return getConfigurator();
    }

    default T setOnSwipeUp(EventHandler<? super SwipeEvent> value) {
        getNode().setOnSwipeUp(value);
        return getConfigurator();
    }

    default T setOnTouchMoved(EventHandler<? super TouchEvent> value) {
        getNode().setOnTouchMoved(value);
        return getConfigurator();
    }

    default T setOnTouchPressed(EventHandler<? super TouchEvent> value) {
        getNode().setOnTouchPressed(value);
        return getConfigurator();
    }

    default T setOnTouchReleased(EventHandler<? super TouchEvent> value) {
        getNode().setOnTouchReleased(value);
        return getConfigurator();
    }

    default T setOnTouchStationary(EventHandler<? super TouchEvent> value) {
        getNode().setOnTouchStationary(value);
        return getConfigurator();
    }

    default T setOnZoom(EventHandler<? super ZoomEvent> value) {
        getNode().setOnZoom(value);
        return getConfigurator();
    }

    default T setOnZoomFinished(EventHandler<? super ZoomEvent> value) {
        getNode().setOnZoomFinished(value);
        return getConfigurator();
    }

    default T setOnZoomStarted(EventHandler<? super ZoomEvent> value) {
        getNode().setOnZoomStarted(value);
        return getConfigurator();
    }

    default T setRotate(double value) {
        getNode().setRotate(value);
        return getConfigurator();
    }

    default T setRotationAxis(Point3D value) {
        getNode().setRotationAxis(value);
        return getConfigurator();
    }

    default T setScaleX(double value) {
        getNode().setScaleX(value);
        return getConfigurator();
    }

    default T setScaleY(double value) {
        getNode().setScaleY(value);
        return getConfigurator();
    }

    default T setScaleZ(double value) {
        getNode().setScaleZ(value);
        return getConfigurator();
    }

    default T setTranslateX(double value) {
        getNode().setTranslateX(value);
        return getConfigurator();
    }

    default T setTranslateY(double value) {
        getNode().setTranslateY(value);
        return getConfigurator();
    }

    default T setTranslateZ(double value) {
        getNode().setTranslateZ(value);
        return getConfigurator();
    }

    default T setUserData(Object value) {
        getNode().setUserData(value);
        return getConfigurator();
    }

    default T setViewOrder(double value) {
        getNode().setViewOrder(value);
        return getConfigurator();
    }

    default T setVisible(boolean value) {
        getNode().setVisible(value);
        return getConfigurator();
    }

    default T setStyleClass(int index, String element) {
        getNode().getStyleClass()
                 .set(index, element);
        return getConfigurator();
    }

    default T setAllStyleClasses(String... elements) {
        getNode().getStyleClass()
                 .setAll(elements);
        return getConfigurator();
    }

    default T setAllStyleClasses(Collection<? extends String> col) {
        getNode().getStyleClass()
                 .setAll(col);
        return getConfigurator();
    }

    default T setTransformClass(int index, Transform element) {
        getNode().getTransforms()
                 .set(index, element);
        return getConfigurator();
    }

    default T setAllTransformClasses(Transform... elements) {
        getNode().getTransforms()
                 .setAll(elements);
        return getConfigurator();
    }

    default T setAllTransformClasses(Collection<? extends Transform> col) {
        getNode().getTransforms()
                 .setAll(col);
        return getConfigurator();
    }

    //endregion Set Functions

    //region Add EFXStyle Functions
    //*****************************************************************
    // Add EFXStyle Functions
    //*****************************************************************

    default T addFirstStyleClass(String styleClass) {
        getNode().getStyleClass()
                 .addFirst(styleClass);
        return getConfigurator();
    }

    default T addLastStyleClass(String styleClass) {
        getNode().getStyleClass()
                 .addLast(styleClass);
        return getConfigurator();
    }

    default T addStyleClass(String styleClass) {
        getNode().getStyleClass()
                 .add(styleClass);
        return getConfigurator();
    }

    default T addStyleClass(int index, String styleClass) {
        getNode().getStyleClass()
                 .add(index, styleClass);
        return getConfigurator();
    }

    default T addAllStyleClasses(String... styleClasses) {
        getNode().getStyleClass()
                 .addAll(styleClasses);
        return getConfigurator();
    }

    default T addAllStyleClasses(Collection<? extends String> c) {
        getNode().getStyleClass()
                 .addAll(c);
        return getConfigurator();
    }

    default T addAllStyleClasses(int index, Collection<? extends String> c) {
        getNode().getStyleClass()
                 .addAll(index, c);
        return getConfigurator();
    }

    //endregion Add EFXStyle Functions

    //region Remove EFXStyle Functions
    //*****************************************************************
    // Remove EFXStyle Functions
    //*****************************************************************

    default T removeFirstStyleClass() {
        getNode().getStyleClass()
                 .removeFirst();
        return getConfigurator();
    }

    default T removeLastStyleClass() {
        getNode().getStyleClass()
                 .removeLast();
        return getConfigurator();
    }

    default T removeStyleClass(String styleClass) {
        getNode().getStyleClass()
                 .remove(styleClass);
        return getConfigurator();
    }

    default T removeStyleClasses(int from, int to) {
        getNode().getStyleClass()
                 .remove(from, to);
        return getConfigurator();
    }

    default T removeStyleClassesIf(Predicate<? super String> filter) {
        getNode().getStyleClass()
                 .removeIf(filter);
        return getConfigurator();
    }

    default T removeAllStyleClasses(String... styleClasses) {
        getNode().getStyleClass()
                 .removeAll(styleClasses);
        return getConfigurator();
    }

    default T removeAllStyleClasses(Collection<? extends String> c) {
        getNode().getStyleClass()
                 .removeAll(c);
        return getConfigurator();
    }

    //endregion Remove EFXStyle Functions

    //region EFXStyle Functions
    //*****************************************************************
    // EFXStyle Functions
    //*****************************************************************

    default T pseudoClassStateChange(PseudoClass pseudoClass, boolean active) {
        getNode().pseudoClassStateChanged(pseudoClass, active);
        return getConfigurator();
    }

    default T applyCss() {
        getNode().applyCss();
        return getConfigurator();
    }

    //endregion EFXStyle Functions

    //region Add Transform Functions
    //*****************************************************************
    // Add Transform Functions
    //*****************************************************************

    default T addFirstTransform(Transform transform) {
        getNode().getTransforms()
                 .addFirst(transform);
        return getConfigurator();
    }

    default T addLastTransform(Transform transform) {
        getNode().getTransforms()
                 .addLast(transform);
        return getConfigurator();
    }

    default T addTransform(Transform transform) {
        getNode().getTransforms()
                 .add(transform);
        return getConfigurator();
    }

    default T addTransform(int index, Transform transform) {
        getNode().getTransforms()
                 .add(index, transform);
        return getConfigurator();
    }

    default T addAllTransforms(Transform... transforms) {
        getNode().getTransforms()
                 .addAll(transforms);
        return getConfigurator();
    }

    default T addAllTransforms(Collection<? extends Transform> c) {
        getNode().getTransforms()
                 .addAll(c);
        return getConfigurator();
    }

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

    default T removeFirstTransform() {
        getNode().getTransforms()
                 .removeFirst();
        return getConfigurator();
    }

    default T removeLastTransform() {
        getNode().getTransforms()
                 .removeLast();
        return getConfigurator();
    }

    default T removeTransform(Transform transform) {
        getNode().getTransforms()
                 .remove(transform);
        return getConfigurator();
    }

    default T removeTransforms(int from, int to) {
        getNode().getTransforms()
                 .remove(from, to);
        return getConfigurator();
    }

    default T removeTransformsIf(Predicate<? super Transform> filter) {
        getNode().getTransforms()
                 .removeIf(filter);
        return getConfigurator();
    }

    default T removeAllTransforms(Transform... transforms) {
        getNode().getTransforms()
                 .removeAll(transforms);
        return getConfigurator();
    }

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

    default T toBack() {
        getNode().toBack();
        return getConfigurator();
    }

    default T toFront() {
        getNode().toFront();
        return getConfigurator();
    }

    default T resize(double width, double height) {
        getNode().resize(width, height);
        return getConfigurator();
    }

    default T autosize() {
        getNode().autosize();
        return getConfigurator();
    }

    default T resizeRelocate(double x, double y, double width, double height) {
        getNode().resizeRelocate(x, y, width, height);
        return getConfigurator();
    }

    default T requestFocus() {
        getNode().requestFocus();
        return getConfigurator();
    }

    //endregion Layout Functions
}
