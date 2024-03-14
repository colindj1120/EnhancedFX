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
package io.github.colindj1120.enhancedfx.factories.configurators.base.interfaces.controls;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Font;

/**
 * Interface for configuring TextInputControl objects in JavaFX, providing a fluent API for setting properties,
 * binding to observable values, and registering listeners for property changes and invalidations. This interface
 * enables developers to build dynamic and responsive UI elements with enhanced behavior and user interaction.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Fluent configuration of TextInputControl properties such as font, text, and prompt text.</li>
 *     <li>Ability to bind properties to observable values for automatic UI updates.</li>
 *     <li>Support for bidirectional bindings to keep the model and UI in sync.</li>
 *     <li>Methods to add and remove change and invalidation listeners for various TextInputControl properties.</li>
 *     <li>Direct manipulation of text through append, insert, delete, and replace operations.</li>
 *     <li>Editing support including cut, copy, paste, undo, redo, and selection manipulation.</li>
 *     <li>Text navigation capabilities such as moving the caret and selecting text programmatically.</li>
 *     <li>Advanced text handling with TextFormatter for input validation and text conversion.</li>
 * </ul>
 *
 * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 * {@link TextInputControl} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextInputControl
 */
public interface TextInputControlConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} for the font property changes.
     *
     * @param changeListener
     *         the listener to be notified when the font changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addFontChangeListener(ChangeListener<? super Font> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the font property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the font becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addFontInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the prompt text property changes.
     *
     * @param changeListener
     *         the listener to be notified when the prompt text changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addPromptTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the prompt text property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the prompt text becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addPromptTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the text formatter property changes.
     *
     * @param changeListener
     *         the listener to be notified when the text formatter changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the text formatter property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the text formatter becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addTextFormatterInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the text property changes.
     *
     * @param changeListener
     *         the listener to be notified when the text changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the text property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the text becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the length property changes.
     *
     * @param changeListener
     *         the listener to be notified when the length of the text changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addLengthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the length property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the length property becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addLengthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the editable property changes.
     *
     * @param changeListener
     *         the listener to be notified when the editable status changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addEditableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the editable property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the editable property becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addEditableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the selection property changes.
     *
     * @param changeListener
     *         the listener to be notified when the selection changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addSelectionChangeListener(ChangeListener<? super IndexRange> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the selection property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the selection becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addSelectionInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the selected text property changes.
     *
     * @param changeListener
     *         the listener to be notified when the selected text changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addSelectedTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the selected text property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the selected text becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addSelectedTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the caret position property changes.
     *
     * @param changeListener
     *         the listener to be notified when the caret position changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addCaretPositionChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the caret position property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the caret position becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addCaretPositionInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the anchor property changes.
     *
     * @param changeListener
     *         the listener to be notified when the anchor position changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addAnchorChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the anchor property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the anchor becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addAnchorInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the undoable property changes.
     *
     * @param changeListener
     *         the listener to be notified when the undoable status changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addUndoableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the undoable property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the undoable property becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addUndoableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} for the redoable property changes.
     *
     * @param changeListener
     *         the listener to be notified when the redoable status changes
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addRedoableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} for the redoable property invalidations.
     *
     * @param invalidationListener
     *         the listener to be notified when the redoable property becomes invalid
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig addRedoableInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a previously added {@link ChangeListener} for the font property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeFontChangeListener(ChangeListener<? super Font> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the font property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeFontInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the prompt text property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removePromptTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the prompt text property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removePromptTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the text formatter property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the text formatter property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeTextFormatterInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the text property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the text property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the length property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeLengthChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the length property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeLengthInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the editable property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeEditableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the editable property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeEditableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the selection property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeSelectionChangeListener(ChangeListener<? super IndexRange> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the selection property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeSelectionInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the selected text property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeSelectedTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the selected text property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeSelectedTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the caret position property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeCaretPositionChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the caret position property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeCaretPositionInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the anchor property.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeAnchorChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the anchor property.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeAnchorInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the property that indicates whether the text input is undoable.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeUndoableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the property that indicates whether the text input is undoable.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeUndoableInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a previously added {@link ChangeListener} for the property that indicates whether the text input is redoable.
     *
     * @param changeListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeRedoableChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes a previously added {@link InvalidationListener} for the property that indicates whether the text input is redoable.
     *
     * @param invalidationListener
     *         the listener to be removed
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig removeRedoableInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //Font Property

    /**
     * Binds the font property of the text input control to an {@link ObservableValue}.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind to the font property
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindFontProperty(ObservableValue<? extends Font> observableValue);

    /**
     * Unbinds the font property of the text input control from its currently bound {@link ObservableValue}.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindFontProperty();

    /**
     * Creates a bidirectional binding between the font property of the text input control and another {@link Property}.
     *
     * @param otherProperty
     *         the other {@link Property} to bind with
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindBidirectionalFontProperty(Property<Font> otherProperty);

    /**
     * Removes a bidirectional binding between the font property of the text input control and another {@link Property}.
     *
     * @param otherProperty
     *         the other {@link Property} to unbind from
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindBidirectionalFontProperty(Property<Font> otherProperty);

    //Prompt Text Property

    /**
     * Binds the prompt text property of the text input control to an {@link ObservableValue}.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind to the prompt text property
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindPromptTextProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the prompt text property of the text input control from its currently bound {@link ObservableValue}.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindPromptTextProperty();

    /**
     * Creates a bidirectional binding between the prompt text property of the text input control and another {@link Property}.
     *
     * @param property
     *         the other {@link Property} to bind with
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindBidirectionalPromptTextProperty(Property<String> property);

    /**
     * Removes a bidirectional binding between the prompt text property of the text input control and another {@link Property}.
     *
     * @param property
     *         the other {@link Property} to unbind from
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindBidirectionalPromptTextProperty(Property<String> property);

    //Text Formatter Property

    /**
     * Binds the text formatter property of the text input control to an {@link ObservableValue}.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind to the text formatter property
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindTextFormatterProperty(ObservableValue<? extends TextFormatter<?>> observableValue);

    /**
     * Unbinds the text formatter property of the text input control from its currently bound {@link ObservableValue}.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindTextFormatterProperty();

    /**
     * Creates a bidirectional binding between the text formatter property of the text input control and another {@link Property}.
     *
     * @param property
     *         the other {@link Property} to bind with
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property);

    /**
     * Removes a bidirectional binding between the text formatter property of the text input control and another {@link Property}.
     *
     * @param property
     *         the other {@link Property} to unbind from
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property);

    //TextProperty

    /**
     * Binds the text property of the text input control to an {@link ObservableValue}.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind to the text property
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindTextProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the text property of the text input control from its currently bound {@link ObservableValue}.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindTextProperty();

    /**
     * Creates a bidirectional binding between the text property of the text input control and another {@link Property}.
     *
     * @param property
     *         the other {@link Property} to bind with
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindBidirectionalTextProperty(Property<String> property);

    /**
     * Removes a bidirectional binding between the text property of the text input control and another {@link Property}.
     *
     * @param property
     *         the other {@link Property} to unbind from
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindBidirectionalTextProperty(Property<String> property);

    //Editable Property

    /**
     * Binds the editable property of the text input control to an {@link ObservableValue}.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind to the editable property
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindEditableProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the editable property of the text input control from its currently bound {@link ObservableValue}.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindEditableProperty();

    /**
     * Creates a bidirectional binding between the editable property of the text input control and another {@link Property}.
     *
     * @param property
     *         the other {@link Property} to bind with
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig bindBidirectionalEditableProperty(Property<Boolean> property);

    /**
     * Removes a bidirectional binding between the editable property of the text input control and another {@link Property}.
     *
     * @param property
     *         the other {@link Property} to unbind from
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig unbindBidirectionalEditableProperty(Property<Boolean> property);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the font of the text within the input control.
     *
     * @param value
     *         the new font to be applied
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig setFont(Font value);

    /**
     * Sets the prompt text for the input control. Prompt text is displayed when the input control is empty.
     *
     * @param value
     *         the prompt text to display
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig setPromptText(String value);

    /**
     * Sets the {@link TextFormatter} for the input control. TextFormatters can provide both a filter and a value converter.
     *
     * @param value
     *         the {@link TextFormatter} to set
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig setTextFormatter(TextFormatter<?> value);

    /**
     * Sets the text of the input control.
     *
     * @param value
     *         the text to set in the input control
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig setText(String value);

    /**
     * Sets whether the input control is editable.
     *
     * @param value
     *         {@code true} if the control should be editable, {@code false} otherwise
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig setEditable(boolean value);

    //endregion Set Functions

    //region Text Manipulation Functions
    //*****************************************************************
    // Text Manipulation Functions
    //*****************************************************************

    /**
     * Appends the given text to the end of the control's content.
     *
     * @param text
     *         the text to append
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig appendText(String text);

    /**
     * Inserts the given text at the specified index in the control's content.
     *
     * @param index
     *         the index at which the text should be inserted
     * @param text
     *         the text to insert
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig insertText(int index, String text);

    /**
     * Deletes the text in a given range.
     *
     * @param range
     *         the range of text to delete
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig deleteText(IndexRange range);

    /**
     * Deletes the text between the start and end indices.
     *
     * @param start
     *         the start index of the text to delete
     * @param end
     *         the end index of the text to delete
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig deleteText(int start, int end);

    /**
     * Replaces the text in a given range with the provided text.
     *
     * @param range
     *         the range of text to replace
     * @param text
     *         the text to use as a replacement
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig replaceText(IndexRange range, String text);

    /**
     * Replaces the text between the start and end indices with the provided text.
     *
     * @param start
     *         the start index of the text to replace
     * @param end
     *         the end index of the text to replace
     * @param text
     *         the text to use as a replacement
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig replaceText(int start, int end, String text);

    /**
     * Cuts the current selection to the clipboard.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig cut();

    /**
     * Copies the current selection to the clipboard.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig copy();

    /**
     * Pastes the content from the clipboard at the current caret position.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig paste();

    /**
     * Selects the text backward from the caret position.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectBackward();

    /**
     * Selects the text forward from the caret position.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectForward();

    /**
     * Moves the caret to the previous word.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig previousWord();

    /**
     * Moves the caret to the next word.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig nextWord();

    /**
     * Moves the caret to the end of the next word.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig endOfNextWord();

    /**
     * Selects the text of the previous word relative to the caret.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectPreviousWord();

    /**
     * Selects the text of the next word relative to the caret.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectNextWord();

    /**
     * Selects the text to the end of the next word from the caret position.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectEndOfNextWord();

    /**
     * Selects all text in the control.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectAll();

    /**
     * Moves the caret to the beginning of the text.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig home();

    /**
     * Moves the caret to the end of the text.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig end();

    /**
     * Selects the text from the beginning to the current caret position.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectHome();

    /**
     * Selects the text from the current caret position to the end.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectEnd();

    /**
     * Deletes the character before the current caret position.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig deletePreviousChar();

    /**
     * Deletes the character after the current caret position.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig deleteNextChar();

    /**
     * Moves the caret forward by one character.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig forward();

    /**
     * Moves the caret backward by one character.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig backward();

    /**
     * Positions the caret at the specified index.
     *
     * @param pos
     *         the position to move the caret to
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig positionCaret(int pos);

    /**
     * Selects the text at the specified caret position.
     *
     * @param pos
     *         the position to select
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectPositionCaret(int pos);

    /**
     * Selects the range of text between the given anchor and caret positions.
     *
     * @param anchor
     *         the anchor position of the selection
     * @param caretPosition
     *         the caret position of the selection
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig selectRange(int anchor, int caretPosition);

    /**
     * Extends the current selection to the specified caret position.
     *
     * @param pos
     *         the position to extend the selection to
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig extendSelection(int pos);

    /**
     * Clears the entire text.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig clear();

    /**
     * Deselects any currently selected text.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig deselect();

    /**
     * Replaces the current selection with the given replacement text.
     *
     * @param replacement
     *         the text to replace the current selection with
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig replaceSelection(String replacement);

    /**
     * Undoes the last text edit action.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig undo();

    /**
     * Redoes the last undone text edit action.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig redo();

    /**
     * Commits the current value of a text input as if the user had pressed Enter.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig commitValue();

    /**
     * Cancels the current edit in the text input control.
     *
     * @return this {@link TextInputControlConfig} instance for method chaining
     */
    TextInputControlConfig cancelEdit();

    //endregion Text Manipulation Functions
}
