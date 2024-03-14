package io.github.colindj1120.enhancedfx.binding;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.WeakListener;
import javafx.beans.binding.Binding;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.Optional;

public class BindingHelperObserver implements InvalidationListener, WeakListener {
    private final WeakReference<Binding<?>> ref;

    public BindingHelperObserver(Binding<?> binding) {
        if (Objects.isNull(binding)) {
            throw new NullPointerException("Binding has to be specified.");
        }
        ref = new WeakReference<>(binding);
    }

    @Override
    public void invalidated(Observable observable) {
        Optional.ofNullable(ref.get())
                .ifPresentOrElse(Binding::invalidate, () -> observable.removeListener(this));
    }

    @Override
    public boolean wasGarbageCollected() {
        return Objects.isNull(ref.get());
    }
}
