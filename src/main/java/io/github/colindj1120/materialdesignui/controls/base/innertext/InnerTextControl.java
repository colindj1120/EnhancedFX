package io.github.colindj1120.materialdesignui.controls.base.innertext;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Tooltip;

public interface InnerTextControl<T extends Control> extends InnerTextBase<T> {
    default ObjectProperty<Skin<?>> skinProperty()            {return getInnerField().skinProperty();}

    default void setSkin(Skin<?> value)                       {skinProperty().set(value);}

    default Skin<?> getSkin()                                 {return skinProperty().getValue();}

    default ObjectProperty<Tooltip> tooltipProperty()         {return getInnerField().tooltipProperty();}

    default void setTooltip(Tooltip value)                    {getInnerField().setTooltip(value);}

    default Tooltip getTooltip()                              {return getInnerField().getTooltip();}

    default ObjectProperty<ContextMenu> contextMenuProperty() {return getInnerField().contextMenuProperty();}

    default void setContextMenu(ContextMenu value)            {getInnerField().setContextMenu(value);}

    default ContextMenu getContextMenu()                      {return getInnerField().getContextMenu();}

//    default void setFocused(boolean value) {getField().foc}

    default boolean isTextFieldFocused()                                {return getInnerField().isFocused();}

    default ReadOnlyBooleanProperty textFieldFocusedProperty()          {return getInnerField().focusedProperty();}

    default boolean isTextFieldFocusWithin()                            {return getInnerField().isFocusWithin();}

    default ReadOnlyBooleanProperty textFieldFocusWithinProperty()      {return getInnerField().focusWithinProperty();}

    default boolean isTextFieldFocusTraversable()                       {return getInnerField().isFocusTraversable();}

    default ReadOnlyBooleanProperty textFieldFocusTraversableProperty() {return getInnerField().focusTraversableProperty();}

    default void setTextFieldFocusTraversable(boolean value)            {getInnerField().setFocusTraversable(value);}

    default boolean isTextFieldFocusVisible()                           {return getInnerField().isFocusVisible();}

    default ReadOnlyBooleanProperty textFieldFocusVisibleProperty()     {return getInnerField().focusVisibleProperty();}

}
