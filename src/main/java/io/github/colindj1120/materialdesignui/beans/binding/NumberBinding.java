package io.github.colindj1120.materialdesignui.beans.binding;

import io.github.colindj1120.materialdesignui.binding.BindingHelperObserver;
import io.github.colindj1120.materialdesignui.binding.ExpressionHelper;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public abstract class NumberBinding extends NumberExpression implements javafx.beans.binding.NumberBinding {
    private Number                   value;
    private boolean                  valid  = false;
    private BindingHelperObserver    observer;
    private ExpressionHelper<Number> helper = null;

    public NumberBinding() {}

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

    /**
     * Start observing the dependencies for changes. If the value of one of the dependencies changes, the binding is marked as invalid.
     *
     * @param dependencies
     *         the dependencies to observe
     */
    protected final void bind(Observable... dependencies) {
        Optional.ofNullable(dependencies)
                .filter(ds -> ds.length > 0)
                .ifPresent(ds -> {
                    if (Objects.isNull(observer)) {
                        observer = new BindingHelperObserver(this);
                    }
                    Arrays.stream(ds)
                          .forEach(d -> d.addListener(observer));
                });
    }

    /**
     * Stop observing the dependencies for changes.
     *
     * @param dependencies
     *         the dependencies to stop observing
     */
    protected final void unbind(Observable... dependencies) {
        Optional.ofNullable(observer)
                .ifPresent(obs -> Arrays.stream(dependencies)
                                        .forEach(d -> d.removeListener(obs)));
    }

    /**
     * A default implementation of {@code dispose()} that is empty.
     */
    @Override
    public void dispose() {}

    /**
     * A default implementation of {@code getDependencies()} that returns an empty {@link javafx.collections.ObservableList}.
     *
     * @return an empty {@code ObservableList}
     */
    @Override
    public ObservableList<?> getDependencies() {
        return FXCollections.emptyObservableList();
    }

    /**
     * Returns the result of {@link #computeValue()}. The method {@code computeValue()} is only called if the binding is invalid. The result is cached and returned if the binding did not become
     * invalid since the last call of {@code get()}.
     *
     * @return the current value
     */
    @Override
    public final Number get() {
        if (!valid) {
            value = computeValue();
            valid = true;
        }
        return value;
    }

    /**
     * The method onInvalidating() can be overridden by extending classes to react, if this binding becomes invalid. The default implementation is empty.
     */
    protected void onInvalidating() {}

    @Override
    public final void invalidate() {
        if (valid) {
            valid = false;
            onInvalidating();
            ExpressionHelper.fireValueChangedEvent(helper);
        }
    }

    @Override
    public final boolean isValid() {
        return valid;
    }

    /**
     * Calculates the current value of this binding.
     * <p>
     * Classes extending {@code LongBinding} have to provide an implementation of {@code computeValue}.
     *
     * @return the current value
     */
    protected abstract Number computeValue();

    /**
     * Returns a string representation of this {@code LongBinding} object.
     *
     * @return a string representation of this {@code LongBinding} object.
     */
    @Override
    public String toString() {
        return valid ? "NumberBinding [value: " + get() + "]"
                     : "NumberBinding [invalid]";
    }
}
