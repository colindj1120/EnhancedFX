package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.LabelConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;

public interface LabelConfig<T extends LabelConfigurator<T>> extends LabeledConfig<T> {
    @Override
    T getConfigurator();

    @Override
    Label getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addLabelForChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().labelForProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addLabelForInvalidationListener(InvalidationListener invalidationListener) {
        getNode().labelForProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeLabelForChangeListener(ChangeListener<? super Node> changeListener) {
        getNode().labelForProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeLabelForInvalidationListener(InvalidationListener invalidationListener) {
        getNode().labelForProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Label For Property

    default T bindLabelForProperty(ObservableValue<? extends Node> observableValue) {
        getNode().labelForProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindLabelForProperty() {
        getNode().labelForProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalLabelForProperty(Property<Node> otherProperty) {
        getNode().labelForProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalLabelForProperty(Property<Node> otherProperty) {
        getNode().labelForProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setLabelFor(Node value) {
        getNode().setLabelFor(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
