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
package io.github.colindj1120.enhancedfx.controls.complexcontrol;

import io.github.colindj1120.enhancedfx.base.beans.efxstyleableproperty.EFXStyleableObjectProperty;
import io.github.colindj1120.enhancedfx.base.collections.ObservableLinkedList;
import io.github.colindj1120.enhancedfx.base.collections.base.UpdateActions;
import io.github.colindj1120.enhancedfx.base.css.StyleablePropertiesManager;
import io.github.colindj1120.enhancedfx.base.factory.CssFactory;
import io.github.colindj1120.enhancedfx.controls.simplecontrol.efxlabeled.efxbuttons.EFXToggleButton;
import io.github.colindj1120.enhancedfx.controls.skins.EFXToggleNavigationBarSkin;
import io.github.colindj1120.enhancedfx.utils.EFXNodeUtils;
import io.github.colindj1120.enhancedfx.utils.EFXPropertyUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.converter.EnumConverter;
import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleGroup;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


public class EFXToggleNavigationBar extends Control {
    protected static final StyleablePropertiesManager STYLES_MANAGER = new StyleablePropertiesManager(Control.getClassCssMetaData());

    protected static final Orientation DEFAULT_ORIENTATION = Orientation.HORIZONTAL;

    // These fields will store the maximum width and height of buttons respectively
    private double maxWidth  = 0;
    private double maxHeight = 0;

    private final ToggleGroup                           toggleGroup      = new ToggleGroup();
    private final ObservableLinkedList<EFXToggleButton> toggleButtonList = new ObservableLinkedList<>();

    private EFXStyleableObjectProperty<Orientation> orientation;
    private BooleanProperty                      isHorizontal;
    private BooleanProperty                      isVertical;

    static {
        STYLES_MANAGER.addCssMetaData(CssFactory.<EFXToggleNavigationBar, Orientation>create()
                                                .property("-orientation")
                                                .converter(EnumConverter.getEnumConverter(Orientation.class))
                                                .initialValue(Orientation.HORIZONTAL)
                                                .isSettableFunction(node -> EFXPropertyUtils.checkProperty(node.orientation))
                                                .propertyGetterFunction(node -> node.orientation));
    }

    public static EFXToggleNavigationBar create() {
        EFXToggleNavigationBar efxToggleNavigationBar = new EFXToggleNavigationBar();
        efxToggleNavigationBar.initialize();
        return efxToggleNavigationBar;
    }

    private void initialize() {
        this.isHorizontal = new SimpleBooleanProperty(this, "isHorizontal", true);
        this.isVertical = new SimpleBooleanProperty(this, "isVertical", false);

        this.orientation = EFXStyleableObjectProperty.<Orientation>create()
                                                     .bean(this)
                                                     .name("orientation")
                                                     .cssMetaData(STYLES_MANAGER.findCssMetaData("-orientation"))
                                                     .initialValue(DEFAULT_ORIENTATION)
                                                     .build();

        // Bind orientation properties to support responsive UI adjustments
        isHorizontal.bind(orientation.isEqualTo(Orientation.HORIZONTAL));
        isVertical.bind(orientation.isEqualTo(Orientation.VERTICAL));
    }

    protected EFXToggleNavigationBar() {
        super();

        // Add a listener to handle dynamic changes in the toggle button list
        toggleButtonList.addActionListener((listChangeItem) -> {
            switch (listChangeItem.getListAction()) {
                case UpdateActions.ADDED, UpdateActions.REPLACED -> checkButtonSize(listChangeItem.getNewElement());
                case UpdateActions.BULK_ADD, UpdateActions.BULK_REPLACED -> checkBulkButtonSize(listChangeItem.getNewList());
                case UpdateActions.REMOVED, UpdateActions.BULK_REMOVE, UpdateActions.CLEARED -> recalculateButtonSizes();
            }
        });
    }

    
    private void checkBulkButtonSize(List<?> buttonList) {
        buttonList.stream()
                  .filter(EFXToggleButton.class::isInstance)
                  .map(EFXToggleButton.class::cast)
                  .forEach(this::checkButtonSize);
    }

    
    private void checkButtonSize(EFXToggleButton toggleButton) {
        Dimension2D buttonSize = calculatePreferredSize(toggleButton);
        boolean     updated    = false;
        if (buttonSize.getWidth() > maxWidth) {
            updated = true;
            maxWidth = buttonSize.getWidth();
        } else {
            toggleButton.setPrefWidth(maxWidth);
        }

        if (buttonSize.getHeight() > maxHeight) {
            updated = true;
            maxHeight = buttonSize.getHeight();
        } else {
            toggleButton.setPrefHeight(maxHeight);
        }

        if (updated) {
            toggleButtonList.forEach(element -> element.setPrefSize(maxWidth, maxHeight));
        }
    }

    
    private void recalculateButtonSizes() {
        // Reset maxWidth and maxHeight
        maxWidth = 0;
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

    
    private Dimension2D calculatePreferredSize(EFXToggleButton button) {
        double buttonWidth  = EFXNodeUtils.getNodeWidth(button);
        double buttonHeight = EFXNodeUtils.getNodeHeight(button);

        return new Dimension2D(buttonWidth, buttonHeight);
    }

    
    @Override
    protected Skin<?> createDefaultSkin() {
        return new EFXToggleNavigationBarSkin(this);
    }

    
    @SuppressWarnings("UnusedReturnValue")
    public EFXToggleButton addToggleButton(String buttonText, Consumer<Boolean> onSelectedAction) {
        EFXToggleButton toggleButton = EFXToggleButton.create(buttonText);
//        toggleButton.selectedProperty()
//                    .addListener((obs, oldVal, newVal) -> onSelectedAction.accept(newVal));
//        toggleButton.setPadding(EFXInsetUtils.empty());
//        toggleButton.setToggleGroup(toggleGroup);
//        toggleButtonList.add(toggleButton);

        return toggleButton;
    }

    
    public void removeToggleButton(EFXToggleButton toggleButton) {
        toggleButtonList.remove(toggleButton);
    }

    //region Getters and Setters
    //*****************************************************************
    // Getters and Setters
    //*****************************************************************

    
    public Optional<EFXToggleButton> findToggleButton(EFXToggleButton toggleButton) {
        return toggleButtonList.stream()
                               .filter(btn -> btn.equals(toggleButton))
                               .findFirst();
    }

    
    public Optional<EFXToggleButton> getToggleButton(int index) {
        return Optional.ofNullable(toggleButtonList.get(index));
    }

    
    public Optional<EFXToggleButton> getSelected() {
        return null;

//                toggleButtonList.stream()
//                               .filter(ToggleButton::isSelected)
//                               .findFirst();
    }

    
    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    
    public ReadOnlyBooleanProperty isHorizontalProperty() {
        return isHorizontal;
    }

    
    public boolean isHorizontal() {
        return isHorizontal.get();
    }

    
    public ReadOnlyBooleanProperty isVerticalProperty() {
        return isVertical;
    }

    
    public boolean isVertical() {
        return isVertical.get();
    }

    
    public Orientation getOrientation() {
        return orientation.get();
    }

    
    public EFXStyleableObjectProperty<Orientation> orientationProperty() {
        return orientation;
    }

    
    public void setOrientation(Orientation orientation) {
        this.orientation.set(orientation);
    }

    
    public ObservableLinkedList<EFXToggleButton> getToggleButtonList() {
        return toggleButtonList;
    }

    //endregion Getters and Setters

    //region EFXStyle Properties
    //*****************************************************************
    // EFXStyle Properties
    //*****************************************************************

    
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {return getClassCssMetaData();}

    
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {return STYLES_MANAGER.getCssMetaDataList();}

    //endregion EFXStyle Properties
}
