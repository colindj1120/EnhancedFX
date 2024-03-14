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
package io.github.colindj1120.enhancedfx.controls.base;

import io.github.colindj1120.enhancedfx.enums.styling.Theme;
import io.github.colindj1120.enhancedfx.factories.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.styling.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.enums.styling.Stylesheets;
import io.github.colindj1120.enhancedfx.utils.ObjectUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.Control;

import java.util.List;
import java.util.Optional;

import static io.github.colindj1120.enhancedfx.enums.styling.Theme.LIGHT_THEME;

/**
 * Abstract class EnhancedControl provides a partial implementation for Controls used in the EnhancedFX library. The class contains common setup, state management, custom property definitions and theme
 * management functionalities that apply to all controls derived from EnhancedControl.
 *
 * <p>
 * This class contains both abstract and concrete methods. The concrete methods implement functionalities that are shared across all types of controls in the EnhancedFX library. On the other hand, the abstract
 * methods represent functionalities that are unique to each type of control and as such must be provided by each subclass.
 * </p>
 *
 * <p>
 * An instance of EnhancedControl has access to a static `StyleablePropertiesManager` and `StringProperty` that provides a themeResourceDirectory. Each instance of the class is associated with a specific
 * Control and comes with methods to hook up the control with its desired properties, behaviors and its theme changes.
 * </p>
 *
 * <p>
 * EnhancedControl contributes towards making EnhancedFX a highly customizable and easily themeable JavaFX UI control library. It supports dynamic theme changes by automatically mapping stylesheets to controls
 * based on the currently selected theme. The themeResourceDirectory can be globally changed to switch the look and feel of all controls derived from EnhancedControl in a JavaFX application.
 * </p>
 *
 * @param <T>
 *         the type of Control this EnhancedControl wraps.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 * @see CustomControlConfigurator
 * @see StyleablePropertiesManager
 */
public abstract class EnhancedControl<T extends Control> extends EnhancedControlBase<T> {
    private static final StyleablePropertiesManager stylesManager = new StyleablePropertiesManager(Control.getClassCssMetaData());

    protected static ObjectProperty<Theme> selectedTheme = new SimpleObjectProperty<>(EnhancedControl.class, "selectedTheme", LIGHT_THEME);

    //region Abstract Functions
    //*****************************************************************
    // Abstract Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract EnhancedControl<?> getControl();

    //endregion Abstract Functions

    /**
     * Constructs an instance of EnhancedControl.
     *
     * <p>
     * This constructor calls the `super()` method to initialize the parent `Control`. It then sets up the styleable properties, configures the control, and updates the pseudo class states by calling the
     * respective methods: `setupStyleableProperties()`, `setupControl()`, and `updatePseudoClassStates()`.
     * </p>
     */
    public EnhancedControl() {
        super();
        setupStyleableProperties();
        setupControl();
        updatePseudoClassStates();
    }

    //region EnhancedControlBase Functions
    //*****************************************************************
    // EnhancedControlBase Functions
    //*****************************************************************

    /**
     * Sets up the control with appropriate configuration for custom behavior.
     *
     * <p>
     * This involves initializing a {@link CustomControlConfigurator} for the control, attaching listeners to focus and theme change events. These listeners handle the effects of these changes on the control's
     * appearance.
     * </p>
     *
     * <p>
     * The configurator is a tool to connect the control with its associated properties and behaviors. It simplifies control setup by handling mundane tasks that would otherwise need to be done manually for
     * each control.
     * </p>
     *
     * <p>
     * The method call {@code addFocusedChangeListener(this::handleCustomControlFocusChange)} attaches a listener to the control's focus property. When the control gains or loses focus, this listener executes
     * the {@code handleCustomControlFocusChange} method.
     * </p>
     *
     * <p>
     * The method call {@code addStringPropertyChangeListener(themeResourceDirectory, this::loadNewTheme)} attaches a listener to the {@code themeResourceDirectory} StringProperty. When the value of the
     * property changes, this listener loads a new theme. The new theme is defined as a stylesheet located at {@code themeResourceDirectory} path.
     * </p>
     */
    protected void setupControl() {
        CustomControlConfigurator.create(getControl())
                                 .addFocusedChangeListener(this::handleCustomControlFocusChange)
                                 .addObjectPropertyChangeListener(selectedTheme, this::loadNewTheme);
    }

    //endregion EnhancedControlBase Functions

    //region Listener Functions
    //*****************************************************************
    // Listener Functions
    //*****************************************************************

    /**
     * Handles the focus change event of a custom control.
     *
     * <p>
     * When a custom control gains focus, it delegates the focus to its inner control. In essence, this method checks if the custom control is now focused. If so, it requests the inner control to take the
     * focus.
     * </p>
     *
     * @param observableValue
     *         The ObservableValue which value was changed.
     * @param oldFocus
     *         The old focus state of the custom control before the change occurred.
     * @param isFocused
     *         The new focus state of the custom control after the change, if the custom control is now focused, it delegates this focus to the inner control by calling its {@code requestFocus} method.
     */
    private void handleCustomControlFocusChange(ObservableValue<? extends Boolean> observableValue, Boolean oldFocus, Boolean isFocused) {
        if (isFocused) {
            this.getInnerControl()
                .requestFocus();
        }
    }

    //endregion Listener Functions

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    public static String getSelectedThemeCssDirectory() {
        return selectedTheme.get().getThemeCssDirectory();
    }

    public static Theme getSelectedTheme() {
        return selectedTheme.get();
    }

    public static ObjectProperty<Theme> selectedThemeCssDirectoryProperty() {
        return selectedTheme;
    }

    public static void setThemeCssDirectory(Theme theme) {
        ObjectUtils.isNotNull(theme, () -> "Theme cannot be null.");

        EnhancedControl.selectedTheme.set(theme);
    }

    //endregion Getters and Setters\

    //region Theme Helper Functions
    //*****************************************************************
    // Theme Helper Functions
    //*****************************************************************

    protected void loadNewThemeHelper(Stylesheets stylesheetsObj, Theme oldTheme, Theme newTheme) {
        // Validate inputs using ObjectUtils for null or empty checks
        ObjectUtils.isNotNull(stylesheetsObj, () -> "Stylesheets object cannot be null.");
        ObjectUtils.isNotNull(oldTheme, () -> "Old theme name cannot be null.");
        ObjectUtils.isNotNull(newTheme, () -> "New theme name cannot be null.");

        // Ensure control and its stylesheets are not null
        Optional.ofNullable(getControl())
                .map(EnhancedControl::getStylesheets)
                .ifPresentOrElse(stylesheets -> {
                    // Directly use getStyleSheet which ensures the existence of the resource
                    String oldStylesheetPath = stylesheetsObj.getStyleSheet(oldTheme.getThemeCssDirectory());
                    String newStylesheetPath = stylesheetsObj.getStyleSheet(newTheme.getThemeCssDirectory());

                    // Since getStyleSheet throws an exception if the resource doesn't exist, there's no need for further existence checks here.
                    stylesheets.remove(oldStylesheetPath);
                    stylesheets.add(newStylesheetPath); // Add the new stylesheet

                }, () -> {
                    throw new IllegalStateException("Control or its stylesheet list cannot be null.");
                });
    }

    /**
     * Validates the existence of a stylesheet path for the specified theme and class.
     *
     * <p>
     * This method ensures that the stylesheet associated with the given theme exists within the application's resources. It uses the {@code stylesheetsObj} to resolve the stylesheet path for the specified
     * theme, throwing an exception if the theme directory is null, empty, or the resolved stylesheet path does not exist. This method is critical for verifying resource integrity before attempting to load or
     * apply theme-related stylesheets.
     * </p>
     *
     * @param stylesheetsObj
     *         The {@link Stylesheets} object used to resolve stylesheet paths.
     * @param clazz
     *         The class context used to locate the stylesheet resource.
     *
     * @return The validated stylesheet path in external form, guaranteed to exist.
     *
     * @throws IllegalStateException
     *         if the theme directory is null, empty, or the resolved stylesheet path does not exist, indicating a configuration or resource placement error.
     */
    protected String checkStylesheetPathExists(Stylesheets stylesheetsObj, Class<?> clazz) {
        // Ensure that stylesheetsObj and clazz are not null
        ObjectUtils.isNotNull(stylesheetsObj, () -> "Stylesheets object cannot be null.");
        ObjectUtils.isNotNull(clazz, () -> "Class object cannot be null.");

        // Retrieve theme directory and resolve the stylesheet path, ensuring neither is null nor empty
        String theme = Optional.ofNullable(getSelectedThemeCssDirectory())
                               .filter(t -> !t.isEmpty())
                               .orElseThrow(() -> new IllegalStateException("Theme Css Directory is null or empty."));

        // No further checks are required here since getStyleSheet ensures the path exists and is in external form.
        // If getResource(path) within getStyleSheet were null, it would have already thrown an IllegalArgumentException.
        return stylesheetsObj.getStyleSheet(theme);
    }

    //endregion Theme Helper Functions

    //region CSS Metadata
    //*****************************************************************
    // CSS Metadata
    //*****************************************************************

    /**
     * Retrieves the CSS metadata associated with the class.
     *
     * @return An unmodifiable list of CssMetaData objects representing the CSS properties that can be applied to this class.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return EnhancedControl.stylesManager.getCssMetaDataList();
    }

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {return getClassCssMetaData();}

    //endregion CSS Metadata
}
