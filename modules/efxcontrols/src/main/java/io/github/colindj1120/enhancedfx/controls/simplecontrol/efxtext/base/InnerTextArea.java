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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

/**
 * {@link InnerTextArea} extends the {@link InnerTextInputControl} interface to provide additional functionality specific to {@link TextArea} components within the EnhancedFX framework. This interface
 * facilitates the manipulation and observation of properties and behaviors unique to text areas, such as text wrapping, preferred column and row counts, and scroll position management.
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li>Text wrapping: Control whether text wraps to the next line or is truncated.</li>
 *     <li>Preferred column and row counts: Specify the ideal dimensions of the text area in terms of columns and rows.</li>
 *     <li>Scroll position management: Directly manipulate the horizontal and vertical scroll positions.</li>
 *     <li>Paragraph handling: Access the text content of the text area divided into paragraphs.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * InnerTextArea<TextArea> myTextArea = new EnhancedTextArea();
 * myTextArea.setWrapText(true);
 * myTextArea.setPrefColumnCount(20);
 * myTextArea.setPrefRowCount(5);
 * myTextArea.setScrollTop(100);
 * myTextArea.setScrollLeft(50);
 * ObservableList<CharSequence> paragraphs = myTextArea.getParagraphs();
 * }
 * </pre>
 *
 * <p>This example demonstrates how to use the {@code InnerTextArea} interface to configure a {@link TextArea} with specific behaviors and properties, such as enabling text wrapping, setting preferred
 * dimensions, and adjusting scroll positions. Additionally, it shows how to access the text area's content by paragraphs, providing a foundation for further text processing or analysis.</p>
 *
 * <p>Note: Implementations of {@code InnerTextArea} should ensure compatibility with the underlying {@link TextArea} component, respecting its limitations and design choices while providing enhanced
 * functionality and customization options through the EnhancedFX framework.</p>
 *
 * @param <T>
 *         the type of TextArea
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see InnerTextInputControl
 * @see TextArea
 */
public interface InnerTextArea<T extends TextArea> extends InnerTextInputControl<T> {
    /*
     * Methods Available:
     *  - ObservableList<CharSequence> getParagraphs()
     *  - BooleanProperty wrapTextProperty()
     *  - boolean isWrapText()
     *  - void setWrapText(boolean value)
     *  - IntegerProperty prefColumnCountProperty()
     *  - int getPrefColumnCount()
     *  - void setPrefColumnCount(int value)
     *  - IntegerProperty prefRowCountProperty()
     *  - int getPrefRowCount()
     *  - void setPrefRowCount(int value)
     *  - DoubleProperty scrollTopProperty()
     *  - double getScrollTop()
     *  - void setScrollTop(double value)
     *  - DoubleProperty scrollLeftProperty()
     *  - double getScrollLeft()
     *  - void setScrollLeft(double value)
     */

    /**
     * Returns the paragraphs in the text area.
     *
     * @return an ObservableList containing the paragraphs in the text area
     */
    default ObservableList<CharSequence> getParagraphs() {return getInnerControl().getParagraphs();}

    /**
     * Returns the BooleanProperty representing the "wrap text" property of the field. When the "wrap text" property is set to true, the text in the field will wrap onto the next line if it exceeds the width of
     * the field. Otherwise, it will be truncated.
     *
     * @return The BooleanProperty representing the "wrap text" property of the field.
     */
    default BooleanProperty wrapTextProperty() {return getInnerControl().wrapTextProperty();}

    /**
     * Determines whether the text in the associated TextArea should be wrapped to fit within the bounds of the TextArea.
     *
     * @return true if the text is wrapped, false otherwise
     */
    default boolean isWrapText() {return getInnerControl().isWrapText();}

    /**
     * Sets the wrap text property of the field. If set to true, the text will be wrapped to fit within the width of the field. If set to false, the text will not be wrapped and may extend beyond the visible
     * area of the field.
     *
     * @param value
     *         the boolean value indicating whether to wrap the text or not
     */
    default void setWrapText(boolean value) {getInnerControl().setWrapText(value);}

    /**
     * Returns the IntegerProperty representing the preferred number of columns in the TextArea. This property is delegated to the `prefColumnCountProperty()` method of the underlying `TextArea` field.
     *
     * @return the IntegerProperty representing the preferred number of columns in the TextArea
     */
    default IntegerProperty prefColumnCountProperty() {return getInnerControl().prefColumnCountProperty();}

    /**
     * Returns the preferred column count of the inner text input control.
     *
     * @return the preferred column count
     */
    default int getPrefColumnCount() {return getInnerControl().getPrefColumnCount();}

    /**
     * Sets the preferred number of columns for the text area.
     *
     * @param value
     *         the preferred number of columns
     */
    default void setPrefColumnCount(int value) {getInnerControl().setPrefColumnCount(value);}

    /**
     * Returns the IntegerProperty for the preferred row count of this InnerTextArea.
     *
     * @return the IntegerProperty for the preferred row count
     */
    default IntegerProperty prefRowCountProperty() {return getInnerControl().prefRowCountProperty();}

    /**
     * Get the preferred row count of the InnerTextArea. The preferred row count is determined by the underlying field's preferred row count.
     *
     * @return the preferred row count of the InnerTextArea
     */
    default int getPrefRowCount() {return getInnerControl().getPrefRowCount();}

    /**
     * Sets the preferred row count for the textarea.
     * <p>
     * The preferred row count specifies the number of visible rows that the textarea should have.
     *
     * @param value
     *         the preferred row count for the textarea
     */
    default void setPrefRowCount(int value) {getInnerControl().setPrefRowCount(value);}

    /**
     * Returns the scrollTop property of the InnerTextArea. The scrollTop property represents the vertical scroll position of the InnerTextArea.
     *
     * @return the scrollTop property of the InnerTextArea
     */
    default DoubleProperty scrollTopProperty() {return getInnerControl().scrollTopProperty();}

    /**
     * Retrieves the scroll top value of the text area.
     *
     * @return The scroll top value of the text area.
     */
    default double getScrollTop() {return getInnerControl().getScrollTop();}

    /**
     * Sets the vertical scroll position of the text area.
     *
     * @param value
     *         the vertical scroll position value
     */
    default void setScrollTop(double value) {getInnerControl().setScrollTop(value);}

    /**
     * Returns the scroll left property of the InnerTextArea.
     *
     * @return the scroll left property of the InnerTextArea
     */
    default DoubleProperty scrollLeftProperty() {return getInnerControl().scrollLeftProperty();}

    /**
     * Retrieves the value of the scrollLeft property for the text area
     *
     * @return The value of the scrollLeft property.
     */
    default double getScrollLeft() {return getInnerControl().getScrollLeft();}

    /**
     * Sets the scroll left value of the text area.
     *
     * @param value
     *         The scroll left value to set.
     */
    default void setScrollLeft(double value) {getInnerControl().setScrollLeft(value);}
}
