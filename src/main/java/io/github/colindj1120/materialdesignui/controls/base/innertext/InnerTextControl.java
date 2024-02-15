package io.github.colindj1120.materialdesignui.controls.base.innertext;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.Tooltip;

public interface InnerTextControl<T extends Control> extends InnerTextBase<T> {
    default ObjectProperty<Skin<?>> skinProperty()            {return getTextField().skinProperty();}

    default void setSkin(Skin<?> value)                       {skinProperty().set(value);}

    default Skin<?> getSkin()                                 {return skinProperty().getValue();}

    default ObjectProperty<Tooltip> tooltipProperty()         {return getTextField().tooltipProperty();}

    default void setTooltip(Tooltip value)                    {getTextField().setTooltip(value);}

    default Tooltip getTooltip()                              {return getTextField().getTooltip();}

    default ObjectProperty<ContextMenu> contextMenuProperty() {return getTextField().contextMenuProperty();}

    default void setContextMenu(ContextMenu value)            {getTextField().setContextMenu(value);}

    default ContextMenu getContextMenu()                      {return getTextField().getContextMenu();}

//    default void setFocused(boolean value) {getField().foc}

    default boolean isTextFieldFocused()                                {return getTextField().isFocused();}

    default ReadOnlyBooleanProperty textFieldFocusedProperty()          {return getTextField().focusedProperty();}

    default boolean isTextFieldFocusWithin()                            {return getTextField().isFocusWithin();}

    default ReadOnlyBooleanProperty textFieldFocusWithinProperty()      {return getTextField().focusWithinProperty();}

    default boolean isTextFieldFocusTraversable()                       {return getTextField().isFocusTraversable();}

    default ReadOnlyBooleanProperty textFieldFocusTraversableProperty() {return getTextField().focusTraversableProperty();}

    default void setTextFieldFocusTraversable(boolean value)            {getTextField().setFocusTraversable(value);}

    default boolean isTextFieldFocusVisible()                           {return getTextField().isFocusVisible();}

    default ReadOnlyBooleanProperty textFieldFocusVisibleProperty()     {return getTextField().focusVisibleProperty();}

}
