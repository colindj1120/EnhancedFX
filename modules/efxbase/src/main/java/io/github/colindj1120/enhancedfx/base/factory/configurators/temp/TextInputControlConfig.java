package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.TextInputControlConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.IndexRange;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Font;

public interface TextInputControlConfig<T extends TextInputControlConfigurator<T>> extends ControlConfig<T> {
    @Override
    T getConfigurator();

    @Override
    TextInputControl getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addFontChangeListener(ChangeListener<? super Font> changeListener) {
        getNode().fontProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addFontInvalidationListener(InvalidationListener invalidationListener) {
        getNode().fontProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addPromptTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().promptTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addPromptTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().promptTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        getNode().textFormatterProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textFormatterProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().textProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addLengthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().lengthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLengthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().lengthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().editableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addEditableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().editableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        getNode().selectionProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addSelectionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectionProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().selectedTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectedTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().caretPositionProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().caretPositionProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().anchorProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addAnchorInvalidationListener(InvalidationListener invalidationListener) {
        getNode().anchorProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().undoableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addUndoableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().undoableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().redoableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addRedoableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().redoableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeFontChangeListener(ChangeListener<? super Font> changeListener) {
        getNode().fontProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeFontInvalidationListener(InvalidationListener invalidationListener) {
        getNode().fontProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removePromptTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().promptTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removePromptTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().promptTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        getNode().textFormatterProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textFormatterProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().textProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeLengthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().lengthProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLengthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().lengthProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().editableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeEditableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().editableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        getNode().selectionProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeSelectionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectionProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().selectedTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectedTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().caretPositionProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().caretPositionProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().anchorProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeAnchorInvalidationListener(InvalidationListener invalidationListener) {
        getNode().anchorProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().undoableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeUndoableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().undoableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().redoableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeRedoableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().redoableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Font Property

    default T bindFontProperty(ObservableValue<? extends Font> observableValue) {
        getNode().fontProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindFontProperty() {
        getNode().fontProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalFontProperty(Property<Font> otherProperty) {
        getNode().fontProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalFontProperty(Property<Font> otherProperty) {
        getNode().fontProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Prompt Text Property

    default T bindPromptTextProperty(ObservableValue<? extends String> observableValue) {
        getNode().promptTextProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindPromptTextProperty() {
        getNode().promptTextProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalPromptTextProperty(Property<String> property) {
        getNode().promptTextProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalPromptTextProperty(Property<String> property) {
        getNode().promptTextProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Text Formatter Property

    default T bindTextFormatterProperty(ObservableValue<? extends TextFormatter<?>> observableValue) {
        getNode().textFormatterProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTextFormatterProperty() {
        getNode().textFormatterProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        getNode().textFormatterProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        getNode().textFormatterProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // TextProperty

    default T bindTextProperty(ObservableValue<? extends String> observableValue) {
        getNode().textProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindTextProperty() {
        getNode().textProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalTextProperty(Property<String> property) {
        getNode().textProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalTextProperty(Property<String> property) {
        getNode().textProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Editable Property

    default T bindEditableProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().editableProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindEditableProperty() {
        getNode().editableProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalEditableProperty(Property<Boolean> property) {
        getNode().editableProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    default T unbindBidirectionalEditableProperty(Property<Boolean> property) {
        getNode().editableProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setFont(Font value) {
        getNode().setFont(value);
        return getConfigurator();
    }

    default T setPromptText(String value) {
        getNode().setPromptText(value);
        return getConfigurator();
    }

    default T setTextFormatter(TextFormatter<?> value) {
        getNode().setTextFormatter(value);
        return getConfigurator();
    }

    default T setText(String value) {
        getNode().setText(value);
        return getConfigurator();
    }

    default T setEditable(boolean value) {
        getNode().setEditable(value);
        return getConfigurator();
    }

    //endregion Set Functions

    //region Text Manipulation Functions
    //*****************************************************************
    // Text Manipulation Functions
    //*****************************************************************

    default T appendText(String text) {
        getNode().appendText(text);
        return getConfigurator();
    }

    default T insertText(int index, String text) {
        getNode().insertText(index, text);
        return getConfigurator();
    }

    default T deleteText(IndexRange range) {
        getNode().deleteText(range);
        return getConfigurator();
    }

    default T deleteText(int start, int end) {
        getNode().deleteText(start, end);
        return getConfigurator();
    }

    default T replaceText(IndexRange range, String text) {
        getNode().replaceText(range, text);
        return getConfigurator();
    }

    default T replaceText(int start, int end, String text) {
        getNode().replaceText(start, end, text);
        return getConfigurator();
    }

    default T cut() {
        getNode().cut();
        return getConfigurator();
    }

    default T copy() {
        getNode().copy();
        return getConfigurator();
    }

    default T paste() {
        getNode().paste();
        return getConfigurator();
    }

    default T selectBackward() {
        getNode().selectBackward();
        return getConfigurator();
    }

    default T selectForward() {
        getNode().selectForward();
        return getConfigurator();
    }

    default T previousWord() {
        getNode().previousWord();
        return getConfigurator();
    }

    default T nextWord() {
        getNode().nextWord();
        return getConfigurator();
    }

    default T endOfNextWord() {
        getNode().endOfNextWord();
        return getConfigurator();
    }

    default T selectPreviousWord() {
        getNode().selectPreviousWord();
        return getConfigurator();
    }

    default T selectNextWord() {
        getNode().selectNextWord();
        return getConfigurator();
    }

    default T selectEndOfNextWord() {
        getNode().selectEndOfNextWord();
        return getConfigurator();
    }

    default T selectAll() {
        getNode().selectAll();
        return getConfigurator();
    }

    default T home() {
        return getConfigurator();
    }

    default T end() {
        getNode().end();
        return getConfigurator();
    }

    default T selectHome() {
        getNode().selectHome();
        return getConfigurator();
    }

    default T selectEnd() {
        getNode().selectEnd();
        return getConfigurator();
    }

    default T deletePreviousChar() {
        getNode().deletePreviousChar();
        return getConfigurator();
    }

    default T deleteNextChar() {
        getNode().deleteNextChar();
        return getConfigurator();
    }

    default T forward() {
        getNode().forward();
        return getConfigurator();
    }

    default T backward() {
        getNode().backward();
        return getConfigurator();
    }

    default T positionCaret(int pos) {
        getNode().positionCaret(pos);
        return getConfigurator();
    }

    default T selectPositionCaret(int pos) {
        getNode().selectPositionCaret(pos);
        return getConfigurator();
    }

    default T selectRange(int anchor, int caretPosition) {
        getNode().selectRange(anchor, caretPosition);
        return getConfigurator();
    }

    default T extendSelection(int pos) {
        getNode().extendSelection(pos);
        return getConfigurator();
    }

    default T clear() {
        getNode().clear();
        return getConfigurator();
    }

    default T deselect() {
        getNode().deselect();
        return getConfigurator();
    }

    default T replaceSelection(String replacement) {
        getNode().replaceSelection(replacement);
        return getConfigurator();
    }

    default T undo() {
        getNode().undo();
        return getConfigurator();
    }

    default T redo() {
        getNode().redo();
        return getConfigurator();
    }

    default T commitValue() {
        getNode().commitValue();
        return getConfigurator();
    }

    default T cancelEdit() {
        getNode().cancelEdit();
        return getConfigurator();
    }

    //endregion Text Manipulation Functions
}
