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
package io.github.colindj1120.enhancedfx.enums.styling;

import io.github.colindj1120.enhancedfx.styling.base.Style;
import io.github.colindj1120.enhancedfx.utils.ObjectUtils;

import java.util.Objects;

/**
 * Enumerates the CSS stylesheets associated with different UI components within the EnhancedFX framework, facilitating easy access and application of styles to enhance the visual presentation and user
 * experience.
 *
 * <p>
 * The {@code Stylesheets} enum centralizes the management of CSS resources, allowing for consistent styling across the application while supporting dynamic theme switching. Each enum constant represents a
 * specific stylesheet for a category of UI components, such as text fields, buttons, and supported controls, ensuring that styles are appropriately segregated and applied.
 * </p>
 *
 * <h2>Stylesheet Paths:</h2>
 * <p>
 * <ul>
 *     <li>{@code ENHANCED_TEXT_BASE} - Path to the stylesheet for base text components.</li>
 *     <li>{@code ENHANCED_TEXT_FIELD} - Path to the stylesheet for enhanced text field components.</li>
 *     <li>{@code ENHANCED_TEXT_AREA} - Path to the stylesheet for enhanced text area components.</li>
 *     <li>{@code ENHANCED_BUTTON} - Path to the stylesheet for button components.</li>
 *     <li>{@code ENHANCED_TOGGLE_BUTTON} - Path to the stylesheet for toggle button components.</li>
 *     <li>{@code ENHANCED_SUPPORTED_CONTROL} - Path to the stylesheet for the supporting text component.</li>
 * </ul>
 * </p>
 *
 * <h2>Functionality:</h2>
 * <p>
 * <ul>
 *     <li>{@code getStyleName} - Retrieves the name of the style as defined by the enum constant.</li>
 *     <li>{@code getStyleSheet} - Composes and retrieves the full URL to the stylesheet resource for a given theme, ensuring the correct application of themed styles to UI components.</li>
 *     <li>{@code toString} - Provides a string representation of the enum constant, useful for logging and debugging.</li>
 *     <li>{@code toLowerCase} - Returns a lowercase version of the enum constant's name, aiding in case-insensitive operations.</li>
 *     <li>{@code toTitleCase} - Transforms the enum constant's name into title case for improved readability, particularly in user interfaces or documentation.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This enumeration is integral to the EnhancedFX styling mechanism, simplifying the process of applying and managing CSS across different components and themes. By leveraging {@code Stylesheets}, developers
 * can ensure a coherent and flexible styling strategy throughout the application.
 * </p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public enum Stylesheets implements Style {
    ENHANCED_TEXT_BASE("%s/EFXTextBase.css"),
    ENHANCED_TEXT_FIELD("%s/EFXTextField.css"),
    ENHANCED_TEXT_AREA("%s/EnhancedTextArea.css"),
    ENHANCED_BUTTON("%s/EFXButton.css"),
    ENHANCED_TOGGLE_BUTTON("%s/EFXToggleButton.css"),
    ENHANCED_SUPPORTED_CONTROL("%s/EFXSupportedControl.css");

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
     * Retrieves the stylesheet URL in external form for the specified theme.
     *
     * <p>
     * This method formats the stylesheet path based on the given theme and checks for its existence within the application's resources. If the resource is found, it returns the path in an external form
     * suitable for use in JavaFX styling. If the resource does not exist, it throws an informative exception detailing the missing resource and the attempted path, ensuring easier debugging and configuration
     * validation.
     * </p>
     *
     * <p>
     * The method leverages {@code ObjectUtils.checkResourcePathNotNull} to validate the presence of the resource. This validation step ensures that an {@link IllegalArgumentException} is thrown if the resource
     * cannot be located, preventing the method from returning a null or invalid stylesheet path. As such, when this method returns successfully, the caller is guaranteed to receive a valid, non-null stylesheet
     * URL.
     * </p>
     *
     * <p>
     * Note: The method assumes the resource path constructed from the given theme always corresponds to a valid resource. If the application's resources or themes are modified, ensure that the path formatting
     * logic and resource organization are kept in sync.
     * </p>
     *
     * @param theme
     *         The theme identifier used to construct the stylesheet resource path.
     *
     * @return The external form URL of the stylesheet resource corresponding to the specified theme.
     *
     * @throws IllegalArgumentException
     *         if the constructed resource path does not correspond to an existing resource, with a detailed message for troubleshooting.
     */
    @Override
    public String getStyleSheet(String theme) {
        String path = String.format(stylesheet, theme);

        // Use the utility method to check if the resource exists, throwing a detailed exception if not
        ObjectUtils.checkResourcePathNotNull(path, () -> String.format("Resource for theme '%s' not found. Attempted path: '%s'", theme, path));

        // Since checkResourcePathNotNull would throw an exception if the resource doesn't exist,
        // the following line will only execute if the resource URL is non-null.
        return Objects.requireNonNull(getClass().getResource(path))
                      .toExternalForm();
    }

    /**
     * Returns the name of the enum constant, as declared in its enum declaration.
     *
     * <p>
     * Overrides the {@code toString} method to provide a direct string representation of the enum constant, facilitating easier logging and debugging. This representation matches the exact declaration name of
     * the constant.
     * </p>
     *
     * @return A {@link String} representing the name of the enum constant.
     */
    @Override
    public String toString() {
        return name();
    }

    /**
     * Converts the name of the enum constant to lowercase.
     *
     * <p>
     * This method facilitates scenarios where a case-insensitive representation of the enum constant is required, such as for user-friendly displays or when interfacing with systems that do not recognize case
     * sensitivity.
     * </p>
     *
     * @return A {@link String} representing the enum constant's name in lowercase letters.
     */
    public String toLowerCase() {
        return name().toLowerCase();
    }

    /**
     * Converts the name of the enum constant to title case.
     *
     * <p>
     * This method is useful for presenting the enum constant in a more human-readable form, particularly in user interfaces or reports. In title case, only the first letter of the enum constant's name is
     * capitalized, enhancing readability while maintaining its recognizability.
     * </p>
     *
     * @return A {@link String} representing the enum constant's name in title case, with the first letter capitalized and the rest in lowercase.
     */
    public String toTitleCase() {
        String lowerCase = name().toLowerCase();
        return lowerCase.substring(0, 1)
                        .toUpperCase() + lowerCase.substring(1);
    }
}
