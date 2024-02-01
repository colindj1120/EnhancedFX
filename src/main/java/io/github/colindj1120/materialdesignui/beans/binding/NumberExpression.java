package io.github.colindj1120.materialdesignui.beans.binding;

import io.github.colindj1120.materialdesignui.beans.value.ObservableNumberValue;
import javafx.beans.binding.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public abstract class NumberExpression extends NumberExpressionBase implements ObservableNumberValue {
    public NumberExpression() {}

    public abstract Number get();

    @Override
    public int intValue() {
        return get().intValue();
    }

    @Override
    public long longValue() {
        return get().longValue();
    }

    @Override
    public float floatValue() {
        return get().floatValue();
    }

    @Override
    public double doubleValue() {
        return get().doubleValue();
    }

    @Override
    public Number getValue() {
        return get();
    }

    public static NumberExpression baseNumberExpression(final ObservableNumberValue value) {
        Objects.requireNonNull(value, "Value must be specified.");

        if (value instanceof NumberExpression expression) {
            return expression;
        }
        return new NumberBinding() {
            {
                super.bind(value);
            }

            @Override
            public void dispose() {
                super.unbind(value);
            }

            @Override
            protected Number computeValue() {
                return value.get();
            }

            @Override
            public ObservableList<ObservableNumberValue> getDependencies() {
                return FXCollections.singletonObservableList(value);
            }
        };
    }

    @Override
    public NumberBinding negate() {
        return new NumberBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected Number computeValue() {
                return switch (this.get()) {
                    case Integer i -> -i;
                    case Long l -> -l;
                    case Float f -> -f;
                    case Double d -> -d;
                    case Short s -> (short) -s;
                    case Byte b -> (byte) -b;
                    default -> throw new IllegalArgumentException("Unsupported number type.");
                };
            }

            @Override
            public ObservableList<ObservableNumberValue> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public DoubleBinding add(final double other) {
        return new DoubleBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected double computeValue() {
                return this.doubleValue() + other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public FloatBinding add(final float other) {
        return new FloatBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected float computeValue() {
                return this.floatValue() + other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public LongBinding add(final long other) {
        return new LongBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected long computeValue() {
                return this.longValue() + other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public IntegerBinding add(final int other) {
        return new IntegerBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected int computeValue() {
                return this.intValue() + other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public DoubleBinding subtract(final double other) {
        return new DoubleBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected double computeValue() {
                return this.doubleValue() - other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public FloatBinding subtract(final float other) {
        return new FloatBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected float computeValue() {
                return this.floatValue() - other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public LongBinding subtract(final long other) {
        return new LongBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected long computeValue() {
                return this.longValue() - other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public IntegerBinding subtract(final int other) {
        return new IntegerBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected int computeValue() {
                return this.intValue() - other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public DoubleBinding multiply(final double other) {
        return new DoubleBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected double computeValue() {
                return this.doubleValue() * other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public FloatBinding multiply(final float other) {
        return new FloatBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected float computeValue() {
                return this.floatValue() * other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public LongBinding multiply(final long other) {
        return new LongBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected long computeValue() {
                return this.longValue() * other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public IntegerBinding multiply(final int other) {
        return new IntegerBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected int computeValue() {
                return this.intValue() * other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public DoubleBinding divide(final double other) {
        return new DoubleBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected double computeValue() {
                return this.doubleValue() / other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public FloatBinding divide(final float other) {
        return new FloatBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected float computeValue() {
                return this.floatValue() / other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public LongBinding divide(final long other) {
        return new LongBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected long computeValue() {
                return this.longValue() / other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    @Override
    public IntegerBinding divide(final int other) {
        return new IntegerBinding() {
            {
                super.bind(this);
            }

            @Override
            public void dispose() {
                super.unbind(this);
            }

            @Override
            protected int computeValue() {
                return this.intValue() / other;
            }

            @Override
            public ObservableList<?> getDependencies() {
                return FXCollections.singletonObservableList(this);
            }
        };
    }

    /**
     * Creates an {@link javafx.beans.binding.ObjectExpression} that holds the value of this {@code LongExpression}. If the value of this {@code LongExpression} changes, the value of the
     * {@code ObjectExpression} will be updated automatically.
     *
     * @return the new {@code ObjectExpression}
     *
     * @since JavaFX 8.0
     */
    public ObjectExpression<Number> asObject() {
        return new ObjectBinding<>() {
            {
                bind(NumberExpression.this);
            }

            @Override
            public void dispose() {
                unbind(NumberExpression.this);
            }

            @Override
            protected Number computeValue() {
                return NumberExpression.this.getValue();
            }
        };
    }
}
