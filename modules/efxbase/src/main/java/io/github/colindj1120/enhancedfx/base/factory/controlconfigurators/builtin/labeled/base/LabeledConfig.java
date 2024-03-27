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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.labeled.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.control.base.ControlConfig;
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
 * Provides configuration capabilities for {@link Labeled} nodes in JavaFX.
 *
 * <p>This interface extends {@code ControlConfig<T>} and allows for the customization of labeled controls such as Labels, Buttons, and Checkboxes.</p>
 *
 * <p>Implementations of this interface can be used to configure properties such as text, font, text alignment, and graphic, as well as to add listeners to these properties for change and invalidation
 * notifications. Additionally, it supports binding properties to observable values for dynamic updates.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *   <li><b>Text Configuration:</b> Set or bind the text property of the node.</li>
 *   <li><b>Font and Style:</b> Customize font, text alignment, and wrapping.</li>
 *   <li><b>Graphic Configuration:</b> Set or bind a graphic to the node, and manage the gap between text and graphic.</li>
 *   <li><b>Listeners:</b> Add or remove listeners for various properties including text changes, font changes, and graphic changes.</li>
 *   <li><b>Property Binding:</b> Bind properties such as text, font, and graphic to observable values for automatic updates.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * Below is a simple usage example that demonstrates how to configure a Label node using an implementation of {@code LabeledConfig}.
 * <pre>{@code
 * LabeledConfig<LabelConfigurator> labelConfigurator = new LabelConfigurator(label);
 * labelConfigurator.setText("Hello, World!")
 *                  .setFont(new Font("Arial", 16))
 *                  .setTextAlignment(TextAlignment.CENTER)
 *                  .setGraphic(new ImageView(iconImage))
 *                  .addTextChangeListener((observable, oldValue, newValue) -> System.out.println("Text changed from " + oldValue + " to " + newValue));
 * }</pre>
 *
 * <p>In this example, a {@code Label} node is configured with text, font, text alignment, and a graphic. Additionally, a text change listener is added to monitor changes to the text property.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see ControlConfig
 * @see ConfiguratorBase
 * @see Labeled
 */
@SuppressWarnings("UnusedReturnValue")
public interface LabeledConfig<T extends ConfiguratorBase> extends ControlConfig<T> {
    /*
     * Methods Available:
     *  - Labeled getNode()
     *
     * Add Listener Functions
     *  - T addTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T addTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T addAlignmentChangeListener(ChangeListener<? super Pos> changeListener)
     *  - T addAlignmentInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener)
     *  - T addTextAlignmentInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener)
     *  - T addTextOverrunInvalidationListener(InvalidationListener invalidationListener)
     *  - T addEllipsisStringChangeListener(ChangeListener<? super String> changeListener)
     *  - T addEllipsisStringInvalidationListener(InvalidationListener invalidationListener)
     *  - T addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addWrapTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T addFontChangeListener(ChangeListener<? super Font> changeListener)
     *  - T addFontInvalidationListener(InvalidationListener invalidationListener)
     *  - T addGraphicChangeListener(ChangeListener<? super Node> changeListener)
     *  - T addGraphicInvalidationListener(InvalidationListener invalidationListener)
     *  - T addUnderlineChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addUnderlineInvalidationListener(InvalidationListener invalidationListener)
     *  - T addLineSpacingChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addLineSpacingInvalidationListener(InvalidationListener invalidationListener)
     *  - T addContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener)
     *  - T addContentDisplayInvalidationListener(InvalidationListener invalidationListener)
     *  - T addGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addGraphicTextGapInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTextFillChangeListener(ChangeListener<? super Paint> changeListener)
     *  - T addTextFillInvalidationListener(InvalidationListener invalidationListener)
     *  - T addMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addMnemonicParsingInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener)
     *  - T removeAlignmentInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener)
     *  - T removeTextAlignmentInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener)
     *  - T removeTextOverrunInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeEllipsisStringChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeEllipsisStringInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeWrapTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeFontChangeListener(ChangeListener<? super Font> changeListener)
     *  - T removeFontInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeGraphicChangeListener(ChangeListener<? super Node> changeListener)
     *  - T removeGraphicInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeUnderlineChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeUnderlineInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeLineSpacingChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeLineSpacingInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener)
     *  - T removeContentDisplayInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeGraphicTextGapInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTextFillChangeListener(ChangeListener<? super Paint> changeListener)
     *  - T removeTextFillInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeMnemonicParsingInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindTextProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindTextProperty()
     *  - T bindBidirectionalTextProperty(Property<String> property)
     *  - T unbindBidirectionalTextProperty(Property<String> property)
     *  - T bindAlignmentProperty(ObservableValue<? extends Pos> observableValue)
     *  - T unbindAlignmentProperty()
     *  - T bindBidirectionalAlignmentProperty(Property<Pos> property)
     *  - T unbindBidirectionalAlignmentProperty(Property<Pos> property)
     *  - T bindTextAlignmentProperty(ObservableValue<? extends TextAlignment> observableValue)
     *  - T unbindTextAlignmentProperty()
     *  - T bindBidirectionalTextAlignmentProperty(Property<TextAlignment> property)
     *  - T unbindBidirectionalTextAlignmentProperty(Property<TextAlignment> property)
     *  - T bindTextOverrunProperty(ObservableValue<? extends OverrunStyle> observableValue)
     *  - T unbindTextOverrunProperty()
     *  - T bindBidirectionalTextOverrunProperty(Property<OverrunStyle> property)
     *  - T unbindBidirectionalTextOverrunProperty(Property<OverrunStyle> property)
     *  - T bindEllipsisStringProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindEllipsisStringProperty()
     *  - T bindBidirectionalEllipsisStringProperty(Property<String> property)
     *  - T unbindBidirectionalEllipsisStringProperty(Property<String> property)
     *  - T bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindWrapTextProperty()
     *  - T bindBidirectionalWrapTextProperty(Property<Boolean> property)
     *  - T unbindBidirectionalWrapTextProperty(Property<Boolean> property)
     *  - T bindFontProperty(ObservableValue<? extends Font> observableValue)
     *  - T unbindFontProperty()
     *  - T bindBidirectionalFontProperty(Property<Font> property)
     *  - T unbindBidirectionalFontProperty(Property<Font> property)
     *  - T bindGraphicProperty(ObservableValue<? extends Node> observableValue)
     *  - T unbindGraphicProperty()
     *  - T bindBidirectionalGraphicProperty(Property<Node> property)
     *  - T unbindBidirectionalGraphicProperty(Property<Node> property)
     *  - T bindUnderlineProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindUnderlineProperty()
     *  - T bindBidirectionalUnderlineProperty(Property<Boolean> property)
     *  - T unbindBidirectionalUnderlineProperty(Property<Boolean> property)
     *  - T bindLineSpacingProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindLineSpacingProperty()
     *  - T bindBidirectionalLineSpacingProperty(Property<Number> property)
     *  - T unbindBidirectionalLineSpacingProperty(Property<Number> property)
     *  - T bindContentDisplayProperty(ObservableValue<? extends ContentDisplay> observableValue)
     *  - T unbindContentDisplayProperty()
     *  - T bindBidirectionalContentDisplayProperty(Property<ContentDisplay> property)
     *  - T unbindBidirectionalContentDisplayProperty(Property<ContentDisplay> property)
     *  - T bindGraphicTextGapProperty(ObservableValue<? extends Number> observableValue)
     *  - T unbindGraphicTextGapProperty()
     *  - T bindBidirectionalGraphicTextGapProperty(Property<Number> property)
     *  - T unbindBidirectionalGraphicTextGapProperty(Property<Number> property)
     *  - T bindTextFillProperty(ObservableValue<? extends Paint> observableValue)
     *  - T unbindTextFillProperty()
     *  - T bindBidirectionalTextFillProperty(Property<Paint> property)
     *  - T unbindBidirectionalTextFillProperty(Property<Paint> property)
     *  - T bindMnemonicParsingProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindMnemonicParsingProperty()
     *  - T bindBidirectionalMnemonicParsingProperty(Property<Boolean> property)
     *  - T unbindBidirectionalMnemonicParsingProperty(Property<Boolean> property)
     *
     * Set Functions
     *  - T setText(String value)
     *  - T setAlignment(Pos value)
     *  - T setTextAlignment(TextAlignment value)
     *  - T setTextOverrun(OverrunStyle value)
     *  - T setEllipsisString(String value)
     *  - T setWrapText(boolean value)
     *  - T setFont(Font value)
     *  - T setGraphic(Node value)
     *  - T setUnderline(boolean value)
     *  - T setLineSpacing(double value)
     *  - T setContentDisplay(ContentDisplay value)
     *  - T setGraphicTextGap(double value)
     *  - T setTextFill(Paint value)
     *  - T setMnemonicParsing(boolean value)
     */

    /**
     * Returns the {@link Labeled} node associated with this configurator.
     *
     * <p>This method provides access to the labeled node that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link Labeled} associated with the current configurator instance
     */
    @Override
    Labeled getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the text property. This listener is notified whenever the text content of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().textProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the text property. This listener is notified whenever the text property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the alignment property. This listener is notified whenever the alignment of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        getNode().alignmentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the alignment property. This listener is notified whenever the alignment property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().alignmentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the text alignment property. This listener is notified whenever the text alignment of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        getNode().textAlignmentProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the text alignment property. This listener is notified whenever the text alignment property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textAlignmentProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the text overrun property. This listener is notified whenever the text overrun style of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        getNode().textOverrunProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the text overrun property. This listener is notified whenever the text overrun property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textOverrunProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the ellipsis string property. This listener is notified whenever the ellipsis string of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        getNode().ellipsisStringProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the ellipsis string property. This listener is notified whenever the ellipsis string property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        getNode().ellipsisStringProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the wrap text property. This listener is notified whenever the wrap text status of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().wrapTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the wrap text property. This listener is notified whenever the wrap text property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().wrapTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the font property. This listener is notified whenever the font of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addFontChangeListener(ChangeListener<? super Font> changeListener) {
        getNode().fontProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the font property. This listener is notified whenever the font property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addFontInvalidationListener(InvalidationListener invalidationListener) {
        getNode().fontProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the graphic property. This listener is notified whenever the graphic of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().graphicProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the graphic property. This listener is notified whenever the graphic property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addGraphicInvalidationListener(InvalidationListener invalidationListener) {
        getNode().graphicProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the underline property. This listener is notified whenever the underline status of the text changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().underlineProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the underline property. This listener is notified whenever the underline property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        getNode().underlineProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the line-spacing property. This listener is notified whenever the line spacing of the text changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().lineSpacingProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the line-spacing property. This listener is notified whenever the line-spacing property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().lineSpacingProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the content display property. This listener is notified whenever the content display strategy of the node changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        getNode().contentDisplayProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the content display property. This listener is notified whenever the content display property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        getNode().contentDisplayProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the label padding property. This listener is notified whenever the padding around the label changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().labelPaddingProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the label padding property. This listener is notified whenever the label padding property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().labelPaddingProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the graphic text gap property. This listener is notified whenever the gap between the graphic and the text changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().graphicTextGapProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the graphic text gap property. This listener is notified whenever the graphic text gap property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        getNode().graphicTextGapProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the text fill property. This listener is notified whenever the color of the text changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        getNode().textFillProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the text fill property. This listener is notified whenever the text fill property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextFillInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textFillProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the mnemonic parsing property. This listener is notified whenever the mnemonic parsing feature is enabled or disabled.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().mnemonicParsingProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for invalidations of the mnemonic parsing property. This listener is notified whenever the mnemonic parsing property becomes invalid.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().mnemonicParsingProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the text property. This method stops the given listener from receiving text change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().textProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the text property. This method stops the given listener from receiving text invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the alignment property. This method stops the given listener from receiving alignment change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        getNode().alignmentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the alignment property. This method stops the given listener from receiving alignment invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().alignmentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the text alignment property. This method stops the given listener from receiving text alignment change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener) {
        getNode().textAlignmentProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the text alignment property. This method stops the given listener from receiving text alignment invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textAlignmentProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the text overrun property. This method stops the given listener from receiving text overrun change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener) {
        getNode().textOverrunProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the text overrun property. This method stops the given listener from receiving text overrun invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextOverrunInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textOverrunProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the ellipsis string property. This method stops the given listener from receiving ellipsis string change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeEllipsisStringChangeListener(ChangeListener<? super String> changeListener) {
        getNode().ellipsisStringProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the ellipsis string property. This method stops the given listener from receiving ellipsis string invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeEllipsisStringInvalidationListener(InvalidationListener invalidationListener) {
        getNode().ellipsisStringProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the wrap text property. This method stops the given listener from receiving wrap text change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().wrapTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the wrap text property. This method stops the given listener from receiving wrap text invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().wrapTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the font property. This method stops the given listener from receiving font change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        getNode().fontProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the font property. This method stops the given listener from receiving font invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeFontInvalidationListener(InvalidationListener invalidationListener) {
        getNode().fontProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the graphic property. This method stops the given listener from receiving graphic change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeGraphicChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().graphicProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the graphic property. This method stops the given listener from receiving graphic invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeGraphicInvalidationListener(InvalidationListener invalidationListener) {
        getNode().graphicProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the underline property. This method stops the given listener from receiving underline change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeUnderlineChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().underlineProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the underline property. This method stops the given listener from receiving underline invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeUnderlineInvalidationListener(InvalidationListener invalidationListener) {
        getNode().underlineProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the line spacing property. This method stops the given listener from receiving line spacing change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLineSpacingChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().lineSpacingProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the line spacing property. This method stops the given listener from receiving line spacing invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLineSpacingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().lineSpacingProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the content display property. This method stops the given listener from receiving content display change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener) {
        getNode().contentDisplayProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the content display property. This method stops the given listener from receiving content display invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeContentDisplayInvalidationListener(InvalidationListener invalidationListener) {
        getNode().contentDisplayProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the label padding property. This method stops the given listener from receiving label padding change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener) {
        getNode().labelPaddingProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the label padding property. This method stops the given listener from receiving label padding invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLabelPaddingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().labelPaddingProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the graphic text gap property. This method stops the given listener from receiving graphic text gap change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().graphicTextGapProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the graphic text gap property. This method stops the given listener from receiving graphic text gap invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeGraphicTextGapInvalidationListener(InvalidationListener invalidationListener) {
        getNode().graphicTextGapProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the text fill property. This method stops the given listener from receiving text fill change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextFillChangeListener(ChangeListener<? super Paint> changeListener) {
        getNode().textFillProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the text fill property. This method stops the given listener from receiving text fill invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextFillInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textFillProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from the mnemonic parsing property. This method stops the given listener from receiving mnemonic parsing change notifications.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().mnemonicParsingProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from the mnemonic parsing property. This method stops the given listener from receiving mnemonic parsing invalidation notifications.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeMnemonicParsingInvalidationListener(InvalidationListener invalidationListener) {
        getNode().mnemonicParsingProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Text Property

    /**
     * Binds the text property of the node to an observable value. The text of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindTextProperty(ObservableValue<? extends String> observableValue) {
        getNode().textProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the text property of the node. This stops the text property from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindTextProperty() {
        getNode().textProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the text property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTextProperty(Property<String> property) {
        getNode().textProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the text property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTextProperty(Property<String> property) {
        getNode().textProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Alignment Property

    /**
     * Binds the alignment property of the node to an observable value. The alignment of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the alignment property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindAlignmentProperty(ObservableValue<? extends Pos> observableValue) {
        getNode().alignmentProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the alignment property of the node. This stops the alignment property from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindAlignmentProperty() {
        getNode().alignmentProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the alignment property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalAlignmentProperty(Property<Pos> property) {
        getNode().alignmentProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the alignment property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalAlignmentProperty(Property<Pos> property) {
        getNode().alignmentProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Text Alignment Property

    /**
     * Binds the text alignment property of the node to an observable value. The text alignment of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the text alignment property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindTextAlignmentProperty(ObservableValue<? extends TextAlignment> observableValue) {
        getNode().textAlignmentProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the text alignment property of the node. This stops the text alignment property from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindTextAlignmentProperty() {
        getNode().textAlignmentProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the text alignment property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        getNode().textAlignmentProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the text alignment property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTextAlignmentProperty(Property<TextAlignment> property) {
        getNode().textAlignmentProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Text Overrun Property

    /**
     * Binds the text overrun property of the node to an observable value. The text overrun behavior of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the text overrun property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindTextOverrunProperty(ObservableValue<? extends OverrunStyle> observableValue) {
        getNode().textOverrunProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the text overrun property of the node. This stops the text overrun behavior from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindTextOverrunProperty() {
        getNode().textOverrunProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the text overrun property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        getNode().textOverrunProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the text overrun property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTextOverrunProperty(Property<OverrunStyle> property) {
        getNode().textOverrunProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Ellipsis String Property

    /**
     * Binds the ellipsis string property of the node to an observable value. The ellipsis string used in text overrun situations will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the ellipsis string property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindEllipsisStringProperty(ObservableValue<? extends String> observableValue) {
        getNode().ellipsisStringProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the ellipsis string property of the node. This stops the ellipsis string from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindEllipsisStringProperty() {
        getNode().ellipsisStringProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the ellipsis string property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalEllipsisStringProperty(Property<String> property) {
        getNode().ellipsisStringProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the ellipsis string property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalEllipsisStringProperty(Property<String> property) {
        getNode().ellipsisStringProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Wrap Text Property

    /**
     * Binds the wrap text property of the node to an observable value. The wrap text behavior of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the wrap text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().wrapTextProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the wrap text property of the node. This stops the wrap text behavior from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindWrapTextProperty() {
        getNode().wrapTextProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the wrap text property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalWrapTextProperty(Property<Boolean> property) {
        getNode().wrapTextProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the wrap text property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalWrapTextProperty(Property<Boolean> property) {
        getNode().wrapTextProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Font Property

    /**
     * Binds the font property of the node to an observable value. The font of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the font property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindFontProperty(ObservableValue<? extends Font> observableValue) {
        getNode().fontProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the font property of the node. This stops the font from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindFontProperty() {
        getNode().fontProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the font property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalFontProperty(Property<Font> property) {
        getNode().fontProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the font property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalFontProperty(Property<Font> property) {
        getNode().fontProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Graphic Property

    /**
     * Binds the graphic property of the node to an observable value. The graphic of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the graphic property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindGraphicProperty(ObservableValue<? extends Node> observableValue) {
        getNode().graphicProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the graphic property of the node. This stops the graphic from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindGraphicProperty() {
        getNode().graphicProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the graphic property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalGraphicProperty(Property<Node> property) {
        getNode().graphicProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the graphic property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalGraphicProperty(Property<Node> property) {
        getNode().graphicProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Underline Property

    /**
     * Binds the underline property of the node to an observable value. The underline status of the text within the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the underline property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindUnderlineProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().underlineProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the underline property of the node. This stops the underline status from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindUnderlineProperty() {
        getNode().underlineProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the underline property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalUnderlineProperty(Property<Boolean> property) {
        getNode().underlineProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the underline property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalUnderlineProperty(Property<Boolean> property) {
        getNode().underlineProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Line Spacing Property

    /**
     * Binds the line spacing property of the node to an observable value. The line spacing within the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the line-spacing property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindLineSpacingProperty(ObservableValue<? extends Number> observableValue) {
        getNode().lineSpacingProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the line-spacing property of the node. This stops the line spacing from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindLineSpacingProperty() {
        getNode().lineSpacingProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the line spacing property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalLineSpacingProperty(Property<Number> property) {
        getNode().lineSpacingProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the line spacing property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalLineSpacingProperty(Property<Number> property) {
        getNode().lineSpacingProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Content Display Property

    /**
     * Binds the content display property of the node to an observable value. The content display mode of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the content display property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindContentDisplayProperty(ObservableValue<? extends ContentDisplay> observableValue) {
        getNode().contentDisplayProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the content display property of the node. This stops the content display mode from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindContentDisplayProperty() {
        getNode().contentDisplayProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the content display property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        getNode().contentDisplayProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the content display property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalContentDisplayProperty(Property<ContentDisplay> property) {
        getNode().contentDisplayProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Graphic Text Gap Property

    /**
     * Binds the graphic text gap property of the node to an observable value. The space between the graphic and text of the node will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the graphic text gap property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindGraphicTextGapProperty(ObservableValue<? extends Number> observableValue) {
        getNode().graphicTextGapProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the graphic text gap property of the node. This stops the graphic text gap from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindGraphicTextGapProperty() {
        getNode().graphicTextGapProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the graphic text gap property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        getNode().graphicTextGapProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the graphic text gap property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalGraphicTextGapProperty(Property<Number> property) {
        getNode().graphicTextGapProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Text Fill Property

    /**
     * Binds the text fill property of the node to an observable value. The color of the text will automatically update whenever the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the text fill property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindTextFillProperty(ObservableValue<? extends Paint> observableValue) {
        getNode().textFillProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the text fill property of the node. This stops the text color from automatically updating.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindTextFillProperty() {
        getNode().textFillProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the text fill property of the node bidirectionally with another property. Both properties will automatically update to reflect each other's changes.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTextFillProperty(Property<Paint> property) {
        getNode().textFillProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the text fill property of the node from a bidirectional binding with another property.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTextFillProperty(Property<Paint> property) {
        getNode().textFillProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Mnemonic Parsing Property

    /**
     * Binds the mnemonic parsing property of the node to an observable value. Enables or disables the parsing of mnemonics in the node's text, automatically updating when the observable value changes.
     *
     * @param observableValue
     *         The observable value to bind to the mnemonic parsing property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindMnemonicParsingProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().mnemonicParsingProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the mnemonic parsing property of the node. This action stops the automatic updating of mnemonic parsing behavior.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindMnemonicParsingProperty() {
        getNode().mnemonicParsingProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the mnemonic parsing property of the node bidirectionally with another property. This ensures that the mnemonic parsing behavior is synchronized with the property it is bound to.
     *
     * @param property
     *         The other property to bind with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        getNode().mnemonicParsingProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the mnemonic parsing property of the node from a bidirectional binding with another property. This action discontinues the synchronization between the two properties.
     *
     * @param property
     *         The other property to unbind from.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalMnemonicParsingProperty(Property<Boolean> property) {
        getNode().mnemonicParsingProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the text of the node.
     *
     * @param value
     *         The new text to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setText(String value) {
        getNode().setText(value);
        return getConfigurator();
    }

    /**
     * Sets the alignment of the node's content.
     *
     * @param value
     *         The new alignment to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setAlignment(Pos value) {
        getNode().setAlignment(value);
        return getConfigurator();
    }

    /**
     * Sets the text alignment within the node.
     *
     * @param value
     *         The new text alignment to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setTextAlignment(TextAlignment value) {
        getNode().setTextAlignment(value);
        return getConfigurator();
    }

    /**
     * Sets the text overrun style of the node.
     *
     * @param value
     *         The new text overrun style to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setTextOverrun(OverrunStyle value) {
        getNode().setTextOverrun(value);
        return getConfigurator();
    }

    /**
     * Sets the ellipsis string used when text overrun happens.
     *
     * @param value
     *         The new ellipsis string to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setEllipsisString(String value) {
        getNode().setEllipsisString(value);
        return getConfigurator();
    }

    /**
     * Enables or disables text wrapping.
     *
     * @param value
     *         {@code true} to enable text wrapping, {@code false} to disable it.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setWrapText(boolean value) {
        getNode().setWrapText(value);
        return getConfigurator();
    }

    /**
     * Sets the font of the node's text.
     *
     * @param value
     *         The new font to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setFont(Font value) {
        getNode().setFont(value);
        return getConfigurator();
    }

    /**
     * Sets the graphic of the node.
     *
     * @param value
     *         The new graphic to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setGraphic(Node value) {
        getNode().setGraphic(value);
        return getConfigurator();
    }

    /**
     * Enables or disables underlining the node's text.
     *
     * @param value
     *         {@code true} to enable underlining, {@code false} to disable it.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setUnderline(boolean value) {
        getNode().setUnderline(value);
        return getConfigurator();
    }

    /**
     * Sets the line spacing in the node.
     *
     * @param value
     *         The new line spacing value to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setLineSpacing(double value) {
        getNode().setLineSpacing(value);
        return getConfigurator();
    }

    /**
     * Sets the content display property of the node.
     *
     * @param value
     *         The new content display mode to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setContentDisplay(ContentDisplay value) {
        getNode().setContentDisplay(value);
        return getConfigurator();
    }

    /**
     * Sets the gap between the graphic and text of the node.
     *
     * @param value
     *         The new graphic text gap value to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setGraphicTextGap(double value) {
        getNode().setGraphicTextGap(value);
        return getConfigurator();
    }

    /**
     * Sets the fill color of the node's text.
     *
     * @param value
     *         The new text fill color to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setTextFill(Paint value) {
        getNode().setTextFill(value);
        return getConfigurator();
    }

    /**
     * Enables or disables mnemonic parsing.
     *
     * @param value
     *         {@code true} to enable mnemonic parsing, {@code false} to disable it.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setMnemonicParsing(boolean value) {
        getNode().setMnemonicParsing(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
