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
package io.github.colindj1120.materialdesignui.styling;

import io.github.colindj1120.materialdesignui.styling.base.Style;

import java.util.Objects;

/**
 * Enum defining the available stylesheets for the UI components in the MaterialDesignUI library. This enum implements the {@link Style} interface, providing a structured way to access stylesheet
 * paths and names associated with each style.
 *
 * <p>Each enum constant represents a distinct stylesheet for a specific component within
 * the library, encapsulating the path to the CSS file. This structure allows for easy retrieval of stylesheet details and integration with JavaFX components.</p>
 *
 * <p>Usage:</p>
 * <pre>
 * Scene scene = new Scene(root);
 * scene.getStylesheets().add(Stylesheets.ENHANCED_TEXT_FIELD.getStyleSheet());
 * </pre>
 *
 * <p>This approach centralizes the management of stylesheet paths, reducing the likelihood
 * of errors due to mis-typed strings and improving code readability.</p>
 *
 * <p>Example Stylesheet Enum:</p>
 * <ul>
 *     <li>{@code ENHANCED_TEXT_FIELD} - Represents the stylesheet for the EnhancedTextField component.</li>
 * </ul>
 *
 * <p>Implementation Notes:</p>
 * <ul>
 *     <li>{@code getStyleName()} returns the name of the enum constant, providing a simple identifier
 *     for the style.</li>
 *     <li>{@code getStyleSheet()} retrieves the external form URL of the stylesheet file, ensuring it
 *     can be directly used with JavaFX scene and control stylesheets collections.</li>
 * </ul>
 *
 * <p>It is important to ensure that the paths provided to the enum constructors are correct and
 * that the resources are properly included in the application's resources directory.</p>
 */
public enum Stylesheets implements Style {
    ENHANCED_TEXT_BASE("/css/EnhancedTextBase.css"),
    ENHANCED_TEXT_FIELD("/css/EnhancedTextField.css"),
    ENHANCED_TOGGLE_BUTTON("/css/EnhancedToggleButton.css");

    private final String stylesheet;

    /**
     * Constructor for the {@code Stylesheets} enum.
     *
     * @param stylesheet
     *         the relative path to the stylesheet file within the application's resources.
     */
    Stylesheets(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    /**
     * Returns the name of the style. This method provides a straightforward way to retrieve the enum constant's name as a string.
     *
     * @return the name of the style as defined by the enum constant.
     */
    @Override
    public String getStyleName() {
        return this.name();
    }

    /**
     * Retrieves the complete URL to the stylesheet file associated with this enum constant. This method ensures that the path to the stylesheet is correctly resolved and can be utilized by JavaFX
     * components.
     *
     * @return the external form URL of the stylesheet file.
     *
     * @throws NullPointerException
     *         if the resource cannot be found, indicating a missing file or incorrect path.
     */
    @Override
    public String getStyleSheet() {
        return Objects.requireNonNull(getClass().getResource(stylesheet))
                      .toExternalForm();
    }
}
