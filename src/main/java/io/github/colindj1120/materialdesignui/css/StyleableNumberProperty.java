package io.github.colindj1120.materialdesignui.css;

import io.github.colindj1120.materialdesignui.beans.property.base.NumberPropertyBase;
import javafx.beans.value.ObservableValue;
import javafx.css.StyleOrigin;
import javafx.css.StyleableProperty;

public abstract class StyleableNumberProperty extends NumberPropertyBase implements StyleableProperty<Number> {
    public StyleableNumberProperty() {
        super();
    }

    public StyleableNumberProperty(Number initialValue) {
        super(initialValue);
    }

    @Override
    public void applyStyle(StyleOrigin origin, Number v) {
        setValue(v);
        this.origin = origin;
    }

    @Override
    public void bind(ObservableValue<? extends Number> observable) {
        super.bind(observable);
        origin = StyleOrigin.USER;
    }

    @Override
    public void set(Number v) {
        super.set(v);
        origin = StyleOrigin.USER;
    }

    @Override
    public StyleOrigin getStyleOrigin() {return origin;}

    private StyleOrigin origin = null;
}
