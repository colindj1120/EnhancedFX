package io.github.colindj1120.enhancedfx.binding;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.Objects;

public class SingleChangeHelper<T> extends ExpressionHelper<T> {
    private final ChangeListener<? super T> listener;
    private       T                         currentValue;

    SingleChangeHelper(ObservableValue<T> observable, T currentValue, ChangeListener<? super T> listener) {
        super(observable);
        this.listener     = listener;
        this.currentValue = currentValue;
    }

    @Override
    protected ExpressionHelper<T> addListener(InvalidationListener listener) {
        return new GenericExpressionHelper<>(observable, currentValue, listener, this.listener);
    }

    @Override
    protected ExpressionHelper<T> removeListener(InvalidationListener listener) {
        return this;
    }

    @Override
    protected ExpressionHelper<T> addListener(ChangeListener<? super T> listener) {
        return new GenericExpressionHelper<>(observable, currentValue, this.listener, listener);
    }

    @Override
    protected ExpressionHelper<T> removeListener(ChangeListener<? super T> listener) {
        return listener.equals(this.listener) ? null : this;
    }

    @Override
    protected void fireValueChangedEvent() {
        final T oldValue = currentValue;
        currentValue = observable.getValue();
        if (!Objects.equals(currentValue, oldValue)) {
            try {
                listener.changed(observable, oldValue, currentValue);
            }
            catch (Exception e) {
                handleUncaughtException(e);
            }
        }
    }
}
