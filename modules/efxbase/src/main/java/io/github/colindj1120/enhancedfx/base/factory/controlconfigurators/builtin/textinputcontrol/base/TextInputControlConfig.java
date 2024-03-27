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
package io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.textinputcontrol.base;

import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.base.ConfiguratorBase;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.builtin.control.base.ControlConfig;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.text.Font;

/**
 * Enables fluent configuration of {@link TextInputControl} components in JavaFX, such as {@link TextField} and {@link TextArea}, through a rich set of methods for customizing behavior, appearance, and event
 * handling.
 *
 * <p>This interface extends from {@link ControlConfig}, providing text input-specific functionalities including text manipulation, property binding, and listener registration for properties like the text,
 * font, prompt text, and more.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *   <li>Configuring text input properties such as font, prompt text, editable status, and text formatter.</li>
 *   <li>Adding, removing, and binding listeners for various text input control properties to enable dynamic UI interactions.</li>
 *   <li>Performing text manipulations like append, insert, delete, and replace operations programmatically.</li>
 *   <li>Controlling selection and caret position within the text content for customized text interaction.</li>
 *   <li>Enabling undo and redo operations within text input controls.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * TextField textField = new TextField();
 * TextInputControlConfigurator.configure(textField)
 *     .setFont(Font.font("Arial", FontWeight.BOLD, 12))
 *     .setPromptText("Enter your name")
 *     .addTextChangeListener((obs, oldVal, newVal) -> System.out.println("Text Changed: " + newVal))
 *     .setTextFormatter(new TextFormatter<>(change -> change.getControlNewText().length() <= 10 ? change : null))
 *     .setEditable(true);
 * }</pre>
 *
 * <p>This example demonstrates configuring a {@link TextField} with a custom font, prompt text, a listener for text changes, a text formatter to limit input length, and setting the editable property.</p>
 *
 * <p>This interface is designed to be part of a configurator pattern, where each method returns the configurator instance (`T`), facilitating method chaining and providing a clean, fluent API for JavaFX UI
 * development.</p>
 *
 * @param <T>
 *         The type of the configurator extending {@code ConfiguratorBase}, facilitating fluent API style configurations
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see TextInputControl
 * @see ControlConfig
 * @see TextField
 * @see TextArea
 */
@SuppressWarnings("UnusedReturnValue")
public interface TextInputControlConfig<T extends ConfiguratorBase> extends ControlConfig<T> {
    /*
     * Methods Available:
     *  - TextInputControl getNode()
     *
     * Add Listener Functions
     *  - T addFontChangeListener(ChangeListener<? super Font> changeListener)
     *  - T addFontInvalidationListener(InvalidationListener invalidationListener)
     *  - T addPromptTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T addPromptTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener)
     *  - T addTextFormatterInvalidationListener(InvalidationListener invalidationListener)
     *  - T addTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T addTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T addLengthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addLengthInvalidationListener(InvalidationListener invalidationListener)
     *  - T addEditableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addEditableInvalidationListener(InvalidationListener invalidationListener)
     *  - T addSelectionChangeListener(ChangeListener<? super IndexRange> changeListener)
     *  - T addSelectionInvalidationListener(InvalidationListener invalidationListener)
     *  - T addSelectedTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T addSelectedTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T addCaretPositionChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addCaretPositionInvalidationListener(InvalidationListener invalidationListener)
     *  - T addAnchorChangeListener(ChangeListener<? super Number> changeListener)
     *  - T addAnchorInvalidationListener(InvalidationListener invalidationListener)
     *  - T addUndoableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addUndoableInvalidationListener(InvalidationListener invalidationListener)
     *  - T addRedoableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T addRedoableInvalidationListener(InvalidationListener invalidationListener)
     *
     * Remove Listener Functions
     *  - T removeFontChangeListener(ChangeListener<? super Font> changeListener)
     *  - T removeFontInvalidationListener(InvalidationListener invalidationListener)
     *  - T removePromptTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T removePromptTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener)
     *  - T removeTextFormatterInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeLengthChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeLengthInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeEditableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeEditableInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeSelectionChangeListener(ChangeListener<? super IndexRange> changeListener)
     *  - T removeSelectionInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeSelectedTextChangeListener(ChangeListener<? super String> changeListener)
     *  - T removeSelectedTextInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeCaretPositionChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeCaretPositionInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeAnchorChangeListener(ChangeListener<? super Number> changeListener)
     *  - T removeAnchorInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeUndoableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeUndoableInvalidationListener(InvalidationListener invalidationListener)
     *  - T removeRedoableChangeListener(ChangeListener<? super Boolean> changeListener)
     *  - T removeRedoableInvalidationListener(InvalidationListener invalidationListener)
     *
     * Binding Functions
     *  - T bindFontProperty(ObservableValue<? extends Font> observableValue)
     *  - T unbindFontProperty()
     *  - T bindBidirectionalFontProperty(Property<Font> otherProperty)
     *  - T unbindBidirectionalFontProperty(Property<Font> otherProperty)
     *  - T bindPromptTextProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindPromptTextProperty()
     *  - T bindBidirectionalPromptTextProperty(Property<String> property)
     *  - T unbindBidirectionalPromptTextProperty(Property<String> property)
     *  - T bindTextFormatterProperty(ObservableValue<? extends TextFormatter<?>> observableValue)
     *  - T unbindTextFormatterProperty()
     *  - T bindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property)
     *  - T unbindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property)
     *  - T bindTextProperty(ObservableValue<? extends String> observableValue)
     *  - T unbindTextProperty()
     *  - T bindBidirectionalTextProperty(Property<String> property)
     *  - T unbindBidirectionalTextProperty(Property<String> property)
     *  - T bindEditableProperty(ObservableValue<? extends Boolean> observableValue)
     *  - T unbindEditableProperty()
     *  - T bindBidirectionalEditableProperty(Property<Boolean> property)
     *  - T unbindBidirectionalEditableProperty(Property<Boolean> property)
     *
     * Set Functions
     *  - T setFont(Font value)
     *  - T setPromptText(String value)
     *  - T setTextFormatter(TextFormatter<?> value)
     *  - T setText(String value)
     *  - T setEditable(boolean value)
     */

    /**
     * Returns the {@link TextInputControl} node associated with this configurator.
     *
     * <p>This method provides access to the text input control that is being configured, offering a foundation for further customization and functionality enhancement.</p>
     *
     * @return The current {@link TextInputControl} associated with the current configurator instance
     */
    @Override
    TextInputControl getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} to listen for changes to the font property. This listener is notified whenever the font of the text input control changes.
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
     * Adds an {@link InvalidationListener} to listen for changes to the font property. This listener is notified whenever the font of the text input control changes.
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
     * Adds a {@link ChangeListener} to listen for changes to the prompt text property. This listener is notified whenever the prompt text of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPromptTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().promptTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the prompt text property. This listener is notified whenever the prompt text of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addPromptTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().promptTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the text formatter property. This listener is notified whenever the text formatter of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        getNode().textFormatterProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the text formatter property. This listener is notified whenever the text formatter of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textFormatterProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the text property. This listener is notified whenever the text of the text input control changes.
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
     * Adds an {@link InvalidationListener} to listen for changes to the text property. This listener is notified whenever the text of the text input control changes.
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
     * Adds a {@link ChangeListener} to listen for changes to the length property. This listener is notified whenever the length of the text in the input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLengthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().lengthProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the length property. This listener is notified whenever the length of the text in the input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addLengthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().lengthProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the editable property. This listener is notified whenever the editable status of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().editableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the editable property. This listener is notified whenever the editable status of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addEditableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().editableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the selection property. This listener is notified whenever the selection of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        getNode().selectionProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the selection property. This listener is notified whenever the selection of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSelectionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectionProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the selected text property. This listener is notified whenever the selected text of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().selectedTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the selected text property. This listener is notified whenever the selected text of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectedTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the caret position property. This listener is notified whenever the caret position of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().caretPositionProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the caret position property. This listener is notified whenever the caret position of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().caretPositionProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the anchor property. This listener is notified whenever the anchor of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().anchorProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the anchor property. This listener is notified whenever the anchor of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addAnchorInvalidationListener(InvalidationListener invalidationListener) {
        getNode().anchorProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the undoable property. This listener is notified whenever the undoable status of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().undoableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the undoable property. This listener is notified whenever the undoable status of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addUndoableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().undoableProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Adds a {@link ChangeListener} to listen for changes to the redoable property. This listener is notified whenever the redoable status of the text input control changes.
     *
     * @param changeListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T addRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().redoableProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    /**
     * Adds an {@link InvalidationListener} to listen for changes to the redoable property. This listener is notified whenever the redoable status of the text input control changes.
     *
     * @param invalidationListener
     *         The listener to add.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
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

    /**
     * Removes a {@link ChangeListener} from listening for changes to the font property.
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
     * Removes an {@link InvalidationListener} from listening for changes to the font property.
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
     * Removes a {@link ChangeListener} from listening for changes to the prompt text property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePromptTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().promptTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the prompt text property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removePromptTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().promptTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the text formatter property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextFormatterChangeListener(ChangeListener<? super TextFormatter<?>> changeListener) {
        getNode().textFormatterProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the text formatter property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeTextFormatterInvalidationListener(InvalidationListener invalidationListener) {
        getNode().textFormatterProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the text property.
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
     * Removes an {@link InvalidationListener} from listening for changes to the text property.
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
     * Removes a {@link ChangeListener} from listening for changes to the length property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLengthChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().lengthProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the length property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeLengthInvalidationListener(InvalidationListener invalidationListener) {
        getNode().lengthProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the editable property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeEditableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().editableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the editable property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeEditableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().editableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the selection property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSelectionChangeListener(ChangeListener<? super IndexRange> changeListener) {
        getNode().selectionProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the selection property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSelectionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectionProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the selected text property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSelectedTextChangeListener(ChangeListener<? super String> changeListener) {
        getNode().selectedTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the selected text property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeSelectedTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectedTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the caret position property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeCaretPositionChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().caretPositionProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the caret position property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeCaretPositionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().caretPositionProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the anchor property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAnchorChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().anchorProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the anchor property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeAnchorInvalidationListener(InvalidationListener invalidationListener) {
        getNode().anchorProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the undoable property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeUndoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().undoableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the undoable property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeUndoableInvalidationListener(InvalidationListener invalidationListener) {
        getNode().undoableProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    /**
     * Removes a {@link ChangeListener} from listening for changes to the redoable property.
     *
     * @param changeListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T removeRedoableChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().redoableProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    /**
     * Removes an {@link InvalidationListener} from listening for changes to the redoable property.
     *
     * @param invalidationListener
     *         The listener to remove.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
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

    /**
     * Binds the font property to the specified {@link ObservableValue}.
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
     * Unbinds the font property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindFontProperty() {
        getNode().fontProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the font property bidirectionally to the specified {@link Property}.
     *
     * @param otherProperty
     *         The property to bind bidirectionally with the font property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalFontProperty(Property<Font> otherProperty) {
        getNode().fontProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    /**
     * Unbinds the font property from the specified bidirectional {@link Property}.
     *
     * @param otherProperty
     *         The property to unbind bidirectionally from the font property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalFontProperty(Property<Font> otherProperty) {
        getNode().fontProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Prompt Text Property

    /**
     * Binds the prompt text property to the specified {@link ObservableValue}.
     *
     * @param observableValue
     *         The observable value to bind to the prompt text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindPromptTextProperty(ObservableValue<? extends String> observableValue) {
        getNode().promptTextProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the prompt text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindPromptTextProperty() {
        getNode().promptTextProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the prompt text property bidirectionally to the specified {@link Property}.
     *
     * @param property
     *         The property to bind bidirectionally with the prompt text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalPromptTextProperty(Property<String> property) {
        getNode().promptTextProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the prompt text property from the specified bidirectional {@link Property}.
     *
     * @param property
     *         The property to unbind bidirectionally from the prompt text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalPromptTextProperty(Property<String> property) {
        getNode().promptTextProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Text Formatter Property

    /**
     * Binds the text formatter property to the specified {@link ObservableValue}.
     *
     * @param observableValue
     *         The observable value to bind to the text formatter property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindTextFormatterProperty(ObservableValue<? extends TextFormatter<?>> observableValue) {
        getNode().textFormatterProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the text formatter property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindTextFormatterProperty() {
        getNode().textFormatterProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the text formatter property bidirectionally to the specified {@link Property}.
     *
     * @param property
     *         The property to bind bidirectionally with the text formatter property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        getNode().textFormatterProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the text formatter property from the specified bidirectional {@link Property}.
     *
     * @param property
     *         The property to unbind bidirectionally from the text formatter property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTextFormatterProperty(Property<TextFormatter<?>> property) {
        getNode().textFormatterProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // TextProperty

    /**
     * Binds the text property to the specified {@link ObservableValue}.
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
     * Unbinds the text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindTextProperty() {
        getNode().textProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the text property bidirectionally to the specified {@link Property}.
     *
     * @param property
     *         The property to bind bidirectionally with the text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalTextProperty(Property<String> property) {
        getNode().textProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the text property from the specified bidirectional {@link Property}.
     *
     * @param property
     *         The property to unbind bidirectionally from the text property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindBidirectionalTextProperty(Property<String> property) {
        getNode().textProperty()
                 .unbindBidirectional(property);
        return getConfigurator();
    }

    // Editable Property

    /**
     * Binds the editable property to the specified {@link ObservableValue}.
     *
     * @param observableValue
     *         The observable value to bind to the editable property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindEditableProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().editableProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    /**
     * Unbinds the editable property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T unbindEditableProperty() {
        getNode().editableProperty()
                 .unbind();
        return getConfigurator();
    }

    /**
     * Binds the editable property bidirectionally to the specified {@link Property}.
     *
     * @param property
     *         The property to bind bidirectionally with the editable property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T bindBidirectionalEditableProperty(Property<Boolean> property) {
        getNode().editableProperty()
                 .bindBidirectional(property);
        return getConfigurator();
    }

    /**
     * Unbinds the editable property from the specified bidirectional {@link Property}.
     *
     * @param property
     *         The property to unbind bidirectionally from the editable property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
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

    /**
     * Sets the font of the node.
     *
     * @param value
     *         The font value to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setFont(Font value) {
        getNode().setFont(value);
        return getConfigurator();
    }

    /**
     * Sets the prompt text of the node.
     *
     * @param value
     *         The prompt text value to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setPromptText(String value) {
        getNode().setPromptText(value);
        return getConfigurator();
    }

    /**
     * Sets the text formatter of the node.
     *
     * @param value
     *         The text formatter value to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setTextFormatter(TextFormatter<?> value) {
        getNode().setTextFormatter(value);
        return getConfigurator();
    }

    /**
     * Sets the text of the node.
     *
     * @param value
     *         The text value to set.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setText(String value) {
        getNode().setText(value);
        return getConfigurator();
    }

    /**
     * Sets the editable property of the node.
     *
     * @param value
     *         The boolean value to set for the editable property.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T setEditable(boolean value) {
        getNode().setEditable(value);
        return getConfigurator();
    }

    //endregion Set Functions

    //region Text Manipulation Functions
    //*****************************************************************
    // Text Manipulation Functions
    //*****************************************************************

    /**
     * Appends the specified text to the end of the text content.
     *
     * @param text
     *         The text to append.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T appendText(String text) {
        getNode().appendText(text);
        return getConfigurator();
    }

    /**
     * Inserts the specified text at the specified position in the text content.
     *
     * @param index
     *         The index at which to insert the text.
     * @param text
     *         The text to insert.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T insertText(int index, String text) {
        getNode().insertText(index, text);
        return getConfigurator();
    }

    /**
     * Deletes the text within the specified range.
     *
     * @param range
     *         The range of text to delete.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T deleteText(IndexRange range) {
        getNode().deleteText(range);
        return getConfigurator();
    }

    /**
     * Deletes the text within the specified range of indices.
     *
     * @param start
     *         The start index of the text to delete.
     * @param end
     *         The end index of the text to delete.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T deleteText(int start, int end) {
        getNode().deleteText(start, end);
        return getConfigurator();
    }

    /**
     * Replaces the text within the specified range with the specified replacement text.
     *
     * @param range
     *         The range of text to replace.
     * @param text
     *         The replacement text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T replaceText(IndexRange range, String text) {
        getNode().replaceText(range, text);
        return getConfigurator();
    }

    /**
     * Replaces the text within the specified range of indices with the specified replacement text.
     *
     * @param start
     *         The start index of the text to replace.
     * @param end
     *         The end index of the text to replace.
     * @param text
     *         The replacement text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T replaceText(int start, int end, String text) {
        getNode().replaceText(start, end, text);
        return getConfigurator();
    }

    /**
     * Cuts the selected text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T cut() {
        getNode().cut();
        return getConfigurator();
    }

    /**
     * Copies the selected text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T copy() {
        getNode().copy();
        return getConfigurator();
    }

    /**
     * Pastes the clipboard content.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T paste() {
        getNode().paste();
        return getConfigurator();
    }

    /**
     * Selects text backward from the current caret position.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectBackward() {
        getNode().selectBackward();
        return getConfigurator();
    }

    /**
     * Selects text forward from the current caret position.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectForward() {
        getNode().selectForward();
        return getConfigurator();
    }

    /**
     * Moves the caret to the previous word boundary.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T previousWord() {
        getNode().previousWord();
        return getConfigurator();
    }

    /**
     * Moves the caret to the next word boundary.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T nextWord() {
        getNode().nextWord();
        return getConfigurator();
    }

    /**
     * Moves the caret to the end of the next word.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T endOfNextWord() {
        getNode().endOfNextWord();
        return getConfigurator();
    }

    /**
     * Selects the previous word.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectPreviousWord() {
        getNode().selectPreviousWord();
        return getConfigurator();
    }

    /**
     * Selects the next word.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectNextWord() {
        getNode().selectNextWord();
        return getConfigurator();
    }

    /**
     * Selects to the end of the next word.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectEndOfNextWord() {
        getNode().selectEndOfNextWord();
        return getConfigurator();
    }

    /**
     * Selects all the text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectAll() {
        getNode().selectAll();
        return getConfigurator();
    }

    /**
     * Moves the caret to the beginning of the text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T home() {
        return getConfigurator();
    }

    /**
     * Moves the caret to the end of the text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T end() {
        getNode().end();
        return getConfigurator();
    }

    /**
     * Selects to the beginning of the text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectHome() {
        getNode().selectHome();
        return getConfigurator();
    }

    /**
     * Selects to the end of the text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectEnd() {
        getNode().selectEnd();
        return getConfigurator();
    }

    /**
     * Deletes the character before the current caret position.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T deletePreviousChar() {
        getNode().deletePreviousChar();
        return getConfigurator();
    }

    /**
     * Deletes the character after the current caret position.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T deleteNextChar() {
        getNode().deleteNextChar();
        return getConfigurator();
    }

    /**
     * Moves the caret forward.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T forward() {
        getNode().forward();
        return getConfigurator();
    }

    /**
     * Moves the caret backward.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T backward() {
        getNode().backward();
        return getConfigurator();
    }

    /**
     * Sets the caret position to the specified position.
     *
     * @param pos
     *         The new caret position.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T positionCaret(int pos) {
        getNode().positionCaret(pos);
        return getConfigurator();
    }

    /**
     * Selects the text range from the current caret position to the specified position.
     *
     * @param pos
     *         The end position of the selection.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectPositionCaret(int pos) {
        getNode().selectPositionCaret(pos);
        return getConfigurator();
    }

    /**
     * Selects the text range between the specified anchor and caret positions.
     *
     * @param anchor
     *         The anchor position.
     * @param caretPosition
     *         The caret position.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T selectRange(int anchor, int caretPosition) {
        getNode().selectRange(anchor, caretPosition);
        return getConfigurator();
    }

    /**
     * Extends the current selection to the specified position.
     *
     * @param pos
     *         The end position of the extended selection.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T extendSelection(int pos) {
        getNode().extendSelection(pos);
        return getConfigurator();
    }

    /**
     * Clears the text selection.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T clear() {
        getNode().clear();
        return getConfigurator();
    }

    /**
     * Deselects any current text selection.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T deselect() {
        getNode().deselect();
        return getConfigurator();
    }

    /**
     * Replaces the current selection with the specified text.
     *
     * @param replacement
     *         The text to replace the selection with.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T replaceSelection(String replacement) {
        getNode().replaceSelection(replacement);
        return getConfigurator();
    }

    /**
     * Undoes the last edit to the text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T undo() {
        getNode().undo();
        return getConfigurator();
    }

    /**
     * Redoes the last undone edit to the text.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T redo() {
        getNode().redo();
        return getConfigurator();
    }

    /**
     * Commits the current value.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T commitValue() {
        getNode().commitValue();
        return getConfigurator();
    }

    /**
     * Cancels the current edit operation.
     *
     * @return The current configurator instance, for chaining further configuration calls.
     */
    default T cancelEdit() {
        getNode().cancelEdit();
        return getConfigurator();
    }

    //endregion Text Manipulation Functions
}
