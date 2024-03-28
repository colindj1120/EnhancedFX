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
package io.github.colindj1120.enhancedfx.controls.css.base;

/**
 * Defines the contract for implementing styles within the EnhancedFX framework, offering a standardized approach to naming and accessing stylesheet resources across various themes.
 *
 * <p>The {@code EFXStyle} interface is crucial for the theming system of EnhancedFX, allowing for dynamic switching of visual themes and the consistent application of styles to UI components. Implementing
 * this interface enables an object to represent a specific style or set of styles, complete with a unique name and the ability to fetch the corresponding stylesheet URL based on the current theme.</p>
 *
 * <h2>Methods:</h2>
 * <ul>
 *     <li>{@code getStyleName} - Retrieves the name of the style, serving as a unique identifier within the system. This name can be used for logging, debugging, or dynamically applying styles at runtime.</li>
 *     <li>{@code getStyleSheet} - Accepts a theme name as input and returns the URL to the stylesheet file associated with that theme and style. This method is essential for theme-based styling, enabling
 *     the application to adapt its appearance dynamically in response to user preferences or other triggers.</li>
 * </ul>
 *
 * <p>Implementing classes or enums should provide specific logic for these methods, ensuring that styles can be effectively managed and applied within the EnhancedFX styling architecture. This approach
 * promotes modularity, reusability, and ease of maintenance in the design and implementation of UI themes.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 */
public interface EFXStyle {
    /**
     * Retrieves the name of the style. This name acts as a unique identifier for the style within the application, facilitating its selection and application to UI components.
     *
     * @return A {@link String} representing the unique name of the style.
     */
    String getStyleName();

    /**
     * Provides the URL to the stylesheet associated with this style for a given theme. This method enables the dynamic application of styles based on the current theme, supporting the customization and
     * flexibility of the UI's appearance.
     *
     * @param theme
     *         The name of the current theme, used to determine the correct stylesheet to be applied.
     *
     * @return A {@link String} representing the URL to the stylesheet file for the specified theme.
     *
     * @throws NullPointerException
     *         if the stylesheet resource cannot be found for the given theme, indicating a potential issue with the resource path or theme configuration.
     */
    String getStyleSheet(String theme);
}
