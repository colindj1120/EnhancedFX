package io.github.colindj1120.materialdesignui.binding;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

class SingleInvalidationHelper<T> extends ExpressionHelper<T> {
    private final InvalidationListener listener;

    SingleInvalidationHelper(ObservableValue<T> expression, InvalidationListener listener) {
        super(expression);
        this.listener = listener;
    }

    @Override
    protected ExpressionHelper<T> addListener(InvalidationListener listener) {
        return new GenericExpressionHelper<>(observable, this.listener, listener);
    }

    @Override
    protected ExpressionHelper<T> removeListener(InvalidationListener listener) {
        return listener.equals(this.listener) ? null : this;
    }

    @Override
    protected ExpressionHelper<T> addListener(ChangeListener<? super T> listener) {
        return new GenericExpressionHelper<>(observable, observable.getValue(), this.listener, listener);
    }

    @Override
    protected ExpressionHelper<T> removeListener(ChangeListener<? super T> listener) {
        return this;
    }

    @Override
    protected void fireValueChangedEvent() {
        try {
            listener.invalidated(observable);
        }
        catch (Exception e) {
            handleUncaughtException(e);
        }
    }
}