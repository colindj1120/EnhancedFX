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
package io.github.colindj1120.enhancedfx.controls.factory.configurators.controls;

import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.controls.LabelConfig;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.layout.RegionConfigurator;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.scene.NodeConfigurator;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.scene.ParentConfigurator;
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
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Transform;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * A specialized configurator for {@link Label} instances in JavaFX, designed to provide a fluent and comprehensive approach to
 * configuring labels. This class extends {@link LabeledConfigurator} and implements {@link LabelConfig}, ensuring a wide range of
 * configuration options including those available to all {@link Labeled} types as well as specific functionalities pertinent to
 * {@link Label} instances.
 *
 * <p>
 * The configurator enables developers to customize labels by setting properties such as 'labelFor', binding properties to observable
 * values, and adding or removing listeners for property changes. This streamlined approach to configuration promotes code readability
 * and maintainability by encapsulating complex setup routines into a series of method calls.
 * </p>
 *
 * <p>
 * <h2>Capabilities include:</h2>
 * <ul>
 *     <li>Setting the 'labelFor' property to associate the label with another control, enhancing form accessibility.</li>
 *     <li>Adding and removing listeners for the 'labelFor' property to react to changes dynamically.</li>
 *     <li>Binding the 'labelFor' property to observable values for automatic updates.</li>
 *     <li>Utilizing bidirectional bindings to synchronize the 'labelFor' property with another property.</li>
 *     <li>Inheriting the ability to configure text, font, alignment, graphic, and other properties from parent configurators.</li>
 * </ul>
 * Additionally, the configurator supports method chaining from its parent classes ({@link NodeConfigurator},
 * {@link ParentConfigurator}, {@link RegionConfigurator}, {@link ControlConfigurator}, and {@link LabeledConfigurator}), enabling
 * a cohesive and versatile configuration experience.
 * </p>
 *
 * <p>
 * <em>Usage example:</em>
 * <pre>{@code
 * Label myLabel = new Label("Username:");
 * TextField usernameField = new TextField();
 * LabelConfigurator.create(myLabel)
 *     .setLabelFor(usernameField)
 *     .setFont(Font.font("Arial", FontWeight.BOLD, 12))
 *     .setTextFill(Color.DARKBLUE)
 *     .bindLabelForProperty(usernameField.focusedProperty().map(focused -> focused ? usernameField : null));
 * }</pre>
 * This example demonstrates creating a {@code LabelConfigurator} to configure a label, including setting the labelFor property
 * dynamically based on the focus state of a {@code TextField}, showcasing the power and flexibility of the configurator.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Label
 * @see LabelConfig
 * @see LabeledConfigurator
 * @see ControlConfigurator
 * @see RegionConfigurator
 * @see ParentConfigurator
 * @see NodeConfigurator
 */
public class LabelConfigurator extends LabeledConfigurator implements LabelConfig {
    /**
     * Creates a new {@code LabelConfigurator} instance for the specified {@link Label}. This factory method facilitates the creation
     * of a configurator tailored to the provided label, enabling immediate access to configuration methods.
     * <p>
     * This approach abstracts the instantiation process, allowing for fluent and intuitive setup of labels through method chaining.
     * </p>
     *
     * @param label
     *         The {@link Label} to be configured by the newly created {@code LabelConfigurator}.
     *
     * @return A new instance of {@code LabelConfigurator} linked to the specified text field.
     */
    public static LabelConfigurator create(Label label) {
        return new LabelConfigurator(label);
    }

    /**
     * The {@link Label} instance that is being configured. This field holds a reference to the specific label object upon which
     * configuration methods will act, enabling the modification and customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the label, allowing changes to be made through the configurator's methods rather
     * than direct access, promoting a more structured and maintainable approach to UI customization. The configurator provides a
     * fluent API for configuring various aspects of the label, including its appearance, behavior, and event handling.
     * </p>
     */
    private Label label;

    /**
     * Constructs a {@code LabelConfigurator} for the specified {@link Label}. This constructor initializes the configurator with a
     * target label. It sets up the environment for further configuration specific to {@link Label} instances. The
     * {@code LabelConfigurator.class} is used as the class reference for error reporting.
     *
     * @param label
     *         The {@link Label} to be configured. Must not be {@code null}, and an {@link IllegalArgumentException} will be thrown if
     *         a null labeled is passed. This ensures that the configurator has a valid target for configuration.
     */
    protected LabelConfigurator(Label label) {
        super(checkNodeNotNullAndInstanceOf(label, Label.class, LabelConfigurator.class, "Constructor"));
        this.label = label;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public Label getNode() {
        return this.label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        super.setNode(checkNodeNotNullAndInstanceOf(value, Label.class, LabelConfigurator.class, "setNode"));
        this.label = ((Label) value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return label.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LabelConfigurator labelConfigurator) {
            return Objects.equals(labelConfigurator.getNode(), this.label);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("LabelConfigurator current label: %s", label.toString());
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
    public LabelConfigurator addLabelForChangeListener(ChangeListener<? super Node> changeListener) {
        label.labelForProperty()
             .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLabelForInvalidationListener(InvalidationListener invalidationListener) {
        label.labelForProperty()
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
    public LabelConfigurator removeLabelForChangeListener(ChangeListener<? super Node> changeListener) {
        label.labelForProperty()
             .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLabelForInvalidationListener(InvalidationListener invalidationListener) {
        label.labelForProperty()
             .removeListener(invalidationListener);
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
    public LabelConfigurator bindLabelForProperty(ObservableValue<? extends Node> observableValue) {
        label.labelForProperty()
             .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindLabelForProperty() {
        label.labelForProperty()
             .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalLabelForProperty(Property<Node> otherProperty) {
        label.labelForProperty()
             .bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalLabelForProperty(Property<Node> otherProperty) {
        label.labelForProperty()
             .unbindBidirectional(otherProperty);
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
    public LabelConfigurator setLabelFor(Node value) {
        label.setLabelFor(value);
        return this;
    }

    //endregion Set Functions

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
    public LabelConfigurator addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.addAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.addBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.addBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.addCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.addClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addClipInvalidationListener(InvalidationListener invalidationListener) {
        super.addClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.addCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.addCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.addDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.addDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.addEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.addEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.addEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.addHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addIdChangeListener(ChangeListener<? super String> changeListener) {
        super.addIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addIdInvalidationListener(InvalidationListener invalidationListener) {
        super.addIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.addInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.addInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.addManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.addMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnContextMenuRequestedChangeListener(
            ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.addOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragDetectedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragDroppedChangeListener(
            ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragEnteredChangeListener(
            ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragExitedChangeListener(
            ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnInputMethodTextChangedChangeListener(
            ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.addOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnKeyReleasedChangeListener(
            ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseClickedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDragEnteredChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDragExitedChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDraggedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDragOverChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDragReleasedChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseEnteredChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseExitedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseMovedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMousePressedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseReleasedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnRotationFinishedChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnRotationStartedChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnScrollFinishedChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnScrollStartedChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnSwipeDownChangeListener(
            ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnSwipeLeftChangeListener(
            ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnSwipeRightChangeListener(
            ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnTouchMovedChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnTouchPressedChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnTouchReleasedChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnTouchStationaryChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnZoomFinishedChangeListener(
            ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnZoomStartedChangeListener(
            ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.addOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.addParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addPickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.addSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.addSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.addRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.addRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.addStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.addTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.addTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.addViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.addViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addVisibleInvalidationListener(InvalidationListener invalidationListener) {
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
    public LabelConfigurator removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.removeAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.removeBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.removeCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.removeClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeClipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.removeCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.removeDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.removeEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.removeEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeIdChangeListener(ChangeListener<? super String> changeListener) {
        super.removeIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeIdInvalidationListener(InvalidationListener invalidationListener) {
        super.removeIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.removeInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnContextMenuRequestedChangeListener(
            ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.removeOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragDetectedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragDoneChangeListener(
            ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragDroppedChangeListener(
            ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragEnteredChangeListener(
            ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragExitedChangeListener(
            ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragOverChangeListener(
            ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnInputMethodTextChangedChangeListener(
            ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.removeOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnKeyPressedChangeListener(
            ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnKeyReleasedChangeListener(
            ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnKeyTypedChangeListener(
            ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseClickedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDragEnteredChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDragExitedChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDraggedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDragOverChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDragReleasedChangeListener(
            ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseEnteredChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseExitedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseMovedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMousePressedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseReleasedChangeListener(
            ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnRotateChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnRotationFinishedChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnRotationStartedChangeListener(
            ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnScrollFinishedChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnScrollChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnScrollStartedChangeListener(
            ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnSwipeDownChangeListener(
            ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnSwipeLeftChangeListener(
            ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnSwipeRightChangeListener(
            ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnSwipeUpChangeListener(
            ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnTouchMovedChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnTouchPressedChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnTouchReleasedChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnTouchStationaryChangeListener(
            ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnZoomFinishedChangeListener(
            ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnZoomStartedChangeListener(
            ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.removeParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removePickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.removeSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.removeRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.removeStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.removeTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeVisibleInvalidationListener(invalidationListener);
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
    public LabelConfigurator setAccessibleHelp(String value) {
        super.setAccessibleHelp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setAccessibleRole(AccessibleRole value) {
        super.setAccessibleRole(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setAccessibleRoleDescription(String value) {
        super.setAccessibleRoleDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setAccessibleText(String value) {
        super.setAccessibleText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setBlendMode(BlendMode value) {
        super.setBlendMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setCache(boolean value) {
        super.setCache(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setCacheHint(CacheHint value) {
        super.setCacheHint(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setClip(Node value) {
        super.setClip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setCursor(Cursor value) {
        super.setCursor(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setDepthTest(DepthTest value) {
        super.setDepthTest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setDisable(boolean value) {
        super.setDisable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setEffect(Effect value) {
        super.setEffect(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setEventDispatcher(EventDispatcher value) {
        super.setEventDispatcher(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setFocusTraversable(boolean value) {
        super.setFocusTraversable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setId(String value) {
        super.setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setInputMethodRequests(InputMethodRequests value) {
        super.setInputMethodRequests(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setLayoutX(double value) {
        super.setLayoutX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setLayoutY(double value) {
        super.setLayoutY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setManaged(boolean value) {
        super.setManaged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setMouseTransparent(boolean value) {
        super.setMouseTransparent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setNodeOrientation(NodeOrientation value) {
        super.setNodeOrientation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
        super.setOnContextMenuRequested(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnDragDetected(EventHandler<? super MouseEvent> value) {
        super.setOnDragDetected(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnDragDone(EventHandler<? super DragEvent> value) {
        super.setOnDragDone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnDragDropped(EventHandler<? super DragEvent> value) {
        super.setOnDragDropped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnDragEntered(EventHandler<? super DragEvent> value) {
        super.setOnDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnDragExited(EventHandler<? super DragEvent> value) {
        super.setOnDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnDragOver(EventHandler<? super DragEvent> value) {
        super.setOnDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
        super.setOnInputMethodTextChanged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnKeyPressed(EventHandler<? super KeyEvent> value) {
        super.setOnKeyPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnKeyTyped(EventHandler<? super KeyEvent> value) {
        super.setOnKeyTyped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseClicked(EventHandler<? super MouseEvent> value) {
        super.setOnMouseClicked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseDragExited(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseDragOver(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseEntered(EventHandler<? super MouseEvent> value) {
        super.setOnMouseEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseExited(EventHandler<? super MouseEvent> value) {
        super.setOnMouseExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        super.setOnMouseMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMousePressed(EventHandler<? super MouseEvent> value) {
        super.setOnMousePressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        super.setOnMouseReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnRotate(EventHandler<? super RotateEvent> value) {
        super.setOnRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnRotationFinished(EventHandler<? super RotateEvent> value) {
        super.setOnRotationFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnRotationStarted(EventHandler<? super RotateEvent> value) {
        super.setOnRotationStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnScroll(EventHandler<? super ScrollEvent> value) {
        super.setOnScroll(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnScrollFinished(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnScrollStarted(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOpacity(double value) {
        super.setOpacity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setStyle(String style) {
        super.setStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnSwipeDown(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeDown(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeLeft(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnSwipeRight(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeRight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnSwipeUp(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeUp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnTouchMoved(EventHandler<? super TouchEvent> value) {
        super.setOnTouchMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnTouchPressed(EventHandler<? super TouchEvent> value) {
        super.setOnTouchPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnTouchReleased(EventHandler<? super TouchEvent> value) {
        super.setOnTouchReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnTouchStationary(EventHandler<? super TouchEvent> value) {
        super.setOnTouchStationary(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnZoom(EventHandler<? super ZoomEvent> value) {
        super.setOnZoom(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnZoomFinished(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOnZoomStarted(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setPickOnBounds(boolean value) {
        super.setPickOnBounds(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setRotate(double value) {
        super.setRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setRotationAxis(Point3D value) {
        super.setRotationAxis(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setScaleX(double value) {
        super.setScaleX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setScaleY(double value) {
        super.setScaleY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setScaleZ(double value) {
        super.setScaleZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setTranslateX(double value) {
        super.setTranslateX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setTranslateY(double value) {
        super.setTranslateY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setTranslateZ(double value) {
        super.setTranslateZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setUserData(Object value) {
        super.setUserData(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setViewOrder(double value) {
        super.setViewOrder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setVisible(boolean value) {
        super.setVisible(value);
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
    public LabelConfigurator bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleHelpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindAccessibleHelpProperty() {
        super.unbindAccessibleHelpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.bindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleRoleDescriptionProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindAccessibleRoleDescriptionProperty() {
        super.unbindAccessibleRoleDescriptionProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.bindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue) {
        super.bindAccessibleRoleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindAccessibleRoleProperty() {
        super.unbindAccessibleRoleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.bindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.unbindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindAccessibleTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindAccessibleTextProperty() {
        super.unbindAccessibleTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.bindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue) {
        super.bindBlendModeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBlendModeProperty() {
        super.unbindBlendModeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.bindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.unbindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue) {
        super.bindCacheHintProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindCacheHintProperty() {
        super.unbindCacheHintProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.bindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.unbindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindCacheProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindCacheProperty() {
        super.unbindCacheProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalCacheProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalCacheProperty(Property<Boolean> property) {
        super.unbindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindClipProperty(ObservableValue<? extends Node> observableValue) {
        super.bindClipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindClipProperty() {
        super.unbindClipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalClipProperty(Property<Node> property) {
        super.bindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalClipProperty(Property<Node> property) {
        super.unbindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindCursorProperty(ObservableValue<? extends Cursor> observableValue) {
        super.bindCursorProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindCursorProperty() {
        super.unbindCursorProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalCursorProperty(Property<Cursor> property) {
        super.bindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalCursorProperty(Property<Cursor> property) {
        super.unbindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue) {
        super.bindDepthTestProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindDepthTestProperty() {
        super.unbindDepthTestProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.bindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.unbindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindDisableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindDisableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindDisableProperty() {
        super.unbindDisableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalDisableProperty(Property<Boolean> property) {
        super.bindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalDisableProperty(Property<Boolean> property) {
        super.unbindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindEffectProperty(ObservableValue<? extends Effect> observableValue) {
        super.bindEffectProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindEffectProperty() {
        super.unbindEffectProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalEffectProperty(Property<Effect> property) {
        super.bindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalEffectProperty(Property<Effect> property) {
        super.unbindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue) {
        super.bindEventDispatcherProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindEventDispatcherProperty() {
        super.unbindEventDispatcherProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.bindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.unbindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindFocusTraversableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindFocusTraversableProperty() {
        super.unbindFocusTraversableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.bindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.unbindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindIdProperty(ObservableValue<? extends String> observableValue) {
        super.bindIdProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindIdProperty() {
        super.unbindIdProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalIdProperty(Property<String> property) {
        super.bindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalIdProperty(Property<String> property) {
        super.unbindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue) {
        super.bindInputMethodRequestsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindInputMethodRequestsProperty() {
        super.unbindInputMethodRequestsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.bindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.unbindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindLayoutXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindLayoutXProperty() {
        super.unbindLayoutXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalLayoutXProperty(Property<Number> property) {
        super.bindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalLayoutXProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindLayoutYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindLayoutYProperty() {
        super.unbindLayoutYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalLayoutYProperty(Property<Number> property) {
        super.bindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalLayoutYProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindManagedProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindManagedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindManagedProperty() {
        super.unbindManagedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalManagedProperty(Property<Boolean> property) {
        super.bindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalManagedProperty(Property<Boolean> property) {
        super.unbindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindMouseTransparentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindMouseTransparentProperty() {
        super.unbindMouseTransparentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.bindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.unbindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue) {
        super.bindNodeOrientationProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindNodeOrientationProperty() {
        super.unbindNodeOrientationProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.bindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.unbindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnContextMenuRequestedProperty(
            ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue) {
        super.bindOnContextMenuRequestedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnContextMenuRequestedProperty() {
        super.unbindOnContextMenuRequestedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnContextMenuRequestedProperty(
            Property<EventHandler<? super ContextMenuEvent>> property) {
        super.bindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnContextMenuRequestedProperty(
            Property<EventHandler<? super ContextMenuEvent>> property) {
        super.unbindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnDragDetectedProperty(
            ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnDragDetectedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnDragDetectedProperty() {
        super.unbindOnDragDetectedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDoneProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnDragDoneProperty() {
        super.unbindOnDragDoneProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnDragDroppedProperty(
            ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDroppedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnDragDroppedProperty() {
        super.unbindOnDragDroppedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnDragEnteredProperty(
            ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnDragEnteredProperty() {
        super.unbindOnDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnDragExitedProperty() {
        super.unbindOnDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnDragOverProperty() {
        super.unbindOnDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnInputMethodTextChangedProperty(
            ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue) {
        super.bindOnInputMethodTextChangedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnInputMethodTextChangedProperty() {
        super.unbindOnInputMethodTextChangedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnInputMethodTextChangedProperty(
            Property<EventHandler<? super InputMethodEvent>> property) {
        super.bindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnInputMethodTextChangedProperty(
            Property<EventHandler<? super InputMethodEvent>> property) {
        super.unbindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnKeyPressedProperty() {
        super.unbindOnKeyPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnKeyReleasedProperty() {
        super.unbindOnKeyReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyTypedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnKeyTypedProperty() {
        super.unbindOnKeyTypedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseClickedProperty(
            ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseClickedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseClickedProperty() {
        super.unbindOnMouseClickedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseDragEnteredProperty(
            ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseDragEnteredProperty() {
        super.unbindOnMouseDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseDragEnteredProperty(
            Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseDragExitedProperty(
            ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseDragExitedProperty() {
        super.unbindOnMouseDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseDragExitedProperty(
            Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseDraggedProperty(
            ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseDraggedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseDraggedProperty() {
        super.unbindOnMouseDraggedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseDragOverProperty(
            ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseDragOverProperty() {
        super.unbindOnMouseDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseDragReleasedProperty(
            ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseDragReleasedProperty() {
        super.unbindOnMouseDragReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseDragReleasedProperty(
            Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseDragReleasedProperty(
            Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseEnteredProperty(
            ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseEnteredProperty() {
        super.unbindOnMouseEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseExitedProperty(
            ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseExitedProperty() {
        super.unbindOnMouseExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseMovedProperty(
            ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseMovedProperty() {
        super.unbindOnMouseMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMousePressedProperty(
            ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMousePressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMousePressedProperty() {
        super.unbindOnMousePressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnMouseReleasedProperty(
            ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnMouseReleasedProperty() {
        super.unbindOnMouseReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnRotateProperty() {
        super.unbindOnRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnRotationFinishedProperty(
            ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnRotationFinishedProperty() {
        super.unbindOnRotationFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnRotationStartedProperty(
            ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnRotationStartedProperty() {
        super.unbindOnRotationStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnScrollProperty() {
        super.unbindOnScrollProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnScrollFinishedProperty(
            ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnScrollFinishedProperty() {
        super.unbindOnScrollFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnScrollStartedProperty(
            ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnScrollStartedProperty() {
        super.unbindOnScrollStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeDownProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnSwipeDownProperty() {
        super.unbindOnSwipeDownProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeLeftProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnSwipeLeftProperty() {
        super.unbindOnSwipeLeftProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnSwipeRightProperty(
            ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeRightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnSwipeRightProperty() {
        super.unbindOnSwipeRightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeUpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnSwipeUpProperty() {
        super.unbindOnSwipeUpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnTouchMovedProperty(
            ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnTouchMovedProperty() {
        super.unbindOnTouchMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnTouchPressedProperty(
            ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnTouchPressedProperty() {
        super.unbindOnTouchPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnTouchReleasedProperty(
            ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnTouchReleasedProperty() {
        super.unbindOnTouchReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnTouchStationaryProperty(
            ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchStationaryProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnTouchStationaryProperty() {
        super.unbindOnTouchStationaryProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnZoomProperty() {
        super.unbindOnZoomProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnZoomFinishedProperty(
            ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnZoomFinishedProperty() {
        super.unbindOnZoomFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOnZoomStartedProperty(
            ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOnZoomStartedProperty() {
        super.unbindOnZoomStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOpacityProperty(ObservableValue<? extends Number> observableValue) {
        super.bindOpacityProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOpacityProperty() {
        super.unbindOpacityProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOpacityProperty(Property<Number> property) {
        super.bindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOpacityProperty(Property<Number> property) {
        super.unbindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindPickOnBoundsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindPickOnBoundsProperty() {
        super.unbindPickOnBoundsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.bindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.unbindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindRotateProperty(ObservableValue<? extends Number> observableValue) {
        super.bindRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindRotateProperty() {
        super.unbindRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalRotateProperty(Property<Number> property) {
        super.bindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalRotateProperty(Property<Number> property) {
        super.unbindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue) {
        super.bindRotationAxisProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindRotationAxisProperty() {
        super.unbindRotationAxisProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.bindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.unbindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindScaleXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindScaleXProperty() {
        super.unbindScaleXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalScaleXProperty(Property<Number> property) {
        super.bindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalScaleXProperty(Property<Number> property) {
        super.unbindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindScaleYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindScaleYProperty() {
        super.unbindScaleYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalScaleYProperty(Property<Number> property) {
        super.bindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalScaleYProperty(Property<Number> property) {
        super.unbindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindScaleZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindScaleZProperty() {
        super.unbindScaleZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalScaleZProperty(Property<Number> property) {
        super.bindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalScaleZProperty(Property<Number> property) {
        super.unbindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindStyleProperty(ObservableValue<? extends String> observableValue) {
        super.bindStyleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindStyleProperty() {
        super.unbindStyleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalStyleProperty(Property<String> property) {
        super.bindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalStyleProperty(Property<String> property) {
        super.unbindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindTranslateXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindTranslateXProperty() {
        super.unbindTranslateXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalTranslateXProperty(Property<Number> property) {
        super.bindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalTranslateXProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindTranslateYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindTranslateYProperty() {
        super.unbindTranslateYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalTranslateYProperty(Property<Number> property) {
        super.bindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalTranslateYProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindTranslateZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindTranslateZProperty() {
        super.unbindTranslateZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalTranslateZProperty(Property<Number> property) {
        super.bindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalTranslateZProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindViewOrderProperty(ObservableValue<? extends Number> observableValue) {
        super.bindViewOrderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindViewOrderProperty() {
        super.unbindViewOrderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalViewOrderProperty(Property<Number> property) {
        super.bindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalViewOrderProperty(Property<Number> property) {
        super.unbindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindVisibleProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindVisibleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindVisibleProperty() {
        super.unbindVisibleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalVisibleProperty(Property<Boolean> property) {
        super.bindBidirectionalVisibleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalVisibleProperty(Property<Boolean> property) {
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
    public LabelConfigurator fireEvent(Event event) {
        super.fireEvent(event);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> LabelConfigurator addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.addEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> LabelConfigurator addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        super.addEventHandler(eventType, eventHandler);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> LabelConfigurator removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.removeEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> LabelConfigurator removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        super.removeEventHandler(eventType, eventHandler);
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
    public LabelConfigurator addFirstStyleClass(String styleClass) {
        super.addFirstStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLastStyleClass(String styleClass) {
        super.addLastStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStyleClass(String styleClass) {
        super.addStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStyleClass(int index, String styleClass) {
        super.addStyleClass(index, styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllStyleClasses(String... styleClasses) {
        super.addAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllStyleClasses(Collection<? extends String> c) {
        super.addAllStyleClasses(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllStyleClasses(int index, Collection<? extends String> c) {
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
    public LabelConfigurator removeFirstStyleClass() {
        super.removeFirstStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLastStyleClass() {
        super.removeLastStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStyleClass(String styleClass) {
        super.removeStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStyleClasses(int from, int to) {
        super.removeStyleClasses(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStyleClassesIf(Predicate<? super String> filter) {
        super.removeStyleClassesIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAllStyleClasses(String... styleClasses) {
        super.removeAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAllStyleClasses(Collection<? extends String> c) {
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
    public LabelConfigurator pseudoClassStateChange(PseudoClass pseudoClass, boolean active) {
        super.pseudoClassStateChange(pseudoClass, active);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator applyCss() {
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
    public LabelConfigurator addFirstTransform(Transform transform) {
        super.addFirstTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLastTransform(Transform transform) {
        super.addLastTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTransform(Transform transform) {
        super.addTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTransform(int index, Transform transform) {
        super.addTransform(index, transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllTransforms(Transform... transforms) {
        super.addAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllTransforms(Collection<? extends Transform> c) {
        super.addAllTransforms(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllTransforms(int index, Collection<? extends Transform> c) {
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
    public LabelConfigurator removeFirstTransform() {
        super.removeFirstTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLastTransform() {
        super.removeLastTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTransform(Transform transform) {
        super.removeTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTransforms(int from, int to) {
        super.removeTransforms(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTransformsIf(Predicate<? super Transform> filter) {
        super.removeTransformsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAllTransforms(Transform... transforms) {
        super.removeAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAllTransforms(Collection<? extends Transform> c) {
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
    public LabelConfigurator toBack() {
        super.toBack();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator toFront() {
        super.toFront();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator resize(double width, double height) {
        super.resize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator autosize() {
        super.autosize();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator resizeRelocate(double x, double y, double width, double height) {
        super.resizeRelocate(x, y, width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator requestFocus() {
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
    public LabelConfigurator addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.addNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.addGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.addGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
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
    public LabelConfigurator removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.removeGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
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
    public LabelConfigurator requestLayout() {
        super.requestLayout();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator Layout() {
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
    public LabelConfigurator addFirstStylesheet(String stylesheet) {
        super.addFirstStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLastStylesheet(String stylesheet) {
        super.addLastStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStylesheet(String stylesheet) {
        super.addStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addStylesheet(int index, String stylesheet) {
        super.addStylesheet(index, stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllStylesheets(String... stylesheets) {
        super.addAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllStylesheets(Collection<? extends String> c) {
        super.addAllStylesheets(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAllStylesheets(int index, Collection<? extends String> c) {
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
    public LabelConfigurator removeFirstStylesheet() {
        super.removeFirstStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLastStylesheet() {
        super.removeLastStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStylesheet(String stylesheet) {
        super.removeStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStylesheets(int from, int to) {
        super.removeStylesheets(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeStylesheetsIf(Predicate<? super String> filter) {
        super.removeStylesheetsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAllStylesheets(String... stylesheets) {
        super.removeAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAllStylesheets(Collection<? extends String> c) {
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
    public LabelConfigurator addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addSnapToPixelChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        super.addSnapToPixelInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addPaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.addPaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        super.addBackgroundChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        super.addBackgroundInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBorderChangeListener(ChangeListener<? super Border> changeListener) {
        super.addBorderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addBorderInvalidationListener(InvalidationListener invalidationListener) {
        super.addBorderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addOpaqueInsetsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpaqueInsetsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addInsetChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addInsetInvalidationListener(InvalidationListener invalidationListener) {
        super.addInsetInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMinWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addMinWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addPrefWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addPrefWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMaxWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addMaxWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMinHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addMinHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addPrefHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addPrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addPrefHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMaxHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addMaxHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        super.addShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addScaleShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCenterShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addCenterShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
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
    public LabelConfigurator removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeSnapToPixelChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSnapToPixelInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removePaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.removePaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        super.removeBackgroundChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBackgroundInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBorderChangeListener(ChangeListener<? super Border> changeListener) {
        super.removeBorderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeBorderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBorderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeOpaqueInsetsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpaqueInsetsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeInsetChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeInsetInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInsetInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMinWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMinWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removePrefWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removePrefWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMaxWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMaxWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMinHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMinHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removePrefHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removePrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removePrefHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMaxHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMaxHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        super.removeShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeScaleShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCenterShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCenterShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
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
    public LabelConfigurator bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindSnapToPixelProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindSnapToPixelProperty() {
        super.unbindSnapToPixelProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        super.bindBidirectionalSnapToPixelProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        super.unbindBidirectionalSnapToPixelProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindPaddingProperty(ObservableValue<? extends Insets> observableValue) {
        super.bindPaddingProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindPaddingProperty() {
        super.unbindPaddingProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalPaddingProperty(Property<Insets> property) {
        super.bindBidirectionalPaddingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalPaddingProperty(Property<Insets> property) {
        super.unbindBidirectionalPaddingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBackgroundProperty(ObservableValue<? extends Background> observableValue) {
        super.bindBackgroundProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBackgroundProperty() {
        super.unbindBackgroundProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalBackgroundProperty(Property<Background> property) {
        super.bindBidirectionalBackgroundProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalBackgroundProperty(Property<Background> property) {
        super.unbindBidirectionalBackgroundProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBorderProperty(ObservableValue<? extends Border> observableValue) {
        super.bindBorderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBorderProperty() {
        super.unbindBorderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalBorderProperty(Property<Border> property) {
        super.bindBidirectionalBorderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalBorderProperty(Property<Border> property) {
        super.unbindBidirectionalBorderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue) {
        super.bindOpaqueInsetsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindOpaqueInsetsProperty() {
        super.unbindOpaqueInsetsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        super.bindBidirectionalOpaqueInsetsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        super.unbindBidirectionalOpaqueInsetsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindMinWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMinWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindMinWidthProperty() {
        super.unbindMinWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalMinWidthProperty(Property<Number> property) {
        super.bindBidirectionalMinWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalMinWidthProperty(Property<Number> property) {
        super.unbindBidirectionalMinWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindPrefWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindPrefWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindPrefWidthProperty() {
        super.unbindPrefWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalPrefWidthProperty(Property<Number> property) {
        super.bindBidirectionalPrefWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalPrefWidthProperty(Property<Number> property) {
        super.unbindBidirectionalPrefWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindMaxWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMaxWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindMaxWidthProperty() {
        super.unbindMaxWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalMaxWidthProperty(Property<Number> property) {
        super.bindBidirectionalMaxWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalMaxWidthProperty(Property<Number> property) {
        super.unbindBidirectionalMaxWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindMinHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMinHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindMinHeightProperty() {
        super.unbindMinHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalMinHeightProperty(Property<Number> property) {
        super.bindBidirectionalMinHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalMinHeightProperty(Property<Number> property) {
        super.unbindBidirectionalMinHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindPrefHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindPrefHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindPrefHeightProperty() {
        super.unbindPrefHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalPrefHeightProperty(Property<Number> property) {
        super.bindBidirectionalPrefHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalPrefHeightProperty(Property<Number> property) {
        super.unbindBidirectionalPrefHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindMaxHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMaxHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindMaxHeightProperty() {
        super.unbindMaxHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalMaxHeightProperty(Property<Number> property) {
        super.bindBidirectionalMaxHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalMaxHeightProperty(Property<Number> property) {
        super.unbindBidirectionalMaxHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindShapeProperty(ObservableValue<? extends Shape> observableValue) {
        super.bindShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindShapeProperty() {
        super.unbindShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalShapeProperty(Property<Shape> property) {
        super.bindBidirectionalShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalShapeProperty(Property<Shape> property) {
        super.unbindBidirectionalShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindScaleShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindScaleShapeProperty() {
        super.unbindScaleShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalScaleShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalScaleShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCenterShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindCenterShapeProperty() {
        super.unbindCenterShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalCenterShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalCenterShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindCacheShapeProperty() {
        super.unbindCacheShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalCacheShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalCacheShapeProperty(Property<Boolean> property) {
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
    public LabelConfigurator setSnapToPixel(boolean value) {
        super.setSnapToPixel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setPadding(Insets value) {
        super.setPadding(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setBackground(Background value) {
        super.setBackground(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setBorder(Border value) {
        super.setBorder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setOpaqueInsets(Insets value) {
        super.setOpaqueInsets(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setMinWidth(double value) {
        super.setMinWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setPrefWidth(double value) {
        super.setPrefWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setMaxWidth(double value) {
        super.setMaxWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setMinHeight(double value) {
        super.setMinHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setPrefHeight(double value) {
        super.setPrefHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setMaxHeight(double value) {
        super.setMaxHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setMinSize(double width, double height) {
        super.setMinSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setPrefSize(double width, double height) {
        super.setPrefSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setMaxSize(double width, double height) {
        super.setMaxSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setShape(Shape value) {
        super.setShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setScaleShape(boolean value) {
        super.setScaleShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setCenterShape(boolean value) {
        super.setCenterShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setCacheShape(boolean value) {
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
    public LabelConfigurator addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        super.addSkinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addSkinInvalidationListener(InvalidationListener invalidationListener) {
        super.addSkinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        super.addTooltipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTooltipInvalidationListener(InvalidationListener invalidationListener) {
        super.addTooltipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        super.addContextMenuChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addContextMenuInvalidationListener(InvalidationListener invalidationListener) {
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
    public LabelConfigurator removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        super.removeSkinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeSkinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSkinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        super.removeTooltipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTooltipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTooltipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        super.removeContextMenuChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeContextMenuInvalidationListener(InvalidationListener invalidationListener) {
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
    public LabelConfigurator bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue) {
        super.bindSkinProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindSkinProperty() {
        super.unbindSkinProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalSkinProperty(Property<Skin<?>> property) {
        super.bindBidirectionalSkinProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalSkinProperty(Property<Skin<?>> property) {
        super.unbindBidirectionalSkinProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue) {
        super.bindTooltipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindTooltipProperty() {
        super.unbindTooltipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalTooltipProperty(Property<Tooltip> property) {
        super.bindBidirectionalTooltipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalTooltipProperty(Property<Tooltip> property) {
        super.unbindBidirectionalTooltipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue) {
        super.bindContextMenuProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindContextMenuProperty() {
        super.unbindContextMenuProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        super.bindBidirectionalContextMenuProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
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
    public LabelConfigurator setSkin(Skin<?> value) {
        super.setSkin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setToolTip(Tooltip value) {
        super.setToolTip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setContextMenu(ContextMenu value) {
        super.setContextMenu(value);
        return this;
    }

    //endregion Set Functions

    //endregion ControlConfig Functions

    //region LabeledConfig Functions
    //*****************************************************************
    // LabeledConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        super.addAlignmentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        super.addAlignmentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        super.addTextAlignmentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextAlignmentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        super.addTextOverrunChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextOverrunInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        super.addEllipsisStringChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        super.addEllipsisStringInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addWrapTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addWrapTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFontChangeListener(ChangeListener<? super Font> changeListener) {
        super.addFontChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addFontInvalidationListener(InvalidationListener invalidationListener) {
        super.addFontInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        super.addGraphicChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addGraphicInvalidationListener(InvalidationListener invalidationListener) {
        super.addGraphicInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addUnderlineChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        super.addUnderlineInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLineSpacingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        super.addLineSpacingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        super.addContentDisplayChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        super.addContentDisplayInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addLabelPaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.addLabelPaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        super.addGraphicTextGapChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        super.addGraphicTextGapInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        super.addTextFillChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addTextFillInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextFillInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addMnemonicParsingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator addMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
        super.addMnemonicParsingInvalidationListener(invalidationListener);
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
    public LabelConfigurator removeTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        super.removeAlignmentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAlignmentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        super.removeTextAlignmentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextAlignmentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        super.removeTextOverrunChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextOverrunInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        super.removeEllipsisStringChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEllipsisStringInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeWrapTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeWrapTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        super.removeFontChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeFontInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFontInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        super.removeGraphicChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeGraphicInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGraphicInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeUnderlineChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        super.removeUnderlineInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLineSpacingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLineSpacingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        super.removeContentDisplayChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        super.removeContentDisplayInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeLabelPaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLabelPaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeGraphicTextGapChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGraphicTextGapInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        super.removeTextFillChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeTextFillInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextFillInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeMnemonicParsingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator removeMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMnemonicParsingInvalidationListener(invalidationListener);
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
    public LabelConfigurator bindTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindTextProperty() {
        super.unbindTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalTextProperty(Property<String> property) {
        super.bindBidirectionalTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalTextProperty(Property<String> property) {
        super.unbindBidirectionalTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindAlignmentProperty(ObservableValue<? extends Pos> observableValue) {
        super.bindAlignmentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindAlignmentProperty() {
        super.unbindAlignmentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalAlignmentProperty(Property<Pos> property) {
        super.bindBidirectionalAlignmentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalAlignmentProperty(Property<Pos> property) {
        super.unbindBidirectionalAlignmentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindTextAlignmentProperty(ObservableValue<? extends TextAlignment> observableValue) {
        super.bindTextAlignmentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindTextAlignmentProperty() {
        super.unbindTextAlignmentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        super.bindBidirectionalTextAlignmentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        super.unbindBidirectionalTextAlignmentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindTextOverrunProperty(ObservableValue<? extends OverrunStyle> observableValue) {
        super.bindTextOverrunProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindTextOverrunProperty() {
        super.unbindTextOverrunProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        super.bindBidirectionalTextOverrunProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        super.unbindBidirectionalTextOverrunProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindEllipsisStringProperty(ObservableValue<? extends String> observableValue) {
        super.bindEllipsisStringProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindEllipsisStringProperty() {
        super.unbindEllipsisStringProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalEllipsisStringProperty(Property<String> property) {
        super.bindBidirectionalEllipsisStringProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalEllipsisStringProperty(Property<String> property) {
        super.unbindBidirectionalEllipsisStringProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindWrapTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindWrapTextProperty() {
        super.unbindWrapTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalWrapTextProperty(Property<Boolean> property) {
        super.bindBidirectionalWrapTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalWrapTextProperty(Property<Boolean> property) {
        super.unbindBidirectionalWrapTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindFontProperty(ObservableValue<? extends Font> observableValue) {
        super.bindFontProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindFontProperty() {
        super.unbindFontProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalFontProperty(Property<Font> property) {
        super.bindBidirectionalFontProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalFontProperty(Property<Font> property) {
        super.unbindBidirectionalFontProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindGraphicProperty(ObservableValue<? extends Node> observableValue) {
        super.bindGraphicProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindGraphicProperty() {
        super.unbindGraphicProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalGraphicProperty(Property<Node> property) {
        super.bindBidirectionalGraphicProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalGraphicProperty(Property<Node> property) {
        super.unbindBidirectionalGraphicProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindUnderlineProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindUnderlineProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindUnderlineProperty() {
        super.unbindUnderlineProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalUnderlineProperty(Property<Boolean> property) {
        super.bindBidirectionalUnderlineProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalUnderlineProperty(Property<Boolean> property) {
        super.unbindBidirectionalUnderlineProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindLineSpacingProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLineSpacingProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindLineSpacingProperty() {
        super.unbindLineSpacingProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalLineSpacingProperty(Property<Number> property) {
        super.bindBidirectionalLineSpacingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalLineSpacingProperty(Property<Number> property) {
        super.unbindBidirectionalLineSpacingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindContentDisplayProperty(ObservableValue<? extends ContentDisplay> observableValue) {
        super.bindContentDisplayProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindContentDisplayProperty() {
        super.unbindContentDisplayProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        super.bindBidirectionalContentDisplayProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        super.unbindBidirectionalContentDisplayProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindGraphicTextGapProperty(ObservableValue<? extends Number> observableValue) {
        super.bindGraphicTextGapProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindGraphicTextGapProperty() {
        super.unbindGraphicTextGapProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        super.bindBidirectionalGraphicTextGapProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        super.unbindBidirectionalGraphicTextGapProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindTextFillProperty(ObservableValue<? extends Paint> observableValue) {
        super.bindTextFillProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindTextFillProperty() {
        super.unbindTextFillProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalTextFillProperty(Property<Paint> property) {
        super.bindBidirectionalTextFillProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalTextFillProperty(Property<Paint> property) {
        super.unbindBidirectionalTextFillProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindMnemonicParsingProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindMnemonicParsingProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindMnemonicParsingProperty() {
        super.unbindMnemonicParsingProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator bindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        super.bindBidirectionalMnemonicParsingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator unbindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        super.unbindBidirectionalMnemonicParsingProperty(property);
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
    public LabelConfigurator setText(String value) {
        super.setText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setAlignment(Pos value) {
        super.setAlignment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setTextAlignment(TextAlignment value) {
        super.setTextAlignment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setTextOverrun(OverrunStyle value) {
        super.setTextOverrun(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setEllipsisString(String value) {
        super.setEllipsisString(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setWrapText(boolean value) {
        super.setWrapText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setFont(Font value) {
        super.setFont(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setGraphic(Node value) {
        super.setGraphic(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setUnderline(boolean value) {
        super.setUnderline(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setLineSpacing(double value) {
        super.setLineSpacing(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setContentDisplay(ContentDisplay value) {
        super.setContentDisplay(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setGraphicTextGap(double value) {
        super.setGraphicTextGap(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setTextFill(Paint value) {
        super.setTextFill(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabelConfigurator setMnemonicParsing(boolean value) {
        super.setMnemonicParsing(value);
        return this;
    }

    //endregion Set Functions

    //endregion LabeledConfig Functions
}
