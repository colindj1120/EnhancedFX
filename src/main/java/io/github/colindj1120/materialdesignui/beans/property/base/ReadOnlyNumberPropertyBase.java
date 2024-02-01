package io.github.colindj1120.materialdesignui.beans.property.base;

import io.github.colindj1120.materialdesignui.beans.property.ReadOnlyNumberProperty;
import io.github.colindj1120.materialdesignui.binding.ExpressionHelper;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;

public abstract class ReadOnlyNumberPropertyBase extends ReadOnlyNumberProperty {
    ExpressionHelper<Number> helper;

    public ReadOnlyNumberPropertyBase() {}

    @Override
    public void addListener(InvalidationListener listener) {
        helper = ExpressionHelper.addListener(helper, this, listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        helper = ExpressionHelper.removeListener(helper, listener);
    }

    @Override
    public void addListener(ChangeListener<? super Number> listener) {
        helper = ExpressionHelper.addListener(helper, this, listener);
    }

    @Override
    public void removeListener(ChangeListener<? super Number> listener) {
        helper = ExpressionHelper.removeListener(helper, listener);
    }

    protected void fireValueChangedEvent() {
        ExpressionHelper.fireValueChangedEvent(helper);
    }

}
