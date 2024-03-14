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
package io.github.colindj1120.enhancedfx.controls.control.efxcontrol;

import io.github.colindj1120.enhancedfx.controls.control.efxcontrol.base.EFXControlBase;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.controls.factory.configurators.controls.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.utils.ObjectUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.Control;

import java.util.List;
import java.util.Optional;

/**
 * Abstract class EFXControl provides a partial implementation for Controls used in the EnhancedFX library. The class contains common setup, state management, custom property definitions and efxTheme
 * management functionalities that apply to all controls derived from EFXControl.
 *
 * <p>
 * This class contains both abstract and concrete methods. The concrete methods implement functionalities that are shared across all types of controls in the EnhancedFX library. On the other hand, the abstract
 * methods represent functionalities that are unique to each type of control and as such must be provided by each subclass.
 * </p>
 *
 * <p>
 * An instance of EFXControl has access to a static `StyleablePropertiesManager` and `StringProperty` that provides a themeResourceDirectory. Each instance of the class is associated with a specific
 * Control and comes with methods to hook up the control with its desired properties, behaviors and its efxTheme changes.
 * </p>
 *
 * <p>
 * EFXControl contributes towards making EnhancedFX a highly customizable and easily themeable JavaFX UI control library. It supports dynamic efxTheme changes by automatically mapping stylesheets to controls
 * based on the currently selected efxTheme. The themeResourceDirectory can be globally changed to switch the look and feel of all controls derived from EFXControl in a JavaFX application.
 * </p>
 *
 * @param <T>
 *         the type of Control this EFXControl wraps.
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 * @see CustomControlConfigurator
 * @see StyleablePropertiesManager
 */
public abstract class EFXControl<T extends Control> extends EFXControlBase<T> {
    private static final StyleablePropertiesManager stylesManager = new StyleablePropertiesManager(Control.getClassCssMetaData());

    protected static ObjectProperty<EFXTheme> selectedTheme = new SimpleObjectProperty<>(EFXControl.class, "selectedTheme", EFXTheme.LIGHT_THEME);

    //region Abstract Functions
    //*****************************************************************
    // Abstract Functions
    //*****************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract EFXControl<?> getControl();

    //endregion Abstract Functions

    /**
     * Constructs an instance of EFXControl.
     *
     * <p>
     * This constructor calls the `super()` method to initialize the parent `Control`. It then sets up the styleable properties, configures the control, and updates the pseudo class states by calling the
     * respective methods: `setupStyleableProperties()`, `setupControl()`, and `updatePseudoClassStates()`.
     * </p>
     */
    public EFXControl() {
        super();
        setupStyleableProperties();
        setupControl();
        updatePseudoClassStates();
    }

    //region EFXControlBase Functions
    //*****************************************************************
    // EFXControlBase Functions
    //*****************************************************************

    /**
     * Sets up the control with appropriate configuration for custom behavior.
     *
     * <p>
     * This involves initializing a {@link CustomControlConfigurator} for the control, attaching listeners to focus and efxTheme change events. These listeners handle the effects of these changes on the control's
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
     * property changes, this listener loads a new efxTheme. The new efxTheme is defined as a stylesheet located at {@code themeResourceDirectory} path.
     * </p>
     */
    protected void setupControl() {
        CustomControlConfigurator.create(getControl())
                                 .addFocusedChangeListener(this::handleCustomControlFocusChange)
                                 .addObjectPropertyChangeListener(selectedTheme, this::loadNewTheme);
    }

    //endregion EFXControlBase Functions

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

    public static EFXTheme getSelectedTheme() {
        return selectedTheme.get();
    }

    public static ObjectProperty<EFXTheme> selectedThemeCssDirectoryProperty() {
        return selectedTheme;
    }

    public static void setThemeCssDirectory(EFXTheme efxTheme) {
        ObjectUtils.isNotNull(efxTheme, () -> "EFXTheme cannot be null.");

        EFXControl.selectedTheme.set(efxTheme);
    }

    //endregion Getters and Setters\

    //region EFXTheme Helper Functions
    //*****************************************************************
    // EFXTheme Helper Functions
    //*****************************************************************

    protected void loadNewThemeHelper(EFXStylesheets efxStylesheetsObj, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
        // Validate inputs using ObjectUtils for null or empty checks
        ObjectUtils.isNotNull(efxStylesheetsObj, () -> "EFXStylesheets object cannot be null.");
        ObjectUtils.isNotNull(oldEFXTheme, () -> "Old efxTheme name cannot be null.");
        ObjectUtils.isNotNull(newEFXTheme, () -> "New efxTheme name cannot be null.");

        // Ensure control and its stylesheets are not null
        Optional.ofNullable(getControl())
                .map(EFXControl::getStylesheets)
                .ifPresentOrElse(stylesheets -> {
                    // Directly use getStyleSheet which ensures the existence of the resource
                    String oldStylesheetPath = efxStylesheetsObj.getStyleSheet(oldEFXTheme.getThemeCssDirectory());
                    String newStylesheetPath = efxStylesheetsObj.getStyleSheet(newEFXTheme.getThemeCssDirectory());

                    // Since getStyleSheet throws an exception if the resource doesn't exist, there's no need for further existence checks here.
                    stylesheets.remove(oldStylesheetPath);
                    stylesheets.add(newStylesheetPath); // Add the new stylesheet

                }, () -> {
                    throw new IllegalStateException("Control or its stylesheet list cannot be null.");
                });
    }

    /**
     * Validates the existence of a stylesheet path for the specified efxTheme and class.
     *
     * <p>
     * This method ensures that the stylesheet associated with the given efxTheme exists within the application's resources. It uses the {@code efxStylesheetsObj} to resolve the stylesheet path for the specified
     * efxTheme, throwing an exception if the efxTheme directory is null, empty, or the resolved stylesheet path does not exist. This method is critical for verifying resource integrity before attempting to load or
     * apply efxTheme-related stylesheets.
     * </p>
     *
     * @param efxStylesheetsObj
     *         The {@link EFXStylesheets} object used to resolve stylesheet paths.
     * @param clazz
     *         The class context used to locate the stylesheet resource.
     *
     * @return The validated stylesheet path in external form, guaranteed to exist.
     *
     * @throws IllegalStateException
     *         if the efxTheme directory is null, empty, or the resolved stylesheet path does not exist, indicating a configuration or resource placement error.
     */
    protected String checkStylesheetPathExists(EFXStylesheets efxStylesheetsObj, Class<?> clazz) {
        // Ensure that efxStylesheetsObj and clazz are not null
        ObjectUtils.isNotNull(efxStylesheetsObj, () -> "EFXStylesheets object cannot be null.");
        ObjectUtils.isNotNull(clazz, () -> "Class object cannot be null.");

        // Retrieve efxTheme directory and resolve the stylesheet path, ensuring neither is null nor empty
        String theme = Optional.ofNullable(getSelectedThemeCssDirectory())
                               .filter(t -> !t.isEmpty())
                               .orElseThrow(() -> new IllegalStateException("EFXTheme Css Directory is null or empty."));

        // No further checks are required here since getStyleSheet ensures the path exists and is in external form.
        // If getResource(path) within getStyleSheet were null, it would have already thrown an IllegalArgumentException.
        return efxStylesheetsObj.getStyleSheet(theme);
    }

    //endregion EFXTheme Helper Functions

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
        return EFXControl.stylesManager.getCssMetaDataList();
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
