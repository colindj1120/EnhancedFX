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
package io.github.colindj1120.enhancedfx.controls.css;

/**
 * Enumerates the themes available within the EnhancedFX framework, providing a centralized way to manage and apply CSS themes for the UI components. This facilitates the dynamic switching between light and
 * dark modes, enhancing the visual presentation and user experience across the application.
 *
 * <p>The {@code EFXTheme} enum simplifies the theme management by associating each theme with its corresponding CSS directory. This approach allows for consistent and easy application of themes, supporting
 * a seamless integration of varied visual styles. Each enum constant represents a distinct theme, with its associated CSS directory containing the necessary stylesheet files to implement the theme's
 * appearance.</p>
 *
 * <h2>Available Themes:</h2>
 * <ul>
 *     <li>{@code LIGHT_THEME} - Path to the directory containing stylesheets for the light theme.</li>
 *     <li>{@code DARK_THEME} - Path to the directory containing stylesheets for the dark theme.</li>
 * </ul>
 *
 * <h2>Functionality:</h2>
 * <ul>
 *     <li>{@code getThemeCssDirectory} - Retrieves the CSS directory path associated with a theme, enabling the application of the theme's styles to the UI components.</li>
 *     <li>{@code toString} - Provides a string representation of the theme, including its name and CSS directory, useful for logging and debugging purposes.</li>
 *     <li>{@code toStringLowerCase} - Returns a lowercase version of the theme's name and CSS directory path, aiding in case-insensitive operations and consistency in UI display or documentation.</li>
 *     <li>{@code toStringTitleCase} - Transforms the theme's name into title case for improved readability, particularly in user interfaces or documentation, while maintaining the original CSS directory
 *     path.</li>
 *     <li>{@code lowerCaseName} - Converts the theme's name to lowercase, facilitating operations and comparisons that are case-insensitive.</li>
 *     <li>{@code titleCaseName} - Converts the theme's name to title case, enhancing the visual presentation of the theme name in user interfaces or documentation.</li>
 * </ul>
 *
 * <p>By utilizing {@code EFXTheme}, developers can easily switch between light and dark themes, or introduce additional themes, thereby ensuring a versatile and adaptable styling strategy that enhances
 * the user interface's appearance and adaptability.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum EFXTheme {
    LIGHT_THEME("/css/light"),
    DARK_THEME("/css/dark");

    /**
     * Directory where the theme's CSS is stored.
     */
    private final String themeCssDirectory;

    /**
     * Constructs an EFXTheme with a specified CSS directory.
     *
     * @param themeCssDirectory
     *         The directory where the theme's CSS is located.
     */
    EFXTheme(String themeCssDirectory) {
        this.themeCssDirectory = themeCssDirectory;
    }

    /**
     * Retrieves the CSS directory of the theme.
     *
     * @return The CSS directory path of the theme.
     */
    public String getThemeCssDirectory() {
        return themeCssDirectory;
    }

    /**
     * Provides a string representation of the theme including its name and CSS directory.
     *
     * @return A formatted string with the theme's name and CSS directory.
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Css Directory: %s", name(), getThemeCssDirectory());
    }

    /**
     * Provides a lower-case string representation of the theme's name and CSS directory.
     *
     * @return A formatted string with the theme's name in lower-case and CSS directory.
     */
    public String toStringLowerCase() {
        return String.format("Name: %s | Css Directory: %s", name().toLowerCase(), getThemeCssDirectory());
    }

    /**
     * Provides a title-case string representation of the theme's name and CSS directory.
     *
     * @return A formatted string with the theme's name in title-case and CSS directory.
     */
    public String toStringTitleCase() {
        String titleCaseName = getTitleCaseName();
        return String.format("Name: %s | Css Directory: %s", titleCaseName, getThemeCssDirectory());
    }

    /**
     * Converts the theme's name to lower-case.
     *
     * @return The theme's name in lower-case.
     */
    public String lowerCaseName() {
        return name().toLowerCase();
    }

    /**
     * Converts the theme's name to title-case.
     *
     * @return The theme's name in title-case.
     */
    public String titleCaseName() {
        return getTitleCaseName();
    }

    /**
     * Helper method to convert the theme's name to title-case.
     *
     * @return The theme's name in title-case.
     */
    private String getTitleCaseName() {
        String lowerCase = name().toLowerCase();
        return lowerCase.substring(0, 1)
                        .toUpperCase() + lowerCase.substring(1);
    }
}
