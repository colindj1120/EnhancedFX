package io.github.colindj1120.materialdesignui.controls.base.innertext;

import javafx.scene.control.Control;

@FunctionalInterface
public interface InnerTextBase<T extends Control> {
    T getInnerField();
}
