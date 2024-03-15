package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.ButtonBaseConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;

public interface ButtonBaseConfig<T extends ButtonBaseConfigurator<T>> extends LabeledConfig<T> {
    @Override
    T getConfigurator();

    @Override
    ButtonBase getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addArmedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().armedProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addArmedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().armedProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        getNode().onActionProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addOnActionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onActionProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeArmedChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().armedProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeArmedInvalidationListener(InvalidationListener invalidationListener) {
        getNode().armedProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        getNode().onActionProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnActionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onActionProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // On Action Property

    default T bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue) {
        getNode().onActionProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnActionProperty() {
        getNode().onActionProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        getNode().onActionProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        getNode().onActionProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setOnAction(EventHandler<ActionEvent> value) {
        getNode().setOnAction(value);
        return getConfigurator();
    }

    //endregion Set Functions

    //region Button Action Functions
    //*****************************************************************
    // Button Action Functions
    //*****************************************************************

    default T arm() {
        getNode().arm();
        return getConfigurator();
    }

    default T disarm() {
        getNode().disarm();
        return getConfigurator();
    }

    default T fire() {
        getNode().fire();
        return getConfigurator();
    }

    //endregion Button Action Functions
}
