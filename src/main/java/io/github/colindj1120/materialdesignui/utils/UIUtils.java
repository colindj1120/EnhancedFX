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
package io.github.colindj1120.materialdesignui.utils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * Provides utility methods for common UI operations in JavaFX applications, particularly those involving background management and alignment adjustments.
 *
 * <p>This class encapsulates functionality to ease the handling of backgrounds and alignment positions across various UI components, promoting a consistent visual design and simplifying code related
 * to UI styling.</p>
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
 * <p>This class is a part of the MaterialDesignUI library, aimed at enhancing JavaFX UI development
 * by providing a set of reusable utility methods following Material Design principles.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public class UIUtils {
    public static final BackgroundFill             TRANSPARENT_BACKGROUND_FILL = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY);
    public static final ObjectProperty<Background> TRANSPARENT_BACKGROUND      = new SimpleObjectProperty<>(new Background(TRANSPARENT_BACKGROUND_FILL));

    /**
     * Binds the background property of one {@link Region} to another. This method creates a unidirectional binding from the 'from' Region to the 'to' Region. After this binding is established, any
     * changes to the background property of the 'from' Region will automatically be reflected in the 'to' Region.
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
}