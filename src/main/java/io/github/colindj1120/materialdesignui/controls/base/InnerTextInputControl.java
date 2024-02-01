/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of MaterialDesignUI (https://github.com/colindj1120/MaterialDesignUI).
 *
 * MaterialDesignUI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialDesignUI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialDesignUI.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.materialdesignui.controls.base;

import javafx.beans.property.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;

/**
 * {@code InnerTextInputControl} is an interface that defines the essential functionalities of a {@link TextInputControl} within the context of a larger text control component, such as a custom text
 * field or text area in JavaFX. This interface extends {@code InnerTextControl} and is specifically designed to enhance and extend the capabilities of standard JavaFX text input controls by adding
 * additional utility methods and properties.
 *
 * <p>Implementations of this interface are responsible for providing methods to interact with the
 * underlying {@code TextInputControl}, such as getting and setting text, handling font properties, managing prompt text, and dealing with text formatting and selection.</p>
 *
 * <p>Key functionalities provided by this interface include:</p>
 * <ul>
 *   <li>Font manipulation: Get, set, and observe changes to the font of the text input control.</li>
 *   <li>Prompt text management: Set and retrieve the prompt text displayed in the control.</li>
 *   <li>Text handling: Methods for getting and setting the text, including text property bindings.</li>
 *   <li>Editing and selection: Provide capabilities for text selection, editing, and formatting.</li>
 *   <li>Undo/Redo operations: Support for undoing and redoing text changes.</li>
 *   <li>Tooltip and context menu support: Methods for setting and retrieving tooltips and context menus.</li>
 * </ul>
 *
 * <p>This interface is particularly useful in scenarios where a {@code TextInputControl} needs to be
 * embedded within a larger custom component, allowing the outer component to control and interact
 * with the text input functionalities effectively.</p>
 *
 * @param <T>
 *         the type of {@link TextInputControl} this interface is meant to support, such as {@link TextField} or {@link TextArea}.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InnerTextControl
 * @see TextInputControl
 * @see TextField
 * @see TextArea
 */
public interface InnerTextInputControl<T extends TextInputControl> extends InnerTextControl<TextInputControl> {
    /**
     * Retrieves the value of the field.
     *
     * @return the value of the field
     */
    T getField();

    /**
     * Retrieves the font property associated with the text field.
     *
     * @return the font property of the text field.
     */
    default ObjectProperty<Font> fontProperty() {return getField().fontProperty();}

    /**
     * Sets the font of the text field.
     *
     * @param value
     *         the new font to set for the text field
     */
    default void setFont(Font value) {getField().setFont(value);}

    /**
     * Gets the font of the text field.
     *
     * @return the font of the text field
     */
    default Font getFont() {return getField().getFont();}

    /**
     * Returns the prompt text property of the text field.
     *
     * @return the prompt text property of the text field
     */
    default StringProperty promptTextProperty() {return getField().promptTextProperty();}

    /**
     * Returns the prompt text of the text input field.
     *
     * @return the prompt text of the text input field
     */
    default String getPromptText() {return getField().getPromptText();}

    /**
     * Sets the prompt text to be displayed in the text field.
     *
     * @param value
     *         the prompt text to be displayed
     */
    default void setPromptText(String value) {getField().setPromptText(value);}

    /**
     * Gets the textFormatter property of the TextField control. The textFormatter property is used to format and validate the text entered into the TextField.
     *
     * @return The textFormatter property of the TextField control.
     */
    default ObjectProperty<TextFormatter<?>> textFormatterProperty() {return getField().textFormatterProperty();}

    /**
     * Retrieves the text formatter used by the text field.
     *
     * @return the text formatter being used
     */
    default TextFormatter<?> getTextFormatter() {return getField().getTextFormatter();}

    /**
     * Sets the text formatter for the text field.
     *
     * @param value
     *         the text formatter to set
     */
    default void setTextFormatter(TextFormatter<?> value) {getField().setTextFormatter(value);}

    /**
     * Retrieves the text value from the text field.
     *
     * @return The text value from the text field.
     */
    default String getText() {return getField().getText();}

    /**
     * Sets the text of the text field.
     *
     * @param value
     *         the text to be set for the text field
     */
    default void setText(String value) {getField().setText(value);}

    /**
     * Returns the StringProperty associated with the text of the TextField.
     *
     * @return the StringProperty object representing the text of the TextField.
     */
    default StringProperty textProperty() {return getField().textProperty();}

    /**
     * Gets the length of the text in the TextField.
     *
     * @return the length of the text
     */
    default int getLength() {return getField().getLength();}

    /**
     * Retrieves the read-only integer property representing the length of the text in the textField.
     *
     * @return The read-only integer property representing the length of the text in the textField.
     */
    default ReadOnlyIntegerProperty lengthProperty() {return getField().lengthProperty();}

    /**
     * Returns whether the text field is editable or not.
     *
     * @return true if the text field is editable, false otherwise
     */
    default boolean isEditable() {return getField().isEditable();}

    /**
     * Sets whether the text field is editable or not.
     *
     * @param value
     *         true if the text field should be editable, false otherwise
     */
    default void setEditable(boolean value) {getField().setEditable(value);}

    /**
     * Returns the editable property of the text field.
     *
     * @return the editable property of the text field
     */
    default BooleanProperty editableProperty() {return getField().editableProperty();}

    /**
     * Returns the range of characters that are currently selected in the text field.
     *
     * @return The range of characters that are currently selected in the text field.
     */
    default IndexRange getSelection() {return getField().getSelection();}

    /**
     * Returns the read-only object property representing the current selection range within the text field.
     *
     * @return the read-only object property representing the selection range
     */
    default ReadOnlyObjectProperty<IndexRange> selectionProperty() {return getField().selectionProperty();}

    /**
     * Retrieves the selected text from a text field.
     *
     * @return The selected text in the text field.
     */
    default String getSelectedText() {return getField().getSelectedText();}

    /**
     * Returns the read-only property that represents the currently selected text of the text field.
     *
     * @return the read-only property representing the currently selected text of the text field
     */
    default ReadOnlyStringProperty selectedTextProperty() {return getField().selectedTextProperty();}

    /**
     * Returns the anchor value of the textField. The anchor value determines the position within the text field where new text is inserted.
     *
     * @return The anchor value of the textField, which represents the insertion position.
     */
    default int getAnchor() {return getField().getAnchor();}

    /**
     * Returns a {@link ReadOnlyIntegerProperty} representing the anchor value of a text field.
     *
     * @return the anchor property of the text field
     */
    default ReadOnlyIntegerProperty anchorProperty() {return getField().anchorProperty();}

    /**
     * Returns the current caret position in the text field.
     *
     * @return The current caret position.
     */
    default int getCaretPosition() {return getField().getCaretPosition();}

    /**
     * Returns the read-only integer property representing the caret position in the text field. The caret position specifies the index of the character where the caret is placed.
     *
     * @return the read-only integer property representing the caret position
     */
    default ReadOnlyIntegerProperty caretPositionProperty() {return getField().caretPositionProperty();}

    /**
     * Returns whether the current text field is undoable.
     *
     * @return {@code true} if the text field is undoable, {@code false} otherwise
     */
    default boolean isUndoable() {return getField().isUndoable();}

    /**
     * Retrieves the read-only boolean property indicating whether an undo operation can be performed.
     *
     * @return The read-only boolean property indicating whether an undo operation can be performed.
     */
    default ReadOnlyBooleanProperty undoableProperty() {return getField().undoableProperty();}

    /**
     * Checks if the text field supports redo operation.
     *
     * @return {@code true} if the text field supports redo operation, {@code false} otherwise.
     */
    default boolean isRedoable() {return getField().isRedoable();}

    /**
     * Returns the read-only boolean property representing the redoable state of the underlying text field.
     *
     * @return the redoable property
     */
    default ReadOnlyBooleanProperty redoableProperty() {return getField().redoableProperty();}

    /**
     * Retrieves the tooltip property of the text field.
     *
     * @return The ObjectProperty representing the tooltip.
     */
    default ObjectProperty<Tooltip> tooltipProperty() {return getField().tooltipProperty();}

    /**
     * Sets the tooltip for the TextField.
     *
     * @param value
     *         the Tooltip to be set
     */
    default void setTooltip(Tooltip value) {getField().setTooltip(value);}

    /**
     * Retrieves the tooltip associated with this method.
     *
     * @return the tooltip of the current text field
     */
    default Tooltip getTooltip() {return getField().getTooltip();}

    /**
     * Returns the {@code ObjectProperty} representing the context menu associated with this method.
     *
     * @return the {@code ObjectProperty} representing the context menu associated with this method.
     */
    default ObjectProperty<ContextMenu> contextMenuProperty() {return getField().contextMenuProperty();}

    /**
     * Sets the context menu for the text field.
     *
     * @param value
     *         the context menu to be set for the text field
     */
    default void setContextMenu(ContextMenu value) {getField().setContextMenu(value);}

    /**
     * Returns the context menu associated with this method.
     *
     * @return the context menu
     */
    default ContextMenu getContextMenu() {return getField().getContextMenu();}

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
    default String getText(int start, int end) {return getField().getText(start, end);}

    /**
     * Appends the given text to the existing text in the text field.
     *
     * @param text
     *         the text to be appended to the text field
     */
    default void appendText(String text) {getField().appendText(text);}

    /**
     * Inserts the specified text at the given index in the text field.
     *
     * @param index
     *         the index at which the text is to be inserted
     * @param text
     *         the text to be inserted
     */
    default void insertText(int index, String text) {getField().insertText(index, text);}

    /**
     * Deletes the text within the specified range in a text field.
     *
     * @param range
     *         the range of text to be deleted
     */
    default void deleteText(IndexRange range) {getField().deleteText(range);}

    /**
     * Deletes the text in the specified range from the textField.
     *
     * @param start
     *         the starting index of the range (inclusive)
     * @param end
     *         the ending index of the range (exclusive)
     */
    default void deleteText(int start, int end) {getField().deleteText(start, end);}

    /**
     * Replaces the text within the specified range of the text field with the given text.
     *
     * @param range
     *         the range of text to be replaced
     * @param text
     *         the text to replace with
     */
    default void replaceText(IndexRange range, String text) {getField().replaceText(range, text);}

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
    default void replaceText(final int start, final int end, final String text) {getField().replaceText(start, end, text);}

    /**
     * Cuts the currently selected text from the text field.
     */
    default void cut() {getField().cut();}

    /**
     * Copies the selected text in the text field to the system clipboard.
     */
    default void copy() {getField().copy();}

    /**
     * Pastes the current contents of the clipboard into the text field.
     */
    default void paste() {getField().paste();}

    /**
     * Selects the text in the text field starting from the current caret position and moving backwards. The selected text will be in the highlighted or highlighted in reverse order depending on the
     * selected theme. Calling this method will update the highlight of the text field.
     *
     * @throws UnsupportedOperationException
     *         if the text field does not support selecting text in reverse order.
     */
    default void selectBackward() {getField().selectBackward();}

    /**
     * Selects text in the forward direction from the current cursor position.
     */
    default void selectForward() {getField().selectForward();}

    /**
     * Moves the cursor to the previous word in the text field.
     */
    default void previousWord() {getField().previousWord();}

    /**
     * Moves the cursor to the next word in the text field.
     */
    default void nextWord() {getField().nextWord();}

    /**
     * Moves the cursor to the end of the next word in the text field. This method should be called when the user wants to move the cursor to the end of the next word in the text field. After calling
     * this method, the cursor will be positioned at the end of the next word.
     */
    default void endOfNextWord() {getField().endOfNextWord();}

    /**
     * Selects the previous word in the text field.
     */
    default void selectPreviousWord() {getField().selectPreviousWord();}

    /**
     * Selects the next word in the text field.
     */
    default void selectNextWord() {getField().selectNextWord();}

    /**
     * Selects the end of the next word in the text field.
     */
    default void selectEndOfNextWord() {getField().selectEndOfNextWord();}

    /**
     * Selects all the text in the text field.
     */
    default void selectAll() {getField().selectAll();}

    /**
     * Method to navigate back to the home page.
     */
    default void home() {getField().home();}

    /**
     * Ends the text input by moving the cursor to the end of the input field.
     */
    default void end() {getField().end();}

    /**
     * Selects the home position in the text field.
     */
    default void selectHome() {getField().selectHome();}

    /**
     * Selects the end of the text in the text field.
     */
    default void selectEnd() {getField().selectEnd();}

    /**
     * Deletes the previous character in the text field.
     *
     * @return true if the previous character was deleted, false otherwise.
     */
    default boolean deletePreviousChar() {return getField().deletePreviousChar();}

    /**
     * Deletes the next character in the text field.
     *
     * @return {@code true} if the next character was successfully deleted, {@code false} otherwise.
     */
    default boolean deleteNextChar() {return getField().deleteNextChar();}

    /**
     * This method moves the caret position of the associated text field one character forward. If the caret is already at the end of the text, this method has no effect.
     */
    default void forward() {getField().forward();}

    /**
     * Moves the cursor in the text field one position backwards.
     */
    default void backward() {getField().backward();}

    /**
     * Positions the caret at the specified position in the text field.
     *
     * @param pos
     *         the position to place the caret at
     */
    default void positionCaret(int pos) {getField().positionCaret(pos);}

    /**
     * Selects the position caret in the text field.
     *
     * @param pos
     *         the desired position to set the caret to
     */
    default void selectPositionCaret(int pos) {getField().selectPositionCaret(pos);}

    /**
     * Method to select range of text in the text field.
     *
     * @param anchor
     *         the starting position of the text range to be selected
     * @param caretPosition
     *         the ending position of the text range to be selected
     */
    default void selectRange(int anchor, int caretPosition) {getField().selectRange(anchor, caretPosition);}

    /**
     * Extend the selection in the text field from the current caret position to the specified position.
     *
     * @param pos
     *         the position to extend the selection to
     */
    default void extendSelection(int pos) {getField().extendSelection(pos);}

    /**
     * Clears the contents of the text field.
     */
    default void clear() {getField().clear();}

    /**
     * Deselects any selected text in the text field.
     */
    default void deselect() {getField().deselect();}

    /**
     * Replaces the currently selected text in the text field with the given replacement text.
     *
     * @param replacement
     *         the text to replace the currently selected text with
     */
    default void replaceSelection(String replacement) {getField().replaceSelection(replacement);}

    /**
     * Undoes the last action performed in the text field. This method calls the `undo()` method of the `textField` object to reverse the last action made by the user.
     * <p>
     * Note: The `undo()` method is specific to the `textField` object used in the implementation and may behave differently depending on the context and implementation details.
     */
    default void undo() {getField().undo();}

    /**
     * Redoes the last action undone in the text field.
     */
    default void redo() {getField().redo();}

    /**
     * Commits the value entered in the text field.
     */
    default void commitValue() {getField().commitValue();}

    /**
     * Cancels the current edit operation in the text field.
     */
    default void cancelEdit() {getField().cancelEdit();}
}
