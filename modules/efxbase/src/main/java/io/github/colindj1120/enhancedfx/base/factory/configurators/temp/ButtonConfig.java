package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.ButtonConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

public interface ButtonConfig<T extends ButtonConfigurator<T>> extends ButtonBaseConfig<T> {
    @Override
    T getConfigurator();

    @Override
    Button getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().defaultButtonProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addDefaultButtonInvalidationListener(InvalidationListener invalidationListener) {
        getNode().defaultButtonProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cancelButtonProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addCancelButtonInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cancelButtonProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeDefaultButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().defaultButtonProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeDefaultButtonInvalidationListener(InvalidationListener invalidationListener) {
        getNode().defaultButtonProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeCancelButtonChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().cancelButtonProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeCancelButtonInvalidationListener(InvalidationListener invalidationListener) {
        getNode().cancelButtonProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Default Button Property

    default T bindDefaultButtonProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().defaultButtonProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindDefaultButtonProperty() {
        getNode().defaultButtonProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty) {
        getNode().defaultButtonProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalDefaultButtonProperty(Property<Boolean> otherProperty) {
        getNode().defaultButtonProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Cancel Button Property

    default T bindCancelButtonProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().cancelButtonProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindCancelButtonProperty() {
        getNode().cancelButtonProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty) {
        getNode().cancelButtonProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalCancelButtonProperty(Property<Boolean> otherProperty) {
        getNode().cancelButtonProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setDefaultButton(boolean value) {
        getNode().setDefaultButton(value);
        return getConfigurator();
    }

    default T setCancelButton(boolean value) {
        getNode().setCancelButton(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
