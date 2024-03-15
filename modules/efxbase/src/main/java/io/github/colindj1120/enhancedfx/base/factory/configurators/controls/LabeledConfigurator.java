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
import io.github.colindj1120.enhancedfx.base.factory.configurators.base.interfaces.controls.LabeledConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Labeled;
import javafx.scene.control.OverrunStyle;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * A comprehensive configurator for {@link Labeled} components within the JavaFX framework, offering an extensive range of
 * customization options through a fluent API. This configurator facilitates the detailed configuration of labels, buttons, and other
 * labeled controls, covering aspects from text properties to alignment, font customization, and graphic integration.
 * <p>
 * By extending {@code ControlConfigurator} and implementing {@link LabeledConfig}, this configurator inherits and provides methods to
 * add or remove listeners, bind properties bidirectionally, and directly set properties on a {@link Labeled} instance, thus enabling a
 * highly flexible and expressive approach to UI design.
 * </p>
 *
 * <p>
 * <h2>Capabilities include:</h2>
 * <ul>
 *     <li>Configuring text characteristics, including font settings, text alignment, overrun strategy, and text wrapping.</li>
 *     <li>Customizing appearance properties such as text fill, underline, line spacing, and content display mode.</li>
 *     <li>Adjusting interaction properties, including mnemonic parsing for keyboard shortcuts.</li>
 *     <li>Managing graphic content, including the graphic node, graphic-text gap, and label padding.</li>
 *     <li>Utilizing listeners and bindings for dynamic UI behavior and interactivity enhancements.</li>
 * </ul>
 * This configurator is indispensable for developers aiming to craft nuanced and user-friendly interfaces with JavaFX, offering
 * precise control over the presentation and functionality of labeled controls.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Labeled
 * @see ControlConfigurator
 * @see ChangeListener
 * @see InvalidationListener
 * @see ObservableValue
 * @see Node
 * @see Font
 * @see Paint
 * @see Pos
 * @see TextAlignment
 * @see OverrunStyle
 * @see ContentDisplay
 */
public abstract class LabeledConfigurator<T extends LabeledConfigurator<T>> extends ControlConfigurator<T> implements LabeledConfig {
    /**
     * The {@link Labeled} instance that is being configured. This field holds a reference to the specific labeled object upon which
     * configuration methods will act, enabling the modification and customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the labeled, allowing changes to be made through the configurator's methods rather
     * than direct access, promoting a more structured and maintainable approach to UI customization. The configurator provides a
     * fluent API for configuring various aspects of the labeled, including its appearance, behavior, and event handling.
     * </p>
     */
    private Labeled labeled;

    /**
     * Constructs a {@code LabeledConfigurator} for the specified {@link Labeled}. This constructor initializes the configurator with a
     * target labeled. It sets up the environment for further configuration specific to {@link Labeled} instances. The
     * {@code LabeledConfigurator.class} is used as the class reference for error reporting.
     *
     * @param labeled
     *         The {@link Labeled} to be configured. Must not be {@code null}, and an {@link IllegalArgumentException} will be thrown
     *         if a null labeled is passed. This ensures that the configurator has a valid target for configuration.
     */
    protected LabeledConfigurator(Labeled labeled) {
        super(ConfiguratorBase.checkNodeNotNullAndInstanceOf(labeled, Labeled.class, LabeledConfigurator.class, "Constructor"));
        this.labeled = labeled;
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
        super.setNode(ConfiguratorBase.checkNodeNotNullAndInstanceOf(value, Labeled.class, LabeledConfigurator.class, "setNode"));
        this.labeled = ((Labeled) value);
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
    public LabeledConfig addTextChangeListener(ChangeListener<? super String> changeListener) {
        labeled.textProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addTextInvalidationListener(InvalidationListener invalidationListener) {
        labeled.textProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        labeled.alignmentProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        labeled.alignmentProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        labeled.textAlignmentProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        labeled.textAlignmentProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        labeled.textOverrunProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        labeled.textOverrunProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        labeled.ellipsisStringProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        labeled.ellipsisStringProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        labeled.wrapTextProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        labeled.wrapTextProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addFontChangeListener(ChangeListener<? super Font> changeListener) {
        labeled.fontProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addFontInvalidationListener(InvalidationListener invalidationListener) {
        labeled.fontProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        labeled.graphicProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addGraphicInvalidationListener(InvalidationListener invalidationListener) {
        labeled.graphicProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        labeled.underlineProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        labeled.underlineProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        labeled.lineSpacingProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        labeled.lineSpacingProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        labeled.contentDisplayProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        labeled.contentDisplayProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        labeled.labelPaddingProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        labeled.labelPaddingProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        labeled.graphicTextGapProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        labeled.graphicTextGapProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        labeled.textFillProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addTextFillInvalidationListener(InvalidationListener invalidationListener) {
        labeled.textFillProperty()
               .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        labeled.mnemonicParsingProperty()
               .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig addMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
        labeled.mnemonicParsingProperty()
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
    public LabeledConfig removeTextChangeListener(ChangeListener<? super String> changeListener) {
        labeled.textProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeTextInvalidationListener(InvalidationListener invalidationListener) {
        labeled.textProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        labeled.alignmentProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        labeled.alignmentProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        labeled.textAlignmentProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        labeled.textAlignmentProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        labeled.textOverrunProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        labeled.textOverrunProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        labeled.ellipsisStringProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        labeled.ellipsisStringProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        labeled.wrapTextProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        labeled.wrapTextProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        labeled.fontProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeFontInvalidationListener(InvalidationListener invalidationListener) {
        labeled.fontProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        labeled.graphicProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeGraphicInvalidationListener(InvalidationListener invalidationListener) {
        labeled.graphicProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        labeled.underlineProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        labeled.underlineProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        labeled.lineSpacingProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        labeled.lineSpacingProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        labeled.contentDisplayProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        labeled.contentDisplayProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        labeled.labelPaddingProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        labeled.labelPaddingProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        labeled.graphicTextGapProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        labeled.graphicTextGapProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        labeled.textFillProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeTextFillInvalidationListener(InvalidationListener invalidationListener) {
        labeled.textFillProperty()
               .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        labeled.mnemonicParsingProperty()
               .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig removeMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
        labeled.mnemonicParsingProperty()
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
    public LabeledConfig bindTextProperty(ObservableValue<? extends String> observableValue) {
        labeled.textProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindTextProperty() {
        labeled.textProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalTextProperty(Property<String> property) {
        labeled.textProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalTextProperty(Property<String> property) {
        labeled.textProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindAlignmentProperty(ObservableValue<? extends Pos> observableValue) {
        labeled.alignmentProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindAlignmentProperty() {
        labeled.alignmentProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalAlignmentProperty(Property<Pos> property) {
        labeled.alignmentProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalAlignmentProperty(Property<Pos> property) {
        labeled.alignmentProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindTextAlignmentProperty(ObservableValue<? extends TextAlignment> observableValue) {
        labeled.textAlignmentProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindTextAlignmentProperty() {
        labeled.textAlignmentProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        labeled.textAlignmentProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        labeled.textAlignmentProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindTextOverrunProperty(ObservableValue<? extends OverrunStyle> observableValue) {
        labeled.textOverrunProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindTextOverrunProperty() {
        labeled.textOverrunProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        labeled.textOverrunProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        labeled.textOverrunProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindEllipsisStringProperty(ObservableValue<? extends String> observableValue) {
        labeled.ellipsisStringProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindEllipsisStringProperty() {
        labeled.ellipsisStringProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalEllipsisStringProperty(Property<String> property) {
        labeled.ellipsisStringProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalEllipsisStringProperty(Property<String> property) {
        labeled.ellipsisStringProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue) {
        labeled.wrapTextProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindWrapTextProperty() {
        labeled.wrapTextProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalWrapTextProperty(Property<Boolean> property) {
        labeled.wrapTextProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalWrapTextProperty(Property<Boolean> property) {
        labeled.wrapTextProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindFontProperty(ObservableValue<? extends Font> observableValue) {
        labeled.fontProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindFontProperty() {
        labeled.fontProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalFontProperty(Property<Font> property) {
        labeled.fontProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalFontProperty(Property<Font> property) {
        labeled.fontProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindGraphicProperty(ObservableValue<? extends Node> observableValue) {
        labeled.graphicProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindGraphicProperty() {
        labeled.graphicProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalGraphicProperty(Property<Node> property) {
        labeled.graphicProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalGraphicProperty(Property<Node> property) {
        labeled.graphicProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindUnderlineProperty(ObservableValue<? extends Boolean> observableValue) {
        labeled.underlineProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindUnderlineProperty() {
        labeled.underlineProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalUnderlineProperty(Property<Boolean> property) {
        labeled.underlineProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalUnderlineProperty(Property<Boolean> property) {
        labeled.underlineProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindLineSpacingProperty(ObservableValue<? extends Number> observableValue) {
        labeled.lineSpacingProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindLineSpacingProperty() {
        labeled.lineSpacingProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalLineSpacingProperty(Property<Number> property) {
        labeled.lineSpacingProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalLineSpacingProperty(Property<Number> property) {
        labeled.lineSpacingProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindContentDisplayProperty(ObservableValue<? extends ContentDisplay> observableValue) {
        labeled.contentDisplayProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindContentDisplayProperty() {
        labeled.contentDisplayProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        labeled.contentDisplayProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        labeled.contentDisplayProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindGraphicTextGapProperty(ObservableValue<? extends Number> observableValue) {
        labeled.graphicTextGapProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindGraphicTextGapProperty() {
        labeled.graphicTextGapProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        labeled.graphicTextGapProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        labeled.graphicTextGapProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindTextFillProperty(ObservableValue<? extends Paint> observableValue) {
        labeled.textFillProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindTextFillProperty() {
        labeled.textFillProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalTextFillProperty(Property<Paint> property) {
        labeled.textFillProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalTextFillProperty(Property<Paint> property) {
        labeled.textFillProperty()
               .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindMnemonicParsingProperty(ObservableValue<? extends Boolean> observableValue) {
        labeled.mnemonicParsingProperty()
               .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindMnemonicParsingProperty() {
        labeled.mnemonicParsingProperty()
               .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig bindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        labeled.mnemonicParsingProperty()
               .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig unbindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        labeled.mnemonicParsingProperty()
               .unbindBidirectional(property);
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
    public LabeledConfig setText(String value) {
        labeled.setText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setAlignment(Pos value) {
        labeled.setAlignment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setTextAlignment(TextAlignment value) {
        labeled.setTextAlignment(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setTextOverrun(OverrunStyle value) {
        labeled.setTextOverrun(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setEllipsisString(String value) {
        labeled.setEllipsisString(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setWrapText(boolean value) {
        labeled.setWrapText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setFont(Font value) {
        labeled.setFont(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setGraphic(Node value) {
        labeled.setGraphic(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setUnderline(boolean value) {
        labeled.setUnderline(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setLineSpacing(double value) {
        labeled.setLineSpacing(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setContentDisplay(ContentDisplay value) {
        labeled.setContentDisplay(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setGraphicTextGap(double value) {
        labeled.setGraphicTextGap(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setTextFill(Paint value) {
        labeled.setTextFill(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LabeledConfig setMnemonicParsing(boolean value) {
        labeled.setMnemonicParsing(value);
        return this;
    }

    //endregion Set Functions
}
