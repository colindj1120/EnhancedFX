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

import io.github.colindj1120.enhancedfx.base.factory.configurators.base.interfaces.controls.TextInputControlConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Font;

/**
 * Provides a comprehensive configurator for {@link TextInputControl} instances within JavaFX applications, facilitating the
 * customization and enhancement of text input controls through a fluent API. This abstract class extends {@code ControlConfigurator}
 * to offer specialized configuration methods tailored to text input controls, such as text fields and text areas.
 *
 * <p>
 * <h2>Capabilities include:</h2>
 * <ul>
 *     <li>Adding and removing listeners for various properties (font, text, prompt text, etc.), enabling
 *     dynamic responses to changes in text input control states.</li>
 *     <li>Binding and unbinding properties to synchronize the text input control's state with other parts
 *     of the application.</li>
 *     <li>Configuring the text input control's text content, font, prompt text, and other editable properties
 *     to customize its appearance and behavior.</li>
 *     <li>Manipulating text within the control, including appending, inserting, deleting, and replacing text,
 *     as well as controlling selection and caret position for precise text operations.</li>
 *     <li>Support for undo and redo actions, providing users with the ability to revert or reapply changes
 *     to the text content.</li>
 *     <li>Enhancing usability through methods that facilitate text navigation and editing actions, such as
 *     moving the caret, selecting text, and clearing the text input.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This configurator aims to encapsulate and abstract the complexity of directly manipulating {@link TextInputControl}
 * properties and event handlers, offering a more intuitive and streamlined approach for developers to customize
 * text input controls within their JavaFX applications.
 * </p>
 *
 * <p>
 * Usage of this class requires extending it to implement concrete configurator classes tailored to specific
 * {@link TextInputControl} subclasses, enabling targeted configurations that align with the requirements
 * of various text input scenarios.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextInputControl
 * @see ControlConfigurator
 */
public abstract class TextInputControlConfigurator<T extends TextInputControlConfigurator<T>> extends ControlConfigurator<T> implements TextInputControlConfig {
    /**
     * The {@link TextInputControl} instance that is being configured. This field holds a reference to the specific textInputControl
     * object upon which configuration methods will act, enabling the modification and customization of its properties and behavior.
     * <p>
     * This private member ensures encapsulation of the textInputControl, allowing changes to be made through the configurator's
     * methods rather than direct access, promoting a more structured and maintainable approach to UI customization. The configurator
     * provides a fluent API for configuring various aspects of the textInputControl, including its appearance, behavior, and event
     * handling.
     * </p>
     */
    private TextInputControl textInputControl;

    /**
     * Constructs a {@code TextInputControlConfigurator} for the specified {@link TextInputControl}. This constructor initializes the
     * configurator with a target textInputControl. It sets up the environment for further configuration specific to
     * {@link TextInputControl} instances. The {@code TextInputControlConfigurator.class} is used as the class reference for error
     * reporting.
     *
     * @param textInputControl
     *         The {@link TextInputControl} to be configured. Must not be {@code null}, and an {@link IllegalArgumentException} will be
     *         thrown if a null textInputControl is passed. This ensures that the configurator has a valid target for configuration.
     */
    protected TextInputControlConfigurator(TextInputControl textInputControl) {
        super(checkNodeNotNullAndInstanceOf(textInputControl, TextInputControl.class, TextInputControlConfigurator.class,
                                            "Constructor"));
        this.textInputControl = textInputControl;
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
        super.setNode(checkNodeNotNullAndInstanceOf(value, TextInputControl.class, TextInputControlConfigurator.class, "setNode"));
        this.textInputControl = ((TextInputControl) value);
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
    public TextInputControlConfig addFontChangeListener(ChangeListener<? super Font> changeListener) {
        textInputControl.fontProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addFontInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.fontProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addPromptTextChangeListener(ChangeListener<? super String> changeListener) {
        textInputControl.promptTextProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addPromptTextInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.promptTextProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        textInputControl.textFormatterProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.textFormatterProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addTextChangeListener(ChangeListener<? super String> changeListener) {
        textInputControl.textProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addTextInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.textProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addLengthChangeListener(ChangeListener<? super Number> changeListener) {
        textInputControl.lengthProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addLengthInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.lengthProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        textInputControl.editableProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addEditableInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.editableProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        textInputControl.selectionProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addSelectionInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.selectionProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        textInputControl.selectedTextProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.selectedTextProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        textInputControl.caretPositionProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.caretPositionProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        textInputControl.anchorProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addAnchorInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.anchorProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        textInputControl.undoableProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addUndoableInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.undoableProperty()
                        .addListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        textInputControl.redoableProperty()
                        .addListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig addRedoableInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.redoableProperty()
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
    public TextInputControlConfig removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        textInputControl.fontProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeFontInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.fontProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removePromptTextChangeListener(ChangeListener<? super String> changeListener) {
        textInputControl.promptTextProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removePromptTextInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.promptTextProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        textInputControl.textFormatterProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.textFormatterProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeTextChangeListener(ChangeListener<? super String> changeListener) {
        textInputControl.textProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeTextInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.textProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeLengthChangeListener(ChangeListener<? super Number> changeListener) {
        textInputControl.lengthProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeLengthInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.lengthProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        textInputControl.editableProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeEditableInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.editableProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        textInputControl.selectionProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeSelectionInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.selectionProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        textInputControl.selectedTextProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.selectedTextProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        textInputControl.caretPositionProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.caretPositionProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        textInputControl.anchorProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeAnchorInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.anchorProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        textInputControl.undoableProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeUndoableInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.undoableProperty()
                        .removeListener(invalidationListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        textInputControl.redoableProperty()
                        .removeListener(changeListener);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig removeRedoableInvalidationListener(InvalidationListener invalidationListener) {
        textInputControl.redoableProperty()
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
    public TextInputControlConfig bindFontProperty(ObservableValue<? extends Font> observableValue) {
        textInputControl.fontProperty()
                        .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindFontProperty() {
        textInputControl.fontProperty()
                        .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindBidirectionalFontProperty(Property<Font> otherProperty) {
        textInputControl.fontProperty()
                        .bindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindBidirectionalFontProperty(Property<Font> otherProperty) {
        textInputControl.fontProperty()
                        .unbindBidirectional(otherProperty);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindPromptTextProperty(ObservableValue<? extends String> observableValue) {
        textInputControl.promptTextProperty()
                        .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindPromptTextProperty() {
        textInputControl.promptTextProperty()
                        .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindBidirectionalPromptTextProperty(Property<String> property) {
        textInputControl.promptTextProperty()
                        .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindBidirectionalPromptTextProperty(Property<String> property) {
        textInputControl.promptTextProperty()
                        .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindTextFormatterProperty(ObservableValue<? extends TextFormatter<?>> observableValue) {
        textInputControl.textFormatterProperty()
                        .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindTextFormatterProperty() {
        textInputControl.textFormatterProperty()
                        .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        textInputControl.textFormatterProperty()
                        .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        textInputControl.textFormatterProperty()
                        .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindTextProperty(ObservableValue<? extends String> observableValue) {
        textInputControl.textProperty()
                        .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindTextProperty() {
        textInputControl.textProperty()
                        .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindBidirectionalTextProperty(Property<String> property) {
        textInputControl.textProperty()
                        .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindBidirectionalTextProperty(Property<String> property) {
        textInputControl.textProperty()
                        .unbindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindEditableProperty(ObservableValue<? extends Boolean> observableValue) {
        textInputControl.editableProperty()
                        .bind(observableValue);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindEditableProperty() {
        textInputControl.editableProperty()
                        .unbind();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig bindBidirectionalEditableProperty(Property<Boolean> property) {
        textInputControl.editableProperty()
                        .bindBidirectional(property);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig unbindBidirectionalEditableProperty(Property<Boolean> property) {
        textInputControl.editableProperty()
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
    public TextInputControlConfig setFont(Font value) {
        textInputControl.setFont(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig setPromptText(String value) {
        textInputControl.setPromptText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig setTextFormatter(TextFormatter<?> value) {
        textInputControl.setTextFormatter(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig setText(String value) {
        textInputControl.setText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig setEditable(boolean value) {
        textInputControl.setEditable(value);
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
    public TextInputControlConfig appendText(String text) {
        textInputControl.appendText(text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig insertText(int index, String text) {
        textInputControl.insertText(index, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig deleteText(IndexRange range) {
        textInputControl.deleteText(range.getStart(), range.getEnd());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig deleteText(int start, int end) {
        textInputControl.deleteText(start, end);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig replaceText(IndexRange range, String text) {
        textInputControl.replaceText(range, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig replaceText(int start, int end, String text) {
        textInputControl.replaceText(start, end, text);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig cut() {
        textInputControl.cut();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig copy() {
        textInputControl.copy();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig paste() {
        textInputControl.paste();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectBackward() {
        textInputControl.selectBackward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectForward() {
        textInputControl.selectForward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig previousWord() {
        textInputControl.previousWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig nextWord() {
        textInputControl.nextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig endOfNextWord() {
        textInputControl.endOfNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectPreviousWord() {
        textInputControl.selectPreviousWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectNextWord() {
        textInputControl.selectNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectEndOfNextWord() {
        textInputControl.selectEndOfNextWord();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectAll() {
        textInputControl.selectAll();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig home() {
        textInputControl.home();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig end() {
        textInputControl.end();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectHome() {
        textInputControl.selectHome();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectEnd() {
        textInputControl.selectEnd();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig deletePreviousChar() {
        textInputControl.deletePreviousChar();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig deleteNextChar() {
        textInputControl.deleteNextChar();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig forward() {
        textInputControl.forward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig backward() {
        textInputControl.backward();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig positionCaret(int pos) {
        textInputControl.positionCaret(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectPositionCaret(int pos) {
        textInputControl.selectPositionCaret(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig selectRange(int anchor, int caretPosition) {
        textInputControl.selectRange(anchor, caretPosition);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig extendSelection(int pos) {
        textInputControl.extendSelection(pos);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig clear() {
        textInputControl.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig deselect() {
        textInputControl.deselect();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig replaceSelection(String replacement) {
        textInputControl.replaceSelection(replacement);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig undo() {
        textInputControl.undo();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig redo() {
        textInputControl.redo();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig commitValue() {
        textInputControl.commitValue();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextInputControlConfig cancelEdit() {
        textInputControl.cancelEdit();
        return this;
    }

    //endregion Text Manipulation Functions
}
