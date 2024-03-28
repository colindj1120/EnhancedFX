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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.base;

import io.github.colindj1120.enhancedfx.controls.simplecontrol.base.InnerBase;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Labeled;
import javafx.scene.control.OverrunStyle;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * {@link InnerLabeled} extends the {@link InnerBase} interface to provide a rich set of functionalities for working with properties specific to {@link Labeled} controls in JavaFX.
 *
 * <p>This includes capabilities for manipulating text, font, alignment, graphic, and various other properties that influence the appearance and behavior of labeled controls.</p>
 *
 * <h2>Capabilities include:</h2>
 * <ul>
 *     <li>Text manipulation: changing the text content and its appearance.</li>
 *     <li>Alignment settings: adjusting text and content alignment within the labeled control.</li>
 *     <li>Graphic management: setting and retrieving the graphic (icon) displayed alongside the text.</li>
 *     <li>Font styling: modifying the font type, size, and style used for the text.</li>
 *     <li>Text decoration: applying underline styling and changing the text fill color.</li>
 *     <li>Content display customization: altering how the text and graphic are arranged.</li>
 *     <li>Mnemonic parsing: enabling or disabling mnemonic parsing for the labeled control.</li>
 *     <li>Text-related properties: adjusting properties such as line spacing, text wrapping, and text overrun behavior.</li>
 * </ul>
 *
 * <p>In addition to these capabilities, {@link InnerLabeled} inherits core functionalities from {@link InnerBase}, such as basic property access and modification methods that are common across different
 * control types.</p>
 *
 * <h2>Usage example:</h2>
 * <pre>
 * {@code
 * // Assuming `myLabeledControl` is an instance of a class implementing InnerLabeled
 * myLabeledControl.setText("Hello World");
 * myLabeledControl.setAlignment(Pos.CENTER);
 * myLabeledControl.setFont(new Font("Arial", 16));
 * myLabeledControl.setUnderline(true);
 * myLabeledControl.setTextFill(Color.BLUE);
 * myLabeledControl.setWrapText(true);
 * myLabeledControl.setContentDisplay(ContentDisplay.TOP);
 * myLabeledControl.setGraphic(new ImageView(new Image("/path/to/icon.png")));
 * }
 * </pre>
 *
 * <p>This detailed customization ability makes {@link InnerLabeled} a powerful tool for developers looking to leverage the full potential of labeled controls in their JavaFX applications, ensuring that the
 * UI precisely meets their design and functionality requirements.</p>
 *
 * @param <T>
 *         the type of {@link Labeled} control being wrapped and enhanced by this interface
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Labeled
 * @see InnerBase
 */
public interface InnerLabeled<T extends Labeled> extends InnerBase<T> {
    /*
     * Methods Available:
     *  - StringProperty textProperty()
     *  - void setText(String value)
     *  - String getText()
     *  - ObjectProperty<Pos> alignmentProperty()
     *  - void setAlignment(Pos value)
     *  - Pos getAlignment()
     *  - ObjectProperty<TextAlignment> textAlignmentProperty()
     *  - void setTextAlignment(TextAlignment value)
     *  - TextAlignment getTextAlignment()
     *  - ObjectProperty<OverrunStyle> textOverrunProperty()
     *  - void setTextOverrun(OverrunStyle value)
     *  - OverrunStyle getTextOverrun()
     *  - StringProperty ellipsisStringProperty()
     *  - void setEllipsisString(String value)
     *  - String getEllipsisString()
     *  - BooleanProperty wrapTextProperty()
     *  - void setWrapText(boolean value)
     *  - boolean isWrapText()
     *  - Orientation getContentBias()
     *  - ObjectProperty<Font> fontProperty()
     *  - void setFont(Font value)
     *  - Font getFont()
     *  - ObjectProperty<Node> graphicProperty()
     *  - void setGraphic(Node value)
     *  - Node getGraphic()
     *  - BooleanProperty underlineProperty()
     *  - void setUnderline(boolean value)
     *  - boolean isUnderline()
     *  - DoubleProperty lineSpacingProperty()
     *  - void setLineSpacing(double value)
     *  - double getLineSpacing()
     *  - ObjectProperty<ContentDisplay> contentDisplayProperty()
     *  - void setContentDisplay(ContentDisplay value)
     *  - ContentDisplay getContentDisplay()
     *  - ReadOnlyObjectProperty<Insets> labelPaddingProperty()
     *  - Insets getLabelPadding()
     *  - DoubleProperty graphicTextGapProperty()
     *  - void setGraphicTextGap(double value)
     *  - double getGraphicTextGap()
     *  - void setTextFill(Paint value)
     *  - Paint getTextFill()
     *  - ObjectProperty<Paint> textFillProperty()
     *  - void setMnemonicParsing(boolean value)
     *  - boolean isMnemonicParsing()
     *  - BooleanProperty mnemonicParsingProperty()
     */

    /**
     * Gets the text property of the inner control.
     *
     * @return the text property of the inner control.
     */
    default StringProperty textProperty() {return getInnerControl().textProperty();}

    /**
     * Sets the text of the inner control.
     *
     * @param value
     *         the new text value to set.
     */
    default void setText(String value) {getInnerControl().setText(value);}

    /**
     * Gets the text of the inner control.
     *
     * @return the current text of the inner control.
     */
    default String getText() {return getInnerControl().getText();}

    /**
     * Gets the alignment property of the inner control.
     *
     * @return the alignment property of the inner control.
     */
    default ObjectProperty<Pos> alignmentProperty() {return getInnerControl().alignmentProperty();}

    /**
     * Sets the alignment of the inner control.
     *
     * @param value
     *         the new alignment position to set.
     */
    default void setAlignment(Pos value) {getInnerControl().setAlignment(value);}

    /**
     * Gets the current alignment of the inner control.
     *
     * @return the current alignment of the inner control.
     */
    default Pos getAlignment() {return getInnerControl().getAlignment();}

    /**
     * Gets the text alignment property of the inner control.
     *
     * @return the text alignment property of the inner control.
     */
    default ObjectProperty<TextAlignment> textAlignmentProperty() {return getInnerControl().textAlignmentProperty();}

    /**
     * Sets the text alignment of the inner control.
     *
     * @param value
     *         the new text alignment to set.
     */
    default void setTextAlignment(TextAlignment value) {getInnerControl().setTextAlignment(value);}

    /**
     * Gets the current text alignment of the inner control.
     *
     * @return the current text alignment of the inner control.
     */
    default TextAlignment getTextAlignment() {return getInnerControl().getTextAlignment();}

    /**
     * Gets the text overrun property of the inner control.
     *
     * @return the text overrun property of the inner control.
     */
    default ObjectProperty<OverrunStyle> textOverrunProperty() {return getInnerControl().textOverrunProperty();}

    /**
     * Sets the text overrun style of the inner control.
     *
     * @param value
     *         the new text overrun style to set.
     */
    default void setTextOverrun(OverrunStyle value) {getInnerControl().setTextOverrun(value);}

    /**
     * Gets the current text overrun style of the inner control.
     *
     * @return the current text overrun style of the inner control.
     */
    default OverrunStyle getTextOverrun() {return getInnerControl().getTextOverrun();}

    /**
     * Gets the ellipsis string property of the inner control.
     *
     * @return the ellipsis string property of the inner control.
     */
    default StringProperty ellipsisStringProperty() {return getInnerControl().ellipsisStringProperty();}

    /**
     * Sets the ellipsis string of the inner control.
     *
     * @param value
     *         the new ellipsis string to set.
     */
    default void setEllipsisString(String value) {getInnerControl().setEllipsisString(value);}

    /**
     * Gets the current ellipsis string of the inner control.
     *
     * @return the current ellipsis string of the inner control.
     */
    default String getEllipsisString() {return getInnerControl().getEllipsisString();}

    /**
     * Gets the wrap text property of the inner control.
     *
     * @return the wrap text property of the inner control.
     */
    default BooleanProperty wrapTextProperty() {return getInnerControl().wrapTextProperty();}

    /**
     * Sets whether the text of the inner control should wrap.
     *
     * @param value
     *         true if the text should wrap; false otherwise.
     */
    default void setWrapText(boolean value) {getInnerControl().setWrapText(value);}

    /**
     * Checks if the text of the inner control is wrapped.
     *
     * @return true if the text is wrapped; false otherwise.
     */
    default boolean isWrapText() {return getInnerControl().isWrapText();}

    /**
     * Gets the content bias of the inner control.
     *
     * @return the content bias of the inner control.
     */
    default Orientation getContentBias() {return getInnerControl().getContentBias();}

    /**
     * Gets the font property of the inner control.
     *
     * @return the font property of the inner control.
     */
    default ObjectProperty<Font> fontProperty() {return getInnerControl().fontProperty();}

    /**
     * Sets the font of the inner control.
     *
     * @param value
     *         the new font to set.
     */
    default void setFont(Font value) {getInnerControl().setFont(value);}

    /**
     * Gets the current font of the inner control.
     *
     * @return the current font of the inner control.
     */
    default Font getFont() {return getInnerControl().getFont();}

    /**
     * Gets the graphic property of the inner control.
     *
     * @return the graphic property of the inner control.
     */
    default ObjectProperty<Node> graphicProperty() {return getInnerControl().graphicProperty();}

    /**
     * Sets the graphic of the inner control.
     *
     * @param value
     *         the new graphic to set.
     */
    default void setGraphic(Node value) {getInnerControl().setGraphic(value);}

    /**
     * Gets the graphic of the inner control.
     *
     * @return the current graphic of the inner control.
     */
    default Node getGraphic() {return getInnerControl().getGraphic();}

    /**
     * Gets the underline property of the inner control.
     *
     * @return the underline property of the inner control.
     */
    default BooleanProperty underlineProperty() {return getInnerControl().underlineProperty();}

    /**
     * Sets whether the text of the inner control should be underlined.
     *
     * @param value
     *         true if the text should be underlined; false otherwise.
     */
    default void setUnderline(boolean value) {getInnerControl().setUnderline(value);}

    /**
     * Checks if the text of the inner control is underlined.
     *
     * @return true if the text is underlined; false otherwise.
     */
    default boolean isUnderline() {return getInnerControl().isUnderline();}

    /**
     * Gets the line spacing property of the inner control.
     *
     * @return the line spacing property of the inner control.
     */
    default DoubleProperty lineSpacingProperty() {return getInnerControl().lineSpacingProperty();}

    /**
     * Sets the line spacing of the inner control.
     *
     * @param value
     *         the new line spacing value to set.
     */
    default void setLineSpacing(double value) {getInnerControl().setLineSpacing(value);}

    /**
     * Gets the current line spacing of the inner control.
     *
     * @return the current line spacing of the inner control.
     */
    default double getLineSpacing() {return getInnerControl().getLineSpacing();}

    /**
     * Gets the content display property of the inner control.
     *
     * @return the content display property of the inner control.
     */
    default ObjectProperty<ContentDisplay> contentDisplayProperty() {return getInnerControl().contentDisplayProperty();}

    /**
     * Sets the content display of the inner control.
     *
     * @param value
     *         the new content display setting to set.
     */
    default void setContentDisplay(ContentDisplay value) {getInnerControl().setContentDisplay(value);}

    /**
     * Gets the current content display setting of the inner control.
     *
     * @return the current content display setting of the inner control.
     */
    default ContentDisplay getContentDisplay() {return getInnerControl().getContentDisplay();}

    /**
     * Gets the label padding property of the inner control.
     *
     * @return the label padding property of the inner control, as a read-only property.
     */
    default ReadOnlyObjectProperty<Insets> labelPaddingProperty() {return getInnerControl().labelPaddingProperty();}

    /**
     * Gets the current label padding of the inner control.
     *
     * @return the current label padding of the inner control.
     */
    default Insets getLabelPadding() {return getInnerControl().getLabelPadding();}

    /**
     * Gets the graphic text gap property of the inner control.
     *
     * @return the graphic text gap property of the inner control.
     */
    default DoubleProperty graphicTextGapProperty() {return getInnerControl().graphicTextGapProperty();}

    /**
     * Sets the gap between the graphic and text of the inner control.
     *
     * @param value
     *         the new gap size to set.
     */
    default void setGraphicTextGap(double value) {getInnerControl().setGraphicTextGap(value);}

    /**
     * Gets the current gap between the graphic and text of the inner control.
     *
     * @return the current gap size.
     */
    default double getGraphicTextGap() {return getInnerControl().getGraphicTextGap();}

    /**
     * Sets the text fill color of the inner control.
     *
     * @param value
     *         the new text fill color to set.
     */
    default void setTextFill(Paint value) {getInnerControl().setTextFill(value);}

    /**
     * Gets the current text fill color of the inner control.
     *
     * @return the current text fill color.
     */
    default Paint getTextFill() {return getInnerControl().getTextFill();}

    /**
     * Gets the text fill property of the inner control.
     *
     * @return the text fill property of the inner control.
     */
    default ObjectProperty<Paint> textFillProperty() {return getInnerControl().textFillProperty();}

    /**
     * Sets whether the inner control should parse mnemonic parsing.
     *
     * @param value
     *         true if mnemonic parsing should be enabled; false otherwise.
     */
    default void setMnemonicParsing(boolean value) {getInnerControl().setMnemonicParsing(value);}

    /**
     * Checks if mnemonic parsing is enabled for the inner control.
     *
     * @return true if mnemonic parsing is enabled; false otherwise.
     */
    default boolean isMnemonicParsing() {return getInnerControl().isMnemonicParsing();}

    /**
     * Gets the mnemonic parsing property of the inner control.
     *
     * @return the mnemonic parsing property of the inner control.
     */
    default BooleanProperty mnemonicParsingProperty() {return getInnerControl().mnemonicParsingProperty();}
}
