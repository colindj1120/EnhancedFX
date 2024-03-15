package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.controls.TextAreaConfigurator;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

public interface TextAreaConfig<T extends TextAreaConfigurator<T>> extends TextInputControlConfig<T> {
    @Override
    T getConfigurator();

    @Override
    TextArea getNode();

    //region Add Listener Functions
    //*****************************************************************
    // Add Listener Functions
    //*****************************************************************

    default T addWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().wrapTextProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().wrapTextProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addPrefColumnCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefColumnCountProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addPrefColumnCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefColumnCountProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addPrefRowCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefRowCountProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addPrefRowCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefRowCountProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addScrollTopChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scrollTopProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addScrollTopInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scrollTopProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    default T addScrollLeftChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scrollLeftProperty()
                 .addListener(changeListener);
        return getConfigurator();
    }

    default T addScrollLeftInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scrollLeftProperty()
                 .addListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Add Listener Functions

    //region Remove Listener Functions
    //*****************************************************************
    // Remove Listener Functions
    //*****************************************************************

    default T removeWrapTextChangeListener(ChangeListener<? super Boolean> changeListener) {
        getNode().wrapTextProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeWrapTextInvalidationListener(InvalidationListener invalidationListener) {
        getNode().wrapTextProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removePrefColumnCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefColumnCountProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removePrefColumnCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefColumnCountProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removePrefRowCountChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().prefColumnCountProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removePrefRowCountInvalidationListener(InvalidationListener invalidationListener) {
        getNode().prefColumnCountProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeScrollTopChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scrollTopProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeScrollTopInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scrollTopProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    default T removeScrollLeftChangeListener(ChangeListener<? super Number> changeListener) {
        getNode().scrollTopProperty()
                 .removeListener(changeListener);
        return getConfigurator();
    }

    default T removeScrollLeftInvalidationListener(InvalidationListener invalidationListener) {
        getNode().scrollTopProperty()
                 .removeListener(invalidationListener);
        return getConfigurator();
    }

    //endregion Remove Listener Functions

    //region Binding Functions
    //*****************************************************************
    // Binding Functions
    //*****************************************************************

    // Wrap Text Property

    default T bindWrapTextProperty(ObservableValue<? extends Boolean> observableValue) {
        getNode().wrapTextProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindWrapTextProperty() {
        getNode().wrapTextProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalWrapTextProperty(Property<Boolean> otherProperty) {
        getNode().wrapTextProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalWrapTextProperty(Property<Boolean> otherProperty) {
        getNode().wrapTextProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pref Column Count Property

    default T bindPrefColumnCountProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefColumnCountProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindPrefColumnCountProperty() {
        getNode().prefColumnCountProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        getNode().prefColumnCountProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalPrefColumnCountProperty(Property<Number> otherProperty) {
        getNode().prefColumnCountProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Pref Row Count Property

    default T bindPrefRowCountProperty(ObservableValue<? extends Number> observableValue) {
        getNode().prefRowCountProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindPrefRowCountProperty() {
        getNode().prefRowCountProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalPrefRowCountProperty(Property<Number> otherProperty) {
        getNode().prefRowCountProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalPrefRowCountProperty(Property<Number> otherProperty) {
        getNode().prefRowCountProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scroll Top Property

    default T bindScrollTopProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scrollTopProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindScrollTopProperty() {
        getNode().scrollTopProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalScrollTopProperty(Property<Number> otherProperty) {
        getNode().scrollTopProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalScrollTopProperty(Property<Number> otherProperty) {
        getNode().scrollTopProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    // Scroll Left Property

    default T bindScrollLeftProperty(ObservableValue<? extends Number> observableValue) {
        getNode().scrollLeftProperty()
                 .bind(observableValue);
        return getConfigurator();
    }

    default T unbindScrollLeftProperty() {
        getNode().scrollLeftProperty()
                 .unbind();
        return getConfigurator();
    }

    default T bindBidirectionalScrollLeftProperty(Property<Number> otherProperty) {
        getNode().scrollLeftProperty()
                 .bindBidirectional(otherProperty);
        return getConfigurator();
    }

    default T unbindBidirectionalScrollLeftProperty(Property<Number> otherProperty) {
        getNode().scrollLeftProperty()
                 .unbindBidirectional(otherProperty);
        return getConfigurator();
    }

    //endregion Binding Functions

    //region Set Functions
    //*****************************************************************
    // Set Functions
    //*****************************************************************

    default T setWrapText(boolean value) {
        getNode().setWrapText(value);
        return getConfigurator();
    }

    default T setPrefColumnCount(int value) {
        getNode().setPrefColumnCount(value);
        return getConfigurator();
    }

    default T setPrefRowCount(int value) {
        getNode().setPrefColumnCount(value);
        return getConfigurator();
    }

    default T setScrollTop(double value) {
        getNode().setScrollTop(value);
        return getConfigurator();
    }

    //endregion Set Functions
}
