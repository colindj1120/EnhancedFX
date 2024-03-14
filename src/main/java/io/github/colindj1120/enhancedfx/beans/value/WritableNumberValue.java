package io.github.colindj1120.enhancedfx.beans.value;

public interface WritableNumberValue extends javafx.beans.value.WritableNumberValue {
    Number get();

    void set(Number value);

    @Override
    void setValue(Number value);
}