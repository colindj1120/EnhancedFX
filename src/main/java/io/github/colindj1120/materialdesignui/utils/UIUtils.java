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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * The UIUtils class provides utility methods for working with UI elements.
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
}