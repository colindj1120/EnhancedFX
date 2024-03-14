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

import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.controls.ButtonBaseConfig;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.controls.ButtonConfig;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.layout.RegionConfigurator;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.scene.NodeConfigurator;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.scene.ParentConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.event.*;
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
 * The {@code ButtonConfigurator} class extends {@code ButtonBaseConfigurator} and implements {@code ButtonConfig}, providing a comprehensive suite of configuration options specifically tailored for
 * {@link Button} instances in JavaFX applications. This configurator encapsulates a {@link Button} instance, facilitating the customization of its properties, behavior, and event handling through a fluent
 * API.
 *
 * <p>
 * Building upon the hierarchy of configurators from {@link NodeConfigurator}, {@link ParentConfigurator}, {@link RegionConfigurator}, {@link ControlConfigurator}, {@link LabeledConfigurator}, to
 * {@link ButtonBaseConfigurator}, this class introduces configurations applicable exclusively to {@code Button} controls, such as setting the button as a default or cancel button. It ensures that all methods
 * from its parent classes are implemented, allowing for seamless method chaining across configurations of all parent and child properties and behaviors.
 * </p>
 *
 * <h2>Key features include:</h2>
 * <p>
 * <ul>
 *     <li>Adding and removing listeners for the "default" and "cancel" button properties.</li>
 *     <li>Binding and unbinding both unidirectionally and bidirectionally for the "default" and "cancel" button properties.</li>
 *     <li>Setting a button as either default or cancel button directly.</li>
 *     <li>Overriding parent class methods for method chaining compatibility.</li>
 * </ul>
 * The {@code ButtonConfigurator} simplifies the JavaFX button configuration process, promoting a cleaner, more readable, and maintainable approach to UI setup.
 * </p>
 *
 * <p>
 * <em>Example Usage:</em>
 * <pre>
 * Button myButton = new Button("Click me!");
 * ButtonConfigurator.configure(myButton)
 *                  .setDefaultButton(true)
 *                  .addDefaultButtonChangeListener((observable, oldValue, newValue) -> System.out.println("Default state changed"))
 *                  .setOnAction(e -> System.out.println("Button clicked!"));
 * </pre>
 * This example demonstrates how {@code ButtonConfigurator} can be used to configure a {@code Button}, set it as a default button, listen for changes to its default property, and handle its action events,
 * all in a fluid and intuitive manner.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Button
 * @see NodeConfigurator
 * @see ParentConfigurator
 * @see RegionConfigurator
 * @see ControlConfigurator
 * @see LabelConfigurator
 * @see ButtonBaseConfigurator
 * @see ButtonConfig
 */
public class ButtonConfigurator extends ButtonBaseConfigurator implements ButtonConfig {
    /**
     * Creates a new {@code ButtonConfigurator} instance for the specified {@link TextField}. This factory method facilitates the creation of a configurator tailored to the provided button, enabling immediate
     * access to configuration methods.
     * <p>
     * This approach abstracts the instantiation process, allowing for fluent and intuitive setup of text fields through method chaining.
     * </p>
     *
     * @param button
     *         The {@link Button} to be configured by the newly created {@code ButtonConfigurator}.
     *
     * @return A new instance of {@code ButtonConfigurator} linked to the specified button.
     */
    public static ButtonConfigurator create(Button button) {
        return new ButtonConfigurator(button);
    }

    /**
     * The {@link Button} instance that is being configured. This field holds a reference to the specific button object upon which configuration methods will act, enabling the modification and customization of
     * its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the button , allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable approach
     * to UI customization. The configurator provides a fluent API for configuring various aspects of the button , including its appearance, behavior, and event handling.
     * </p>
     */
    private Button button;

    /**
     * Constructs a {@code ButtonConfigurator} for the specified {@link Button}. This constructor initializes the configurator with a target button. It sets up the environment for further configuration specific
     * to {@link Button} instances. The {@code ButtonConfigurator.class} is used as the class reference for error reporting.
     *
     * @param button
     *         The {@link Button} to be configured. Must not be {@code null}, and an {@link IllegalArgumentException} will be thrown if a null button is passed. This ensures that the configurator has a valid
     *         target for configuration.
     */
    protected ButtonConfigurator(Button button) {
        super(ConfiguratorBase.checkNodeNotNullAndInstanceOf(button, Button.class, ButtonConfigurator.class, "Constructor"));
        this.button = button;
    }

    //region Overridden Functions
    //*****************************************************************
    // Overridden Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public Button getNode() {
        return button;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        super.setNode(ConfiguratorBase.checkNodeNotNullAndInstanceOf(value, Button.class, ButtonConfigurator.class, "setNode"));
        this.button = ((Button) value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return button.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ButtonConfigurator buttonConfigurator) {
            return Objects.equals(buttonConfigurator.getNode(), this.button);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("ButtonConfigurator current button: %s", button.toString());
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
    public ButtonConfig addDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        button.defaultButtonProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig addDefaultButtonInvalidationListener(InvalidationListener invalidationListener) {
        button.defaultButtonProperty()
              .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig addCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        button.cancelButtonProperty()
              .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig addCancelButtonInvalidationListener(InvalidationListener invalidationListener) {
        button.cancelButtonProperty()
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
    public ButtonConfig removeDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        button.defaultButtonProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig removeDefaultButtonInvalidationListener(InvalidationListener invalidationListener) {
        button.defaultButtonProperty()
              .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig removeCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        button.cancelButtonProperty()
              .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig removeCancelButtonInvalidationListener(InvalidationListener invalidationListener) {
        button.cancelButtonProperty()
              .removeListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    // DefaultButton Property

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig bindDefaultButtonProperty(ObservableValue<? extends Boolean> observableValue) {
        button.defaultButtonProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig unbindDefaultButtonProperty() {
        button.defaultButtonProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig bindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty) {
        button.defaultButtonProperty()
              .bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig unbindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty) {
        button.defaultButtonProperty()
              .unbindBidirectional(otherProperty);
        return this;
    }

    // CancelButton Property

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig bindCancelButtonProperty(ObservableValue<? extends Boolean> observableValue) {
        button.cancelButtonProperty()
              .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig unbindCancelButtonProperty() {
        button.cancelButtonProperty()
              .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig bindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty) {
        button.cancelButtonProperty()
              .bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig unbindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty) {
        button.cancelButtonProperty()
              .unbindBidirectional(otherProperty);
        return this;
    }

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig setDefaultButton(boolean value) {
        button.setDefaultButton(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfig setCancelButton(boolean value) {
        button.setCancelButton(value);
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
    public ButtonConfigurator addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.addAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.addBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.addBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.addCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.addClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addClipInvalidationListener(InvalidationListener invalidationListener) {
        super.addClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.addCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.addCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.addDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.addDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.addEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.addEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.addEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.addHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addIdChangeListener(ChangeListener<? super String> changeListener) {
        super.addIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addIdInvalidationListener(InvalidationListener invalidationListener) {
        super.addIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.addInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.addInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.addManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.addMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.addOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.addOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.addOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.addParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addPickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.addSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.addSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.addRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.addRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.addStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.addTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.addTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.addViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.addViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addVisibleInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.removeAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.removeBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.removeCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.removeClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeClipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.removeCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.removeDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.removeEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.removeEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeIdChangeListener(ChangeListener<? super String> changeListener) {
        super.removeIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeIdInvalidationListener(InvalidationListener invalidationListener) {
        super.removeIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.removeInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.removeOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.removeOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.removeParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removePickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.removeSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.removeRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.removeStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.removeTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeVisibleInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator setAccessibleHelp(String value) {
        super.setAccessibleHelp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setAccessibleRole(AccessibleRole value) {
        super.setAccessibleRole(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setAccessibleRoleDescription(String value) {
        super.setAccessibleRoleDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setAccessibleText(String value) {
        super.setAccessibleText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setBlendMode(BlendMode value) {
        super.setBlendMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setCache(boolean value) {
        super.setCache(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setCacheHint(CacheHint value) {
        super.setCacheHint(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setClip(Node value) {
        super.setClip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setCursor(Cursor value) {
        super.setCursor(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setDepthTest(DepthTest value) {
        super.setDepthTest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setDisable(boolean value) {
        super.setDisable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setEffect(Effect value) {
        super.setEffect(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setEventDispatcher(EventDispatcher value) {
        super.setEventDispatcher(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setFocusTraversable(boolean value) {
        super.setFocusTraversable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setId(String value) {
        super.setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setInputMethodRequests(InputMethodRequests value) {
        super.setInputMethodRequests(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setLayoutX(double value) {
        super.setLayoutX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setLayoutY(double value) {
        super.setLayoutY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setManaged(boolean value) {
        super.setManaged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setMouseTransparent(boolean value) {
        super.setMouseTransparent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setNodeOrientation(NodeOrientation value) {
        super.setNodeOrientation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
        super.setOnContextMenuRequested(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnDragDetected(EventHandler<? super MouseEvent> value) {
        super.setOnDragDetected(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnDragDone(EventHandler<? super DragEvent> value) {
        super.setOnDragDone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnDragDropped(EventHandler<? super DragEvent> value) {
        super.setOnDragDropped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnDragEntered(EventHandler<? super DragEvent> value) {
        super.setOnDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnDragExited(EventHandler<? super DragEvent> value) {
        super.setOnDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnDragOver(EventHandler<? super DragEvent> value) {
        super.setOnDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
        super.setOnInputMethodTextChanged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnKeyPressed(EventHandler<? super KeyEvent> value) {
        super.setOnKeyPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnKeyTyped(EventHandler<? super KeyEvent> value) {
        super.setOnKeyTyped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseClicked(EventHandler<? super MouseEvent> value) {
        super.setOnMouseClicked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseDragExited(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseDragOver(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseEntered(EventHandler<? super MouseEvent> value) {
        super.setOnMouseEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseExited(EventHandler<? super MouseEvent> value) {
        super.setOnMouseExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        super.setOnMouseMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMousePressed(EventHandler<? super MouseEvent> value) {
        super.setOnMousePressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        super.setOnMouseReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnRotate(EventHandler<? super RotateEvent> value) {
        super.setOnRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnRotationFinished(EventHandler<? super RotateEvent> value) {
        super.setOnRotationFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnRotationStarted(EventHandler<? super RotateEvent> value) {
        super.setOnRotationStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnScroll(EventHandler<? super ScrollEvent> value) {
        super.setOnScroll(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnScrollFinished(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnScrollStarted(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOpacity(double value) {
        super.setOpacity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setStyle(String style) {
        super.setStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnSwipeDown(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeDown(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeLeft(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnSwipeRight(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeRight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnSwipeUp(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeUp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnTouchMoved(EventHandler<? super TouchEvent> value) {
        super.setOnTouchMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnTouchPressed(EventHandler<? super TouchEvent> value) {
        super.setOnTouchPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnTouchReleased(EventHandler<? super TouchEvent> value) {
        super.setOnTouchReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnTouchStationary(EventHandler<? super TouchEvent> value) {
        super.setOnTouchStationary(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnZoom(EventHandler<? super ZoomEvent> value) {
        super.setOnZoom(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnZoomFinished(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOnZoomStarted(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setPickOnBounds(boolean value) {
        super.setPickOnBounds(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setRotate(double value) {
        super.setRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setRotationAxis(Point3D value) {
        super.setRotationAxis(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setScaleX(double value) {
        super.setScaleX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setScaleY(double value) {
        super.setScaleY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setScaleZ(double value) {
        super.setScaleZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setTranslateX(double value) {
        super.setTranslateX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setTranslateY(double value) {
        super.setTranslateY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setTranslateZ(double value) {
        super.setTranslateZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setUserData(Object value) {
        super.setUserData(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setViewOrder(double value) {
        super.setViewOrder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setVisible(boolean value) {
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
    public ButtonConfigurator bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleHelpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindAccessibleHelpProperty() {
        super.unbindAccessibleHelpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.bindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleRoleDescriptionProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindAccessibleRoleDescriptionProperty() {
        super.unbindAccessibleRoleDescriptionProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.bindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue) {
        super.bindAccessibleRoleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindAccessibleRoleProperty() {
        super.unbindAccessibleRoleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.bindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.unbindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindAccessibleTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindAccessibleTextProperty() {
        super.unbindAccessibleTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.bindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue) {
        super.bindBlendModeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBlendModeProperty() {
        super.unbindBlendModeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.bindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.unbindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue) {
        super.bindCacheHintProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindCacheHintProperty() {
        super.unbindCacheHintProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.bindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.unbindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindCacheProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindCacheProperty() {
        super.unbindCacheProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalCacheProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalCacheProperty(Property<Boolean> property) {
        super.unbindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindClipProperty(ObservableValue<? extends Node> observableValue) {
        super.bindClipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindClipProperty() {
        super.unbindClipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalClipProperty(Property<Node> property) {
        super.bindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalClipProperty(Property<Node> property) {
        super.unbindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindCursorProperty(ObservableValue<? extends Cursor> observableValue) {
        super.bindCursorProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindCursorProperty() {
        super.unbindCursorProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalCursorProperty(Property<Cursor> property) {
        super.bindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalCursorProperty(Property<Cursor> property) {
        super.unbindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue) {
        super.bindDepthTestProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindDepthTestProperty() {
        super.unbindDepthTestProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.bindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.unbindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindDisableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindDisableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindDisableProperty() {
        super.unbindDisableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalDisableProperty(Property<Boolean> property) {
        super.bindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalDisableProperty(Property<Boolean> property) {
        super.unbindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindEffectProperty(ObservableValue<? extends Effect> observableValue) {
        super.bindEffectProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindEffectProperty() {
        super.unbindEffectProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalEffectProperty(Property<Effect> property) {
        super.bindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalEffectProperty(Property<Effect> property) {
        super.unbindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue) {
        super.bindEventDispatcherProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindEventDispatcherProperty() {
        super.unbindEventDispatcherProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.bindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.unbindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindFocusTraversableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindFocusTraversableProperty() {
        super.unbindFocusTraversableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.bindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.unbindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindIdProperty(ObservableValue<? extends String> observableValue) {
        super.bindIdProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindIdProperty() {
        super.unbindIdProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalIdProperty(Property<String> property) {
        super.bindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalIdProperty(Property<String> property) {
        super.unbindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue) {
        super.bindInputMethodRequestsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindInputMethodRequestsProperty() {
        super.unbindInputMethodRequestsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.bindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.unbindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindLayoutXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindLayoutXProperty() {
        super.unbindLayoutXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalLayoutXProperty(Property<Number> property) {
        super.bindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalLayoutXProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindLayoutYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindLayoutYProperty() {
        super.unbindLayoutYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalLayoutYProperty(Property<Number> property) {
        super.bindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalLayoutYProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindManagedProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindManagedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindManagedProperty() {
        super.unbindManagedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalManagedProperty(Property<Boolean> property) {
        super.bindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalManagedProperty(Property<Boolean> property) {
        super.unbindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindMouseTransparentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindMouseTransparentProperty() {
        super.unbindMouseTransparentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.bindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.unbindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue) {
        super.bindNodeOrientationProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindNodeOrientationProperty() {
        super.unbindNodeOrientationProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.bindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.unbindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnContextMenuRequestedProperty(ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue) {
        super.bindOnContextMenuRequestedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnContextMenuRequestedProperty() {
        super.unbindOnContextMenuRequestedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property) {
        super.bindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property) {
        super.unbindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnDragDetectedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnDragDetectedProperty() {
        super.unbindOnDragDetectedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDoneProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnDragDoneProperty() {
        super.unbindOnDragDoneProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDroppedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnDragDroppedProperty() {
        super.unbindOnDragDroppedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnDragEnteredProperty() {
        super.unbindOnDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnDragExitedProperty() {
        super.unbindOnDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnDragOverProperty() {
        super.unbindOnDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnInputMethodTextChangedProperty(ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue) {
        super.bindOnInputMethodTextChangedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnInputMethodTextChangedProperty() {
        super.unbindOnInputMethodTextChangedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property) {
        super.bindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property) {
        super.unbindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnKeyPressedProperty() {
        super.unbindOnKeyPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnKeyReleasedProperty() {
        super.unbindOnKeyReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyTypedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnKeyTypedProperty() {
        super.unbindOnKeyTypedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseClickedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseClickedProperty() {
        super.unbindOnMouseClickedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseDragEnteredProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseDragEnteredProperty() {
        super.unbindOnMouseDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseDragExitedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseDragExitedProperty() {
        super.unbindOnMouseDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseDraggedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseDraggedProperty() {
        super.unbindOnMouseDraggedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseDragOverProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseDragOverProperty() {
        super.unbindOnMouseDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseDragReleasedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseDragReleasedProperty() {
        super.unbindOnMouseDragReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseEnteredProperty() {
        super.unbindOnMouseEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseExitedProperty() {
        super.unbindOnMouseExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseMovedProperty() {
        super.unbindOnMouseMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMousePressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMousePressedProperty() {
        super.unbindOnMousePressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnMouseReleasedProperty() {
        super.unbindOnMouseReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnRotateProperty() {
        super.unbindOnRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnRotationFinishedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnRotationFinishedProperty() {
        super.unbindOnRotationFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnRotationStartedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnRotationStartedProperty() {
        super.unbindOnRotationStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnScrollProperty() {
        super.unbindOnScrollProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnScrollFinishedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnScrollFinishedProperty() {
        super.unbindOnScrollFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnScrollStartedProperty() {
        super.unbindOnScrollStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeDownProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnSwipeDownProperty() {
        super.unbindOnSwipeDownProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeLeftProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnSwipeLeftProperty() {
        super.unbindOnSwipeLeftProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeRightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnSwipeRightProperty() {
        super.unbindOnSwipeRightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeUpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnSwipeUpProperty() {
        super.unbindOnSwipeUpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnTouchMovedProperty() {
        super.unbindOnTouchMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnTouchPressedProperty() {
        super.unbindOnTouchPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnTouchReleasedProperty() {
        super.unbindOnTouchReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnTouchStationaryProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchStationaryProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnTouchStationaryProperty() {
        super.unbindOnTouchStationaryProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnZoomProperty() {
        super.unbindOnZoomProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnZoomFinishedProperty() {
        super.unbindOnZoomFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOnZoomStartedProperty() {
        super.unbindOnZoomStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOpacityProperty(ObservableValue<? extends Number> observableValue) {
        super.bindOpacityProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOpacityProperty() {
        super.unbindOpacityProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOpacityProperty(Property<Number> property) {
        super.bindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOpacityProperty(Property<Number> property) {
        super.unbindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindPickOnBoundsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindPickOnBoundsProperty() {
        super.unbindPickOnBoundsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.bindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.unbindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindRotateProperty(ObservableValue<? extends Number> observableValue) {
        super.bindRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindRotateProperty() {
        super.unbindRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalRotateProperty(Property<Number> property) {
        super.bindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalRotateProperty(Property<Number> property) {
        super.unbindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue) {
        super.bindRotationAxisProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindRotationAxisProperty() {
        super.unbindRotationAxisProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.bindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.unbindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindScaleXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindScaleXProperty() {
        super.unbindScaleXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalScaleXProperty(Property<Number> property) {
        super.bindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalScaleXProperty(Property<Number> property) {
        super.unbindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindScaleYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindScaleYProperty() {
        super.unbindScaleYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalScaleYProperty(Property<Number> property) {
        super.bindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalScaleYProperty(Property<Number> property) {
        super.unbindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindScaleZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindScaleZProperty() {
        super.unbindScaleZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalScaleZProperty(Property<Number> property) {
        super.bindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalScaleZProperty(Property<Number> property) {
        super.unbindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindStyleProperty(ObservableValue<? extends String> observableValue) {
        super.bindStyleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindStyleProperty() {
        super.unbindStyleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalStyleProperty(Property<String> property) {
        super.bindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalStyleProperty(Property<String> property) {
        super.unbindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindTranslateXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindTranslateXProperty() {
        super.unbindTranslateXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalTranslateXProperty(Property<Number> property) {
        super.bindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalTranslateXProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindTranslateYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindTranslateYProperty() {
        super.unbindTranslateYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalTranslateYProperty(Property<Number> property) {
        super.bindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalTranslateYProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindTranslateZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindTranslateZProperty() {
        super.unbindTranslateZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalTranslateZProperty(Property<Number> property) {
        super.bindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalTranslateZProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindViewOrderProperty(ObservableValue<? extends Number> observableValue) {
        super.bindViewOrderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindViewOrderProperty() {
        super.unbindViewOrderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalViewOrderProperty(Property<Number> property) {
        super.bindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalViewOrderProperty(Property<Number> property) {
        super.unbindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindVisibleProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindVisibleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindVisibleProperty() {
        super.unbindVisibleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalVisibleProperty(Property<Boolean> property) {
        super.bindBidirectionalVisibleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalVisibleProperty(Property<Boolean> property) {
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
    public ButtonConfigurator fireEvent(Event event) {
        super.fireEvent(event);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> ButtonConfigurator addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.addEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> ButtonConfigurator addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        super.addEventHandler(eventType, eventHandler);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> ButtonConfigurator removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.removeEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> ButtonConfigurator removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
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
    public ButtonConfigurator addFirstStyleClass(String styleClass) {
        super.addFirstStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLastStyleClass(String styleClass) {
        super.addLastStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStyleClass(String styleClass) {
        super.addStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStyleClass(int index, String styleClass) {
        super.addStyleClass(index, styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllStyleClasses(String... styleClasses) {
        super.addAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllStyleClasses(Collection<? extends String> c) {
        super.addAllStyleClasses(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllStyleClasses(int index, Collection<? extends String> c) {
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
    public ButtonConfigurator removeFirstStyleClass() {
        super.removeFirstStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLastStyleClass() {
        super.removeLastStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStyleClass(String styleClass) {
        super.removeStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStyleClasses(int from, int to) {
        super.removeStyleClasses(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStyleClassesIf(Predicate<? super String> filter) {
        super.removeStyleClassesIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAllStyleClasses(String... styleClasses) {
        super.removeAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAllStyleClasses(Collection<? extends String> c) {
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
    public ButtonConfigurator pseudoClassStateChange(PseudoClass pseudoClass, boolean active) {
        super.pseudoClassStateChange(pseudoClass, active);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator applyCss() {
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
    public ButtonConfigurator addFirstTransform(Transform transform) {
        super.addFirstTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLastTransform(Transform transform) {
        super.addLastTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTransform(Transform transform) {
        super.addTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTransform(int index, Transform transform) {
        super.addTransform(index, transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllTransforms(Transform... transforms) {
        super.addAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllTransforms(Collection<? extends Transform> c) {
        super.addAllTransforms(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllTransforms(int index, Collection<? extends Transform> c) {
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
    public ButtonConfigurator removeFirstTransform() {
        super.removeFirstTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLastTransform() {
        super.removeLastTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTransform(Transform transform) {
        super.removeTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTransforms(int from, int to) {
        super.removeTransforms(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTransformsIf(Predicate<? super Transform> filter) {
        super.removeTransformsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAllTransforms(Transform... transforms) {
        super.removeAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAllTransforms(Collection<? extends Transform> c) {
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
    public ButtonConfigurator toBack() {
        super.toBack();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator toFront() {
        super.toFront();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator resize(double width, double height) {
        super.resize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator autosize() {
        super.autosize();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator resizeRelocate(double x, double y, double width, double height) {
        super.resizeRelocate(x, y, width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator requestFocus() {
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
    public ButtonConfigurator addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.addNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.addGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.addGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.removeGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator requestLayout() {
        super.requestLayout();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator Layout() {
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
    public ButtonConfigurator addFirstStylesheet(String stylesheet) {
        super.addFirstStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLastStylesheet(String stylesheet) {
        super.addLastStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStylesheet(String stylesheet) {
        super.addStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addStylesheet(int index, String stylesheet) {
        super.addStylesheet(index, stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllStylesheets(String... stylesheets) {
        super.addAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllStylesheets(Collection<? extends String> c) {
        super.addAllStylesheets(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAllStylesheets(int index, Collection<? extends String> c) {
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
    public ButtonConfigurator removeFirstStylesheet() {
        super.removeFirstStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLastStylesheet() {
        super.removeLastStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStylesheet(String stylesheet) {
        super.removeStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStylesheets(int from, int to) {
        super.removeStylesheets(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeStylesheetsIf(Predicate<? super String> filter) {
        super.removeStylesheetsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAllStylesheets(String... stylesheets) {
        super.removeAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAllStylesheets(Collection<? extends String> c) {
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
    public ButtonConfigurator addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addSnapToPixelChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        super.addSnapToPixelInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addPaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.addPaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        super.addBackgroundChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        super.addBackgroundInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBorderChangeListener(ChangeListener<? super Border> changeListener) {
        super.addBorderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addBorderInvalidationListener(InvalidationListener invalidationListener) {
        super.addBorderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addOpaqueInsetsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpaqueInsetsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addInsetChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addInsetInvalidationListener(InvalidationListener invalidationListener) {
        super.addInsetInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMinWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addMinWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addPrefWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addPrefWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMaxWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addMaxWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMinHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addMinHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addPrefHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addPrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addPrefHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMaxHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addMaxHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        super.addShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addScaleShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCenterShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addCenterShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeSnapToPixelChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSnapToPixelInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removePaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.removePaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        super.removeBackgroundChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBackgroundInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBorderChangeListener(ChangeListener<? super Border> changeListener) {
        super.removeBorderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeBorderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBorderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeOpaqueInsetsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpaqueInsetsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeInsetChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeInsetInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInsetInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMinWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMinWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removePrefWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removePrefWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMaxWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMaxWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMinHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMinHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removePrefHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removePrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removePrefHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMaxHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMaxHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        super.removeShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeScaleShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCenterShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCenterShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindSnapToPixelProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindSnapToPixelProperty() {
        super.unbindSnapToPixelProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        super.bindBidirectionalSnapToPixelProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        super.unbindBidirectionalSnapToPixelProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindPaddingProperty(ObservableValue<? extends Insets> observableValue) {
        super.bindPaddingProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindPaddingProperty() {
        super.unbindPaddingProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalPaddingProperty(Property<Insets> property) {
        super.bindBidirectionalPaddingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalPaddingProperty(Property<Insets> property) {
        super.unbindBidirectionalPaddingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBackgroundProperty(ObservableValue<? extends Background> observableValue) {
        super.bindBackgroundProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBackgroundProperty() {
        super.unbindBackgroundProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalBackgroundProperty(Property<Background> property) {
        super.bindBidirectionalBackgroundProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalBackgroundProperty(Property<Background> property) {
        super.unbindBidirectionalBackgroundProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBorderProperty(ObservableValue<? extends Border> observableValue) {
        super.bindBorderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBorderProperty() {
        super.unbindBorderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalBorderProperty(Property<Border> property) {
        super.bindBidirectionalBorderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalBorderProperty(Property<Border> property) {
        super.unbindBidirectionalBorderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue) {
        super.bindOpaqueInsetsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindOpaqueInsetsProperty() {
        super.unbindOpaqueInsetsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        super.bindBidirectionalOpaqueInsetsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        super.unbindBidirectionalOpaqueInsetsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindMinWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMinWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindMinWidthProperty() {
        super.unbindMinWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalMinWidthProperty(Property<Number> property) {
        super.bindBidirectionalMinWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalMinWidthProperty(Property<Number> property) {
        super.unbindBidirectionalMinWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindPrefWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindPrefWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindPrefWidthProperty() {
        super.unbindPrefWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalPrefWidthProperty(Property<Number> property) {
        super.bindBidirectionalPrefWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalPrefWidthProperty(Property<Number> property) {
        super.unbindBidirectionalPrefWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindMaxWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMaxWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindMaxWidthProperty() {
        super.unbindMaxWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalMaxWidthProperty(Property<Number> property) {
        super.bindBidirectionalMaxWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalMaxWidthProperty(Property<Number> property) {
        super.unbindBidirectionalMaxWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindMinHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMinHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindMinHeightProperty() {
        super.unbindMinHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalMinHeightProperty(Property<Number> property) {
        super.bindBidirectionalMinHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalMinHeightProperty(Property<Number> property) {
        super.unbindBidirectionalMinHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindPrefHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindPrefHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindPrefHeightProperty() {
        super.unbindPrefHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalPrefHeightProperty(Property<Number> property) {
        super.bindBidirectionalPrefHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalPrefHeightProperty(Property<Number> property) {
        super.unbindBidirectionalPrefHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindMaxHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMaxHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindMaxHeightProperty() {
        super.unbindMaxHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalMaxHeightProperty(Property<Number> property) {
        super.bindBidirectionalMaxHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalMaxHeightProperty(Property<Number> property) {
        super.unbindBidirectionalMaxHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindShapeProperty(ObservableValue<? extends Shape> observableValue) {
        super.bindShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindShapeProperty() {
        super.unbindShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalShapeProperty(Property<Shape> property) {
        super.bindBidirectionalShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalShapeProperty(Property<Shape> property) {
        super.unbindBidirectionalShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindScaleShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindScaleShapeProperty() {
        super.unbindScaleShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalScaleShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalScaleShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCenterShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindCenterShapeProperty() {
        super.unbindCenterShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalCenterShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalCenterShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindCacheShapeProperty() {
        super.unbindCacheShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalCacheShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalCacheShapeProperty(Property<Boolean> property) {
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
    public ButtonConfigurator setSnapToPixel(boolean value) {
        super.setSnapToPixel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setPadding(Insets value) {
        super.setPadding(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setBackground(Background value) {
        super.setBackground(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setBorder(Border value) {
        super.setBorder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setOpaqueInsets(Insets value) {
        super.setOpaqueInsets(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setMinWidth(double value) {
        super.setMinWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setPrefWidth(double value) {
        super.setPrefWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setMaxWidth(double value) {
        super.setMaxWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setMinHeight(double value) {
        super.setMinHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setPrefHeight(double value) {
        super.setPrefHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setMaxHeight(double value) {
        super.setMaxHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setMinSize(double width, double height) {
        super.setMinSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setPrefSize(double width, double height) {
        super.setPrefSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setMaxSize(double width, double height) {
        super.setMaxSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setShape(Shape value) {
        super.setShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setScaleShape(boolean value) {
        super.setScaleShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setCenterShape(boolean value) {
        super.setCenterShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setCacheShape(boolean value) {
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
    public ButtonConfigurator addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        super.addSkinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addSkinInvalidationListener(InvalidationListener invalidationListener) {
        super.addSkinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        super.addTooltipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTooltipInvalidationListener(InvalidationListener invalidationListener) {
        super.addTooltipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        super.addContextMenuChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addContextMenuInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        super.removeSkinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeSkinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSkinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        super.removeTooltipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTooltipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTooltipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        super.removeContextMenuChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeContextMenuInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue) {
        super.bindSkinProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindSkinProperty() {
        super.unbindSkinProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalSkinProperty(Property<Skin<?>> property) {
        super.bindBidirectionalSkinProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalSkinProperty(Property<Skin<?>> property) {
        super.unbindBidirectionalSkinProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue) {
        super.bindTooltipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindTooltipProperty() {
        super.unbindTooltipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalTooltipProperty(Property<Tooltip> property) {
        super.bindBidirectionalTooltipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalTooltipProperty(Property<Tooltip> property) {
        super.unbindBidirectionalTooltipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue) {
        super.bindContextMenuProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindContextMenuProperty() {
        super.unbindContextMenuProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        super.bindBidirectionalContextMenuProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
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
    public ButtonConfigurator setSkin(Skin<?> value) {
        super.setSkin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setToolTip(Tooltip value) {
        super.setToolTip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setContextMenu(ContextMenu value) {
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
    public ButtonConfigurator addTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        super.addAlignmentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        super.addAlignmentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        super.addTextAlignmentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextAlignmentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        super.addTextOverrunChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextOverrunInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        super.addEllipsisStringChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        super.addEllipsisStringInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addWrapTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addWrapTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFontChangeListener(ChangeListener<? super Font> changeListener) {
        super.addFontChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addFontInvalidationListener(InvalidationListener invalidationListener) {
        super.addFontInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        super.addGraphicChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addGraphicInvalidationListener(InvalidationListener invalidationListener) {
        super.addGraphicInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addUnderlineChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        super.addUnderlineInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLineSpacingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        super.addLineSpacingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        super.addContentDisplayChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        super.addContentDisplayInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addLabelPaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.addLabelPaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        super.addGraphicTextGapChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        super.addGraphicTextGapInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        super.addTextFillChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addTextFillInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextFillInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addMnemonicParsingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator addMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator removeTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        super.removeAlignmentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAlignmentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        super.removeTextAlignmentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextAlignmentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        super.removeTextOverrunChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextOverrunInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        super.removeEllipsisStringChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEllipsisStringInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeWrapTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeWrapTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        super.removeFontChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeFontInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFontInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        super.removeGraphicChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeGraphicInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGraphicInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeUnderlineChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        super.removeUnderlineInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLineSpacingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLineSpacingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        super.removeContentDisplayChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        super.removeContentDisplayInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeLabelPaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLabelPaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeGraphicTextGapChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGraphicTextGapInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        super.removeTextFillChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeTextFillInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextFillInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeMnemonicParsingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator removeMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
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
    public ButtonConfigurator bindTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindTextProperty() {
        super.unbindTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalTextProperty(Property<String> property) {
        super.bindBidirectionalTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalTextProperty(Property<String> property) {
        super.unbindBidirectionalTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindAlignmentProperty(ObservableValue<? extends Pos> observableValue) {
        super.bindAlignmentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindAlignmentProperty() {
        super.unbindAlignmentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalAlignmentProperty(Property<Pos> property) {
        super.bindBidirectionalAlignmentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalAlignmentProperty(Property<Pos> property) {
        super.unbindBidirectionalAlignmentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindTextAlignmentProperty(ObservableValue<? extends TextAlignment> observableValue) {
        super.bindTextAlignmentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindTextAlignmentProperty() {
        super.unbindTextAlignmentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        super.bindBidirectionalTextAlignmentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        super.unbindBidirectionalTextAlignmentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindTextOverrunProperty(ObservableValue<? extends OverrunStyle> observableValue) {
        super.bindTextOverrunProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindTextOverrunProperty() {
        super.unbindTextOverrunProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        super.bindBidirectionalTextOverrunProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        super.unbindBidirectionalTextOverrunProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindEllipsisStringProperty(ObservableValue<? extends String> observableValue) {
        super.bindEllipsisStringProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindEllipsisStringProperty() {
        super.unbindEllipsisStringProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalEllipsisStringProperty(Property<String> property) {
        super.bindBidirectionalEllipsisStringProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalEllipsisStringProperty(Property<String> property) {
        super.unbindBidirectionalEllipsisStringProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindWrapTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindWrapTextProperty() {
        super.unbindWrapTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalWrapTextProperty(Property<Boolean> property) {
        super.bindBidirectionalWrapTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalWrapTextProperty(Property<Boolean> property) {
        super.unbindBidirectionalWrapTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindFontProperty(ObservableValue<? extends Font> observableValue) {
        super.bindFontProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindFontProperty() {
        super.unbindFontProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalFontProperty(Property<Font> property) {
        super.bindBidirectionalFontProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalFontProperty(Property<Font> property) {
        super.unbindBidirectionalFontProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindGraphicProperty(ObservableValue<? extends Node> observableValue) {
        super.bindGraphicProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindGraphicProperty() {
        super.unbindGraphicProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalGraphicProperty(Property<Node> property) {
        super.bindBidirectionalGraphicProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalGraphicProperty(Property<Node> property) {
        super.unbindBidirectionalGraphicProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindUnderlineProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindUnderlineProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindUnderlineProperty() {
        super.unbindUnderlineProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalUnderlineProperty(Property<Boolean> property) {
        super.bindBidirectionalUnderlineProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalUnderlineProperty(Property<Boolean> property) {
        super.unbindBidirectionalUnderlineProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindLineSpacingProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLineSpacingProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindLineSpacingProperty() {
        super.unbindLineSpacingProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalLineSpacingProperty(Property<Number> property) {
        super.bindBidirectionalLineSpacingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalLineSpacingProperty(Property<Number> property) {
        super.unbindBidirectionalLineSpacingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindContentDisplayProperty(ObservableValue<? extends ContentDisplay> observableValue) {
        super.bindContentDisplayProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindContentDisplayProperty() {
        super.unbindContentDisplayProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        super.bindBidirectionalContentDisplayProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        super.unbindBidirectionalContentDisplayProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindGraphicTextGapProperty(ObservableValue<? extends Number> observableValue) {
        super.bindGraphicTextGapProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindGraphicTextGapProperty() {
        super.unbindGraphicTextGapProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        super.bindBidirectionalGraphicTextGapProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        super.unbindBidirectionalGraphicTextGapProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindTextFillProperty(ObservableValue<? extends Paint> observableValue) {
        super.bindTextFillProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindTextFillProperty() {
        super.unbindTextFillProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalTextFillProperty(Property<Paint> property) {
        super.bindBidirectionalTextFillProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalTextFillProperty(Property<Paint> property) {
        super.unbindBidirectionalTextFillProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindMnemonicParsingProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindMnemonicParsingProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindMnemonicParsingProperty() {
        super.unbindMnemonicParsingProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator bindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        super.bindBidirectionalMnemonicParsingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator unbindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
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
    public ButtonConfigurator setText(String value) {
        super.setText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setAlignment(Pos value) {
        super.setAlignment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setTextAlignment(TextAlignment value) {
        super.setTextAlignment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setTextOverrun(OverrunStyle value) {
        super.setTextOverrun(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setEllipsisString(String value) {
        super.setEllipsisString(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setWrapText(boolean value) {
        super.setWrapText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setFont(Font value) {
        super.setFont(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setGraphic(Node value) {
        super.setGraphic(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setUnderline(boolean value) {
        super.setUnderline(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setLineSpacing(double value) {
        super.setLineSpacing(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setContentDisplay(ContentDisplay value) {
        super.setContentDisplay(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setGraphicTextGap(double value) {
        super.setGraphicTextGap(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setTextFill(Paint value) {
        super.setTextFill(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonConfigurator setMnemonicParsing(boolean value) {
        super.setMnemonicParsing(value);
        return this;
    }

    //endregion Set Functions

    //endregion LabeledConfig Functions

    //region ButtonBaseConfig Functions
    //*****************************************************************
    // ButtonBaseConfig Functions
    //*****************************************************************

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig addArmedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addArmedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig addArmedInvalidationListener(InvalidationListener invalidationListener) {
        super.addArmedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        super.addOnActionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig addOnActionInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnActionInvalidationListener(invalidationListener);
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
    public ButtonBaseConfig removeArmedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeArmedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig removeArmedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeArmedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        super.removeOnActionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig removeOnActionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnActionInvalidationListener(invalidationListener);
        return this;
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //On Action Property

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue) {
        super.bindOnActionProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig unbindOnActionProperty() {
        super.unbindOnActionProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        super.bindBidirectionalOnActionProperty(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        super.unbindBidirectionalOnActionProperty(otherProperty);
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
    public ButtonBaseConfig setOnAction(EventHandler<ActionEvent> value) {
        super.setOnAction(value);
        return this;
    }

    //endregion Set Functions

    //region Button Action Functions
    //*****************************************************************
    // Button Action Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig arm() {
        super.arm();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig disarm() {
        super.disarm();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ButtonBaseConfig fire() {
        super.fire();
        return this;
    }

    //endregion Button Action Functions

    //endregion ButtonBaseConfig Functions
}
