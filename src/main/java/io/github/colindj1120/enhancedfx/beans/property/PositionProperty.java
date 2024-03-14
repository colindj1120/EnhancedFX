package io.github.colindj1120.enhancedfx.beans.property;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class PositionProperty {
    private final DoubleProperty x = new SimpleDoubleProperty(0);
    private final DoubleProperty y = new SimpleDoubleProperty(0);

    public PositionProperty() {}

    public PositionProperty(double x, double y) {
        setX(x);
        setY(y);
    }

    public static PositionProperty of(double x, double y) {
        return new PositionProperty(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionProperty that = (PositionProperty) o;
        return getX() == (that.getX()) && getY() == (that.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "X/Y: [" + getX() + ", " + getY() + "]";
    }

    public double getX() {
        return x.get();
    }

    /**
     * The x coordinate property.
     */
    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double xPosition) {
        this.x.set(xPosition);
    }

    public double getY() {
        return y.get();
    }

    /**
     * The y coordinate property
     */
    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double yPosition) {
        this.y.set(yPosition);
    }
}
