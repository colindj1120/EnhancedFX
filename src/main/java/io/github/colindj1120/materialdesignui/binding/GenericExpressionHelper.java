package io.github.colindj1120.materialdesignui.binding;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

class GenericExpressionHelper<T> extends ExpressionHelper<T> {
    private final List<InvalidationListener>      invalidationListeners = new ArrayList<>();
    private final List<ChangeListener<? super T>> changeListeners       = new ArrayList<>();
    private       boolean                         locked;
    private       T                               currentValue;

    GenericExpressionHelper(ObservableValue<T> observable, InvalidationListener listener0, InvalidationListener listener1) {
        super(observable);
        this.invalidationListeners.addAll(List.of(listener0, listener1));
    }

    GenericExpressionHelper(ObservableValue<T> observable, T currentValue, ChangeListener<? super T> listener0, ChangeListener<? super T> listener1) {
        super(observable);
        this.changeListeners.addAll(List.of(listener0, listener1));
        this.currentValue = currentValue;
    }

    GenericExpressionHelper(ObservableValue<T> observable, T currentValue, InvalidationListener invalidationListener, ChangeListener<? super T> changeListener) {
        super(observable);
        this.invalidationListeners.add(invalidationListener);
        this.changeListeners.add(changeListener);
        this.currentValue = currentValue;
    }

    @Override
    protected GenericExpressionHelper<T> addListener(InvalidationListener listener) {
        invalidationListeners.add(listener);
        return this;
    }

    @Override
    protected ExpressionHelper<T> removeListener(InvalidationListener listener) {
        Supplier<ExpressionHelper<T>> supplier1 = () -> new SingleChangeHelper<>(observable, currentValue, changeListeners.getFirst());
        Supplier<ExpressionHelper<T>> supplier2 = () -> new SingleInvalidationHelper<>(observable, invalidationListeners.getFirst());

        return removeFromListAndAdjust(listener, invalidationListeners, changeListeners, supplier1, supplier2);
    }

    @Override
    protected ExpressionHelper<T> addListener(ChangeListener<? super T> listener) {
        changeListeners.add(listener);

        if (changeListeners.size() == 1) {
            currentValue = observable.getValue();
        }
        return this;
    }

    @Override
    protected ExpressionHelper<T> removeListener(ChangeListener<? super T> listener) {
        Supplier<ExpressionHelper<T>> supplier1 = () -> new SingleInvalidationHelper<>(observable, invalidationListeners.getFirst());
        Supplier<ExpressionHelper<T>> supplier2 = () -> new SingleChangeHelper<>(observable, currentValue, changeListeners.getFirst());

        return removeFromListAndAdjust(listener, changeListeners, invalidationListeners, supplier1, supplier2);
    }

    private <V> ExpressionHelper<T> removeFromListAndAdjust(V listener, List<V> listenersToRemoveFrom, List<?> otherListeners, Supplier<ExpressionHelper<T>> supplier1,
                                                            Supplier<ExpressionHelper<T>> supplier2) {
        Predicate<V> listenerEquals = listener::equals;
        return listenersToRemoveFrom.stream()
                                    .filter(listenerEquals)
                                    .findFirst()
                                    .map(targetListener -> {
                                        if (listenersToRemoveFrom.size() == 1 && otherListeners.size() == 1) {
                                            listenersToRemoveFrom.remove(targetListener);
                                            return supplier1.get();
                                        } else if (listenersToRemoveFrom.size() == 1 && otherListeners.isEmpty()) {
                                            listenersToRemoveFrom.remove(targetListener);
                                            return this;
                                        } else if (listenersToRemoveFrom.size() == 2 && otherListeners.isEmpty()) {
                                            listenersToRemoveFrom.remove(targetListener);
                                            return supplier2.get();
                                        } else {
                                            if (!locked) {
                                                listenersToRemoveFrom.remove(targetListener);
                                            }
                                            return this;
                                        }
                                    })
                                    .orElse(this);
    }

    @Override
    protected void fireValueChangedEvent() {
        locked = true;
        try {
            handleInvalidationListeners();
            handleChangeListeners();
        }
        finally {
            locked = false;
        }
    }

    private void handleInvalidationListeners() {
        for (InvalidationListener invalidationListener : invalidationListeners) {
            try {
                invalidationListener.invalidated(observable);
            }
            catch (Exception e) {
                handleUncaughtException(e);
            }
        }
    }

    private void handleChangeListeners() {
        if (!changeListeners.isEmpty()) {
            final T oldValue = currentValue;
            currentValue = observable.getValue();

            if (!Objects.equals(currentValue, oldValue)) {
                Consumer<ChangeListener<? super T>> notifyChangeListener = listener -> {
                    try {
                        listener.changed(observable, oldValue, currentValue);
                    } catch (Exception e) {
                        handleUncaughtException(e);
                    }
                };

                changeListeners.forEach(notifyChangeListener);
            }
        }
    }
}
