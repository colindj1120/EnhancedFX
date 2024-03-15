package io.github.colindj1120.enhancedfx.base.factory.configurators.controls;

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
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.transform.Transform;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class TextAreaConfigurator<T extends TextAreaConfigurator<T>> extends TextInputControlConfigurator<T> {
    /**
     * Creates a new {@code TextAreaConfigurator} instance for the specified {@link TextArea}. This factory method facilitates the
     * creation of a configurator tailored to the provided text field, enabling immediate access to configuration methods.
     * <p>
     * This approach abstracts the instantiation process, allowing for fluent and intuitive setup of text fields through method
     * chaining.
     * </p>
     *
     * @param textArea
     *         The {@link TextArea} to be configured by the newly created {@code TextAreaConfigurator}.
     *
     * @return A new instance of {@code TextAreaConfigurator} linked to the specified text field.
     */
    public static TextAreaConfigurator create(TextArea textArea) {
        return new TextAreaConfigurator(textArea);
    }

    /**
     * The {@link TextArea} instance that is being configured. This field holds a reference to the specific textArea object upon
     * which configuration methods will act, enabling the modification and customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the textArea, allowing changes to be made through the configurator's methods
     * rather than direct access, promoting a more structured and maintainable approach to UI customization. The configurator provides
     * a fluent API for configuring various aspects of the textArea, including its appearance, behavior, and event handling.
     * </p>
     */
    private TextArea textArea;

    /**
     * Constructs a {@code TextAreaConfigurator} for the specified {@link TextArea}. This constructor initializes the configurator
     * with a target textArea. It sets up the environment for further configuration specific to {@link TextArea} instances. The
     * {@code TextAreaConfigurator.class} is used as the class reference for error reporting.
     *
     * @param textArea
     *         The {@link TextArea} to be configured. Must not be {@code null}, and an {@link IllegalArgumentException} will be thrown
     *         if a null textArea is passed. This ensures that the configurator has a valid target for configuration.
     */
    protected TextAreaConfigurator(TextArea textArea) {
        super(checkNodeNotNullAndInstanceOf(textArea, TextArea.class, TextAreaConfigurator.class, "Constructor"));
        this.textArea = textArea;
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextArea getNode() {
        return textArea;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        super.setNode(checkNodeNotNullAndInstanceOf(value, TextArea.class, TextAreaConfigurator.class, "setNode"));
        this.textArea = ((TextArea) value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return textArea.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextAreaConfigurator textAreaConfigurator) {
            return Objects.equals(textAreaConfigurator.getNode(), this.textArea);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("TextAreaConfigurator current text area: %s", textArea.toString());
    }

    //endregion Overridden Methods

    //********************************************************************************************************//
    //                                                                                                        //
    //                                                                                                        //
    //    Below are the overridden parent class methods that just call super to allow for method chaining.    //
    //                                                                                                        //
    //                                                                                                        //
    //********************************************************************************************************//

    //region NodeConfig Functions
    //*****************************************************************
    // NodeConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.addAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.addBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.addBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.addCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.addClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addClipInvalidationListener(InvalidationListener invalidationListener) {
        super.addClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.addCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.addCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.addDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.addDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.addEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.addEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.addEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.addHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addIdChangeListener(ChangeListener<? super String> changeListener) {
        super.addIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addIdInvalidationListener(InvalidationListener invalidationListener) {
        super.addIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.addInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.addInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.addManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.addMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.addOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.addOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.addOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.addParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addPickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.addSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.addSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.addRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.addRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.addStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.addTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.addTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.addViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.addViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.addVisibleInvalidationListener(invalidationListener);
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
    public TextAreaConfigurator removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.removeAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.removeBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.removeCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.removeClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeClipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.removeCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.removeDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.removeEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.removeEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeIdChangeListener(ChangeListener<? super String> changeListener) {
        super.removeIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeIdInvalidationListener(InvalidationListener invalidationListener) {
        super.removeIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.removeInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.removeOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.removeOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.removeParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removePickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.removeSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.removeRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.removeStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.removeTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeVisibleInvalidationListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleHelpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindAccessibleHelpProperty() {
        super.unbindAccessibleHelpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.bindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleRoleDescriptionProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindAccessibleRoleDescriptionProperty() {
        super.unbindAccessibleRoleDescriptionProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.bindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue) {
        super.bindAccessibleRoleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindAccessibleRoleProperty() {
        super.unbindAccessibleRoleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.bindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.unbindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindAccessibleTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindAccessibleTextProperty() {
        super.unbindAccessibleTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.bindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue) {
        super.bindBlendModeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBlendModeProperty() {
        super.unbindBlendModeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.bindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.unbindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue) {
        super.bindCacheHintProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindCacheHintProperty() {
        super.unbindCacheHintProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.bindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.unbindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindCacheProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindCacheProperty() {
        super.unbindCacheProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalCacheProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalCacheProperty(Property<Boolean> property) {
        super.unbindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindClipProperty(ObservableValue<? extends Node> observableValue) {
        super.bindClipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindClipProperty() {
        super.unbindClipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalClipProperty(Property<Node> property) {
        super.bindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalClipProperty(Property<Node> property) {
        super.unbindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindCursorProperty(ObservableValue<? extends Cursor> observableValue) {
        super.bindCursorProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindCursorProperty() {
        super.unbindCursorProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalCursorProperty(Property<Cursor> property) {
        super.bindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalCursorProperty(Property<Cursor> property) {
        super.unbindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue) {
        super.bindDepthTestProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindDepthTestProperty() {
        super.unbindDepthTestProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.bindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.unbindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindDisableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindDisableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindDisableProperty() {
        super.unbindDisableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalDisableProperty(Property<Boolean> property) {
        super.bindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalDisableProperty(Property<Boolean> property) {
        super.unbindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindEffectProperty(ObservableValue<? extends Effect> observableValue) {
        super.bindEffectProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindEffectProperty() {
        super.unbindEffectProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalEffectProperty(Property<Effect> property) {
        super.bindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalEffectProperty(Property<Effect> property) {
        super.unbindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue) {
        super.bindEventDispatcherProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindEventDispatcherProperty() {
        super.unbindEventDispatcherProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.bindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.unbindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindFocusTraversableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindFocusTraversableProperty() {
        super.unbindFocusTraversableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.bindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.unbindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindIdProperty(ObservableValue<? extends String> observableValue) {
        super.bindIdProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindIdProperty() {
        super.unbindIdProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalIdProperty(Property<String> property) {
        super.bindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalIdProperty(Property<String> property) {
        super.unbindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue) {
        super.bindInputMethodRequestsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindInputMethodRequestsProperty() {
        super.unbindInputMethodRequestsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.bindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.unbindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindLayoutXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindLayoutXProperty() {
        super.unbindLayoutXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalLayoutXProperty(Property<Number> property) {
        super.bindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalLayoutXProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindLayoutYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindLayoutYProperty() {
        super.unbindLayoutYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalLayoutYProperty(Property<Number> property) {
        super.bindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalLayoutYProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindManagedProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindManagedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindManagedProperty() {
        super.unbindManagedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalManagedProperty(Property<Boolean> property) {
        super.bindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalManagedProperty(Property<Boolean> property) {
        super.unbindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindMouseTransparentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindMouseTransparentProperty() {
        super.unbindMouseTransparentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.bindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.unbindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue) {
        super.bindNodeOrientationProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindNodeOrientationProperty() {
        super.unbindNodeOrientationProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.bindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.unbindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnContextMenuRequestedProperty(ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue) {
        super.bindOnContextMenuRequestedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnContextMenuRequestedProperty() {
        super.unbindOnContextMenuRequestedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property) {
        super.bindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property) {
        super.unbindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnDragDetectedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnDragDetectedProperty() {
        super.unbindOnDragDetectedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDoneProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnDragDoneProperty() {
        super.unbindOnDragDoneProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDroppedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnDragDroppedProperty() {
        super.unbindOnDragDroppedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnDragEnteredProperty() {
        super.unbindOnDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnDragExitedProperty() {
        super.unbindOnDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnDragOverProperty() {
        super.unbindOnDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnInputMethodTextChangedProperty(ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue) {
        super.bindOnInputMethodTextChangedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnInputMethodTextChangedProperty() {
        super.unbindOnInputMethodTextChangedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property) {
        super.bindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property) {
        super.unbindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnKeyPressedProperty() {
        super.unbindOnKeyPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnKeyReleasedProperty() {
        super.unbindOnKeyReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyTypedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnKeyTypedProperty() {
        super.unbindOnKeyTypedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseClickedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseClickedProperty() {
        super.unbindOnMouseClickedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseDragEnteredProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseDragEnteredProperty() {
        super.unbindOnMouseDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseDragExitedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseDragExitedProperty() {
        super.unbindOnMouseDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseDraggedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseDraggedProperty() {
        super.unbindOnMouseDraggedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseDragOverProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseDragOverProperty() {
        super.unbindOnMouseDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseDragReleasedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseDragReleasedProperty() {
        super.unbindOnMouseDragReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseEnteredProperty() {
        super.unbindOnMouseEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseExitedProperty() {
        super.unbindOnMouseExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseMovedProperty() {
        super.unbindOnMouseMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMousePressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMousePressedProperty() {
        super.unbindOnMousePressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnMouseReleasedProperty() {
        super.unbindOnMouseReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnRotateProperty() {
        super.unbindOnRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnRotationFinishedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnRotationFinishedProperty() {
        super.unbindOnRotationFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnRotationStartedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnRotationStartedProperty() {
        super.unbindOnRotationStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnScrollProperty() {
        super.unbindOnScrollProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnScrollFinishedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnScrollFinishedProperty() {
        super.unbindOnScrollFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnScrollStartedProperty() {
        super.unbindOnScrollStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeDownProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnSwipeDownProperty() {
        super.unbindOnSwipeDownProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeLeftProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnSwipeLeftProperty() {
        super.unbindOnSwipeLeftProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeRightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnSwipeRightProperty() {
        super.unbindOnSwipeRightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeUpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnSwipeUpProperty() {
        super.unbindOnSwipeUpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnTouchMovedProperty() {
        super.unbindOnTouchMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnTouchPressedProperty() {
        super.unbindOnTouchPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnTouchReleasedProperty() {
        super.unbindOnTouchReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnTouchStationaryProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchStationaryProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnTouchStationaryProperty() {
        super.unbindOnTouchStationaryProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnZoomProperty() {
        super.unbindOnZoomProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnZoomFinishedProperty() {
        super.unbindOnZoomFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOnZoomStartedProperty() {
        super.unbindOnZoomStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOpacityProperty(ObservableValue<? extends Number> observableValue) {
        super.bindOpacityProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOpacityProperty() {
        super.unbindOpacityProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOpacityProperty(Property<Number> property) {
        super.bindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOpacityProperty(Property<Number> property) {
        super.unbindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindPickOnBoundsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindPickOnBoundsProperty() {
        super.unbindPickOnBoundsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.bindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.unbindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindRotateProperty(ObservableValue<? extends Number> observableValue) {
        super.bindRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindRotateProperty() {
        super.unbindRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalRotateProperty(Property<Number> property) {
        super.bindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalRotateProperty(Property<Number> property) {
        super.unbindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue) {
        super.bindRotationAxisProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindRotationAxisProperty() {
        super.unbindRotationAxisProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.bindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.unbindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindScaleXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindScaleXProperty() {
        super.unbindScaleXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalScaleXProperty(Property<Number> property) {
        super.bindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalScaleXProperty(Property<Number> property) {
        super.unbindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindScaleYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindScaleYProperty() {
        super.unbindScaleYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalScaleYProperty(Property<Number> property) {
        super.bindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalScaleYProperty(Property<Number> property) {
        super.unbindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindScaleZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindScaleZProperty() {
        super.unbindScaleZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalScaleZProperty(Property<Number> property) {
        super.bindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalScaleZProperty(Property<Number> property) {
        super.unbindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindStyleProperty(ObservableValue<? extends String> observableValue) {
        super.bindStyleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindStyleProperty() {
        super.unbindStyleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalStyleProperty(Property<String> property) {
        super.bindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalStyleProperty(Property<String> property) {
        super.unbindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindTranslateXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindTranslateXProperty() {
        super.unbindTranslateXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalTranslateXProperty(Property<Number> property) {
        super.bindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalTranslateXProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindTranslateYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindTranslateYProperty() {
        super.unbindTranslateYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalTranslateYProperty(Property<Number> property) {
        super.bindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalTranslateYProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindTranslateZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindTranslateZProperty() {
        super.unbindTranslateZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalTranslateZProperty(Property<Number> property) {
        super.bindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalTranslateZProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindViewOrderProperty(ObservableValue<? extends Number> observableValue) {
        super.bindViewOrderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindViewOrderProperty() {
        super.unbindViewOrderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalViewOrderProperty(Property<Number> property) {
        super.bindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalViewOrderProperty(Property<Number> property) {
        super.unbindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindVisibleProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindVisibleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindVisibleProperty() {
        super.unbindVisibleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalVisibleProperty(Property<Boolean> property) {
        super.bindBidirectionalVisibleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalVisibleProperty(Property<Boolean> property) {
        super.unbindBidirectionalVisibleProperty(property);
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
    public TextAreaConfigurator fireEvent(Event event) {
        super.fireEvent(event);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> TextAreaConfigurator addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.addEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> TextAreaConfigurator addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        super.addEventHandler(eventType, eventHandler);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> TextAreaConfigurator removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.removeEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> TextAreaConfigurator removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        super.removeEventHandler(eventType, eventHandler);
        return this;
    }

    //endregion Event Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setAccessibleHelp(String value) {
        super.setAccessibleHelp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setAccessibleRole(AccessibleRole value) {
        super.setAccessibleRole(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setAccessibleRoleDescription(String value) {
        super.setAccessibleRoleDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setAccessibleText(String value) {
        super.setAccessibleText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setBlendMode(BlendMode value) {
        super.setBlendMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setCache(boolean value) {
        super.setCache(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setCacheHint(CacheHint value) {
        super.setCacheHint(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setClip(Node value) {
        super.setClip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setCursor(Cursor value) {
        super.setCursor(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setDepthTest(DepthTest value) {
        super.setDepthTest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setDisable(boolean value) {
        super.setDisable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setEffect(Effect value) {
        super.setEffect(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setEventDispatcher(EventDispatcher value) {
        super.setEventDispatcher(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setFocusTraversable(boolean value) {
        super.setFocusTraversable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setId(String value) {
        super.setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setInputMethodRequests(InputMethodRequests value) {
        super.setInputMethodRequests(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setLayoutX(double value) {
        super.setLayoutX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setLayoutY(double value) {
        super.setLayoutY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setManaged(boolean value) {
        super.setManaged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setMouseTransparent(boolean value) {
        super.setMouseTransparent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setNodeOrientation(NodeOrientation value) {
        super.setNodeOrientation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
        super.setOnContextMenuRequested(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnDragDetected(EventHandler<? super MouseEvent> value) {
        super.setOnDragDetected(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnDragDone(EventHandler<? super DragEvent> value) {
        super.setOnDragDone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnDragDropped(EventHandler<? super DragEvent> value) {
        super.setOnDragDropped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnDragEntered(EventHandler<? super DragEvent> value) {
        super.setOnDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnDragExited(EventHandler<? super DragEvent> value) {
        super.setOnDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnDragOver(EventHandler<? super DragEvent> value) {
        super.setOnDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
        super.setOnInputMethodTextChanged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnKeyPressed(EventHandler<? super KeyEvent> value) {
        super.setOnKeyPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnKeyTyped(EventHandler<? super KeyEvent> value) {
        super.setOnKeyTyped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseClicked(EventHandler<? super MouseEvent> value) {
        super.setOnMouseClicked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseDragExited(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseDragOver(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseEntered(EventHandler<? super MouseEvent> value) {
        super.setOnMouseEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseExited(EventHandler<? super MouseEvent> value) {
        super.setOnMouseExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        super.setOnMouseMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMousePressed(EventHandler<? super MouseEvent> value) {
        super.setOnMousePressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        super.setOnMouseReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnRotate(EventHandler<? super RotateEvent> value) {
        super.setOnRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnRotationFinished(EventHandler<? super RotateEvent> value) {
        super.setOnRotationFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnRotationStarted(EventHandler<? super RotateEvent> value) {
        super.setOnRotationStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnScroll(EventHandler<? super ScrollEvent> value) {
        super.setOnScroll(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnScrollFinished(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnScrollStarted(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOpacity(double value) {
        super.setOpacity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setStyle(String style) {
        super.setStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnSwipeDown(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeDown(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeLeft(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnSwipeRight(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeRight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnSwipeUp(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeUp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnTouchMoved(EventHandler<? super TouchEvent> value) {
        super.setOnTouchMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnTouchPressed(EventHandler<? super TouchEvent> value) {
        super.setOnTouchPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnTouchReleased(EventHandler<? super TouchEvent> value) {
        super.setOnTouchReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnTouchStationary(EventHandler<? super TouchEvent> value) {
        super.setOnTouchStationary(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnZoom(EventHandler<? super ZoomEvent> value) {
        super.setOnZoom(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnZoomFinished(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOnZoomStarted(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setPickOnBounds(boolean value) {
        super.setPickOnBounds(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setRotate(double value) {
        super.setRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setRotationAxis(Point3D value) {
        super.setRotationAxis(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setScaleX(double value) {
        super.setScaleX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setScaleY(double value) {
        super.setScaleY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setScaleZ(double value) {
        super.setScaleZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setTranslateX(double value) {
        super.setTranslateX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setTranslateY(double value) {
        super.setTranslateY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setTranslateZ(double value) {
        super.setTranslateZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setUserData(Object value) {
        super.setUserData(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setViewOrder(double value) {
        super.setViewOrder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setVisible(boolean value) {
        super.setVisible(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setStyleClass(int index, String element) {
        super.setStyleClass(index, element);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setAllStyleClasses(String... elements) {
        super.setAllStyleClasses(elements);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setAllStyleClasses(Collection<? extends String> col) {
        super.setAllStyleClasses(col);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setTransformClass(int index, Transform element) {
        super.setTransformClass(index,element);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setAllTransformClasses(Transform... elements) {
        super.setAllTransformClasses(elements);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setAllTransformClasses(Collection<? extends Transform> col) {
        super.setAllTransformClasses(col);
        return this;
    }

    //endregion Set Functions

    //region Add EFXStyle Functions
    //*****************************************************************
    // Add EFXStyle Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFirstStyleClass(String styleClass) {
        super.addFirstStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLastStyleClass(String styleClass) {
        super.addLastStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStyleClass(String styleClass) {
        super.addStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStyleClass(int index, String styleClass) {
        super.addStyleClass(index, styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllStyleClasses(String... styleClasses) {
        super.addAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllStyleClasses(Collection<? extends String> c) {
        super.addAllStyleClasses(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllStyleClasses(int index, Collection<? extends String> c) {
        super.addAllStyleClasses(index, c);
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
    public TextAreaConfigurator removeFirstStyleClass() {
        super.removeFirstStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLastStyleClass() {
        super.removeLastStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStyleClass(String styleClass) {
        super.removeStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStyleClasses(int from, int to) {
        super.removeStyleClasses(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStyleClassesIf(Predicate<? super String> filter) {
        super.removeStyleClassesIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAllStyleClasses(String... styleClasses) {
        super.removeAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAllStyleClasses(Collection<? extends String> c) {
        super.removeAllStyleClasses(c);
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
    public TextAreaConfigurator pseudoClassStateChange(PseudoClass pseudoClass, boolean active) {
        super.pseudoClassStateChange(pseudoClass, active);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator applyCss() {
        super.applyCss();
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
    public TextAreaConfigurator addFirstTransform(Transform transform) {
        super.addFirstTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLastTransform(Transform transform) {
        super.addLastTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTransform(Transform transform) {
        super.addTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTransform(int index, Transform transform) {
        super.addTransform(index, transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllTransforms(Transform... transforms) {
        super.addAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllTransforms(Collection<? extends Transform> c) {
        super.addAllTransforms(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllTransforms(int index, Collection<? extends Transform> c) {
        super.addAllTransforms(index, c);
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
    public TextAreaConfigurator removeFirstTransform() {
        super.removeFirstTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLastTransform() {
        super.removeLastTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTransform(Transform transform) {
        super.removeTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTransforms(int from, int to) {
        super.removeTransforms(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTransformsIf(Predicate<? super Transform> filter) {
        super.removeTransformsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAllTransforms(Transform... transforms) {
        super.removeAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAllTransforms(Collection<? extends Transform> c) {
        super.removeAllTransforms(c);
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
    public TextAreaConfigurator toBack() {
        super.toBack();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator toFront() {
        super.toFront();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator resize(double width, double height) {
        super.resize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator autosize() {
        super.autosize();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator resizeRelocate(double x, double y, double width, double height) {
        super.resizeRelocate(x, y, width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator requestFocus() {
        super.requestFocus();
        return this;
    }

    //endregion Layout Functions

    //endregion NodeConfig Functions

    //region ParentConfig Functions
    //*****************************************************************
    // ParentConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.addNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.addGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.addGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        super.addStylesheetsListInvalidationListener(invalidationListener);
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
    public TextAreaConfigurator removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.removeGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStylesheetsListInvalidationListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Layout Functions
    //*****************************************************************
    // Layout Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator requestLayout() {
        super.requestLayout();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator Layout() {
        super.Layout();
        return this;
    }

    //endregion Layout Functions

    //region Add Stylesheet Functions
    //*****************************************************************
    // Add Stylesheet Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFirstStylesheet(String stylesheet) {
        super.addFirstStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLastStylesheet(String stylesheet) {
        super.addLastStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStylesheet(String stylesheet) {
        super.addStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addStylesheet(int index, String stylesheet) {
        super.addStylesheet(index, stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllStylesheets(String... stylesheets) {
        super.addAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllStylesheets(Collection<? extends String> c) {
        super.addAllStylesheets(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAllStylesheets(int index, Collection<? extends String> c) {
        super.addAllStylesheets(index, c);
        return this;
    }

    //endregion Add Stylesheet Functions

    //region Remove Stylesheet Functions
    //*****************************************************************
    // Remove Stylesheet Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFirstStylesheet() {
        super.removeFirstStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLastStylesheet() {
        super.removeLastStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStylesheet(String stylesheet) {
        super.removeStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStylesheets(int from, int to) {
        super.removeStylesheets(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeStylesheetsIf(Predicate<? super String> filter) {
        super.removeStylesheetsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAllStylesheets(String... stylesheets) {
        super.removeAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAllStylesheets(Collection<? extends String> c) {
        super.removeAllStylesheets(c);
        return this;
    }

    //endregion Remove Stylesheet Functions

    //endregion ParentConfig Functions

    //region RegionConfig Functions
    //*****************************************************************
    // RegionConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addSnapToPixelChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        super.addSnapToPixelInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addPaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.addPaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        super.addBackgroundChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        super.addBackgroundInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBorderChangeListener(ChangeListener<? super Border> changeListener) {
        super.addBorderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addBorderInvalidationListener(InvalidationListener invalidationListener) {
        super.addBorderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addOpaqueInsetsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpaqueInsetsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addInsetChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addInsetInvalidationListener(InvalidationListener invalidationListener) {
        super.addInsetInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMinWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addMinWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addPrefWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addPrefWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMaxWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addMaxWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMinHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addMinHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addPrefHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addPrefHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMaxHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addMaxHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        super.addShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addScaleShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCenterShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addCenterShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheShapeInvalidationListener(invalidationListener);
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
    public TextAreaConfigurator removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeSnapToPixelChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSnapToPixelInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removePaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.removePaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        super.removeBackgroundChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBackgroundInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBorderChangeListener(ChangeListener<? super Border> changeListener) {
        super.removeBorderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeBorderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBorderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeOpaqueInsetsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpaqueInsetsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeInsetChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeInsetInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInsetInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMinWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMinWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removePrefWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removePrefWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMaxWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMaxWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMinHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMinHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removePrefHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removePrefHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMaxHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMaxHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        super.removeShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeScaleShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCenterShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCenterShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheShapeInvalidationListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindSnapToPixelProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindSnapToPixelProperty() {
        super.unbindSnapToPixelProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        super.bindBidirectionalSnapToPixelProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        super.unbindBidirectionalSnapToPixelProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindPaddingProperty(ObservableValue<? extends Insets> observableValue) {
        super.bindPaddingProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindPaddingProperty() {
        super.unbindPaddingProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalPaddingProperty(Property<Insets> property) {
        super.bindBidirectionalPaddingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalPaddingProperty(Property<Insets> property) {
        super.unbindBidirectionalPaddingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBackgroundProperty(ObservableValue<? extends Background> observableValue) {
        super.bindBackgroundProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBackgroundProperty() {
        super.unbindBackgroundProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalBackgroundProperty(Property<Background> property) {
        super.bindBidirectionalBackgroundProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalBackgroundProperty(Property<Background> property) {
        super.unbindBidirectionalBackgroundProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBorderProperty(ObservableValue<? extends Border> observableValue) {
        super.bindBorderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBorderProperty() {
        super.unbindBorderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalBorderProperty(Property<Border> property) {
        super.bindBidirectionalBorderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalBorderProperty(Property<Border> property) {
        super.unbindBidirectionalBorderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue) {
        super.bindOpaqueInsetsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindOpaqueInsetsProperty() {
        super.unbindOpaqueInsetsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        super.bindBidirectionalOpaqueInsetsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        super.unbindBidirectionalOpaqueInsetsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindMinWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMinWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindMinWidthProperty() {
        super.unbindMinWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalMinWidthProperty(Property<Number> property) {
        super.bindBidirectionalMinWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalMinWidthProperty(Property<Number> property) {
        super.unbindBidirectionalMinWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindPrefWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindPrefWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindPrefWidthProperty() {
        super.unbindPrefWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalPrefWidthProperty(Property<Number> property) {
        super.bindBidirectionalPrefWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalPrefWidthProperty(Property<Number> property) {
        super.unbindBidirectionalPrefWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindMaxWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMaxWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindMaxWidthProperty() {
        super.unbindMaxWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalMaxWidthProperty(Property<Number> property) {
        super.bindBidirectionalMaxWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalMaxWidthProperty(Property<Number> property) {
        super.unbindBidirectionalMaxWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindMinHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMinHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindMinHeightProperty() {
        super.unbindMinHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalMinHeightProperty(Property<Number> property) {
        super.bindBidirectionalMinHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalMinHeightProperty(Property<Number> property) {
        super.unbindBidirectionalMinHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindPrefHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindPrefHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindPrefHeightProperty() {
        super.unbindPrefHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalPrefHeightProperty(Property<Number> property) {
        super.bindBidirectionalPrefHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalPrefHeightProperty(Property<Number> property) {
        super.unbindBidirectionalPrefHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindMaxHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMaxHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindMaxHeightProperty() {
        super.unbindMaxHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalMaxHeightProperty(Property<Number> property) {
        super.bindBidirectionalMaxHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalMaxHeightProperty(Property<Number> property) {
        super.unbindBidirectionalMaxHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindShapeProperty(ObservableValue<? extends Shape> observableValue) {
        super.bindShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindShapeProperty() {
        super.unbindShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalShapeProperty(Property<Shape> property) {
        super.bindBidirectionalShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalShapeProperty(Property<Shape> property) {
        super.unbindBidirectionalShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindScaleShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindScaleShapeProperty() {
        super.unbindScaleShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalScaleShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalScaleShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCenterShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindCenterShapeProperty() {
        super.unbindCenterShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalCenterShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalCenterShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindCacheShapeProperty() {
        super.unbindCacheShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalCacheShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalCacheShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalCacheShapeProperty(property);
        return this;
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setSnapToPixel(boolean value) {
        super.setSnapToPixel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setPadding(Insets value) {
        super.setPadding(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setBackground(Background value) {
        super.setBackground(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setBorder(Border value) {
        super.setBorder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setOpaqueInsets(Insets value) {
        super.setOpaqueInsets(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setMinWidth(double value) {
        super.setMinWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setPrefWidth(double value) {
        super.setPrefWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setMaxWidth(double value) {
        super.setMaxWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setMinHeight(double value) {
        super.setMinHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setPrefHeight(double value) {
        super.setPrefHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setMaxHeight(double value) {
        super.setMaxHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setMinSize(double width, double height) {
        super.setMinSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setPrefSize(double width, double height) {
        super.setPrefSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setMaxSize(double width, double height) {
        super.setMaxSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setShape(Shape value) {
        super.setShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setScaleShape(boolean value) {
        super.setScaleShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setCenterShape(boolean value) {
        super.setCenterShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setCacheShape(boolean value) {
        super.setCacheShape(value);
        return this;
    }

    //endregion Set Functions

    //endregion RegionConfig Functions

    //region ControlConfig Functions
    //*****************************************************************
    // ControlConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        super.addSkinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSkinInvalidationListener(InvalidationListener invalidationListener) {
        super.addSkinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        super.addTooltipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTooltipInvalidationListener(InvalidationListener invalidationListener) {
        super.addTooltipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        super.addContextMenuChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addContextMenuInvalidationListener(InvalidationListener invalidationListener) {
        super.addContextMenuInvalidationListener(invalidationListener);
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
    public TextAreaConfigurator removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        super.removeSkinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeSkinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSkinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        super.removeTooltipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTooltipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTooltipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        super.removeContextMenuChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeContextMenuInvalidationListener(InvalidationListener invalidationListener) {
        super.removeContextMenuInvalidationListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue) {
        super.bindSkinProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindSkinProperty() {
        super.unbindSkinProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalSkinProperty(Property<Skin<?>> property) {
        super.bindBidirectionalSkinProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalSkinProperty(Property<Skin<?>> property) {
        super.unbindBidirectionalSkinProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue) {
        super.bindTooltipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindTooltipProperty() {
        super.unbindTooltipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalTooltipProperty(Property<Tooltip> property) {
        super.bindBidirectionalTooltipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalTooltipProperty(Property<Tooltip> property) {
        super.unbindBidirectionalTooltipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue) {
        super.bindContextMenuProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindContextMenuProperty() {
        super.unbindContextMenuProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        super.bindBidirectionalContextMenuProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        super.unbindBidirectionalContextMenuProperty(property);
        return this;
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setSkin(Skin<?> value) {
        super.setSkin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setToolTip(Tooltip value) {
        super.setToolTip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setContextMenu(ContextMenu value) {
        super.setContextMenu(value);
        return this;
    }

    //endregion Set Functions

    //endregion ControlConfig Functions

    //region TextInputControlConfig Functions
    //*****************************************************************
    // TextInputControlConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFontChangeListener(ChangeListener<? super Font> changeListener) {
        super.addFontChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addFontInvalidationListener(InvalidationListener invalidationListener) {
        super.addFontInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPromptTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addPromptTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addPromptTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addPromptTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        super.addTextFormatterChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextFormatterInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLengthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLengthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addLengthInvalidationListener(InvalidationListener invalidationListener) {
        super.addLengthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addEditableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addEditableInvalidationListener(InvalidationListener invalidationListener) {
        super.addEditableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        super.addSelectionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSelectionInvalidationListener(InvalidationListener invalidationListener) {
        super.addSelectionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addSelectedTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addSelectedTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        super.addCaretPositionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        super.addCaretPositionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        super.addAnchorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addAnchorInvalidationListener(InvalidationListener invalidationListener) {
        super.addAnchorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addUndoableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addUndoableInvalidationListener(InvalidationListener invalidationListener) {
        super.addUndoableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addRedoableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator addRedoableInvalidationListener(InvalidationListener invalidationListener) {
        super.addRedoableInvalidationListener(invalidationListener);
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
    public TextAreaConfigurator removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        super.removeFontChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeFontInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFontInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePromptTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removePromptTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removePromptTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removePromptTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        super.removeTextFormatterChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextFormatterInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLengthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLengthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeLengthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLengthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeEditableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeEditableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEditableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        super.removeSelectionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeSelectionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSelectionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeSelectedTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSelectedTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeCaretPositionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCaretPositionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeAnchorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeAnchorInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAnchorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeUndoableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeUndoableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeUndoableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeRedoableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator removeRedoableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRedoableInvalidationListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindFontProperty(ObservableValue<? extends Font> observableValue) {
        super.bindFontProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindFontProperty() {
        super.unbindFontProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalFontProperty(Property<Font> otherProperty) {
        super.bindBidirectionalFontProperty(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalFontProperty(Property<Font> otherProperty) {
        super.unbindBidirectionalFontProperty(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindPromptTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindPromptTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindPromptTextProperty() {
        super.unbindPromptTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalPromptTextProperty(Property<String> property) {
        super.bindBidirectionalPromptTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalPromptTextProperty(Property<String> property) {
        super.unbindBidirectionalPromptTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindTextFormatterProperty(ObservableValue<? extends TextFormatter<?>> observableValue) {
        super.bindTextFormatterProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindTextFormatterProperty() {
        super.unbindTextFormatterProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        super.bindBidirectionalTextFormatterProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        super.unbindBidirectionalTextFormatterProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindTextProperty() {
        super.unbindTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalTextProperty(Property<String> property) {
        super.bindBidirectionalTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalTextProperty(Property<String> property) {
        super.unbindBidirectionalTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindEditableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindEditableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindEditableProperty() {
        super.unbindEditableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator bindBidirectionalEditableProperty(Property<Boolean> property) {
        super.bindBidirectionalEditableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator unbindBidirectionalEditableProperty(Property<Boolean> property) {
        super.unbindBidirectionalEditableProperty(property);
        return this;
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setFont(Font value) {
        super.setFont(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setPromptText(String value) {
        super.setPromptText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setTextFormatter(TextFormatter<?> value) {
        super.setTextFormatter(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setText(String value) {
        super.setText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator setEditable(boolean value) {
        super.setEditable(value);
        return this;
    }

    //endregion Set Functions

    //region Text Manipulation Functions
    //*****************************************************************
    // Text Manipulation Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator appendText(String text) {
        super.appendText(text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator insertText(int index, String text) {
        super.insertText(index, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator deleteText(IndexRange range) {
        super.deleteText(range);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator deleteText(int start, int end) {
        super.deleteText(start, end);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator replaceText(IndexRange range, String text) {
        super.replaceText(range, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator cut() {
        super.cut();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator copy() {
        super.copy();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator paste() {
        super.paste();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectBackward() {
        super.selectBackward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectForward() {
        super.selectForward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator previousWord() {
        super.previousWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator nextWord() {
        super.nextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator endOfNextWord() {
        super.endOfNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectPreviousWord() {
        super.selectPreviousWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectNextWord() {
        super.selectNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectEndOfNextWord() {
        super.selectEndOfNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectAll() {
        super.selectAll();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator home() {
        super.home();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator end() {
        super.end();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectHome() {
        super.selectHome();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectEnd() {
        super.selectEnd();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator deletePreviousChar() {
        super.deletePreviousChar();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator deleteNextChar() {
        super.deleteNextChar();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator forward() {
        super.forward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator backward() {
        super.backward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator positionCaret(int pos) {
        super.positionCaret(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectPositionCaret(int pos) {
        super.selectPositionCaret(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator selectRange(int anchor, int caretPosition) {
        super.selectRange(anchor, caretPosition);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator extendSelection(int pos) {
        super.extendSelection(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator clear() {
        super.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator deselect() {
        super.deselect();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator replaceSelection(String replacement) {
        super.replaceSelection(replacement);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator undo() {
        super.undo();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator redo() {
        super.redo();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator commitValue() {
        super.commitValue();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextAreaConfigurator cancelEdit() {
        super.cancelEdit();
        return this;
    }

    //endregion Text Manipulation Functions

    //endregion TextInputControlConfig Functions
}
