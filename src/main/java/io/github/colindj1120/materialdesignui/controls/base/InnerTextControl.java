package io.github.colindj1120.materialdesignui.controls.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Tooltip;

@FunctionalInterface
public interface InnerTextControl<T extends Control> {
    T getField();

    default ObjectProperty<Skin<?>> skinProperty()            {return getField().skinProperty();}

    default void setSkin(Skin<?> value)                       {skinProperty().set(value);}

    default Skin<?> getSkin()                                 {return skinProperty().getValue();}

    default ObjectProperty<Tooltip> tooltipProperty()         {return getField().tooltipProperty();}

    default void setTooltip(Tooltip value)                    {getField().setTooltip(value);}

    default Tooltip getTooltip()                              {return getField().getTooltip();}

    default ObjectProperty<ContextMenu> contextMenuProperty() {return getField().contextMenuProperty();}

    default void setContextMenu(ContextMenu value)            {getField().setContextMenu(value);}

    default ContextMenu getContextMenu()                      {return getField().getContextMenu();}

//    default void setFocused(boolean value) {getField().foc}

    default boolean isTextFieldFocused()                                {return getField().isFocused();}

    default ReadOnlyBooleanProperty textFieldFocusedProperty()          {return getField().focusedProperty();}

    default boolean isTextFieldFocusWithin()                            {return getField().isFocusWithin();}

    default ReadOnlyBooleanProperty textFieldFocusWithinProperty()      {return getField().focusWithinProperty();}

    default boolean isTextFieldFocusTraversable()                       {return getField().isFocusTraversable();}

    default ReadOnlyBooleanProperty textFieldFocusTraversableProperty() {return getField().focusTraversableProperty();}

    default void setTextFieldFocusTraversable(boolean value)            {getField().setFocusTraversable(value);}

    default boolean isTextFieldFocusVisible()                           {return getField().isFocusVisible();}

    default ReadOnlyBooleanProperty textFieldFocusVisibleProperty()     {return getField().focusVisibleProperty();}

}
