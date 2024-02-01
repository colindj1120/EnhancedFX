package io.github.colindj1120.materialdesignui.binding;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.Objects;
import java.util.Optional;

public abstract class ExpressionHelper<T> {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Static methods

    public static <T> ExpressionHelper<T> addListener(ExpressionHelper<T> helper, ObservableValue<T> observable, InvalidationListener listener) {
        if (Objects.isNull(observable)) {
            throw new NullPointerException("Observable Cannot Be Null");
        }
        if (Objects.isNull(listener)) {
            throw new NullPointerException("Listener Cannot Be Null");
        }
        observable.getValue(); // validate observable
        return (Objects.isNull(helper)) ? new SingleInvalidationHelper<>(observable, listener) : helper.addListener(listener);
    }

    public static <T> ExpressionHelper<T> removeListener(ExpressionHelper<T> helper, InvalidationListener listener) {
        Objects.requireNonNull(listener, "Listener Cannot Be Null");
        return Optional.ofNullable(helper)
                       .map(h -> h.removeListener(listener))
                       .orElse(null);
    }

    public static <T> ExpressionHelper<T> addListener(ExpressionHelper<T> helper, ObservableValue<T> observable, ChangeListener<? super T> listener) {
        Objects.requireNonNull(observable, "Observable Cannot Be Null");
        Objects.requireNonNull(listener, "Listener Cannot Be Null");

        return Optional.ofNullable(helper)
                       .map(h -> h.addListener(listener))
                       .orElse(new SingleChangeHelper<>(observable, observable.getValue(), listener));
    }

    public static <T> ExpressionHelper<T> removeListener(ExpressionHelper<T> helper, ChangeListener<? super T> listener) {
        Objects.requireNonNull(listener, "Listener Cannot Be Null");
        return Optional.ofNullable(helper)
                       .map(h -> h.removeListener(listener))
                       .orElse(null);
    }

    public static <T> void fireValueChangedEvent(ExpressionHelper<T> helper) {
        Optional.ofNullable(helper)
                .ifPresent(h -> h.fireValueChangedEvent());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Common implementations

    protected final ObservableValue<T> observable;

    protected ExpressionHelper(ObservableValue<T> observable) {
        this.observable = observable;
    }

    protected abstract ExpressionHelper<T> addListener(InvalidationListener listener);

    protected abstract ExpressionHelper<T> removeListener(InvalidationListener listener);

    protected abstract ExpressionHelper<T> addListener(ChangeListener<? super T> listener);

    protected abstract ExpressionHelper<T> removeListener(ChangeListener<? super T> listener);

    protected abstract void fireValueChangedEvent();

    protected void handleUncaughtException(Exception e) {
        Thread.currentThread()
              .getUncaughtExceptionHandler()
              .uncaughtException(Thread.currentThread(), e);
    }
}
