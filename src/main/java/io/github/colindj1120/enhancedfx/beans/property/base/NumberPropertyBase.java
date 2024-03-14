package io.github.colindj1120.enhancedfx.beans.property.base;

import io.github.colindj1120.enhancedfx.beans.binding.NumberBinding;
import io.github.colindj1120.enhancedfx.beans.property.NumberProperty;
import io.github.colindj1120.enhancedfx.beans.value.ObservableNumberValue;
import io.github.colindj1120.enhancedfx.binding.ExpressionHelper;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.WeakListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.Optional;

public abstract class NumberPropertyBase extends NumberProperty {
    private Number                   value;
    private ObservableNumberValue    observable = null;
    private InvalidationListener     listener   = null;
    private boolean                  valid      = true;
    private ExpressionHelper<Number> helper     = null;

    public NumberPropertyBase() {}

    public NumberPropertyBase(Number initialValue) {
        this.value = initialValue;
    }

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

    private void markInvalid() {
        if (valid) {
            valid = false;
            invalidated();
            fireValueChangedEvent();
        }
    }

    protected void invalidated() {}

    @Override
    public Number get() {
        valid = true;
        return Objects.requireNonNullElse(observable.get(), value);
    }

    @Override
    public void set(Number newValue) {
        if (isBound()) {
            throw new IllegalArgumentException(this + " A bound value cannot be set.");
        }

        if (!Objects.equals(value, newValue)) {
            value = newValue;
            markInvalid();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBound() {
        return Objects.nonNull(observable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(final ObservableValue<? extends Number> rawObservable) {
        Objects.requireNonNull(rawObservable, "Cannot bind to null observable");

        ObservableNumberValue newObservable;
        if (rawObservable instanceof ObservableNumberValue rawValue) {
            newObservable = rawValue;
        } else if (rawObservable instanceof javafx.beans.value.ObservableNumberValue numberValue) {
            newObservable = new NumberPropertyValueWrapper(rawObservable) {
                @Override
                protected Number computeValue() {
                    return numberValue.getValue();
                }
            };
        } else {
            newObservable = new NumberPropertyValueWrapper(rawObservable) {
                @Override
                protected Number computeValue() {
                    return Objects.requireNonNullElse(rawObservable.getValue(), 0.0);
                }
            };
        }

        if (!newObservable.equals(observable)) {
            unbind();
            observable = newObservable;
            listener = Objects.requireNonNullElse(listener, new NumberPropertyListener(this));
            observable.addListener(listener);
            markInvalid();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbind() {
        Optional.ofNullable(observable)
                .ifPresent(obs -> {
                    value = obs.get();
                    obs.removeListener(listener);
                    if (obs instanceof NumberPropertyValueWrapper valueWrapper) {
                        valueWrapper.dispose();
                    }
                    observable = null;
                });
    }

    @Override
    public String toString() {
        final StringBuilder result = getBeanNameString(this.getClass()
                                                           .getSimpleName());
        if (isBound()) {
            result.append("bound, ");
            if (valid) {
                result.append("value: ")
                      .append(get());
            } else {
                result.append("invalid");
            }
        } else {
            result.append("value: ")
                  .append(get());
        }
        result.append("]");
        return result.toString();
    }

    private static class NumberPropertyListener implements InvalidationListener, WeakListener {
        private final WeakReference<NumberPropertyBase> weakReference;

        public NumberPropertyListener(NumberPropertyBase ref) {
            this.weakReference = new WeakReference<>(ref);
        }

        @Override
        public void invalidated(Observable observable) {
            Optional.ofNullable(weakReference.get())
                    .ifPresentOrElse(NumberPropertyBase::markInvalid, () -> observable.removeListener(this));
        }

        @Override
        public boolean wasGarbageCollected() {
            return Objects.isNull(weakReference.get());
        }
    }

    private abstract static class NumberPropertyValueWrapper extends NumberBinding {
        private final ObservableValue<? extends Number> observable;

        public NumberPropertyValueWrapper(ObservableValue<? extends Number> observable) {
            this.observable = observable;
            bind(observable);
        }

        @Override
        public void dispose() {
            unbind(observable);
        }
    }
}
