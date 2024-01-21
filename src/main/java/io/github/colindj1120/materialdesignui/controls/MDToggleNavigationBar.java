package io.github.colindj1120.materialdesignui.controls;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.StyleableObjectProperty;
import javafx.geometry.Orientation;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MDToggleNavigationBar extends Pane {
    private final HBox horizontalContainer = new HBox();
    private final VBox verticalContainer   = new VBox();

    private StyleableObjectProperty<Orientation> orientation;

    private final BooleanProperty isHorizontal = new SimpleBooleanProperty(this, "isHorizontal", true);
    private final BooleanProperty isVertical = new SimpleBooleanProperty(this, "isVertical", false);

    public MDToggleNavigationBar() {
        super();

        isHorizontal.bind(orientation.isEqualTo(Orientation.HORIZONTAL));
        isVertical.bind(orientation.isEqualTo(Orientation.VERTICAL));
    }

    public ReadOnlyBooleanProperty isHorizontalProperty() {
        return isHorizontal;
    }

    public boolean isHorizontal() {
        return isHorizontal.get();
    }

    public ReadOnlyBooleanProperty isVerticalProperty() {
        return isVertical;
    }

    public boolean isVertical() {
        return isVertical.get();
    }



}
