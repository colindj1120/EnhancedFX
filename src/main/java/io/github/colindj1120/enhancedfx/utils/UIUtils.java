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
package io.github.colindj1120.enhancedfx.utils;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Provides utility methods for common UI operations in JavaFX applications, particularly those involving background management
 * and alignment adjustments.
 *
 * <p>This class encapsulates functionality to ease the handling of backgrounds and alignment positions across various UI
 * components, promoting a consistent visual design and simplifying code related to UI styling.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *     <li>Management of transparent backgrounds with predefined static properties.</li>
 *     <li>Unidirectional binding of background properties between {@link Region} objects to
 *     synchronize their visual appearance dynamically.</li>
 *     <li>Conversion of generic alignment positions to their corresponding baseline positions
 *     for uniform text positioning and layout consistency.</li>
 * </ul>
 *
 * <p>Usage of these utilities can significantly reduce boilerplate code associated with
 * UI styling and layout management, allowing developers to focus more on business logic and
 * less on the intricacies of JavaFX's layout mechanisms.</p>
 *
 * <p>Example Usage:</p>
 * <pre>
 *     // Creating transparent backgrounds for a consistent UI design
 *     VBox vbox = new VBox();
 *     vbox.setBackground(UIUtils.TRANSPARENT_BACKGROUND.get());
 *
 *     // Synchronizing backgrounds between two regions
 *     HBox hbox = new HBox();
 *     UIUtils.bindBackgrounds(vbox, hbox); // vbox's background now follows hbox's
 *
 *     // Standardizing alignment across different components
 *     Pos alignment = UIUtils.convertToBaseline(Pos.CENTER_RIGHT);
 *     hbox.setAlignment(alignment);
 * </pre>
 *
 * <p>This class is a part of the EnhancedFX library, aimed at enhancing JavaFX UI development
 * by providing a set of reusable utility methods following Material Design principles.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public class UIUtils {
    /**
     * Private constructor to prevent instantiation of the {@code UIUtils} class.
     *
     * <p>
     * This utility class is designed to provide static methods and should not be instantiated or extended. The private constructor enforces this design pattern by prohibiting instantiation of
     * {@code UIUtils}.
     * </p>
     */
    private UIUtils() {}

    public static final BackgroundFill             TRANSPARENT_BACKGROUND_FILL;
    public static final Background                 TRANSPARENT_BACKGROUND;
    public static final ObjectProperty<Background> TRANSPARENT_BACKGROUND_PROPERTY;

    static {
        TRANSPARENT_BACKGROUND_FILL = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY);
        TRANSPARENT_BACKGROUND = new Background(TRANSPARENT_BACKGROUND_FILL);
        TRANSPARENT_BACKGROUND_PROPERTY = new SimpleObjectProperty<>(TRANSPARENT_BACKGROUND);
    }

    /**
     * Binds the background property of one {@link Region} to another. This method creates a unidirectional binding from the
     * 'from' Region to the 'to' Region. After this binding is established, any changes to the background property of the 'from'
     * Region will automatically be reflected in the 'to' Region.
     *
     * <p>Usage of this method simplifies the process of maintaining consistent styling
     * across different UI components in JavaFX, especially when dynamic changes to the background are required.</p>
     *
     * <p>This binding is unidirectional; changes in the 'to' Region's background will not
     * affect the 'from' Region.</p>
     *
     * <p>Example Usage:</p>
     * <pre>
     *     VBox vbox = new VBox();
     *     HBox hbox = new HBox();
     *     bindBackgrounds(vbox, hbox); // vbox's background will now follow hbox's background
     * </pre>
     *
     * @param to
     *         The {@link Region} whose background property will be bound to another.
     * @param from
     *         The {@link Region} whose background property is to be followed.
     */
    public static void bindBackgrounds(Region to, Region from) {
        to.backgroundProperty()
          .bind(from.backgroundProperty());
    }

    /**
     * Converts the given alignment position to its corresponding baseline alignment position.
     *
     * @param alignment
     *         the alignment position to be converted
     *
     * @return the corresponding baseline alignment position
     */
    public static Pos convertToBaseline(Pos alignment) {
        if (Objects.isNull(alignment)) {
            return Pos.BASELINE_LEFT;
        }

        return switch (alignment) {
            case TOP_LEFT, CENTER_LEFT, BOTTOM_LEFT, BASELINE_LEFT -> Pos.BASELINE_LEFT;
            case TOP_CENTER, CENTER, BOTTOM_CENTER, BASELINE_CENTER -> Pos.BASELINE_CENTER;
            case TOP_RIGHT, CENTER_RIGHT, BOTTOM_RIGHT, BASELINE_RIGHT -> Pos.BASELINE_RIGHT;
        };
    }

    /**
     * Sets the font of a label to a new font based on the properties of the old font.
     *
     * @param label
     *         the label whose font will be changed
     * @param oldFont
     *         the old font to use as a reference
     * @param newFont
     *         the new font to set for the label
     */
    public static void setLabelFont(Label label, Font oldFont, Font newFont) {
        //@formatter:off
        label.setFont(Font.font(newFont.getFamily(),
                                FontWeight.findByName(oldFont.getStyle()),
                                FontPosture.findByName(oldFont.getStyle()),
                                newFont.getSize()));
        //@formatter:on
    }

    /**
     * Creates a {@link ChangeListener} that manages the visibility of a {@link Label} within a component.
     *
     * <p>
     * This listener is designed to add or remove the specified label from a given list of children nodes based on the label's
     * visibility changes, ensuring that the label is displayed in the component only when it is visible. Additionally, it
     * requests a layout pass on the control to which the label belongs whenever the visibility changes, ensuring that the
     * control's layout is updated to reflect the presence or absence of the label.
     * </p>
     *
     * <p>
     * This method is particularly useful for components that need to dynamically show or hide auxiliary text, such as error
     * messages or hints, based on certain conditions. By attaching this listener to the visibility property of a label,
     * developers can easily incorporate dynamic text elements into custom controls without manually managing the addition and
     * removal of the label from the control's children list.
     * </p>
     *
     * @param label
     *         The {@link Label} whose visibility changes are to be managed. This label is added to or removed from the specified
     *         children list based on its visibility.
     * @param children
     *         The {@link ObservableList} of {@link Node}s representing the children of the component. This list is modified to
     *         include or exclude the label based on its visibility.
     * @param control
     *         The {@link Control} to which the label belongs.
     * @param <T>
     *         The specific type of {@link Control} that is being managed. This allows the method to be used with various types of
     *         controls that derive from the {@link Control} class.
     *
     * @return A {@link ChangeListener<Boolean>} that reacts to changes in the label's visibility property by adding or removing
     *         the label from the children list and requesting a layout update on the control.
     */
    @NotNull
    public static <T extends Control> ChangeListener<Boolean> manageLabelVisibility(Label label,
                                                                                    ObservableList<Node> children,
                                                                                    T control) {
        return (obs, oldVisible, isVisible) -> {
            if (isVisible) {
                children.add(label);
            } else {
                children.remove(label);
            }
            control.requestLayout();
        };
    }

    /**
     * Updates the style of a {@link Node} by replacing or adding a new color value for a specified style property. This method
     * first removes any existing value for the specified style property and then appends the new color value to the node's
     * existing style.
     *
     * @param newColor
     *         The new {@link Color} value to apply to the style property.
     * @param node
     *         The {@link Node} whose style is to be updated.
     * @param style
     *         The CSS style property (e.g., "-fx-fill", "-fx-background-color") to which the new color is applied.
     */
    public static void updateStyleWithNewColor(Color newColor, Node node, String style) {
        String baseStyle = node.getStyle()
                               .replaceAll(String.format("%s: #[0-9a-fA-F]+;", style), ""); //remove old color
        String newStyle = String.format("%s %s: #%s;", baseStyle, style, newColor.toString()
                                                                                 .substring(2)); //append new color
        node.setStyle(newStyle); //set the new style
    }

    /**
     * Creates an {@link InvalidationListener} that triggers a layout request for a specified {@link Control} whenever it is
     * invalidated.
     *
     * <p>This listener is designed to be used with JavaFX controls to automatically request a layout pass whenever the control
     * is invalidated. By attaching this listener to a control's invalidation event, developers can ensure that the control's
     * layout is updated whenever its state changes.</p>
     *
     * @param control
     *         The {@link Control} for which the layout request will be triggered.
     * @param <T>
     *         The specific type of {@link Control}.
     *
     * @return An {@link InvalidationListener} that invokes the {@code requestLayout()} method on the specified control whenever
     *         it is invalidated.
     */
    public static <T extends Control> InvalidationListener requestControlLayout(T control) {
        return invalidated -> control.requestLayout();
    }
}