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

import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesCreator;
import io.github.colindj1120.materialdesignui.styling.StyleablePropertiesManager;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.ColorConverter;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * {@code TextBase} serves as an abstract base class for text-related UI components in JavaFX. It extends {@link HBox} and implements the {@link InnerTextBase} interface, providing a foundational
 * framework for text field controls with enhanced features such as custom text fill color and empty state checking.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Custom Text Fill Color:</b> Allows setting and getting the fill color of the text, enabling
 *       customization of text appearance.</li>
 *   <li><b>Empty State Checking:</b> Provides functionality to determine whether the text field is empty,
 *       aiding in validation and user input handling.</li>
 *   <li><b>CSS Styling Support:</b> Integrates with JavaFX's CSS styling mechanism, allowing the text
 *       fill color to be defined via CSS.</li>
 *   <li><b>Abstract Methods:</b> Requires the implementation of methods to access the underlying {@link TextField}
 *       or {@link TextInputControl}, ensuring flexibility and customization in derived classes.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * As an abstract class, {@code TextBase} is intended to be subclassed to create custom text field controls.
 * Subclasses should provide implementations for abstract methods and can further extend functionality as needed.
 *
 * <pre>
 * public class CustomTextField extends TextBase {
 *     // Implementation details...
 * }
 * </pre>
 *
 * <p>This class is part of the {@code io.github.colindj1120.materialdesignui.controls.base} package and is designed
 * to integrate with the Material Design UI framework for JavaFX.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see javafx.scene.layout.HBox
 * @see javafx.scene.control.TextField
 * @see javafx.scene.control.TextInputControl
 * @see javafx.scene.paint.Color
 * @see javafx.css.StyleableObjectProperty
 */
public abstract class TextBase extends HBox implements InnerTextBase<TextInputControl> {
    protected static final StyleablePropertiesManager     STYLEABLE_PROPERTIES_MANAGER = new StyleablePropertiesManager(HBox.getClassCssMetaData());
    protected final        StyleableObjectProperty<Color> textFill;
    protected final        StyleableObjectProperty<Color> promptTextFill;
    protected              BooleanBinding                 isEmpty;

    static {
        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<TextBase, Color>().property("-fx-text-fill")
                                                                                                                .converter(ColorConverter.getInstance())
                                                                                                                .initialValue(Color.valueOf("#000000"))
                                                                                                                .isSettableFunction(node -> checkProperty(node.textFill))
                                                                                                                .propertyGetterFunction(node -> node.textFill));

        STYLEABLE_PROPERTIES_MANAGER.addCssMetaData(new StyleablePropertiesManager.CssBuilder<TextBase, Color>().property("-fx-prompt-text-fill")
                                                                                                                .converter(ColorConverter.getInstance())
                                                                                                                .initialValue(Color.valueOf("#000000"))
                                                                                                                .isSettableFunction(node -> checkProperty(node.promptTextFill))
                                                                                                                .propertyGetterFunction(node -> node.promptTextFill));
    }

    /**
     * This class represents a TextBase object.
     *
     * <p>The TextBase class is used to create and manage a text field.
     * It provides methods for setting the fill color of the text and prompt text, checking if the text field is empty, and getting the color of the text fill.</p>
     *
     * <p>Usage example:</p>
     *
     * <pre>
     *      TextBase textBase = new TextBase();
     *      textBase.setTextFill(Color.RED);
     *      textBase.setPromptTextFill(Color.WHITE);
     *      boolean isEmpty = textBase.isEmpty();
     *      Color textFill = textBase.getTextFill();
     * </pre>
     */
    public TextBase() {
        textFill = StyleablePropertiesCreator.StyleableObjectPropertyCreator.<Color>builder()
                                                                            .bean(this)
                                                                            .name("textFill")
                                                                            .cssMetaData(findCssMetaData("-fx-text-fill"))
                                                                            .defaultValue(Color.valueOf("#000000"))
                                                                            .build();

        promptTextFill = StyleablePropertiesCreator.StyleableObjectPropertyCreator.<Color>builder()
                                                                                  .bean(this)
                                                                                  .name("promptTextFill")
                                                                                  .cssMetaData(findCssMetaData("-fx-prompt-text-fill"))
                                                                                  .defaultValue(Color.valueOf("#000000"))
                                                                                  .build();

        textFill.addListener((obs, oldColor, newColor) -> updateTextWithNewColor(newColor));
        promptTextFill.addListener((obs, oldColor, newColor) -> updatePromptTextWithNewColor(newColor));
    }

    /**
     * Retrieves the TextField associated with this object.
     *
     * @return the TextField associated with this object
     */
    public abstract TextField getField();

    /**
     * Finds the CSS metadata for the given CSS property.
     *
     * @param cssProperty
     *         the CSS property for which to find the metadata
     * @param <S>
     *         the type of the styleable object
     * @param <V>
     *         the type of the CSS property value
     *
     * @return the CSS metadata for the given CSS property
     */
    protected <S extends Styleable, V> CssMetaData<S, V> findCssMetaData(String cssProperty) {
        return STYLEABLE_PROPERTIES_MANAGER.findCssMetaData(cssProperty);
    }

    /**
     * Checks if a property is non-null and not bound.
     *
     * @param property
     *         the property object to check
     * @param <T>
     *         the type of the property object must extend Property
     *
     * @return true if the property is non-null and not bound, false otherwise
     */
    protected static <T extends Property<?>> boolean checkProperty(T property) {
        return Objects.nonNull(property) && !property.isBound();
    }

    /**
     * Updates the style of a text field by changing the text fill color to a new color.
     *
     * @param newColor
     *         the new color to be applied to the text field
     */
    protected void updateTextWithNewColor(Color newColor) {
        Node node = getField();
        String baseStyle = node.getStyle()
                               .replaceAll("-fx-text-fill: #[0-9a-fA-F]+;", ""); //remove old color
        String newStyle = String.format("%s -fx-text-fill: #%s;", baseStyle, newColor.toString()
                                                                                     .substring(2)); //append new color
        node.setStyle(newStyle); //set the new style
    }

    /**
     * Updates the prompt text color of a text field with a new color.
     *
     * @param newColor
     *         The new color to be applied to the prompt text
     */
    protected void updatePromptTextWithNewColor(Color newColor) {
        Node node = getField();
        String baseStyle = node.getStyle()
                               .replaceAll("-fx-prompt-text-fill: #[0-9a-fA-F]+;", ""); //remove old color
        String newStyle = String.format("%s -fx-prompt-text-fill: #%s;", baseStyle, newColor.toString()
                                                                                            .substring(2)); //append new color
        node.setStyle(newStyle); //set the new style
    }

    /**
     * Returns a boolean value indicating whether the text field is empty or not.
     *
     * @return true if the text field is empty, false otherwise
     */
    public Boolean isEmpty() {
        return isEmpty.get();
    }

    /**
     * Checks if the property isEmpty is true or false.
     *
     * @return The boolean binding that represents the value of the isEmpty property.
     */
    public BooleanBinding isEmptyProperty() {
        return isEmpty;
    }

    /**
     * Gets the color of the text fill.
     *
     * @return the color of the text fill
     */
    public Color getTextFill() {
        return textFill.get();
    }

    /**
     * Returns the text fills property of this object.
     *
     * @return the text fill property
     */
    public StyleableObjectProperty<Color> textFillProperty() {
        return textFill;
    }

    /**
     * Sets the fill color of the text.
     *
     * @param textFill
     *         the color to set as the fill color of the text
     */
    public void setTextFill(Color textFill) {
        this.textFill.set(textFill);
    }
}
