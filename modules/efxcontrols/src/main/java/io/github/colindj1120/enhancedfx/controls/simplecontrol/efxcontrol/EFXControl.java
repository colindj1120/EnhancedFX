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
package io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol;

import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.factory.controlconfigurators.custom.customcontrol.CustomControlConfigurator;
import io.github.colindj1120.enhancedfx.controls.css.EFXStylesheets;
import io.github.colindj1120.enhancedfx.controls.css.EFXTheme;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxcontrol.base.EFXControlBase;
import io.github.colindj1120.enhancedfx.utils.EFXObjectUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.control.Control;

import java.util.List;
import java.util.Optional;

/**
 * The {@code EFXControl} class extends {@code EFXControlBase} to provide a generic framework for creating enhanced controls within the EnhancedFX UI toolkit.
 *
 * <p>It incorporates advanced theme management, CSS styling capabilities, and event handling mechanisms to facilitate the development of rich, interactive user interface components.</p>
 *
 * <h2>Capabilities:</h2>
 * <ul>
 *     <li><em>Dynamic Theme Support:</em> Seamlessly switch between light and dark themes, or any custom themes defined within the {@code EFXTheme} enum, without needing to restart the application.</li>
 *     <li><em>Automated Style Management:</em> Utilizes a {@code StyleablePropertiesManager} to manage CSS metadata and styleable properties, simplifying the process of applying and updating CSS styles
 *     dynamically.</li>
 *     <li><em>Event Handling:</em> Implements a structured approach to adding focus and theme change listeners, ensuring controls react appropriately to user interactions and theme changes.</li>
 *     <li><em>Resource Validation:</em> Provides utility methods for validating the existence of stylesheet paths associated with selected themes, preventing runtime errors due to missing resources.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <em>Here's a simple usage example that demonstrates how to create a custom control extending {@code EFXControl}:</em>
 * <pre>
 * {@code
 * public class MyCustomControl extends EFXControl<Button> {
 *     private Button button = new Button("Click Me!");
 *
 *     public MyCustomControl() {
 *         super();
 *         // Additional initialization here
 *     }
 *
 *     @Override
 *     protected EFXControl<?> getControl() {
 *         return this;
 *     }
 *
 *     @Override
 *     protected void setupControl() {
 *         // Set up the control, e.g., attach event listeners
 *         button.setOnAction(event -> System.out.println("Button clicked!"));
 *         getChildren().add(button); // Add the button as a child of this control
 *     }
 *
 *     @Override
 *     protected void setupStyleableProperties() {
 *         // Define custom styleable properties if necessary
 *     }
 *
 *     @Override
 *     protected void updatePseudoClassStates() {
 *         // Update CSS pseudo-class states as needed
 *     }
 *
 *     @Override
 *     public Button getInnerControl() {
 *         return button; // Return the inner control
 *     }
 * }
 * }
 * </pre>
 *
 * <p>This example showcases the basic structure of a custom control extending {@code EFXControl}. The developer is responsible for implementing the abstract methods to specify the behavior, appearance, and
 * internal components of the control.</p>
 *
 * <p>By leveraging the capabilities of {@code EFXControl}, developers can create versatile and dynamically-styled controls with full support for the EnhancedFX theming system, enhancing the overall user
 * experience of JavaFX applications.</p>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see Control
 * @see EFXControlBase
 * @see EFXTheme
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
     * <p>This constructor calls the `super()` method to initialize the parent `Control`. It then sets up the styleable properties, configures the control, and updates the pseudo class states by calling the
     * respective methods: `setupStyleableProperties()`, `setupControl()`, and `updatePseudoClassStates()`.</p>
     */
    protected EFXControl() {
        super();
    }

    //region EFXControlBase Functions
    //*****************************************************************
    // EFXControlBase Functions
    //*****************************************************************

    /**
     * Sets up the control with appropriate configuration for custom behavior.
     *
     * <h2>Function Steps:</h2>
     * <em>This process is divided into several key steps to ensure the control is fully configured for its intended use within the EnhancedFX framework:</em>
     * <ol>
     *     <li><em>Control Configurator Initialization:</em> A {@link CustomControlConfigurator} instance is initialized for the control. This configurator acts as a bridge, linking the control with its
     *     associated properties and behaviors. It simplifies the control's setup process by automating common setup tasks.</li>
     *     <li><em>Focus Change Listener Attachment:</em> The configurator attaches a listener to the control's focus property via the method call {@code addFocusedChangeListener
     *     (this::handleCustomControlFocusChange)}. This listener is responsible for executing the {@code handleCustomControlFocusChange} method whenever the control's focus state changes, allowing for
     *     custom focus behavior.</li>
     *     <li><em>Theme Change Listener Attachment:</em> Similarly, the method call {@code addStringPropertyChangeListener(themeResourceDirectory, this::loadNewTheme)} attaches a listener to the {@code
     *     themeResourceDirectory} StringProperty. This ensures that any changes to the theme resource directory trigger the loading of a new theme, with the {@code loadNewTheme} method adapting the
     *     control's appearance according to the new stylesheet defined at the {@code themeResourceDirectory} path.</li>
     * </ol>
     *
     * <p>Through these steps, the control is fully prepared to respond to focus and theme changes, demonstrating EnhancedFX's ability to dynamically adjust control behavior and appearance.
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
     * <p>When a custom control gains focus, it delegates the focus to its inner control. In essence, this method checks if the custom control is now focused. If so, it requests the inner control to take the
     * focus.</p>
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

    /**
     * Retrieves the CSS directory path of the selected theme.
     *
     * @return The CSS directory path of the selected theme.
     */
    public static String getSelectedThemeCssDirectory() {
        return selectedTheme.get()
                            .getThemeCssDirectory();
    }

    /**
     * Returns the currently selected theme.
     *
     * @return The currently selected theme.
     */
    public static EFXTheme getSelectedTheme() {
        return selectedTheme.get();
    }

    /**
     * Retrieves the selectedThemeCssDirectoryProperty.
     *
     * @return The ObjectProperty representing the selected theme CSS directory.
     *
     * @since 1.0
     */
    public static ObjectProperty<EFXTheme> selectedThemeCssDirectoryProperty() {
        return selectedTheme;
    }

    /**
     * Sets the directory for the CSS stylesheets associated with the provided EFXTheme.
     *
     * @param efxTheme
     *         The EFXTheme to set the CSS directory for.
     *
     * @throws IllegalArgumentException
     *         if efxTheme is null.
     */
    public static void setThemeCssDirectory(EFXTheme efxTheme) {
        EFXObjectUtils.isNotNull(efxTheme, () -> "EFXTheme cannot be null.");

        EFXControl.selectedTheme.set(efxTheme);
    }

    //endregion Getters and Setters\

    //region EFXTheme Helper Functions
    //*****************************************************************
    // EFXTheme Helper Functions
    //*****************************************************************

    /**
     * Assists in the process of transitioning the current control to a new theme by dynamically updating its associated stylesheets.
     *
     * <p>This method encapsulates the validation of necessary parameters, the retrieval of stylesheet paths based on theme information, and the replacement of the old theme's stylesheet with that of the new
     * theme.</p>
     *
     * <h2>Key Function Steps:</h2>
     * <ol>
     *     <li><em>Validation of Inputs:</em> Ensures that none of the parameters ({@code efxStylesheetsObj}, {@code oldEFXTheme}, {@code newEFXTheme}) are null, using {@code EFXObjectUtils}. Each parameter
     *     has a specific check to provide a clear error message if the validation fails.</li>
     *     <li><em>Retrieval of Stylesheet Paths:</em> Utilizes the {@code efxStylesheetsObj} to fetch the stylesheet paths corresponding to both the old and new themes. The paths are derived from each
     *     theme's CSS directory, ensuring that only valid resources are referenced.</li>
     *     <li><em>Update of Stylesheets:</em> Accesses the control's current stylesheet collection. If the control and its stylesheets are valid (not null), the method proceeds to remove the stylesheet
     *     associated with the old theme and add the one associated with the new theme. This operation is critical for applying the visual aspects of the new theme to the control.</li>
     *     <li><em>Error Handling:</em> In cases where the control or its stylesheet list is unexpectedly null, an {@code IllegalStateException} is thrown, indicating a fundamental issue that prevents the
     *     theme update process from proceeding.</li>
     * </ol>
     *
     * <p>This method plays a crucial role in facilitating dynamic theme changes within the EnhancedFX framework, enabling controls to adapt their appearance seamlessly in response to user preferences or
     * application-wide theme adjustments.</p>
     *
     * @param efxStylesheetsObj
     *         An instance of {@link EFXStylesheets} providing access to the stylesheet paths for different themes.
     * @param oldEFXTheme
     *         The currently applied theme, which is to be replaced.
     * @param newEFXTheme
     *         The new theme to apply to the control.
     *
     * @throws NullPointerException
     *         If any of the method parameters are null, detailed error messages are provided to aid in diagnosing the cause of the null reference.
     * @throws IllegalStateException
     *         If the control or its stylesheet list is null, preventing the theme update process.
     */
    protected void loadNewThemeHelper(EFXStylesheets efxStylesheetsObj, EFXTheme oldEFXTheme, EFXTheme newEFXTheme) {
        // Validate inputs using EFXObjectUtils for null
        EFXObjectUtils.isNotNull(efxStylesheetsObj, () -> "EFXStylesheets object cannot be null.");
        EFXObjectUtils.isNotNull(oldEFXTheme, () -> "Old efxTheme name cannot be null.");
        EFXObjectUtils.isNotNull(newEFXTheme, () -> "New efxTheme name cannot be null.");

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
     * <p>This method ensures that the stylesheet associated with the given efxTheme exists within the application's resources. It uses the {@code efxStylesheetsObj} to resolve the stylesheet path for the
     * specified efxTheme, throwing an exception if the efxTheme directory is null, empty, or the resolved stylesheet path does not exist. This method is critical for verifying resource integrity before
     * attempting to load or apply efxTheme-related stylesheets.</p>
     *
     * @param efxStylesheetsObj
     *         The {@link EFXStylesheets} object used to resolve stylesheet paths.
     * @param clazz
     *         The class context used to locate the stylesheet resource.
     *
     * @return The validated stylesheet path in external form, guaranteed to exist.
     *
     * @throws IllegalStateException
     *         If the efxTheme directory is null, empty, or the resolved stylesheet path does not exist, indicating a configuration or resource placement error.
     */
    protected String checkStylesheetPathExists(EFXStylesheets efxStylesheetsObj, Class<?> clazz) {
        // Ensure that efxStylesheetsObj and clazz are not null
        EFXObjectUtils.isNotNull(efxStylesheetsObj, () -> "EFXStylesheets object cannot be null.");
        EFXObjectUtils.isNotNull(clazz, () -> "Class object cannot be null.");

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
