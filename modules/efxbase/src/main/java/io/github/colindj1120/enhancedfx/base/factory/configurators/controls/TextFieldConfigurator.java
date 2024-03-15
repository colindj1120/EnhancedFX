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
package io.github.colindj1120.enhancedfx.base.factory.configurators.controls;

import io.github.colindj1120.enhancedfx.base.factory.configurators.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.configurators.base.interfaces.controls.TextFieldConfig;
import io.github.colindj1120.enhancedfx.base.factory.configurators.layout.RegionConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.configurators.scene.NodeConfigurator;
import io.github.colindj1120.enhancedfx.base.factory.configurators.scene.ParentConfigurator;
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
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.transform.Transform;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Provides a comprehensive configuration utility for {@link TextField} instances within JavaFX applications. This class extends {@link TextInputControlConfigurator} and implements {@link TextFieldConfig},
 * offering a fluent API for configuring text fields by chaining method calls.
 *
 * <p>
 * <h2>Capabilities include but are not limited to:</h2>
 * <ul>
 *     <li>Adding and removing change and invalidation listeners for text field properties such as preferred column count, onAction event handler, and alignment.</li>
 *     <li>Binding and unbinding these properties to external {@link ObservableValue}s, facilitating dynamic UI updates.</li>
 *     <li>Establishing bidirectional bindings for these properties, ensuring synchronized updates between the text field and other UI components.</li>
 *     <li>Directly setting properties like preferred column count, alignment, and custom onAction event handlers for specific user actions.</li>
 *     <li>Inheriting capabilities from {@link NodeConfigurator}, {@link ParentConfigurator}, {@link RegionConfigurator}, {@link ControlConfigurator}, and {@link TextInputControlConfigurator}, such as
 *     styling, size adjustments, and event handling, thereby allowing comprehensive configuration of text fields beyond basic properties.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This configurator simplifies the process of configuring text fields, making code more readable and maintainable by abstracting complex setup and configuration sequences into a series of straightforward
 * method calls.
 * </p>
 *
 * <p>
 * <em>Usage example:</em>
 * <pre>
 * {@code
 * TextField textField = new TextField();
 * TextFieldConfigurator.create(textField)
 *     .setPrefColumnCount(10)
 *     .setAlignment(Pos.CENTER)
 *     .addOnActionChangeListener((obs, oldEvent, newEvent) -> System.out.println("Action!"))
 *     .setMinHeight(50.0)
 *     .bindTooltipProperty(observableValue)
 *     .addVisibleChangeListener((obs, oldVisible, isVisible) -> System.out.println("Visibility Changed!"))
 *     .applyCss();
 * }
 * </pre>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextField
 * @see ChangeListener
 * @see InvalidationListener
 * @see ObservableValue
 * @see EventHandler
 * @see ActionEvent
 * @see Pos
 * @see NodeConfigurator
 * @see ParentConfigurator
 * @see RegionConfigurator
 * @see ControlConfigurator
 * @see TextInputControlConfigurator
 * @see TextFieldConfig
 */
public class TextFieldConfigurator<T extends TextFieldConfigurator<T>> extends TextInputControlConfigurator<T> implements TextFieldConfig {
    /**
     * Creates a new {@code TextFieldConfigurator} instance for the specified {@link TextField}. This factory method facilitates the creation of a configurator tailored to the provided text field, enabling
     * immediate access to configuration methods.
     * <p>
     * This approach abstracts the instantiation process, allowing for fluent and intuitive setup of text fields through method chaining.
     * </p>
     *
     * @param textField
     *         The {@link TextField} to be configured by the newly created {@code TextFieldConfigurator}.
     *
     * @return A new instance of {@code TextFieldConfigurator} linked to the specified text field.
     */
    public static TextFieldConfigurator create(TextField textField) {
        return new TextFieldConfigurator(textField);
    }

    /**
     * The {@link TextField} instance that is being configured. This field holds a reference to the specific textField object upon which configuration methods will act, enabling the modification and
     * customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the textField, allowing changes to be made through the configurator's methods rather than direct access, promoting a more structured and maintainable approach
     * to UI customization. The configurator provides a fluent API for configuring various aspects of the textField, including its appearance, behavior, and event handling.
     * </p>
     */
    private TextField textField;

    /**
     * Constructs a {@code TextFieldConfigurator} for the specified {@link TextField}. This constructor initializes the configurator with a target textField. It sets up the environment for further configuration
     * specific to {@link TextField} instances. The {@code TextFieldConfigurator.class} is used as the class reference for error reporting.
     *
     * @param textField
     *         The {@link TextField} to be configured. Must not be {@code null}, and an {@link IllegalArgumentException} will be thrown if a null textField is passed. This ensures that the configurator has a
     *         valid target for configuration.
     */
    protected TextFieldConfigurator(TextField textField) {
        super(ConfiguratorBase.checkNodeNotNullAndInstanceOf(textField, TextField.class, TextFieldConfigurator.class, "Constructor"));
        this.textField = textField;
    }

    //region Overridden Methods
    //*****************************************************************
    // Overridden Methods
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField getNode() {
        return textField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNode(Node value) {
        super.setNode(ConfiguratorBase.checkNodeNotNullAndInstanceOf(value, TextField.class, TextFieldConfigurator.class, "setNode"));
        this.textField = ((TextField) value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return textField.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TextFieldConfigurator textFieldConfigurator) {
            return Objects.equals(textFieldConfigurator.getNode(), this.textField);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("TextFieldConfigurator current text field: %s", textField.toString());
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
    public TextFieldConfigurator addPrefColumnCountChangeListener(ChangeListener<? super Number> changeListener) {
        textField.prefColumnCountProperty()
                 .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPrefColumnCountInvalidationListener(InvalidationListener invalidationListener) {
        textField.prefColumnCountProperty()
                 .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        textField.onActionProperty()
                 .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnActionInvalidationListener(InvalidationListener invalidationListener) {
        textField.onActionProperty()
                 .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        textField.alignmentProperty()
                 .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        textField.alignmentProperty()
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
    public TextFieldConfigurator removePrefColumnCountChangeListener(ChangeListener<? super Number> changeListener) {
        textField.prefColumnCountProperty()
                 .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePrefColumnCountInvalidationListener(InvalidationListener invalidationListener) {
        textField.prefColumnCountProperty()
                 .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        textField.onActionProperty()
                 .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnActionInvalidationListener(InvalidationListener invalidationListener) {
        textField.onActionProperty()
                 .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        textField.alignmentProperty()
                 .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        textField.alignmentProperty()
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
    public TextFieldConfigurator bindPrefColumnCountProperty(ObservableValue<? extends Number> observableValue) {
        textField.prefColumnCountProperty()
                 .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindPrefColumnCountProperty() {
        textField.prefColumnCountProperty()
                 .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        textField.prefColumnCountProperty()
                 .bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        textField.prefColumnCountProperty()
                 .unbindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue) {
        textField.onActionProperty()
                 .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnActionProperty() {
        textField.onActionProperty()
                 .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        textField.onActionProperty()
                 .bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        textField.onActionProperty()
                 .unbindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindAlignmentProperty(ObservableValue<? extends Pos> observableValue) {
        textField.alignmentProperty()
                 .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindAlignmentProperty() {
        textField.alignmentProperty()
                 .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalAlignmentProperty(Property<Pos> otherProperty) {
        textField.alignmentProperty()
                 .bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalAlignmentProperty(Property<Pos> otherProperty) {
        textField.alignmentProperty()
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
    public TextFieldConfigurator setPrefColumnCount(int value) {
        textField.setPrefColumnCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnAction(EventHandler<ActionEvent> value) {
        textField.setOnAction(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setAlignment(Pos value) {
        textField.setAlignment(value);
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
    public TextFieldConfigurator addAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.addAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.addBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.addBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.addCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.addCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.addClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addClipInvalidationListener(InvalidationListener invalidationListener) {
        super.addClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.addCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.addCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.addDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.addDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.addDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.addEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.addEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.addEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.addEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.addFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.addHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addIdChangeListener(ChangeListener<? super String> changeListener) {
        super.addIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addIdInvalidationListener(InvalidationListener invalidationListener) {
        super.addIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.addInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.addInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.addLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.addLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.addLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.addLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.addManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.addMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.addNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.addNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.addOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.addOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.addOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.addOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.addOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.addOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.addOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.addOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.addOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.addOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.addOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.addOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.addOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.addParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addParentInvalidationListener(InvalidationListener invalidationListener) {
        super.addParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.addPickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.addPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.addSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.addSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.addRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.addRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.addRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.addStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.addStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.addTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.addTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.addTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.addTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.addViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.addViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addVisibleInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator removeAccessibleHelpChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleHelpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAccessibleHelpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleHelpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAccessibleRoleDescriptionChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleRoleDescriptionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAccessibleRoleDescriptionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleDescriptionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAccessibleRoleChangeListener(ChangeListener<? super AccessibleRole> changeListener) {
        super.removeAccessibleRoleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAccessibleRoleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleRoleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAccessibleTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeAccessibleTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAccessibleTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAccessibleTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBlendModeChangeListener(ChangeListener<? super BlendMode> changeListener) {
        super.removeBlendModeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBlendModeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBlendModeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBoundsInLocalChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInLocalChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBoundsInLocalInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInLocalInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBoundsInParentChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeBoundsInParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBoundsInParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBoundsInParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCacheHintChangeListener(ChangeListener<? super CacheHint> changeListener) {
        super.removeCacheHintChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCacheHintInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheHintInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCacheChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCacheInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCacheInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeClipChangeListener(ChangeListener<? super Node> changeListener) {
        super.removeClipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeClipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeClipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCursorChangeListener(ChangeListener<? super Cursor> changeListener) {
        super.removeCursorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCursorInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCursorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeDepthTestChangeListener(ChangeListener<? super DepthTest> changeListener) {
        super.removeDepthTestChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeDepthTestInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDepthTestInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeDisabledChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisabledChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeDisabledInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisabledInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeDisableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeDisableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeDisableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeDisableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeEffectiveNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeEffectiveNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeEffectiveNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectiveNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeEffectChangeListener(ChangeListener<? super Effect> changeListener) {
        super.removeEffectChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeEffectInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEffectInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeEventDispatcherChangeListener(ChangeListener<? super EventDispatcher> changeListener) {
        super.removeEventDispatcherChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeEventDispatcherInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEventDispatcherInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFocusedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFocusedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFocusTraversableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusTraversableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFocusTraversableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusTraversableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFocusVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFocusVisibleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusVisibleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFocusWithinChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeFocusWithinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFocusWithinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFocusWithinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeHoverChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeHoverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeHoverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHoverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeIdChangeListener(ChangeListener<? super String> changeListener) {
        super.removeIdChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeIdInvalidationListener(InvalidationListener invalidationListener) {
        super.removeIdInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeInputMethodRequestsChangeListener(ChangeListener<? super InputMethodRequests> changeListener) {
        super.removeInputMethodRequestsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeInputMethodRequestsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInputMethodRequestsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLayoutBoundsChangeListener(ChangeListener<? super Bounds> changeListener) {
        super.removeLayoutBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLayoutBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLayoutXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLayoutXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLayoutYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLayoutYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLayoutYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLayoutYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLocalToParentTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToParentTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLocalToParentTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToParentTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLocalToSceneTransformChangeListener(ChangeListener<? super Transform> changeListener) {
        super.removeLocalToSceneTransformChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLocalToSceneTransformInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLocalToSceneTransformInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeManagedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeManagedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeManagedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeManagedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMouseTransparentChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeMouseTransparentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMouseTransparentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMouseTransparentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeNodeOrientationChangeListener(ChangeListener<? super NodeOrientation> changeListener) {
        super.removeNodeOrientationChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeNodeOrientationInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNodeOrientationInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnContextMenuRequestedChangeListener(ChangeListener<? super EventHandler<? super ContextMenuEvent>> changeListener) {
        super.removeOnContextMenuRequestedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnContextMenuRequestedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnContextMenuRequestedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragDetectedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnDragDetectedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragDetectedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDetectedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragDoneChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDoneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragDoneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDoneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragDroppedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragDroppedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragDroppedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragDroppedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragEnteredChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragExitedChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragOverChangeListener(ChangeListener<? super EventHandler<? super DragEvent>> changeListener) {
        super.removeOnDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnInputMethodTextChangedChangeListener(ChangeListener<? super EventHandler<? super InputMethodEvent>> changeListener) {
        super.removeOnInputMethodTextChangedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnInputMethodTextChangedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnInputMethodTextChangedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnKeyPressedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnKeyPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnKeyReleasedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnKeyReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnKeyTypedChangeListener(ChangeListener<? super EventHandler<? super KeyEvent>> changeListener) {
        super.removeOnKeyTypedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnKeyTypedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnKeyTypedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseClickedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseClickedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseClickedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseClickedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDragEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDragEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDragExitedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDragExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDraggedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseDraggedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDraggedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDraggedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDragOverChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragOverChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDragOverInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragOverInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDragReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseDragEvent>> changeListener) {
        super.removeOnMouseDragReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseDragReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseDragReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseEnteredChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseEnteredChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseEnteredInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseEnteredInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseExitedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseExitedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseExitedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseExitedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseMovedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMousePressedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMousePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMousePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMousePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseReleasedChangeListener(ChangeListener<? super EventHandler<? super MouseEvent>> changeListener) {
        super.removeOnMouseReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnMouseReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnMouseReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnRotateChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnRotationFinishedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnRotationFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnRotationStartedChangeListener(ChangeListener<? super EventHandler<? super RotateEvent>> changeListener) {
        super.removeOnRotationStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnRotationStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnRotationStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnScrollFinishedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnScrollFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnScrollChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnScrollInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnScrollStartedChangeListener(ChangeListener<? super EventHandler<? super ScrollEvent>> changeListener) {
        super.removeOnScrollStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnScrollStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnScrollStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnSwipeDownChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeDownChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnSwipeDownInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeDownInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnSwipeLeftChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeLeftChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnSwipeLeftInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeLeftInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnSwipeRightChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeRightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnSwipeRightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeRightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnSwipeUpChangeListener(ChangeListener<? super EventHandler<? super SwipeEvent>> changeListener) {
        super.removeOnSwipeUpChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnSwipeUpInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnSwipeUpInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnTouchMovedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchMovedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnTouchMovedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchMovedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnTouchPressedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchPressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnTouchPressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchPressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnTouchReleasedChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchReleasedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnTouchReleasedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchReleasedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnTouchStationaryChangeListener(ChangeListener<? super EventHandler<? super TouchEvent>> changeListener) {
        super.removeOnTouchStationaryChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnTouchStationaryInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnTouchStationaryInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnZoomFinishedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomFinishedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnZoomFinishedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomFinishedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnZoomChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnZoomInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnZoomStartedChangeListener(ChangeListener<? super EventHandler<? super ZoomEvent>> changeListener) {
        super.removeOnZoomStartedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOnZoomStartedInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOnZoomStartedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOpacityChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeOpacityChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOpacityInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpacityInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeParentChangeListener(ChangeListener<? super Parent> changeListener) {
        super.removeParentChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeParentInvalidationListener(InvalidationListener invalidationListener) {
        super.removeParentInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePickOnBoundsChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePickOnBoundsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePickOnBoundsInvalidationListener(InvalidationListener invalidationListener) {
        super.removePickOnBoundsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePressedChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removePressedChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePressedInvalidationListener(InvalidationListener invalidationListener) {
        super.removePressedInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeSceneChangeListener(ChangeListener<? super Scene> changeListener) {
        super.removeSceneChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeSceneInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSceneInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeRotateChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeRotateChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeRotateInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotateInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeRotationAxisChangeListener(ChangeListener<? super Point3D> changeListener) {
        super.removeRotationAxisChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeRotationAxisInvalidationListener(InvalidationListener invalidationListener) {
        super.removeRotationAxisInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeScaleXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeScaleXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeScaleYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeScaleYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeScaleZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeScaleZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeScaleZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStyleChangeListener(ChangeListener<? super String> changeListener) {
        super.removeStyleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStyleInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStyleListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStyleListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStyleListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeStyleListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTransformListChangeListener(ListChangeListener<? super Transform> listChangeListener) {
        super.removeTransformListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTransformListInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTransformListInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTranslateXChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateXChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTranslateXInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateXInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTranslateYChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateYChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTranslateYInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateYInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTranslateZChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeTranslateZChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTranslateZInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTranslateZInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeViewOrderChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeViewOrderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeViewOrderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeViewOrderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeVisibleChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeVisibleChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeVisibleInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator bindAccessibleHelpProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleHelpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindAccessibleHelpProperty() {
        super.unbindAccessibleHelpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.bindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalAccessibleHelpProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleHelpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindAccessibleRoleDescriptionProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleRoleDescriptionProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindAccessibleRoleDescriptionProperty() {
        super.unbindAccessibleRoleDescriptionProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.bindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalAccessibleRoleDescriptionProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleRoleDescriptionProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindAccessibleRoleProperty(ObservableValue<? extends AccessibleRole> observableValue) {
        super.bindAccessibleRoleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindAccessibleRoleProperty() {
        super.unbindAccessibleRoleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.bindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalAccessibleRoleProperty(Property<AccessibleRole> property) {
        super.unbindBidirectionalAccessibleRoleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindAccessibleTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindAccessibleTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindAccessibleTextProperty() {
        super.unbindAccessibleTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.bindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalAccessibleTextProperty(Property<String> property) {
        super.unbindBidirectionalAccessibleTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBlendModeProperty(ObservableValue<? extends BlendMode> observableValue) {
        super.bindBlendModeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBlendModeProperty() {
        super.unbindBlendModeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.bindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalBlendModeProperty(Property<BlendMode> property) {
        super.unbindBidirectionalBlendModeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindCacheHintProperty(ObservableValue<? extends CacheHint> observableValue) {
        super.bindCacheHintProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindCacheHintProperty() {
        super.unbindCacheHintProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.bindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalCacheHintProperty(Property<CacheHint> property) {
        super.unbindBidirectionalCacheHintProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindCacheProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindCacheProperty() {
        super.unbindCacheProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalCacheProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalCacheProperty(Property<Boolean> property) {
        super.unbindBidirectionalCacheProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindClipProperty(ObservableValue<? extends Node> observableValue) {
        super.bindClipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindClipProperty() {
        super.unbindClipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalClipProperty(Property<Node> property) {
        super.bindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalClipProperty(Property<Node> property) {
        super.unbindBidirectionalClipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindCursorProperty(ObservableValue<? extends Cursor> observableValue) {
        super.bindCursorProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindCursorProperty() {
        super.unbindCursorProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalCursorProperty(Property<Cursor> property) {
        super.bindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalCursorProperty(Property<Cursor> property) {
        super.unbindBidirectionalCursorProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindDepthTestProperty(ObservableValue<? extends DepthTest> observableValue) {
        super.bindDepthTestProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindDepthTestProperty() {
        super.unbindDepthTestProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.bindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalDepthTestProperty(Property<DepthTest> property) {
        super.unbindBidirectionalDepthTestProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindDisableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindDisableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindDisableProperty() {
        super.unbindDisableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalDisableProperty(Property<Boolean> property) {
        super.bindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalDisableProperty(Property<Boolean> property) {
        super.unbindBidirectionalDisableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindEffectProperty(ObservableValue<? extends Effect> observableValue) {
        super.bindEffectProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindEffectProperty() {
        super.unbindEffectProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalEffectProperty(Property<Effect> property) {
        super.bindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalEffectProperty(Property<Effect> property) {
        super.unbindBidirectionalEffectProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindEventDispatcherProperty(ObservableValue<? extends EventDispatcher> observableValue) {
        super.bindEventDispatcherProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindEventDispatcherProperty() {
        super.unbindEventDispatcherProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.bindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalEventDispatcherProperty(Property<EventDispatcher> property) {
        super.unbindBidirectionalEventDispatcherProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindFocusTraversableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindFocusTraversableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindFocusTraversableProperty() {
        super.unbindFocusTraversableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.bindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalFocusTraversableProperty(Property<Boolean> property) {
        super.unbindBidirectionalFocusTraversableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindIdProperty(ObservableValue<? extends String> observableValue) {
        super.bindIdProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindIdProperty() {
        super.unbindIdProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalIdProperty(Property<String> property) {
        super.bindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalIdProperty(Property<String> property) {
        super.unbindBidirectionalIdProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindInputMethodRequestsProperty(ObservableValue<? extends InputMethodRequests> observableValue) {
        super.bindInputMethodRequestsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindInputMethodRequestsProperty() {
        super.unbindInputMethodRequestsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.bindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalInputMethodRequestsProperty(Property<InputMethodRequests> property) {
        super.unbindBidirectionalInputMethodRequestsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindLayoutXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindLayoutXProperty() {
        super.unbindLayoutXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalLayoutXProperty(Property<Number> property) {
        super.bindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalLayoutXProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindLayoutYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindLayoutYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindLayoutYProperty() {
        super.unbindLayoutYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalLayoutYProperty(Property<Number> property) {
        super.bindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalLayoutYProperty(Property<Number> property) {
        super.unbindBidirectionalLayoutYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindManagedProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindManagedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindManagedProperty() {
        super.unbindManagedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalManagedProperty(Property<Boolean> property) {
        super.bindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalManagedProperty(Property<Boolean> property) {
        super.unbindBidirectionalManagedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindMouseTransparentProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindMouseTransparentProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindMouseTransparentProperty() {
        super.unbindMouseTransparentProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.bindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalMouseTransparentProperty(Property<Boolean> property) {
        super.unbindBidirectionalMouseTransparentProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindNodeOrientationProperty(ObservableValue<? extends NodeOrientation> observableValue) {
        super.bindNodeOrientationProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindNodeOrientationProperty() {
        super.unbindNodeOrientationProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.bindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalNodeOrientationProperty(Property<NodeOrientation> property) {
        super.unbindBidirectionalNodeOrientationProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnContextMenuRequestedProperty(ObservableValue<? extends EventHandler<? super ContextMenuEvent>> observableValue) {
        super.bindOnContextMenuRequestedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnContextMenuRequestedProperty() {
        super.unbindOnContextMenuRequestedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property) {
        super.bindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnContextMenuRequestedProperty(Property<EventHandler<? super ContextMenuEvent>> property) {
        super.unbindBidirectionalOnContextMenuRequestedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnDragDetectedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnDragDetectedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnDragDetectedProperty() {
        super.unbindOnDragDetectedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnDragDetectedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnDragDetectedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnDragDoneProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDoneProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnDragDoneProperty() {
        super.unbindOnDragDoneProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnDragDoneProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDoneProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnDragDroppedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragDroppedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnDragDroppedProperty() {
        super.unbindOnDragDroppedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnDragDroppedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragDroppedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnDragEnteredProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnDragEnteredProperty() {
        super.unbindOnDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnDragEnteredProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnDragExitedProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnDragExitedProperty() {
        super.unbindOnDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnDragExitedProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnDragOverProperty(ObservableValue<? extends EventHandler<? super DragEvent>> observableValue) {
        super.bindOnDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnDragOverProperty() {
        super.unbindOnDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.bindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnDragOverProperty(Property<EventHandler<? super DragEvent>> property) {
        super.unbindBidirectionalOnDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnInputMethodTextChangedProperty(ObservableValue<? extends EventHandler<? super InputMethodEvent>> observableValue) {
        super.bindOnInputMethodTextChangedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnInputMethodTextChangedProperty() {
        super.unbindOnInputMethodTextChangedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property) {
        super.bindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnInputMethodTextChangedProperty(Property<EventHandler<? super InputMethodEvent>> property) {
        super.unbindBidirectionalOnInputMethodTextChangedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnKeyPressedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnKeyPressedProperty() {
        super.unbindOnKeyPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnKeyPressedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnKeyReleasedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnKeyReleasedProperty() {
        super.unbindOnKeyReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnKeyReleasedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnKeyTypedProperty(ObservableValue<? extends EventHandler<? super KeyEvent>> observableValue) {
        super.bindOnKeyTypedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnKeyTypedProperty() {
        super.unbindOnKeyTypedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.bindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnKeyTypedProperty(Property<EventHandler<? super KeyEvent>> property) {
        super.unbindBidirectionalOnKeyTypedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseClickedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseClickedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseClickedProperty() {
        super.unbindOnMouseClickedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseClickedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseClickedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseDragEnteredProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseDragEnteredProperty() {
        super.unbindOnMouseDragEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseDragEnteredProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseDragExitedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseDragExitedProperty() {
        super.unbindOnMouseDragExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseDragExitedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseDraggedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseDraggedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseDraggedProperty() {
        super.unbindOnMouseDraggedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseDraggedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseDraggedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseDragOverProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragOverProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseDragOverProperty() {
        super.unbindOnMouseDragOverProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseDragOverProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragOverProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseDragReleasedProperty(ObservableValue<? extends EventHandler<? super MouseDragEvent>> observableValue) {
        super.bindOnMouseDragReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseDragReleasedProperty() {
        super.unbindOnMouseDragReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.bindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseDragReleasedProperty(Property<EventHandler<? super MouseDragEvent>> property) {
        super.unbindBidirectionalOnMouseDragReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseEnteredProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseEnteredProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseEnteredProperty() {
        super.unbindOnMouseEnteredProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseEnteredProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseEnteredProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseExitedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseExitedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseExitedProperty() {
        super.unbindOnMouseExitedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseExitedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseExitedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseMovedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseMovedProperty() {
        super.unbindOnMouseMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseMovedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMousePressedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMousePressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMousePressedProperty() {
        super.unbindOnMousePressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMousePressedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMousePressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnMouseReleasedProperty(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue) {
        super.bindOnMouseReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnMouseReleasedProperty() {
        super.unbindOnMouseReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.bindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnMouseReleasedProperty(Property<EventHandler<? super MouseEvent>> property) {
        super.unbindBidirectionalOnMouseReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnRotateProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnRotateProperty() {
        super.unbindOnRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnRotateProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnRotationFinishedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnRotationFinishedProperty() {
        super.unbindOnRotationFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnRotationFinishedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnRotationStartedProperty(ObservableValue<? extends EventHandler<? super RotateEvent>> observableValue) {
        super.bindOnRotationStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnRotationStartedProperty() {
        super.unbindOnRotationStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.bindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnRotationStartedProperty(Property<EventHandler<? super RotateEvent>> property) {
        super.unbindBidirectionalOnRotationStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnScrollProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnScrollProperty() {
        super.unbindOnScrollProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnScrollProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnScrollFinishedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnScrollFinishedProperty() {
        super.unbindOnScrollFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnScrollFinishedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnScrollStartedProperty(ObservableValue<? extends EventHandler<? super ScrollEvent>> observableValue) {
        super.bindOnScrollStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnScrollStartedProperty() {
        super.unbindOnScrollStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.bindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnScrollStartedProperty(Property<EventHandler<? super ScrollEvent>> property) {
        super.unbindBidirectionalOnScrollStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnSwipeDownProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeDownProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnSwipeDownProperty() {
        super.unbindOnSwipeDownProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnSwipeDownProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeDownProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnSwipeLeftProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeLeftProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnSwipeLeftProperty() {
        super.unbindOnSwipeLeftProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnSwipeLeftProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeLeftProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnSwipeRightProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeRightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnSwipeRightProperty() {
        super.unbindOnSwipeRightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnSwipeRightProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeRightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnSwipeUpProperty(ObservableValue<? extends EventHandler<? super SwipeEvent>> observableValue) {
        super.bindOnSwipeUpProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnSwipeUpProperty() {
        super.unbindOnSwipeUpProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.bindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnSwipeUpProperty(Property<EventHandler<? super SwipeEvent>> property) {
        super.unbindBidirectionalOnSwipeUpProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnTouchMovedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchMovedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnTouchMovedProperty() {
        super.unbindOnTouchMovedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnTouchMovedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchMovedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnTouchPressedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchPressedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnTouchPressedProperty() {
        super.unbindOnTouchPressedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnTouchPressedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchPressedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnTouchReleasedProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchReleasedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnTouchReleasedProperty() {
        super.unbindOnTouchReleasedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnTouchReleasedProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchReleasedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnTouchStationaryProperty(ObservableValue<? extends EventHandler<? super TouchEvent>> observableValue) {
        super.bindOnTouchStationaryProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnTouchStationaryProperty() {
        super.unbindOnTouchStationaryProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.bindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnTouchStationaryProperty(Property<EventHandler<? super TouchEvent>> property) {
        super.unbindBidirectionalOnTouchStationaryProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnZoomProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnZoomProperty() {
        super.unbindOnZoomProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnZoomProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnZoomFinishedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomFinishedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnZoomFinishedProperty() {
        super.unbindOnZoomFinishedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnZoomFinishedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomFinishedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOnZoomStartedProperty(ObservableValue<? extends EventHandler<? super ZoomEvent>> observableValue) {
        super.bindOnZoomStartedProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOnZoomStartedProperty() {
        super.unbindOnZoomStartedProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.bindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOnZoomStartedProperty(Property<EventHandler<? super ZoomEvent>> property) {
        super.unbindBidirectionalOnZoomStartedProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOpacityProperty(ObservableValue<? extends Number> observableValue) {
        super.bindOpacityProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOpacityProperty() {
        super.unbindOpacityProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOpacityProperty(Property<Number> property) {
        super.bindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOpacityProperty(Property<Number> property) {
        super.unbindBidirectionalOpacityProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindPickOnBoundsProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindPickOnBoundsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindPickOnBoundsProperty() {
        super.unbindPickOnBoundsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.bindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalPickOnBoundsProperty(Property<Boolean> property) {
        super.unbindBidirectionalPickOnBoundsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindRotateProperty(ObservableValue<? extends Number> observableValue) {
        super.bindRotateProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindRotateProperty() {
        super.unbindRotateProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalRotateProperty(Property<Number> property) {
        super.bindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalRotateProperty(Property<Number> property) {
        super.unbindBidirectionalRotateProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindRotationAxisProperty(ObservableValue<? extends Point3D> observableValue) {
        super.bindRotationAxisProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindRotationAxisProperty() {
        super.unbindRotationAxisProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.bindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalRotationAxisProperty(Property<Point3D> property) {
        super.unbindBidirectionalRotationAxisProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindScaleXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindScaleXProperty() {
        super.unbindScaleXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalScaleXProperty(Property<Number> property) {
        super.bindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalScaleXProperty(Property<Number> property) {
        super.unbindBidirectionalScaleXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindScaleYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindScaleYProperty() {
        super.unbindScaleYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalScaleYProperty(Property<Number> property) {
        super.bindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalScaleYProperty(Property<Number> property) {
        super.unbindBidirectionalScaleYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindScaleZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindScaleZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindScaleZProperty() {
        super.unbindScaleZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalScaleZProperty(Property<Number> property) {
        super.bindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalScaleZProperty(Property<Number> property) {
        super.unbindBidirectionalScaleZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindStyleProperty(ObservableValue<? extends String> observableValue) {
        super.bindStyleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindStyleProperty() {
        super.unbindStyleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalStyleProperty(Property<String> property) {
        super.bindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalStyleProperty(Property<String> property) {
        super.unbindBidirectionalStyleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindTranslateXProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateXProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindTranslateXProperty() {
        super.unbindTranslateXProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalTranslateXProperty(Property<Number> property) {
        super.bindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalTranslateXProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateXProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindTranslateYProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateYProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindTranslateYProperty() {
        super.unbindTranslateYProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalTranslateYProperty(Property<Number> property) {
        super.bindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalTranslateYProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateYProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindTranslateZProperty(ObservableValue<? extends Number> observableValue) {
        super.bindTranslateZProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindTranslateZProperty() {
        super.unbindTranslateZProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalTranslateZProperty(Property<Number> property) {
        super.bindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalTranslateZProperty(Property<Number> property) {
        super.unbindBidirectionalTranslateZProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindViewOrderProperty(ObservableValue<? extends Number> observableValue) {
        super.bindViewOrderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindViewOrderProperty() {
        super.unbindViewOrderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalViewOrderProperty(Property<Number> property) {
        super.bindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalViewOrderProperty(Property<Number> property) {
        super.unbindBidirectionalViewOrderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindVisibleProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindVisibleProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindVisibleProperty() {
        super.unbindVisibleProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalVisibleProperty(Property<Boolean> property) {
        super.bindBidirectionalVisibleProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalVisibleProperty(Property<Boolean> property) {
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
    public TextFieldConfigurator fireEvent(Event event) {
        super.fireEvent(event);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> TextFieldConfigurator addEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.addEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> TextFieldConfigurator addEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
        super.addEventHandler(eventType, eventHandler);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> TextFieldConfigurator removeEventFilter(EventType<S> eventType, EventHandler<? super S> eventFilter) {
        super.removeEventFilter(eventType, eventFilter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Event> TextFieldConfigurator removeEventHandler(EventType<S> eventType, EventHandler<? super S> eventHandler) {
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
    public TextFieldConfigurator setAccessibleHelp(String value) {
        super.setAccessibleHelp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setAccessibleRole(AccessibleRole value) {
        super.setAccessibleRole(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setAccessibleRoleDescription(String value) {
        super.setAccessibleRoleDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setAccessibleText(String value) {
        super.setAccessibleText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setBlendMode(BlendMode value) {
        super.setBlendMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setCache(boolean value) {
        super.setCache(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setCacheHint(CacheHint value) {
        super.setCacheHint(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setClip(Node value) {
        super.setClip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setCursor(Cursor value) {
        super.setCursor(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setDepthTest(DepthTest value) {
        super.setDepthTest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setDisable(boolean value) {
        super.setDisable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setEffect(Effect value) {
        super.setEffect(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setEventDispatcher(EventDispatcher value) {
        super.setEventDispatcher(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setFocusTraversable(boolean value) {
        super.setFocusTraversable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setId(String value) {
        super.setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setInputMethodRequests(InputMethodRequests value) {
        super.setInputMethodRequests(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setLayoutX(double value) {
        super.setLayoutX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setLayoutY(double value) {
        super.setLayoutY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setManaged(boolean value) {
        super.setManaged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setMouseTransparent(boolean value) {
        super.setMouseTransparent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setNodeOrientation(NodeOrientation value) {
        super.setNodeOrientation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
        super.setOnContextMenuRequested(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnDragDetected(EventHandler<? super MouseEvent> value) {
        super.setOnDragDetected(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnDragDone(EventHandler<? super DragEvent> value) {
        super.setOnDragDone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnDragDropped(EventHandler<? super DragEvent> value) {
        super.setOnDragDropped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnDragEntered(EventHandler<? super DragEvent> value) {
        super.setOnDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnDragExited(EventHandler<? super DragEvent> value) {
        super.setOnDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnDragOver(EventHandler<? super DragEvent> value) {
        super.setOnDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
        super.setOnInputMethodTextChanged(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnKeyPressed(EventHandler<? super KeyEvent> value) {
        super.setOnKeyPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnKeyTyped(EventHandler<? super KeyEvent> value) {
        super.setOnKeyTyped(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseClicked(EventHandler<? super MouseEvent> value) {
        super.setOnMouseClicked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseDragEntered(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseDragExited(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseDragOver(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragOver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseDragReleased(EventHandler<? super MouseDragEvent> value) {
        super.setOnMouseDragReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseEntered(EventHandler<? super MouseEvent> value) {
        super.setOnMouseEntered(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseExited(EventHandler<? super MouseEvent> value) {
        super.setOnMouseExited(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseMoved(EventHandler<? super MouseEvent> value) {
        super.setOnMouseMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMousePressed(EventHandler<? super MouseEvent> value) {
        super.setOnMousePressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnMouseReleased(EventHandler<? super MouseEvent> value) {
        super.setOnMouseReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnRotate(EventHandler<? super RotateEvent> value) {
        super.setOnRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnRotationFinished(EventHandler<? super RotateEvent> value) {
        super.setOnRotationFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnRotationStarted(EventHandler<? super RotateEvent> value) {
        super.setOnRotationStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnScroll(EventHandler<? super ScrollEvent> value) {
        super.setOnScroll(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnScrollFinished(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnScrollStarted(EventHandler<? super ScrollEvent> value) {
        super.setOnScrollStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOpacity(double value) {
        super.setOpacity(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setStyle(String style) {
        super.setStyle(style);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnSwipeDown(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeDown(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeLeft(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnSwipeRight(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeRight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnSwipeUp(EventHandler<? super SwipeEvent> value) {
        super.setOnSwipeUp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnTouchMoved(EventHandler<? super TouchEvent> value) {
        super.setOnTouchMoved(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnTouchPressed(EventHandler<? super TouchEvent> value) {
        super.setOnTouchPressed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnTouchReleased(EventHandler<? super TouchEvent> value) {
        super.setOnTouchReleased(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnTouchStationary(EventHandler<? super TouchEvent> value) {
        super.setOnTouchStationary(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnZoom(EventHandler<? super ZoomEvent> value) {
        super.setOnZoom(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnZoomFinished(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomFinished(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOnZoomStarted(EventHandler<? super ZoomEvent> value) {
        super.setOnZoomStarted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setPickOnBounds(boolean value) {
        super.setPickOnBounds(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setRotate(double value) {
        super.setRotate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setRotationAxis(Point3D value) {
        super.setRotationAxis(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setScaleX(double value) {
        super.setScaleX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setScaleY(double value) {
        super.setScaleY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setScaleZ(double value) {
        super.setScaleZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setTranslateX(double value) {
        super.setTranslateX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setTranslateY(double value) {
        super.setTranslateY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setTranslateZ(double value) {
        super.setTranslateZ(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setUserData(Object value) {
        super.setUserData(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setViewOrder(double value) {
        super.setViewOrder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setVisible(boolean value) {
        super.setVisible(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setStyleClass(int index, String element) {
        super.setStyleClass(index, element);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setAllStyleClasses(String... elements) {
        super.setAllStyleClasses(elements);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setAllStyleClasses(Collection<? extends String> col) {
        super.setAllStyleClasses(col);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setTransformClass(int index, Transform element) {
        super.setTransformClass(index, element);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setAllTransformClasses(Transform... elements) {
        super.setAllTransformClasses(elements);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setAllTransformClasses(Collection<? extends Transform> col) {
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
    public TextFieldConfigurator addFirstStyleClass(String styleClass) {
        super.addFirstStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLastStyleClass(String styleClass) {
        super.addLastStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStyleClass(String styleClass) {
        super.addStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStyleClass(int index, String styleClass) {
        super.addStyleClass(index, styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllStyleClasses(String... styleClasses) {
        super.addAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllStyleClasses(Collection<? extends String> c) {
        super.addAllStyleClasses(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllStyleClasses(int index, Collection<? extends String> c) {
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
    public TextFieldConfigurator removeFirstStyleClass() {
        super.removeFirstStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLastStyleClass() {
        super.removeLastStyleClass();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStyleClass(String styleClass) {
        super.removeStyleClass(styleClass);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStyleClasses(int from, int to) {
        super.removeStyleClasses(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStyleClassesIf(Predicate<? super String> filter) {
        super.removeStyleClassesIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAllStyleClasses(String... styleClasses) {
        super.removeAllStyleClasses(styleClasses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAllStyleClasses(Collection<? extends String> c) {
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
    public TextFieldConfigurator pseudoClassStateChange(PseudoClass pseudoClass, boolean active) {
        super.pseudoClassStateChange(pseudoClass, active);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator applyCss() {
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
    public TextFieldConfigurator addFirstTransform(Transform transform) {
        super.addFirstTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLastTransform(Transform transform) {
        super.addLastTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTransform(Transform transform) {
        super.addTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTransform(int index, Transform transform) {
        super.addTransform(index, transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllTransforms(Transform... transforms) {
        super.addAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllTransforms(Collection<? extends Transform> c) {
        super.addAllTransforms(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllTransforms(int index, Collection<? extends Transform> c) {
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
    public TextFieldConfigurator removeFirstTransform() {
        super.removeFirstTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLastTransform() {
        super.removeLastTransform();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTransform(Transform transform) {
        super.removeTransform(transform);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTransforms(int from, int to) {
        super.removeTransforms(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTransformsIf(Predicate<? super Transform> filter) {
        super.removeTransformsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAllTransforms(Transform... transforms) {
        super.removeAllTransforms(transforms);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAllTransforms(Collection<? extends Transform> c) {
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
    public TextFieldConfigurator toBack() {
        super.toBack();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator toFront() {
        super.toFront();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator resize(double width, double height) {
        super.resize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator autosize() {
        super.autosize();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator resizeRelocate(double x, double y, double width, double height) {
        super.resizeRelocate(x, y, width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator requestFocus() {
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
    public TextFieldConfigurator addNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.addNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.addGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.addGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.addStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator removeNeedsLayoutChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeNeedsLayoutChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeNeedsLayoutInvalidationListener(InvalidationListener invalidationListener) {
        super.removeNeedsLayoutInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeGetChildrenUnmodifiableChangeListener(ListChangeListener<? super Node> listChangeListener) {
        super.removeGetChildrenUnmodifiableChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeGetChildrenUnmodifiableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeGetChildrenUnmodifiableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStylesheetsListChangeListener(ListChangeListener<? super String> listChangeListener) {
        super.removeStylesheetsListChangeListener(listChangeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStylesheetsListInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator requestLayout() {
        super.requestLayout();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator Layout() {
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
    public TextFieldConfigurator addFirstStylesheet(String stylesheet) {
        super.addFirstStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLastStylesheet(String stylesheet) {
        super.addLastStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStylesheet(String stylesheet) {
        super.addStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addStylesheet(int index, String stylesheet) {
        super.addStylesheet(index, stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllStylesheets(String... stylesheets) {
        super.addAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllStylesheets(Collection<? extends String> c) {
        super.addAllStylesheets(c);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAllStylesheets(int index, Collection<? extends String> c) {
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
    public TextFieldConfigurator removeFirstStylesheet() {
        super.removeFirstStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLastStylesheet() {
        super.removeLastStylesheet();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStylesheet(String stylesheet) {
        super.removeStylesheet(stylesheet);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStylesheets(int from, int to) {
        super.removeStylesheets(from, to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeStylesheetsIf(Predicate<? super String> filter) {
        super.removeStylesheetsIf(filter);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAllStylesheets(String... stylesheets) {
        super.removeAllStylesheets(stylesheets);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAllStylesheets(Collection<? extends String> c) {
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
    public TextFieldConfigurator addSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addSnapToPixelChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        super.addSnapToPixelInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addPaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.addPaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        super.addBackgroundChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        super.addBackgroundInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBorderChangeListener(ChangeListener<? super Border> changeListener) {
        super.addBorderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addBorderInvalidationListener(InvalidationListener invalidationListener) {
        super.addBorderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addOpaqueInsetsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        super.addOpaqueInsetsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        super.addInsetChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addInsetInvalidationListener(InvalidationListener invalidationListener) {
        super.addInsetInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMinWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addMinWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addPrefWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addPrefWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMaxWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.addMaxWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMinHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addMinHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addPrefHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addPrefHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.addMaxHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.addMaxHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        super.addShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addScaleShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addScaleShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCenterShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.addCenterShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addCacheShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator removeSnapToPixelChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeSnapToPixelChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeSnapToPixelInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSnapToPixelInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removePaddingChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePaddingInvalidationListener(InvalidationListener invalidationListener) {
        super.removePaddingInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBackgroundChangeListener(ChangeListener<? super Background> changeListener) {
        super.removeBackgroundChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBackgroundInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBackgroundInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBorderChangeListener(ChangeListener<? super Border> changeListener) {
        super.removeBorderChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeBorderInvalidationListener(InvalidationListener invalidationListener) {
        super.removeBorderInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOpaqueInsetsChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeOpaqueInsetsChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeOpaqueInsetsInvalidationListener(InvalidationListener invalidationListener) {
        super.removeOpaqueInsetsInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeInsetChangeListener(ChangeListener<? super Insets> changeListener) {
        super.removeInsetChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeInsetInvalidationListener(InvalidationListener invalidationListener) {
        super.removeInsetInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMinWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMinWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMinWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMinWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePrefWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removePrefWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePrefWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removePrefWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMaxWidthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMaxWidthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMaxWidthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMaxWidthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMinHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMinHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMinHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMinHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePrefHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removePrefHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePrefHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removePrefHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMaxHeightChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeMaxHeightChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeMaxHeightInvalidationListener(InvalidationListener invalidationListener) {
        super.removeMaxHeightInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeShapeChangeListener(ChangeListener<? super Shape> changeListener) {
        super.removeShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeScaleShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeScaleShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeScaleShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeScaleShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCenterShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCenterShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCenterShapeInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCenterShapeInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCacheShapeChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeCacheShapeChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCacheShapeInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator bindSnapToPixelProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindSnapToPixelProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindSnapToPixelProperty() {
        super.unbindSnapToPixelProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        super.bindBidirectionalSnapToPixelProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalSnapToPixelProperty(Property<Boolean> property) {
        super.unbindBidirectionalSnapToPixelProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindPaddingProperty(ObservableValue<? extends Insets> observableValue) {
        super.bindPaddingProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindPaddingProperty() {
        super.unbindPaddingProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalPaddingProperty(Property<Insets> property) {
        super.bindBidirectionalPaddingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalPaddingProperty(Property<Insets> property) {
        super.unbindBidirectionalPaddingProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBackgroundProperty(ObservableValue<? extends Background> observableValue) {
        super.bindBackgroundProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBackgroundProperty() {
        super.unbindBackgroundProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalBackgroundProperty(Property<Background> property) {
        super.bindBidirectionalBackgroundProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalBackgroundProperty(Property<Background> property) {
        super.unbindBidirectionalBackgroundProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBorderProperty(ObservableValue<? extends Border> observableValue) {
        super.bindBorderProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBorderProperty() {
        super.unbindBorderProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalBorderProperty(Property<Border> property) {
        super.bindBidirectionalBorderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalBorderProperty(Property<Border> property) {
        super.unbindBidirectionalBorderProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindOpaqueInsetsProperty(ObservableValue<? extends Insets> observableValue) {
        super.bindOpaqueInsetsProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindOpaqueInsetsProperty() {
        super.unbindOpaqueInsetsProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        super.bindBidirectionalOpaqueInsetsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalOpaqueInsetsProperty(Property<Insets> property) {
        super.unbindBidirectionalOpaqueInsetsProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindMinWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMinWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindMinWidthProperty() {
        super.unbindMinWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalMinWidthProperty(Property<Number> property) {
        super.bindBidirectionalMinWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalMinWidthProperty(Property<Number> property) {
        super.unbindBidirectionalMinWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindPrefWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindPrefWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindPrefWidthProperty() {
        super.unbindPrefWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalPrefWidthProperty(Property<Number> property) {
        super.bindBidirectionalPrefWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalPrefWidthProperty(Property<Number> property) {
        super.unbindBidirectionalPrefWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindMaxWidthProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMaxWidthProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindMaxWidthProperty() {
        super.unbindMaxWidthProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalMaxWidthProperty(Property<Number> property) {
        super.bindBidirectionalMaxWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalMaxWidthProperty(Property<Number> property) {
        super.unbindBidirectionalMaxWidthProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindMinHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMinHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindMinHeightProperty() {
        super.unbindMinHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalMinHeightProperty(Property<Number> property) {
        super.bindBidirectionalMinHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalMinHeightProperty(Property<Number> property) {
        super.unbindBidirectionalMinHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindPrefHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindPrefHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindPrefHeightProperty() {
        super.unbindPrefHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalPrefHeightProperty(Property<Number> property) {
        super.bindBidirectionalPrefHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalPrefHeightProperty(Property<Number> property) {
        super.unbindBidirectionalPrefHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindMaxHeightProperty(ObservableValue<? extends Number> observableValue) {
        super.bindMaxHeightProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindMaxHeightProperty() {
        super.unbindMaxHeightProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalMaxHeightProperty(Property<Number> property) {
        super.bindBidirectionalMaxHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalMaxHeightProperty(Property<Number> property) {
        super.unbindBidirectionalMaxHeightProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindShapeProperty(ObservableValue<? extends Shape> observableValue) {
        super.bindShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindShapeProperty() {
        super.unbindShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalShapeProperty(Property<Shape> property) {
        super.bindBidirectionalShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalShapeProperty(Property<Shape> property) {
        super.unbindBidirectionalShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindScaleShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindScaleShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindScaleShapeProperty() {
        super.unbindScaleShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalScaleShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalScaleShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalScaleShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindCenterShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCenterShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindCenterShapeProperty() {
        super.unbindCenterShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalCenterShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalCenterShapeProperty(Property<Boolean> property) {
        super.unbindBidirectionalCenterShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindCacheShapeProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindCacheShapeProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindCacheShapeProperty() {
        super.unbindCacheShapeProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalCacheShapeProperty(Property<Boolean> property) {
        super.bindBidirectionalCacheShapeProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalCacheShapeProperty(Property<Boolean> property) {
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
    public TextFieldConfigurator setSnapToPixel(boolean value) {
        super.setSnapToPixel(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setPadding(Insets value) {
        super.setPadding(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setBackground(Background value) {
        super.setBackground(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setBorder(Border value) {
        super.setBorder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setOpaqueInsets(Insets value) {
        super.setOpaqueInsets(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setMinWidth(double value) {
        super.setMinWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setPrefWidth(double value) {
        super.setPrefWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setMaxWidth(double value) {
        super.setMaxWidth(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setMinHeight(double value) {
        super.setMinHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setPrefHeight(double value) {
        super.setPrefHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setMaxHeight(double value) {
        super.setMaxHeight(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setMinSize(double width, double height) {
        super.setMinSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setPrefSize(double width, double height) {
        super.setPrefSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setMaxSize(double width, double height) {
        super.setMaxSize(width, height);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setShape(Shape value) {
        super.setShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setScaleShape(boolean value) {
        super.setScaleShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setCenterShape(boolean value) {
        super.setCenterShape(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setCacheShape(boolean value) {
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
    public TextFieldConfigurator addSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        super.addSkinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addSkinInvalidationListener(InvalidationListener invalidationListener) {
        super.addSkinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        super.addTooltipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTooltipInvalidationListener(InvalidationListener invalidationListener) {
        super.addTooltipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        super.addContextMenuChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addContextMenuInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator removeSkinChangeListener(ChangeListener<? super Skin<?>> changeListener) {
        super.removeSkinChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeSkinInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSkinInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTooltipChangeListener(ChangeListener<? super Tooltip> changeListener) {
        super.removeTooltipChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTooltipInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTooltipInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeContextMenuChangeListener(ChangeListener<? super ContextMenu> changeListener) {
        super.removeContextMenuChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeContextMenuInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator bindSkinProperty(ObservableValue<? extends Skin<?>> observableValue) {
        super.bindSkinProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindSkinProperty() {
        super.unbindSkinProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalSkinProperty(Property<Skin<?>> property) {
        super.bindBidirectionalSkinProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalSkinProperty(Property<Skin<?>> property) {
        super.unbindBidirectionalSkinProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindTooltipProperty(ObservableValue<? extends Tooltip> observableValue) {
        super.bindTooltipProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindTooltipProperty() {
        super.unbindTooltipProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalTooltipProperty(Property<Tooltip> property) {
        super.bindBidirectionalTooltipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalTooltipProperty(Property<Tooltip> property) {
        super.unbindBidirectionalTooltipProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindContextMenuProperty(ObservableValue<? extends ContextMenu> observableValue) {
        super.bindContextMenuProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindContextMenuProperty() {
        super.unbindContextMenuProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
        super.bindBidirectionalContextMenuProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalContextMenuProperty(Property<ContextMenu> property) {
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
    public TextFieldConfigurator setSkin(Skin<?> value) {
        super.setSkin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setToolTip(Tooltip value) {
        super.setToolTip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setContextMenu(ContextMenu value) {
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
    public TextFieldConfigurator addFontChangeListener(ChangeListener<? super Font> changeListener) {
        super.addFontChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addFontInvalidationListener(InvalidationListener invalidationListener) {
        super.addFontInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPromptTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addPromptTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addPromptTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addPromptTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        super.addTextFormatterChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextFormatterInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLengthChangeListener(ChangeListener<? super Number> changeListener) {
        super.addLengthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addLengthInvalidationListener(InvalidationListener invalidationListener) {
        super.addLengthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addEditableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addEditableInvalidationListener(InvalidationListener invalidationListener) {
        super.addEditableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        super.addSelectionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addSelectionInvalidationListener(InvalidationListener invalidationListener) {
        super.addSelectionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        super.addSelectedTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        super.addSelectedTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        super.addCaretPositionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        super.addCaretPositionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        super.addAnchorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addAnchorInvalidationListener(InvalidationListener invalidationListener) {
        super.addAnchorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addUndoableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addUndoableInvalidationListener(InvalidationListener invalidationListener) {
        super.addUndoableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.addRedoableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator addRedoableInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        super.removeFontChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeFontInvalidationListener(InvalidationListener invalidationListener) {
        super.removeFontInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePromptTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removePromptTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removePromptTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removePromptTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        super.removeTextFormatterChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextFormatterInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLengthChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeLengthChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeLengthInvalidationListener(InvalidationListener invalidationListener) {
        super.removeLengthInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeEditableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeEditableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeEditableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        super.removeSelectionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeSelectionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSelectionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        super.removeSelectedTextChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        super.removeSelectedTextInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeCaretPositionChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        super.removeCaretPositionInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        super.removeAnchorChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeAnchorInvalidationListener(InvalidationListener invalidationListener) {
        super.removeAnchorInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeUndoableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeUndoableInvalidationListener(InvalidationListener invalidationListener) {
        super.removeUndoableInvalidationListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        super.removeRedoableChangeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator removeRedoableInvalidationListener(InvalidationListener invalidationListener) {
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
    public TextFieldConfigurator bindFontProperty(ObservableValue<? extends Font> observableValue) {
        super.bindFontProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindFontProperty() {
        super.unbindFontProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalFontProperty(Property<Font> otherProperty) {
        super.bindBidirectionalFontProperty(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalFontProperty(Property<Font> otherProperty) {
        super.unbindBidirectionalFontProperty(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindPromptTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindPromptTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindPromptTextProperty() {
        super.unbindPromptTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalPromptTextProperty(Property<String> property) {
        super.bindBidirectionalPromptTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalPromptTextProperty(Property<String> property) {
        super.unbindBidirectionalPromptTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindTextFormatterProperty(ObservableValue<? extends TextFormatter<?>> observableValue) {
        super.bindTextFormatterProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindTextFormatterProperty() {
        super.unbindTextFormatterProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        super.bindBidirectionalTextFormatterProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        super.unbindBidirectionalTextFormatterProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindTextProperty(ObservableValue<? extends String> observableValue) {
        super.bindTextProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindTextProperty() {
        super.unbindTextProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalTextProperty(Property<String> property) {
        super.bindBidirectionalTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalTextProperty(Property<String> property) {
        super.unbindBidirectionalTextProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindEditableProperty(ObservableValue<? extends Boolean> observableValue) {
        super.bindEditableProperty(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindEditableProperty() {
        super.unbindEditableProperty();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator bindBidirectionalEditableProperty(Property<Boolean> property) {
        super.bindBidirectionalEditableProperty(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator unbindBidirectionalEditableProperty(Property<Boolean> property) {
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
    public TextFieldConfigurator setFont(Font value) {
        super.setFont(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setPromptText(String value) {
        super.setPromptText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setTextFormatter(TextFormatter<?> value) {
        super.setTextFormatter(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setText(String value) {
        super.setText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator setEditable(boolean value) {
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
    public TextFieldConfigurator appendText(String text) {
        super.appendText(text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator insertText(int index, String text) {
        super.insertText(index, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator deleteText(IndexRange range) {
        super.deleteText(range);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator deleteText(int start, int end) {
        super.deleteText(start, end);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator replaceText(IndexRange range, String text) {
        super.replaceText(range, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator cut() {
        super.cut();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator copy() {
        super.copy();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator paste() {
        super.paste();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectBackward() {
        super.selectBackward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectForward() {
        super.selectForward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator previousWord() {
        super.previousWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator nextWord() {
        super.nextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator endOfNextWord() {
        super.endOfNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectPreviousWord() {
        super.selectPreviousWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectNextWord() {
        super.selectNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectEndOfNextWord() {
        super.selectEndOfNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectAll() {
        super.selectAll();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator home() {
        super.home();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator end() {
        super.end();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectHome() {
        super.selectHome();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectEnd() {
        super.selectEnd();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator deletePreviousChar() {
        super.deletePreviousChar();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator deleteNextChar() {
        super.deleteNextChar();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator forward() {
        super.forward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator backward() {
        super.backward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator positionCaret(int pos) {
        super.positionCaret(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectPositionCaret(int pos) {
        super.selectPositionCaret(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator selectRange(int anchor, int caretPosition) {
        super.selectRange(anchor, caretPosition);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator extendSelection(int pos) {
        super.extendSelection(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator clear() {
        super.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator deselect() {
        super.deselect();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator replaceSelection(String replacement) {
        super.replaceSelection(replacement);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator undo() {
        super.undo();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator redo() {
        super.redo();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator commitValue() {
        super.commitValue();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextFieldConfigurator cancelEdit() {
        super.cancelEdit();
        return this;
    }

    //endregion Text Manipulation Functions

    //endregion TextInputControlConfig Functions
}
