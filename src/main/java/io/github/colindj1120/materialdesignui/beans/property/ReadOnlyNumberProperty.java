package io.github.colindj1120.materialdesignui.beans.property;

import io.github.colindj1120.materialdesignui.beans.binding.NumberExpression;
import io.github.colindj1120.materialdesignui.beans.property.base.ReadOnlyNumberPropertyBase;
import javafx.beans.InvalidationListener;
import javafx.beans.WeakInvalidationListener;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectPropertyBase;
import javafx.beans.property.ReadOnlyProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public abstract class ReadOnlyNumberProperty extends NumberExpression implements ReadOnlyProperty<Number> {

    /**
     * The constructor of {@code ReadOnlyLongProperty}.
     */
    public ReadOnlyNumberProperty() {}

    /**
     * Returns a string representation of this {@code ReadOnlyLongProperty} object.
     *
     * @return a string representation of this {@code ReadOnlyLongProperty} object.
     */
    @Override
    public String toString() {
        final StringBuilder result = getBeanNameString(this.getClass().getSimpleName());

        result.append("value: ")
              .append(get())
              .append("]");
        return result.toString();
    }

    @NotNull
    protected StringBuilder getBeanNameString(String callingClass) {
        final StringBuilder result = new StringBuilder(callingClass + " [");
        Optional.ofNullable(getBean())
                .ifPresent(b -> result.append("bean: ")
                                      .append(b)
                                      .append(", "));

        Optional.ofNullable(getName())
                .filter(n -> !n.isEmpty())
                .ifPresent(n -> result.append("name: ")
                                      .append(n)
                                      .append(", "));
        return result;
    }

    /**
     * Returns a {@code ReadOnlyLongProperty} that wraps a {@link javafx.beans.property.ReadOnlyProperty}. If the {@code ReadOnlyProperty} is already a {@code ReadOnlyLongProperty}, it will be
     * returned. Otherwise a new {@code ReadOnlyLongProperty} is created that is bound to the {@code ReadOnlyProperty}.
     * <p>
     * Note: null values will be interpreted as 0L
     *
     * @param <T>
     *         The type of Number to be wrapped
     * @param property
     *         The source {@code ReadOnlyProperty}
     *
     * @return A {@code ReadOnlyLongProperty} that wraps the {@code ReadOnlyProperty} if necessary
     *
     * @throws NullPointerException
     *         if {@code property} is {@code null}
     * @since JavaFX 8.0
     */
    public static <T extends Number> ReadOnlyNumberProperty readOnlyNumberProperty(final ReadOnlyProperty<T> property) {
        Objects.requireNonNull(property, "Property cannot be null");

        if (property instanceof ReadOnlyNumberProperty readOnlyNumberProperty) {
            return readOnlyNumberProperty;
        }

        return new ReadOnlyNumberPropertyBase() {
            private boolean valid = true;
            private final InvalidationListener listener = observable -> {
                if (valid) {
                    valid = false;
                    fireValueChangedEvent();
                }
            };

            {
                property.addListener(new WeakInvalidationListener(listener));
            }

            @Override
            public Number get() {
                valid = true;
                return Objects.requireNonNullElse(property.getValue(), 0.0);
            }

            @Override
            public Object getBean() {
                return null; // Virtual property, no bean
            }

            @Override
            public String getName() {
                return property.getName();
            }
        };
    }

    /**
     * Creates a {@link javafx.beans.property.ReadOnlyObjectProperty} that holds the value of this {@code ReadOnlyNumberProperty}. If the value of this {@code ReadOnlyNumberProperty} changes, the
     * value of the {@code ReadOnlyObjectProperty} will be updated automatically.
     *
     * @return the new {@code ReadOnlyObjectProperty}
     */
    @Override
    public ReadOnlyObjectProperty<Number> asObject() {
        return new ReadOnlyObjectPropertyBase<>() {
            private boolean valid = true;
            private final InvalidationListener listener = observable -> {
                if (valid) {
                    valid = false;
                    fireValueChangedEvent();
                }
            };

            {
                ReadOnlyNumberProperty.this.addListener(new WeakInvalidationListener(listener));
            }

            @Override
            public Object getBean() {
                return null; // Virtual property, does not exist on a bean
            }

            @Override
            public String getName() {
                return ReadOnlyNumberProperty.this.getName();
            }

            @Override
            public Number get() {
                valid = true;
                return ReadOnlyNumberProperty.this.getValue();
            }
        };
    }

}
