package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.TextFieldConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public interface TextFieldConfig<T extends TextFieldConfigurator<T>> extends TextInputControlConfig<T> {
    @Override
    T getConfigurator();

    @Override
    TextField getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addPrefColumnCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefColumnCountProperty().addListener(changeListener);
        return getConfigurator();
    }

    default T addPrefColumnCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefColumnCountProperty().addListener(invalidationListener);
        return getConfigurator();
    }

    default T addOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        getNode().onActionProperty().addListener(changeListener);
        return getConfigurator();
    }

    default T addOnActionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onActionProperty().addListener(invalidationListener);
        return getConfigurator();
    }

    default T addAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        getNode().alignmentProperty().addListener(changeListener);
        return getConfigurator();
    }

    default T addAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().alignmentProperty().addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removePrefColumnCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefColumnCountProperty().removeListener(changeListener);
        return getConfigurator();
    }

    default T removePrefColumnCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefColumnCountProperty().removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeOnActionChangeListener(ChangeListener<? super EventHandler<ActionEvent>> changeListener) {
        getNode().onActionProperty().removeListener(changeListener);
        return getConfigurator();
    }

    default T removeOnActionInvalidationListener(InvalidationListener invalidationListener) {
        getNode().onActionProperty().removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeAlignmentChangeListener(ChangeListener<? super Pos> changeListener) {
        getNode().alignmentProperty().removeListener(changeListener);
        return getConfigurator();
    }

    default T removeAlignmentInvalidationListener(InvalidationListener invalidationListener) {
        getNode().alignmentProperty().removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Pref Column Count Property

    default T bindPrefColumnCountProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefColumnCountProperty().bind(observableValue);
        return getConfigurator();
    }

    default T unbindPrefColumnCountProperty() {
        getNode().prefColumnCountProperty().unbind();
        return getConfigurator();
    }

    default T bindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        getNode().prefColumnCountProperty().bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        getNode().prefColumnCountProperty().unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // On Action Property

    default T bindOnActionProperty(ObservableValue<? extends EventHandler<ActionEvent>> observableValue) {
        getNode().onActionProperty().bind(observableValue);
        return getConfigurator();
    }

    default T unbindOnActionProperty() {
        getNode().onActionProperty().unbind();
        return getConfigurator();
    }

    default T bindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        getNode().onActionProperty().bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalOnActionProperty(Property<EventHandler<ActionEvent>> otherProperty) {
        getNode().onActionProperty().unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Alignment Property

    default T bindAlignmentProperty(ObservableValue<? extends Pos> observableValue) {
        getNode().alignmentProperty().bind(observableValue);
        return getConfigurator();
    }

    default T unbindAlignmentProperty() {
        getNode().alignmentProperty().unbind();
        return getConfigurator();
    }

    default T bindBidirectionalAlignmentProperty(Property<Pos> otherProperty) {
        getNode().alignmentProperty().bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalAlignmentProperty(Property<Pos> otherProperty) {
        getNode().alignmentProperty().unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setPrefColumnCount(int value) {
        getNode().setPrefColumnCount(value);
        return getConfigurator();
    }

    default T setOnAction(EventHandler<ActionEvent> value) {
        getNode().setOnAction(value);
        return getConfigurator();
    }

    default T setAlignment(Pos value) {
        getNode().setAlignment(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
