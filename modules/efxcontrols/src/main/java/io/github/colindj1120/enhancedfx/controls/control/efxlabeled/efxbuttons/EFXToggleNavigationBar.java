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
package io.github.colindj1120.enhancedfx.controls.control.efxlabeled.efxbuttons;

import io.github.colindj1120.enhancedfx.base.collections.ObservableLinkedList;
import io.github.colindj1120.enhancedfx.base.collections.base.UpdateActions;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import io.github.colindj1120.enhancedfx.base.factory.ExtendedStyleableObjectPropertyFactory;
import io.github.colindj1120.enhancedfx.controls.skins.EFXToggleNavigationBarSkin;
import io.github.colindj1120.enhancedfx.utils.NodeUtils;
import io.github.colindj1120.enhancedfx.utils.PropertyUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.converter.EnumConverter;
import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Represents a navigational bar component that allows for toggling between different views or functionalities within an application.
 *
 * <p>This control consists of multiple {@link EFXToggleButton} instances managed by a {@link ToggleGroup}, ensuring that at most one toggle button can be selected at any time.</p>
 *
 * <p>The {@code EFXToggleNavigationBar} supports both horizontal and vertical orientations, customizable through CSS or directly via property methods. It dynamically adjusts the size of all toggle
 * buttons
 * to match the largest button, ensuring a uniform appearance.</p>
 *
 * <p>This class leverages {@link ObservableLinkedList} to listen for changes in the toggle button list, allowing for dynamic modifications at runtime. Changes to the button list or button states
 * trigger
 * updates to the navigation bar layout and appearance.</p>
 *
 * <p>Styling can be customized through CSS with properties such as {@code -orientation} to define the layout direction. The control's skin is defined by {@link EFXToggleNavigationBarSkin}, which
 * handles
 * the visual representation and interaction logic.</p>
 *
 * <h2>Usage Example</h2>
 * <pre>{@code
 * EFXToggleNavigationBar navigationBar = new EFXToggleNavigationBar();
 * navigationBar.addToggleButton("Tab 1", isSelected -> { /* Handle selection *\/ });
 * navigationBar.addToggleButton("Tab 2", isSelected -> { /* Handle selection *\/ });
 * navigationBar.setOrientation(Orientation.VERTICAL);
 * }</pre>
 *
 * <h2>CSS Styling</h2>
 * <p>To customize the orientation via CSS:</p>
 * <pre>{@code
 * .toggle-navigation-bar {
 *     -orientation: horizontal; // or vertical
 * }
 * }</pre>
 *
 * @author Colin Jokisch
 * @version 1.0.0
 * @see EFXToggleButton for the toggle button implementation used within this navigation bar.
 * @see ObservableLinkedList for how toggle buttons are managed and observed for changes.
 * @see ToggleGroup for the grouping mechanism that ensures mutual exclusivity among toggle buttons.
 * @see CssMetaData for details on how CSS styling is applied.
 */
public class EFXToggleNavigationBar extends Control {
    protected static final StyleablePropertiesManager STYLES_MANAGER = new StyleablePropertiesManager(Control.getClassCssMetaData());

    protected static final Orientation DEFAULT_ORIENTATION = Orientation.HORIZONTAL;

    // These fields will store the maximum width and height of buttons respectively
    private double maxWidth  = 0;
    private double maxHeight = 0;

    private final ToggleGroup                           toggleGroup      = new ToggleGroup();
    private final ObservableLinkedList<EFXToggleButton> toggleButtonList = new ObservableLinkedList<>();

    private final StyleableObjectProperty<Orientation> orientation = ExtendedStyleableObjectPropertyFactory.<Orientation>builder()
                                                                                                           .bean(this)
                                                                                                           .name("orientation")
                                                                                                           .cssMetaData(STYLES_MANAGER.findCssMetaData("-orientation"))
                                                                                                           .defaultValue(DEFAULT_ORIENTATION)
                                                                                                           .build();

    private final BooleanProperty isHorizontal = new SimpleBooleanProperty(this, "isHorizontal", true);
    private final BooleanProperty isVertical   = new SimpleBooleanProperty(this, "isVertical", false);

    static {
        STYLES_MANAGER.addCssMetaData(CssFactory.<EFXToggleNavigationBar, Orientation>create()
                                                .property("-orientation")
                                                .converter(EnumConverter.getEnumConverter(Orientation.class))
                                                .initialValue(Orientation.HORIZONTAL)
                                                .isSettableFunction(node -> PropertyUtils.checkProperty(node.orientation))
                                                .propertyGetterFunction(node -> node.orientation));
    }

    /**
     * Constructs a new instance of {@link EFXToggleNavigationBar}, initializing its orientation, toggle button list, and event handlers.
     *
     * <p>This constructor sets up the navigation bar with default orientation (horizontal) and initializes the internal mechanisms to manage
     * the addition, removal, and replacement of toggle buttons dynamically.</p>
     *
     * <p>It binds the {@code isHorizontal} and {@code isVertical} properties to the current orientation to facilitate responsive UI changes based on the navigation bar's orientation.</p>
     *
     * <p>The constructor also establishes a listener on the {@code toggleButtonList}, an {@link ObservableLinkedList} of {@link EFXToggleButton},
     * to automatically adjust the size of buttons based on their content and ensure uniformity. This listener responds to various list actions such as adding, removing, and replacing buttons, as well
     * as bulk operations that may affect multiple buttons at once.</p>
     *
     * <p>Actions handled by the listener include:</p>
     *
     * <ul>
     *     <li><b>ADDED</b> and <b>REPLACED</b>: Checks and adjusts the size of the newly added or replaced toggle button.</li>
     *     <li><b>BULK_ADD</b> and <b>BULK_REPLACED</b>: Performs a bulk size check on all newly added or replaced buttons to ensure consistency.</li>
     *     <li><b>REMOVED</b>, <b>BULK_REMOVE</b>, and <b>CLEARED</b>: Recalculates the sizes of all remaining buttons in the list to maintain uniform appearance.</li>
     * </ul>
     * This dynamic size management ensures that all the toggle buttons within the {@code EFXToggleNavigationBar} have consistent dimensions, contributing
     * to a cohesive and visually appealing user interface.
     *
     */
    public EFXToggleNavigationBar() {
        super();

        // Bind orientation properties to support responsive UI adjustments
        isHorizontal.bind(orientation.isEqualTo(Orientation.HORIZONTAL));
        isVertical.bind(orientation.isEqualTo(Orientation.VERTICAL));

        // Add a listener to handle dynamic changes in the toggle button list
        toggleButtonList.addActionListener((listChangeItem) -> {
            switch (listChangeItem.getListAction()) {
                case UpdateActions.ADDED, UpdateActions.REPLACED -> checkButtonSize(listChangeItem.getNewElement());
                case UpdateActions.BULK_ADD, UpdateActions.BULK_REPLACED -> checkBulkButtonSize(listChangeItem.getNewList());
                case UpdateActions.REMOVED, UpdateActions.BULK_REMOVE, UpdateActions.CLEARED -> recalculateButtonSizes();
            }
        });
    }

    /**
     * Checks the size of the button's list and calls the {@link #checkButtonSize(EFXToggleButton)} method for each button that is an instance of {@link EFXToggleButton }.
     *
     * @param buttonList
     *         the list of buttons to check the size of
     */
    private void checkBulkButtonSize(List<?> buttonList) {
        buttonList.stream()
                  .filter(EFXToggleButton.class::isInstance)
                  .map(EFXToggleButton.class::cast)
                  .forEach(this::checkButtonSize);
    }

    /**
     * Checks the size of the given toggle button and updates the size of all toggle buttons in the toggleButtonList if necessary.
     *
     * @param toggleButton
     *         the toggle button to check the size of
     */
    private void checkButtonSize(EFXToggleButton toggleButton) {
        Dimension2D buttonSize = calculatePreferredSize(toggleButton);
        boolean     updated    = false;
        if (buttonSize.getWidth() > maxWidth) {
            updated  = true;
            maxWidth = buttonSize.getWidth();
        } else {
            toggleButton.setPrefWidth(maxWidth);
        }

        if (buttonSize.getHeight() > maxHeight) {
            updated   = true;
            maxHeight = buttonSize.getHeight();
        } else {
            toggleButton.setPrefHeight(maxHeight);
        }

        if (updated) {
            toggleButtonList.forEach(element -> element.setPrefSize(maxWidth, maxHeight));
        }
    }

    /**
     * Recalculates the sizes of all buttons in the toggleButtonList by iterating through all buttons in the toggleButtonList.
     *
     * <p>It adjusts the maxWidth and maxHeight variables based on the preferred size of each button, and then sets the preferred size of all buttons in the toggleButtonList to the calculated
     * maxWidth and maxHeight.</p>
     */
    private void recalculateButtonSizes() {
        // Reset maxWidth and maxHeight
        maxWidth  = 0;
        maxHeight = 0;

        // Iterate through all buttons, adjust maxWidth and maxHeight
        toggleButtonList.stream()
                        .map(this::calculatePreferredSize)
                        .forEach(buttonSize -> {
                            if (buttonSize.getWidth() > maxWidth) {
                                maxWidth = buttonSize.getWidth();
                            }
                            if (buttonSize.getHeight() > maxHeight) {
                                maxHeight = buttonSize.getHeight();
                            }
                        });

        // Readjust sizes for all buttons
        toggleButtonList.forEach(btn -> btn.setPrefSize(maxWidth, maxHeight));
    }

    /**
     * Calculates the preferred size for the given EFXToggleButton.
     *
     * @param button
     *         the EFXToggleButton for which to calculate the preferred size
     *
     * @return a Dimension2D object representing the calculated preferred size of the button
     */
    private Dimension2D calculatePreferredSize(EFXToggleButton button) {
        double buttonWidth= NodeUtils.getNodeWidth(button);
        double buttonHeight= NodeUtils.getNodeHeight(button);

        return new Dimension2D(buttonWidth, buttonHeight);
    }

    /**
     * Creates and returns the default skin for the EFXToggleNavigationBar.
     *
     * @return the default skin for the EFXToggleNavigationBar
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EFXToggleNavigationBarSkin(this);
    }

    /**
     * Adds a new toggle button to the EFXToggleNavigationBar.
     *
     * @param buttonText
     *         the text to be displayed on the button
     * @param onSelectedAction
     *         the action to be executed when the button is selected
     *
     * @return the newly added EFXToggleButton
     */
    @SuppressWarnings("UnusedReturnValue")
    public EFXToggleButton addToggleButton(String buttonText, Consumer<Boolean> onSelectedAction) {
        EFXToggleButton toggleButton = new EFXToggleButton(buttonText);
//        toggleButton.selectedProperty()
//                    .addListener((obs, oldVal, newVal) -> onSelectedAction.accept(newVal));
//        toggleButton.setPadding(InsetUtils.empty());
//        toggleButton.setToggleGroup(toggleGroup);
//        toggleButtonList.add(toggleButton);

        return toggleButton;
    }

    /**
     * Removes the specified {@link EFXToggleButton} from the toggleButtonList.
     *
     * @param toggleButton
     *         the {@link EFXToggleButton} to be removed
     */
    public void removeToggleButton(EFXToggleButton toggleButton) {
        toggleButtonList.remove(toggleButton);
    }

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    /**
     * Finds the first occurrence of a given EFXToggleButton in the toggleButtonList.
     *
     * @param toggleButton
     *         the EFXToggleButton to search for in the toggleButtonList
     *
     * @return an Optional containing the first occurrence of the given EFXToggleButton in the toggleButtonList, or an empty Optional if the toggleButtonList doesn't contain the given
     *         EFXToggleButton
     */
    public Optional<EFXToggleButton> findToggleButton(EFXToggleButton toggleButton) {
        return toggleButtonList.stream()
                               .filter(btn -> btn.equals(toggleButton))
                               .findFirst();
    }

    /**
     * Retrieves the {@link EFXToggleButton} at the specified index from the toggle button list.
     *
     * @param index
     *         the index of the toggle button to retrieve
     *
     * @return an {@link Optional} containing the toggle button at the specified index if it exists, or an empty {@link Optional} otherwise
     */
    public Optional<EFXToggleButton> getToggleButton(int index) {
        return Optional.ofNullable(toggleButtonList.get(index));
    }

    /**
     * Returns an {@link Optional} which contains the currently selected {@link EFXToggleButton} from the toggleButtonList.
     *
     * <p>The method streams through the toggleButtonList and filters the button that are selected. </p>
     *
     * <p>It then returns the first selected button found, or an empty Optional if no buttons are selected.</p>
     *
     * @return an Optional containing the currently selected EFXToggleButton, or an empty Optional if no buttons are selected.
     *
     * @see EFXToggleButton
     * @see Optional
     * @see ToggleButton
     */
    public Optional<EFXToggleButton> getSelected() {
        return null;

//                toggleButtonList.stream()
//                               .filter(ToggleButton::isSelected)
//                               .findFirst();
    }

    /**
     * Retrieves the ToggleGroup associated with the EFXToggleNavigationBar.
     *
     * @return the ToggleGroup associated with the EFXToggleNavigationBar
     */
    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    /**
     * Returns the read-only boolean property that represents whether the EFXToggleNavigationBar is in horizontal orientation.
     *
     * @return the read-only boolean property for horizontal orientation
     */
    public ReadOnlyBooleanProperty isHorizontalProperty() {
        return isHorizontal;
    }

    /**
     * Returns whether the EFXToggleNavigationBar is horizontal or not.
     *
     * @return {@code true} if the EFXToggleNavigationBar is horizontal, {@code false} otherwise.
     */
    public boolean isHorizontal() {
        return isHorizontal.get();
    }

    /**
     * Returns the read-only boolean property indicating whether the EFXToggleNavigationBar is in vertical orientation.
     *
     * @return the read-only boolean property indicating the vertical orientation
     */
    public ReadOnlyBooleanProperty isVerticalProperty() {
        return isVertical;
    }

    /**
     * Determines if the object is oriented vertically.
     *
     * @return true if the object is oriented vertically, false otherwise.
     */
    public boolean isVertical() {
        return isVertical.get();
    }

    /**
     * Returns the orientation of the EFXToggleNavigationBar. The orientation specifies whether the navigation bar is currently in horizontal or vertical orientation.
     *
     * @return the orientation of the EFXToggleNavigationBar
     *
     * @see Orientation
     */
    public Orientation getOrientation() {
        return orientation.get();
    }

    /**
     * Returns the orientation property of the EFXToggleNavigationBar. The orientation property specifies the current orientation of the navigation bar, either horizontal or vertical.
     *
     * @return the orientation property of the EFXToggleNavigationBar.
     *
     * @see StyleableObjectProperty
     * @see Orientation
     * @see EFXToggleNavigationBar
     */
    public StyleableObjectProperty<Orientation> orientationProperty() {
        return orientation;
    }

    /**
     * Sets the orientation of the EFXToggleNavigationBar.
     *
     * @param orientation
     *         the desired orientation of the EFXToggleNavigationBar
     */
    public void setOrientation(Orientation orientation) {
        this.orientation.set(orientation);
    }

    /**
     * Retrieves the list of toggle buttons in the toggle navigation bar.
     *
     * @return The list of toggle buttons as an ObservableLinkedList of EFXToggleButton objects.
     */
    public ObservableLinkedList<EFXToggleButton> getToggleButtonList() {
        return toggleButtonList;
    }

    //endregion Getters and Setters

    //region EFXStyle Properties
    //*****************************************************************
    // EFXStyle Properties
    //*****************************************************************

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {return getClassCssMetaData();}

    /**
     * Returns a list of CssMetaData objects representing the CSS properties that can be applied to this class.
     *
     * @return a list of CssMetaData objects representing the CSS properties
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {return STYLES_MANAGER.getCssMetaDataList();}

    //endregion EFXStyle Properties
}
