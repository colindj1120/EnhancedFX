package io.github.colindj1120.materialdesignui.beans.property;

import io.github.colindj1120.materialdesignui.beans.property.base.NumberPropertyBase;
import io.github.colindj1120.materialdesignui.beans.value.WritableNumberValue;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.Property;

import java.util.Objects;

public abstract class NumberProperty extends ReadOnlyNumberProperty implements Property<Number>, WritableNumberValue {
    public NumberProperty() {}

    @Override
    public void setValue(Number v) {
        set(Objects.requireNonNullElse(v, 0.0));
    }

    @Override
    public void bindBidirectional(Property<Number> other) {
        Bindings.bindBidirectional(this, other);
    }

    @Override
    public void unbindBidirectional(Property<Number> other) {
        Bindings.unbindBidirectional(this, other);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static NumberProperty numberProperty(final Property<Number> property) {
        Objects.requireNonNull(property, "Property cannot be null");
        return new NumberPropertyBase() {
            {
                Bindings.bindBidirectional(this, property);
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
     * Creates an {@link javafx.beans.property.ObjectProperty} that bidirectionally bound to this {@code LongProperty}. If the value of this {@code LongProperty} changes, the value of the
     * {@code ObjectProperty} will be updated automatically and vice-versa.
     *
     * <p>
     * Can be used for binding an ObjectProperty to LongProperty.
     *
     * <blockquote><pre>
     *   LongProperty longProperty = new SimpleLongProperty(1L);
     *   ObjectProperty&lt;Long&gt; objectProperty = new SimpleObjectProperty&lt;&gt;(2L);
     *
     *   objectProperty.bind(longProperty.asObject());
     * </pre></blockquote>
     *
     * @return the new {@code ObjectProperty}
     *
     * @since JavaFX 8.0
     */
    @Override
    public ObjectProperty<Number> asObject() {
        return new ObjectPropertyBase<>() {
            {
                Bindings.bindBidirectional(this, NumberProperty.this);
            }

            @Override
            public Object getBean() {
                return null; // Virtual property, does not exist on a bean
            }

            @Override
            public String getName() {
                return NumberProperty.this.getName();
            }
        };
    }
}
