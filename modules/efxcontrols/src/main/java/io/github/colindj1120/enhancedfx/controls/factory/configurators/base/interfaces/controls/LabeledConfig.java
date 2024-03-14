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
package io.github.colindj1120.enhancedfx.controls.factory.configurators.base.interfaces.controls;

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
 * Provides a comprehensive configuration interface for labeled controls in JavaFX, enabling fluent setup and adjustment of various
 * properties related to the text, alignment, font, graphic, and other visual elements. Designed for enhanced flexibility and
 * efficiency in configuring UI components, it supports a wide range of capabilities essential for customizing the appearance and
 * behavior of labeled controls.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Adding and removing change listeners for text, alignment, font, and other properties.</li>
 *     <li>Binding and unbinding properties to observable values, facilitating dynamic UI updates.</li>
 *     <li>Direct manipulation of properties such as text, font size, color, and alignment.</li>
 *     <li>Configuring text overrun behavior and text wrapping for better text management in UI components.</li>
 *     <li>Setting and changing the graphic content, allowing for a combination of text and imagery.</li>
 *     <li>Adjusting content display options and text alignment for optimal user interface layout.</li>
 *     <li>Modifying padding, spacing, and other layout-related properties for fine-grained control over the appearance.</li>
 * </ul>
 *
 * <p>This interface is designed to be implemented by configurator classes that aim to enhance the flexibility and usability of
 * {@link Labeled} nodes. It serves as a foundational component of the EnhancedFX library, which seeks to provide
 * extended functionality and utilities beyond the core JavaFX library.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Labeled
 */
public interface LabeledConfig {
    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the text property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the text property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the alignment property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addAlignmentChangeListener(ChangeListener<? super Pos> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the alignment property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addAlignmentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the text alignment property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the text alignment property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addTextAlignmentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the text overrun property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the text overrun property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addTextOverrunInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the ellipsis string property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addEllipsisStringChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the ellipsis string property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addEllipsisStringInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the wrap text property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the wrap text property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addWrapTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the font property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addFontChangeListener(ChangeListener<? super Font> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the font property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addFontInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the graphic property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addGraphicChangeListener(ChangeListener<? super Node> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the graphic property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addGraphicInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the underline property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addUnderlineChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the underline property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addUnderlineInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the line spacing property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addLineSpacingChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the line spacing property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addLineSpacingInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the content display property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the content display property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addContentDisplayInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the label padding property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the label padding property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addLabelPaddingInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the graphic text gap property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the graphic text gap property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addGraphicTextGapInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the text fill property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addTextFillChangeListener(ChangeListener<? super Paint> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the text fill property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addTextFillInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Adds a {@link ChangeListener} that will be notified whenever the mnemonic parsing property changes.
     *
     * @param changeListener
     *         the {@link ChangeListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Adds an {@link InvalidationListener} that will be notified whenever the mnemonic parsing property becomes invalid.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to add; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig addMnemonicParsingInvalidationListener(InvalidationListener invalidationListener);

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    /**
     * Removes a {@link ChangeListener} from the text property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeTextChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the text property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the alignment property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the alignment property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeAlignmentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the text alignment property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeTextAlignmentChangeListener(ChangeListener<? super TextAlignment> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the text alignment property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeTextAlignmentInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the text overrun property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeTextOverrunChangeListener(ChangeListener<? super OverrunStyle> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the text overrun property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeTextOverrunInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the ellipsis string property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeEllipsisStringChangeListener(ChangeListener<? super String> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the ellipsis string property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeEllipsisStringInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the wrap text property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the wrap text property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeWrapTextInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the font property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeFontChangeListener(ChangeListener<? super Font> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the font property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeFontInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the graphic property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeGraphicChangeListener(ChangeListener<? super Node> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the graphic property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeGraphicInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the underline property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeUnderlineChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the underline property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeUnderlineInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the line spacing property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeLineSpacingChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the line spacing property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeLineSpacingInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the content display property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeContentDisplayChangeListener(ChangeListener<? super ContentDisplay> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the content display property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeContentDisplayInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the label padding property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeLabelPaddingChangeListener(ChangeListener<? super Insets> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the label padding property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeLabelPaddingInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the graphic text gap property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeGraphicTextGapChangeListener(ChangeListener<? super Number> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the graphic text gap property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeGraphicTextGapInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the text fill property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeTextFillChangeListener(ChangeListener<? super Paint> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the text fill property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeTextFillInvalidationListener(InvalidationListener invalidationListener);

    /**
     * Removes a {@link ChangeListener} from the mnemonic parsing property.
     *
     * @param changeListener
     *         the {@link ChangeListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeMnemonicParsingChangeListener(ChangeListener<? super Boolean> changeListener);

    /**
     * Removes an {@link InvalidationListener} from the mnemonic parsing property.
     *
     * @param invalidationListener
     *         the {@link InvalidationListener} to remove; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig removeMnemonicParsingInvalidationListener(InvalidationListener invalidationListener);

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    //Text Property

    /**
     * Binds the text property to an {@link ObservableValue}. The text property will automatically update to reflect the observable
     * value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindTextProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the text property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindTextProperty();

    /**
     * Binds the text property bidirectionally to a {@link Property}. Both properties will automatically update to reflect each other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalTextProperty(Property<String> property);

    /**
     * Unbinds the text property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalTextProperty(Property<String> property);

    //Alignment Property

    /**
     * Binds the alignment property to an {@link ObservableValue}. The alignment property will automatically update to reflect the
     * observable value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindAlignmentProperty(ObservableValue<? extends Pos> observableValue);

    /**
     * Unbinds the alignment property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindAlignmentProperty();

    /**
     * Binds the alignment property bidirectionally to a {@link Property}. Both properties will automatically update to reflect each
     * other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalAlignmentProperty(Property<Pos> property);

    /**
     * Unbinds the alignment property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalAlignmentProperty(Property<Pos> property);

    //Text Alignment Property

    /**
     * Binds the text alignment property to an {@link ObservableValue}. The text alignment property will automatically update to
     * reflect the observable value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindTextAlignmentProperty(ObservableValue<? extends TextAlignment> observableValue);

    /**
     * Unbinds the text alignment property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindTextAlignmentProperty();

    /**
     * Binds the text alignment property bidirectionally to a {@link Property}. Both properties will automatically update to reflect
     * each other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalTextAlignmentProperty(Property<TextAlignment> property);

    /**
     * Unbinds the text alignment property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalTextAlignmentProperty(Property<TextAlignment> property);

    //Text Overrun Property

    /**
     * Binds the text overrun property to an {@link ObservableValue}. The text overrun property will automatically update to reflect
     * the observable value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindTextOverrunProperty(ObservableValue<? extends OverrunStyle> observableValue);

    /**
     * Unbinds the text overrun property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindTextOverrunProperty();

    /**
     * Binds the text overrun property bidirectionally to a {@link Property}. Both properties will automatically update to reflect each
     * other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalTextOverrunProperty(Property<OverrunStyle> property);

    /**
     * Unbinds the text overrun property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalTextOverrunProperty(Property<OverrunStyle> property);

    //Ellipsis String Property

    /**
     * Binds the ellipsis string property to an {@link ObservableValue}. This property will update automatically to reflect the
     * observable value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindEllipsisStringProperty(ObservableValue<? extends String> observableValue);

    /**
     * Unbinds the ellipsis string property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindEllipsisStringProperty();

    /**
     * Binds the ellipsis string property bidirectionally to a {@link Property}. Both properties will update automatically to reflect
     * each other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalEllipsisStringProperty(Property<String> property);

    /**
     * Unbinds the ellipsis string property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalEllipsisStringProperty(Property<String> property);

    //Wrap Text Property

    /**
     * Binds the wrap text property to an {@link ObservableValue}. This property will update automatically to reflect the observable
     * value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the wrap text property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindWrapTextProperty();

    /**
     * Binds the wrap text property bidirectionally to a {@link Property}. Both properties will update automatically to reflect each
     * other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalWrapTextProperty(Property<Boolean> property);

    /**
     * Unbinds the wrap text property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalWrapTextProperty(Property<Boolean> property);

    //Font Property

    /**
     * Binds the font property to an {@link ObservableValue}. This property will update automatically to reflect the observable value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindFontProperty(ObservableValue<? extends Font> observableValue);

    /**
     * Unbinds the font property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindFontProperty();

    /**
     * Binds the font property bidirectionally to a {@link Property}. Both properties will update automatically to reflect each other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalFontProperty(Property<Font> property);

    /**
     * Unbinds the font property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalFontProperty(Property<Font> property);

    //Graphic Property

    /**
     * Binds the graphic property to an {@link ObservableValue}. This property will update automatically to reflect the observable
     * value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindGraphicProperty(ObservableValue<? extends Node> observableValue);

    /**
     * Unbinds the graphic property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindGraphicProperty();

    /**
     * Binds the graphic property bidirectionally to a {@link Property}. Both properties will update automatically to reflect each
     * other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalGraphicProperty(Property<Node> property);

    /**
     * Unbinds the graphic property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalGraphicProperty(Property<Node> property);

    //Underline Property

    /**
     * Binds the underline property to an {@link ObservableValue}. This property will update automatically to reflect the observable
     * value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindUnderlineProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the underline property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindUnderlineProperty();

    /**
     * Binds the underline property bidirectionally to a {@link Property}. Both properties will update automatically to reflect each
     * other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalUnderlineProperty(Property<Boolean> property);

    /**
     * Unbinds the underline property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalUnderlineProperty(Property<Boolean> property);

    //Line Spacing Property

    /**
     * Binds the line spacing property to an {@link ObservableValue}. This property will update automatically to reflect the observable
     * value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindLineSpacingProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the line spacing property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindLineSpacingProperty();

    /**
     * Binds the line spacing property bidirectionally to a {@link Property}. Both properties will update automatically to reflect each
     * other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalLineSpacingProperty(Property<Number> property);

    /**
     * Unbinds the line spacing property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalLineSpacingProperty(Property<Number> property);

    //Content Display Property

    /**
     * Binds the content display property to an {@link ObservableValue}. This property will update automatically to reflect the
     * observable value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindContentDisplayProperty(ObservableValue<? extends ContentDisplay> observableValue);

    /**
     * Unbinds the content display property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindContentDisplayProperty();

    /**
     * Binds the content display property bidirectionally to a {@link Property}. Both properties will update automatically to reflect
     * each other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalContentDisplayProperty(Property<ContentDisplay> property);

    /**
     * Unbinds the content display property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalContentDisplayProperty(Property<ContentDisplay> property);

    //Graphic Text Gap Property

    /**
     * Binds the graphic text gap property to an {@link ObservableValue}. This property will automatically update to reflect the
     * observable value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindGraphicTextGapProperty(ObservableValue<? extends Number> observableValue);

    /**
     * Unbinds the graphic text gap property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindGraphicTextGapProperty();

    /**
     * Binds the graphic text gap property bidirectionally to a {@link Property}. Both properties will automatically update to reflect
     * each other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalGraphicTextGapProperty(Property<Number> property);

    /**
     * Unbinds the graphic text gap property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalGraphicTextGapProperty(Property<Number> property);

    //Text Fill Property

    /**
     * Binds the text fill property to an {@link ObservableValue}. This property will automatically update to reflect the observable
     * value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindTextFillProperty(ObservableValue<? extends Paint> observableValue);

    /**
     * Unbinds the text fill property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindTextFillProperty();

    /**
     * Binds the text fill property bidirectionally to a {@link Property}. Both properties will automatically update to reflect each
     * other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalTextFillProperty(Property<Paint> property);

    /**
     * Unbinds the text fill property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalTextFillProperty(Property<Paint> property);

    //Mnemonic Parsing Property

    /**
     * Binds the mnemonic parsing property to an {@link ObservableValue}. This property will automatically update to reflect the
     * observable value.
     *
     * @param observableValue
     *         the {@link ObservableValue} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindMnemonicParsingProperty(ObservableValue<? extends Boolean> observableValue);

    /**
     * Unbinds the mnemonic parsing property from its current {@link ObservableValue}.
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindMnemonicParsingProperty();

    /**
     * Binds the mnemonic parsing property bidirectionally to a {@link Property}. Both properties will automatically update to reflect
     * each other.
     *
     * @param property
     *         the {@link Property} to bind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig bindBidirectionalMnemonicParsingProperty(Property<Boolean> property);

    /**
     * Unbinds the mnemonic parsing property from a bidirectional binding with another {@link Property}.
     *
     * @param property
     *         the {@link Property} to unbind; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig unbindBidirectionalMnemonicParsingProperty(Property<Boolean> property);

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    /**
     * Sets the text for the labeled control.
     *
     * @param value
     *         the new text value
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setText(String value);

    /**
     * Sets the alignment for the labeled control.
     *
     * @param value
     *         the new alignment value; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setAlignment(Pos value);

    /**
     * Sets the text alignment within the labeled control.
     *
     * @param value
     *         the new text alignment value; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setTextAlignment(TextAlignment value);

    /**
     * Sets the text overrun strategy for the labeled control.
     *
     * @param value
     *         the new text overrun style; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setTextOverrun(OverrunStyle value);

    /**
     * Sets the ellipsis string used when the text is overrun in the labeled control.
     *
     * @param value
     *         the ellipsis string
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setEllipsisString(String value);

    /**
     * Sets whether the text in the labeled control should wrap.
     *
     * @param value
     *         {@code true} if the text should wrap; {@code false} otherwise
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setWrapText(boolean value);

    /**
     * Sets the font of the text within the labeled control.
     *
     * @param value
     *         the new font; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setFont(Font value);

    /**
     * Sets the graphic for the labeled control.
     *
     * @param value
     *         the new graphic node
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setGraphic(Node value);

    /**
     * Sets whether the text within the labeled control should be underlined.
     *
     * @param value
     *         {@code true} if the text should be underlined; {@code false} otherwise
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setUnderline(boolean value);

    /**
     * Sets the spacing between lines of text within the labeled control.
     *
     * @param value
     *         the line spacing amount
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setLineSpacing(double value);

    /**
     * Sets the content display strategy for the labeled control.
     *
     * @param value
     *         the new content display setting; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setContentDisplay(ContentDisplay value);

    /**
     * Sets the gap between the graphic and the text within the labeled control.
     *
     * @param value
     *         the graphic text gap amount
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setGraphicTextGap(double value);

    /**
     * Sets the fill color of the text within the labeled control.
     *
     * @param value
     *         the new text fill paint; cannot be null
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setTextFill(Paint value);

    /**
     * Sets whether mnemonic parsing is enabled for the labeled control.
     *
     * @param value
     *         {@code true} if mnemonic parsing should be enabled; {@code false} otherwise
     *
     * @return this {@link LabeledConfig} instance for method chaining
     */
    LabeledConfig setMnemonicParsing(boolean value);

    //endregion Set Functions
}
