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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxtext.base;

import io.github.colindj1120.enhancedfx.controls.simplecontrol.base.InnerBase;
import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;

/**
 * Defines the core functionality required by text input controls within the EnhancedFX framework, extending the {@link InnerBase} interface.
 *
 * <p>This interface abstracts common behaviors and properties across different types of text input controls, such as {@link TextField} and {@link TextArea},providing a unified API for manipulating text, font,
 * prompt text, and other attributes.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Text manipulation: Get and set the text content of the control.</li>
 *     <li>Font styling: Change the font used within the text input control.</li>
 *     <li>Prompt text: Manage placeholder text displayed when the control is empty.</li>
 *     <li>Text formatter: Utilize a {@link TextFormatter} to format and validate text input.</li>
 *     <li>Editable state: Configure whether the text input is editable by the user.</li>
 *     <li>Selection handling: Get and modify the current text selection.</li>
 *     <li>Clipboard operations: Support cut, copy, paste, and other clipboard actions.</li>
 *     <li>Undo/redo support: Provide undo and redo functionality for text changes.</li>
 *     <li>Tooltip and context menu: Associate tooltips and custom context menus with the control.</li>
 *     <li>Text navigation and manipulation: Methods to navigate and manipulate text programmatically.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * InnerTextInputControl<TextInputControl> myTextInput = // instantiation of an InnerTextInputControl implementation
 * myTextInput.setText("Hello, world!");
 * myTextInput.setFont(Font.font("Arial", 16));
 * myTextInput.setPromptText("Enter something...");
 * myTextInput.setEditable(true);
 * myTextInput.copy();
 * myTextInput.selectAll();
 * myTextInput.undo();
 * }
 * </pre>
 *
 * <p>This example demonstrates basic usage of the {@code InnerTextInputControl} interface to set text content, apply font styling,configure prompt text, and perform various text operations such as selecting
 * all text and undoing the last action.</p>
 *
 * <p>Note: Implementations of {@code InnerTextInputControl} should ensure they properly delegate these operations to the underlying {@link TextInputControl} instance,respecting the control's specific
 * characteristics and constraints.</p>
 *
 * @param <T>
 *         the type of {@link TextInputControl} this interface is meant to support, such as {@link TextField} or {@link TextArea}.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InnerBase
 * @see TextInputControl
 * @see TextField
 * @see TextArea
 */
public interface InnerTextInputControl<T extends TextInputControl> extends InnerBase<T> {
    /*
     * Methods Available:
     *  - ObjectProperty<Font> fontProperty()
     *  - void setFont(Font value)
     *  - Font getFont()
     *  - StringProperty promptTextProperty()
     *  - String getPromptText()
     *  - void setPromptText(String value)
     *  - ObjectProperty<TextFormatter<?>> textFormatterProperty()
     *  - TextFormatter<?> getTextFormatter()
     *  - void setTextFormatter(TextFormatter<?> value)
     *  - String getText()
     *  - void setText(String value)
     *  - StringProperty textProperty()
     *  - int getLength()
     *  - ReadOnlyIntegerProperty lengthProperty()
     *  - boolean isEditable()
     *  - void setEditable(boolean value)
     *  - BooleanProperty editableProperty()
     *  - IndexRange getSelection()
     *  - ReadOnlyObjectProperty<IndexRange> selectionProperty()
     *  - String getSelectedText()
     *  - ReadOnlyStringProperty selectedTextProperty()
     *  - int getAnchor()
     *  - ReadOnlyIntegerProperty anchorProperty()
     *  - int getCaretPosition()
     *  - ReadOnlyIntegerProperty caretPositionProperty()
     *  - boolean isUndoable()
     *  - ReadOnlyBooleanProperty undoableProperty()
     *  - boolean isRedoable()
     *  - ReadOnlyBooleanProperty redoableProperty()
     *  - ObjectProperty<Tooltip> tooltipProperty()
     *  - void setTooltip(Tooltip value)
     *  - Tooltip getTooltip()
     *  - ObjectProperty<ContextMenu> contextMenuProperty()
     *  - void setContextMenu(ContextMenu value)
     *  - ContextMenu getContextMenu()
     *  - String getText(int start, int end)
     *  - void appendText(String text)
     *  - void insertText(int index, String text)
     *  - void deleteText(IndexRange range)
     *  - void deleteText(int start, int end)
     *  - void replaceText(IndexRange range, String text)
     *  - void replaceText(int start, int end, String text)
     *  - void cut()
     *  - void copy()
     *  - void paste()
     *  - void selectBackward()
     *  - void selectForward()
     *  - void previousWord()
     *  - void nextWord()
     *  - void endOfNextWord()
     *  - void selectPreviousWord()
     *  - void selectNextWord()
     *  - void selectEndOfNextWord()
     *  - void selectAll()
     *  - void home()
     *  - void end()
     *  - void selectHome()
     *  - void selectEnd()
     *  - boolean deletePreviousChar()
     *  - boolean deleteNextChar()
     *  - void forward()
     *  - void backward()
     *  - void positionCaret(int pos)
     *  - void selectPositionCaret(int pos)
     *  - void selectRange(int anchor, int caretPosition)
     *  - void extendSelection(int pos)
     *  - void clear()
     *  - void deselect()
     *  - void replaceSelection(String replacement)
     *  - void undo()
     *  - void redo()
     *  - void commitValue()
     *  - void cancelEdit()
     */

    /**
     * Retrieves the font property associated with the text field.
     *
     * @return the font property of the text field.
     */
    default ObjectProperty<Font> fontProperty() {return getInnerControl().fontProperty();}

    /**
     * Sets the font of the text field.
     *
     * @param value
     *         the new font to set for the text field
     */
    default void setFont(Font value) {getInnerControl().setFont(value);}

    /**
     * Gets the font of the text field.
     *
     * @return the font of the text field
     */
    default Font getFont() {return getInnerControl().getFont();}

    /**
     * Returns the prompt text property of the text field.
     *
     * @return the prompt text property of the text field
     */
    default StringProperty promptTextProperty() {return getInnerControl().promptTextProperty();}

    /**
     * Returns the prompt text of the text input field.
     *
     * @return the prompt text of the text input field
     */
    default String getPromptText() {return getInnerControl().getPromptText();}

    /**
     * Sets the prompt text to be displayed in the text field.
     *
     * @param value
     *         the prompt text to be displayed
     */
    default void setPromptText(String value) {getInnerControl().setPromptText(value);}

    /**
     * Gets the textFormatter property of the TextField control. The textFormatter property is used to format and validate the text entered into the TextField.
     *
     * @return The textFormatter property of the TextField control.
     */
    default ObjectProperty<TextFormatter<?>> textFormatterProperty() {return getInnerControl().textFormatterProperty();}

    /**
     * Retrieves the text formatter used by the text field.
     *
     * @return the text formatter being used
     */
    default TextFormatter<?> getTextFormatter() {return getInnerControl().getTextFormatter();}

    /**
     * Sets the text formatter for the text field.
     *
     * @param value
     *         the text formatter to set
     */
    default void setTextFormatter(TextFormatter<?> value) {getInnerControl().setTextFormatter(value);}

    /**
     * Retrieves the text value from the text field.
     *
     * @return The text value from the text field.
     */
    default String getText() {return getInnerControl().getText();}

    /**
     * Sets the text of the text field.
     *
     * @param value
     *         the text to be set for the text field
     */
    default void setText(String value) {getInnerControl().setText(value);}

    /**
     * Returns the StringProperty associated with the text of the TextField.
     *
     * @return the StringProperty object representing the text of the TextField.
     */
    default StringProperty textProperty() {
        return getInnerControl().textProperty();
    }

    /**
     * Gets the length of the text in the TextField.
     *
     * @return the length of the text
     */
    default int getLength() {return getInnerControl().getLength();}

    /**
     * Retrieves the read-only integer property representing the length of the text in the textField.
     *
     * @return The read-only integer property representing the length of the text in the textField.
     */
    default ReadOnlyIntegerProperty lengthProperty() {return getInnerControl().lengthProperty();}

    /**
     * Returns whether the text field is editable or not.
     *
     * @return true if the text field is editable, false otherwise
     */
    default boolean isEditable() {return getInnerControl().isEditable();}

    /**
     * Sets whether the text field is editable or not.
     *
     * @param value
     *         true if the text field should be editable, false otherwise
     */
    default void setEditable(boolean value) {getInnerControl().setEditable(value);}

    /**
     * Returns the editable property of the text field.
     *
     * @return the editable property of the text field
     */
    default BooleanProperty editableProperty() {return getInnerControl().editableProperty();}

    /**
     * Returns the range of characters that are currently selected in the text field.
     *
     * @return The range of characters that are currently selected in the text field.
     */
    default IndexRange getSelection() {return getInnerControl().getSelection();}

    /**
     * Returns the read-only object property representing the current selection range within the text field.
     *
     * @return the read-only object property representing the selection range
     */
    default ReadOnlyObjectProperty<IndexRange> selectionProperty() {return getInnerControl().selectionProperty();}

    /**
     * Retrieves the selected text from a text field.
     *
     * @return The selected text in the text field.
     */
    default String getSelectedText() {return getInnerControl().getSelectedText();}

    /**
     * Returns the read-only property that represents the currently selected text of the text field.
     *
     * @return the read-only property representing the currently selected text of the text field
     */
    default ReadOnlyStringProperty selectedTextProperty() {return getInnerControl().selectedTextProperty();}

    /**
     * Returns the anchor value of the textField. The anchor value determines the position within the text field where the new text is inserted.
     *
     * @return The anchor value of the textField, which represents the insertion position.
     */
    default int getAnchor() {return getInnerControl().getAnchor();}

    /**
     * Returns a {@link ReadOnlyIntegerProperty} representing the anchor value of a text field.
     *
     * @return the anchor property of the text field
     */
    default ReadOnlyIntegerProperty anchorProperty() {return getInnerControl().anchorProperty();}

    /**
     * Returns the current caret position in the text field.
     *
     * @return The current caret position.
     */
    default int getCaretPosition() {return getInnerControl().getCaretPosition();}

    /**
     * Returns the read-only integer property representing the caret position in the text field. The caret position specifies the index of the character where the caret is placed.
     *
     * @return the read-only integer property representing the caret position
     */
    default ReadOnlyIntegerProperty caretPositionProperty() {return getInnerControl().caretPositionProperty();}

    /**
     * Returns whether the current text field is undoable.
     *
     * @return {@code true} if the text field is undoable, {@code false} otherwise
     */
    default boolean isUndoable() {return getInnerControl().isUndoable();}

    /**
     * Retrieves the read-only boolean property indicating whether an undo operation can be performed.
     *
     * @return The read-only boolean property indicating whether an undo operation can be performed.
     */
    default ReadOnlyBooleanProperty undoableProperty() {return getInnerControl().undoableProperty();}

    /**
     * Checks if the text field supports redo operation.
     *
     * @return {@code true} if the text field supports redo operation, {@code false} otherwise.
     */
    default boolean isRedoable() {return getInnerControl().isRedoable();}

    /**
     * Returns the read-only boolean property representing the redoable state of the underlying text field.
     *
     * @return the redoable property
     */
    default ReadOnlyBooleanProperty redoableProperty() {return getInnerControl().redoableProperty();}

    /**
     * Retrieves the tooltip property of the text field.
     *
     * @return The ObjectProperty representing the tooltip.
     */
    default ObjectProperty<Tooltip> tooltipProperty() {return getInnerControl().tooltipProperty();}

    /**
     * Sets the tooltip for the TextField.
     *
     * @param value
     *         the Tooltip to be set
     */
    default void setTooltip(Tooltip value) {getInnerControl().setTooltip(value);}

    /**
     * Retrieves the tooltip associated with this method.
     *
     * @return the tooltip of the current text field
     */
    default Tooltip getTooltip() {return getInnerControl().getTooltip();}

    /**
     * Returns the {@code ObjectProperty} representing the context menu associated with this method.
     *
     * @return the {@code ObjectProperty} representing the context menu associated with this method.
     */
    default ObjectProperty<ContextMenu> contextMenuProperty() {return getInnerControl().contextMenuProperty();}

    /**
     * Sets the context menu for the text field.
     *
     * @param value
     *         the context menu to be set for the text field
     */
    default void setContextMenu(ContextMenu value) {getInnerControl().setContextMenu(value);}

    /**
     * Returns the context menu associated with this method.
     *
     * @return the context menu
     */
    default ContextMenu getContextMenu() {return getInnerControl().getContextMenu();}

    /**
     * Retrieves the text within the specified range from the text field.
     *
     * @param start
     *         the index of the first character to retrieve (inclusive)
     * @param end
     *         the index of the last character to retrieve (exclusive)
     *
     * @return the text within the specified range
     */
    default String getText(int start, int end) {return getInnerControl().getText(start, end);}

    /**
     * Appends the given text to the existing text in the text field.
     *
     * @param text
     *         the text to be appended to the text field
     */
    default void appendText(String text) {getInnerControl().appendText(text);}

    /**
     * Inserts the specified text at the given index in the text field.
     *
     * @param index
     *         the index at which the text is to be inserted
     * @param text
     *         the text to be inserted
     */
    default void insertText(int index, String text) {getInnerControl().insertText(index, text);}

    /**
     * Deletes the text within the specified range in a text field.
     *
     * @param range
     *         the range of text to be deleted
     */
    default void deleteText(IndexRange range) {getInnerControl().deleteText(range);}

    /**
     * Deletes the text in the specified range from the textField.
     *
     * @param start
     *         the starting index of the range (inclusive)
     * @param end
     *         the ending index of the range (exclusive)
     */
    default void deleteText(int start, int end) {getInnerControl().deleteText(start, end);}

    /**
     * Replaces the text within the specified range of the text field with the given text.
     *
     * @param range
     *         the range of text to be replaced
     * @param text
     *         the text to replace with
     */
    default void replaceText(IndexRange range, String text) {getInnerControl().replaceText(range, text);}

    /**
     * Replaces the text in the TextField from the specified start index to the specified end index with the given text.
     *
     * @param start
     *         the index of the first character to be replaced
     * @param end
     *         the index of the last character to be replaced (exclusive)
     * @param text
     *         the text to be inserted at the specified position
     */
    default void replaceText(final int start, final int end, final String text) {getInnerControl().replaceText(start, end, text);}

    /**
     * Cuts the currently selected text from the text field.
     */
    default void cut() {getInnerControl().cut();}

    /**
     * Copies the selected text in the text field to the system clipboard.
     */
    default void copy() {getInnerControl().copy();}

    /**
     * Pastes the current contents of the clipboard into the text field.
     */
    default void paste() {getInnerControl().paste();}

    /**
     * Selects the text in the text field starting from the current caret position and moving backwards.
     *
     * <p>The selected text will be in the highlighted or highlighted in reverse order depending on the selected theme. Calling this method will update the highlight of the text field.</p>
     *
     * @throws UnsupportedOperationException
     *         if the text field does not support selecting text in reverse order.
     */
    default void selectBackward() {getInnerControl().selectBackward();}

    /**
     * Selects text in the forward direction from the current cursor position.
     */
    default void selectForward() {getInnerControl().selectForward();}

    /**
     * Moves the cursor to the previous word in the text field.
     */
    default void previousWord() {getInnerControl().previousWord();}

    /**
     * Moves the cursor to the next word in the text field.
     */
    default void nextWord() {getInnerControl().nextWord();}

    /**
     * Moves the cursor to the end of the next word in the text field.
     *
     * <p>This method should be called when the user wants to move the cursor to the end of the next word in the text field. After calling this method, the cursor will be positioned at the end of the next
     * word.</p>
     */
    default void endOfNextWord() {getInnerControl().endOfNextWord();}

    /**
     * Selects the previous word in the text field.
     */
    default void selectPreviousWord() {getInnerControl().selectPreviousWord();}

    /**
     * Selects the next word in the text field.
     */
    default void selectNextWord() {getInnerControl().selectNextWord();}

    /**
     * Selects the end of the next word in the text field.
     */
    default void selectEndOfNextWord() {getInnerControl().selectEndOfNextWord();}

    /**
     * Selects all the text in the text field.
     */
    default void selectAll() {getInnerControl().selectAll();}

    /**
     * Method to navigate back to the home page.
     */
    default void home() {getInnerControl().home();}

    /**
     * Ends the text input by moving the cursor to the end of the input field.
     */
    default void end() {getInnerControl().end();}

    /**
     * Selects the home position in the text field.
     */
    default void selectHome() {getInnerControl().selectHome();}

    /**
     * Selects the end of the text in the text field.
     */
    default void selectEnd() {getInnerControl().selectEnd();}

    /**
     * Deletes the previous character in the text field.
     *
     * @return true if the previous character was deleted, false otherwise.
     */
    default boolean deletePreviousChar() {return getInnerControl().deletePreviousChar();}

    /**
     * Deletes the next character in the text field.
     *
     * @return {@code true} if the next character was successfully deleted, {@code false} otherwise.
     */
    default boolean deleteNextChar() {return getInnerControl().deleteNextChar();}

    /**
     * This method moves the caret position of the associated text field one character forward. If the caret is already at the end of the text, this method has no effect.
     */
    default void forward() {getInnerControl().forward();}

    /**
     * Moves the cursor in the text field one position backwards.
     */
    default void backward() {getInnerControl().backward();}

    /**
     * Positions the caret at the specified position in the text field.
     *
     * @param pos
     *         the position to place the caret at
     */
    default void positionCaret(int pos) {getInnerControl().positionCaret(pos);}

    /**
     * Selects the position caret in the text field.
     *
     * @param pos
     *         the desired position to set the caret to
     */
    default void selectPositionCaret(int pos) {getInnerControl().selectPositionCaret(pos);}

    /**
     * Method to select range of text in the text field.
     *
     * @param anchor
     *         the starting position of the text range to be selected
     * @param caretPosition
     *         the ending position of the text range to be selected
     */
    default void selectRange(int anchor, int caretPosition) {getInnerControl().selectRange(anchor, caretPosition);}

    /**
     * Extend the selection in the text field from the current caret position to the specified position.
     *
     * @param pos
     *         the position to extend the selection to
     */
    default void extendSelection(int pos) {getInnerControl().extendSelection(pos);}

    /**
     * Clears the contents of the text field.
     */
    default void clear() {getInnerControl().clear();}

    /**
     * Deselects any selected text in the text field.
     */
    default void deselect() {getInnerControl().deselect();}

    /**
     * Replaces the currently selected text in the text field with the given replacement text.
     *
     * @param replacement
     *         the text to replace the currently selected text with
     */
    default void replaceSelection(String replacement) {getInnerControl().replaceSelection(replacement);}

    /**
     * Undoes the last action performed in the text field. This method calls the `undo()` method of the `textField` object to reverse the last action made by the user.
     * <p>
     * Note: The `undo()` method is specific to the `textField` object used in the implementation and may behave differently depending on the context and implementation details.
     */
    default void undo() {getInnerControl().undo();}

    /**
     * Redoes the last action undone in the text field.
     */
    default void redo() {getInnerControl().redo();}

    /**
     * Commits the value entered in the text field.
     */
    default void commitValue() {getInnerControl().commitValue();}

    /**
     * Cancels the current edit operation in the text field.
     */
    default void cancelEdit() {getInnerControl().cancelEdit();}
}
