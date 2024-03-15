package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.ToggleButtonConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public interface ToggleButtonConfig<T extends ToggleButtonConfigurator<T>> extends ButtonBaseConfig<T> {
    @Override
    T getConfigurator();

    @Override
    ToggleButton getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addSelectedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().selectedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addSelectedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addToggleGroupChangeListener(ChangeListener<? super ToggleGroup> changeListener) {
        getNode().toggleGroupProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addToggleGroupInvalidationListener(InvalidationListener invalidationListener) {
        getNode().toggleGroupProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeSelectedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().selectedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeSelectedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().selectedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeToggleGroupChangeListener(ChangeListener<? super ToggleGroup> changeListener) {
        getNode().toggleGroupProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeToggleGroupInvalidationListener(InvalidationListener invalidationListener) {
        getNode().toggleGroupProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Selected Property

    default T bindSelectedProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().selectedProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindSelectedProperty() {
        getNode().selectedProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalSelectedProperty(Property<Boolean> otherProperty) {
        getNode().selectedProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalSelectedProperty(Property<Boolean> otherProperty) {
        getNode().selectedProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Toggle Group Property

    default T bindToggleGroupProperty(ObservableValue<? extends ToggleGroup> observableValue) {
        getNode().toggleGroupProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindToggleGroupProperty() {
        getNode().toggleGroupProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalToggleGroupProperty(Property<ToggleGroup> otherProperty) {
        getNode().toggleGroupProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalToggleGroupProperty(Property<ToggleGroup> otherProperty) {
        getNode().toggleGroupProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setSelected(Boolean value) {
        getNode().setSelected(value);
        return getConfigurator();
    }

    default T setToggleGroup(ToggleGroup value) {
        getNode().setToggleGroup(value);
        return getConfigurator();
    }

    //endregion Set Functions
}